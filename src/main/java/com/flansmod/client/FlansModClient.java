package com.flansmod.client;

import com.flansmod.api.IControllable;
import com.flansmod.client.ClientProxy;
import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.gui.GuiDriveableController;
import com.flansmod.client.gui.GuiTeamScores;
import com.flansmod.client.model.GunAnimations;
import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.IScope;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.network.PacketTeamInfo;
import com.flansmod.common.teams.Team;
import com.flansmod.common.types.InfoType;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.particle.EntityCritFX;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityDropParticleFX;
import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.client.particle.EntityExplodeFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFireworkSparkFX;
import net.minecraft.client.particle.EntityFishWakeFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityFootStepFX;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.particle.EntityHugeExplodeFX;
import net.minecraft.client.particle.EntityLargeExplodeFX;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.particle.EntityNoteFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.particle.EntitySnowShovelFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.client.particle.EntitySuspendFX;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderPlayerEvent.Pre;
import net.minecraftforge.client.event.RenderPlayerEvent.Specials.Post;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

public class FlansModClient extends FlansMod {

   public static boolean doneTutorial = false;
   public static boolean controlModeMouse = true;
   public static int controlModeSwitchTimer = 20;
   public static int shootTimeLeft;
   public static int shootTimeRight;
   public static float playerRecoilPitch;
   public static float playerRecoilYaw;
   public static float antiRecoilPitch;
   public static float antiRecoilYaw;
   public static int scopeTime;
   public static IScope currentScope = null;
   public static float zoomProgress = 0.0F;
   public static float lastZoomProgress = 0.0F;
   public static float lastZoomLevel = 1.0F;
   public static float lastFOVZoomLevel = 1.0F;
   public static float originalMouseSensitivity = 0.5F;
   public static float originalFOV = 90.0F;
   public static int originalThirdPerson = 0;
   public static boolean inPlane = false;
   public static PacketTeamInfo teamInfo;
   public static int teamsScoreGUILock = 0;
   public static Minecraft minecraft = FMLClientHandler.instance().getClient();


   public void load() {
      log("Loading Flan\'s mod client side.");
      MinecraftForge.EVENT_BUS.register(this);
   }

   @SubscribeEvent
   public void renderOffHandGun(Post event) {
      RenderPlayer renderer = event.renderer;
      EntityPlayer player = event.entityPlayer;
      float dt = event.partialRenderTick;
      PlayerData data = PlayerHandler.getPlayerData(player, Side.CLIENT);
      ItemStack gunStack = null;
      if(player instanceof EntityOtherPlayerMP) {
         gunStack = data.offHandGunStack;
      } else {
         ItemStack gunType = player.getCurrentEquippedItem();
         if(gunType == null || !(gunType.getItem() instanceof ItemGun) || !((ItemGun)gunType.getItem()).type.oneHanded || data.offHandGunSlot == 0) {
            return;
         }

         gunStack = player.inventory.getStackInSlot(data.offHandGunSlot - 1);
      }

      if(gunStack != null && gunStack.getItem() instanceof ItemGun) {
         GunType gunType1 = ((ItemGun)gunStack.getItem()).type;
         GL11.glPushMatrix();
         renderer.modelBipedMain.bipedLeftArm.postRender(0.0625F);
         GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
         float f2 = 1.0F;
         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(-f2, -f2, f2);
         int k = gunStack.getItem().getColorFromItemStack(gunStack, 0);
         float f11 = (float)(k >> 16 & 255) / 255.0F;
         float f12 = (float)(k >> 8 & 255) / 255.0F;
         float f3 = (float)(k & 255) / 255.0F;
         GL11.glColor4f(f11, f12, f3, 1.0F);
         ClientProxy.gunRenderer.renderOffHandGun(player, gunStack);
         GL11.glPopMatrix();
      }
   }

   private float interpolateRotation(float x, float y, float dT) {
      float f3;
      for(f3 = y - x; f3 < -180.0F; f3 += 360.0F) {
         ;
      }

      while(f3 >= 180.0F) {
         f3 -= 360.0F;
      }

      return x + dT * f3;
   }

