package com.flansmod.common.guns;

import com.flansmod.client.debug.EntityDebugDot;
import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.EntityAAGun;
import com.flansmod.common.guns.EntityDamageSourceGun;
import com.flansmod.common.guns.EntityGrenade;
import com.flansmod.common.guns.EntityMG;
import com.flansmod.common.guns.EntityShootable;
import com.flansmod.common.guns.FlansModExplosion;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.ItemShootable;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.guns.raytracing.BlockHit;
import com.flansmod.common.guns.raytracing.BulletHit;
import com.flansmod.common.guns.raytracing.DriveableHit;
import com.flansmod.common.guns.raytracing.EntityHit;
import com.flansmod.common.guns.raytracing.EnumHitboxType;
import com.flansmod.common.guns.raytracing.PlayerBulletHit;
import com.flansmod.common.guns.raytracing.PlayerHitbox;
import com.flansmod.common.guns.raytracing.PlayerSnapshot;
import com.flansmod.common.network.PacketFlak;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBullet extends EntityShootable implements IEntityAdditionalSpawnData {

   private static int bulletLife = 600;
   public Entity owner;
   private int ticksInAir;
   public BulletType type;
   public InfoType firedFrom;
   public float damage;
   public boolean shotgun;
   public int pingOfShooter;
   public static Random bulletRandom = new Random();
   public boolean detonated;
   public Entity lockedOnTo;
   public double prevDistanceToEntity;
   public int cnt;
   public boolean toggleLock;
   public double lockedOnToSpeed;
   public double thisSpeed;
   public int closeCount;
   public int soundTime;
   public float speedA;
   public int impactX;
   public int impactY;
   public int impactZ;
   public boolean isFirstPositionSetting;
   public boolean isPositionUpper;
   public static boolean hitCrossHair;
   public float penetratingPower;
   public int submunitionDelay;
   public boolean hasSetSubDelay;
   public boolean hasSetVLSDelay;
   public int VLSDelay;
   public Vector3f lookVector;
   public Vector3f initialPos;
   public boolean hasSetLook;


   public EntityBullet(World world) {
      super(world);
      this.shotgun = false;
      this.pingOfShooter = 0;
      this.detonated = false;
      this.prevDistanceToEntity = 0.0D;
      this.toggleLock = false;
      this.lockedOnToSpeed = 0.0D;
      this.thisSpeed = 0.0D;
      this.closeCount = 0;
      this.soundTime = 0;
      this.isFirstPositionSetting = true;
      this.isPositionUpper = true;
      this.submunitionDelay = 20;
      this.hasSetSubDelay = false;
      this.hasSetVLSDelay = false;
      this.VLSDelay = 0;
      this.hasSetLook = false;
      this.ticksInAir = 0;
      this.setSize(0.5F, 0.5F);
   }

   private EntityBullet(World world, EntityLivingBase shooter, float gunDamage, BulletType bulletType, InfoType shotFrom) {
      this(world);
      this.owner = shooter;
      if(shooter instanceof EntityPlayerMP) {
         this.pingOfShooter = ((EntityPlayerMP)shooter).ping;
      }

      this.type = bulletType;
      this.firedFrom = shotFrom;
      this.damage = gunDamage;
      this.penetratingPower = this.type.penetratingPower;
      this.setSize(bulletType.hitBoxSize, bulletType.hitBoxSize);
   }

   public EntityBullet(World world, EntityLivingBase shooter, float spread, float gunDamage, BulletType type1, float speed, boolean shot, InfoType shotFrom) {
      this(world, Vec3.createVectorHelper(shooter.posX, shooter.posY + (double)shooter.getEyeHeight(), shooter.posZ), shooter.rotationYaw, shooter.rotationPitch, shooter, spread, gunDamage, type1, speed, shotFrom);
      this.shotgun = shot;
   }

   public EntityBullet(World world, Vec3 origin, float yaw, float pitch, EntityLivingBase shooter, float spread, float gunDamage, BulletType type1, InfoType shotFrom) {
      this(world, origin, yaw, pitch, shooter, spread, gunDamage, type1, 3.0F, shotFrom);
   }

   public EntityBullet(World world, Vec3 origin, float yaw, float pitch, EntityLivingBase shooter, float spread, float gunDamage, BulletType type1, float speed, InfoType shotFrom) {
      this(world, shooter, gunDamage, type1, shotFrom);
      this.setLocationAndAngles(origin.xCoord, origin.yCoord, origin.zCoord, yaw, pitch);
      this.setPosition(super.posX, super.posY, super.posZ);
      super.yOffset = 0.0F;
      super.motionX = (double)(-MathHelper.sin(super.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(super.rotationPitch / 180.0F * 3.1415927F));
      super.motionZ = (double)(MathHelper.cos(super.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(super.rotationPitch / 180.0F * 3.1415927F));
      super.motionY = (double)(-MathHelper.sin(super.rotationPitch / 180.0F * 3.141593F));
      this.setArrowHeading(super.motionX, super.motionY, super.motionZ, spread / 2.0F, speed);
   }

   public EntityBullet(World world, Vector3f origin, Vector3f direction, EntityLivingBase shooter, float spread, float gunDamage, BulletType type1, float speed, InfoType shotFrom) {
      this(world, shooter, gunDamage, type1, shotFrom);
      this.damage = gunDamage;
      this.setPosition((double)origin.x, (double)origin.y, (double)origin.z);
      super.motionX = (double)direction.x;
      super.motionY = (double)direction.y;
      super.motionZ = (double)direction.z;
      this.setArrowHeading(super.motionX, super.motionY, super.motionZ, spread, speed);
   }

   public EntityBullet(World world, Vec3 origin, float yaw, float pitch, double motX, double motY, double motZ, EntityLivingBase shooter, float gunDamage, BulletType type1, InfoType shotFrom) {
      this(world, shooter, gunDamage, type1, shotFrom);
      this.setLocationAndAngles(origin.xCoord, origin.yCoord, origin.zCoord, yaw, pitch);
      this.setPosition(super.posX, super.posY, super.posZ);
      super.yOffset = 0.0F;
      super.motionX = motX;
      super.motionY = motY;
      super.motionZ = motZ;
   }

   protected void entityInit() {}

   public void setArrowHeading(double d, double d1, double d2, float spread, float speed) {
      float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
      d /= (double)f2;
      d1 /= (double)f2;
      d2 /= (double)f2;
      d *= (double)speed;
      d1 *= (double)speed;
      d2 *= (double)speed;
      d += super.rand.nextGaussian() * 0.005D * (double)spread * (double)speed;
      d1 += super.rand.nextGaussian() * 0.005D * (double)spread * (double)speed;
      d2 += super.rand.nextGaussian() * 0.005D * (double)spread * (double)speed;
      super.motionX = d;
      super.motionY = d1;
      super.motionZ = d2;
      float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
      super.prevRotationYaw = super.rotationYaw = (float)(Math.atan2(d, d2) * 180.0D / 3.1415927410125732D);
      super.prevRotationPitch = super.rotationPitch = (float)(Math.atan2(d1, (double)f3) * 180.0D / 3.1415927410125732D);
      this.getLockOnTarget();
   }

   private void getLockOnTarget() {
      if(this.type.lockOnToPlanes || this.type.lockOnToVehicles || this.type.lockOnToMechas || this.type.lockOnToLivings || this.type.lockOnToPlayers) {
         Vector3f motionVec = new Vector3f(super.motionX, super.motionY, super.motionZ);
         Entity closestEntity = null;
         float closestAngle = this.type.maxLockOnAngle * 3.1415927F / 180.0F;
         Iterator var4 = super.worldObj.loadedEntityList.iterator();

         while(var4.hasNext()) {
            Object obj = var4.next();
            Entity entity = (Entity)obj;
            String etype = entity.getEntityData().getString("EntityType");
            if(this.type.lockOnToMechas && entity instanceof EntityMecha || this.type.lockOnToVehicles && entity instanceof EntityVehicle || this.type.lockOnToVehicles && etype.equals("Vehicle") || this.type.lockOnToPlanes && entity instanceof EntityPlane || this.type.lockOnToPlanes && etype.equals("Plane") || this.type.lockOnToPlayers && entity instanceof EntityPlayer || this.type.lockOnToLivings && entity instanceof EntityLivingBase) {
               Vector3f relPosVec = new Vector3f(entity.posX - super.posX, entity.posY - super.posY, entity.posZ - super.posZ);
               float angle = Math.abs(Vector3f.angle(motionVec, relPosVec));
               if(angle < closestAngle) {
                  closestEntity = entity;
                  closestAngle = angle;
               }
            }
         }

         if(closestEntity != null) {
            this.lockedOnTo = closestEntity;
         }
      }

   }

   public void setVelocity(double d, double d1, double d2) {
      super.motionX = d;
      super.motionY = d1;
      super.motionZ = d2;
      if(super.prevRotationPitch == 0.0F && super.prevRotationYaw == 0.0F) {
         float f = MathHelper.sqrt_double(d * d + d2 * d2);
         super.prevRotationYaw = super.rotationYaw = (float)(Math.atan2(d, d2) * 180.0D / 3.1415927410125732D);
         super.prevRotationPitch = super.rotationPitch = (float)(Math.atan2(d1, (double)f) * 180.0D / 3.1415927410125732D);
         this.setLocationAndAngles(super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch);
      }

   }

   public void onUpdate() {
      super.onUpdate();
      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      if(this.type == null) {
         FlansMod.log("EntityBullet.onUpdate() Error: BulletType is null (" + this + ")");
         this.setDead();
      } else if(this.type.despawnTime > 0 && super.ticksExisted > this.type.despawnTime) {
         this.detonated = true;
         this.setDead();
      } else {
         if(!this.hasSetSubDelay && this.type.hasSubmunitions) {
            this.setSubmunitionDelay();
         } else if(this.type.hasSubmunitions) {
            --this.submunitionDelay;
         }

         if(!this.hasSetVLSDelay && this.type.VLS) {
            this.VLSDelay = this.type.VLSTime;
            this.hasSetVLSDelay = true;
         }

         if(this.VLSDelay > 0) {
            --this.VLSDelay;
         }

         if(!this.hasSetLook && this.owner != null) {
            this.lookVector = new Vector3f((float)this.owner.getLookVec().xCoord, (float)this.owner.getLookVec().yCoord, (float)this.owner.getLookVec().zCoord);
            this.initialPos = new Vector3f(this.owner.posX, this.owner.posY, this.owner.posZ);
            this.hasSetLook = true;
         }

         if(this.soundTime > 0) {
            --this.soundTime;
         }

         if(this.owner != null) {
            double hits = this.owner.posX - super.posX;
            double motion = this.owner.posY - super.posY;
            double speed = this.owner.posZ - super.posZ;
            double nextPosVec = Math.sqrt(hits * hits + motion * motion + speed * speed);
            if(this.type.maxRange != -1 && (double)this.type.maxRange < nextPosVec) {
               if(super.ticksExisted > this.type.fuse && this.type.fuse > 0) {
                  this.detonate();
               }

               this.setDead();
            }
         } else {
            this.setDead();
         }

         ++this.ticksInAir;
         if(this.ticksInAir > this.type.fuse && this.type.fuse > 0 && !super.isDead) {
            this.setDead();
         }

         if(super.ticksExisted > bulletLife) {
            this.setDead();
         }

         if(!super.isDead) {
            if(!super.worldObj.isRemote) {
               if(super.ticksExisted > this.type.fuse && this.type.fuse > 0) {
                  this.detonate();
               }

               if(this.type.livingProximityTrigger > 0.0F || this.type.driveableProximityTrigger > 0.0F) {
                  float var33 = Math.max(this.type.livingProximityTrigger, this.type.driveableProximityTrigger);
                  List origin = super.worldObj.getEntitiesWithinAABBExcludingEntity(this, super.boundingBox.expand((double)var33, (double)var33, (double)var33));
                  Iterator var36 = origin.iterator();

                  while(var36.hasNext()) {
                     Object hitBoxSize = var36.next();
                     if(hitBoxSize != this.owner || super.ticksExisted >= 10) {
                        if(hitBoxSize instanceof EntityLivingBase && this.getDistanceToEntity((Entity)hitBoxSize) < this.type.livingProximityTrigger) {
                           if(TeamsManager.getInstance() == null || TeamsManager.getInstance().currentRound == null || !(hitBoxSize instanceof EntityPlayerMP) || !(this.owner instanceof EntityPlayer) || TeamsManager.getInstance().currentRound.gametype.playerAttacked((EntityPlayerMP)hitBoxSize, new EntityDamageSourceGun(this.type.shortName, this, (EntityPlayer)this.owner, this.type, false))) {
                              if(this.type.damageToTriggerer > 0.0F) {
                                 ((EntityLivingBase)hitBoxSize).attackEntityFrom(this.getBulletDamage(false), this.type.damageToTriggerer);
                              }

                              FlansMod.proxy.spawnParticle("redstone", super.posX, super.posY, super.posZ, 0.0D, 0.0D, 0.0D);
                              this.detonate();
                              break;
                           }
                        } else if(hitBoxSize instanceof EntityDriveable && this.getDistanceToEntity((Entity)hitBoxSize) < this.type.driveableProximityTrigger) {
                           if(this.type.damageToTriggerer > 0.0F) {
                              ((EntityDriveable)hitBoxSize).attackEntityFrom(this.getBulletDamage(false), this.type.damageToTriggerer);
                           }

                           this.detonate();
                           break;
                        }
                     }
                  }
               }
            }

            ArrayList var34 = new ArrayList();
            Vector3f var35 = new Vector3f(super.posX, super.posY, super.posZ);
            Vector3f var37 = new Vector3f(super.motionX, super.motionY, super.motionZ);
            float var38 = this.type.hitBoxSize >= 0.0F?this.type.hitBoxSize:0.0F;
            float var39 = var37.length();
            this.speedA = var39;
            if(this.type.hasSubmunitions && this.submunitionDelay < 0) {
               this.DeploySubmunitions();
               this.submunitionDelay = 9001;
            }

            int motionXZ;
            Vector3f var54;
            Vector3f var55;
            float var56;
            float var57;
            for(int posVec = 0; posVec < super.worldObj.loadedEntityList.size(); ++posVec) {
               Object var41 = super.worldObj.loadedEntityList.get(posVec);
               if(var41 instanceof EntityDriveable) {
                  EntityDriveable hit = (EntityDriveable)var41;
                  if(!hit.isDead() && !hit.isPartOfThis(this.owner) && this.getDistanceToEntity(hit) <= hit.getDriveableType().bulletDetectionRadius + var39) {
                     ArrayList drag = hit.attackFromBullet(var35, var37);
                     var34.addAll(drag);
                  }
               } else if(var41 instanceof EntityPlayer) {
                  EntityPlayer var43 = (EntityPlayer)var41;
                  PlayerData var46 = PlayerHandler.getPlayerData(var43);
                  boolean gravity = false;
                  if(var46 != null) {
                     if(var43.isDead || var46.team == Team.spectators || var43 == this.owner && this.ticksInAir < 20) {
                        continue;
                     }

                     motionXZ = TeamsManager.bulletSnapshotMin;
                     if(TeamsManager.bulletSnapshotDivisor > 0) {
                        motionXZ += this.pingOfShooter / TeamsManager.bulletSnapshotDivisor;
                     }

                     if(motionXZ >= var46.snapshots.length) {
                        motionXZ = var46.snapshots.length - 1;
                     }

                     PlayerSnapshot length;
                     if(var46.snapshots[motionXZ] != null) {
                        length = var46.snapshots[motionXZ];
                     } else {
                        length = var46.snapshots[0];
                     }

                     if(length == null) {
                        gravity = true;
                     } else {
                        ArrayList x = length.raytrace(var35, var37);
                        var34.addAll(x);
                     }
                  }

                  if(var46 == null || gravity) {
                     MovingObjectPosition var53 = var43.boundingBox.expand((double)var38, (double)var38, (double)var38).calculateIntercept(var35.toVec3(), Vec3.createVectorHelper(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ));
                     if(var53 != null) {
                        var55 = new Vector3f(var53.hitVec.xCoord - super.posX, var53.hitVec.yCoord - super.posY, var53.hitVec.zCoord - super.posZ);
                        var57 = 1.0F;
                        if(var37.x != 0.0F) {
                           var57 = var55.x / var37.x;
                        } else if(var37.y != 0.0F) {
                           var57 = var55.y / var37.y;
                        } else if(var37.z != 0.0F) {
                           var57 = var55.z / var37.z;
                        }

                        if(var57 < 0.0F) {
                           var57 = -var57;
                        }

                        var34.add(new PlayerBulletHit(new PlayerHitbox(var43, new RotatedAxes(), new Vector3f(), new Vector3f(), new Vector3f(), EnumHitboxType.BODY), var57));
                     }
                  }
               } else {
                  Entity var44 = (Entity)var41;
                  if(var44 != this && var44 != this.owner && !var44.isDead && !(var44 instanceof EntityItem) && !(var44 instanceof EntityXPOrb) && !(var44 instanceof EntityArrow) && (var44.getClass().toString().indexOf("flansmod.") < 0 || var44 instanceof EntityAAGun || var44 instanceof EntityGrenade)) {
                     AxisAlignedBB var47 = var44.boundingBox.addCoord(-(var44.posX - var44.prevPosX) * 2.0D, -(var44.posY - var44.prevPosY) * 2.0D, -(var44.posZ - var44.prevPosZ) * 2.0D);
                     MovingObjectPosition var51 = var47.expand((double)var38, (double)var38, (double)var38).calculateIntercept(var35.toVec3(), Vec3.createVectorHelper(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ));
                     if(var51 != null) {
                        var54 = new Vector3f(var51.hitVec.xCoord - super.posX, var51.hitVec.yCoord - super.posY, var51.hitVec.zCoord - super.posZ);
                        var56 = 1.0F;
                        if(var37.x != 0.0F) {
                           var56 = var54.x / var37.x;
                        } else if(var37.y != 0.0F) {
                           var56 = var54.y / var37.y;
                        } else if(var37.z != 0.0F) {
                           var56 = var54.z / var37.z;
                        }

                        if(var56 < 0.0F) {
                           var56 = -var56;
                        }

                        var34.add(new EntityHit(var44, var56));
                     }
                  }
               }
            }

            Vec3 var40 = Vec3.createVectorHelper(super.posX, super.posY, super.posZ);
            Vec3 var42 = Vec3.createVectorHelper(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ);
            MovingObjectPosition var45 = super.worldObj.func_147447_a(var40, var42, false, true, true);
            var40 = Vec3.createVectorHelper(super.posX, super.posY, super.posZ);
            float var52;
            if(var45 != null) {
               Vec3 var48 = var40.subtract(var45.hitVec);
               var52 = 1.0F;
               if(super.motionX != 0.0D) {
                  var52 = (float)(var48.xCoord / super.motionX);
               } else if(super.motionY != 0.0D) {
                  var52 = (float)(var48.yCoord / super.motionY);
               } else if(super.motionZ != 0.0D) {
                  var52 = (float)(var48.zCoord / super.motionZ);
               }

               if(var52 < 0.0F) {
                  var52 = -var52;
               }

               var34.add(new BlockHit(var45, var52));
            }

            if(!var34.isEmpty()) {
               Collections.sort(var34);
               Iterator var49 = var34.iterator();

               while(var49.hasNext()) {
                  BulletHit var59 = (BulletHit)var49.next();
                  if(var59 instanceof DriveableHit) {
                     if(this.type.entityHitSoundEnable) {
                        PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)this.type.hitSoundRange, super.dimension, this.type.hitSound, true);
                     }

                     if(super.worldObj.isRemote && this.owner instanceof EntityPlayer && FlansMod.proxy.isThePlayer((EntityPlayer)this.owner)) {
                        hitCrossHair = true;
                     }

                     DriveableHit var58 = (DriveableHit)var59;
                     var58.driveable.lastAtkEntity = this.owner;
                     this.penetratingPower = var58.driveable.bulletHit(this, var58, this.penetratingPower);
                     if(this.type.canSpotEntityDriveable) {
                        var58.driveable.setEntityMarker(200);
                     }

                     if(FlansMod.DEBUG) {
                        super.worldObj.spawnEntityInWorld(new EntityDebugDot(super.worldObj, new Vector3f(super.posX + super.motionX * (double)var58.intersectTime, super.posY + super.motionY * (double)var58.intersectTime, super.posZ + super.motionZ * (double)var58.intersectTime), 1000, 0.0F, 0.0F, 1.0F));
                     }
                  } else if(var59 instanceof PlayerBulletHit) {
                     if(this.type.entityHitSoundEnable) {
                        PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)this.type.hitSoundRange, super.dimension, this.type.hitSound, true);
                     }

                     if(super.worldObj.isRemote && this.owner instanceof EntityPlayer && FlansMod.proxy.isThePlayer((EntityPlayer)this.owner)) {
                        hitCrossHair = true;
                     }

                     PlayerBulletHit var60 = (PlayerBulletHit)var59;
                     this.penetratingPower = var60.hitbox.hitByBullet(this, this.penetratingPower);
                     if(FlansMod.DEBUG) {
                        super.worldObj.spawnEntityInWorld(new EntityDebugDot(super.worldObj, new Vector3f(super.posX + super.motionX * (double)var60.intersectTime, super.posY + super.motionY * (double)var60.intersectTime, super.posZ + super.motionZ * (double)var60.intersectTime), 1000, 1.0F, 0.0F, 0.0F));
                     }
                  } else if(var59 instanceof EntityHit) {
                     if(this.type.entityHitSoundEnable) {
                        PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)this.type.hitSoundRange, super.dimension, this.type.hitSound, true);
                     }

                     if(super.worldObj.isRemote && this.owner instanceof EntityPlayer && FlansMod.proxy.isThePlayer((EntityPlayer)this.owner)) {
                        hitCrossHair = true;
                     }

                     EntityHit var61 = (EntityHit)var59;
                     var56 = this.damage;
                     if(var61.entity instanceof EntityLivingBase) {
                        var56 *= this.type.damageVsLiving;
                        if(var61.entity != this.owner) {
                           FlansMod.proxy.spawnParticle("reddust", var61.entity.posX, var61.entity.posY, var61.entity.posZ, 0.0D, 0.0D, 0.0D);
                        }
                     } else {
                        var56 *= this.type.damageVsEntity;
                     }

                     if(var61.entity.attackEntityFrom(this.getBulletDamage(false), var56) && var61.entity instanceof EntityLivingBase) {
                        EntityLivingBase var69 = (EntityLivingBase)var61.entity;
                        Iterator y = this.type.hitEffects.iterator();

                        while(y.hasNext()) {
                           PotionEffect z = (PotionEffect)y.next();
                           var69.addPotionEffect(new PotionEffect(z));
                        }

                        ++var69.arrowHitTimer;
                        var69.hurtResistantTime = var69.maxHurtResistantTime / 2;
                     }

                     if(this.type.setEntitiesOnFire) {
                        var61.entity.setFire(20);
                     }

                     --this.penetratingPower;
                     if(FlansMod.DEBUG) {
                        super.worldObj.spawnEntityInWorld(new EntityDebugDot(super.worldObj, new Vector3f(super.posX + super.motionX * (double)var61.intersectTime, super.posY + super.motionY * (double)var61.intersectTime, super.posZ + super.motionZ * (double)var61.intersectTime), 1000, 1.0F, 1.0F, 0.0F));
                        FlansMod.log(var61.entity.toString() + ": d=" + var56 + ": damage=" + this.damage + ": type.damageVsEntity=" + this.type.damageVsEntity);
                     }
                  } else if(var59 instanceof BlockHit) {
                      BlockHit var62 = (BlockHit)var59;
                      MovingObjectPosition var75 = var62.raytraceResult;
                      int var70 = var75.blockX;
                      int var63 = var75.blockY;
                      int var66 = var75.blockZ;
                      float volume = 1.1F;
                      Block dXYZ = super.worldObj.getBlock(var70, var63, var66);
                      Material mat = dXYZ.getMaterial();
                      double relPosVec = 0.0D;
                      if(mat == Material.rock) {
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hit.rock", volume, 2.0F);
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hitwizz", volume, 1.0F);
                         super.worldObj.spawnParticle("blockcrack_4_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_4_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_4_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("bulletholeParticle", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                      } else if(mat == Material.iron) {
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hit.metal", volume, 2.0F);
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hitwizz", volume, 1.0F);
                         super.worldObj.spawnParticle("blockcrack_42_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_42_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_42_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                      } else if(mat == Material.wood) {
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hit.wood", volume, 2.0F);
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hitwizz", volume, 1.0F);
                         super.worldObj.spawnParticle("blockcrack_5_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_5_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_5_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                      } else if(mat == Material.ground) {
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hit.dirt", volume, 2.0F);
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hitwizz", volume, 1.0F);
                         super.worldObj.spawnParticle("blockcrack_3_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_3_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_3_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                      } else if(mat == Material.grass) {
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hit.dirt", volume, 2.0F);
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hitwizz", volume, 1.0F);
                         super.worldObj.spawnParticle("blockcrack_2_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_2_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_2_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                      } else if(mat == Material.glass) {
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, dXYZ.stepSound.getBreakSound(), volume, 2.0F);
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hitwizz", volume, 1.0F);
                         super.worldObj.spawnParticle("blockcrack_20_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_20_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_20_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                      } else if(mat == Material.sand) {
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, dXYZ.stepSound.getBreakSound(), volume, 2.0F);
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hitwizz", volume, 1.0F);
                         super.worldObj.spawnParticle("blockcrack_13_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_13_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                         super.worldObj.spawnParticle("blockcrack_13_12", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, relPosVec, 0.0D);
                      } else {
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, dXYZ.stepSound.getBreakSound(), volume, 2.0F);
                         super.worldObj.playSoundEffect(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord - 3.0D, "flansmod:bullet.hitwizz", volume, 1.0F);
                         super.worldObj.spawnParticle("largesmoke", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, 0.08D, 0.0D);
                         super.worldObj.spawnParticle("smoke", var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord, 0.0D, 0.0D, 0.0D);
                         this.setPosition(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord);
                         this.setDead();
                      }
                   

                     if(FlansMod.DEBUG) {
                        super.worldObj.spawnEntityInWorld(new EntityDebugDot(super.worldObj, new Vector3f(var75.hitVec.xCoord, var75.hitVec.yCoord, var75.hitVec.zCoord), 1000, 0.0F, 1.0F, 0.0F));
                     }

                     Block d = super.worldObj.getBlock(var70, var63, var66);
                     Material targetPoint = d.getMaterial();
                     if(this.type.breaksGlass && targetPoint == Material.glass && TeamsManager.canBreakGlass) {
                        super.worldObj.setBlockToAir(var70, var63, var66);
                        FlansMod.proxy.playBlockBreakSound(var70, var63, var66, d);
                     }

                     if(super.worldObj.isRemote && d.getMaterial() != Material.air && this.type.explosionRadius <= 0.0F) {
                        int diff = 2 + super.rand.nextInt(3);

                        for(int speed2 = 0; speed2 < diff; ++speed2) {
                           FlansMod.proxy.spawnParticle("blockcrack_" + Block.getIdFromBlock(d) + "_" + super.worldObj.getBlockMetadata(var70, var63, var66), var75.hitVec.xCoord + ((double)super.rand.nextFloat() - 0.5D) * (double)super.width, var75.hitVec.yCoord + 0.1D, var75.hitVec.zCoord + ((double)super.rand.nextFloat() - 0.5D) * (double)super.width, -super.motionX * 4.0D, 1.5D, -super.motionZ * 4.0D);
                           FlansMod.proxy.spawnParticle("explode", var75.hitVec.xCoord, var75.hitVec.yCoord, var75.hitVec.zCoord, 0.0D, 0.0D, 0.0D);
                           Minecraft.getMinecraft().effectRenderer.addEffect(new BulletHoleFX(worldObj, var75.hitVec.xCoord, var75.hitVec.yCoord, var75.hitVec.zCoord, var75.sideHit));
			            
					}

                     }

                     this.setPosition(var45.hitVec.xCoord, var45.hitVec.yCoord, var45.hitVec.zCoord);
                     if(this.type.hitSoundEnable) {
                        PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, (double)this.type.hitSoundRange, super.dimension, this.type.hitSound, true);
                     }

                     this.setDead();
                     break;
                  }

                  if(this.penetratingPower <= 0.0F || this.type.explodeOnImpact && this.ticksInAir > 1) {
                     this.setPosition(super.posX + super.motionX * (double)var59.intersectTime, super.posY + super.motionY * (double)var59.intersectTime, super.posZ + super.motionZ * (double)var59.intersectTime);
                     this.setDead();
                     break;
                  }
               }
            }

            float var50 = this.type.dragInAir;
            var52 = 0.02F;
            if(this.isInWater()) {
               for(motionXZ = 0; motionXZ < 4; ++motionXZ) {
                  var56 = 0.25F;
                  super.worldObj.spawnParticle("bubble", super.posX - super.motionX * (double)var56, super.posY - super.motionY * (double)var56, super.posZ - super.motionZ * (double)var56, super.motionX, super.motionY + 0.10000000149011612D, super.motionZ);
               }
            }

            if(!this.isInWater() || !this.type.torpedo) {
               super.motionX *= (double)var50;
               super.motionY *= (double)var50;
               super.motionZ *= (double)var50;
               super.motionY -= (double)(var52 * this.type.fallSpeed);
            }

            double targetSpeed;
            double motiona;
            double motionYab;
            double var67;
            double var68;
            double var72;
            double var76;
            double var81;
            Vector3f var83;
            if(this.lockedOnTo != null) {
               EntityDriveable var65;
               if(this.lockedOnTo instanceof EntityDriveable) {
                  var65 = (EntityDriveable)this.lockedOnTo;
                  if(var65.getDriveableType().lockedOnSound != null && this.soundTime <= 0 && !super.worldObj.isRemote) {
                     PacketPlaySound.sendSoundPacket(this.lockedOnTo.posX, this.lockedOnTo.posY, this.lockedOnTo.posZ, (double)var65.getDriveableType().lockedOnSoundRange, super.dimension, var65.getDriveableType().lockedOnSound, false);
                     this.soundTime = var65.getDriveableType().soundTime;
                  }
               } else {
                  this.lockedOnTo.getEntityData().setBoolean("Tracking", true);
               }

               if(super.ticksExisted > this.type.tickStartHoming) {
                  var67 = this.lockedOnTo.posX - super.posX;
                  if(this.type.isDoTopAttack && Math.abs(this.lockedOnTo.posX - super.posX) > 2.0D && Math.abs(this.lockedOnTo.posZ - super.posZ) > 2.0D) {
                     var72 = this.lockedOnTo.posY + 30.0D - super.posY;
                  } else {
                     var72 = this.lockedOnTo.posY - super.posY;
                  }

                  var68 = this.lockedOnTo.posZ - super.posZ;
                  if(!this.type.isDoTopAttack) {
                     var76 = (double)this.getDistanceToEntity(this.lockedOnTo);
                  } else {
                     var76 = Math.sqrt(var67 * var67 + var72 * var72 + var68 * var68);
                  }

                  if(this.owner != null && this.type.enableSACLOS) {
                     var81 = this.lockedOnTo.posX - this.owner.posX;
                     targetSpeed = this.lockedOnTo.posY - this.owner.posY;
                     motiona = this.lockedOnTo.posZ - this.owner.posZ;
                     Vec3 motionXab = this.owner.getLookVec();
                     Vector3f playerVec3f = new Vector3f(motionXab.xCoord, motionXab.yCoord, motionXab.zCoord);
                     motionYab = (double)Math.abs(Vector3f.angle(playerVec3f, new Vector3f(var81, targetSpeed, motiona)));
                     if(motionYab > Math.toRadians((double)this.type.maxDegreeOfSACLOS)) {
                        this.lockedOnTo = null;
                     }
                  }

                  if(this.toggleLock) {
                     if(var76 > (double)this.type.maxRangeOfMissile) {
                        this.lockedOnTo = null;
                     }

                     this.toggleLock = false;
                  }

                  var81 = Math.sqrt(super.motionX * super.motionX + super.motionY * super.motionY + super.motionZ * super.motionZ);
                  var83 = new Vector3f(var67 * var81 / var76, var72 * var81 / var76, var68 * var81 / var76);
                  double angle = (double)Math.abs(Vector3f.angle(var37, var83));
                  if(angle > Math.toRadians((double)this.type.maxDegreeOfMissile)) {
                     this.lockedOnTo = null;
                  } else {
                     super.motionX = (double)var83.x;
                     super.motionY = (double)var83.y;
                     super.motionZ = (double)var83.z;
                  }

                  if(super.ticksExisted > 4 && var76 > this.prevDistanceToEntity) {
                     ++this.closeCount;
                     if(this.closeCount > 15) {
                        this.lockedOnTo = null;
                     }
                  } else if(this.closeCount > 0) {
                     --this.closeCount;
                  }

                  this.prevDistanceToEntity = var76;
               }

               if(this.lockedOnTo instanceof EntityDriveable) {
                  var65 = (EntityDriveable)this.lockedOnTo;
                  if(var65.varFlare || var65.ticksFlareUsing > 0) {
                     this.lockedOnTo = null;
                  }
               } else if(this.lockedOnTo != null && this.lockedOnTo.getEntityData().getBoolean("FlareUsing")) {
                  this.lockedOnTo = null;
               }
            }

            if(this.owner != null && this.type.shootForSettingPos && !this.isFirstPositionSetting) {
               if(this.owner instanceof EntityPlayer) {
                  EntityPlayer var71 = (EntityPlayer)this.owner;
                  if(var71.getCurrentEquippedItem() != null && var71.getCurrentEquippedItem().getItem() instanceof ItemGun) {
                     ItemGun var77 = (ItemGun)var71.getCurrentEquippedItem().getItem();
                     this.impactX = var77.impactX;
                     this.impactY = var77.impactY;
                     this.impactZ = var77.impactZ;
                  }
               }

               this.isFirstPositionSetting = true;
            }

            if(this.type.shootForSettingPos && this.isFirstPositionSetting && this.isPositionUpper) {
               var67 = super.motionX;
               var72 = super.motionY;
               var68 = super.motionZ;
               var76 = Math.sqrt(var67 * var67 + var72 * var72 + var68 * var68);
               super.motionX = 0.0D;
               super.motionY = var76;
               super.motionZ = 0.0D;
               if(super.posY - (double)this.type.shootForSettingPosHeight > this.owner.posY) {
                  this.isPositionUpper = false;
               }
            }

            if(this.type.shootForSettingPos && this.isFirstPositionSetting && !this.isPositionUpper) {
               var67 = (double)this.impactX - super.posX;
               var72 = (double)this.impactZ - super.posZ;
               var68 = Math.sqrt(var67 * var67 + var72 * var72);
               var76 = super.motionX;
               var81 = super.motionY;
               targetSpeed = super.motionZ;
               motiona = Math.sqrt(var76 * var76 + var81 * var81 + targetSpeed * targetSpeed);
               super.motionX = var67 * motiona / var68;
               super.motionZ = var72 * motiona / var68;
               if(Math.abs((double)this.impactX - super.posX) < 1.0D && Math.abs((double)this.impactZ - super.posZ) < 1.0D) {
                  double var84 = super.motionX;
                  motionYab = super.motionY;
                  double motionZab = super.motionZ;
                  double motionab = Math.sqrt(var76 * var76 + var81 * var81 + targetSpeed * targetSpeed);
                  super.motionX = 0.0D;
                  super.motionY = -motionab;
                  super.motionZ = 0.0D;
               }
            }

            super.renderDistanceWeight = 256.0D;
            if(this.owner != null && this.type.manualGuidance && this.VLSDelay <= 0 && this.lockedOnTo == null) {
               super.renderDistanceWeight = 256.0D;
               var54 = new Vector3f((float)this.owner.getLookVec().xCoord, (float)this.owner.getLookVec().yCoord, (float)this.owner.getLookVec().zCoord);
               var55 = new Vector3f(this.owner.posX, this.owner.posY, this.owner.posZ);
               if(this.type.fixedDirection) {
                  var54 = this.lookVector;
                  var55 = this.initialPos;
               }

               var57 = (float)(super.posX - (double)var55.x);
               float var64 = (float)(super.posY - (double)var55.y);
               float var74 = (float)(super.posZ - (double)var55.z);
               float var73 = (float)Math.sqrt((double)(var57 * var57 + var64 * var64 + var74 * var74));
               var73 += this.type.turnRadius;
               var54.normalise();
               Vector3f var80 = new Vector3f(var55.x + var54.x * var73, var55.y + var54.y * var73, var55.z + var54.z * var73);
               Vector3f var78 = Vector3f.sub(var80, new Vector3f(super.posX, super.posY, super.posZ), (Vector3f)null);
               float var82 = this.type.trackPhaseSpeed;
               float turnSpeed = this.type.trackPhaseTurn;
               var78.normalise();
               turnSpeed = 0.1F;
               var83 = new Vector3f(var78.x * var82, var78.y * var82, var78.z * var82);
               super.motionX += ((double)var83.x - super.motionX) * (double)turnSpeed;
               super.motionY += ((double)var83.y - super.motionY) * (double)turnSpeed;
               super.motionZ += ((double)var83.z - super.motionZ) * (double)turnSpeed;
            }

            if(this.type.torpedo && this.isInWater()) {
               new Vector3f(super.motionX, super.motionY, super.motionZ);
               var56 = var37.length();
               var37.normalise();
               super.motionY *= 0.30000001192092896D;
               super.motionX = (double)(var37.x * 1.0F);
               super.motionZ = (double)(var37.z * 1.0F);
            }

            this.setPosition(super.posX + super.motionX, super.posY + super.motionY, super.posZ + super.motionZ);
            float var79 = MathHelper.sqrt_double(super.motionX * super.motionX + super.motionZ * super.motionZ);
            super.rotationYaw = (float)(Math.atan2(super.motionX, super.motionZ) * 180.0D / 3.1415927410125732D);

            for(super.rotationPitch = (float)(Math.atan2(super.motionY, (double)var79) * 180.0D / 3.1415927410125732D); super.rotationPitch - super.prevRotationPitch < -180.0F; super.prevRotationPitch -= 360.0F) {
               ;
            }

            while(super.rotationPitch - super.prevRotationPitch >= 180.0F) {
               super.prevRotationPitch += 360.0F;
            }

            while(super.rotationYaw - super.prevRotationYaw < -180.0F) {
               super.prevRotationYaw -= 360.0F;
            }

            while(super.rotationYaw - super.prevRotationYaw >= 180.0F) {
               super.prevRotationYaw += 360.0F;
            }

            super.rotationPitch = super.prevRotationPitch + (super.rotationPitch - super.prevRotationPitch) * 0.2F;
            super.rotationYaw = super.prevRotationYaw + (super.rotationYaw - super.prevRotationYaw) * 0.2F;
            if(this.type.trailParticles && super.worldObj.isRemote && this.ticksInAir > 1) {
               this.spawnParticles();
            }

            if(super.worldObj.isRemote) {
               this.extinguish();
            }

         }
      }
   }

   @SideOnly(Side.CLIENT)
   private void spawnParticles() {
      double dX = (super.posX - super.prevPosX) / 10.0D;
      double dY = (super.posY - super.prevPosY) / 10.0D;
      double dZ = (super.posZ - super.prevPosZ) / 10.0D;
      float spread = 0.1F;
      int i;
      if(this.VLSDelay > 0 && this.type.boostPhaseParticle != null) {
         for(i = 0; i < 10; ++i) {
            FlansMod.proxy.spawnParticle(this.type.boostPhaseParticle, super.prevPosX + dX * (double)i + super.rand.nextGaussian() * (double)spread, super.prevPosY + dY * (double)i + super.rand.nextGaussian() * (double)spread, super.prevPosZ + dZ * (double)i + super.rand.nextGaussian() * (double)spread, 0.0D, 0.0D, 0.0D);
         }
      } else if(!this.type.VLS || this.VLSDelay <= 0) {
         for(i = 0; i < 10; ++i) {
            FlansMod.proxy.spawnParticle(this.type.trailParticleType, super.prevPosX + dX * (double)i + super.rand.nextGaussian() * (double)spread, super.prevPosY + dY * (double)i + super.rand.nextGaussian() * (double)spread, super.prevPosZ + dZ * (double)i + super.rand.nextGaussian() * (double)spread, 0.0D, 0.0D, 0.0D);
         }
      }

   }

   @SideOnly(Side.CLIENT)
   private void spawnHitParticles(double x, double y, double z) {
      FlansMod.proxy.spawnParticle("explode", x, y, z, 0.0D, 0.0D, 0.0D);
   }

   public DamageSource getBulletDamage(boolean headshot) {
      return this.owner instanceof EntityPlayer?(new EntityDamageSourceGun(this.type.shortName, this, (EntityPlayer)this.owner, this.firedFrom, headshot)).setProjectile():(new EntityDamageSourceIndirect(this.type.shortName, this, this.owner)).setProjectile();
   }

   private boolean isPartOfOwner(Entity entity) {
      if(this.owner == null) {
         return false;
      } else if(entity != this.owner && entity != this.owner.riddenByEntity && entity != this.owner.ridingEntity) {
         if(this.owner instanceof EntityPlayer) {
            if(PlayerHandler.getPlayerData((EntityPlayer)this.owner, super.worldObj.isRemote?Side.CLIENT:Side.SERVER) == null) {
               return false;
            }

            EntityMG mg = PlayerHandler.getPlayerData((EntityPlayer)this.owner, super.worldObj.isRemote?Side.CLIENT:Side.SERVER).mountingGun;
            if(mg != null && mg == entity) {
               return true;
            }
         }

         return this.owner.ridingEntity instanceof EntitySeat && (((EntitySeat)this.owner.ridingEntity).driveable == null || ((EntitySeat)this.owner.ridingEntity).driveable.isPartOfThis(entity));
      } else {
         return true;
      }
   }

   public void setDead() {
      try {
         if(super.isDead) {
            return;
         }

         super.setDead();
         if(super.worldObj.isRemote) {
            return;
         }

         if(this.type.explosionRadius > 0.0F) {
            if(this.owner instanceof EntityPlayer) {
               new FlansModExplosion(super.worldObj, this, (EntityPlayer)this.owner, this.type, super.posX, super.posY, super.posZ, this.type.explosionRadius, TeamsManager.explosions && this.type.explosionBreaksBlocks, this.type.explosionDamageVsLiving, this.type.explosionDamageVsPlayer, this.type.explosionDamageVsPlane, this.type.explosionDamageVsVehicle, this.type.smokeParticleCount, this.type.debrisParticleCount);
            } else {
               super.worldObj.createExplosion(this, super.posX, super.posY, super.posZ, this.type.explosionRadius, TeamsManager.explosions);
            }
         }

         if(this.type.fireRadius > 0.0F) {
            for(float e = -this.type.fireRadius; e < this.type.fireRadius; ++e) {
               for(float damage = -this.type.fireRadius; damage < this.type.fireRadius; ++damage) {
                  for(int dropStack = -1; dropStack < 1; ++dropStack) {
                     if(super.worldObj.getBlock((int)(super.posX + (double)e), (int)(super.posY + (double)dropStack), (int)(super.posZ + (double)damage)).getMaterial() == Material.air) {
                        super.worldObj.setBlock((int)(super.posX + (double)e), (int)(super.posY + (double)dropStack), (int)(super.posZ + (double)damage), Blocks.fire);
                     }
                  }
               }
            }
         }

         if(this.type.flak > 0) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketFlak(super.posX, super.posY, super.posZ, this.type.flak, this.type.flakParticles), super.posX, super.posY, super.posZ, 200.0F, super.dimension);
         }

         if(this.type.dropItemOnHit != null) {
            String var5 = this.type.dropItemOnHit;
            int var6 = 0;
            if(var5.contains(".")) {
               var6 = Integer.parseInt(var5.split("\\.")[1]);
               var5 = var5.split("\\.")[0];
            }

            ItemStack var7 = InfoType.getRecipeElement(var5, var6);
            this.entityDropItem(var7, 1.0F);
         }
      } catch (Exception var4) {
         if(FlansMod.printStackTrace) {
            var4.printStackTrace();
         }
      }

   }

   public float moveToTarget(float current, float target, float speed) {
      float pitchToMove;
      for(pitchToMove = (float)(Math.sqrt((double)(target * target)) - Math.sqrt((double)(current * current))); pitchToMove > 180.0F; pitchToMove -= 360.0F) {
         ;
      }

      while(pitchToMove <= -180.0F) {
         pitchToMove += 360.0F;
      }

      float signDeltaY = 0.0F;
      if(pitchToMove > speed) {
         signDeltaY = 1.0F;
      } else {
         if(pitchToMove >= -speed) {
            signDeltaY = 0.0F;
            return target;
         }

         signDeltaY = -1.0F;
      }

      if(current > target) {
         current -= speed;
      } else if(current < target) {
         current += speed;
      }

      return current;
   }

   public void detonate() {
      if(super.ticksExisted >= this.type.primeDelay) {
         if(!this.detonated) {
            this.detonated = true;
            PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.type.detonateSound, true);
            if(!super.worldObj.isRemote && this.type.explosionRadius > 0.1F) {
               if(this.owner instanceof EntityPlayer) {
                  new FlansModExplosion(super.worldObj, this, (EntityPlayer)this.owner, this.type, super.posX, super.posY, super.posZ, this.type.explosionRadius, TeamsManager.explosions && this.type.explosionBreaksBlocks, this.type.explosionDamageVsLiving, this.type.explosionDamageVsPlayer, this.type.explosionDamageVsPlane, this.type.explosionDamageVsVehicle, this.type.smokeParticleCount, this.type.debrisParticleCount);
                  super.isDead = true;
               } else {
                  super.worldObj.createExplosion(this, super.posX, super.posY, super.posZ, this.type.explosionRadius, TeamsManager.explosions && this.type.explosionBreaksBlocks);
                  super.isDead = true;
               }
            }

            if(!super.worldObj.isRemote && this.type.fireRadius > 0.1F) {
               for(float itemName = -this.type.fireRadius; itemName < this.type.fireRadius; ++itemName) {
                  for(float damage = -this.type.fireRadius; damage < this.type.fireRadius; ++damage) {
                     for(float dropStack = -this.type.fireRadius; dropStack < this.type.fireRadius; ++dropStack) {
                        int x = MathHelper.floor_double((double)itemName + super.posX);
                        int y = MathHelper.floor_double((double)damage + super.posY);
                        int z = MathHelper.floor_double((double)dropStack + super.posZ);
                        if(itemName * itemName + damage * damage + dropStack * dropStack <= this.type.fireRadius * this.type.fireRadius && super.worldObj.getBlock(x, y, z) == Blocks.air && super.rand.nextBoolean()) {
                           super.worldObj.setBlock(x, y, z, Blocks.fire, 0, 3);
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

               ItemStack var10 = InfoType.getRecipeElement(var8, var9);
               this.entityDropItem(var10, 1.0F);
            }

         }
      }
   }

   public void writeEntityToNBT(NBTTagCompound tag) {
      if(this.type == null) {
         FlansMod.log("EntityBullet.writeEntityToNBT() Error: BulletType is null (" + this + ")");
         this.setDead();
      } else {
         tag.setString("type", this.type.shortName);
         if(this.owner == null) {
            tag.setString("owner", "null");
         } else {
            tag.setString("owner", this.owner.getCommandSenderName());
         }

      }
   }

   public void readEntityFromNBT(NBTTagCompound tag) {
      String typeString = tag.getString("type");
      String ownerName = tag.getString("owner");
      if(typeString != null) {
         this.type = BulletType.getBullet(typeString);
      }

      if(this.type == null) {
         super.isDead = true;
      } else {
         if(this.type.despawnTime <= 0) {
            super.isDead = true;
         }

         if(ownerName != null && !ownerName.equals("null")) {
            this.owner = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152612_a(ownerName);
         }

      }
   }

   public float getShadowSize() {
      return this.type.hitBoxSize;
   }

   public void setSubmunitionDelay() {
      this.submunitionDelay = this.type.subMunitionTimer;
      this.hasSetSubDelay = true;
   }

   public void DeploySubmunitions() {
      ItemShootable itemShootable = (ItemShootable)GameRegistry.findItem("flansmod", this.type.submunition);
      ShootableType shootType = itemShootable.type;
      World world = super.worldObj;
      EntityLivingBase entityplayer = (EntityLivingBase)this.owner;

      for(int sm = 0; sm < this.type.numSubmunitions; ++sm) {
         world.spawnEntityInWorld(itemShootable.getEntity(world, new Vector3f(super.posX, super.posY, super.posZ), new Vector3f(super.motionX, super.motionY, super.motionZ), entityplayer, this.type.submunitionSpread, this.damage, this.speedA, 0, this.firedFrom));
      }

      if(this.type.destroyOnDeploySubmunition) {
         this.detonate();
      }

   }

   public int getBrightnessForRender(float par1) {
      if(this.type.hasLight) {
         return 15728880;
      } else {
         int i = MathHelper.floor_double(super.posX);
         int j = MathHelper.floor_double(super.posZ);
         if(super.worldObj.blockExists(i, 0, j)) {
            double d0 = (super.boundingBox.maxY - super.boundingBox.minY) * 0.66D;
            int k = MathHelper.floor_double(super.posY - (double)super.yOffset + d0);
            return super.worldObj.getLightBrightnessForSkyBlocks(i, k, j, 0);
         } else {
            return 0;
         }
      }
   }

   public void writeSpawnData(ByteBuf data) {
      data.writeDouble(super.motionX);
      data.writeDouble(super.motionY);
      data.writeDouble(super.motionZ);
      data.writeInt(this.impactX);
      data.writeInt(this.impactY);
      data.writeInt(this.impactZ);
      data.writeInt(this.lockedOnTo == null?-1:this.lockedOnTo.getEntityId());
      ByteBufUtils.writeUTF8String(data, this.type.shortName);
      if(this.owner == null) {
         ByteBufUtils.writeUTF8String(data, "null");
      } else {
         ByteBufUtils.writeUTF8String(data, this.owner.getCommandSenderName());
      }

   }

   public void readSpawnData(ByteBuf data) {
      try {
         super.motionX = data.readDouble();
         super.motionY = data.readDouble();
         super.motionZ = data.readDouble();
         this.impactX = data.readInt();
         this.impactY = data.readInt();
         this.impactZ = data.readInt();
         int e = data.readInt();
         if(e != -1) {
            this.lockedOnTo = super.worldObj.getEntityByID(e);
         }

         this.type = BulletType.getBullet(ByteBufUtils.readUTF8String(data));
         this.penetratingPower = this.type.penetratingPower;
         String name = ByteBufUtils.readUTF8String(data);
         Iterator var4 = super.worldObj.loadedEntityList.iterator();

         while(var4.hasNext()) {
            Object obj = var4.next();
            if(((Entity)obj).getCommandSenderName().equals(name)) {
               this.owner = (EntityPlayer)obj;
            }
         }
      } catch (Exception var6) {
         FlansMod.log("Failed to read bullet owner from server.");
         super.setDead();
         var6.printStackTrace();
      }

   }

   public boolean isBurning() {
      return false;
   }

}
