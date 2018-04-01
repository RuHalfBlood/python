package com.flansmod.client.gui;

import com.flansmod.client.FlansModClient;
import com.flansmod.common.network.PacketTeamInfo;
import com.flansmod.common.teams.Team;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiTeamScores extends GuiScreen {

   public static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/teamsScores.png");
   public static final ResourceLocation texture2 = new ResourceLocation("flansmod", "gui/teamsScores2.png");


   public void drawScreen(int i, int j, float f) {
      PacketTeamInfo teamInfo = FlansModClient.teamInfo;
      if(teamInfo != null && PacketTeamInfo.gametype != null && !PacketTeamInfo.gametype.equals("") && PacketTeamInfo.teamData != null && PacketTeamInfo.teamData.length >= 1) {
         if(PacketTeamInfo.sortedByTeam) {
            this.renderTwoTeamGUI(teamInfo);
         } else {
            this.renderDMGUI(teamInfo);
         }

      } else {
         super.mc.displayGuiScreen((GuiScreen)null);
      }
   }

   public void renderTwoTeamGUI(PacketTeamInfo teamInfo) {
      long newTime = super.mc.theWorld.getWorldInfo().getWorldTime();
      ScaledResolution scaledresolution = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int k = scaledresolution.getScaledWidth();
      int l = scaledresolution.getScaledHeight();
      FontRenderer fontrenderer = super.mc.fontRenderer;
      this.drawDefaultBackground();
      GL11.glEnable(3042);
      super.mc.renderEngine.bindTexture(texture2);
      int guiHeight = 68 + 9 * PacketTeamInfo.numLines;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int m = k / 2 - 156;
      int n = l / 2 - guiHeight / 2;
      func_146110_a(m, n, 100.0F, 0.0F, 312, 66, 512.0F, 256.0F);

      int i;
      for(i = 0; i < PacketTeamInfo.numLines; ++i) {
         func_146110_a(m, n + 66 + 9 * i, 100.0F, 71.0F, 312, 9, 512.0F, 256.0F);
      }

      func_146110_a(m, n + 66 + PacketTeamInfo.numLines * 9, 100.0F, 168.0F, 312, 12, 512.0F, 256.0F);
      if(PacketTeamInfo.showZombieScore) {
         func_146110_a(m + 103, n + 51, 412.0F, 0.0F, 29, 11, 512.0F, 256.0F);
         func_146110_a(m + 254, n + 51, 412.0F, 0.0F, 29, 11, 512.0F, 256.0F);
      }

      this.drawString(super.fontRendererObj, PacketTeamInfo.map, m + 6, n + 6, 16777215);
      this.drawString(super.fontRendererObj, PacketTeamInfo.gametype, m + 312 - 6 - super.fontRendererObj.getStringWidth(PacketTeamInfo.gametype), n + 6, 16777215);
      int j;
      if(teamInfo.roundOver()) {
         Team var13 = teamInfo.getWinner();
         if(var13 == null) {
            this.drawString(super.fontRendererObj, "Time Ran Out!", m + 10, n + 20, 16777215);
         } else {
            this.drawString(super.fontRendererObj, var13.name + " Won!", m + 10, n + 20, 16777215);
         }
      } else {
         i = PacketTeamInfo.timeLeft / 20;
         j = i / 60;
         i %= 60;
         this.drawString(super.fontRendererObj, "Time Left : " + j + ":" + (i < 10?"0" + i:Integer.valueOf(i)), m + 10, n + 20, 16777215);
         this.drawString(super.fontRendererObj, "Score Limit : " + PacketTeamInfo.scoreLimit, m + 302 - super.fontRendererObj.getStringWidth("Score Limit : " + PacketTeamInfo.scoreLimit), n + 20, 16777215);
      }

      for(i = 0; i < 2; ++i) {
         super.fontRendererObj.drawString("§" + PacketTeamInfo.teamData[i].team.textColour + PacketTeamInfo.teamData[i].team.name, m + 10 + 151 * i, n + 39, 16777215);
         super.fontRendererObj.drawString("§" + PacketTeamInfo.teamData[i].team.textColour + PacketTeamInfo.teamData[i].score, m + 133 + 151 * i, n + 39, 16777215);

         for(j = 0; j < PacketTeamInfo.teamData[i].numPlayers; ++j) {
            this.drawString(super.fontRendererObj, PacketTeamInfo.teamData[i].playerData[j].username, m + 12 + 151 * i, n + 67 + 9 * j, 16777215);
            this.drawCenteredString(super.fontRendererObj, "" + PacketTeamInfo.teamData[i].playerData[j].score, m + 111 + 151 * i, n + 67 + 9 * j, 16777215);
            this.drawCenteredString(super.fontRendererObj, "" + (PacketTeamInfo.showZombieScore?PacketTeamInfo.teamData[i].playerData[j].zombieScore:PacketTeamInfo.teamData[i].playerData[j].kills), m + 127 + 151 * i, n + 67 + 9 * j, 16777215);
            this.drawCenteredString(super.fontRendererObj, "" + PacketTeamInfo.teamData[i].playerData[j].deaths, m + 143 + 151 * i, n + 67 + 9 * j, 16777215);
         }
      }

      GL11.glDisable(3042);
   }

   public void renderDMGUI(PacketTeamInfo teamInfo) {
      long newTime = super.mc.theWorld.getWorldInfo().getWorldTime();
      ScaledResolution scaledresolution = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int k = scaledresolution.getScaledWidth();
      int l = scaledresolution.getScaledHeight();
      FontRenderer fontrenderer = super.mc.fontRenderer;
      this.drawDefaultBackground();
      GL11.glEnable(3042);
      super.mc.renderEngine.bindTexture(texture);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int guiHeight = 34 + 9 * PacketTeamInfo.numLines;
      int m = k / 2 - 128;
      int n = l / 2 - guiHeight / 2;
      this.drawTexturedModalRect(m, n, 0, 45, 256, 24);

      int line;
      for(line = 0; line < PacketTeamInfo.numLines; ++line) {
         this.drawTexturedModalRect(m, n + 24 + 9 * line, 0, 71, 256, 9);
      }

      this.drawTexturedModalRect(m, l / 2 + guiHeight / 2 - 10, 0, 87, 256, 10);
      this.drawCenteredString(super.fontRendererObj, PacketTeamInfo.gametype, k / 2, n + 4, 16777215);
      this.drawString(super.fontRendererObj, "Name", m + 8, n + 14, 16777215);
      this.drawString(super.fontRendererObj, "Score", m + 100, n + 14, 16777215);
      this.drawString(super.fontRendererObj, "Kills", m + 150, n + 14, 16777215);
      this.drawString(super.fontRendererObj, "Deaths", m + 200, n + 14, 16777215);
      line = 0;
      int q;
      if(PacketTeamInfo.sortedByTeam) {
         for(q = 0; q < PacketTeamInfo.numTeams; ++q) {
            if(PacketTeamInfo.teamData[q] != null && PacketTeamInfo.teamData[q].team != null) {
               this.drawString(super.fontRendererObj, "§" + PacketTeamInfo.teamData[q].team.textColour + PacketTeamInfo.teamData[q].team.name, m + 8, n + 25 + 9 * line, 16777215);
               this.drawString(super.fontRendererObj, "" + PacketTeamInfo.teamData[q].score, m + 100, n + 25 + 9 * line, 16777215);
               ++line;

               for(int q1 = 0; q1 < PacketTeamInfo.teamData[q].numPlayers; ++q1) {
                  this.drawString(super.fontRendererObj, PacketTeamInfo.teamData[q].playerData[q1].username, m + 8, n + 25 + 9 * line, 16777215);
                  this.drawString(super.fontRendererObj, "" + PacketTeamInfo.teamData[q].playerData[q1].score, m + 100, n + 25 + 9 * line, 16777215);
                  this.drawString(super.fontRendererObj, "" + PacketTeamInfo.teamData[q].playerData[q1].kills, m + 150, n + 25 + 9 * line, 16777215);
                  this.drawString(super.fontRendererObj, "" + PacketTeamInfo.teamData[q].playerData[q1].deaths, m + 200, n + 25 + 9 * line, 16777215);
                  ++line;
               }
            }
         }
      } else {
         for(q = 0; q < PacketTeamInfo.teamData[0].numPlayers; ++q) {
            this.drawString(super.fontRendererObj, PacketTeamInfo.teamData[0].playerData[q].username, m + 8, n + 25 + 9 * line, 16777215);
            this.drawString(super.fontRendererObj, "" + PacketTeamInfo.teamData[0].playerData[q].score, m + 100, n + 25 + 9 * line, 16777215);
            this.drawString(super.fontRendererObj, "" + PacketTeamInfo.teamData[0].playerData[q].kills, m + 150, n + 25 + 9 * line, 16777215);
            this.drawString(super.fontRendererObj, "" + PacketTeamInfo.teamData[0].playerData[q].deaths, m + 200, n + 25 + 9 * line, 16777215);
            ++line;
         }
      }

      GL11.glDisable(3042);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

}
