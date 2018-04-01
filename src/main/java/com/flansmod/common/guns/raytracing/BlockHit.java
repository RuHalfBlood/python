package com.flansmod.common.guns.raytracing;

import com.flansmod.common.guns.raytracing.BulletHit;
import net.minecraft.util.MovingObjectPosition;

public class BlockHit extends BulletHit {

   public MovingObjectPosition raytraceResult;


   public BlockHit(MovingObjectPosition mop, float f) {
      super(f);
      this.raytraceResult = mop;
   }
}
