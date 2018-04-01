package com.flansmod.common.paintjob;

import com.flansmod.common.paintjob.IPaintableItem;
import com.flansmod.common.paintjob.PaintableType;
import com.flansmod.common.paintjob.Paintjob;
import com.flansmod.common.paintjob.TileEntityPaintjobTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerPaintjobTable extends Container {

   public InventoryPlayer playerInv;
   public TileEntityPaintjobTable table;
   public World world;


   public ContainerPaintjobTable(InventoryPlayer i, World w, TileEntityPaintjobTable te) {
      this.playerInv = i;
      this.world = w;
      this.table = te;
      this.addSlotToContainer(new Slot(this.table, 0, 187, 139));
      this.addSlotToContainer(new Slot(this.table, 1, 187, 193));

      int col;
      for(col = 0; col < 3; ++col) {
         for(int col1 = 0; col1 < 9; ++col1) {
            this.addSlotToContainer(new Slot(this.playerInv, col1 + col * 9 + 9, 8 + col1 * 18, 184 + col * 18));
         }
      }

      for(col = 0; col < 9; ++col) {
         this.addSlotToContainer(new Slot(this.playerInv, col, 8 + col * 18, 242));
      }

   }

   public void onContainerClosed(EntityPlayer player) {}

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return true;
   }

   public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
      ItemStack stack = null;
      Slot currentSlot = (Slot)super.inventorySlots.get(slotID);
      if(currentSlot != null && currentSlot.getHasStack()) {
         ItemStack slotStack = currentSlot.getStack();
         stack = slotStack.copy();
         if(slotID >= 1) {
            return null;
         }

         if(!this.mergeItemStack(slotStack, 1, super.inventorySlots.size(), true)) {
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

   public void clickPaintjob(int i) {
      ItemStack paintableStack = this.table.getPaintableStack();
      if(paintableStack != null && paintableStack.getItem() instanceof IPaintableItem) {
         PaintableType paintableType = ((IPaintableItem)paintableStack.getItem()).GetPaintableType();
         this.clickPaintjob(paintableType.getPaintjob(i));
      }

   }

   public void clickPaintjob(Paintjob paintjob) {
      ItemStack paintableStack = this.table.getPaintableStack();
      if(paintableStack != null && paintableStack.getItem() instanceof IPaintableItem) {
         PaintableType paintableType = ((IPaintableItem)paintableStack.getItem()).GetPaintableType();
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

         paintableStack.setItemDamage(paintjob.ID);
      }

   }
}
