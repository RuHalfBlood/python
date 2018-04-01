package com.flansmod.hand.client.dev;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiDevButton extends GuiButton {

   public boolean leftOriented = false;
   public boolean onlyText = false;
   public boolean drawUnderline = true;
   public boolean drawBlackOutline = false;
   public boolean canMouseBeOver = true;
   public boolean canButtonBePressed = true;
   private int textColor = -1;
   private int textColorOver = -1;
   private int color = -16739505;
   private int over = -16728497;


   public GuiDevButton(int id, int x, int y, String text) {
      super(id, x, y, 74, 16, text);
   }

   public GuiDevButton(int id, int x, int y, int w, int h, String text) {
      super(id, x, y, w, h, text);
   }

   public GuiDevButton setColor(int color, int over) {
      this.color = color;
      this.over = over;
      return this;
   }

   public void drawButton(Minecraft mc, int mx, int my) {
      if(super.visible) {
         FontRenderer fontrenderer = mc.fontRenderer;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         super.field_146123_n = this.canMouseBeOver && mx >= super.xPosition && my >= super.yPosition && mx < super.xPosition + super.width && my < super.yPosition + super.height;
         int color = this.color;
         if(super.field_146123_n) {
            color = this.over;
         }

         if(!super.enabled) {
            color = -11513776;
         }

         int x = super.xPosition;
         int y = super.yPosition;
         int w = super.width;
         int h = super.height;
         if(!this.onlyText) {
            if(this.drawBlackOutline) {
               drawRect(x, y, x + w, y + h, -16777216);
               ++x;
               ++y;
               w -= 2;
               h -= 2;
            }

            drawRect(x, y, x + w, y + h, color);
            if(this.drawUnderline) {
               drawRect(x, y + h - 4, x + w, y + h, 889192448);
            }
         }

         if(this.leftOriented) {
            this.drawString(fontrenderer, super.displayString, super.xPosition + 5, super.yPosition + (super.height - 8) / 2, super.field_146123_n?this.textColorOver:this.textColor);
         } else {
            this.drawCenteredString(fontrenderer, super.displayString, super.xPosition + super.width / 2, super.yPosition + (super.height - 8) / 2, super.field_146123_n?this.textColorOver:this.textColor);
         }

      }
   }

   public GuiDevButton drawBlackOutline() {
      this.drawBlackOutline = true;
      return this;
   }

   public GuiDevButton drawOnlyText() {
      this.onlyText = true;
      return this;
   }

   public GuiDevButton setTextColor(int color, int over) {
      this.textColor = color;
      this.textColorOver = over;
      return this;
   }

   public GuiDevButton drawUnderline(boolean flag) {
      this.drawUnderline = flag;
      return this;
   }

   public GuiDevButton setTextLeftOriented() {
      this.leftOriented = true;
      return this;
   }

   public GuiDevButton setCantBePressed() {
      this.canButtonBePressed = false;
      return this;
   }

   public boolean mousePressed(Minecraft mc, int mx, int my) {
      return this.canButtonBePressed && super.mousePressed(mc, mx, my);
   }
}