   @SubscribeEvent
   public void renderLiving(Pre event) {
      PlayerData data = PlayerHandler.getPlayerData(event.entityPlayer, Side.CLIENT);
      if(FlansMod.DEBUG && data != null && data.snapshots[0] != null) {
         data.snapshots[0].renderSnapshot();
      }

      RendererLivingEntity.NAME_TAG_RANGE = 64.0F;
      RendererLivingEntity.NAME_TAG_RANGE_SNEAK = 32.0F;
      if(event.entity instanceof EntityPlayer && teamInfo != null) {
         PacketTeamInfo var10000 = teamInfo;
         if(PacketTeamInfo.gametype != null) {
            PacketTeamInfo var10001 = teamInfo;
            if(!"No Gametype".equals(PacketTeamInfo.gametype)) {
               var10000 = teamInfo;
               PacketTeamInfo.PlayerScoreData rendering = PacketTeamInfo.getPlayerScoreData(event.entity.getCommandSenderName());
               var10000 = teamInfo;
               PacketTeamInfo.PlayerScoreData thePlayer = PacketTeamInfo.getPlayerScoreData(minecraft.thePlayer.getCommandSenderName());
               Team renderingTeam = rendering == null?Team.spectators:rendering.team.team;
               Team thePlayerTeam = thePlayer == null?Team.spectators:thePlayer.team.team;
               if(data.skin == null) {
                  data.skin = ((AbstractClientPlayer)event.entityPlayer).getLocationSkin();
               }

               if(data.skin != null) {
                  ResourceLocation skin = rendering != null && rendering.playerClass != null?FlansModResourceHandler.getTexture(rendering.playerClass):null;
                  ((AbstractClientPlayer)event.entityPlayer).func_152121_a(Type.SKIN, skin == null?data.skin:skin);
               }

               if(thePlayerTeam == Team.spectators) {
                  return;
               }

               if(renderingTeam == Team.spectators) {
                  event.setCanceled(true);
                  return;
               }

               if(renderingTeam != thePlayerTeam) {
                  RendererLivingEntity.NAME_TAG_RANGE = 0.0F;
                  RendererLivingEntity.NAME_TAG_RANGE_SNEAK = 0.0F;
                  return;
               }

               var10000 = teamInfo;
               if(!PacketTeamInfo.sortedByTeam) {
                  RendererLivingEntity.NAME_TAG_RANGE = 0.0F;
                  RendererLivingEntity.NAME_TAG_RANGE_SNEAK = 0.0F;
               }
            }
         }
      }

   }

   public static int shootTime(boolean left) {
      return left?shootTimeLeft:shootTimeRight;
   }

