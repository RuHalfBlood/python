package com.flansmod.common.guns;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.guns.AAGunType;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.network.PacketAAGunAngles;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketMGFire;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

public class EntityAAGun extends Entity implements IEntityAdditionalSpawnData {

   private int sUpdateTime;
   private double sPosX;
   private double sPosY;
   private double sPosZ;
   private double sYaw;
   private double sPitch;
   private double field_9388_j;
   private double field_9387_k;
   private double field_9386_l;
   private int health;
   private int shootDelay;
   public float gunYaw;
   public float gunPitch;
   public float prevGunYaw;
   public float prevGunPitch;
   public float[] barrelRecoil;
   public AAGunType type;
   public Entity towedByEntity;
   public ItemStack[] ammo;
   public int reloadTimer;
   public int currentBarrel;
   public boolean mouseHeld;
   public boolean wasShooting;
   public int shootcnt;
   public int shootTimeCount;
   public EntityPlayer placer;
   public String placerName;
   public Entity target;
   public static final float targetAcquireInterval = 10.0F;
   public int ticksSinceUsed;


   public EntityAAGun(World world) {
      super(world);
      this.shootcnt = 1;
      this.shootTimeCount = 0;
      this.placer = null;
      this.placerName = null;
      this.target = null;
      this.ticksSinceUsed = 0;
      super.preventEntitySpawning = true;
      this.setSize(2.0F, 2.0F);
      super.yOffset = 0.0F;
      this.gunYaw = 0.0F;
      this.gunPitch = 0.0F;
      this.shootDelay = 0;
   }

   public EntityAAGun(World world, AAGunType type1, double d, double d1, double d2, EntityPlayer p) {
      this(world);
      this.placer = p;
      this.placerName = p.getCommandSenderName();
      this.type = type1;
      this.initType();
      this.setPosition(d, d1, d2);
   }

   public void setPosition(double d, double d1, double d2) {
      super.posX = d;
      super.posY = d1;
      super.posZ = d2;
      float f = super.width / 2.0F;
      float f1 = super.height;
      super.boundingBox.setBounds(d - (double)f, d1 - (double)super.yOffset + (double)super.ySize, d2 - (double)f, d + (double)f, d1 - (double)super.yOffset + (double)super.ySize + (double)f1, d2 + (double)f);
   }

   public void setPositionAndRotation2(double d, double d1, double d2, float f, float f1, int i) {
      this.sPosX = d;
      this.sPosY = d1;
      this.sPosZ = d2;
      this.sYaw = (double)f;
      this.sPitch = (double)f1;
      this.sUpdateTime = i;
   }

   public void initType() {
      this.health = this.type.health;
      this.barrelRecoil = new float[this.type.numBarrels];
      this.ammo = new ItemStack[this.type.numBarrels];
   }

   protected void entityInit() {}

   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {}

   public void applyEntityCollision(Entity entity) {}

   public AxisAlignedBB getCollisionBox(Entity entity) {
      return entity.boundingBox;
   }

   public AxisAlignedBB getBoundingBox() {
      return super.boundingBox;
   }

   public boolean canBePushed() {
      return false;
   }

   public double getMountedYOffset() {
      return 0.0D;
   }

   public void setMouseHeld(boolean held) {
      this.mouseHeld = held;
   }

   public boolean attackEntityFrom(DamageSource damagesource, float i) {
      if(damagesource.damageType.equals("player")) {
         Entity player = damagesource.getEntity();
         if(player != super.riddenByEntity) {
            if(super.riddenByEntity != null) {
               return super.riddenByEntity.attackEntityFrom(damagesource, i);
            }

            if(TeamsManager.canBreakGuns) {
               this.setDead();
            }
         }
      } else {
         this.setBeenAttacked();
         this.health = (int)((float)this.health - i);
         if(!super.worldObj.isRemote && this.health <= 0) {
            this.setDead();
         }
      }

      return true;
   }

   public Vec3 rotate(double x, double y, double z) {
      double cosYaw = Math.cos((double)(180.0F - this.gunYaw * 3.1415927F / 180.0F));
      double sinYaw = Math.sin((double)(180.0F - this.gunYaw * 3.1415927F / 180.0F));
      double cosPitch = Math.cos((double)(this.gunPitch * 3.1415927F / 180.0F));
      double sinPitch = Math.sin((double)(this.gunPitch * 3.1415927F / 180.0F));
      double newX = x * cosYaw + (y * sinPitch + z * cosPitch) * sinYaw;
      double newY = y * cosPitch - z * sinPitch;
      double newZ = -x * sinYaw + (y * sinPitch + z * cosPitch) * cosYaw;
      return Vec3.createVectorHelper(newX, newY, newZ);
   }

