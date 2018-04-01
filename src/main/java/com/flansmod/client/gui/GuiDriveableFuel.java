package com.flansmod.client.gui;

import com.flansmod.client.gui.GuiDriveableMenu;
import com.flansmod.common.driveables.ContainerDriveableMenu;
import com.flansmod.common.driveables.EntityDriveable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiDriveableFuel extends GuiContainer {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/planeFuel.png");
   public World world;
   public InventoryPlayer inventory;
   public EntityDriveable plane;
   private int anim = 0;
   private long lastTime;


   public GuiDriveableFuel(InventoryPlayer inventoryplayer, World world1, EntityDriveable entPlane) {
      super(new ContainerDriveableMenu(inventoryplayer, world1, true, entPlane));
      this.plane = entPlane;
      super.ySize = 161;
      this.world = world1;
      this.inventory = inventoryplayer;
   }

   protected void drawGuiContainerForegroundLayer(int i, int j) {
      super.fontRendererObj.drawString(this.plane.getDriveableType().name + " - Fuel", 6, 6, 4210752);
      super.fontRendererObj.drawString("Inventory", 8, super.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i1, int j1) {
      long newTime = super.mc.theWorld.getWorldInfo().getWorldTime();
      if(newTime > this.lastTime) {
         this.lastTime = newTime;
         if(newTime % 5L == 0L) {
            ++this.anim;
         }
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture(texture);
      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
      int fuelTankSize = this.plane.getDriveableType().fuelTankSize;
      float fuelInTank = this.plane.driveableData.fuelInTank;
      if(this.plane.fuelling) {
         this.drawTexturedModalRect(j + 15, k + 44, 176 + 15 * (this.anim % 4), 0, 15, 16);
      }

      if(fuelInTank < (float)(fuelTankSize / 8) && this.anim % 4 > 1) {
         this.drawTexturedModalRect(j + 16, k + 25, 176, 16, 6, 6);
      }

      if(fuelInTank > 0.0F) {
         this.drawTexturedModalRect(j + 26, k + 21, 0, 161, (int)(129.0F * fuelInTank / (float)fuelTankSize), 15);
      }

   }

   protected void mouseClicked(int i, int j, int k) {
      super.mouseClicked(i, j, k);
      int m = i - (super.width - super.xSize) / 2;
      int n = j - (super.height - super.ySize) / 2;
      if(m > 161 && m < 171 && n > 5 && n < 15) {
         super.mc.displayGuiScreen(new GuiDriveableMenu(this.inventory, this.world, this.plane));
      }

   }

}
