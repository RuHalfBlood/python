package com.flansmod.common.guns.raytracing;

import com.flansmod.common.guns.raytracing.BulletHit;
import com.flansmod.common.guns.raytracing.PlayerHitbox;

public class PlayerBulletHit extends BulletHit {

   public PlayerHitbox hitbox;


   public PlayerBulletHit(PlayerHitbox box, float f) {
      super(f);
      this.hitbox = box;
   }
}
