package com.flansmod.common;

import com.flansmod.common.FlansMod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockManyNames extends ItemBlock {

   public ItemBlockManyNames(Block b) {
      super(b);
      this.setHasSubtypes(true);
   }

   public String getUnlocalizedName(ItemStack stack) {
      return super.getUnlocalizedName() + "." + stack.getItemDamage();
   }

   public int getMetadata(int par1) {
      return par1;
   }

   public CreativeTabs[] getCreativeTabs() {
      return new CreativeTabs[]{FlansMod.tabFlanDriveables, FlansMod.tabFlanGuns, FlansMod.tabFlanTeams, FlansMod.tabFlanParts};
   }
}
