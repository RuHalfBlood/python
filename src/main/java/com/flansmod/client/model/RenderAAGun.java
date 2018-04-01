package com.flansmod.client.model;

import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.model.ModelAAGun;
import com.flansmod.common.guns.EntityAAGun;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderAAGun extends Render {

   public RenderAAGun() {
      super.shadowSize = 1.0F;
   }

   public void render(EntityAAGun aa, double d, double d1, double d2, float f, float f1) {
      if(aa.ridingEntity == null || aa.ridingEntity.getClass().toString().indexOf("mcheli.aircraft.MCH_EntitySeat") <= 0) {
         this.bindEntityTexture(aa);
         GL11.glPushMatrix();
         GL11.glTranslatef((float)d, (float)d1, (float)d2);
         GL11.glScalef(1.0F, 1.0F, 1.0F);

         float dYaw;
         for(dYaw = aa.gunYaw - aa.prevGunYaw; dYaw > 180.0F; dYaw -= 360.0F) {
            ;
         }

         while(dYaw <= -180.0F) {
            dYaw += 360.0F;
         }

         ModelAAGun modelAAGun = aa.type.model;
         if(modelAAGun != null) {
            modelAAGun.renderBase(0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, aa);
            GL11.glRotatef(180.0F - (aa.prevGunYaw + dYaw * f1), 0.0F, 1.0F, 0.0F);
            modelAAGun.renderGun(0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, aa);
         }

         GL11.glPopMatrix();
      }
   }

   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
      this.render((EntityAAGun)entity, d, d1, d2, f, f1);
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return FlansModResourceHandler.getTexture(((EntityAAGun)entity).type);
   }
}
