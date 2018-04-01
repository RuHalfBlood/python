package com.flansmod.client.model;

import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.model.AnimTrackLink;
import com.flansmod.client.model.ModelDriveable;
import com.flansmod.client.model.ModelVehicle;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.ItemVehicle;
import com.flansmod.common.driveables.ShootPoint;
import com.flansmod.common.driveables.VehicleType;
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

public class RenderVehicle extends Render implements IItemRenderer {

   public RenderVehicle() {
      super.shadowSize = 1.0F;
   }

   public void render(EntityVehicle vehicle, double d, double d1, double d2, float f, float f1) {
      if(vehicle.ridingEntity == null || vehicle.ridingEntity.getClass().toString().indexOf("mcheli.aircraft.MCH_EntitySeat") <= 0) {
         this.bindEntityTexture(vehicle);
         VehicleType type = vehicle.getVehicleType();
         GL11.glPushMatrix();
         GL11.glTranslatef((float)d, (float)d1, (float)d2);

         float dYaw;
         for(dYaw = vehicle.axes.getYaw() - vehicle.prevRotationYaw; dYaw > 180.0F; dYaw -= 360.0F) {
            ;
         }

         while(dYaw <= -180.0F) {
            dYaw += 360.0F;
         }

         float dPitch;
         for(dPitch = vehicle.axes.getPitch() - vehicle.prevRotationPitch; dPitch > 180.0F; dPitch -= 360.0F) {
            ;
         }

         while(dPitch <= -180.0F) {
            dPitch += 360.0F;
         }

         float dRoll;
         for(dRoll = vehicle.axes.getRoll() - vehicle.prevRotationRoll; dRoll > 180.0F; dRoll -= 360.0F) {
            ;
         }

         while(dRoll <= -180.0F) {
            dRoll += 360.0F;
         }

         GL11.glRotatef(180.0F - vehicle.prevRotationYaw - dYaw * f1, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(vehicle.prevRotationPitch + dPitch * f1, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(vehicle.prevRotationRoll + dRoll * f1, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glPushMatrix();
         ModelVehicle modVehicle = (ModelVehicle)type.model;
         GL11.glPushMatrix();
         float point = (float)Math.sin(Math.toRadians((double)vehicle.recoilPos)) - (float)Math.sin(Math.toRadians((double)vehicle.lastRecoilPos));
         float newPos = (float)Math.sin(Math.toRadians((double)vehicle.lastRecoilPos)) + point * f1;
         GL11.glScalef(type.modelScale, type.modelScale, type.modelScale);
         if(modVehicle != null) {
            modVehicle.render(vehicle, f1);
         }

         int newRot2;
         AnimTrackLink newPos2;
         float bPitch;
         for(newRot2 = 0; newRot2 < vehicle.trackLinksLeft.length; ++newRot2) {
            newPos2 = vehicle.trackLinksLeft[newRot2];
            bPitch = newPos2.zRot;
            GL11.glPushMatrix();
            GL11.glTranslatef(newPos2.position.x / 16.0F, newPos2.position.y / 16.0F, newPos2.position.z / 16.0F);

            while(bPitch > 180.0F) {
               bPitch -= 360.0F;
            }

            while(bPitch <= -180.0F) {
               bPitch += 360.0F;
            }

            GL11.glRotatef(bPitch * 57.29578F, 0.0F, 0.0F, 1.0F);
            modVehicle.renderFancyTracks(vehicle, f1);
            GL11.glPopMatrix();
         }

         for(newRot2 = 0; newRot2 < vehicle.trackLinksRight.length; ++newRot2) {
            newPos2 = vehicle.trackLinksRight[newRot2];

            for(bPitch = newPos2.zRot; bPitch > 180.0F; bPitch -= 360.0F) {
               ;
            }

            while(bPitch <= -180.0F) {
               bPitch += 360.0F;
            }

            GL11.glPushMatrix();
            GL11.glTranslatef(newPos2.position.x / 16.0F, newPos2.position.y / 16.0F, newPos2.position.z / 16.0F);
            GL11.glRotatef(bPitch * 57.29578F, 0.0F, 0.0F, 1.0F);
            modVehicle.renderFancyTracks(vehicle, f1);
            GL11.glPopMatrix();
         }

         if(type.turretOrigin != null && vehicle.isPartIntact(EnumDriveablePart.turret) && vehicle.seats != null && vehicle.seats[0] != null) {
            dYaw = vehicle.seats[0].looking.getYaw() - vehicle.seats[0].prevLooking.getYaw();

            for(float var27 = vehicle.seats[0].looking.getPitch(); dYaw > 180.0F; dYaw -= 360.0F) {
               ;
            }

            while(dYaw <= -180.0F) {
               dYaw += 360.0F;
            }

            float var28 = vehicle.seats[0].prevLooking.getYaw() + dYaw * f1;
            GL11.glTranslatef(type.turretOrigin.x, type.turretOrigin.y, type.turretOrigin.z);
            GL11.glRotatef(-var28, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-type.turretOrigin.x, -type.turretOrigin.y, -type.turretOrigin.z);
            if(modVehicle != null) {
               modVehicle.renderTurret(0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, vehicle, f1);
            }

            if(modVehicle != null) {
               GL11.glTranslatef(modVehicle.barrelAttach.x, modVehicle.barrelAttach.y, -modVehicle.barrelAttach.z);
               bPitch = vehicle.seats[0].looking.getPitch() - vehicle.seats[0].prevLooking.getPitch();
               float aPitch = vehicle.seats[0].prevLooking.getPitch() + bPitch * f1;
               GL11.glRotatef(-aPitch, 0.0F, 0.0F, 1.0F);
               GL11.glTranslatef(newPos * -0.3125F, 0.0F, 0.0F);
               modVehicle.renderAnimBarrel(0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F, vehicle, f1);
            }
         }

         GL11.glPopMatrix();
         GL11.glPushMatrix();
         if(FlansMod.DEBUG && type.turretOrigin != null && vehicle.isPartIntact(EnumDriveablePart.turret) && vehicle.seats != null && vehicle.seats[0] != null) {
            for(dYaw = vehicle.seats[0].looking.getYaw() - vehicle.seats[0].prevLooking.getYaw(); dYaw > 180.0F; dYaw -= 360.0F) {
               ;
            }

            while(dYaw <= -180.0F) {
               dYaw += 360.0F;
            }

            point = vehicle.seats[0].prevLooking.getYaw() + dYaw * f1;
            GL11.glTranslatef(type.turretOrigin.x, type.turretOrigin.y, type.turretOrigin.z);
            GL11.glRotatef(-point, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-vehicle.seats[0].looking.getPitch(), 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-type.turretOrigin.x, -type.turretOrigin.y, -type.turretOrigin.z);
            GL11.glColor4f(0.0F, 0.0F, 1.0F, 0.3F);
            Iterator var24 = type.shootPointsPrimary.iterator();

            ShootPoint var29;
            while(var24.hasNext()) {
               var29 = (ShootPoint)var24.next();
               if(var29.rootPos.part == EnumDriveablePart.turret) {
                  renderAABB(AxisAlignedBB.getBoundingBox((double)(var29.rootPos.position.x - 0.25F), (double)(var29.rootPos.position.y - 0.25F), (double)(var29.rootPos.position.z - 0.25F), (double)(var29.rootPos.position.x + 0.25F), (double)(var29.rootPos.position.y + 0.25F), (double)(var29.rootPos.position.z + 0.25F)));
               }
            }

            GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.3F);
            var24 = type.shootPointsSecondary.iterator();

            while(var24.hasNext()) {
               var29 = (ShootPoint)var24.next();
               if(var29.rootPos.part == EnumDriveablePart.turret) {
                  renderAABB(AxisAlignedBB.getBoundingBox((double)(var29.rootPos.position.x - 0.25F), (double)(var29.rootPos.position.y - 0.25F), (double)(var29.rootPos.position.z - 0.25F), (double)(var29.rootPos.position.x + 0.25F), (double)(var29.rootPos.position.y + 0.25F), (double)(var29.rootPos.position.z + 0.25F)));
               }
            }
         }

         GL11.glPopMatrix();
         if(modVehicle != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef(modVehicle.drillHeadOrigin.x, modVehicle.drillHeadOrigin.y, modVehicle.drillHeadOrigin.z);
            GL11.glRotatef(vehicle.harvesterAngle * 50.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(-modVehicle.drillHeadOrigin.x, -modVehicle.drillHeadOrigin.y, -modVehicle.drillHeadOrigin.z);
            modVehicle.renderDrillBit(vehicle, f1);
            GL11.glPopMatrix();
         }

         if(modVehicle != null) {
            Vector3f var22 = this.Interpolate(vehicle.doorRot, vehicle.prevDoorRot, f1);
            Vector3f var26 = this.Interpolate(vehicle.doorPos, vehicle.prevDoorPos, f1);
            GL11.glPushMatrix();
            GL11.glTranslatef(modVehicle.doorAttach.x + var26.x / 16.0F, modVehicle.doorAttach.y + var26.y / 16.0F, -modVehicle.doorAttach.z + var26.z / 16.0F);
            GL11.glRotatef(var22.x, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-var22.y, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var22.z, 0.0F, 0.0F, 1.0F);
            modVehicle.renderDoor(vehicle, 0.0625F);
            GL11.glPopMatrix();
            Vector3f var31 = this.Interpolate(vehicle.door2Rot, vehicle.prevDoor2Rot, f1);
            Vector3f var30 = this.Interpolate(vehicle.door2Pos, vehicle.prevDoor2Pos, f1);
            GL11.glPushMatrix();
            GL11.glTranslatef(modVehicle.door2Attach.x + var30.x / 16.0F, modVehicle.door2Attach.y + var30.y / 16.0F, -modVehicle.door2Attach.z + var30.z / 16.0F);
            GL11.glRotatef(var31.x, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-var31.y, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(var31.z, 0.0F, 0.0F, 1.0F);
            modVehicle.renderDoor2(vehicle, 0.0625F);
            GL11.glPopMatrix();
         }

         GL11.glPopMatrix();
         if(FlansMod.DEBUG) {
            GL11.glDisable(3553);
            GL11.glEnable(3042);
            GL11.glDisable(2929);
            GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.3F);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            Iterator var21 = vehicle.getDriveableData().parts.values().iterator();

            while(var21.hasNext()) {
               DriveablePart var23 = (DriveablePart)var21.next();
               if(var23.box != null) {
                  renderAABB(AxisAlignedBB.getBoundingBox((double)var23.box.x, (double)var23.box.y, (double)var23.box.z, (double)(var23.box.x + var23.box.w), (double)(var23.box.y + var23.box.h), (double)(var23.box.z + var23.box.d)));
               }
            }

            GL11.glColor4f(0.0F, 0.0F, 1.0F, 0.3F);
            var21 = type.shootPointsPrimary.iterator();

            ShootPoint var25;
            while(var21.hasNext()) {
               var25 = (ShootPoint)var21.next();
               if(var25.rootPos.part != EnumDriveablePart.turret) {
                  renderAABB(AxisAlignedBB.getBoundingBox((double)(var25.rootPos.position.x - 0.25F), (double)(var25.rootPos.position.y - 0.25F), (double)(var25.rootPos.position.z - 0.25F), (double)(var25.rootPos.position.x + 0.25F), (double)(var25.rootPos.position.y + 0.25F), (double)(var25.rootPos.position.z + 0.25F)));
               }
            }

            GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.3F);
            var21 = type.shootPointsSecondary.iterator();

            while(var21.hasNext()) {
               var25 = (ShootPoint)var21.next();
               if(var25.rootPos.part != EnumDriveablePart.turret) {
                  renderAABB(AxisAlignedBB.getBoundingBox((double)(var25.rootPos.position.x - 0.25F), (double)(var25.rootPos.position.y - 0.25F), (double)(var25.rootPos.position.z - 0.25F), (double)(var25.rootPos.position.x + 0.25F), (double)(var25.rootPos.position.y + 0.25F), (double)(var25.rootPos.position.z + 0.25F)));
               }
            }

            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }

         GL11.glPopMatrix();
      }
   }

   public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
      this.render((EntityVehicle)entity, d, d1, d2, f, f1);
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
               return Minecraft.getMinecraft().gameSettings.fancyGraphics && item != null && item.getItem() instanceof ItemVehicle && ((ItemVehicle)item.getItem()).type.model != null;
           }
       }
       return false;
   }

   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
       return false;
   }

   public /* varargs */ void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
       GL11.glPushMatrix();
       if (item != null && item.getItem() instanceof ItemVehicle) {
           VehicleType vehicleType = ((ItemVehicle)item.getItem()).type;
           if (vehicleType.model != null) {
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
                       GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                       GL11.glTranslatef((float)0.0f, (float)0.15f, (float)-0.4f);
                       scale = 1.0f;
                       break;
                   }
                   case EQUIPPED_FIRST_PERSON: {
                       GL11.glRotatef((float)25.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                       GL11.glRotatef((float)-5.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                       GL11.glTranslatef((float)0.15f, (float)0.45f, (float)-0.6f);
                       break;
                   }
               }
               GL11.glScalef((float)(scale / vehicleType.cameraDistance), (float)(scale / vehicleType.cameraDistance), (float)(scale / vehicleType.cameraDistance));
               Minecraft.getMinecraft().renderEngine.bindTexture(FlansModResourceHandler.getTexture(vehicleType));
               ModelDriveable model = vehicleType.model;
               model.render(vehicleType);
           }
       }
       GL11.glPopMatrix();
   }


   public Vector3f Interpolate(Vector3f current, Vector3f prev, float f1) {
      Vector3f result = new Vector3f(prev.x + (current.x - prev.x) * f1, prev.y + (current.y - prev.y) * f1, prev.z + (current.z - prev.z) * f1);
      return result;
   }
}
