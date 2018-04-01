package com.flansmod.client.gui;

import com.flansmod.api.IControllable;
import com.flansmod.client.FlansModClient;
import com.flansmod.client.KeyInputHandler;
import com.flansmod.common.FlansMod;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiDriveableController extends GuiScreen {

   private IControllable plane;
   private boolean leftMouseHeld;
   private boolean rightMouseHeld;


   public GuiDriveableController(IControllable thePlane) {
      this.plane = thePlane;
   }

   public void initGui() {
      if(super.mc.gameSettings.thirdPersonView == 1) {
         super.mc.renderViewEntity = (EntityLivingBase)(this.plane.getCamera() == null?super.mc.thePlayer:this.plane.getCamera());
      }

   }

   public void onGuiClosed() {
      super.mc.mouseHelper.ungrabMouseCursor();
      super.mc.renderViewEntity = super.mc.thePlayer;
   }

   public void handleMouseInput() {
      EntityPlayer player = (EntityPlayer)this.plane.getControllingEntity();
      if(player != super.mc.thePlayer) {
         super.mc.displayGuiScreen((GuiScreen)null);
      } else {
         int dWheel = Mouse.getDWheel();
         if(dWheel != 0) {
            player.inventory.changeCurrentItem(dWheel);
         }

         if(Mouse.isButtonDown(1)) {
            this.plane.pressKey(8, player);
         }

         if(!this.leftMouseHeld && Mouse.isButtonDown(0)) {
            this.leftMouseHeld = true;
            this.plane.updateKeyHeldState(9, true);
         }

         if(this.leftMouseHeld && !Mouse.isButtonDown(0)) {
            this.leftMouseHeld = false;
            this.plane.updateKeyHeldState(9, false);
         }

         if(!this.rightMouseHeld && Mouse.isButtonDown(1)) {
            this.rightMouseHeld = true;
            this.plane.updateKeyHeldState(8, true);
         }

         if(this.rightMouseHeld && !Mouse.isButtonDown(1)) {
            this.rightMouseHeld = false;
            this.plane.updateKeyHeldState(8, false);
         }

      }
   }

   protected void keyTyped(char c, int i) {
      if(i == 1) {
         super.mc.displayGuiScreen((GuiScreen)null);
         super.mc.displayInGameMenu();
      }

      if(i == 59) {
         super.mc.gameSettings.hideGUI = !super.mc.gameSettings.hideGUI;
      }

      if(i == 61) {
         super.mc.gameSettings.showDebugInfo = !super.mc.gameSettings.showDebugInfo;
      }

      if(i == 63) {
         super.mc.gameSettings.thirdPersonView = (super.mc.gameSettings.thirdPersonView + 1) % 3;
         if(super.mc.gameSettings.thirdPersonView == 1) {
            super.mc.renderViewEntity = (EntityLivingBase)(this.plane.getCamera() == null?super.mc.thePlayer:this.plane.getCamera());
         } else {
            super.mc.renderViewEntity = super.mc.thePlayer;
         }
      }

      if(i == 66) {
         super.mc.gameSettings.smoothCamera = !super.mc.gameSettings.smoothCamera;
      }

      if(i == super.mc.gameSettings.keyBindInventory.getKeyCode()) {
         super.mc.displayGuiScreen(new GuiInventory(super.mc.thePlayer));
      }

      if(i == super.mc.gameSettings.keyBindDrop.getKeyCode()) {
         ;
      }

      if(i == super.mc.gameSettings.keyBindChat.getKeyCode()) {
         super.mc.displayGuiScreen(new GuiChat());
      }

      if(i == super.mc.gameSettings.keyBindCommand.getKeyCode()) {
         super.mc.displayGuiScreen(new GuiChat("/"));
      }

      if(i == KeyInputHandler.reloadModelsKey.getKeyCode()) {
         FlansModClient.reloadModels(false);
      }

   }

   public void updateScreen() {
      if(super.mc.gameSettings.thirdPersonView == 1) {
         super.mc.renderViewEntity = (EntityLivingBase)(this.plane.getCamera() == null?super.mc.thePlayer:this.plane.getCamera());
      } else {
         super.mc.renderViewEntity = super.mc.thePlayer;
      }

   }

   public void handleInput() {
      EntityPlayer player = (EntityPlayer)this.plane.getControllingEntity();
      if(player != super.mc.thePlayer) {
         super.mc.displayGuiScreen((GuiScreen)null);
      } else {
         if(!Mouse.isGrabbed()) {
            super.mc.mouseHelper.grabMouseCursor();
         }

         this.handleMouseInput();

         while(Keyboard.next()) {
            this.handleKeyboardInput();
         }

         int l = Mouse.getDX();
         int m = Mouse.getDY();
         this.plane.onMouseMoved(l, m);
         if(this.plane != null && !this.plane.isDead() && this.plane.getControllingEntity() != null && this.plane.getControllingEntity() instanceof EntityPlayer) {
            if(FlansMod.proxy.keyDown(super.mc.gameSettings.keyBindForward.getKeyCode())) {
               this.plane.pressKey(0, player);
            }

            if(FlansMod.proxy.keyDown(super.mc.gameSettings.keyBindBack.getKeyCode())) {
               this.plane.pressKey(1, player);
            }

            if(FlansMod.proxy.keyDown(super.mc.gameSettings.keyBindLeft.getKeyCode())) {
               this.plane.pressKey(2, player);
            }

            if(FlansMod.proxy.keyDown(super.mc.gameSettings.keyBindRight.getKeyCode())) {
               this.plane.pressKey(3, player);
            }

            if(FlansMod.proxy.keyDown(super.mc.gameSettings.keyBindJump.getKeyCode())) {
               this.plane.pressKey(4, player);
            }

            if(FlansMod.proxy.keyDown(KeyInputHandler.downKey.getKeyCode())) {
               this.plane.pressKey(5, player);
            }

            if(FlansMod.proxy.keyDown(super.mc.gameSettings.keyBindSneak.getKeyCode())) {
               this.plane.pressKey(6, player);
            }

            if(FlansMod.proxy.keyDown(KeyInputHandler.inventoryKey.getKeyCode())) {
               this.plane.pressKey(7, player);
            }

            if(FlansMod.proxy.keyDown(KeyInputHandler.bombKey.getKeyCode())) {
               this.plane.pressKey(8, player);
            }

            if(FlansMod.proxy.keyDown(KeyInputHandler.gunKey.getKeyCode())) {
               this.plane.pressKey(9, player);
            }

            if(FlansMod.proxy.keyDown(KeyInputHandler.controlSwitchKey.getKeyCode())) {
               this.plane.pressKey(10, player);
            }

            if(FlansMod.proxy.keyDown(KeyInputHandler.leftRollKey.getKeyCode())) {
               this.plane.pressKey(11, player);
            }

            if(FlansMod.proxy.keyDown(KeyInputHandler.rightRollKey.getKeyCode())) {
               this.plane.pressKey(12, player);
            }

            if(FlansMod.proxy.keyDown(KeyInputHandler.gearKey.getKeyCode())) {
               this.plane.pressKey(13, player);
            }

            if(FlansMod.proxy.keyDown(KeyInputHandler.doorKey.getKeyCode())) {
               this.plane.pressKey(14, player);
            }

            if(FlansMod.proxy.keyDown(KeyInputHandler.modeKey.getKeyCode())) {
               this.plane.pressKey(15, player);
            }

            if(FlansMod.proxy.keyDown(KeyInputHandler.flareKey.getKeyCode())) {
               this.plane.pressKey(18, player);
            }
         } else {
            super.mc.displayGuiScreen((GuiScreen)null);
         }

      }
   }

   public void drawBackground(int i) {}

   public boolean doesGuiPauseGame() {
      return false;
   }
}
