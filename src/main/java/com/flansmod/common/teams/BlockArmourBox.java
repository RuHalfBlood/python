package com.flansmod.common.teams;

import com.flansmod.common.CraftingInstance;
import com.flansmod.common.FlansMod;
import com.flansmod.common.teams.ArmourBoxType;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockArmourBox extends Block {

   public ArmourBoxType type;


   public BlockArmourBox(ArmourBoxType t) throws Exception {
      super(Material.wood);
      this.type = t;
      this.setBlockName(this.type.shortName);
      this.setHardness(2.0F);
      this.setResistance(4.0F);
      Block block = Block.getBlockFromName("flansmod:armorBox." + this.type.shortName);
      if(block != null) {
         throw new Exception("Caught an exception during block registration");
      } else {
         GameRegistry.registerBlock(this, "armorBox." + this.type.shortName);
         this.setCreativeTab(FlansMod.tabFlanTeams);
         this.type.block = this;
         this.type.item = Item.getItemFromBlock(this);
      }
   }

   public void buyArmour(String shortName, int piece, InventoryPlayer inventory) {
      if(FMLCommonHandler.instance().getEffectiveSide().isClient()) {
         FlansMod.proxy.buyArmour(shortName, piece, this.type);
      }

      ArmourBoxType.ArmourBoxEntry entryPicked = null;
      Iterator resultStack = this.type.pages.iterator();

      while(resultStack.hasNext()) {
         ArmourBoxType.ArmourBoxEntry crafting = (ArmourBoxType.ArmourBoxEntry)resultStack.next();
         if(crafting.shortName.equals(shortName)) {
            entryPicked = crafting;
         }
      }

      ItemStack resultStack1 = new ItemStack(entryPicked.armours[piece].item);
      CraftingInstance crafting1 = new CraftingInstance(inventory, entryPicked.requiredStacks[piece], resultStack1);
      if(crafting1.canCraft()) {
         crafting1.craft(inventory.player);
      }

   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int metadata) {
      return this.type == null?null:(side == 1?this.type.top:(side == 0?this.type.bottom:this.type.side));
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister register) {
      this.type.top = register.registerIcon("FlansMod:" + this.type.topTexturePath);
      this.type.side = register.registerIcon("FlansMod:" + this.type.sideTexturePath);
      this.type.bottom = register.registerIcon("FlansMod:" + this.type.bottomTexturePath);
   }

   public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
      if(entityplayer.isSneaking()) {
         return false;
      } else {
         entityplayer.openGui(FlansMod.INSTANCE, 11, world, i, j, k);
         return true;
      }
   }
}
