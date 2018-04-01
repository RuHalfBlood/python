package com.flansmod.client.gui;

import com.flansmod.client.gui.GuiDriveableMenu;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.ContainerDriveableInventory;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.PilotGun;
import com.flansmod.common.driveables.Seat;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketDriveableGUI;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiDriveableInventory extends GuiContainer {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/planeInventory.png");
   public ContainerDriveableInventory container;
   public InventoryPlayer inventory;
   public World world;
   public int scroll;
   public int numItems;
   public int maxScroll;
   public EntityDriveable driveable;
   public int screen;


   public GuiDriveableInventory(InventoryPlayer inventoryplayer, World world1, EntityDriveable entPlane, int i) {
      super(new ContainerDriveableInventory(inventoryplayer, world1, entPlane, i));
      this.driveable = entPlane;
      this.inventory = inventoryplayer;
      this.world = world1;
      this.container = (ContainerDriveableInventory)super.inventorySlots;
      super.ySize = 180;
      this.screen = i;
      this.maxScroll = this.container.maxScroll;
      this.numItems = this.container.numItems;
   }

   protected void drawGuiContainerForegroundLayer(int x, int y) {
      String title = " - Guns";
      if(this.screen == 1) {
         title = " - " + this.driveable.getBombInventoryName();
      }

      if(this.screen == 2) {
         title = " - Cargo";
      }

      if(this.screen == 3) {
         title = " - " + this.driveable.getMissileInventoryName();
      }

      super.fontRendererObj.drawString(this.driveable.getDriveableType().name + title, 6, 6, 4210752);
      super.fontRendererObj.drawString("Inventory", 8, super.ySize - 96 + 2, 4210752);
      RenderHelper.enableGUIStandardItemLighting();
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      ArrayList listHoveringText = new ArrayList();
      int i1;
      if(this.screen == 0) {
         int mouseOverSlot = 0;

         Iterator ai;
         ShootableType ammo;
         int var17;
         for(i1 = 0; i1 < this.driveable.getDriveableType().seats.length; ++i1) {
            if(mouseOverSlot < 3 + this.scroll && this.driveable.getDriveableType().seats[i1].gunType != null) {
               if(mouseOverSlot >= this.scroll) {
                  Seat slot = this.driveable.getDriveableType().seats[i1];
                  super.fontRendererObj.drawString(slot.gunName, 53, 29 + 19 * (mouseOverSlot - this.scroll), 0);
                  int gunType = 25 + 19 * (mouseOverSlot - this.scroll);
                  if(y >= super.guiTop + gunType && y <= super.guiTop + gunType + 16) {
                     if(x >= super.guiLeft + 10 && x <= super.guiLeft + 10 + 17) {
                        listHoveringText.add(slot.gunType.name);
                     } else if(x >= super.guiLeft + 28 && x <= super.guiLeft + 28 + 17) {
                        if(slot.gunType.ammo.size() > 0) {
                           listHoveringText.add("[Ammo]");
                        }

                        ai = slot.gunType.ammo.iterator();

                        while(ai.hasNext()) {
                           ammo = (ShootableType)ai.next();
                           listHoveringText.add("> " + ammo.name);
                        }
                     }
                  }

                  for(var17 = 0; var17 < slot.gunType.ammo.size(); ++var17) {
                     this.drawStack(new ItemStack(((ShootableType)slot.gunType.ammo.get(var17)).getItem()), 110 + var17 * 16, gunType);
                  }

                  this.drawStack(new ItemStack(slot.gunType.getItem()), 10, 25 + 19 * (mouseOverSlot - this.scroll));
               }

               ++mouseOverSlot;
            }
         }

         for(i1 = 0; i1 < this.driveable.getDriveableType().pilotGuns.size(); ++i1) {
            if(mouseOverSlot < 3 + this.scroll && ((PilotGun)this.driveable.getDriveableType().pilotGuns.get(i1)).type != null) {
               if(mouseOverSlot >= this.scroll) {
                  super.fontRendererObj.drawString("Driver\'s gun " + (i1 + 1), 53, 29 + 19 * (mouseOverSlot - this.scroll), 0);
                  int var14 = 25 + 19 * (mouseOverSlot - this.scroll);
                  GunType var16 = ((PilotGun)this.driveable.getDriveableType().pilotGuns.get(i1)).type;
                  if(y >= super.guiTop + var14 && y <= super.guiTop + var14 + 16) {
                     if(x >= super.guiLeft + 10 && x <= super.guiLeft + 10 + 17) {
                        listHoveringText.add(var16.name);
                     } else if(x >= super.guiLeft + 28 && x <= super.guiLeft + 28 + 17) {
                        if(var16.ammo.size() > 0) {
                           listHoveringText.add("[Ammo]");
                        }

                        ai = var16.ammo.iterator();

                        while(ai.hasNext()) {
                           ammo = (ShootableType)ai.next();
                           listHoveringText.add("> " + ammo.name);
                        }
                     }
                  }

                  for(var17 = 0; var17 < var16.ammo.size(); ++var17) {
                     this.drawStack(new ItemStack(((ShootableType)var16.ammo.get(var17)).getItem()), 110 + var17 * 16, var14);
                  }

                  this.drawStack(new ItemStack(((PilotGun)this.driveable.getDriveableType().pilotGuns.get(i1)).type.getItem()), 10, 25 + 19 * (mouseOverSlot - this.scroll));
               }

               ++mouseOverSlot;
            }
         }
      } else if(this.screen == 3 && x >= super.guiLeft + 10 && x < super.guiLeft + 180 - 10 && y >= super.guiTop + 20 && y < super.guiTop + 90 && this.driveable.getDriveableType().ammo.size() > 0) {
         listHoveringText.add("[Missiles]");
         Iterator var11 = this.driveable.getDriveableType().ammo.iterator();

         while(var11.hasNext()) {
            BulletType var13 = (BulletType)var11.next();
            if(var13 != null) {
               listHoveringText.add("> " + var13.name);
            }
         }
      }

      Slot var12 = null;

      for(i1 = 0; i1 < super.inventorySlots.inventorySlots.size(); ++i1) {
         Slot var15 = (Slot)super.inventorySlots.inventorySlots.get(i1);
         if(this.func_146978_c(var15.xDisplayPosition, var15.yDisplayPosition, 16, 16, x, y) && var15.func_111238_b()) {
            var12 = var15;
         }
      }

      if(var12 == null || !var12.getHasStack()) {
         this.drawHoveringText(listHoveringText, x - super.guiLeft, y - super.guiTop, super.fontRendererObj);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable('\u803a');
      RenderHelper.disableStandardItemLighting();
      GL11.glDisable(2896);
      GL11.glDisable(2929);
   }

   private void drawStack(ItemStack itemstack, int i, int j) {
      if(itemstack != null && itemstack.getItem() != null) {
         GuiScreen.itemRender.renderItemIntoGUI(super.fontRendererObj, super.mc.renderEngine, itemstack, i, j);
         GuiScreen.itemRender.renderItemOverlayIntoGUI(super.fontRendererObj, super.mc.renderEngine, itemstack, i, j);
      }

   }

   private static String getGunSlotName(int i) {
      switch(i) {
      case 0:
         return "Left Nose Gun";
      case 1:
         return "Right Nose Gun";
      case 2:
         return "Left Wing Gun";
      case 3:
         return "Right Wing Gun";
      case 4:
         return "Tail Gun";
      case 5:
         return "Left Bay Gun";
      case 6:
         return "Right Bay Gun";
      case 7:
         return "Dorsal Gun";
      default:
         return "Not a Gun";
      }
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i1, int j1) {
      int j;
      int k;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      super.mc.renderEngine.bindTexture(texture);
      j = (super.width - super.xSize) / 2;
      k = (super.height - super.ySize) / 2;
      this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
      int m;
      label47:
      switch(this.screen) {
      case 0:
         m = 0;

         while(true) {
            if(m >= (this.numItems > 3?3:this.numItems)) {
               break label47;
            }

            this.drawTexturedModalRect(j + 9, k + 24 + 19 * m, 176, 0, 37, 18);
            ++m;
         }
      case 1:
      case 2:
      case 3:
         m = (this.numItems + 7) / 8;

         for(int row = 0; row < (m > 3?3:m); ++row) {
            this.drawTexturedModalRect(j + 9, k + 24 + 19 * row, 7, 97, 18 * ((row + this.scroll + 1) * 8 <= this.numItems?8:this.numItems % 8), 18);
         }
      }

      if(this.scroll == 0) {
         this.drawTexturedModalRect(j + 161, k + 41, 176, 18, 10, 10);
      }

      if(this.scroll == this.maxScroll) {
         this.drawTexturedModalRect(j + 161, k + 53, 176, 28, 10, 10);
      }

   }

   protected void mouseClicked(int i, int j, int k) {
      super.mouseClicked(i, j, k);
      int m = i - (super.width - super.xSize) / 2;
      int n = j - (super.height - super.ySize) / 2;
      if(this.scroll > 0 && m > 161 && m < 171 && n > 41 && n < 51) {
         --this.scroll;
         this.container.updateScroll(this.scroll);
      }

      if(this.scroll < this.maxScroll && m > 161 && m < 171 && n > 53 && n < 63) {
         ++this.scroll;
         this.container.updateScroll(this.scroll);
      }

      if(m > 161 && m < 171 && n > 5 && n < 15) {
         if(this.driveable instanceof EntityMecha) {
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketDriveableGUI(4)));
            this.inventory.player.openGui(FlansMod.INSTANCE, 10, this.world, this.driveable.chunkCoordX, this.driveable.chunkCoordY, this.driveable.chunkCoordZ);
         } else {
            super.mc.displayGuiScreen(new GuiDriveableMenu(this.inventory, this.world, this.driveable));
         }
      }

   }

   public boolean doesGuiPauseGame() {
      return false;
   }

}
