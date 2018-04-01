package com.flansmod.hand.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelHand extends ModelBase {

   public ModelRenderer rightArm = new ModelRenderer(this, 40, 16);
   public ModelRenderer leftArm;


   public ModelHand() {
      this.rightArm.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4);
      this.leftArm = new ModelRenderer(this, 40, 16);
      this.leftArm.mirror = true;
      this.leftArm.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4);
   }

   public void render(AbstractClientPlayer p, int f) {
      TextureManager renderengine = Minecraft.getMinecraft().renderEngine;
      renderengine.bindTexture(p.getLocationSkin());
      super.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
      if(f == 2) {
         this.rightArm.render(0.0625F);
      }

      if(f == 1) {
         this.leftArm.render(0.0625F);
      }

   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
   }
}
