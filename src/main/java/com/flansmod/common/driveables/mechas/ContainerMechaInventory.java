package com.flansmod.common.driveables.mechas;

import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.driveables.mechas.EnumMechaSlotType;
import com.flansmod.common.driveables.mechas.SlotMecha;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerMechaInventory extends Container {

   public InventoryPlayer inventory;
   public World world;
   public EntityMecha mecha;
   public int numItems;
   public int maxScroll;
   public int scroll;


   public ContainerMechaInventory(InventoryPlayer inv, World w, EntityMecha em) {
      this.inventory = inv;
      this.world = w;
      this.mecha = em;
      this.numItems = this.mecha.getDriveableType().numCargoSlots;
      int numRows = (this.numItems + 7) / 8;
      this.maxScroll = numRows > 3?numRows - 3:0;
      int startSlot = this.mecha.driveableData.getCargoInventoryStart();

      int col;
      int col1;
      for(col = 0; col < numRows; ++col) {
         col1 = -1000;
         if(col < 3 + this.scroll && col >= this.scroll) {
            col1 = 25 + 19 * (col - this.scroll);
         }

         for(int col2 = 0; col2 < ((col + this.scroll + 1) * 8 <= this.numItems?8:this.numItems % 8); ++col2) {
            this.addSlotToContainer(new Slot(this.mecha.driveableData, startSlot + col * 8 + col2, 186 + 18 * col2, col1));
         }
      }

      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.legs, 84, 128));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.hips, 60, 128));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.leftArm, 36, 80));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.leftTool, 36, 56));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.leftShoulder, 60, 32));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.head, 84, 32));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.feet, 108, 128));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.rightArm, 132, 80));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.rightTool, 132, 56));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.rightShoulder, 108, 32));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.u1, 10, 32));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.u2, 10, 56));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.u3, 10, 80));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.u4, 10, 104));
      this.addSlotToContainer(new SlotMecha(this.mecha.inventory, EnumMechaSlotType.u5, 10, 128));

      for(col = 0; col < 3; ++col) {
         for(col1 = 0; col1 < 9; ++col1) {
            this.addSlotToContainer(new Slot(this.inventory, col1 + col * 9 + 9, 182 + col1 * 18, 98 + col * 18));
         }
      }

      for(col = 0; col < 9; ++col) {
         this.addSlotToContainer(new Slot(this.inventory, col, 182 + col * 18, 156));
      }

   }

   public void onContainerClosed(EntityPlayer par1EntityPlayer) {
      super.onContainerClosed(par1EntityPlayer);
      this.mecha.couldNotFindFuel = false;
   }

   public void updateScroll(int scrololol) {
      this.scroll = scrololol;
      int m = (this.numItems + 7) / 8;

      for(int row = 0; row < m; ++row) {
         int yPos = -1000;
         if(row < 3 + this.scroll && row >= this.scroll) {
            yPos = 25 + 19 * (row - this.scroll);
         }

         for(int col = 0; col < ((row + 1) * 8 < this.numItems?8:this.numItems % 8); ++col) {
            ((Slot)super.inventorySlots.get(row * 8 + col)).yDisplayPosition = yPos;
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
