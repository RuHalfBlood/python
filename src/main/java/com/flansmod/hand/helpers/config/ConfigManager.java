package com.flansmod.hand.helpers.config;

import java.io.File;
import com.flansmod.hand.helpers.config.IConfig;
import com.flansmod.hand.helpers.loader.ILoader;
import com.flansmod.hand.helpers.loader.NIConfiguration;
import com.flansmod.hand.helpers.loader.NILoader;

public final class ConfigManager {

   private ConfigManager() {
      throw new IllegalAccessError();
   }

   public static void loadConfig(IConfig config) {
      NILoader.loadConfig(new ConfigManager.ConfigLoader(config));
   }

   public static class ConfigLoader implements ILoader {

      public IConfig callBack;
      public String path;


      public ConfigLoader(IConfig callBack) {
         this.callBack = callBack;
         this.path = callBack.getConfigFileName();
      }

      public void processData(File currentFile, NIConfiguration data) {
         this.callBack.setupConfig(data);
      }

      public ILoader.LoaderType getLoaderType() {
         return ILoader.LoaderType.FILE;
      }

      public String getPath() {
         return this.path;
      }
   }
}
