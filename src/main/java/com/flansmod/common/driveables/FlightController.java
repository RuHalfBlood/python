package com.flansmod.common.driveables;

import com.flansmod.common.driveables.DriveableData;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.EnumPlaneMode;
import com.flansmod.common.driveables.PlaneType;
import com.flansmod.common.driveables.Propeller;
import com.flansmod.common.vector.Vector3f;
import java.util.Iterator;

public class FlightController {

   public float throttle;
   public float yawControl;
   public float pitchControl;
   public float rollControl;
   public EnumPlaneMode mode;
   public float gravity = 1.0F;
   public float drag = 0.0F;
   public float thrust = 0.0F;
   public float lift = 0.0F;
   public Vector3f angularMomentum = new Vector3f(0.0F, 0.0F, 0.0F);


   public void UpdateParams(EntityPlane plane) {
      this.throttle = plane.throttle;
      this.yawControl = plane.flapsYaw;
      this.pitchControl = (plane.flapsPitchLeft + plane.flapsPitchRight) / 2.0F;
      this.rollControl = (plane.flapsPitchRight - plane.flapsPitchLeft) / 2.0F;
      this.mode = plane.mode;
   }

   public void fly(EntityPlane plane) {
       PlaneType type = plane.getPlaneType();
       DriveableData data = plane.getDriveableData();
       this.UpdateParams(plane);
       this.SetAxes(plane);
       this.thrust = 0.01f * (type.maxThrottle + (data.engine == null ? 0.0f : data.engine.engineSpeed));
       this.gravity = 0.098000005f;
       this.drag = 1.0f - 0.05f * type.drag;
       switch (this.mode) {
           case PLANE: {
               this.PlaneModeFly(plane);
           }
           case HELI: {
               this.HeliModeFly(plane);
           }
       }
   }


   public void SetAxes(EntityPlane plane) {
      PlaneType type = plane.getPlaneType();
      float sensitivityAdjust = (float)plane.getSpeedXYZ() / 2.0F > 0.5F?1.5F - (float)plane.getSpeedXYZ() / 2.0F:4.0F * (float)plane.getSpeedXYZ() / 2.0F - 1.0F;
      if(this.mode == EnumPlaneMode.HELI) {
         sensitivityAdjust = this.throttle > 0.5F?1.5F - this.throttle:4.0F * this.throttle - 1.0F;
      }

      if((double)((float)plane.getSpeedXYZ()) < 0.5D && sensitivityAdjust < 0.0F) {
         sensitivityAdjust = 0.0F;
      }

      sensitivityAdjust *= 0.125F;
      float yaw = this.yawControl * (this.yawControl > 0.0F?type.turnLeftModifier:type.turnRightModifier) * sensitivityAdjust;
      float pitch = this.pitchControl * (this.pitchControl > 0.0F?type.lookUpModifier:type.lookDownModifier) * sensitivityAdjust;
      float roll = this.rollControl * (this.rollControl > 0.0F?type.rollLeftModifier:type.rollRightModifier) * sensitivityAdjust;
      if(plane.axes.getPitch() <= 60.0F && plane.getSpeedXYZ() < 0.2D && this.mode == EnumPlaneMode.PLANE && this.throttle >= 0.1F) {
         pitch = -1.0F;
      }

      if(this.mode == EnumPlaneMode.PLANE) {
         if(!plane.isPartIntact(EnumDriveablePart.tail)) {
            yaw = 0.0F;
            pitch = 0.0F;
         }

         if(!plane.isPartIntact(EnumDriveablePart.leftWing)) {
            roll = (float)((double)roll - 2.0D * plane.getSpeedXZ());
         }

         if(!plane.isPartIntact(EnumDriveablePart.rightWing)) {
            roll = (float)((double)roll + 2.0D * plane.getSpeedXZ());
         }
      } else if(this.mode == EnumPlaneMode.HELI && !plane.isPartIntact(EnumDriveablePart.tail)) {
         yaw = 10.0F * this.throttle;
      }

      this.angularMomentum.x = this.moveToTarget(this.angularMomentum.x, yaw, 1.0F);
      this.angularMomentum.y = this.moveToTarget(this.angularMomentum.y, pitch, 1.0F);
      this.angularMomentum.z = this.moveToTarget(this.angularMomentum.z, roll, 1.0F);
      this.LimitAngularMomentum(this.angularMomentum, 20.0F);
      plane.axes.rotateLocalYaw(this.angularMomentum.x);
      plane.axes.rotateLocalPitch(this.angularMomentum.y);
      plane.axes.rotateLocalRoll(-this.angularMomentum.z);
      this.angularMomentum.scale(0.99F);
   }

