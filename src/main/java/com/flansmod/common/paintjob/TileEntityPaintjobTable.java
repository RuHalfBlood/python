package com.flansmod.common.paintjob;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPaintjobTable extends TileEntity implements IInventory, IUpdatePlayerListBox {

   private ItemStack[] inventoryStacks = new ItemStack[2];


   public int getSizeInventory() {
      return 2;
   }

   public ItemStack getStackInSlot(int index) {
      return this.inventoryStacks[index];
   }

   public ItemStack decrStackSize(int index, int count) {
      if(this.getStackInSlot(index) != null) {
         ItemStack returnStack;
         if(count >= this.getStackInSlot(index).stackSize) {
            returnStack = this.getStackInSlot(index);
            this.setInventorySlotContents(index, (ItemStack)null);
            return returnStack;
         } else {
            returnStack = this.getStackInSlot(index).splitStack(count);
            return returnStack;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int index) {
      ItemStack returnStack = this.getStackInSlot(index);
      this.setInventorySlotContents(index, (ItemStack)null);
      return returnStack;
   }

   public void setInventorySlotContents(int index, ItemStack stack) {
      this.inventoryStacks[index] = stack;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public boolean isUseableByPlayer(EntityPlayer player) {
      return true;
   }

   public boolean isItemValidForSlot(int index, ItemStack stack) {
      return true;
   }

   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);

      for(int i = 0; i < this.inventoryStacks.length; ++i) {
         NBTTagCompound stackNBT = new NBTTagCompound();
         if(this.getStackInSlot(i) != null) {
            this.getStackInSlot(i).writeToNBT(stackNBT);
         }

         nbt.setTag("stack_" + i, stackNBT);
      }

   }

   public void readFromNBT(NBTTagCompound nbt) {
      super.readFromNBT(nbt);

      for(int i = 0; i < this.inventoryStacks.length; ++i) {
         this.setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("stack_" + i)));
      }

   }

   public void update() {}

   public Packet getDescriptionPacket() {
      NBTTagCompound nbt = new NBTTagCompound();
      this.writeToNBT(nbt);
      return new S35PacketUpdateTileEntity(super.xCoord, super.yCoord, super.zCoord, this.getBlockMetadata(), nbt);
   }

   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
      this.readFromNBT(packet.func_148857_g());
   }

   public ItemStack getPaintableStack() {
      return this.inventoryStacks[0];
   }

   public void setPaintableStack(ItemStack stack) {
      this.inventoryStacks[0] = stack;
   }

   public ItemStack getPaintCans() {
      return this.inventoryStacks[1];
   }

   public String getInventoryName() {
      return "PaintjobTable";
   }

   public boolean hasCustomInventoryName() {
      return false;
   }

   public void openInventory() {}

   public void closeInventory() {}
}
