package com.flansmod.client.gui;

import com.flansmod.common.FlansMod;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketTeamSelect;
import com.flansmod.common.teams.PlayerClass;
import com.flansmod.common.teams.Team;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiTeamSelect extends GuiScreen {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/teams.png");
   private static RenderItem itemRenderer = new RenderItem();
   private boolean classMenu;
   public static Team[] teamChoices;
   private PlayerClass[] classChoices;
   private int guiHeight;


   public GuiTeamSelect() {
      if(teamChoices == null) {
         FMLClientHandler.instance().getClient().displayGuiScreen((GuiScreen)null);
      } else {
         this.classMenu = false;
         this.guiHeight = 29 + 24 * teamChoices.length;
      }
   }

   public GuiTeamSelect(Team[] teams) {
      this.classMenu = false;
      teamChoices = teams;
      this.guiHeight = 29 + 24 * teams.length;
   }

   public GuiTeamSelect(PlayerClass[] classes) {
      this.classMenu = true;
      this.classChoices = classes;
      this.guiHeight = 29 + 24 * classes.length;
   }

   public void initGui() {
      super.initGui();
      int i;
      if(this.classMenu) {
         for(i = 0; i < this.classChoices.length; ++i) {
            if(this.classChoices[i] != null) {
               super.buttonList.add(new GuiButton(i, super.width / 2 - 128 + 9, super.height / 2 - this.guiHeight / 2 + 24 + 24 * i, 73, 20, this.classChoices[i].name));
            }
         }
      } else {
         if(teamChoices == null) {
            FMLClientHandler.instance().getClient().displayGuiScreen((GuiScreen)null);
            return;
         }

         for(i = 0; i < teamChoices.length; ++i) {
            if(teamChoices[i] != null) {
               super.buttonList.add(new GuiButton(i, super.width / 2 - 128 + 10, super.height / 2 - this.guiHeight / 2 + 24 + 24 * i, 236, 20, "§" + teamChoices[i].textColour + teamChoices[i].name));
            } else {
               super.buttonList.add(new GuiButton(i, super.width / 2 - 128 + 10, super.height / 2 - this.guiHeight / 2 + 24 + 24 * i, 236, 20, "No Team / Builder"));
            }
         }
      }

   }

   public void drawScreen(int i, int j, float f) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture(texture);
      this.drawTexturedModalRect(super.width / 2 - 128, super.height / 2 - this.guiHeight / 2, 0, 0, 256, 22);
      this.drawTexturedModalRect(super.width / 2 - 128, super.height / 2 + this.guiHeight / 2 - 6, 0, 73, 256, 7);
      int n;
      if(this.classMenu) {
         for(n = 0; n < this.classChoices.length; ++n) {
            this.drawTexturedModalRect(super.width / 2 - 128, super.height / 2 - this.guiHeight / 2 + 22 + 24 * n, 0, 23, 256, 24);
         }
      } else {
         for(n = 0; n < teamChoices.length; ++n) {
            this.drawTexturedModalRect(super.width / 2 - 128, super.height / 2 - this.guiHeight / 2 + 22 + 24 * n, 0, 48, 256, 24);
         }
      }

      super.fontRendererObj.drawStringWithShadow(this.classMenu?"Choose a Class":"Choose a Team", super.width / 2 - 120, super.height / 2 - this.guiHeight / 2 + 8, 16777215);
      super.drawScreen(i, j, f);
      if(this.classMenu) {
         for(n = 0; n < this.classChoices.length; ++n) {
            for(int m = 0; m < this.classChoices[n].startingItems.size(); ++m) {
               this.drawSlotInventory((ItemStack)this.classChoices[n].startingItems.get(m), super.width / 2 - 128 + 85 + 18 * m, super.height / 2 - this.guiHeight / 2 + 26 + 24 * n);
            }
         }
      }

   }

   protected void actionPerformed(GuiButton button) {
      if(this.classMenu) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketTeamSelect(this.classChoices[button.id].shortName, true)));
      } else {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketTeamSelect(teamChoices[button.id] == null?"null":teamChoices[button.id].shortName, false)));
      }

      Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
   }

   private void drawSlotInventory(ItemStack itemstack, int i, int j) {
      itemRenderer.renderItemIntoGUI(super.fontRendererObj, super.mc.renderEngine, itemstack, i, j);
      itemRenderer.renderItemOverlayIntoGUI(super.fontRendererObj, super.mc.renderEngine, itemstack, i, j);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   protected void keyTyped(char c, int i) {
      if(i == 1 || i == super.mc.gameSettings.keyBindInventory.getKeyCode()) {
         super.mc.thePlayer.closeScreen();
         if(this.classMenu) {
            if(this.classChoices != null && this.classChoices.length > 0) {
               FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketTeamSelect(this.classChoices[0].shortName, true)));
            }
         } else {
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketTeamSelect(Team.spectators.shortName, false)));
         }
      }

   }

   public void onGuiClosed() {}

}
