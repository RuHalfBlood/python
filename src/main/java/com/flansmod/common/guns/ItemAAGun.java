package com.flansmod.common.guns;

import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.AAGunType;
import com.flansmod.common.guns.EntityAAGun;
import com.flansmod.common.types.IFlanItem;
import com.flansmod.common.types.InfoType;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemAAGun extends Item implements IFlanItem {

   public static final ArrayList names = new ArrayList();
   @SideOnly(Side.CLIENT)
   private ArrayList icons;
   public AAGunType type;


   public ItemAAGun(AAGunType type1) {
      super.maxStackSize = 1;
      this.type = type1;
      this.type.item = this;
      this.setCreativeTab(FlansMod.tabFlanGuns);
      GameRegistry.registerItem(this, this.type.shortName, "flansmod");
   }

   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
      float cosYaw = MathHelper.cos(-entityplayer.rotationYaw * 0.01745329F - 3.141593F);
      float sinYaw = MathHelper.sin(-entityplayer.rotationYaw * 0.01745329F - 3.141593F);
      float cosPitch = -MathHelper.cos(-entityplayer.rotationPitch * 0.01745329F);
      float sinPitch = MathHelper.sin(-entityplayer.rotationPitch * 0.01745329F);
      double length = 5.0D;
      Vec3 posVec = Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY + 1.62D - (double)entityplayer.yOffset, entityplayer.posZ);
      Vec3 lookVec = posVec.addVector((double)(sinYaw * cosPitch) * length, (double)sinPitch * length, (double)(cosYaw * cosPitch) * length);
      MovingObjectPosition movingobjectposition = world.rayTraceBlocks(posVec, lookVec, true);
      if(movingobjectposition == null) {
         return itemstack;
      } else {
         if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
            int i = movingobjectposition.blockX;
            int j = movingobjectposition.blockY;
            int k = movingobjectposition.blockZ;
            if(!world.isRemote && world.isSideSolid(i, j, k, ForgeDirection.UP)) {
               world.spawnEntityInWorld(new EntityAAGun(world, this.type, (double)i + 0.5D, (double)j + 1.0D, (double)k + 0.5D, entityplayer));
            }

            if(!entityplayer.capabilities.isCreativeMode) {
               --itemstack.stackSize;
            }
         }

         return itemstack;
      }
   }

   public Entity spawnAAGun(World world, double x, double y, double z, ItemStack stack) {
      EntityAAGun entity = new EntityAAGun(world, this.type, x, y, z, (EntityPlayer)null);
      if(!world.isRemote) {
         world.spawnEntityInWorld(entity);
      }

      return entity;
   }

   @SideOnly(Side.CLIENT)
   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
      return this.type.colour;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister icon) {
      super.itemIcon = icon.registerIcon("FlansMod:" + this.type.iconPath);
   }

   public InfoType getInfoType() {
      return this.type;
   }

}
