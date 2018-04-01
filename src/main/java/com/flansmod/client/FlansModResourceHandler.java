package com.flansmod.client;

import com.flansmod.common.guns.GunType;
import com.flansmod.common.paintjob.PaintableType;
import com.flansmod.common.paintjob.Paintjob;
import com.flansmod.common.types.InfoType;
import java.util.HashMap;
import net.minecraft.util.ResourceLocation;

public class FlansModResourceHandler {

   private static HashMap iconMap = new HashMap();
   private static HashMap textureMap = new HashMap();
   private static HashMap paintjobMap = new HashMap();
   private static HashMap paintjobIconMap = new HashMap();
   private static HashMap scopeMap = new HashMap();
   private static HashMap soundMap = new HashMap();


   public static ResourceLocation getIcon(PaintableType paintableType, Paintjob paintjob) {
      if(paintjobIconMap.containsKey(paintjob)) {
         return (ResourceLocation)paintjobIconMap.get(paintjob);
      } else {
         ResourceLocation resLoc = new ResourceLocation("flansmod", "textures/items/" + paintjob.iconName + ".png");
         paintjobIconMap.put(paintjob, resLoc);
         return resLoc;
      }
   }

   public static ResourceLocation getIcon(InfoType infoType) {
      if(iconMap.containsKey(infoType)) {
         return (ResourceLocation)iconMap.get(infoType);
      } else {
         ResourceLocation resLoc = new ResourceLocation("flansmod", "textures/items/" + infoType.iconPath + ".png");
         iconMap.put(infoType, resLoc);
         return resLoc;
      }
   }

   public static ResourceLocation getTexture(InfoType infoType) {
      if(textureMap.containsKey(infoType)) {
         return (ResourceLocation)textureMap.get(infoType);
      } else {
         ResourceLocation resLoc = new ResourceLocation("flansmod", "skins/" + infoType.texture + ".png");
         if(infoType.texture != null) {
            textureMap.put(infoType, resLoc);
            return resLoc;
         } else {
            return null;
         }
      }
   }

   public static ResourceLocation getDeployableTexture(GunType gunType) {
      if(textureMap.containsKey(gunType)) {
         return (ResourceLocation)textureMap.get(gunType);
      } else {
         ResourceLocation resLoc = new ResourceLocation("flansmod", "skins/" + gunType.deployableTexture + ".png");
         textureMap.put(gunType, resLoc);
         return resLoc;
      }
   }

   public static ResourceLocation getScope(String scopeTexture) {
      if(scopeMap.containsKey(scopeTexture)) {
         return (ResourceLocation)scopeMap.get(scopeTexture);
      } else {
         ResourceLocation resLoc = new ResourceLocation("flansmod", "gui/" + scopeTexture + ".png");
         scopeMap.put(scopeTexture, resLoc);
         return resLoc;
      }
   }

   public static ResourceLocation getSound(String sound) {
      if(soundMap.containsKey(sound)) {
         ;
      }

      ResourceLocation resLoc = new ResourceLocation("flansmod", sound);
      soundMap.put(sound, resLoc);
      return resLoc;
   }

   public static ResourceLocation getPaintjobTexture(Paintjob paintjob) {
      if(paintjobMap.containsKey(paintjob)) {
         return (ResourceLocation)paintjobMap.get(paintjob);
      } else {
         ResourceLocation resLoc = new ResourceLocation("flansmod", "skins/" + paintjob.textureName + ".png");
         paintjobMap.put(paintjob, resLoc);
         return resLoc;
      }
   }

}
