package com.flansmod.client;

import com.flansmod.common.FlansMod;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class EntityDebris1 extends EntityFX {

   public EntityDebris1(World w, double px, double py, double pz, double mx, double my, double mz) {
      super(w, px, py, pz, mx, my, mz);
      super.particleMaxAge *= 5;
      super.particleGravity = 1.0F;
      super.motionX = mx;
      super.motionY = my;
      super.motionZ = mz;
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

      super.motionY -= 0.04D * (double)super.particleGravity;
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      super.motionX *= 0.99D;
      super.motionY *= 0.99D;
      super.motionZ *= 0.99D;
      if(super.posY < 0.0D) {
         this.setDead();
      }

      byte NUM = 5;

      for(int i = 0; i < NUM; ++i) {
         double dx = (super.posX - super.prevPosX) / (double)NUM;
         double dy = (super.posY - super.prevPosY) / (double)NUM;
         double dz = (super.posZ - super.prevPosZ) / (double)NUM;
         if(super.particleAge < 10) {
            FlansMod.proxy.spawnParticle("flame", super.prevPosX + dx * (double)i, super.prevPosY + dy * (double)i, super.prevPosZ + dz * (double)i, 0.0D, 0.0D, 0.0D);
         }

         FlansMod.proxy.spawnParticle("largesmoke", super.prevPosX + dx * (double)i, super.prevPosY + dy * (double)i * 2.0D, super.prevPosZ + dz * (double)i, 0.0D, 0.0D, 0.0D);
      }

      if(super.onGround) {
         this.setDead();
      }

   }
}
