package cofh.api.energy;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemEnergyContainer extends Item implements IEnergyContainerItem {

   protected int capacity;
   protected int maxReceive;
   protected int maxExtract;


   public ItemEnergyContainer() {}

   public ItemEnergyContainer(int capacity) {
      this(capacity, capacity, capacity);
   }

   public ItemEnergyContainer(int capacity, int maxTransfer) {
      this(capacity, maxTransfer, maxTransfer);
   }

   public ItemEnergyContainer(int capacity, int maxReceive, int maxExtract) {
      this.capacity = capacity;
      this.maxReceive = maxReceive;
      this.maxExtract = maxExtract;
   }

   public ItemEnergyContainer setCapacity(int capacity) {
      this.capacity = capacity;
      return this;
   }

   public void setMaxTransfer(int maxTransfer) {
      this.setMaxReceive(maxTransfer);
      this.setMaxExtract(maxTransfer);
   }

   public void setMaxReceive(int maxReceive) {
      this.maxReceive = maxReceive;
   }

   public void setMaxExtract(int maxExtract) {
      this.maxExtract = maxExtract;
   }

   public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
      if(container.stackTagCompound == null) {
         container.stackTagCompound = new NBTTagCompound();
      }

      int energy = container.stackTagCompound.getInteger("Energy");
      int energyReceived = Math.min(this.capacity - energy, Math.min(this.maxReceive, maxReceive));
      if(!simulate) {
         energy += energyReceived;
         container.stackTagCompound.setInteger("Energy", energy);
      }

      return energyReceived;
   }

   public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
      if(container.stackTagCompound != null && container.stackTagCompound.hasKey("Energy")) {
         int energy = container.stackTagCompound.getInteger("Energy");
         int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
         if(!simulate) {
            energy -= energyExtracted;
            container.stackTagCompound.setInteger("Energy", energy);
         }

         return energyExtracted;
      } else {
         return 0;
      }
   }

   public int getEnergyStored(ItemStack container) {
      return container.stackTagCompound != null && container.stackTagCompound.hasKey("Energy")?container.stackTagCompound.getInteger("Energy"):0;
   }

   public int getMaxEnergyStored(ItemStack container) {
      return this.capacity;
   }
}
