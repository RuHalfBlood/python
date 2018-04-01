package com.flansmod.client.gui;

import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.model.RenderMecha;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.mechas.ContainerMechaInventory;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.driveables.mechas.MechaType;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiMechaInventory extends GuiContainer {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/mechaInventory.png");
   private static final RenderMecha mechaRenderer = new RenderMecha();
   public ContainerMechaInventory container;
   public InventoryPlayer inventory;
   public World world;
   public int scroll;
   public int numItems;
   public int maxScroll;
   public EntityMecha mecha;
   private int anim = 0;
   private long lastTime;


   public GuiMechaInventory(InventoryPlayer inventoryplayer, World world1, EntityMecha entMecha) {
      super(new ContainerMechaInventory(inventoryplayer, world1, entMecha));
      this.mecha = entMecha;
      this.inventory = inventoryplayer;
      this.world = world1;
      this.container = (ContainerMechaInventory)super.inventorySlots;
      super.ySize = 180;
      super.xSize = 350;
      this.maxScroll = this.container.maxScroll;
      this.numItems = this.container.numItems;
   }

   public void drawScreen(int i, int j, float f) {
      super.drawScreen(i, j, f);
   }

   protected void drawGuiContainerForegroundLayer(int x, int y) {
      super.fontRendererObj.drawString(this.mecha.getMechaType().name, 9, 9, 4210752);
      super.fontRendererObj.drawString("Inventory", 181, super.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i1, int j1) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture(texture);
      int j = (super.width - super.xSize) / 2;
      int k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
      int numRows = (this.numItems + 7) / 8;

      for(int newTime = 0; newTime < (numRows > 3?3:numRows); ++newTime) {
         this.drawTexturedModalRect(j + 185, k + 24 + 19 * newTime, 181, 97, 18 * ((newTime + this.scroll + 1) * 8 <= this.numItems?8:this.numItems % 8), 18);
      }

      if(this.scroll == 0) {
         this.drawTexturedModalRect(j + 336, k + 41, 350, 0, 10, 10);
      }

      if(this.scroll == this.maxScroll) {
         this.drawTexturedModalRect(j + 336, k + 53, 350, 10, 10, 10);
      }

      long var12 = super.mc.theWorld.getWorldInfo().getWorldTime();
      if(var12 > this.lastTime) {
         this.lastTime = var12;
         if(var12 % 5L == 0L) {
            ++this.anim;
         }
      }

      int fuelTankSize = this.mecha.getMechaType().fuelTankSize;
      float fuelInTank = this.mecha.driveableData.fuelInTank;
      if(fuelInTank < (float)(fuelTankSize / 8) && this.anim % 4 > 1) {
         this.drawTexturedModalRect(super.width / 2 - 14, super.height / 2 - 59, 360, 0, 6, 6);
      }

      if(fuelInTank > 0.0F) {
         this.drawTexturedModalRect(super.width / 2 - 18, super.height / 2 + 45 - (int)(94.0F * fuelInTank / (float)fuelTankSize), 350, 20, 15, (int)(94.0F * fuelInTank / (float)fuelTankSize));
      }

      MechaType type = this.mecha.getMechaType();
      GL11.glPushMatrix();
      GL11.glEnable(2929);
      GL11.glEnable(2896);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glTranslatef((float)(j + 92), (float)(k + 105), 100.0F);
      GL11.glScalef(-50.0F / type.cameraDistance, 50.0F / type.cameraDistance, 50.0F / type.cameraDistance);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(30.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef((float)FlansMod.ticker, 0.0F, 1.0F, 0.0F);
      super.mc.renderEngine.bindTexture(FlansModResourceHandler.getTexture(type));
      mechaRenderer.render(this.mecha, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      GL11.glPopMatrix();
   }

   public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
      float f = 0.001953125F;
      float f1 = 0.00390625F;
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV((double)par1, (double)(par2 + par6), (double)super.zLevel, (double)((float)par3 * f), (double)((float)(par4 + par6) * f1));
      tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)super.zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
      tessellator.addVertexWithUV((double)(par1 + par5), (double)par2, (double)super.zLevel, (double)((float)(par3 + par5) * f), (double)((float)par4 * f1));
      tessellator.addVertexWithUV((double)par1, (double)par2, (double)super.zLevel, (double)((float)par3 * f), (double)((float)par4 * f1));
      tessellator.draw();
   }

   public void initGui() {
      super.initGui();
      super.buttonList.add(new GuiButton(0, super.width / 2 - 166, super.height / 2 + 63, 93, 20, "Passenger Guns"));
      super.buttonList.add(new GuiButton(1, super.width / 2 - 68, super.height / 2 + 63, 68, 20, "Repair"));
   }

   protected void actionPerformed(GuiButton button) {
      if(button.id == 0) {
         this.inventory.player.openGui(FlansMod.INSTANCE, 6, this.world, this.mecha.chunkCoordX, this.mecha.chunkCoordY, this.mecha.chunkCoordZ);
      }

      if(button.id == 1) {
         this.inventory.player.openGui(FlansMod.INSTANCE, 1, this.world, this.mecha.chunkCoordX, this.mecha.chunkCoordY, this.mecha.chunkCoordZ);
      }

   }

   protected void mouseClicked(int i, int j, int k) {
      super.mouseClicked(i, j, k);
      int m = i - (super.width - super.xSize) / 2;
      int n = j - (super.height - super.ySize) / 2;
      if(this.scroll > 0 && m > 336 && m < 346 && n > 41 && n < 51) {
         --this.scroll;
         this.container.updateScroll(this.scroll);
      }

      if(this.scroll < this.maxScroll && m > 336 & m < 346 && n > 53 && n < 63) {
         ++this.scroll;
         this.container.updateScroll(this.scroll);
      }

   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   static {
      mechaRenderer.setRenderManager(RenderManager.instance);
   }
}
