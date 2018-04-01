package com.flansmod.client.tmt;

import com.flansmod.client.tmt.Angle3D;
import com.flansmod.client.tmt.Bone;
import com.flansmod.client.tmt.PositionTransformVertex;
import com.flansmod.client.tmt.TransformGroup;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class TransformGroupBone extends TransformGroup {

   protected Angle3D baseAngles;
   protected Vec3 baseVector;
   protected Bone attachedBone;
   protected double weight;


   public TransformGroupBone(Bone bone, double wght) {
      this.baseVector = bone.getPosition();
      this.baseAngles = bone.getAbsoluteAngle();
      this.attachedBone = bone;
      this.weight = wght;
   }

   public Angle3D getBaseAngles() {
      return this.baseAngles.copy();
   }

   public Angle3D getTransformAngle() {
      Angle3D returnAngle = this.attachedBone.getAbsoluteAngle().copy();
      returnAngle.angleX -= this.baseAngles.angleX;
      returnAngle.angleY -= this.baseAngles.angleY;
      returnAngle.angleZ -= this.baseAngles.angleZ;
      return returnAngle;
   }

   public Vec3 getBaseVector() {
      return Vec3.createVectorHelper(this.baseVector.xCoord, this.baseVector.yCoord, this.baseVector.zCoord);
   }

   public Vec3 getTransformVector() {
      return this.baseVector.subtract(this.attachedBone.getPosition());
   }

   public Vec3 getCurrentVector() {
      return this.attachedBone.getPosition();
   }

   public double getWeight() {
      return this.weight;
   }

   public void attachBone(Bone bone) {
      this.baseVector = bone.getPosition();
      this.baseAngles = bone.getAbsoluteAngle();
      this.attachedBone = bone;
   }

   public Vec3 doTransformation(PositionTransformVertex vertex) {
      Vec3 vector = Vec3.createVectorHelper(vertex.neutralVector.xCoord, vertex.neutralVector.yCoord, vertex.neutralVector.zCoord);
      vector = this.getBaseVector().subtract(vector);
      Angle3D angle = this.getTransformAngle();
      this.setVectorRotations(vector, angle.angleX, angle.angleY, angle.angleZ);
      return vector;
   }

   protected void setVectorRotations(Vec3 vector, float xRot, float yRot, float zRot) {
      float xC = MathHelper.cos(xRot);
      float xS = MathHelper.sin(xRot);
      float yC = MathHelper.cos(yRot);
      float yS = MathHelper.sin(yRot);
      float zC = MathHelper.cos(zRot);
      float zS = MathHelper.sin(zRot);
      double xVec = vector.xCoord;
      double yVec = vector.yCoord;
      double zVec = vector.zCoord;
      double xy = (double)xC * yVec - (double)xS * zVec;
      double xz = (double)xC * zVec + (double)xS * yVec;
      double yz = (double)yC * xz - (double)yS * xVec;
      double yx = (double)yC * xVec + (double)yS * xz;
      double zx = (double)zC * yx - (double)zS * xy;
      double zy = (double)zC * xy + (double)zS * yx;
      vector.xCoord = zx;
      vector.yCoord = zy;
      vector.zCoord = yz;
   }
}
