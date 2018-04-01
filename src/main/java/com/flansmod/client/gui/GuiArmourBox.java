package com.flansmod.client.gui;

import com.flansmod.common.teams.ArmourBoxType;
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

public class GuiArmourBox extends GuiScreen {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/armourBox.png");
   private InventoryPlayer inventory;
   private Minecraft mc;
   private static RenderItem itemRenderer = new RenderItem();
   private ArmourBoxType type;
   private int page;
   private int guiOriginX;
   private int guiOriginY;
   private int scroll;


   public GuiArmourBox(InventoryPlayer playerinventory, ArmourBoxType type) {
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
      int n = this.guiOriginY = l / 2 - 91;
      this.drawTexturedModalRect(m, n, 0, 0, 176, 182);
      this.drawCenteredString(super.fontRendererObj, this.type.name, k / 2, n + 5, 16777215);
      this.mc.renderEngine.bindTexture(texture);
      if(this.page == 0) {
         this.drawTexturedModalRect(m + 77, n + 87, 176, 0, 10, 10);
      }

      if(this.page >= this.type.pages.size() - 1) {
         this.drawTexturedModalRect(m + 89, n + 87, 186, 0, 10, 10);
      }

      RenderHelper.enableGUIStandardItemLighting();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable('\u803a');
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
      this.drawRecipe(fontrenderer, m, n, this.page);

      int col;
      for(col = 0; col < 3; ++col) {
         for(int col1 = 0; col1 < 9; ++col1) {
            this.drawSlotInventory(this.inventory.getStackInSlot(col1 + (col + 1) * 9), m + 8 + col1 * 18, n + 100 + col * 18);
         }
      }

      for(col = 0; col < 9; ++col) {
         this.drawSlotInventory(this.inventory.getStackInSlot(col), m + 8 + col * 18, n + 158);
      }

      GL11.glDisable(3042);
   }

   private void drawRecipe(FontRenderer fontrenderer, int m, int n, int q) {
      ArmourBoxType.ArmourBoxEntry page = (ArmourBoxType.ArmourBoxEntry)this.type.pages.get(q);
      if(page != null) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

         for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 2; ++j) {
               this.drawSlotInventory(new ItemStack(page.armours[i * 2 + j].item), m + 9 + 83 * i, n + 44 + 22 * j);
               int numParts = page.requiredStacks[i * 2 + j].size();
               int startPart = 0;
               if(numParts >= 4) {
                  startPart = this.scroll / 40 % (numParts - 2);
               }

               for(int p = 0; p < (numParts < 3?numParts:3); ++p) {
                  this.drawSlotInventory((ItemStack)page.requiredStacks[i * 2 + j].get(startPart + p), m + 30 + p * 19 + 83 * i, n + 44 + 22 * j);
               }
            }
         }

         RenderHelper.disableStandardItemLighting();
         this.drawCenteredString(fontrenderer, page.name, m + 87, n + 25, 16777215);
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
         if(m > 77 && m < 87 && n > 87 && n < 97 && this.page > 0) {
            --this.page;
         }

         if(m > 89 && m < 99 && n > 87 && n < 97 && this.page < this.type.pages.size() - 1) {
            ++this.page;
         }

         for(int x = 0; x < 2; ++x) {
            for(int y = 0; y < 2; ++y) {
               if(((ArmourBoxType.ArmourBoxEntry)this.type.pages.get(this.page)).armours[x * 2 + y] != null && m > 7 + 83 * x && m < 27 + 83 * x && n > 42 + 22 * y && n < 62 + 22 * y) {
                  this.type.block.buyArmour(((ArmourBoxType.ArmourBoxEntry)this.type.pages.get(this.page)).shortName, x * 2 + y, this.inventory);
               }
            }
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
