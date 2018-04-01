package com.flansmod.client.model;

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.EnumMeleeAnimation;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

public class ModelGun extends ModelBase {

   public ModelRendererTurbo[] gunModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] backpackModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] defaultBarrelModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] defaultScopeModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] defaultStockModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] defaultGripModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] defaultGadgetModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] ammoModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] revolverBarrelModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] revolver2BarrelModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] breakActionModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] slideModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] pumpModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] minigunBarrelModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] leverActionModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] hammerModel = new ModelRendererTurbo[0];
   public Vector3f minigunBarrelOrigin = new Vector3f();
   public Vector3f barrelAttachPoint = new Vector3f();
   public Vector3f scopeAttachPoint = new Vector3f();
   public Vector3f stockAttachPoint = new Vector3f();
   public Vector3f gripAttachPoint = new Vector3f();
   public Vector3f gadgetAttachPoint = new Vector3f();
   public Vector3f slideAttachPoint = new Vector3f();
   public Vector3f pumpAttachPoint = new Vector3f();
   public Vector3f accessoryAttachPoint = new Vector3f();
   public ModelRendererTurbo[][] flashModel = new ModelRendererTurbo[0][0];
   public Vector3f muzzleFlashPoint = new Vector3f(0.0F, 0.0F, 0.0F);
   public boolean hasFlash = false;
   public double muzzleDistance = 0.0;
   
   public boolean hasArms = false;
   public Vector3f leftArmPos = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f leftArmRot = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f leftArmScale = new Vector3f(1.0F, 1.0F, 1.0F);
   public Vector3f rightArmPos = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f rightArmRot = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f rightArmScale = new Vector3f(1.0F, 1.0F, 1.0F);
   public Vector3f rightArmReloadPos = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f rightArmReloadRot = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f leftArmReloadPos = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f leftArmReloadRot = new Vector3f(0.0F, 0.0F, 0.0F);
   public boolean rightHandAmmo = false;
   public boolean leftHandAmmo = false;
   public float gunSlideDistance = 0.25F;
   public float RecoilSlideDistance = 0.125F;
   public float RotateSlideDistance = -8.0F;
   public EnumAnimationType animationType;
   public EnumMeleeAnimation meleeAnimation;
   public float tiltGunTime;
   public float unloadClipTime;
   public float loadClipTime;
   public float untiltGunTime;
   public boolean scopeIsOnSlide;
   public boolean scopeIsOnBreakAction;
   public float numBulletsInReloadAnimation;
   public int pumpDelay;
   public int pumpDelayAfterReload;
   public int pumpTime;
   public int hammerDelay;
   public float pumpHandleDistance;
   public float endLoadedAmmoDistance;
   public boolean gripIsOnPump;
   public boolean gadgetIsOnPump;
   public Vector3f barrelBreakPoint;
   public float revolverFlipAngle;
   public float revolver2FlipAngle;
   public Vector3f revolverFlipPoint;
   public Vector3f revolver2FlipPoint;
   public float breakAngle;
   public boolean spinningCocking;
   public Vector3f spinPoint;
   public Vector3f hammerSpinPoint;
   public float hammerAngle;
   public boolean isSingleAction;
   public boolean slideLockOnEmpty;
   public float rotateGunVertical;
   public float rotateGunHorizontal;
   public float tiltGun;
   public Vector3f translateGun;
   public float rotateClipVertical;
   public float rotateClipHorizontal;
   public float tiltClip;
   public Vector3f translateClip;
   public Vector3f thirdPersonOffset;
   public Vector3f itemFrameOffset;
   private static float lightmapLastX;
   private static float lightmapLastY;
   private static boolean optifineBreak = false;
   public int test;

   public ModelGun() {
      this.animationType = EnumAnimationType.NONE;
      this.meleeAnimation = EnumMeleeAnimation.DEFAULT;
      this.tiltGunTime = 0.25F;
      this.unloadClipTime = 0.25F;
      this.loadClipTime = 0.25F;
      this.untiltGunTime = 0.25F;
      this.scopeIsOnSlide = false;
      this.scopeIsOnBreakAction = false;
      this.numBulletsInReloadAnimation = 1.0F;
      this.pumpDelay = 0;
      this.pumpDelayAfterReload = 0;
      this.pumpTime = 1;
      this.hammerDelay = 0;
      this.pumpHandleDistance = 0.25F;
      this.endLoadedAmmoDistance = 1.0F;
      this.gripIsOnPump = false;
      this.gadgetIsOnPump = false;
      this.barrelBreakPoint = new Vector3f();
      this.revolverFlipAngle = 15.0F;
      this.revolver2FlipAngle = 15.0F;
      this.revolverFlipPoint = new Vector3f();
      this.revolver2FlipPoint = new Vector3f();
      this.breakAngle = 45.0F;
      this.spinningCocking = false;
      this.spinPoint = new Vector3f();
      this.hammerSpinPoint = new Vector3f();
      this.hammerAngle = 75.0F;
      this.isSingleAction = false;
      this.slideLockOnEmpty = false;
      this.rotateGunVertical = 0.0F;
      this.rotateGunHorizontal = 0.0F;
      this.tiltGun = 0.0F;
      this.translateGun = new Vector3f(0.0F, 0.0F, 0.0F);
      this.rotateClipVertical = 0.0F;
      this.rotateClipHorizontal = 0.0F;
      this.tiltClip = 0.0F;
      this.translateClip = new Vector3f(0.0F, 0.0F, 0.0F);
      this.thirdPersonOffset = new Vector3f();
      this.itemFrameOffset = new Vector3f();
   }

   public static void glowOn() {
      glowOn(15);
   }

   public static void glowOn(int glow) {
      GL11.glPushAttrib(64);
      GL11.glEnable(3042);
      GL11.glBlendFunc(1, 1);

      try {
         lightmapLastX = OpenGlHelper.lastBrightnessX;
         lightmapLastY = OpenGlHelper.lastBrightnessY;
      } catch (NoSuchFieldError var3) {
         optifineBreak = true;
      }

      float glowRatioX = Math.min((float)glow / 15.0F * 240.0F + lightmapLastX, 240.0F);
      float glowRatioY = Math.min((float)glow / 15.0F * 240.0F + lightmapLastY, 240.0F);
      if(!optifineBreak) {
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, glowRatioX, glowRatioY);
      }

   }

   public static void glowOff() {
      GL11.glEnable(2896);
      if(!optifineBreak) {
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lightmapLastX, lightmapLastY);
      }

      GL11.glPopAttrib();
   }

   public void renderGun(float f) {
      this.render(this.gunModel, f);
   }

   public void renderSlide(float f) {
      this.render(this.slideModel, f);
   }

   public void renderPump(float f) {
      this.render(this.pumpModel, f);
   }

   public void renderDefaultScope(float f) {
      this.render(this.defaultScopeModel, f);
   }

   public void renderDefaultBarrel(float f) {
      this.render(this.defaultBarrelModel, f);
   }

   public void renderDefaultStock(float f) {
      this.render(this.defaultStockModel, f);
   }

   public void renderDefaultGrip(float f) {
      this.render(this.defaultGripModel, f);
   }

   public void renderDefaultGadget(float f) {
      this.render(this.defaultGadgetModel, f);
   }

   public void renderAmmo(float f) {
      this.render(this.ammoModel, f);
   }

   public void renderMinigunBarrel(float f) {
      this.render(this.minigunBarrelModel, f);
   }

   public void renderRevolverBarrel(float f) {
      this.render(this.revolverBarrelModel, f);
   }

   public void renderRevolver2Barrel(float f) {
      this.render(this.revolver2BarrelModel, f);
   }

   public void renderBreakAction(float f) {
      this.render(this.breakActionModel, f);
   }

   public void renderHammer(float f) {
      this.render(this.hammerModel, f);
   }

   
   private void render(ModelRendererTurbo[] models, float f) {
      ModelRendererTurbo[] var3 = models;
      int var4 = models.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         ModelRendererTurbo model = var3[var5];
         if(model != null) {
            model.render(f);
         }
      }

   }

   public void flipAll() {
      this.flip(this.gunModel);
      this.flip(this.defaultBarrelModel);
      this.flip(this.defaultScopeModel);
      this.flip(this.defaultStockModel);
      this.flip(this.defaultGripModel);
      this.flip(this.defaultGadgetModel);
      this.flip(this.ammoModel);
      this.flip(this.slideModel);
      this.flip(this.pumpModel);
      this.flip(this.minigunBarrelModel);
      this.flip(this.revolverBarrelModel);
      this.flip(this.revolver2BarrelModel);
      this.flip(this.breakActionModel);
      this.flip(this.hammerModel);
   }

   protected void flip(ModelRendererTurbo[] model) {
      ModelRendererTurbo[] var2 = model;
      int var3 = model.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ModelRendererTurbo part = var2[var4];
         part.doMirror(false, true, true);
         part.setRotationPoint(part.rotationPointX, -part.rotationPointY, -part.rotationPointZ);
      }

   }

   public void translateAll(float x, float y, float z) {
      this.translate(this.gunModel, x, y, z);
      this.translate(this.defaultBarrelModel, x, y, z);
      this.translate(this.defaultScopeModel, x, y, z);
      this.translate(this.defaultStockModel, x, y, z);
      this.translate(this.defaultGripModel, x, y, z);
      this.translate(this.defaultGadgetModel, x, y, z);
      this.translate(this.ammoModel, x, y, z);
      this.translate(this.slideModel, x, y, z);
      this.translate(this.pumpModel, x, y, z);
      this.translate(this.minigunBarrelModel, x, y, z);
      this.translate(this.revolverBarrelModel, x, y, z);
      this.translate(this.revolver2BarrelModel, x, y, z);
      this.translate(this.breakActionModel, x, y, z);
      this.translate(this.hammerModel, x, y, z);
   }

   protected void translate(ModelRendererTurbo[] model, float x, float y, float z) {
      ModelRendererTurbo[] var5 = model;
      int var6 = model.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         ModelRendererTurbo mod = var5[var7];
         mod.rotationPointX += x;
         mod.rotationPointY += y;
         mod.rotationPointZ += z;
      }

   }

}