   public static void tick() {
      if(minecraft.thePlayer != null && minecraft.theWorld != null) {
         if(minecraft.thePlayer.ridingEntity instanceof IControllable && minecraft.currentScreen == null) {
            minecraft.displayGuiScreen(new GuiDriveableController((IControllable)minecraft.thePlayer.ridingEntity));
         }

         if(teamInfo != null) {
            PacketTeamInfo var10000 = teamInfo;
            if(PacketTeamInfo.timeLeft > 0) {
               var10000 = teamInfo;
               --PacketTeamInfo.timeLeft;
            }
         }

         if(teamsScoreGUILock > 0) {
            --teamsScoreGUILock;
            if(minecraft.currentScreen == null) {
               minecraft.displayGuiScreen(new GuiTeamScores());
            }
         }

         if(shootTimeLeft > 0) {
            --shootTimeLeft;
         }

         if(shootTimeRight > 0) {
            --shootTimeRight;
         }

         if(scopeTime > 0) {
            --scopeTime;
         }

         if(playerRecoilPitch > 0.0F) {
            playerRecoilPitch *= 0.8F;
         }

         minecraft.thePlayer.rotationPitch -= playerRecoilPitch;
         minecraft.thePlayer.rotationYaw -= playerRecoilYaw;
         antiRecoilPitch += playerRecoilPitch;
         antiRecoilYaw += playerRecoilYaw;
         minecraft.thePlayer.rotationPitch += antiRecoilPitch * 0.2F;
         minecraft.thePlayer.rotationYaw += antiRecoilYaw * 0.2F;
         antiRecoilPitch *= 0.8F;
         antiRecoilYaw *= 0.8F;
         Iterator itemInHand = FlansMod.gunAnimationsRight.values().iterator();

         GunAnimations itemstackInHand;
         while(itemInHand.hasNext()) {
            itemstackInHand = (GunAnimations)itemInHand.next();
            itemstackInHand.update();
         }

         itemInHand = FlansMod.gunAnimationsLeft.values().iterator();

         while(itemInHand.hasNext()) {
            itemstackInHand = (GunAnimations)itemInHand.next();
            itemstackInHand.update();
         }

         itemInHand = minecraft.theWorld.playerEntities.iterator();

         while(itemInHand.hasNext()) {
            Object var9 = itemInHand.next();
            EntityPlayer e = (EntityPlayer)var9;
            ItemStack currentItem = e.getCurrentEquippedItem();
            if(currentItem != null && currentItem.getItem() instanceof ItemGun) {
               if(e == minecraft.thePlayer && minecraft.gameSettings.thirdPersonView == 0) {
                  e.clearItemInUse();
               } else if(currentItem.getItemUseAction() == EnumAction.bow || currentItem.getItemUseAction() == EnumAction.block) {
                  e.setItemInUse(currentItem, 100);
               }
            }
         }

         Item itemInHand1 = null;
 		ItemStack itemstackInHand1 = minecraft.thePlayer.inventory.getCurrentItem();
 		if (itemstackInHand1 != null)
 			itemInHand1 = itemstackInHand1.getItem();
 		if (currentScope != null && (itemInHand1 == null || !(itemInHand1 instanceof ItemGun && ((ItemGun)itemInHand1).type.getCurrentScope(itemstackInHand1) == currentScope && !minecraft.getMinecraft().thePlayer.isSprinting())))
 		{
 			currentScope = null;
 			minecraft.gameSettings.fovSetting = originalFOV;
 			minecraft.gameSettings.mouseSensitivity = originalMouseSensitivity;
 			minecraft.gameSettings.thirdPersonView = originalThirdPerson;
 		}


         lastZoomProgress = zoomProgress;
         if(currentScope == null) {
            zoomProgress *= 0.66F;
         } else {
            zoomProgress = 1.0F - (1.0F - zoomProgress) * 0.66F;
         }

         if(minecraft.thePlayer.ridingEntity instanceof IControllable) {
            inPlane = true;

            try {
               ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, minecraft.entityRenderer, Float.valueOf(((IControllable)minecraft.thePlayer.ridingEntity).getPlayerRoll()), new String[]{"camRoll", "R", "field_78495_O"});
            } catch (Exception var7) {
               log("I forgot to update obfuscated reflection D:");
               throw new RuntimeException(var7);
            }

            if(minecraft.thePlayer.ridingEntity instanceof IControllable) {
               try {
                  ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, minecraft.entityRenderer, Float.valueOf(((IControllable)minecraft.thePlayer.ridingEntity).getCameraDistance()), new String[]{"thirdPersonDistance", "E", "field_78490_B"});
               } catch (Exception var6) {
                  log("I forgot to update obfuscated reflection D:");
                  throw new RuntimeException(var6);
               }
            }
         } else if(inPlane) {
            try {
               ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, minecraft.entityRenderer, Float.valueOf(0.0F), new String[]{"camRoll", "R", "field_78495_O"});
            } catch (Exception var5) {
               log("I forgot to update obfuscated reflection D:");
               throw new RuntimeException(var5);
            }

