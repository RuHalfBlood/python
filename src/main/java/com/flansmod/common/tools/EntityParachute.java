package com.flansmod.common.tools;

import com.flansmod.common.tools.ToolType;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityParachute extends Entity implements IEntityAdditionalSpawnData {

   public ToolType type;


   public EntityParachute(World w) {
      super(w);
      super.ignoreFrustumCheck = true;
      System.out.println(w.isRemote?"Client paraspawn":"Server paraspawn");
   }

   public EntityParachute(World w, ToolType t, EntityPlayer player) {
      this(w);
      this.type = t;
      if(canUseParachute(player)) {
         --player.posY;
         this.setPosition(player.posX, player.posY - 1.5D, player.posZ);
      } else {
         this.setDead();
      }

   }

   public static boolean canUseParachute(Entity player) {
      List list = player.worldObj.getCollidingBoundingBoxes(player, player.boundingBox.expand(0.0D, 3.0D, 0.0D));
      return list.size() == 0;
   }

   public void onUpdate() {
      super.onUpdate();
      if(!super.worldObj.isRemote && (super.riddenByEntity == null || super.riddenByEntity.ridingEntity != this)) {
         this.setDead();
      }

      if(super.riddenByEntity != null) {
         super.riddenByEntity.fallDistance = 0.0F;
      }

      super.motionY = -0.3D;
      if(super.riddenByEntity != null && super.riddenByEntity instanceof EntityLivingBase) {
         float speedMultiplier = 0.025F;
         double moveForwards = (double)((EntityLivingBase)super.riddenByEntity).moveForward;
         double moveStrafing = (double)((EntityLivingBase)super.riddenByEntity).moveStrafing;
         double sinYaw = -Math.sin((double)(super.riddenByEntity.rotationYaw * 3.1415927F / 180.0F));
         double cosYaw = Math.cos((double)(super.riddenByEntity.rotationYaw * 3.1415927F / 180.0F));
         super.motionX += (moveForwards * sinYaw + moveStrafing * cosYaw) * (double)speedMultiplier;
         super.motionZ += (moveForwards * cosYaw - moveStrafing * sinYaw) * (double)speedMultiplier;
         super.prevRotationYaw = super.rotationYaw;
         super.rotationYaw = super.riddenByEntity.rotationYaw;
      }

      super.motionX *= 0.9300000071525574D;
      super.motionZ *= 0.9300000071525574D;
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      if(super.onGround || super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.posY), MathHelper.floor_double(super.posZ)).getMaterial() == Material.water) {
         this.setDead();
      }

   }

   protected void fall(float par1) {}

   public boolean attackEntityFrom(DamageSource source, float f) {
      this.setDead();
      return true;
   }

   protected void entityInit() {}

   protected void readEntityFromNBT(NBTTagCompound tags) {
      this.type = ToolType.getType(tags.getString("Type"));
   }

   protected void writeEntityToNBT(NBTTagCompound tags) {
      tags.setString("Type", this.type.shortName);
   }

   public ItemStack getPickedResult(MovingObjectPosition target) {
      ItemStack stack = new ItemStack(this.type.item, 1, 0);
      return stack;
   }

   public void writeSpawnData(ByteBuf buffer) {
      ByteBufUtils.writeUTF8String(buffer, this.type.shortName);
   }

   public void readSpawnData(ByteBuf additionalData) {
      this.type = ToolType.getType(ByteBufUtils.readUTF8String(additionalData));
   }
}
