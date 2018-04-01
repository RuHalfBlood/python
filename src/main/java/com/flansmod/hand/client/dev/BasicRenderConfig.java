package com.flansmod.hand.client.dev;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import com.flansmod.hand.FlanshandCore;
import com.flansmod.hand.helpers.config.ConfigManager;
import com.flansmod.hand.helpers.config.IConfig;
import com.flansmod.hand.helpers.loader.NIConfiguration;
import com.flansmod.hand.helpers.loader.NILoader;

@SideOnly(Side.CLIENT)
public abstract class BasicRenderConfig implements IConfig {

   private String filename;


   public BasicRenderConfig(String filename) {
      this.filename = filename + ".fhcfg";
      ConfigManager.loadConfig(this);
   }

   public void saveConfig() {
      NIConfiguration configuration = new NIConfiguration(new File(FlanshandCore.flansFolder, "cfgs/" + this.filename));
      this.writeConfig(configuration);
      configuration.save();   Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Сохранено в (" + this.filename + ")"));
   }

   public static void readByReflection(BasicRenderConfig instance, NIConfiguration config) {
      Field[] var2 = instance.getClass().getFields();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Field field = var2[var4];

         try {
            field.setFloat(instance, config.getFloat(field.getName(), 0.0F));
         } catch (IllegalAccessException var7) {
            var7.printStackTrace();
         }
      }

   }

   public static void saveByReflection(BasicRenderConfig instance, NIConfiguration config) {
      NILoader.ConfigCategory category = new NILoader.ConfigCategory();
      Field[] var3 = instance.getClass().getFields();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Field field = var3[var5];

         try {
            category.put(field.getName(), String.valueOf(field.getFloat(instance)));
         } catch (IllegalAccessException var8) {
            var8.printStackTrace();
         }
      }

      config.getData().put("default", category);
   }

   public abstract void writeConfig(NIConfiguration var1);

   public abstract void setupConfig(NIConfiguration var1);

   public String getConfigFileName() {
      return this.filename;
   }
}
