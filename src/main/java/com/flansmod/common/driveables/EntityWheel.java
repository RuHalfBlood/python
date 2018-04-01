package com.flansmod.common.driveables;

import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityWheel extends Entity implements IEntityAdditionalSpawnData {

   public EntityDriveable vehicle;
   public int ID;
   @SideOnly(Side.CLIENT)
   public boolean foundVehicle;
   private int vehicleID;
   public boolean onDeck;
   private int invulnerableUnmountCount;
   public int timeLimitDriveableNull;


   public EntityWheel(World world) {
      super(world);
      this.onDeck = false;
      this.timeLimitDriveableNull = 0;
      this.setSize(1.0F, 1.0F);
      super.stepHeight = 1.0F;
      this.invulnerableUnmountCount = 0;
   }

   public EntityWheel(World world, EntityDriveable entity, int i) {
      this(world);
      this.vehicle = entity;
      this.vehicleID = entity.getEntityId();
      this.ID = i;
      this.initPosition();
   }

   public void initPosition() {
      Vector3f wheelVector = this.vehicle.axes.findLocalVectorGlobally(this.vehicle.getDriveableType().wheelPositions[this.ID].position);
      this.setPosition(this.vehicle.posX + (double)wheelVector.x, this.vehicle.posY + (double)wheelVector.y, this.vehicle.posZ + (double)wheelVector.z);
      super.stepHeight = this.vehicle.getDriveableType().wheelStepHeight;
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
   }

   protected void fall(float k) {}

   protected void entityInit() {}

   protected void readEntityFromNBT(NBTTagCompound tags) {
      this.setDead();
   }

   protected void writeEntityToNBT(NBTTagCompound tags) {}

   public void onUpdate() {
      if(super.ridingEntity != null) {
         this.invulnerableUnmountCount = 80;
      } else if(this.invulnerableUnmountCount > 0) {
         --this.invulnerableUnmountCount;
      }

      if(super.worldObj.isRemote && !this.foundVehicle) {
         this.vehicle = (EntityDriveable)super.worldObj.getEntityByID(this.vehicleID);
         if(this.vehicle == null) {
            return;
         }

         this.foundVehicle = true;
         this.vehicle.wheels[this.ID] = this;
      }

      if(this.vehicle != null) {
         EntityDriveable entD = (EntityDriveable)super.worldObj.getEntityByID(this.vehicleID);
         if(entD == null) {
            ++this.timeLimitDriveableNull;
         } else {
            this.timeLimitDriveableNull = 0;
         }

         if(this.timeLimitDriveableNull > 1200) {
            this.setDead();
         }

         if(!super.addedToChunk) {
            super.worldObj.spawnEntityInWorld(this);
         }

      }
   }

   public double getSpeedXZ() {
      return Math.sqrt(super.motionX * super.motionX + super.motionZ * super.motionZ);
   }

   public double getSpeedXYZ() {
      return Math.cbrt(super.motionX * super.motionX + super.motionZ * super.motionZ + super.motionY * super.motionY);
   }

   public void setPositionAndRotation2(double d, double d1, double d2, float f, float f1, int i) {}

   public void writeSpawnData(ByteBuf data) {
      data.writeInt(this.vehicleID);
      data.writeInt(this.ID);
   }

   public void readSpawnData(ByteBuf data) {
      this.vehicleID = data.readInt();
      this.ID = data.readInt();
      this.vehicle = (EntityDriveable)super.worldObj.getEntityByID(this.vehicleID);
      if(this.vehicle != null) {
         this.setPosition(super.posX, super.posY, super.posZ);
      }

   }
}
