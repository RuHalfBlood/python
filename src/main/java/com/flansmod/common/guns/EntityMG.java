package com.flansmod.common.guns;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.EnumFireMode;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketMGFire;
import com.flansmod.common.network.PacketMGMount;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.teams.EntityGunItem;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

public class EntityMG extends Entity implements IEntityAdditionalSpawnData {

   public int blockX;
   public int blockY;
   public int blockZ;
   public int direction;
   public GunType type;
   public ItemStack ammo;
   public int reloadTimer;
   public int soundDelay;
   public int shootDelay;
   public static List mgs = new ArrayList();
   public EntityPlayer gunner;
   public boolean isShooting;
   public boolean wasShooting = false;
   public int ticksSinceUsed = 0;


   public EntityMG(World world) {
      super(world);
      this.setSize(1.0F, 1.0F);
      super.ignoreFrustumCheck = true;
   }

   public EntityMG(World world, int x, int y, int z, int dir, GunType gunType) {
      super(world);
      this.setSize(1.0F, 1.0F);
      this.blockX = x;
      this.blockY = y;
      this.blockZ = z;
      super.prevPosX = (double)x + 0.5D;
      super.prevPosY = (double)y;
      super.prevPosZ = (double)z + 0.5D;
      this.setPosition((double)x + 0.5D, (double)y, (double)z + 0.5D);
      this.direction = dir;
      super.rotationYaw = 0.0F;
      super.rotationPitch = -60.0F;
      this.type = gunType;
      super.ignoreFrustumCheck = true;
      mgs.add(this);
   }

   public boolean canBeCollidedWith() {
      return !super.isDead;
   }

   public void onUpdate() {
      super.onUpdate();
      if(this.type == null) {
         FlansMod.log("EntityMG.onUpdate() Error: GunType is null (" + this + ")");
         this.setDead();
      } else {
         ++this.ticksSinceUsed;
         if(TeamsManager.mgLife > 0 && this.ticksSinceUsed > TeamsManager.mgLife * 20) {
            this.setDead();
         }

         if(super.worldObj.getBlock(this.blockX, this.blockY - 1, this.blockZ) == null && !super.worldObj.isRemote) {
            this.setDead();
         }

         super.prevRotationYaw = super.rotationYaw;
         super.prevRotationPitch = super.rotationPitch;
         if(this.gunner != null) {
            this.ticksSinceUsed = 0;

            for(super.rotationYaw = this.gunner.rotationYaw - (float)(this.direction * 90); super.rotationYaw < -180.0F; super.rotationYaw += 360.0F) {
               ;
            }

            while(super.rotationYaw > 180.0F) {
               super.rotationYaw -= 360.0F;
            }

            super.rotationPitch = this.gunner.rotationPitch;
            if(super.rotationYaw > this.type.sideViewLimit) {
               super.prevRotationYaw = super.rotationYaw = this.type.sideViewLimit;
            }

            if(super.rotationYaw < -this.type.sideViewLimit) {
               super.prevRotationYaw = super.rotationYaw = -this.type.sideViewLimit;
            }

            float bullet = (float)this.direction * 90.0F + super.rotationYaw;
            double dX = (double)this.type.standBackDist * Math.sin((double)(bullet * 3.1415927F / 180.0F));
            double dZ = -((double)this.type.standBackDist * Math.cos((double)(bullet * 3.1415927F / 180.0F)));
            this.gunner.setPosition((double)this.blockX + 0.5D + dX, (double)this.blockY + this.gunner.getYOffset() - 0.5D, (double)this.blockZ + 0.5D + dZ);
         } else {
            --super.rotationPitch;
         }

         if(super.rotationPitch < this.type.topViewLimit) {
            super.rotationPitch = this.type.topViewLimit;
         }

         if(super.rotationPitch > this.type.bottomViewLimit) {
            super.rotationPitch = this.type.bottomViewLimit;
         }

         if(this.shootDelay > 0) {
            --this.shootDelay;
         }

         if(this.reloadTimer > 0) {
            --this.reloadTimer;
         }

         if(this.ammo != null && this.ammo.getItemDamage() == this.ammo.getMaxDamage()) {
            this.ammo = null;
         }

         if(this.ammo == null && this.gunner != null) {
            int var6 = this.findAmmo(this.gunner);
            if(var6 >= 0) {
               this.ammo = this.gunner.inventory.getStackInSlot(var6);
               if(!this.gunner.capabilities.isCreativeMode) {
                  this.gunner.inventory.setInventorySlotContents(var6, (ItemStack)null);
               }

               this.reloadTimer = this.type.reloadTime;
               PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.type.reloadSound, false);
            }
         }

         if(super.worldObj.isRemote && this.gunner != null && this.gunner == FMLClientHandler.instance().getClient().thePlayer && this.type.mode == EnumFireMode.FULLAUTO) {
            this.checkForShooting();
         }

         if(!super.worldObj.isRemote && this.isShooting) {
            if(this.gunner == null || this.gunner.isDead) {
               this.isShooting = false;
            }

            if(this.ammo == null || this.reloadTimer > 0 || this.shootDelay > 0) {
               return;
            }

            BulletType var7 = BulletType.getBullet(this.ammo.getItem());
            if(this.gunner != null && !this.gunner.capabilities.isCreativeMode) {
               this.ammo.damageItem(1, this.gunner);
            }

            this.shootDelay = this.type.shootDelay;
            super.worldObj.spawnEntityInWorld(((ItemBullet)this.ammo.getItem()).getEntity(super.worldObj, Vec3.createVectorHelper((double)this.blockX + 0.5D, (double)((float)this.blockY + this.type.pivotHeight), (double)this.blockZ + 0.5D), (float)this.direction * 90.0F + super.rotationYaw, super.rotationPitch, this.gunner, this.type.bulletSpread, this.type.damage, this.ammo.getItemDamage(), this.type));
            if(this.soundDelay <= 0) {
               this.soundDelay = this.type.shootSoundLength;
               PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.type.shootSound, this.type.distortSound);
            }
         }

