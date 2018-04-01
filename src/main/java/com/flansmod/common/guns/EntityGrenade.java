package com.flansmod.common.guns;

import com.flansmod.client.FlansModClient;
import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.EntityDamageSourceGun;
import com.flansmod.common.guns.EntityShootable;
import com.flansmod.common.guns.FlansModExplosion;
import com.flansmod.common.guns.GrenadeType;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketFlak;
import com.flansmod.common.network.PacketFlashBang;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.teams.ItemTeamArmour;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntityGrenade extends EntityShootable implements IEntityAdditionalSpawnData {

   public GrenadeType type;
   public EntityLivingBase thrower;
   public Team teamOfThrower;
   public RotatedAxes axes;
   public Vector3f angularVelocity;
   public float prevRotationRoll;
   public int smokeTime;
   public boolean smoking;
   public boolean stuck;
   public int stuckToX;
   public int stuckToY;
   public int stuckToZ;
   public boolean detonated;
   public int numUsesRemaining;
   public boolean isThisStick;
   public Entity stickedEntity;
   public int motionTime;


   public EntityGrenade(World w) {
      super(w);
      this.axes = new RotatedAxes();
      this.angularVelocity = new Vector3f(0.0F, 0.0F, 0.0F);
      this.prevRotationRoll = 0.0F;
      this.smokeTime = 0;
      this.smoking = false;
      this.stuck = false;
      this.detonated = false;
      this.numUsesRemaining = 0;
      this.isThisStick = false;
      this.motionTime = 0;
   }

   public EntityGrenade(World w, GrenadeType g, EntityLivingBase t) {
      this(w);
      this.setPosition(t.posX, t.posY + (double)t.getEyeHeight(), t.posZ);
      this.type = g;
      this.numUsesRemaining = this.type.numUses;
      this.thrower = t;
      if(this.thrower instanceof EntityPlayer && PlayerHandler.getPlayerData((EntityPlayer)this.thrower) != null) {
         this.teamOfThrower = PlayerHandler.getPlayerData((EntityPlayer)this.thrower).team;
      }

      this.setSize(g.hitBoxSize, g.hitBoxSize);
      this.axes.setAngles(t.rotationYaw + 90.0F, g.spinWhenThrown?t.rotationPitch:0.0F, 0.0F);
      super.rotationYaw = super.prevRotationYaw = g.spinWhenThrown?t.rotationYaw + 90.0F:0.0F;
      super.rotationPitch = super.prevRotationPitch = t.rotationPitch;
      float speed = 0.5F * this.type.throwSpeed;
      super.motionX = (double)(this.axes.getXAxis().x * speed);
      super.motionY = (double)(this.axes.getXAxis().y * speed);
      super.motionZ = (double)(this.axes.getXAxis().z * speed);
      if(this.type.spinWhenThrown) {
         this.angularVelocity = new Vector3f(0.0F, 0.0F, 10.0F);
      }

      if(this.type.throwSound != null) {
         PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.type.throwSound, true);
      }

   }

   public void onUpdate() {
      super.onUpdate();
      if(this.type == null) {
         FlansMod.log("EntityGrenade.onUpdate() Error: GrenadeType is null (" + this + ")");
         this.setDead();
      } else {
         if(this.motionTime > 0) {
            --this.motionTime;
         }

         if(this.type.despawnTime > 0 && super.ticksExisted > this.type.despawnTime) {
            this.detonated = true;
            this.setDead();
         } else {
            if(super.worldObj.isRemote && this.type.trailParticles) {
               double motVec = (super.posX - super.prevPosX) / 10.0D;
               double entity = (super.posY - super.prevPosY) / 10.0D;
               double obj1 = (super.posZ - super.prevPosZ) / 10.0D;

               for(int hitVec = 0; hitVec < 10; ++hitVec) {
                  EntityFX preHitMotVec = FlansModClient.getParticle(this.type.trailParticleType, super.worldObj, super.prevPosX + motVec * (double)hitVec, super.prevPosY + entity * (double)hitVec, super.prevPosZ + obj1 * (double)hitVec);
                  if(preHitMotVec != null && Minecraft.getMinecraft().gameSettings.fancyGraphics) {
                     preHitMotVec.renderDistanceWeight = 100.0D;
                  }
               }
            }

            List var14;
            if(this.smoking) {
               FlansMod.getPacketHandler().sendToAllAround(new PacketFlak(super.posX, super.posY, super.posZ, 50, this.type.smokeParticleType), super.posX, super.posY, super.posZ, 30.0F, super.dimension);
               var14 = super.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, super.boundingBox.expand((double)this.type.smokeRadius, (double)this.type.smokeRadius, (double)this.type.smokeRadius));
               Iterator list = var14.iterator();

               while(list.hasNext()) {
                  Object var19 = list.next();
                  EntityLivingBase obj = (EntityLivingBase)var19;
                  if(obj.getDistanceToEntity(this) < this.type.smokeRadius) {
                     boolean var25 = true;

                     for(int mat = 0; mat < 5; ++mat) {
                        ItemStack var32 = obj.getEquipmentInSlot(mat);
                        if(var32 != null && var32.getItem() instanceof ItemTeamArmour && ((ItemTeamArmour)var32.getItem()).type.smokeProtection) {
                           var25 = false;
                        }
                     }

                     if(var25) {
                        Iterator var30 = this.type.smokeEffects.iterator();

                        while(var30.hasNext()) {
                           PotionEffect var33 = (PotionEffect)var30.next();
                           obj.addPotionEffect(new PotionEffect(var33));
                        }
                     }
                  }
               }

               --this.smokeTime;
               if(this.smokeTime == 0) {
                  this.setDead();
               }
            }

            float var15;
            List var17;
            Iterator var21;
            Object var23;
            if(!super.worldObj.isRemote) {
               if(super.ticksExisted > this.type.fuse && this.type.fuse > 0) {
                  this.detonate();
               }

               if(this.type.livingProximityTrigger > 0.0F || this.type.driveableProximityTrigger > 0.0F) {
                  var15 = Math.max(this.type.livingProximityTrigger, this.type.driveableProximityTrigger);
                  var17 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.expand((double)var15, (double)var15, (double)var15));
                  var21 = var17.iterator();

                  while(var21.hasNext()) {
                     var23 = var21.next();
                     if(var23 != this.thrower || super.ticksExisted >= 10) {
                        if(var23 instanceof EntityLivingBase && this.getDistanceToEntity((Entity)var23) < this.type.livingProximityTrigger) {
                           if(TeamsManager.getInstance() == null || TeamsManager.getInstance().currentRound == null || !(var23 instanceof EntityPlayerMP) || !(this.thrower instanceof EntityPlayer) || TeamsManager.getInstance().currentRound.gametype.playerAttacked((EntityPlayerMP)var23, new EntityDamageSourceGun(this.type.shortName, this, (EntityPlayer)this.thrower, this.type, false))) {
                              if(this.type.damageToTriggerer > 0.0F) {
                                 ((EntityLivingBase)var23).attackEntityFrom(this.getGrenadeDamage(), this.type.damageToTriggerer);
                              }

                              this.detonate();
                              break;
                           }
                        } else if(var23 instanceof EntityDriveable && this.getDistanceToEntity((Entity)var23) < this.type.driveableProximityTrigger) {
                           if(this.type.damageToTriggerer > 0.0F) {
                              ((EntityDriveable)var23).attackEntityFrom(this.getGrenadeDamage(), this.type.damageToTriggerer);
                           }

                           this.detonate();
                           break;
                        }
                     }
                  }
               }
            }

            if(this.stuck && super.worldObj.isAirBlock(this.stuckToX, this.stuckToY, this.stuckToZ)) {
               this.stuck = false;
            }

            Vector3f var16;
            if(!this.stuck && !this.type.stickToThrower) {
               super.prevRotationYaw = this.axes.getYaw();
               super.prevRotationPitch = this.axes.getPitch();
               this.prevRotationRoll = this.axes.getRoll();
               if(this.angularVelocity.lengthSquared() > 1.0E-8F) {
                  this.axes.rotateLocal(this.angularVelocity.length(), this.angularVelocity.normalise((Vector3f)null));
               }

               var16 = new Vector3f(super.posX, super.posY, super.posZ);
               Vector3f var18 = new Vector3f(super.motionX, super.motionY, super.motionZ);
               Vector3f var22 = Vector3f.add(var16, var18, (Vector3f)null);
               MovingObjectPosition var24 = super.worldObj.rayTraceBlocks(var16.toVec3(), var22.toVec3());
               if(var24 != null && var24.typeOfHit == MovingObjectType.BLOCK) {
                  Block var27 = super.worldObj.getBlock(var24.blockX, var24.blockY, var24.blockZ);
                  Material var31 = var27.getMaterial();
                  if(this.type.explodeOnImpact) {
                     this.detonate();
                  } else if(this.type.breaksGlass && var31 == Material.glass && TeamsManager.canBreakGlass) {
                     super.worldObj.setBlockToAir(var24.blockX, var24.blockY, var24.blockZ);
                     FlansMod.proxy.playBlockBreakSound(var24.blockX, var24.blockY, var24.blockZ, var27);
                  } else if(!this.type.penetratesBlocks) {
                     Vector3f var34 = new Vector3f(var24.hitVec);
                     Vector3f var35 = Vector3f.sub(var34, var16, (Vector3f)null);
                     Vector3f postHitMotVec = Vector3f.sub(var18, var35, (Vector3f)null);
                     int sideHit = var24.sideHit;
                     switch(sideHit) {
                     case 0:
                     case 1:
                        postHitMotVec.setY(-postHitMotVec.getY());
                        break;
                     case 2:
                     case 3:
                        postHitMotVec.setZ(-postHitMotVec.getZ());
                        break;
                     case 4:
                     case 5:
                        postHitMotVec.setX(-postHitMotVec.getX());
                     }

                     float lambda = Math.abs(var18.lengthSquared()) < 1.0E-8F?1.0F:postHitMotVec.length() / var18.length();
                     postHitMotVec.scale(this.type.bounciness / 2.0F);
                     super.posX += (double)(var35.x + postHitMotVec.x);
                     super.posY += (double)(var35.y + postHitMotVec.y);
                     super.posZ += (double)(var35.z + postHitMotVec.z);
                     super.motionX = (double)(postHitMotVec.x / lambda);
                     super.motionY = (double)(postHitMotVec.y / lambda);
                     super.motionZ = (double)(postHitMotVec.z / lambda);
                     var18 = new Vector3f(super.motionX, super.motionY, super.motionZ);
                     float randomSpinner = 90.0F;
                     Vector3f.add(this.angularVelocity, new Vector3f(super.rand.nextGaussian() * (double)randomSpinner, super.rand.nextGaussian() * (double)randomSpinner, super.rand.nextGaussian() * (double)randomSpinner), this.angularVelocity);
                     this.angularVelocity.scale(var18.lengthSquared());
                     if((double)var18.lengthSquared() > 0.01D) {
                        this.playSound(this.type.bounceSound, 1.0F, 1.2F / (super.rand.nextFloat() * 0.2F + 0.9F));
                     }

                     if(this.type.sticky) {
                        super.posX = (double)var34.x;
                        super.posY = (double)var34.y;
                        super.posZ = (double)var34.z;
                        super.motionX = super.motionY = super.motionZ = 0.0D;
                        this.angularVelocity.set(0.0F, 0.0F, 0.0F);
                        float yaw = this.axes.getYaw();
                        switch(var24.sideHit) {
                        case 0:
                           this.axes.setAngles(yaw, 180.0F, 0.0F);
                           break;
                        case 1:
                           this.axes.setAngles(yaw, 0.0F, 0.0F);
                           break;
                        case 2:
                           this.axes.setAngles(270.0F, 90.0F, 0.0F);
                           this.axes.rotateLocalYaw(yaw);
                           break;
                        case 3:
                           this.axes.setAngles(90.0F, 90.0F, 0.0F);
                           this.axes.rotateLocalYaw(yaw);
                           break;
                        case 4:
                           this.axes.setAngles(180.0F, 90.0F, 0.0F);
                           this.axes.rotateLocalYaw(yaw);
                           break;
                        case 5:
                           this.axes.setAngles(0.0F, 90.0F, 0.0F);
                           this.axes.rotateLocalYaw(yaw);
                        }

                        this.stuck = true;
                        this.stuckToX = var24.blockX;
                        this.stuckToY = var24.blockY;
                        this.stuckToZ = var24.blockZ;
                     }
                  }
               } else {
                  super.posX += super.motionX;
                  super.posY += super.motionY;
                  super.posZ += super.motionZ;
               }

               this.setPosition(super.posX, super.posY, super.posZ);
            }

            if(this.type.stickToThrower) {
               if(this.thrower != null && !this.thrower.isDead) {
                  this.setPosition(this.thrower.posX, this.thrower.posY, this.thrower.posZ);
               } else {
                  this.setDead();
               }
            }

            float var20;
            if(this.type.stickToEntity) {
               var14 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox);
               var20 = this.axes.getYaw();
               if(this.stickedEntity == null && !this.stuck) {
                  var21 = var14.iterator();

                  while(var21.hasNext()) {
                     var23 = var21.next();
                     if(var23 instanceof Entity && var23 != this.thrower && !(var23 instanceof EntityGrenade)) {
                        this.stickedEntity = (Entity)var23;
                        break;
                     }
                  }
               }

               if(this.stickedEntity != null) {
                  this.setPosition(this.stickedEntity.posX, this.stickedEntity.posY, this.stickedEntity.posZ);
                  if(this.stickedEntity.isDead) {
                     this.setDead();
                  }
               }
            }

            if(this.type.stickToDriveable) {
               var14 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox);
               var20 = this.axes.getYaw();
               if(this.stickedEntity == null && !this.stuck) {
                  var21 = var14.iterator();

                  while(var21.hasNext()) {
                     var23 = var21.next();
                     if(var23 instanceof Entity && var23 != this.thrower && !(var23 instanceof EntityGrenade) && var23 instanceof EntityDriveable) {
                        this.stickedEntity = (Entity)var23;
                        break;
                     }
                  }
               }

               if(this.stickedEntity != null) {
                  this.setPosition(this.stickedEntity.posX, this.stickedEntity.posY, this.stickedEntity.posZ);
                  if(this.stickedEntity.isDead) {
                     this.setDead();
                  }
               }
            }

            if(this.type.stickToEntityAfter) {
               var14 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox);
               var20 = this.axes.getYaw();
               if(this.stickedEntity == null) {
                  var21 = var14.iterator();

                  while(var21.hasNext()) {
                     var23 = var21.next();
                     if(var23 instanceof Entity && !(var23 instanceof EntityGrenade) && var23 != this.thrower) {
                        if(this.type.allowStickSound) {
                           PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)this.type.stickSoundRange, super.dimension, this.type.stickSound, true);
                        }

                        this.stickedEntity = (Entity)var23;
                        break;
                     }
                  }
               }

               if(this.stickedEntity != null) {
                  this.setPosition(this.stickedEntity.posX, this.stickedEntity.posY, this.stickedEntity.posZ);
                  if(this.stickedEntity.isDead) {
                     this.setDead();
                  }
               }
            }

            if(this.thrower != null && this.type.motionSensor && super.ticksExisted % 5 == 0 && this.motionTime == 0 && (!super.worldObj.isRemote || FlansMod.proxy.isThePlayer((EntityPlayer)this.thrower) || FlansMod.proxy.isOnSameTeamClientPlayer(this.thrower))) {
           
               var15 = this.type.motionSensorRange;
               var17 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.expand((double)var15, (double)var15, (double)var15));
               Iterator var28 = var17.iterator();

               while(var28.hasNext()) {
                  Object var29 = var28.next();
                  if(var29 instanceof Entity && var29 != this.thrower && var29 != this && !(var29 instanceof EntityBullet)) {
                     Entity var26 = (Entity)var29;
                     if(var26.motionX != 0.0D || var26.motionY > 0.0D || var26.motionZ != 0.0D || Math.abs(var26.posX - var26.prevPosX) + Math.abs(var26.posY - var26.prevPosY) + Math.abs(var26.posZ - var26.prevPosZ) > 0.05D) {
                        if(!super.worldObj.isRemote) {
                           PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)this.type.motionSoundRange, super.dimension, this.type.motionSound, true);
                           this.motionTime = this.type.motionTime;
                           break;
                        }

                        if(var26 instanceof EntityPlayer && !FlansMod.proxy.isThePlayer((EntityPlayer)var26)) {
                         
                           this.motionTime = this.type.motionTime;
                        }
                     }
                  }
               }
            }

            if((this.type.damageVsLiving > 0.0F || this.type.damageVsPlayer > 0.0F) && !this.stuck) {
               var16 = new Vector3f(super.motionX, super.motionY, super.motionZ);
               var17 = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox);
               var21 = var17.iterator();

               while(var21.hasNext()) {
                  var23 = var21.next();
                  if((var23 != this.thrower || super.ticksExisted >= 10) && (double)var16.lengthSquared() >= 0.01D) {
                     if(var23 instanceof EntityPlayer) {
                        ((EntityPlayer)var23).attackEntityFrom(this.getGrenadeDamage(), this.type.damageVsPlayer * var16.lengthSquared() * 3.0F);
                     } else if(var23 instanceof EntityLivingBase) {
                        ((EntityLivingBase)var23).attackEntityFrom(this.getGrenadeDamage(), this.type.damageVsLiving * var16.lengthSquared() * 3.0F);
                     }
                  }
               }
            }

            super.motionY -= 0.024525D * (double)this.type.fallSpeed;
            if(super.worldObj.isRemote) {
               this.extinguish();
            }

         }
      }
   }

   public boolean attackEntityFrom(DamageSource source, float f) {
      if(this.type.detonateWhenShot) {
         this.detonate();
      }

      return this.type.detonateWhenShot;
   }

   public void detonate() {
      if(super.ticksExisted >= this.type.primeDelay) {
         if(!this.detonated) {
            this.detonated = true;
            PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.type.detonateSound, true);
            if(!super.worldObj.isRemote && this.type.explosionRadius > 0.1F) {
               if(this.thrower instanceof EntityPlayer) {
                  new FlansModExplosion(super.worldObj, this, (EntityPlayer)this.thrower, this.type, super.posX, super.posY, super.posZ, this.type.explosionRadius, TeamsManager.explosions && this.type.explosionBreaksBlocks, this.type.explosionDamageVsLiving, this.type.explosionDamageVsPlayer, this.type.explosionDamageVsPlane, this.type.explosionDamageVsVehicle, this.type.smokeParticleCount, this.type.debrisParticleCount);
               } else {
                  super.worldObj.createExplosion(this, super.posX, super.posY, super.posZ, this.type.explosionRadius, TeamsManager.explosions && this.type.explosionBreaksBlocks);
               }
            }

            if(!super.worldObj.isRemote && this.type.fireRadius > 0.1F) {
               for(float list = -this.type.fireRadius; list < this.type.fireRadius; ++list) {
                  for(float damage = -this.type.fireRadius; damage < this.type.fireRadius; ++damage) {
                     for(float dropStack = -this.type.fireRadius; dropStack < this.type.fireRadius; ++dropStack) {
                        int obj = MathHelper.floor_double((double)list + super.posX);
                        int entity = MathHelper.floor_double((double)damage + super.posY);
                        int z = MathHelper.floor_double((double)dropStack + super.posZ);
                        if(list * list + damage * damage + dropStack * dropStack <= this.type.fireRadius * this.type.fireRadius && super.worldObj.getBlock(obj, entity, z) == Blocks.air && super.rand.nextBoolean()) {
                           super.worldObj.setBlock(obj, entity, z, Blocks.fire, 0, 3);
                        }
                     }
                  }
               }
            }

            for(int var7 = 0; var7 < this.type.explodeParticles; ++var7) {
               super.worldObj.spawnParticle(this.type.explodeParticleType, super.posX, super.posY, super.posZ, super.rand.nextGaussian(), super.rand.nextGaussian(), super.rand.nextGaussian());
            }

            if(!super.worldObj.isRemote && this.type.dropItemOnDetonate != null) {
               String var8 = this.type.dropItemOnDetonate;
               int var9 = 0;
               if(var8.contains(".")) {
                  var9 = Integer.parseInt(var8.split("\\.")[1]);
                  var8 = var8.split("\\.")[0];
               }

               ItemStack var11 = InfoType.getRecipeElement(var8, var9);
               this.entityDropItem(var11, 1.0F);
            }

            if(this.type.smokeTime > 0) {
               this.smoking = true;
               this.smokeTime = this.type.smokeTime;
            } else if(!super.worldObj.isRemote) {
               this.setDead();
            }

            if(this.type.flashBang && !super.worldObj.isRemote) {
               List var10 = super.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, super.boundingBox.expand((double)this.type.smokeRadius, (double)this.type.smokeRadius, (double)this.type.smokeRadius));
               Iterator var12 = var10.iterator();

               while(var12.hasNext()) {
                  Object var13 = var12.next();
                  EntityLivingBase var14 = (EntityLivingBase)var13;
                  if(var14.getDistanceToEntity(this) < (float)this.type.flashRange && this.type.flashDamageEnable) {
                     if(this.type.flashEffects) {
                        var14.addPotionEffect(new PotionEffect(this.type.flashEffectsID, this.type.flashEffectsDuration, this.type.flashEffectsLevel));
                     }

                     var14.attackEntityFrom(this.getGrenadeDamage(), this.type.flashDamage);
                  }
               }

               FlansMod.getPacketHandler().sendToAllAround(new PacketFlak(super.posX, super.posY, super.posZ, 50, this.type.smokeParticleType), super.posX, super.posY, super.posZ, 30.0F, super.dimension);
               if(this.type.flashSoundEnable) {
                  PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)this.type.flashSoundRange, super.dimension, this.type.flashSound, true);
               }

               FlansMod.getPacketHandler().sendToAllAround(new PacketFlashBang(this.type.flashTime), super.posX, super.posY, super.posZ, (float)this.type.flashRange, super.dimension);
               this.setDead();
            }

         }
      }
   }

   public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int i) {}

   private DamageSource getGrenadeDamage() {
      return this.thrower instanceof EntityPlayer?(new EntityDamageSourceGun(this.type.shortName, this, (EntityPlayer)this.thrower, this.type, false)).setProjectile():(new EntityDamageSourceIndirect(this.type.shortName, this, this.thrower)).setProjectile();
   }

   protected void entityInit() {}

   protected void readEntityFromNBT(NBTTagCompound tags) {
      this.type = GrenadeType.getGrenade(tags.getString("Type"));
      if(this.type == null) {
         FlansMod.log("EntityGrenade.readEntityFromNBT() Error: GrenadeType is null (" + this + ")");
         this.setDead();
      } else {
         this.thrower = super.worldObj.getPlayerEntityByName(tags.getString("Thrower"));
         super.rotationYaw = tags.getFloat("RotationYaw");
         super.rotationPitch = tags.getFloat("RotationPitch");
         this.axes.setAngles(super.rotationYaw, super.rotationPitch, 0.0F);
      }
   }

   protected void writeEntityToNBT(NBTTagCompound tags) {
      if(this.type == null) {
         FlansMod.log("EntityGrenade.writeEntityToNBT() Error: GrenadeType is null (" + this + ")");
         this.setDead();
      } else {
         tags.setString("Type", this.type.shortName);
         if(this.thrower != null) {
            tags.setString("Thrower", this.thrower.getCommandSenderName());
         }

         tags.setFloat("RotationYaw", this.axes.getYaw());
         tags.setFloat("RotationPitch", this.axes.getPitch());
      }
   }

   public void writeSpawnData(ByteBuf data) {
      ByteBufUtils.writeUTF8String(data, this.type.shortName);
      data.writeInt(this.thrower == null?0:this.thrower.getEntityId());
      data.writeFloat(this.axes.getYaw());
      data.writeFloat(this.axes.getPitch());
   }

   public void readSpawnData(ByteBuf data) {
      this.type = GrenadeType.getGrenade(ByteBufUtils.readUTF8String(data));
      this.thrower = (EntityLivingBase)super.worldObj.getEntityByID(data.readInt());
      this.setRotation(data.readFloat(), data.readFloat());
      super.prevRotationYaw = super.rotationYaw;
      super.prevRotationPitch = super.rotationPitch;
      this.axes.setAngles(super.rotationYaw, super.rotationPitch, 0.0F);
      if(this.type.spinWhenThrown) {
         this.angularVelocity = new Vector3f(0.0F, 0.0F, 10.0F);
      }

   }

   public boolean isBurning() {
      return false;
   }

   public boolean canBeCollidedWith() {
      return !super.isDead && this.type.isDeployableBag;
   }

   public boolean interactFirst(EntityPlayer player) {
      if(this.type.isDeployableBag && !super.worldObj.isRemote) {
         boolean used = false;
         if(this.type.healAmount > 0.0F && player.getHealth() < player.getMaxHealth()) {
            player.heal(this.type.healAmount);
            FlansMod.getPacketHandler().sendToAllAround((PacketBase)(new PacketFlak(player.posX, player.posY, player.posZ, 5, "heart")), new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 50.0D));
            used = true;
         }

         for(Iterator gun = this.type.potionEffects.iterator(); gun.hasNext(); used = true) {
            PotionEffect bulletToGive = (PotionEffect)gun.next();
            player.addPotionEffect(new PotionEffect(bulletToGive));
         }

         if(this.type.numClips > 0 && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemGun) {
            GunType var6 = ((ItemGun)player.getCurrentEquippedItem().getItem()).type;
            if(var6.ammo.size() > 0) {
               ShootableType var7 = (ShootableType)var6.ammo.get(0);
               int numToGive = Math.min(var7.maxStackSize, this.type.numClips * var6.numAmmoItemsInGun);
               if(player.inventory.addItemStackToInventory(new ItemStack(var7.item, numToGive))) {
                  used = true;
               }
            }
         }

         if(used) {
            --this.numUsesRemaining;
            if(this.numUsesRemaining <= 0) {
               this.setDead();
            }
         }
      }

      return true;
   }
}
