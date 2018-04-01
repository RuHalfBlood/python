package com.flansmod.hand.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ScissorHelper {

   private static int[] currentPos = new int[4];
   private static int[] savedPos = new int[4];


   public static void startScissor(int x, int y, int w, int h) {
      GL11.glEnable(3089);
      setScissorPos(x, y, w, h);
   }

   public static void startScissor2(int x, int y, int x2, int y2) {
      GL11.glEnable(3089);
      setScissorPos(x, y, x2 - x, y2 - y);
   }

   public static void restoreScissorPos() {
      GL11.glScissor(savedPos[0], savedPos[1], savedPos[2], savedPos[3]);
   }

   public static void setScissorPos(int x, int y, int w, int h) {
      Minecraft minecraft = Minecraft.getMinecraft();
      ScaledResolution scaledRes = new ScaledResolution(minecraft, minecraft.displayWidth, minecraft.displayHeight);
      int scale = scaledRes.getScaleFactor();
      int scissorWidth = w * scale;
      int scissorHeight = h * scale;
      int scissorX = x * scale;
      int scissorY = minecraft.displayHeight - scissorHeight - y * scale;
      currentPos[0] = scissorX;
      currentPos[1] = scissorY;
      currentPos[2] = scissorWidth;
      currentPos[3] = scissorHeight;
      GL11.glScissor(scissorX, scissorY, scissorWidth, scissorHeight);
   }

   public static void saveScissorPos() {
      System.arraycopy(currentPos, 0, savedPos, 0, currentPos.length);
   }

   public static void enableScissor() {
      GL11.glEnable(3089);
   }

   public static void disableScissor() {
      GL11.glDisable(3089);
   }

}
