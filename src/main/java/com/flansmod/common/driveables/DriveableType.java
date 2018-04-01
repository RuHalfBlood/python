package com.flansmod.common.driveables;

import com.flansmod.client.model.ModelDriveable;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.CollisionBox;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.DriveablePosition;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.EnumWeaponType;
import com.flansmod.common.driveables.PilotGun;
import com.flansmod.common.driveables.Seat;
import com.flansmod.common.driveables.ShootPoint;
import com.flansmod.common.driveables.collisions.CollisionShapeBox;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.EnumFireMode;
import com.flansmod.common.paintjob.PaintableType;
import com.flansmod.common.parts.PartType;
import com.flansmod.common.types.TypeFile;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

public class DriveableType extends PaintableType {

   @SideOnly(Side.CLIENT)
   public ModelDriveable model;
   public HashMap health = new HashMap();
   public HashMap partwiseRecipe = new HashMap();
   public ArrayList driveableRecipe = new ArrayList();
   public boolean acceptAllAmmo = true;
   public List ammo = new ArrayList();
   public boolean harvestBlocks = false;
   public ArrayList materialsHarvested = new ArrayList();
   public boolean collectHarvest = false;
   public boolean dropHarvest = false;
   public Vector3f harvestBoxSize = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f harvestBoxPos = new Vector3f(0.0F, 0.0F, 0.0F);
   public float fallDamageFactor = 1.0F;
   public EnumWeaponType primary;
   public EnumWeaponType secondary;
   public boolean alternatePrimary;
   public boolean alternateSecondary;
   public int shootDelayPrimary;
   public int shootDelaySecondary;
   public EnumFireMode modePrimary;
   public EnumFireMode modeSecondary;
   public String shootSoundPrimary;
   public String shootSoundSecondary;
   public ArrayList shootPointsPrimary;
   public ArrayList shootPointsSecondary;
   public ArrayList pilotGuns;
   public int reloadTimePrimary;
   public int reloadTimeSecondary;
   public String reloadSoundPrimary;
   public String reloadSoundSecondary;
   public int placeTimePrimary;
   public int placeTimeSecondary;
   public String placeSoundPrimary;
   public String placeSoundSecondary;
   public int numPassengers;
   public Seat[] seats;
   public int numPassengerGunners;
   public ArrayList shootParticlesPrimary;
   public ArrayList shootParticlesSecondary;
   public int numCargoSlots;
   public int numBombSlots;
   public int numMissileSlots;
   public int fuelTankSize;
   public float yOffset;
   public float cameraDistance;
   public ArrayList emitters;
   public float maxThrottle;
   public float maxNegativeThrottle;
   public Vector3f turretOrigin;
   public Vector3f turretOriginOffset;
   public DriveablePosition[] wheelPositions;
   public float wheelSpringStrength;
   public float wheelStepHeight;
   public boolean canRoll;
   public ArrayList collisionPoints;
   public float drag;
   public boolean floatOnWater;
   public boolean placeableOnLand;
   public boolean placeableOnWater;
   public boolean placeableOnSponge;
   public float buoyancy;
   public float floatOffset;
   public float bulletDetectionRadius;
   public boolean onRadar;
   public int animFrames;
   public int startSoundRange;
   public String startSound;
   public int startSoundLength;
   public int engineSoundRange;
   public String engineSound;
   public int engineSoundLength;
   public int backSoundRange;
   public String idleSound;
   public int idleSoundLength;
   public String backSound;
   public int backSoundLength;
   public boolean collisionDamageEnable;
   public boolean pushOnCollision;
   public float collisionDamageThrottle;
   public float collisionDamageTimes;
   public boolean enableReloadTime;
   public boolean canMountEntity;
   public float bulletSpread;
   public float bulletSpeed;
   public boolean rangingGun;
   public boolean isExplosionWhenDestroyed;
   public String lockedOnSound;
   public int soundTime;
   public int canLockOnAngle;
   public int lockOnSoundTime;
   public String lockOnSound;
   public int maxRangeLockOn;
   public int lockedOnSoundRange;
   public String lockingOnSound;
   public boolean lockOnToPlanes;
   public boolean lockOnToVehicles;
   public boolean lockOnToMechas;
   public boolean lockOnToPlayers;
   public boolean lockOnToLivings;
   public boolean hasFlare;
   public int flareDelay;
   public String flareSound;
   public int timeFlareUsing;
   public int radarDetectableAltitude;
   public boolean stealth;
   public float recoilDist;
   public float recoilTime;
   public boolean fixedPrimaryFire;
   public Vector3f primaryFireAngle;
   public float gunLength;
   public boolean setPlayerInvisible;
   public float maxThrottleInWater;
   public int maxDepth;
   public ArrayList leftTrackPoints;
   public ArrayList rightTrackPoints;
   public float trackLinkLength;
   public boolean IT1;
   public static ArrayList types = new ArrayList();
   public ArrayList collisionBox;
   public boolean fancyCollision;
   public CollisionShapeBox colbox;


