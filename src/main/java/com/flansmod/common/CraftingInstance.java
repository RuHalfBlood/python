package com.flansmod.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class CraftingInstance {

   public IInventory inventory;
   public List requiredStacks;
   public List outputStacks;
   public boolean craftingSuccessful;


   public CraftingInstance(IInventory i, List in, List out) {
      this.inventory = i;
      this.requiredStacks = in;
      this.outputStacks = out;
   }

   public CraftingInstance(IInventory i, ArrayList in, ItemStack out) {
      this(i, (List)in, Arrays.asList(new ItemStack[]{out}));
   }

   public boolean canCraft() {
      this.craftingSuccessful = true;
      Iterator var1 = this.requiredStacks.iterator();

      while(var1.hasNext()) {
         ItemStack check = (ItemStack)var1.next();
         int numMatchingStuff = 0;

         for(int j = 0; j < this.inventory.getSizeInventory(); ++j) {
            ItemStack stack = this.inventory.getStackInSlot(j);
            if(stack != null && stack.getItem() == check.getItem() && stack.getItemDamage() == check.getItemDamage()) {
               numMatchingStuff += stack.stackSize;
            }
         }

         if(numMatchingStuff < check.stackSize) {
            this.craftingSuccessful = false;
         }
      }

      return this.craftingSuccessful;
   }

   public void craft(EntityPlayer player) {
      if(this.craftingSuccessful) {
         Iterator var2 = this.requiredStacks.iterator();

         ItemStack stack;
         while(var2.hasNext()) {
            stack = (ItemStack)var2.next();
            int amountLeft = stack.stackSize;

            for(int j = 0; j < this.inventory.getSizeInventory(); ++j) {
               ItemStack stack1 = this.inventory.getStackInSlot(j);
               if(amountLeft > 0 && stack1 != null && stack1.getItem() == stack.getItem() && stack1.getItemDamage() == stack.getItemDamage()) {
                  amountLeft -= this.inventory.decrStackSize(j, amountLeft).stackSize;
               }
            }
         }

         var2 = this.outputStacks.iterator();

         while(var2.hasNext()) {
            stack = (ItemStack)var2.next();
            if(!player.inventory.addItemStackToInventory(stack)) {
               player.dropPlayerItemWithRandomChoice(stack, false);
            }
         }

      }
   }
}
