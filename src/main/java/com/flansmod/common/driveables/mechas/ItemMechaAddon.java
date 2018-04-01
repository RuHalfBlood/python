package com.flansmod.common.driveables.mechas;

import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.mechas.MechaItemType;
import com.flansmod.common.types.IFlanItem;
import com.flansmod.common.types.InfoType;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMechaAddon extends Item implements IFlanItem {

   public MechaItemType type;


   public ItemMechaAddon(MechaItemType type1) {
      this.type = type1;
      this.setMaxStackSize(1);
      this.type.item = this;
      this.setCreativeTab(FlansMod.tabFlanMechas);
      GameRegistry.registerItem(this, this.type.shortName, "flansmod");
   }

   public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
      if(!this.type.packName.isEmpty()) {
         list.add(this.type.packName);
      }

      if(this.type.description != null) {
         Collections.addAll(list, this.type.description.split("_"));
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
