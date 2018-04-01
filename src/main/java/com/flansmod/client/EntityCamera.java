package com.flansmod.client;

import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCamera extends EntityLivingBase {

   public EntityDriveable driveable;


   public EntityCamera(World world) {
      super(world);
      this.setSize(0.0F, 0.0F);
   }

   public EntityCamera(World world, EntityDriveable d) {
      this(world);
      this.driveable = d;
      this.setPosition(d.posX, d.posY, d.posZ);
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      Vector3f cameraPosition = new Vector3f();
      cameraPosition = this.driveable.axes.findLocalVectorGlobally(cameraPosition);
      double dX = this.driveable.posX + (double)cameraPosition.x - super.posX;
      double dY = this.driveable.posY + (double)cameraPosition.y - super.posY;
      double dZ = this.driveable.posZ + (double)cameraPosition.z - super.posZ;
      float lerpAmount = 0.1F;
      this.setPosition(super.posX + dX * (double)lerpAmount, super.posY + dY * (double)lerpAmount, super.posZ + dZ * (double)lerpAmount);
      if(FlansMod.proxy.mouseControlEnabled()) {
         super.rotationYaw = this.driveable.axes.getYaw() - 90.0F;
         super.rotationPitch = this.driveable.axes.getPitch();
      } else {
         EntityPlayer player = FlansMod.proxy.getThePlayer();
         if(player != null) {
            super.rotationYaw = player.rotationYaw;
            super.rotationPitch = player.rotationPitch;
         }
      }

      while(super.rotationYaw - super.prevRotationYaw >= 180.0F) {
         super.rotationYaw -= 360.0F;
      }

      while(super.rotationYaw - super.prevRotationYaw < -180.0F) {
         super.rotationYaw += 360.0F;
      }

   }

   public ItemStack getHeldItem() {
      return null;
   }

   public ItemStack getEquipmentInSlot(int p_71124_1_) {
      return null;
   }

   public void setCurrentItemOrArmor(int p_70062_1_, ItemStack p_70062_2_) {}

   public ItemStack[] getLastActiveItems() {
      return null;
   }
}
