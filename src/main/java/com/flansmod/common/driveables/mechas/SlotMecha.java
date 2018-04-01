package com.flansmod.common.driveables.mechas;

import com.flansmod.common.driveables.mechas.EnumMechaItemType;
import com.flansmod.common.driveables.mechas.EnumMechaSlotType;
import com.flansmod.common.driveables.mechas.ItemMechaAddon;
import com.flansmod.common.guns.ItemGun;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotMecha extends Slot {

   private EnumMechaSlotType slotType;


   public SlotMecha(IInventory inv, EnumMechaSlotType e, int x, int y) {
      super(inv, e.ordinal(), x, y);
      this.slotType = e;
   }

   public boolean isItemValid(ItemStack stack) {
      if(stack != null && stack.getItem() != null) {
         EnumMechaItemType itemType = null;
         Item item = stack.getItem();
         if(item instanceof ItemGun && ((ItemGun)item).type.usableByMechas) {
            itemType = EnumMechaItemType.tool;
         } else {
            if(!(item instanceof ItemMechaAddon)) {
               return false;
            }

            itemType = ((ItemMechaAddon)item).type.type;
         }

         return this.slotType.accepts(itemType);
      } else {
         return true;
      }
   }

   public void putStack(ItemStack stack) {
      if(this.isItemValid(stack)) {
         super.inventory.setInventorySlotContents(this.slotType.ordinal(), stack);
         this.onSlotChanged();
      }
   }
}
