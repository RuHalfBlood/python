package com.flansmod.client.model;

import com.flansmod.client.model.ModelFlagpole;
import com.flansmod.common.teams.EntityFlagpole;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFlagpole extends Render {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "teamsMod/Flagpole.png");
   public ModelFlagpole modelFlagpole = new ModelFlagpole();


   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
      this.bindEntityTexture(entity);
      EntityFlagpole flagpole = (EntityFlagpole)entity;
      GL11.glPushMatrix();
      GL11.glTranslatef((float)d, (float)d1, (float)d2);
      GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
      GL11.glScalef(-1.0F, -1.0F, 1.0F);
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      this.modelFlagpole.renderPole(0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, flagpole);
      GL11.glPopMatrix();
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return texture;
   }

}
