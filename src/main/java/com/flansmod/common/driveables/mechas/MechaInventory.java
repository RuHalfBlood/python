/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.flansmod.common.driveables.mechas;

import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.driveables.mechas.EnumMechaSlotType;
import com.flansmod.common.driveables.mechas.ItemMechaAddon;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.guns.ItemGun;
import java.util.HashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class MechaInventory
implements IInventory {
    public EntityMecha mecha;
    public HashMap<EnumMechaSlotType, ItemStack> stacks;

    public MechaInventory(EntityMecha m) {
        this.mecha = m;
        this.stacks = new HashMap();
        for (EnumMechaSlotType type : EnumMechaSlotType.values()) {
            this.stacks.put(type, null);
        }
    }

    public MechaInventory(EntityMecha m, NBTTagCompound tags) {
        this(m);
        this.readFromNBT(tags);
    }

    public void readFromNBT(NBTTagCompound tags) {
        if (tags == null) {
            return;
        }
        for (EnumMechaSlotType type : EnumMechaSlotType.values()) {
            this.stacks.put(type, ItemStack.loadItemStackFromNBT((NBTTagCompound)tags.getCompoundTag(type.toString())));
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound tags) {
        if (tags == null) {
            return null;
        }
        for (EnumMechaSlotType type : EnumMechaSlotType.values()) {
            if (this.stacks.get((Object)type) == null) continue;
            tags.setTag(type.toString(), (NBTBase)this.stacks.get((Object)type).writeToNBT(new NBTTagCompound()));
        }
        return tags;
    }

    public int getSizeInventory() {
        return EnumMechaSlotType.values().length;
    }

    public ItemStack getStackInSlot(int i) {
        return this.stacks.get((Object)EnumMechaSlotType.values()[i]);
    }

    public ItemStack getStackInSlot(EnumMechaSlotType e) {
        return this.stacks.get((Object)e);
    }

    public ItemStack decrStackSize(int i, int j) {
        this.markDirty();
        ItemStack slot = this.getStackInSlot(i);
        if (slot == null) {
            return null;
        }
        int numToTake = Math.min(j, slot.stackSize);
        ItemStack returnStack = slot.copy();
        returnStack.stackSize = numToTake;
        slot.stackSize -= numToTake;
        if (slot.stackSize <= 0) {
            slot = null;
        }
        this.setInventorySlotContents(i, slot);
        return returnStack;
    }

    public ItemStack getStackInSlotOnClosing(int i) {
        return this.getStackInSlot(i);
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.setInventorySlotContents(EnumMechaSlotType.values()[i], itemstack);
    }

    public void setInventorySlotContents(EnumMechaSlotType e, ItemStack itemstack) {
        this.markDirty();
        this.stacks.put(e, itemstack);
    }

    public String getInventoryName() {
        return "Mecha";
    }

    public boolean hasCustomInventoryName() {
        return true;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public void markDirty() {
        if (this.mecha != null) {
            this.mecha.couldNotFindFuel = false;
        }
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        return this.mecha != null && (double)entityplayer.getDistanceToEntity((Entity)this.mecha) <= 10.0;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        Item item = itemstack.getItem();
        if (item == null) {
            return true;
        }
        switch (EnumMechaSlotType.values()[i]) {
            case leftTool: 
            case rightTool: {
                return item instanceof ItemGun || item instanceof ItemMechaAddon;
            }
            case leftArm: 
            case rightArm: {
                return item instanceof ItemBullet;
            }
        }
        return false;
    }

}

