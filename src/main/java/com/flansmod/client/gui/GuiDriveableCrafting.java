package com.flansmod.client.gui;

import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.mechas.MechaType;
import com.flansmod.common.parts.ItemPart;
import com.flansmod.common.parts.PartType;
import com.flansmod.common.types.EnumType;
import cpw.mods.fml.client.FMLClientHandler;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiDriveableCrafting extends GuiScreen {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "gui/driveableCrafting.png");
   private InventoryPlayer inventory;
   private Minecraft mc;
   private World world;
   private int x;
   private int y;
   private int z;
   private static RenderItem itemRenderer = new RenderItem();
   private int guiOriginX;
   private int guiOriginY;
   private static int blueprintsScroll = 0;
   private int recipeScroll = 0;
   private static int selectedBlueprint = 0;
   private float spinner = 0.0F;
   private boolean canCraft = false;


   public GuiDriveableCrafting(InventoryPlayer playerinventory, World w, int i, int j, int k) {
      this.inventory = playerinventory;
      this.mc = FMLClientHandler.instance().getClient();
      this.world = w;
      this.x = i;
      this.y = j;
      this.z = k;
   }

   public void initGui() {
      super.initGui();
      super.buttonList.add(new GuiButton(0, super.width / 2 + 22, super.height / 2 + 63, 40, 20, "Craft"));
   }

   protected void actionPerformed(GuiButton button) {
      if(button.id == 0) {
         FlansMod.proxy.craftDriveable(this.inventory.player, (DriveableType)DriveableType.types.get(selectedBlueprint));
      }

   }

   public void drawScreen(int i, int j, float f) {
      ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
      int w = scaledresolution.getScaledWidth();
      int h = scaledresolution.getScaledHeight();
      this.drawDefaultBackground();
      GL11.glEnable(3042);
      this.mc.renderEngine.bindTexture(texture);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.guiOriginX = w / 2 - 88;
      this.guiOriginY = h / 2 - 99;
      this.drawTexturedModalRect(this.guiOriginX, this.guiOriginY, 0, 0, 176, 198);
      this.drawString(super.fontRendererObj, "Vehicle Crafting", this.guiOriginX + 6, this.guiOriginY + 6, 16777215);
      this.drawString(super.fontRendererObj, "Requires", this.guiOriginX + 6, this.guiOriginY + 125, 16777215);
      this.drawString(super.fontRendererObj, "Engine", this.guiOriginX + 114, this.guiOriginY + 141, 16777215);

      int engines;
      for(int selectedType = 0; selectedType < 2; ++selectedType) {
         for(int temporaryInventory = 0; temporaryInventory < 8; ++temporaryInventory) {
            engines = blueprintsScroll * 8 + 8 * selectedType + temporaryInventory;
            if(engines == selectedBlueprint) {
               this.mc.renderEngine.bindTexture(texture);
               this.drawTexturedModalRect(this.guiOriginX + 8 + temporaryInventory * 18, this.guiOriginY + 18 + selectedType * 18, 213, 11, 16, 16);
            }

            if(engines < DriveableType.types.size()) {
               DriveableType bestEngineSpeed = (DriveableType)DriveableType.types.get(engines);
               this.drawSlotInventory(new ItemStack(bestEngineSpeed.item), this.guiOriginX + 8 + temporaryInventory * 18, this.guiOriginY + 18 + selectedType * 18);
            }
         }
      }

      ++this.spinner;
      if(selectedBlueprint < DriveableType.types.size()) {
         this.canCraft = true;
         DriveableType var18 = (DriveableType)DriveableType.types.get(selectedBlueprint);
         if(var18 != null) {
            GL11.glPushMatrix();
            GL11.glEnable(2929);
            GL11.glEnable(3008);
            GL11.glTranslatef((float)(w / 2 - 46), (float)(h / 2 - 10), 100.0F);
            if(var18 instanceof MechaType) {
               GL11.glTranslatef(0.0F, 15.0F, 0.0F);
            }

            GL11.glScalef(-50.0F * var18.modelScale / var18.cameraDistance, 50.0F * var18.modelScale / var18.cameraDistance, 50.0F * var18.modelScale / var18.cameraDistance);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(30.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(this.spinner / 5.0F, 0.0F, 1.0F, 0.0F);
            this.mc.renderEngine.bindTexture(FlansModResourceHandler.getTexture(var18));
            if(var18.model != null) {
               var18.model.render(var18);
            }

            GL11.glDisable(2929);
            GL11.glDisable(3008);
            GL11.glPopMatrix();
            if(var18.model == null) {
               this.drawString(super.fontRendererObj, "Model not found.", this.guiOriginX + 12, this.guiOriginY + 84, 16777215);
            }

            String recipeName = var18.name;
            if(recipeName.length() > 16) {
               recipeName = recipeName.substring(0, 15) + "...";
            }

            this.drawString(super.fontRendererObj, recipeName, this.guiOriginX + 82, this.guiOriginY + 64, 16777215);
            this.drawString(super.fontRendererObj, "Cargo Slots : " + var18.numCargoSlots, this.guiOriginX + 82, this.guiOriginY + 74, 16777215);
            this.drawString(super.fontRendererObj, "Bomb Slots : " + var18.numBombSlots, this.guiOriginX + 82, this.guiOriginY + 84, 16777215);
            this.drawString(super.fontRendererObj, "Passengers : " + var18.numPassengers, this.guiOriginX + 82, this.guiOriginY + 94, 16777215);
            this.drawString(super.fontRendererObj, "Guns : " + var18.ammoSlots(), this.guiOriginX + 82, this.guiOriginY + 104, 16777215);
            this.drawString(super.fontRendererObj, var18.numEngines() + "x", this.guiOriginX + 100, this.guiOriginY + 141, 16777215);
            InventoryPlayer var19 = new InventoryPlayer((EntityPlayer)null);
            var19.copyInventory(this.inventory);

            int var21;
            for(engines = 0; engines < 3; ++engines) {
               for(var21 = 0; var21 < 4; ++var21) {
                  int bestEngineStack = this.recipeScroll * 4 + engines * 4 + var21;
                  if(bestEngineStack < var18.driveableRecipe.size()) {
                     ItemStack partType = (ItemStack)var18.driveableRecipe.get(bestEngineStack);
                     int part = 0;

                     for(int n = 0; n < var19.getSizeInventory(); ++n) {
                        ItemStack stackInSlot = var19.getStackInSlot(n);
                        if(stackInSlot != null && partType != null && stackInSlot.getItem() == partType.getItem() && stackInSlot.getItemDamage() == partType.getItemDamage()) {
                           int amountFound = Math.min(stackInSlot.stackSize, partType.stackSize - part);
                           stackInSlot.stackSize -= amountFound;
                           if(stackInSlot.stackSize <= 0) {
                              stackInSlot = null;
                           }

                           var19.setInventorySlotContents(n, stackInSlot);
                           part += amountFound;
                           if(part == partType.stackSize) {
                              break;
                           }
                        }
                     }

                     if(part < partType.stackSize) {
                        this.mc.renderEngine.bindTexture(texture);
                        this.drawTexturedModalRect(this.guiOriginX + 8 + var21 * 18, this.guiOriginY + 138 + engines * 18, 195, 11, 16, 16);
                        this.canCraft = false;
                     }

                     this.drawSlotInventory(partType, this.guiOriginX + 8 + var21 * 18, this.guiOriginY + 138 + engines * 18);
                  }
               }
            }

            HashMap var20 = new HashMap();

            ItemStack var22;
            for(var21 = 0; var21 < var19.getSizeInventory(); ++var21) {
               var22 = var19.getStackInSlot(var21);
               if(var22 != null && var22.getItem() instanceof ItemPart) {
                  PartType var24 = ((ItemPart)var22.getItem()).type;
                  if(var24.category == 2 && var24.worksWith.contains(EnumType.getFromObject(var18))) {
                     if(var20.containsKey(var24)) {
                        ItemStack var10000 = (ItemStack)var20.get(var24);
                        var10000.stackSize += var22.stackSize;
                     } else {
                        var20.put(var24, var22);
                     }
                  }
               }
            }

            float var23 = -1.0F;
            var22 = null;
            Iterator var25 = var20.keySet().iterator();

            while(var25.hasNext()) {
               PartType var26 = (PartType)var25.next();
               if(var26.engineSpeed > var23 && ((ItemStack)var20.get(var26)).stackSize >= var18.numEngines()) {
                  var23 = var26.engineSpeed;
                  var22 = (ItemStack)var20.get(var26);
               }
            }

            this.mc.renderEngine.bindTexture(texture);
            if(var22 == null) {
               this.drawTexturedModalRect(this.guiOriginX + 152, this.guiOriginY + 138, 195, 11, 16, 16);
               this.canCraft = false;
            } else {
               this.drawSlotInventory(var22, this.guiOriginX + 152, this.guiOriginY + 138);
            }
         }

         if(!this.canCraft) {
            this.mc.renderEngine.bindTexture(texture);
            this.drawTexturedModalRect(this.guiOriginX + 108, this.guiOriginY + 160, 176, 28, 44, 24);
            this.drawString(super.fontRendererObj, "Craft", this.guiOriginX + 116, this.guiOriginY + 168, 10526880);
         } else {
            super.drawScreen(i, j, f);
         }

      }
   }

   private void drawSlotInventory(ItemStack itemstack, int i, int j) {
      if(itemstack != null && itemstack.getItem() != null) {
         itemRenderer.renderItemIntoGUI(super.fontRendererObj, this.mc.renderEngine, itemstack, i, j);
         itemRenderer.renderItemOverlayIntoGUI(super.fontRendererObj, this.mc.renderEngine, itemstack, i, j);
         GL11.glDisable(2896);
         GL11.glDisable(2929);
      }
   }

   protected void keyTyped(char c, int i) {
      if(i == 1 || i == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
         this.mc.thePlayer.closeScreen();
      }

   }

   protected void mouseClicked(int i, int j, int k) {
      super.mouseClicked(i, j, k);
      int x = i - this.guiOriginX;
      int y = j - this.guiOriginY;
      if(k == 0 || k == 1) {
         for(int selectedType = 0; selectedType < 2; ++selectedType) {
            for(int n = 0; n < 8; ++n) {
               if(x >= 8 + n * 18 && x <= 26 + n * 18 && y >= 18 + 18 * selectedType && y <= 42 + 18 * selectedType) {
                  selectedBlueprint = blueprintsScroll * 8 + selectedType * 8 + n;
               }
            }
         }

         if(x >= 157 && x <= 167 && y >= 21 && y <= 31 && blueprintsScroll > 0) {
            --blueprintsScroll;
         }

         if(x >= 157 && x <= 167 && y >= 39 && y <= 49 && blueprintsScroll * 8 + 16 < DriveableType.types.size()) {
            ++blueprintsScroll;
         }

         if(selectedBlueprint >= DriveableType.types.size()) {
            return;
         }

         if(x >= 83 && x <= 93 && y >= 141 && y <= 151 && this.recipeScroll > 0) {
            --this.recipeScroll;
         }

         if(x >= 83 && x <= 93 && y >= 177 && y <= 187) {
            DriveableType var8 = (DriveableType)DriveableType.types.get(selectedBlueprint);
            if(var8 != null && this.recipeScroll * 4 + 12 < var8.driveableRecipe.size()) {
               ++this.recipeScroll;
            }
         }
      }

   }

   public boolean doesGuiPauseGame() {
      return false;
   }

}
