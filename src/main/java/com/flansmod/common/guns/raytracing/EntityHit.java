package com.flansmod.common.guns.raytracing;

import com.flansmod.common.guns.raytracing.BulletHit;
import net.minecraft.entity.Entity;

public class EntityHit extends BulletHit {

   public Entity entity;


   public EntityHit(Entity e, float f) {
      super(f);
      this.entity = e;
   }
}
