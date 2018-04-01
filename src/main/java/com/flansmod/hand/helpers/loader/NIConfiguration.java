package com.flansmod.hand.helpers.loader;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.flansmod.hand.helpers.loader.NILoader;

public class NIConfiguration {

   private Map data = Maps.newLinkedHashMap();
   public static final String DEFAULT = "default";
   private File file;


   public NIConfiguration(File file) {
      this.file = file;
   }

   public void put(String key, NILoader.ConfigCategory value) {
      if(value.listCategoryData().size() != 0) {
         this.data.put(key, value);
      }
   }

   public String getString(String category, String key, Object defaultValue) {
      Object obj = this.get(category, key, defaultValue);
      return obj == null?String.valueOf(defaultValue):String.valueOf(obj);
   }

   public float getFloat(String key, float defaultValue) {
      return this.getFloat("default", key, defaultValue);
   }

   public float getFloat(String category, String key, float defaultValue) {
      try {
         return Float.parseFloat(this.getString(category, key, Float.valueOf(defaultValue)).replaceAll("\\s+", ""));
      } catch (NumberFormatException var5) {
         return defaultValue;
      }
   }

   private Object get(String category, String key, Object defaultValue) {
      if(!key.contains("\\s") && !key.contains("=") && !key.contains("[") && !key.contains("]")) {
         NILoader.ConfigCategory configCategory = (NILoader.ConfigCategory)this.data.get(category);
         if(configCategory == null) {
            NILoader.ConfigCategory value1 = new NILoader.ConfigCategory();
            value1.put(key, String.valueOf(defaultValue));
            this.data.put(category, value1);
            return null;
         } else {
            String value = configCategory.get(key);
            if(value == null) {
               configCategory.put(key, String.valueOf(defaultValue));
               return null;
            } else {
               return value;
            }
         }
      } else {
         throw new IllegalArgumentException("Key <" + key + "> contains illegal characters.");
      }
   }

   public Map getData() {
      return this.data;
   }

   public void save() {
      FileOutputStream outputStream = null;
      boolean end = false;

      try {
         outputStream = new FileOutputStream(this.file);
         Iterator ex = this.data.keySet().iterator();

         while(ex.hasNext()) {
            String e = (String)ex.next();
            if(!ex.hasNext()) {
               end = true;
            }

            this.writeString(outputStream, "[" + e + "]", false);
            NILoader.ConfigCategory category = (NILoader.ConfigCategory)this.data.get(e);
            Iterator categoryKeys = category.listCategoryData().keySet().iterator();
            boolean ending = false;

            while(categoryKeys.hasNext()) {
               String key = (String)categoryKeys.next();
               if(!categoryKeys.hasNext() && end) {
                  ending = true;
               }

               String value = String.valueOf(category.get(key));
               if(!value.equals("null")) {
                  this.writeString(outputStream, key + " = " + value, ending);
               } else {
                  this.writeString(outputStream, "- " + key, ending);
               }
            }

            if(!end) {
               outputStream.write(13);
            }
         }
      } catch (IOException var11) {
         try {
            outputStream.close();
         } catch (IOException var10) {
            var10.printStackTrace();
         }
      }

   }

   private void writeString(FileOutputStream stream, String str, boolean end) throws IOException {
      stream.write(str.getBytes());
      if(!end) {
         stream.write(13);
      }

   }

   public String toString() {
      return "LoadedData [mapSize = " + this.data.size() + "]";
   }

   static class Commentary {

      private List commentaries = Lists.newArrayList();


      public void putCommentary(String commentary) {
         this.commentaries.add(commentary);
      }

      public List getCommentaries() {
         return this.commentaries;
      }
   }
}
