package com.flansmod.client.gui;

import com.flansmod.client.ClientProxy;
import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.model.GunAnimations;
import com.flansmod.client.model.ModelAttachment;
import com.flansmod.client.model.ModelDriveable;
import com.flansmod.client.model.RenderGun;
import com.flansmod.common.FlansMod;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketGunPaint;
import com.flansmod.common.paintjob.ContainerPaintjobTable;
import com.flansmod.common.paintjob.IPaintableItem;
import com.flansmod.common.paintjob.PaintableType;
import com.flansmod.common.paintjob.Paintjob;
import com.flansmod.common.paintjob.TileEntityPaintjobTable;
import com.flansmod.common.types.EnumType;
import com.flansmod.common.vector.Vector3f;
import java.util.Random;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiPaintjobTable extends GuiContainer {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/paintjobTable.png");
   private static final Random rand = new Random();
   private static final int paletteSizeX = 18;
   private static final int paletteSizeY = 4;
   private static final float componentBarLength = 68.0F;
   private Paintjob hoveringOver = null;
   private int mouseX;
   private int mouseY;
   private InventoryPlayer inventory;
   private boolean inCustomMode;
   private float customModeTransitionTimer = 0.0F;
   private float transitionSpeed = 0.9F;
   private int prevMainPageX;
   private RotatedAxes modelAxes = new RotatedAxes();
   private RotatedAxes prevModelAxes = new RotatedAxes();
   private static int[][] paletteColours = new int[18][4];
   private static int[] baseColours = new int[]{0, 16777215, 16711680, 16733440, 16755200, 16776960, 11206400, 5635840, '\uff00', '\uff55', '\uffaa', '\uffff', '\uaaff', 22015, 255, 5570815, 11141375, 16711935};
   private static int currentColour;


   private static void ResetPalette() {
      for(int x = 0; x < 18; ++x) {
         for(int y = 0; y < 4; ++y) {
            int red = baseColours[x] >> 16 & 255;
            int green = baseColours[x] >> 8 & 255;
            int blue = baseColours[x] >> 0 & 255;
            if(x == 0) {
               red = green = blue = 255 * y / 7;
            } else if(x == 1) {
               red = green = blue = 255 * (y + 4) / 7;
            } else {
               if(y == 3) {
                  red /= 2;
                  green /= 2;
                  blue /= 2;
               }

               if(y == 1) {
                  red = 255 - (255 - red) / 2;
                  green = 255 - (255 - green) / 2;
                  blue = 255 - (255 - blue) / 2;
               }

               if(y == 0) {
                  red = 255 - (255 - red) / 4;
                  green = 255 - (255 - green) / 4;
                  blue = 255 - (255 - blue) / 4;
               }
            }

            paletteColours[x][y] = (red << 16) + (green << 8) + blue;
         }
      }

   }

   public GuiPaintjobTable(InventoryPlayer inv, World w, TileEntityPaintjobTable te) {
      super(new ContainerPaintjobTable(inv, w, te));
      this.inventory = inv;
      super.xSize = 224;
      super.ySize = 264;
   }

   public void updateScreen() {
      super.updateScreen();
      this.prevModelAxes = this.modelAxes.clone();
      if(this.inCustomMode) {
         this.customModeTransitionTimer = 1.0F - (1.0F - this.customModeTransitionTimer) * this.transitionSpeed;
      } else {
         this.customModeTransitionTimer *= this.transitionSpeed;
         this.modelAxes.rotateLocalYaw(2.5F);
      }

      int xPos = this.GetMainPageX();
      int dPos = xPos - this.prevMainPageX;

      for(int i = 0; i < 38; ++i) {
         Slot var10000 = super.inventorySlots.getSlot(i);
         var10000.xDisplayPosition += dPos;
      }

      this.prevMainPageX = xPos;
   }

   private int GetMainPageX() {
      return (int)(-500.0F * this.customModeTransitionTimer);
   }

   private int GetMainPageY() {
      return 0;
   }

   private int GetCustomPageX() {
      return (int)(500.0F * (1.0F - this.customModeTransitionTimer));
   }

   private int GetCustomPageY() {
      return 0;
   }

   private Vector3f GetRenderOrigin() {
      Vector3f mainPagePosition = new Vector3f(100.0F, 64.0F, 100.0F);
      return mainPagePosition;
   }

   protected void drawGuiContainerForegroundLayer(int x, int y) {
      if(this.customModeTransitionTimer <= 0.999F) {
         int renderOrigin = (super.width - super.xSize) / 2 + this.GetMainPageX();
         int paintableStack = (super.height - super.ySize) / 2 + this.GetMainPageY();
         super.fontRendererObj.drawString("Inventory", this.GetMainPageX() + 8, this.GetMainPageY() + (super.ySize - 94) + 2, 4210752);
         super.fontRendererObj.drawString("Paintjob Table", this.GetMainPageX() + 8, this.GetMainPageY() + 6, 4210752);
      }

      Vector3f renderOrigin1 = this.GetRenderOrigin();
      ItemStack paintableStack1 = super.inventorySlots.getSlot(0).getStack();
      if(paintableStack1 != null && paintableStack1.getItem() instanceof IPaintableItem) {
         ItemStack tempStack = paintableStack1.copy();
         if(this.hoveringOver != null) {
            tempStack.setItemDamage(this.hoveringOver.ID);
         }

         PaintableType paintableType = ((IPaintableItem)paintableStack1.getItem()).GetPaintableType();
         EnumType eType = EnumType.getFromObject(paintableType);
         if(paintableType.GetModel() != null) {
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            GL11.glPushMatrix();
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            RenderHelper.enableStandardItemLighting();
            GL11.glPopMatrix();
            GL11.glEnable(2977);
            GL11.glTranslatef(renderOrigin1.x, renderOrigin1.y, renderOrigin1.z);
            GL11.glRotatef(160.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
            float scale = paintableType.GetRecommendedScale();
            GL11.glScalef(-scale, scale, scale);

            float dYaw;
            for(dYaw = this.modelAxes.getYaw() - this.prevModelAxes.getYaw(); dYaw > 180.0F; dYaw -= 360.0F) {
               ;
            }

            while(dYaw < -180.0F) {
               dYaw += 360.0F;
            }

            GL11.glRotatef(this.prevModelAxes.getYaw() + dYaw * RenderGun.smoothing, 0.0F, 1.0F, 0.0F);
            Paintjob paintjob = (Paintjob)paintableType.paintjobs.get(tempStack.getItemDamage());

            switch (eType) {
                case gun: {
                    GunType gunType = (GunType)paintableType;
                    ClientProxy.gunRenderer.renderGun(tempStack, gunType, 0.0625f, gunType.model, GunAnimations.defaults, 0.0f, ItemRenderType.ENTITY);
                    break;
                }
                case attachment: {
                    this.mc.renderEngine.bindTexture(FlansModResourceHandler.getPaintjobTexture(paintjob));
                    AttachmentType model = (AttachmentType)paintableType;
                    GL11.glScalef((float)model.modelScale, (float)model.modelScale, (float)model.modelScale);
                    ((ModelAttachment)paintableType.GetModel()).renderAttachment(0.0625f);
                    break;
                }
                case plane: 
                case vehicle: 
                case mecha: {
                    this.mc.renderEngine.bindTexture(FlansModResourceHandler.getPaintjobTexture(paintjob));
                    ((ModelDriveable)paintableType.GetModel()).render((DriveableType)paintableType);
                    break;
                }
            }
            GL11.glPopMatrix();
        }
    }
}



   private void drawModalRectWithCustomSizedTexture(int a, int b, int c, int d, int e, int f, int g, int h) {
      func_146110_a(a, b, (float)c, (float)d, e, f, (float)g, (float)h);
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(2929);
      super.mc.renderEngine.bindTexture(texture);
      short textureX = 512;
      short textureY = 256;
      int xOrigin;
      int yOrigin;
      int green;
      int n;
      int var18;
      if(this.customModeTransitionTimer <= 0.999F) {
         xOrigin = (super.width - super.xSize) / 2 + this.GetMainPageX();
         yOrigin = (super.height - super.ySize) / 2 + this.GetMainPageY();
         this.drawModalRectWithCustomSizedTexture(xOrigin, yOrigin, 0, 0, super.xSize, 114, textureX, textureY);
         this.drawModalRectWithCustomSizedTexture(xOrigin, yOrigin + 122, 0, 114, super.xSize, 142, textureX, textureY);
         ItemStack scale = super.inventorySlots.getSlot(0).getStack();
         int blue;
         int s;
         if(scale != null && scale.getItem() instanceof IPaintableItem) {
            PaintableType red = ((IPaintableItem)scale.getItem()).GetPaintableType();
            green = red.paintjobs.size();
            blue = green / 9 + 1;

            for(n = 0; n < blue; ++n) {
               for(s = 0; s < 9; ++s) {
                  if(9 * n + s < green) {
                     Paintjob stack = (Paintjob)red.paintjobs.get(9 * n + s);
                     ItemStack stack1 = scale.copy();
                     stack1.setItemDamage(stack.ID);
                     GuiScreen.itemRender.renderItemIntoGUI(super.mc.fontRenderer, super.mc.getTextureManager(), stack1, xOrigin + 8 + s * 18, yOrigin + 130 + n * 18);
                  }
               }
            }
         }

         if(this.hoveringOver != null) {
            var18 = this.hoveringOver.dyesNeeded.length;
            if(var18 != 0 && !this.inventory.player.capabilities.isCreativeMode) {
               boolean[] var20 = new boolean[var18];

               for(blue = 0; blue < var18; ++blue) {
                  n = this.hoveringOver.dyesNeeded[blue].stackSize;

                  for(s = 0; s < this.inventory.getSizeInventory(); ++s) {
                     ItemStack var23 = this.inventory.getStackInSlot(s);
                     if(var23 != null && var23.getItem() == Items.dye && var23.getItemDamage() == this.hoveringOver.dyesNeeded[blue].getItemDamage()) {
                        n -= var23.stackSize;
                     }
                  }

                  if(n <= 0) {
                     var20[blue] = true;
                  }
               }

               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glDisable(2896);
               super.mc.renderEngine.bindTexture(texture);
               blue = this.mouseX + 6;
               n = this.mouseY - 20;
               if(var18 == 1) {
                  this.drawModalRectWithCustomSizedTexture(blue, n, var20[0]?379:356, 0, 22, 22, textureX, textureY);
               } else {
                  this.drawModalRectWithCustomSizedTexture(blue, n, 256, var20[0]?23:0, 20, 22, textureX, textureY);

                  for(s = 1; s < var18 - 1; ++s) {
                     this.drawModalRectWithCustomSizedTexture(blue + 2 + 18 * s, n, 277, var20[s]?23:0, 18, 22, textureX, textureY);
                  }

                  this.drawModalRectWithCustomSizedTexture(blue + 2 + 18 * (var18 - 1), n, 296, var20[var18 - 1]?23:0, 20, 22, textureX, textureY);
               }

               for(s = 0; s < var18; ++s) {
                  GuiScreen.itemRender.renderItemIntoGUI(super.mc.fontRenderer, super.mc.getTextureManager(), this.hoveringOver.dyesNeeded[s], blue + 3 + s * 18, n + 3);
                  GuiScreen.itemRender.renderItemOverlayIntoGUI(super.mc.fontRenderer, super.mc.getTextureManager(), this.hoveringOver.dyesNeeded[s], blue + 3 + s * 18, n + 3, (String)null);
               }
            }
         }
      }

      if(this.customModeTransitionTimer >= 0.001F) {
         super.mc.renderEngine.bindTexture(texture);
         xOrigin = (super.width - super.xSize) / 2 + this.GetCustomPageX() - 32;
         yOrigin = (super.height - super.ySize) / 2 + this.GetCustomPageY();
         this.drawModalRectWithCustomSizedTexture(xOrigin, yOrigin + 200, 224, 206, 288, 50, textureX, textureY);
         GL11.glDisable(3553);

         float var22;
         for(int var16 = 0; var16 < 18; ++var16) {
            for(var18 = 0; var18 < 4; ++var18) {
               green = paletteColours[var16][var18];
               var22 = 0.00390625F;
               GL11.glColor3f(var22 * (float)(green >> 16 & 255), var22 * (float)(green >> 8 & 255), var22 * (float)(green >> 0 & 255));
               this.drawModalRectWithCustomSizedTexture(xOrigin + 8 + 9 * var16, yOrigin + 200 + 8 + 9 * var18, 0, 0, 7, 7, textureX, textureY);
            }
         }

         float var17 = 0.00390625F;
         float var19 = var17 * (float)(currentColour >> 16 & 255);
         float var21 = var17 * (float)(currentColour >> 8 & 255);
         var22 = var17 * (float)(currentColour >> 0 & 255);
         GL11.glColor3f(var19, var21, var22);
         this.drawModalRectWithCustomSizedTexture(xOrigin + 172, yOrigin + 208, 0, 0, 34, 34, textureX, textureY);

         for(n = 0; (float)n < 68.0F; ++n) {
            GL11.glColor3f((float)n / 68.0F, var21, var22);
            this.drawModalRectWithCustomSizedTexture(xOrigin + 212 + n, yOrigin + 208, 0, 0, 1, 10, textureX, textureY);
         }

         for(n = 0; (float)n < 68.0F; ++n) {
            GL11.glColor3f(var19, (float)n / 68.0F, var22);
            this.drawModalRectWithCustomSizedTexture(xOrigin + 212 + n, yOrigin + 220, 0, 0, 1, 10, textureX, textureY);
         }

         for(n = 0; (float)n < 68.0F; ++n) {
            GL11.glColor3f(var19, var21, (float)n / 68.0F);
            this.drawModalRectWithCustomSizedTexture(xOrigin + 212 + n, yOrigin + 232, 0, 0, 1, 10, textureX, textureY);
         }

         GL11.glEnable(3553);
         GL11.glColor3f(1.0F, 1.0F, 1.0F);
         this.drawModalRectWithCustomSizedTexture(xOrigin + 212 + (int)(var19 * 68.0F), yOrigin + 207, 317, 21, 3, 12, textureX, textureY);
         this.drawModalRectWithCustomSizedTexture(xOrigin + 212 + (int)(var21 * 68.0F), yOrigin + 219, 317, 21, 3, 12, textureX, textureY);
         this.drawModalRectWithCustomSizedTexture(xOrigin + 212 + (int)(var22 * 68.0F), yOrigin + 231, 317, 21, 3, 12, textureX, textureY);
      }

      GL11.glEnable(2929);
   }

   public void handleMouseInput() {
      super.handleMouseInput();
      this.mouseX = Mouse.getEventX() * super.width / super.mc.displayWidth;
      this.mouseY = super.height - Mouse.getEventY() * super.height / super.mc.displayHeight - 1;
      int mouseXInGUI = this.mouseX - super.guiLeft;
      int mouseYInGUI = this.mouseY - super.guiTop;
      this.hoveringOver = null;
      int numPaintjobs;
      int numRows;
      if(this.inCustomMode) {
         int gunStack = this.GetCustomPageX() - 32;
         int paintableType = this.GetCustomPageY();

         for(numPaintjobs = 0; numPaintjobs < 18; ++numPaintjobs) {
            for(numRows = 0; numRows < 4; ++numRows) {
               if(mouseXInGUI >= gunStack + 8 + 9 * numPaintjobs && mouseXInGUI < gunStack + 15 + 9 * numPaintjobs && mouseYInGUI >= paintableType + 208 + 9 * numRows && mouseYInGUI < paintableType + 215 + 9 * numRows) {
                  switch(Mouse.getEventButton()) {
                  case 0:
                     currentColour = paletteColours[numPaintjobs][numRows];
                     break;
                  case 1:
                     paletteColours[numPaintjobs][numRows] = currentColour;
                  }
               }
            }
         }

         if(Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
            if(mouseXInGUI >= gunStack + 212 && (float)mouseXInGUI < (float)(gunStack + 212) + 68.0F && mouseYInGUI >= paintableType + 208 && mouseYInGUI < paintableType + 218) {
               numPaintjobs = (int)((float)((mouseXInGUI - (gunStack + 212)) * 255) / 68.0F);
               currentColour &= '\uffff';
               currentColour |= numPaintjobs << 16;
            }

            if(mouseXInGUI >= gunStack + 212 && (float)mouseXInGUI < (float)(gunStack + 212) + 68.0F && mouseYInGUI >= paintableType + 220 && mouseYInGUI < paintableType + 230) {
               numPaintjobs = (int)((float)((mouseXInGUI - (gunStack + 212)) * 255) / 68.0F);
               currentColour &= 16711935;
               currentColour |= numPaintjobs << 8;
            }

            if(mouseXInGUI >= gunStack + 212 && (float)mouseXInGUI < (float)(gunStack + 212) + 68.0F && mouseYInGUI >= paintableType + 232 && mouseYInGUI < paintableType + 242) {
               numPaintjobs = (int)((float)((mouseXInGUI - (gunStack + 212)) * 255) / 68.0F);
               currentColour &= 16776960;
               currentColour |= numPaintjobs << 0;
            }
         }
      } else {
         ItemStack var13 = super.inventorySlots.getSlot(0).getStack();
         if(var13 != null && var13.getItem() instanceof IPaintableItem) {
            PaintableType var14 = ((IPaintableItem)var13.getItem()).GetPaintableType();
            numPaintjobs = var14.paintjobs.size();
            numRows = numPaintjobs / 9 + 1;

            for(int j = 0; j < numRows; ++j) {
               for(int i = 0; i < 9; ++i) {
                  if(9 * j + i < numPaintjobs) {
                     Paintjob paintjob = (Paintjob)var14.paintjobs.get(9 * j + i);
                     ItemStack stack = var13.copy();
                     stack.getTagCompound().setString("Paint", paintjob.iconName);
                     int slotX = 7 + i * 18;
                     int slotY = 129 + j * 18;
                     if(mouseXInGUI >= slotX && mouseXInGUI < slotX + 18 && mouseYInGUI >= slotY && mouseYInGUI < slotY + 18) {
                        this.hoveringOver = paintjob;
                     }
                  }
               }
            }
         }
      }

   }

   protected void mouseClicked(int x, int y, int button) {
      super.mouseClicked(x, y, button);
      if(button == 0) {
         if(this.hoveringOver != null) {
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketGunPaint(this.hoveringOver.ID)));
            ((ContainerPaintjobTable)super.inventorySlots).clickPaintjob(this.hoveringOver.ID);
         }
      }
   }

   static {
      ResetPalette();
   }
}
