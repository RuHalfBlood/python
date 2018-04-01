package com.flansmod.common.guns;

import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.driveables.EntityWheel;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.EntityDamageSourceGun;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketExplosion;
import com.flansmod.common.network.PacketParticle;
import com.flansmod.common.types.InfoType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class FlansModExplosion extends Explosion {

   private static final int boomRadius = 16;
   private static final Random explosionRNG = new Random();
   private HashMap playerLocations = new HashMap();
   private World worldObj;
   public InfoType type;
   public EntityPlayer player;
   private float radius;
   private final float damegeVsLiving;
   private final float damegeVsPlayer;
   private final float damegeVsPlane;
   private final float damegeVsVehicle;
   public boolean breakBlocks;


   public FlansModExplosion(World w, Entity e, EntityPlayer p, InfoType t, double x, double y, double z, float r, boolean breakBlocks, float damegeLiving, float damagePlayer, float damegePlane, float damegeVehicle, int smokeCount, int debrisCount) {
      super(w, e, x, y, z, r);
      this.radius = r;
      this.worldObj = w;
      this.type = t;
      this.player = p;
      super.isFlaming = false;
      super.isSmoking = true;
      this.breakBlocks = breakBlocks;
      this.damegeVsPlayer = damagePlayer;
      this.damegeVsLiving = damegeLiving;
      this.damegeVsPlane = damegePlane;
      this.damegeVsVehicle = damegeVehicle;
      this.doExplosionA();
      this.doExplosionB(true);
      this.spawnParticle(smokeCount, debrisCount);
      if(!this.worldObj.isRemote) {
         Iterator var19 = this.worldObj.playerEntities.iterator();

         while(var19.hasNext()) {
            Object playerEntity = var19.next();
            if(playerEntity instanceof EntityPlayerMP) {
               EntityPlayerMP entityplayer = (EntityPlayerMP)playerEntity;
               if(entityplayer.getDistanceSq(x, y, z) < 4096.0D) {
                  FlansMod.getPacketHandler().sendTo((PacketBase)(new PacketExplosion(x, y, z, r)), entityplayer);
               }
            }
         }
      }

   }

   public void doExplosionA() {
      float f = super.explosionSize;
      HashSet hashset = new HashSet();

      double d0;
      double d1;
      double d2;
      int i;
      int j;
      int k;
      for(i = 0; i < 16; ++i) {
         for(j = 0; j < 16; ++j) {
            for(k = 0; k < 16; ++k) {
               if(i == 0 || i == 15 || j == 0 || j == 15 || k == 0 || k == 15) {
                  double l1 = (double)((float)i / 15.0F * 2.0F - 1.0F);
                  double j2 = (double)((float)j / 15.0F * 2.0F - 1.0F);
                  double vec3 = (double)((float)k / 15.0F * 2.0F - 1.0F);
                  double aList = Math.sqrt(l1 * l1 + j2 * j2 + vec3 * vec3);
                  l1 /= aList;
                  j2 /= aList;
                  vec3 /= aList;
                  float d7 = super.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
                  d0 = super.explosionX;
                  d1 = super.explosionY;
                  d2 = super.explosionZ;

                  for(float f2 = 0.3F; d7 > 0.0F; d7 -= f2 * 0.75F) {
                     int d8 = MathHelper.floor_double(d0);
                     int i1 = MathHelper.floor_double(d1);
                     int d9 = MathHelper.floor_double(d2);
                     Block block = this.worldObj.getBlock(d8, i1, d9);
                     float d10 = super.exploder != null?super.exploder.func_145772_a(this, this.worldObj, d8, i1, d9, block):block.getExplosionResistance(super.exploder, this.worldObj, d8, i1, d9, super.explosionX, super.explosionY, super.explosionZ);
                     d7 -= (d10 + 0.3F) * f2;
                     if(d7 > 0.0F && (super.exploder == null || super.exploder.func_145774_a(this, this.worldObj, d8, i1, d9, block, d7))) {
                        hashset.add(new ChunkPosition(d8, i1, d9));
                     }

                     d0 += l1 * (double)f2;
                     d1 += j2 * (double)f2;
                     d2 += vec3 * (double)f2;
                  }
               }
            }
         }
      }

      super.affectedBlockPositions.addAll(hashset);
      super.explosionSize *= 2.0F;
      i = MathHelper.floor_double(super.explosionX - (double)super.explosionSize - 1.0D);
      j = MathHelper.floor_double(super.explosionX + (double)super.explosionSize + 1.0D);
      k = MathHelper.floor_double(super.explosionY - (double)super.explosionSize - 1.0D);
      int var33 = MathHelper.floor_double(super.explosionY + (double)super.explosionSize + 1.0D);
      int i2 = MathHelper.floor_double(super.explosionZ - (double)super.explosionSize - 1.0D);
      int var34 = MathHelper.floor_double(super.explosionZ + (double)super.explosionSize + 1.0D);
      List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(super.exploder, AxisAlignedBB.getBoundingBox((double)i, (double)k, (double)i2, (double)j, (double)var33, (double)var34));
      Vec3 var35 = Vec3.createVectorHelper(super.explosionX, super.explosionY, super.explosionZ);
      Iterator var17 = list.iterator();

      while(var17.hasNext()) {
         Object var36 = var17.next();
         Entity entity = (Entity)var36;
         double var37 = entity.getDistance(super.explosionX, super.explosionY, super.explosionZ) / (double)super.explosionSize;
         if(var37 <= 1.0D) {
            d0 = entity.posX - super.explosionX;
            d1 = entity.posY + (double)entity.getEyeHeight() - super.explosionY;
            d2 = entity.posZ - super.explosionZ;
            double var38 = (double)MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
            if(var38 != 0.0D) {
               d0 /= var38;
               d1 /= var38;
               d2 /= var38;
               double var39 = (double)this.getBlockDensity(var35, entity.boundingBox);
               double var40 = (1.0D - var37) * var39;
               EntityDriveable entityDriveable = null;
               float damage = (float)((var40 * var40 + var40) / 2.0D * 8.0D * (double)super.explosionSize + 1.0D);
               if(entity instanceof EntityPlayer) {
                  damage *= this.damegeVsPlayer;
               } else if(entity instanceof EntityLivingBase) {
                  damage *= this.damegeVsLiving;
               } else if(entity instanceof EntityPlane) {
                  damage *= this.damegeVsPlane;
               } else if(entity instanceof EntityVehicle) {
                  damage *= this.damegeVsVehicle;
               } else if(entity instanceof EntityWheel) {
                  entityDriveable = ((EntityWheel)entity).vehicle;
               } else if(entity instanceof EntitySeat) {
                  entityDriveable = ((EntitySeat)entity).driveable;
               }

               if(entityDriveable instanceof EntityPlane) {
                  damage *= this.damegeVsPlane;
               }

               if(entityDriveable instanceof EntityVehicle) {
                  damage *= this.damegeVsVehicle;
               }

               if(damage > 0.5F) {
                  boolean b = entity.attackEntityFrom((DamageSource)(this.player != null && this.type != null?new EntityDamageSourceGun(this.type.shortName, entity, this.player, this.type, false):DamageSource.setExplosionSource(this)), damage);
                  if(b) {
                     EntityBullet.hitCrossHair = true;
                  }

                  double d11 = EnchantmentProtection.func_92092_a(entity, var40);
                  entity.motionX += d0 * d11;
                  entity.motionY += d1 * d11;
                  entity.motionZ += d2 * d11;
               }

               if(entity instanceof EntityPlayer) {
                  this.playerLocations.put((EntityPlayer)entity, Vec3.createVectorHelper(d0 * var40, d1 * var40, d2 * var40));
               }
            }
         }
      }

      super.explosionSize = f;
   }

   public void spawnParticle(int numSmoke, int numDebris) {
      float mod = this.radius * 0.1F;

      int debris;
      float smokeRand;
      for(debris = 0; debris < numSmoke; ++debris) {
         smokeRand = (float)Math.random();
         if((double)smokeRand < 0.25D) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketParticle("flansmod.flare", super.explosionX, super.explosionY, super.explosionZ, (double)((float)Math.random() * mod), (double)((float)Math.random() * mod), (double)((float)Math.random() * mod)), super.explosionX, super.explosionY, super.explosionZ, 150.0F, this.worldObj.provider.dimensionId);
         } else if((double)smokeRand > 0.25D && (double)smokeRand < 0.5D) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketParticle("flansmod.flare", super.explosionX, super.explosionY, super.explosionZ, (double)((float)Math.random() * mod), (double)((float)Math.random() * mod), (double)(-((float)Math.random()) * mod)), super.explosionX, super.explosionY, super.explosionZ, 150.0F, this.worldObj.provider.dimensionId);
         } else if((double)smokeRand > 0.5D && (double)smokeRand < 0.75D) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketParticle("flansmod.flare", super.explosionX, super.explosionY, super.explosionZ, (double)(-((float)Math.random()) * mod), (double)((float)Math.random() * mod), (double)(-((float)Math.random()) * mod)), super.explosionX, super.explosionY, super.explosionZ, 150.0F, this.worldObj.provider.dimensionId);
         } else if((double)smokeRand > 0.75D) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketParticle("flansmod.flare", super.explosionX, super.explosionY, super.explosionZ, (double)(-((float)Math.random()) * mod), (double)((float)Math.random() * mod), (double)((float)Math.random() * mod)), super.explosionX, super.explosionY, super.explosionZ, 150.0F, this.worldObj.provider.dimensionId);
         }
      }

      for(debris = 0; debris < numDebris; ++debris) {
         smokeRand = (float)Math.random();
         if((double)smokeRand < 0.25D) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketParticle("flansmod.debris1", super.explosionX, super.explosionY, super.explosionZ, (double)((float)Math.random() * mod), (double)((float)Math.random() * mod), (double)((float)Math.random() * mod)), super.explosionX, super.explosionY, super.explosionZ, 150.0F, this.worldObj.provider.dimensionId);
         } else if((double)smokeRand > 0.25D && (double)smokeRand < 0.5D) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketParticle("flansmod.debris1", super.explosionX, super.explosionY, super.explosionZ, (double)((float)Math.random() * mod), (double)((float)Math.random() * mod), (double)(-((float)Math.random()) * mod)), super.explosionX, super.explosionY, super.explosionZ, 150.0F, this.worldObj.provider.dimensionId);
         } else if((double)smokeRand > 0.5D && (double)smokeRand < 0.75D) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketParticle("flansmod.debris1", super.explosionX, super.explosionY, super.explosionZ, (double)(-((float)Math.random()) * mod), (double)((float)Math.random() * mod), (double)((float)Math.random() * mod)), super.explosionX, super.explosionY, super.explosionZ, 150.0F, this.worldObj.provider.dimensionId);
         } else if((double)smokeRand > 0.75D) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketParticle("flansmod.debris1", super.explosionX, super.explosionY, super.explosionZ, (double)(-((float)Math.random()) * mod), (double)((float)Math.random() * mod), (double)(-((float)Math.random()) * mod)), super.explosionX, super.explosionY, super.explosionZ, 150.0F, this.worldObj.provider.dimensionId);
         }
      }

   }

   public float getBlockDensity(Vec3 p_72842_1_, AxisAlignedBB p_72842_2_) {
      double d0 = 1.0D / ((p_72842_2_.maxX - p_72842_2_.minX) * 2.0D + 1.0D);
      double d1 = 1.0D / ((p_72842_2_.maxY - p_72842_2_.minY) * 2.0D + 1.0D);
      double d2 = 1.0D / ((p_72842_2_.maxZ - p_72842_2_.minZ) * 2.0D + 1.0D);
      if(d0 >= 0.0D && d1 >= 0.0D && d2 >= 0.0D) {
         int i = 0;
         int j = 0;

         for(float f = 0.0F; f <= 1.0F; f = (float)((double)f + d0)) {
            for(float f1 = 0.0F; f1 <= 1.0F; f1 = (float)((double)f1 + d1)) {
               for(float f2 = 0.0F; f2 <= 1.0F; f2 = (float)((double)f2 + d2)) {
                  double d3 = p_72842_2_.minX + (p_72842_2_.maxX - p_72842_2_.minX) * (double)f;
                  double d4 = p_72842_2_.minY + (p_72842_2_.maxY - p_72842_2_.minY) * (double)f1;
                  double d5 = p_72842_2_.minZ + (p_72842_2_.maxZ - p_72842_2_.minZ) * (double)f2;
                  if(this.worldObj.func_147447_a(Vec3.createVectorHelper(d3, d4, d5), p_72842_1_, false, true, false) == null) {
                     ++i;
                  }

                  ++j;
               }
            }
         }

         return (float)i / (float)j;
      } else {
         return 0.0F;
      }
   }

   public void doExplosionB(boolean par1) {
      this.worldObj.playSoundEffect(super.explosionX, super.explosionY, super.explosionZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
      Iterator iterator;
      ChunkPosition chunkposition;
      int i;
      int j;
      int k;
      Block block;
      if(!this.worldObj.isRemote && this.breakBlocks) {
         iterator = super.affectedBlockPositions.iterator();

         while(iterator.hasNext()) {
            chunkposition = (ChunkPosition)iterator.next();
            i = chunkposition.chunkPosX;
            j = chunkposition.chunkPosY;
            k = chunkposition.chunkPosZ;
            block = this.worldObj.getBlock(i, j, k);
            if(block.getMaterial() != Material.air) {
               if(block.canDropFromExplosion(this)) {
                  block.dropBlockAsItemWithChance(this.worldObj, i, j, k, this.worldObj.getBlockMetadata(i, j, k), 1.0F / super.explosionSize, 0);
               }

               block.onBlockExploded(this.worldObj, i, j, k, this);
            }
         }
      }

      if(!this.worldObj.isRemote && super.isFlaming) {
         iterator = super.affectedBlockPositions.iterator();

         while(iterator.hasNext()) {
            chunkposition = (ChunkPosition)iterator.next();
            i = chunkposition.chunkPosX;
            j = chunkposition.chunkPosY;
            k = chunkposition.chunkPosZ;
            block = this.worldObj.getBlock(i, j, k);
            Block blockBelow = this.worldObj.getBlock(i, j - 1, k);
            if(block == null && blockBelow.isOpaqueCube() && explosionRNG.nextInt(3) == 0) {
               this.worldObj.setBlock(i, j, k, Blocks.fire);
            }
         }
      }

   }

   public Map func_77277_b() {
      return this.playerLocations;
   }

   public EntityLivingBase getExplosivePlacedBy() {
      return super.exploder == null?null:(super.exploder instanceof EntityTNTPrimed?((EntityTNTPrimed)super.exploder).getTntPlacedBy():(super.exploder instanceof EntityLivingBase?(EntityLivingBase)super.exploder:null));
   }

   public static void clientExplosion(World worldObj, float explosionSize, double explosionX, double explosionY, double explosionZ) {
      ArrayList affectedBlockPositions = new ArrayList();
      Object exploder = null;
      Explosion explosion = new Explosion(worldObj, (Entity)exploder, explosionX, explosionY, explosionZ, explosionSize);
      if(explosionSize < 2.0F) {
         explosionX += (double)(explosionRNG.nextFloat() - 0.5F);
         explosionZ += (double)(explosionRNG.nextFloat() - 0.5F);
      }

      boolean isSmoking = true;
      float iterator = explosionSize;
      HashSet cnt = new HashSet();

      int chunkposition;
      int i;
      int j;
      double k;
      double d0;
      double d1;
      double d2;
      double d3;
      double d4;
      double d5;
      for(chunkposition = 0; chunkposition < 16; ++chunkposition) {
         for(i = 0; i < 16; ++i) {
            for(j = 0; j < 16; ++j) {
               if(chunkposition == 0 || chunkposition == 15 || i == 0 || i == 15 || j == 0 || j == 15) {
                  d2 = (double)((float)chunkposition / 15.0F * 2.0F - 1.0F);
                  d3 = (double)((float)i / 15.0F * 2.0F - 1.0F);
                  d4 = (double)((float)j / 15.0F * 2.0F - 1.0F);
                  d5 = Math.sqrt(d2 * d2 + d3 * d3 + d4 * d4);
                  d2 /= d5;
                  d3 /= d5;
                  d4 /= d5;
                  float d6 = explosionSize * (0.7F + worldObj.rand.nextFloat() * 0.6F);
                  k = explosionX;
                  d0 = explosionY;
                  d1 = explosionZ;

                  for(float d9 = 0.3F; d6 > 0.0F; d6 -= d9 * 0.75F) {
                     int d7 = MathHelper.floor_double(k);
                     int d10 = MathHelper.floor_double(d0);
                     int m = MathHelper.floor_double(d1);
                     Block pname = worldObj.getBlock(d7, d10, m);
                     float f3 = 0.0F;
                     d6 -= (f3 + 0.3F) * d9;
                     if(d6 > 0.0F && (exploder == null || ((Entity)exploder).func_145774_a(explosion, worldObj, d7, d10, m, pname, d6))) {
                        cnt.add(new ChunkPosition(d7, d10, m));
                     }

                     k += d2 * (double)d9;
                     d0 += d3 * (double)d9;
                     d1 += d4 * (double)d9;
                  }
               }
            }
         }
      }

      affectedBlockPositions.addAll(cnt);
      explosionSize *= 2.0F;
      chunkposition = MathHelper.floor_double(explosionX - (double)explosionSize - 1.0D);
      i = MathHelper.floor_double(explosionX + (double)explosionSize + 1.0D);
      j = MathHelper.floor_double(explosionY - (double)explosionSize - 1.0D);
      int var44 = MathHelper.floor_double(explosionY + (double)explosionSize + 1.0D);
      int l = MathHelper.floor_double(explosionZ - (double)explosionSize - 1.0D);
      int var45 = MathHelper.floor_double(explosionZ + (double)explosionSize + 1.0D);
      List list = worldObj.getEntitiesWithinAABBExcludingEntity((Entity)exploder, AxisAlignedBB.getBoundingBox((double)chunkposition, (double)j, (double)l, (double)i, (double)var44, (double)var45));
      Vec3 var46 = Vec3.createVectorHelper(explosionX, explosionY, explosionZ);

      for(int i1 = 0; i1 < list.size(); ++i1) {
         Entity var47 = (Entity)list.get(i1);
         double d41 = var47.getDistance(explosionX, explosionY, explosionZ) / (double)explosionSize;
         if(d41 <= 1.0D) {
            k = var47.posX - explosionX;
            d0 = var47.posY + (double)var47.getEyeHeight() - explosionY;
            d1 = var47.posZ - explosionZ;
            double var49 = (double)MathHelper.sqrt_double(k * k + d0 * d0 + d1 * d1);
            if(var49 != 0.0D) {
               k /= var49;
               d0 /= var49;
               d1 /= var49;
               double var51 = (double)worldObj.getBlockDensity(var46, var47.boundingBox);
               double var53 = (1.0D - d41) * var51;
               double d8 = EnchantmentProtection.func_92092_a(var47, var53);
               var47.motionX += k * d8;
               var47.motionY += d0 * d8;
               var47.motionZ += d1 * d8;
            }
         }
      }

      explosionSize = iterator;
      if(iterator >= 2.0F && isSmoking) {
         worldObj.spawnParticle("hugeexplosion", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D);
      } else {
         worldObj.spawnParticle("largeexplode", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D);
      }

      Iterator var40 = affectedBlockPositions.iterator();
      int var41 = 0;

      while(var40.hasNext()) {
         ++var41;
         ChunkPosition var42 = (ChunkPosition)var40.next();
         i = var42.chunkPosX;
         j = var42.chunkPosY;
         int var43 = var42.chunkPosZ;
         worldObj.getBlock(i, j, var43);
         d0 = (double)((float)i + worldObj.rand.nextFloat());
         d1 = (double)((float)j + worldObj.rand.nextFloat());
         d2 = (double)((float)var43 + worldObj.rand.nextFloat());
         d3 = d0 - explosionX;
         d4 = d1 - explosionY;
         d5 = d2 - explosionZ;
         double var48 = (double)MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
         d3 /= var48;
         d4 /= var48;
         d5 /= var48;
         double var50 = 0.5D / (var48 / (double)explosionSize + 0.1D);
         var50 *= (double)(worldObj.rand.nextFloat() * worldObj.rand.nextFloat() + 0.3F);
         d3 *= var50;
         d4 *= var50;
         d5 *= var50;
         if(explosionSize <= 1.0F && var41 % 4 == 0 || explosionSize > 1.0F) {
            FlansMod.proxy.spawnParticle("explode", (d0 + explosionX * 1.0D) / 2.0D, (d1 + explosionY * 1.0D) / 2.0D, (d2 + explosionZ * 1.0D) / 2.0D, d3, d4, d5);
         }

         Block block = Blocks.air;
         if(explosionSize <= 2.0F) {
            if(var41 % 8 == 0) {
               block = getNearBlock(worldObj, i, j, var43);
            }
         } else {
            block = getNearBlock(worldObj, i, j, var43);
         }

         if(block != Blocks.air) {
            float var52;
            if(explosionSize <= 1.0F) {
               var52 = explosionSize * 2.0F;
            } else {
               var52 = explosionSize * 0.5F;
            }

            String var54 = "blockdust_" + Block.getIdFromBlock(block) + "_" + worldObj.getBlockMetadata(i, j, var43);
            FlansMod.proxy.spawnParticle(var54, (d0 + explosionX * 1.0D) / 2.0D, (d1 + explosionY * 1.0D) / 2.0D, (d2 + explosionZ * 1.0D) / 2.0D, d3 * (double)var52, d4 * (double)var52, d5 * (double)var52);
         }
      }

   }

   public static Block getNearBlock(World w, int x, int y, int z) {
      int[][] offset = new int[][]{{0, -1, 0}, {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}};

      for(int i = 0; i < offset.length; ++i) {
         if(y > 1 || offset[i][1] == 0) {
            Block block = w.getBlock(x + offset[i][0], y + offset[i][1], z + offset[i][2]);
            if(block != Blocks.air) {
               return block;
            }
         }
      }

      return Blocks.air;
   }

}
