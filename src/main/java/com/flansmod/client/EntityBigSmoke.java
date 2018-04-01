package com.flansmod.client;

import com.flansmod.common.FlansMod;
import net.minecraft.client.particle.EntityExplodeFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityBigSmoke extends EntityExplodeFX {

   public int disTime;


   public EntityBigSmoke(World w, double px, double py, double pz, double mx, double my, double mz) {
      super(w, px, py, pz, mx, my, mz);
      super.particleMaxAge = 300;
      super.particleGravity = 1.0F;
      super.motionX = mx;
      super.motionY = my;
      super.motionZ = mz;
      super.particleScale = 0.0F;
      this.disTime = 10;
   }

   public int getFXLayer() {
      return 1;
   }

   public float getEntityBrightness(float f) {
      return 1.0F;
   }

   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {}

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
      --this.disTime;
      byte NUM = 1;
      if(this.disTime < 0) {
         double dx = (super.posX - super.prevPosX) / (double)NUM;
         double dy = (super.posY - super.prevPosY) / (double)NUM;
         double dz = (super.posZ - super.prevPosZ) / (double)NUM;
         FlansMod.proxy.spawnParticle("flansmod.rocketexhaust", super.prevPosX + dx * 1.0D + 5.0D * Math.random(), super.prevPosY + dy * 1.0D + 7.0D * Math.random(), super.prevPosZ + dz * 1.0D + 5.0D * Math.random(), 0.0D, 0.0D, 0.0D);
         this.disTime = 2;
      } else if(this.isInWater()) {
         super.motionY *= 0.89D;
         super.motionY += 0.1D;
      }

   }
}
