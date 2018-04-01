package com.flansmod.client.model;

import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.teams.ArmourType;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ModelCustomArmour extends ModelBiped {

   public ArmourType type;
   public ModelRendererTurbo[] headModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] bodyModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] leftArmModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] rightArmModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] leftLegModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] rightLegModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] skirtFrontModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] skirtRearModel = new ModelRendererTurbo[0];


   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      GL11.glPushMatrix();
      GL11.glScalef(this.type.modelScale, this.type.modelScale, this.type.modelScale);
      super.isSneak = entity.isSneaking();
      ItemStack itemstack = ((EntityLivingBase)entity).getEquipmentInSlot(0);
      super.heldItemRight = itemstack != null?1:0;
      super.aimedBow = false;
      if(itemstack != null && entity instanceof EntityPlayer && ((EntityPlayer)entity).getItemInUseCount() > 0) {
         EnumAction enumaction = itemstack.getItemUseAction();
         if(enumaction == EnumAction.block) {
            super.heldItemRight = 3;
         } else if(enumaction == EnumAction.bow) {
            super.aimedBow = true;
         }
      }

      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.render(this.headModel, super.bipedHead, f5, this.type.modelScale);
      this.render(this.bodyModel, super.bipedBody, f5, this.type.modelScale);
      this.render(this.leftArmModel, super.bipedLeftArm, f5, this.type.modelScale);
      this.render(this.rightArmModel, super.bipedRightArm, f5, this.type.modelScale);
      this.render(this.leftLegModel, super.bipedLeftLeg, f5, this.type.modelScale);
      this.render(this.rightLegModel, super.bipedRightLeg, f5, this.type.modelScale);
      ModelRendererTurbo[] var13 = this.skirtFrontModel;
      int var10 = var13.length;

      int var11;
      ModelRendererTurbo mod;
      for(var11 = 0; var11 < var10; ++var11) {
         mod = var13[var11];
         mod.rotationPointX = (super.bipedLeftLeg.rotationPointX + super.bipedRightLeg.rotationPointX) / 2.0F / this.type.modelScale;
         mod.rotationPointY = (super.bipedLeftLeg.rotationPointY + super.bipedRightLeg.rotationPointY) / 2.0F / this.type.modelScale;
         mod.rotationPointZ = (super.bipedLeftLeg.rotationPointZ + super.bipedRightLeg.rotationPointZ) / 2.0F / this.type.modelScale;
         mod.rotateAngleX = Math.min(super.bipedLeftLeg.rotateAngleX, super.bipedRightLeg.rotateAngleX);
         mod.rotateAngleY = super.bipedLeftLeg.rotateAngleY;
         mod.rotateAngleZ = super.bipedLeftLeg.rotateAngleZ;
         mod.render(f5);
      }

      var13 = this.skirtRearModel;
      var10 = var13.length;

      for(var11 = 0; var11 < var10; ++var11) {
         mod = var13[var11];
         mod.rotationPointX = (super.bipedLeftLeg.rotationPointX + super.bipedRightLeg.rotationPointX) / 2.0F / this.type.modelScale;
         mod.rotationPointY = (super.bipedLeftLeg.rotationPointY + super.bipedRightLeg.rotationPointY) / 2.0F / this.type.modelScale;
         mod.rotationPointZ = (super.bipedLeftLeg.rotationPointZ + super.bipedRightLeg.rotationPointZ) / 2.0F / this.type.modelScale;
         mod.rotateAngleX = Math.max(super.bipedLeftLeg.rotateAngleX, super.bipedRightLeg.rotateAngleX);
         mod.rotateAngleY = super.bipedLeftLeg.rotateAngleY;
         mod.rotateAngleZ = super.bipedLeftLeg.rotateAngleZ;
         mod.render(f5);
      }

      GL11.glPopMatrix();
   }

   public void render(ModelRendererTurbo[] models, ModelRenderer bodyPart, float f5, float scale) {
      this.setBodyPart(models, bodyPart, scale);
      ModelRendererTurbo[] var5 = models;
      int var6 = models.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         ModelRendererTurbo mod = var5[var7];
         mod.rotateAngleX = bodyPart.rotateAngleX;
         mod.rotateAngleY = bodyPart.rotateAngleY;
         mod.rotateAngleZ = bodyPart.rotateAngleZ;
         mod.render(f5);
      }

   }

   public void setBodyPart(ModelRendererTurbo[] models, ModelRenderer bodyPart, float scale) {
      ModelRendererTurbo[] var4 = models;
      int var5 = models.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         ModelRendererTurbo mod = var4[var6];
         mod.rotationPointX = bodyPart.rotationPointX / scale;
         mod.rotationPointY = bodyPart.rotationPointY / scale;
         mod.rotationPointZ = bodyPart.rotationPointZ / scale;
      }

   }
}
