package com.flansmod.client.debug;

import com.flansmod.common.vector.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityDebugDot extends Entity {

   public float red = 1.0F;
   public float green = 1.0F;
   public float blue = 1.0F;
   public int life = 0;


   public EntityDebugDot(World w) {
      super(w);
   }

   public EntityDebugDot(World w, Vector3f pos, int l) {
      super(w);
      this.setPosition((double)pos.x, (double)pos.y, (double)pos.z);
      this.life = l;
   }

   public EntityDebugDot(World w, Vector3f pos, int l, float r, float g, float b) {
      super(w);
      this.setPosition((double)pos.x, (double)pos.y, (double)pos.z);
      this.life = l;
      this.red = r;
      this.green = g;
      this.blue = b;
   }

   public void onUpdate() {
      --this.life;
      if(this.life <= 0) {
         this.setDead();
      }

   }

   public AxisAlignedBB getBoundingBox() {
      return null;
   }

   protected void entityInit() {}

   protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {}

   protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {}
}