   public DriveableType(TypeFile file) {
      super(file);
      this.primary = EnumWeaponType.NONE;
      this.secondary = EnumWeaponType.NONE;
      this.alternatePrimary = false;
      this.alternateSecondary = false;
      this.shootDelayPrimary = 1;
      this.shootDelaySecondary = 1;
      this.modePrimary = EnumFireMode.FULLAUTO;
      this.modeSecondary = EnumFireMode.FULLAUTO;
      this.shootPointsPrimary = new ArrayList();
      this.shootPointsSecondary = new ArrayList();
      this.pilotGuns = new ArrayList();
      this.reloadTimePrimary = 0;
      this.reloadTimeSecondary = 0;
      this.reloadSoundPrimary = "";
      this.reloadSoundSecondary = "";
      this.placeTimePrimary = 5;
      this.placeTimeSecondary = 5;
      this.placeSoundPrimary = "";
      this.placeSoundSecondary = "";
      this.numPassengers = 0;
      this.numPassengerGunners = 0;
      this.shootParticlesPrimary = new ArrayList();
      this.shootParticlesSecondary = new ArrayList();
      this.fuelTankSize = 100;
      this.yOffset = 0.625F;
      this.cameraDistance = 5.0F;
      this.emitters = new ArrayList();
      this.maxThrottle = 1.0F;
      this.maxNegativeThrottle = 0.0F;
      this.turretOrigin = new Vector3f();
      this.turretOriginOffset = new Vector3f();
      this.wheelPositions = new DriveablePosition[0];
      this.wheelSpringStrength = 0.5F;
      this.wheelStepHeight = 1.0F;
      this.canRoll = true;
      this.collisionPoints = new ArrayList();
      this.drag = 1.0F;
      this.floatOnWater = false;
      this.placeableOnLand = true;
      this.placeableOnWater = false;
      this.placeableOnSponge = false;
      this.buoyancy = 0.0165F;
      this.floatOffset = 0.0F;
      this.bulletDetectionRadius = 5.0F;
      this.onRadar = false;
      this.animFrames = 2;
      this.startSoundRange = 50;
      this.startSound = "";
      this.engineSoundRange = 50;
      this.engineSound = "";
      this.backSoundRange = 50;
      this.idleSound = "";
      this.idleSoundLength = 50;
      this.backSound = "";
      this.collisionDamageEnable = false;
      this.pushOnCollision = true;
      this.collisionDamageThrottle = 0.0F;
      this.collisionDamageTimes = 0.0F;
      this.enableReloadTime = false;
      this.canMountEntity = false;
      this.bulletSpread = 0.0F;
      this.bulletSpeed = 3.0F;
      this.rangingGun = false;
      this.isExplosionWhenDestroyed = false;
      this.lockedOnSound = "";
      this.soundTime = 0;
      this.canLockOnAngle = 10;
      this.lockOnSoundTime = 60;
      this.lockOnSound = "";
      this.maxRangeLockOn = 500;
      this.lockedOnSoundRange = 5;
      this.lockingOnSound = "";
      this.lockOnToPlanes = false;
      this.lockOnToVehicles = false;
      this.lockOnToMechas = false;
      this.lockOnToPlayers = false;
      this.lockOnToLivings = false;
      this.hasFlare = false;
      this.flareDelay = 200;
      this.flareSound = "";
      this.timeFlareUsing = 1;
      this.radarDetectableAltitude = -1;
      this.stealth = false;
      this.recoilDist = 5.0F;
      this.recoilTime = 5.0F;
      this.fixedPrimaryFire = false;
      this.primaryFireAngle = new Vector3f(0.0F, 0.0F, 0.0F);
      this.gunLength = 0.0F;
      this.setPlayerInvisible = false;
      this.maxThrottleInWater = 0.5F;
      this.maxDepth = 3;
      this.leftTrackPoints = new ArrayList();
      this.rightTrackPoints = new ArrayList();
      this.trackLinkLength = 0.0F;
      this.IT1 = false;
      this.collisionBox = new ArrayList();
      this.fancyCollision = false;
   }

   public void preRead(TypeFile file) {
      super.preRead(file);
      Iterator var2 = file.lines.iterator();

      String line;
      String[] split;
      while(var2.hasNext()) {
         line = (String)var2.next();
         if(line == null) {
            break;
         }

         if(!line.startsWith("//")) {
            split = line.split(" ");
            if(split.length >= 2 && split[0].equals("Passengers")) {
               this.numPassengers = Integer.parseInt(split[1]);
               this.seats = new Seat[this.numPassengers + 1];
               break;
            }
         }
      }

      var2 = file.lines.iterator();

      while(var2.hasNext()) {
         line = (String)var2.next();
         if(line == null) {
            break;
         }

         if(!line.startsWith("//")) {
            split = line.split(" ");
            if(split.length >= 2 && split[0].equals("NumWheels")) {
               this.wheelPositions = new DriveablePosition[Integer.parseInt(split[1])];
               break;
            }
         }
      }

      types.add(this);
   }

   public void postRead(TypeFile file) {
      super.postRead(file);
   }

