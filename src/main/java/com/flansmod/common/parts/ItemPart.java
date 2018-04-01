package com.flansmod.common.parts;

import com.flansmod.common.FlansMod;
import com.flansmod.common.parts.PartType;
import com.flansmod.common.types.IFlanItem;
import com.flansmod.common.types.InfoType;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPart extends Item implements IFlanItem {

   public PartType type;


   public ItemPart(PartType type1) {
      this.type = type1;
      this.setMaxStackSize(this.type.stackSize);
      if(this.type.category == 9) {
         this.setMaxDamage(this.type.fuel);
         this.setHasSubtypes(true);
      }

      this.type.item = this;
      this.setTextureName("FlansMod:" + this.type.iconPath);
      this.setCreativeTab(FlansMod.tabFlanParts);
      GameRegistry.registerItem(this, this.type.shortName, "flansmod");
   }

   public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
      if(!this.type.packName.isEmpty()) {
         par3List.add(this.type.packName);
      }

      if(this.type.category == 9) {
         par3List.add("Fuel Stored: " + (this.type.fuel - par1ItemStack.getItemDamage()) + " / " + this.type.fuel);
      }

   }

   @SideOnly(Side.CLIENT)
   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
      return this.type.colour;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister icon) {
      super.itemIcon = icon.registerIcon("FlansMod:" + this.type.iconPath);
   }

   public InfoType getInfoType() {
      return this.type;
   }
}
