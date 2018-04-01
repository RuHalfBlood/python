package com.flansmod.common.teams;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.teams.EntityFlagpole;
import com.flansmod.common.teams.GametypeCTF;
import com.flansmod.common.teams.ITeamBase;
import com.flansmod.common.teams.ITeamObject;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFlag extends Entity implements ITeamObject {

   public int baseID;
   public EntityFlagpole base;
   public boolean isHome;
   public int timeUntilReturn;


   public EntityFlag(World world) {
      super(world);
      this.isHome = true;
      this.setSize(1.0F, 1.0F);
      super.renderDistanceWeight = 100.0D;
      super.ignoreFrustumCheck = true;
   }

   public EntityFlag(World world, EntityFlagpole pole) {
      this(world);
      this.setPosition(pole.posX, pole.posY + 2.0D, pole.posZ);
      this.setBase(pole);
   }

   public boolean canBeCollidedWith() {
      return true;
   }

   protected void entityInit() {
      super.dataWatcher.addObject(2, Byte.valueOf((byte)0));
   }

   public void onUpdate() {
      super.onUpdate();
      if(this.base == null && !super.worldObj.isRemote) {
         this.setBase(TeamsManager.getInstance().getBase(this.baseID));
      }

      if(super.ridingEntity != null && super.ridingEntity.isDead) {
         if(super.ridingEntity instanceof EntityPlayerMP) {
            EntityPlayerMP flagTeam = (EntityPlayerMP)super.ridingEntity;
            Team team = PlayerHandler.getPlayerData(flagTeam.getCommandSenderName()).team;
            TeamsManager.getInstance();
            TeamsManager.messageAll("§f" + flagTeam.getCommandSenderName() + " dropped the §" + team.textColour + team.name + "§f flag");
         }

         this.mountEntity((Entity)null);
      }

      if(!super.addedToChunk) {
         super.worldObj.spawnEntityInWorld(this);
      }

      if(this.timeUntilReturn > 0) {
         if(super.ridingEntity == null && !this.isHome) {
            --this.timeUntilReturn;
            if(this.timeUntilReturn == 0) {
               this.reset();
               Team var3 = TeamsManager.getInstance().getTeam(this.getBase().getOwnerID());
               TeamsManager.messageAll("§fThe §" + var3.textColour + var3.name + "§f flag returned itself");
            }
         } else {
            this.timeUntilReturn = 0;
         }
      }

      if(super.worldObj.isRemote) {
         this.extinguish();
      }

   }

   public void mountEntity(Entity entity) {
      if(entity == null) {
         if(TeamsManager.getInstance().currentRound != null && TeamsManager.getInstance().currentRound.gametype instanceof GametypeCTF) {
            this.timeUntilReturn = ((GametypeCTF)TeamsManager.getInstance().currentRound.gametype).flagReturnTime * 20;
         } else {
            this.timeUntilReturn = 600;
         }
      }

      super.mountEntity(entity);
   }

   public void reset() {
      this.mountEntity((Entity)null);
      this.setPosition(this.base.posX, this.base.posY + 2.0D, this.base.posZ);
      this.isHome = true;
   }

   public boolean writeToNBTOptional(NBTTagCompound tags) {
      return false;
   }

   protected void readEntityFromNBT(NBTTagCompound tags) {}

   protected void writeEntityToNBT(NBTTagCompound tags) {}

   public ITeamBase getBase() {
      return this.base;
   }

   public void onBaseSet(int newTeamID) {
      super.dataWatcher.updateObject(2, Byte.valueOf((byte)newTeamID));
      this.setPosition(this.base.posX, this.base.posY + 2.0D, this.base.posZ);
   }

   public void onBaseCapture(int newTeamID) {
      this.onBaseSet(newTeamID);
   }

   public void tick() {}

   public void setBase(ITeamBase b) {
      this.base = (EntityFlagpole)b;
      if(this.base != null) {
         this.base.addObject(this);
         this.onBaseSet(this.base.getOwnerID());
      }

   }

   public void destroy() {
      this.setDead();
   }

   public double getPosX() {
      return super.posX;
   }

   public double getPosY() {
      return super.posY;
   }

   public double getPosZ() {
      return super.posZ;
   }

   public int getTeamID() {
      return super.dataWatcher.getWatchableObjectByte(2);
   }

   public boolean isSpawnPoint() {
      return false;
   }

   public boolean interactFirst(EntityPlayer player) {
      return false;
   }

   public ItemStack getPickedResult(MovingObjectPosition target) {
      ItemStack stack = new ItemStack(FlansMod.flag, 1, 0);
      return stack;
   }

   public boolean forceChunkLoading() {
      return false;
   }

   public boolean isBurning() {
      return false;
   }
}
