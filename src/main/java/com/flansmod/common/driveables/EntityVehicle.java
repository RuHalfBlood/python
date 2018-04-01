package com.flansmod.common.driveables;

import com.flansmod.api.IExplodeable;
import com.flansmod.client.model.AnimTankTrack;
import com.flansmod.client.model.AnimTrackLink;
import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.driveables.DriveableData;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EntityWheel;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.ShootPoint;
import com.flansmod.common.driveables.VehicleType;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketDriveableKey;
import com.flansmod.common.network.PacketParticle;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.network.PacketVehicleControl;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.tools.ItemTool;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityVehicle extends EntityDriveable implements IExplodeable {

   public int shellDelay;
   public int gunDelay;
   public int soundPosition;
   public int idlePosition;
   public float wheelsYaw;
   private int ticksSinceUsed = 0;
   public boolean varDoor;
   public float wheelsAngle;
   public int toggleTimer = 0;
   public float yaw = 0.0F;
   public float pitch = 0.0F;
   public float roll = 0.0F;
   public float yawSpeed = 0.0F;
   public boolean leftTurnHeld = false;
   public boolean rightTurnHeld = false;
   public boolean allWheelsOnGround;
   boolean lockTurretForward = false;
   public Vector3f doorPos = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f doorRot = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f door2Pos = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f door2Rot = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f prevDoorPos = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f prevDoorRot = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f prevDoor2Pos = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f prevDoor2Rot = new Vector3f(0.0F, 0.0F, 0.0F);
   public int yawDelay = 0;
   public int pitchDelay = 0;
   public boolean turretYawing;
   public boolean turretPitching;
   public boolean deployedSmoke = false;
   public EntityPlayer placer = null;
   public String placerName = null;
   public Entity target = null;
   public static final float targetAcquireInterval = 10.0F;
   public AnimTankTrack rightTrack;
   public AnimTankTrack leftTrack;
   public AnimTrackLink[] trackLinksLeft = new AnimTrackLink[0];
   public AnimTrackLink[] trackLinksRight = new AnimTrackLink[0];


   public EntityVehicle(World world) {
      super(world);
      super.stepHeight = 1.0F;
   }

   public EntityVehicle(World world, double x, double y, double z, VehicleType type, DriveableData data) {
      super(world, type, data);
      super.stepHeight = 1.0F;
      this.setPosition(x, y, z);
      this.initType(type, false);
   }

   public EntityVehicle(World world, double x, double y, double z, EntityPlayer p, VehicleType type, DriveableData data) {
      super(world, type, data);
      this.placer = p;
      this.placerName = p.getCommandSenderName();
      super.stepHeight = 1.0F;
      this.setPosition(x, y, z);
      this.rotateYaw(p.rotationYaw + 90.0F);
      this.initType(type, false);
      this.setupTracks(type);
   }

   public void setupTracks(DriveableType type) {
      this.rightTrack = new AnimTankTrack(type.rightTrackPoints, type.trackLinkLength);
      this.leftTrack = new AnimTankTrack(type.leftTrackPoints, type.trackLinkLength);
      int numLinks = Math.round(this.rightTrack.getTrackLength() / type.trackLinkLength);
      this.trackLinksLeft = new AnimTrackLink[numLinks];
      this.trackLinksRight = new AnimTrackLink[numLinks];

      for(int i = 0; i < numLinks; ++i) {
         float progress = 0.01F + type.trackLinkLength * (float)i;
         int trackPart = this.leftTrack.getTrackPart(progress);
         this.trackLinksLeft[i] = new AnimTrackLink(progress);
         this.trackLinksRight[i] = new AnimTrackLink(progress);
         this.trackLinksLeft[i].position = this.leftTrack.getPositionOnTrack(progress);
         this.trackLinksRight[i].position = this.rightTrack.getPositionOnTrack(progress);
         this.trackLinksLeft[i].rot = new RotatedAxes(0.0F, 0.0F, this.rotateTowards((Vector3f)this.leftTrack.points.get(trackPart == 0?this.leftTrack.points.size() - 1:trackPart - 1), this.trackLinksLeft[i].position));
         this.trackLinksRight[i].rot = new RotatedAxes(0.0F, 0.0F, this.rotateTowards((Vector3f)this.rightTrack.points.get(trackPart == 0?this.rightTrack.points.size() - 1:trackPart - 1), this.trackLinksRight[i].position));
         this.trackLinksLeft[i].zRot = this.rotateTowards((Vector3f)this.leftTrack.points.get(trackPart == 0?this.leftTrack.points.size() - 1:trackPart - 1), this.trackLinksLeft[i].position);
         this.trackLinksRight[i].zRot = this.rotateTowards((Vector3f)this.rightTrack.points.get(trackPart == 0?this.rightTrack.points.size() - 1:trackPart - 1), this.trackLinksRight[i].position);
      }

   }

   protected void initType(DriveableType type, boolean clientSide) {
      super.initType(type, clientSide);
      this.setupTracks(type);
   }

   public void readSpawnData(ByteBuf data) {
      super.readSpawnData(data);
   }

   protected void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setBoolean("VarDoor", this.varDoor);
   }

   protected void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.varDoor = tag.getBoolean("VarDoor");
   }

   public boolean isInRangeToRenderDist(double d) {
      double d1 = 400.0D;
      return d < d1 * d1;
   }

   public void onMouseMoved(int deltaX, int deltaY) {}

   public void setPositionRotationAndMotion(double x, double y, double z, float yaw, float pitch, float roll, double motX, double motY, double motZ, float velYaw, float velPitch, float velRoll, float throt, float steeringYaw) {
      super.setPositionRotationAndMotion(x, y, z, yaw, pitch, roll, motX, motY, motZ, velYaw, velPitch, velRoll, throt, steeringYaw);
      this.wheelsYaw = steeringYaw;
   }

   public boolean interactFirst(EntityPlayer entityplayer) {
      if(super.isDead) {
         return false;
      } else if(super.worldObj.isRemote) {
         return false;
      } else {
         ItemStack currentItem = entityplayer.getCurrentEquippedItem();
         if(currentItem != null && currentItem.getItem() instanceof ItemTool && ((ItemTool)currentItem.getItem()).type.healDriveables) {
            return true;
         } else {
            VehicleType type = this.getVehicleType();

            for(int i = 0; i <= type.numPassengers; ++i) {
               if(super.seats[i].interactFirst(entityplayer)) {
                  if(i == 0) {
                     this.shellDelay = type.vehicleShellDelay;
                     FlansMod.proxy.doTutorialStuff(entityplayer, this);
                  }

                  return true;
               }
            }

            return false;
         }
      }
   }

   public boolean pressKey(int key, EntityPlayer player) {
      VehicleType type = this.getVehicleType();
      if(super.worldObj.isRemote && (key == 6 || key == 8 || key == 9)) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableKey(key)));
         return true;
      } else {
         switch(key) {
         case 0:
            super.throttle += 0.01F;
            if(super.throttle > 1.0F) {
               super.throttle = 1.0F;
            }

            return true;
         case 1:
            super.throttle -= 0.01F;
            if(super.throttle < -1.0F) {
               super.throttle = -1.0F;
            }

            if(super.throttle < 0.0F && type.maxNegativeThrottle == 0.0F) {
               super.throttle = 0.0F;
            }

            return true;
         case 2:
            --this.wheelsYaw;
            this.leftTurnHeld = true;
            return true;
         case 3:
            ++this.wheelsYaw;
            this.leftTurnHeld = true;
            return true;
         case 4:
            super.throttle *= 0.8F;
            if(super.throttle > 0.001F) {
               super.throttle = 0.0F;
            }

            if(super.throttle < -0.001F) {
               super.throttle = 0.0F;
            }

            this.target = null;
            return true;
         case 5:
            Minecraft mc = Minecraft.getMinecraft();
            if(this.toggleTimer <= 0 && TeamsManager.allowVehicleZoom) {
               this.toggleTimer = 10;
               if(mc.gameSettings.fovSetting != 10.0F) {
                  mc.gameSettings.fovSetting = 10.0F;
                  mc.gameSettings.mouseSensitivity = 0.2F;
               } else if(mc.gameSettings.fovSetting == 10.0F) {
                  mc.gameSettings.fovSetting = 70.0F;
                  mc.gameSettings.mouseSensitivity = 0.5F;
               }
            }

            return true;
         case 6:
            super.seats[0].riddenByEntity.setInvisible(false);
            super.seats[0].riddenByEntity.mountEntity((Entity)null);
            return true;
         case 7:
            if(super.worldObj.isRemote) {
               FlansMod.proxy.openDriveableMenu((EntityPlayer)super.seats[0].riddenByEntity, super.worldObj, this);
            }

            return true;
         case 8:
         case 9:
            return super.pressKey(key, player);
         case 10:
            FlansMod.proxy.changeControlMode((EntityPlayer)super.seats[0].riddenByEntity);
            super.seats[0].targetYaw = super.seats[0].looking.getYaw();
            super.seats[0].targetPitch = super.seats[0].looking.getPitch();
            return true;
         case 11:
            super.seats[0].targetYaw -= super.seats[0].seatInfo.aimingSpeed.x;
            return true;
         case 12:
            super.seats[0].targetYaw += super.seats[0].seatInfo.aimingSpeed.x;
            return true;
         case 13:
            if(super.seats[0].targetPitch < -super.seats[0].seatInfo.minPitch) {
               super.seats[0].targetPitch += super.seats[0].seatInfo.aimingSpeed.y;
            }

            return true;
         case 14:
            if(this.toggleTimer <= 0) {
               this.varDoor = !this.varDoor;
               if(type.hasDoor) {
                  player.addChatMessage(new ChatComponentText("Doors " + (this.varDoor?"open":"closed")));
               }

               this.toggleTimer = 10;
               FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketVehicleControl(this)));
            }

            return true;
         case 15:
            if(super.seats[0].targetPitch > -super.seats[0].seatInfo.maxPitch) {
               super.seats[0].targetPitch -= super.seats[0].seatInfo.aimingSpeed.y;
            }

            return true;
         case 16:
            return true;
         case 18:
            if(type.hasFlare && super.ticksFlareUsing <= 0 && super.flareDelay <= 0) {
               super.ticksFlareUsing = type.timeFlareUsing * 20;
               super.flareDelay = type.flareDelay;
               this.dischargeSmoke();
               if(super.worldObj.isRemote) {
                  FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableKey(key)));
               } else {
                  this.dischargeSmoke();
                  if(!type.flareSound.isEmpty()) {
                     PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, type.flareSound, false);
                  }
               }

               return true;
            }
         case 17:
         default:
            return false;
         }
      }
   }

   @SideOnly(Side.CLIENT)
   public void resetZoom() {
      if(TeamsManager.allowVehicleZoom) {
         Minecraft mc = Minecraft.getMinecraft();
         if(mc.gameSettings.fovSetting == 10.0F) {
            mc.gameSettings.fovSetting = 70.0F;
            mc.gameSettings.mouseSensitivity = 0.5F;
         }
      }

   }

   public Vector3f getLookVector(ShootPoint dp) {
      return this.rotate(super.seats[0].looking.getXAxis());
   }

   public void onUpdate() {
      double bkPrevPosY = super.prevPosY;
      super.onUpdate();
      super.renderDistanceWeight = 4000.0D;
      this.animateFancyTracks();
      if(super.worldObj.isRemote) {
         Iterator type = this.findEntitiesWithinbounds().iterator();

         while(type.hasNext()) {
            Entity data = (Entity)type.next();
            if(!this.isPartOfThis(data) && data instanceof EntityPlayer) {
               this.moveRiders(data);
            }
         }
      }

      VehicleType var30 = this.getVehicleType();
      DriveableData var31 = this.getDriveableData();
      if(var30 == null) {
         FlansMod.log("Vehicle type null. Not ticking vehicle");
      } else {
         if(var30.shootWithOpenDoor) {
            super.canFire = this.varDoor;
         }

         boolean thePlayerIsDrivingThis = super.worldObj.isRemote && super.seats[0] != null && super.seats[0].riddenByEntity instanceof EntityPlayer && FlansMod.proxy.isThePlayer((EntityPlayer)super.seats[0].riddenByEntity);
         ++this.ticksSinceUsed;
         if(!super.worldObj.isRemote && super.seats[0].riddenByEntity != null) {
            this.ticksSinceUsed = 0;
         }

         if(!super.worldObj.isRemote && TeamsManager.vehicleLife > 0 && this.ticksSinceUsed > TeamsManager.vehicleLife * 20) {
            this.setDead();
         }

         if(super.worldObj.isRemote && (super.varFlare || super.ticksFlareUsing > var30.timeFlareUsing * 20 - 5) && super.ticksExisted % 5 == 0) {
            this.deployedSmoke = true;
         }

         if(var30.setPlayerInvisible && !super.worldObj.isRemote && super.seats[0].riddenByEntity != null) {
            super.seats[0].riddenByEntity.setInvisible(true);
         }

         if(super.ticksFlareUsing <= 0) {
            this.deployedSmoke = false;
         }

         if(super.ticksFlareUsing > 0) {
            --super.ticksFlareUsing;
         }

         if(super.flareDelay > 0) {
            --super.flareDelay;
         }

         if(this.shellDelay > 0) {
            --this.shellDelay;
         }

         if(this.gunDelay > 0) {
            --this.gunDelay;
         }

         if(this.toggleTimer > 0) {
            --this.toggleTimer;
         }

         if(this.soundPosition > 0) {
            --this.soundPosition;
         }

         if(this.idlePosition > 0) {
            --this.idlePosition;
         }

         if(var30.tank && !this.hasBothTracks()) {
            super.throttle = 0.0F;
         }

         if(super.disabled) {
            this.wheelsYaw = 0.0F;
         }

         if(this.hasEnoughFuel()) {
            this.wheelsAngle += super.throttle / 7.0F;
         }

         this.prevDoorPos = this.doorPos;
         this.prevDoorRot = this.doorRot;
         this.prevDoor2Pos = this.door2Pos;
         this.prevDoor2Rot = this.door2Rot;
         if(!this.varDoor) {
            this.doorPos = this.transformPart(this.doorPos, var30.doorPos1, var30.doorRate);
            this.doorRot = this.transformPart(this.doorRot, var30.doorRot1, var30.doorRotRate);
            this.door2Pos = this.transformPart(this.door2Pos, var30.door2Pos1, var30.door2Rate);
            this.door2Rot = this.transformPart(this.door2Rot, var30.door2Rot1, var30.door2RotRate);
         } else {
            this.doorPos = this.transformPart(this.doorPos, var30.doorPos2, var30.doorRate);
            this.doorRot = this.transformPart(this.doorRot, var30.doorRot2, var30.doorRotRate);
            this.door2Pos = this.transformPart(this.door2Pos, var30.door2Pos2, var30.door2Rate);
            this.door2Rot = this.transformPart(this.door2Rot, var30.door2Rot2, var30.door2RotRate);
         }

         this.wheelsYaw *= 0.9F;
         if(this.wheelsYaw > 20.0F) {
            this.wheelsYaw = 20.0F;
         }

         if(this.wheelsYaw < -20.0F) {
            this.wheelsYaw = -20.0F;
         }

         float drz;
         if(super.worldObj.isRemote && !thePlayerIsDrivingThis && super.serverPositionTransitionTicker > 0) {
            double amountToMoveCar = super.posX + (super.field_70118_ct - super.posX) / (double)super.serverPositionTransitionTicker;
            double y = super.posY + (super.field_70117_cu - super.posY) / (double)super.serverPositionTransitionTicker;
            double turningLeft = super.posZ + (super.field_70116_cv - super.posZ) / (double)super.serverPositionTransitionTicker;
            double seat = MathHelper.wrapAngleTo180_double(super.serverYaw - (double)super.axes.getYaw());
            double dy = MathHelper.wrapAngleTo180_double(super.serverPitch - (double)super.axes.getPitch());
            double drx = MathHelper.wrapAngleTo180_double(super.serverRoll - (double)super.axes.getRoll());
            super.rotationYaw = (float)((double)super.axes.getYaw() + seat / (double)super.serverPositionTransitionTicker);
            super.rotationPitch = (float)((double)super.axes.getPitch() + dy / (double)super.serverPositionTransitionTicker);
            drz = (float)((double)super.axes.getRoll() + drx / (double)super.serverPositionTransitionTicker);
            --super.serverPositionTransitionTicker;
            this.setPosition(amountToMoveCar, y, turningLeft);
            this.setRotation(super.rotationYaw, super.rotationPitch, drz);
         }

         this.correctWheelPos();
         Vector3f var32 = new Vector3f();
         EntityWheel[] bmy = super.wheels;
         int var34 = bmy.length;

         float dry;
         float dxz;
         float drxz;
         float var54;
         for(int animSpeed = 0; animSpeed < var34; ++animSpeed) {
            EntityWheel var37 = bmy[animSpeed];
            if(var37 != null) {
               double turningRight = var37.posY;
               super.onGround = true;
               var37.onGround = true;
               List dx = super.worldObj.getEntitiesWithinAABB(Entity.class, var37.boundingBox);
               boolean var48 = false;
               Iterator dz = dx.iterator();

               while(dz.hasNext()) {
                  Entity var52 = (Entity)dz.next();
                  if(this.getClass().toString().indexOf("cuchaz.ships.EntityShip") > 0) {
                     var37.onGround = true;
                     var48 = true;
                  }
               }

               var37.rotationYaw = super.axes.getYaw();
               if(!var30.tank && (var37.ID == 2 || var37.ID == 3)) {
                  var37.rotationYaw += this.wheelsYaw;
               }

               var37.motionX *= 0.8999999761581421D;
               var37.motionY *= super.posY - bkPrevPosY < 0.0D?0.9990000128746033D:0.8999999761581421D;
               var37.motionZ *= 0.8999999761581421D;
               boolean var50 = !TeamsManager.vehiclesNeedFuel || super.seats != null && super.seats[0] != null && super.seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
               if(var50 || var31.fuelInTank > var31.engine.fuelConsumption * super.throttle) {
                  if(!this.getVehicleType().tank) {
                     var54 = 0.1F * super.throttle * (super.throttle > 0.0F?var30.maxThrottle:var30.maxNegativeThrottle) * var31.engine.engineSpeed;
                     var37.motionX += Math.cos((double)(var37.rotationYaw * 3.1415927F / 180.0F)) * (double)var54;
                     var37.motionZ += Math.sin((double)(var37.rotationYaw * 3.1415927F / 180.0F)) * (double)var54;
                     if(var37.ID != 2 && var37.ID != 3) {
                        var37.motionX *= 0.8999999761581421D;
                        var37.motionZ *= 0.8999999761581421D;
                     } else {
                        var54 = 0.01F * (this.wheelsYaw > 0.0F?var30.turnLeftModifier:var30.turnRightModifier) * (float)(super.throttle > 0.0F?1:-1);
                        var37.motionX -= var37.getSpeedXZ() * Math.sin((double)(var37.rotationYaw * 3.1415927F / 180.0F)) * (double)var54 * (double)this.wheelsYaw;
                        var37.motionZ += var37.getSpeedXZ() * Math.cos((double)(var37.rotationYaw * 3.1415927F / 180.0F)) * (double)var54 * (double)this.wheelsYaw;
                     }
                  } else {
                     boolean var53 = var37.ID == 0 || var37.ID == 3;
                     dry = 0.02F;
                     var37.motionX *= (double)(1.0F - Math.abs(this.wheelsYaw) * dry);
                     var37.motionZ *= (double)(1.0F - Math.abs(this.wheelsYaw) * dry);
                     drz = 0.04F * (super.throttle > 0.0F?var30.maxThrottle:var30.maxNegativeThrottle) * var31.engine.engineSpeed;
                     dxz = 0.1F * (this.wheelsYaw > 0.0F?var30.turnLeftModifier:var30.turnRightModifier);
                     drxz = (super.throttle + this.wheelsYaw * (float)(var53?1:-1) * dxz) * drz;
                     var37.motionX += (double)drxz * Math.cos((double)(var37.rotationYaw * 3.1415927F / 180.0F));
                     var37.motionZ += (double)drxz * Math.sin((double)(var37.rotationYaw * 3.1415927F / 180.0F));
                     this.yawSpeed = (float)((double)this.yawSpeed + (double)drxz * Math.sin((double)(var37.rotationYaw * 3.1415927F / 180.0F)));
                  }
               }

               var37.moveEntity(var37.motionX, var37.motionY, var37.motionZ);
               Vector3f var56 = super.axes.findLocalVectorGlobally(this.getVehicleType().wheelPositions[var37.ID].position);
               Vector3f var55 = new Vector3f(var37.posX - super.posX, var37.posY - super.posY, var37.posZ - super.posZ);
               Vector3f var57 = (Vector3f)Vector3f.sub(var56, var55, (Vector3f)null).scale(var30.wheelSpringStrength);
               if(var57.length() > 0.001F) {
                  var37.moveEntity((double)var57.x, (double)var57.y, (double)var57.z);
                  var57.scale(0.5F);
                  Vector3f.sub(var32, var57, var32);
               }

               dxz = 0.0F;
               if(super.wheels[0] != null && super.wheels[1] != null && super.wheels[2] != null && super.wheels[3] != null) {
                  dxz = (float)(super.wheels[0].posX + super.wheels[1].posX + super.wheels[2].posX + super.wheels[3].posX) / 4.0F;
                  if(!super.wheels[0].onGround && !super.wheels[1].onGround && !super.wheels[2].onGround && !super.wheels[3].onGround) {
                     this.allWheelsOnGround = false;
                  } else {
                     this.allWheelsOnGround = true;
                  }
               }

               if(this.allWheelsOnGround && (!var30.floatOnWater || !super.worldObj.isAnyLiquid(var37.boundingBox.copy().offset(0.0D, (double)(-var30.floatOffset), 0.0D))) && !var37.onDeck) {
                  var37.moveEntity(0.0D, -0.196D, 0.0D);
               } else if(var30.floatOnWater && super.worldObj.isAnyLiquid(var37.boundingBox.copy().offset(0.0D, (double)(-var30.floatOffset), 0.0D)) && super.worldObj.isAnyLiquid(var37.boundingBox.copy().offset(0.0D, (double)(1.0F - var30.floatOffset), 0.0D)) && !var37.onDeck) {
                  var37.moveEntity(0.0D, 1.0D, 0.0D);
               } else if((!var30.floatOnWater || !super.worldObj.isAnyLiquid(var37.boundingBox.copy().offset(0.0D, (double)(-var30.floatOffset), 0.0D)) || super.worldObj.isAnyLiquid(var37.boundingBox.copy().offset(0.0D, (double)(1.0F - var30.floatOffset), 0.0D))) && !var37.onDeck) {
                  var37.moveEntity(0.0D, !super.onDeck?-0.9800000190734863D:0.0D, 0.0D);
               } else {
                  var37.moveEntity(0.0D, 0.0D, 0.0D);
                  this.roll = 0.0F;
                  this.pitch = 0.0F;
               }

               if((double)super.throttle >= 0.3D || (double)super.throttle <= -0.3D) {
                  Vector3f var58 = new Vector3f(0.0F, 0.0F, 0.0F);
                  Vector3f tyaw = var30.wheelPositions[var37.ID].position;
                  tyaw = super.axes.findLocalVectorGlobally(tyaw);
                  if((double)super.throttle > 0.1D) {
                     var58 = new Vector3f(1.0F, 0.0F, 0.0F);
                  }

                  if((double)super.throttle < -0.1D) {
                     var58 = new Vector3f(-1.0F, 0.0F, 0.0F);
                  }

                  if((var37.ID == 2 || var37.ID == 3) && (double)this.wheelsYaw >= 0.1D) {
                     var58 = new Vector3f(var58.x, 0.0F, 1.0F);
                  }

                  if((var37.ID == 0 || var37.ID == 1) && (double)this.wheelsYaw <= -0.1D) {
                     var58 = new Vector3f(var58.x, 0.0F, -1.0F);
                  }

                  var58 = super.axes.findLocalVectorGlobally(var58);
                  Vector3f tpitch = new Vector3f(super.posX + (double)tyaw.x + (double)var58.x, super.posY + (double)tyaw.y, super.posZ + (double)tyaw.z + (double)var58.z);
                  boolean troll = super.worldObj.isAirBlock(Math.round(tpitch.x), Math.round(tpitch.y), Math.round(tpitch.z));
                  boolean velocityScale = super.worldObj.isAirBlock(Math.round(tpitch.x), Math.round(tpitch.y + var30.wheelStepHeight), Math.round(tpitch.z));
                  if(!troll && !velocityScale) {
                     super.throttle = (float)((double)super.throttle * 0.6D);
                     EntityWheel[] steeringScale = super.wheels;
                     int effectiveWheelSpeed = steeringScale.length;

                     for(int var27 = 0; var27 < effectiveWheelSpeed; ++var27) {
                        EntityWheel wheel2 = steeringScale[var27];
                        super.axes.findLocalVectorGlobally(var30.wheelPositions[wheel2.ID].position);
                     }
                  }
               }
            }
         }

         if(super.wheels[0] != null && super.wheels[1] != null && super.wheels[2] != null && super.wheels[3] != null) {
            super.lastPos.x = (float)(super.wheels[0].motionX + super.wheels[1].motionX + super.wheels[2].motionX + super.wheels[3].motionX) / 4.0F;
            super.lastPos.y = (float)(super.wheels[0].motionY + super.wheels[1].motionY + super.wheels[2].motionY + super.wheels[3].motionY) / 4.0F;
            super.lastPos.z = (float)(super.wheels[0].motionZ + super.wheels[1].motionZ + super.wheels[2].motionZ + super.wheels[3].motionZ) / 4.0F;
         }

         double var33 = super.motionY;
         super.motionY = (double)var32.y;
         if(var32 != null) {
            this.moveEntity((double)var32.x, (double)var32.y, (double)var32.z);
         }

         super.motionY = var33;
         if(super.wheels[0] != null && super.wheels[1] != null && super.wheels[2] != null && super.wheels[3] != null) {
            Vector3f var35 = new Vector3f((super.wheels[2].posX + super.wheels[3].posX) / 2.0D, (super.wheels[2].posY + super.wheels[3].posY) / 2.0D, (super.wheels[2].posZ + super.wheels[3].posZ) / 2.0D);
            Vector3f var39 = new Vector3f((super.wheels[0].posX + super.wheels[1].posX) / 2.0D, (super.wheels[0].posY + super.wheels[1].posY) / 2.0D, (super.wheels[0].posZ + super.wheels[1].posZ) / 2.0D);
            Vector3f var41 = new Vector3f((super.wheels[0].posX + super.wheels[3].posX) / 2.0D, (super.wheels[0].posY + super.wheels[3].posY) / 2.0D, (super.wheels[0].posZ + super.wheels[3].posZ) / 2.0D);
            Vector3f var44 = new Vector3f((super.wheels[1].posX + super.wheels[2].posX) / 2.0D, (super.wheels[1].posY + super.wheels[2].posY) / 2.0D, (super.wheels[1].posZ + super.wheels[2].posZ) / 2.0D);
            float var47;
            float var49;
            float var51;
            if(var35.y > var39.y && super.throttle > 0.0F) {
               var47 = var35.y - var39.y;
               var49 = var35.x - var39.x;
               var49 = (float)Math.sqrt((double)(var49 * var49));
               var51 = var35.z - var39.z;
               var51 = (float)Math.sqrt((double)(var51 * var51));
               var54 = (float)Math.sqrt((double)(var49 + var51));
               var47 /= var54;
               super.throttle *= 1.0F - var47 / 60.0F;
            }

            var47 = var35.x - var39.x;
            var49 = var35.y - var39.y;
            var51 = var35.z - var39.z;
            var54 = var41.x - var44.x;
            dry = var41.y - var44.y;
            drz = var41.z - var44.z;
            dxz = (float)Math.sqrt((double)(var47 * var47 + var51 * var51));
            drxz = (float)Math.sqrt((double)(var54 * var54 + drz * drz));
            float var59 = (float)Math.atan2((double)var51, (double)var47);
            float var60 = -((float)Math.atan2((double)var49, (double)dxz));
            float var61 = 0.0F;
            if(var30.canRoll) {
               var61 = -((float)Math.atan2((double)dry, (double)drxz));
            }

            this.yaw = var59;
            this.pitch = this.Lerp(this.pitch, var60, 0.2F);
            this.roll = this.Lerp(this.roll, var61, 0.2F);
            float var62;
            float var63;
            float var64;
            if(var30.tank) {
               var62 = 0.04F * (super.throttle > 0.0F?var30.maxThrottle:var30.maxNegativeThrottle) * var31.engine.engineSpeed;
               var63 = 0.1F * (this.wheelsYaw > 0.0F?var30.turnLeftModifier:var30.turnRightModifier);
               var64 = this.wheelsYaw * var63 * var62;
               this.yaw = super.axes.getYaw() / 180.0F * 3.14159F + var64;
            } else {
               var62 = 0.1F * super.throttle * (super.throttle > 0.0F?var30.maxThrottle:var30.maxNegativeThrottle) * var31.engine.engineSpeed;
               var63 = 0.1F * (this.wheelsYaw > 0.0F?var30.turnLeftModifier:var30.turnRightModifier);
               var64 = this.wheelsYaw * var63 * var62;
               this.yaw = super.axes.getYaw() / 180.0F * 3.14159F + var64;
            }

            super.axes.setAngles(this.yaw * 180.0F / 3.14159F, this.pitch * 180.0F / 3.14159F, this.roll * 180.0F / 3.14159F);
         }

         if(super.ridingEntity != null && super.ridingEntity.getClass().toString().indexOf("mcheli.aircraft.MCH_EntitySeat") > 0) {
            super.axes.setAngles(super.ridingEntity.rotationYaw + 90.0F, 0.0F, 0.0F);
         }

         this.checkForCollisions();
         if(Math.abs(super.throttle) > 0.01F && Math.abs(super.throttle) < 0.2F && this.soundPosition == 0 && this.hasEnoughFuel()) {
            PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)var30.startSoundRange, super.dimension, var30.startSound, false);
            this.soundPosition = var30.startSoundLength;
         }

         if(super.throttle >= 0.2F && this.soundPosition == 0 && this.hasEnoughFuel()) {
            PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)var30.engineSoundRange, super.dimension, var30.engineSound, false);
            this.soundPosition = var30.engineSoundLength;
         }

         if(super.seats[0] != null && super.throttle <= 0.01F && super.throttle >= -0.2F && super.seats[0].riddenByEntity != null && this.idlePosition == 0) {
            PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)var30.engineSoundRange, super.dimension, var30.idleSound, false);
            this.idlePosition = var30.idleSoundLength;
         }

         if(super.throttle <= -0.2F && this.soundPosition == 0 && this.hasEnoughFuel()) {
            PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)var30.backSoundRange, super.dimension, var30.backSound, false);
            this.soundPosition = var30.backSoundLength;
         }

         EntitySeat[] var36 = super.seats;
         int var40 = var36.length;

         for(int var43 = 0; var43 < var40; ++var43) {
            EntitySeat var46 = var36[var43];
            if(var46 != null) {
               var46.updatePosition();
            }
         }

         if(thePlayerIsDrivingThis) {
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketVehicleControl(this)));
            super.field_70118_ct = super.posX;
            super.field_70117_cu = super.posY;
            super.field_70116_cv = super.posZ;
            super.serverYaw = (double)super.axes.getYaw();
         }

         if(!super.worldObj.isRemote && super.ticksExisted % 5 == 0) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketVehicleControl(this), super.posX, super.posY, super.posZ, 400.0F, super.dimension);
         }

         byte var38 = 4;
         if(((double)super.throttle <= 0.05D || (double)super.throttle > 0.33D) && ((double)super.throttle >= -0.05D || (double)super.throttle < -0.33D)) {
            if(((double)super.throttle <= 0.33D || (double)super.throttle > 0.66D) && ((double)super.throttle >= -0.33D || (double)super.throttle < -0.66D)) {
               if(((double)super.throttle <= 0.66D || (double)super.throttle > 0.9D) && ((double)super.throttle >= -0.66D || (double)super.throttle < -0.9D)) {
                  if((double)super.throttle > 0.9D && super.throttle <= 1.0F || (double)super.throttle < -0.9D && super.throttle >= -1.0F) {
                     var38 = 0;
                  }
               } else {
                  var38 = 1;
               }
            } else {
               var38 = 2;
            }
         } else {
            var38 = 3;
         }

         boolean var42 = false;
         boolean var45 = false;
         if((double)super.throttle > 0.05D) {
            --super.animCountLeft;
            --super.animCountRight;
         } else if((double)super.throttle < -0.05D) {
            ++super.animCountLeft;
            ++super.animCountRight;
         } else if(this.wheelsYaw < -1.0F) {
            var42 = true;
            ++super.animCountLeft;
            --super.animCountRight;
            var38 = 1;
            if(this.soundPosition == 0 && this.hasEnoughFuel() && var30.tank) {
               PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)var30.engineSoundRange, super.dimension, var30.engineSound, false);
               this.soundPosition = var30.engineSoundLength;
            }
         } else if(this.wheelsYaw > 1.0F) {
            var45 = true;
            --super.animCountLeft;
            ++super.animCountRight;
            var38 = 1;
            if(this.soundPosition == 0 && this.hasEnoughFuel() && var30.tank) {
               PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)var30.engineSoundRange, super.dimension, var30.engineSound, false);
               this.soundPosition = var30.engineSoundLength;
            }
         } else {
            var42 = false;
            var45 = false;
         }

         if(super.animCountLeft <= 0) {
            super.animCountLeft = var38;
            ++super.animFrameLeft;
         }

         if(super.animCountRight <= 0) {
            super.animCountRight = var38;
            ++super.animFrameRight;
         }

         if((super.throttle < 0.0F || var42) && super.animCountLeft >= var38) {
            super.animCountLeft = 0;
            --super.animFrameLeft;
         }

         if((super.throttle < 0.0F || var45) && super.animCountRight >= var38) {
            super.animCountRight = 0;
            --super.animFrameRight;
         }

         if(super.animFrameLeft > var30.animFrames) {
            super.animFrameLeft = 0;
         }

         if(super.animFrameLeft < 0) {
            super.animFrameLeft = var30.animFrames;
         }

         if(super.animFrameRight > var30.animFrames) {
            super.animFrameRight = 0;
         }

         if(super.animFrameRight < 0) {
            super.animFrameRight = var30.animFrames;
         }

      }
   }

   public Entity getValidTarget() {
      if(this.placer == null && this.placerName != null) {
         this.placer = super.worldObj.getPlayerEntityByName(this.placerName);
      }

      float targetRange = 150.0F;
      Entity target = null;
      Iterator var3 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.expand((double)targetRange, (double)targetRange, (double)targetRange)).iterator();

      while(var3.hasNext()) {
         Object obj = var3.next();
         Entity candidateEntity = (Entity)obj;
         boolean targetMobs = true;
         boolean targetPlayers = false;
         boolean targetPlanes = true;
         boolean targetVehicles = true;
         if((targetMobs && candidateEntity instanceof EntityBat || targetPlayers && candidateEntity instanceof EntityPlayer || targetPlanes && candidateEntity instanceof EntityPlane || targetVehicles && candidateEntity instanceof EntityVehicle) && candidateEntity.getDistanceToEntity(this) < targetRange) {
            targetRange = candidateEntity.getDistanceToEntity(this);
            if(this.isPartOfThis(candidateEntity)) {
               candidateEntity = null;
            }

            if(candidateEntity instanceof EntityPlayer) {
               if(candidateEntity == this.placer || candidateEntity.getCommandSenderName().equals(this.placerName)) {
                  candidateEntity = null;
               }

               if(TeamsManager.enabled && TeamsManager.getInstance().currentRound != null && this.placer != null) {
                  PlayerData placerData = PlayerHandler.getPlayerData(this.placer, super.worldObj.isRemote?Side.CLIENT:Side.SERVER);
                  PlayerData candidateData = PlayerHandler.getPlayerData((EntityPlayer)candidateEntity, super.worldObj.isRemote?Side.CLIENT:Side.SERVER);
                  if(candidateData.team == Team.spectators || candidateData.team == null) {
                     candidateEntity = null;
                  }

                  EntityPlayerMP var10001 = (EntityPlayerMP)this.placer;
                  if(!TeamsManager.getInstance().currentRound.gametype.playerCanAttack(var10001, placerData.team, (EntityPlayerMP)candidateEntity, candidateData.team)) {
                     candidateEntity = null;
                  }
               }
            }

            target = candidateEntity;
         }
      }

      if(target != null) {
         return target;
      } else {
         return null;
      }
   }

   public void animateFancyTracks() {
      float funkypart = (float)this.getVehicleType().trackLinkFix;
      boolean funk = true;
      float funk2 = 0.0F;

      int i;
      float speed;
      float newAngle;
      int part;
      for(i = 0; i < this.trackLinksLeft.length; ++i) {
         this.trackLinksLeft[i].prevPosition = this.trackLinksLeft[i].position;
         this.trackLinksLeft[i].prevZRot = this.trackLinksLeft[i].zRot;
         speed = super.throttle * 1.5F - this.wheelsYaw / 12.0F;
         this.trackLinksLeft[i].progress += speed;
         if(this.trackLinksLeft[i].progress > this.leftTrack.getTrackLength()) {
            this.trackLinksLeft[i].progress -= this.leftTrack.getTrackLength();
         }

         if(this.trackLinksLeft[i].progress < 0.0F) {
            this.trackLinksLeft[i].progress += this.leftTrack.getTrackLength();
         }

         for(this.trackLinksLeft[i].position = this.leftTrack.getPositionOnTrack(this.trackLinksLeft[i].progress); this.trackLinksLeft[i].zRot > 180.0F; this.trackLinksLeft[i].zRot -= 360.0F) {
            ;
         }

         while(this.trackLinksLeft[i].zRot <= -180.0F) {
            this.trackLinksLeft[i].zRot += 360.0F;
         }

         newAngle = this.rotateTowards((Vector3f)this.leftTrack.points.get(this.leftTrack.getTrackPart(this.trackLinksLeft[i].progress)), this.trackLinksLeft[i].position);
         part = this.leftTrack.getTrackPart(this.trackLinksLeft[i].progress);
         if(funk) {
            funk2 = speed < 0.0F?0.0F:1.0F;
         } else {
            funk2 = speed < 0.0F?-1.0F:0.0F;
         }

         this.trackLinksLeft[i].zRot = this.Lerp(this.trackLinksLeft[i].zRot, newAngle, (float)part != funkypart + funk2?0.5F:1.0F);
      }

      for(i = 0; i < this.trackLinksRight.length; ++i) {
         this.trackLinksRight[i].prevPosition = this.trackLinksRight[i].position;
         this.trackLinksRight[i].prevZRot = this.trackLinksRight[i].zRot;
         speed = super.throttle * 1.5F + this.wheelsYaw / 12.0F;
         this.trackLinksRight[i].progress += speed;
         if(this.trackLinksRight[i].progress > this.rightTrack.getTrackLength()) {
            this.trackLinksRight[i].progress -= this.leftTrack.getTrackLength();
         }

         if(this.trackLinksRight[i].progress < 0.0F) {
            this.trackLinksRight[i].progress += this.rightTrack.getTrackLength();
         }

         this.trackLinksRight[i].position = this.rightTrack.getPositionOnTrack(this.trackLinksRight[i].progress);
         newAngle = this.rotateTowards((Vector3f)this.rightTrack.points.get(this.rightTrack.getTrackPart(this.trackLinksRight[i].progress)), this.trackLinksRight[i].position);
         part = this.rightTrack.getTrackPart(this.trackLinksRight[i].progress);
         if(funk) {
            funk2 = speed < 0.0F?0.0F:1.0F;
         } else {
            funk2 = speed < 0.0F?-1.0F:0.0F;
         }

         this.trackLinksRight[i].zRot = this.Lerp(this.trackLinksRight[i].zRot, newAngle, (float)part != funkypart + funk2?0.5F:1.0F);
      }

   }

   public float rotateTowards(Vector3f point, Vector3f original) {
      float angle = (float)Math.atan2((double)(point.y - original.y), (double)(point.x - original.x));
      return angle;
   }

   public void dischargeSmoke() {
      VehicleType type = this.getVehicleType();

      for(int i = 0; i < type.smokers.size(); ++i) {
         VehicleType.SmokePoint smoker = (VehicleType.SmokePoint)type.smokers.get(i);
         Vector3f dir = smoker.direction;
         Vector3f pos = smoker.position;
         int time = smoker.detTime;
         dir = super.axes.findLocalVectorGlobally(dir);
         pos = super.axes.findLocalVectorGlobally(pos);
         if(EnumDriveablePart.getPart(smoker.part) == EnumDriveablePart.turret) {
            dir = this.rotate(super.seats[0].looking.findLocalVectorGlobally(smoker.direction));
            pos = this.getPositionOnTurret(smoker.position, false);
         }

         FlansMod.getPacketHandler().sendToAllAround(new PacketParticle("flansmod.smoker", super.posX + (double)(pos.x / 16.0F), super.posY + (double)(pos.y / 16.0F), super.posZ + (double)(pos.z / 16.0F), (double)dir.x, (double)dir.y, (double)dir.z), super.posX, super.posY, super.posZ, 150.0F, super.dimension);
      }

   }

   public float Lerp(float start, float end, float percent) {
      float result = start + percent * (end - start);
      return result;
   }

   public static float Clamp(float val, float min, float max) {
      return Math.max(min, Math.min(max, val));
   }

   public List findEntitiesWithinbounds() {
      VehicleType type = this.getVehicleType();
      AxisAlignedBB initialBox = super.boundingBox.copy();
      List riddenEntities = super.worldObj.getEntitiesWithinAABB(Entity.class, initialBox);
      Vector3f size = new Vector3f(type.harvestBoxSize.x / 8.0F, type.harvestBoxSize.y / 8.0F, type.harvestBoxSize.z / 8.0F);
      Vector3f pos = new Vector3f(type.harvestBoxPos.x / 8.0F, type.harvestBoxPos.y / 8.0F, type.harvestBoxPos.z / 8.0F);
      boolean fancy = false;
      if(!fancy) {
         for(float checkBox = pos.x; checkBox <= pos.x + size.x; ++checkBox) {
            for(float entityhere = pos.y; entityhere <= pos.y + size.y; ++entityhere) {
               for(float i = pos.z; i <= pos.z + size.z; ++i) {
                  Vector3f v = super.axes.findLocalVectorGlobally(new Vector3f(checkBox, entityhere, i));
                  double var10000 = super.posX + (double)v.x;
                  var10000 = super.posY + (double)v.y;
                  var10000 = super.posZ + (double)v.z;
                  AxisAlignedBB checkBox1 = super.boundingBox.copy().offset((double)v.x, (double)v.y, (double)v.z);
                  List entityhere1 = super.worldObj.getEntitiesWithinAABB(Entity.class, checkBox1);

                  for(int i1 = 0; i1 < entityhere1.size(); ++i1) {
                     if(entityhere1.get(i1) instanceof EntityPlayer && !this.isPartOfThis((Entity)entityhere1.get(i1))) {
                        riddenEntities.add(entityhere1.get(i1));
                     }
                  }
               }
            }
         }
      } else {
         AxisAlignedBB var20 = this.getBoundingBox().copy().expand(50.0D, 50.0D, 50.0D);
         List var21 = super.worldObj.getEntitiesWithinAABB(EntityPlayer.class, var20);

         for(int var22 = 0; var22 < var21.size(); ++var22) {
            if(var21.get(var22) instanceof EntityPlayer) {
               riddenEntities.add(var21.get(var22));
            }
         }
      }

      return riddenEntities;
   }

   public Vector3f transformPart(Vector3f current, Vector3f target, Vector3f rate) {
      if(Math.sqrt((double)((current.x - target.x) * (current.x - target.x))) > (double)(rate.x / 2.0F)) {
         if(current.x > target.x) {
            current.x -= rate.x;
         } else if(current.x < target.x) {
            current.x += rate.x;
         }
      } else {
         current.x = target.x;
      }

      if(Math.sqrt((double)((current.y - target.y) * (current.y - target.y))) > (double)(rate.y / 2.0F)) {
         if(current.y > target.y) {
            current.y -= rate.y;
         } else if(current.y < target.y) {
            current.y += rate.y;
         }
      } else {
         current.y = target.y;
      }

      if(Math.sqrt((double)((current.z - target.z) * (current.z - target.z))) > (double)(rate.z / 2.0F)) {
         if(current.z > target.z) {
            current.z -= rate.z;
         } else if(current.z < target.z) {
            current.z += rate.z;
         }
      } else {
         current.z = target.z;
      }

      return current;
   }

   protected void fall(float k) {
      if(k > 10.0F) {
         float damage = (float)(MathHelper.ceiling_float_int(k) * 2);
         boolean no_damage = true;
         if(damage > 0.0F && super.invulnerableUnmountCount == 0 && super.ticksExisted > 20) {
            DriveableType type = this.getDriveableType();
            damage = (float)((int)(damage * type.fallDamageFactor));
            this.attackPart(EnumDriveablePart.core, DamageSource.fall, damage);
            if(type.wheelPositions.length > 0) {
               this.attackPart(type.wheelPositions[0].part, DamageSource.fall, damage / 5.0F);
            }

            no_damage = false;
         }

      }
   }

   private float averageAngles(float a, float b) {
      FlansMod.log("Pre  " + a + " " + b);

      float pi;
      for(pi = 3.1415927F; a > b + pi; a -= 2.0F * pi) {
         ;
      }

      while(a < b - pi) {
         a += 2.0F * pi;
      }

      float avg;
      for(avg = (a + b) / 2.0F; avg > pi; avg -= 2.0F * pi) {
         ;
      }

      while(avg < -pi) {
         avg += 2.0F * pi;
      }

      FlansMod.log("Post " + a + " " + b + " " + avg);
      return avg;
   }

   private Vec3 subtract(Vec3 a, Vec3 b) {
      return Vec3.createVectorHelper(a.xCoord - b.xCoord, a.yCoord - b.yCoord, a.zCoord - b.zCoord);
   }

   private Vec3 crossProduct(Vec3 a, Vec3 b) {
      return Vec3.createVectorHelper(a.yCoord * b.zCoord - a.zCoord * b.yCoord, a.zCoord * b.xCoord - a.xCoord * b.zCoord, a.xCoord * b.yCoord - a.yCoord * b.xCoord);
   }

   public boolean landVehicle() {
      return true;
   }

   public boolean attackEntityFrom(DamageSource damagesource, float i) {
      if(!super.worldObj.isRemote && !super.isDead) {
         VehicleType type = this.getVehicleType();
         if(damagesource.damageType.equals("player") && damagesource.getEntity().onGround && (super.seats[0] == null || super.seats[0].riddenByEntity == null) && !super.locked) {
            ItemStack vehicleStack = new ItemStack(type.item, 1, 0);
            vehicleStack.stackTagCompound = new NBTTagCompound();
            super.driveableData.writeToNBT(vehicleStack.stackTagCompound);
            this.entityDropItem(vehicleStack, 0.5F);
            this.setDead();
         }

         return super.attackEntityFrom(damagesource, i);
      } else {
         return true;
      }
   }

   public VehicleType getVehicleType() {
      return VehicleType.getVehicle(super.driveableType);
   }

   public float getPlayerRoll() {
      return super.axes.getRoll();
   }

   public float getAvgWheelSpeedXYZ() {
      float speed = (float)(super.wheels[0].getSpeedXYZ() + super.wheels[1].getSpeedXYZ() + super.wheels[2].getSpeedXYZ() + super.wheels[3].getSpeedXYZ()) / 4.0F;
      return speed;
   }

   public void Recoil() {}

   protected void dropItemsOnPartDeath(Vector3f midpoint, DriveablePart part) {}

   public String getBombInventoryName() {
      return "Mines";
   }

   public String getMissileInventoryName() {
      return "Shells";
   }

   public boolean hasMouseControlMode() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public EntityLivingBase getCamera() {
      return null;
   }

   public boolean hasBothTracks() {
      boolean tracks = true;
      if(!this.isPartIntact(EnumDriveablePart.leftTrack)) {
         tracks = false;
      }

      if(!this.isPartIntact(EnumDriveablePart.rightTrack)) {
         tracks = false;
      }

      return tracks;
   }

   public void setDead() {
      super.setDead();
      EntityWheel[] var1 = super.wheels;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         EntityWheel wheel = var1[var3];
         if(wheel != null) {
            wheel.setDead();
         }
      }

   }
}
