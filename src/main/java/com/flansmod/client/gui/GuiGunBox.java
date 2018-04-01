package com.flansmod.client.gui;

import com.flansmod.common.guns.boxes.GunBoxType;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiGunBox extends GuiScreen {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/weaponBox.png");
   private InventoryPlayer inventory;
   private Minecraft mc;
   private static RenderItem itemRenderer = new RenderItem();
   private GunBoxType type;
   private int page;
   private int guiOriginX;
   private int guiOriginY;
   private int scroll;


   public GuiGunBox(InventoryPlayer playerinventory, GunBoxType type) {
      this.inventory = playerinventory;
      this.mc = FMLClientHandler.instance().getClient();
      this.type = type;
      this.page = 0;
   }

   public void updateScreen() {
      super.updateScreen();
      ++this.scroll;
   }

   public void drawScreen(int i, int j, float f) {
      ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
      int k = scaledresolution.getScaledWidth();
      int l = scaledresolution.getScaledHeight();
      FontRenderer fontrenderer = this.mc.fontRenderer;
      this.drawDefaultBackground();
      GL11.glEnable(3042);
      this.mc.renderEngine.bindTexture(texture);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int m = this.guiOriginX = k / 2 - 88;
      int n = this.guiOriginY = l / 2 - 102;
      this.drawTexturedModalRect(m, n, 0, 0, 176, 204);
      this.drawCenteredString(super.fontRendererObj, this.type.name, k / 2, n + 5, 16777215);
      this.mc.renderEngine.bindTexture(texture);
      if(this.type.numGuns > this.page * 2 + 1 && this.type.guns[this.page * 2] != null && this.type.guns[this.page * 2 + 1] != null) {
         this.drawTexturedModalRect(m + 89, n + 18, 5, 18, 82, 90);
      }

      if(this.page == 0) {
         this.drawTexturedModalRect(m + 77, n + 109, 176, 0, 10, 10);
      }

      if(this.type.numGuns <= this.page * 2 + 2) {
         this.drawTexturedModalRect(m + 89, n + 109, 186, 0, 10, 10);
      }

      RenderHelper.enableGUIStandardItemLighting();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable('\u803a');
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
      this.drawRecipe(fontrenderer, m, n, this.page * 2, 0);
      if(this.type.numGuns > this.page * 2 + 1) {
         this.drawRecipe(fontrenderer, m, n, this.page * 2 + 1, 1);
      }

      int col;
      for(col = 0; col < 3; ++col) {
         for(int col1 = 0; col1 < 9; ++col1) {
            this.drawSlotInventory(this.inventory.getStackInSlot(col1 + (col + 1) * 9), m + 8 + col1 * 18, n + 122 + col * 18);
         }
      }

      for(col = 0; col < 9; ++col) {
         this.drawSlotInventory(this.inventory.getStackInSlot(col), m + 8 + col * 18, n + 180);
      }

      GL11.glDisable(3042);
   }

   private void drawRecipe(FontRenderer fontrenderer, int m, int n, int q, int offset) {
      m += offset * 84;
      if(this.type.guns[q] != null) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawSlotInventory(new ItemStack(this.type.guns[q].getItem()), m + 9, n + 44);
         if(this.type.bullets[q] != null) {
            this.drawSlotInventory(new ItemStack(this.type.bullets[q].getItem()), m + 9, n + 66);
         }

         if(this.type.altBullets[q] != null) {
            this.drawSlotInventory(new ItemStack(this.type.altBullets[q].getItem()), m + 9, n + 88);
         }

         int numParts = this.type.gunParts[q].size();
         int startPart = 0;
         if(numParts >= 4) {
            startPart = this.scroll / 40 % (numParts - 2);
         }

         int name;
         for(name = 0; name < (numParts < 3?numParts:3); ++name) {
            this.drawSlotInventory((ItemStack)this.type.gunParts[q].get(startPart + name), m + 30 + name * 19, n + 44);
         }

         if(this.type.bullets[q] != null) {
            numParts = this.type.bulletParts[q].size();
            startPart = 0;
            if(numParts >= 4) {
               startPart = this.scroll / 40 % (numParts - 2);
            }

            for(name = 0; name < (numParts < 3?numParts:3); ++name) {
               this.drawSlotInventory((ItemStack)this.type.bulletParts[q].get(startPart + name), m + 30 + name * 19, n + 66);
            }
         }

         if(this.type.altBullets[q] != null) {
            numParts = this.type.altBulletParts[q].size();
            startPart = 0;
            if(numParts >= 4) {
               startPart = this.scroll / 40 % (numParts - 2);
            }

            for(name = 0; name < (numParts < 3?numParts:3); ++name) {
               this.drawSlotInventory((ItemStack)this.type.altBulletParts[q].get(startPart + name), m + 30 + name * 19, n + 88);
            }
         }

         RenderHelper.disableStandardItemLighting();
         String var10 = this.type.guns[q].name;
         if(var10.length() > 12) {
            int nextSpace = var10.indexOf(" ", 10);
            if(nextSpace != -1) {
               this.drawCenteredString(fontrenderer, var10.substring(0, nextSpace), m + 46, n + 22, 16777215);
               this.drawCenteredString(fontrenderer, var10.substring(nextSpace + 1, var10.length()), m + 46, n + 32, 16777215);
            } else {
               this.drawCenteredString(fontrenderer, var10, m + 46, n + 25, 16777215);
            }
         } else {
            this.drawCenteredString(fontrenderer, var10, m + 46, n + 25, 16777215);
         }

         RenderHelper.enableGUIStandardItemLighting();
      }

   }

   private void drawSlotInventory(ItemStack itemstack, int i, int j) {
      if(itemstack != null && itemstack.getItem() != null) {
         RenderHelper.enableGUIStandardItemLighting();
         itemRenderer.renderItemIntoGUI(super.fontRendererObj, this.mc.renderEngine, itemstack, i, j);
         itemRenderer.renderItemOverlayIntoGUI(super.fontRendererObj, this.mc.renderEngine, itemstack, i, j);
         GL11.glDisable(2896);
         GL11.glDisable(2929);
      }
   }

   protected void mouseClicked(int i, int j, int k) {
      super.mouseClicked(i, j, k);
      int m = i - this.guiOriginX;
      int n = j - this.guiOriginY;
      if(k == 0 || k == 1) {
         if(m > 77 && m < 87 && n > 109 && n < 119 && this.page > 0) {
            --this.page;
         }

         if(m > 89 && m < 99 && n > 109 && n < 119 && this.type.numGuns > this.page * 2 + 2) {
            ++this.page;
         }

         if(this.type.guns[this.page * 2] != null && m > 7 && m < 27 && n > 42 && n < 62) {
            this.type.block.buyGun(this.page * 2, this.inventory, this.type);
         }

         if(this.type.bullets[this.page * 2] != null && m > 7 && m < 27 && n > 64 && n < 84) {
            this.type.block.buyAmmo(this.page * 2, this.inventory, this.type);
         }

         if(this.type.altBullets[this.page * 2] != null && m > 7 && m < 27 && n > 86 && n < 106) {
            this.type.block.buyAltAmmo(this.page * 2, this.inventory, this.type);
         }

         if(this.page * 2 + 1 < this.type.numGuns && this.type.guns[this.page * 2 + 1] != null && m > 91 && m < 111 && n > 42 && n < 62) {
            this.type.block.buyGun(this.page * 2 + 1, this.inventory, this.type);
         }

         if(this.page * 2 + 1 < this.type.numGuns && this.type.bullets[this.page * 2 + 1] != null && m > 91 && m < 111 && n > 64 && n < 84) {
            this.type.block.buyAmmo(this.page * 2 + 1, this.inventory, this.type);
         }

         if(this.page * 2 + 1 < this.type.numGuns && this.type.altBullets[this.page * 2 + 1] != null && m > 91 && m < 111 && n > 86 && n < 106) {
            this.type.block.buyAltAmmo(this.page * 2 + 1, this.inventory, this.type);
         }
      }

   }

   protected void keyTyped(char c, int i) {
      if(i == 1 || i == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
         this.mc.thePlayer.closeScreen();
      }

   }

   public boolean doesGuiPauseGame() {
      return false;
   }

}
