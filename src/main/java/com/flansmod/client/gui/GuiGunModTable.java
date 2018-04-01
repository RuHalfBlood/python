package com.flansmod.client.gui;

import com.flansmod.client.ClientProxy;
import com.flansmod.client.model.GunAnimations;
import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.ContainerGunModTable;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketGunPaint;
import com.flansmod.common.paintjob.Paintjob;
import java.util.Collections;
import java.util.Random;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiGunModTable extends GuiContainer {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/gunTableNew.png");
   private static final Random rand = new Random();
   private Paintjob hoveringOver = null;
   private String hoveringOverModSlots = null;
   private int mouseX;
   private int mouseY;
   private InventoryPlayer inventory;
   private boolean flipGunModel = false;
   private int[] lastStats = new int[]{0, 0, 0, 0};


   public GuiGunModTable(InventoryPlayer inv, World w) {
      super(new ContainerGunModTable(inv, w));
      this.inventory = inv;
      super.xSize = 331;
      super.ySize = 236;
   }

   protected void drawGuiContainerForegroundLayer(int x, int y) {
      super.fontRendererObj.drawString("Gun Modification Table", 6, 6, 4210752);
      super.fontRendererObj.drawString("Inventory", 7, 142, 4210752);
      super.fontRendererObj.drawString("Gun Information", 179, 22, 4210752);
      super.fontRendererObj.drawString("Paintjobs", 179, 128, 4210752);
      ItemStack gunStack = super.inventorySlots.getSlot(0).getStack();
      if(gunStack != null && gunStack.getItem() instanceof ItemGun) {
         GunType gunType = ((ItemGun)gunStack.getItem()).type;
         int reloadt = Math.round(gunType.getReloadTime(gunStack));
         if(gunType.model != null) {
            GL11.glPushMatrix();
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
            GL11.glTranslatef(105.0F, 55.0F, 100.0F);
            GL11.glRotatef(160.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
            if(this.flipGunModel) {
               GL11.glTranslatef(-30.0F, 0.0F, 0.0F);
               GL11.glRotatef(190.0F, 0.0F, 1.0F, 0.0F);
            }

            RenderHelper.enableStandardItemLighting();
            GL11.glScalef(-60.0F, 60.0F, 60.0F);
            ClientProxy.gunRenderer.renderGun(gunStack, gunType, 0.0625F, gunType.model, GunAnimations.defaults, 0.0F, ItemRenderType.ENTITY);
            GL11.glPopMatrix();
         }

         super.fontRendererObj.drawString(gunStack.getDisplayName(), 207, 36, 4210752);
         super.fontRendererObj.drawString(gunType.description, 207, 46, 4210752);
         super.fontRendererObj.drawString("Damage", 181, 61, 4210752);
         super.fontRendererObj.drawString("Accuracy", 181, 73, 4210752);
         super.fontRendererObj.drawString("Recoil", 181, 85, 4210752);
         super.fontRendererObj.drawString("Reload", 181, 97, 4210752);
         super.fontRendererObj.drawString(String.valueOf(roundFloat(gunType.getDamage(gunStack), 2)), 241, 62, 4210752);
         super.fontRendererObj.drawString(String.valueOf(roundFloat(gunType.getSpread(gunStack), 2)), 241, 74, 4210752);
         super.fontRendererObj.drawString(String.valueOf(roundFloat(gunType.getRecoilPitch(gunStack), 2)), 241, 86, 4210752);
         super.fontRendererObj.drawString(roundFloat((float)(reloadt / 20), 2) + "s", 241, 98, 4210752);
         if(this.hoveringOverModSlots != null) {
            this.drawHoveringText(Collections.singletonList(this.hoveringOverModSlots), this.mouseX - super.guiLeft, this.mouseY - super.guiTop, super.fontRendererObj);
         }
      }

   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int xOrigin = (super.width - super.xSize) / 2;
      int yOrigin = (super.height - super.ySize) / 2;
      super.mc.renderEngine.bindTexture(texture);
      this.drawTexturedModalRect(xOrigin, yOrigin, 0, 0, super.xSize, super.ySize);

      for(int gunStack = 1; gunStack < 17; ++gunStack) {
         super.inventorySlots.getSlot(gunStack).yDisplayPosition = -1000;
      }

      ItemStack var17 = super.inventorySlots.getSlot(0).getStack();
      if(var17 == null) {
         this.lastStats = new int[]{0, 0, 0, 0};
      }

      boolean[] haveDyes;
      int s;
      int amountNeeded;
      int s1;
      if(var17 != null && var17.getItem() instanceof ItemGun) {
         GunType numDyes = ((ItemGun)var17.getItem()).type;
         haveDyes = new boolean[]{numDyes.allowBarrelAttachments, numDyes.allowScopeAttachments, numDyes.allowStockAttachments, numDyes.allowGripAttachments, numDyes.allowGadgetAttachments, numDyes.allowSlideAttachments, numDyes.allowPumpAttachments, numDyes.allowAccessoryAttachments};
         s = Math.round(numDyes.getReloadTime(var17));
         this.drawTexturedModalRect(xOrigin + 146, yOrigin + 63, 340, 166, 20, 10);

         for(amountNeeded = 0; amountNeeded < haveDyes.length; ++amountNeeded) {
            if(haveDyes[amountNeeded]) {
               this.drawTexturedModalRect(xOrigin + 16 + amountNeeded * 18, yOrigin + 88, 340 + amountNeeded * 18, 136, 18, 18);
               super.inventorySlots.getSlot(amountNeeded + 1).yDisplayPosition = 89;
            }
         }

         for(amountNeeded = 0; amountNeeded < 8; ++amountNeeded) {
            if(amountNeeded < numDyes.numGenericAttachmentSlots) {
               this.drawTexturedModalRect(xOrigin + 16 + 18 * amountNeeded, yOrigin + 114, 340, 100, 18, 18);
               super.inventorySlots.getSlot(haveDyes.length + 1 + amountNeeded).yDisplayPosition = 115;
            }
         }

         int[] var19 = new int[]{Math.round(numDyes.getDamage(var17)) * 4, Math.round(numDyes.getSpread(var17)) * 4, Math.round(numDyes.getRecoilPitch(var17)) * 4, s / 20 * 8};
         this.displayGunValues(var19);
         s1 = numDyes.paintjobs.size();
         int stack = s1 / 2 + 1;

         int y;
         int x;
         for(y = 0; y < stack; ++y) {
            for(x = 0; x < 2; ++x) {
               if(2 * y + x < s1) {
                  this.drawTexturedModalRect(xOrigin + 181 + 18 * x, yOrigin + 150 + 18 * y, 340, 100, 18, 18);
               }
            }
         }

         for(y = 0; y < stack; ++y) {
            for(x = 0; x < 2; ++x) {
               if(2 * y + x < s1) {
                  Paintjob paintjob = (Paintjob)numDyes.paintjobs.get(2 * y + x);
                  ItemStack stack1 = var17.copy();
                  stack1.setItemDamage(paintjob.ID);
                  GuiScreen.itemRender.renderItemIntoGUI(super.mc.fontRenderer, super.mc.getTextureManager(), stack1, xOrigin + 182 + x * 18, yOrigin + 151 + y * 18);
               }
            }
         }
      }

      if(this.hoveringOver != null) {
         int var18 = this.hoveringOver.dyesNeeded.length;
         if(var18 != 0 && !this.inventory.player.capabilities.isCreativeMode) {
            haveDyes = new boolean[var18];

            for(s = 0; s < var18; ++s) {
               amountNeeded = this.hoveringOver.dyesNeeded[s].stackSize;

               for(s1 = 0; s1 < this.inventory.getSizeInventory(); ++s1) {
                  ItemStack var20 = this.inventory.getStackInSlot(s1);
                  if(var20 != null && var20.getItem() == Items.dye && var20.getItemDamage() == this.hoveringOver.dyesNeeded[s].getItemDamage()) {
                     amountNeeded -= var20.stackSize;
                  }
               }

               if(amountNeeded <= 0) {
                  haveDyes[s] = true;
               }
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            super.mc.renderEngine.bindTexture(texture);

            for(s = 0; s < var18; ++s) {
               this.drawTexturedModalRect(xOrigin + 223 + 18 * s, yOrigin + 150, haveDyes[s]?358:340, 118, 18, 18);
            }

            for(s = 0; s < var18; ++s) {
               GuiScreen.itemRender.renderItemIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), this.hoveringOver.dyesNeeded[s], xOrigin + 224 + s * 18, yOrigin + 151);
               GuiScreen.itemRender.renderItemOverlayIntoGUI(super.fontRendererObj, super.mc.getTextureManager(), this.hoveringOver.dyesNeeded[s], xOrigin + 224 + s * 18, yOrigin + 151);
            }
         }
      }

   }

   public void displayGunValues(int[] stats) {
      int xOrigin = (super.width - super.xSize) / 2;
      int yOrigin = (super.height - super.ySize) / 2;

      int k;
      for(k = 0; k < 4; ++k) {
         this.drawTexturedModalRect(xOrigin + 239, yOrigin + 60 + 12 * k, 340, 80, 80, 10);
      }

      for(k = 0; k < 4; ++k) {
         int difference = stats[k] - this.lastStats[k];
         boolean finalWidth = false;
         int var7;
         if(k == 0) {
            if(stats[k] < 80 && difference != 0 && difference > 0) {
               var7 = this.lastStats[k] += 2;
            } else if(difference != 0 && difference < 0) {
               var7 = this.lastStats[k] -= 2;
            } else if(stats[k] < 80) {
               var7 = stats[k];
            } else {
               var7 = 80;
            }

            this.drawTexturedModalRect(xOrigin + 239, yOrigin + 60 + 12 * k, 340, 90, var7, 10);
         } else {
            difference = 80 - stats[k] - this.lastStats[k];
            if(80 - stats[k] > 2 && difference != 0 && difference > 0) {
               var7 = this.lastStats[k] += 2;
            } else if(difference != 0 && difference < 0) {
               var7 = this.lastStats[k] -= 2;
            } else if(80 - stats[k] > 2) {
               var7 = 80 - stats[k];
            } else {
               var7 = 2;
            }

            this.drawTexturedModalRect(xOrigin + 239, yOrigin + 60 + 12 * k, 340, 90, var7, 10);
         }
      }

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

   public void handleMouseInput() {
      super.handleMouseInput();
      this.mouseX = Mouse.getEventX() * super.width / super.mc.displayWidth;
      this.mouseY = super.height - Mouse.getEventY() * super.height / super.mc.displayHeight - 1;
      int mouseXInGUI = this.mouseX - super.guiLeft;
      int mouseYInGUI = this.mouseY - super.guiTop;
      this.hoveringOver = null;
      ItemStack gunStack = super.inventorySlots.getSlot(0).getStack();
      if(gunStack != null && gunStack.getItem() instanceof ItemGun) {
         GunType gunType = ((ItemGun)gunStack.getItem()).type;
         int numPaintjobs = gunType.paintjobs.size();
         int numRows = numPaintjobs / 2 + 1;

         for(int text = 0; text < numRows; ++text) {
            for(int allowBools = 0; allowBools < 2; ++allowBools) {
               if(2 * text + allowBools < numPaintjobs) {
                  Paintjob a = (Paintjob)gunType.paintjobs.get(2 * text + allowBools);
                  ItemStack slotX = gunStack.copy();
                  slotX.stackTagCompound.setString("Paint", a.iconName);
                  int slotY = 181 + allowBools * 18;
                  int slotY1 = 150 + text * 18;
                  if(mouseXInGUI >= slotY && mouseXInGUI < slotY + 18 && mouseYInGUI >= slotY1 && mouseYInGUI < slotY1 + 18) {
                     this.hoveringOver = a;
                  }
               }
            }
         }

         this.hoveringOverModSlots = null;
         String[] var13 = new String[]{"Barrel", "Scope", "Stock", "Grip", "Gadget", "Slide", "Pump", "Accessory"};
         boolean[] var14 = new boolean[]{gunType.allowBarrelAttachments, gunType.allowScopeAttachments, gunType.allowStockAttachments, gunType.allowGripAttachments, gunType.allowGadgetAttachments, gunType.allowSlideAttachments, gunType.allowPumpAttachments, gunType.allowAccessoryAttachments};

         for(int var15 = 0; var15 < var14.length; ++var15) {
            int var16 = 16 + var15 * 18;
            byte var17 = 88;
            if(mouseXInGUI >= var16 && mouseXInGUI < var16 + 18 && mouseYInGUI >= var17 && mouseYInGUI < var17 + 18 && !super.inventorySlots.getSlot(var15 + 1).getHasStack() && var14[var15]) {
               this.hoveringOverModSlots = var13[var15];
            }
         }
      }

   }

   protected void mouseClicked(int x, int y, int button) {
      int xOrigin = (super.width - super.xSize) / 2;
      int yOrigin = (super.height - super.ySize) / 2;
      super.mouseClicked(x, y, button);
      int m = x - xOrigin;
      int n = y - yOrigin;
      if((button == 0 || button == 1) && m >= 146 && m <= 165 && n >= 63 && n <= 72) {
         if(!this.flipGunModel) {
            this.flipGunModel = true;
         } else {
            this.flipGunModel = false;
         }
      }

      if(button == 0) {
         if(this.hoveringOver != null) {
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketGunPaint(this.hoveringOver.ID)));
            ((ContainerGunModTable)super.inventorySlots).clickPaintjob(this.hoveringOver);
         }
      }
   }

   public static float roundFloat(float value, int points) {
      int pow = 10;

      for(int result = 1; result < points; ++result) {
         pow *= 10;
      }

      float var4 = value * (float)pow;
      return (float)((int)(var4 - (float)((int)var4) >= 0.5F?var4 + 1.0F:var4)) / (float)pow;
   }

}
