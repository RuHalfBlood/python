package com.flansmod.client;

import com.flansmod.common.FlansMod;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class EntityFMFlame extends EntityFX {

   public static ResourceLocation icon = new ResourceLocation("flansmod", "particle/FMFlame.png");


   public EntityFMFlame(World w, double px, double py, double pz, double mx, double my, double mz) {
      super(w, px, py, pz, mx, my, mz);
      super.particleMaxAge = 6;
      super.particleAge = 0;
      super.particleGravity = 1.0F;
      super.motionX = mx;
      super.motionY = my;
      super.motionZ = mz;
      FlansMod.proxy.spawnParticle("flansmod.fmsmoke", super.posX, super.posY, super.posZ, 0.0D, 0.0D, 0.0D);
      icon = new ResourceLocation("flansmod", "particle/FMFlame.png");
   }

   public int getFXLayer() {
      return 3;
   }

   public float getEntityBrightness(float f) {
      return 1.0F;
   }

   public int getBrightnessForRender(float par1) {
      return 15728880;
   }

   public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
      GL11.glPushMatrix();
      par1Tessellator.startDrawingQuads();
      GL11.glAlphaFunc(516, 0.001F);
      GL11.glEnable(3042);
      int srcBlend = GL11.glGetInteger(3041);
      int dstBlend = GL11.glGetInteger(3040);
      GL11.glDepthMask(false);
      FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("flansmod", "particle/FMFlame.png"));
      float scale = 0.6F - (float)super.particleAge * 0.1F;
      float xPos = (float)(super.prevPosX + (super.posX - super.prevPosX) * (double)par2 - EntityFX.interpPosX);
      float yPos = (float)(super.prevPosY + (super.posY - super.prevPosY) * (double)par2 - EntityFX.interpPosY);
      float zPos = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * (double)par2 - EntityFX.interpPosZ);
      float colorIntensity = 1.0F;
      par1Tessellator.setColorRGBA_F(super.particleRed * colorIntensity, super.particleGreen * (colorIntensity - (float)super.particleAge * 0.2F), super.particleBlue * (colorIntensity - (float)super.particleAge * 0.2F), 1.0F - (float)super.particleAge * 0.1F);
      par1Tessellator.addVertexWithUV((double)(xPos - par3 * scale - par6 * scale), (double)(yPos - par4 * scale), (double)(zPos - par5 * scale - par7 * scale), 0.0D, 1.0D);
      par1Tessellator.addVertexWithUV((double)(xPos - par3 * scale + par6 * scale), (double)(yPos + par4 * scale), (double)(zPos - par5 * scale + par7 * scale), 1.0D, 1.0D);
      par1Tessellator.addVertexWithUV((double)(xPos + par3 * scale + par6 * scale), (double)(yPos + par4 * scale), (double)(zPos + par5 * scale + par7 * scale), 1.0D, 0.0D);
      par1Tessellator.addVertexWithUV((double)(xPos + par3 * scale - par6 * scale), (double)(yPos - par4 * scale), (double)(zPos + par5 * scale - par7 * scale), 0.0D, 0.0D);
      par1Tessellator.draw();
      GL11.glDisable(3042);
      GL11.glDepthMask(true);
      GL11.glPopMatrix();
   }

   public void onUpdate() {
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      super.renderDistanceWeight = 2000.0D;
      if(super.particleAge++ >= super.particleMaxAge) {
         this.setDead();
      }

      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      if(super.onGround) {
         this.setDead();
      }

   }

}
