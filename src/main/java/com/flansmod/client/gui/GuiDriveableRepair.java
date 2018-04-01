package com.flansmod.client.gui;

import com.flansmod.client.gui.GuiDriveableMenu;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketDriveableGUI;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiDriveableRepair extends GuiScreen {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/repair.png");
   private EntityPlayer driver;
   private EntityDriveable driving;
   private ArrayList partsToDraw = new ArrayList();
   private static RenderItem itemRenderer = new RenderItem();
   private int guiOriginX;
   private int guiOriginY;


   public GuiDriveableRepair(EntityPlayer player) {
      this.driver = player;
      this.driving = ((EntitySeat)player.ridingEntity).driveable;
      Iterator var2 = this.driving.getDriveableData().parts.values().iterator();

      while(var2.hasNext()) {
         DriveablePart part = (DriveablePart)var2.next();
         if(part.maxHealth > 0) {
            this.partsToDraw.add(part);
         }
      }

   }

   public void initGui() {
      super.initGui();

      for(int i = 0; i < this.partsToDraw.size(); ++i) {
         super.buttonList.add(new GuiButton(i, 0, 0, 55, 20, "Repair"));
      }

   }

   protected void actionPerformed(GuiButton button) {
      FlansMod.proxy.repairDriveable(this.driver, this.driving, (DriveablePart)this.partsToDraw.get(button.id));
   }

   private void updateButtons() {
      int y = 43;

      for(int i = 0; i < this.partsToDraw.size(); ++i) {
         DriveablePart part = (DriveablePart)this.partsToDraw.get(i);
         GuiButton button = (GuiButton)super.buttonList.get(i);
         button.xPosition = this.guiOriginX + 9;
         button.yPosition = part.health <= 0?this.guiOriginY + y:-1000;
         y += part.health <= 0?40:20;
      }

   }

   public void drawScreen(int i, int j, float f) {
      short guiWidth = 202;
      int guiHeight = 31;

      DriveablePart w;
      for(Iterator scaledresolution = this.partsToDraw.iterator(); scaledresolution.hasNext(); guiHeight += w.health <= 0?40:20) {
         w = (DriveablePart)scaledresolution.next();
      }

      this.updateButtons();
      ScaledResolution var23 = new ScaledResolution(super.mc, super.mc.displayWidth, super.mc.displayHeight);
      int var24 = var23.getScaledWidth();
      int h = var23.getScaledHeight();
      this.drawDefaultBackground();
      GL11.glEnable(3042);
      super.mc.renderEngine.bindTexture(texture);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.guiOriginX = var24 / 2 - guiWidth / 2;
      this.guiOriginY = h / 2 - guiHeight / 2;
      this.drawTexturedModalRect(this.guiOriginX, this.guiOriginY, 0, 0, 202, 23);
      this.drawTexturedModalRect(this.guiOriginX, this.guiOriginY + guiHeight - 8, 0, 65, 202, 8);
      this.drawString(super.fontRendererObj, this.driving.getDriveableType().name + " - Repair", this.guiOriginX + 7, this.guiOriginY + 7, 16777215);
      int y = 23;

      boolean broken;
      for(Iterator var10 = this.partsToDraw.iterator(); var10.hasNext(); y += broken?40:20) {
         DriveablePart part = (DriveablePart)var10.next();
         broken = part.health <= 0;
         super.mc.renderEngine.bindTexture(texture);
         this.drawTexturedModalRect(this.guiOriginX, this.guiOriginY + y, 0, 24, 202, broken?40:20);
         float percentHealth = (float)part.health / (float)part.maxHealth;
         GL11.glColor3f(1.0F - percentHealth, percentHealth, 0.0F);
         this.drawTexturedModalRect(this.guiOriginX + 121, this.guiOriginY + y + 2, 0, 73, (int)(70.0F * percentHealth), 16);
         this.drawString(super.fontRendererObj, part.type.getName(), this.guiOriginX + 10, this.guiOriginY + y + 6, 16777215);
         this.drawCenteredString(super.fontRendererObj, (int)(percentHealth * 100.0F) + "%", this.guiOriginX + 158, this.guiOriginY + y + 6, 16777215);
         if(broken) {
            InventoryPlayer temporaryInventory = new InventoryPlayer((EntityPlayer)null);
            temporaryInventory.copyInventory(this.driver.inventory);
            ArrayList stacksNeeded = this.driving.getDriveableType().getItemsRequired(part, this.driving.getDriveableData().engine);

            for(int n = 0; n < 7; ++n) {
               int stackNum = n + FlansMod.ticker / 60 % Math.max(1, stacksNeeded.size() - 6);
               if(stackNum < stacksNeeded.size()) {
                  ItemStack stackNeeded = (ItemStack)stacksNeeded.get(stackNum);
                  int totalAmountFound = 0;

                  for(int m = 0; m < temporaryInventory.getSizeInventory(); ++m) {
                     ItemStack stackInSlot = temporaryInventory.getStackInSlot(m);
                     if(stackInSlot != null && stackInSlot.getItem() == stackNeeded.getItem() && stackInSlot.getItemDamage() == stackNeeded.getItemDamage()) {
                        int amountFound = Math.min(stackInSlot.stackSize, stackNeeded.stackSize - totalAmountFound);
                        stackInSlot.stackSize -= amountFound;
                        if(stackInSlot.stackSize <= 0) {
                           stackInSlot = null;
                        }

                        temporaryInventory.setInventorySlotContents(m, stackInSlot);
                        totalAmountFound += amountFound;
                        if(totalAmountFound == stackNeeded.stackSize) {
                           break;
                        }
                     }
                  }

                  if(totalAmountFound < stackNeeded.stackSize) {
                     super.mc.renderEngine.bindTexture(texture);
                     this.drawTexturedModalRect(this.guiOriginX + 67 + 18 * n, this.guiOriginY + y + 22, 202, 0, 16, 16);
                  }

                  this.drawSlotInventory((ItemStack)stacksNeeded.get(stackNum), this.guiOriginX + 67 + 18 * n, this.guiOriginY + y + 22);
               }
            }
         }
      }

      super.drawScreen(i, j, f);
   }

   protected void mouseClicked(int i, int j, int k) {
      super.mouseClicked(i, j, k);
      int m = i - this.guiOriginX;
      int n = j - this.guiOriginY;
      if(m > 185 && m < 195 && n > 5 && n < 15) {
         if(this.driving instanceof EntityMecha) {
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableGUI(4)));
            this.driver.openGui(FlansMod.INSTANCE, 10, this.driver.worldObj, this.driving.chunkCoordX, this.driving.chunkCoordY, this.driving.chunkCoordZ);
         } else {
            super.mc.displayGuiScreen(new GuiDriveableMenu(this.driver.inventory, this.driver.worldObj, this.driving));
         }
      }

   }

   private void drawSlotInventory(ItemStack itemstack, int i, int j) {
      if(itemstack != null && itemstack.getItem() != null) {
         itemRenderer.renderItemIntoGUI(super.fontRendererObj, super.mc.renderEngine, itemstack, i, j);
         itemRenderer.renderItemOverlayIntoGUI(super.fontRendererObj, super.mc.renderEngine, itemstack, i, j);
         GL11.glDisable(2896);
         GL11.glDisable(2929);
      }
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

}
