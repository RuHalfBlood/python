package com.flansmod.common.driveables;

import com.flansmod.common.driveables.EntityDriveable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerDriveableInventory extends Container {

   public InventoryPlayer inventory;
   public World world;
   public EntityDriveable plane;
   public int numItems;
   public int screen;
   public int maxScroll;
   public int scroll;


   public ContainerDriveableInventory(InventoryPlayer inventoryplayer, World worldy, EntityDriveable entPlane, int i) {
      this.inventory = inventoryplayer;
      this.world = worldy;
      this.plane = entPlane;
      this.screen = i;
      this.numItems = 0;
      switch(i) {
      case 0:
         this.numItems = this.plane.driveableData.numGuns;
         this.maxScroll = this.numItems > 3?this.numItems - 3:0;
         break;
      case 1:
         this.numItems = this.plane.getDriveableType().numBombSlots;
         this.maxScroll = (this.numItems + 7) / 8 > 3?(this.numItems + 7) / 8 - 3:0;
         break;
      case 2:
         this.numItems = this.plane.getDriveableType().numCargoSlots;
         this.maxScroll = (this.numItems + 7) / 8 > 3?(this.numItems + 7) / 8 - 3:0;
         break;
      case 3:
         this.numItems = this.plane.getDriveableType().numMissileSlots;
         this.maxScroll = (this.numItems + 7) / 8 > 3?(this.numItems + 7) / 8 - 3:0;
      }

      int col;
      int col1;
      int row;
      label108:
      switch(this.screen) {
      case 0:
         col = 0;
         col1 = 0;

         while(true) {
            if(col1 >= this.plane.driveableData.numGuns) {
               break label108;
            }

            row = -1000;
            if(col < 3 + this.scroll && col >= this.scroll) {
               row = 25 + 19 * col;
            }

            this.addSlotToContainer(new Slot(this.plane.driveableData, col1, 29, row));
            ++col;
            ++col1;
         }
      case 1:
      case 2:
      case 3:
         col = this.plane.driveableData.getBombInventoryStart();
         if(this.screen == 2) {
            col = this.plane.driveableData.getCargoInventoryStart();
         }

         if(this.screen == 3) {
            col = this.plane.driveableData.getMissileInventoryStart();
         }

         col1 = (this.numItems + 7) / 8;

         for(row = 0; row < col1; ++row) {
            int yPos = -1000;
            if(row < 3 + this.scroll && row >= this.scroll) {
               yPos = 25 + 19 * (row - this.scroll);
            }

            for(int col2 = 0; col2 < ((row + this.scroll + 1) * 8 <= this.numItems?8:this.numItems % 8); ++col2) {
               this.addSlotToContainer(new Slot(this.plane.driveableData, col + row * 8 + col2, 10 + 18 * col2, yPos));
            }
         }
      }

      for(col = 0; col < 3; ++col) {
         for(col1 = 0; col1 < 9; ++col1) {
            this.addSlotToContainer(new Slot(inventoryplayer, col1 + col * 9 + 9, 8 + col1 * 18, 98 + col * 18));
         }
      }

      for(col = 0; col < 9; ++col) {
         this.addSlotToContainer(new Slot(inventoryplayer, col, 8 + col * 18, 156));
      }

   }

   public void updateScroll(int scrololol) {
      this.scroll = scrololol;
      int m;
      int row;
      int yPos;
      switch(this.screen) {
      case 0:
         m = 0;

         for(row = 0; row < this.plane.driveableData.numGuns; ++row) {
            yPos = -1000;
            if(m < 3 + this.scroll && m >= this.scroll) {
               yPos = 25 + 19 * (m - this.scroll);
            }

            ((Slot)super.inventorySlots.get(m)).yDisplayPosition = yPos;
            ++m;
         }

         return;
      case 1:
      case 2:
      case 3:
         m = (this.numItems + 7) / 8;

         for(row = 0; row < m; ++row) {
            yPos = -1000;
            if(row < 3 + this.scroll && row >= this.scroll) {
               yPos = 25 + 19 * (row - this.scroll);
            }

            for(int col = 0; col < ((row + 1) * 8 <= this.numItems?8:this.numItems % 8); ++col) {
               ((Slot)super.inventorySlots.get(row * 8 + col)).yDisplayPosition = yPos;
            }
         }
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
         if(slotID >= this.numItems) {
            if(!this.mergeItemStack(slotStack, 0, this.numItems, false)) {
               return null;
            }
         } else if(!this.mergeItemStack(slotStack, this.numItems, super.inventorySlots.size(), true)) {
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
}
