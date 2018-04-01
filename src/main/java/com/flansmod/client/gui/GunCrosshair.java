package com.flansmod.client.gui;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import org.lwjgl.opengl.GL11;

import com.flansmod.common.guns.ItemGun;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GunCrosshair {

   private long zLevel = 0L;
   private long curDist = 0L;
   private long maxDist = 30L;
   private long lastTime = 0L;
   private static long entityHit = 0L;
   private final long stepTime = 4L;
   private long increment = 1L;


   @SubscribeEvent
   public void render(RenderGameOverlayEvent event) {
      if(event.type == ElementType.CROSSHAIRS) {
         long itemStack = System.currentTimeMillis();
         if(this.lastTime == 0L) {
            this.lastTime = itemStack;
         }

         long elapsedTime = itemStack - this.lastTime;
         this.increment = elapsedTime / 4L;
         this.lastTime = itemStack;
         ItemStack itemStack1 = Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem();
         Item item1 = null;
         if(itemStack1 != null) {
            item1 = itemStack1.getItem();
         }

         if(item1 != null) {
            if(item1 instanceof ItemGun) {
               event.setCanceled(true);
               this.maxDist = (long)(((ItemGun)item1).impactX * 6);
               
                  if(this.curDist > 0L) {
                     event.setCanceled(true);
                     this.animateIn(event);
         
                     Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
                     GL11.glEnable(3042);
                     GL11.glBlendFunc(775, 769);
                     GL11.glDisable(3042);
                  }

               } else {
                  if(this.curDist <= this.maxDist) {
                     this.animateOut(event);
                  } else {
                     this.animateIn(event);
                  }

               }
            } else if(this.curDist > 0L) {
               event.setCanceled(true);
               this.animateIn(event);
  
         } else if(this.curDist > 0L) {
            event.setCanceled(true);
            this.animateIn(event);
      
         }

         if(entityHit > 0L) {
        //    Minecraft.getMinecraft().getTextureManager().bindTexture(Texture.gui);
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            this.drawTexturedModalRect(event.resolution.getScaledWidth() / 2 - 3, event.resolution.getScaledHeight() / 2 - 3, 0, 0, 7, 7);
            GL11.glDisable(3042);
            entityHit = Math.max(entityHit - this.increment, 0L);
         }
      } else if(event.type == ElementType.HOTBAR) {
         ItemStack itemStack2 = Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem();
         Item item = null;
         if(itemStack2 != null) {
            item = itemStack2.getItem();
         }

         if(item != null && item instanceof ItemGun) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow((ItemGun)item + "", 0, event.resolution.getScaledHeight() - 10, 16777215);
         }
      }

   }

   private void animateOut(RenderGameOverlayEvent event) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
      GL11.glEnable(3042);
      GL11.glBlendFunc(775, 769);
      this.drawTexturedModalRect(event.resolution.getScaledWidth() / 2, (int)((long)(event.resolution.getScaledHeight() / 2 - 4) - this.curDist), 7, 3, 1, 4);
      this.drawTexturedModalRect((int)((long)(event.resolution.getScaledWidth() / 2 + 1) + this.curDist), event.resolution.getScaledHeight() / 2, 8, 7, 4, 1);
      this.drawTexturedModalRect(event.resolution.getScaledWidth() / 2, (int)((long)(event.resolution.getScaledHeight() / 2 + 1) + this.curDist), 7, 8, 1, 4);
      this.drawTexturedModalRect((int)((long)(event.resolution.getScaledWidth() / 2 - 4) - this.curDist), event.resolution.getScaledHeight() / 2, 3, 7, 4, 1);
      GL11.glDisable(3042);
      if(this.curDist < this.maxDist) {
         this.curDist = Math.min(this.curDist + this.increment, this.maxDist);
      }

   }

   private void animateIn(RenderGameOverlayEvent event) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.icons);
      GL11.glEnable(3042);
      GL11.glBlendFunc(775, 769);
      this.drawTexturedModalRect(event.resolution.getScaledWidth() / 2, (int)((long)(event.resolution.getScaledHeight() / 2 - 4) - this.curDist), 7, 3, 1, 4);
      this.drawTexturedModalRect((int)((long)(event.resolution.getScaledWidth() / 2 + 1) + this.curDist), event.resolution.getScaledHeight() / 2, 8, 7, 4, 1);
      this.drawTexturedModalRect(event.resolution.getScaledWidth() / 2, (int)((long)(event.resolution.getScaledHeight() / 2 + 1) + this.curDist), 7, 8, 1, 4);
      this.drawTexturedModalRect((int)((long)(event.resolution.getScaledWidth() / 2 - 4) - this.curDist), event.resolution.getScaledHeight() / 2, 3, 7, 4, 1);
      GL11.glDisable(3042);
      if(this.curDist > 0L) {
         this.curDist = Math.max(this.curDist - this.increment, 0L);
      }

   }

   public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
      float f = 0.00390625F;
      float f1 = 0.00390625F;
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
      tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
      tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
      tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
      tessellator.draw();
   }

   public static void entityHit() {
      entityHit = 8L;
   }

}
