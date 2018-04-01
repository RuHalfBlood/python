package com.flansmod.common.driveables;

import cofh.api.energy.IEnergyContainerItem;
import com.flansmod.api.IControllable;
import com.flansmod.api.IExplodeable;
import com.flansmod.client.EntityCamera;
import com.flansmod.client.FlansModClient;
import com.flansmod.client.debug.EntityDebugVector;
import com.flansmod.common.FlansMod;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.driveables.DriveableData;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.DriveablePosition;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.driveables.EntityWheel;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.EnumWeaponType;
import com.flansmod.common.driveables.PilotGun;
import com.flansmod.common.driveables.Seat;
import com.flansmod.common.driveables.ShootPoint;
import com.flansmod.common.driveables.collisions.CollisionPlane;
import com.flansmod.common.driveables.collisions.CollisionShapeBox;
import com.flansmod.common.driveables.collisions.CollisionTest;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.EntityDamageSourceGun;
import com.flansmod.common.guns.EntityShootable;
import com.flansmod.common.guns.EnumFireMode;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.InventoryHelper;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.guns.ItemShootable;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.guns.raytracing.DriveableHit;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketDriveableDamage;
import com.flansmod.common.network.PacketDriveableKeyHeld;
import com.flansmod.common.network.PacketParticle;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.parts.ItemPart;
import com.flansmod.common.parts.PartType;
import com.flansmod.common.teams.Gametype;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public abstract class EntityDriveable extends Entity implements IControllable, IExplodeable, IEntityAdditionalSpawnData {

   public boolean syncFromServer;
   public int serverPositionTransitionTicker;
   public double field_70118_ct;
   public double field_70117_cu;
   public double field_70116_cv;
   public double serverYaw;
   public double serverPitch;
   public double serverRoll;
   public DriveableData driveableData;
   public String driveableType;
   public float throttle;
   public EntityWheel[] wheels;
   public boolean fuelling;
   public float prevRotationRoll;
   public Vector3f angularVelocity;
   public boolean leftMouseHeld;
   public boolean rightMouseHeld;
   public int shootDelayPrimary;
   public int shootDelaySecondary;
   public float minigunSpeedPrimary;
   public float minigunSpeedSecondary;
   public int currentGunPrimary;
   public int currentGunSecondary;
   public float harvesterAngle;
   public RotatedAxes prevAxes;
   public RotatedAxes axes;
   public EntitySeat[] seats;
   private int foundFuel;
   public boolean couldNotFindFuel;
   public boolean isAmmoPlaced;
   public int lockOnSoundDelay;
   private int[] emitterTimers;
   public int animCountLeft;
   public int animFrameLeft;
   public int animCountRight;
   public int animFrameRight;
   public boolean leftTurnHeld;
   public boolean rightTurnHeld;
   public boolean isShowedPosition;
   public int tickCount;
   public int impactX;
   public int impactY;
   public int impactZ;
   public boolean locked;
   public boolean neverLocked;
   public String key;
   private String lastKey;
   public boolean isRecoil;
   public float recoilPos;
   public float lastRecoilPos;
   public int recoilTimer;
   public Vector3f lastPos;
   public boolean hugeBoat;
   public boolean onDeck;
   public double deckHeight;
   public int deckCheck;
   public int prevDeckCheck;
   public boolean isMecha;
   public boolean disabled;
   public float propAngle;
   public float prevPropAngle;
   public float rotorAngle;
   public float prevRotorAngle;
   public int flareDelay;
   public int ticksFlareUsing;
   public boolean varFlare;
   public float drakonDoorAngle;
   public float drakonArmAngle;
   public float drakonRailAngle;
   public float prevDrakonDoorAngle;
   public float prevDrakonArmAngle;
   public float prevDrakonRailAngle;
   public boolean reloadingDrakon;
   public boolean canFireIT1;
   public int stage;
   public int reloadAnimTime;
   public boolean toDeactivate;
   public int timeTillDeactivate;
   public boolean canFire;
   @SideOnly(Side.CLIENT)
   public EntityLivingBase camera;
   protected int invulnerableUnmountCount;
   private ItemStack[][] prevInventoryItems;
   public Entity lastAtkEntity;


   public EntityDriveable(World world) {
      super(world);
      this.syncFromServer = true;
      this.angularVelocity = new Vector3f(0.0F, 0.0F, 0.0F);
      this.leftMouseHeld = false;
      this.rightMouseHeld = false;
      this.foundFuel = -1;
      this.couldNotFindFuel = false;
      this.isAmmoPlaced = false;
      this.animCountLeft = 0;
      this.animFrameLeft = 0;
      this.animCountRight = 0;
      this.animFrameRight = 0;
      this.leftTurnHeld = false;
      this.rightTurnHeld = false;
      this.isShowedPosition = false;
      this.tickCount = 0;
      this.neverLocked = true;
      this.key = "ChangeMe";
      this.isRecoil = false;
      this.recoilPos = 0.0F;
      this.lastRecoilPos = 0.0F;
      this.recoilTimer = 0;
      this.lastPos = new Vector3f(0.0F, 0.0F, 0.0F);
      this.hugeBoat = false;
      this.onDeck = false;
      this.deckHeight = 0.0D;
      this.deckCheck = 0;
      this.prevDeckCheck = 0;
      this.isMecha = false;
      this.disabled = false;
      this.propAngle = 0.0F;
      this.prevPropAngle = 0.0F;
      this.rotorAngle = 0.0F;
      this.prevRotorAngle = 0.0F;
      this.flareDelay = 0;
      this.ticksFlareUsing = 0;
      this.drakonDoorAngle = 0.0F;
      this.drakonArmAngle = 0.0F;
      this.drakonRailAngle = 0.0F;
      this.prevDrakonDoorAngle = 0.0F;
      this.prevDrakonArmAngle = 0.0F;
      this.prevDrakonRailAngle = 0.0F;
      this.reloadingDrakon = false;
      this.canFireIT1 = true;
      this.stage = 1;
      this.reloadAnimTime = 0;
      this.toDeactivate = false;
      this.timeTillDeactivate = 0;
      this.canFire = true;
      this.prevInventoryItems = new ItemStack[][]{null, null};
      this.lastAtkEntity = null;
      this.axes = new RotatedAxes();
      this.prevAxes = new RotatedAxes();
      super.preventEntitySpawning = true;
      this.setSize(1.0F, 1.0F);
      super.yOffset = 0.375F;
      super.ignoreFrustumCheck = true;
      super.renderDistanceWeight = 200.0D;
      this.locked = false;
   }

   public EntityDriveable(World world, DriveableType t, DriveableData d) {
      this(world);
      this.driveableType = t.shortName;
      this.driveableData = d;
   }

   protected void initType(DriveableType type, boolean clientSide) {
      this.seats = new EntitySeat[type.numPassengers + 1];

      int ps;
      for(ps = 0; ps < type.numPassengers + 1; ++ps) {
         if(!clientSide) {
            this.seats[ps] = new EntitySeat(super.worldObj, this, ps);
            super.worldObj.spawnEntityInWorld(this.seats[ps]);
         }
      }

      this.wheels = new EntityWheel[type.wheelPositions.length];

      for(ps = 0; ps < this.wheels.length; ++ps) {
         if(!clientSide) {
            this.wheels[ps] = new EntityWheel(super.worldObj, this, ps);
            super.worldObj.spawnEntityInWorld(this.wheels[ps]);
         }
      }

      super.stepHeight = type.wheelStepHeight;
      super.yOffset = type.yOffset;
      this.emitterTimers = new int[type.emitters.size()];

      for(ps = 0; ps < type.emitters.size(); ++ps) {
         this.emitterTimers[ps] = super.rand.nextInt(((DriveableType.ParticleEmitter)type.emitters.get(ps)).emitRate);
      }

      this.getEntityData().setBoolean("CanMountEntity", type.canMountEntity);

      for(ps = 0; ps < 2; ++ps) {
         EnumWeaponType weaponType = ps == 0?type.primary:type.secondary;
         if(weaponType == EnumWeaponType.GUN) {
            weaponType = EnumWeaponType.NONE;
         }

         int istart = this.getInventoryStart(weaponType);
         if(istart == this.driveableData.getAmmoInventoryStart()) {
            istart += type.numPassengerGunners;
         }

         int isize = this.getInventorySize(weaponType);
         if(istart >= 0 || isize > 0) {
            this.prevInventoryItems[ps] = new ItemStack[isize];

            for(int i = 0; i < isize; ++i) {
               this.prevInventoryItems[ps][i] = this.driveableData.getStackInSlot(istart + i);
            }
         }
      }

   }

   protected void writeEntityToNBT(NBTTagCompound tag) {
      this.driveableData.writeToNBT(tag);
      tag.setString("Type", this.driveableType);
      tag.setFloat("RotationYaw", this.axes.getYaw());
      tag.setFloat("RotationPitch", this.axes.getPitch());
      tag.setFloat("RotationRoll", this.axes.getRoll());
      if(this.key != null) {
         tag.setString("key", this.key);
      }

      tag.setBoolean("locked", this.locked);
   }

   protected void readEntityFromNBT(NBTTagCompound tag) {
      this.driveableType = tag.getString("Type");
      this.driveableData = new DriveableData(tag);
      this.initType(DriveableType.getDriveable(this.driveableType), false);
      super.prevRotationYaw = tag.getFloat("RotationYaw");
      super.prevRotationPitch = tag.getFloat("RotationPitch");
      this.prevRotationRoll = tag.getFloat("RotationRoll");
      this.locked = tag.getBoolean("locked");
      this.key = tag.getString("key");
      this.axes = new RotatedAxes(super.prevRotationYaw, super.prevRotationPitch, this.prevRotationRoll);
   }

   public void writeSpawnData(ByteBuf data) {
      ByteBufUtils.writeUTF8String(data, this.driveableType);
      NBTTagCompound tag = new NBTTagCompound();
      this.driveableData.writeToNBT(tag);
      ByteBufUtils.writeTag(data, tag);
      data.writeFloat(this.axes.getYaw());
      data.writeFloat(this.axes.getPitch());
      data.writeFloat(this.axes.getRoll());
      EnumDriveablePart[] var3 = EnumDriveablePart.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         EnumDriveablePart ep = var3[var5];
         DriveablePart part = (DriveablePart)this.getDriveableData().parts.get(ep);
         data.writeShort((short)part.health);
         data.writeBoolean(part.onFire);
      }

      if(this.key != null) {
         ByteBufUtils.writeUTF8String(data, this.key);
      }

   }

   public void readSpawnData(ByteBuf data) {
      try {
         this.driveableType = ByteBufUtils.readUTF8String(data);
         this.driveableData = new DriveableData(ByteBufUtils.readTag(data));
         this.initType(this.getDriveableType(), true);
         this.axes.setAngles(data.readFloat(), data.readFloat(), data.readFloat());
         super.prevRotationYaw = this.axes.getYaw();
         super.prevRotationPitch = this.axes.getPitch();
         this.prevRotationRoll = this.axes.getRoll();
         EnumDriveablePart[] e = EnumDriveablePart.values();
         int var3 = e.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            EnumDriveablePart ep = e[var4];
            DriveablePart part = (DriveablePart)this.getDriveableData().parts.get(ep);
            part.health = data.readShort();
            part.onFire = data.readBoolean();
         }

         this.key = ByteBufUtils.readUTF8String(data);
      } catch (Exception var7) {
         FlansMod.log("Failed to retreive plane type from server.");
         super.setDead();
         var7.printStackTrace();
      }

      this.camera = new EntityCamera(super.worldObj, this);
      super.worldObj.spawnEntityInWorld(this.camera);
   }

   public abstract void onMouseMoved(int var1, int var2);

   @SideOnly(Side.CLIENT)
   public EntityLivingBase getCamera() {
      return this.camera;
   }

   protected boolean canSit(int seat) {
      return this.getDriveableType().numPassengers >= seat && this.seats[seat].riddenByEntity == null;
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected void entityInit() {}

   public AxisAlignedBB getCollisionBox(Entity entity) {
      if(this.getDriveableType().collisionDamageEnable && this.throttle > this.getDriveableType().collisionDamageThrottle) {
         if(entity instanceof EntityLiving) {
            entity.attackEntityFrom(DamageSource.generic, this.throttle * this.getDriveableType().collisionDamageTimes);
         } else if(entity instanceof EntityPlayer) {
            entity.attackEntityFrom(DamageSource.generic, this.throttle * this.getDriveableType().collisionDamageTimes);
         }
      }

      return super.boundingBox;
   }

   public AxisAlignedBB getBoundingBox() {
      return super.boundingBox;
   }

   public boolean canBePushed() {
      return false;
   }

   public double getMountedYOffset() {
      return -0.3D;
   }

   public boolean attackEntityFrom(DamageSource damagesource, float i) {
      if(!super.worldObj.isRemote && !super.isDead) {
         if(this.isMountedEntity(damagesource.getEntity())) {
            return false;
         } else {
            this.attackPart(EnumDriveablePart.core, damagesource, i);
            if(i > 0.0F) {
               this.checkPartsWhenAttacked();
               FlansMod.getPacketHandler().sendToAllAround(new PacketDriveableDamage(this), super.posX, super.posY, super.posZ, 100.0F, super.dimension);
            }

            return true;
         }
      } else {
         return true;
      }
   }

   public boolean isMountedEntity(Entity entity) {
      if(entity != null) {
         Entity entity2 = super.worldObj.getEntityByID(entity.getEntityId());
         EntitySeat[] var3 = this.seats;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            EntitySeat seat = var3[var5];
            if(seat.riddenByEntity != null && (seat.riddenByEntity == entity || seat.riddenByEntity == entity2)) {
               return true;
            }
         }
      }

      return false;
   }

   public void setDead() {
      super.setDead();
      if(super.worldObj.isRemote) {
         this.camera.setDead();
      }

      EntitySeat[] var1 = this.seats;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         EntitySeat seat = var1[var3];
         if(seat != null) {
            seat.setDead();
         }
      }

   }

   public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {}

   public boolean canBeCollidedWith() {
      return true;
   }

   public void applyEntityCollision(Entity entity) {}

   public void setPositionAndRotation2(double d, double d1, double d2, float f, float f1, int i) {
      if(super.ticksExisted <= 1) {
         if(!(super.riddenByEntity instanceof EntityPlayer) || !FlansMod.proxy.isThePlayer((EntityPlayer)super.riddenByEntity)) {
            if(this.syncFromServer) {
               this.serverPositionTransitionTicker = i + 5;
            } else {
               double var10 = d - super.posX;
               double var12 = d1 - super.posY;
               double var14 = d2 - super.posZ;
               double var16 = var10 * var10 + var12 * var12 + var14 * var14;
               if(var16 <= 1.0D) {
                  return;
               }

               this.serverPositionTransitionTicker = 3;
            }

            this.field_70118_ct = d;
            this.field_70117_cu = d1;
            this.field_70116_cv = d2;
            this.serverYaw = (double)f;
            this.serverPitch = (double)f1;
         }

      }
   }

   public void setIT1(boolean canFire, boolean reloading, int stag, int stageTime) {
      if(super.worldObj.isRemote && super.ticksExisted % 5 == 0) {
         this.canFireIT1 = canFire;
         this.reloadingDrakon = reloading;
         this.stage = stag;
         this.reloadAnimTime = stageTime;
      }

   }

   public void setPositionRotationAndMotion(double x, double y, double z, float yaw, float pitch, float roll, double motX, double motY, double motZ, float velYaw, float velPitch, float velRoll, float throt, float steeringYaw) {
      if(super.worldObj.isRemote) {
         this.field_70118_ct = x;
         this.field_70117_cu = y;
         this.field_70116_cv = z;
         this.serverYaw = (double)yaw;
         this.serverPitch = (double)pitch;
         this.serverRoll = (double)roll;
         this.serverPositionTransitionTicker = 5;
      } else {
         this.setPosition(x, y, z);
         super.prevRotationYaw = yaw;
         super.prevRotationPitch = pitch;
         this.prevRotationRoll = roll;
         this.setRotation(yaw, pitch, roll);
      }

      super.motionX = motX;
      super.motionY = motY;
      super.motionZ = motZ;
      this.angularVelocity = new Vector3f(velYaw, velPitch, velRoll);
      this.throttle = throt;
   }

   public void setVelocity(double d, double d1, double d2) {
      super.motionX = d;
      super.motionY = d1;
      super.motionZ = d2;
   }

   public boolean pressKey(int key, EntityPlayer player) {
      if(!super.worldObj.isRemote && key == 9 && this.getDriveableType().modePrimary == EnumFireMode.SEMIAUTO) {
         this.shoot(false);
         return true;
      } else if(!super.worldObj.isRemote && key == 8 && this.getDriveableType().modeSecondary == EnumFireMode.SEMIAUTO) {
         this.shoot(true);
         return true;
      } else {
         return false;
      }
   }

   public void updateKeyHeldState(int key, boolean held) {
      if(super.worldObj.isRemote) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableKeyHeld(key, held)));
         if(key == 2) {
            this.leftTurnHeld = true;
            this.rightTurnHeld = false;
         } else if(key == 3) {
            this.rightTurnHeld = true;
            this.leftTurnHeld = false;
         } else {
            this.leftTurnHeld = false;
            this.rightTurnHeld = false;
         }
      }

      switch(key) {
      case 8:
         this.rightMouseHeld = held;
         break;
      case 9:
         this.leftMouseHeld = held;
      }

   }

   public void shoot(boolean secondary) {
      DriveableType type = this.getDriveableType();
      if(this.seats[0] != null) {
         if(!type.IT1 || this.canFireIT1 || type.weaponType(secondary) != EnumWeaponType.MISSILE) {
            if(this.canFire) {
               if(this.getShootDelay(secondary) <= 0) {
                  ArrayList shootPoints = type.shootPoints(secondary);
                  EnumWeaponType weaponType = type.weaponType(secondary);
                  if(shootPoints.size() == 0) {
                     return;
                  }

                  int currentGun = this.getCurrentGun(secondary);
                  if(type.alternate(secondary)) {
                     currentGun = (currentGun + 1) % shootPoints.size();
                     this.setCurrentGun(currentGun, secondary);
                     this.shootEach(type, (ShootPoint)shootPoints.get(currentGun), currentGun, secondary, weaponType);
                  } else {
                     for(int i = 0; i < shootPoints.size(); ++i) {
                        this.shootEach(type, (ShootPoint)shootPoints.get(i), i, secondary, weaponType);
                     }
                  }
               }

            }
         }
      }
   }

   private boolean driverIsCreative() {
      return this.seats != null && this.seats[0] != null && this.seats[0].riddenByEntity instanceof EntityPlayer && ((EntityPlayer)this.seats[0].riddenByEntity).capabilities.isCreativeMode;
   }

   public void spawnParticle(ArrayList list, ShootPoint shootPoint, Vector3f v) {
      float bkz;
      for(Iterator var4 = list.iterator(); var4.hasNext(); shootPoint.rootPos.position.z = bkz) {
         DriveableType.ShootParticle s = (DriveableType.ShootParticle)var4.next();
         float bkx = shootPoint.rootPos.position.x;
         float bky = shootPoint.rootPos.position.y;
         bkz = shootPoint.rootPos.position.z;
         Vector3f velocity = new Vector3f(s.x, s.y, s.z);
         Vector3f vv = this.lastPos;
         velocity = this.getDirection(shootPoint, velocity);
         if(shootPoint.rootPos.part == EnumDriveablePart.core) {
            Vector3f v2 = this.axes.findLocalVectorGlobally(shootPoint.rootPos.position);
            Vector3f v3 = this.rotate(this.seats[0].looking.findLocalVectorGlobally(shootPoint.offPos));
            Vector3f.add(v2, v3, v);
         }

         FlansMod.getPacketHandler().sendToAllAround(new PacketParticle(s.name, super.posX + (double)v.x, super.posY + (double)v.y, super.posZ + (double)v.z, (double)velocity.x, (double)velocity.y, (double)velocity.z), super.posX + (double)v.x, super.posY + (double)v.y, super.posZ + (double)v.z, 150.0F, super.dimension);
         shootPoint.rootPos.position.x = bkx;
         shootPoint.rootPos.position.y = bky;
      }

   }

   private void shootEach(DriveableType type, ShootPoint shootPoint, int currentGun, boolean secondary, EnumWeaponType weaponType) {
      Vector3f gunVec = this.getFiringPosition(shootPoint);
      Vector3f lookVector = this.getLookVector(shootPoint);
      if(!secondary && type.fixedPrimaryFire) {
         lookVector = this.axes.findLocalVectorGlobally(type.primaryFireAngle);
         if(shootPoint.rootPos.part == EnumDriveablePart.turret) {
            lookVector = this.getPositionOnTurret(type.primaryFireAngle, false);
         }

         if(shootPoint.rootPos.part == EnumDriveablePart.barrel) {
            lookVector = this.getPositionOnTurret(type.primaryFireAngle, true);
         }
      }

      if(weaponType == EnumWeaponType.SHELL) {
         this.isRecoil = true;
      }

      if(this.isPartIntact(shootPoint.rootPos.part)) {
         if(!this.disabled) {
            float spread;
            if(shootPoint.rootPos instanceof PilotGun) {
               PilotGun slot = (PilotGun)shootPoint.rootPos;
               GunType damageMultiplier = slot.type;
               spread = damageMultiplier.bulletSpeed;
               if(type.rangingGun) {
                  spread = type.bulletSpeed;
               }

               ItemStack shellSpeed = this.driveableData.ammo[this.getDriveableType().numPassengerGunners + currentGun];
               if(damageMultiplier != null && shellSpeed != null && shellSpeed.getItem() instanceof ItemShootable && TeamsManager.bulletsEnabled) {
                  ShootableType bulletStack = ((ItemShootable)shellSpeed.getItem()).type;
                  if(damageMultiplier.isAmmo(bulletStack)) {
                     this.spawnParticle(type.shootParticle(secondary), shootPoint, gunVec);
                     super.worldObj.spawnEntityInWorld(((ItemShootable)shellSpeed.getItem()).getEntity(super.worldObj, Vector3f.add(new Vector3f(super.posX, super.posY, super.posZ), gunVec, (Vector3f)null), lookVector, (EntityLivingBase)this.seats[0].riddenByEntity, damageMultiplier.bulletSpread / 2.0F, damageMultiplier.damage, spread, shellSpeed.getItemDamage(), type));
                     PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, type.shootSound(secondary), false);
                     int bulletItem = shellSpeed.getItemDamage();
                     shellSpeed.setItemDamage(bulletItem + 1);
                     if(bulletItem + 1 == shellSpeed.getMaxDamage()) {
                        shellSpeed.setItemDamage(0);
                        if(!this.driverIsCreative()) {
                           --shellSpeed.stackSize;
                           if(shellSpeed.stackSize <= 0) {
                              this.onWeaponInventoryChanged(secondary);
                              shellSpeed = null;
                           }

                           this.driveableData.setInventorySlotContents(this.getDriveableType().numPassengerGunners + currentGun, shellSpeed);
                        }
                     }

                     this.setShootDelay(type.shootDelay(secondary), secondary);
                  }
               }
            } else {
               EntityShootable bulletEntity;
               int var16;
               int var17;
               ItemStack var19;
               float var22;
               ItemStack var23;
               ItemBullet var24;

               switch (weaponType) {
                   case BOMB: {
                       if (!TeamsManager.bombsEnabled) break;
                       int slot = -1;
                       for (int i = this.driveableData.getBombInventoryStart(); i < this.driveableData.getBombInventoryStart() + type.numBombSlots; ++i) {
                           ItemStack bomb = this.driveableData.getStackInSlot(i);
                           if (bomb == null || !(bomb.getItem() instanceof ItemBullet) || !type.isValidAmmo(((ItemBullet)bomb.getItem()).type, weaponType)) continue;
                           slot = i;
                       }
                       if (slot == -1) break;
                       boolean spread1 = false;
                       boolean damageMultiplier = true;
                       float shellSpeed = 0.0f;
                       ItemStack bulletStack = this.driveableData.getStackInSlot(slot);
                       ItemBullet bulletItem = (ItemBullet)bulletStack.getItem();
                       if (shootPoint.rootPos instanceof PilotGun) {
                           PilotGun pilotGun = (PilotGun)shootPoint.rootPos;
                           GunType gunType = pilotGun.type;
                       }
                       EntityShootable bulletEntity1 = bulletItem.getEntity(this.worldObj, Vec3.createVectorHelper((double)((double)gunVec.x + this.posX), (double)((double)gunVec.y + this.posY), (double)((double)gunVec.z + this.posZ)), this.axes.getYaw(), this.axes.getPitch(), this.motionX, this.motionY, this.motionZ, (EntityLivingBase)this.seats[0].riddenByEntity, (boolean)damageMultiplier ? 1 : 0, this.driveableData.getStackInSlot(slot).getItemDamage(), type);
                       this.worldObj.spawnEntityInWorld((Entity)bulletEntity1);
                       this.spawnParticle(type.shootParticle(secondary), shootPoint, gunVec);
                       if (type.shootSound(secondary) != null) {
                           PacketPlaySound.sendSoundPacket(this.posX, this.posY, this.posZ, 50.0, this.dimension, type.shootSound(secondary), false);
                       }
                       if (!this.driverIsCreative()) {
                           bulletStack.setItemDamage(bulletStack.getItemDamage() + 1);
                           if (bulletStack.getItemDamage() == bulletStack.getMaxDamage()) {
                               bulletStack.setItemDamage(0);
                               --bulletStack.stackSize;
                               if (bulletStack.stackSize == 0) {
                                   this.onWeaponInventoryChanged(secondary);
                                   bulletStack = null;
                               }
                           }
                           this.driveableData.setInventorySlotContents(slot, bulletStack);
                       }
                       this.setShootDelay(type.shootDelay(secondary), secondary);
                       break;
                   }
                   case MISSILE: 
                   case SHELL: {
                       this.tryRecoil();
                       if (!TeamsManager.shellsEnabled) break;
                       int slot = -1;
                       for (int i = this.driveableData.getMissileInventoryStart(); i < this.driveableData.getMissileInventoryStart() + type.numMissileSlots; ++i) {
                           ItemStack shell = this.driveableData.getStackInSlot(i);
                           if (shell == null || !(shell.getItem() instanceof ItemBullet) || !type.isValidAmmo(((ItemBullet)shell.getItem()).type, weaponType)) continue;
                           slot = i;
                       }
                       if (slot == -1) break;
                       boolean damageMultiplier = true;
                       float spread1 = type.bulletSpread;
                       float shellSpeed = type.bulletSpeed;
                       ItemStack bulletStack = this.driveableData.getStackInSlot(slot);
                       ItemBullet bulletItem = (ItemBullet)bulletStack.getItem();
                       EntityShootable bulletEntity1 = bulletItem.getEntity(this.worldObj, Vector3f.add(gunVec, new Vector3f(this.posX, this.posY, this.posZ), null), lookVector, (EntityLivingBase)this.seats[0].riddenByEntity, spread1, (boolean)damageMultiplier ? 1 : 0, shellSpeed, this.driveableData.getStackInSlot(slot).getItemDamage(), (InfoType)type);
                       this.worldObj.spawnEntityInWorld((Entity)bulletEntity1);
                       this.spawnParticle(type.shootParticle(secondary), shootPoint, gunVec);
                       this.isRecoil = true;
                       if (type.shootSound(secondary) != null) {
                           PacketPlaySound.sendSoundPacket(this.posX, this.posY, this.posZ, 50.0, this.dimension, type.shootSound(secondary), false);
                       }
                       if (!this.driverIsCreative()) {
                           bulletStack.setItemDamage(bulletStack.getItemDamage() + 1);
                           if (bulletStack.getItemDamage() == bulletStack.getMaxDamage()) {
                               bulletStack.setItemDamage(0);
                               --bulletStack.stackSize;
                               if (bulletStack.stackSize == 0) {
                                   this.onWeaponInventoryChanged(secondary);
                                   bulletStack = null;
                               }
                           }
                           this.driveableData.setInventorySlotContents(slot, bulletStack);
                       }
                       this.setShootDelay(type.shootDelay(secondary), secondary);
                       this.canFireIT1 = false;
                       break;
                   }
                   case GUN: {
                       break;
                   }
                   case MINE: {
                       break;
                   }
               }
           }
      }
      }
   }
       

   public Vector3f getOrigin(ShootPoint dp) {
      Vector3f localGunVec = new Vector3f(dp.rootPos.position);
      if(dp.rootPos.part == EnumDriveablePart.turret) {
         Vector3f.sub(localGunVec, this.getDriveableType().turretOrigin, localGunVec);
         localGunVec = this.seats[0].looking.findLocalVectorGlobally(localGunVec);
         Vector3f.add(localGunVec, this.getDriveableType().turretOrigin, localGunVec);
      }

      return this.rotate(localGunVec);
   }

   public Vector3f getPositionOnTurret(Vector3f vecIn, boolean barrel) {
      RotatedAxes yawOnlyLooking = new RotatedAxes(this.seats[0].looking.getYaw(), 0.0F, 0.0F);
      if(barrel) {
         yawOnlyLooking = this.seats[0].looking;
      }

      Vector3f.sub(vecIn, this.getDriveableType().turretOrigin, vecIn);
      Vector3f transform = yawOnlyLooking.findLocalVectorGlobally(vecIn);
      Vector3f.add(transform, this.getDriveableType().turretOrigin, transform);
      Vector3f turretOriginOffset = new Vector3f(this.getDriveableType().turretOriginOffset);
      turretOriginOffset = yawOnlyLooking.findLocalVectorGloballyYaw(turretOriginOffset);
      Vector3f.add(transform, turretOriginOffset, transform);
      return this.rotate(transform);
   }

   public Vector3f getDirection(ShootPoint dp, Vector3f vIn) {
      Vector3f localGunVec = new Vector3f(vIn);
      localGunVec = this.seats[0].looking.findLocalVectorGlobally(localGunVec);
      return this.rotate(localGunVec);
   }

   public Vector3f getLookVector(ShootPoint dp) {
      return this.axes.getXAxis();
   }

   public Vector3f getFiringPosition(ShootPoint dp) {
      Vector3f rootVector = new Vector3f(dp.rootPos.position);
      Vector3f offsetVector = new Vector3f(dp.offPos);
      Vector3f localGunVec = new Vector3f(dp.rootPos.position);
      if(dp.rootPos.part == EnumDriveablePart.turret) {
         if(offsetVector.x == 0.0F && offsetVector.y == 0.0F && offsetVector.z == 0.0F) {
            Vector3f.sub(localGunVec, this.getDriveableType().turretOrigin, localGunVec);
            localGunVec = this.seats[0].looking.findLocalVectorGlobally(localGunVec);
            Vector3f.add(localGunVec, this.getDriveableType().turretOrigin, localGunVec);
         } else {
            RotatedAxes yawOnlyLooking = new RotatedAxes(this.seats[0].looking.getYaw(), 0.0F, 0.0F);
            Vector3f.sub(rootVector, this.getDriveableType().turretOrigin, rootVector);
            rootVector = yawOnlyLooking.findLocalVectorGlobally(rootVector);
            Vector3f.add(rootVector, this.getDriveableType().turretOrigin, rootVector);
            Vector3f.sub(offsetVector, this.getDriveableType().turretOrigin, offsetVector);
            offsetVector = this.seats[0].looking.findLocalVectorGlobally(offsetVector);
            Vector3f.add(rootVector, offsetVector, localGunVec);
         }
      }

      return this.rotate(localGunVec);
   }

   public void correctWheelPos() {
      if(super.ticksExisted % 200 == 0) {
         EntityWheel[] var1 = this.wheels;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            EntityWheel wheel = var1[var3];
            if(wheel != null) {
               Vector3f target = this.axes.findLocalVectorGlobally(this.getDriveableType().wheelPositions[wheel.ID].position);
               target.x = (float)((double)target.x + super.posX);
               target.y = (float)((double)target.y + super.posY);
               target.z = (float)((double)target.z + super.posZ);
               byte tf = 1;
               byte cf = 1;
               byte range = 5;
               if(MathHelper.abs(target.x - (float)wheel.posX) > (float)range) {
                  wheel.posX = (double)((target.x * (float)tf + (float)wheel.posX * (float)cf) / (float)(tf + cf));
               }

               if(MathHelper.abs(target.y - (float)wheel.posY) > (float)range) {
                  wheel.posY = (double)((target.y * (float)tf + (float)wheel.posY * (float)cf) / (float)(tf + cf));
               }

               if(MathHelper.abs(target.z - (float)wheel.posZ) > (float)range) {
                  wheel.posZ = (double)((target.z * (float)tf + (float)wheel.posZ * (float)cf) / (float)(tf + cf));
               }
            }
         }
      }

   }

   public void onUpdate() {
      super.onUpdate();
      DriveableType type = this.getDriveableType();
      DriveableData data = this.getDriveableData();
      this.hugeBoat = this.getDriveableType().floatOnWater && this.getDriveableType().wheelStepHeight == 0.0F;
      int canThrust;
      if(this.hugeBoat) {
         for(canThrust = 0; canThrust < super.worldObj.loadedEntityList.size(); ++canThrust) {
            Object fuelMultiplier = super.worldObj.loadedEntityList.get(canThrust);
            if(fuelMultiplier instanceof EntityPlayer && !this.isPartOfThis((Entity)fuelMultiplier)) {
               this.moveRiders((Entity)fuelMultiplier);
            }

            if(fuelMultiplier instanceof EntityWheel && !this.isPartOfThis((Entity)fuelMultiplier) && this.getDistanceToEntity((Entity)fuelMultiplier) <= this.getDriveableType().bulletDetectionRadius) {
               ;
            }

            if(fuelMultiplier instanceof EntityDriveable && !this.isPartOfThis((Entity)fuelMultiplier) && this.getDistanceToEntity((Entity)fuelMultiplier) <= this.getDriveableType().bulletDetectionRadius) {
               ;
            }
         }
      }

      if(this.deckCheck != this.prevDeckCheck) {
         this.onDeck = true;
      } else {
         this.onDeck = false;
      }

      boolean var16;
      if(type.IT1 && !this.disabled) {
         var16 = false;
         if(type.weaponType(false) == EnumWeaponType.MISSILE) {
            var16 = this.leftMouseHeld;
         }

         if(type.weaponType(true) == EnumWeaponType.MISSILE) {
            var16 = this.rightMouseHeld;
         }

         this.prevDrakonDoorAngle = this.drakonDoorAngle;
         this.prevDrakonArmAngle = this.drakonArmAngle;
         this.prevDrakonRailAngle = this.drakonRailAngle;
         if(this.canFireIT1) {
            this.reloadingDrakon = false;
         }

         if(this.stage == 0) {
            this.stage = 1;
         }

         if(this.stage == 8 && var16) {
            this.stage = 1;
            this.timeTillDeactivate = 5;
            this.toDeactivate = true;
         }

         if(this.timeTillDeactivate <= 0 && this.toDeactivate) {
            this.canFireIT1 = false;
            this.toDeactivate = false;
         }

         if(this.reloadAnimTime <= 0) {
            this.IT1Reload();
         }

         --this.reloadAnimTime;
         --this.timeTillDeactivate;
      }

      this.prevPropAngle = this.propAngle;
      this.prevRotorAngle = this.rotorAngle;
      if(this.throttle != 0.0F) {
         this.propAngle = (float)((double)this.propAngle + Math.pow((double)this.throttle, 0.4D) * 1.5D);
         this.rotorAngle += this.throttle / 7.0F;
      }

      if(this.leftMouseHeld && !this.disabled) {
         this.tryRecoil();
         this.setRecoilTimer();
      }

      this.lastRecoilPos = this.recoilPos;
      if(this.recoilPos > 180.0F - 180.0F / type.recoilTime) {
         this.recoilPos = 0.0F;
         this.isRecoil = false;
      }

      if(this.isRecoil) {
         this.recoilPos += 180.0F / type.recoilTime;
      }

      if(this.recoilTimer >= 0) {
         --this.recoilTimer;
      }

      this.checkInventoryChanged();
      if(super.worldObj.isAnyLiquid(super.boundingBox) && !this.hugeBoat) {
         if(this.throttle >= type.maxThrottleInWater) {
            this.throttle = type.maxThrottleInWater;
         }

         if(this.throttle <= -type.maxThrottleInWater) {
            this.throttle = -type.maxThrottleInWater;
         }

         if(super.worldObj.isAnyLiquid(super.boundingBox.copy().offset(0.0D, (double)type.maxDepth, 0.0D))) {
            this.throttle = 0.0F;
            this.disabled = true;
         }
      } else {
         this.disabled = false;
      }

      Vector3f pos;
      Vector3f var17;
      if((type.lockOnToLivings || type.lockOnToMechas || type.lockOnToPlanes || type.lockOnToPlayers || type.lockOnToVehicles) && !super.worldObj.isRemote && this.seats.length > 0 && this.lockOnSoundDelay <= 0 && this.seats[0] != null && this.seats[0].riddenByEntity instanceof EntityPlayer) {
         canThrust = this.getCurrentGun(false);
         var17 = this.getOrigin((ShootPoint)type.shootPoints(false).get(canThrust));
         Iterator i = super.worldObj.loadedEntityList.iterator();

         while(i.hasNext()) {
            Object stack = i.next();
            Entity item = (Entity)stack;
            if(type.lockOnToMechas && item instanceof EntityMecha || type.lockOnToVehicles && item instanceof EntityVehicle || type.lockOnToPlanes && item instanceof EntityPlane || type.lockOnToPlayers && item instanceof EntityPlayer || type.lockOnToLivings && item instanceof EntityLivingBase) {
               double part = (double)this.getDistanceToEntity(item);
               if(this.getDistanceSqToEntity(item) < (double)(type.maxRangeLockOn * type.maxRangeLockOn)) {
                  pos = new Vector3f(item.posX - super.posX, item.posY - super.posY, item.posZ - super.posZ);
                  float localPosition2 = Math.abs(Vector3f.angle(var17, pos));
                  if((double)localPosition2 < Math.toRadians((double)type.canLockOnAngle)) {
                     PacketPlaySound.sendSoundPacket(this.seats[0].posX, this.seats[0].posY, this.seats[0].posZ, 10.0D, super.dimension, type.lockOnSound, false);
                     if(item instanceof EntityDriveable) {
                        PacketPlaySound.sendSoundPacket(item.posX, item.posY, item.posZ, (double)((EntityDriveable)item).getDriveableType().lockedOnSoundRange, item.dimension, ((EntityDriveable)item).getDriveableType().lockingOnSound, false);
                     }

                     this.lockOnSoundDelay = type.lockOnSoundTime;
                     break;
                  }
               }
            }
         }
      }

      if(this.lockOnSoundDelay > 0) {
         --this.lockOnSoundDelay;
      }

      if(super.ridingEntity != null) {
         this.invulnerableUnmountCount = 80;
      } else if(this.invulnerableUnmountCount > 0) {
         --this.invulnerableUnmountCount;
      }

      if(!super.worldObj.isRemote) {
         for(canThrust = 0; canThrust < this.getDriveableType().numPassengers + 1; ++canThrust) {
            if(this.seats[canThrust] == null || !this.seats[canThrust].addedToChunk) {
               this.seats[canThrust] = new EntitySeat(super.worldObj, this, canThrust);
               super.worldObj.spawnEntityInWorld(this.seats[canThrust]);
            }
         }

         for(canThrust = 0; canThrust < type.wheelPositions.length; ++canThrust) {
            if(this.wheels[canThrust] == null || !this.wheels[canThrust].addedToChunk) {
               this.wheels[canThrust] = new EntityWheel(super.worldObj, this, canThrust);
               super.worldObj.spawnEntityInWorld(this.wheels[canThrust]);
            }
         }
      }

      if(this.lastAtkEntity != null && this.lastAtkEntity instanceof EntityPlayer && this.isShowedPosition && (!super.worldObj.isRemote || FlansMod.proxy.isThePlayer((EntityPlayer)this.lastAtkEntity) || FlansMod.proxy.isOnSameTeamClientPlayer((EntityLivingBase)this.lastAtkEntity))) {
         
      }

      if(this.isShowedPosition && this.tickCount > 0) {
         --this.tickCount;
      }

      if(this.tickCount <= 0) {
         this.isShowedPosition = false;
      }

      if(this.hasEnoughFuel()) {
         this.harvesterAngle += this.throttle / 5.0F;
      }

      int damage;
      if(type.harvestBlocks && type.harvestBoxSize != null && type.harvestBoxPos != null && TeamsManager.driveablesBreakBlocks) {
         Vector3f var24 = new Vector3f(type.harvestBoxSize.x / 16.0F, type.harvestBoxSize.y / 16.0F, type.harvestBoxSize.z / 16.0F);
         var17 = new Vector3f(type.harvestBoxPos.x / 16.0F, type.harvestBoxPos.y / 16.0F, type.harvestBoxPos.z / 16.0F);

         for(float var19 = var17.x; var19 <= var17.x + var24.x; ++var19) {
            for(float var23 = var17.y; var23 <= var17.y + var24.y; ++var23) {
               for(float var27 = var17.z; var27 <= var17.z + var24.z; ++var27) {
                  Vector3f var30 = this.axes.findLocalVectorGlobally(new Vector3f(var19, var23, var27));
                  damage = (int)Math.round(super.posX + (double)var30.x);
                  int var38 = (int)Math.round(super.posY + (double)var30.y);
                  int var39 = (int)Math.round(super.posZ + (double)var30.z);
                  Block block = super.worldObj.getBlock(damage, var38, var39);
                  if(type.materialsHarvested.contains(block.getMaterial()) && block.getBlockHardness(super.worldObj, damage, var38, var39) >= 0.0F) {
                     ArrayList stacks;
                     int i1;
                     ItemStack stack1;
                     if(type.collectHarvest) {
                        stacks = block.getDrops(super.worldObj, damage, var38, var39, super.worldObj.getBlockMetadata(damage, var38, var39), 0);

                        for(i1 = 0; i1 < stacks.size(); ++i1) {
                           stack1 = (ItemStack)stacks.get(i1);
                           FlansMod.log("");
                           if(!InventoryHelper.addItemStackToInventory(this.driveableData, stack1, this.driverIsCreative()) && !super.worldObj.isRemote && super.worldObj.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
                              super.worldObj.spawnEntityInWorld(new EntityItem(super.worldObj, (double)((float)damage + 0.5F), (double)((float)var38 + 0.5F), (double)((float)var39 + 0.5F), stack1));
                           }
                        }
                     } else if(type.dropHarvest) {
                        stacks = block.getDrops(super.worldObj, damage, var38, var39, super.worldObj.getBlockMetadata(damage, var38, var39), 0);

                        for(i1 = 0; i1 < stacks.size(); ++i1) {
                           stack1 = (ItemStack)stacks.get(i1);
                           super.worldObj.spawnEntityInWorld(new EntityItem(super.worldObj, (double)((float)damage + 0.5F), (double)((float)var38 + 0.5F), (double)((float)var39 + 0.5F), stack1));
                        }
                     }

                     super.worldObj.func_147480_a(damage, var38, var39, false);
                  }
               }
            }
         }
      }

      Iterator var25 = this.getDriveableData().parts.values().iterator();

      while(var25.hasNext()) {
         DriveablePart var18 = (DriveablePart)var25.next();
         if(var18.box != null) {
            var18.update(this);
            Vector3f var22;
            if(super.worldObj.isRemote) {
               if(var18.onFire) {
                  var22 = this.axes.findLocalVectorGlobally(new Vector3f(var18.box.x + super.rand.nextFloat() * var18.box.w, var18.box.y + super.rand.nextFloat() * var18.box.h, var18.box.z + super.rand.nextFloat() * var18.box.d));
                  super.worldObj.spawnParticle("flame", super.posX + (double)var22.x, super.posY + (double)var22.y, super.posZ + (double)var22.z, 0.0D, 0.0D, 0.0D);
               }

               if(var18.health > 0 && var18.health < var18.maxHealth / 2) {
                  var22 = this.axes.findLocalVectorGlobally(new Vector3f(var18.box.x + super.rand.nextFloat() * var18.box.w, var18.box.y + super.rand.nextFloat() * var18.box.h, var18.box.z + super.rand.nextFloat() * var18.box.d));
                  super.worldObj.spawnParticle(var18.health < var18.maxHealth / 4?"largesmoke":"smoke", super.posX + (double)var22.x, super.posY + (double)var22.y, super.posZ + (double)var22.z, 0.0D, 0.0D, 0.0D);
               }
            }

            if(var18.onFire) {
               if(super.worldObj.isRaining() && super.rand.nextInt(40) == 0) {
                  var18.onFire = false;
               }

               var22 = this.axes.findLocalVectorGlobally(new Vector3f(var18.box.x + var18.box.w / 2.0F, var18.box.y + var18.box.h / 2.0F, var18.box.z + var18.box.d / 2.0F));
               if(super.worldObj.getBlock(MathHelper.floor_double(super.posX + (double)var22.x), MathHelper.floor_double(super.posY + (double)var22.y), MathHelper.floor_double(super.posZ + (double)var22.z)).getMaterial() == Material.water) {
                  var18.onFire = false;
               }
            } else {
               var22 = this.axes.findLocalVectorGlobally(new Vector3f(var18.box.x / 16.0F + var18.box.w / 32.0F, var18.box.y / 16.0F + var18.box.h / 32.0F, var18.box.z / 16.0F + var18.box.d / 32.0F));
               if(super.worldObj.getBlock(MathHelper.floor_double(super.posX + (double)var22.x), MathHelper.floor_double(super.posY + (double)var22.y), MathHelper.floor_double(super.posZ + (double)var22.z)).getMaterial() == Material.lava) {
                  var18.onFire = true;
               }
            }
         }
      }

      for(canThrust = 0; canThrust < type.emitters.size(); ++canThrust) {
         DriveableType.ParticleEmitter var20 = (DriveableType.ParticleEmitter)type.emitters.get(canThrust);
         --this.emitterTimers[canThrust];
         boolean var28 = false;
         boolean var26 = false;
         DriveablePart var29 = (DriveablePart)this.getDriveableData().parts.get(EnumDriveablePart.getPart(var20.part));
         float var33 = (float)var29.health / (float)var29.maxHealth;
         if(this.isPartIntact(EnumDriveablePart.getPart(var20.part)) && var33 >= var20.minHealth && var33 <= var20.maxHealth) {
            var28 = true;
         } else {
            var28 = false;
         }

         if(this.throttle >= var20.minThrottle && this.throttle <= var20.maxThrottle) {
            var26 = true;
         }

         if(this.isMecha) {
            var26 = true;
         }

         if(this.emitterTimers[canThrust] <= 0) {
            if(var26 && var28) {
               Vector3f var36 = new Vector3f(0.0F, 0.0F, 0.0F);
               pos = new Vector3f(0.0F, 0.0F, 0.0F);
               if(this.seats != null && this.seats[0] != null) {
                  Vector3f var40;
                  if(EnumDriveablePart.getPart(var20.part) != EnumDriveablePart.turret && EnumDriveablePart.getPart(var20.part) != EnumDriveablePart.barrel) {
                     var40 = new Vector3f(var20.origin.x + super.rand.nextFloat() * var20.extents.x - var20.extents.x * 0.5F, var20.origin.y + super.rand.nextFloat() * var20.extents.y - var20.extents.y * 0.5F, var20.origin.z + super.rand.nextFloat() * var20.extents.z - var20.extents.z * 0.5F);
                     pos = this.axes.findLocalVectorGlobally(var40);
                     var36 = this.axes.findLocalVectorGlobally(var20.velocity);
                  } else if(EnumDriveablePart.getPart(var20.part) != EnumDriveablePart.turret && (EnumDriveablePart.getPart(var20.part) != EnumDriveablePart.head || var20.part == "barrel")) {
                     if(EnumDriveablePart.getPart(var20.part) == EnumDriveablePart.barrel) {
                        var40 = new Vector3f(var20.origin.x + super.rand.nextFloat() * var20.extents.x - var20.extents.x * 0.5F, var20.origin.y + super.rand.nextFloat() * var20.extents.y - var20.extents.y * 0.5F, var20.origin.z + super.rand.nextFloat() * var20.extents.z - var20.extents.z * 0.5F);
                        pos = this.getPositionOnTurret(var40, true);
                        var36 = this.getPositionOnTurret(var20.velocity, true);
                     }
                  } else {
                     var40 = new Vector3f(var20.origin.x + super.rand.nextFloat() * var20.extents.x - var20.extents.x * 0.5F, var20.origin.y + super.rand.nextFloat() * var20.extents.y - var20.extents.y * 0.5F, var20.origin.z + super.rand.nextFloat() * var20.extents.z - var20.extents.z * 0.5F);
                     pos = this.getPositionOnTurret(var40, false);
                     var36 = this.getPositionOnTurret(var20.velocity, false);
                  }

                  FlansMod.getPacketHandler().sendToAllAround(new PacketParticle(var20.effectType, super.posX + (double)pos.x, super.posY + (double)pos.y, super.posZ + (double)pos.z, (double)var36.x, (double)var36.y, (double)var36.z), super.posX + (double)pos.x, super.posY + (double)pos.y, super.posZ + (double)pos.z, 150.0F, super.dimension);
               }
            }

            this.emitterTimers[canThrust] = var20.emitRate;
         }
      }

      this.checkParts();
      super.prevRotationYaw = this.axes.getYaw();
      super.prevRotationPitch = this.axes.getPitch();
      this.prevRotationRoll = this.axes.getRoll();
      this.prevAxes = this.axes.clone();
      if(super.riddenByEntity != null && super.riddenByEntity.isDead) {
         super.riddenByEntity = null;
      }

      if(super.riddenByEntity != null && super.isDead) {
         super.riddenByEntity.mountEntity((Entity)null);
      }

      if(super.riddenByEntity != null) {
         super.riddenByEntity.fallDistance = 0.0F;
      }

      var16 = this.driverIsCreative() || this.driveableData.fuelInTank > 0.0F;
      if(this.seats[0] != null && this.seats[0].riddenByEntity == null || !var16 && this.getDriveableType().maxThrottle != 0.0F && this.getDriveableType().maxNegativeThrottle != 0.0F) {
         this.throttle *= 0.99F;
      }

      if(this.seats[0] != null && this.seats[0].riddenByEntity == null) {
         this.rightMouseHeld = this.leftMouseHeld = false;
      }

      if(this.shootDelayPrimary > 0) {
         --this.shootDelayPrimary;
      }

      if(this.shootDelaySecondary > 0) {
         --this.shootDelaySecondary;
      }

      if(super.ticksExisted == 1) {
         this.setShootDelay(this.getDriveableType().placeTimePrimary, false);
         this.setShootDelay(this.getDriveableType().placeTimeSecondary, true);
         if(!super.worldObj.isRemote) {
            if(!this.getDriveableType().placeSoundPrimary.isEmpty()) {
               PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.getDriveableType().placeSoundPrimary, false);
            }

            if(!this.getDriveableType().placeSoundSecondary.isEmpty()) {
               PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.getDriveableType().placeSoundSecondary, false);
            }
         }
      }

      if(!super.worldObj.isRemote) {
         if(this.leftMouseHeld && this.getDriveableType().modePrimary == EnumFireMode.FULLAUTO) {
            this.shoot(false);
         }

         if(this.rightMouseHeld && this.getDriveableType().modeSecondary == EnumFireMode.FULLAUTO) {
            this.shoot(true);
         }

         this.minigunSpeedPrimary *= 0.9F;
         this.minigunSpeedSecondary *= 0.9F;
         if(this.leftMouseHeld && this.getDriveableType().modePrimary == EnumFireMode.MINIGUN) {
            this.minigunSpeedPrimary += 0.1F;
            if(this.minigunSpeedPrimary > 1.0F) {
               this.shoot(false);
            }
         }

         if(this.rightMouseHeld && this.getDriveableType().modeSecondary == EnumFireMode.MINIGUN) {
            this.minigunSpeedSecondary += 0.1F;
            if(this.minigunSpeedSecondary > 1.0F) {
               this.shoot(true);
            }
         }
      }

      this.prevDeckCheck = this.deckCheck;
      byte var21 = 2;
      if(data.fuelInTank < (float)type.fuelTankSize) {
         for(int var34 = 0; var34 < data.getSizeInventory(); ++var34) {
            ItemStack var31 = data.getStackInSlot(var34);
            if(var31 != null && var31.stackSize > 0) {
               Item var32 = var31.getItem();
               if(data.engine.useRFPower) {
                  if(var32 instanceof IEnergyContainerItem) {
                     IEnergyContainerItem var35 = (IEnergyContainerItem)var32;
                     data.fuelInTank += (float)(var21 * var35.extractEnergy(var31, data.engine.RFDrawRate, false) / data.engine.RFDrawRate);
                  }
               } else {
                  if(var32 instanceof ItemPart) {
                     PartType var37 = ((ItemPart)var32).type;
                     if(var37.category == 9) {
                        data.fuelInTank += (float)var21;
                        damage = var31.getItemDamage();
                        var31.setItemDamage(damage + 1);
                        if(damage >= var31.getMaxDamage()) {
                           var31.setItemDamage(0);
                           --var31.stackSize;
                           if(var31.stackSize <= 0) {
                              data.setInventorySlotContents(var34, (ItemStack)null);
                           }
                        }
                        break;
                     }
                  } else if(FlansMod.hooks.BuildCraftLoaded && var31.isItemEqual(FlansMod.hooks.BuildCraftOilBucket) && data.fuelInTank + (float)(1000 * var21) <= (float)type.fuelTankSize) {
                     data.fuelInTank += (float)(1000 * var21);
                     data.setInventorySlotContents(var34, new ItemStack(Items.bucket));
                  } else if(FlansMod.hooks.BuildCraftLoaded && var31.isItemEqual(FlansMod.hooks.BuildCraftFuelBucket) && data.fuelInTank + (float)(2000 * var21) <= (float)type.fuelTankSize) {
                     data.fuelInTank += (float)(2000 * var21);
                     data.setInventorySlotContents(var34, new ItemStack(Items.bucket));
                  }

                  super.prevPosX = super.posX;
                  super.prevPosY = super.posY;
                  super.prevPosZ = super.posZ;
               }
            }
         }

      }
   }

   public void checkInventoryChanged() {
      DriveableType type = this.getDriveableType();
      if(type != null) {
         if(!super.worldObj.isRemote) {
            if(this.driveableData.inventoryChanged) {
               this.driveableData.inventoryChanged = false;

               try {
                  for(int e = 0; e < 2; ++e) {
                     EnumWeaponType weaponType = e == 0?type.primary:type.secondary;
                     if(weaponType == EnumWeaponType.GUN) {
                        weaponType = EnumWeaponType.NONE;
                     }

                     int istart = this.getInventoryStart(weaponType);
                     if(istart == this.driveableData.getAmmoInventoryStart()) {
                        istart += type.numPassengerGunners;
                     }

                     int isize = this.getInventorySize(weaponType);
                     if(istart >= 0 || isize > 0) {
                        if(this.prevInventoryItems[e] == null) {
                           this.prevInventoryItems[e] = new ItemStack[isize];
                        }

                        int i;
                        for(i = 0; i < isize; ++i) {
                           ItemStack itemStack = this.driveableData.getStackInSlot(istart + i);
                           if(itemStack != null && itemStack.getItem() instanceof ItemBullet && (this.prevInventoryItems[e][i] == null || !ItemStack.areItemStacksEqual(itemStack, this.prevInventoryItems[e][i])) && type.isValidAmmo(((ItemBullet)itemStack.getItem()).type, weaponType)) {
                              this.onWeaponInventoryChanged(e == 1);
                              break;
                           }
                        }

                        for(i = 0; i < isize; ++i) {
                           this.prevInventoryItems[e][i] = this.driveableData.getStackInSlot(istart + i);
                        }
                     }
                  }
               } catch (Exception var8) {
                  var8.printStackTrace();
               }

            }
         }
      }
   }

   public void onWeaponInventoryChanged(boolean secondary) {
      DriveableType type = this.getDriveableType();
      if(!secondary) {
         if(type.reloadTimePrimary > 0 && this.getShootDelay(secondary) <= 0) {
            FlansMod.log("EntityDriveable Reload Primary " + type.reloadTimePrimary + " tick");
            this.setShootDelay(type.reloadTimePrimary, secondary);
            PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.getDriveableType().reloadSoundPrimary, false);
         }
      } else if(type.reloadTimeSecondary > 0 && this.getShootDelay(secondary) <= 0) {
         FlansMod.log("EntityDriveable Reload Secondary " + type.reloadTimeSecondary + " tick");
         this.setShootDelay(type.reloadTimeSecondary, secondary);
         PacketPlaySound.sendSoundPacket(super.posX, super.posY, super.posZ, 50.0D, super.dimension, this.getDriveableType().reloadSoundSecondary, false);
      }

   }



   public int getInventoryStart(EnumWeaponType wt) {
       switch (wt) {
           case GUN: 
           case NONE: {
               return this.driveableData.getAmmoInventoryStart();
           }
           case MISSILE: 
           case SHELL: {
               return this.driveableData.getMissileInventoryStart();
           }
           case BOMB: 
           case MINE: {
               return this.driveableData.getBombInventoryStart();
           }
       }
       return -1;
   }

   public int getInventorySize(EnumWeaponType wt) {
       switch (wt) {
           case GUN: 
           case NONE: {
               return this.driveableData.ammo.length;
           }
           case MISSILE: 
           case SHELL: {
               return this.driveableData.missiles.length;
           }
           case BOMB: 
           case MINE: {
               return this.driveableData.bombs.length;
           }
       }
       return -1;
   }


   public void checkForCollisions() {
      boolean damagePart = false;
      boolean crashInWater = false;
      double speed = this.getSpeedXYZ();
      Iterator var5 = this.getDriveableType().collisionPoints.iterator();

      while(var5.hasNext()) {
         DriveablePosition p = (DriveablePosition)var5.next();
         if(!((DriveablePart)this.driveableData.parts.get(p.part)).dead) {
            Vector3f lastRelPos = this.prevAxes.findLocalVectorGlobally(p.position);
            Vec3 lastPos = Vec3.createVectorHelper(super.prevPosX + (double)lastRelPos.x, super.prevPosY + (double)lastRelPos.y, super.prevPosZ + (double)lastRelPos.z);
            Vector3f currentRelPos = this.axes.findLocalVectorGlobally(p.position);
            Vec3 currentPos = Vec3.createVectorHelper(super.posX + (double)currentRelPos.x, super.posY + (double)currentRelPos.y, super.posZ + (double)currentRelPos.z);
            if(FlansMod.DEBUG && super.worldObj.isRemote) {
               super.worldObj.spawnEntityInWorld(new EntityDebugVector(super.worldObj, new Vector3f(lastPos), Vector3f.sub(currentRelPos, lastRelPos, (Vector3f)null), 10, 1.0F, 0.0F, 0.0F));
            }

            MovingObjectPosition hit = super.worldObj.rayTraceBlocks(lastPos, currentPos, crashInWater);
            if(hit != null && hit.typeOfHit == MovingObjectType.BLOCK) {
               int x = hit.blockX;
               int y = hit.blockY;
               int z = hit.blockZ;
               Block blockHit = super.worldObj.getBlock(x, y, z);
               int meta = super.worldObj.getBlockMetadata(x, y, z);
               float blockHardness = blockHit.getBlockHardness(super.worldObj, x, y, z);
               float damage = blockHardness * blockHardness * (float)speed;
               if(null == blockHit.getCollisionBoundingBoxFromPool(super.worldObj, x, y, z)) {
                  damage = 0.0F;
               }

               if(damage > 0.0F) {
                  damagePart = true;
               }

               if(!this.attackPart(p.part, DamageSource.inWall, damage) && TeamsManager.driveablesBreakBlocks) {
                  super.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 2001, x, y, z, Block.getIdFromBlock(blockHit) + (meta << 12));
                  if(!super.worldObj.isRemote) {
                     blockHit.dropBlockAsItem(super.worldObj, x, y, z, meta, 1);
                     super.worldObj.setBlockToAir(x, y, z);
                  }
               } else {
                  super.worldObj.createExplosion(this, currentPos.xCoord, currentPos.yCoord, currentPos.zCoord, 1.0F, false);
               }
            }
         }
      }

      if(damagePart && !super.worldObj.isRemote) {
         FlansMod.getPacketHandler().sendToAllAround(new PacketDriveableDamage(this), super.posX, super.posY, super.posZ, 100.0F, super.dimension);
      }

   }

   protected void fall(float k) {
      double fallDist = (super.posY - super.prevPosY + super.motionY) / 2.0D;
      float damage = (float)(fallDist < -0.3D?-fallDist * 50.0D:0.0D);
      boolean no_damage = true;
      if(damage > 0.0F && this.invulnerableUnmountCount == 0 && super.ticksExisted > 20 && !no_damage) {
         DriveableType type = this.getDriveableType();
         damage = (float)((int)(damage * type.fallDamageFactor));
         this.attackPart(EnumDriveablePart.core, DamageSource.fall, damage);
         if(type.wheelPositions.length > 0) {
            this.attackPart(type.wheelPositions[0].part, DamageSource.fall, damage / 5.0F);
         }

         no_damage = false;
      }

   }

   public boolean attackPart(EnumDriveablePart ep, DamageSource source, float damage) {
      if(ep == EnumDriveablePart.core) {
         if(source.getSourceOfDamage() instanceof EntityLivingBase) {
            this.lastAtkEntity = source.getSourceOfDamage();
         } else if(source.getEntity() instanceof EntityLivingBase) {
            this.lastAtkEntity = source.getEntity();
         } else {
            this.lastAtkEntity = null;
         }
      }

      DriveablePart part = (DriveablePart)this.driveableData.parts.get(ep);
      return part.attack(damage, source.isFireDamage());
   }

   public Vector3f rotate(Vector3f inVec) {
      return this.axes.findLocalVectorGlobally(inVec);
   }

   public Vector3f rotate(Vec3 inVec) {
      return this.rotate(inVec.xCoord, inVec.yCoord, inVec.zCoord);
   }

   public Vector3f rotate(double x, double y, double z) {
      return this.rotate(new Vector3f((float)x, (float)y, (float)z));
   }

   public void rotateYaw(float rotateBy) {
      if(Math.abs(rotateBy) >= 0.01F) {
         this.axes.rotateLocalYaw(rotateBy);
         this.updatePrevAngles();
      }
   }

   public void rotatePitch(float rotateBy) {
      if(Math.abs(rotateBy) >= 0.01F) {
         this.axes.rotateLocalPitch(rotateBy);
         this.updatePrevAngles();
      }
   }

   public void rotateRoll(float rotateBy) {
      if(Math.abs(rotateBy) >= 0.01F) {
         this.axes.rotateLocalRoll(rotateBy);
         this.updatePrevAngles();
      }
   }

   public void updatePrevAngles() {
      double dYaw = (double)(this.axes.getYaw() - super.prevRotationYaw);
      if(dYaw > 180.0D) {
         super.prevRotationYaw += 360.0F;
      }

      if(dYaw < -180.0D) {
         super.prevRotationYaw -= 360.0F;
      }

      double dPitch = (double)(this.axes.getPitch() - super.prevRotationPitch);
      if(dPitch > 180.0D) {
         super.prevRotationPitch += 360.0F;
      }

      if(dPitch < -180.0D) {
         super.prevRotationPitch -= 360.0F;
      }

      double dRoll = (double)(this.axes.getRoll() - this.prevRotationRoll);
      if(dRoll > 180.0D) {
         this.prevRotationRoll += 360.0F;
      }

      if(dRoll < -180.0D) {
         this.prevRotationRoll -= 360.0F;
      }

   }

   public void setRotation(float rotYaw, float rotPitch, float rotRoll) {
      this.axes.setAngles(rotYaw, rotPitch, rotRoll);
   }

   public boolean isPartOfThis(Entity ent) {
      EntitySeat[] var2 = this.seats;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         EntitySeat seat = var2[var4];
         if(seat != null) {
            if(ent == seat) {
               return true;
            }

            if(seat.riddenByEntity == ent) {
               return true;
            }
         }
      }

      return ent == this;
   }

   public float getShadowSize() {
      return 0.0F;
   }

   public DriveableType getDriveableType() {
      return DriveableType.getDriveable(this.driveableType);
   }

   public DriveableData getDriveableData() {
      return this.driveableData;
   }

   public boolean isDead() {
      return super.isDead;
   }

   public Entity getControllingEntity() {
      return this.seats[0].getControllingEntity();
   }

   public ItemStack getPickedResult(MovingObjectPosition target) {
      ItemStack stack = new ItemStack(this.getDriveableType().item, 1, 0);
      stack.stackTagCompound = new NBTTagCompound();
      this.driveableData.writeToNBT(stack.stackTagCompound);
      return stack;
   }

   public boolean hasFuel() {
      return this.seats != null && this.seats[0] != null && this.seats[0].riddenByEntity != null?this.driverIsCreative() || this.driveableData.fuelInTank > 0.0F:false;
   }

   public boolean hasEnoughFuel() {
      return this.driverIsCreative() || this.driveableData.fuelInTank > this.driveableData.engine.fuelConsumption * this.throttle;
   }

   public double getSpeedXYZ() {
      return Math.sqrt(super.motionX * super.motionX + super.motionY * super.motionY + super.motionZ * super.motionZ);
   }

   public double getSpeedXZ() {
      return Math.sqrt(super.motionX * super.motionX + super.motionZ * super.motionZ);
   }

   public boolean landVehicle() {
      return false;
   }

   public boolean gearDown() {
      return true;
   }

   public boolean onGround() {
      return super.onGround;
   }

   public void moveRiders(Entity rider) {
      if(!this.isPartOfThis(rider)) {
         boolean isHuman = false;
         boolean isDriveable = false;
         if(rider instanceof EntityPlayer) {
            Vector3f riderPos = new Vector3f(rider.posX, rider.posY, rider.posZ);
            Vector3f riderMotion = new Vector3f(rider.motionX, rider.motionY, rider.motionY);
            new Vector3f(super.posX - (double)this.lastPos.x, super.posY - (double)this.lastPos.y, super.posZ - (double)this.lastPos.z);
            if(rider instanceof EntityVehicle) {
               Vector3f vehicleMotion = ((EntityVehicle)rider).lastPos;
            }

            Vector3f vehiclePos = new Vector3f(super.posX, super.posY, super.posZ);
            Vector3f relativePos = Vector3f.sub(riderPos, vehiclePos, (Vector3f)null);
            if(rider instanceof EntityPlayer) {
               isHuman = true;
            }

            if(rider instanceof EntityDriveable) {
               isDriveable = true;
            }

            relativePos = new Vector3f(relativePos.x, relativePos.y - (isHuman?0.55F:0.0F), relativePos.z);
            this.axes.findGlobalVectorLocally(relativePos);
            this.axes.findGlobalVectorLocally(riderMotion);
            Vector3f ellipsoid = new Vector3f(rider.width / 2.0F, rider.height, rider.width / 2.0F);
            CollisionTest test = new CollisionTest(ellipsoid, new Vector3f(relativePos.x, relativePos.y, relativePos.z), riderMotion);
            test.collisionRecursiveDepth = 0;
            Vector3f eSpacePosition = test.ConvertR3ToESpace(test.R3Position);
            Vector3f eSpaceVelocity = test.velocity;
            DriveableType type = this.getDriveableType();
            Iterator finalPos;
            if(type.fancyCollision) {
               finalPos = type.collisionBox.iterator();

               while(finalPos.hasNext()) {
                  CollisionShapeBox velocity = (CollisionShapeBox)finalPos.next();
                  this.checkCollision(test, velocity);
               }
            } else {
               finalPos = this.getDriveableData().parts.values().iterator();

               while(finalPos.hasNext()) {
                  DriveablePart var27 = (DriveablePart)finalPos.next();
                  var27.rayTraceRider(this, test);
               }
            }

            if(test.didCollide) {
               Vector3f var26 = this.collideWithDriveable(test, eSpacePosition, eSpaceVelocity);
               if(rider instanceof EntityAnimal) {
                  return;
               }

               Vector3f var28 = Vector3f.sub(var26, test.basePoint, (Vector3f)null);
               test.ConvertESpaceToR3(var28);
               var26 = new Vector3f(var26.x * test.eRad.x, var26.y * test.eRad.y, var26.z * test.eRad.z);
               Vector3f.sub(var26, vehiclePos, (Vector3f)null);
               if(rider.onGround && super.posY + (double)var26.y + 0.625D < (double)riderPos.y) {
                  ;
               }

               boolean stationary = this.throttle == 0.0F;
               if(var26 == null) {
                  var26 = new Vector3f(0.0F, 0.0F, 0.0F);
               }

               test.ConvertESpaceToR3(var26);
               boolean onTop = test.collisionPlaneNormal.y >= 0.5F;
               if(super.posY + (double)var26.y + 0.625D < (double)riderPos.y) {
                  var26.y = riderPos.y - (float)super.posY - 0.625F;
               }

               if(!this.hugeBoat) {
                  rider.setPosition(!onTop?(double)(riderPos.x + var26.x / (48.0F * Math.abs(relativePos.x))):(double)riderPos.x, onTop?super.posY + (double)var26.y + 0.625D:(double)riderPos.y, !onTop?(double)(riderPos.z + var26.z / (48.0F * Math.abs(relativePos.z))):(double)riderPos.z);
               }

               if(this.hugeBoat && !stationary) {
                  rider.setPosition((double)riderPos.x, super.posY + (double)var26.y + 0.59375D, (double)riderPos.z);
               } else if(this.hugeBoat && stationary) {
                  rider.setPosition((double)riderPos.x, super.posY + (double)var26.y + 0.625D, (double)riderPos.z);
               }

               var26 = Vector3f.sub(var26, riderPos, (Vector3f)null);
               var26.normalise();
               rider.motionY = 0.0D;
               this.updateRiderPos(rider, test, var26, riderMotion);
               if(this.getDriveableType().collisionDamageEnable && !test.isOnTop && this.throttle > this.getDriveableType().collisionDamageThrottle) {
                  boolean player = true;
                  if(TeamsManager.getInstance() != null && TeamsManager.getInstance().currentRound != null && rider instanceof EntityPlayerMP && this.seats[0].riddenByEntity instanceof EntityPlayer) {
                     EntityPlayerMP attacker = (EntityPlayerMP)this.seats[0].riddenByEntity;
                     EntityPlayerMP player1 = (EntityPlayerMP)rider;
                     Gametype var10000 = TeamsManager.getInstance().currentRound.gametype;
                     if(Gametype.getPlayerData(attacker) != null) {
                        var10000 = TeamsManager.getInstance().currentRound.gametype;
                        if(Gametype.getPlayerData(attacker).team != null) {
                           var10000 = TeamsManager.getInstance().currentRound.gametype;
                           if(Gametype.getPlayerData(player1) != null) {
                              var10000 = TeamsManager.getInstance().currentRound.gametype;
                              if(Gametype.getPlayerData(player1).team != null) {
                                 var10000 = TeamsManager.getInstance().currentRound.gametype;
                                 Team var29 = Gametype.getPlayerData(player1).team;
                                 Gametype var10001 = TeamsManager.getInstance().currentRound.gametype;
                                 if(var29 == Gametype.getPlayerData(attacker).team) {
                                    player = false;
                                 }
                              }
                           }
                        }
                     }
                  }

                  EntitySeat[] var30 = this.seats;
                  int var32 = var30.length;

                  for(int var24 = 0; var24 < var32; ++var24) {
                     EntitySeat seat = var30[var24];
                     if(rider == seat.lastRiddenByEntity) {
                        player = false;
                     }
                  }

                  if(player) {
                     if(rider instanceof EntityLiving) {
                        rider.attackEntityFrom(DamageSource.generic, this.throttle * this.getDriveableType().collisionDamageTimes);
                     } else if(rider instanceof EntityPlayer) {
                        rider.attackEntityFrom(DamageSource.generic, this.throttle * this.getDriveableType().collisionDamageTimes);
                     }
                  }
               }

               if(rider instanceof EntityPlayer) {
                  EntityPlayer var31 = (EntityPlayer)rider;
                  var31.onGround = true;
                  var31.isAirBorne = false;
                  var31.fallDistance = 0.0F;
               }
            } else if(rider instanceof EntityDriveable) {
               ((EntityDriveable)rider).deckHeight = 0.0D;
            }

         }
      }
   }

   public DamageSource getBulletDamage(boolean headshot) {
      DriveableType type = this.getDriveableType();
      EntityLivingBase owner = (EntityLivingBase)this.seats[0].riddenByEntity;
      return owner instanceof EntityPlayer?(new EntityDamageSourceGun(this.getDriveableType().shortName, this, (EntityPlayer)owner, type, headshot)).setProjectile():(new EntityDamageSourceIndirect(type.shortName, this, owner)).setProjectile();
   }

   public void checkCollision(CollisionTest tester, CollisionShapeBox box) {
      double distance = tester.nearestDistance;
      new Vector3f(0.0F, 0.0F, 0.0F);
      boolean surface = false;
      Vector3f pos = new Vector3f(super.posX, super.posY, super.posZ);
      RotatedAxes shift = this.axes;
      float var10000 = box.pos.x + box.size.x;
      float f5 = -box.pos.y + box.size.y;
      var10000 = box.pos.z + box.size.z;
      box.pos = new Vector3f(box.pos.x, box.pos.y, box.pos.z);
      Vector3f p1 = new Vector3f(box.pos.x - box.p1.x, box.pos.y + box.size.y + box.p1.y - box.size.y + 0.625F, box.pos.z - box.p1.z);
      Vector3f p2 = new Vector3f(box.pos.x + box.size.x + box.p2.x, box.pos.y + box.size.y + box.p2.y - box.size.y + 0.625F, box.pos.z - box.p2.z);
      Vector3f p3 = new Vector3f(box.pos.x + box.size.x + box.p3.x, box.pos.y + box.size.y + box.p3.y - box.size.y + 0.625F, box.pos.z + box.size.z + box.p3.z);
      Vector3f p4 = new Vector3f(box.pos.x - box.p4.x, box.pos.y + box.size.y + box.p4.y - box.size.y + 0.625F, box.pos.z + box.size.z + box.p4.z);
      Vector3f p5 = new Vector3f(box.pos.x - box.p5.x, box.pos.y - box.p5.y - box.size.y + 0.625F, box.pos.z - box.p5.z);
      Vector3f p6 = new Vector3f(box.pos.x + box.size.x + box.p6.x, box.pos.y - box.p6.y - box.size.y + 0.625F, box.pos.z - box.p6.z);
      Vector3f p7 = new Vector3f(box.pos.x + box.size.x + box.p7.x, box.pos.y - box.p7.y - box.size.y + 0.625F, box.pos.z + box.size.z + box.p7.z);
      Vector3f p8 = new Vector3f(box.pos.x - box.p8.x, box.pos.y - box.p8.y - box.size.y + 0.625F, box.pos.z + box.size.z + box.p8.z);
      if(EnumDriveablePart.getPart(box.part) == EnumDriveablePart.turret && this.seats[0] != null) {
         p1 = this.getPositionOnTurret(p1, false);
         p2 = this.getPositionOnTurret(p2, false);
         p3 = this.getPositionOnTurret(p3, false);
         p4 = this.getPositionOnTurret(p4, false);
         p5 = this.getPositionOnTurret(p5, false);
         p6 = this.getPositionOnTurret(p6, false);
         p7 = this.getPositionOnTurret(p7, false);
         p8 = this.getPositionOnTurret(p8, false);
      } else {
         p1 = shift.findLocalVectorGlobally(p1);
         p2 = shift.findLocalVectorGlobally(p2);
         p3 = shift.findLocalVectorGlobally(p3);
         p4 = shift.findLocalVectorGlobally(p4);
         p5 = shift.findLocalVectorGlobally(p5);
         p6 = shift.findLocalVectorGlobally(p6);
         p7 = shift.findLocalVectorGlobally(p7);
         p8 = shift.findLocalVectorGlobally(p8);
      }

      double topFaceDist = 100.0D;
      tester.checkTriangle(tester, p3, p2, p1);
      Vector3f collisionPoint;
      if(tester.didCollide && tester.nearestDistance < distance) {
         collisionPoint = tester.intersectionPoint;
         surface = true;
         tester.part = EnumDriveablePart.getPart(box.part);
      }

      tester.checkTriangle(tester, p4, p3, p1);
      if(tester.didCollide && tester.nearestDistance < distance) {
         collisionPoint = tester.intersectionPoint;
         surface = true;
         tester.part = EnumDriveablePart.getPart(box.part);
      }

      if(tester.didCollide) {
         tester.isOnTop = true;
         topFaceDist = tester.nearestDistance;
      }

      tester.checkTriangle(tester, p6, p7, p3);
      if(tester.didCollide && tester.nearestDistance < distance) {
         distance = tester.nearestDistance;
         collisionPoint = tester.intersectionPoint;
         surface = true;
         tester.part = EnumDriveablePart.getPart(box.part);
      }

      tester.checkTriangle(tester, p3, p2, p6);
      if(tester.didCollide && tester.nearestDistance < distance) {
         distance = tester.nearestDistance;
         collisionPoint = tester.intersectionPoint;
         surface = true;
         tester.part = EnumDriveablePart.getPart(box.part);
      }

      tester.checkTriangle(tester, p4, p1, p5);
      if(tester.didCollide && tester.nearestDistance < distance) {
         distance = tester.nearestDistance;
         collisionPoint = tester.intersectionPoint;
         surface = true;
         tester.part = EnumDriveablePart.getPart(box.part);
      }

      tester.checkTriangle(tester, p5, p8, p4);
      if(tester.didCollide && tester.nearestDistance < distance) {
         distance = tester.nearestDistance;
         collisionPoint = tester.intersectionPoint;
         surface = true;
         tester.part = EnumDriveablePart.getPart(box.part);
      }

      tester.checkTriangle(tester, p6, p5, p1);
      if(tester.didCollide && tester.nearestDistance < distance) {
         distance = tester.nearestDistance;
         collisionPoint = tester.intersectionPoint;
         surface = true;
         tester.part = EnumDriveablePart.getPart(box.part);
      }

      tester.checkTriangle(tester, p1, p2, p6);
      if(tester.didCollide && tester.nearestDistance < distance) {
         distance = tester.nearestDistance;
         collisionPoint = tester.intersectionPoint;
         surface = true;
         tester.part = EnumDriveablePart.getPart(box.part);
      }

      tester.checkTriangle(tester, p8, p7, p3);
      if(tester.didCollide && tester.nearestDistance < distance) {
         distance = tester.nearestDistance;
         collisionPoint = tester.intersectionPoint;
         surface = true;
         tester.part = EnumDriveablePart.getPart(box.part);
      }

      tester.checkTriangle(tester, p3, p4, p8);
      if(tester.didCollide && tester.nearestDistance < distance) {
         distance = tester.nearestDistance;
         collisionPoint = tester.intersectionPoint;
         surface = true;
         tester.part = EnumDriveablePart.getPart(box.part);
      }

      tester.checkTriangle(tester, p5, p6, p7);
      if(tester.didCollide && tester.nearestDistance < distance) {
         collisionPoint = tester.intersectionPoint;
         surface = true;
         tester.part = EnumDriveablePart.getPart(box.part);
      }

      tester.checkTriangle(tester, p8, p7, p5);
      if(tester.didCollide && tester.nearestDistance < distance) {
         collisionPoint = tester.intersectionPoint;
         surface = true;
         tester.part = EnumDriveablePart.getPart(box.part);
      }

      if(tester.didCollide) {
         tester.isOnTop = true;
         topFaceDist = tester.nearestDistance;
      }

      Vector3f.add(p1, pos, p1);
      Vector3f.add(p2, pos, p2);
      Vector3f.add(p3, pos, p3);
      Vector3f.add(p4, pos, p4);
      Vector3f.add(p5, pos, p5);
      Vector3f.add(p6, pos, p6);
      Vector3f.add(p7, pos, p7);
      Vector3f.add(p8, pos, p8);
      boolean muff = true;
      String wank = "crit";
      if(tester.nearestDistance < topFaceDist) {
         tester.isOnTop = false;
      }

      if(surface) {
         tester.isOnTop = true;
      }

   }

   public void renderTri(Vector3f p1, Vector3f p2, Vector3f p3) {
      Vector3f pos = new Vector3f(super.posX, super.posY, super.posZ);
      Vector3f p1a = Vector3f.add(p1, pos, (Vector3f)null);
      Vector3f p2a = Vector3f.add(p2, pos, (Vector3f)null);
      Vector3f p3a = Vector3f.add(p3, pos, (Vector3f)null);
      this.renderLine(p1a, p2a);
      this.renderLine(p2a, p3a);
      this.renderLine(p3a, p1a);
   }

   public void renderLine(Vector3f in, Vector3f out) {
      float dx = out.x - in.x;
      float dy = out.y - in.y;
      float dz = out.z - in.z;
      Vector3f diff = Vector3f.sub(out, in, (Vector3f)null);
      diff.normalise();
      float distance = (float)Math.sqrt((double)(dx * dx + dy * dy + dz * dz));

      for(int i = 0; i < 10; ++i) {
         float dist2 = distance / 10.0F * (float)i;
         Vector3f newVec = new Vector3f(in.x + dist2 * diff.x, in.y + dist2 * diff.y, in.z + dist2 * diff.z);
         FlansMod.proxy.spawnParticle("reddust", (double)newVec.x, (double)newVec.y, (double)newVec.z, 0.0D, 0.0D, 0.0D);
      }

   }

   public Vector3f collideWithDriveable(CollisionTest tester, Vector3f Pos, Vector3f vel) {
      float unitScale = 0.0625F;
      float veryCloseDistance = 0.005F * unitScale;
      if(tester.collisionRecursiveDepth > 2) {
         return Pos;
      } else {
         tester.basePoint = Pos;
         tester.didCollide = false;
         Iterator destinationPoint;
         if(this.getDriveableType().fancyCollision) {
            destinationPoint = this.getDriveableType().collisionBox.iterator();

            while(destinationPoint.hasNext()) {
               CollisionShapeBox newBasePoint = (CollisionShapeBox)destinationPoint.next();
               this.checkCollision(tester, newBasePoint);
            }
         } else {
            destinationPoint = this.getDriveableData().parts.values().iterator();

            while(destinationPoint.hasNext()) {
               DriveablePart newBasePoint1 = (DriveablePart)destinationPoint.next();
               newBasePoint1.rayTraceRider(this, tester);
            }
         }

         if(tester.didCollide = false) {
            return Vector3f.add(Pos, vel, (Vector3f)null);
         } else {
            Vector3f destinationPoint1 = Vector3f.add(Pos, vel, (Vector3f)null);
            Vector3f newBasePoint2 = Pos;
            if(tester.nearestDistance >= (double)veryCloseDistance) {
               vel.normalise();
               vel.scale((float)(tester.nearestDistance - (double)veryCloseDistance));
               newBasePoint2 = Vector3f.add(tester.basePoint, vel, (Vector3f)null);
               if(vel.normalise() == new Vector3f(0.0F, 0.0F, 0.0F)) {
                  return Vector3f.add(Pos, vel, (Vector3f)null);
               }

               vel.normalise();
               Vector3f.sub(tester.intersectionPoint, new Vector3f(vel.x * veryCloseDistance, vel.y * veryCloseDistance, vel.z * veryCloseDistance), tester.intersectionPoint);
            }

            Vector3f slidePlaneOrigin = tester.intersectionPoint;
            if(tester.intersectionPoint == null) {
               return Vector3f.add(Pos, vel, (Vector3f)null);
            } else {
               Vector3f slidePlaneNormal = Vector3f.sub(newBasePoint2, tester.intersectionPoint, (Vector3f)null);
               slidePlaneNormal.normalise();
               tester.collisionPlaneNormal = slidePlaneNormal;
               CollisionPlane plane = new CollisionPlane(slidePlaneOrigin, slidePlaneNormal);
               double sDV = plane.signedDistanceTo(destinationPoint1);
               Vector3f scaledNormal = new Vector3f((double)slidePlaneNormal.x * sDV, (double)slidePlaneNormal.y * sDV, (double)slidePlaneNormal.z * sDV);
               Vector3f newDestPoint = Vector3f.sub(destinationPoint1, scaledNormal, (Vector3f)null);
               Vector3f newVelocityVector = Vector3f.sub(newDestPoint, tester.intersectionPoint, (Vector3f)null);
               if(newVelocityVector.length() < veryCloseDistance) {
                  return newBasePoint2;
               } else {
                  ++tester.collisionRecursiveDepth;
                  return this.collideWithDriveable(tester, newBasePoint2, newVelocityVector);
               }
            }
         }
      }
   }

   public void updateRiderPos(Entity rider, CollisionTest test, Vector3f pos, Vector3f motion) {
      boolean isDriveable = false;
      if(rider instanceof EntityDriveable) {
         isDriveable = true;
      }

      Vector3f vehicleMotion = this.lastPos;
      Vector3f riderMountPoint = new Vector3f(rider.posX - super.posX, rider.posY - super.posY, rider.posZ - super.posZ);
      float yawDiff = this.axes.getYaw() - this.prevAxes.getYaw();
      float pitchDiff = this.axes.getPitch() - this.prevAxes.getPitch();
      float rollDiff = this.axes.getRoll() - this.prevAxes.getRoll();
      RotatedAxes velAxes = new RotatedAxes(this.axes.getYaw() + yawDiff, this.axes.getPitch() + pitchDiff, this.axes.getRoll() + rollDiff);
      Vector3f currentLocalPos = this.axes.findGlobalVectorLocally(riderMountPoint);
      Vector3f nextGlobalPos = velAxes.findLocalVectorGlobally(currentLocalPos);
      Vector3f diff = new Vector3f(0.0F, 0.0F, 0.0F);
      if(nextGlobalPos == null) {
         nextGlobalPos = new Vector3f(0.0F, 0.0F, 0.0F);
      }

      if(diff == null) {
         diff = new Vector3f(0.0F, 0.0F, 0.0F);
      }

      Vector3f.add(vehicleMotion, diff, diff);
      rider.setPosition((double)nextGlobalPos.x + super.posX + (this.hugeBoat?(double)diff.x / 1.5D:0.0D), !isDriveable?rider.posY:((EntityDriveable)rider).deckHeight, (double)nextGlobalPos.z + super.posZ + (this.hugeBoat?(double)diff.z / 1.5D:0.0D));
      if(this.hugeBoat) {
         if(this.lastPos.x == 0.0F && this.lastPos.y == 0.0F && this.lastPos.z == 0.0F) {
            rider.motionX = rider.motionX;
            rider.motionY = rider.motionY;
            rider.motionZ = rider.motionZ;
            if(rider.motionY < 0.0D) {
               rider.motionY = 0.0D;
            }
         }
      } else if(this.lastPos.x == 0.0F && this.lastPos.y == 0.0F && this.lastPos.z == 0.0F) {
         rider.motionX = rider.motionX;
         rider.motionY = rider.motionY;
         rider.motionZ = rider.motionZ;
      } else {
         rider.motionX = (double)diff.x;
         rider.motionY = (double)diff.y;
         rider.motionZ = (double)diff.z;
      }

   }

   public void handleVehicleCollision(EntityDriveable collided, CollisionTest test, Vector3f finalPos, boolean hugeBoat) {}

   public ArrayList attackFromBullet(Vector3f origin, Vector3f motion) {
      ArrayList hits = new ArrayList();
      Vector3f relativePosVector = Vector3f.sub(origin, new Vector3f((float)super.posX, (float)super.posY, (float)super.posZ), (Vector3f)null);
      Vector3f rotatedPosVector = this.axes.findGlobalVectorLocally(relativePosVector);
      Vector3f rotatedMotVector = this.axes.findGlobalVectorLocally(motion);
      Iterator var7 = this.getDriveableData().parts.values().iterator();

      while(var7.hasNext()) {
         DriveablePart part = (DriveablePart)var7.next();
         DriveableHit hit = part.rayTrace(this, rotatedPosVector, rotatedMotVector);
         if(hit != null) {
            hits.add(hit);
         }
      }

      return hits;
   }

   public ArrayList attackFromBulletButBetter(Vector3f origin, Vector3f motion, float size) {
      ArrayList hits = new ArrayList();
      new Vector3f(super.posX - (double)this.lastPos.x, super.posY - (double)this.lastPos.y, super.posZ - (double)this.lastPos.z);
      Vector3f vehiclePos = new Vector3f(super.posX, super.posY, super.posZ);
      Vector3f relativePos = Vector3f.sub(origin, vehiclePos, (Vector3f)null);
      this.axes.findGlobalVectorLocally(relativePos);
      this.axes.findGlobalVectorLocally(motion);
      Vector3f ellipsoid = new Vector3f(size, size, size);
      CollisionTest test = new CollisionTest(ellipsoid, new Vector3f(relativePos.x, relativePos.y, relativePos.z), motion);
      test.collisionRecursiveDepth = 0;
      Vector3f eSpacePosition = test.ConvertR3ToESpace(test.R3Position);
      Vector3f eSpaceVelocity = test.velocity;
      Iterator hitPos = this.getDriveableData().parts.values().iterator();

      while(hitPos.hasNext()) {
         DriveablePart intersect2 = (DriveablePart)hitPos.next();
         intersect2.rayTraceRider(this, test);
      }

      if(test.didCollide) {
         Vector3f hitPos1 = new Vector3f(0.0F, 0.0F, 0.0F);
         Vector3f intersect21 = new Vector3f(test.ConvertESpaceToR3(test.intersectionPoint));
         Vector3f.sub(origin, intersect21, hitPos1);
         float f = hitPos1.length() / motion.length();
         DriveableHit hit = new DriveableHit(this, test.part, f);
         hits.add(hit);
      }

      return hits;
   }

   public float bulletHit(EntityBullet bullet, DriveableHit hit, float penetratingPower) {
      DriveablePart part = (DriveablePart)this.getDriveableData().parts.get(hit.part);
      if(bullet != null && hit != null) {
         part.hitByBullet(bullet, hit);
      }

      if(!super.worldObj.isRemote) {
         this.checkParts();
         FlansMod.getPacketHandler().sendToAllAround(new PacketDriveableDamage(this), super.posX, super.posY, super.posZ, 100.0F, super.dimension);
      }

      return penetratingPower - 5.0F;
   }

   public DriveablePart raytraceParts(Vector3f origin, Vector3f motion) {
      Vector3f relativePosVector = Vector3f.sub(origin, new Vector3f((float)super.posX, (float)super.posY, (float)super.posZ), (Vector3f)null);
      Vector3f rotatedPosVector = this.axes.findGlobalVectorLocally(relativePosVector);
      Vector3f rotatedMotVector = this.axes.findGlobalVectorLocally(motion);
      Iterator var6 = this.getDriveableData().parts.values().iterator();

      DriveablePart part;
      do {
         if(!var6.hasNext()) {
            return null;
         }

         part = (DriveablePart)var6.next();
      } while(part.rayTrace(this, rotatedPosVector, rotatedMotVector) == null);

      return part;
   }

   public boolean canHitPart(EnumDriveablePart part) {
      return true;
   }

   public void checkParts() {
      Iterator seatNum = this.getDriveableData().parts.values().iterator();

      while(seatNum.hasNext()) {
         DriveablePart type = (DriveablePart)seatNum.next();
         if(type != null && !type.dead && type.health <= 0 && type.maxHealth > 0) {
            this.killPart(type);
         }
      }

      EntitySeat[] var5 = this.seats;
      int var7 = var5.length;

      int i;
      for(i = 0; i < var7; ++i) {
         EntitySeat var10000 = var5[i];
      }

      if(((DriveablePart)this.getDriveableData().parts.get(EnumDriveablePart.core)).dead) {
         int var6 = this.seats.length;
         DriveableType var8 = this.getDriveableType();
         if(!super.worldObj.isRemote) {
            for(i = 0; i < var6; ++i) {
               if(this.seats[i].riddenByEntity != null && this.seats[i].riddenByEntity instanceof EntityPlayer) {
                  Entity part = this.seats[i].riddenByEntity;
                  this.seats[i].riddenByEntity.mountEntity((Entity)null);
                  if(this.lastAtkEntity instanceof EntityPlayer) {
                     part.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)this.lastAtkEntity), 1.0E7F);
                  } else if(this.lastAtkEntity instanceof EntityLivingBase) {
                     part.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this.lastAtkEntity), 1.0E7F);
                  }
               }
            }

            if(var8.isExplosionWhenDestroyed) {
               super.worldObj.createExplosion(this, super.posX, super.posY, super.posZ, 4.0F, false);
            }

            Iterator var9 = this.driveableData.parts.values().iterator();

            while(var9.hasNext()) {
               DriveablePart var10 = (DriveablePart)var9.next();
               if(var10.health > 0 && !var10.dead) {
                  this.killPart(var10);
               }
            }
         }

         this.setDead();
      }

   }

   public void checkPartsWhenAttacked() {
      Iterator var1 = this.getDriveableData().parts.values().iterator();

      while(var1.hasNext()) {
         DriveablePart part = (DriveablePart)var1.next();
         if(part != null && !part.dead && part.health <= 0 && part.maxHealth > 0) {
            this.killPart(part);
         }
      }

   }

   private void killPart(DriveablePart part) {
      if(!part.dead) {
         part.health = 0;
         part.dead = true;
         DriveableType type = this.getDriveableType();
         int var9;
         if(!super.worldObj.isRemote) {
            Vector3f pos = new Vector3f(0.0F, 0.0F, 0.0F);
            if(part.box != null) {
               pos = this.axes.findLocalVectorGlobally(new Vector3f(part.box.x / 16.0F + part.box.w / 32.0F, part.box.y / 16.0F + part.box.h / 32.0F, part.box.z / 16.0F + part.box.d / 32.0F));
            }

            ArrayList drops = type.getItemsRequired(part, this.getDriveableData().engine);
            ItemStack child;
            if(drops != null) {
               Iterator i = drops.iterator();

               while(i.hasNext()) {
                  child = (ItemStack)i.next();
                  super.worldObj.spawnEntityInWorld(new EntityItem(super.worldObj, super.posX + (double)pos.x, super.posY + (double)pos.y, super.posZ + (double)pos.z, child.copy()));
               }
            }

            this.dropItemsOnPartDeath(pos, part);
            if(part.type == EnumDriveablePart.core) {
               for(var9 = 0; var9 < this.getDriveableData().getSizeInventory(); ++var9) {
                  child = this.getDriveableData().getStackInSlot(var9);
                  if(child != null) {
                     super.worldObj.spawnEntityInWorld(new EntityItem(super.worldObj, super.posX + super.rand.nextGaussian(), super.posY + super.rand.nextGaussian(), super.posZ + super.rand.nextGaussian(), child));
                  }
               }
            }
         }

         EnumDriveablePart[] var7 = part.type.getChildren();
         int var8 = var7.length;

         for(var9 = 0; var9 < var8; ++var9) {
            EnumDriveablePart var10 = var7[var9];
            this.killPart((DriveablePart)this.getDriveableData().parts.get(var10));
         }

      }
   }

   protected abstract void dropItemsOnPartDeath(Vector3f var1, DriveablePart var2);

   public float getPlayerRoll() {
      return this.axes.getRoll();
   }

   public void explode() {}

   public float getCameraDistance() {
      return this.getDriveableType().cameraDistance;
   }

   public boolean isPartIntact(EnumDriveablePart part) {
      DriveablePart thisPart = (DriveablePart)this.getDriveableData().parts.get(part);
      return thisPart.maxHealth == 0 || thisPart.health > 0;
   }

   public abstract boolean hasMouseControlMode();

   public abstract String getBombInventoryName();

   public abstract String getMissileInventoryName();

   public boolean rotateWithTurret(Seat seat) {
      return seat.part == EnumDriveablePart.turret;
   }

   public String getCommandSenderName() {
      return this.getDriveableType().name;
   }

   @SideOnly(Side.CLIENT)
   public boolean showInventory(int seat) {
      return seat != 0 || !FlansModClient.controlModeMouse;
   }

   public int getShootDelay(boolean secondary) {
      return secondary?this.shootDelaySecondary:this.shootDelayPrimary;
   }

   public boolean canLaunchIT1() {
      return this.canFireIT1;
   }

   public float getMinigunSpeed(boolean secondary) {
      return secondary?this.minigunSpeedSecondary:this.minigunSpeedPrimary;
   }

   public int getCurrentGun(boolean secondary) {
      return secondary?this.currentGunSecondary:this.currentGunPrimary;
   }

   public void setShootDelay(int i, boolean secondary) {
      this.setRecoilTimer();
      if(secondary) {
         this.shootDelaySecondary = i > this.shootDelaySecondary?i:this.shootDelaySecondary;
      } else {
         this.shootDelayPrimary = i > this.shootDelayPrimary?i:this.shootDelayPrimary;
      }

   }

   public void setMinigunSpeed(float f, boolean secondary) {
      if(secondary) {
         this.minigunSpeedSecondary = f;
      } else {
         this.minigunSpeedPrimary = f;
      }

   }

   public void setCurrentGun(int i, boolean secondary) {
      if(secondary) {
         this.currentGunSecondary = i;
      } else {
         this.currentGunPrimary = i;
      }

   }

   public void setEntityMarker(int tick) {
      this.isShowedPosition = true;
      this.tickCount = tick;
   }

   public void lock(String tool, EntityPlayer player) {
      if(this.key == "ChangeMe") {
         this.key = tool;
         player.addChatMessage(new ChatComponentText("Registered key"));
      } else if(tool == this.key) {
         this.locked = true;
         player.addChatMessage(new ChatComponentText("Locked"));
      } else {
         player.addChatMessage(new ChatComponentText(this.key));
      }

   }

   public void IT1Reload() {
      DriveableType type = this.getDriveableType();
      if(this.stage == 1) {
         this.drakonDoorAngle = this.moveToTarget(this.drakonDoorAngle, 0.0F, 5.0F);
         this.drakonArmAngle = this.moveToTarget(this.drakonArmAngle, 0.0F, 3.0F);
         this.drakonRailAngle = this.moveToTarget(this.drakonRailAngle, -10.0F, 5.0F);
         if(this.drakonRailAngle == -10.0F) {
            ++this.stage;
         }
      }

      if(this.stage == 2) {
         this.drakonDoorAngle = this.moveToTarget(this.drakonDoorAngle, -90.0F, 5.0F);
         this.drakonArmAngle = this.moveToTarget(this.drakonArmAngle, 0.0F, 3.0F);
         this.drakonRailAngle = this.moveToTarget(this.drakonRailAngle, -10.0F, 1.0F);
         if(this.drakonDoorAngle == -90.0F) {
            ++this.stage;
         }
      }

      if(this.stage == 3) {
         this.drakonDoorAngle = this.moveToTarget(this.drakonDoorAngle, -90.0F, 5.0F);
         this.drakonArmAngle = this.moveToTarget(this.drakonArmAngle, 179.0F, 3.0F);
         this.drakonRailAngle = this.moveToTarget(this.drakonRailAngle, -10.0F, 1.0F);
         if(this.drakonArmAngle == 179.0F) {
            ++this.stage;
         }
      }

      if(this.stage == 4) {
         this.drakonDoorAngle = this.moveToTarget(this.drakonDoorAngle, 0.0F, 10.0F);
         this.drakonArmAngle = this.moveToTarget(this.drakonArmAngle, 180.0F, 3.0F);
         this.drakonRailAngle = this.moveToTarget(this.drakonRailAngle, -10.0F, 1.0F);
         if(this.drakonDoorAngle == 0.0F && this.IT1Loaded()) {
            ++this.stage;
            this.reloadAnimTime = 60;
         }
      }

      if(this.stage == 5) {
         this.drakonDoorAngle = this.moveToTarget(this.drakonDoorAngle, -90.0F, 10.0F);
         this.drakonArmAngle = this.moveToTarget(this.drakonArmAngle, 180.0F, 3.0F);
         this.drakonRailAngle = this.moveToTarget(this.drakonRailAngle, -10.0F, 1.0F);
         this.reloadingDrakon = true;
         if(this.drakonDoorAngle == -90.0F) {
            ++this.stage;
         }
      }

      if(this.stage == 6) {
         this.drakonDoorAngle = this.moveToTarget(this.drakonDoorAngle, -90.0F, 5.0F);
         this.drakonArmAngle = this.moveToTarget(this.drakonArmAngle, 0.0F, 3.0F);
         this.drakonRailAngle = this.moveToTarget(this.drakonRailAngle, -10.0F, 1.0F);
         if(this.drakonArmAngle == 0.0F) {
            ++this.stage;
         }
      }

      if(this.stage == 7) {
         this.drakonDoorAngle = this.moveToTarget(this.drakonDoorAngle, 0.0F, 10.0F);
         this.drakonArmAngle = this.moveToTarget(this.drakonArmAngle, 0.0F, 3.0F);
         this.drakonRailAngle = this.moveToTarget(this.drakonRailAngle, 0.0F, 1.0F);
         if(this.drakonRailAngle == 0.0F && this.drakonDoorAngle == 0.0F) {
            ++this.stage;
            this.canFireIT1 = true;
            this.reloadingDrakon = false;
         }
      }

      if(this.stage == 8) {
         this.drakonDoorAngle = this.moveToTarget(this.drakonDoorAngle, 0.0F, 10.0F);
         this.drakonArmAngle = this.moveToTarget(this.drakonArmAngle, 0.0F, 3.0F);
         if(super.worldObj.isRemote && super.ticksExisted > 2) {
            this.drakonRailAngle = this.moveToTarget(this.drakonRailAngle, -this.seats[0].looking.getPitch(), this.seats[0].seatInfo.aimingSpeed.y);
         }

         if(!this.IT1Loaded()) {
            this.stage = 1;
            this.canFireIT1 = false;
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

   public boolean IT1Loaded() {
      DriveableType type = this.getDriveableType();
      boolean loaded = false;

      for(int i = this.driveableData.getMissileInventoryStart(); i < this.driveableData.getMissileInventoryStart() + type.numMissileSlots; ++i) {
         ItemStack shell = this.driveableData.getStackInSlot(i);
         if(shell != null && shell.getItem() instanceof ItemBullet && type.isValidAmmo(((ItemBullet)shell.getItem()).type, EnumWeaponType.MISSILE)) {
            loaded = true;
         }
      }

      return loaded;
   }

   public void tryRecoil() {
      int slot = -1;
      DriveableType type = this.getDriveableType();

      for(int i = this.driveableData.getMissileInventoryStart(); i < this.driveableData.getMissileInventoryStart() + type.numMissileSlots; ++i) {
         ItemStack shell = this.driveableData.getStackInSlot(i);
         if(shell != null && shell.getItem() instanceof ItemBullet && type.isValidAmmo(((ItemBullet)shell.getItem()).type, EnumWeaponType.SHELL)) {
            slot = i;
         }
      }

      if(this.recoilTimer <= 0 && slot != -1) {
         this.isRecoil = true;
      }

   }

   public void setRecoilTimer() {
      int slot = -1;
      DriveableType type = this.getDriveableType();

      for(int i = this.driveableData.getMissileInventoryStart(); i < this.driveableData.getMissileInventoryStart() + type.numMissileSlots; ++i) {
         ItemStack shell = this.driveableData.getStackInSlot(i);
         if(shell != null && shell.getItem() instanceof ItemBullet && type.isValidAmmo(((ItemBullet)shell.getItem()).type, EnumWeaponType.SHELL)) {
            slot = i;
         }
      }

      if(this.recoilTimer <= 0 && slot != -1) {
         this.recoilTimer = this.getDriveableType().shootDelayPrimary;
      }

   }

   public void unlock(String tool, EntityPlayer player) {
      if(this.key == "ChangeMe") {
         this.key = tool;
         player.addChatMessage(new ChatComponentText("Registered key"));
      } else if(tool == this.key) {
         this.locked = false;
         player.addChatMessage(new ChatComponentText("Unlocked"));
      } else {
         player.addChatMessage(new ChatComponentText(this.key));
      }

   }
}
