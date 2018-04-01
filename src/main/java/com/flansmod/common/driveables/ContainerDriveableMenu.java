package com.flansmod.common.driveables;

import com.flansmod.common.driveables.EntityDriveable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerDriveableMenu extends Container {

   public EntityDriveable plane;
   public boolean isFuel;
   public InventoryPlayer inventory;
   public World world;


   public ContainerDriveableMenu(InventoryPlayer inventoryplayer, World worldy) {
      this(inventoryplayer, worldy, false, (EntityDriveable)null);
   }

   public ContainerDriveableMenu(InventoryPlayer inventoryplayer, World worldy, boolean fuel, EntityDriveable planey) {
      this.inventory = inventoryplayer;
      this.world = worldy;
      this.plane = planey;
      this.isFuel = fuel;
      if(this.isFuel) {
         this.addSlotToContainer(new Slot(this.plane.driveableData, this.plane.driveableData.getFuelSlot(), 35, 44));
      }

      int col;
      for(col = 0; col < 3; ++col) {
         for(int col1 = 0; col1 < 9; ++col1) {
            this.addSlotToContainer(new Slot(inventoryplayer, col1 + col * 9 + 9, 8 + col1 * 18, 79 + (this.isFuel?0:19) + col * 18));
         }
      }

      for(col = 0; col < 9; ++col) {
         this.addSlotToContainer(new Slot(inventoryplayer, col, 8 + col * 18, 137 + (this.isFuel?0:19)));
      }

   }

   public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
      ItemStack stack = null;
      Slot currentSlot = (Slot)super.inventorySlots.get(slotID);
      if(currentSlot != null && currentSlot.getHasStack()) {
         ItemStack slotStack = currentSlot.getStack();
         stack = slotStack.copy();
         if(slotID != 0) {
            if(!this.mergeItemStack(slotStack, 0, 1, false)) {
               return null;
            }
         } else if(!this.mergeItemStack(slotStack, 1, super.inventorySlots.size(), true)) {
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

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return true;
   }
}
