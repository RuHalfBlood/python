package com.flansmod.common.paintjob;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.guns.InventoryHelper;
import com.flansmod.common.paintjob.TileEntityPaintjobTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPaintjobTable extends BlockContainer {

   private IIcon side;
   private IIcon top;


   public BlockPaintjobTable() {
      super(Material.rock);
      this.setHardness(2.0F);
      this.setResistance(4.0F);
      this.setBlockName("paintjobTable");
      this.setCreativeTab(FlansMod.tabFlanGuns);
   }

   public boolean canPlaceBlockAt(World world, int x, int y, int z) {
      return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z);
   }

   public TileEntity createNewTileEntity(World world, int i) {
      return new TileEntityPaintjobTable();
   }

   public IIcon getIcon(int i, int j) {
      return i == 1?this.top:this.side;
   }

   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int facing, float par7, float par8, float par9) {
      if(world.isRemote) {
         PlayerHandler var10000 = FlansMod.playerHandler;
         PlayerData var11 = PlayerHandler.getPlayerData(player, Side.CLIENT);
         PlayerHandler var10001 = FlansMod.playerHandler;
         var11.shootTimeLeft = PlayerHandler.getPlayerData(player, Side.CLIENT).shootTimeRight = 10;
         return true;
      } else {
         TileEntityPaintjobTable table = (TileEntityPaintjobTable)world.getTileEntity(x, y, z);
         if(!world.isRemote) {
            player.openGui(FlansMod.INSTANCE, 13, world, x, y, z);
         }

         return true;
      }
   }

   public void breakBlock(World worldIn, int x, int y, int z, Block block, int meta) {
      TileEntity tileentity = worldIn.getTileEntity(x, y, z);
      if(tileentity instanceof IInventory) {
         InventoryHelper.dropInventoryItems(worldIn, x, y, z, (IInventory)tileentity);
      }

      super.breakBlock(worldIn, x, y, z, block, meta);
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister register) {
      this.top = register.registerIcon("FlansMod:paintjobTableTop");
      this.side = register.registerIcon("FlansMod:planeCraftingTableSide");
   }
}
