package com.flansmod.client.tmt;

import com.flansmod.client.tmt.TexturedPolygon;
import java.util.ArrayList;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class TextureGroup {

   public ArrayList poly = new ArrayList();
   public String texture = "";


   public void addPoly(TexturedPolygon polygon) {
      this.poly.add(polygon);
   }

   public void loadTexture() {
      this.loadTexture(-1);
   }

   public void loadTexture(int defaultTexture) {
      if(!this.texture.equals("")) {
         TextureManager renderengine = RenderManager.instance.renderEngine;
         renderengine.bindTexture(new ResourceLocation("", this.texture));
      } else if(defaultTexture > -1) {
         RenderManager.instance.renderEngine.bindTexture(new ResourceLocation("", ""));
      }

   }
}
