package com.flansmod.client.model;

import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.common.guns.EntityGrenade;
import com.flansmod.common.guns.GrenadeType;
import com.flansmod.common.guns.ItemGrenade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderGrenade extends Render implements IItemRenderer {

   public RenderGrenade() {
      super.shadowSize = 0.1F;
   }

   public void render(EntityGrenade grenade, double d, double d1, double d2, float f, float f1) {
      this.bindEntityTexture(grenade);
      GL11.glPushMatrix();
      GL11.glTranslatef((float)d, (float)d1, (float)d2);
      if(grenade.stuck) {
         GL11.glRotatef(180.0F - grenade.axes.getYaw(), 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(grenade.axes.getPitch(), 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(grenade.axes.getRoll(), 1.0F, 0.0F, 0.0F);
      } else {
         float model;
         for(model = grenade.axes.getYaw() - grenade.prevRotationYaw; model > 180.0F; model -= 360.0F) {
            ;
         }

         while(model <= -180.0F) {
            model += 360.0F;
         }

         float dPitch;
         for(dPitch = grenade.axes.getPitch() - grenade.prevRotationPitch; dPitch > 180.0F; dPitch -= 360.0F) {
            ;
         }

         while(dPitch <= -180.0F) {
            dPitch += 360.0F;
         }

         float dRoll;
         for(dRoll = grenade.axes.getRoll() - grenade.prevRotationRoll; dRoll > 180.0F; dRoll -= 360.0F) {
            ;
         }

         while(dRoll <= -180.0F) {
            dRoll += 360.0F;
         }

         GL11.glRotatef(180.0F - grenade.prevRotationYaw - model * f1, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(grenade.prevRotationPitch + dPitch * f1, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(grenade.prevRotationRoll + dRoll * f1, 1.0F, 0.0F, 0.0F);
      }

      ModelBase model1 = grenade.type.model;
      if(model1 != null) {
         model1.render(grenade, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      }

      GL11.glPopMatrix();
   }

   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
      this.render((EntityGrenade)entity, d, d1, d2, f, f1);
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      ResourceLocation texture = FlansModResourceHandler.getTexture(((EntityGrenade)entity).type);
      return texture == null?FlansModResourceHandler.getIcon(((EntityGrenade)entity).type):texture;
   }

   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
       switch (type) {
           case EQUIPPED: 
           case EQUIPPED_FIRST_PERSON: {
               return item != null && item.getItem() instanceof ItemGrenade && ((ItemGrenade)item.getItem()).type.model != null;
           }
       }
       return false;
   }

   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
       return false;
   }

   public /* varargs */ void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
       GL11.glPushMatrix();
       if (item != null && item.getItem() instanceof ItemGrenade) {
           GrenadeType grenadeType = ((ItemGrenade)item.getItem()).type;
           if (grenadeType.model != null) {
               switch (type) {
                   case EQUIPPED: {
                       GL11.glRotatef((float)35.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                       GL11.glRotatef((float)-5.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                       GL11.glTranslatef((float)0.75f, (float)-0.22f, (float)-0.08f);
                       GL11.glTranslatef((float)0.0f, (float)0.25f, (float)0.0f);
                       break;
                   }
                   case EQUIPPED_FIRST_PERSON: {
                       GL11.glRotatef((float)25.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                       GL11.glRotatef((float)-5.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                       GL11.glTranslatef((float)0.15f, (float)0.2f, (float)-0.6f);
                       GL11.glTranslatef((float)0.0f, (float)0.25f, (float)0.0f);
                       break;
                   }
               }
               Minecraft.getMinecraft().renderEngine.bindTexture(FlansModResourceHandler.getTexture(grenadeType));
               ModelBase model = grenadeType.model;
               model.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
           }
       }
       GL11.glPopMatrix();
   }

}

