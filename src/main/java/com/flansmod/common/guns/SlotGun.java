package com.flansmod.common.guns;

import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.guns.EnumAttachmentType;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemAttachment;
import com.flansmod.common.guns.ItemGun;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGun extends Slot {

   private int slotID;
   private SlotGun gunSlot;


   public SlotGun(IInventory inventory, int i, int x, int y, SlotGun s) {
      super(inventory, i, x, y);
      this.slotID = i;
      this.gunSlot = s;
   }

   public boolean isItemValid(ItemStack stack) {
      switch(this.slotID) {
      case 0:
         return stack == null || stack.getItem() instanceof ItemGun && !((ItemGun)stack.getItem()).type.deployable && stack.getTagCompound() != null;
      case 1:
         return stack == null || this.canAttachToCurrentGun(stack) && ((ItemAttachment)stack.getItem()).type.type == EnumAttachmentType.barrel;
      case 2:
         return stack == null || this.canAttachToCurrentGun(stack) && ((ItemAttachment)stack.getItem()).type.type == EnumAttachmentType.sights;
      case 3:
         return stack == null || this.canAttachToCurrentGun(stack) && ((ItemAttachment)stack.getItem()).type.type == EnumAttachmentType.stock;
      case 4:
         return stack == null || this.canAttachToCurrentGun(stack) && ((ItemAttachment)stack.getItem()).type.type == EnumAttachmentType.grip;
      case 5:
         return stack == null || this.canAttachToCurrentGun(stack) && ((ItemAttachment)stack.getItem()).type.type == EnumAttachmentType.gadget;
      case 6:
         return stack == null || this.canAttachToCurrentGun(stack) && ((ItemAttachment)stack.getItem()).type.type == EnumAttachmentType.slide;
      case 7:
         return stack == null || this.canAttachToCurrentGun(stack) && ((ItemAttachment)stack.getItem()).type.type == EnumAttachmentType.pump;
      case 8:
         return stack == null || this.canAttachToCurrentGun(stack) && ((ItemAttachment)stack.getItem()).type.type == EnumAttachmentType.accessory;
      default:
         return stack == null || this.canAttachToCurrentGun(stack) && ((ItemAttachment)stack.getItem()).type.type == EnumAttachmentType.generic;
      }
   }

   public boolean canAttachToCurrentGun(ItemStack stack) {
      if(stack != null && stack.getItem() instanceof ItemAttachment && this.gunSlot.getHasStack() && this.gunSlot.getStack().getItem() instanceof ItemGun) {
         AttachmentType attachmentType = ((ItemAttachment)stack.getItem()).type;
         GunType gunType = ((ItemGun)this.gunSlot.getStack().getItem()).type;
         return gunType.allowAllAttachments || gunType.allowedAttachments.contains(attachmentType);
      } else {
         return false;
      }
   }
}
