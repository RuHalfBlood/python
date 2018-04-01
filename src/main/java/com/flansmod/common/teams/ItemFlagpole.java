package com.flansmod.common.teams;

import com.flansmod.common.FlansMod;
import com.flansmod.common.teams.EntityFlagpole;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class ItemFlagpole extends Item {

   public ItemFlagpole() {
      this.setCreativeTab(FlansMod.tabFlanTeams);
   }

   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
      float f = 1.0F;
      float f1 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
      float f2 = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f;
      double d = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double)f;
      double d1 = entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double)f + 1.62D - (double)entityplayer.yOffset;
      double d2 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double)f;
      Vec3 vec3d = Vec3.createVectorHelper(d, d1, d2);
      float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
      float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
      float f5 = -MathHelper.cos(-f1 * 0.01745329F);
      float f6 = MathHelper.sin(-f1 * 0.01745329F);
      float f7 = f4 * f5;
      float f9 = f3 * f5;
      double d3 = 5.0D;
      Vec3 vec3d1 = vec3d.addVector((double)f7 * d3, (double)f6 * d3, (double)f9 * d3);
      MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3d, vec3d1, true);
      if(movingobjectposition == null) {
         return itemstack;
      } else {
         if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
            int i = movingobjectposition.blockX;
            int j = movingobjectposition.blockY;
            int k = movingobjectposition.blockZ;
            if(!world.isRemote) {
               if(world.getBlock(i, j, k) == Blocks.snow) {
                  --j;
               }

               if(this.isSolid(world, i, j, k)) {
                  world.spawnEntityInWorld(new EntityFlagpole(world, i, j + 1, k));
               }
            }
         }

         return itemstack;
      }
   }

   private boolean isSolid(World world, int i, int j, int k) {
      Block block = world.getBlock(i, j, k);
      return block != null && block.getMaterial().isSolid() && block.isOpaqueCube();
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister icon) {
      super.itemIcon = icon.registerIcon("FlansMod:flagpole");
   }
}