   public boolean canBeCollidedWith() {
      return !super.isDead;
   }

   public void onUpdate() {
      super.onUpdate();
      if(this.type == null) {
         FlansMod.log("EntityAAGun.onUpdate() Error: AAGunType is null (" + this + ")");
         this.setDead();
      } else {
         this.prevGunYaw = this.gunYaw;
         this.prevGunPitch = this.gunPitch;
         ++this.ticksSinceUsed;
         if(TeamsManager.aaLife > 0 && this.ticksSinceUsed > TeamsManager.aaLife * 20) {
            this.setDead();
         }

         if(super.riddenByEntity != null) {
            this.ticksSinceUsed = 0;
            this.gunYaw = super.riddenByEntity.rotationYaw - 90.0F;
            this.gunPitch = super.riddenByEntity.rotationPitch;
         }

         if(this.gunPitch > this.type.bottomViewLimit) {
            this.gunPitch = this.type.bottomViewLimit;
         }

         if(this.gunPitch < -this.type.topViewLimit) {
            this.gunPitch = -this.type.topViewLimit;
         }

         int j;
         for(j = 0; j < this.type.numBarrels; ++j) {
            this.barrelRecoil[j] *= 0.9F;
         }

         if(this.shootDelay > 0) {
            --this.shootDelay;
         }

         double bullet;
         double d9;
         double d12;
         double var12;
         if(this.isSentry()) {
            if(this.target != null && this.target.isDead) {
               this.target = null;
            }

            if(this.target == null && (float)super.ticksExisted % 10.0F == 0.0F) {
               this.target = this.getValidTarget();
            }

            if(this.target != null) {
               var12 = this.target.posX - super.posX;
               bullet = this.target.posY - (super.posY + 1.5D);
               d9 = this.target.posZ - super.posZ;
               d12 = Math.sqrt(var12 * var12 + bullet * bullet + d9 * d9);
               if(d12 > (double)this.type.targetRange) {
                  this.target = null;
               } else {
                  float newYaw;
                  float newPitch;
                  if(!this.type.canShootHomingMissile) {
                     newYaw = 180.0F + (float)Math.atan2(d9, var12) * 180.0F / 3.14159F;
                     newPitch = -((float)Math.atan2(bullet, Math.sqrt(var12 * var12 + d9 * d9))) * 180.0F / 3.14159F;
                     float turnSpeed = 0.25F;
                     this.gunYaw += (newYaw - this.gunYaw) * turnSpeed;
                     this.gunPitch += (newPitch - this.gunPitch) * turnSpeed;
                  } else {
                     newYaw = 180.0F + (float)Math.atan2(d9, var12) * 180.0F / 3.14159F;
                     newPitch = -((float)Math.atan2(bullet, Math.sqrt(var12 * var12 + d9 * d9))) * 180.0F / 3.14159F;
                     this.gunYaw = newYaw;
                     this.gunPitch = newPitch;
                  }
               }
            }
         }

         if(!super.onGround && !super.worldObj.isRemote) {
            super.motionY -= 0.0245D;
         }

         super.motionX *= 0.5D;
         super.motionZ *= 0.5D;
         this.moveEntity(super.motionX, super.motionY, super.motionZ);
         if(super.worldObj.isRemote && super.riddenByEntity != null && super.riddenByEntity == FMLClientHandler.instance().getClient().thePlayer) {
            this.checkForShooting();
         }

         if(super.worldObj.isRemote) {
            if(this.sUpdateTime > 0) {
               var12 = super.posX + (this.sPosX - super.posX) / (double)this.sUpdateTime;
               bullet = super.posY + (this.sPosY - super.posY) / (double)this.sUpdateTime;
               d9 = super.posZ + (this.sPosZ - super.posZ) / (double)this.sUpdateTime;

               for(d12 = this.sYaw - (double)super.rotationYaw; d12 < -180.0D; d12 += 360.0D) {
                  ;
               }

               while(d12 >= 180.0D) {
                  d12 -= 360.0D;
               }

               super.rotationYaw = (float)((double)super.rotationYaw + d12 / (double)this.sUpdateTime);
               super.rotationPitch = (float)((double)super.rotationPitch + (this.sPitch - (double)super.rotationPitch) / (double)this.sUpdateTime);
               --this.sUpdateTime;
               this.setPosition(var12, bullet, d9);
               this.setRotation(super.rotationYaw, super.rotationPitch);
            }

         } else {
            if(super.riddenByEntity != null && super.riddenByEntity.isDead) {
               super.riddenByEntity = null;
            }

            int ammoSlot;
            if(this.reloadTimer > 0) {
               --this.reloadTimer;
            } else {
               for(j = 0; j < this.type.numBarrels; ++j) {
                  if(this.ammo[j] != null && this.ammo[j].getItemDamage() == this.ammo[j].getMaxDamage()) {
                     this.ammo[j] = null;
                  }

                  if(this.ammo[j] == null && super.riddenByEntity != null && super.riddenByEntity instanceof EntityPlayer) {
                     ammoSlot = this.findAmmo((EntityPlayer)super.riddenByEntity);
                     if(ammoSlot >= 0) {
                        this.ammo[j] = ((EntityPlayer)super.riddenByEntity).inventory.getStackInSlot(ammoSlot);
                        if(!((EntityPlayer)super.riddenByEntity).capabilities.isCreativeMode) {
                           ((EntityPlayer)super.riddenByEntity).inventory.decrStackSize(ammoSlot, 1);
                        }

                        this.reloadTimer = this.type.reloadTime;
                        PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.type.reloadSound, true);
                     }
                  }
               }
            }

            if(this.shootcnt > 0 && this.shootcnt < 10) {
               ++this.shootcnt;
            }

            if(!super.worldObj.isRemote && this.reloadTimer <= 0 && this.shootDelay <= 0) {
               BulletType var14;
               if(this.mouseHeld && super.riddenByEntity != null && super.riddenByEntity instanceof EntityPlayer) {
                  EntityPlayer var13 = (EntityPlayer)super.riddenByEntity;

                  for(ammoSlot = 0; ammoSlot < this.type.numBarrels; ++ammoSlot) {
                     if(this.shootDelay <= 0 && this.ammo[ammoSlot] != null && (!this.type.fireAlternately || this.type.fireAlternately && this.currentBarrel == ammoSlot)) {
                        var14 = BulletType.getBullet(this.ammo[ammoSlot].getItem());
                        if(!((EntityPlayer)super.riddenByEntity).capabilities.isCreativeMode) {
                           this.ammo[ammoSlot].damageItem(1, var13);
                        }

                        this.shootDelay = this.type.shootDelay;
                        this.barrelRecoil[ammoSlot] = (float)this.type.recoil;
                        super.worldObj.spawnEntityInWorld(((ItemBullet)this.ammo[ammoSlot].getItem()).getEntity(super.worldObj, this.rotate((double)this.type.barrelX[this.currentBarrel] / 16.0D - (double)this.type.barrelZ[this.currentBarrel] / 16.0D, (double)this.type.barrelY[this.currentBarrel] / 16.0D, (double)this.type.barrelX[this.currentBarrel] / 16.0D + (double)this.type.barrelZ[this.currentBarrel] / 16.0D).addVector(super.posX, super.posY, super.posZ), this.gunYaw + 90.0F, this.gunPitch, var13, (float)this.type.accuracy, (float)this.type.damage, this.ammo[ammoSlot].getItemDamage(), this.type));
                        PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.type.shootSound, true);
                     }
                  }

                  this.currentBarrel = (this.currentBarrel + 1) % this.type.numBarrels;
               } else if(this.target != null && this.shootcnt > 5) {
                  for(j = 0; j < this.type.numBarrels; ++j) {
                     ammoSlot = j;
                     if(this.type.shareAmmo) {
                        ammoSlot = 0;
                     }

                     if(this.shootDelay <= 0 && this.ammo[ammoSlot] != null && (!this.type.fireAlternately || this.type.fireAlternately && this.currentBarrel == ammoSlot)) {
                        var14 = BulletType.getBullet(this.ammo[ammoSlot].getItem());
                        this.ammo[ammoSlot].setItemDamage(this.ammo[ammoSlot].getItemDamage() + 1);
                        this.shootDelay = this.type.shootDelay;
                        this.barrelRecoil[ammoSlot] = (float)this.type.recoil;
                        super.worldObj.spawnEntityInWorld(((ItemBullet)this.ammo[ammoSlot].getItem()).getEntity(super.worldObj, this.rotate((double)this.type.barrelX[this.currentBarrel] / 16.0D - (double)this.type.barrelZ[this.currentBarrel] / 16.0D, (double)this.type.barrelY[this.currentBarrel] / 16.0D, (double)this.type.barrelX[this.currentBarrel] / 16.0D + (double)this.type.barrelZ[this.currentBarrel] / 16.0D).addVector(super.posX, super.posY + 1.5D, super.posZ), this.gunYaw + 90.0F, this.gunPitch, this.placer, (float)this.type.accuracy, (float)this.type.damage, this.ammo[ammoSlot].getItemDamage(), this.type));
                        PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.type.shootSound, true);
                        if(this.shootTimeCount >= this.type.countExplodeAfterShoot - 1 && this.type.countExplodeAfterShoot != -1 && !super.worldObj.isRemote) {
                           this.setDead();
                        }

                        ++this.shootTimeCount;
                     }
                  }

                  this.currentBarrel = (this.currentBarrel + 1) % this.type.numBarrels;
               }
            }

