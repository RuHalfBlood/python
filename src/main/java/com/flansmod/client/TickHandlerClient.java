package com.flansmod.client;

import com.flansmod.client.FlansModClient;
import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.KeyInputHandler;
import com.flansmod.client.gui.GuiDriveableController;
import com.flansmod.client.gui.GuiTeamScores;
import com.flansmod.client.model.RenderFlag;
import com.flansmod.client.model.RenderGun;
import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.network.PacketTeamInfo;
import com.flansmod.common.teams.ItemTeamArmour;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3f;
import com.flansmod.common.vector.Vector3i;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public class TickHandlerClient extends Gui {

   public static final ResourceLocation offHand = new ResourceLocation("flansmod", "gui/offHand.png");
   public static ArrayList blockLightOverrides = new ArrayList();
   public static ArrayList vehicleLightOverrides = new ArrayList();
   public static int lightOverrideRefreshRate = 5;
   public static int vehicleLightOverrideRefreshRate = 1;
   int tickcount = 0;
   int tickcountflash = 0;
   public static double muzzleTick = 0.0;
   int tickcountWounded = 0;
   boolean isInFlash;
   int flashTime;
   EntityPlayer entityPlayerFlash;
   private static GuiScreen guiDriveableController = null;
   private static RenderItem itemRenderer = new RenderItem();
   private static List killMessages = new ArrayList();

   private float lineOffset;

   private Entity pointedEntity;


   public TickHandlerClient() {
      FMLCommonHandler.instance().bus().register(this);
      MinecraftForge.EVENT_BUS.register(this);
   }

   @SubscribeEvent
   public void eventHandler(MouseEvent event) {
      EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
      if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ItemGun && ((ItemGun)player.getCurrentEquippedItem().getItem()).type.oneHanded && Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode()) && Math.abs(event.dwheel) > 0) {
         event.setCanceled(true);
      }

   }
  /* @SubscribeEvent
   public void renderCrosshair(Pre event) {
      Minecraft mc = Minecraft.getMinecraft();
      ScaledResolution scaledresolution = new ScaledResolution(FlansModClient.minecraft, FlansModClient.minecraft.displayWidth, FlansModClient.minecraft.displayHeight);
      int w = scaledresolution.getScaledWidth();
      int h = scaledresolution.getScaledHeight();
      ItemStack item = Minecraft.getMinecraft().thePlayer.getHeldItem();
      if(item != null && item.getItem() instanceof ItemGun && item.stackTagCompound != null && event.type.equals(ElementType.CROSSHAIRS)) {
         event.setCanceled(true);
         byte target = 0;
     
         EntityClientPlayerMP player = mc.thePlayer;
      

   


         int burst = 0;
        

         if(burst >= 1) {
            if(burst > 3) {
               burst = 3;
            }

            this.lineOffset = (float)(7 + burst * 3);
         } else if(player.motionX <= 0.0D && player.motionY <= 0.0D && player.motionZ <= 0.0D && player.motionX >= 0.0D && player.motionZ >= 0.0D) {
            if(!player.isSneaking()) {
               if(this.lineOffset > 3.0F) {
                  this.lineOffset -= 0.25F;
               } else {
                  this.lineOffset = 3.0F;
               }
            } else if(player.isSneaking()) {
               if(this.lineOffset > 0.0F) {
                  this.lineOffset -= 0.25F;
               } else if(this.lineOffset < 0.0F) {
                  this.lineOffset = 0.0F;
               }
            } else if(this.lineOffset > 1.0F) {
               this.lineOffset -= 0.25F;
            } else {
               this.lineOffset = 1.0F;
            }
         } else if(this.lineOffset > 8.0F) {
            this.lineOffset -= 0.25F;
         } else {
            this.lineOffset = 8.0F;
         }

         GL11.glPushMatrix();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(2896);
         GL11.glScalef(0.5F, 0.5F, 0.5F);
         mc.renderEngine.bindTexture(new ResourceLocation("flansmod", "textures/crosshair.png"));
         int var33 = Math.round(this.lineOffset);
         this.drawTexturedModalRect(w - 1, h - 14 - var33, 15 + target, 1, 2, 12);
         this.drawTexturedModalRect(w - 14 - var33, h, 1 + target, 15, 12, 2);
         this.drawTexturedModalRect(w + 3 + var33, h, 19 + target, 15, 12, 2);
         this.drawTexturedModalRect(w - 1, h + 3 + var33, 15 + target, 19, 2, 12);
         GL11.glPopMatrix();
         mc.renderEngine.bindTexture(new ResourceLocation("minecraft", "textures/gui/icons.png"));
      }

   }*/



   @SubscribeEvent
   public void eventHandler(RenderGameOverlayEvent event) {
      Minecraft mc = Minecraft.getMinecraft();
      //РендерКроссхаира
    if(event.type == ElementType.CROSSHAIRS && mc.thePlayer != null && mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemGun && (!((ItemGun)mc.thePlayer.getHeldItem().getItem()).type.showCrosshair || FlansModClient.currentScope != null)) {
       event.setCanceled(true);
      } else {
         ScaledResolution scaledresolution = new ScaledResolution(FlansModClient.minecraft, FlansModClient.minecraft.displayWidth, FlansModClient.minecraft.displayHeight);
         int i = scaledresolution.getScaledWidth();
         int j = scaledresolution.getScaledHeight();
         Tessellator tessellator = Tessellator.instance;
         ItemStack currentStack;
         if(!event.isCancelable() && event.type == ElementType.HELMET) {
            String teamInfo = null;
            if(FlansModClient.currentScope != null && FlansModClient.currentScope.hasZoomOverlay() && FMLClientHandler.instance().getClient().currentScreen == null && FlansModClient.zoomProgress > 0.8F) {
               teamInfo = FlansModClient.currentScope.getZoomOverlay();
            } else if(mc.thePlayer != null) {
               currentStack = mc.thePlayer.inventory.armorInventory[3];
               if(currentStack != null && currentStack.getItem() instanceof ItemTeamArmour) {
                  teamInfo = ((ItemTeamArmour)currentStack.getItem()).type.overlay;
               }
            }
           
            if(teamInfo != null) {
               FlansModClient.minecraft.entityRenderer.setupOverlayRendering();
               GL11.glEnable(3042);
               GL11.glDisable(2929);
               GL11.glDepthMask(false);
               GL11.glBlendFunc(770, 771);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glDisable(3008);
               mc.renderEngine.bindTexture(FlansModResourceHandler.getScope(teamInfo));
               tessellator.startDrawingQuads();
               tessellator.addVertexWithUV((double)(i / 2 - 2 * j), (double)j, -90.0D, 0.0D, 1.0D);
               tessellator.addVertexWithUV((double)(i / 2 + 2 * j), (double)j, -90.0D, 1.0D, 1.0D);
               tessellator.addVertexWithUV((double)(i / 2 + 2 * j), 0.0D, -90.0D, 1.0D, 0.0D);
               tessellator.addVertexWithUV((double)(i / 2 - 2 * j), 0.0D, -90.0D, 0.0D, 0.0D);
               tessellator.draw();
               GL11.glDepthMask(true);
               GL11.glEnable(2929);
               GL11.glEnable(3008);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }
         }

         if(!event.isCancelable() && event.type == ElementType.HOTBAR) {
            int ent;
            if(mc.thePlayer != null) {
               ItemStack var17 = mc.thePlayer.inventory.getCurrentItem();
               if(var17 != null && var17.getItem() instanceof ItemGun) {
                  ItemGun var18 = (ItemGun)var17.getItem();
                  GunType data = var18.type;
                  ent = 0;

                  ItemStack healthP;
                  for(int speed = 0; speed < data.numAmmoItemsInGun; ++speed) {
                     healthP = ((ItemGun)var17.getItem()).getBulletItemStack(var17, speed);
                     if(healthP != null && healthP.getItem() != null && healthP.getItemDamage() < healthP.getMaxDamage()) {
                        RenderHelper.enableGUIStandardItemLighting();
                        GL11.glEnable('\u803a');
                        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
                        this.drawSlotInventory(mc.fontRenderer, healthP, i / 2 + 16 + ent, j - 65);
                        GL11.glDisable('\u803a');
                        RenderHelper.disableStandardItemLighting();
                        String colour = healthP.getMaxDamage() - healthP.getItemDamage() + "/" + healthP.getMaxDamage();
                        if(data.submode.length >= 2) {
                           colour = colour + "[" + data.getFireMode(var17) + "]";
                        }

                        if(healthP.getMaxDamage() == 1) {
                           colour = "";
                        }

                        mc.fontRenderer.drawString(colour, i / 2 + 32 + ent, j - 59, 0);
                        mc.fontRenderer.drawString(colour, i / 2 + 33 + ent, j - 60, 16777215);
                        ent += 16 + mc.fontRenderer.getStringWidth(colour);
                     }
                  }

                  PlayerData var26 = PlayerHandler.getPlayerData((EntityPlayer)mc.thePlayer, Side.CLIENT);
                  if(data.oneHanded && var26.offHandGunSlot != 0) {
                     healthP = mc.thePlayer.inventory.getStackInSlot(var26.offHandGunSlot - 1);
                     if(healthP != null && healthP.getItem() instanceof ItemGun) {
                        GunType var31 = ((ItemGun)healthP.getItem()).type;
                        ent = 0;

                        for(int entP = 0; entP < var31.numAmmoItemsInGun; ++entP) {
                           ItemStack up2 = ((ItemGun)healthP.getItem()).getBulletItemStack(healthP, entP);
                           if(up2 != null && up2.getItem() != null && up2.getItemDamage() < up2.getMaxDamage()) {
                              String s = up2.getMaxDamage() - up2.getItemDamage() + "/" + up2.getMaxDamage();
                              if(data.submode.length >= 2) {
                                 s = s + "[" + data.getFireMode(healthP) + "]";
                              }

                              if(up2.getMaxDamage() == 1) {
                                 s = "";
                              }

                              RenderHelper.enableGUIStandardItemLighting();
                              GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                              GL11.glEnable('\u803a');
                              OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
                              this.drawSlotInventory(mc.fontRenderer, up2, i / 2 - 32 - ent, j - 65);
                              ent += 16 + mc.fontRenderer.getStringWidth(s);
                              GL11.glDisable('\u803a');
                              RenderHelper.disableStandardItemLighting();
                              mc.fontRenderer.drawString(s, i / 2 - 16 - ent, j - 59, 0);
                              mc.fontRenderer.drawString(s, i / 2 - 17 - ent, j - 60, 16777215);
                           }
                        }
                     }
                  }
               }
            }

            PacketTeamInfo var19 = FlansModClient.teamInfo;
            if(var19 != null && FlansModClient.minecraft.thePlayer != null && (PacketTeamInfo.numTeams > 0 || !PacketTeamInfo.sortedByTeam) && PacketTeamInfo.getPlayerScoreData(FlansModClient.minecraft.thePlayer.getCommandSenderName()) != null) {
               GL11.glEnable(3042);
               GL11.glDisable(2929);
               GL11.glDepthMask(false);
               GL11.glBlendFunc(770, 771);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glDisable(3008);
               mc.renderEngine.bindTexture(GuiTeamScores.texture);
               tessellator.startDrawingQuads();
               tessellator.addVertexWithUV((double)(i / 2 - 43), 27.0D, -90.0D, 0.33203125D, 0.10546875D);
               tessellator.addVertexWithUV((double)(i / 2 + 43), 27.0D, -90.0D, 0.66796875D, 0.10546875D);
               tessellator.addVertexWithUV((double)(i / 2 + 43), 0.0D, -90.0D, 0.66796875D, 0.0D);
               tessellator.addVertexWithUV((double)(i / 2 - 43), 0.0D, -90.0D, 0.33203125D, 0.0D);
               tessellator.draw();
               int var20;
               if(PacketTeamInfo.numTeams == 2 && PacketTeamInfo.sortedByTeam) {
                  var20 = PacketTeamInfo.teamData[0].team.teamColour;
                  GL11.glColor4f((float)(var20 >> 16 & 255) / 256.0F, (float)(var20 >> 8 & 255) / 256.0F, (float)(var20 & 255) / 256.0F, 1.0F);
                  tessellator.startDrawingQuads();
                  tessellator.addVertexWithUV((double)(i / 2 - 43), 27.0D, -90.0D, 0.0D, 0.48828125D);
                  tessellator.addVertexWithUV((double)(i / 2 - 19), 27.0D, -90.0D, 0.09375D, 0.48828125D);
                  tessellator.addVertexWithUV((double)(i / 2 - 19), 0.0D, -90.0D, 0.09375D, 0.3828125D);
                  tessellator.addVertexWithUV((double)(i / 2 - 43), 0.0D, -90.0D, 0.0D, 0.3828125D);
                  tessellator.draw();
                  var20 = PacketTeamInfo.teamData[1].team.teamColour;
                  GL11.glColor4f((float)(var20 >> 16 & 255) / 256.0F, (float)(var20 >> 8 & 255) / 256.0F, (float)(var20 & 255) / 256.0F, 1.0F);
                  tessellator.startDrawingQuads();
                  tessellator.addVertexWithUV((double)(i / 2 + 19), 27.0D, -90.0D, 0.2421875D, 0.48828125D);
                  tessellator.addVertexWithUV((double)(i / 2 + 43), 27.0D, -90.0D, 0.3359375D, 0.48828125D);
                  tessellator.addVertexWithUV((double)(i / 2 + 43), 0.0D, -90.0D, 0.3359375D, 0.3828125D);
                  tessellator.addVertexWithUV((double)(i / 2 + 19), 0.0D, -90.0D, 0.2421875D, 0.3828125D);
                  tessellator.draw();
                  GL11.glDepthMask(true);
                  GL11.glEnable(2929);
                  GL11.glEnable(3008);
                  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                  mc.fontRenderer.drawString(PacketTeamInfo.teamData[0].score + "", i / 2 - 35, 9, 0);
                  mc.fontRenderer.drawString(PacketTeamInfo.teamData[0].score + "", i / 2 - 36, 8, 16777215);
                  mc.fontRenderer.drawString(PacketTeamInfo.teamData[1].score + "", i / 2 + 35 - mc.fontRenderer.getStringWidth(PacketTeamInfo.teamData[1].score + ""), 9, 0);
                  mc.fontRenderer.drawString(PacketTeamInfo.teamData[1].score + "", i / 2 + 34 - mc.fontRenderer.getStringWidth(PacketTeamInfo.teamData[1].score + ""), 8, 16777215);
               }

               mc.fontRenderer.drawString(PacketTeamInfo.gametype + "", i / 2 + 48, 9, 0);
               mc.fontRenderer.drawString(PacketTeamInfo.gametype + "", i / 2 + 47, 8, 16777215);
               mc.fontRenderer.drawString(PacketTeamInfo.map + "", i / 2 - 47 - mc.fontRenderer.getStringWidth(PacketTeamInfo.map + ""), 9, 0);
               mc.fontRenderer.drawString(PacketTeamInfo.map + "", i / 2 - 48 - mc.fontRenderer.getStringWidth(PacketTeamInfo.map + ""), 8, 16777215);
               var20 = PacketTeamInfo.timeLeft / 20;
               int var21 = var20 / 60;
               var20 %= 60;
               String var25 = var21 + ":" + (var20 < 10?"0" + var20:Integer.valueOf(var20));
               mc.fontRenderer.drawString(var25, i / 2 - mc.fontRenderer.getStringWidth(var25) / 2 - 1, 29, 0);
               mc.fontRenderer.drawString(var25, i / 2 - mc.fontRenderer.getStringWidth(var25) / 2, 30, 16777215);
               GL11.glDepthMask(true);
               GL11.glEnable(2929);
               GL11.glEnable(3008);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               String var27 = FlansModClient.minecraft.thePlayer.getCommandSenderName();
               mc.fontRenderer.drawString(PacketTeamInfo.getPlayerScoreData(var27).score + "", i / 2 - 7, 1, 0);
               mc.fontRenderer.drawString(PacketTeamInfo.getPlayerScoreData(var27).kills + "", i / 2 - 7, 9, 0);
               mc.fontRenderer.drawString(PacketTeamInfo.getPlayerScoreData(var27).deaths + "", i / 2 - 7, 17, 0);
            }

            Iterator var23 = killMessages.iterator();

            TickHandlerClient.KillMessage var22;


            RenderHelper.enableGUIStandardItemLighting();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable('\u803a');
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 255.0F, 255.0F);
            var23 = killMessages.iterator();

            while(var23.hasNext()) {
               var22 = (TickHandlerClient.KillMessage)var23.next();
               mc.fontRenderer.drawString("§" + var22.killerName + "     §" + var22.killedName, i - mc.fontRenderer.getStringWidth(var22.killerName + "     " + var22.killedName), 2 + var22.line * 16, 16777215);
               this.drawSlotInventory(mc.fontRenderer, new ItemStack(var22.weapon.item), i - mc.fontRenderer.getStringWidth("     " + var22.killedName) - 6, -2 + var22.line * 16);
            }

            GL11.glDisable(3042);
            RenderHelper.disableStandardItemLighting();
            mc.renderEngine.bindTexture(offHand);
            currentStack = mc.thePlayer.inventory.getCurrentItem();
            PlayerData var24 = PlayerHandler.getPlayerData((EntityPlayer)mc.thePlayer, Side.CLIENT);
            if(currentStack != null && currentStack.getItem() instanceof ItemGun && ((ItemGun)currentStack.getItem()).type.oneHanded) {
               for(ent = 0; ent < 9; ++ent) {
                  if(var24.offHandGunSlot == ent + 1) {
                     tessellator.startDrawingQuads();
                     tessellator.addVertexWithUV((double)(i / 2 - 88 + 20 * ent), (double)(j - 3), -90.0D, 0.25D, 0.5D);
                     tessellator.addVertexWithUV((double)(i / 2 - 72 + 20 * ent), (double)(j - 3), -90.0D, 0.5D, 0.5D);
                     tessellator.addVertexWithUV((double)(i / 2 - 72 + 20 * ent), (double)(j - 19), -90.0D, 0.5D, 0.0D);
                     tessellator.addVertexWithUV((double)(i / 2 - 88 + 20 * ent), (double)(j - 19), -90.0D, 0.25D, 0.0D);
                     tessellator.draw();
                  } else if(var24.isValidOffHandWeapon(mc.thePlayer, ent + 1)) {
                     tessellator.startDrawingQuads();
                     tessellator.addVertexWithUV((double)(i / 2 - 88 + 20 * ent), (double)(j - 3), -90.0D, 0.0D, 0.5D);
                     tessellator.addVertexWithUV((double)(i / 2 - 72 + 20 * ent), (double)(j - 3), -90.0D, 0.25D, 0.5D);
                     tessellator.addVertexWithUV((double)(i / 2 - 72 + 20 * ent), (double)(j - 19), -90.0D, 0.25D, 0.0D);
                     tessellator.addVertexWithUV((double)(i / 2 - 88 + 20 * ent), (double)(j - 19), -90.0D, 0.0D, 0.0D);
                     tessellator.draw();
                  }
               }
            }

            if(EntityBullet.hitCrossHair) {
               this.tickcount = 20;
               EntityBullet.hitCrossHair = false;
            }

            if(this.tickcount > 0) {
               FlansModClient.minecraft.entityRenderer.setupOverlayRendering();
               GL11.glEnable(3042);
               GL11.glDisable(2929);
               GL11.glDepthMask(false);
               GL11.glBlendFunc(770, 771);
               GL11.glColor4f(FlansMod.hitCrossHairColor[1], FlansMod.hitCrossHairColor[2], FlansMod.hitCrossHairColor[3], FlansMod.hitCrossHairColor[0] * (float)this.tickcount / 20.0F);
               GL11.glDisable(3008);
               mc.renderEngine.bindTexture(new ResourceLocation("flansmod", "gui/CrossHair.png"));
               tessellator.startDrawingQuads();
               tessellator.addVertexWithUV((double)(i / 2 - 2 * j), (double)j, -90.0D, 0.0D, 1.0D);
               tessellator.addVertexWithUV((double)(i / 2 + 2 * j), (double)j, -90.0D, 1.0D, 1.0D);
               tessellator.addVertexWithUV((double)(i / 2 + 2 * j), 0.0D, -90.0D, 1.0D, 0.0D);
               tessellator.addVertexWithUV((double)(i / 2 - 2 * j), 0.0D, -90.0D, 0.0D, 0.0D);
               tessellator.draw();
               GL11.glDepthMask(true);
               GL11.glEnable(2929);
               GL11.glEnable(3008);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            if(mc.thePlayer.hurtTime > 0) {
               this.tickcountWounded = 40;
            }

            if(this.tickcountWounded > 0) {
               FlansModClient.minecraft.entityRenderer.setupOverlayRendering();
               GL11.glEnable(3042);
               GL11.glDisable(2929);
               GL11.glDepthMask(false);
               GL11.glBlendFunc(770, 771);
               GL11.glColor4f(FlansMod.hitCrossHairColor[1], FlansMod.hitCrossHairColor[2], FlansMod.hitCrossHairColor[3], FlansMod.hitCrossHairColor[0] * (float)this.tickcountWounded / 20.0F);
               GL11.glDisable(3008);
               mc.renderEngine.bindTexture(new ResourceLocation("flansmod", "gui/Blood.png"));
               tessellator.startDrawingQuads();
               tessellator.addVertexWithUV((double)(i / 2 - 2 * j), (double)j, -90.0D, 0.0D, 1.0D);
               tessellator.addVertexWithUV((double)(i / 2 + 2 * j), (double)j, -90.0D, 1.0D, 1.0D);
               tessellator.addVertexWithUV((double)(i / 2 + 2 * j), 0.0D, -90.0D, 1.0D, 0.0D);
               tessellator.addVertexWithUV((double)(i / 2 - 2 * j), 0.0D, -90.0D, 0.0D, 0.0D);
               tessellator.draw();
               GL11.glDepthMask(true);
               GL11.glEnable(2929);
               GL11.glEnable(3008);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            if(mc.thePlayer.ridingEntity instanceof EntitySeat) {
               EntityDriveable var28 = ((EntitySeat)mc.thePlayer.ridingEntity).driveable;
               float var29 = (float)(var28.motionX * var28.motionX + var28.motionY * var28.motionY + var28.motionZ * var28.motionZ);
               int var30 = (int)((float)((DriveablePart)var28.getDriveableData().parts.get(EnumDriveablePart.core)).health / (float)((DriveablePart)var28.getDriveableData().parts.get(EnumDriveablePart.core)).maxHealth * 100.0F);
               boolean var32 = false;
               int var33;
               if(var30 > 75) {
                  var33 = 16777215;
               } else if(var30 < 75 && var30 > 50) {
                  var33 = '\uff00';
               } else if(var30 < 50 && var30 > 25) {
                  var33 = 14329120;
               } else {
                  var33 = 16711680;
               }

               mc.fontRenderer.drawString(String.format("Throttle : %.0f%%", new Object[]{Float.valueOf(var28.throttle * 100.0F)}), 2, 2, 16777215);
               mc.fontRenderer.drawString("Health : " + var30 + "%", 2, 12, var33);
               mc.fontRenderer.drawString(String.format("Speed : %.2f", new Object[]{Double.valueOf(Math.sqrt((double)var29))}), 2, 22, 16777215);
               mc.fontRenderer.drawString(String.format("Yaw : %.0f%%", new Object[]{Float.valueOf(((EntitySeat)mc.thePlayer.ridingEntity).looking.getYaw())}), 92, 2, 16777215);
               mc.fontRenderer.drawString(String.format("Pitch : %.0f%%", new Object[]{Float.valueOf(((EntitySeat)mc.thePlayer.ridingEntity).looking.getPitch())}), 92, 12, 16777215);
               if(var28 instanceof EntityPlane) {
                  if(var28.getDriveableType().hasFlare) {
                     if(var28.ticksFlareUsing <= 0 && var28.flareDelay <= 0) {
                        mc.fontRenderer.drawString("Flare : READY", 2, 32, '\uff00');
                     }

                     if(var28.ticksFlareUsing > 0) {
                        mc.fontRenderer.drawString("Flare : Deploying", 2, 42, 16711680);
                     }

                     if(var28.flareDelay > 0) {
                        mc.fontRenderer.drawString("Flare : Reloading", 2, 52, 14329120);
                     }
                  }

                  Vector3f var34 = (Vector3f)var28.axes.getYAxis().normalise();
                  mc.fontRenderer.drawString(String.format("Lift : %.0f%%", new Object[]{Float.valueOf((float)var28.getSpeedXYZ() * (float)var28.getSpeedXYZ() * var34.y)}), 92, 22, 16777215);
               }

               if(var28 instanceof EntityVehicle) {
                  if(var28.getDriveableType().hasFlare) {
                     if(var28.ticksFlareUsing <= 0 && var28.flareDelay <= 0) {
                        mc.fontRenderer.drawString("Smoke : READY", 2, 32, '\uff00');
                     }

                     if(var28.ticksFlareUsing > 0) {
                        mc.fontRenderer.drawString("Smoke : Deploying", 2, 42, 16711680);
                     }

                     if(var28.flareDelay > 0) {
                        mc.fontRenderer.drawString("Smoke : Reloading", 2, 52, 14329120);
                     }
                  }

                  if(((EntityVehicle)var28).getVehicleType().shootWithOpenDoor) {
                     if(((EntityVehicle)var28).varDoor) {
                        mc.fontRenderer.drawString("Weapon : READY", 2, 62, '\uff00');
                        mc.fontRenderer.drawString("[" + Keyboard.getKeyName(KeyInputHandler.doorKey.getKeyCode()) + " to disable]", 100, 62, '\uff00');
                     }

                     if(!((EntityVehicle)var28).varDoor) {
                        mc.fontRenderer.drawString("Weapon : DISABLED", 2, 62, 16711680);
                        mc.fontRenderer.drawString("[" + Keyboard.getKeyName(KeyInputHandler.doorKey.getKeyCode()) + " to activate]", 100, 62, 16711680);
                     }
                  }
               }

               if(FlansMod.DEBUG) {
                  mc.fontRenderer.drawString("MotionX : " + var28.motionX, 2, 32, 16777215);
                  mc.fontRenderer.drawString("MotionY : " + var28.motionY, 2, 42, 16777215);
                  mc.fontRenderer.drawString("MotionZ : " + var28.motionZ, 2, 52, 16777215);
                  mc.fontRenderer.drawString("Break Blocks : " + TeamsManager.driveablesBreakBlocks, 2, 62, 16777215);
               }
            }
         }

      }
   }



   @SubscribeEvent
   public void renderTick(TickEvent.RenderTickEvent event) {
       switch (event.phase) {
           case START: {
               RenderGun.smoothing = event.renderTickTime;
               this.renderTickStart(Minecraft.getMinecraft(), event.renderTickTime);
               break;
           }
           case END: {
               this.renderTickEnd(Minecraft.getMinecraft());
           }
       }
   }

   @SubscribeEvent
   public void clientTick(TickEvent.ClientTickEvent event) {

       if (muzzleTick >= 1.0) {
           // empty if block
       }
       if ((muzzleTick -= 0.5) <= 0.0) {
           muzzleTick = 0.0;
       }

       switch (event.phase) {
           case START: {
               this.clientTickStart(Minecraft.getMinecraft());
               break;
           }
           case END: {
               this.clientTickEnd(Minecraft.getMinecraft());
           }
       }
   }


   public void clientTickStart(Minecraft mc) {
      if(this.tickcount > 0) {
         --this.tickcount;
      }

      if(this.tickcountWounded > 0) {
         --this.tickcountWounded;
      }

      Iterator var2;
      Vector3i v;
      if(FlansMod.ticker % lightOverrideRefreshRate == 0 && mc.theWorld != null) {
         lightOverrideRefreshRate = mc.gameSettings.fancyGraphics?10:20;
         var2 = blockLightOverrides.iterator();

         while(var2.hasNext()) {
            v = (Vector3i)var2.next();
            mc.theWorld.updateLightByType(EnumSkyBlock.Block, v.x, v.y, v.z);
         }

         blockLightOverrides.clear();
         var2 = mc.theWorld.playerEntities.iterator();

         int i;
         int k;
         int xd;
         int yd;
         int zd;
         Object var14;
         while(var2.hasNext()) {
            var14 = var2.next();
            EntityPlayer mecha = (EntityPlayer)var14;
            ItemStack x = mecha.getCurrentEquippedItem();
            if(x != null && x.getItem() instanceof ItemGun) {
               GunType y = ((ItemGun)x.getItem()).type;
               AttachmentType z = y.getGrip(x);
               if(z != null && z.flashlight) {
                  for(i = 0; i < 2; ++i) {
                     MovingObjectPosition j = mecha.rayTrace((double)(z.flashlightRange / 2.0F * (float)(i + 1)), 1.0F);
                     if(j != null) {
                        k = j.blockX;
                        xd = j.blockY;
                        yd = j.blockZ;
                        zd = j.sideHit;
                        switch(zd) {
                        case 0:
                           --xd;
                           break;
                        case 1:
                           ++xd;
                           break;
                        case 2:
                           --yd;
                           break;
                        case 3:
                           ++yd;
                           break;
                        case 4:
                           --k;
                           break;
                        case 5:
                           ++k;
                        }

                        blockLightOverrides.add(new Vector3i(k, xd, yd));
                        mc.theWorld.setLightValue(EnumSkyBlock.Block, k, xd, yd, 12);
                        mc.theWorld.updateLightByType(EnumSkyBlock.Block, k, xd + 1, yd);
                        mc.theWorld.updateLightByType(EnumSkyBlock.Block, k, xd - 1, yd);
                        mc.theWorld.updateLightByType(EnumSkyBlock.Block, k + 1, xd, yd);
                        mc.theWorld.updateLightByType(EnumSkyBlock.Block, k - 1, xd, yd);
                        mc.theWorld.updateLightByType(EnumSkyBlock.Block, k, xd, yd + 1);
                        mc.theWorld.updateLightByType(EnumSkyBlock.Block, k, xd, yd - 1);
                     }
                  }
               }
            }
         }

         var2 = mc.theWorld.loadedEntityList.iterator();

         while(var2.hasNext()) {
            var14 = var2.next();
            if(var14 instanceof EntityBullet) {
               EntityBullet var16 = (EntityBullet)var14;
               if((var16.isDead || !var16.type.hasLight) && var16.isDead && var16.type.hasLight) {
                  ;
               }
            } else if(var14 instanceof EntityMecha) {
               EntityMecha var15 = (EntityMecha)var14;
               int var17 = MathHelper.floor_double(var15.posX);
               int var18 = MathHelper.floor_double(var15.posY);
               int var19 = MathHelper.floor_double(var15.posZ);
               if(var15.lightLevel() > 0) {
                  blockLightOverrides.add(new Vector3i(var17, var18, var19));
                  mc.theWorld.setLightValue(EnumSkyBlock.Block, var17, var18, var19, Math.max(mc.theWorld.getBlockLightValue(var17, var18, var19), var15.lightLevel()));
                  mc.theWorld.updateLightByType(EnumSkyBlock.Block, var17 + 1, var18, var19);
                  mc.theWorld.updateLightByType(EnumSkyBlock.Block, var17 - 1, var18 + 1, var19);
                  mc.theWorld.updateLightByType(EnumSkyBlock.Block, var17, var18 + 1, var19);
                  mc.theWorld.updateLightByType(EnumSkyBlock.Block, var17, var18 - 1, var19);
                  mc.theWorld.updateLightByType(EnumSkyBlock.Block, var17, var18, var19 + 1);
                  mc.theWorld.updateLightByType(EnumSkyBlock.Block, var17, var18, var19 - 1);
               }

               if(var15.forceDark()) {
                  for(i = -3; i <= 3; ++i) {
                     for(int var20 = -3; var20 <= 3; ++var20) {
                        for(k = -3; k <= 3; ++k) {
                           xd = i + var17;
                           yd = var20 + var18;
                           zd = k + var19;
                           blockLightOverrides.add(new Vector3i(xd, yd, zd));
                           mc.theWorld.setLightValue(EnumSkyBlock.Sky, xd, yd, zd, Math.abs(i) + Math.abs(var20) + Math.abs(k));
                        }
                     }
                  }
               }
            }
         }
      }

      if(FlansMod.ticker % vehicleLightOverrideRefreshRate == 0 && mc.theWorld != null) {
         vehicleLightOverrideRefreshRate = mc.gameSettings.fancyGraphics?1:2;
         var2 = vehicleLightOverrides.iterator();

         while(var2.hasNext()) {
            v = (Vector3i)var2.next();
            mc.theWorld.updateLightByType(EnumSkyBlock.Block, v.x, v.y, v.z);
         }

         vehicleLightOverrides.clear();
      }

   }

   public void clientTickEnd(Minecraft minecraft) {
      for(int i = 0; i < killMessages.size(); ++i) {
         --((TickHandlerClient.KillMessage)killMessages.get(i)).timer;
         if(((TickHandlerClient.KillMessage)killMessages.get(i)).timer == 0) {
            killMessages.remove(i);
         }
      }

      RenderFlag.angle += 2.0F;
      FlansModClient.tick();
   }

   public void renderTickStart(Minecraft mc, float smoothing) {
      if(mc.currentScreen == null && FlansModClient.controlModeMouse) {
         MouseHelper mouse = mc.mouseHelper;
         Entity ridden = mc.thePlayer.ridingEntity;
         if(ridden instanceof EntityDriveable) {
            EntityDriveable entity = (EntityDriveable)ridden;
            entity.onMouseMoved(mouse.deltaX, mouse.deltaY);
         }
      }

      FlansModClient.renderTick(smoothing);
      if(mc.currentScreen instanceof GuiDriveableController) {
         guiDriveableController = mc.currentScreen;
         mc.currentScreen = null;
      } else {
         guiDriveableController = null;
      }

   }

   public void renderTickEnd(Minecraft mc) {
      if(mc.currentScreen == null && guiDriveableController != null) {
         mc.currentScreen = guiDriveableController;
         guiDriveableController = null;
      }

      Tessellator tessellator = Tessellator.instance;
      ScaledResolution scaledresolution = new ScaledResolution(FlansModClient.minecraft, FlansModClient.minecraft.displayWidth, FlansModClient.minecraft.displayHeight);
      int i = scaledresolution.getScaledWidth();
      int j = scaledresolution.getScaledHeight();
      if(FlansMod.isInFlash) {
         this.isInFlash = true;
         this.flashTime = FlansMod.flashTime;
         this.tickcountflash = 0;
         FlansMod.isInFlash = false;
         FlansMod.flashTime = 0;
      }

      if(this.isInFlash && this.tickcountflash < this.flashTime) {
         FlansModClient.minecraft.entityRenderer.setupOverlayRendering();
         GL11.glEnable(3042);
         GL11.glDisable(2929);
         GL11.glDepthMask(false);
         GL11.glBlendFunc(770, 771);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glDisable(3008);
         mc.renderEngine.bindTexture(new ResourceLocation("flansmod", "gui/flash.png"));
         tessellator.startDrawingQuads();
         tessellator.addVertexWithUV((double)(i / 2 - 2 * j), (double)j, -90.0D, 0.0D, 1.0D);
         tessellator.addVertexWithUV((double)(i / 2 + 2 * j), (double)j, -90.0D, 1.0D, 1.0D);
         tessellator.addVertexWithUV((double)(i / 2 + 2 * j), 0.0D, -90.0D, 1.0D, 0.0D);
         tessellator.addVertexWithUV((double)(i / 2 - 2 * j), 0.0D, -90.0D, 0.0D, 0.0D);
         tessellator.draw();
         GL11.glDepthMask(true);
         GL11.glEnable(2929);
         GL11.glEnable(3008);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         ++this.tickcountflash;
      } else {
         this.isInFlash = false;
         this.flashTime = 0;
         this.tickcountflash = 0;
      }

   }

   private void drawSlotInventory(FontRenderer fontRenderer, ItemStack itemstack, int i, int j) {
      if(itemstack != null && itemstack.getItem() != null) {
         itemRenderer.renderItemIntoGUI(fontRenderer, FlansModClient.minecraft.renderEngine, itemstack, i, j);
         itemRenderer.renderItemOverlayIntoGUI(fontRenderer, FlansModClient.minecraft.renderEngine, itemstack, i, j);
      }
   }

   public static void addKillMessage(boolean headshot, InfoType infoType, String killer, String killed) {
      Iterator var4 = killMessages.iterator();

      while(var4.hasNext()) {
         TickHandlerClient.KillMessage killMessage = (TickHandlerClient.KillMessage)var4.next();
         ++killMessage.line;
         if(killMessage.line > 5) {
            killMessage.timer = 0;
         }
      }

      killMessages.add(new TickHandlerClient.KillMessage(headshot, infoType, killer, killed));
   }


   private static class KillMessage {

      public String killerName;
      public String killedName;
      public InfoType weapon;
      public int timer;
      public int line;
      public boolean headshot;


      public KillMessage(boolean head, InfoType infoType, String killer, String killed) {
         this.headshot = head;
         this.killerName = killer;
         this.killedName = killed;
         this.weapon = infoType;
         this.line = 0;
         this.timer = 200;
      }
   }
}
