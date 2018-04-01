package com.flansmod.client.model;

import com.flansmod.client.FlansModClient;
import com.flansmod.client.model.ModelFlagpole;
import com.flansmod.common.teams.EntityFlag;
import com.flansmod.common.teams.EntityFlagpole;
import com.flansmod.common.teams.Team;
import java.util.List;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFlag extends Render {

   private static final ResourceLocation texture = new ResourceLocation("flansmod", "teamsMod/Flagpole.png");
   public ModelFlagpole modelFlagpole = new ModelFlagpole();
   public static float angle;


   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
      this.bindEntityTexture(entity);
      EntityFlag flag = (EntityFlag)entity;
      int teamID = flag.getTeamID();
      Team team = FlansModClient.getTeam(teamID);
      if(team == null) {
         switch(teamID) {
         case 0:
            GL11.glColor3f(0.5019608F, 0.5019608F, 0.5019608F);
            break;
         case 1:
            GL11.glColor3f(0.2509804F, 0.2509804F, 0.2509804F);
            break;
         case 2:
            GL11.glColor3f(0.6313726F, 0.49803922F, 1.0F);
            break;
         case 3:
            GL11.glColor3f(1.0F, 0.49803922F, 0.7137255F);
         }
      } else {
         int ents = team.teamColour;
         GL11.glColor3f((float)(ents >> 16 & 255) / 255.0F, (float)(ents >> 8 & 255) / 255.0F, (float)(ents & 255) / 255.0F);
      }

      GL11.glPushMatrix();
      GL11.glTranslatef((float)d, (float)d1, (float)d2);
      GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
      List ents1 = flag.worldObj.getEntitiesWithinAABB(EntityFlagpole.class, flag.boundingBox.expand(1.0D, 2.0D, 1.0D));
      if(ents1.size() == 0) {
         GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.5F, 0.0F, 0.0F);
      }

      GL11.glScalef(-1.0F, -1.0F, 1.0F);
      this.modelFlagpole.renderFlag(0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, flag);
      GL11.glPopMatrix();
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return texture;
   }

}
