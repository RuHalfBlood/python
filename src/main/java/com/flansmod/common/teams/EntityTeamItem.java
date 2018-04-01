package com.flansmod.common.teams;

import com.flansmod.common.PlayerHandler;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.teams.TileEntitySpawner;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class EntityTeamItem extends EntityItem implements IEntityAdditionalSpawnData {

   public TileEntitySpawner spawner;
   public double angle;
   public int xCoord;
   public int yCoord;
   public int zCoord;


   public EntityTeamItem(TileEntitySpawner te, int i) {
      super(te.getWorldObj(), (double)((float)te.xCoord + 0.5F), (double)((float)te.yCoord + 0.5F), (double)((float)te.zCoord + 0.5F), ((ItemStack)te.stacksToSpawn.get(i)).copy());
      te.itemEntities.add(this);
      this.angle = (double)i * 3.141592653589793D * 2.0D / (double)te.stacksToSpawn.size();
      super.motionX = super.motionY = super.motionZ = 0.0D;
      super.lifespan = 1000000000;
      this.spawner = te;
   }

   public EntityTeamItem(World world) {
      super(world);
   }

   public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int i) {}

   public void onUpdate() {
      ++super.ticksExisted;
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      super.prevRotationYaw = super.rotationYaw;
      ++super.age;
      if(super.worldObj.isRemote) {
         this.angle += 0.05D;
         this.setPosition((double)((float)this.xCoord + 0.5F) + Math.cos(this.angle) * 0.30000001192092896D, (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord + 0.5F) + Math.sin(this.angle) * 0.30000001192092896D);
      }

      if(super.worldObj.isRemote) {
         this.extinguish();
      }

   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
      return false;
   }

   public void onCollideWithPlayer(EntityPlayer player) {
      if(!super.worldObj.isRemote) {
         EntityItemPickupEvent event = new EntityItemPickupEvent(player, this);
         if(MinecraftForge.EVENT_BUS.post(event)) {
            return;
         }

         int spawnerTeamID = this.spawner.getTeamID();
         Team spawnerTeam = TeamsManager.getInstance().getTeam(spawnerTeamID);
         Team playerTeam = PlayerHandler.getPlayerData(player).team;
         if(spawnerTeam != null && playerTeam != spawnerTeam) {
            return;
         }

         int var2 = this.getEntityItem().stackSize;
         if(event.getResult() == Result.ALLOW || var2 <= 0 || player.inventory.addItemStackToInventory(this.getEntityItem())) {
            FMLCommonHandler.instance().firePlayerItemPickupEvent(player, this);
            this.playSound("random.pop", 0.2F, ((super.rand.nextFloat() - super.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            player.onItemPickup(this, var2);
            if(this.getEntityItem().stackSize <= 0) {
               this.spawner.itemEntities.remove(this);
               this.setDead();
            }
         }
      }

   }

   public void writeSpawnData(ByteBuf data) {
      if(this.spawner == null) {
         data.writeInt(0);
         data.writeInt(0);
         data.writeInt(0);
      } else {
         data.writeInt(this.spawner.xCoord);
         data.writeInt(this.spawner.yCoord);
         data.writeInt(this.spawner.zCoord);
      }

      data.writeDouble(this.angle);
      NBTTagCompound tags = new NBTTagCompound();
      this.getEntityItem().writeToNBT(tags);
      ByteBufUtils.writeTag(data, tags);
   }

   public void readSpawnData(ByteBuf data) {
      this.xCoord = data.readInt();
      this.yCoord = data.readInt();
      this.zCoord = data.readInt();
      this.angle = data.readDouble();
      this.setEntityItemStack(ItemStack.loadItemStackFromNBT(ByteBufUtils.readTag(data)));
   }

   public void readEntityFromNBT(NBTTagCompound tags) {
      this.setDead();
   }

   public boolean canAttackWithItem() {
      return false;
   }

   public boolean isBurning() {
      return false;
   }
}
