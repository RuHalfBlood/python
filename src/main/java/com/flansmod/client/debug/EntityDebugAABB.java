package com.flansmod.client.debug;

import com.flansmod.common.vector.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityDebugAABB extends Entity {

   public Vector3f vector;
   public int life;
   public float red;
   public float green;
   public float blue;
   public float rotationRoll;
   public Vector3f offset;


   public EntityDebugAABB(World w, Vector3f u, Vector3f v, int i, float r, float g, float b, float yaw, float pitch, float roll, Vector3f offset) {
      super(w);
      this.red = 1.0F;
      this.green = 1.0F;
      this.blue = 1.0F;
      this.setPosition((double)u.x, (double)u.y, (double)u.z);
      super.rotationYaw = yaw;
      super.rotationPitch = pitch;
      this.rotationRoll = roll;
      this.vector = v;
      this.life = i;
      this.red = r;
      this.green = g;
      this.blue = b;
      this.offset = offset;
   }

   public EntityDebugAABB(World w, Vector3f u, Vector3f v, int i, float r, float g, float b, float yaw, float pitch, float roll) {
      this(w, u, v, i, r, g, b, yaw, pitch, roll, new Vector3f());
   }

   public EntityDebugAABB(World w, Vector3f u, Vector3f v, int i, float r, float g, float b) {
      this(w, u, v, i, r, g, b, 0.0F, 0.0F, 0.0F);
   }

   public EntityDebugAABB(World w, Vector3f u, Vector3f v, int i) {
      this(w, u, v, i, 1.0F, 1.0F, 1.0F);
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

   protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {}

   protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {}
}
