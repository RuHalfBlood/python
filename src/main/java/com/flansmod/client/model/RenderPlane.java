package com.flansmod.client.model;

import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.model.ModelDriveable;
import com.flansmod.client.model.ModelPlane;
import com.flansmod.client.model.animation.AnimationController;
import com.flansmod.client.model.animation.AnimationPart;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.ItemPlane;
import com.flansmod.common.driveables.PlaneType;
import com.flansmod.common.driveables.Propeller;
import com.flansmod.common.driveables.ShootPoint;
import com.flansmod.common.paintjob.Paintjob;
import com.flansmod.common.vector.Vector3f;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderPlane extends Render implements IItemRenderer {

   public RenderPlane() {
      super.shadowSize = 1.0F;
   }

   public void render(EntityPlane entityPlane, double d, double d1, double d2, float f, float f1) {
      if(entityPlane.ridingEntity == null || entityPlane.ridingEntity.getClass().toString().indexOf("mcheli.aircraft.MCH_EntitySeat") <= 0) {
         this.bindEntityTexture(entityPlane);
         PlaneType type = entityPlane.getPlaneType();
         GL11.glPushMatrix();
         GL11.glTranslatef((float)d, (float)d1, (float)d2);

         float dYaw;
         for(dYaw = entityPlane.axes.getYaw() - entityPlane.prevRotationYaw; dYaw > 180.0F; dYaw -= 360.0F) {
            ;
         }

         while(dYaw <= -180.0F) {
            dYaw += 360.0F;
         }

         float dPitch;
         for(dPitch = entityPlane.axes.getPitch() - entityPlane.prevRotationPitch; dPitch > 180.0F; dPitch -= 360.0F) {
            ;
         }

         while(dPitch <= -180.0F) {
            dPitch += 360.0F;
         }

         float dRoll;
         for(dRoll = entityPlane.axes.getRoll() - entityPlane.prevRotationRoll; dRoll > 180.0F; dRoll -= 360.0F) {
            ;
         }

         while(dRoll <= -180.0F) {
            dRoll += 360.0F;
         }

         GL11.glRotatef(180.0F - entityPlane.prevRotationYaw - dYaw * f1, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(entityPlane.prevRotationPitch + dPitch * f1, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(entityPlane.prevRotationRoll + dRoll * f1, 1.0F, 0.0F, 0.0F);
         ModelPlane model = (ModelPlane)type.model;
         if(model != null) {
            GL11.glPushMatrix();
            GL11.glScalef(type.modelScale, type.modelScale, type.modelScale);
            model.render(entityPlane, f1);
            float dRotorAngle = entityPlane.rotorAngle - entityPlane.prevRotorAngle;
            float var10000 = entityPlane.prevRotorAngle + dRotorAngle * f1;

            int wingPos;
            for(wingPos = 0; wingPos < model.heliMainRotorModels.length; ++wingPos) {
               GL11.glPushMatrix();
               GL11.glTranslatef(model.heliMainRotorOrigins[wingPos].x, model.heliMainRotorOrigins[wingPos].y, model.heliMainRotorOrigins[wingPos].z);
               GL11.glRotatef((entityPlane.rotorAngle + f1 * entityPlane.throttle / 7.0F) * model.heliRotorSpeeds[wingPos] * 1440.0F / 3.1415927F, 0.0F, 1.0F, 0.0F);
               GL11.glTranslatef(-model.heliMainRotorOrigins[wingPos].x, -model.heliMainRotorOrigins[wingPos].y, -model.heliMainRotorOrigins[wingPos].z);
               model.renderRotor(entityPlane, 0.0625F, wingPos);
               GL11.glPopMatrix();
            }

            for(wingPos = 0; wingPos < model.heliTailRotorModels.length; ++wingPos) {
               GL11.glPushMatrix();
               GL11.glTranslatef(model.heliTailRotorOrigins[wingPos].x, model.heliTailRotorOrigins[wingPos].y, model.heliTailRotorOrigins[wingPos].z);
               GL11.glRotatef((entityPlane.rotorAngle + f1 * entityPlane.throttle / 7.0F) * 1440.0F / 3.1415927F, 0.0F, 0.0F, 1.0F);
               GL11.glTranslatef(-model.heliTailRotorOrigins[wingPos].x, -model.heliTailRotorOrigins[wingPos].y, -model.heliTailRotorOrigins[wingPos].z);
               model.renderTailRotor(entityPlane, 0.0625F, wingPos);
               GL11.glPopMatrix();
            }

            Vector3f var24 = this.getRenderPosition(entityPlane.wingPos, entityPlane.prevWingPos, f1);
            Vector3f wingRot = this.getRenderPosition(entityPlane.wingRot, entityPlane.prevWingRot, f1);
            if(entityPlane.initiatedAnim) {
               AnimationController doorPos = entityPlane.anim;
               AnimationPart doorRot = doorPos.getCorePart();
               this.renderAnimPart(doorRot, new Vector3f(0.0F, 0.0F, 0.0F), model, entityPlane, 0.0625F, f1);
            }

            GL11.glPushMatrix();
            GL11.glTranslatef(model.leftWingAttach.x + var24.x / 16.0F, model.leftWingAttach.y + var24.y / 16.0F, -model.leftWingAttach.z + var24.z / 16.0F);
            GL11.glRotatef(wingRot.x, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(wingRot.y, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(wingRot.z, 0.0F, 0.0F, 1.0F);
            model.renderLeftWing(entityPlane, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef(model.rightWingAttach.x + var24.x / 16.0F, model.rightWingAttach.y + var24.y / 16.0F, -model.rightWingAttach.z + var24.z / 16.0F);
            GL11.glRotatef(-wingRot.x, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-wingRot.y, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(wingRot.z, 0.0F, 0.0F, 1.0F);
            model.renderRightWing(entityPlane, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef(model.leftWingWheelAttach.x + entityPlane.wingWheelPos.x / 16.0F, model.leftWingWheelAttach.y + entityPlane.wingWheelPos.y / 16.0F, -model.leftWingWheelAttach.z + entityPlane.wingWheelPos.z / 16.0F);
            GL11.glRotatef(entityPlane.wingWheelRot.x, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(entityPlane.wingWheelRot.y, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(entityPlane.wingWheelRot.z, 0.0F, 0.0F, 1.0F);
            model.renderLeftWingWheel(entityPlane, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef(model.rightWingWheelAttach.x + entityPlane.wingWheelPos.x / 16.0F, model.rightWingWheelAttach.y + entityPlane.wingWheelPos.y / 16.0F, -model.rightWingWheelAttach.z + entityPlane.wingWheelPos.z / 16.0F);
            GL11.glRotatef(-entityPlane.wingWheelRot.x, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-entityPlane.wingWheelRot.y, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(entityPlane.wingWheelRot.z, 0.0F, 0.0F, 1.0F);
            model.renderRightWingWheel(entityPlane, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef(model.bodyWheelAttach.x + entityPlane.coreWheelPos.x / 16.0F, model.bodyWheelAttach.y + entityPlane.coreWheelPos.y / 16.0F, model.bodyWheelAttach.z + entityPlane.coreWheelPos.z / 16.0F);
            GL11.glRotatef(entityPlane.coreWheelRot.x, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(entityPlane.coreWheelRot.y, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(entityPlane.coreWheelRot.z, 0.0F, 0.0F, 1.0F);
            model.renderCoreWheel(entityPlane, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef(model.tailWheelAttach.x + entityPlane.tailWheelPos.x / 16.0F, model.tailWheelAttach.y + entityPlane.tailWheelPos.y / 16.0F, model.tailWheelAttach.z + entityPlane.tailWheelPos.z / 16.0F);
            GL11.glRotatef(entityPlane.tailWheelRot.x, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(entityPlane.tailWheelRot.y, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(entityPlane.tailWheelRot.z, 0.0F, 0.0F, 1.0F);
            model.renderTailWheel(entityPlane, 0.0625F);
            GL11.glPopMatrix();
            Vector3f var25 = this.getRenderPosition(entityPlane.doorPos, entityPlane.prevDoorPos, f1);
            Vector3f var26 = this.getRenderPosition(entityPlane.doorRot, entityPlane.prevDoorRot, f1);
            GL11.glPushMatrix();
            GL11.glTranslatef(model.doorAttach.x + var25.x / 16.0F, model.doorAttach.y + var25.y / 16.0F, model.doorAttach.z + var25.z / 16.0F);
            GL11.glRotatef(var26.x, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(var26.y, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var26.z, 0.0F, 0.0F, 1.0F);
            model.renderDoor(entityPlane, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
         }

         if(FlansMod.DEBUG) {
            GL11.glDisable(3553);
            GL11.glEnable(3042);
            GL11.glDisable(2929);
            GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.3F);
            GL11.glScalef(-1.0F, 1.0F, -1.0F);
            Iterator var21 = entityPlane.getDriveableData().parts.values().iterator();

            while(var21.hasNext()) {
               DriveablePart point = (DriveablePart)var21.next();
               if(point.box != null) {
                  GL11.glColor4f(1.0F, entityPlane.isPartIntact(point.type)?1.0F:0.0F, 0.0F, 0.3F);
                  renderAABB(AxisAlignedBB.getBoundingBox((double)point.box.x, (double)point.box.y, (double)point.box.z, (double)(point.box.x + point.box.w), (double)(point.box.y + point.box.h), (double)(point.box.z + point.box.d)));
               }
            }

            GL11.glColor4f(1.0F, 1.0F, 0.0F, 0.3F);
            var21 = type.propellers.iterator();

            while(var21.hasNext()) {
               Propeller var22 = (Propeller)var21.next();
               renderAABB(AxisAlignedBB.getBoundingBox((double)((float)var22.x / 16.0F - 0.25F), (double)((float)var22.y / 16.0F - 0.25F), (double)((float)var22.z / 16.0F - 0.25F), (double)((float)var22.x / 16.0F + 0.25F), (double)((float)var22.y / 16.0F + 0.25F), (double)((float)var22.z / 16.0F + 0.25F)));
            }

            GL11.glColor4f(1.0F, 0.0F, 1.0F, 0.3F);
            var21 = type.shootPointsPrimary.iterator();

            ShootPoint var23;
            while(var21.hasNext()) {
               var23 = (ShootPoint)var21.next();
               renderAABB(AxisAlignedBB.getBoundingBox((double)(var23.rootPos.position.x - 0.25F), (double)(var23.rootPos.position.y - 0.25F), (double)(var23.rootPos.position.z - 0.25F), (double)(var23.rootPos.position.x + 0.25F), (double)(var23.rootPos.position.y + 0.25F), (double)(var23.rootPos.position.z + 0.25F)));
            }

            GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.3F);
            var21 = type.shootPointsSecondary.iterator();

            while(var21.hasNext()) {
               var23 = (ShootPoint)var21.next();
               renderAABB(AxisAlignedBB.getBoundingBox((double)(var23.rootPos.position.x - 0.25F), (double)(var23.rootPos.position.y - 0.25F), (double)(var23.rootPos.position.z - 0.25F), (double)(var23.rootPos.position.x + 0.25F), (double)(var23.rootPos.position.y + 0.25F), (double)(var23.rootPos.position.z + 0.25F)));
            }

            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }

         GL11.glPopMatrix();
      }
   }

   public Vector3f getRenderPosition(Vector3f current, Vector3f previous, float f) {
      Vector3f diff = new Vector3f(current.x - previous.x, current.y - previous.y, current.z - previous.z);
      Vector3f corrected = new Vector3f(previous.x + diff.x * f, previous.y + diff.y * f, previous.z + diff.z * f);
      return corrected;
   }

   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
      this.render((EntityPlane)entity, d, d1, d2, f, f1);
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      DriveableType type = ((EntityDriveable)entity).getDriveableType();
      Paintjob paintjob = type.getPaintjob(((EntityDriveable)entity).getDriveableData().paintjobID);
      return FlansModResourceHandler.getPaintjobTexture(paintjob);
   }

   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
       switch (type) {
           case EQUIPPED: 
           case EQUIPPED_FIRST_PERSON: 
           case ENTITY: {
               return Minecraft.getMinecraft().gameSettings.fancyGraphics && item != null && item.getItem() instanceof ItemPlane && ((ItemPlane)item.getItem()).type.model != null;
           }
       }
       return false;
   }


   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return false;
   }



   public /* varargs */ void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
       GL11.glPushMatrix();
       if (item != null && item.getItem() instanceof ItemPlane) {
           PlaneType planeType = ((ItemPlane)item.getItem()).type;
           if (planeType.model != null) {
               float scale = 0.5f;
               switch (type) {
                   case ENTITY: {
                       scale = 1.5f;
                       GL11.glRotatef((float)((EntityItem)data[1]).ticksExisted, (float)0.0f, (float)1.0f, (float)0.0f);
                       break;
                   }
                   case EQUIPPED: {
                       GL11.glRotatef((float)15.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                       GL11.glRotatef((float)15.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                       GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                       GL11.glTranslatef((float)0.0f, (float)0.2f, (float)0.4f);
                       scale = 1.0f;
                       break;
                   }
                   case EQUIPPED_FIRST_PERSON: {
                       GL11.glRotatef((float)25.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                       GL11.glRotatef((float)-5.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                       GL11.glTranslatef((float)0.15f, (float)0.45f, (float)-0.6f);
                       GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                       break;
                   }
               }
               GL11.glScalef((float)(scale / planeType.cameraDistance), (float)(scale / planeType.cameraDistance), (float)(scale / planeType.cameraDistance));
               Minecraft.getMinecraft().renderEngine.bindTexture(FlansModResourceHandler.getTexture(planeType));
               ModelDriveable model = planeType.model;
               model.render(planeType);
           }
       }
       GL11.glPopMatrix();
   }


   public int getPartId(int i) {
      return i;
   }

   public void renderAnimPart(AnimationPart p, Vector3f parent, ModelPlane mod, EntityPlane plane, float f5, float f1) {
      Vector3f pos = Vector3f.sub(p.position, parent, (Vector3f)null);
      Vector3f offset = this.Interpolate(p.offset, p.prevOff, f1);
      Vector3f rotation = this.Interpolate(p.rotation, p.prevRot, f1);
      GL11.glPushMatrix();
      GL11.glTranslatef(pos.x / 16.0F, -pos.y / 16.0F, -pos.z / 16.0F);
      GL11.glRotatef(rotation.x, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(rotation.y, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(rotation.z, 0.0F, 0.0F, 1.0F);
      GL11.glTranslatef(offset.x / 16.0F, offset.y / 16.0F, offset.z / 16.0F);
      int i = this.getPartId(p.type);
      mod.renderValk(plane, f5, i);
      if(p.hasChildren) {
         Iterator var11 = p.children.iterator();

         while(var11.hasNext()) {
            AnimationPart p2 = (AnimationPart)var11.next();
            this.renderAnimPart(p2, p.position, mod, plane, f5, f1);
         }
      }

      GL11.glPopMatrix();
   }

   public Vector3f Interpolate(Vector3f current, Vector3f prev, float f1) {
      Vector3f result = new Vector3f(prev.x + (current.x - prev.x) * f1, prev.y + (current.y - prev.y) * f1, prev.z + (current.z - prev.z) * f1);
      return result;
   }
}