   public float moveToTarget(float current, float target, float speed) {
      float pitchToMove;
      for(pitchToMove = (float)(Math.sqrt((double)(target * target)) - Math.sqrt((double)(current * current))); pitchToMove > 180.0F; pitchToMove -= 360.0F) {
         ;
      }

      while(pitchToMove <= -180.0F) {
         pitchToMove += 360.0F;
      }

      float signDeltaY = 0.0F;
      if(pitchToMove > speed) {
         signDeltaY = 1.0F;
      } else {
         if(pitchToMove >= -speed) {
            signDeltaY = 0.0F;
            return target;
         }

         signDeltaY = -1.0F;
      }

      if(current > target) {
         current -= speed;
      } else if(current < target) {
         current += speed;
      }

      return current;
   }

   public void LimitAngularMomentum(Vector3f vec, float angle) {
      if(vec.x > angle) {
         vec.x = angle;
      }

      if(vec.x < -angle) {
         vec.x = -angle;
      }

      if(vec.y > angle) {
         vec.y = angle;
      }

      if(vec.y < -angle) {
         vec.y = -angle;
      }

      if(vec.z > angle) {
         vec.z = angle;
      }

      if(vec.z < -angle) {
         vec.z = -angle;
      }

   }

   public void PlaneModeFly(EntityPlane plane) {
      if(plane.mode != EnumPlaneMode.HELI) {
         PlaneType type = plane.getPlaneType();
         DriveableData data = plane.getDriveableData();
         int numPropsWorking = 0;
         boolean numProps = false;
         float fuelConsumptionMultiplier = 2.0F;
         float flap = this.angularMomentum.length();
         this.drag -= flap / 100.0F;
         this.throttle -= -flap / 500.0F;
         Iterator forwards = type.propellers.iterator();

         while(forwards.hasNext()) {
            Propeller lastTickSpeed = (Propeller)forwards.next();
            if(plane.isPartIntact(lastTickSpeed.planePart)) {
               ++numPropsWorking;
            }
         }

         int var14 = type.propellers.size();
         if(var14 != 0) {
            Vector3f var15 = (Vector3f)plane.axes.getXAxis().normalise();
            float var16 = (float)plane.getSpeedXYZ();
            if(var16 > 2.0F) {
               var16 = 2.0F;
            }

            float newSpeed = var16 + this.thrust * 2.0F;
            float proportionOfMotionToCorrect = 2.0F * this.throttle;
            if(proportionOfMotionToCorrect < 0.0F) {
               proportionOfMotionToCorrect = 0.0F;
            }

            if(proportionOfMotionToCorrect > 1.5F) {
               proportionOfMotionToCorrect = 1.5F;
            }

            int numWingsIntact = 0;
            if(plane.isPartIntact(EnumDriveablePart.rightWing)) {
               ++numWingsIntact;
            }

            if(plane.isPartIntact(EnumDriveablePart.leftWing)) {
               ++numWingsIntact;
            }

            this.lift = (float)plane.getSpeedXYZ() * (float)plane.getSpeedXYZ() * (float)numWingsIntact / 2.0F;
            Vector3f up2 = (Vector3f)plane.axes.getYAxis().normalise();
            this.lift = (float)((double)this.lift * Math.sqrt((double)(up2.y * up2.y)));
            if(this.lift > this.gravity) {
               this.lift = this.gravity;
            }

            plane.motionX *= (double)(1.0F - proportionOfMotionToCorrect);
            plane.motionY *= (double)(1.0F - proportionOfMotionToCorrect);
            plane.motionZ *= (double)(1.0F - proportionOfMotionToCorrect);
            plane.motionX += (double)(proportionOfMotionToCorrect * newSpeed * var15.x);
            plane.motionY += (double)(proportionOfMotionToCorrect * newSpeed * var15.y);
            plane.motionZ += (double)(proportionOfMotionToCorrect * newSpeed * var15.z);
            plane.motionY += (double)this.lift;
            plane.motionY -= (double)this.gravity;
            if(plane.axes.getPitch() <= 60.0F && plane.getSpeedXYZ() < 0.2D) {
               plane.motionY -= (double)this.gravity;
            }

            if(!plane.isPartIntact(EnumDriveablePart.rightWing) && !plane.isPartIntact(EnumDriveablePart.rightWing)) {
               plane.motionY += -1.0D;
            }

            plane.motionX *= (double)this.drag;
            if(plane.posY - plane.prevPosY < 0.0D) {
               plane.motionY *= this.drag < 1.0F?0.999D:1.0D;
            } else {
               plane.motionY *= (double)this.drag;
            }

            plane.motionZ *= (double)this.drag;
            plane.lastPos = new Vector3f(plane.motionX, plane.motionY, plane.motionZ);
            data.fuelInTank -= this.thrust * fuelConsumptionMultiplier * data.engine.fuelConsumption;
            if(plane.getSpeedXYZ() > 2.0D) {
               plane.axes.rotateLocalPitch(((float)Math.random() - 0.5F) / 4.0F);
               plane.axes.rotateLocalYaw(((float)Math.random() - 0.5F) / 4.0F);
               plane.axes.rotateLocalRoll(((float)Math.random() - 0.5F) / 4.0F);
            }

         }
      }
   }

