package com.flansmod.common.guns;

import com.flansmod.client.FlansModClient;
import com.flansmod.client.TickHandlerClient;
import com.flansmod.client.debug.EntityDebugDot;
import com.flansmod.client.debug.EntityDebugVector;
import com.flansmod.client.model.GunAnimations;
import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.EntityAAGun;
import com.flansmod.common.guns.EntityDamageSourceGun;
import com.flansmod.common.guns.EntityGrenade;
import com.flansmod.common.guns.EntityMG;
import com.flansmod.common.guns.EnumFireMode;
import com.flansmod.common.guns.EnumSecondaryFunction;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.IScope;
import com.flansmod.common.guns.InventoryHelper;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.guns.ItemShootable;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.guns.raytracing.BulletHit;
import com.flansmod.common.guns.raytracing.EntityHit;
import com.flansmod.common.guns.raytracing.EnumHitboxType;
import com.flansmod.common.guns.raytracing.PlayerBulletHit;
import com.flansmod.common.guns.raytracing.PlayerHitbox;
import com.flansmod.common.guns.raytracing.PlayerSnapshot;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketGunFire;
import com.flansmod.common.network.PacketGunSpread;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.network.PacketReload;
import com.flansmod.common.network.PacketSelectOffHandGun;
import com.flansmod.common.paintjob.IPaintableItem;
import com.flansmod.common.paintjob.PaintableType;
import com.flansmod.common.teams.EntityFlag;
import com.flansmod.common.teams.EntityFlagpole;
import com.flansmod.common.teams.EntityGunItem;
import com.flansmod.common.teams.Team;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3f;
import com.flansmod.hand.client.dev.flans.RenderConfigFlans;
import com.google.common.collect.Multimap;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class ItemGun extends Item implements IPaintableItem {

   public GunType type;
   private static boolean rightMouseHeld;
   private static boolean lastRightMouseHeld;
   private static boolean leftMouseHeld;
   private static boolean lastLeftMouseHeld;
   public int soundDelay;
   public int lockOnSoundDelay;
   public int impactX = 0;
   public int impactY = 0;
   public int impactZ = 0;
   public IIcon[] icons;
   @SideOnly(Side.CLIENT)
   private RenderConfigFlans renderConfig;


   public InfoType getInfoType() {
      return this.type;
   }

   public PaintableType GetPaintableType() {
      return this.type;
   }

   public ItemGun(GunType gun) {
      super.maxStackSize = 1;
      this.type = gun;
      this.type.item = this;
      this.setHasSubtypes(true);
      this.setCreativeTab(FlansMod.tabFlanGuns);
      GameRegistry.registerItem(this, this.type.shortName, "flansmod");
      this.renderConfig = new RenderConfigFlans(gun.shortName);

   }
   @SideOnly(Side.CLIENT)
   public RenderConfigFlans getRenderConfig() {
      return this.renderConfig;
   }

   public boolean getShareTag() {
      return true;
   }

   public ItemStack getBulletItemStack(ItemStack gun, int id) {
      if(!gun.hasTagCompound()) {
         gun.stackTagCompound = new NBTTagCompound();
         return null;
      } else {
         NBTTagList ammoTagsList;
         if(gun.stackTagCompound.hasKey("ammo")) {
            ammoTagsList = gun.stackTagCompound.getTagList("ammo", 10);
            NBTTagCompound var5 = ammoTagsList.getCompoundTagAt(id);
            return ItemStack.loadItemStackFromNBT(var5);
         } else {
            ammoTagsList = new NBTTagList();

            for(int ammoTags = 0; ammoTags < this.type.numAmmoItemsInGun; ++ammoTags) {
               ammoTagsList.appendTag(new NBTTagCompound());
            }

            gun.stackTagCompound.setTag("ammo", ammoTagsList);
            return null;
         }
      }
   }

   public void setBulletItemStack(ItemStack gun, ItemStack bullet, int id) {
      if(!gun.hasTagCompound()) {
         gun.stackTagCompound = new NBTTagCompound();
      }

      NBTTagList ammoTagsList;
      if(!gun.stackTagCompound.hasKey("ammo")) {
         ammoTagsList = new NBTTagList();

         for(int ammoTags = 0; ammoTags < this.type.numAmmoItemsInGun; ++ammoTags) {
            ammoTagsList.appendTag(new NBTTagCompound());
         }

         gun.stackTagCompound.setTag("ammo", ammoTagsList);
      }

      ammoTagsList = gun.stackTagCompound.getTagList("ammo", 10);
      NBTTagCompound var6 = ammoTagsList.getCompoundTagAt(id);
      if(bullet == null) {
         var6 = new NBTTagCompound();
      }

      bullet.writeToNBT(var6);
   }

   public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean advancedTooltips) {
      if(!this.type.packName.isEmpty()) {
         lines.add(this.type.packName);
      }


      if(this.type.showDamage) {
         lines.add("§9Damage§7: " + this.type.getDamage(stack));
      }

      if(this.type.showRecoil) {
         lines.add("§9Recoil§7: " + this.type.getRecoilPitch(stack));
      }

      if(this.type.showSpread) {
         lines.add("§9Accuracy§7: " + this.type.getSpread(stack));
      }

      if(this.type.showReloadTime) {
         lines.add("§9Reload Time§7: " + (int)this.type.getReloadTime(stack) / 20F + "s");
      }

      Iterator i = this.type.getCurrentAttachments(stack).iterator();

      while(i.hasNext()) {
         AttachmentType bulletStack = (AttachmentType)i.next();
         if(this.type.showAttachments) {
            String bulletType = bulletStack.name;
            lines.add(bulletType);
         }
      }

      for(int var9 = 0; var9 < this.type.numAmmoItemsInGun; ++var9) {
         ItemStack var10 = this.getBulletItemStack(stack, var9);
         if(var10 != null && var10.getItem() instanceof ItemBullet) {
            BulletType var11 = ((ItemBullet)var10.getItem()).type;
            String line = var11.name + " " + (var10.getMaxDamage() - var10.getItemDamage()) + "/" + var10.getMaxDamage();
            lines.add(line);
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void onUpdateClient(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
      if(entity instanceof EntityPlayer && ((EntityPlayer)entity).inventory.getCurrentItem() == itemstack) {
         Minecraft mc = Minecraft.getMinecraft();
         EntityPlayer player = (EntityPlayer)entity;
         PlayerData data = PlayerHandler.getPlayerData(player, Side.CLIENT);
         if(this.soundDelay <= 0 && this.type.idleSound != null) {
            PacketPlaySound.sendSoundPacket(entity.posX, entity.posY, entity.posZ, (double)this.type.idleSoundRange, entity.dimension, this.type.idleSound, false);
            this.soundDelay = this.type.idleSoundLength;
         }

         if(this.type.deployable) {
            return;
         }

         GameSettings gameSettings = FMLClientHandler.instance().getClient().gameSettings;
         if(FMLClientHandler.instance().getClient().currentScreen != null) {
            if(FlansModClient.currentScope != null) {
               FlansModClient.currentScope = null;
               gameSettings.mouseSensitivity = FlansModClient.originalMouseSensitivity;
               gameSettings.thirdPersonView = FlansModClient.originalThirdPerson;
               gameSettings.fovSetting = FlansModClient.originalFOV;
               FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketGunSpread(itemstack, this.type.getDefaultSpread(itemstack))));
            }
         } else if(mc.objectMouseOver == null || !(mc.objectMouseOver.entityHit instanceof EntityFlagpole) && !(mc.objectMouseOver.entityHit instanceof EntityFlag) && !(mc.objectMouseOver.entityHit instanceof EntityGunItem) && (!(mc.objectMouseOver.entityHit instanceof EntityGrenade) || !((EntityGrenade)mc.objectMouseOver.entityHit).type.isDeployableBag)) {
            lastRightMouseHeld = rightMouseHeld;
            lastLeftMouseHeld = leftMouseHeld;
            rightMouseHeld = Mouse.isButtonDown(0);
            leftMouseHeld = Mouse.isButtonDown(1);
            boolean offHandFull = false;
            if(this.type.oneHanded) {
               if(data.offHandGunSlot == player.inventory.currentItem + 1) {
                  data.offHandGunSlot = 0;
               }

               int currentScope = Mouse.getDWheel();
               if(Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode()) && currentScope != 0) {
                  data.cycleOffHandItem(player, currentScope);
               }

               if(data.offHandGunSlot != 0) {
                  offHandFull = true;
                  ItemStack f = player.inventory.getStackInSlot(data.offHandGunSlot - 1);
                  if(f != null && f.getItem() instanceof ItemGun) {
                     GunType spread = ((ItemGun)f.getItem()).type;
                     if(spread.usableByPlayers) {
                        if(spread.getFireMode(f) == EnumFireMode.BURST && data.burstRoundsRemainingLeft > 0) {
                           if(this.clientSideShoot(player, f, spread, true)) {
                              player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, (ItemStack)null);
                           }
                        } else {
                           if(leftMouseHeld && !lastLeftMouseHeld) {
                              FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketGunFire(true, true, player.rotationYaw, player.rotationPitch)));
                              if(this.clientSideShoot(player, f, spread, true)) {
                                 player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, (ItemStack)null);
                              }
                           }

                           if((spread.getFireMode(f) == EnumFireMode.FULLAUTO || spread.getFireMode(f) == EnumFireMode.MINIGUN) && !leftMouseHeld && lastLeftMouseHeld) {
                              FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketGunFire(true, false, player.rotationYaw, player.rotationPitch)));
                           }

                           if((spread.getFireMode(f) == EnumFireMode.FULLAUTO || spread.getFireMode(f) == EnumFireMode.MINIGUN) && leftMouseHeld && this.clientSideShoot(player, f, spread, true)) {
                              player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, (ItemStack)null);
                           }
                        }
                     }
                  } else {
                     data.offHandGunSlot = 0;
                  }
               }
            }

            if(this.type.usableByPlayers) {
               if(this.type.getFireMode(itemstack) == EnumFireMode.BURST && data.burstRoundsRemainingRight > 0) {
                  if(this.clientSideShoot(player, itemstack, this.type, false)) {
                     player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                  }
               } else {
                  if(rightMouseHeld && !lastRightMouseHeld) {
                     FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketGunFire(false, true, player.rotationYaw, player.rotationPitch)));
                     if(this.clientSideShoot(player, itemstack, this.type, false)) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                     }
                  }

                  if((this.type.getFireMode(itemstack) == EnumFireMode.FULLAUTO || this.type.getFireMode(itemstack) == EnumFireMode.MINIGUN) && !rightMouseHeld && lastRightMouseHeld) {
                     FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketGunFire(false, false, player.rotationYaw, player.rotationPitch)));
                  }

                  if((this.type.getFireMode(itemstack) == EnumFireMode.FULLAUTO || this.type.getFireMode(itemstack) == EnumFireMode.MINIGUN) && rightMouseHeld && this.clientSideShoot(player, itemstack, this.type, false)) {
                     player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                  }
               }
            }

            IScope var14 = this.type.getCurrentScope(itemstack);
            if(!offHandFull && (this.type.secondaryFunction == EnumSecondaryFunction.ADS_ZOOM || this.type.secondaryFunction == EnumSecondaryFunction.ZOOM) && Mouse.isButtonDown(1) && FlansModClient.scopeTime <= 0 && FMLClientHandler.instance().getClient().currentScreen == null && !player.isSprinting()) {
               if(FlansModClient.currentScope == null) {
                  FlansModClient.currentScope = var14;
                  FlansModClient.lastZoomLevel = var14.getZoomFactor();
                  FlansModClient.lastFOVZoomLevel = var14.getFOVFactor();
                  float var15 = FlansModClient.originalMouseSensitivity = gameSettings.mouseSensitivity;
                  gameSettings.mouseSensitivity = var15 / (float)Math.sqrt((double)var14.getZoomFactor());
                  FlansModClient.originalThirdPerson = gameSettings.thirdPersonView;
                  gameSettings.thirdPersonView = 0;
                  FlansModClient.originalFOV = gameSettings.fovSetting;
                  float var16 = this.type.getSpread(itemstack);
                  if(this.type.numBullets == 1) {
                     FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketGunSpread(itemstack, var16 * 0.2F)));
                  } else {
                     FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketGunSpread(itemstack, var16 * 0.8F)));
                  }
               } else {
                  FlansModClient.currentScope = null;
                  gameSettings.mouseSensitivity = FlansModClient.originalMouseSensitivity;
                  gameSettings.thirdPersonView = FlansModClient.originalThirdPerson;
                  gameSettings.fovSetting = FlansModClient.originalFOV;
                  FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketGunSpread(itemstack, this.type.getDefaultSpread(itemstack))));
               }

               FlansModClient.scopeTime = 10;
            }
         }
      }

      if(this.soundDelay > 0) {
         --this.soundDelay;
      }

   }

   public boolean clientSideShoot(EntityPlayer player, ItemStack stack, GunType gunType, boolean left) {
      PlayerData data = PlayerHandler.getPlayerData(player);
      if(this.type.meleeSound != null) {
         PacketPlaySound.sendSoundPacket(player.posX, player.posY, player.posZ, (double)this.type.meleeSoundRange, player.dimension, this.type.meleeSound, true);
      }

      if(this.type.secondaryFunctionWhenShoot != null && this.type.secondaryFunctionWhenShoot == EnumSecondaryFunction.CUSTOM_MELEE) {
         if(player.worldObj.isRemote) {
            GunAnimations onLastBullet = FlansModClient.getGunAnimations(player, false);
            onLastBullet.doMelee(this.type.meleeTime);
         }

         if(player instanceof EntityPlayer) {
            PlayerData var13 = PlayerHandler.getPlayerData(player);
            data.doMelee(player, this.type.meleeTime, this.type);
         }
      }


      if (FlansModClient.shootTime(left) <= 0 && !player.isSprinting()) {

         boolean var14 = false;
         boolean hasAmmo = false;

         for(int animations = 0; animations < gunType.numAmmoItemsInGun; ++animations) {
            ItemStack pumpDelay = this.getBulletItemStack(stack, animations);
            if(pumpDelay != null && pumpDelay.getItem() != null && pumpDelay.getItemDamage() < pumpDelay.getMaxDamage()) {
               if(pumpDelay.getMaxDamage() - pumpDelay.getItemDamage() == 1 && gunType.model.slideLockOnEmpty) {
                  var14 = true;
               }

               hasAmmo = true;
               break;
            }
         }

         if(hasAmmo) {
            GunAnimations var15 = null;
            TickHandlerClient.muzzleTick = 1.5;
            if(left) {
               if(FlansMod.gunAnimationsLeft.containsKey(player)) {
                  var15 = (GunAnimations)FlansMod.gunAnimationsLeft.get(player);
               } else {
                  var15 = new GunAnimations();
                  FlansMod.gunAnimationsLeft.put(player, var15);
               }
            } else if(FlansMod.gunAnimationsRight.containsKey(player)) {
               var15 = (GunAnimations)FlansMod.gunAnimationsRight.get(player);
            } else {
               var15 = new GunAnimations();
               FlansMod.gunAnimationsRight.put(player, var15);
            }

            int var16 = gunType.model == null?0:gunType.model.pumpDelay;
            int pumpTime = gunType.model == null?1:gunType.model.pumpTime;
            int hammerDelay = gunType.model == null?0:gunType.model.hammerDelay;
            float hammerAngle = gunType.model == null?0.0F:gunType.model.hammerAngle;
            var15.onGunEmpty(var14);
            var15.doShoot(var16, pumpTime, hammerDelay, hammerAngle);
            if(!player.isSneaking()) {
               FlansModClient.playerRecoilPitch += gunType.getRecoilPitch(stack);
               FlansModClient.playerRecoilYaw += gunType.getRecoilYaw(stack);
            } else {
               FlansModClient.playerRecoilPitch += gunType.getRecoilPitch(stack) - gunType.decreaseRecoilPitch;
               FlansModClient.playerRecoilYaw += gunType.getRecoilYaw(stack) / gunType.decreaseRecoilYaw;
            }

            if(left) {
               FlansModClient.shootTimeLeft = gunType.shootDelay;
            } else {
               FlansModClient.shootTimeRight = gunType.shootDelay;
            }

            if(gunType.consumeGunUponUse) {
               return true;
            }
         }

         if(gunType.getFireMode(stack) == EnumFireMode.BURST) {
            if(left) {
               if(data.burstRoundsRemainingLeft > 0) {
                  --data.burstRoundsRemainingLeft;
               } else {
                  data.burstRoundsRemainingLeft = gunType.numBurstRounds;
               }
            } else if(data.burstRoundsRemainingRight > 0) {
               --data.burstRoundsRemainingRight;
            } else {
               data.burstRoundsRemainingRight = gunType.numBurstRounds;
            }
         }
      }

      return false;
   }

   public void onUpdateServer(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
      if(entity instanceof EntityPlayerMP) {
         EntityPlayerMP player = (EntityPlayerMP)entity;
         PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)player);
         if(data == null) {
            return;
         }

         if(player.inventory.getCurrentItem() != itemstack) {
            if(player.inventory.getCurrentItem() == null || player.inventory.getCurrentItem().getItem() == null || !(player.inventory.getCurrentItem().getItem() instanceof ItemGun)) {
               data.isShootingRight = data.isShootingLeft = false;
               data.offHandGunSlot = 0;
               (new PacketSelectOffHandGun(0)).handleServerSide(player);
            }

            return;
         }

         if(this.type.getFireMode(itemstack) == EnumFireMode.BURST && data.burstRoundsRemainingRight > 0) {
            player.inventory.setInventorySlotContents(player.inventory.currentItem, this.tryToShoot(itemstack, this.type, world, player, false));
         }

         if(data.isShootingRight) {
            if(this.type.getFireMode(itemstack) == EnumFireMode.FULLAUTO) {
               player.inventory.setInventorySlotContents(player.inventory.currentItem, this.tryToShoot(itemstack, this.type, world, player, false));
            }

            if(this.type.useLoopingSounds && data.loopedSoundDelay <= 0 && data.minigunSpeed > 0.1F && !data.reloadingRight ) {
               data.loopedSoundDelay = data.shouldPlayWarmupSound?this.type.warmupSoundLength:this.type.loopedSoundLength;
               PacketPlaySound.sendSoundPacket(player.posX, player.posY, player.posZ, 50.0D, player.dimension, data.shouldPlayWarmupSound?this.type.warmupSound:this.type.loopedSound, false);
               data.shouldPlayWarmupSound = false;
            }

            if(this.type.getFireMode(itemstack) == EnumFireMode.MINIGUN && data.minigunSpeed > this.type.minigunStartSpeed) {
               player.inventory.setInventorySlotContents(player.inventory.currentItem, this.tryToShoot(itemstack, this.type, world, player, false));
            }
         } else if(this.type.useLoopingSounds && data.shouldPlayCooldownSound) {
            PacketPlaySound.sendSoundPacket(player.posX, player.posY, player.posZ, 50.0D, player.dimension, this.type.cooldownSound, false);
            data.shouldPlayCooldownSound = false;
         }

         if(this.type.oneHanded && data.offHandGunSlot != 0) {
            ItemStack offHandGunStack = player.inventory.getStackInSlot(data.offHandGunSlot - 1);
            if(offHandGunStack != null && offHandGunStack.getItem() instanceof ItemGun) {
               GunType offHandGunType = ((ItemGun)offHandGunStack.getItem()).type;
               if(offHandGunType.getFireMode(offHandGunStack) == EnumFireMode.BURST && data.burstRoundsRemainingLeft > 0) {
                  player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, this.tryToShoot(offHandGunStack, offHandGunType, world, player, true));
               }

               if(data.isShootingLeft) {
                  if(offHandGunType.getFireMode(offHandGunStack) == EnumFireMode.FULLAUTO) {
                     player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, this.tryToShoot(offHandGunStack, offHandGunType, world, player, true));
                  }

                  if(offHandGunType.useLoopingSounds && data.loopedSoundDelay <= 0 && data.minigunSpeed > 0.1F && !data.reloadingLeft) {
                     data.loopedSoundDelay = data.shouldPlayWarmupSound?offHandGunType.warmupSoundLength:offHandGunType.loopedSoundLength;
                     PacketPlaySound.sendSoundPacket(player.posX, player.posY, player.posZ, 50.0D, player.dimension, data.shouldPlayWarmupSound?offHandGunType.warmupSound:offHandGunType.loopedSound, false);
                     data.shouldPlayWarmupSound = false;
                  }

                  if(offHandGunType.getFireMode(offHandGunStack) == EnumFireMode.MINIGUN && data.minigunSpeed > offHandGunType.minigunStartSpeed) {
                     player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, this.tryToShoot(offHandGunStack, offHandGunType, world, player, true));
                  }
               } else if(offHandGunType.useLoopingSounds && data.shouldPlayCooldownSound) {
                  PacketPlaySound.sendSoundPacket(player.posX, player.posY, player.posZ, 50.0D, player.dimension, offHandGunType.cooldownSound, false);
                  data.shouldPlayCooldownSound = false;
               }
            }
         }
      }

   }

   public void onUpdate(ItemStack itemstack, World world, Entity pEnt, int i, boolean flag) {
      if(world.isRemote) {
         this.onUpdateClient(itemstack, world, pEnt, i, flag);
      } else {
         this.onUpdateServer(itemstack, world, pEnt, i, flag);
      }

      if(pEnt instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)pEnt;
         PlayerData data = PlayerHandler.getPlayerData(player);
         if(data == null) {
            return;
         }

         if(this.type.activateSlowInInventoryLevel != -1) {
            for(int closestEntity = 0; closestEntity < 9; ++closestEntity) {
               ItemStack k = player.inventory.getStackInSlot(closestEntity);
               if(k != null && k.getItem().equals(this)) {
                  ItemGun meleeDamagePoint = (ItemGun)k.getItem();
                  player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 2, meleeDamagePoint.type.activateSlowInInventoryLevel));
                  break;
               }
            }
         }

         if(!this.type.canSetPosition) {
            this.impactX = this.impactY = this.impactZ = 0;
         }

         if(this.lockOnSoundDelay > 0) {
            --this.lockOnSoundDelay;
         }

         Entity var27 = null;
         Vector3f nextPosInPlayerCoords;
         Vector3f nextPosInWorldCoords;
         ItemGun var29;
         if(this.type.lockOnToLivings || this.type.lockOnToMechas || this.type.lockOnToPlanes || this.type.lockOnToPlayers || this.type.lockOnToVehicles) {
            Iterator var28 = player.worldObj.loadedEntityList.iterator();

            while(var28.hasNext()) {
               Object var30 = var28.next();
               Entity nextPos = (Entity)var30;
               Vec3 nextAngles = player.getLookVec();
               double nextAxes = Math.sqrt((nextPos.posX - player.posX) * (nextPos.posX - player.posX) + (nextPos.posY - player.posY) * (nextPos.posY - player.posY) + (nextPos.posZ - player.posZ) * (nextPos.posZ - player.posZ));
               nextPosInPlayerCoords = new Vector3f(nextPos.posX - player.posX, nextPos.posY - player.posY, nextPos.posZ - player.posZ);
               nextPosInWorldCoords = new Vector3f(nextAngles.xCoord, nextAngles.yCoord, nextAngles.zCoord);
               float dPos = Math.abs(Vector3f.angle(nextPosInWorldCoords, nextPosInPlayerCoords));
               if((double)dPos < Math.toRadians((double)this.type.canLockOnAngle) && nextAxes < (double)this.type.maxRangeLockOn) {
                  String hits = nextPos.getEntityData().getString("EntityType");
                  if((this.type.lockOnToMechas && nextPos instanceof EntityMecha || this.type.lockOnToVehicles && nextPos instanceof EntityVehicle || this.type.lockOnToVehicles && hits.equals("Vehicle") || this.type.lockOnToPlanes && nextPos instanceof EntityPlane || this.type.lockOnToPlanes && hits.equals("Plane") || this.type.lockOnToPlayers && nextPos instanceof EntityPlayer || this.type.lockOnToLivings && nextPos instanceof EntityLivingBase) && !data.reloadingRight) {
                     var27 = nextPos;
                  }
               }
            }

            if(var27 != null) {
               var27.getEntityData().setBoolean("LockOn", true);
            }

            if(var27 != null && this.lockOnSoundDelay <= 0 && !player.worldObj.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemGun) {
               var29 = (ItemGun)player.getCurrentEquippedItem().getItem();
               PacketPlaySound.sendSoundPacket(player.posX, player.posY, player.posZ, 10.0D, player.dimension, var29.type.lockOnSound, false);
               this.lockOnSoundDelay = this.type.lockOnSoundTime;
               if(var27 instanceof EntityDriveable && ((EntityDriveable)var27).getDriveableType().hasFlare) {
                  EntityDriveable var32 = (EntityDriveable)var27;
                  PacketPlaySound.sendSoundPacket(var27.posX, var27.posY, var27.posZ, (double)var32.getDriveableType().lockedOnSoundRange, var27.dimension, var32.getDriveableType().lockingOnSound, false);
               }
            }
         }

         if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemGun) {
            var29 = (ItemGun)player.getCurrentEquippedItem().getItem();
            if(var29.type.allowNightVision) {
               player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 2, 0));
            }
         }

         if(data.meleeLength > 0 && this.type.meleePath.size() > 0 && player.inventory.getCurrentItem() == itemstack) {
            for(int var31 = 0; var31 < this.type.meleeDamagePoints.size(); ++var31) {
               Vector3f var33 = (Vector3f)this.type.meleeDamagePoints.get(var31);
               Vector3f var34 = (Vector3f)this.type.meleePath.get((data.meleeProgress + 1) % this.type.meleePath.size());
               Vector3f var35 = (Vector3f)this.type.meleePathAngles.get((data.meleeProgress + 1) % this.type.meleePathAngles.size());
               RotatedAxes var36 = (new RotatedAxes()).rotateGlobalRoll(-var35.x).rotateGlobalPitch(-var35.z).rotateGlobalYaw(-var35.y);
               Vector3f nextPosInGunCoords = var36.findLocalVectorGlobally(var33);
               Vector3f.add(var34, nextPosInGunCoords, nextPosInGunCoords);
               Vector3f.add(new Vector3f(0.0F, 0.0F, 0.0F), nextPosInGunCoords, nextPosInGunCoords);
               nextPosInPlayerCoords = (new RotatedAxes(player.rotationYaw + 90.0F, player.rotationPitch, 0.0F)).findLocalVectorGlobally(nextPosInGunCoords);
               if(!FlansMod.proxy.isThePlayer(player)) {
                  ++nextPosInPlayerCoords.y;
               }

               nextPosInWorldCoords = new Vector3f(player.posX + (double)nextPosInPlayerCoords.x, player.posY + (double)nextPosInPlayerCoords.y, player.posZ + (double)nextPosInPlayerCoords.z);
               Vector3f var37 = data.lastMeleePositions[var31] == null?new Vector3f():Vector3f.sub(nextPosInWorldCoords, data.lastMeleePositions[var31], (Vector3f)null);
               if(player.worldObj.isRemote && FlansMod.DEBUG) {
                  player.worldObj.spawnEntityInWorld(new EntityDebugVector(player.worldObj, data.lastMeleePositions[var31], var37, 200, 1.0F, 0.0F, 0.0F));
               }

               ArrayList var38 = new ArrayList();

               for(int swingDistance = 0; swingDistance < world.loadedEntityList.size(); ++swingDistance) {
                  Object obj = world.loadedEntityList.get(swingDistance);
                  if(obj instanceof EntityPlayer) {
                     EntityPlayer bulletHit = (EntityPlayer)obj;
                     PlayerData entityHit = PlayerHandler.getPlayerData(bulletHit);
                     boolean living = false;
                     if(bulletHit != player) {
                        if(entityHit != null) {
                           if(bulletHit.isDead || entityHit.team == Team.spectators) {
                              continue;
                           }

                           int hitLambda = player instanceof EntityPlayerMP?((EntityPlayerMP)player).ping / 50:0;
                           if(hitLambda >= entityHit.snapshots.length) {
                              hitLambda = entityHit.snapshots.length - 1;
                           }

                           PlayerSnapshot hitPoint = entityHit.snapshots[hitLambda];
                           if(hitPoint == null) {
                              hitPoint = entityHit.snapshots[0];
                           }

                           if(hitPoint == null) {
                              living = true;
                           } else {
                              ArrayList hitLambda1 = hitPoint.raytrace(data.lastMeleePositions[var31] == null?nextPosInWorldCoords:data.lastMeleePositions[var31], var37);
                              var38.addAll(hitLambda1);
                           }
                        }

                        if(entityHit == null || living) {
                           MovingObjectPosition var48 = data.lastMeleePositions[var31] == null?player.boundingBox.calculateIntercept(nextPosInWorldCoords.toVec3(), Vec3.createVectorHelper(0.0D, 0.0D, 0.0D)):player.boundingBox.calculateIntercept(data.lastMeleePositions[var31].toVec3(), nextPosInWorldCoords.toVec3());
                           if(var48 != null) {
                              Vector3f var51 = new Vector3f(var48.hitVec.xCoord - (double)data.lastMeleePositions[var31].x, var48.hitVec.yCoord - (double)data.lastMeleePositions[var31].y, var48.hitVec.zCoord - (double)data.lastMeleePositions[var31].z);
                              float var52 = 1.0F;
                              if(var37.x != 0.0F) {
                                 var52 = var51.x / var37.x;
                              } else if(var37.y != 0.0F) {
                                 var52 = var51.y / var37.y;
                              } else if(var37.z != 0.0F) {
                                 var52 = var51.z / var37.z;
                              }

                              if(var52 < 0.0F) {
                                 var52 = -var52;
                              }

                              var38.add(new PlayerBulletHit(new PlayerHitbox(bulletHit, new RotatedAxes(), new Vector3f(), new Vector3f(), new Vector3f(), EnumHitboxType.BODY), var52));
                           }
                        }
                     }
                  } else {
                     Entity var41 = (Entity)obj;
                     if(var41 != player && !var41.isDead && (var41 instanceof EntityLivingBase || var41 instanceof EntityAAGun)) {
                        MovingObjectPosition var43 = var41.boundingBox.calculateIntercept(data.lastMeleePositions[var31].toVec3(), nextPosInWorldCoords.toVec3());
                        if(var43 != null) {
                           Vector3f var46 = new Vector3f(var43.hitVec.xCoord - (double)data.lastMeleePositions[var31].x, var43.hitVec.yCoord - (double)data.lastMeleePositions[var31].y, var43.hitVec.zCoord - (double)data.lastMeleePositions[var31].z);
                           float var49 = 1.0F;
                           if(var37.x != 0.0F) {
                              var49 = var46.x / var37.x;
                           } else if(var37.y != 0.0F) {
                              var49 = var46.y / var37.y;
                           } else if(var37.z != 0.0F) {
                              var49 = var46.z / var37.z;
                           }

                           if(var49 < 0.0F) {
                              var49 = -var49;
                           }

                           var38.add(new EntityHit(var41, var49));
                        }
                     }
                  }
               }

               if(!var38.isEmpty()) {
                  Collections.sort(var38);
                  float var39 = var37.length();
                  Iterator var40 = var38.iterator();

                  while(var40.hasNext()) {
                     BulletHit var42 = (BulletHit)var40.next();
                     if(var42 instanceof PlayerBulletHit) {
                        PlayerBulletHit var44 = (PlayerBulletHit)var42;
                        float damageMultiplier = 1.0f;
                        switch (var44.hitbox.type) {
                            case LEFTITEM: 
                            case RIGHTITEM: {
                                data.meleeLength = 0;
                                data.meleeProgress = 0;
                                return;
                            }
                            case HEAD: {
                                damageMultiplier = 1.25f;
                                break;
                            }
                            case RIGHTARM: 
                            case LEFTARM: {
                                damageMultiplier = 0.8f;
                                break;
                            }
                        }

                        if(var44.hitbox.player.attackEntityFrom(this.getMeleeDamage(player), var39 * this.type.meleeDamage)) {
                           ++var44.hitbox.player.arrowHitTimer;
                           var44.hitbox.player.hurtResistantTime = var44.hitbox.player.maxHurtResistantTime / 2;
                        }

                        if(FlansMod.DEBUG) {
                           world.spawnEntityInWorld(new EntityDebugDot(world, new Vector3f(data.lastMeleePositions[var31].x + var37.x * var44.intersectTime, data.lastMeleePositions[var31].y + var37.y * var44.intersectTime, data.lastMeleePositions[var31].z + var37.z * var44.intersectTime), 1000, 1.0F, 0.0F, 0.0F));
                        }
                     } else if(var42 instanceof EntityHit) {
                        EntityHit var45 = (EntityHit)var42;
                        if(var45.entity.attackEntityFrom(DamageSource.causePlayerDamage(player), var39 * this.type.meleeDamage) && var45.entity instanceof EntityLivingBase) {
                           EntityLivingBase var50 = (EntityLivingBase)var45.entity;
                           ++var50.arrowHitTimer;
                           var50.hurtResistantTime = var50.maxHurtResistantTime / 2;
                        }

                        if(FlansMod.DEBUG) {
                           world.spawnEntityInWorld(new EntityDebugDot(world, new Vector3f(data.lastMeleePositions[var31].x + var37.x * var45.intersectTime, data.lastMeleePositions[var31].y + var37.y * var45.intersectTime, data.lastMeleePositions[var31].z + var37.z * var45.intersectTime), 1000, 1.0F, 0.0F, 0.0F));
                        }
                     }
                  }
               }

               data.lastMeleePositions[var31] = nextPosInWorldCoords;
            }

            ++data.meleeProgress;
            if(data.meleeProgress == data.meleeLength) {
               data.meleeProgress = data.meleeLength = 0;
            }
         }
      }

   }

   public DamageSource getMeleeDamage(EntityPlayer attacker) {
      return new EntityDamageSourceGun(this.type.shortName, attacker, attacker, this.type, false);
   }

   public void onMouseHeld(ItemStack stack, World world, EntityPlayerMP player, boolean left, boolean isShooting) {
      PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)player);
      if(data != null && data.shootClickDelay == 0) {
         if(player.ridingEntity instanceof EntitySeat && ((EntitySeat)player.ridingEntity).seatInfo.id == 0) {
            return;
         }

         if(left && data.offHandGunSlot != 0) {
            ItemStack offHandGunStack = player.inventory.getStackInSlot(data.offHandGunSlot - 1);
            GunType gunType = ((ItemGun)offHandGunStack.getItem()).type;
            data.isShootingLeft = isShooting;
            if(gunType.getFireMode(offHandGunStack) == EnumFireMode.SEMIAUTO && isShooting) {
               data.isShootingLeft = false;
               player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, this.tryToShoot(offHandGunStack, gunType, world, player, true));
            }

            if(gunType.getFireMode(offHandGunStack) == EnumFireMode.BURST && isShooting && data.burstRoundsRemainingLeft == 0) {
               data.isShootingLeft = false;
               data.burstRoundsRemainingLeft = gunType.numBurstRounds;
               player.inventory.setInventorySlotContents(data.offHandGunSlot - 1, this.tryToShoot(offHandGunStack, gunType, world, player, true));
            }
         } else {
            data.isShootingRight = isShooting;
            if(this.type.getFireMode(stack) == EnumFireMode.SEMIAUTO && isShooting) {
               data.isShootingRight = false;
               player.inventory.setInventorySlotContents(player.inventory.currentItem, this.tryToShoot(stack, this.type, world, player, false));
            }

            if(this.type.getFireMode(stack) == EnumFireMode.BURST && isShooting && data.burstRoundsRemainingRight == 0) {
               data.isShootingRight = false;
               data.burstRoundsRemainingRight = this.type.numBurstRounds;
               player.inventory.setInventorySlotContents(player.inventory.currentItem, this.tryToShoot(stack, this.type, world, player, false));
            }
         }

         if(this.type.useLoopingSounds && isShooting) {
            data.shouldPlayWarmupSound = true;
         }
      }

   }

   public ItemStack tryToShoot(ItemStack gunStack, GunType gunType, World world, EntityPlayerMP entityplayer, boolean left) {
	   
      if(this.type.deployable) {
         return gunStack;
      } else {
         PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)entityplayer);
         if(left && data.shootTimeLeft <= 0 || !left && data.shootTimeRight <= 0 && !entityplayer.isSprinting()) {
            int bulletID = 0;

            ItemStack bulletStack;
            for(bulletStack = null; bulletID < gunType.numAmmoItemsInGun; ++bulletID) {
               ItemStack checkingStack = this.getBulletItemStack(gunStack, bulletID);
               if(checkingStack != null && checkingStack.getItem() != null && checkingStack.getItemDamage() < checkingStack.getMaxDamage()) {
                  bulletStack = checkingStack;
                  break;
               }
            }

            if(bulletStack == null) {
               if(this.reload(gunStack, gunType, world, entityplayer, false, left)) {
                  data.shootTimeRight = data.shootTimeLeft = (int)gunType.getReloadTime(gunStack);
                  if(left) {
                     data.reloadingLeft = true;
                     data.burstRoundsRemainingLeft = 0;
                  } else {
                     data.reloadingRight = true;
                     data.burstRoundsRemainingRight = 0;
                  }

                  FlansMod.getPacketHandler().sendTo((PacketBase)(new PacketReload(left)), entityplayer);
                  if(gunType.reloadSoundOnEmpty != null) {
                     PacketPlaySound.sendSoundPacket(entityplayer.posX, entityplayer.posY, entityplayer.posZ, (double)this.type.reloadSoundRange, entityplayer.dimension, gunType.reloadSoundOnEmpty, true);
                  } else if(gunType.reloadSound != null) {
                     PacketPlaySound.sendSoundPacket(entityplayer.posX, entityplayer.posY, entityplayer.posZ, (double)this.type.reloadSoundRange, entityplayer.dimension, gunType.reloadSound, true);
                  }
               }
            } else if(bulletStack.getItem() instanceof ItemShootable) {
               this.shoot(gunStack, gunType, world, bulletStack, entityplayer, left);
               bulletStack.setItemDamage(bulletStack.getItemDamage() + 1);
               this.setBulletItemStack(gunStack, bulletStack, bulletID);
               if(gunType.getFireMode(gunStack) == EnumFireMode.BURST) {
                  if(left && data.burstRoundsRemainingLeft > 0) {
                     --data.burstRoundsRemainingLeft;
                  }

                  if(!left && data.burstRoundsRemainingRight > 0) {
                     --data.burstRoundsRemainingRight;
                  }
               }

               if(gunType.consumeGunUponUse) {
                  return null;
               }
            }
         }

         return gunStack;
      }
   }

   public boolean reload(ItemStack gunStack, GunType gunType, World world, EntityPlayer player, boolean forceReload, boolean left) {
      return this.reload(gunStack, gunType, world, player, player.inventory, player.capabilities.isCreativeMode, forceReload);
   }

   public boolean reload(ItemStack gunStack, GunType gunType, World world, Entity entity, IInventory inventory, boolean creative, boolean forceReload) {
      if(gunType.deployable) {
         return false;
      } else if(forceReload && !gunType.canForceReload) {
         return false;
      } else {
         boolean reloadedSomething = false;

         for(int i = 0; i < gunType.numAmmoItemsInGun; ++i) {
            ItemStack bulletStack = this.getBulletItemStack(gunStack, i);
            if(bulletStack == null || bulletStack.getItemDamage() == bulletStack.getMaxDamage() || forceReload) {
               int bestSlot = -1;
               int bulletsInBestSlot = 0;

               for(int newBulletStack = 0; newBulletStack < inventory.getSizeInventory(); ++newBulletStack) {
                  ItemStack newBulletType = inventory.getStackInSlot(newBulletStack);
                  if(newBulletType != null && newBulletType.getItem() instanceof ItemShootable && gunType.isAmmo(((ItemShootable)((ItemShootable)newBulletType.getItem())).type)) {
                     int stackToLoad = newBulletType.getMaxDamage() - newBulletType.getItemDamage();
                     if(stackToLoad > bulletsInBestSlot) {
                        bestSlot = newBulletStack;
                        bulletsInBestSlot = stackToLoad;
                     }
                  }
               }

               if(bestSlot != -1) {
                  ItemStack var16 = inventory.getStackInSlot(bestSlot);
                  ShootableType var17 = ((ItemShootable)var16.getItem()).type;
                  if(bulletStack != null && bulletStack.getItem() instanceof ItemShootable && ((ItemShootable)bulletStack.getItem()).type.dropItemOnReload != null && !creative && bulletStack.getItemDamage() == bulletStack.getMaxDamage()) {
                     dropItem(world, entity, ((ItemShootable)bulletStack.getItem()).type.dropItemOnReload);
                  }

                  if(bulletStack != null && bulletStack.getItemDamage() < bulletStack.getMaxDamage() && !InventoryHelper.addItemStackToInventory(inventory, bulletStack, creative)) {
                     entity.entityDropItem(bulletStack, 0.5F);
                  }

                  ItemStack var18 = var16.copy();
                  var18.stackSize = 1;
                  this.setBulletItemStack(gunStack, var18, i);
                  if(!creative) {
                     --var16.stackSize;
                  }

                  if(var16.stackSize <= 0) {
                     var16 = null;
                  }

                  inventory.setInventorySlotContents(bestSlot, var16);
                  reloadedSomething = true;
               }
            }
         }

         return reloadedSomething;
      }
   }

   public static void dropItem(World world, Entity entity, String itemName) {
      if(itemName != null) {
         int damage = 0;
         if(itemName.contains(".")) {
            damage = Integer.parseInt(itemName.split("\\.")[1]);
            itemName = itemName.split("\\.")[0];
         }

         ItemStack dropStack = InfoType.getRecipeElement(itemName, damage);
         entity.entityDropItem(dropStack, 0.5F);
      }

   }

   private void shoot(ItemStack stack, GunType gunType, World world, ItemStack bulletStack, EntityPlayer entityplayer, boolean left) {
      ShootableType bullet = ((ItemShootable)bulletStack.getItem()).type;
      if(this.soundDelay <= 0 && gunType.shootSound != null) {
         AttachmentType itemShootable = gunType.getBarrel(stack);
         boolean shootableType = itemShootable != null && itemShootable.silencer;
         PacketPlaySound.sendSoundPacket(entityplayer.posX, entityplayer.posY, entityplayer.posZ, (double)this.type.gunSoundRange, entityplayer.dimension, gunType.shootSound, gunType.distortSound, shootableType);
         this.soundDelay = gunType.shootSoundLength;
      }

      if(!world.isRemote && bulletStack.getItem() instanceof ItemShootable) {
         ItemShootable var13 = (ItemShootable)bulletStack.getItem();
         ShootableType var14 = var13.type;
         int numBullets = -1;
         float spread = -1.0F;
         if(var14 instanceof BulletType) {
            if(gunType.allowNumBulletsByBulletType) {
               numBullets = ((BulletType)var14).numBullets;
            }

            if(gunType.allowSpreadByBullet) {
               spread = ((BulletType)var14).bulletSpread;
            }
         }

         if(numBullets <= 0) {
            numBullets = gunType.numBullets;
         }

         if(spread <= 0.0F) {
            float k = gunType.getSpread(stack);
            if(entityplayer.isSneaking()) {
               k *= 0.9F;
            }

            if(entityplayer.isSprinting()) {
               k *= 1.75F;
            }

            spread = k;
         }

         for(int var15 = 0; var15 < numBullets; ++var15) {
            world.spawnEntityInWorld(var13.getEntity(world, entityplayer, (entityplayer.isSneaking()?0.7F:1.0F) * spread, gunType.getDamage(stack), gunType.getBulletSpeed(stack), numBullets > 1, bulletStack.getItemDamage(), gunType));
         }

         if(bullet.dropItemOnShoot != null && !entityplayer.capabilities.isCreativeMode) {
            dropItem(world, entityplayer, bullet.dropItemOnShoot);
         }

         if(gunType.dropItemOnShoot != null) {
            dropItem(world, entityplayer, gunType.dropItemOnShoot);
         }
      }

      if(left) {
         PlayerHandler.getPlayerData(entityplayer).shootTimeLeft = gunType.shootDelay;
      } else {
         PlayerHandler.getPlayerData(entityplayer).shootTimeRight = gunType.shootDelay;
      }

      if(gunType.knockback > 0.0F) {
         ;
      }

   }

   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
      if(this.type.deployable) {
         float i = MathHelper.cos(-entityplayer.rotationYaw * 0.01745329F - 3.141593F);
         float sinYaw = MathHelper.sin(-entityplayer.rotationYaw * 0.01745329F - 3.141593F);
         float cosPitch = -MathHelper.cos(-entityplayer.rotationPitch * 0.01745329F);
         float sinPitch = MathHelper.sin(-entityplayer.rotationPitch * 0.01745329F);
         double length = 5.0D;
         Vec3 posVec = Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY + 1.62D - (double)entityplayer.yOffset, entityplayer.posZ);
         Vec3 lookVec = posVec.addVector((double)(sinYaw * cosPitch) * length, (double)sinPitch * length, (double)(i * cosPitch) * length);
         MovingObjectPosition look = world.rayTraceBlocks(posVec, lookVec, true);
         if(look != null && look.typeOfHit == MovingObjectType.BLOCK && look.sideHit == 1) {
            int playerDir = MathHelper.floor_double((double)(entityplayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            int i1 = look.blockX;
            int j = look.blockY;
            int k = look.blockZ;
            if(!world.isRemote) {
               if(world.getBlock(i1, j, k) == Blocks.snow) {
                  --j;
               }

               if(this.isSolid(world, i1, j, k) && (world.getBlock(i1, j + 1, k) == Blocks.air || world.getBlock(i1, j + 1, k) == Blocks.snow) && world.getBlock(i1 + (playerDir == 1?1:0) - (playerDir == 3?1:0), j + 1, k - (playerDir == 0?1:0) + (playerDir == 2?1:0)) == Blocks.air && (world.getBlock(i1 + (playerDir == 1?1:0) - (playerDir == 3?1:0), j, k - (playerDir == 0?1:0) + (playerDir == 2?1:0)) == Blocks.air || world.getBlock(i1 + (playerDir == 1?1:0) - (playerDir == 3?1:0), j, k - (playerDir == 0?1:0) + (playerDir == 2?1:0)) == Blocks.snow)) {
                  Iterator var17 = EntityMG.mgs.iterator();

                  while(var17.hasNext()) {
                     EntityMG mg = (EntityMG)var17.next();
                     if(mg.blockX == i1 && mg.blockY == j + 1 && mg.blockZ == k && !mg.isDead) {
                        return itemstack;
                     }
                  }

                  if(!world.isRemote) {
                     world.spawnEntityInWorld(new EntityMG(world, i1, j + 1, k, playerDir, this.type));
                  }

                  if(!entityplayer.capabilities.isCreativeMode) {
                     itemstack.stackSize = 0;
                  }
               }
            }
         }
      }

      if(world.isRemote) {
         for(int var19 = 0; var19 < 3; ++var19) {
            Minecraft.getMinecraft().entityRenderer.itemRenderer.updateEquippedItem();
         }
      }

      return itemstack;
   }

   private boolean isSolid(World world, int i, int j, int k) {
      Block block = world.getBlock(i, j, k);
      return block != null && block.getMaterial().isSolid() && block.isOpaqueCube();
   }

   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
      return this.type.secondaryFunction != EnumSecondaryFunction.MELEE;
   }

   public boolean isFull3D() {
      return true;
   }

   public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
      if(this.type.meleeSound != null) {
         PacketPlaySound.sendSoundPacket(entityLiving.posX, entityLiving.posY, entityLiving.posZ, (double)this.type.meleeSoundRange, entityLiving.dimension, this.type.meleeSound, true);
      }

      if(this.type.secondaryFunction == EnumSecondaryFunction.CUSTOM_MELEE) {
         if(entityLiving.worldObj.isRemote) {
            GunAnimations data = FlansModClient.getGunAnimations(entityLiving, false);
            data.doMelee(this.type.meleeTime);
         }

         if(entityLiving instanceof EntityPlayer) {
            PlayerData data1 = PlayerHandler.getPlayerData((EntityPlayer)entityLiving);
            data1.doMelee((EntityPlayer)entityLiving, this.type.meleeTime, this.type);
         }
      }

      return this.type.secondaryFunction != EnumSecondaryFunction.MELEE;
   }

   public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player) {
      return true;
   }

   public boolean func_150897_b(Block p_150897_1_) {
      return false;
   }

   @SubscribeEvent
   public void onEventBlockBreak(BreakEvent event) {
      EntityPlayer player = event.getPlayer();
      if(player != null && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemGun) {
         event.setCanceled(true);
      }

   }

   @SideOnly(Side.CLIENT)
   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
      return this.type.colour;
   }

   public boolean isItemStackDamageable() {
      return true;
   }

   public void getSubItems(Item item, CreativeTabs tabs, List list) {
      PaintableType type = ((IPaintableItem)item).GetPaintableType();
      if(FlansMod.addAllPaintjobsToCreative) {
         Iterator var5 = type.paintjobs.iterator();

         while(var5.hasNext()) {
            com.flansmod.common.paintjob.Paintjob paintjob = (com.flansmod.common.paintjob.Paintjob)var5.next();
            this.addPaintjobToList(item, type, paintjob, list);
         }
      } else {
         this.addPaintjobToList(item, type, type.defaultPaintjob, list);
      }

   }

   private void addPaintjobToList(Item item, PaintableType type, com.flansmod.common.paintjob.Paintjob paintjob, List list) {
      ItemStack paintableStack = new ItemStack(item, 1, paintjob.ID);
      NBTTagCompound tags = new NBTTagCompound();
      paintableStack.setTagCompound(tags);
      list.add(paintableStack);
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister icon) {
      this.icons = new IIcon[this.type.paintjobs.size()];
      super.itemIcon = icon.registerIcon("FlansMod:" + this.type.iconPath);

      for(int i = 0; i < this.type.paintjobs.size(); ++i) {
         this.icons[i] = icon.registerIcon("FlansMod:" + ((com.flansmod.common.paintjob.Paintjob)this.type.paintjobs.get(i)).iconName);
      }

   }

   @SideOnly(Side.CLIENT)
   public IIcon getIconIndex(ItemStack stack) {
      return this.icons[stack.getItemDamage()];
   }

   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
      return 100;
   }

   public EnumAction getItemUseAction(ItemStack par1ItemStack) {
      return this.type != null?this.type.itemUseAction:EnumAction.bow;
   }

   public Multimap getAttributeModifiers(ItemStack stack) {
      Multimap map = super.getAttributeModifiers(stack);
      map.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), new AttributeModifier(Item.field_111210_e, "KnockbackResist", (double)this.type.knockbackModifier, 0));
      map.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(Item.field_111210_e, "MovementSpeed", (double)(this.type.getMovementSpeed(stack) - 1.0F), 2));
      if(this.type.secondaryFunction == EnumSecondaryFunction.MELEE) {
         map.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(Item.field_111210_e, "Weapon modifier", (double)this.type.meleeDamage, 0));
      }

      return map;
   }
}
