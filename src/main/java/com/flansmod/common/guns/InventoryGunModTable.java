package com.flansmod.common.guns;

import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryGunModTable extends InventoryBasic {

   public ItemStack lastGunStack;
   public GunType gunType;
   public int genericScroll = 0;
   private boolean busy = false;


   public InventoryGunModTable() {
      super("Gun Modification Table", true, 17);
   }

   public void markDirty() {
      if(!this.busy) {
         ItemStack gunStack = this.getStackInSlot(0);
         if(gunStack != null && gunStack.getItem() instanceof ItemGun) {
            this.gunType = ((ItemGun)gunStack.getItem()).type;
            String[] tags = new String[]{"barrel", "scope", "stock", "grip", "gadget", "slide", "pump", "accessory"};
            NBTTagCompound gunTags;
            if(gunStack != this.lastGunStack) {
               this.busy = true;
               gunTags = gunStack.stackTagCompound.getCompoundTag("attachments");

               int attachmentTags;
               for(attachmentTags = 0; attachmentTags < 8; ++attachmentTags) {
                  this.setInventorySlotContents(attachmentTags + 1, ItemStack.loadItemStackFromNBT(gunTags.getCompoundTag(tags[attachmentTags])));
               }

               this.genericScroll = 0;

               for(attachmentTags = 0; attachmentTags < Math.min(this.gunType.numGenericAttachmentSlots, 8); ++attachmentTags) {
                  this.setInventorySlotContents(tags.length + attachmentTags + 1, ItemStack.loadItemStackFromNBT(gunTags.getCompoundTag("generic_" + attachmentTags)));
               }

               this.busy = false;
            } else {
               gunTags = new NBTTagCompound();
               gunTags.setTag("ammo", this.getStackInSlot(0).stackTagCompound.getTag("ammo"));
               if(this.getStackInSlot(0).stackTagCompound.getTag("Paint") != null) {
                  gunTags.setTag("Paint", this.getStackInSlot(0).stackTagCompound.getTag("Paint"));
               }

               NBTTagCompound var6 = new NBTTagCompound();

               int i;
               for(i = 0; i < 8; ++i) {
                  this.writeAttachmentTags(var6, this.getStackInSlot(i + 1), tags[i]);
               }

               for(i = 0; i < this.gunType.numGenericAttachmentSlots; ++i) {
                  if(i >= this.genericScroll * 4 && i < this.genericScroll * 4 + 8) {
                     this.writeAttachmentTags(var6, this.getStackInSlot(i - this.genericScroll * 4 + tags.length + 1), "generic_" + i);
                  } else {
                     var6.setTag("generic_" + i, this.getStackInSlot(0).stackTagCompound.getTag("generic_" + i));
                  }
               }

               gunTags.setTag("attachments", var6);
               gunStack.stackTagCompound = gunTags;
            }

            this.lastGunStack = gunStack;
         }
      }
   }

   public void writeAttachmentTags(NBTTagCompound attachmentTags, ItemStack attachmentStack, String attachmentName) {
      NBTTagCompound tags = new NBTTagCompound();
      if(attachmentStack != null) {
         attachmentStack.writeToNBT(tags);
      }

      attachmentTags.setTag(attachmentName, tags);
   }

   public boolean isItemValidForSlot(int i, ItemStack itemstack) {
      return false;
   }
}
