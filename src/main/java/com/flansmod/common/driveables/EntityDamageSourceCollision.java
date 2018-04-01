package com.flansmod.common.driveables;

import com.flansmod.common.driveables.EntityDriveable;
import net.minecraft.util.EntityDamageSource;

public class EntityDamageSourceCollision extends EntityDamageSource {

   public EntityDriveable source;


   public EntityDamageSourceCollision(EntityDriveable driveable) {
      super(driveable.getDriveableType().shortName, driveable);
      this.source = driveable;
   }
}