            if(!super.worldObj.isRemote) {
               FlansMod.getPacketHandler().sendToAllAround(new PacketAAGunAngles(this), super.posX, super.posY, super.posZ, 50.0F, super.dimension);
            }

         }
      }
   }

   public boolean isSentry() {
      return this.type.targetMobs || this.type.targetPlayers || this.type.targetPlanes || this.type.targetVehicles || this.type.targetMechas;
   }

   public Entity getValidTarget() {
      if(super.worldObj.isRemote) {
         return null;
      } else {
         if(this.placer == null && this.placerName != null) {
            this.placer = super.worldObj.getPlayerEntityByName(this.placerName);
         }

         Iterator var1 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.expand((double)this.type.targetRange, (double)this.type.targetRange, (double)this.type.targetRange)).iterator();

         Entity candidateEntity;
         while(true) {
            do {
               do {
                  if(!var1.hasNext()) {
                     return null;
                  }

                  Object obj = var1.next();
                  candidateEntity = (Entity)obj;
               } while((!this.type.targetMobs || !(candidateEntity instanceof EntityMob)) && (!this.type.targetPlayers || !(candidateEntity instanceof EntityPlayer)) && (!this.type.targetPlanes || !(candidateEntity instanceof EntityPlane)) && (!this.type.targetVehicles || !(candidateEntity instanceof EntityVehicle)));
            } while(candidateEntity.getDistanceToEntity(this) >= this.type.targetRange);

            if(!(candidateEntity instanceof EntityPlayer)) {
               break;
            }

            if(candidateEntity != this.placer && !candidateEntity.getCommandSenderName().equals(this.placerName)) {
               if(!TeamsManager.enabled || TeamsManager.getInstance().currentRound == null || this.placer == null) {
                  break;
               }

               PlayerData placerData = PlayerHandler.getPlayerData(this.placer, super.worldObj.isRemote?Side.CLIENT:Side.SERVER);
               PlayerData candidateData = PlayerHandler.getPlayerData((EntityPlayer)candidateEntity, super.worldObj.isRemote?Side.CLIENT:Side.SERVER);
               if(candidateData.team != Team.spectators && candidateData.team != null) {
                  EntityPlayerMP var10001 = (EntityPlayerMP)this.placer;
                  if(!TeamsManager.getInstance().currentRound.gametype.playerCanAttack(var10001, placerData.team, (EntityPlayerMP)candidateEntity, candidateData.team)) {
                     continue;
                  }
                  break;
               }
            }
         }

         return candidateEntity;
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

   public void setDead() {
      super.setDead();
      if(!super.worldObj.isRemote) {
         if(this.type.isDropThis) {
            this.dropItem(this.type.getItem(), 1);
         }

         ItemStack[] var1 = this.ammo;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            ItemStack stack = var1[var3];
            if(stack != null) {
               this.entityDropItem(stack, 0.5F);
            }
         }

      }
   }

   public void updateRiderPosition() {
      if(super.riddenByEntity != null) {
         double x = (double)this.type.gunnerX / 16.0D;
         double y = (double)this.type.gunnerY / 16.0D;
         double z = (double)this.type.gunnerZ / 16.0D;
         double cosYaw = Math.cos((double)(-this.gunYaw) / 180.0D * 3.141592653589793D);
         double sinYaw = Math.sin((double)(-this.gunYaw) / 180.0D * 3.141592653589793D);
         double cosPitch = Math.cos((double)this.gunPitch / 180.0D * 3.141592653589793D);
         double sinPitch = Math.sin((double)this.gunPitch / 180.0D * 3.141592653589793D);
         double x2 = x * cosYaw + z * sinYaw;
         double z2 = -x * sinYaw + z * cosYaw;
         super.riddenByEntity.setPosition(super.posX + x2, super.posY + y, super.posZ + z2);
      }
   }

   protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
      if(this.type == null) {
         FlansMod.log("EntityAAGun.writeEntityToNBT() Error: AAGunType is null (" + this + ")");
         this.setDead();
      } else {
         nbttagcompound.setString("Type", this.type.shortName);
         nbttagcompound.setInteger("Health", this.health);
         nbttagcompound.setFloat("RotationYaw", super.rotationYaw);
         nbttagcompound.setFloat("RotationPitch", super.rotationPitch);

         for(int i = 0; i < this.type.numBarrels; ++i) {
            if(this.ammo[i] != null) {
               nbttagcompound.setTag("Ammo " + i, this.ammo[i].writeToNBT(new NBTTagCompound()));
            }
         }

         nbttagcompound.setString("Placer", this.placer.getCommandSenderName());
      }
   }

   protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
      this.type = AAGunType.getAAGun(nbttagcompound.getString("Type"));
      if(this.type == null) {
         FlansMod.log("EntityAAGun.readEntityFromNBT() Error: AAGunType is null (" + this + ")");
         this.setDead();
      } else {
         this.initType();
         this.health = nbttagcompound.getInteger("Health");
         super.rotationYaw = nbttagcompound.getFloat("RotationYaw");
         super.rotationPitch = nbttagcompound.getFloat("RotationPitch");

         for(int i = 0; i < this.type.numBarrels; ++i) {
            this.ammo[i] = ItemStack.loadItemStackFromNBT(nbttagcompound.getCompoundTag("Ammo " + i));
         }

         this.placerName = nbttagcompound.getString("Placer");
      }
   }

   public float getShadowSize() {
      return 0.0F;
   }

   public boolean interactFirst(EntityPlayer entityplayer) {
      if(super.riddenByEntity != null && super.riddenByEntity instanceof EntityPlayer && super.riddenByEntity != entityplayer) {
         return true;
      } else {
         if(!super.worldObj.isRemote) {
            if(super.riddenByEntity == entityplayer) {
               entityplayer.mountEntity((Entity)null);
               return true;
            }

            if(!this.isSentry()) {
               entityplayer.mountEntity(this);
            }

            for(int i = 0; i < (this.type.shareAmmo?1:this.type.numBarrels); ++i) {
               if(this.ammo[i] == null) {
                  int slot = this.findAmmo(entityplayer);
                  if(slot >= 0) {
                     this.ammo[i] = entityplayer.inventory.getStackInSlot(slot).copy();
                     this.ammo[i].stackSize = 1;
                     if(!entityplayer.capabilities.isCreativeMode) {
                        entityplayer.inventory.decrStackSize(slot, 1);
                     }

                     this.reloadTimer = this.type.reloadTime;
                     super.worldObj.playSoundAtEntity(this, this.type.reloadSound, 1.0F, 1.0F / (super.rand.nextFloat() * 0.4F + 0.8F));
                  }
               }
            }
         }

         return true;
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

   public void writeSpawnData(ByteBuf data) {
      ByteBufUtils.writeUTF8String(data, this.type.shortName);
   }

   public void readSpawnData(ByteBuf data) {
      try {
         this.type = AAGunType.getAAGun(ByteBufUtils.readUTF8String(data));
         this.initType();
      } catch (Exception var3) {
         FlansMod.log("Failed to retreive AA gun type from server.");
         super.setDead();
         var3.printStackTrace();
      }

   }

   public boolean canRiderInteract() {
      return false;
   }

   public ItemStack getPickedResult(MovingObjectPosition target) {
      ItemStack stack = new ItemStack(this.type.item, 1, 0);
      return stack;
   }
}
