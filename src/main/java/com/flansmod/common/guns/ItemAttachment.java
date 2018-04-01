package com.flansmod.common.guns;

import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.paintjob.IPaintableItem;
import com.flansmod.common.paintjob.PaintableType;
import com.flansmod.common.types.InfoType;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;

public class ItemAttachment extends Item implements IPaintableItem {

   public AttachmentType type;
   public IIcon[] icons;


   public ItemAttachment(AttachmentType t) {
      this.type = t;
      this.type.item = this;
      super.maxStackSize = t.maxStackSize;
      this.setCreativeTab(FlansMod.tabFlanGuns);
      GameRegistry.registerItem(this, this.type.shortName, "flansmod");
   }

   @SideOnly(Side.CLIENT)
   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
      return this.type.colour;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister icon) {
      this.icons = new IIcon[this.type.paintjobs.size()];
      super.itemIcon = icon.registerIcon("FlansMod:" + this.type.iconPath);

      for(int i = 0; i < this.type.paintjobs.size(); ++i) {
         this.icons[i] = icon.registerIcon("FlansMod:" + ((com.flansmod.common.paintjob.Paintjob)this.type.paintjobs.get(i)).iconName);
      }

   }

   @SideOnly(Side.CLIENT)
   public IIcon getIconIndex(ItemStack stack) {
      return this.icons[stack.getItemDamage()];
   }

   public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean b) {
      if(!this.type.packName.isEmpty()) {
         lines.add(this.type.packName);
      }

      if(this.type.description != null) {
         Collections.addAll(lines, this.type.description.split("_"));
      }

   }

   public InfoType getInfoType() {
      return this.type;
   }

   public PaintableType GetPaintableType() {
      return this.type;
   }

   public void getSubItems(Item item, CreativeTabs tabs, List list) {
      PaintableType type = ((IPaintableItem)item).GetPaintableType();
      if(FlansMod.addAllPaintjobsToCreative) {
         Iterator var5 = type.paintjobs.iterator();

         while(var5.hasNext()) {
            com.flansmod.common.paintjob.Paintjob paintjob = (com.flansmod.common.paintjob.Paintjob)var5.next();
            this.addPaintjobToList(item, type, paintjob, list);
         }
      } else {
         this.addPaintjobToList(item, type, type.defaultPaintjob, list);
      }

   }

   private void addPaintjobToList(Item item, PaintableType type, com.flansmod.common.paintjob.Paintjob paintjob, List list) {
      ItemStack paintableStack = new ItemStack(item, 1, paintjob.ID);
      NBTTagCompound tags = new NBTTagCompound();
      paintableStack.setTagCompound(tags);
      list.add(paintableStack);
   }
}