   protected void read(String[] split, TypeFile file) {
      super.read(split, file);

      String[] p1;
      int p2;
      String s;
      try {
         if(FMLCommonHandler.instance().getSide().isClient() && split[0].equals("Model")) {
            this.model = (ModelDriveable)FlansMod.proxy.loadModel(split[1], super.shortName, ModelDriveable.class);
         } else if(split[0].equals("Texture")) {
            super.texture = split[1];
         } else if(split[0].equals("IsExplosionWhenDestroyed")) {
            this.isExplosionWhenDestroyed = Boolean.parseBoolean(split[1]);
         } else if(split[0].equals("FallDamageFactor")) {
            this.fallDamageFactor = Float.parseFloat(split[1]);
         } else if(split[0].equals("MaxThrottle")) {
            this.maxThrottle = Float.parseFloat(split[1]);
         } else if(split[0].equals("MaxNegativeThrottle")) {
            this.maxNegativeThrottle = Float.parseFloat(split[1]);
         } else if(split[0].equals("MaxThrottleInWater")) {
            this.maxThrottleInWater = Float.parseFloat(split[1]);
         } else if(split[0].equals("MaxDepth")) {
            this.maxDepth = Integer.parseInt(split[1]);
         } else if(split[0].equals("Drag")) {
            this.drag = Float.parseFloat(split[1]);
         } else if(split[0].equals("TurretOrigin")) {
            this.turretOrigin = new Vector3f(Float.parseFloat(split[1]) / 16.0F, Float.parseFloat(split[2]) / 16.0F, Float.parseFloat(split[3]) / 16.0F);
         } else if(split[0].equals("TurretOriginOffset")) {
            this.turretOriginOffset = new Vector3f(Float.parseFloat(split[1]) / 16.0F, Float.parseFloat(split[2]) / 16.0F, Float.parseFloat(split[3]) / 16.0F);
         } else if(!split[0].equals("CollisionPoint") && !split[0].equals("AddCollisionPoint")) {
            if(split[0].equals("CollisionDamageEnable")) {
               this.collisionDamageEnable = Boolean.parseBoolean(split[1]);
            } else if(split[0].equals("CollisionDamageThrottle")) {
               this.collisionDamageThrottle = Float.parseFloat(split[1]);
            } else if(split[0].equals("CollisionDamageTimes")) {
               this.collisionDamageTimes = Float.parseFloat(split[1]);
            } else if(split[0].equals("CanLockAngle")) {
               this.canLockOnAngle = Integer.parseInt(split[1]);
            } else if(split[0].equals("LockOnSoundTime")) {
               this.lockOnSoundTime = Integer.parseInt(split[1]);
            } else if(split[0].equals("LockOnToDriveables")) {
               this.lockOnToPlanes = this.lockOnToVehicles = this.lockOnToMechas = Boolean.parseBoolean(split[1].toLowerCase());
            } else if(split[0].equals("LockOnToVehicles")) {
               this.lockOnToVehicles = Boolean.parseBoolean(split[1].toLowerCase());
            } else if(split[0].equals("LockOnToPlanes")) {
               this.lockOnToPlanes = Boolean.parseBoolean(split[1].toLowerCase());
            } else if(split[0].equals("LockOnToMechas")) {
               this.lockOnToMechas = Boolean.parseBoolean(split[1].toLowerCase());
            } else if(split[0].equals("LockOnToPlayers")) {
               this.lockOnToPlayers = Boolean.parseBoolean(split[1].toLowerCase());
            } else if(split[0].equals("LockOnToLivings")) {
               this.lockOnToLivings = Boolean.parseBoolean(split[1].toLowerCase());
            } else if(split[0].equals("LockedOnSoundRange")) {
               this.lockedOnSoundRange = Integer.parseInt(split[1]);
            } else if(split[0].equals("CanRoll")) {
               this.canRoll = Boolean.parseBoolean(split[1]);
            }
         } else {
            this.collisionPoints.add(new DriveablePosition(split));
         }

         if(split[0].equals("HasFlare")) {
            this.hasFlare = Boolean.parseBoolean(split[1]);
         }

         if(split[0].equals("FlareDelay")) {
            this.flareDelay = Integer.parseInt(split[1]);
            if(this.flareDelay <= 0) {
               this.flareDelay = 1;
            }
         }

         Vector3f var17;
         ShootPoint var22;
         if(split[0].equals("TimeFlareUsing")) {
            this.timeFlareUsing = Integer.parseInt(split[1]);
            if(this.timeFlareUsing <= 0) {
               this.timeFlareUsing = 1;
            }
         } else if(split[0].equals("PlaceableOnLand")) {
            this.placeableOnLand = Boolean.parseBoolean(split[1]);
         } else if(split[0].equals("PlaceableOnWater")) {
            this.placeableOnWater = Boolean.parseBoolean(split[1]);
         } else if(split[0].equals("PlaceableOnSponge")) {
            this.placeableOnSponge = Boolean.parseBoolean(split[1]);
         } else if(split[0].equals("FloatOnWater")) {
            this.floatOnWater = Boolean.parseBoolean(split[1]);
         } else if(split[0].equals("Boat")) {
            this.placeableOnLand = false;
            this.placeableOnWater = true;
            this.floatOnWater = true;
            this.wheelStepHeight = 0.0F;
         } else if(split[0].equals("Buoyancy")) {
            this.buoyancy = Float.parseFloat(split[1]);
         } else if(split[0].equals("FloatOffset")) {
            this.floatOffset = Float.parseFloat(split[1]);
         } else if(split[0].equals("CanMountEntity")) {
            this.canMountEntity = Boolean.parseBoolean(split[1]);
         } else if(!split[0].equals("Wheel") && !split[0].equals("WheelPosition")) {
            if(!split[0].equals("WheelRadius") && !split[0].equals("WheelStepHeight")) {
               if(!split[0].equals("WheelSpringStrength") && !split[0].equals("SpringStrength")) {
                  if(split[0].equals("TrackFrames")) {
                     this.animFrames = Integer.parseInt(split[1]) - 1;
                  } else if(split[0].equals("Harvester")) {
                     this.harvestBlocks = Boolean.parseBoolean(split[1]);
                  } else if(split[0].equals("CollectHarvest")) {
                     this.collectHarvest = Boolean.parseBoolean(split[1]);
                  } else if(split[0].equals("DropHarvest")) {
                     this.dropHarvest = Boolean.parseBoolean(split[1]);
                  } else if(split[0].equals("HarvestBox")) {
                     this.harvestBoxSize = new Vector3f(split[1], super.shortName);
                     this.harvestBoxPos = new Vector3f(split[2], super.shortName);
                  } else if(split[0].equals("HarvestMaterial")) {
                     this.materialsHarvested.add(getMaterial(split[1]));
                  } else if(split[0].equals("HarvestToolType")) {
                     if(split[1].equals("Axe")) {
                        this.materialsHarvested.add(Material.wood);
                        this.materialsHarvested.add(Material.plants);
                        this.materialsHarvested.add(Material.vine);
                     } else if(!split[1].equals("Pickaxe") && !split[1].equals("Drill")) {
                        if(!split[1].equals("Spade") && !split[1].equals("Shovel") && !split[1].equals("Excavator")) {
                           if(!split[1].equals("Hoe") && !split[1].equals("Combine")) {
                              if(split[1].equals("Tank")) {
                                 this.materialsHarvested.add(Material.leaves);
                                 this.materialsHarvested.add(Material.cactus);
                                 this.materialsHarvested.add(Material.wood);
                                 this.materialsHarvested.add(Material.plants);
                              }
                           } else {
                              this.materialsHarvested.add(Material.plants);
                              this.materialsHarvested.add(Material.leaves);
                              this.materialsHarvested.add(Material.vine);
                              this.materialsHarvested.add(Material.cactus);
                              this.materialsHarvested.add(Material.gourd);
                           }
                        } else {
                           this.materialsHarvested.add(Material.ground);
                           this.materialsHarvested.add(Material.grass);
                           this.materialsHarvested.add(Material.sand);
                           this.materialsHarvested.add(Material.snow);
                           this.materialsHarvested.add(Material.clay);
                        }
                     } else {
                        this.materialsHarvested.add(Material.iron);
                        this.materialsHarvested.add(Material.anvil);
                        this.materialsHarvested.add(Material.rock);
                     }
                  } else if(split[0].equals("CargoSlots")) {
                     this.numCargoSlots = Integer.parseInt(split[1]);
                  } else if(!split[0].equals("BombSlots") && !split[0].equals("MineSlots")) {
                     if(!split[0].equals("MissileSlots") && !split[0].equals("ShellSlots")) {
                        if(split[0].equals("FuelTankSize")) {
                           this.fuelTankSize = Integer.parseInt(split[1]);
                        } else if(split[0].equals("BulletDetection")) {
                           this.bulletDetectionRadius = (float)Integer.parseInt(split[1]);
                        } else if(split[0].equals("AddAmmo")) {
                           this.ammo.add(BulletType.getBullet(split[1]));
                        } else if(!split[0].equals("AllowAllAmmo") && !split[0].equals("AcceptAllAmmo")) {
                           if(split[0].equals("Primary")) {
                              this.primary = EnumWeaponType.valueOf(split[1].toUpperCase());
                           } else if(split[0].equals("Secondary")) {
                              this.secondary = EnumWeaponType.valueOf(split[1].toUpperCase());
                           } else if(split[0].equals("ShootDelayPrimary")) {
                              this.shootDelayPrimary = Integer.parseInt(split[1]);
                           } else if(split[0].equals("ShootDelaySecondary")) {
                              this.shootDelaySecondary = Integer.parseInt(split[1]);
                           } else if(split[0].equals("PlaceTimePrimary")) {
                              this.placeTimePrimary = Integer.parseInt(split[1]);
                           } else if(split[0].equals("PlaceTimeSecondary")) {
                              this.placeTimeSecondary = Integer.parseInt(split[1]);
                           } else if(split[0].equals("ReloadTimePrimary")) {
                              this.reloadTimePrimary = Integer.parseInt(split[1]);
                           } else if(split[0].equals("ReloadTimeSecondary")) {
                              this.reloadTimeSecondary = Integer.parseInt(split[1]);
                           } else if(split[0].equals("AlternatePrimary")) {
                              this.alternatePrimary = Boolean.parseBoolean(split[1]);
                           } else if(split[0].equals("AlternateSecondary")) {
                              this.alternateSecondary = Boolean.parseBoolean(split[1]);
                           } else if(split[0].equals("ModePrimary")) {
                              this.modePrimary = EnumFireMode.valueOf(split[1].toUpperCase());
                           } else if(split[0].equals("ModeSecondary")) {
                              this.modeSecondary = EnumFireMode.valueOf(split[1].toUpperCase());
                           } else if(split[0].equals("BulletSpeed")) {
                              this.bulletSpeed = Float.parseFloat(split[1]);
                           } else if(split[0].equals("BulletSpread")) {
                              this.bulletSpread = Float.parseFloat(split[1]);
                           } else if(split[0].equals("RangingGun")) {
                              this.rangingGun = Boolean.parseBoolean(split[1]);
                           } else if(split[0].equals("GunLength")) {
                              this.gunLength = Float.parseFloat(split[1]);
                           } else if(split[0].equals("RecoilDistance")) {
                              this.recoilDist = Float.parseFloat(split[1]);
                           } else if(split[0].equals("RecoilTime")) {
                              this.recoilTime = Float.parseFloat(split[1]);
                           } else {
                              DriveablePosition e;
                              if(split[0].equals("ShootPointPrimary")) {
                                 if(split.length == 9) {
                                    p1 = new String[]{split[0], split[1], split[2], split[3], split[4], split[5]};
                                    var17 = new Vector3f(Float.parseFloat(split[6]) / 16.0F, Float.parseFloat(split[7]) / 16.0F, Float.parseFloat(split[8]) / 16.0F);
                                 } else if(split.length == 8) {
                                    p1 = new String[]{split[0], split[1], split[2], split[3], split[4]};
                                    var17 = new Vector3f(Float.parseFloat(split[5]) / 16.0F, Float.parseFloat(split[6]) / 16.0F, Float.parseFloat(split[7]) / 16.0F);
                                 } else {
                                    p1 = split;
                                    var17 = new Vector3f(0.0F, 0.0F, 0.0F);
                                 }

                                 e = this.getShootPoint(p1);
                                 var22 = new ShootPoint(e, var17);
                                 this.shootPointsPrimary.add(var22);
                                 if(e instanceof PilotGun) {
                                    this.pilotGuns.add((PilotGun)var22.rootPos);
                                 }
                              } else if(split[0].equals("ShootPointSecondary")) {
                                 if(split.length == 9) {
                                    p1 = new String[]{split[0], split[1], split[2], split[3], split[4], split[5]};
                                    var17 = new Vector3f(Float.parseFloat(split[6]) / 16.0F, Float.parseFloat(split[7]) / 16.0F, Float.parseFloat(split[8]) / 16.0F);
                                 } else if(split.length == 8) {
                                    p1 = new String[]{split[0], split[1], split[2], split[3], split[4]};
                                    var17 = new Vector3f(Float.parseFloat(split[5]) / 16.0F, Float.parseFloat(split[6]) / 16.0F, Float.parseFloat(split[7]) / 16.0F);
                                 } else {
                                    p1 = split;
                                    var17 = new Vector3f(0.0F, 0.0F, 0.0F);
                                 }

                                 e = this.getShootPoint(p1);
                                 var22 = new ShootPoint(e, var17);
                                 this.shootPointsSecondary.add(var22);
                                 if(e instanceof PilotGun) {
                                    this.pilotGuns.add((PilotGun)var22.rootPos);
                                 }
                              } else if(split[0].equals("EnableReloadTime")) {
                                 this.enableReloadTime = Boolean.parseBoolean(split[1]);
                              } else if(split[0].equals("ShootParticlesPrimary")) {
                                 this.shootParticlesPrimary.add(new DriveableType.ShootParticle(split[1], Float.valueOf(split[2]).floatValue(), Float.valueOf(split[3]).floatValue(), Float.valueOf(split[4]).floatValue()));
                              } else if(split[0].equals("ShootParticlesSecondary")) {
                                 this.shootParticlesSecondary.add(new DriveableType.ShootParticle(split[1], Float.valueOf(split[2]).floatValue(), Float.valueOf(split[3]).floatValue(), Float.valueOf(split[4]).floatValue()));
                              }
                           }
                        } else {
                           this.acceptAllAmmo = Boolean.parseBoolean(split[1]);
                        }
                     } else {
                        this.numMissileSlots = Integer.parseInt(split[1]);
                     }
                  } else {
                     this.numBombSlots = Integer.parseInt(split[1]);
                  }
               } else {
                  this.wheelSpringStrength = Float.parseFloat(split[1]);
               }
            } else {
               this.wheelStepHeight = Float.parseFloat(split[1]);
            }
         } else {
            this.wheelPositions[Integer.parseInt(split[1])] = new DriveablePosition(new Vector3f(Float.parseFloat(split[2]) / 16.0F, Float.parseFloat(split[3]) / 16.0F, Float.parseFloat(split[4]) / 16.0F), split.length > 5?EnumDriveablePart.getPart(split[5]):EnumDriveablePart.coreWheel);
         }

         if(split[0].equals("SetPlayerInvisible")) {
            this.setPlayerInvisible = Boolean.parseBoolean(split[1].toLowerCase());
         }

         if(split[0].equals("IT1")) {
            this.IT1 = Boolean.parseBoolean(split[1].toLowerCase());
         }

         if(split[0].equals("FixedPrimary")) {
            this.fixedPrimaryFire = Boolean.parseBoolean(split[1].toLowerCase());
         }

         if(split[0].equals("PrimaryAngle")) {
            this.primaryFireAngle = new Vector3f(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3]));
         } else if(split[0].equals("AddGun")) {
            this.secondary = EnumWeaponType.GUN;
            PilotGun var15;
            PilotGun var26;
            if(split.length == 6) {
               var15 = (PilotGun)this.getShootPoint(split);
               var17 = new Vector3f(0.0F, 0.0F, 0.0F);
               var26 = (PilotGun)this.getShootPoint(split);
            } else {
               String[] var23 = new String[]{split[0], split[1], split[2], split[3], split[4], split[5]};
               var15 = (PilotGun)this.getShootPoint(var23);
               var26 = (PilotGun)this.getShootPoint(var23);
               var17 = new Vector3f(Float.parseFloat(split[6]) / 16.0F, Float.parseFloat(split[7]) / 16.0F, Float.parseFloat(split[8]) / 16.0F);
            }

            var22 = new ShootPoint(var15, var17);
            this.shootPointsSecondary.add(var22);
            this.pilotGuns.add(var26);
            this.driveableRecipe.add(new ItemStack(var26.type.item));
         } else if(split[0].equals("BombPosition")) {
            this.primary = EnumWeaponType.BOMB;
            if(split.length == 4) {
               this.shootPointsPrimary.add(new ShootPoint(new DriveablePosition(new Vector3f(Float.parseFloat(split[1]) / 16.0F, Float.parseFloat(split[2]) / 16.0F, Float.parseFloat(split[3]) / 16.0F), EnumDriveablePart.core), new Vector3f(0.0F, 0.0F, 0.0F)));
            } else if(split.length == 7) {
               this.shootPointsPrimary.add(new ShootPoint(new DriveablePosition(new Vector3f(Float.parseFloat(split[1]) / 16.0F, Float.parseFloat(split[2]) / 16.0F, Float.parseFloat(split[3]) / 16.0F), EnumDriveablePart.core), new Vector3f(Float.parseFloat(split[4]) / 16.0F, Float.parseFloat(split[5]) / 16.0F, Float.parseFloat(split[6]) / 16.0F)));
            }
         } else if(split[0].equals("BarrelPosition")) {
            this.primary = EnumWeaponType.SHELL;
            if(split.length == 4) {
               this.shootPointsPrimary.add(new ShootPoint(new DriveablePosition(new Vector3f(Float.parseFloat(split[1]) / 16.0F, Float.parseFloat(split[2]) / 16.0F, Float.parseFloat(split[3]) / 16.0F), EnumDriveablePart.turret), new Vector3f(0.0F, 0.0F, 0.0F)));
            } else if(split.length == 7) {
               this.shootPointsPrimary.add(new ShootPoint(new DriveablePosition(new Vector3f(Float.parseFloat(split[1]) / 16.0F, Float.parseFloat(split[2]) / 16.0F, Float.parseFloat(split[3]) / 16.0F), EnumDriveablePart.turret), new Vector3f(Float.parseFloat(split[4]) / 16.0F, Float.parseFloat(split[5]) / 16.0F, Float.parseFloat(split[6]) / 16.0F)));
            }
         } else if(split[0].equals("ShootDelay")) {
            this.shootDelaySecondary = Integer.parseInt(split[1]);
         } else if(!split[0].equals("ShellDelay") && !split[0].equals("BombDelay")) {
            EnumDriveablePart var16;
            int var32;
            if(split[0].equals("AddRecipeParts")) {
               var16 = EnumDriveablePart.getPart(split[1]);
               ItemStack[] var29 = new ItemStack[(split.length - 2) / 2];

               for(var32 = 0; var32 < (split.length - 2) / 2; ++var32) {
                  p2 = Integer.parseInt(split[2 * var32 + 2]);
                  boolean var25 = split[2 * var32 + 3].contains(".");
                  s = var25?split[2 * var32 + 3].split("\\.")[0]:split[2 * var32 + 3];
                  int p5 = var25?Integer.parseInt(split[2 * var32 + 3].split("\\.")[1]):0;
                  var29[var32] = getRecipeElement(s, p2, p5, super.shortName);
                  this.driveableRecipe.add(var29[var32]);
               }

               this.partwiseRecipe.put(var16, var29);
            } else if(split[0].equals("AddDye")) {
               int var18 = Integer.parseInt(split[1]);
               int var31 = -1;

               for(var32 = 0; var32 < ItemDye.field_150923_a.length; ++var32) {
                  if(ItemDye.field_150923_a[var32].equals(split[2])) {
                     var31 = var32;
                  }
               }

               if(var31 == -1) {
                  FlansMod.log("Failed to find dye colour : " + split[2] + " while adding " + file.name);
                  return;
               }

               this.driveableRecipe.add(new ItemStack(Items.dye, var18, var31));
            } else if(split[0].equals("SetupPart")) {
               var16 = EnumDriveablePart.getPart(split[1]);
               CollisionBox var34 = new CollisionBox(Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]), Integer.parseInt(split[7]), Integer.parseInt(split[8]));
               this.health.put(var16, var34);
            } else if(!split[0].equals("Driver") && !split[0].equals("Pilot")) {
               if(split[0].equals("DriverPart")) {
                  this.seats[0].part = EnumDriveablePart.getPart(split[1]);
               } else if(!split[0].equals("DriverGun") && !split[0].equals("PilotGun")) {
                  if(split[0].equals("DriverGunOrigin")) {
                     this.seats[0].gunOrigin = new Vector3f(Float.parseFloat(split[1]) / 16.0F, Float.parseFloat(split[2]) / 16.0F, Float.parseFloat(split[3]) / 16.0F);
                  } else if(split[0].equals("RotatedDriverOffset")) {
                     this.seats[0].rotatedOffset = new Vector3f((float)Integer.parseInt(split[1]) / 16.0F, (float)Integer.parseInt(split[2]) / 16.0F, (float)Integer.parseInt(split[3]) / 16.0F);
                  } else if(split[0].equals("RotatedPassengerOffset")) {
                     this.seats[Integer.parseInt(split[1])].rotatedOffset = new Vector3f((float)Integer.parseInt(split[2]) / 16.0F, (float)Integer.parseInt(split[3]) / 16.0F, (float)Integer.parseInt(split[4]) / 16.0F);
                  } else if(split[0].equals("DriverAimSpeed")) {
                     this.seats[0].aimingSpeed = new Vector3f(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3]));
                  } else if(split[0].equals("PassengerAimSpeed")) {
                     this.seats[Integer.parseInt(split[1])].aimingSpeed = new Vector3f(Float.parseFloat(split[2]), Float.parseFloat(split[3]), Float.parseFloat(split[4]));
                  } else if(split[0].equals("DriverLegacyAiming")) {
                     this.seats[0].legacyAiming = Boolean.parseBoolean(split[1]);
                  } else if(split[0].equals("PassengerLegacyAiming")) {
                     this.seats[Integer.parseInt(split[1])].legacyAiming = Boolean.parseBoolean(split[2]);
                  } else if(split[0].equals("DriverYawBeforePitch")) {
                     this.seats[0].yawBeforePitch = Boolean.parseBoolean(split[1]);
                  } else if(split[0].equals("PassengerYawBeforePitch")) {
                     this.seats[Integer.parseInt(split[1])].yawBeforePitch = Boolean.parseBoolean(split[2]);
                  } else if(split[0].equals("DriverLatePitch")) {
                     this.seats[0].latePitch = Boolean.parseBoolean(split[1]);
                  } else if(split[0].equals("PassengerLatePitch")) {
                     this.seats[Integer.parseInt(split[1])].latePitch = Boolean.parseBoolean(split[2]);
                  } else if(split[0].equals("DriverTraverseSounds")) {
                     this.seats[0].traverseSounds = Boolean.parseBoolean(split[1]);
                  } else if(split[0].equals("PassengerTraverseSounds")) {
                     this.seats[Integer.parseInt(split[1])].traverseSounds = Boolean.parseBoolean(split[2]);
                  } else if(split[0].equals("Passenger")) {
                     Seat var19 = new Seat(split);
                     this.seats[var19.id] = var19;
                     if(var19.gunType != null) {
                        var19.gunnerID = this.numPassengerGunners++;
                        this.driveableRecipe.add(new ItemStack(var19.gunType.item));
                     }
                  } else if(split[0].equals("GunOrigin")) {
                     this.seats[Integer.parseInt(split[1])].gunOrigin = new Vector3f(Float.parseFloat(split[2]) / 16.0F, Float.parseFloat(split[3]) / 16.0F, Float.parseFloat(split[4]) / 16.0F);
                  } else if(split[0].equals("YOffset")) {
                     this.yOffset = Float.parseFloat(split[1]);
                  } else if(split[0].equals("CameraDistance")) {
                     this.cameraDistance = Float.parseFloat(split[1]);
                  } else if(split[0].equals("StartSoundRange")) {
                     this.startSoundRange = Integer.parseInt(split[1]);
                  } else if(split[0].equals("StartSoundLength")) {
                     this.startSoundLength = Integer.parseInt(split[1]);
                  } else if(split[0].equals("EngineSoundRange")) {
                     this.engineSoundRange = Integer.parseInt(split[1]);
                  } else if(split[0].equals("EngineSoundLength")) {
                     this.engineSoundLength = Integer.parseInt(split[1]);
                  } else if(split[0].equals("IdleSoundLength")) {
                     this.idleSoundLength = Integer.parseInt(split[1]);
                  } else if(split[0].equals("BackSoundRange")) {
                     this.backSoundRange = Integer.parseInt(split[1]);
                  } else if(split[0].equals("BackSoundLength")) {
                     this.backSoundLength = Integer.parseInt(split[1]);
                  } else if(split[0].equals("SoundTime")) {
                     this.soundTime = Integer.parseInt(split[1]);
                  } else if(split[0].equals("YawSoundLength")) {
                     this.seats[0].yawSoundLength = Integer.parseInt(split[1]);
                  } else if(split[0].equals("PitchSoundLength")) {
                     this.seats[0].pitchSoundLength = Integer.parseInt(split[1]);
                  } else if(split[0].equals("PassengerYawSoundLength")) {
                     this.seats[Integer.parseInt(split[1])].yawSoundLength = Integer.parseInt(split[2]);
                  } else if(split[0].equals("PassengerPitchSoundLength")) {
                     this.seats[Integer.parseInt(split[1])].pitchSoundLength = Integer.parseInt(split[2]);
                  } else if(split[0].equals("StartSound")) {
                     this.startSound = split[1];
                     FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                  } else if(split[0].equals("EngineSound")) {
                     this.engineSound = split[1];
                     FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                  } else if(split[0].equals("IdleSound")) {
                     this.idleSound = split[1];
                     FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                  } else if(split[0].equals("BackSound")) {
                     this.backSound = split[1];
                     FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                  } else if(split[0].equals("YawSound")) {
                     this.seats[0].yawSound = split[1];
                     FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                  } else if(split[0].equals("PitchSound")) {
                     this.seats[0].pitchSound = split[1];
                     FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                  } else if(split[0].equals("PassengerYawSound")) {
                     this.seats[Integer.parseInt(split[1])].yawSound = split[2];
                     FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                  } else if(split[0].equals("PassengerPitchSound")) {
                     this.seats[Integer.parseInt(split[1])].pitchSound = split[2];
                     FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                  } else if(!split[0].equals("ShootMainSound") && !split[0].equals("ShootSoundPrimary") && !split[0].equals("ShellSound") && !split[0].equals("BombSound")) {
                     if(!split[0].equals("ShootSecondarySound") && !split[0].equals("ShootSoundSecondary")) {
                        if(split[0].equals("PlaceSoundPrimary")) {
                           this.placeSoundPrimary = split[1];
                           FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                        } else if(split[0].equals("PlaceSoundSecondary")) {
                           this.placeSoundSecondary = split[1];
                           FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                        } else if(split[0].equals("ReloadSoundPrimary")) {
                           this.reloadSoundPrimary = split[1];
                           FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                        } else if(split[0].equals("ReloadSoundSecondary")) {
                           this.reloadSoundSecondary = split[1];
                           FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                        } else if(split[0].equals("LockedOnSound")) {
                           this.lockedOnSound = split[1];
                           FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                        } else if(split[0].equals("LockOnSound")) {
                           this.lockOnSound = split[1];
                           FlansMod.proxy.loadSound(super.contentPack, "guns", split[1]);
                        } else if(split[0].equals("LockingOnSound")) {
                           this.lockingOnSound = split[1];
                           FlansMod.proxy.loadSound(super.contentPack, "guns", split[1]);
                        }
                     } else {
                        this.shootSoundSecondary = split[1];
                        FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                     }
                  } else {
                     this.shootSoundPrimary = split[1];
                     FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
                  }
               } else {
                  this.seats[0].gunName = split[2];
               }
            } else if(split.length > 4) {
               this.seats[0] = new Seat(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Float.parseFloat(split[4]), Float.parseFloat(split[5]), Float.parseFloat(split[6]), Float.parseFloat(split[7]));
            } else {
               this.seats[0] = new Seat(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
            }
         } else {
            this.shootDelayPrimary = Integer.parseInt(split[1]);
         }

         if(split[0].equals("FlareSound")) {
            this.flareSound = split[1];
            FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
         }

         if(split[0].equals("FancyCollision")) {
            this.fancyCollision = Boolean.parseBoolean(split[1]);
         }

         CollisionShapeBox var20;
         if(split[0].equals("AddCollisionMesh")) {
            var20 = new CollisionShapeBox(new Vector3f(split[1], super.shortName), new Vector3f(split[2], super.shortName), new Vector3f(split[3], super.shortName), new Vector3f(split[4], super.shortName), new Vector3f(split[5], super.shortName), new Vector3f(split[6], super.shortName), new Vector3f(split[7], super.shortName), new Vector3f(split[8], super.shortName), new Vector3f(split[9], super.shortName), new Vector3f(split[10], super.shortName), "core");
            this.collisionBox.add(var20);
         }

         Vector3f p6;
         Vector3f p7;
         Vector3f p8;
         CollisionShapeBox box;
         Vector3f var21;
         Vector3f var27;
         Vector3f var28;
         Vector3f var30;
         Vector3f var33;
         Vector3f var35;
         if(split[0].equals("AddCollisionMeshRaw")) {
            var21 = new Vector3f(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3]));
            var17 = new Vector3f(Float.parseFloat(split[4]), Float.parseFloat(split[5]), Float.parseFloat(split[6]));
            var35 = new Vector3f(Float.parseFloat(split[8]), Float.parseFloat(split[9]), Float.parseFloat(split[10]));
            var27 = new Vector3f(Float.parseFloat(split[11]), Float.parseFloat(split[12]), Float.parseFloat(split[13]));
            var28 = new Vector3f(Float.parseFloat(split[14]), Float.parseFloat(split[15]), Float.parseFloat(split[16]));
            var30 = new Vector3f(Float.parseFloat(split[17]), Float.parseFloat(split[18]), Float.parseFloat(split[19]));
            var33 = new Vector3f(Float.parseFloat(split[20]), Float.parseFloat(split[21]), Float.parseFloat(split[22]));
            p6 = new Vector3f(Float.parseFloat(split[23]), Float.parseFloat(split[24]), Float.parseFloat(split[25]));
            p7 = new Vector3f(Float.parseFloat(split[26]), Float.parseFloat(split[27]), Float.parseFloat(split[28]));
            p8 = new Vector3f(Float.parseFloat(split[29]), Float.parseFloat(split[30]), Float.parseFloat(split[31]));
            box = new CollisionShapeBox(var21, var17, var35, var27, var28, var30, var33, p6, p7, p8, "core");
            this.collisionBox.add(box);
         }

         if(split[0].equals("AddTurretCollisionMesh")) {
            var20 = new CollisionShapeBox(new Vector3f(split[1], super.shortName), new Vector3f(split[2], super.shortName), new Vector3f(split[3], super.shortName), new Vector3f(split[4], super.shortName), new Vector3f(split[5], super.shortName), new Vector3f(split[6], super.shortName), new Vector3f(split[7], super.shortName), new Vector3f(split[8], super.shortName), new Vector3f(split[9], super.shortName), new Vector3f(split[10], super.shortName), "turret");
            this.collisionBox.add(var20);
         }

         if(split[0].equals("AddTurretCollisionMeshRaw")) {
            var21 = new Vector3f(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3]));
            var17 = new Vector3f(Float.parseFloat(split[4]), Float.parseFloat(split[5]), Float.parseFloat(split[6]));
            var35 = new Vector3f(Float.parseFloat(split[8]), Float.parseFloat(split[9]), Float.parseFloat(split[10]));
            var27 = new Vector3f(Float.parseFloat(split[11]), Float.parseFloat(split[12]), Float.parseFloat(split[13]));
            var28 = new Vector3f(Float.parseFloat(split[14]), Float.parseFloat(split[15]), Float.parseFloat(split[16]));
            var30 = new Vector3f(Float.parseFloat(split[17]), Float.parseFloat(split[18]), Float.parseFloat(split[19]));
            var33 = new Vector3f(Float.parseFloat(split[20]), Float.parseFloat(split[21]), Float.parseFloat(split[22]));
            p6 = new Vector3f(Float.parseFloat(split[23]), Float.parseFloat(split[24]), Float.parseFloat(split[25]));
            p7 = new Vector3f(Float.parseFloat(split[26]), Float.parseFloat(split[27]), Float.parseFloat(split[28]));
            p8 = new Vector3f(Float.parseFloat(split[29]), Float.parseFloat(split[30]), Float.parseFloat(split[31]));
            box = new CollisionShapeBox(var21, var17, var35, var27, var28, var30, var33, p6, p7, p8, "turret");
            this.collisionBox.add(box);
         }

         if(split[0].equals("LeftLinkPoint")) {
            this.leftTrackPoints.add(new Vector3f(split[1], super.shortName));
         }

         if(split[0].equals("RightLinkPoint")) {
            this.rightTrackPoints.add(new Vector3f(split[1], super.shortName));
         }

         if(split[0].equals("TrackLinkLength")) {
            this.trackLinkLength = Float.parseFloat(split[1]);
         } else if(split[0].equals("OnRadar")) {
            this.onRadar = split[1].equals("True");
         } else if(!split[0].equalsIgnoreCase("AddParticle") && !split[0].equalsIgnoreCase("AddEmitter")) {
            if(split[0].equals("RadarDetectableAltitude")) {
               this.radarDetectableAltitude = Integer.parseInt(split[1]);
            } else if(split[0].equals("Stealth")) {
               this.stealth = split[1].equals("True");
            }
         } else {
            DriveableType.ParticleEmitter var24 = new DriveableType.ParticleEmitter();
            var24.effectType = split[1];
            var24.emitRate = Integer.parseInt(split[2]);
            var24.origin = new Vector3f(split[3], super.shortName);
            var24.extents = new Vector3f(split[4], super.shortName);
            var24.velocity = new Vector3f(split[5], super.shortName);
            var24.minThrottle = Float.parseFloat(split[6]);
            var24.maxThrottle = Float.parseFloat(split[7]);
            var24.minHealth = Float.parseFloat(split[8]);
            var24.maxHealth = Float.parseFloat(split[9]);
            var24.part = split[10];
            var24.origin.scale(0.0625F);
            var24.extents.scale(0.0625F);
            var24.velocity.scale(0.0625F);
            this.emitters.add(var24);
         }
      } catch (Exception var14) {
         if(split != null) {
            String msg = " : ";
            p1 = split;
            p2 = split.length;

            for(int p3 = 0; p3 < p2; ++p3) {
               s = p1[p3];
               msg = msg + " " + s;
            }

            FlansMod.log("Errored reading " + file.name + "");
         } else {
            FlansMod.log("Errored reading " + file.name);
         }

         if(FlansMod.printStackTrace) {
            var14.printStackTrace();
         }
      }

   }

   private DriveablePosition getShootPoint(String[] split) {
      return (DriveablePosition)(split.length == 6?new PilotGun(split):(split.length == 5?new DriveablePosition(split):new DriveablePosition(new Vector3f(), EnumDriveablePart.core)));
   }

   public ArrayList shootPoints(boolean s) {
      return s?this.shootPointsSecondary:this.shootPointsPrimary;
   }

   public boolean alternate(boolean s) {
      return s?this.alternateSecondary:this.alternatePrimary;
   }

   public EnumWeaponType weaponType(boolean s) {
      return s?this.secondary:this.primary;
   }

   public int shootDelay(boolean s) {
      return s?this.shootDelaySecondary:this.shootDelayPrimary;
   }

   public String shootSound(boolean s) {
      return s?this.shootSoundSecondary:this.shootSoundPrimary;
   }

   public ArrayList shootParticle(boolean s) {
      return s?this.shootParticlesSecondary:this.shootParticlesPrimary;
   }

   public int numEngines() {
      return 1;
   }

   public int ammoSlots() {
      return this.numPassengerGunners + this.pilotGuns.size();
   }

   public boolean isValidAmmo(BulletType bulletType, EnumWeaponType weaponType) {
      return (this.acceptAllAmmo || this.ammo.contains(bulletType)) && bulletType.weaponType == weaponType;
   }

   public ArrayList getItemsRequired(DriveablePart part, PartType engine) {
      ArrayList stacks = new ArrayList();
      int gun;
      int var6;
      if(this.partwiseRecipe.get(part.type) != null) {
         ItemStack[] var4 = (ItemStack[])this.partwiseRecipe.get(part.type);
         gun = var4.length;

         for(var6 = 0; var6 < gun; ++var6) {
            ItemStack seat = var4[var6];
            stacks.add(seat.copy());
         }
      }

      Iterator var8 = this.pilotGuns.iterator();

      while(var8.hasNext()) {
         PilotGun var10 = (PilotGun)var8.next();
         if(var10.part == part.type) {
            stacks.add(new ItemStack(var10.type.item));
         }
      }

      Seat[] var9 = this.seats;
      gun = var9.length;

      for(var6 = 0; var6 < gun; ++var6) {
         Seat var11 = var9[var6];
         if(var11 != null && var11.part == part.type && var11.gunType != null) {
            stacks.add(new ItemStack(var11.gunType.item));
         }
      }

      return stacks;
   }

   public static DriveableType getDriveable(String find) {
      Iterator var1 = types.iterator();

      DriveableType type;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         type = (DriveableType)var1.next();
      } while(!type.shortName.equals(find));

      return type;
   }

   public float GetRecommendedScale() {
      return 100.0F / this.cameraDistance;
   }

   @SideOnly(Side.CLIENT)
   public ModelBase GetModel() {
      return this.model;
   }


   public class ParticleEmitter {

      public String effectType;
      public int emitRate;
      public Vector3f origin;
      public Vector3f extents;
      public Vector3f velocity;
      public float minThrottle;
      public float maxThrottle;
      public String part;
      public float minHealth;
      public float maxHealth;


   }

   public class ShootParticle {

      float x = 0.0F;
      float y = 0.0F;
      float z = 0.0F;
      String name;


      public ShootParticle(String s, float x1, float y1, float z1) {
         this.x = x1;
         this.y = y1;
         this.z = z1;
         this.name = s;
      }
   }
}
