package com.flansmod.common.guns;

import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.InventoryGunModTable;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.SlotGun;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerGunModTable extends Container {

   private InventoryGunModTable inventory;
   public InventoryPlayer playerInv;
   public World world;


   public ContainerGunModTable(InventoryPlayer i, World w) {
      this.playerInv = i;
      this.inventory = new InventoryGunModTable();
      this.world = w;
      SlotGun gunSlot = new SlotGun(this.inventory, 0, 184, 37, (SlotGun)null);
      this.addSlotToContainer(gunSlot);

      int col;
      for(col = 0; col < 8; ++col) {
         this.addSlotToContainer(new SlotGun(this.inventory, col + 1, 17 + col * 18, 89, gunSlot));
      }

      for(col = 0; col < 8; ++col) {
         this.addSlotToContainer(new SlotGun(this.inventory, 9 + col, 17 + col * 18, 115 + col * 18, gunSlot));
      }

      for(col = 0; col < 3; ++col) {
         for(int col1 = 0; col1 < 9; ++col1) {
            this.addSlotToContainer(new Slot(this.playerInv, col1 + col * 9 + 9, 8 + col1 * 18, 154 + col * 18));
         }
      }

      for(col = 0; col < 9; ++col) {
         this.addSlotToContainer(new Slot(this.playerInv, col, 8 + col * 18, 212));
      }

   }

   public void onContainerClosed(EntityPlayer player) {
      if(this.inventory.getStackInSlot(0) != null) {
         player.dropPlayerItemWithRandomChoice(this.inventory.getStackInSlot(0), false);
      }

   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return true;
   }

   public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
      ItemStack stack = null;
      Slot currentSlot = (Slot)super.inventorySlots.get(slotID);
      if(currentSlot != null && currentSlot.getHasStack()) {
         ItemStack slotStack = currentSlot.getStack();
         stack = slotStack.copy();
         if(slotID >= 17) {
            return null;
         }

         if(!this.mergeItemStack(slotStack, 17, super.inventorySlots.size(), true)) {
            return null;
         }

         if(slotStack.stackSize == 0) {
            currentSlot.putStack((ItemStack)null);
         } else {
            currentSlot.onSlotChanged();
         }

         if(slotStack.stackSize == stack.stackSize) {
            return null;
         }

         currentSlot.onPickupFromSlot(player, slotStack);
      }

      return stack;
   }

   public void pressButton(boolean paint, boolean left) {}

   public void clickPaintjob(int id) {
      ItemStack gunStack = this.inventory.getStackInSlot(0);
      if(gunStack != null && gunStack.getItem() instanceof ItemGun) {
         GunType gunType = ((ItemGun)gunStack.getItem()).type;
         this.clickPaintjob(gunType.getPaintjob(id));
      }

   }

   public void clickPaintjob(com.flansmod.common.paintjob.Paintjob paintjob) {
      ItemStack gunStack = this.inventory.getStackInSlot(0);
      if(gunStack != null && gunStack.getItem() instanceof ItemGun) {
         GunType gunType = ((ItemGun)gunStack.getItem()).type;
         int numDyes = paintjob.dyesNeeded.length;
         if(!this.playerInv.player.capabilities.isCreativeMode) {
            int n;
            int amountNeeded;
            int s;
            ItemStack stack;
            for(n = 0; n < numDyes; ++n) {
               amountNeeded = paintjob.dyesNeeded[n].stackSize;

               for(s = 0; s < this.playerInv.getSizeInventory(); ++s) {
                  stack = this.playerInv.getStackInSlot(s);
                  if(stack != null && stack.getItem() == Items.dye && stack.getItemDamage() == paintjob.dyesNeeded[n].getItemDamage()) {
                     amountNeeded -= stack.stackSize;
                  }
               }

               if(amountNeeded > 0) {
                  return;
               }
            }

            for(n = 0; n < numDyes; ++n) {
               amountNeeded = paintjob.dyesNeeded[n].stackSize;

               for(s = 0; s < this.playerInv.getSizeInventory(); ++s) {
                  if(amountNeeded > 0) {
                     stack = this.playerInv.getStackInSlot(s);
                     if(stack != null && stack.getItem() == Items.dye && stack.getItemDamage() == paintjob.dyesNeeded[n].getItemDamage()) {
                        ItemStack consumed = this.playerInv.decrStackSize(s, amountNeeded);
                        amountNeeded -= consumed.stackSize;
                     }
                  }
               }
            }
         }

         gunStack.setItemDamage(paintjob.ID);
      }

   }
}
