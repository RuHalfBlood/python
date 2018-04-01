package com.flansmod.client;

import com.flansmod.common.driveables.EntityDriveable;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class MovingSoundDriveable extends MovingSound {

   private final EntityDriveable driveable;
   private float field_147669_l = 0.0F;


   public MovingSoundDriveable(ResourceLocation sound, EntityDriveable driveable) {
      super(sound);
      this.driveable = driveable;
      super.repeat = true;
      super.field_147665_h = 0;
   }

   public void update() {
      if(this.driveable.isDead) {
         super.donePlaying = true;
      } else {
         super.xPosF = (float)this.driveable.posX;
         super.yPosF = (float)this.driveable.posY;
         super.zPosF = (float)this.driveable.posZ;
         float f = MathHelper.sqrt_double(this.driveable.motionX * this.driveable.motionX + this.driveable.motionZ * this.driveable.motionZ);
         if((double)f >= 0.01D) {
            this.field_147669_l = MathHelper.clamp_float(this.field_147669_l + 0.0025F, 0.0F, 1.0F);
            super.volume = 0.0F + MathHelper.clamp_float(f, 0.0F, 0.5F) * 0.7F;
         } else {
            this.field_147669_l = 0.0F;
            super.volume = 0.0F;
         }
      }

   }
}