   public void HeliModeFly(EntityPlane plane) {
      PlaneType type = plane.getPlaneType();
      DriveableData data = plane.getDriveableData();
      int numPropsWorking = 0;
      boolean numProps = false;
      float fuelConsumptionMultiplier = 2.0F;
      Iterator up = type.heliPropellers.iterator();

      while(up.hasNext()) {
         Propeller upwardsForce = (Propeller)up.next();
         if(plane.isPartIntact(upwardsForce.planePart)) {
            ++numPropsWorking;
         }
      }

      int var9 = type.heliPropellers.size();
      this.gravity = 0.05F;
      if(var9 != 0) {
         Vector3f var10 = (Vector3f)plane.axes.getYAxis().normalise();
         this.thrust *= (float)(numPropsWorking / var9) * 2.0F;
         float var11 = this.throttle * this.thrust + (this.gravity - this.thrust / 2.0F);
         if(this.throttle < 0.5F) {
            var11 = this.gravity * this.throttle * 2.0F;
         }

         if(!plane.isPartIntact(EnumDriveablePart.blades)) {
            var11 = 0.0F;
         }

         if(this.throttle < 0.52F && this.throttle > 0.48F && var10.y >= 0.7F) {
            var11 = this.gravity / var10.y;
         }

         if(plane.getPlaneType().mode != EnumPlaneMode.VTOL && var10.y < 0.0F) {
            var10.y *= -1.0F;
            var10.x *= -1.0F;
            var10.z *= -1.0F;
         }

         plane.motionX += (double)(var11 * var10.x * 0.5F);
         plane.motionY += (double)(var11 * var10.y - this.gravity);
         plane.motionZ += (double)(var11 * var10.z * 0.5F);
         plane.motionX *= (double)(1.0F - (1.0F - this.drag) / 5.0F);
         plane.motionY *= (double)this.drag;
         plane.motionZ *= (double)(1.0F - (1.0F - this.drag) / 5.0F);
         plane.lastPos = new Vector3f(plane.motionX, plane.motionY, plane.motionZ);
         data.fuelInTank -= var11 * fuelConsumptionMultiplier * data.engine.fuelConsumption;
      }
   }
}
