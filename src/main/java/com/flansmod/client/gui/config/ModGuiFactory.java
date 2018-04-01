package com.flansmod.client.gui.config;

import com.flansmod.client.gui.config.ModGuiConfig;
import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionCategoryElement;
import cpw.mods.fml.client.IModGuiFactory.RuntimeOptionGuiHandler;
import java.util.Set;
import net.minecraft.client.Minecraft;

public class ModGuiFactory implements IModGuiFactory {

   public void initialize(Minecraft minecraftInstance) {}

   public Class mainConfigGuiClass() {
      return ModGuiConfig.class;
   }

   public Set runtimeGuiCategories() {
      return null;
   }

   public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
      return null;
   }
}
