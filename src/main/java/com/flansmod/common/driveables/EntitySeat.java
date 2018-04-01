package com.flansmod.common.driveables;

import com.flansmod.api.IControllable;
import com.flansmod.client.FlansModClient;
import com.flansmod.common.FlansMod;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.Seat;
import com.flansmod.common.guns.EnumFireMode;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemShootable;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketDriveableKey;
import com.flansmod.common.network.PacketDriveableKeyHeld;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.network.PacketSeatCheck;
import com.flansmod.common.network.PacketSeatUpdates;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.tools.ItemTool;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntitySeat extends Entity implements IControllable, IEntityAdditionalSpawnData {

   @SideOnly(Side.CLIENT)
   public boolean foundDriveable;
   private int driveableID;
   private int seatID;
   public EntityDriveable driveable;
   @SideOnly(Side.CLIENT)
   public float playerRoll;
   @SideOnly(Side.CLIENT)
   public float prevPlayerRoll;
   public Seat seatInfo;
   public boolean driver;
   public RotatedAxes looking;
   public RotatedAxes prevLooking;
   public RotatedAxes playerLooking;
   public RotatedAxes prevPlayerLooking;
   public int gunDelay;
   public float minigunSpeed;
   public float minigunAngle;
   public int soundDelay;
   public int yawSoundDelay;
   public int pitchSoundDelay;
   public boolean playYawSound;
   public boolean playPitchSound;
   private double playerPosX;
   private double playerPosY;
   private double playerPosZ;
   private float playerYaw;
   private float playerPitch;
   private double prevPlayerPosX;
   private double prevPlayerPosY;
   private double prevPlayerPosZ;
   private float prevPlayerYaw;
   private float prevPlayerPitch;
   private boolean shooting;
   public Entity lastRiddenByEntity;
   public float targetYaw;
   public float targetPitch;
   public boolean artillery;
   public int timeLimitDriveableNull;


   public EntitySeat(World world) {
      super(world);
      this.yawSoundDelay = 0;
      this.pitchSoundDelay = 0;
      this.playYawSound = false;
      this.playPitchSound = false;
      this.targetYaw = 0.0F;
      this.targetPitch = 0.0F;
      this.artillery = false;
      this.timeLimitDriveableNull = 0;
      this.setSize(1.0F, 1.0F);
      this.prevLooking = new RotatedAxes();
      this.looking = new RotatedAxes();
      this.playerLooking = new RotatedAxes();
      this.prevPlayerLooking = new RotatedAxes();
      this.lastRiddenByEntity = null;
   }

   public EntitySeat(World world, EntityDriveable d, int id) {
      this(world);
      this.driveable = d;
      this.driveableID = d.getEntityId();
      this.seatInfo = this.driveable.getDriveableType().seats[id];
      this.driver = id == 0;
      this.setPosition(d.posX, d.posY, d.posZ);
      this.playerPosX = this.prevPlayerPosX = super.posX;
      this.playerPosY = this.prevPlayerPosY = super.posY;
      this.playerPosZ = this.prevPlayerPosZ = super.posZ;
      this.looking.setAngles((this.seatInfo.minYaw + this.seatInfo.maxYaw) / 2.0F, 0.0F, 0.0F);
      this.playerLooking.setAngles((this.seatInfo.minYaw + this.seatInfo.maxYaw) / 2.0F, 0.0F, 0.0F);
   }

   public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int i) {}

   public void onUpdate() {
      super.onUpdate();
      if(this.driver && super.riddenByEntity == null) {
         this.prevLooking = this.looking.clone();
         this.prevPlayerLooking = this.playerLooking.clone();
      }

      if(super.worldObj.isRemote && !this.foundDriveable) {
         this.driveable = (EntityDriveable)super.worldObj.getEntityByID(this.driveableID);
         if(this.driveable == null) {
            return;
         }

         this.foundDriveable = true;
         this.driveable.seats[this.seatID] = this;
         this.seatInfo = this.driveable.getDriveableType().seats[this.seatID];
         this.looking.setAngles((this.seatInfo.minYaw + this.seatInfo.maxYaw) / 2.0F, 0.0F, 0.0F);
         this.playerLooking.setAngles((this.seatInfo.minYaw + this.seatInfo.maxYaw) / 2.0F, 0.0F, 0.0F);
         this.prevLooking = this.looking.clone();
         this.playerPosX = this.prevPlayerPosX = super.posX = this.driveable.posX;
         this.playerPosY = this.prevPlayerPosY = super.posY = this.driveable.posY;
         this.playerPosZ = this.prevPlayerPosZ = super.posZ = this.driveable.posZ;
         this.setPosition(super.posX, super.posY, super.posZ);
      }

      if(this.driveable != null) {
         EntityDriveable entD = (EntityDriveable)super.worldObj.getEntityByID(this.driveableID);
         if(entD == null) {
            ++this.timeLimitDriveableNull;
         } else {
            this.timeLimitDriveableNull = 0;
         }

         if(this.timeLimitDriveableNull > 1200) {
            this.setDead();
         }

         if(this.gunDelay > 0) {
            --this.gunDelay;
         }

         if(this.soundDelay > 0) {
            --this.soundDelay;
         }

         if(this.yawSoundDelay > 0) {
            --this.yawSoundDelay;
         }

         if(this.pitchSoundDelay > 0) {
            --this.pitchSoundDelay;
         }

         if(this.playYawSound && this.yawSoundDelay == 0 && this.seatInfo.traverseSounds && !this.driveable.disabled) {
            PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.seatInfo.yawSound, false);
            this.yawSoundDelay = this.seatInfo.yawSoundLength;
         }

         if(this.playPitchSound && this.pitchSoundDelay == 0 && this.seatInfo.traverseSounds && !this.driveable.disabled) {
            PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.seatInfo.pitchSound, false);
            this.pitchSoundDelay = this.seatInfo.pitchSoundLength;
         }

         if(!(super.riddenByEntity instanceof EntityPlayer) || !FlansMod.proxy.isThePlayer((EntityPlayer)super.riddenByEntity)) {
            this.playYawSound = false;
            this.playPitchSound = false;
            this.yawSoundDelay = 0;
            this.pitchSoundDelay = 0;
         }

         if(super.worldObj.isRemote) {
            if(this.driver && super.riddenByEntity instanceof EntityPlayer && FlansMod.proxy.isThePlayer((EntityPlayer)super.riddenByEntity) && FlansModClient.controlModeMouse && this.driveable.hasMouseControlMode()) {
               this.looking = new RotatedAxes();
               this.playerLooking = new RotatedAxes();
            }

            Vector3f xAxis = this.driveable.axes.findLocalAxesGlobally(this.looking).getXAxis();
            Vector3f yAxis = this.driveable.axes.findLocalAxesGlobally(this.looking).getYAxis();
            Vector3f zAxis = this.driveable.axes.findLocalAxesGlobally(this.looking).getZAxis();
            Vector3f yOffset = this.driveable.axes.findLocalVectorGlobally(new Vector3f(0.0F, super.riddenByEntity == null?0.0F:(float)super.riddenByEntity.getYOffset(), 0.0F));

            for(int i = 0; i < 10; ++i) {
               ;
            }

            if(this.lastRiddenByEntity instanceof EntityPlayer && super.riddenByEntity == null && FlansMod.proxy.isThePlayer((EntityPlayer)this.lastRiddenByEntity)) {
               FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketSeatCheck(this)));
            }
         }

         if(super.riddenByEntity instanceof EntityPlayer && this.shooting) {
            this.pressKey(9, (EntityPlayer)super.riddenByEntity);
         }

         this.minigunSpeed *= 0.95F;
         this.minigunAngle += this.minigunSpeed;
         this.lastRiddenByEntity = super.riddenByEntity;
      }
   }

   public void updatePosition() {
      if(!super.worldObj.isRemote || this.foundDriveable) {
         this.prevPlayerPosX = this.playerPosX;
         this.prevPlayerPosY = this.playerPosY;
         this.prevPlayerPosZ = this.playerPosZ;
         this.prevPlayerYaw = this.playerYaw;
         this.prevPlayerPitch = this.playerPitch;
         Vector3f localPosition = new Vector3f((float)this.seatInfo.x / 16.0F, (float)this.seatInfo.y / 16.0F, (float)this.seatInfo.z / 16.0F);
         if(this.driveable != null && this.driveable.seats != null && this.driveable.seats[0] != null && this.driveable.seats[0].looking != null) {
            RotatedAxes relativePosition = new RotatedAxes(this.driveable.seats[0].looking.getYaw(), this.driveable.seats[0].seatInfo.part == EnumDriveablePart.barrel?this.driveable.seats[0].looking.getPitch():0.0F, 0.0F);
            Vector3f type = relativePosition.findLocalVectorGlobally(this.seatInfo.rotatedOffset);
            Vector3f.add(localPosition, new Vector3f(type.x, this.driveable.seats[0].seatInfo.part == EnumDriveablePart.barrel?type.y:0.0F, type.z), localPosition);
         }

         Vector3f relativePosition1 = this.driveable.axes.findLocalVectorGlobally(localPosition);
         this.setPosition(this.driveable.posX + (double)relativePosition1.x, this.driveable.posY + (double)relativePosition1.y, this.driveable.posZ + (double)relativePosition1.z);
         if(super.riddenByEntity != null) {
            DriveableType type1 = this.driveable.getDriveableType();
            Vec3 yOffset = this.driveable.rotate(0.0D, super.riddenByEntity.getYOffset(), 0.0D).toVec3();
            this.playerPosX = super.posX + yOffset.xCoord;
            this.playerPosY = super.posY + yOffset.yCoord;
            this.playerPosZ = super.posZ + yOffset.zCoord;
            super.riddenByEntity.lastTickPosX = super.riddenByEntity.prevPosX = this.prevPlayerPosX;
            super.riddenByEntity.lastTickPosY = super.riddenByEntity.prevPosY = this.prevPlayerPosY;
            super.riddenByEntity.lastTickPosZ = super.riddenByEntity.prevPosZ = this.prevPlayerPosZ;
            super.riddenByEntity.setPosition(this.playerPosX, this.playerPosY, this.playerPosZ);
            RotatedAxes globalLookAxes = this.driveable.axes.findLocalAxesGlobally(this.playerLooking);
            this.playerYaw = -90.0F + globalLookAxes.getYaw();
            this.playerPitch = globalLookAxes.getPitch();
            double dYaw = (double)(this.playerYaw - this.prevPlayerYaw);
            if(dYaw > 180.0D) {
               this.prevPlayerYaw += 360.0F;
            }

            if(dYaw < -180.0D) {
               this.prevPlayerYaw -= 360.0F;
            }

            if(super.riddenByEntity instanceof EntityPlayer) {
               super.riddenByEntity.prevRotationYaw = this.prevPlayerYaw;
               super.riddenByEntity.prevRotationPitch = this.prevPlayerPitch;
               super.riddenByEntity.rotationYaw = this.playerYaw;
               super.riddenByEntity.rotationPitch = this.playerPitch;
            }

            if(super.worldObj.isRemote) {
               this.prevPlayerRoll = this.playerRoll;
               this.playerRoll = -globalLookAxes.getRoll();
            }
         }

      }
   }

   public void updateRiderPosition() {
      if(super.riddenByEntity instanceof EntityPlayer) {
         super.riddenByEntity.rotationYaw = this.playerYaw;
         super.riddenByEntity.rotationPitch = this.playerPitch;
         super.riddenByEntity.prevRotationYaw = this.prevPlayerYaw;
         super.riddenByEntity.prevRotationPitch = this.prevPlayerPitch;
      }

      super.riddenByEntity.lastTickPosX = super.riddenByEntity.prevPosX = this.prevPlayerPosX;
      super.riddenByEntity.lastTickPosY = super.riddenByEntity.prevPosY = this.prevPlayerPosY;
      super.riddenByEntity.lastTickPosZ = super.riddenByEntity.prevPosZ = this.prevPlayerPosZ;
   }

   @SideOnly(Side.CLIENT)
   public EntityLivingBase getCamera() {
      return this.driveable.getCamera();
   }

   public boolean canBeCollidedWith() {
      return !super.isDead;
   }

   protected void entityInit() {}

   public float getShadowSize() {
      return 4.0F;
   }

   protected void readEntityFromNBT(NBTTagCompound tags) {}

   protected void writeEntityToNBT(NBTTagCompound tags) {}

   public boolean writeToNBTOptional(NBTTagCompound tags) {
      return false;
   }

   public boolean writeMountToNBT(NBTTagCompound tags) {
      return false;
   }

   public void onMouseMoved(int deltaX, int deltaY) {
      if(this.foundDriveable) {
         this.prevLooking = this.looking.clone();
         this.prevPlayerLooking = this.playerLooking.clone();
         if(this.driver) {
            this.driveable.onMouseMoved(deltaX, deltaY);
         }

         if(!this.driver || !FlansModClient.controlModeMouse || !this.driveable.hasMouseControlMode()) {
            float lookSpeed = 4.0F;
            float newPlayerPitch = this.playerLooking.getPitch() - (float)deltaY / lookSpeed * FlansMod.proxy.getMouseSensitivity();
            if(newPlayerPitch > -this.seatInfo.minPitch) {
               newPlayerPitch = -this.seatInfo.minPitch;
            }

            if(newPlayerPitch < -this.seatInfo.maxPitch) {
               newPlayerPitch = -this.seatInfo.maxPitch;
            }

            float newPlayerYaw = this.playerLooking.getYaw() + (float)deltaX / lookSpeed * FlansMod.proxy.getMouseSensitivity();
            float otherNewPlayerYaw = newPlayerYaw - 360.0F;
            if(newPlayerYaw < 0.0F) {
               otherNewPlayerYaw = newPlayerYaw + 360.0F;
            }

            float targetX;
            float yawToMove;
            if((newPlayerYaw < this.seatInfo.minYaw || newPlayerYaw > this.seatInfo.maxYaw) && (otherNewPlayerYaw < this.seatInfo.minYaw || otherNewPlayerYaw > this.seatInfo.maxYaw)) {
               targetX = Math.min(Math.abs(newPlayerYaw - this.seatInfo.minYaw), Math.abs(newPlayerYaw - this.seatInfo.maxYaw));
               yawToMove = Math.min(Math.abs(otherNewPlayerYaw - this.seatInfo.minYaw), Math.abs(otherNewPlayerYaw - this.seatInfo.maxYaw));
               if(targetX <= yawToMove) {
                  if(newPlayerYaw > this.seatInfo.maxYaw) {
                     newPlayerYaw = this.seatInfo.maxYaw;
                  }

                  if(newPlayerYaw < this.seatInfo.minYaw) {
                     newPlayerYaw = this.seatInfo.minYaw;
                  }
               } else {
                  if(otherNewPlayerYaw > this.seatInfo.maxYaw) {
                     otherNewPlayerYaw = this.seatInfo.maxYaw;
                  }

                  if(otherNewPlayerYaw < this.seatInfo.minYaw) {
                     otherNewPlayerYaw = this.seatInfo.minYaw;
                  }

                  if(newPlayerYaw < 0.0F) {
                     newPlayerYaw = otherNewPlayerYaw - 360.0F;
                  } else {
                     newPlayerYaw = otherNewPlayerYaw + 360.0F;
                  }
               }
            }

            this.playerLooking.setAngles(newPlayerYaw, newPlayerPitch, 0.0F);
            if(this.driveable.disabled) {
               return;
            }

            if(!FlansModClient.controlModeMouse && this.driver && !(this.driveable instanceof EntityPlane)) {
               targetX = this.targetYaw;
            } else {
               targetX = this.playerLooking.getYaw();
            }

            for(yawToMove = targetX - this.looking.getYaw(); yawToMove > 180.0F; yawToMove -= 360.0F) {
               ;
            }

            while(yawToMove <= -180.0F) {
               yawToMove += 360.0F;
            }

            float signDeltaX = 0.0F;
            if(yawToMove > this.seatInfo.aimingSpeed.x / 2.0F && !this.seatInfo.legacyAiming) {
               signDeltaX = 1.0F;
            } else if(yawToMove < -(this.seatInfo.aimingSpeed.x / 2.0F) && !this.seatInfo.legacyAiming) {
               signDeltaX = -1.0F;
            } else {
               signDeltaX = 0.0F;
            }

            float newYaw = 0.0F;
            if(!this.seatInfo.legacyAiming && (signDeltaX != 0.0F || deltaX != 0)) {
               newYaw = this.looking.getYaw() + signDeltaX * this.seatInfo.aimingSpeed.x;
            } else {
               newYaw = targetX;
            }

            float otherNewYaw = newYaw - 360.0F;
            if(newYaw < 0.0F) {
               otherNewYaw = newYaw + 360.0F;
            }

            float targetY;
            float pitchToMove;
            if((newYaw < this.seatInfo.minYaw || newYaw > this.seatInfo.maxYaw) && (otherNewYaw < this.seatInfo.minYaw || otherNewYaw > this.seatInfo.maxYaw)) {
               targetY = Math.min(Math.abs(newYaw - this.seatInfo.minYaw), Math.abs(newYaw - this.seatInfo.maxYaw));
               pitchToMove = Math.min(Math.abs(otherNewYaw - this.seatInfo.minYaw), Math.abs(otherNewYaw - this.seatInfo.maxYaw));
               if(targetY <= pitchToMove) {
                  if(newYaw > this.seatInfo.maxYaw) {
                     newYaw = this.seatInfo.maxYaw;
                  }

                  if(newYaw < this.seatInfo.minYaw) {
                     newYaw = this.seatInfo.minYaw;
                  }
               } else {
                  if(otherNewYaw > this.seatInfo.maxYaw) {
                     otherNewYaw = this.seatInfo.maxYaw;
                  }

                  if(otherNewYaw < this.seatInfo.minYaw) {
                     otherNewYaw = this.seatInfo.minYaw;
                  }

                  if(newYaw < 0.0F) {
                     newYaw = otherNewYaw - 360.0F;
                  } else {
                     newYaw = otherNewYaw + 360.0F;
                  }
               }
            }

            targetY = this.playerLooking.getPitch();
            if(!FlansModClient.controlModeMouse && this.driver && !(this.driveable instanceof EntityPlane)) {
               targetY = this.targetPitch;
            }

            for(pitchToMove = targetY - this.looking.getPitch(); pitchToMove > 180.0F; pitchToMove -= 360.0F) {
               ;
            }

            while(pitchToMove <= -180.0F) {
               pitchToMove += 360.0F;
            }

            float signDeltaY = 0.0F;
            if(pitchToMove > this.seatInfo.aimingSpeed.y / 2.0F && !this.seatInfo.legacyAiming) {
               signDeltaY = 1.0F;
            } else if(pitchToMove < -(this.seatInfo.aimingSpeed.y / 2.0F) && !this.seatInfo.legacyAiming) {
               signDeltaY = -1.0F;
            } else {
               signDeltaY = 0.0F;
            }

            float newPitch = 0.0F;
            float minYawToMove = 0.0F;
            float currentYawToMove = 0.0F;
            if(this.seatInfo.latePitch) {
               minYawToMove = (float)Math.sqrt((double)(pitchToMove / this.seatInfo.aimingSpeed.y * (pitchToMove / this.seatInfo.aimingSpeed.y))) * this.seatInfo.aimingSpeed.x;
            } else {
               minYawToMove = 360.0F;
            }

            currentYawToMove = (float)Math.sqrt((double)(yawToMove * yawToMove));
            if(!this.seatInfo.legacyAiming && (signDeltaY != 0.0F || deltaY != 0)) {
               if((this.seatInfo.yawBeforePitch || currentYawToMove >= minYawToMove) && FlansModClient.controlModeMouse) {
                  if(this.seatInfo.yawBeforePitch && signDeltaX == 0.0F) {
                     newPitch = this.looking.getPitch() + signDeltaY * this.seatInfo.aimingSpeed.y;
                  } else if(this.seatInfo.yawBeforePitch && signDeltaX != 0.0F) {
                     newPitch = this.looking.getPitch();
                  } else {
                     newPitch = this.looking.getPitch();
                  }
               } else {
                  newPitch = this.looking.getPitch() + signDeltaY * this.seatInfo.aimingSpeed.y;
               }
            } else {
               newPitch = targetY;
            }

            if(newPitch > -this.seatInfo.minPitch) {
               newPitch = -this.seatInfo.minPitch;
            }

            if(newPitch < -this.seatInfo.maxPitch) {
               newPitch = -this.seatInfo.maxPitch;
            }

            if(this.driveable instanceof EntityVehicle && ((EntityVehicle)this.driveable).target == null || !(this.driveable instanceof EntityVehicle)) {
               this.looking.setAngles(newYaw, newPitch, 0.0F);
            }

            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketSeatUpdates(this)));
            if(signDeltaX != 0.0F && this.seatInfo.traverseSounds) {
               this.playYawSound = true;
            } else {
               this.playYawSound = false;
            }

            if(signDeltaY != 0.0F && !this.seatInfo.yawBeforePitch && currentYawToMove < minYawToMove) {
               this.playPitchSound = true;
            } else if(signDeltaY != 0.0F && this.seatInfo.yawBeforePitch && signDeltaX == 0.0F) {
               this.playPitchSound = true;
            } else {
               this.playPitchSound = false;
            }

            if(this.driveable instanceof EntityVehicle && ((EntityVehicle)this.driveable).target != null) {
               this.playPitchSound = false;
               this.playYawSound = false;
            }
         }

      }
   }

   public void updateKeyHeldState(int key, boolean held) {
      if(super.worldObj.isRemote && this.foundDriveable) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableKeyHeld(key, held)));
      }

      if(this.driver) {
         this.driveable.updateKeyHeldState(key, held);
      } else if(key == 9) {
         this.shooting = held;
      }

   }

   public boolean pressKey(int key, EntityPlayer player) {
      if(this.driver && (!super.worldObj.isRemote || this.foundDriveable)) {
         return this.driveable.pressKey(key, player);
      } else if(super.worldObj.isRemote) {
         if(this.foundDriveable) {
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableKey(key)));
            if(key == 9) {
               this.minigunSpeed += 0.1F;
            }
         }

         return false;
      } else {
         if(key == 6 && super.riddenByEntity != null) {
            super.riddenByEntity.mountEntity((Entity)null);
         }

         if(key == 9) {
            GunType gun = this.seatInfo.gunType;
            this.minigunSpeed += 0.1F;
            if((gun != null && gun.mode != EnumFireMode.MINIGUN || this.minigunSpeed > 2.0F) && this.gunDelay <= 0 && TeamsManager.bulletsEnabled) {
               ItemStack bulletItemStack = this.driveable.getDriveableData().ammo[this.seatInfo.gunnerID];
               if(gun != null && bulletItemStack != null && bulletItemStack.getItem() instanceof ItemShootable) {
                  ShootableType bullet = ((ItemShootable)bulletItemStack.getItem()).type;
                  if(gun.isAmmo(bullet)) {
                     Vector3f gunOrigin = Vector3f.add(this.driveable.axes.findLocalVectorGlobally(this.seatInfo.gunOrigin), new Vector3f(this.driveable.posX, this.driveable.posY, this.driveable.posZ), (Vector3f)null);
                     RotatedAxes globalLookAxes = this.driveable.axes.findLocalAxesGlobally(this.looking);
                     Vector3f shootVec = this.driveable.axes.findLocalVectorGlobally(this.looking.getXAxis());
                     Vector3f yOffset = this.driveable.axes.findLocalVectorGlobally(new Vector3f(0.0F, (float)player.getMountedYOffset(), 0.0F));
                     super.worldObj.spawnEntityInWorld(((ItemShootable)bulletItemStack.getItem()).getEntity(super.worldObj, Vector3f.add(yOffset, new Vector3f(gunOrigin.x, gunOrigin.y, gunOrigin.z), (Vector3f)null), shootVec, (EntityLivingBase)super.riddenByEntity, gun.bulletSpread, gun.damage, gun.bulletSpeed, bulletItemStack.getItemDamage(), this.driveable.getDriveableType()));
                     if(this.soundDelay <= 0) {
                        PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, gun.shootSound, false);
                        this.soundDelay = gun.shootSoundLength;
                     }

                     int damage = bulletItemStack.getItemDamage();
                     if(!((EntityPlayer)super.riddenByEntity).capabilities.isCreativeMode) {
                        bulletItemStack.setItemDamage(damage + 1);
                     }

                     if(damage >= bulletItemStack.getMaxDamage()) {
                        if(((EntityPlayer)super.riddenByEntity).capabilities.isCreativeMode) {
                           bulletItemStack.setItemDamage(0);
                        } else {
                           this.driveable.getDriveableData().ammo[this.seatInfo.gunnerID] = null;
                        }
                     }

                     this.gunDelay = gun.shootDelay;
                  }
               }
            }
         }

         return false;
      }
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
         } else if(currentItem != null && currentItem.getItem() instanceof ItemTool && ((ItemTool)currentItem.getItem()).type.key) {
            return true;
         } else if(currentItem != null && currentItem.getItem() instanceof ItemLead) {
            if(super.riddenByEntity != null && super.riddenByEntity instanceof EntityLiving && !(super.riddenByEntity instanceof EntityPlayer)) {
               EntityLiving checkRange1 = (EntityLiving)super.riddenByEntity;
               super.riddenByEntity.mountEntity((Entity)null);
               checkRange1.setLeashedToEntity(entityplayer, true);
               return true;
            } else {
               double checkRange = 10.0D;
               List nearbyMobs = super.worldObj.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(super.posX - checkRange, super.posY - checkRange, super.posZ - checkRange, super.posX + checkRange, super.posY + checkRange, super.posZ + checkRange));
               Iterator var6 = nearbyMobs.iterator();

               while(var6.hasNext()) {
                  Object obj = var6.next();
                  EntityLiving entity = (EntityLiving)obj;
                  if(entity.getLeashed() && entity.getLeashedToEntity() == entityplayer && !this.driveable.locked) {
                     entity.mountEntity(this);
                     this.looking.setAngles(-entity.rotationYaw, entity.rotationPitch, 0.0F);
                     entity.clearLeashed(true, !entityplayer.capabilities.isCreativeMode);
                  }
               }

               return true;
            }
         } else if(super.riddenByEntity == null && !this.driveable.locked) {
            entityplayer.mountEntity(this);
            return true;
         } else {
            return false;
         }
      }
   }

   public Entity getControllingEntity() {
      return super.riddenByEntity;
   }

   public boolean isDead() {
      return super.isDead;
   }

   public void setDead() {
      super.setDead();
   }

   public ItemStack getPickedResult(MovingObjectPosition target) {
      return super.worldObj.isRemote && !this.foundDriveable?null:this.driveable.getPickedResult(target);
   }

   public float getPlayerRoll() {
      while(this.playerRoll - this.prevPlayerRoll > 180.0F) {
         this.playerRoll -= 360.0F;
      }

      while(this.playerRoll - this.prevPlayerRoll < -180.0F) {
         this.playerRoll += 360.0F;
      }

      return this.playerRoll;
   }

   public float getCameraDistance() {
      return this.foundDriveable && this.seatID == 0?this.driveable.getDriveableType().cameraDistance:5.0F;
   }

   public boolean attackEntityFrom(DamageSource source, float f) {
      return (!super.worldObj.isRemote || this.foundDriveable) && this.driveable.attackEntityFrom(source, f);
   }

   public void writeSpawnData(ByteBuf data) {
      data.writeInt(this.driveableID);
      data.writeInt(this.seatInfo.id);
   }

   public void readSpawnData(ByteBuf data) {
      this.driveableID = data.readInt();
      this.driveable = (EntityDriveable)super.worldObj.getEntityByID(this.driveableID);
      this.seatID = data.readInt();
      this.driver = this.seatID == 0;
      if(this.driveable != null) {
         this.seatInfo = this.driveable.getDriveableType().seats[this.seatID];
         this.looking.setAngles((this.seatInfo.minYaw + this.seatInfo.maxYaw) / 2.0F, 0.0F, 0.0F);
         this.playerPosX = this.prevPlayerPosX = super.posX = this.driveable.posX;
         this.playerPosY = this.prevPlayerPosY = super.posY = this.driveable.posY;
         this.playerPosZ = this.prevPlayerPosZ = super.posZ = this.driveable.posZ;
         this.setPosition(super.posX, super.posY, super.posZ);
      }

   }

   public float getMinigunSpeed() {
      return this.minigunSpeed;
   }
}
