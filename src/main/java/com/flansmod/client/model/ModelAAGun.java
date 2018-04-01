package com.flansmod.client.model;

import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.guns.EntityAAGun;
import net.minecraft.client.model.ModelBase;

public class ModelAAGun extends ModelBase {

   public boolean oldModel = false;
   public ModelRendererTurbo[] baseModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] seatModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] gunModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[][] barrelModel = new ModelRendererTurbo[0][0];
   public ModelRendererTurbo[][] ammoModel = new ModelRendererTurbo[0][0];
   public ModelRendererTurbo[] gunsightModel = new ModelRendererTurbo[0];
   public int barrelX;
   public int barrelY;
   public int barrelZ;


   public void renderBase(float f, float f1, float f2, float f3, float f4, float f5, EntityAAGun aa) {
      ModelRendererTurbo[] var8 = this.baseModel;
      int var9 = var8.length;

      for(int var10 = 0; var10 < var9; ++var10) {
         ModelRendererTurbo aBaseModel = var8[var10];
         aBaseModel.render(f5);
      }

   }

   public void renderGun(float f, float f1, float f2, float f3, float f4, float f5, EntityAAGun aa) {
      ModelRendererTurbo[] i = this.seatModel;
      int j = i.length;

      int var10;
      ModelRendererTurbo aGunsightModel;
      for(var10 = 0; var10 < j; ++var10) {
         aGunsightModel = i[var10];
         aGunsightModel.render(f5);
      }

      i = this.gunModel;
      j = i.length;

      for(var10 = 0; var10 < j; ++var10) {
         aGunsightModel = i[var10];
         aGunsightModel.setPosition((float)this.barrelX, (float)this.barrelY, (float)this.barrelZ);
         aGunsightModel.rotateAngleZ = -aa.gunPitch / 180.0F * 3.1415927F;
         aGunsightModel.render(f5);
      }

      i = this.gunsightModel;
      j = i.length;

      for(var10 = 0; var10 < j; ++var10) {
         aGunsightModel = i[var10];
         aGunsightModel.rotateAngleZ = -aa.gunPitch / 180.0F * 3.1415927F;
         aGunsightModel.render(f5);
      }

      int var12;
      for(var12 = 0; var12 < this.barrelModel.length; ++var12) {
         for(j = 0; j < this.barrelModel[var12].length; ++j) {
            this.barrelModel[var12][j].setPosition(-aa.barrelRecoil[var12] * (float)Math.cos((double)(-aa.gunPitch * 3.1415927F / 180.0F)) + (float)this.barrelX, -aa.barrelRecoil[var12] * (float)Math.sin((double)(-aa.gunPitch * 3.1415927F / 180.0F)) + (float)this.barrelY, (float)this.barrelZ);
            this.barrelModel[var12][j].rotateAngleZ = -aa.gunPitch / 180.0F * 3.1415927F;
            this.barrelModel[var12][j].render(f5);
         }
      }

      for(var12 = 0; var12 < this.ammoModel.length; ++var12) {
         if(aa.ammo[var12] != null) {
            for(j = 0; j < this.ammoModel[var12].length; ++j) {
               this.ammoModel[var12][j].setPosition((float)this.barrelX, (float)this.barrelY, (float)this.barrelZ);
               this.ammoModel[var12][j].rotateAngleZ = -aa.gunPitch / 180.0F * 3.1415927F;
               this.ammoModel[var12][j].render(f5);
            }
         }
      }

   }

   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {}

   public void flipAll() {
      ModelRendererTurbo[] var1 = this.baseModel;
      int var2 = var1.length;

      int var3;
      ModelRendererTurbo anAmmoModel;
      for(var3 = 0; var3 < var2; ++var3) {
         anAmmoModel = var1[var3];
         anAmmoModel.doMirror(false, true, true);
         anAmmoModel.setRotationPoint(anAmmoModel.rotationPointX, -anAmmoModel.rotationPointY, -anAmmoModel.rotationPointZ);
      }

      var1 = this.seatModel;
      var2 = var1.length;

      for(var3 = 0; var3 < var2; ++var3) {
         anAmmoModel = var1[var3];
         anAmmoModel.doMirror(false, true, true);
         anAmmoModel.setRotationPoint(anAmmoModel.rotationPointX, -anAmmoModel.rotationPointY, -anAmmoModel.rotationPointZ);
      }

      var1 = this.gunModel;
      var2 = var1.length;

      for(var3 = 0; var3 < var2; ++var3) {
         anAmmoModel = var1[var3];
         anAmmoModel.doMirror(false, true, true);
         anAmmoModel.setRotationPoint(anAmmoModel.rotationPointX, -anAmmoModel.rotationPointY, -anAmmoModel.rotationPointZ);
      }

      var1 = this.gunsightModel;
      var2 = var1.length;

      for(var3 = 0; var3 < var2; ++var3) {
         anAmmoModel = var1[var3];
         anAmmoModel.doMirror(false, true, true);
         anAmmoModel.setRotationPoint(anAmmoModel.rotationPointX, -anAmmoModel.rotationPointY, -anAmmoModel.rotationPointZ);
      }

      ModelRendererTurbo[][] var6 = this.barrelModel;
      var2 = var6.length;

      int j;
      ModelRendererTurbo[] var7;
      for(var3 = 0; var3 < var2; ++var3) {
         var7 = var6[var3];

         for(j = 0; j < var7.length; ++j) {
            var7[j].doMirror(false, true, true);
            var7[j].setRotationPoint(var7[j].rotationPointX, -var7[j].rotationPointY, -var7[j].rotationPointZ);
         }
      }

      var6 = this.ammoModel;
      var2 = var6.length;

      for(var3 = 0; var3 < var2; ++var3) {
         var7 = var6[var3];

         for(j = 0; j < var7.length; ++j) {
            var7[j].doMirror(false, true, true);
            var7[j].setRotationPoint(var7[j].rotationPointX, -var7[j].rotationPointY, -var7[j].rotationPointZ);
         }
      }

   }
}
