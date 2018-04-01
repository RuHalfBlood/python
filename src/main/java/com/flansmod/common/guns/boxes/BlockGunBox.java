package com.flansmod.common.guns.boxes;

import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.boxes.GunBoxType;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockGunBox extends Block {

   public GunBoxType type;


   public BlockGunBox(GunBoxType t) throws Exception {
      super(Material.wood);
      this.setHardness(2.0F);
      this.setResistance(4.0F);
      this.type = t;
      this.setBlockName(this.type.shortName);
      Block block = Block.getBlockFromName("flansmod:gunBox." + this.type.shortName);
      if(block != null) {
         throw new Exception("Caught an exception during block registration");
      } else {
         GameRegistry.registerBlock(this, "gunBox." + this.type.shortName);
         this.setCreativeTab(FlansMod.tabFlanGuns);
         this.type.block = this;
         this.type.item = Item.getItemFromBlock(this);
      }
   }

   public void buyGun(int i, InventoryPlayer inventory, GunBoxType type) {
      if(FMLCommonHandler.instance().getEffectiveSide().isClient()) {
         FlansMod.proxy.buyGun(type, i);
      }

      if(i <= type.numGuns && type.guns[i] != null) {
         boolean canBuy = true;
         Iterator gunStack = type.gunParts[i].iterator();

         ItemStack gunType;
         int tags;
         int ammoTagsList;
         ItemStack j;
         while(gunStack.hasNext()) {
            gunType = (ItemStack)gunStack.next();
            tags = 0;

            for(ammoTagsList = 0; ammoTagsList < inventory.getSizeInventory(); ++ammoTagsList) {
               j = inventory.getStackInSlot(ammoTagsList);
               if(j != null && j.getItem() == gunType.getItem() && j.getItemDamage() == gunType.getItemDamage()) {
                  tags += j.stackSize;
               }
            }

            if(tags < gunType.stackSize) {
               canBuy = false;
            }
         }

         if(canBuy) {
            gunStack = type.gunParts[i].iterator();

            while(gunStack.hasNext()) {
               gunType = (ItemStack)gunStack.next();
               tags = gunType.stackSize;

               for(ammoTagsList = 0; ammoTagsList < inventory.getSizeInventory(); ++ammoTagsList) {
                  j = inventory.getStackInSlot(ammoTagsList);
                  if(tags > 0 && j != null && j.getItem() == gunType.getItem() && j.getItemDamage() == gunType.getItemDamage()) {
                     tags -= inventory.decrStackSize(ammoTagsList, tags).stackSize;
                  }
               }
            }

            ItemStack var10 = new ItemStack(type.guns[i].getItem());
            if(type.guns[i] instanceof GunType) {
               GunType var11 = (GunType)type.guns[i];
               NBTTagCompound var12 = new NBTTagCompound();
               var12.setString("Paint", var11.defaultPaintjob.iconName);
               NBTTagList var13 = new NBTTagList();

               for(int var14 = 0; var14 < var11.numAmmoItemsInGun; ++var14) {
                  var13.appendTag(new NBTTagCompound());
               }

               var12.setTag("ammo", var13);
               var10.stackTagCompound = var12;
            }

            if(!inventory.addItemStackToInventory(var10)) {
               inventory.player.dropPlayerItemWithRandomChoice(var10, false);
            }
         }
      }

   }

   public void buyAmmo(int i, InventoryPlayer inventory, GunBoxType type) {
      if(FMLCommonHandler.instance().getEffectiveSide().isClient()) {
         FlansMod.proxy.buyAmmo(type, i, 1);
      }

      if(i <= type.numGuns && type.bulletParts[i] != null) {
         boolean canBuy = true;
         Iterator var5 = type.bulletParts[i].iterator();

         ItemStack remove;
         int amountLeft;
         int j;
         ItemStack stack;
         while(var5.hasNext()) {
            remove = (ItemStack)var5.next();
            amountLeft = 0;

            for(j = 0; j < inventory.getSizeInventory(); ++j) {
               stack = inventory.getStackInSlot(j);
               if(stack != null && stack.getItem() == remove.getItem() && stack.getItemDamage() == remove.getItemDamage()) {
                  amountLeft += stack.stackSize;
               }
            }

            if(amountLeft < remove.stackSize) {
               canBuy = false;
            }
         }

         if(canBuy) {
            var5 = type.bulletParts[i].iterator();

            while(var5.hasNext()) {
               remove = (ItemStack)var5.next();
               amountLeft = remove.stackSize;

               for(j = 0; j < inventory.getSizeInventory(); ++j) {
                  stack = inventory.getStackInSlot(j);
                  if(amountLeft > 0 && stack != null && stack.getItem() == remove.getItem() && stack.getItemDamage() == remove.getItemDamage()) {
                     amountLeft -= inventory.decrStackSize(j, amountLeft).stackSize;
                  }
               }
            }

            if(!inventory.addItemStackToInventory(new ItemStack(type.bullets[i].getItem()))) {
               ;
            }
         }
      }

   }

   public void buyAltAmmo(int i, InventoryPlayer inventory, GunBoxType type) {
      if(FMLCommonHandler.instance().getEffectiveSide().isClient()) {
         FlansMod.proxy.buyAmmo(type, i, 2);
      }

      if(i <= type.numGuns && type.altBulletParts[i] != null) {
         boolean canBuy = true;
         Iterator var5 = type.altBulletParts[i].iterator();

         ItemStack remove;
         int amountLeft;
         int j;
         ItemStack stack;
         while(var5.hasNext()) {
            remove = (ItemStack)var5.next();
            amountLeft = 0;

            for(j = 0; j < inventory.getSizeInventory(); ++j) {
               stack = inventory.getStackInSlot(j);
               if(stack != null && stack.getItem() == remove.getItem() && stack.getItemDamage() == remove.getItemDamage()) {
                  amountLeft += stack.stackSize;
               }
            }

            if(amountLeft < remove.stackSize) {
               canBuy = false;
            }
         }

         if(canBuy) {
            var5 = type.altBulletParts[i].iterator();

            while(var5.hasNext()) {
               remove = (ItemStack)var5.next();
               amountLeft = remove.stackSize;

               for(j = 0; j < inventory.getSizeInventory(); ++j) {
                  stack = inventory.getStackInSlot(j);
                  if(amountLeft > 0 && stack != null && stack.getItem() == remove.getItem() && stack.getItemDamage() == remove.getItemDamage()) {
                     amountLeft -= inventory.decrStackSize(j, amountLeft).stackSize;
                  }
               }
            }

            if(!inventory.addItemStackToInventory(new ItemStack(type.altBullets[i].getItem()))) {
               ;
            }
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int metadata) {
      return this.type == null?null:(side == 1?this.type.top:(side == 0?this.type.bottom:this.type.side));
   }

   public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
      if(entityplayer.isSneaking()) {
         return false;
      } else {
         entityplayer.openGui(FlansMod.INSTANCE, 5, world, i, j, k);
         return true;
      }
   }

   public Block purchaseItem(int i, int id, InventoryPlayer inventory, GunBoxType type) {
      switch(i) {
      case 0:
         this.buyGun(id, inventory, type);
         break;
      case 1:
         this.buyAmmo(id, inventory, type);
         break;
      case 2:
         this.buyAltAmmo(id, inventory, type);
      }

      return this;
   }

   public ArrayList getDrops(World world, int x, int y, int z, int metadata, int fortune) {
      ArrayList ret = new ArrayList();
      ret.add(new ItemStack(this, 1, 0));
      return ret;
   }

   public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
      super.breakBlock(world, x, y, z, block, metadata);
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister register) {
      this.type.top = register.registerIcon("FlansMod:" + this.type.topTexturePath);
      this.type.side = register.registerIcon("FlansMod:" + this.type.sideTexturePath);
      this.type.bottom = register.registerIcon("FlansMod:" + this.type.bottomTexturePath);
   }
}
