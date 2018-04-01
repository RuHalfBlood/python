package com.flansmod.client.model;

import com.flansmod.client.model.ModelDriveable;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.EnumPlaneMode;
import com.flansmod.common.driveables.PlaneType;
import com.flansmod.common.driveables.Propeller;
import com.flansmod.common.vector.Vector3f;
import java.util.Iterator;
import org.lwjgl.opengl.GL11;

public class ModelPlane extends ModelDriveable {

   public ModelRendererTurbo[] noseModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] leftWingModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] rightWingModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] topWingModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] bayModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] tailModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[][] propellerModels = new ModelRendererTurbo[0][0];
   public ModelRendererTurbo[] yawFlapModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] pitchFlapLeftModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] pitchFlapRightModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] pitchFlapLeftWingModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] pitchFlapRightWingModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] leftAnimWingModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] rightAnimWingModel = new ModelRendererTurbo[0];
   public Vector3f leftWingAttach = new Vector3f();
   public Vector3f rightWingAttach = new Vector3f();
   public ModelRendererTurbo[] bodyAnimWheelModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] tailAnimWheelModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] leftAnimWingWheelModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] rightAnimWingWheelModel = new ModelRendererTurbo[0];
   public Vector3f bodyWheelAttach = new Vector3f();
   public Vector3f tailWheelAttach = new Vector3f();
   public Vector3f leftWingWheelAttach = new Vector3f();
   public Vector3f rightWingWheelAttach = new Vector3f();
   public ModelRendererTurbo[] doorAnimModel = new ModelRendererTurbo[0];
   public Vector3f doorAttach = new Vector3f();
   public ModelRendererTurbo[][] heliMainRotorModels = new ModelRendererTurbo[0][0];
   public Vector3f[] heliMainRotorOrigins = new Vector3f[0];
   public float[] heliRotorSpeeds = new float[0];
   public ModelRendererTurbo[][] heliTailRotorModels = new ModelRendererTurbo[0][0];
   public Vector3f[] heliTailRotorOrigins = new Vector3f[0];
   public ModelRendererTurbo[] skidsModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] helicopterModeParts = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] planeModeParts = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] bodyWheelModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] tailWheelModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] leftWingWheelModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] rightWingWheelModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] tailDoorOpenModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] tailDoorCloseModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] rightWingPos1Model = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] rightWingPos2Model = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] leftWingPos1Model = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] leftWingPos2Model = new ModelRendererTurbo[0];
   public ModelRendererTurbo[] hudModel = new ModelRendererTurbo[0];
   public ModelRendererTurbo[][] valkyrie = new ModelRendererTurbo[0][0];


   public void render(EntityDriveable driveable, float f1) {
      this.render(0.0625F, (EntityPlane)driveable, f1);
   }

   public void render(DriveableType type) {
      super.render(type);
      this.renderPart(this.noseModel);
      this.renderPart(this.leftWingModel);
      this.renderPart(this.rightWingModel);
      GL11.glPushMatrix();
      GL11.glTranslatef(this.leftWingAttach.x, this.leftWingAttach.y, -this.leftWingAttach.z);
      this.renderPart(this.leftAnimWingModel);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslatef(this.rightWingAttach.x, this.rightWingAttach.y, -this.rightWingAttach.z);
      this.renderPart(this.rightAnimWingModel);
      GL11.glPopMatrix();
      this.renderPart(this.topWingModel);
      this.renderPart(this.bayModel);
      this.renderPart(this.tailModel);
      ModelRendererTurbo[][] var2 = this.propellerModels;
      int var3 = var2.length;

      int var4;
      ModelRendererTurbo[] partModel;
      for(var4 = 0; var4 < var3; ++var4) {
         partModel = var2[var4];

         for(int j = 0; j < partModel.length; ++j) {
            partModel[j].rotateAngleX = (float)j * 2.0F * 3.1415927F / (float)partModel.length;
            partModel[j].render(0.0625F);
         }
      }

      var2 = this.heliMainRotorModels;
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         partModel = var2[var4];
         this.renderPart(partModel);
      }

      var2 = this.heliTailRotorModels;
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         partModel = var2[var4];
         this.renderPart(partModel);
      }

      var2 = this.valkyrie;
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         partModel = var2[var4];
         this.renderPart(partModel);
      }

      this.renderPart(this.helicopterModeParts);
      this.renderPart(this.skidsModel);
      this.renderPart(this.yawFlapModel);
      this.renderPart(this.pitchFlapLeftModel);
      this.renderPart(this.pitchFlapRightModel);
      this.renderPart(this.pitchFlapLeftWingModel);
      this.renderPart(this.pitchFlapRightWingModel);
      this.renderPart(this.bodyWheelModel);
      this.renderPart(this.tailWheelModel);
      this.renderPart(this.leftWingWheelModel);
      this.renderPart(this.rightWingWheelModel);
      this.renderPart(this.tailDoorCloseModel);
      this.renderPart(this.rightWingPos1Model);
      this.renderPart(this.leftWingPos1Model);
      this.renderPart(this.hudModel);
      GL11.glPushMatrix();
      GL11.glTranslatef(this.bodyWheelAttach.x, this.bodyWheelAttach.y, -this.bodyWheelAttach.z);
      this.renderPart(this.bodyAnimWheelModel);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslatef(this.tailWheelAttach.x, this.tailWheelAttach.y, -this.tailWheelAttach.z);
      this.renderPart(this.tailAnimWheelModel);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslatef(this.leftWingWheelAttach.x, this.leftWingWheelAttach.y, -this.leftWingWheelAttach.z);
      this.renderPart(this.leftAnimWingWheelModel);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslatef(this.rightWingWheelAttach.x, this.rightWingWheelAttach.y, -this.rightWingWheelAttach.z);
      this.renderPart(this.rightAnimWingWheelModel);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glTranslatef(this.doorAttach.x, this.doorAttach.y, -this.doorAttach.z);
      this.renderPart(this.doorAnimModel);
      GL11.glPopMatrix();
   }

   public void render(float f5, EntityPlane plane, float f) {
      PlaneType type = plane.getPlaneType();

      float dAngle;
      for(dAngle = plane.propAngle - plane.prevPropAngle; dAngle > 180.0F; dAngle -= 360.0F) {
         ;
      }

      while(dAngle <= -180.0F) {
         dAngle += 360.0F;
      }

      float angle = plane.prevPropAngle + dAngle * f;
      Iterator var7 = plane.getPlaneType().propellers.iterator();

      int numParts;
      while(var7.hasNext()) {
         Propeller propeller = (Propeller)var7.next();
         if(plane.isPartIntact(propeller.planePart) && this.propellerModels.length > propeller.ID) {
            numParts = this.propellerModels[propeller.ID].length;

            for(int seat = 0; seat < numParts; ++seat) {
               this.propellerModels[propeller.ID][seat].rotateAngleX = angle + (float)seat * 2.0F * 3.1415927F / (float)numParts;
               this.propellerModels[propeller.ID][seat].render(f5);
            }
         }
      }

      ModelRendererTurbo[] var19;
      int var20;
      ModelRendererTurbo var21;
      if(plane.isPartIntact(EnumDriveablePart.nose)) {
         var19 = this.noseModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.render(f5);
         }
      }

      if(plane.isPartIntact(EnumDriveablePart.bay)) {
         var19 = this.bayModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.render(f5);
         }
      }

      if(plane.isPartIntact(EnumDriveablePart.tail)) {
         var19 = this.tailModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.render(f5);
         }

         var19 = this.tailDoorOpenModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(plane.varDoor) {
               var21.render(f5);
            }
         }

         var19 = this.tailDoorCloseModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(!plane.varDoor) {
               var21.render(f5);
            }
         }

         var19 = this.yawFlapModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.rotateAngleY = plane.flapsYaw * 3.1415927F / 180.0F;
            var21.render(f5);
         }

         var19 = this.pitchFlapLeftModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.rotateAngleZ = plane.flapsPitchLeft * 3.1415927F / 180.0F;
            var21.render(f5);
         }

         var19 = this.pitchFlapRightModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.rotateAngleZ = plane.flapsPitchRight * 3.1415927F / 180.0F;
            var21.render(f5);
         }
      }

      if(plane.isPartIntact(EnumDriveablePart.skids)) {
         var19 = this.skidsModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(plane.varGear) {
               var21.render(f5);
            }
         }
      }

      if(plane.isPartIntact(EnumDriveablePart.tailWheel)) {
         var19 = this.tailWheelModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(plane.varGear) {
               var21.render(f5);
            }
         }
      }

      if(plane.isPartIntact(EnumDriveablePart.leftWing)) {
         var19 = this.leftWingModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.render(f5);
         }

         var19 = this.leftWingPos1Model;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(plane.varWing) {
               var21.render(f5);
            }
         }

         var19 = this.leftWingPos2Model;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(!plane.varWing) {
               var21.render(f5);
            }
         }

         var19 = this.pitchFlapLeftWingModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.rotateAngleZ = plane.flapsPitchLeft * 3.1415927F / 180.0F;
            var21.render(f5);
         }
      }

      if(plane.isPartIntact(EnumDriveablePart.rightWing)) {
         var19 = this.rightWingModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.render(f5);
         }

         var19 = this.rightWingPos1Model;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(plane.varWing) {
               var21.render(f5);
            }
         }

         var19 = this.rightWingPos2Model;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(!plane.varWing) {
               var21.render(f5);
            }
         }

         var19 = this.pitchFlapRightWingModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.rotateAngleZ = plane.flapsPitchRight * 3.1415927F / 180.0F;
            var21.render(f5);
         }
      }

      if(plane.isPartIntact(EnumDriveablePart.leftWingWheel)) {
         var19 = this.leftWingWheelModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(plane.varGear) {
               var21.render(f5);
            }
         }
      }

      if(plane.isPartIntact(EnumDriveablePart.rightWingWheel)) {
         var19 = this.rightWingWheelModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(plane.varGear) {
               var21.render(f5);
            }
         }
      }

      if(plane.isPartIntact(EnumDriveablePart.core)) {
         var19 = super.bodyModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.render(f5);
         }

         var19 = super.bodyDoorOpenModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(plane.varDoor) {
               var21.render(f5);
            }
         }

         var19 = super.bodyDoorCloseModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(!plane.varDoor) {
               var21.render(f5);
            }
         }

         var19 = this.hudModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.rotateAngleX = -(plane.axes.getRoll() * 3.1415927F / 180.0F);
            var21.render(f5);
         }

         if(plane.mode == EnumPlaneMode.HELI) {
            this.renderPart(this.helicopterModeParts);
         } else {
            this.renderPart(this.planeModeParts);
         }
      }

      if(plane.isPartIntact(EnumDriveablePart.coreWheel)) {
         var19 = this.bodyWheelModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            if(plane.varGear) {
               var21.render(f5);
            }
         }
      }

      if(plane.isPartIntact(EnumDriveablePart.topWing)) {
         var19 = this.topWingModel;
         var20 = var19.length;

         for(numParts = 0; numParts < var20; ++numParts) {
            var21 = var19[numParts];
            var21.render(f5);
         }
      }

      EntitySeat[] var23 = plane.seats;
      var20 = var23.length;

      for(numParts = 0; numParts < var20; ++numParts) {
         EntitySeat var26 = var23[numParts];
         if(var26 != null && var26.seatInfo != null && var26.seatInfo.gunName != null && super.gunModels.get(var26.seatInfo.gunName) != null && plane.isPartIntact(var26.seatInfo.part)) {
            float yaw = var26.prevLooking.getYaw() + (var26.looking.getYaw() - var26.prevLooking.getYaw()) * f;
            float pitch = var26.prevLooking.getPitch() + (var26.looking.getPitch() - var26.prevLooking.getPitch()) * f;
            ModelRendererTurbo[][] gunModel = (ModelRendererTurbo[][])super.gunModels.get(var26.seatInfo.gunName);
            ModelRendererTurbo[] minigunSpeed = gunModel[0];
            int var15 = minigunSpeed.length;

            int var16;
            ModelRendererTurbo gunModelPart;
            for(var16 = 0; var16 < var15; ++var16) {
               gunModelPart = minigunSpeed[var16];
               gunModelPart.rotateAngleY = (180.0F - yaw) * 3.1415927F / 180.0F;
               gunModelPart.render(f5);
            }

            minigunSpeed = gunModel[1];
            var15 = minigunSpeed.length;

            for(var16 = 0; var16 < var15; ++var16) {
               gunModelPart = minigunSpeed[var16];
               gunModelPart.rotateAngleY = (180.0F - yaw) * 3.1415927F / 180.0F;
               gunModelPart.rotateAngleZ = -pitch * 3.1415927F / 180.0F;
               gunModelPart.render(f5);
            }

            minigunSpeed = gunModel[2];
            var15 = minigunSpeed.length;

            for(var16 = 0; var16 < var15; ++var16) {
               gunModelPart = minigunSpeed[var16];
               gunModelPart.rotateAngleY = (180.0F - yaw) * 3.1415927F / 180.0F;
               gunModelPart.rotateAngleZ = -pitch * 3.1415927F / 180.0F;
               gunModelPart.render(f5);
            }

            if(gunModel.length > 3) {
               float var22 = var26.getMinigunSpeed();
               ModelRendererTurbo[] var24 = gunModel[3];
               var16 = var24.length;

               for(int var25 = 0; var25 < var16; ++var25) {
                  ModelRendererTurbo gunModelPart1 = var24[var25];
                  gunModelPart1.rotateAngleY = (180.0F - yaw) * 3.1415927F / 180.0F;
                  gunModelPart1.rotateAngleZ = -pitch * 3.1415927F / 180.0F;
                  gunModelPart1.rotateAngleX = var26.minigunAngle * 0.5F;
                  gunModelPart1.render(f5);
               }
            }
         }
      }

   }

   public void renderValk(EntityPlane plane, float f5, int id) {
      ModelRendererTurbo[] var4 = this.valkyrie[id];
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         ModelRendererTurbo aModel = var4[var6];
         aModel.render(f5);
      }

   }

   public void renderLeftWing(EntityPlane plane, float f5) {
      if(plane.isPartIntact(EnumDriveablePart.leftWing)) {
         ModelRendererTurbo[] var3 = this.leftAnimWingModel;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            ModelRendererTurbo aAnimLeftWingModel = var3[var5];
            aAnimLeftWingModel.render(f5);
         }
      }

   }

   public void renderRightWing(EntityPlane plane, float f5) {
      if(plane.isPartIntact(EnumDriveablePart.rightWing)) {
         ModelRendererTurbo[] var3 = this.rightAnimWingModel;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            ModelRendererTurbo aAnimRightWingModel = var3[var5];
            aAnimRightWingModel.render(f5);
         }
      }

   }

   public void renderLeftWingWheel(EntityPlane plane, float f5) {
      if(plane.isPartIntact(EnumDriveablePart.leftWingWheel)) {
         ModelRendererTurbo[] var3 = this.leftAnimWingWheelModel;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            ModelRendererTurbo aAnimLeftWingWheelModel = var3[var5];
            aAnimLeftWingWheelModel.render(f5);
         }
      }

   }

   public void renderRightWingWheel(EntityPlane plane, float f5) {
      if(plane.isPartIntact(EnumDriveablePart.rightWingWheel)) {
         ModelRendererTurbo[] var3 = this.rightAnimWingWheelModel;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            ModelRendererTurbo aAnimRightWingWheelModel = var3[var5];
            aAnimRightWingWheelModel.render(f5);
         }
      }

   }

   public void renderCoreWheel(EntityPlane plane, float f5) {
      if(plane.isPartIntact(EnumDriveablePart.coreWheel)) {
         ModelRendererTurbo[] var3 = this.bodyAnimWheelModel;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            ModelRendererTurbo aAnimBodyWheelModel = var3[var5];
            aAnimBodyWheelModel.render(f5);
         }
      }

   }

   public void renderTailWheel(EntityPlane plane, float f5) {
      if(plane.isPartIntact(EnumDriveablePart.tailWheel)) {
         ModelRendererTurbo[] var3 = this.tailAnimWheelModel;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            ModelRendererTurbo aAnimTailWheelModel = var3[var5];
            aAnimTailWheelModel.render(f5);
         }
      }

   }

   public void renderDoor(EntityPlane plane, float f5) {
      if(plane.isPartIntact(EnumDriveablePart.core)) {
         ModelRendererTurbo[] var3 = this.doorAnimModel;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            ModelRendererTurbo aDoorAnimModel = var3[var5];
            aDoorAnimModel.render(f5);
         }
      }

   }

   public void renderRotor(EntityPlane plane, float f5, int i) {
      PlaneType type = plane.getPlaneType();
      if(i >= type.heliPropellers.size() || plane.isPartIntact(((Propeller)type.heliPropellers.get(i)).planePart)) {
         for(int j = 0; j < this.heliMainRotorModels[i].length; ++j) {
            this.heliMainRotorModels[i][j].render(f5);
         }
      }

   }

   public void renderTailRotor(EntityPlane plane, float f5, int i) {
      PlaneType type = plane.getPlaneType();
      if(i >= type.heliTailPropellers.size() || plane.isPartIntact(((Propeller)type.heliTailPropellers.get(i)).planePart)) {
         for(int j = 0; j < this.heliTailRotorModels[i].length; ++j) {
            this.heliTailRotorModels[i][j].render(f5);
         }
      }

   }

   public void flipAll() {
      super.flipAll();
      this.flip(this.noseModel);
      this.flip(this.leftWingModel);
      this.flip(this.rightWingModel);
      this.flip(this.topWingModel);
      this.flip(this.bayModel);
      this.flip(this.tailModel);
      this.flip(this.yawFlapModel);
      this.flip(this.skidsModel);
      this.flip(this.helicopterModeParts);
      this.flip(this.planeModeParts);
      this.flip(this.pitchFlapLeftModel);
      this.flip(this.pitchFlapRightModel);
      this.flip(this.pitchFlapLeftWingModel);
      this.flip(this.pitchFlapRightWingModel);
      this.flip(this.bodyWheelModel);
      this.flip(this.tailWheelModel);
      this.flip(this.leftWingWheelModel);
      this.flip(this.rightWingWheelModel);
      this.flip(this.tailDoorOpenModel);
      this.flip(this.tailDoorCloseModel);
      this.flip(this.rightWingPos1Model);
      this.flip(this.rightWingPos2Model);
      this.flip(this.leftWingPos1Model);
      this.flip(this.leftWingPos2Model);
      this.flip(this.hudModel);
      this.flip(this.leftAnimWingModel);
      this.flip(this.rightAnimWingModel);
      this.flip(this.bodyAnimWheelModel);
      this.flip(this.tailAnimWheelModel);
      this.flip(this.leftAnimWingWheelModel);
      this.flip(this.rightAnimWingWheelModel);
      this.flip(this.doorAnimModel);
      ModelRendererTurbo[][] var1 = this.valkyrie;
      int var2 = var1.length;

      int var3;
      ModelRendererTurbo[] propellerModel;
      for(var3 = 0; var3 < var2; ++var3) {
         propellerModel = var1[var3];
         this.flip(propellerModel);
      }

      var1 = this.propellerModels;
      var2 = var1.length;

      for(var3 = 0; var3 < var2; ++var3) {
         propellerModel = var1[var3];
         this.flip(propellerModel);
      }

      var1 = this.heliMainRotorModels;
      var2 = var1.length;

      for(var3 = 0; var3 < var2; ++var3) {
         propellerModel = var1[var3];
         this.flip(propellerModel);
      }

      var1 = this.heliTailRotorModels;
      var2 = var1.length;

      for(var3 = 0; var3 < var2; ++var3) {
         propellerModel = var1[var3];
         this.flip(propellerModel);
      }

   }

   public void translateAll(float x, float y, float z) {
      super.translateAll(x, y, z);
      this.translate(this.noseModel, x, y, z);
      this.translate(this.leftWingModel, x, y, z);
      this.translate(this.rightWingModel, x, y, z);
      this.translate(this.topWingModel, x, y, z);
      this.translate(this.bayModel, x, y, z);
      this.translate(this.tailModel, x, y, z);
      this.translate(this.yawFlapModel, x, y, z);
      this.translate(this.skidsModel, x, y, z);
      this.translate(this.helicopterModeParts, x, y, z);
      this.translate(this.planeModeParts, x, y, z);
      this.translate(this.pitchFlapLeftModel, x, y, z);
      this.translate(this.pitchFlapRightModel, x, y, z);
      this.translate(this.pitchFlapLeftWingModel, x, y, z);
      this.translate(this.pitchFlapRightWingModel, x, y, z);
      this.translate(this.bodyWheelModel, x, y, z);
      this.translate(this.tailWheelModel, x, y, z);
      this.translate(this.leftWingWheelModel, x, y, z);
      this.translate(this.rightWingWheelModel, x, y, z);
      this.translate(this.tailDoorOpenModel, x, y, z);
      this.translate(this.tailDoorCloseModel, x, y, z);
      this.translate(this.rightWingPos1Model, x, y, z);
      this.translate(this.rightWingPos2Model, x, y, z);
      this.translate(this.leftWingPos1Model, x, y, z);
      this.translate(this.leftWingPos2Model, x, y, z);
      this.translate(this.hudModel, x, y, z);
      this.translate(this.leftAnimWingModel, x, y, z);
      this.translate(this.rightAnimWingModel, x, y, z);
      this.translate(this.bodyAnimWheelModel, x, y, z);
      this.translate(this.tailAnimWheelModel, x, y, z);
      this.translate(this.leftAnimWingWheelModel, x, y, z);
      this.translate(this.rightAnimWingWheelModel, x, y, z);
      this.translate(this.doorAnimModel, x, y, z);
      ModelRendererTurbo[][] var4 = this.valkyrie;
      int var5 = var4.length;

      int var6;
      ModelRendererTurbo[] o;
      for(var6 = 0; var6 < var5; ++var6) {
         o = var4[var6];
         this.translate(o, x, y, z);
      }

      var4 = this.propellerModels;
      var5 = var4.length;

      for(var6 = 0; var6 < var5; ++var6) {
         o = var4[var6];
         this.translate(o, x, y, z);
      }

      var4 = this.heliMainRotorModels;
      var5 = var4.length;

      for(var6 = 0; var6 < var5; ++var6) {
         o = var4[var6];
         this.translate(o, x, y, z);
      }

      var4 = this.heliTailRotorModels;
      var5 = var4.length;

      for(var6 = 0; var6 < var5; ++var6) {
         o = var4[var6];
         this.translate(o, x, y, z);
      }

      Vector3f[] var8 = this.heliMainRotorOrigins;
      var5 = var8.length;

      Vector3f var9;
      for(var6 = 0; var6 < var5; ++var6) {
         var9 = var8[var6];
         Vector3f.add(var9, new Vector3f(x / 16.0F, y / 16.0F, z / 16.0F), var9);
      }

      var8 = this.heliTailRotorOrigins;
      var5 = var8.length;

      for(var6 = 0; var6 < var5; ++var6) {
         var9 = var8[var6];
         Vector3f.add(var9, new Vector3f(x / 16.0F, y / 16.0F, z / 16.0F), var9);
      }

   }
}
