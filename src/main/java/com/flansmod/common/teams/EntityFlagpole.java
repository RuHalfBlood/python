package com.flansmod.common.teams;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.teams.EntityFlag;
import com.flansmod.common.teams.ITeamBase;
import com.flansmod.common.teams.ITeamObject;
import com.flansmod.common.teams.ItemOpStick;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.teams.TeamsMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFlagpole extends Entity implements ITeamBase {

   public int defaultTeamID;
   public int currentTeamID;
   public TeamsMap map;
   public List objects;
   public String name;
   private int ID;
   private EntityFlag flag;
   public static TeamsManager teamsManager = TeamsManager.getInstance();
   private boolean uninitialized;
   private int loadDistance;


   public EntityFlagpole(World world) {
      super(world);
      this.objects = new ArrayList();
      this.name = "Default Name";
      this.uninitialized = true;
      this.loadDistance = 1;
      this.setSize(1.0F, 2.0F);
      super.renderDistanceWeight = 100.0D;
   }

   public EntityFlagpole(World world, double x, double y, double z) {
      this(world);
      this.setPosition(x, y, z);
      this.flag = new EntityFlag(super.worldObj, this);
      this.objects.add(this.flag);
      super.worldObj.spawnEntityInWorld(this.flag);
      if(teamsManager.maps.size() > 0) {
         this.map = (TeamsMap)teamsManager.maps.values().iterator().next();
      }

   }

   public EntityFlagpole(World world, int x, int y, int z) {
      this(world, (double)x + 0.5D, (double)y, (double)z + 0.5D);
   }

   public AxisAlignedBB getBoundingBox() {
      return null;
   }

   public boolean canBeCollidedWith() {
      return true;
   }

   protected void entityInit() {}

   protected void readEntityFromNBT(NBTTagCompound tags) {
      this.setBaseID(tags.getInteger("ID"));
      this.currentTeamID = this.defaultTeamID = tags.getInteger("TeamID");
      this.map = (TeamsMap)teamsManager.maps.get(tags.getString("Map"));
      this.name = tags.getString("Name");
      this.setMap(this.map);
   }

   protected void writeEntityToNBT(NBTTagCompound tags) {
      tags.setInteger("TeamID", this.defaultTeamID);
      tags.setString("Map", this.map == null?"":this.map.shortName);
      tags.setInteger("ID", this.getBaseID());
      tags.setString("Name", this.name);
   }

   public TeamsMap getMap() {
      return this.map;
   }

   public void setMap(TeamsMap newMap) {
      if(newMap == null) {
         FlansMod.log("Flagpole given invalid map");
      } else {
         if(this.map != null && this.map != newMap) {
            this.map.removeBase(this);
         }

         this.map = newMap;
         newMap.addBase(this);
      }
   }

   public void setMapFirstTime(TeamsMap newMap) {
      if(newMap == null) {
         FlansMod.log("Flagpole given invalid map");
      } else {
         if(this.map != null && this.map != newMap) {
            this.map.removeBase(this);
         }

         this.map = newMap;
         newMap.addBaseFirstTime(this);
      }
   }

   public List getObjects() {
      return this.objects;
   }

   public void tick() {}

   public void startRound() {
      this.currentTeamID = this.defaultTeamID;
   }

   public void addObject(ITeamObject object) {
      this.objects.add(object);
   }

   public String getName() {
      return this.name;
   }

   public void setName(String newName) {
      this.name = newName;
   }

   public void destroy() {
      this.setDead();
   }

   public Entity getEntity() {
      return this;
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

   public World getWorld() {
      return super.worldObj;
   }

   public void roundCleanup() {
      if(this.flag != null) {
         this.flag.reset();
      }

   }

   public ITeamObject getFlag() {
      return this.flag;
   }

   public void onUpdate() {
      super.onUpdate();
      if(!super.worldObj.isRemote) {
         if(this.flag == null) {
            this.flag = new EntityFlag(super.worldObj, this);
            this.objects.add(this.flag);
         }

         if(!this.flag.addedToChunk) {
            super.worldObj.spawnEntityInWorld(this.flag);
         }

         if(this.flag.isHome) {
            this.flag.setPosition(super.posX, super.posY + 2.0D, super.posZ);
         }
      }

      if(super.worldObj.isRemote) {
         this.extinguish();
      }

   }

   public void setDead() {
      super.setDead();
   }

   public boolean interactFirst(EntityPlayer player) {
      PlayerData data = PlayerHandler.getPlayerData(player);
      if(!super.worldObj.isRemote && data.team == null && TeamsManager.getInstance().playerIsOp(player) && (player.getCurrentEquippedItem() == null || !(player.getCurrentEquippedItem().getItem() instanceof ItemOpStick))) {
         ItemOpStick.openBaseEditGUI(this, (EntityPlayerMP)player);
      }

      return false;
   }

   public ItemStack getPickedResult(MovingObjectPosition target) {
      ItemStack stack = new ItemStack(FlansMod.flag, 1, 0);
      return stack;
   }

   public void setBaseID(int i) {
      this.ID = i;
   }

   public int getBaseID() {
      return this.ID;
   }

   public int getDefaultOwnerID() {
      return this.defaultTeamID;
   }

   public void setDefaultOwnerID(int id) {
      this.currentTeamID = this.defaultTeamID = id;
      Iterator var2 = this.objects.iterator();

      while(var2.hasNext()) {
         ITeamObject object = (ITeamObject)var2.next();
         object.onBaseSet(id);
      }

   }

   public int getOwnerID() {
      return this.currentTeamID;
   }

   public void setOwnerID(int id) {
      this.currentTeamID = id;
   }

   public boolean isBurning() {
      return false;
   }

}
