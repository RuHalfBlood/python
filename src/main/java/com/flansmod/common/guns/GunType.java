package com.flansmod.common.guns;

import com.flansmod.client.model.ModelGun;
import com.flansmod.client.model.ModelMG;
import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.guns.EnumFireMode;
import com.flansmod.common.guns.EnumSecondaryFunction;
import com.flansmod.common.guns.IScope;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.guns.ItemGrenade;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.paintjob.PaintableType;
import com.flansmod.common.types.TypeFile;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class GunType extends PaintableType implements IScope {

   public static final Random rand = new Random();
   public List ammo = new ArrayList();
   public boolean canForceReload = true;
   public int reloadTime;
   public float recoilPitch = 0.0F;
   public float recoilYaw = 0.0F;
   //Отдача в лево в право
   public float rndRecoilPitchRange = 1.0F;
   public float rndRecoilYawRange = 1.0F;
   public float decreaseRecoilPitch = 1.0F;
   public float decreaseRecoilYaw = 1.0F;
   public float bulletSpread;
   public boolean allowSpreadByBullet = false;
   public float damage = 0.0F;
   public float meleeDamage = 1.0F;
   public float bulletSpeed = 5.0F;
   public int numBullets = 1;
   public boolean allowNumBulletsByBulletType = false;
   public int shootDelay = 0;
   public int numAmmoItemsInGun = 1;
   public EnumFireMode mode;
   public EnumFireMode[] submode;
   public EnumFireMode defaultmode;
   public int numBurstRounds;
   public float minigunStartSpeed;
   public boolean canShootUnderwater;
   public float knockback;
   public EnumSecondaryFunction secondaryFunction;
   public EnumSecondaryFunction secondaryFunctionWhenShoot;
   public boolean oneHanded;
   public boolean consumeGunUponUse;
   public boolean showCrosshair;
   public String dropItemOnShoot;
   public int meleeTime;
   public ArrayList meleePath;
   public ArrayList meleePathAngles;
   public ArrayList meleeDamagePoints;
   public boolean usableByPlayers;
   public boolean usableByMechas;
   public EnumAction itemUseAction;
   public int canLockOnAngle;
   public int lockOnSoundTime;
   public String lockOnSound;
   public int maxRangeLockOn;
   public boolean canSetPosition;
   public boolean lockOnToPlanes;
   public boolean lockOnToVehicles;
   public boolean lockOnToMechas;
   public boolean lockOnToPlayers;
   public boolean lockOnToLivings;
   public boolean showAttachments;
   public boolean showDamage;
   public boolean showRecoil;
   public boolean showSpread;
   public boolean showReloadTime;
   public boolean shield;
   public Vector3f shieldOrigin;
   public Vector3f shieldDimensions;
   public float shieldDamageAbsorption;
   public String shootSound;
   public int shootSoundLength;
   public boolean distortSound;
   public String reloadSound;
   public String reloadSoundOnEmpty;
   public int idleSoundRange;
   public int meleeSoundRange;
   public int reloadSoundRange;
   public int gunSoundRange;
   public boolean useLoopingSounds;
   public String warmupSound;
   public int warmupSoundLength;
   public String loopedSound;
   public int loopedSoundLength;
   public String cooldownSound;
   public String meleeSound;
   public String idleSound;
   public int idleSoundLength;
   public boolean deployable;
   @SideOnly(Side.CLIENT)
   public ModelMG deployableModel;
   public String deployableTexture;
   public float standBackDist;
   public float topViewLimit;
   public float bottomViewLimit;
   public float sideViewLimit;
   public float pivotHeight;
   public String defaultScopeTexture;
   public boolean hasScopeOverlay;
   public float zoomLevel;
   public float FOVFactor;
   public boolean allowNightVision;
   @SideOnly(Side.CLIENT)
   public ModelGun model;
   public float modelScale;
   public boolean allowAllAttachments;
   public ArrayList allowedAttachments;
   public boolean allowBarrelAttachments;
   public boolean allowScopeAttachments;
   public boolean allowStockAttachments;
   public boolean allowGripAttachments;
   public boolean allowGadgetAttachments;
   public boolean allowSlideAttachments;
   public boolean allowPumpAttachments;
   public boolean allowAccessoryAttachments;
   public int numGenericAttachmentSlots;
   public static HashMap guns = new HashMap();
   public static ArrayList gunList = new ArrayList();
   public float moveSpeedModifier;
   public int activateSlowInInventoryLevel;
   public float knockbackModifier;
   private float defaultSpread;


   public GunType(TypeFile file) {
      super(file);
      this.mode = EnumFireMode.FULLAUTO;
      this.submode = new EnumFireMode[]{EnumFireMode.FULLAUTO};
      this.defaultmode = this.mode;
      this.numBurstRounds = 3;
      this.minigunStartSpeed = 15.0F;
      this.canShootUnderwater = false;
      this.knockback = 1.0F;
      this.secondaryFunction = EnumSecondaryFunction.ADS_ZOOM;
      this.secondaryFunctionWhenShoot = null;
      this.oneHanded = false;
      this.consumeGunUponUse = false;
      this.showCrosshair = false;
      this.dropItemOnShoot = null;
      this.meleeTime = 1;
      this.meleePath = new ArrayList();
      this.meleePathAngles = new ArrayList();
      this.meleeDamagePoints = new ArrayList();
      this.usableByPlayers = true;
      this.usableByMechas = true;
      this.itemUseAction = EnumAction.bow;
      this.canLockOnAngle = 5;
      this.lockOnSoundTime = 0;
      this.lockOnSound = "";
      this.maxRangeLockOn = 80;
      this.canSetPosition = false;
      this.lockOnToPlanes = false;
      this.lockOnToVehicles = false;
      this.lockOnToMechas = false;
      this.lockOnToPlayers = false;
      this.lockOnToLivings = false;
      this.showAttachments = true;
      this.showDamage = true;
      this.showRecoil = true;
      this.showSpread = true;
      this.showReloadTime = true;
      this.shield = false;
      this.shieldDamageAbsorption = 0.0F;
      this.distortSound = true;
      this.idleSoundRange = 50;
      this.meleeSoundRange = 50;
      this.reloadSoundRange = 30;
      this.gunSoundRange = 128;
      this.useLoopingSounds = false;
      this.warmupSoundLength = 20;
      this.loopedSoundLength = 20;
      this.deployable = false;
      this.standBackDist = 1.5F;
      this.topViewLimit = -60.0F;
      this.bottomViewLimit = 30.0F;
      this.sideViewLimit = 45.0F;
      this.pivotHeight = 0.375F;
      this.hasScopeOverlay = false;
      this.zoomLevel = 1.0F;
      this.FOVFactor = 1.5F;
      this.allowNightVision = false;
      this.modelScale = 1.0F;
      this.allowAllAttachments = false;
      this.allowedAttachments = new ArrayList();
      this.allowBarrelAttachments = false;
      this.allowScopeAttachments = false;
      this.allowStockAttachments = false;
      this.allowGripAttachments = false;
      this.allowGadgetAttachments = false;
      this.allowSlideAttachments = false;
      this.allowPumpAttachments = false;
      this.allowAccessoryAttachments = false;
      this.numGenericAttachmentSlots = 0;
      this.moveSpeedModifier = 1.0F;
      this.activateSlowInInventoryLevel = -1;
      this.knockbackModifier = 0.0F;
      this.defaultSpread = 1.0F;
   }

   public void preRead(TypeFile file) {
      super.preRead(file);
   }

   public void postRead(TypeFile file) {
      super.postRead(file);
      gunList.add(this);
      guns.put(super.shortName, this);
   }

   protected void read(String[] split, TypeFile file) {
      super.read(split, file);

      try {
         if(split[0].equals("Damage")) {
            this.damage = Float.parseFloat(split[1]);
         } else if(split[0].equals("MeleeDamage")) {
            this.meleeDamage = Float.parseFloat(split[1]);
            if(this.meleeDamage > 0.0F) {
               this.secondaryFunction = EnumSecondaryFunction.MELEE;
            }
         } else if(split[0].equals("CanForceReload")) {
            this.canForceReload = Boolean.parseBoolean(split[1].toLowerCase());
         } else if(split[0].equals("ReloadTime")) {
            this.reloadTime = Integer.parseInt(split[1]);
         } else if(split[0].equals("Recoil")) {
            this.recoilPitch = Float.parseFloat(split[1]);
         } else if(split[0].equals("RecoilYaw")) {
            this.recoilYaw = Float.parseFloat(split[1]) / 10.0F;
         } else if(split[0].equals("RandomRecoilRange")) {
            this.rndRecoilPitchRange = Float.parseFloat(split[1]);
         } else if(split[0].equals("RandomRecoilYawRange")) {
            this.rndRecoilYawRange = Float.parseFloat(split[1]);
         } else if(split[0].equals("DecreaseRecoil")) {
            this.decreaseRecoilPitch = Float.parseFloat(split[1]);
         } else if(split[0].equals("DecreaseRecoilYaw")) {
            this.decreaseRecoilYaw = Float.parseFloat(split[1]);
         } else if(split[0].equals("Knockback")) {
            this.knockback = Float.parseFloat(split[1]);
         } else if(!split[0].equals("Accuracy") && !split[0].equals("Spread")) {
            if(split[0].equals("NumBullets")) {
               this.numBullets = Integer.parseInt(split[1]);
            } else if(split[0].equals("AllowNumBulletsByBulletType")) {
               this.allowNumBulletsByBulletType = Boolean.parseBoolean(split[1]);
            } else if(split[0].equals("AllowSpreadByBullet")) {
               this.allowSpreadByBullet = Boolean.parseBoolean(split[1]);
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
            } else if(split[0].equals("showCrosshair")) {
               this.showCrosshair = Boolean.parseBoolean(split[1]);
            } else if(split[0].equals("ConsumeGunOnUse")) {
               this.consumeGunUponUse = Boolean.parseBoolean(split[1]);
            } else if(split[0].equals("DropItemOnShoot")) {
               this.dropItemOnShoot = split[1];
            } else if(split[0].equals("NumBurstRounds")) {
               this.numBurstRounds = Integer.parseInt(split[1]);
            } else if(split[0].equals("MinigunStartSpeed")) {
               this.minigunStartSpeed = Float.parseFloat(split[1]);
            } else if(split[0].equals("ItemUseAction")) {
               this.itemUseAction = EnumAction.valueOf(split[1].toLowerCase());
            } else if(split[0].equals("MaxRangeLockOn")) {
               this.maxRangeLockOn = Integer.parseInt(split[1]);
            } else if(split[0].equals("ShowAttachments")) {
               this.showAttachments = Boolean.parseBoolean(split[1]);
            } else if(split[0].equals("ShowDamage")) {
               this.showDamage = Boolean.parseBoolean(split[1]);
            } else if(split[0].equals("ShowRecoil")) {
               this.showRecoil = Boolean.parseBoolean(split[1]);
            } else if(split[0].equals("ShowAccuracy")) {
               this.showSpread = Boolean.parseBoolean(split[1]);
            } else if(split[0].equals("ShowReloadTime")) {
               this.showReloadTime = Boolean.parseBoolean(split[1]);
            } else if(split[0].equals("ShootDelay")) {
               this.shootDelay = Integer.parseInt(split[1]);
            } else if(split[0].equals("SoundLength")) {
               this.shootSoundLength = Integer.parseInt(split[1]);
            } else if(split[0].equals("DistortSound")) {
               this.distortSound = split[1].equals("True");
            } else if(split[0].equals("IdleSoundRange")) {
               this.idleSoundRange = Integer.parseInt(split[1]);
            } else if(split[0].equals("MeleeSoundRange")) {
               this.meleeSoundRange = Integer.parseInt(split[1]);
            } else if(split[0].equals("ReloadSoundRange")) {
               this.reloadSoundRange = Integer.parseInt(split[1]);
            } else if(split[0].equals("GunSoundRange")) {
               this.gunSoundRange = Integer.parseInt(split[1]);
            } else if(split[0].equals("ShootSound")) {
               this.shootSound = split[1];
               FlansMod.proxy.loadSound(super.contentPack, "guns", split[1]);
            } else if(split[0].equals("ReloadSound")) {
               this.reloadSound = split[1];
               FlansMod.proxy.loadSound(super.contentPack, "guns", split[1]);
            } else if(split[0].equals("EmptyReloadSound")) {
               this.reloadSoundOnEmpty = split[1];
               FlansMod.proxy.loadSound(super.contentPack, "guns", split[1]);
            } else if(split[0].equals("IdleSound")) {
               this.idleSound = split[1];
               FlansMod.proxy.loadSound(super.contentPack, "guns", split[1]);
            } else if(split[0].equals("IdleSoundLength")) {
               this.idleSoundLength = Integer.parseInt(split[1]);
            } else if(split[0].equals("MeleeSound")) {
               this.meleeSound = split[1];
               FlansMod.proxy.loadSound(super.contentPack, "guns", split[1]);
            } else if(split[0].equals("WarmupSound")) {
               this.warmupSound = split[1];
               FlansMod.proxy.loadSound(super.contentPack, "guns", split[1]);
            } else if(split[0].equals("WarmupSoundLength")) {
               this.warmupSoundLength = Integer.parseInt(split[1]);
            } else if(!split[0].equals("LoopedSound") && !split[0].equals("SpinSound")) {
               if(!split[0].equals("LoopedSoundLength") && !split[0].equals("SpinSoundLength")) {
                  if(split[0].equals("CooldownSound")) {
                     this.cooldownSound = split[1];
                     FlansMod.proxy.loadSound(super.contentPack, "guns", split[1]);
                  } else if(split[0].equals("LockOnSound")) {
                     this.lockOnSound = split[1];
                     FlansMod.proxy.loadSound(super.contentPack, "guns", split[1]);
                  } else {
                     int e;
                     if(split[0].equals("Mode")) {
                        this.mode = EnumFireMode.getFireMode(split[1]);
                        this.defaultmode = this.mode;
                        this.submode = new EnumFireMode[split.length - 1];

                        for(e = 0; e < this.submode.length; ++e) {
                           this.submode[e] = EnumFireMode.getFireMode(split[1 + e]);
                        }
                     } else if(split[0].equals("Scope")) {
                        this.hasScopeOverlay = true;
                        if(split[1].equals("None")) {
                           this.hasScopeOverlay = false;
                        } else {
                           this.defaultScopeTexture = split[1];
                        }
                     } else if(split[0].equals("AllowNightVision")) {
                        this.allowNightVision = Boolean.parseBoolean(split[1]);
                     } else if(split[0].equals("ZoomLevel")) {
                        this.zoomLevel = Float.parseFloat(split[1]);
                        if(this.zoomLevel > 1.0F) {
                           this.secondaryFunction = EnumSecondaryFunction.ZOOM;
                        }
                     } else if(split[0].equals("FOVZoomLevel")) {
                        this.FOVFactor = Float.parseFloat(split[1]);
                        if(this.FOVFactor > 1.0F) {
                           this.secondaryFunction = EnumSecondaryFunction.ADS_ZOOM;
                        }
                        
                     } else if(split[0].equals("Deployable")) {
                        this.deployable = split[1].equals("True");
                     } else if(FMLCommonHandler.instance().getSide().isClient() && this.deployable && split[0].equals("DeployedModel")) {
                        this.deployableModel = (ModelMG)FlansMod.proxy.loadModel(split[1], super.shortName, ModelMG.class);
                     } else if(FMLCommonHandler.instance().getSide().isClient() && split[0].equals("Model")) {
                        this.model = (ModelGun)FlansMod.proxy.loadModel(split[1], super.shortName, ModelGun.class);
                     } else if(split[0].equals("ModelScale")) {
                        this.modelScale = Float.parseFloat(split[1]);
                     } else if(split[0].equals("Texture")) {
                        super.texture = split[1];
                     } else if(split[0].equals("DeployedTexture")) {
                        this.deployableTexture = split[1];
                     } else if(split[0].equals("StandBackDistance")) {
                        this.standBackDist = Float.parseFloat(split[1]);
                     } else if(split[0].equals("TopViewLimit")) {
                        this.topViewLimit = -Float.parseFloat(split[1]);
                     } else if(split[0].equals("BottomViewLimit")) {
                        this.bottomViewLimit = Float.parseFloat(split[1]);
                     } else if(split[0].equals("SideViewLimit")) {
                        this.sideViewLimit = Float.parseFloat(split[1]);
                     } else if(split[0].equals("PivotHeight")) {
                        this.pivotHeight = Float.parseFloat(split[1]);
                     } else if(split[0].equals("Ammo")) {
                        ShootableType var10 = ShootableType.getShootableType(split[1]);
                        if(var10 != null) {
                           this.ammo.add(var10);
                        }
                     } else if(!split[0].equals("NumAmmoSlots") && !split[0].equals("NumAmmoItemsInGun") && !split[0].equals("LoadIntoGun")) {
                        if(split[0].equals("BulletSpeed")) {
                           this.bulletSpeed = Float.parseFloat(split[1]);
                        } else if(split[0].equals("CanShootUnderwater")) {
                           this.canShootUnderwater = Boolean.parseBoolean(split[1].toLowerCase());
                        } else if(split[0].equals("CanSetPosition")) {
                           this.canSetPosition = Boolean.parseBoolean(split[1].toLowerCase());
                        } else if(split[0].equals("OneHanded")) {
                           this.oneHanded = Boolean.parseBoolean(split[1].toLowerCase());
                        } else if(split[0].equals("SecondaryFunction")) {
                           this.secondaryFunction = EnumSecondaryFunction.get(split[1]);
                        } else if(split[0].equals("UsableByPlayers")) {
                           this.usableByPlayers = Boolean.parseBoolean(split[1]);
                        } else if(split[0].equals("UsableByMechas")) {
                           this.usableByMechas = Boolean.parseBoolean(split[1]);
                        } else if(split[0].equals("UseCustomMelee") && Boolean.parseBoolean(split[1])) {
                           this.secondaryFunction = EnumSecondaryFunction.CUSTOM_MELEE;
                        } else if(split[0].equals("UseCustomMeleeWhenShoot") && Boolean.parseBoolean(split[1])) {
                           this.secondaryFunctionWhenShoot = EnumSecondaryFunction.CUSTOM_MELEE;
                        } else if(split[0].equals("MeleeTime")) {
                           this.meleeTime = Integer.parseInt(split[1]);
                        } else if(split[0].equals("AddNode")) {
                           this.meleePath.add(new Vector3f(Float.parseFloat(split[1]) / 16.0F, Float.parseFloat(split[2]) / 16.0F, Float.parseFloat(split[3]) / 16.0F));
                           this.meleePathAngles.add(new Vector3f(Float.parseFloat(split[4]), Float.parseFloat(split[5]), Float.parseFloat(split[6])));
                        } else if(!split[0].equals("MeleeDamagePoint") && !split[0].equals("MeleeDamageOffset")) {
                           if(!split[0].equals("MoveSpeedModifier") && !split[0].equals("Slowness")) {
                              if(split[0].equals("ActivateSlowInInventoryLevel")) {
                                 this.activateSlowInInventoryLevel = Integer.parseInt(split[1]);
                              } else if(!split[0].equals("KnockbackReduction") && !split[0].equals("KnockbackModifier")) {
                                 if(split[0].equals("AllowAllAttachments")) {
                                    this.allowAllAttachments = Boolean.parseBoolean(split[1].toLowerCase());
                                 } else if(split[0].equals("AllowAttachments")) {
                                    for(e = 1; e < split.length; ++e) {
                                       this.allowedAttachments.add(AttachmentType.getAttachment(split[e]));
                                    }
                                 } else if(split[0].equals("AllowBarrelAttachments")) {
                                    this.allowBarrelAttachments = Boolean.parseBoolean(split[1].toLowerCase());
                                 } else if(split[0].equals("AllowScopeAttachments")) {
                                    this.allowScopeAttachments = Boolean.parseBoolean(split[1].toLowerCase());
                                 } else if(split[0].equals("AllowStockAttachments")) {
                                    this.allowStockAttachments = Boolean.parseBoolean(split[1].toLowerCase());
                                 } else if(split[0].equals("AllowGripAttachments")) {
                                    this.allowGripAttachments = Boolean.parseBoolean(split[1].toLowerCase());
                                 } else if(split[0].equals("AllowGadgetAttachments")) {
                                    this.allowGadgetAttachments = Boolean.parseBoolean(split[1].toLowerCase());
                                 } else if(split[0].equals("AllowSlideAttachments")) {
                                    this.allowSlideAttachments = Boolean.parseBoolean(split[1].toLowerCase());
                                 } else if(split[0].equals("AllowPumpAttachments")) {
                                    this.allowPumpAttachments = Boolean.parseBoolean(split[1].toLowerCase());
                                 } else if(split[0].equals("AllowAccessoryAttachments")) {
                                    this.allowAccessoryAttachments = Boolean.parseBoolean(split[1].toLowerCase());
                                 } else if(split[0].equals("NumGenericAttachmentSlots")) {
                                    this.numGenericAttachmentSlots = Integer.parseInt(split[1]);
                                 } else if(split[0].toLowerCase().equals("shield")) {
                                    this.shield = true;
                                    this.shieldDamageAbsorption = Float.parseFloat(split[1]);
                                    this.shieldOrigin = new Vector3f(Float.parseFloat(split[2]) / 16.0F, Float.parseFloat(split[3]) / 16.0F, Float.parseFloat(split[4]) / 16.0F);
                                    this.shieldDimensions = new Vector3f(Float.parseFloat(split[5]) / 16.0F, Float.parseFloat(split[6]) / 16.0F, Float.parseFloat(split[7]) / 16.0F);
                                 }
                              } else {
                                 this.knockbackModifier = Float.parseFloat(split[1]);
                              }
                           } else {
                              this.moveSpeedModifier = Float.parseFloat(split[1]);
                           }
                        } else {
                           this.meleeDamagePoints.add(new Vector3f(Float.parseFloat(split[1]) / 16.0F, Float.parseFloat(split[2]) / 16.0F, Float.parseFloat(split[3]) / 16.0F));
                        }
                     } else {
                        this.numAmmoItemsInGun = Integer.parseInt(split[1]);
                     }
                  }
               } else {
                  this.loopedSoundLength = Integer.parseInt(split[1]);
               }
            } else {
               this.loopedSound = split[1];
               this.useLoopingSounds = true;
               FlansMod.proxy.loadSound(super.contentPack, "guns", split[1]);
            }
         } else {
            this.defaultSpread = this.bulletSpread = Float.parseFloat(split[1]);
         }
      } catch (Exception var9) {
         if(split != null) {
            String msg = " : ";
            String[] var5 = split;
            int var6 = split.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               String s = var5[var7];
               msg = msg + " " + s;
            }

            System.out.println("Reading gun file failed. " + file.name + msg);
         } else {
            System.out.println("Reading gun file failed. " + file.name);
         }

         if(FlansMod.printStackTrace) {
            var9.printStackTrace();
         }
      }

   }

   public boolean isAmmo(ShootableType type) {
      return this.ammo.contains(type);
   }

   public boolean isAmmo(ItemStack stack) {
      return stack == null?false:(stack.getItem() instanceof ItemBullet?this.isAmmo((ShootableType)((ItemBullet)stack.getItem()).type):(stack.getItem() instanceof ItemGrenade?this.isAmmo((ShootableType)((ItemGrenade)stack.getItem()).type):false));
   }

   public void reloadModel() {
      this.model = (ModelGun)FlansMod.proxy.loadModel(super.modelString, super.shortName, ModelGun.class);
   }

   public float getZoomFactor() {
      return this.zoomLevel;
   }

   public boolean hasZoomOverlay() {
      return this.hasScopeOverlay;
   }

   public String getZoomOverlay() {
      return this.defaultScopeTexture;
   }

   public float getFOVFactor() {
      return this.FOVFactor;
   }

   public IScope getCurrentScope(ItemStack gunStack) {
      AttachmentType attachedScope = this.getScope(gunStack);
      return (IScope)(attachedScope == null?this:attachedScope);
   }

   public ArrayList getCurrentAttachments(ItemStack gun) {
      this.checkForTags(gun);
      ArrayList attachments = new ArrayList();
      NBTTagCompound attachmentTags = gun.stackTagCompound.getCompoundTag("attachments");
      NBTTagList genericsList = attachmentTags.getTagList("generics", 10);

      for(int i = 0; i < this.numGenericAttachmentSlots; ++i) {
         this.appendToList(gun, "generic_" + i, attachments);
      }

      this.appendToList(gun, "barrel", attachments);
      this.appendToList(gun, "scope", attachments);
      this.appendToList(gun, "stock", attachments);
      this.appendToList(gun, "grip", attachments);
      this.appendToList(gun, "gadget", attachments);
      this.appendToList(gun, "slide", attachments);
      this.appendToList(gun, "pump", attachments);
      this.appendToList(gun, "accessory", attachments);
      return attachments;
   }

   private void appendToList(ItemStack gun, String name, ArrayList attachments) {
      AttachmentType type = this.getAttachment(gun, name);
      if(type != null) {
         attachments.add(type);
      }

   }

   public AttachmentType getBarrel(ItemStack gun) {
      return this.getAttachment(gun, "barrel");
   }

   public AttachmentType getScope(ItemStack gun) {
      return this.getAttachment(gun, "scope");
   }

   public AttachmentType getStock(ItemStack gun) {
      return this.getAttachment(gun, "stock");
   }

   public AttachmentType getGrip(ItemStack gun) {
      return this.getAttachment(gun, "grip");
   }

   public AttachmentType getGadget(ItemStack gun) {
      return this.getAttachment(gun, "gadget");
   }

   public AttachmentType getSlide(ItemStack gun) {
      return this.getAttachment(gun, "slide");
   }

   public AttachmentType getPump(ItemStack gun) {
      return this.getAttachment(gun, "pump");
   }

   public AttachmentType getAccessory(ItemStack gun) {
      return this.getAttachment(gun, "accessory");
   }

   public AttachmentType getGeneric(ItemStack gun, int i) {
      return this.getAttachment(gun, "generic_" + i);
   }

   public ItemStack getBarrelItemStack(ItemStack gun) {
      return this.getAttachmentItemStack(gun, "barrel");
   }

   public ItemStack getScopeItemStack(ItemStack gun) {
      return this.getAttachmentItemStack(gun, "scope");
   }

   public ItemStack getStockItemStack(ItemStack gun) {
      return this.getAttachmentItemStack(gun, "stock");
   }

   public ItemStack getGripItemStack(ItemStack gun) {
      return this.getAttachmentItemStack(gun, "grip");
   }

   public ItemStack getGadgetItemStack(ItemStack gun) {
      return this.getAttachmentItemStack(gun, "gadget");
   }

   public ItemStack getSlideItemStack(ItemStack gun) {
      return this.getAttachmentItemStack(gun, "slide");
   }

   public ItemStack getPumpItemStack(ItemStack gun) {
      return this.getAttachmentItemStack(gun, "pump");
   }

   public ItemStack getAccessoryItemStack(ItemStack gun) {
      return this.getAttachmentItemStack(gun, "accessory");
   }

   public ItemStack getGenericItemStack(ItemStack gun, int i) {
      return this.getAttachmentItemStack(gun, "generic_" + i);
   }

   public AttachmentType getAttachment(ItemStack gun, String name) {
      this.checkForTags(gun);
      return AttachmentType.getFromNBT(gun.stackTagCompound.getCompoundTag("attachments").getCompoundTag(name));
   }

   public ItemStack getAttachmentItemStack(ItemStack gun, String name) {
      this.checkForTags(gun);
      return ItemStack.loadItemStackFromNBT(gun.stackTagCompound.getCompoundTag("attachments").getCompoundTag(name));
   }

   private void checkForTags(ItemStack gun) {
      if(!gun.hasTagCompound()) {
         gun.stackTagCompound = new NBTTagCompound();
      }

      if(!gun.stackTagCompound.hasKey("attachments")) {
         NBTTagCompound attachmentTags = new NBTTagCompound();

         for(int i = 0; i < this.numGenericAttachmentSlots; ++i) {
            attachmentTags.setTag("generic_" + i, new NBTTagCompound());
         }

         attachmentTags.setTag("barrel", new NBTTagCompound());
         attachmentTags.setTag("scope", new NBTTagCompound());
         attachmentTags.setTag("stock", new NBTTagCompound());
         attachmentTags.setTag("grip", new NBTTagCompound());
         attachmentTags.setTag("gadget", new NBTTagCompound());
         attachmentTags.setTag("slide", new NBTTagCompound());
         attachmentTags.setTag("pump", new NBTTagCompound());
         attachmentTags.setTag("accessory", new NBTTagCompound());
         gun.stackTagCompound.setTag("attachments", attachmentTags);
      }

   }

   public float getMeleeDamage(ItemStack stack) {
      float stackMeleeDamage = this.meleeDamage;

      AttachmentType attachment;
      for(Iterator var3 = this.getCurrentAttachments(stack).iterator(); var3.hasNext(); stackMeleeDamage *= attachment.meleeDamageMultiplier) {
         attachment = (AttachmentType)var3.next();
      }

      return stackMeleeDamage;
   }

   public float getDamage(ItemStack stack) {
      float stackDamage = this.damage;

      AttachmentType attachment;
      for(Iterator var3 = this.getCurrentAttachments(stack).iterator(); var3.hasNext(); stackDamage *= attachment.damageMultiplier) {
         attachment = (AttachmentType)var3.next();
      }

      return stackDamage;
   }

   public float getSpread(ItemStack stack) {
      float stackSpread = this.bulletSpread;

      AttachmentType attachment;
      for(Iterator var3 = this.getCurrentAttachments(stack).iterator(); var3.hasNext(); stackSpread *= attachment.spreadMultiplier) {
         attachment = (AttachmentType)var3.next();
      }

      return stackSpread;
   }

   public float getDefaultSpread(ItemStack stack) {
      float stackSpread = this.defaultSpread;

      AttachmentType attachment;
      for(Iterator var3 = this.getCurrentAttachments(stack).iterator(); var3.hasNext(); stackSpread *= attachment.spreadMultiplier) {
         attachment = (AttachmentType)var3.next();
      }

      return stackSpread;
   }

   public float getRecoilPitch(ItemStack stack) {
      float stackRecoil = this.recoilPitch + rand.nextFloat() * this.rndRecoilPitchRange;

      AttachmentType attachment;
      for(Iterator var3 = this.getCurrentAttachments(stack).iterator(); var3.hasNext(); stackRecoil *= attachment.recoilMultiplier) {
         attachment = (AttachmentType)var3.next();
      }

      return stackRecoil;
   }

   public float getRecoilYaw(ItemStack stack) {
      float stackRecoilYaw = this.recoilYaw + (rand.nextFloat() - 0.5F) * this.rndRecoilYawRange;

      AttachmentType attachment;
      for(Iterator var3 = this.getCurrentAttachments(stack).iterator(); var3.hasNext(); stackRecoilYaw *= attachment.recoilMultiplier) {
         attachment = (AttachmentType)var3.next();
      }

      return stackRecoilYaw;
   }

   public float getBulletSpeed(ItemStack stack) {
      float stackBulletSpeed = this.bulletSpeed;

      AttachmentType attachment;
      for(Iterator var3 = this.getCurrentAttachments(stack).iterator(); var3.hasNext(); stackBulletSpeed *= attachment.bulletSpeedMultiplier) {
         attachment = (AttachmentType)var3.next();
      }

      return stackBulletSpeed;
   }

   public float getReloadTime(ItemStack stack) {
      float stackReloadTime = (float)this.reloadTime;

      AttachmentType attachment;
      for(Iterator var3 = this.getCurrentAttachments(stack).iterator(); var3.hasNext(); stackReloadTime *= attachment.reloadTimeMultiplier) {
         attachment = (AttachmentType)var3.next();
      }

      return stackReloadTime;
   }

   public float getMovementSpeed(ItemStack stack) {
      float stackMovement = this.moveSpeedModifier;

      AttachmentType attachment;
      for(Iterator var3 = this.getCurrentAttachments(stack).iterator(); var3.hasNext(); stackMovement *= attachment.moveSpeedMultiplier) {
         attachment = (AttachmentType)var3.next();
      }

      return stackMovement;
   }

   public void setFireMode(ItemStack stack, int fireMode) {
      if(!stack.hasTagCompound()) {
         stack.setTagCompound(new NBTTagCompound());
      }

      if(fireMode < EnumFireMode.values().length) {
         stack.getTagCompound().setByte("GunMode", (byte)fireMode);
      } else {
         stack.getTagCompound().setByte("GunMode", (byte)this.mode.ordinal());
      }

   }

   public EnumFireMode getFireMode(ItemStack stack) {
      if(stack.hasTagCompound() && stack.getTagCompound().hasKey("GunMode")) {
         byte gm = stack.getTagCompound().getByte("GunMode");
         if(gm < EnumFireMode.values().length) {
            for(int attachment = 0; attachment < this.submode.length; ++attachment) {
               if(gm == this.submode[attachment].ordinal()) {
                  return EnumFireMode.values()[gm];
               }
            }
         }
      }

      this.setFireMode(stack, this.mode.ordinal());
      Iterator var4 = this.getCurrentAttachments(stack).iterator();

      AttachmentType var5;
      do {
         if(!var4.hasNext()) {
            return this.mode;
         }

         var5 = (AttachmentType)var4.next();
      } while(var5.modeOverride == null);

      return var5.modeOverride;
   }

   public static GunType getGun(String s) {
      return (GunType)guns.get(s);
   }

   public com.flansmod.common.paintjob.Paintjob getPaintjob(String s) {
      Iterator var2 = super.paintjobs.iterator();

      com.flansmod.common.paintjob.Paintjob paintjob;
      do {
         if(!var2.hasNext()) {
            return super.defaultPaintjob;
         }

         paintjob = (com.flansmod.common.paintjob.Paintjob)var2.next();
      } while(!paintjob.iconName.equals(s));

      return paintjob;
   }

   public float GetRecommendedScale() {
      return 60.0F;
   }

   @SideOnly(Side.CLIENT)
   public ModelBase GetModel() {
      return this.model;
   }

}
