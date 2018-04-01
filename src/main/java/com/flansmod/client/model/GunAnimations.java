package com.flansmod.client.model;

import java.util.Random;

public class GunAnimations {

   public static GunAnimations defaults = new GunAnimations();
   public boolean isGunEmpty;
   public float gunRecoil = 0.0F;
   public float lastGunRecoil = 0.0F;
   public float gunSlide = 0.0F;
   public float lastGunSlide = 0.0F;
   public int timeUntilPump = 0;
   public int timeToPumpFor = 0;
   public float pumped = -1.0F;
   public float lastPumped = -1.0F;
   public boolean pumping = false;
   public boolean reloading = false;
   public float reloadAnimationTime = 0.0F;
   public float reloadAnimationProgress = 0.0F;
   public float lastReloadAnimationProgress = 0.0F;
   public float minigunBarrelRotation = 0.0F;
   public float minigunBarrelRotationSpeed = 0.0F;
   public int muzzleFlashTime = 0;
   public int flashInt = 0;
   public float hammerRotation = 0.0F;
   public int timeUntilPullback = 0;
   public float gunPullback = -1.0F;
   public float lastGunPullback = -1.0F;
   public boolean isFired = false;
   public int meleeAnimationProgress = 0;
   public int meleeAnimationLength = 0;


   public void update() {
      this.lastPumped = this.pumped;
      this.lastGunPullback = this.gunPullback;
      if(this.timeUntilPump > 0) {
         --this.timeUntilPump;
         if(this.timeUntilPump == 0) {
            this.pumping = true;
            this.lastPumped = this.pumped = -1.0F;
         }
      }

      if(this.timeUntilPullback > 0) {
         --this.timeUntilPullback;
         if(this.timeUntilPullback == 0) {
            this.isFired = true;
            this.lastGunPullback = this.gunPullback = -1.0F;
         }
      } else {
         this.hammerRotation *= 0.6F;
      }

      if(this.muzzleFlashTime > 0) {
         --this.muzzleFlashTime;
      }

      if(this.pumping) {
         this.pumped += 2.0F / (float)this.timeToPumpFor;
         if(this.pumped >= 0.999F) {
            this.pumping = false;
         }
      }

      if(this.isFired) {
         this.gunPullback += 0.5F;
         if(this.gunPullback >= 0.999F) {
            this.isFired = false;
         }
      }

      this.lastGunRecoil = this.gunRecoil;
      if(this.gunRecoil > 0.0F) {
         this.gunRecoil *= 0.5F;
      }

      this.lastGunSlide = this.gunSlide;
      if(this.isGunEmpty) {
         this.lastGunSlide = this.gunSlide = 0.5F;
      }

      if(this.gunSlide > 0.0F && !this.isGunEmpty) {
         this.gunSlide *= 0.5F;
      }

      this.lastReloadAnimationProgress = this.reloadAnimationProgress;
      if(this.reloading) {
         this.reloadAnimationProgress += 1.0F / this.reloadAnimationTime;
      }

      if(this.reloading && this.reloadAnimationProgress >= 0.9F) {
         this.isGunEmpty = false;
      }

      if(this.reloading && this.reloadAnimationProgress >= 1.0F) {
         this.reloading = false;
      }

      this.minigunBarrelRotation += this.minigunBarrelRotationSpeed;
      this.minigunBarrelRotationSpeed *= 0.9F;
      if(this.meleeAnimationLength > 0) {
         ++this.meleeAnimationProgress;
         if(this.meleeAnimationProgress == this.meleeAnimationLength) {
            this.meleeAnimationProgress = this.meleeAnimationLength = 0;
         }
      }

   }

   public void onGunEmpty(boolean atLastBullet) {
      this.isGunEmpty = atLastBullet;
   }

   public void doShoot(int pumpDelay, int pumpTime, int hammerDelay, float hammerAngle) {
      this.minigunBarrelRotationSpeed += 2.0F;
      this.lastGunSlide = this.gunSlide = 1.0F;
      this.lastGunRecoil = this.gunRecoil = 1.0F;
      this.timeUntilPump = pumpDelay;
      this.timeToPumpFor = pumpTime;
      this.timeUntilPullback = hammerDelay;
      this.hammerRotation = hammerAngle;
      this.muzzleFlashTime = 2;
      Random r = new Random();
      byte Low = -1;
      byte High = 3;
      int result = r.nextInt(High - Low) + Low;
      if(result == -1) {
         result = 0;
      }

      if(result == 3) {
         result = 2;
      }

      this.flashInt = result;
   }

   public void doReload(int reloadTime, int pumpDelay, int pumpTime) {
      this.reloading = true;
      this.lastReloadAnimationProgress = this.reloadAnimationProgress = 0.0F;
      this.reloadAnimationTime = (float)reloadTime;
      this.timeUntilPump = pumpDelay;
      this.timeToPumpFor = pumpTime;
   }

   public void doMelee(int meleeTime) {
      this.meleeAnimationLength = meleeTime;
   }

}
