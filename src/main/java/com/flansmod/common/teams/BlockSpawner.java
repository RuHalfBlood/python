package com.flansmod.common.teams;

import com.flansmod.client.FlansModClient;
import com.flansmod.common.FlansMod;
import com.flansmod.common.teams.ItemOpStick;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TileEntitySpawner;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpawner extends BlockContainer {

   public static boolean colouredPass = false;
   public IIcon[][] icons;


   public BlockSpawner(Material material) {
      super(material);
      this.setCreativeTab(FlansMod.tabFlanTeams);
   }

   public void getSubBlocks(Item item, CreativeTabs tab, List list) {
      if(tab == FlansMod.tabFlanTeams) {
         list.add(new ItemStack(item, 1, 0));
         list.add(new ItemStack(item, 1, 1));
         list.add(new ItemStack(item, 1, 2));
      }

   }

   public IIcon getIcon(int i, int j) {
      if(j > 2) {
         j = 2;
      }

      return this.icons[colouredPass?1:0][j];
   }

   public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
      return null;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      return true;
   }

   public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
      return World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4) || BlockFence.func_149825_a(par1World.getBlock(par2, par3 - 1, par4));
   }

   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
      if(!par1World.isRemote && par1World.getBlockMetadata(par2, par3, par4) != 1) {
         ;
      }

   }

   public void setBlockBoundsBasedOnState(IBlockAccess access, int i, int j, int k) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.03125F, 1.0F);
   }

   public void setBlockBoundsForItemRender() {
      float var1 = 0.5F;
      float var2 = 0.015625F;
      float var3 = 0.5F;
      this.setBlockBounds(0.0F, 0.5F - var2, 0.0F, 1.0F, 0.5F + var2, 1.0F);
   }

   public int getMobilityFlag() {
      return 1;
   }

   public TileEntity createNewTileEntity(World var1, int i) {
      return new TileEntitySpawner();
   }

   public int colorMultiplier(IBlockAccess access, int x, int y, int z) {
      if(!colouredPass) {
         return 16777215;
      } else {
         try {
            TileEntitySpawner e = (TileEntitySpawner)access.getTileEntity(x, y, z);
            int spawnerTeamID = e.getTeamID();
            Team spawnerTeam = FlansModClient.getTeam(spawnerTeamID);
            boolean currentMap = FlansModClient.isCurrentMap(e.map);
            if(spawnerTeam == null || !currentMap) {
               switch(spawnerTeamID) {
               case 0:
                  return 8421504;
               case 1:
                  return 4210752;
               case 2:
                  return 10584063;
               case 3:
                  return 16744374;
               }
            }

            return spawnerTeam.teamColour;
         } catch (Exception var9) {
            return 16777215;
         }
      }
   }

   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9) {
      if(world.isRemote) {
         return true;
      } else {
         if(MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile())) {
            TileEntitySpawner spawner = (TileEntitySpawner)world.getTileEntity(x, y, z);
            ItemStack item = player.getCurrentEquippedItem();
            if(item != null && item.getItem() != null) {
               if(!(item.getItem() instanceof ItemOpStick)) {
                  spawner.stacksToSpawn.add(item.copy());
                  Iterator var12 = spawner.itemEntities.iterator();

                  while(var12.hasNext()) {
                     Entity entity = (Entity)var12.next();
                     entity.setDead();
                  }

                  spawner.currentDelay = 10;
               }
            } else {
               spawner.spawnDelay = (spawner.spawnDelay + 200) % 6000;
               player.addChatMessage(new ChatComponentText("Set spawn delay to " + spawner.spawnDelay / 20));
            }
         }

         return true;
      }
   }

   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister register) {
      this.icons = new IIcon[2][3];

      for(int i = 0; i < 2; ++i) {
         this.icons[i][0] = register.registerIcon("FlansMod:spawner_item_" + (i + 1));
         this.icons[i][1] = register.registerIcon("FlansMod:spawner_player_" + (i + 1));
         this.icons[i][2] = register.registerIcon("FlansMod:spawner_vehicle_" + (i + 1));
      }

   }

}