         if(this.soundDelay > 0) {
            --this.soundDelay;
         }

      }
   }

   @SideOnly(Side.CLIENT)
   private void checkForShooting() {
      if(Mouse.isButtonDown(0) && !this.wasShooting && !FlansMod.proxy.isScreenOpen()) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketMGFire(true)));
         this.wasShooting = true;
      } else if(!Mouse.isButtonDown(0) && this.wasShooting) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketMGFire(false)));
         this.wasShooting = false;
      }

   }

   public void mouseHeld(boolean held) {
      this.isShooting = held;
   }

   public boolean attackEntityFrom(DamageSource damagesource, float i) {
      if(damagesource.damageType.equals("player")) {
         Entity player = damagesource.getEntity();
         if(player == this.gunner) {
            if(this.type.mode == EnumFireMode.FULLAUTO) {
               return true;
            }

            if(this.ammo == null || this.reloadTimer > 0 || this.shootDelay > 0) {
               return true;
            }

            BulletType bullet = BulletType.getBullet(this.ammo.getItem());
            if(this.gunner != null && !this.gunner.capabilities.isCreativeMode) {
               this.ammo.damageItem(1, (EntityLiving)player);
            }

            this.shootDelay = this.type.shootDelay;
            if(!super.worldObj.isRemote) {
               super.worldObj.spawnEntityInWorld(((ItemBullet)this.ammo.getItem()).getEntity(super.worldObj, (EntityLivingBase)player, this.type.bulletSpread, this.type.damage, this.type.bulletSpeed, false, this.ammo.getItemDamage(), this.type));
            }

            if(this.soundDelay <= 0) {
               float var10000;
               if(this.type.distortSound) {
                  var10000 = 1.0F / (super.rand.nextFloat() * 0.4F + 0.8F);
               } else {
                  var10000 = 1.0F;
               }

               PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.type.shootSound, this.type.distortSound);
               this.soundDelay = this.type.shootSoundLength;
            }
         } else {
            if(this.gunner != null) {
               return this.gunner.attackEntityFrom(damagesource, i);
            }

            if(TeamsManager.canBreakGuns) {
               this.setDead();
            }
         }
      }

      return true;
   }

   public boolean interactFirst(EntityPlayer player) {
      if(this.gunner != null && this.gunner instanceof EntityPlayer && this.gunner != player) {
         return true;
      } else {
         if(!super.worldObj.isRemote) {
            if(this.gunner == player) {
               this.mountGun(player, false);
               FlansMod.getPacketHandler().sendToAllAround(new PacketMGMount(player, this, false), super.posX, super.posY, super.posZ, 400.0F, super.dimension);
               return true;
            }

            if(PlayerHandler.getPlayerData(player).mountingGun != null && !PlayerHandler.getPlayerData(player).mountingGun.isDead) {
               PlayerHandler.getPlayerData(player).mountingGun.mountGun(player, false);
               return true;
            }

            if(TeamsManager.instance.currentRound != null && PlayerHandler.getPlayerData(player).team == Team.spectators) {
               return true;
            }

            this.mountGun(player, true);
            FlansMod.getPacketHandler().sendToAllAround(new PacketMGMount(player, this, true), super.posX, super.posY, super.posZ, 400.0F, super.dimension);
            if(this.ammo == null) {
               int slot = this.findAmmo(player);
               if(slot >= 0) {
                  this.ammo = player.inventory.getStackInSlot(slot);
                  player.inventory.setInventorySlotContents(slot, (ItemStack)null);
                  this.reloadTimer = this.type.reloadTime;
                  super.worldObj.playSoundAtEntity(this, this.type.reloadSound, 1.0F, 1.0F / (super.rand.nextFloat() * 0.4F + 0.8F));
               }
            }
         }

         return true;
      }
   }

   public void mountGun(EntityPlayer player, boolean mounting) {
      if(player != null) {
         Side side = super.worldObj.isRemote?Side.CLIENT:Side.SERVER;
         if(PlayerHandler.getPlayerData(player, side) != null) {
            if(mounting) {
               this.gunner = player;
               PlayerHandler.getPlayerData(player, side).mountingGun = this;
            } else {
               PlayerHandler.getPlayerData(player, side).mountingGun = null;
               this.gunner = null;
            }

         }
      }
   }

   public int findAmmo(EntityPlayer player) {
      for(int i = 0; i < player.inventory.getSizeInventory(); ++i) {
         ItemStack stack = player.inventory.getStackInSlot(i);
         if(this.type.isAmmo(stack)) {
            return i;
         }
      }

      return -1;
   }

   public void setDead() {
      try {
         if(!super.worldObj.isRemote) {
            if(TeamsManager.weaponDrops == 2) {
               EntityGunItem e = new EntityGunItem(super.worldObj, super.posX, super.posY, super.posZ, new ItemStack(this.type.getItem()), Arrays.asList(new ItemStack[]{this.ammo}));
               super.worldObj.spawnEntityInWorld(e);
            } else if(TeamsManager.weaponDrops == 1) {
               this.dropItem(this.type.getItem(), 1);
               if(this.ammo != null) {
                  this.entityDropItem(this.ammo, 0.5F);
               }
            }
         }

         if(this.gunner != null && PlayerHandler.getPlayerData(this.gunner) != null) {
            PlayerHandler.getPlayerData(this.gunner).mountingGun = null;
         }
      } catch (Exception var2) {
         if(FlansMod.printStackTrace) {
            var2.printStackTrace();
         }
      }

      super.setDead();
   }

   protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
      if(this.type == null) {
         FlansMod.log("EntityMG.writeEntityToNBT() Error: GunType is null (" + this + ")");
         this.setDead();
      } else {
         nbttagcompound.setString("Type", this.type.shortName);
         if(this.ammo != null) {
            nbttagcompound.setTag("Ammo", this.ammo.writeToNBT(new NBTTagCompound()));
         }

         nbttagcompound.setInteger("BlockX", this.blockX);
         nbttagcompound.setInteger("BlockY", this.blockY);
         nbttagcompound.setInteger("BlockZ", this.blockZ);
         nbttagcompound.setByte("Dir", (byte)this.direction);
      }
   }

   protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
      this.type = GunType.getGun(nbttagcompound.getString("Type"));
      if(this.type == null) {
         FlansMod.log("EntityMG.readEntityFromNBT() Error: GunType is null (" + this + ")");
         this.setDead();
      } else {
         this.blockX = nbttagcompound.getInteger("BlockX");
         this.blockY = nbttagcompound.getInteger("BlockY");
         this.blockZ = nbttagcompound.getInteger("BlockZ");
         this.direction = nbttagcompound.getByte("Dir");
         this.ammo = ItemStack.loadItemStackFromNBT(nbttagcompound.getCompoundTag("Ammo"));
      }
   }

   protected void entityInit() {}

   public void writeSpawnData(ByteBuf data) {
      ByteBufUtils.writeUTF8String(data, this.type.shortName);
      data.writeInt(this.direction);
      data.writeInt(this.blockX);
      data.writeInt(this.blockY);
      data.writeInt(this.blockZ);
      ByteBufUtils.writeItemStack(data, this.ammo);
   }

   public void readSpawnData(ByteBuf data) {
      try {
         this.type = GunType.getGun(ByteBufUtils.readUTF8String(data));
         this.direction = data.readInt();
         this.blockX = data.readInt();
         this.blockY = data.readInt();
         this.blockZ = data.readInt();
         this.ammo = ByteBufUtils.readItemStack(data);
      } catch (Exception var3) {
         FlansMod.log("Failed to retreive gun type from server.");
         super.setDead();
         var3.printStackTrace();
      }

   }

   public ItemStack getPickedResult(MovingObjectPosition target) {
      ItemStack stack = new ItemStack(this.type.item, 1, 0);
      return stack;
   }

}
