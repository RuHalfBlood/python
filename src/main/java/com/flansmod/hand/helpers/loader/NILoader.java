package com.flansmod.hand.helpers.loader;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.flansmod.hand.FlanshandCore;
import com.flansmod.hand.helpers.loader.ILoader;
import com.flansmod.hand.helpers.loader.NIConfiguration;
import com.flansmod.hand.misc.ResourceReader;

public class NILoader {

   private static File currentFile;


   public static void loadConfig(ILoader loader) {
      if(loader != null) {
         String path = loader.getPath();
         if(loader.getLoaderType().equals(ILoader.LoaderType.FILE)) {
            if(!path.endsWith(".fhcfg")) {
               path = path + ".fhcfg";
            }

            File file = new File(FlanshandCore.flansFolder, "cfgs/" + path);
            currentFile = file;
            if(!file.exists()) {
               try {
                  File data = file.getParentFile();
                  if(data != null) {
                     data.mkdirs();
                  }

                  file.createNewFile();
               } catch (IOException var4) {
                  var4.printStackTrace();
               }
            }

            if(file.canRead()) {
               NIConfiguration data1 = loadFile(ResourceReader.read(file), file);
               loader.processData(currentFile, data1);
               if(file.canWrite()) {
                  data1.save();
               }
            }
         }

      }
   }

   private static NIConfiguration loadFile(List strs, File file) {
      NIConfiguration data = new NIConfiguration(file);
      String categoryName = "default";
      NILoader.ConfigCategory category = new NILoader.ConfigCategory();
      Iterator var5 = strs.iterator();

      while(var5.hasNext()) {
         String line = (String)var5.next();
         if(line != null && line.length() != 0 && line.trim().replaceAll("\\s+", "").length() != 0 && !line.startsWith("#") && !line.startsWith("//")) {
            if(line.startsWith("[") && line.endsWith("]")) {
               if(!line.equals("[default]")) {
                  data.put(categoryName, category);
                  category = new NILoader.ConfigCategory();
                  categoryName = line.substring(1, line.length() - 1);
               }
            } else {
               try {
                  boolean isKeyOnly = line.startsWith("-");
                  if(!isKeyOnly) {
                     String key = line.substring(0, line.indexOf("=")).trim().replaceAll("\\s+", "");
                     String value = line.substring(line.indexOf("=") + 1).trim();
                     category.put(key, value);
                  } else {
                     category.put(line.replace("-", "").trim().replaceAll("\\s+", ""), (String)null);
                  }
               } catch (Exception var10) {
                  ;
               }
            }
         }
      }

      data.put(categoryName, category);
      return data;
   }

   public static class ConfigCategory {

      private Map categoryData = new LinkedHashMap();


      public void put(String key, String value) {
         this.categoryData.put(key, value);
      }

      public String get(String key) {
         return (String)this.categoryData.get(key);
      }

      public Map listCategoryData() {
         return this.categoryData;
      }
   }
}
