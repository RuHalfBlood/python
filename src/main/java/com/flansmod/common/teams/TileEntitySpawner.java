package com.flansmod.common.teams;

import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.ItemPlane;
import com.flansmod.common.driveables.ItemVehicle;
import com.flansmod.common.guns.ItemAAGun;
import com.flansmod.common.teams.EntityTeamItem;
import com.flansmod.common.teams.ITeamBase;
import com.flansmod.common.teams.ITeamObject;
import com.flansmod.common.teams.TeamsManager;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

public class TileEntitySpawner extends TileEntity implements ITeamObject {

   public int spawnDelay = 1200;
   public List stacksToSpawn = new ArrayList();
   public List itemEntities = new ArrayList();
   public Entity spawnedEntity;
   public ITeamBase base;
   private int baseID = -1;
   private int dimension;
   public int currentDelay;
   private Ticket chunkTicket;
   private boolean uninitialized = true;
   private int loadDistance = 1;
   private int teamID;
   public String map;


   public TileEntitySpawner() {
      TeamsManager.getInstance().registerObject(this);
   }

   public Packet getDescriptionPacket() {
      NBTTagCompound tags = new NBTTagCompound();
      tags.setByte("TeamID", this.base == null?0:(byte)this.base.getOwnerID());
      tags.setString("Map", this.base != null && this.base.getMap() != null?this.base.getMap().shortName:"");
      return new S35PacketUpdateTileEntity(super.xCoord, super.yCoord, super.zCoord, 1, tags);
   }

   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
      this.teamID = packet.func_148857_g().getByte("TeamID");
      this.map = packet.func_148857_g().getString("Map");
   }

   public void updateEntity() {
      if(!super.worldObj.isRemote) {
         if(this.baseID >= 0 && this.base == null) {
            ITeamBase i = TeamsManager.getInstance().getBase(this.baseID);
            if(i != null) {
               this.setBase(i);
               i.addObject(this);
            }
         }

         if(super.worldObj.getBlock(super.xCoord, super.yCoord, super.zCoord) != FlansMod.spawner) {
            this.destroy();
         } else if(super.worldObj.getBlockMetadata(super.xCoord, super.yCoord, super.zCoord) != 1) {
            int var3;
            for(var3 = this.itemEntities.size() - 1; var3 >= 0; --var3) {
               if(((EntityTeamItem)this.itemEntities.get(var3)).isDead) {
                  this.itemEntities.remove(var3);
               }
            }

            if(this.currentDelay > 0 && this.itemEntities.size() == 0) {
               --this.currentDelay;
            }

            if(this.currentDelay == 0) {
               this.currentDelay = this.spawnDelay > 0?this.spawnDelay:20;

               for(var3 = 0; var3 < this.stacksToSpawn.size(); ++var3) {
                  if(super.worldObj.getBlockMetadata(super.xCoord, super.yCoord, super.zCoord) == 2) {
                     if(this.spawnedEntity == null || this.spawnedEntity.isDead) {
                        ItemStack itemEntity = (ItemStack)this.stacksToSpawn.get(var3);
                        if(itemEntity != null && itemEntity.getItem() instanceof ItemPlane) {
                           this.spawnedEntity = ((ItemPlane)itemEntity.getItem()).spawnPlane(super.worldObj, (double)((float)super.xCoord + 0.5F), (double)((float)super.yCoord + 0.5F), (double)((float)super.zCoord + 0.5F), itemEntity);
                        }

                        if(itemEntity != null && itemEntity.getItem() instanceof ItemVehicle) {
                           this.spawnedEntity = ((ItemVehicle)itemEntity.getItem()).spawnVehicle(super.worldObj, (double)((float)super.xCoord + 0.5F), (double)((float)super.yCoord + 0.5F), (double)((float)super.zCoord + 0.5F), itemEntity);
                        }

                        if(itemEntity != null && itemEntity.getItem() instanceof ItemAAGun) {
                           this.spawnedEntity = ((ItemAAGun)itemEntity.getItem()).spawnAAGun(super.worldObj, (double)((float)super.xCoord + 0.5F), (double)super.yCoord, (double)((float)super.zCoord + 0.5F), itemEntity);
                        }
                     }
                  } else {
                     EntityTeamItem var4 = new EntityTeamItem(this, var3);
                     super.worldObj.spawnEntityInWorld(var4);
                  }
               }
            }

         }
      }
   }

   public void writeToNBT(NBTTagCompound nbt) {
      super.writeToNBT(nbt);
      nbt.setInteger("delay", this.spawnDelay);
      nbt.setInteger("Base", this.baseID);
      nbt.setInteger("dim", super.worldObj.provider.dimensionId);
      nbt.setInteger("numStacks", this.stacksToSpawn.size());

      for(int i = 0; i < this.stacksToSpawn.size(); ++i) {
         NBTTagCompound stackNBT = new NBTTagCompound();
         ((ItemStack)this.stacksToSpawn.get(i)).writeToNBT(stackNBT);
         nbt.setTag("stack" + i, stackNBT);
      }

   }

   public void readFromNBT(NBTTagCompound nbt) {
      super.readFromNBT(nbt);
      this.currentDelay = this.spawnDelay = nbt.getInteger("delay");
      if(this.currentDelay < 20) {
         this.currentDelay = 20;
      }

      this.baseID = nbt.getInteger("Base");
      this.dimension = nbt.getInteger("dim");
      this.setBase(TeamsManager.getInstance().getBase(this.baseID));
      if(this.base != null) {
         this.base.addObject(this);
      }

      for(int i = 0; i < nbt.getInteger("numStacks"); ++i) {
         this.stacksToSpawn.add(ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("stack" + i)));
      }

   }

   public ITeamBase getBase() {
      return this.base;
   }

   public int getTeamID() {
      return super.worldObj.isRemote?this.teamID:(this.base == null?0:this.base.getOwnerID());
   }

   public void onBaseSet(int newTeamID) {
      FlansMod.packetHandler.sendToDimension(this.getDescriptionPacket(), super.worldObj == null?this.dimension:super.worldObj.provider.dimensionId);
   }

   public void onBaseCapture(int newTeamID) {
      this.onBaseSet(newTeamID);
   }

   public void setBase(ITeamBase b) {
      this.base = b;
      if(b != null) {
         this.baseID = b.getBaseID();
      }

      FlansMod.packetHandler.sendToDimension(this.getDescriptionPacket(), super.worldObj == null?this.dimension:super.worldObj.provider.dimensionId);
   }

   public void tick() {}

   public void destroy() {
      super.worldObj.setBlock(super.xCoord, super.yCoord, super.zCoord, Blocks.air);
   }

   public double getPosX() {
      return (double)((float)super.xCoord + 0.5F);
   }

   public double getPosY() {
      return (double)((float)super.yCoord + 0.5F);
   }

   public double getPosZ() {
      return (double)((float)super.zCoord + 0.5F);
   }

   public boolean isSpawnPoint() {
      int metadata = super.worldObj.getBlockMetadata(super.xCoord, super.yCoord, super.zCoord);
      return metadata == 1;
   }

   public boolean forceChunkLoading() {
      return false;
   }
}
