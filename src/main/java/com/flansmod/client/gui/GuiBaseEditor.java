package com.flansmod.client.gui;

import com.flansmod.common.FlansMod;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketBaseEdit;
import java.util.Arrays;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiBaseEditor extends GuiScreen {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/baseEdit.png");
   private int guiOriginX;
   private int guiOriginY;
   private GuiTextField nameEntryField;
   private GuiButton[] teamButtons;
   private GuiButton[] mapButtons;
   private GuiButton leftButton;
   private GuiButton rightButton;
   private int mapsPage;
   public PacketBaseEdit packet;


   public GuiBaseEditor(PacketBaseEdit packet) {
      this.packet = packet;
   }

   public void initGui() {
      super.initGui();
      super.buttonList.clear();
      Keyboard.enableRepeatEvents(true);
      this.nameEntryField = new GuiTextField(super.fontRendererObj, super.width / 2 - 128 + 70, super.height / 2 - 94 + 24, 179, super.fontRendererObj.FONT_HEIGHT);
      this.nameEntryField.setMaxStringLength(60);
      this.nameEntryField.setEnableBackgroundDrawing(true);
      this.nameEntryField.setVisible(true);
      this.nameEntryField.setFocused(true);
      this.nameEntryField.setTextColor(16777215);
      this.nameEntryField.setText(this.packet.baseName);
      this.teamButtons = new GuiButton[4];
      this.teamButtons[0] = new GuiButton(0, super.width / 2 - 128 + 6, super.height / 2 - 94 + 38, 58, 20, "No Team");
      this.teamButtons[1] = new GuiButton(1, super.width / 2 - 128 + 68, super.height / 2 - 94 + 38, 58, 20, "Spectator");
      this.teamButtons[2] = new GuiButton(2, super.width / 2 - 128 + 130, super.height / 2 - 94 + 38, 58, 20, "Team 1");
      this.teamButtons[3] = new GuiButton(3, super.width / 2 - 128 + 192, super.height / 2 - 94 + 38, 58, 20, "Team 2");
      super.buttonList.addAll(Arrays.asList(this.teamButtons).subList(0, 4));
      this.mapButtons = new GuiButton[5];

      for(int i = 0; i < 5; ++i) {
         this.mapButtons[i] = new GuiButton(4 + i, super.width / 2 - 128 + 28, super.height / 2 - 94 + 75 + 22 * i, 200, 20, "Map " + (i + 1));
         super.buttonList.add(this.mapButtons[i]);
      }

      this.leftButton = new GuiButton(9, super.width / 2 - 128 + 6, super.height / 2 - 94 + 119, 20, 20, "<");
      this.rightButton = new GuiButton(10, super.width / 2 + 128 - 26, super.height / 2 - 94 + 119, 20, 20, ">");
      super.buttonList.add(this.leftButton);
      super.buttonList.add(this.rightButton);
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
      int m = this.guiOriginX = k / 2 - 128;
      int n = this.guiOriginY = l / 2 - 94;
      this.drawTexturedModalRect(m, n, 0, 0, 256, 189);
      this.drawString(super.fontRendererObj, "Base Settings", this.guiOriginX + 6, this.guiOriginY + 6, 16777215);
      this.drawString(super.fontRendererObj, "Base Name : ", this.guiOriginX + 6, this.guiOriginY + 24, 16777215);
      this.drawString(super.fontRendererObj, "Map", this.guiOriginX + 6, this.guiOriginY + 64, 16777215);
      this.nameEntryField.drawTextBox();
      super.drawScreen(i, j, f);
   }

   protected void actionPerformed(GuiButton button) {
      switch(button.id) {
      case 0:
      case 1:
      case 2:
      case 3:
         this.packet.teamID = button.id;
         break;
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
         this.packet.mapID = this.mapsPage * 5 + button.id - 4;
         break;
      case 9:
         --this.mapsPage;
         break;
      case 10:
         ++this.mapsPage;
      }

   }

   public void updateScreen() {
      int i;
      for(i = 0; i < 4; ++i) {
         this.teamButtons[i].enabled = this.packet.teamID != i;
      }

      for(i = 0; i < 5; ++i) {
         this.mapButtons[i].visible = this.packet.maps.length > i + this.mapsPage * 5;
         if(this.mapButtons[i].visible) {
            this.mapButtons[i].displayString = this.packet.maps[i + this.mapsPage * 5];
            this.mapButtons[i].enabled = i + this.mapsPage * 5 != this.packet.mapID;
         }
      }

      this.rightButton.visible = this.packet.maps.length > (this.mapsPage + 1) * 5;
      this.leftButton.visible = this.mapsPage > 0;
      this.nameEntryField.updateCursorCounter();
   }

   protected void mouseClicked(int i, int j, int k) {
      super.mouseClicked(i, j, k);
      this.nameEntryField.mouseClicked(i, j, k);
   }

   protected void keyTyped(char c, int i) {
      super.keyTyped(c, i);
      this.nameEntryField.textboxKeyTyped(c, i);
   }

   public void onGuiClosed() {
      super.onGuiClosed();
      this.packet.baseName = this.nameEntryField.getText();
      Keyboard.enableRepeatEvents(false);
      FlansMod.getPacketHandler().sendToServer((PacketBase)this.packet);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

}