            try {
               ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, minecraft.entityRenderer, Float.valueOf(4.0F), new String[]{"thirdPersonDistance", "E", "field_78490_B"});
            } catch (Exception var4) {
               log("I forgot to update obfuscated reflection D:");
               throw new RuntimeException(var4);
            }

            inPlane = false;
         }

         if(controlModeSwitchTimer > 0) {
            --controlModeSwitchTimer;
         }

      }
   }

   public static void renderTick(float smoothing) {
      if(Math.abs(zoomProgress - lastZoomProgress) > 1.0E-4F) {
         float actualZoomProgress = lastZoomProgress + (zoomProgress - lastZoomProgress) * smoothing;
         float botchedZoomProgress = zoomProgress > 0.8F?1.0F:0.0F;
         double zoomLevel = (double)(botchedZoomProgress * lastZoomLevel + (1.0F - botchedZoomProgress));
         float FOVZoomLevel = actualZoomProgress * lastFOVZoomLevel + (1.0F - actualZoomProgress);
         if(Math.abs(zoomLevel - 1.0D) < 0.009999999776482582D) {
            zoomLevel = 1.0D;
         }

         try {
            ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, minecraft.entityRenderer, Double.valueOf(zoomLevel), new String[]{"cameraZoom", "af", "field_78503_V"});
            minecraft.gameSettings.fovSetting = ((originalFOV * 40.0F + 70.0F) / FOVZoomLevel - 70.0F) / 40.0F;
         } catch (Exception var7) {
            log("I forgot to update obfuscated reflection D:");
            throw new RuntimeException(var7);
         }
      }

   }

   @SubscribeEvent
   public void chatMessage(ClientChatReceivedEvent event) {
      if(event.message.getUnformattedText().equals("#flansmod")) {
         event.setCanceled(true);
      }

   }

   private boolean checkFileExists(File file) {
      if(!file.exists()) {
         try {
            file.createNewFile();
         } catch (Exception var3) {
            FlansMod.log("Failed to create file");
            FlansMod.log(file.getAbsolutePath());
         }

         return false;
      } else {
         return true;
      }
   }

   public static boolean flipControlMode() {
      if(controlModeSwitchTimer > 0) {
         return false;
      } else {
         controlModeMouse = !controlModeMouse;
         FMLClientHandler.instance().getClient().displayGuiScreen(controlModeMouse?new GuiDriveableController((IControllable)FMLClientHandler.instance().getClient().thePlayer.ridingEntity):null);
         controlModeSwitchTimer = 40;
         return true;
      }
   }

   public static void reloadModels(boolean reloadSkins) {
      Iterator var1 = InfoType.infoTypes.iterator();

      while(var1.hasNext()) {
         InfoType type = (InfoType)var1.next();
         type.reloadModel();
      }

      if(reloadSkins) {
         FlansMod.proxy.forceReload();
      }

   }

   public static Team getTeam(int spawnerTeamID) {
      return teamInfo == null?null:teamInfo.getTeam(spawnerTeamID);
   }

   public static boolean isCurrentMap(String map) {
      boolean var1;
      if(teamInfo != null) {
         PacketTeamInfo var10000 = teamInfo;
         if(PacketTeamInfo.mapShortName != null) {
            var10000 = teamInfo;
            if(PacketTeamInfo.mapShortName.equals(map)) {
               var1 = true;
               return var1;
            }
         }
      }

      var1 = false;
      return var1;
   }

   @SideOnly(Side.CLIENT)
   public static EntityFX getParticle(String s, World w, double x, double y, double z) {
      Minecraft mc = Minecraft.getMinecraft();
      Object fx = null;
      if(s.equals("hugeexplosion")) {
         fx = new EntityHugeExplodeFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("largeexplode")) {
         fx = new EntityLargeExplodeFX(mc.renderEngine, w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("fireworksSpark")) {
         fx = new EntityFireworkSparkFX(w, x, y, z, 0.0D, 0.0D, 0.0D, mc.effectRenderer);
      } else if(s.equals("bubble")) {
         fx = new EntityBubbleFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("suspended")) {
         fx = new EntitySuspendFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("depthsuspend")) {
         fx = new EntityAuraFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("townaura")) {
         fx = new EntityAuraFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("crit")) {
         fx = new EntityCritFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("magicCrit")) {
         fx = new EntityCritFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
         ((EntityFX)fx).setRBGColorF(((EntityFX)fx).getRedColorF() * 0.3F, ((EntityFX)fx).getGreenColorF() * 0.8F, ((EntityFX)fx).getBlueColorF());
         ((EntityFX)fx).nextTextureIndexX();
      } else if(s.equals("smoke")) {
         fx = new EntitySmokeFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("mobSpell")) {
         fx = new EntitySpellParticleFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
         ((EntityFX)fx).setRBGColorF(0.0F, 0.0F, 0.0F);
      } else if(s.equals("mobSpellAmbient")) {
         fx = new EntitySpellParticleFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
         ((EntityFX)fx).setAlphaF(0.15F);
         ((EntityFX)fx).setRBGColorF(0.0F, 0.0F, 0.0F);
      } else if(s.equals("spell")) {
         fx = new EntitySpellParticleFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("instantSpell")) {
         fx = new EntitySpellParticleFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
         ((EntitySpellParticleFX)fx).setBaseSpellTextureIndex(144);
      } else if(s.equals("witchMagic")) {
         fx = new EntitySmokeFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
         ((EntitySpellParticleFX)fx).setBaseSpellTextureIndex(144);
         float k = w.rand.nextFloat() * 0.5F + 0.35F;
         ((EntityFX)fx).setRBGColorF(1.0F * k, 0.0F * k, 1.0F * k);
      } else if(s.equals("note")) {
         fx = new EntityNoteFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("portal")) {
         fx = new EntityPortalFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("enchantmenttable")) {
         fx = new EntityEnchantmentTableParticleFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("explode")) {
         fx = new EntityExplodeFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("flame")) {
         fx = new EntityFlameFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("lava")) {
         fx = new EntityLavaFX(w, x, y, z);
      } else if(s.equals("footstep")) {
         fx = new EntityFootStepFX(mc.renderEngine, w, x, y, z);
      } else if(s.equals("splash")) {
         fx = new EntitySplashFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("wake")) {
         fx = new EntityFishWakeFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("largesmoke")) {
         fx = new EntitySmokeFX(w, x, y, z, 0.0D, 0.0D, 0.0D, 2.5F);
      } else if(s.equals("cloud")) {
         fx = new EntityCloudFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("reddust")) {
         fx = new EntityReddustFX(w, x, y, z, 0.0F, 0.0F, 0.0F);
      } else if(s.equals("snowballpoof")) {
         fx = new EntityBreakingFX(w, x, y, z, Items.snowball);
      } else if(s.equals("dripWater")) {
         fx = new EntityDropParticleFX(w, x, y, z, Material.water);
      } else if(s.equals("dripLava")) {
         fx = new EntityDropParticleFX(w, x, y, z, Material.lava);
      } else if(s.equals("snowshovel")) {
         fx = new EntitySnowShovelFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("slime")) {
         fx = new EntityBreakingFX(w, x, y, z, Items.slime_ball);
      } else if(s.equals("heart")) {
         fx = new EntityHeartFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("angryVillager")) {
         fx = new EntityHeartFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
         ((EntityFX)fx).setParticleTextureIndex(81);
         ((EntityFX)fx).setRBGColorF(1.0F, 1.0F, 1.0F);
      } else if(s.equals("happyVillager")) {
         fx = new EntityAuraFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
         ((EntityFX)fx).setParticleTextureIndex(82);
         ((EntityFX)fx).setRBGColorF(1.0F, 1.0F, 1.0F);
      } else if(s.equals("snowshovel")) {
         fx = new EntitySnowShovelFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("snowshovel")) {
         fx = new EntitySnowShovelFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else if(s.equals("snowshovel")) {
         fx = new EntitySnowShovelFX(w, x, y, z, 0.0D, 0.0D, 0.0D);
      } else {
         String[] astring;
         int k1;
         if(s.startsWith("iconcrack_")) {
            astring = s.split("_", 3);
            int block = Integer.parseInt(astring[1]);
            if(astring.length > 2) {
               k1 = Integer.parseInt(astring[2]);
               fx = new EntityBreakingFX(w, x, y, z, 0.0D, 0.0D, 0.0D, Item.getItemById(block), k1);
            } else {
               fx = new EntityBreakingFX(w, x, y, z, 0.0D, 0.0D, 0.0D, Item.getItemById(block), 0);
            }
         } else {
            Block block1;
            if(s.startsWith("blockcrack_")) {
               astring = s.split("_", 3);
               block1 = Block.getBlockById(Integer.parseInt(astring[1]));
               k1 = Integer.parseInt(astring[2]);
               fx = (new EntityDiggingFX(w, x, y, z, 0.0D, 0.0D, 0.0D, block1, k1)).applyRenderColor(k1);
            } else if(s.startsWith("blockdust_")) {
               astring = s.split("_", 3);
               block1 = Block.getBlockById(Integer.parseInt(astring[1]));
               k1 = Integer.parseInt(astring[2]);
               fx = (new EntityBlockDustFX(w, x, y, z, 0.0D, 0.0D, 0.0D, block1, k1)).applyRenderColor(k1);
            }
         }
      }

      if(mc.gameSettings.fancyGraphics) {
         ((Entity)fx).renderDistanceWeight = 200.0D;
      }

      if(fx != null) {
         mc.effectRenderer.addEffect((EntityFX)fx);
      }

      return (EntityFX)fx;
   }

   public static GunAnimations getGunAnimations(EntityLivingBase living, boolean offHand) {
      GunAnimations animations = null;
      if(offHand) {
         if(FlansMod.gunAnimationsLeft.containsKey(living)) {
            animations = (GunAnimations)FlansMod.gunAnimationsLeft.get(living);
         } else {
            animations = new GunAnimations();
            FlansMod.gunAnimationsLeft.put(living, animations);
         }
      } else if(FlansMod.gunAnimationsRight.containsKey(living)) {
         animations = (GunAnimations)FlansMod.gunAnimationsRight.get(living);
      } else {
         animations = new GunAnimations();
         FlansMod.gunAnimationsRight.put(living, animations);
      }

      return animations;
   }

}
