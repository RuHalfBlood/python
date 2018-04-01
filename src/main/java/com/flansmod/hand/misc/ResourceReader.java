package com.flansmod.hand.misc;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.flansmod.hand.FlanshandCore;
import org.apache.commons.io.IOUtils;

public class ResourceReader {

   public static List listNativeFolder(String folderName) {
      ArrayList str = Lists.newArrayList();
      File dir = new File(FlanshandCore.flansFolder, folderName);
      if(!dir.exists()) {
         dir.mkdirs();
         return str;
      } else {
         File[] var3 = dir.listFiles();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            File file = var3[var5];
            str.add(file.getName());
         }

         return str;
      }
   }

   public static List readFromNative(String name) {
      FileInputStream inputStream = null;

      try {
         File e = new File(FlanshandCore.flansFolder, name);
         inputStream = new FileInputStream(e);
      } catch (FileNotFoundException var14) {
         var14.printStackTrace();
      }

      List str;
      try {
         str = IOUtils.readLines(inputStream, "UTF-8");
      } catch (IOException var12) {
         throw new RuntimeException(var12);
      } finally {
         try {
            inputStream.close();
         } catch (IOException var11) {
            var11.printStackTrace();
         }

      }

      return str;
   }

   public static List read(File file) {
      ArrayList str = Lists.newArrayList();
      if(!file.exists()) {
         return str;
      } else {
         FileInputStream inputStream = null;

         try {
            inputStream = new FileInputStream(file);
         } catch (IOException var15) {
            var15.printStackTrace();
         }

         ArrayList var4;
         try {
            List str1 = IOUtils.readLines(inputStream, "UTF-8");
            return str1;
         } catch (IOException var16) {
            var4 = str;
         } finally {
            try {
               inputStream.close();
            } catch (IOException var14) {
               var14.printStackTrace();
            }

         }

         return var4;
      }
   }
}
