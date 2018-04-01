package com.flansmod.client.model;

import com.flansmod.common.FlansMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderNull extends Render {

   private static final ResourceLocation texture = new ResourceLocation("Flan", "null.png");
   protected ModelBase model;


   public RenderNull() {
      super.shadowSize = 0.0F;
   }

   public void func_157_a(Entity entity, double d, double d1, double d2, float f, float f1) {}

   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
      if(FlansMod.DEBUG) {
         GL11.glPushMatrix();
         GL11.glTranslatef((float)d, (float)d1, (float)d2);
         GL11.glRotatef(-entity.rotationYaw, 0.0F, 1.0F, 0.0F);
         GL11.glDisable(3553);
         GL11.glEnable(3042);
         GL11.glColor4f(0.0F, 0.0F, 1.0F, 0.3F);
         GL11.glScalef(-1.0F, 1.0F, -1.0F);
         renderAABB(AxisAlignedBB.getBoundingBox(-0.25D, -0.25D, -0.25D, 0.25D, 0.25D, 0.25D));
         GL11.glEnable(3553);
         GL11.glEnable(2929);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPopMatrix();
      }

   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return texture;
   }

}
