package com.flansmod.common.driveables.mechas;

import com.flansmod.client.debug.EntityDebugVector;
import com.flansmod.client.gui.GuiDriveableController;
import com.flansmod.client.model.GunAnimations;
import com.flansmod.common.FlansMod;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.driveables.DriveableData;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.mechas.EnumMechaSlotType;
import com.flansmod.common.driveables.mechas.ItemMechaAddon;
import com.flansmod.common.driveables.mechas.MechaInventory;
import com.flansmod.common.driveables.mechas.MechaItemType;
import com.flansmod.common.driveables.mechas.MechaType;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.EnumFireMode;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.InventoryHelper;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketDriveableDamage;
import com.flansmod.common.network.PacketDriveableGUI;
import com.flansmod.common.network.PacketDriveableKey;
import com.flansmod.common.network.PacketMechaControl;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.tools.ItemTool;
import com.flansmod.common.vector.Vector3f;
import com.flansmod.common.vector.Vector3i;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class EntityMecha extends EntityDriveable {

   private int ticksSinceUsed;
   public int toggleTimer;
   private float moveX;
   private float moveZ;
   public RotatedAxes legAxes;
   public float prevLegsYaw;
   private int jumpDelay;
   public MechaInventory inventory;
   public float legSwing;
   public int shootDelayLeft;
   public int shootDelayRight;
   public int soundDelayLeft;
   public int soundDelayRight;
   public Vector3i breakingBlock;
   public float breakingProgress;
   private float rocketTimer;
   private int diamondTimer;
   public int legAnimTimer;
   public int legAnimMax;
   public int animState;
   public int targetLeftUpper;
   public int targetLeftLower;
   public int targetLeftFoot;
   public int targetLeftUpperSpeed;
   public int targetLeftLowerSpeed;
   public int targetLeftFootSpeed;
   int targetRightUpper;
   int targetRightLower;
   int targetRightFoot;
   int targetRightUpperSpeed;
   int targetRightLowerSpeed;
   int targetRightFootSpeed;
   public float leftLegUpperAngle;
   public float leftLegLowerAngle;
   public float leftFootAngle;
   public float rightLegUpperAngle;
   public float rightLegLowerAngle;
   public float rightFootAngle;
   public float prevLeftLegUpperAngle;
   public float prevLeftLegLowerAngle;
   public float prevLeftFootAngle;
   public float prevRightLegUpperAngle;
   public float prevRightLegLowerAngle;
   public float prevRightFootAngle;
   public float legPosition;
   public int stompDelay;
   public GunAnimations leftAnimations;
   public GunAnimations rightAnimations;
   boolean couldNotFindFuel;


   public EntityMecha(World world) {
      super(world);
      this.toggleTimer = 0;
      this.moveX = 0.0F;
      this.moveZ = 0.0F;
      this.prevLegsYaw = 0.0F;
      this.jumpDelay = 0;
      this.legSwing = 0.0F;
      this.shootDelayLeft = 0;
      this.shootDelayRight = 0;
      this.soundDelayLeft = 0;
      this.soundDelayRight = 0;
      this.breakingBlock = null;
      this.breakingProgress = 0.0F;
      this.rocketTimer = 0.0F;
      this.diamondTimer = 0;
      this.legAnimTimer = 1;
      this.legAnimMax = 1;
      this.targetLeftUpper = 0;
      this.targetLeftLower = 0;
      this.targetLeftFoot = 0;
      this.targetLeftUpperSpeed = 1;
      this.targetLeftLowerSpeed = 1;
      this.targetLeftFootSpeed = 1;
      this.targetRightUpper = 0;
      this.targetRightLower = 0;
      this.targetRightFoot = 0;
      this.targetRightUpperSpeed = 1;
      this.targetRightLowerSpeed = 1;
      this.targetRightFootSpeed = 1;
      this.leftLegUpperAngle = 0.0F;
      this.leftLegLowerAngle = 0.0F;
      this.leftFootAngle = 0.0F;
      this.rightLegUpperAngle = 0.0F;
      this.rightLegLowerAngle = 0.0F;
      this.rightFootAngle = 0.0F;
      this.prevLeftLegUpperAngle = 0.0F;
      this.prevLeftLegLowerAngle = 0.0F;
      this.prevLeftFootAngle = 0.0F;
      this.prevRightLegUpperAngle = 0.0F;
      this.prevRightLegLowerAngle = 0.0F;
      this.prevRightFootAngle = 0.0F;
      this.legPosition = 0.0F;
      this.leftAnimations = new GunAnimations();
      this.rightAnimations = new GunAnimations();
      this.setSize(2.0F, 3.0F);
      super.stepHeight = 3.0F;
      this.legAxes = new RotatedAxes();
      this.inventory = new MechaInventory(this);
      super.isMecha = true;
   }

   public EntityMecha(World world, double x, double y, double z, MechaType type, DriveableData data, NBTTagCompound tags) {
      super(world, type, data);
      this.toggleTimer = 0;
      this.moveX = 0.0F;
      this.moveZ = 0.0F;
      this.prevLegsYaw = 0.0F;
      this.jumpDelay = 0;
      this.legSwing = 0.0F;
      this.shootDelayLeft = 0;
      this.shootDelayRight = 0;
      this.soundDelayLeft = 0;
      this.soundDelayRight = 0;
      this.breakingBlock = null;
      this.breakingProgress = 0.0F;
      this.rocketTimer = 0.0F;
      this.diamondTimer = 0;
      this.legAnimTimer = 1;
      this.legAnimMax = 1;
      this.targetLeftUpper = 0;
      this.targetLeftLower = 0;
      this.targetLeftFoot = 0;
      this.targetLeftUpperSpeed = 1;
      this.targetLeftLowerSpeed = 1;
      this.targetLeftFootSpeed = 1;
      this.targetRightUpper = 0;
      this.targetRightLower = 0;
      this.targetRightFoot = 0;
      this.targetRightUpperSpeed = 1;
      this.targetRightLowerSpeed = 1;
      this.targetRightFootSpeed = 1;
      this.leftLegUpperAngle = 0.0F;
      this.leftLegLowerAngle = 0.0F;
      this.leftFootAngle = 0.0F;
      this.rightLegUpperAngle = 0.0F;
      this.rightLegLowerAngle = 0.0F;
      this.rightFootAngle = 0.0F;
      this.prevLeftLegUpperAngle = 0.0F;
      this.prevLeftLegLowerAngle = 0.0F;
      this.prevLeftFootAngle = 0.0F;
      this.prevRightLegUpperAngle = 0.0F;
      this.prevRightLegLowerAngle = 0.0F;
      this.prevRightFootAngle = 0.0F;
      this.legPosition = 0.0F;
      this.leftAnimations = new GunAnimations();
      this.rightAnimations = new GunAnimations();
      this.legAxes = new RotatedAxes();
      this.setSize(2.0F, 3.0F);
      super.stepHeight = 3.0F;
      this.setPosition(x, y, z);
      this.initType(type, false);
      this.inventory = new MechaInventory(this, tags);
      super.isMecha = true;
   }

   public EntityMecha(World world, double x, double y, double z, EntityPlayer placer, MechaType type, DriveableData data, NBTTagCompound tags) {
      this(world, x, y, z, type, data, tags);
      this.rotateYaw(placer.rotationYaw + 90.0F);
      this.legAxes.rotateGlobalYaw(placer.rotationYaw + 90.0F);
      this.prevLegsYaw = this.legAxes.getYaw();
      super.isMecha = true;
   }

   protected void initType(DriveableType type, boolean clientSide) {
      super.initType(type, clientSide);
      this.setSize(((MechaType)type).width, ((MechaType)type).height);
      super.stepHeight = (float)((MechaType)type).stepHeight;
      super.isMecha = true;
   }

   protected void writeEntityToNBT(NBTTagCompound tag) {
      super.writeEntityToNBT(tag);
      tag.setFloat("LegsYaw", this.legAxes.getYaw());
      tag.setTag("Inventory", this.inventory.writeToNBT(new NBTTagCompound()));
      super.isMecha = true;
   }

   protected void readEntityFromNBT(NBTTagCompound tag) {
      super.readEntityFromNBT(tag);
      this.legAxes.setAngles(tag.getFloat("LegsYaw"), 0.0F, 0.0F);
      this.inventory.readFromNBT(tag.getCompoundTag("Inventory"));
      super.isMecha = true;
   }

   public void writeSpawnData(ByteBuf data) {
      super.writeSpawnData(data);
      ByteBufUtils.writeTag(data, this.inventory.writeToNBT(new NBTTagCompound()));
      super.isMecha = true;
   }

   public void readSpawnData(ByteBuf data) {
      super.readSpawnData(data);
      this.legAxes.rotateGlobalYaw(super.axes.getYaw());
      this.prevLegsYaw = this.legAxes.getYaw();
      this.inventory.readFromNBT(ByteBufUtils.readTag(data));
      super.isMecha = true;
   }

   public void onMouseMoved(int deltaX, int deltaY) {}

   public boolean interactFirst(EntityPlayer entityplayer) {
      if(super.isDead) {
         return false;
      } else if(super.worldObj.isRemote) {
         return false;
      } else {
         ItemStack currentItem = entityplayer.getCurrentEquippedItem();
         if(currentItem != null && currentItem.getItem() instanceof ItemTool && ((ItemTool)currentItem.getItem()).type.healDriveables) {
            return true;
         } else {
            MechaType type = this.getMechaType();

            for(int i = 0; i <= type.numPassengers; ++i) {
               if(super.seats[i].interactFirst(entityplayer)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   public MechaType getMechaType() {
      return MechaType.getMecha(super.driveableType);
   }

   public boolean pressKey(int key, EntityPlayer player) {
      MechaType type = this.getMechaType();
      DriveableData data = this.getDriveableData();
      if(super.worldObj.isRemote && (key == 6 || key == 8 || key == 9)) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableKey(key)));
         return true;
      } else {
         switch(key) {
         case 0:
            return true;
         case 1:
            return true;
         case 2:
            return true;
         case 3:
            return true;
         case 4:
            boolean canThrustCreatively = super.seats != null && super.seats[0] != null && super.seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
            if(super.onGround && this.jumpDelay == 0 && (canThrustCreatively || data.fuelInTank > data.engine.fuelConsumption) && this.isPartIntact(EnumDriveablePart.hips)) {
               this.jumpDelay = 20;
               super.motionY += (double)type.jumpVelocity;
               if(!canThrustCreatively) {
                  data.fuelInTank -= data.engine.fuelConsumption;
               }
            }

            return true;
         case 5:
            return true;
         case 6:
            super.seats[0].riddenByEntity.mountEntity((Entity)null);
            return true;
         case 7:
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableGUI(4)));
            ((EntityPlayer)super.seats[0].riddenByEntity).openGui(FlansMod.INSTANCE, 10, super.worldObj, super.chunkCoordX, super.chunkCoordY, super.chunkCoordZ);
            return true;
         case 8:
            return true;
         case 9:
            return true;
         case 10:
            return true;
         case 11:
            return true;
         case 12:
            return true;
         case 13:
            return true;
         case 14:
            return true;
         case 15:
            return true;
         case 16:
            return true;
         case 17:
            return true;
         default:
            return false;
         }
      }
   }

   private boolean useItem(boolean left) {
      if(left) {
         if(!this.isPartIntact(EnumDriveablePart.leftArm)) {
            return true;
         }
      } else if(!this.isPartIntact(EnumDriveablePart.rightArm)) {
         return true;
      }

      boolean creative = !(super.seats[0].riddenByEntity instanceof EntityPlayer) || ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
      ItemStack heldStack = left?this.inventory.getStackInSlot(EnumMechaSlotType.leftTool):this.inventory.getStackInSlot(EnumMechaSlotType.rightTool);
      if(heldStack == null) {
         return false;
      } else {
         Item heldItem = heldStack.getItem();
         MechaType mechaType = this.getMechaType();
         if(heldItem instanceof ItemMechaAddon) {
            MechaItemType gunItem = ((ItemMechaAddon)heldItem).type;
            float gunType = gunItem.reach * mechaType.reach;
            Vector3f delay = new Vector3f((double)((float)mechaType.seats[0].x / 16.0F), (double)((float)mechaType.seats[0].y / 16.0F) + super.seats[0].riddenByEntity.getMountedYOffset(), (double)((float)mechaType.seats[0].z / 16.0F));
            delay = super.axes.findLocalVectorGlobally(delay);
            Vector3f.add(delay, new Vector3f(super.posX, super.posY, super.posZ), delay);
            Vector3f bulletID = super.axes.findLocalVectorGlobally(super.seats[0].looking.findLocalVectorGlobally(new Vector3f(gunType, 0.0F, 0.0F)));
            super.worldObj.spawnEntityInWorld(new EntityDebugVector(super.worldObj, delay, bulletID, 20));
            Vector3f bulletStack = Vector3f.add(bulletID, delay, (Vector3f)null);
            MovingObjectPosition pumpDelay = super.worldObj.rayTraceBlocks(delay.toVec3(), bulletStack.toVec3());
            if(pumpDelay != null && pumpDelay.typeOfHit == MovingObjectType.BLOCK) {
               if(this.breakingBlock == null || this.breakingBlock.x != pumpDelay.blockX || this.breakingBlock.y != pumpDelay.blockY || this.breakingBlock.z != pumpDelay.blockZ) {
                  this.breakingProgress = 0.0F;
               }

               this.breakingBlock = new Vector3i(pumpDelay.blockX, pumpDelay.blockY, pumpDelay.blockZ);
            }
         } else if(heldItem instanceof ItemGun) {
            ItemGun var15 = (ItemGun)heldItem;
            GunType var16 = var15.type;
            int var17 = left?this.shootDelayLeft:this.shootDelayRight;
            if(var17 <= 0) {
               int var18 = 0;

               ItemStack var19;
               for(var19 = null; var18 < var16.numAmmoItemsInGun; ++var18) {
                  ItemStack var20 = var15.getBulletItemStack(heldStack, var18);
                  if(var20 != null && var20.getItem() != null && var20.getItemDamage() < var20.getMaxDamage()) {
                     var19 = var20;
                     break;
                  }
               }

               if(var19 == null) {
                  var15.reload(heldStack, var16, super.worldObj, this, super.driveableData, this.infiniteAmmo() || creative, false);
               } else if(var19.getItem() instanceof ItemBullet) {
                  this.shoot(heldStack, var16, var19, creative, left);
                  if(super.worldObj.isRemote) {
                     int var21 = var16.model == null?0:var16.model.pumpDelay;
                     int pumpTime = var16.model == null?1:var16.model.pumpTime;
                     int hammerDelay = var16.model == null?0:var16.model.hammerDelay;
                     float hammerAngle = var16.model == null?0.0F:var16.model.hammerAngle;
                     if(left) {
                        this.leftAnimations.doShoot(var21, pumpTime, hammerDelay, hammerAngle);
                     } else {
                        this.rightAnimations.doShoot(var21, pumpTime, hammerDelay, hammerAngle);
                     }
                  }

                  var19.setItemDamage(var19.getItemDamage() + 1);
                  var15.setBulletItemStack(heldStack, var19, var18);
               }
            }
         }

         return true;
      }
   }

   private void shoot(ItemStack stack, GunType gunType, ItemStack bulletStack, boolean creative, boolean left) {
      MechaType mechaType = this.getMechaType();
      BulletType bulletType = ((ItemBullet)bulletStack.getItem()).type;
      RotatedAxes a = new RotatedAxes();
      Vector3f armVector = new Vector3f(mechaType.armLength, 0.0F, 0.0F);
      Vector3f gunVector = new Vector3f(mechaType.armLength + 1.2F * mechaType.heldItemScale, 0.5F * mechaType.heldItemScale, 0.0F);
      Vector3f armOrigin = left?mechaType.leftArmOrigin:mechaType.rightArmOrigin;
      a.rotateGlobalYaw(super.axes.getYaw());
      armOrigin = a.findLocalVectorGlobally(armOrigin);
      a.rotateLocalPitch(-super.seats[0].looking.getPitch());
      gunVector = a.findLocalVectorGlobally(gunVector);
      armVector = a.findLocalVectorGlobally(armVector);
      Vector3f bulletOrigin = Vector3f.add(armOrigin, gunVector, (Vector3f)null);
      bulletOrigin = Vector3f.add(new Vector3f(super.posX, super.posY, super.posZ), bulletOrigin, (Vector3f)null);
      if(!super.worldObj.isRemote) {
         for(int k = 0; k < gunType.numBullets; ++k) {
            super.worldObj.spawnEntityInWorld(((ItemBullet)bulletStack.getItem()).getEntity(super.worldObj, bulletOrigin, armVector, (EntityLivingBase)((EntityLivingBase)super.seats[0].riddenByEntity), gunType.getSpread(stack) / 2.0F, gunType.getDamage(stack), gunType.getBulletSpeed(stack), bulletStack.getItemDamage(), mechaType));
         }
      }

      if(left) {
         this.shootDelayLeft = gunType.mode == EnumFireMode.SEMIAUTO?Math.max(gunType.shootDelay, 5):gunType.shootDelay;
      } else {
         this.shootDelayRight = gunType.mode == EnumFireMode.SEMIAUTO?Math.max(gunType.shootDelay, 5):gunType.shootDelay;
      }

      if(bulletType.dropItemOnShoot != null && !creative) {
         ItemGun.dropItem(super.worldObj, this, bulletType.dropItemOnShoot);
      }

      if((left?this.soundDelayLeft:this.soundDelayRight) <= 0 && gunType.shootSound != null) {
         PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, gunType.shootSound, gunType.distortSound);
         if(left) {
            this.soundDelayLeft = gunType.shootSoundLength;
         } else {
            this.soundDelayRight = gunType.shootSoundLength;
         }
      }

   }

   private boolean driverIsCreative() {
      return super.seats != null && super.seats[0] != null && super.seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
   }

   protected void fall(float f) {
      this.attackEntityFrom(DamageSource.fall, f);
   }

   public void setLegAngles(float LLU, float pLLU, float RLU, float pRLU, float LLL, float pLLL, float RLL, float pRLL, float LLF, float pLLF, float RLF, float pRLF) {
      this.leftLegUpperAngle = LLU;
      this.leftLegLowerAngle = LLL;
      this.leftFootAngle = LLF;
      this.rightLegUpperAngle = RLU;
      this.rightLegLowerAngle = RLL;
      this.rightFootAngle = RLF;
      this.prevLeftLegUpperAngle = pLLU;
      this.prevLeftLegLowerAngle = pLLL;
      this.prevLeftFootAngle = pLLF;
      this.prevRightLegUpperAngle = pRLU;
      this.prevRightLegLowerAngle = pRLL;
      this.prevRightFootAngle = pRLF;
   }

   public boolean attackEntityFrom(DamageSource damagesource, float i) {
      if(!super.worldObj.isRemote && !super.isDead) {
         MechaType type = this.getMechaType();
         if(damagesource.getDamageType().equals("fall")) {
            boolean mechaStack = type.takeFallDamage && !this.stopFallDamage();
            boolean damageBlocksFromFalling = type.damageBlocksFromFalling || this.breakBlocksUponFalling();
            byte wouldBeNegativeDamage;
            if(i * type.fallDamageMultiplier * this.vulnerability() - 2.0F < 0.0F) {
               wouldBeNegativeDamage = 0;
            } else {
               wouldBeNegativeDamage = 1;
            }

            float damageToInflict = mechaStack?i * type.fallDamageMultiplier * this.vulnerability() * (float)wouldBeNegativeDamage:0.0F;
            float blockDamageFromFalling = damageBlocksFromFalling?i * type.blockDamageFromFalling / 10.0F:0.0F;
            ((DriveablePart)super.driveableData.parts.get(EnumDriveablePart.hips)).attack(damageToInflict, false);
            this.checkParts();
            FlansMod.getPacketHandler().sendToAllAround(new PacketDriveableDamage(this), super.posX, super.posY, super.posZ, 400.0F, super.dimension);
            if(blockDamageFromFalling > 1.0F) {
               super.worldObj.createExplosion(this, super.posX, super.posY, super.posZ, blockDamageFromFalling, TeamsManager.explosions);
            }
         } else if(damagesource.damageType.equals("player") && damagesource.getEntity().onGround && (super.seats[0] == null || super.seats[0].riddenByEntity == null) && !super.locked) {
            ItemStack mechaStack1 = new ItemStack(type.item, 1, super.driveableData.paintjobID);
            mechaStack1.stackTagCompound = new NBTTagCompound();
            super.driveableData.writeToNBT(mechaStack1.stackTagCompound);
            this.inventory.writeToNBT(mechaStack1.stackTagCompound);
            this.entityDropItem(mechaStack1, 0.5F);
            this.setDead();
         } else {
            ((DriveablePart)super.driveableData.parts.get(EnumDriveablePart.core)).attack(i * this.vulnerability(), damagesource.isFireDamage());
         }

         return true;
      } else {
         return true;
      }
   }

   public void onUpdate() {
      super.onUpdate();
      boolean legDir = true;
      if(this.legPosition > 1.0F) {
         this.legPosition = 0.0F;
      }

      this.prevLeftLegUpperAngle = this.leftLegUpperAngle;
      this.prevLeftLegLowerAngle = this.leftLegLowerAngle;
      this.prevLeftFootAngle = this.leftFootAngle;
      this.prevRightLegUpperAngle = this.rightLegUpperAngle;
      this.prevRightLegLowerAngle = this.rightLegLowerAngle;
      this.prevRightFootAngle = this.rightFootAngle;
      Iterator type = this.getMechaType().legNodes.iterator();

      while(type.hasNext()) {
         MechaType.LegNode data = (MechaType.LegNode)type.next();
         if(this.legPosition >= data.lowerBound && this.legPosition <= data.upperBound) {
            if(data.legPart == 1) {
               this.targetLeftUpper = data.rotation;
               this.targetLeftUpperSpeed = data.speed;
            } else if(data.legPart == 2) {
               this.targetLeftLower = data.rotation;
               this.targetLeftLowerSpeed = data.speed;
            } else if(data.legPart == 3) {
               this.targetLeftFoot = data.rotation;
               this.targetLeftFootSpeed = data.speed;
            } else if(data.legPart == 4) {
               this.targetRightUpper = data.rotation;
               this.targetRightUpperSpeed = data.speed;
            } else if(data.legPart == 5) {
               this.targetRightLower = data.rotation;
               this.targetRightLowerSpeed = data.speed;
            } else if(data.legPart == 6) {
               this.targetRightFoot = data.rotation;
               this.targetRightFootSpeed = data.speed;
            }
         }
      }

      if(this.leftLegUpperAngle < (float)this.targetLeftUpper) {
         this.leftLegUpperAngle += (float)this.targetLeftUpperSpeed;
      } else if(this.leftLegUpperAngle > (float)this.targetLeftUpper) {
         this.leftLegUpperAngle -= (float)this.targetLeftUpperSpeed;
      }

      if((float)Math.sqrt((double)((this.leftLegUpperAngle - (float)this.targetLeftUpper) * (this.leftLegUpperAngle - (float)this.targetLeftUpper))) <= (float)(this.targetLeftUpperSpeed / 2)) {
         this.leftLegUpperAngle = (float)this.targetLeftUpper;
      }

      if(this.rightLegUpperAngle < (float)this.targetRightUpper) {
         this.rightLegUpperAngle += (float)this.targetRightUpperSpeed;
      } else if(this.rightLegUpperAngle > (float)this.targetRightUpper) {
         this.rightLegUpperAngle -= (float)this.targetRightUpperSpeed;
      }

      if((float)Math.sqrt((double)((this.rightLegUpperAngle - (float)this.targetRightUpper) * (this.rightLegUpperAngle - (float)this.targetRightUpper))) <= (float)(this.targetRightUpperSpeed / 2)) {
         this.rightLegUpperAngle = (float)this.targetRightUpper;
      }

      if(this.leftLegLowerAngle < (float)this.targetLeftLower) {
         this.leftLegLowerAngle += (float)this.targetLeftLowerSpeed;
      } else if(this.leftLegLowerAngle > (float)this.targetLeftLower) {
         this.leftLegLowerAngle -= (float)this.targetRightLowerSpeed;
      }

      if(this.rightLegLowerAngle < (float)this.targetRightLower) {
         this.rightLegLowerAngle += (float)this.targetRightLowerSpeed;
      } else if(this.rightLegLowerAngle > (float)this.targetRightLower) {
         this.rightLegLowerAngle -= (float)this.targetRightLowerSpeed;
      }

      if((float)Math.sqrt((double)((this.leftLegLowerAngle - (float)this.targetLeftLower) * (this.leftLegLowerAngle - (float)this.targetLeftLower))) <= (float)(this.targetLeftLowerSpeed / 2)) {
         this.leftLegLowerAngle = (float)this.targetLeftLower;
      }

      if((float)Math.sqrt((double)((this.rightLegLowerAngle - (float)this.targetRightLower) * (this.rightLegLowerAngle - (float)this.targetRightLower))) <= (float)(this.targetRightLowerSpeed / 2)) {
         this.rightLegLowerAngle = (float)this.targetRightLower;
      }

      if(this.leftFootAngle < (float)this.targetLeftFoot) {
         this.leftFootAngle += (float)this.targetLeftFootSpeed;
      } else if(this.leftFootAngle > (float)this.targetLeftFoot) {
         this.leftFootAngle -= (float)this.targetLeftFootSpeed;
      }

      if(this.rightFootAngle < (float)this.targetRightFoot) {
         this.rightFootAngle += (float)this.targetRightFootSpeed;
      } else if(this.rightFootAngle > (float)this.targetRightFoot) {
         this.rightFootAngle -= (float)this.targetRightFootSpeed;
      }

      if((float)Math.sqrt((double)((this.rightFootAngle - (float)this.targetRightFoot) * (this.rightFootAngle - (float)this.targetRightFoot))) <= (float)(this.targetRightFootSpeed / 2)) {
         this.rightFootAngle = (float)this.targetRightFoot;
      }

      if((float)Math.sqrt((double)((this.leftFootAngle - (float)this.targetLeftFoot) * (this.leftFootAngle - (float)this.targetLeftFoot))) <= (float)(this.targetLeftFootSpeed / 2)) {
         this.leftFootAngle = (float)this.targetLeftFoot;
      }

      if(this.jumpDelay > 0) {
         --this.jumpDelay;
      }

      if(this.shootDelayLeft > 0) {
         --this.shootDelayLeft;
      }

      if(this.shootDelayRight > 0) {
         --this.shootDelayRight;
      }

      if(this.soundDelayLeft > 0) {
         --this.soundDelayLeft;
      }

      if(this.soundDelayRight > 0) {
         --this.soundDelayRight;
      }

      if(!super.worldObj.isRemote && (super.seats[0] == null || super.seats[0].riddenByEntity == null)) {
         super.rightMouseHeld = super.leftMouseHeld = false;
      }

      this.leftAnimations.update();
      this.rightAnimations.update();
      MechaType var28 = this.getMechaType();
      DriveableData var29 = this.getDriveableData();
      if(var28 == null) {
         FlansMod.log("Mecha type null. Not ticking mecha");
      } else {
         if(this.stompDelay > 0) {
            --this.stompDelay;
         }

         this.prevLegsYaw = this.legAxes.getYaw();
         boolean driverIsCreative;
         if(this.toggleTimer == 0 && this.autoRepair()) {
            EnumDriveablePart[] thePlayerIsDrivingThis = EnumDriveablePart.values();
            int driverIsLiving = thePlayerIsDrivingThis.length;

            for(int jetPack = 0; jetPack < driverIsLiving; ++jetPack) {
               EnumDriveablePart actualMotion = thePlayerIsDrivingThis[jetPack];
               DriveablePart entity = (DriveablePart)var29.parts.get(actualMotion);
               driverIsCreative = super.seats != null && super.seats[0] != null && super.seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
               if(entity != null && entity.health != 0 && entity.health < entity.maxHealth && (driverIsCreative || var29.fuelInTank >= 10.0F)) {
                  ++entity.health;
                  if(!driverIsCreative) {
                     var29.fuelInTank -= 10.0F;
                  }
               }
            }

            this.toggleTimer = 20;
         }

         int intent;
         float var34;
         float var35;
         int var39;
         if(this.diamondDetect() != null && this.diamondTimer == 0 && super.worldObj.isRemote && super.seats[0] != null && super.seats[0].riddenByEntity instanceof EntityPlayer && FlansMod.proxy.isThePlayer((EntityPlayer)super.seats[0].riddenByEntity)) {
            float var30 = 901.0F;

            for(float var32 = -30.0F; var32 <= 30.0F; ++var32) {
               for(var34 = -30.0F; var34 <= 30.0F; ++var34) {
                  for(var35 = -30.0F; var35 <= 30.0F; ++var35) {
                     int var37 = MathHelper.floor_double((double)var32 + super.posX);
                     var39 = MathHelper.floor_double((double)var34 + super.posY);
                     intent = MathHelper.floor_double((double)var35 + super.posZ);
                     if(var32 * var32 + var34 * var34 + var35 * var35 < var30 && super.worldObj.getBlock(var37, var39, intent) == Blocks.diamond_ore) {
                        var30 = var32 * var32 + var34 * var34 + var35 * var35;
                     }
                  }
               }
            }

            if(var30 < 901.0F) {
               PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.diamondDetect().detectSound, false);
               this.diamondTimer = 1 + 2 * MathHelper.floor_float(MathHelper.sqrt_float(var30));
            }
         }

         if(this.diamondTimer > 0) {
            --this.diamondTimer;
         }

         if(this.isPartIntact(EnumDriveablePart.hips)) {
            this.setSize(var28.width, var28.height);
            super.yOffset = var28.yOffset;
         } else {
            this.setSize(var28.width, var28.height - var28.chassisHeight);
            super.yOffset = var28.yOffset - var28.chassisHeight;
         }

         boolean var31 = super.worldObj.isRemote && super.seats[0] != null && super.seats[0].riddenByEntity instanceof EntityPlayer && FlansMod.proxy.isThePlayer((EntityPlayer)super.seats[0].riddenByEntity);
         boolean var33 = super.seats[0] != null && super.seats[0].riddenByEntity instanceof EntityLivingBase;
         ++this.ticksSinceUsed;
         if(!super.worldObj.isRemote && super.seats[0].riddenByEntity != null) {
            this.ticksSinceUsed = 0;
         }

         if(!super.worldObj.isRemote && TeamsManager.mechaLove > 0 && this.ticksSinceUsed > TeamsManager.mechaLove * 20) {
            this.setDead();
         }

         if(this.toggleTimer > 0) {
            --this.toggleTimer;
         }

         double var38;
         if(super.worldObj.isRemote && !var31 && super.serverPositionTransitionTicker > 0) {
            double var36 = super.posX + (super.field_70118_ct - super.posX) / (double)super.serverPositionTransitionTicker;
            var38 = super.posY + (super.field_70117_cu - super.posY) / (double)super.serverPositionTransitionTicker;
            double var43 = super.posZ + (super.field_70116_cv - super.posZ) / (double)super.serverPositionTransitionTicker;
            double metadata = MathHelper.wrapAngleTo180_double(super.serverYaw - (double)super.axes.getYaw());
            double leftStack = MathHelper.wrapAngleTo180_double(super.serverPitch - (double)super.axes.getPitch());
            double leftStackIsTool = MathHelper.wrapAngleTo180_double(super.serverRoll - (double)super.axes.getRoll());
            super.rotationYaw = (float)((double)super.axes.getYaw() + metadata / (double)super.serverPositionTransitionTicker);
            super.rotationPitch = (float)((double)super.axes.getPitch() + leftStack / (double)super.serverPositionTransitionTicker);
            float breakingBlocks = (float)((double)super.axes.getRoll() + leftStackIsTool / (double)super.serverPositionTransitionTicker);
            --super.serverPositionTransitionTicker;
            this.setPosition(var36, var38, var43);
            this.setRotation(super.rotationYaw, super.rotationPitch, breakingBlocks);
         }

         if(super.seats[0] != null) {
            if(super.seats[0].riddenByEntity instanceof EntityLivingBase && !(super.seats[0].riddenByEntity instanceof EntityPlayer)) {
               super.axes.setAngles(((EntityLivingBase)super.seats[0].riddenByEntity).renderYawOffset + 90.0F, 0.0F, 0.0F);
            } else {
               if(var28.limitHeadTurn) {
                  var34 = this.legAxes.getYaw();
                  var35 = super.axes.getYaw();
                  var38 = (double)(var35 - var34);
                  if(var38 > 180.0D) {
                     var35 -= 360.0F;
                  }

                  if(var38 < -180.0D) {
                     var35 += 360.0F;
                  }

                  if(var34 + var28.limitHeadTurnValue < var35) {
                     super.axes.setAngles(var34 + var28.limitHeadTurnValue, 0.0F, 0.0F);
                  }

                  if(var34 - var28.limitHeadTurnValue > var35) {
                     super.axes.setAngles(var34 - var28.limitHeadTurnValue, 0.0F, 0.0F);
                  }
               }

               var34 = super.seats[0].looking.getYaw() - super.seats[0].prevLooking.getYaw();
               super.axes.rotateGlobalYaw(var34);
               super.seats[0].looking.rotateGlobalYaw(-var34);
               super.seats[0].playerLooking.rotateGlobalYaw(-var34);
            }
         }

         this.moveX = 0.0F;
         this.moveZ = 0.0F;
         var34 = this.jetPackPower();
         if(!super.onGround && var31 && Minecraft.getMinecraft().currentScreen instanceof GuiDriveableController && FlansMod.proxy.isKeyDown(4) && this.shouldFly() && (((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode || var29.fuelInTank >= 10.0F * var34)) {
            super.motionY *= 0.95D;
            super.motionY += 0.07D * (double)var34;
            super.fallDistance = 0.0F;
            if(!((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode) {
               var29.fuelInTank -= 10.0F * var34;
            }

            if(this.rocketTimer <= 0.0F && this.rocketPack().soundEffect != null) {
               PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.rocketPack().soundEffect, false);
               this.rocketTimer = this.rocketPack().soundTime;
            }
         } else if(this.isInWater() && this.shouldFloat()) {
            super.motionY *= 0.89D;
            super.motionY += 0.1D;
         }

         if(this.rocketTimer != 0.0F) {
            --this.rocketTimer;
         }

         Vector3f var41 = new Vector3f(0.0D, super.motionY - 0.03999999910593033D, 0.0D);
         if(var33) {
            EntityLivingBase var40 = (EntityLivingBase)super.seats[0].riddenByEntity;
            driverIsCreative = var40 instanceof EntityPlayer && ((EntityPlayer)var40).capabilities.isCreativeMode;
            if(var31 && Minecraft.getMinecraft().currentScreen instanceof GuiDriveableController) {
               if(FlansMod.proxy.isKeyDown(0)) {
                  this.moveX = 1.0F;
               }

               if(FlansMod.proxy.isKeyDown(1)) {
                  this.moveX = -1.0F;
               }

               if(FlansMod.proxy.isKeyDown(2)) {
                  this.moveZ = -1.0F;
               }

               if(FlansMod.proxy.isKeyDown(3)) {
                  this.moveZ = 1.0F;
               }
            } else if(super.seats[0].riddenByEntity instanceof EntityLiving && !(super.seats[0].riddenByEntity instanceof EntityPlayer)) {
               this.moveZ = 1.0F;
            }

            Vector3f var44 = new Vector3f(this.moveX, 0.0F, this.moveZ);
            if((double)Math.abs(var44.lengthSquared()) > 0.1D) {
               var44.normalise();
               ++this.legSwing;
               this.legPosition += this.getMechaType().legAnimSpeed;
               if(this.stompDelay == 0 && this.legPosition >= this.getMechaType().stompRangeLower && this.legPosition <= this.getMechaType().stompRangeUpper) {
                  PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.getMechaType().stompSound, false);
                  this.stompDelay = this.getMechaType().stompSoundLength;
               }

               var44 = super.axes.findLocalVectorGlobally(var44);
               this.legAxes.findGlobalVectorLocally(var44);
               float var46 = (float)Math.atan2((double)var44.z, (double)var44.x) * 180.0F / 3.1415927F;
               float material = var46 - this.legAxes.getYaw();
               if(material > 180.0F) {
                  material -= 360.0F;
               }

               if(material < -180.0F) {
                  material += 360.0F;
               }

               float var48 = Math.signum(material);
               material = Math.abs(material);
               if((double)material > 0.1D) {
                  this.legAxes.rotateGlobalYaw(Math.min(material, var28.rotateSpeed) * var48);
               }

               var44.scale(var28.moveSpeed * var29.engine.engineSpeed * this.speedMultiplier() * 0.215F);
               boolean rightStack = super.seats != null && super.seats[0] != null && super.seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
               if((rightStack || var29.fuelInTank > var29.engine.fuelConsumption) && this.isPartIntact(EnumDriveablePart.hips)) {
                  if(!super.onGround && this.shouldFly() && (rightStack || var29.fuelInTank > 10.0F * var34 + var29.engine.fuelConsumption)) {
                     var44.scale(var34);
                     if(!rightStack) {
                        var29.fuelInTank -= 10.0F * var34;
                     }
                  }

                  Vector3f.add(var41, var44, var41);
                  if(!rightStack) {
                     var29.fuelInTank -= var29.engine.fuelConsumption;
                  }
               }
            } else {
               this.legPosition = 0.0F;
            }

            if(!super.worldObj.isRemote) {
               if(super.leftMouseHeld) {
                  this.useItem(true);
               }

               if(super.rightMouseHeld) {
                  this.useItem(false);
               }

               if(this.breakingBlock != null) {
                  Block seat = super.worldObj.getBlock(this.breakingBlock.x, this.breakingBlock.y, this.breakingBlock.z);
                  int var47 = super.worldObj.getBlockMetadata(this.breakingBlock.x, this.breakingBlock.y, this.breakingBlock.z);
                  Material var50 = seat.getMaterial();
                  ItemStack var49 = this.inventory.getStackInSlot(EnumMechaSlotType.leftTool);
                  ItemStack var51 = this.inventory.getStackInSlot(EnumMechaSlotType.rightTool);
                  boolean var52 = var49 != null && var49.getItem() instanceof ItemMechaAddon;
                  boolean rightStackIsTool = var51 != null && var51.getItem() instanceof ItemMechaAddon;
                  boolean var53 = super.leftMouseHeld && var52 || super.rightMouseHeld && rightStackIsTool;
                  if(seat != null && var53) {
                     float blockHardness = seat.getBlockHardness(super.worldObj, this.breakingBlock.x, this.breakingBlock.y, this.breakingBlock.z);
                     float mineSpeed = 1.0F;
                     boolean atLeastOneEffectiveTool = false;
                     MechaItemType cancelled;
                     if(var52) {
                        cancelled = ((ItemMechaAddon)var49.getItem()).type;
                        if(cancelled.function.effectiveAgainst(var50) && cancelled.toolHardness > blockHardness) {
                           mineSpeed *= cancelled.speed;
                           atLeastOneEffectiveTool = true;
                        }
                     }

                     if(rightStackIsTool) {
                        cancelled = ((ItemMechaAddon)var51.getItem()).type;
                        if(cancelled.function.effectiveAgainst(var50) && cancelled.toolHardness > blockHardness) {
                           mineSpeed *= cancelled.speed;
                           atLeastOneEffectiveTool = true;
                        }
                     }

                     if(blockHardness < -0.01F) {
                        mineSpeed = 0.0F;
                     } else if(Math.abs(blockHardness) < 0.01F) {
                        mineSpeed = 9001.0F;
                     } else {
                        mineSpeed /= seat.getBlockHardness(super.worldObj, this.breakingBlock.x, this.breakingBlock.y, this.breakingBlock.z);
                     }

                     this.breakingProgress += 0.1F * mineSpeed;
                     if(this.breakingProgress >= 1.0F) {
                        boolean var54 = false;
                        if(var40 instanceof EntityPlayerMP) {
                           BreakEvent vacuumItems = ForgeHooks.onBlockBreakEvent(super.worldObj, ((EntityPlayerMP)var40).capabilities.isCreativeMode?GameType.CREATIVE:(((EntityPlayerMP)var40).capabilities.allowEdit?GameType.SURVIVAL:GameType.ADVENTURE), (EntityPlayerMP)super.seats[0].riddenByEntity, this.breakingBlock.x, this.breakingBlock.y, this.breakingBlock.z);
                           var54 = vacuumItems.isCanceled();
                        }

                        if(!var54) {
                           boolean var55 = this.vacuumItems();
                           if(var55) {
                              Iterator var24 = seat.getDrops(super.worldObj, this.breakingBlock.x, this.breakingBlock.y, this.breakingBlock.z, var47, 0).iterator();

                              while(var24.hasNext()) {
                                 ItemStack stack = (ItemStack)var24.next();
                                 boolean fuelCheck = var29.fuelInTank >= 5.0F || ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
                                 if(fuelCheck && this.refineIron() && stack.getItem() instanceof ItemBlock && ((ItemBlock)stack.getItem()).field_150939_a == Blocks.iron_ore) {
                                    stack = new ItemStack(Items.iron_ingot, 1, 0);
                                    if(!((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode) {
                                       var29.fuelInTank -= 5.0F;
                                    }
                                 }

                                 fuelCheck = var29.fuelInTank >= 0.1F || ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
                                 if(fuelCheck && this.wasteCompact().booleanValue() && stack.getItem() instanceof ItemBlock && (((ItemBlock)stack.getItem()).field_150939_a == Blocks.cobblestone || ((ItemBlock)stack.getItem()).field_150939_a == Blocks.dirt || ((ItemBlock)stack.getItem()).field_150939_a == Blocks.sand)) {
                                    stack.stackSize = 0;
                                    if(!((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode) {
                                       var29.fuelInTank -= 0.1F;
                                    }
                                 }

                                 fuelCheck = var29.fuelInTank >= 3.0F * this.diamondMultiplier() || ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
                                 float multiplier;
                                 if(fuelCheck && stack.getItem() == Items.diamond) {
                                    multiplier = this.diamondMultiplier();
                                    stack.stackSize *= MathHelper.floor_float(multiplier) + (super.rand.nextFloat() < this.tailFloat(multiplier)?1:0);
                                    if(!((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode) {
                                       var29.fuelInTank -= 3.0F * this.diamondMultiplier();
                                    }
                                 }

                                 fuelCheck = var29.fuelInTank >= 2.0F * this.redstoneMultiplier() || ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
                                 if(fuelCheck && stack.getItem() == Items.redstone) {
                                    multiplier = this.redstoneMultiplier();
                                    stack.stackSize *= MathHelper.floor_float(multiplier) + (super.rand.nextFloat() < this.tailFloat(multiplier)?1:0);
                                    if(!((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode) {
                                       var29.fuelInTank -= 2.0F * this.redstoneMultiplier();
                                    }
                                 }

                                 fuelCheck = var29.fuelInTank >= 2.0F * this.coalMultiplier() || ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
                                 if(fuelCheck && stack.getItem() == Items.coal) {
                                    multiplier = this.coalMultiplier();
                                    stack.stackSize *= MathHelper.floor_float(multiplier) + (super.rand.nextFloat() < this.tailFloat(multiplier)?1:0);
                                    if(!((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode) {
                                       var29.fuelInTank -= 2.0F * this.coalMultiplier();
                                    }
                                 }

                                 fuelCheck = var29.fuelInTank >= 2.0F * this.emeraldMultiplier() || ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
                                 if(fuelCheck && stack.getItem() == Items.emerald) {
                                    multiplier = this.emeraldMultiplier();
                                    stack.stackSize *= MathHelper.floor_float(multiplier) + (super.rand.nextFloat() < this.tailFloat(multiplier)?1:0);
                                    if(!((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode) {
                                       var29.fuelInTank -= 2.0F * this.emeraldMultiplier();
                                    }
                                 }

                                 fuelCheck = var29.fuelInTank >= 2.0F * this.ironMultiplier() || ((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode;
                                 if(fuelCheck && stack.getItem() == Items.iron_ingot && this.refineIron()) {
                                    multiplier = this.ironMultiplier();
                                    stack.stackSize *= MathHelper.floor_float(multiplier) + (super.rand.nextFloat() < this.tailFloat(multiplier)?1:0);
                                    if(!((EntityPlayer)super.seats[0].riddenByEntity).capabilities.isCreativeMode) {
                                       var29.fuelInTank -= 2.0F * this.ironMultiplier();
                                    }
                                 }

                                 if(this.autoCoal() && stack.getItem() == Items.coal && var29.fuelInTank + 250.0F < (float)var28.fuelTankSize) {
                                    var29.fuelInTank = Math.min(var29.fuelInTank + 1000.0F, (float)var28.fuelTankSize);
                                    this.couldNotFindFuel = false;
                                    stack.stackSize = 0;
                                 }

                                 if(!InventoryHelper.addItemStackToInventory(super.driveableData, stack, driverIsCreative) && !super.worldObj.isRemote && super.worldObj.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
                                    super.worldObj.spawnEntityInWorld(new EntityItem(super.worldObj, (double)((float)this.breakingBlock.x + 0.5F), (double)((float)this.breakingBlock.y + 0.5F), (double)((float)this.breakingBlock.z + 0.5F), stack));
                                 }
                              }
                           }

                           super.worldObj.func_147480_a(this.breakingBlock.x, this.breakingBlock.y, this.breakingBlock.z, atLeastOneEffectiveTool && !var55);
                        }
                     }
                  } else {
                     this.breakingBlock = null;
                  }
               }
            }
         }

         super.motionY = (double)var41.y;
         this.moveEntity((double)var41.x, (double)var41.y, (double)var41.z);
         this.setPosition(super.posX, super.posY, super.posZ);
         if(var31) {
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketMechaControl(this)));
            super.field_70118_ct = super.posX;
            super.field_70117_cu = super.posY;
            super.field_70116_cv = super.posZ;
            super.serverYaw = (double)super.axes.getYaw();
         }

         if(!super.worldObj.isRemote && super.ticksExisted % 5 == 0) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketMechaControl(this), super.posX, super.posY, super.posZ, 400.0F, super.dimension);
         }

         EntitySeat[] var42 = super.seats;
         var39 = var42.length;

         for(intent = 0; intent < var39; ++intent) {
            EntitySeat var45 = var42[intent];
            if(var45 != null) {
               var45.updatePosition();
            }
         }

         if(!var33 || var31) {
            this.legSwing /= var28.legSwingLimit;
         }

      }
   }

   private float tailFloat(float f) {
      return f - (float)MathHelper.floor_float(f);
   }

   public boolean stopFallDamage() {
      Iterator var1 = this.getUpgradeTypes().iterator();

      MechaItemType type;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         type = (MechaItemType)var1.next();
      } while(!type.stopMechaFallDamage);

      return true;
   }

   public boolean breakBlocksUponFalling() {
      Iterator var1 = this.getUpgradeTypes().iterator();

      MechaItemType type;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         type = (MechaItemType)var1.next();
      } while(!type.forceBlockFallDamage);

      return true;
   }

   public boolean vacuumItems() {
      Iterator var1 = this.getUpgradeTypes().iterator();

      MechaItemType type;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         type = (MechaItemType)var1.next();
      } while(!type.vacuumItems);

      return true;
   }

   public boolean refineIron() {
      Iterator var1 = this.getUpgradeTypes().iterator();

      MechaItemType type;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         type = (MechaItemType)var1.next();
      } while(!type.refineIron);

      return true;
   }

   public MechaItemType diamondDetect() {
      Iterator var1 = this.getUpgradeTypes().iterator();

      MechaItemType type;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         type = (MechaItemType)var1.next();
      } while(!type.diamondDetect);

      return type;
   }

   public Boolean wasteCompact() {
      Iterator var1 = this.getUpgradeTypes().iterator();

      MechaItemType type;
      do {
         if(!var1.hasNext()) {
            return Boolean.valueOf(false);
         }

         type = (MechaItemType)var1.next();
      } while(!type.wasteCompact);

      return Boolean.valueOf(true);
   }

   public float diamondMultiplier() {
      float multiplier = 1.0F;

      MechaItemType type;
      for(Iterator var2 = this.getUpgradeTypes().iterator(); var2.hasNext(); multiplier *= type.fortuneDiamond) {
         type = (MechaItemType)var2.next();
      }

      return multiplier;
   }

   public float speedMultiplier() {
      float multiplier = 1.0F;

      MechaItemType type;
      for(Iterator var2 = this.getUpgradeTypes().iterator(); var2.hasNext(); multiplier *= type.speedMultiplier) {
         type = (MechaItemType)var2.next();
      }

      return multiplier;
   }

   public float coalMultiplier() {
      float multiplier = 1.0F;

      MechaItemType type;
      for(Iterator var2 = this.getUpgradeTypes().iterator(); var2.hasNext(); multiplier *= type.fortuneCoal) {
         type = (MechaItemType)var2.next();
      }

      return multiplier;
   }

   public float redstoneMultiplier() {
      float multiplier = 1.0F;

      MechaItemType type;
      for(Iterator var2 = this.getUpgradeTypes().iterator(); var2.hasNext(); multiplier *= type.fortuneRedstone) {
         type = (MechaItemType)var2.next();
      }

      return multiplier;
   }

   public float vulnerability() {
      float multiplier = 1.0F;

      MechaItemType type;
      for(Iterator var2 = this.getUpgradeTypes().iterator(); var2.hasNext(); multiplier *= 1.0F - type.damageResistance) {
         type = (MechaItemType)var2.next();
      }

      return multiplier;
   }

   public float emeraldMultiplier() {
      float multiplier = 1.0F;

      MechaItemType type;
      for(Iterator var2 = this.getUpgradeTypes().iterator(); var2.hasNext(); multiplier *= type.fortuneEmerald) {
         type = (MechaItemType)var2.next();
      }

      return multiplier;
   }

   public float ironMultiplier() {
      float multiplier = 1.0F;

      MechaItemType type;
      for(Iterator var2 = this.getUpgradeTypes().iterator(); var2.hasNext(); multiplier *= type.fortuneIron) {
         type = (MechaItemType)var2.next();
      }

      return multiplier;
   }

   public int lightLevel() {
      int level = 0;

      MechaItemType type;
      for(Iterator var2 = this.getUpgradeTypes().iterator(); var2.hasNext(); level = Math.max(level, type.lightLevel)) {
         type = (MechaItemType)var2.next();
      }

      return level;
   }

   public boolean forceDark() {
      Iterator var1 = this.getUpgradeTypes().iterator();

      MechaItemType type;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         type = (MechaItemType)var1.next();
      } while(!type.forceDark);

      return true;
   }

   public boolean autoCoal() {
      Iterator var1 = this.getUpgradeTypes().iterator();

      MechaItemType type;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         type = (MechaItemType)var1.next();
      } while(!type.autoCoal);

      return true;
   }

   public boolean autoRepair() {
      Iterator var1 = this.getUpgradeTypes().iterator();

      MechaItemType type;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         type = (MechaItemType)var1.next();
      } while(!type.autoRepair);

      return true;
   }

   public boolean shouldFloat() {
      Iterator var1 = this.getUpgradeTypes().iterator();

      MechaItemType type;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         type = (MechaItemType)var1.next();
      } while(!type.floater);

      return true;
   }

   public boolean infiniteAmmo() {
      Iterator var1 = this.getUpgradeTypes().iterator();

      MechaItemType type;
      do {
         if(!var1.hasNext()) {
            return false;
         }

         type = (MechaItemType)var1.next();
      } while(!type.infiniteAmmo);

      return true;
   }

   public MechaItemType rocketPack() {
      Iterator var1 = this.getUpgradeTypes().iterator();

      MechaItemType type;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         type = (MechaItemType)var1.next();
      } while(!type.rocketPack);

      return type;
   }

   public boolean shouldFly() {
      return this.rocketPack() != null;
   }

   public float jetPackPower() {
      float multiplier = 1.0F;

      MechaItemType type;
      for(Iterator var2 = this.getUpgradeTypes().iterator(); var2.hasNext(); multiplier *= type.rocketPower) {
         type = (MechaItemType)var2.next();
      }

      return multiplier;
   }

   public ArrayList getUpgradeTypes() {
      ArrayList types = new ArrayList();
      Iterator var2 = this.inventory.stacks.values().iterator();

      while(var2.hasNext()) {
         ItemStack stack = (ItemStack)var2.next();
         if(stack != null && stack.getItem() instanceof ItemMechaAddon) {
            types.add(((ItemMechaAddon)stack.getItem()).type);
         }
      }

      return types;
   }

   @SideOnly(Side.CLIENT)
   public boolean showInventory(int seat) {
      return seat != 0;
   }

   protected void dropItemsOnPartDeath(Vector3f midpoint, DriveablePart part) {}

   public boolean hasMouseControlMode() {
      return false;
   }

   public String getBombInventoryName() {
      return "";
   }

   public String getMissileInventoryName() {
      return "";
   }

   @SideOnly(Side.CLIENT)
   public EntityLivingBase getCamera() {
      return null;
   }
}
