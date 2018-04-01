package com.flansmod.hand.helpers.loader;

import java.io.File;
import com.flansmod.hand.helpers.loader.NIConfiguration;

public interface ILoader {

   void processData(File var1, NIConfiguration var2);

   ILoader.LoaderType getLoaderType();

   String getPath();

   public static enum LoaderType {

      FILE("FILE", 0);
      // $FF: synthetic field
      private static final ILoader.LoaderType[] $VALUES = new ILoader.LoaderType[]{FILE};


      private LoaderType(String var1, int var2) {}

   }
}
