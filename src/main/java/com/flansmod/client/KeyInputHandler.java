package com.flansmod.client;

import com.flansmod.api.IControllable;
import com.flansmod.client.FlansModClient;
import com.flansmod.client.gui.GuiTeamScores;
import com.flansmod.client.gui.GuiTeamSelect;
import com.flansmod.common.FlansMod;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketGunMode;
import com.flansmod.common.network.PacketReload;
import com.flansmod.common.network.PacketRequestDebug;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyInputHandler {

   public static KeyBinding downKey = new KeyBinding("Down key", 29, "Flan\'s Mod");
   public static KeyBinding inventoryKey = new KeyBinding("Inventory key", 19, "Flan\'s Mod");
   public static KeyBinding bombKey = new KeyBinding("Bomb Key", 47, "Flan\'s Mod");
   public static KeyBinding gunKey = new KeyBinding("Gun Key", 48, "Flan\'s Mod");
   public static KeyBinding controlSwitchKey = new KeyBinding("Control Switch key", 46, "Flan\'s Mod");
   public static KeyBinding reloadKey = new KeyBinding("Reload key", 19, "Flan\'s Mod");
   public static KeyBinding gunModeKey = new KeyBinding("Gun Mode key", 33, "Flan\'s Mod");
   public static KeyBinding teamsMenuKey = new KeyBinding("Teams Menu Key", 34, "Flan\'s Mod");
   public static KeyBinding teamsScoresKey = new KeyBinding("Teams Scores Key", 35, "Flan\'s Mod");
   public static KeyBinding leftRollKey = new KeyBinding("Roll Left Key", 44, "Flan\'s Mod");
   public static KeyBinding rightRollKey = new KeyBinding("Roll Right Key", 45, "Flan\'s Mod");
   public static KeyBinding gearKey = new KeyBinding("Gear Up / Down Key", 38, "Flan\'s Mod");
   public static KeyBinding doorKey = new KeyBinding("Door Open / Close Key", 37, "Flan\'s Mod");
   public static KeyBinding modeKey = new KeyBinding("Mode Switch Key", 36, "Flan\'s Mod");
   public static KeyBinding flareKey = new KeyBinding("Flare Key", 49, "Flan\'s Mod");
   public static KeyBinding debugKey = new KeyBinding("Debug Key", 68, "Flan\'s Mod");
   public static KeyBinding reloadModelsKey = new KeyBinding("Reload Models Key", 67, "Flan\'s Mod");
   Minecraft mc;


   public KeyInputHandler() {
      ClientRegistry.registerKeyBinding(downKey);
      ClientRegistry.registerKeyBinding(inventoryKey);
      ClientRegistry.registerKeyBinding(bombKey);
      ClientRegistry.registerKeyBinding(gunKey);
      ClientRegistry.registerKeyBinding(controlSwitchKey);
      ClientRegistry.registerKeyBinding(reloadKey);
      ClientRegistry.registerKeyBinding(gunModeKey);
      ClientRegistry.registerKeyBinding(teamsMenuKey);
      ClientRegistry.registerKeyBinding(teamsScoresKey);
      ClientRegistry.registerKeyBinding(leftRollKey);
      ClientRegistry.registerKeyBinding(rightRollKey);
      ClientRegistry.registerKeyBinding(gearKey);
      ClientRegistry.registerKeyBinding(doorKey);
      ClientRegistry.registerKeyBinding(modeKey);
      ClientRegistry.registerKeyBinding(flareKey);
      ClientRegistry.registerKeyBinding(debugKey);
      ClientRegistry.registerKeyBinding(reloadModelsKey);
      this.mc = Minecraft.getMinecraft();
   }

   @SubscribeEvent
   public void onKeyInput(KeyInputEvent event) {
      if(!FMLClientHandler.instance().isGUIOpen(GuiChat.class) && this.mc.currentScreen == null) {
         EntityClientPlayerMP player = this.mc.thePlayer;
         Entity ridingEntity = player.ridingEntity;
         if(teamsMenuKey.isPressed()) {
            this.mc.displayGuiScreen(new GuiTeamSelect());
         } else if(teamsScoresKey.isPressed()) {
            this.mc.displayGuiScreen(new GuiTeamScores());
         } else if(reloadKey.isPressed() && FlansModClient.shootTime(false) <= 0) {
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketReload(false)));
         } else if(gunModeKey.isPressed()) {
            FlansMod.getPacketHandler().sendToServer((PacketBase)(new PacketGunMode()));
         } else {
            if(debugKey.isPressed()) {
               if(FlansMod.DEBUG) {
                  FlansMod.DEBUG = false;
               } else {
                  FlansMod.packetHandler.sendToServer((PacketBase)(new PacketRequestDebug()));
               }
            }

            if(reloadModelsKey.isPressed()) {
               FlansModClient.reloadModels(Keyboard.isKeyDown(42));
            }

            if(ridingEntity instanceof IControllable) {
               IControllable riding = (IControllable)ridingEntity;
               if(this.mc.gameSettings.keyBindForward.isPressed()) {
                  riding.pressKey(0, player);
               }

               if(this.mc.gameSettings.keyBindBack.isPressed()) {
                  riding.pressKey(1, player);
               }

               if(this.mc.gameSettings.keyBindLeft.isPressed()) {
                  riding.pressKey(2, player);
               }

               if(this.mc.gameSettings.keyBindRight.isPressed()) {
                  riding.pressKey(3, player);
               }

               if(this.mc.gameSettings.keyBindJump.isPressed()) {
                  riding.pressKey(4, player);
               }

               if(downKey.isPressed()) {
                  riding.pressKey(5, player);
               }

               if(this.mc.gameSettings.keyBindSneak.isPressed()) {
                  riding.pressKey(6, player);
               }

               if(this.mc.gameSettings.keyBindInventory.isPressed() || inventoryKey.isPressed()) {
                  riding.pressKey(7, player);
               }

               if(bombKey.isPressed()) {
                  riding.pressKey(8, player);
               }

               if(gunKey.isPressed()) {
                  riding.pressKey(9, player);
               }

               if(controlSwitchKey.isPressed()) {
                  riding.pressKey(10, player);
               }

               if(leftRollKey.isPressed()) {
                  riding.pressKey(11, player);
               }

               if(rightRollKey.isPressed()) {
                  riding.pressKey(12, player);
               }

               if(gearKey.isPressed()) {
                  riding.pressKey(13, player);
               }

               if(doorKey.isPressed()) {
                  riding.pressKey(14, player);
               }

               if(modeKey.isPressed()) {
                  riding.pressKey(15, player);
               }

               if(flareKey.isPressed()) {
                  riding.pressKey(18, player);
               }
            }

         }
      }
   }

}
