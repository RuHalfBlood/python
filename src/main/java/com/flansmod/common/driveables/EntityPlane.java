package com.flansmod.common.driveables;

import com.flansmod.client.model.animation.AnimationController;
import com.flansmod.client.model.animation.AnimationPart;
import com.flansmod.common.FlansMod;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.driveables.DriveableData;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EntityWheel;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.EnumPlaneMode;
import com.flansmod.common.driveables.FlightController;
import com.flansmod.common.driveables.PlaneType;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketDriveableControl;
import com.flansmod.common.network.PacketDriveableKey;
import com.flansmod.common.network.PacketParticle;
import com.flansmod.common.network.PacketPlaneAnimator;
import com.flansmod.common.network.PacketPlaneControl;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.tools.ItemTool;
import com.flansmod.common.vector.Matrix4f;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPlane extends EntityDriveable {

   public float flapsYaw;
   public float flapsPitchLeft;
   public float flapsPitchRight;
   public int soundPosition;
   public int bombDelay;
   public int gunDelay;
   public int ticksSinceUsed;
   public boolean varGear;
   public boolean varDoor;
   public boolean varWing;
   public boolean doorsHaveShut;
   public int toggleTimer;
   public EnumPlaneMode mode;
   public Vector3f wingPos;
   public Vector3f wingRot;
   public Vector3f wingWheelPos;
   public Vector3f wingWheelRot;
   public Vector3f coreWheelPos;
   public Vector3f coreWheelRot;
   public Vector3f tailWheelPos;
   public Vector3f tailWheelRot;
   public Vector3f doorPos;
   public Vector3f doorRot;
   public Vector3f prevWingPos;
   public Vector3f prevWingRot;
   public Vector3f prevWingWheelPos;
   public Vector3f prevWingWheelRot;
   public Vector3f prevCoreWheelPos;
   public Vector3f prevCoreWheelRot;
   public Vector3f prevTailWheelPos;
   public Vector3f prevTailWheelRot;
   public Vector3f prevDoorPos;
   public Vector3f prevDoorRot;
   public float xSpeed;
   public float ySpeed;
   public float zSpeed;
   public float rollSpeed;
   public FlightController control;
   public AnimationController anim;
   public boolean initiatedAnim;


   public EntityPlane(World world) {
      super(world);
      this.ticksSinceUsed = 0;
      this.varGear = true;
      this.varDoor = false;
      this.varWing = false;
      this.doorsHaveShut = false;
      this.toggleTimer = 0;
      this.wingPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.wingRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.wingWheelPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.wingWheelRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.coreWheelPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.coreWheelRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.tailWheelPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.tailWheelRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.doorPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.doorRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevWingPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevWingRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevWingWheelPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevWingWheelRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevCoreWheelPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevCoreWheelRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevTailWheelPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevTailWheelRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevDoorPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevDoorRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.xSpeed = 0.0F;
      this.ySpeed = 0.0F;
      this.zSpeed = 0.0F;
      this.rollSpeed = 0.0F;
      this.control = new FlightController();
      this.anim = new AnimationController();
      this.initiatedAnim = false;
   }

   public EntityPlane(World world, double x, double y, double z, PlaneType type, DriveableData data) {
      super(world, type, data);
      this.ticksSinceUsed = 0;
      this.varGear = true;
      this.varDoor = false;
      this.varWing = false;
      this.doorsHaveShut = false;
      this.toggleTimer = 0;
      this.wingPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.wingRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.wingWheelPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.wingWheelRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.coreWheelPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.coreWheelRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.tailWheelPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.tailWheelRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.doorPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.doorRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevWingPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevWingRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevWingWheelPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevWingWheelRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevCoreWheelPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevCoreWheelRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevTailWheelPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevTailWheelRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevDoorPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevDoorRot = new Vector3f(0.0F, 0.0F, 0.0F);
      this.xSpeed = 0.0F;
      this.ySpeed = 0.0F;
      this.zSpeed = 0.0F;
      this.rollSpeed = 0.0F;
      this.control = new FlightController();
      this.anim = new AnimationController();
      this.initiatedAnim = false;
      this.setPosition(x, y, z);
      super.prevPosX = x;
      super.prevPosY = y;
      super.prevPosZ = z;
      this.initType(type, false);
   }

   public EntityPlane(World world, double x, double y, double z, EntityPlayer placer, PlaneType type, DriveableData data) {
      this(world, x, y + 5.625D, z, type, data);
      this.rotateYaw(placer.rotationYaw + 90.0F);
      this.rotatePitch(type.restingPitch);
   }

   public void initType(DriveableType type, boolean clientSide) {
      super.initType(type, clientSide);
      this.mode = ((PlaneType)type).mode == EnumPlaneMode.HELI?EnumPlaneMode.HELI:EnumPlaneMode.PLANE;
      if(((PlaneType)type).mode == EnumPlaneMode.VTOL) {
         this.mode = EnumPlaneMode.HELI;
      }

   }

   protected void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setTag("Pos", this.newDoubleNBTList(new double[]{super.posX, super.posY + 1.0D, super.posZ}));
      tag.setBoolean("VarGear", this.varGear);
      tag.setBoolean("VarDoor", this.varDoor);
      tag.setBoolean("VarWing", this.varWing);
   }

   protected void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.varGear = tag.getBoolean("VarGear");
      this.varDoor = tag.getBoolean("VarDoor");
      this.varWing = tag.getBoolean("VarWing");
   }

   public void onMouseMoved(int deltaX, int deltaY) {
      if(FMLCommonHandler.instance().getSide().isClient()) {
         if(FlansMod.proxy.mouseControlEnabled()) {
            float sensitivity = 0.02F;
            this.flapsPitchLeft -= sensitivity * (float)deltaY;
            this.flapsPitchRight -= sensitivity * (float)deltaY;
            if(this.mode != EnumPlaneMode.SIXDOF) {
               this.flapsPitchLeft -= sensitivity * (float)deltaX;
               this.flapsPitchRight += sensitivity * (float)deltaX;
            } else {
               this.flapsPitchLeft -= sensitivity * (float)deltaX;
               this.flapsPitchRight += sensitivity * (float)deltaX;
            }

         }
      }
   }

   public void setPositionRotationAndMotion(double x, double y, double z, float yaw, float pitch, float roll, double motX, double motY, double motZ, float velYaw, float velPitch, float velRoll, float throt, float steeringYaw) {
      super.setPositionRotationAndMotion(x, y, z, yaw, pitch, roll, motX, motY, motZ, velYaw, velPitch, velRoll, throt, steeringYaw);
      this.flapsYaw = steeringYaw;
   }

   public void setRotorPosition(float current, float previous) {
      super.rotorAngle = current;
      super.prevRotorAngle = previous;
   }

   public void setPropPosition(float current, float previous) {
      super.propAngle = current;
      super.prevPropAngle = previous;
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
            PlaneType type = this.getPlaneType();

            for(int i = 0; i <= type.numPassengers; ++i) {
               if(super.seats[i].interactFirst(entityplayer)) {
                  if(i == 0) {
                     this.bombDelay = type.planeBombDelay;
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
      PlaneType type = this.getPlaneType();
      if(super.worldObj.isRemote && (key == 6 || key == 8 || key == 9)) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableKey(key)));
         return true;
      } else {
         boolean canThrust = super.seats[0] != null && super.seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode || this.getDriveableData().fuelInTank > 0.0F;
         switch(key) {
         case 0:
            if(canThrust || super.throttle < 0.0F) {
               super.throttle += 0.002F;
               if(super.throttle > 1.0F) {
                  super.throttle = 1.0F;
               }

               this.xSpeed += 0.5F;
            }

            return true;
         case 1:
            if(canThrust || super.throttle > 0.0F) {
               super.throttle -= 0.005F;
               if(super.throttle < -1.0F) {
                  super.throttle = -1.0F;
               }

               if(super.throttle < 0.0F && type.maxNegativeThrottle == 0.0F) {
                  super.throttle = 0.0F;
               }

               this.xSpeed -= 0.5F;
            }

            return true;
         case 2:
            if(this.mode != EnumPlaneMode.SIXDOF) {
               --this.flapsYaw;
            }

            --this.zSpeed;
            return true;
         case 3:
            if(this.mode != EnumPlaneMode.SIXDOF) {
               ++this.flapsYaw;
            }

            ++this.zSpeed;
            return true;
         case 4:
            if(this.mode != EnumPlaneMode.SIXDOF) {
               ++this.flapsPitchLeft;
               ++this.flapsPitchRight;
            }

            ++this.ySpeed;
            return true;
         case 5:
            if(this.mode != EnumPlaneMode.SIXDOF) {
               --this.flapsPitchLeft;
               --this.flapsPitchRight;
            }

            --this.ySpeed;
            return true;
         case 6:
            if(super.seats[0].riddenByEntity != null) {
               super.seats[0].riddenByEntity.mountEntity((Entity)null);
            }

            return true;
         case 7:
            if(super.worldObj.isRemote && (type.invInflight || Math.abs(super.throttle) < 0.1F && super.onGround)) {
               FlansMod.proxy.openDriveableMenu((EntityPlayer)super.seats[0].riddenByEntity, super.worldObj, this);
            }

            return true;
         case 8:
         case 9:
            return super.pressKey(key, player);
         case 10:
            FlansMod.proxy.changeControlMode((EntityPlayer)super.seats[0].riddenByEntity);
            super.seats[0].playerLooking = new RotatedAxes(0.0F, 0.0F, 0.0F);
            return true;
         case 11:
            if(this.mode != EnumPlaneMode.SIXDOF) {
               ++this.flapsPitchLeft;
               --this.flapsPitchRight;
            } else {
               this.flapsYaw -= 0.25F;
            }

            return true;
         case 12:
            if(this.mode != EnumPlaneMode.SIXDOF) {
               --this.flapsPitchLeft;
               ++this.flapsPitchRight;
            } else {
               this.flapsYaw += 0.25F;
            }

            return true;
         case 13:
            if(this.toggleTimer <= 0 && super.worldObj.isAirBlock((int)super.posX, (int)(super.posY - 3.0D), (int)super.posZ)) {
               this.varGear = !this.varGear;
               player.addChatMessage(new ChatComponentText("Landing gear " + (this.varGear?"down":"up")));
               this.toggleTimer = 10;
               FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableControl(this)));
            }

            return true;
         case 14:
            if(this.toggleTimer <= 0) {
               this.varDoor = !this.varDoor;
               if(type.hasDoor) {
                  player.addChatMessage(new ChatComponentText("Doors " + (this.varDoor?"open":"closed")));
               }

               this.toggleTimer = 10;
               FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableControl(this)));
            }

            return true;
         case 15:
            if(this.toggleTimer <= 0) {
               if(type.hasWing) {
                  this.varWing = !this.varWing;
                  player.addChatMessage(new ChatComponentText("Switching mode"));
               }

               if(type.mode == EnumPlaneMode.VTOL) {
                  if(this.mode == EnumPlaneMode.HELI) {
                     this.mode = EnumPlaneMode.PLANE;
                  } else {
                     this.mode = EnumPlaneMode.HELI;
                  }

                  player.addChatMessage(new ChatComponentText(this.mode == EnumPlaneMode.HELI?"Entering hover mode":"Entering plane mode"));
               }

               this.anim.changeState(this.varWing?0:1);
               this.toggleTimer = 10;
               FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableControl(this)));
            }

            return true;
         case 16:
            super.axes.setAngles(super.axes.getYaw(), 0.0F, 0.0F);
            return true;
         case 18:
            if(type.hasFlare && super.ticksFlareUsing <= 0 && super.flareDelay <= 0) {
               super.ticksFlareUsing = type.timeFlareUsing * 20;
               super.flareDelay = type.flareDelay;
               if(super.worldObj.isRemote) {
                  FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableKey(key)));
               } else if(!type.flareSound.isEmpty()) {
                  PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, type.flareSound, false);
               }

               return true;
            }
         case 17:
         default:
            return false;
         }
      }
   }

   public void updateKeyHeldState(int key, boolean held) {
      super.updateKeyHeldState(key, held);
      if(!super.worldObj.isRemote) {
         switch(key) {
         case 8:
         case 9:
         }
      }

   }

   public void onUpdate() {
      super.onUpdate();
      this.prevWingPos = this.wingPos;
      this.prevWingRot = this.wingRot;
      this.prevWingWheelPos = this.wingWheelPos;
      this.prevWingWheelRot = this.wingWheelRot;
      this.prevCoreWheelPos = this.coreWheelPos;
      this.prevCoreWheelRot = this.coreWheelRot;
      this.prevTailWheelPos = this.tailWheelPos;
      this.prevTailWheelRot = this.tailWheelRot;
      this.prevDoorPos = this.doorPos;
      this.prevDoorRot = this.doorRot;
      if(this.getPlaneType().valkyrie) {
         if(!this.initiatedAnim) {
            this.anim.initPoses();
            this.anim.initAnim();
            this.initiatedAnim = true;
            this.anim.changeState(this.varWing?0:1);
         }

         if(this.initiatedAnim) {
            int type = this.varWing?0:1;
            this.anim.UpdateAnim(type);
         }
      }

      if(this.initiatedAnim && super.throttle > 0.2F) {
         Vector3f var27 = this.anim.getFullPosition(new Vector3f(151.0F, -25.0F, -24.0F), (AnimationPart)this.anim.parts.get(5));
         var27 = super.axes.findLocalVectorGlobally(new Vector3f(-var27.x, -var27.y, var27.z));
         Vector3f data = this.anim.getFullPosition(new Vector3f(151.0F, -25.0F, 24.0F), (AnimationPart)this.anim.parts.get(8));
         data = super.axes.findLocalVectorGlobally(new Vector3f(-data.x, -data.y, data.z));

         for(int thePlayerIsDrivingThis = 0; thePlayerIsDrivingThis < 4; ++thePlayerIsDrivingThis) {
            if(!Float.isNaN(var27.x)) {
               FlansMod.getPacketHandler().sendToAllAround(new PacketParticle("flansmod.afterburn", super.posX + (double)(data.x / 16.0F), super.posY + (double)(data.y / 16.0F), super.posZ + (double)(data.z / 16.0F), 0.0D, 0.0D, 0.0D), super.posX + (double)(data.x / 16.0F), super.posY + (double)(data.y / 16.0F), super.posZ + (double)(data.z / 16.0F), 150.0F, super.dimension);
            }

            if(!Float.isNaN(var27.x)) {
               FlansMod.getPacketHandler().sendToAllAround(new PacketParticle("flansmod.afterburn", super.posX + (double)(var27.x / 16.0F), super.posY + (double)(var27.y / 16.0F), super.posZ + (double)(var27.z / 16.0F), 0.0D, 0.0D, 0.0D), super.posX + (double)(var27.x / 16.0F), super.posY + (double)(var27.y / 16.0F), super.posZ + (double)(var27.z / 16.0F), 150.0F, super.dimension);
            }
         }
      }

      PlaneType var28 = this.getPlaneType();
      DriveableData var29 = this.getDriveableData();
      if(var28 == null) {
         FlansMod.log("Plane type null. Not ticking plane");
      } else {
         boolean var30 = super.worldObj.isRemote && super.seats[0] != null && super.seats[0].riddenByEntity instanceof EntityPlayer && FlansMod.proxy.isThePlayer((EntityPlayer)super.seats[0].riddenByEntity);
         ++this.ticksSinceUsed;
         if(!super.worldObj.isRemote && super.seats[0].riddenByEntity != null) {
            this.ticksSinceUsed = 0;
         }

         if(!super.worldObj.isRemote && TeamsManager.planeLife > 0 && this.ticksSinceUsed > TeamsManager.planeLife * 20) {
            this.setDead();
         }

         if(super.worldObj.isRemote && (super.varFlare || super.ticksFlareUsing > 0) && super.ticksExisted % 5 == 0) {
            Vector3f canThrust = super.axes.findLocalVectorGlobally(new Vector3f(0.0F, -0.5F, 0.0F));
            FlansMod.proxy.spawnParticle("flansmod.flare", super.posX, super.posY, super.posZ, (double)canThrust.x, (double)canThrust.y, (double)canThrust.z);
         }

         if(super.ticksFlareUsing > 0) {
            --super.ticksFlareUsing;
         }

         if(super.flareDelay > 0) {
            --super.flareDelay;
         }

         if(this.bombDelay > 0) {
            --this.bombDelay;
         }

         if(this.gunDelay > 0) {
            --this.gunDelay;
         }

         if(this.toggleTimer > 0) {
            --this.toggleTimer;
         }

         if(!this.varWing) {
            this.wingPos = this.transformPart(this.wingPos, var28.wingPos1, var28.wingRate);
            this.wingRot = this.transformPart(this.wingRot, var28.wingRot1, var28.wingRotRate);
         } else {
            this.wingPos = this.transformPart(this.wingPos, var28.wingPos2, var28.wingRate);
            this.wingRot = this.transformPart(this.wingRot, var28.wingRot2, var28.wingRotRate);
         }

         if(this.varGear) {
            this.wingWheelPos = this.transformPart(this.wingWheelPos, var28.wingWheelPos1, var28.wingWheelRate);
            this.wingWheelRot = this.transformPart(this.wingWheelRot, var28.wingWheelRot1, var28.wingWheelRotRate);
            this.coreWheelPos = this.transformPart(this.coreWheelPos, var28.bodyWheelPos1, var28.bodyWheelRate);
            this.coreWheelRot = this.transformPart(this.coreWheelRot, var28.bodyWheelRot1, var28.bodyWheelRotRate);
            this.tailWheelPos = this.transformPart(this.tailWheelPos, var28.tailWheelPos1, var28.tailWheelRate);
            this.tailWheelRot = this.transformPart(this.tailWheelRot, var28.tailWheelRot1, var28.tailWheelRotRate);
         } else {
            this.wingWheelPos = this.transformPart(this.wingWheelPos, var28.wingWheelPos2, var28.wingWheelRate);
            this.wingWheelRot = this.transformPart(this.wingWheelRot, var28.wingWheelRot2, var28.wingWheelRotRate);
            this.coreWheelPos = this.transformPart(this.coreWheelPos, var28.bodyWheelPos2, var28.bodyWheelRate);
            this.coreWheelRot = this.transformPart(this.coreWheelRot, var28.bodyWheelRot2, var28.bodyWheelRotRate);
            this.tailWheelPos = this.transformPart(this.tailWheelPos, var28.tailWheelPos2, var28.tailWheelRate);
            this.tailWheelRot = this.transformPart(this.tailWheelRot, var28.tailWheelRot2, var28.tailWheelRotRate);
         }

         if(!this.varDoor) {
            this.doorPos = this.transformPart(this.doorPos, var28.doorPos1, var28.doorRate);
            this.doorRot = this.transformPart(this.doorRot, var28.doorRot1, var28.doorRotRate);
         } else {
            this.doorPos = this.transformPart(this.doorPos, var28.doorPos2, var28.doorRate);
            this.doorRot = this.transformPart(this.doorRot, var28.doorRot2, var28.doorRotRate);
         }

         if(!super.worldObj.isAirBlock((int)super.posX, (int)(super.posY - 10.0D), (int)super.posZ) && (double)super.throttle <= 0.4D) {
            if(!this.varGear && (EntityPlayer)super.seats[0].riddenByEntity != null) {
               ((EntityPlayer)super.seats[0].riddenByEntity).addChatMessage(new ChatComponentText("Deploying landing gear"));
            }

            this.varGear = true;
            if(var28.foldWingForLand) {
               if(this.varWing && (EntityPlayer)super.seats[0].riddenByEntity != null) {
                  ((EntityPlayer)super.seats[0].riddenByEntity).addChatMessage(new ChatComponentText("Extending wings"));
               }

               this.varWing = false;
            }
         }

         if(!super.worldObj.isAirBlock((int)super.posX, (int)(super.posY - 3.0D), (int)super.posZ) && (double)super.throttle <= 0.05D) {
            if(!this.doorsHaveShut) {
               this.varDoor = true;
            }

            this.doorsHaveShut = true;
         } else if(!var28.flyWithOpenDoor) {
            this.varDoor = false;
            this.doorsHaveShut = false;
         }

         if(super.locked) {
            this.varDoor = false;
            this.doorsHaveShut = false;
         }

         if(!this.isPartIntact(EnumDriveablePart.tail) && var28.spinWithoutTail) {
            this.flapsYaw = 15.0F;
         }

         this.flapsYaw *= 0.9F;
         this.flapsPitchLeft *= 0.9F;
         this.flapsPitchRight *= 0.9F;
         if(this.flapsYaw > 20.0F) {
            this.flapsYaw = 20.0F;
         }

         if(this.flapsYaw < -20.0F) {
            this.flapsYaw = -20.0F;
         }

         if(this.flapsPitchRight > 20.0F) {
            this.flapsPitchRight = 20.0F;
         }

         if(this.flapsPitchRight < -20.0F) {
            this.flapsPitchRight = -20.0F;
         }

         if(this.flapsPitchLeft > 20.0F) {
            this.flapsPitchLeft = 20.0F;
         }

         if(this.flapsPitchLeft < -20.0F) {
            this.flapsPitchLeft = -20.0F;
         }

         double motion;
         if(super.worldObj.isRemote && !var30 && super.serverPositionTransitionTicker > 0) {
            double var31 = super.posX + (super.field_70118_ct - super.posX) / (double)super.serverPositionTransitionTicker;
            motion = super.posY + (super.field_70117_cu - super.posY) / (double)super.serverPositionTransitionTicker;
            double updateSpeed = super.posZ + (super.field_70116_cv - super.posZ) / (double)super.serverPositionTransitionTicker;
            double dYaw = MathHelper.wrapAngleTo180_double(super.serverYaw - (double)super.axes.getYaw());
            double dPitch = MathHelper.wrapAngleTo180_double(super.serverPitch - (double)super.axes.getPitch());
            double wPos = MathHelper.wrapAngleTo180_double(super.serverRoll - (double)super.axes.getRoll());
            super.rotationYaw = (float)((double)super.axes.getYaw() + dYaw / (double)super.serverPositionTransitionTicker);
            super.rotationPitch = (float)((double)super.axes.getPitch() + dPitch / (double)super.serverPositionTransitionTicker);
            float currentWheelPos = (float)((double)super.axes.getRoll() + wPos / (double)super.serverPositionTransitionTicker);
            --super.serverPositionTransitionTicker;
            this.setPosition(var31, motion, updateSpeed);
            this.setRotation(super.rotationYaw, super.rotationPitch, currentWheelPos);
         }

         boolean var32 = super.seats[0] != null && super.seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode || var29.fuelInTank > 0.0F;
         float throttlePull = 0.99F;
         if(super.seats[0] != null && super.seats[0].riddenByEntity != null && this.mode == EnumPlaneMode.HELI && var32) {
            super.throttle = (super.throttle - 0.5F) * throttlePull + 0.5F;
         }

         this.control.fly(this);
         motion = Math.sqrt(super.motionX * super.motionX + super.motionY * super.motionY + super.motionZ * super.motionZ);
         if(motion > 10.0D) {
            super.motionX *= 10.0D / motion;
            super.motionY *= 10.0D / motion;
            super.motionZ *= 10.0D / motion;
         }

         EntityWheel[] var33 = super.wheels;
         int amountToMoveCar = var33.length;

         EntityWheel seat;
         int var38;
         for(var38 = 0; var38 < amountToMoveCar; ++var38) {
            seat = var33[var38];
            if(seat != null && super.worldObj != null && var28.floatOnWater && super.worldObj.isAnyLiquid(seat.boundingBox)) {
               super.motionY += (double)var28.buoyancy;
            }
         }

         var33 = super.wheels;
         amountToMoveCar = var33.length;

         for(var38 = 0; var38 < amountToMoveCar; ++var38) {
            seat = var33[var38];
            if(seat != null) {
               seat.prevPosY = seat.posY;
               seat.moveEntity(super.motionX, super.onDeck?0.0D:super.motionY, super.motionZ);
            }
         }

         this.correctWheelPos();

         for(int var34 = 0; var34 < 2; ++var34) {
            Vector3f var35 = new Vector3f(super.motionX / 2.0D, super.onDeck?0.0D:super.motionY / 2.0D, super.motionZ / 2.0D);
            EntityWheel[] var39 = super.wheels;
            int var40 = var39.length;

            for(int var42 = 0; var42 < var40; ++var42) {
               EntityWheel wheel = var39[var42];
               if(wheel != null) {
                  super.onGround = true;
                  wheel.onGround = true;
                  wheel.rotationYaw = super.axes.getYaw();
                  Vector3f var43 = this.getPlaneType().wheelPositions[wheel.ID].position;
                  if(var28.valkyrie && this.varWing) {
                     var43 = new Vector3f(var43.x, var43.y + 5.625F, var43.z);
                  }

                  Vector3f targetWheelPos = super.axes.findLocalVectorGlobally(var43);
                  Vector3f var44 = new Vector3f(wheel.posX - super.posX, wheel.posY - super.posY, wheel.posZ - super.posZ);
                  float targetWheelLength = targetWheelPos.length();
                  float currentWheelLength = var44.length();
                  float dLength = targetWheelLength - currentWheelLength;
                  float dAngle = Vector3f.angle(targetWheelPos, var44);
                  float newLength = currentWheelLength + dLength * var28.wheelSpringStrength;
                  Vector3f rotateAround = Vector3f.cross(targetWheelPos, var44, (Vector3f)null);
                  Matrix4f mat = new Matrix4f();
                  mat.m00 = var44.x;
                  mat.m10 = var44.y;
                  mat.m20 = var44.z;
                  mat.rotate(dAngle * var28.wheelSpringStrength, rotateAround);
                  if(super.ticksExisted > 5 && (!var28.valkyrie || this.anim.timeSinceSwitch >= 10)) {
                     super.axes.rotateGlobal(-dAngle * var28.wheelSpringStrength, rotateAround);
                  }

                  Vector3f newWheelPos = new Vector3f(mat.m00, mat.m10, mat.m20);
                  newWheelPos.normalise().scale(newLength);
                  float wheelProportion = 0.75F;
                  Vector3f amountToMoveWheel = new Vector3f();
                  amountToMoveWheel.x = (newWheelPos.x - var44.x) * (1.0F - wheelProportion);
                  amountToMoveWheel.y = (newWheelPos.y - var44.y) * (1.0F - wheelProportion);
                  amountToMoveWheel.z = (newWheelPos.z - var44.z) * (1.0F - wheelProportion);
                  var35.x -= (newWheelPos.x - var44.x) * (1.0F - wheelProportion);
                  var35.y -= (newWheelPos.y - var44.y) * (1.0F - wheelProportion);
                  var35.z -= (newWheelPos.z - var44.z) * (1.0F - wheelProportion);
                  var35.y = (float)((double)var35.y + (wheel.posY - wheel.prevPosY - (super.onDeck?0.0D:super.motionY)) * 0.5D / (double)super.wheels.length);
                  wheel.moveEntity((double)amountToMoveWheel.x, (double)amountToMoveWheel.y, (double)amountToMoveWheel.z);
               }
            }

            this.moveEntity((double)var35.x, (double)var35.y, (double)var35.z);
         }

         if(super.ridingEntity != null && super.ridingEntity.getClass().toString().indexOf("mcheli.aircraft.MCH_EntitySeat") > 0) {
            super.axes.setAngles(super.ridingEntity.rotationYaw + 90.0F, 0.0F, 0.0F);
         }

         this.checkForCollisions();
         if(super.throttle > 0.01F && super.throttle < 0.2F && this.soundPosition == 0) {
            PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, var28.startSound, false);
            this.soundPosition = var28.startSoundLength;
         }

         if(super.throttle > 0.2F && this.soundPosition == 0) {
            PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, var28.engineSound, false);
            this.soundPosition = var28.engineSoundLength;
         }

         if(this.soundPosition > 0) {
            --this.soundPosition;
         }

         EntitySeat[] var36 = super.seats;
         amountToMoveCar = var36.length;

         for(var38 = 0; var38 < amountToMoveCar; ++var38) {
            EntitySeat var41 = var36[var38];
            if(var41 != null) {
               var41.updatePosition();
            }
         }

         if(var30) {
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketPlaneControl(this)));
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketPlaneAnimator(this)));
            super.field_70118_ct = super.posX;
            super.field_70117_cu = super.posY;
            super.field_70116_cv = super.posZ;
            super.serverYaw = (double)super.axes.getYaw();
         }

         float var37 = 0.01F;
         if(!super.worldObj.isRemote) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketPlaneControl(this), super.posX, super.posY, super.posZ, 400.0F, super.dimension);
            FlansMod.getPacketHandler().sendToAllAround(new PacketPlaneAnimator(this), super.posX, super.posY, super.posZ, 400.0F, super.dimension);
         }

      }
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

   public boolean gearDown() {
      return this.varGear;
   }

   public boolean attackEntityFrom(DamageSource damagesource, float i, boolean doDamage) {
      if(!super.worldObj.isRemote && !super.isDead) {
         PlaneType type = PlaneType.getPlane(super.driveableType);
         if(damagesource.damageType.equals("player") && damagesource.getEntity().onGround && (super.seats[0] == null || super.seats[0].riddenByEntity == null) && !super.locked) {
            ItemStack planeStack = new ItemStack(type.item, 1, super.driveableData.paintjobID);
            planeStack.stackTagCompound = new NBTTagCompound();
            super.driveableData.writeToNBT(planeStack.stackTagCompound);
            this.entityDropItem(planeStack, 0.5F);
            this.setDead();
         }

         return super.attackEntityFrom(damagesource, i);
      } else {
         return true;
      }
   }

   public boolean canHitPart(EnumDriveablePart part) {
      return this.varGear || part != EnumDriveablePart.coreWheel && part != EnumDriveablePart.leftWingWheel && part != EnumDriveablePart.rightWingWheel && part != EnumDriveablePart.tailWheel;
   }

   public boolean attackEntityFrom(DamageSource damagesource, float i) {
      return this.attackEntityFrom(damagesource, i, true);
   }

   public PlaneType getPlaneType() {
      return PlaneType.getPlane(super.driveableType);
   }

   protected void dropItemsOnPartDeath(Vector3f midpoint, DriveablePart part) {}

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

   public String getBombInventoryName() {
      return "Bombs";
   }

   public String getMissileInventoryName() {
      return "Missiles";
   }

   public boolean hasMouseControlMode() {
      return true;
   }
}
