package com.flansmod.client.gui;

import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.ContainerDriveableMenu;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketDriveableGUI;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiDriveableMenu extends GuiContainer {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/planeMenu.png");
   public World world;
   public InventoryPlayer inventory;
   public EntityDriveable entity;


   public GuiDriveableMenu(InventoryPlayer inventoryplayer, World world1, EntityDriveable entPlane) {
      super(new ContainerDriveableMenu(inventoryplayer, world1));
      this.entity = entPlane;
      super.ySize = 180;
      this.world = world1;
      this.inventory = inventoryplayer;
   }

   public void initGui() {
      super.initGui();
      DriveableType type = this.entity.getDriveableType();
      GuiButton cargoButton = new GuiButton(0, super.width / 2 - 60, super.height / 2 - 71, 58, 20, "Cargo");
      cargoButton.enabled = type.numCargoSlots > 0;
      super.buttonList.add(cargoButton);
      GuiButton gunsButton = new GuiButton(1, super.width / 2 + 2, super.height / 2 - 71, 58, 20, "Guns");
      gunsButton.enabled = type.ammoSlots() > 0;
      super.buttonList.add(gunsButton);
      GuiButton fuelButton = new GuiButton(2, super.width / 2 - 60, super.height / 2 - 49, 58, 20, "Fuel");
      fuelButton.enabled = type.fuelTankSize > 0;
      super.buttonList.add(fuelButton);
      GuiButton missileButton = new GuiButton(3, super.width / 2 + 2, super.height / 2 - 49, 58, 20, this.entity.getMissileInventoryName());
      missileButton.enabled = type.numMissileSlots > 0;
      super.buttonList.add(missileButton);
      GuiButton bombButton = new GuiButton(5, super.width / 2 + 2, super.height / 2 - 27, 58, 20, this.entity.getBombInventoryName());
      bombButton.enabled = type.numBombSlots > 0;
      super.buttonList.add(bombButton);
      super.buttonList.add(new GuiButton(4, super.width / 2 - 60, super.height / 2 - 27, 58, 20, "Repair"));
   }

   protected void actionPerformed(GuiButton button) {
      if(button.id == 0) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableGUI(3)));
      }

      if(button.id == 1) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableGUI(0)));
      }

      if(button.id == 2) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableGUI(2)));
      }

      if(button.id == 3) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableGUI(5)));
      }

      if(button.id == 4) {
         this.inventory.player.openGui(FlansMod.INSTANCE, 1, this.world, this.entity.chunkCoordX, this.entity.chunkCoordY, this.entity.chunkCoordZ);
      }

      if(button.id == 5) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableGUI(1)));
      }

      if(button.id == 6) {
         FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableGUI(1)));
      }

   }

   protected void drawGuiContainerForegroundLayer(int i, int j) {
      super.fontRendererObj.drawString(this.entity.getDriveableType().name, 6, 6, 4210752);
      super.fontRendererObj.drawString("Inventory", 8, super.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i1, int j1) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture(texture);
      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
   }

}
