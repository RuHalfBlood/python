package com.flansmod.common.guns;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class InventoryHelper {

   public static boolean addItemStackToInventory(IInventory inventory, ItemStack stack, boolean creative) {
      if(stack == null) {
         return false;
      } else if(stack.stackSize == 0) {
         return false;
      } else {
         try {
            int throwable;
            if(stack.isItemDamaged()) {
               throwable = getFirstEmptyStack(inventory);
               if(throwable >= 0) {
                  ItemStack stackToAdd = ItemStack.copyItemStack(stack);
                  stackToAdd.animationsToGo = 5;
                  inventory.setInventorySlotContents(throwable, stackToAdd);
                  stack.stackSize = 0;
                  return true;
               } else if(creative) {
                  stack.stackSize = 0;
                  return true;
               } else {
                  return false;
               }
            } else {
               do {
                  throwable = stack.stackSize;
                  stack.stackSize = storePartialItemStack(inventory, stack);
               } while(stack.stackSize > 0 && stack.stackSize < throwable);

               if(stack.stackSize == throwable && creative) {
                  stack.stackSize = 0;
                  return true;
               } else {
                  return stack.stackSize < throwable;
               }
            }
         } catch (Throwable var5) {
            var5.printStackTrace();
            return false;
         }
      }
   }

   public static int storeItemStack(IInventory inventory, ItemStack stack) {
      for(int i = 0; i < inventory.getSizeInventory(); ++i) {
         ItemStack oldStack = inventory.getStackInSlot(i);
         if(oldStack != null && oldStack.getItem() == stack.getItem() && oldStack.isStackable() && oldStack.stackSize < oldStack.getMaxStackSize() && oldStack.stackSize < inventory.getInventoryStackLimit() && (!oldStack.getHasSubtypes() || oldStack.getItemDamage() == stack.getItemDamage()) && ItemStack.areItemStackTagsEqual(oldStack, stack)) {
            return i;
         }
      }

      return -1;
   }

   public static int storePartialItemStack(IInventory inventory, ItemStack stack) {
      Item item = stack.getItem();
      int j = stack.stackSize;
      int k;
      if(stack.getMaxStackSize() == 1) {
         k = getFirstEmptyStack(inventory);
         if(k < 0) {
            return j;
         } else {
            if(inventory.getStackInSlot(k) == null) {
               inventory.setInventorySlotContents(k, ItemStack.copyItemStack(stack));
            }

            return 0;
         }
      } else {
         k = storeItemStack(inventory, stack);
         if(k < 0) {
            k = getFirstEmptyStack(inventory);
         }

         if(k < 0) {
            return j;
         } else {
            ItemStack oldStack = inventory.getStackInSlot(k);
            if(oldStack == null) {
               oldStack = new ItemStack(item, 0, stack.getItemDamage());
               if(stack.hasTagCompound()) {
                  oldStack.setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
               }

               inventory.setInventorySlotContents(k, oldStack);
            }

            int l = j;
            if(j > oldStack.getMaxStackSize() - oldStack.stackSize) {
               l = oldStack.getMaxStackSize() - oldStack.stackSize;
            }

            if(l > inventory.getInventoryStackLimit() - oldStack.stackSize) {
               l = inventory.getInventoryStackLimit() - oldStack.stackSize;
            }

            if(l == 0) {
               return j;
            } else {
               j -= l;
               oldStack.stackSize += l;
               oldStack.animationsToGo = 5;
               return j;
            }
         }
      }
   }

   public static int getFirstEmptyStack(IInventory inventory) {
      for(int i = 0; i < inventory.getSizeInventory(); ++i) {
         if(inventory.getStackInSlot(i) == null) {
            return i;
         }
      }

      return -1;
   }

   public static void dropInventoryItems(World worldIn, int x, int y, int z, IInventory tileentity) {}
}
