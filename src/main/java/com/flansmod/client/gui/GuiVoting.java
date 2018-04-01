package com.flansmod.client.gui;

import com.flansmod.common.FlansMod;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketVoteCast;
import com.flansmod.common.network.PacketVoting;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiVoting extends GuiScreen {

   public static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/vote.png");
   public static PacketVoting packet;
   public static int myVote = 0;
   private int guiHeight;


   public GuiVoting(PacketVoting packet) {
      myVote = 0;
      packet = packet;
   }

   public void initGui() {
      super.initGui();
      super.buttonList.clear();
      this.guiHeight = 29 + packet.clientOptions.length * 24;

      for(int i = 0; i < packet.clientOptions.length; ++i) {
         super.buttonList.add(new GuiButton(i, super.width / 2 + 128 - 50, super.height / 2 - this.guiHeight / 2 + 24 + 24 * i, 40, 20, "Vote"));
      }

   }

   protected void actionPerformed(GuiButton button) {
      myVote = button.id + 1;
      FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketVoteCast(myVote)));
   }

   public void drawScreen(int i, int j, float f) {
      ScaledResolution scaledresolution = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int k = scaledresolution.getScaledWidth();
      int l = scaledresolution.getScaledHeight();
      FontRenderer fontrenderer = super.mc.fontRenderer;
      this.drawDefaultBackground();
      GL11.glEnable(3042);
      super.mc.renderEngine.bindTexture(texture);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int m = k / 2 - 128;
      int n = l / 2 - this.guiHeight / 2;
      this.drawTexturedModalRect(m, n, 0, 0, 256, 22);

      int p;
      for(p = 0; p < packet.clientOptions.length; ++p) {
         this.drawTexturedModalRect(m, n + 22 + 24 * p, 0, 23, 256, 24);
      }

      this.drawTexturedModalRect(m, l / 2 + this.guiHeight / 2 - 6, 0, 73, 256, 7);
      this.drawString(super.fontRendererObj, "Vote for the Next Round", m + 8, n + 8, 16777215);
      this.drawString(super.fontRendererObj, packet.timeLeft / 20 + "", m + 256 - 20, n + 8, 16777215);

      for(p = 0; p < packet.clientOptions.length; ++p) {
         this.drawString(super.fontRendererObj, packet.clientOptions[p].mapName, m + 10, n + 25 + 24 * p, 16777215);
         this.drawString(super.fontRendererObj, packet.clientOptions[p].gametype + " : §" + packet.clientOptions[p].teamNames[0] + ", §" + packet.clientOptions[p].teamNames[1], m + 10, n + 35 + 24 * p, 16777215);
         this.drawCenteredString(super.fontRendererObj, (myVote == p + 1?"§2":"") + packet.numVotes[p], m + 196, n + 31 + 24 * p, 16777215);
      }

      super.drawScreen(i, j, f);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

}
