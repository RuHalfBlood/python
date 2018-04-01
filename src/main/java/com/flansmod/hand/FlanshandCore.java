package com.flansmod.hand;

import com.flansmod.common.guns.ItemGun;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import net.minecraft.client.Minecraft;
import com.flansmod.hand.client.dev.flans.GuiFlansGunDev;
import org.lwjgl.input.Keyboard;

@Mod(
   modid = "flanshand_core",
   name = "HalfBlood",
   version = "HB1.0"
)
public class FlanshandCore {

   @Instance("flanshand_core")
   public static FlanshandCore INSTANCE;
   public static final String modid = "flanshand_core";
   public static final String VERSION = "1.0";
   public static File flansFolder = new File(Loader.instance().getConfigDir().getParentFile(), "/fhfcfg/");


   public FlanshandCore() {
      flansFolder.mkdirs();
   }

   @EventHandler
   public void init(FMLInitializationEvent ev) {
      FMLCommonHandler.instance().bus().register(this);
   }

   @SubscribeEvent
   @SideOnly(Side.CLIENT)
   public void gunDev(ClientTickEvent event) {
      Minecraft mc = Minecraft.getMinecraft();
      if(mc.thePlayer != null) {
         if(Keyboard.isKeyDown(66) && mc.inGameHasFocus && mc.thePlayer.capabilities.isCreativeMode && mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemGun) {
            mc.displayGuiScreen(new GuiFlansGunDev(mc.thePlayer.getCurrentEquippedItem(), ((ItemGun)mc.thePlayer.getCurrentEquippedItem().getItem()).getRenderConfig()));
         }

      }
   }

}
