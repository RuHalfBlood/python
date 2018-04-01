package com.flansmod.common.driveables;

import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.CollisionBox;
import com.flansmod.common.driveables.DriveableData;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.PlaneType;
import com.flansmod.common.paintjob.IPaintableItem;
import com.flansmod.common.paintjob.PaintableType;
import com.flansmod.common.paintjob.Paintjob;
import com.flansmod.common.parts.PartType;
import com.flansmod.common.types.EnumType;
import com.flansmod.common.types.InfoType;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSponge;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class ItemPlane extends Item implements IPaintableItem {

   public PlaneType type;
   public IIcon[] icons;


   public ItemPlane(PlaneType type1) {
      super.maxStackSize = 1;
      this.type = type1;
      this.type.item = this;
      this.setCreativeTab(FlansMod.tabFlanDriveables);
      GameRegistry.registerItem(this, this.type.shortName, "flansmod");
   }

   public boolean getShareTag() {
      return true;
   }

   private NBTTagCompound getTagCompound(ItemStack stack, World world) {
      if(stack.stackTagCompound == null) {
         if(!world.isRemote && stack.getItemDamage() != 0) {
            stack.stackTagCompound = this.getOldTagCompound(stack, world);
         }

         if(stack.stackTagCompound == null) {
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setString("Type", this.type.shortName);
            stack.stackTagCompound.setString("Engine", ((PartType)PartType.defaultEngines.get(EnumType.plane)).shortName);
         }
      }

      return stack.stackTagCompound;
   }

   private NBTTagCompound getOldTagCompound(ItemStack stack, World world) {
      try {
         File e = world.getSaveHandler().getMapFileFromName("plane_" + stack.getItemDamage());
         if(e != null && e.exists()) {
            FileInputStream fileinputstream = new FileInputStream(e);
            NBTTagCompound tags = CompressedStreamTools.readCompressed(fileinputstream).getCompoundTag("data");
            EnumDriveablePart[] var6 = EnumDriveablePart.values();
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
               EnumDriveablePart part = var6[var8];
               tags.setInteger(part.getShortName() + "_Health", this.type.health.get(part) == null?0:((CollisionBox)this.type.health.get(part)).health);
               tags.setBoolean(part.getShortName() + "_Fire", false);
            }

            fileinputstream.close();
            return tags;
         }
      } catch (IOException var10) {
         FlansMod.log("Failed to read old vehicle file");
         var10.printStackTrace();
      }

      return null;
   }

   public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean advancedTooltips) {
      if(!this.type.packName.isEmpty()) {
         lines.add(this.type.packName);
      }

      if(this.type.description != null) {
         Collections.addAll(lines, this.type.description.split("_"));
      }

      NBTTagCompound tags = this.getTagCompound(stack, player.worldObj);
      String engineName = tags.getString("Engine");
      PartType part = PartType.getPart(engineName);
      if(part != null) {
         lines.add(part.name);
      }

   }

   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
      float cosYaw = MathHelper.cos(-entityplayer.rotationYaw * 0.01745329F - 3.141593F);
      float sinYaw = MathHelper.sin(-entityplayer.rotationYaw * 0.01745329F - 3.141593F);
      float cosPitch = -MathHelper.cos(-entityplayer.rotationPitch * 0.01745329F);
      float sinPitch = MathHelper.sin(-entityplayer.rotationPitch * 0.01745329F);
      double length = 5.0D;
      Vec3 posVec = Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY + 1.62D - (double)entityplayer.yOffset, entityplayer.posZ);
      Vec3 lookVec = posVec.addVector((double)(sinYaw * cosPitch) * length, (double)sinPitch * length, (double)(cosYaw * cosPitch) * length);
      MovingObjectPosition movingobjectposition = world.rayTraceBlocks(posVec, lookVec, this.type.placeableOnWater);
      if(movingobjectposition == null) {
         return itemstack;
      } else {
         if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK) {
            int i = movingobjectposition.blockX;
            int j = movingobjectposition.blockY;
            int k = movingobjectposition.blockZ;
            Block block = world.getBlock(i, j, k);
            DriveableData data;
            if(this.type.placeableOnLand || block instanceof BlockLiquid) {
               if(!world.isRemote) {
                  data = this.getPlaneData(itemstack, world);
                  if(data != null) {
                     world.spawnEntityInWorld(new EntityPlane(world, (double)i + 0.5D, (double)j + 2.5D, (double)k + 0.5D, entityplayer, this.type, data));
                  }
               }

               if(!entityplayer.capabilities.isCreativeMode) {
                  --itemstack.stackSize;
               }
            }

            if(!this.type.placeableOnLand && this.type.placeableOnSponge && block instanceof BlockSponge) {
               if(!world.isRemote) {
                  data = this.getPlaneData(itemstack, world);
                  if(data != null) {
                     world.spawnEntityInWorld(new EntityPlane(world, (double)i + 0.5D, (double)j + 2.5D, (double)k + 0.5D, entityplayer, this.type, data));
                  }
               }

               if(!entityplayer.capabilities.isCreativeMode) {
                  --itemstack.stackSize;
               }
            }
         }

         return itemstack;
      }
   }

   public Entity spawnPlane(World world, double x, double y, double z, ItemStack stack) {
      DriveableData data = this.getPlaneData(stack, world);
      if(data != null) {
         EntityPlane entity = new EntityPlane(world, x, y, z, this.type, data);
         if(!world.isRemote) {
            world.spawnEntityInWorld(entity);
         }

         return entity;
      } else {
         return null;
      }
   }

   public DriveableData getPlaneData(ItemStack itemstack, World world) {
      return new DriveableData(this.getTagCompound(itemstack, world), itemstack.getItemDamage());
   }

   @SideOnly(Side.CLIENT)
   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
      return this.type.colour;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister icon) {
      this.icons = new IIcon[this.type.paintjobs.size()];
      super.itemIcon = icon.registerIcon("FlansMod:" + this.type.iconPath);

      for(int i = 0; i < this.type.paintjobs.size(); ++i) {
         this.icons[i] = icon.registerIcon("FlansMod:" + ((Paintjob)this.type.paintjobs.get(i)).iconName);
      }

   }

   @SideOnly(Side.CLIENT)
   public IIcon getIconIndex(ItemStack stack) {
      return this.icons[stack.getItemDamage()];
   }

   public void getSubItems(Item item, CreativeTabs tabs, List list) {
      ItemStack planeStack = new ItemStack(item, 1, 0);
      NBTTagCompound tags = new NBTTagCompound();
      tags.setString("Type", this.type.shortName);
      if(PartType.defaultEngines.containsKey(EnumType.plane)) {
         tags.setString("Engine", ((PartType)PartType.defaultEngines.get(EnumType.plane)).shortName);
      }

      EnumDriveablePart[] var6 = EnumDriveablePart.values();
      int var7 = var6.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         EnumDriveablePart part = var6[var8];
         tags.setInteger(part.getShortName() + "_Health", this.type.health.get(part) == null?0:((CollisionBox)this.type.health.get(part)).health);
         tags.setBoolean(part.getShortName() + "_Fire", false);
      }

      planeStack.stackTagCompound = tags;
      list.add(planeStack);
   }

   public InfoType getInfoType() {
      return this.type;
   }

   public PaintableType GetPaintableType() {
      return this.type;
   }
}
