package com.flansmod.common.driveables;

import com.flansmod.common.driveables.CollisionBox;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.parts.ItemPart;
import com.flansmod.common.parts.PartType;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DriveableData implements IInventory {

   public String type;
   public int numGuns;
   public int numBombs;
   public int numMissiles;
   public int numCargo;
   public ItemStack[] ammo;
   public ItemStack[] bombs;
   public ItemStack[] missiles;
   public ItemStack[] cargo;
   public PartType engine;
   public ItemStack fuel;
   public float fuelInTank;
   public HashMap parts;
   public boolean inventoryChanged;
   public int paintjobID;


   public DriveableData(NBTTagCompound tags, int paintjobID) {
      this(tags);
      this.paintjobID = paintjobID;
   }

   public DriveableData(NBTTagCompound tags) {
      this.inventoryChanged = false;
      this.parts = new HashMap();
      this.readFromNBT(tags);
   }

   public void readFromNBT(NBTTagCompound tag) {
      if(tag != null) {
         if(tag.hasKey("Type")) {
            this.type = tag.getString("Type");
            DriveableType dType = DriveableType.getDriveable(this.type);
            this.numBombs = dType.numBombSlots;
            this.numCargo = dType.numCargoSlots;
            this.numMissiles = dType.numMissileSlots;
            this.numGuns = dType.ammoSlots();
            this.engine = PartType.getPart(tag.getString("Engine"));
            this.paintjobID = tag.getInteger("Paint");
            this.ammo = new ItemStack[this.numGuns];
            this.bombs = new ItemStack[this.numBombs];
            this.missiles = new ItemStack[this.numMissiles];
            this.cargo = new ItemStack[this.numCargo];

            int i;
            for(i = 0; i < this.numGuns; ++i) {
               this.ammo[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Ammo " + i));
            }

            for(i = 0; i < this.numBombs; ++i) {
               this.bombs[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Bombs " + i));
            }

            for(i = 0; i < this.numMissiles; ++i) {
               this.missiles[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Missiles " + i));
            }

            for(i = 0; i < this.numCargo; ++i) {
               this.cargo[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Cargo " + i));
            }

            this.fuel = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Fuel"));
            this.fuelInTank = (float)tag.getInteger("FuelInTank");
            EnumDriveablePart[] var8 = EnumDriveablePart.values();
            int part = var8.length;

            for(int var5 = 0; var5 < part; ++var5) {
               EnumDriveablePart part1 = var8[var5];
               this.parts.put(part1, new DriveablePart(part1, (CollisionBox)dType.health.get(part1)));
            }

            Iterator var9 = this.parts.values().iterator();

            while(var9.hasNext()) {
               DriveablePart var7 = (DriveablePart)var9.next();
               var7.readFromNBT(tag);
            }

         }
      }
   }

   public void writeToNBT(NBTTagCompound tag) {
      tag.setString("Type", this.type);
      tag.setString("Engine", this.engine.shortName);
      tag.setInteger("Paint", this.paintjobID);

      int i;
      for(i = 0; i < this.ammo.length; ++i) {
         if(this.ammo[i] != null) {
            tag.setTag("Ammo " + i, this.ammo[i].writeToNBT(new NBTTagCompound()));
         }
      }

      for(i = 0; i < this.bombs.length; ++i) {
         if(this.bombs[i] != null) {
            tag.setTag("Bombs " + i, this.bombs[i].writeToNBT(new NBTTagCompound()));
         }
      }

      for(i = 0; i < this.missiles.length; ++i) {
         if(this.missiles[i] != null) {
            tag.setTag("Missiles " + i, this.missiles[i].writeToNBT(new NBTTagCompound()));
         }
      }

      for(i = 0; i < this.cargo.length; ++i) {
         if(this.cargo[i] != null) {
            tag.setTag("Cargo " + i, this.cargo[i].writeToNBT(new NBTTagCompound()));
         }
      }

      if(this.fuel != null) {
         tag.setTag("Fuel", this.fuel.writeToNBT(new NBTTagCompound()));
      }

      tag.setInteger("FuelInTank", (int)this.fuelInTank);
      Iterator var4 = this.parts.values().iterator();

      while(var4.hasNext()) {
         DriveablePart part = (DriveablePart)var4.next();
         part.writeToNBT(tag);
      }

   }

   public int getSizeInventory() {
      return this.getFuelSlot() + 1;
   }

   public ItemStack getStackInSlot(int i) {
      ItemStack[] inv = this.ammo;
      if(i >= this.ammo.length) {
         i -= this.ammo.length;
         inv = this.bombs;
         if(i >= this.bombs.length) {
            i -= this.bombs.length;
            inv = this.missiles;
            if(i >= this.missiles.length) {
               i -= this.missiles.length;
               inv = this.cargo;
               if(i >= this.cargo.length) {
                  return this.fuel;
               }
            }
         }
      }

      return inv[i];
   }

   public ItemStack decrStackSize(int i, int j) {
      ItemStack[] inv = this.ammo;
      if(i >= this.ammo.length) {
         i -= this.ammo.length;
         inv = this.bombs;
         if(i >= this.bombs.length) {
            i -= this.bombs.length;
            inv = this.missiles;
            if(i >= this.missiles.length) {
               i -= this.missiles.length;
               inv = this.cargo;
               if(i >= this.cargo.length) {
                  i -= this.cargo.length;
                  inv = new ItemStack[]{this.fuel};
                  this.setInventorySlotContents(this.getFuelSlot(), (ItemStack)null);
               }
            }
         }
      }

      if(inv[i] != null) {
         ItemStack itemstack1;
         if(inv[i].stackSize <= j) {
            itemstack1 = inv[i];
            inv[i] = null;
            return itemstack1;
         } else {
            itemstack1 = inv[i].splitStack(j);
            if(inv[i].stackSize <= 0) {
               inv[i] = null;
            }

            return itemstack1;
         }
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int i) {
      return this.getStackInSlot(i);
   }

   public void setInventorySlotContents(int i, ItemStack stack) {
      if(stack != null) {
         this.inventoryChanged = true;
      }

      ItemStack[] inv = this.ammo;
      if(i >= this.ammo.length) {
         i -= this.ammo.length;
         inv = this.bombs;
         if(i >= this.bombs.length) {
            i -= this.bombs.length;
            inv = this.missiles;
            if(i >= this.missiles.length) {
               i -= this.missiles.length;
               inv = this.cargo;
               if(i >= this.cargo.length) {
                  this.fuel = stack;
                  return;
               }
            }
         }
      }

      inv[i] = stack;
   }

   public String getInventoryName() {
      return "Flan\'s Secret Data";
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void markDirty() {}

   public boolean isUseableByPlayer(EntityPlayer player) {
      return true;
   }

   public void openInventory() {}

   public void closeInventory() {}

   public int getAmmoInventoryStart() {
      return 0;
   }

   public int getBombInventoryStart() {
      return this.ammo.length;
   }

   public int getMissileInventoryStart() {
      return this.ammo.length + this.bombs.length;
   }

   public int getCargoInventoryStart() {
      return this.ammo.length + this.bombs.length + this.missiles.length;
   }

   public int getFuelSlot() {
      return this.ammo.length + this.bombs.length + this.missiles.length + this.cargo.length;
   }

   public boolean hasCustomInventoryName() {
      return false;
   }

   public boolean isItemValidForSlot(int i, ItemStack itemstack) {
      return i < this.getBombInventoryStart() && itemstack != null && itemstack.getItem() instanceof ItemBullet?true:(i >= this.getBombInventoryStart() && i < this.getMissileInventoryStart() && itemstack != null && itemstack.getItem() instanceof ItemBullet?true:(i >= this.getMissileInventoryStart() && i < this.getCargoInventoryStart() && itemstack != null && itemstack.getItem() instanceof ItemBullet?true:(i >= this.getCargoInventoryStart() && i < this.getFuelSlot()?true:i == this.getFuelSlot() && itemstack != null && itemstack.getItem() instanceof ItemPart && ((ItemPart)itemstack.getItem()).type.category == 9)));
   }
}
