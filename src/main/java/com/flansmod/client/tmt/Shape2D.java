package com.flansmod.client.tmt;

import com.flansmod.client.tmt.Coord2D;
import com.flansmod.client.tmt.PositionTransformVertex;
import com.flansmod.client.tmt.Shape3D;
import com.flansmod.client.tmt.TexturedPolygon;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class Shape2D {

   public ArrayList coords;


   public Shape2D() {
      this.coords = new ArrayList();
   }

   public Shape2D(Coord2D[] coordArray) {
      this.coords = new ArrayList();
      Collections.addAll(this.coords, coordArray);
   }

   public Shape2D(ArrayList coordList) {
      this.coords = coordList;
   }

   public Coord2D[] getCoordArray() {
      return (Coord2D[])((Coord2D[])this.coords.toArray());
   }

   public Shape3D extrude(float x, float y, float z, float rotX, float rotY, float rotZ, float depth, int u, int v, float textureWidth, float textureHeight, int shapeTextureWidth, int shapeTextureHeight, int sideTextureWidth, int sideTextureHeight, float[] faceLengths) {
      PositionTransformVertex[] verts = new PositionTransformVertex[this.coords.size() * 2];
      PositionTransformVertex[] vertsTop = new PositionTransformVertex[this.coords.size()];
      PositionTransformVertex[] vertsBottom = new PositionTransformVertex[this.coords.size()];
      TexturedPolygon[] poly = new TexturedPolygon[this.coords.size() + 2];
      Vec3 extrudeVector = Vec3.createVectorHelper(0.0D, 0.0D, (double)depth);
      this.setVectorRotations(extrudeVector, rotX, rotY, rotZ);
      if(faceLengths != null && faceLengths.length < this.coords.size()) {
         faceLengths = null;
      }

      float totalLength = 0.0F;

      Coord2D curCoord;
      float currentLength;
      float ratioPosition;
      for(int currentLengthPosition = 0; currentLengthPosition < this.coords.size(); ++currentLengthPosition) {
         Coord2D idx = (Coord2D)this.coords.get(currentLengthPosition);
         curCoord = (Coord2D)this.coords.get((currentLengthPosition + 1) % this.coords.size());
         float nextCoord = (float)(idx.uCoord + u) / textureWidth;
         currentLength = (float)(shapeTextureWidth * 2 - idx.uCoord + u) / textureWidth;
         ratioPosition = (float)(idx.vCoord + v) / textureHeight;
         Vec3 ratioLength = Vec3.createVectorHelper(idx.xCoord, idx.yCoord, 0.0D);
         this.setVectorRotations(ratioLength, rotX, rotY, rotZ);
         verts[currentLengthPosition] = new PositionTransformVertex(x + (float)ratioLength.xCoord, y + (float)ratioLength.yCoord, z + (float)ratioLength.zCoord, nextCoord, ratioPosition);
         verts[currentLengthPosition + this.coords.size()] = new PositionTransformVertex(x + (float)ratioLength.xCoord - (float)extrudeVector.xCoord, y + (float)ratioLength.yCoord - (float)extrudeVector.yCoord, z + (float)ratioLength.zCoord - (float)extrudeVector.zCoord, currentLength, ratioPosition);
         vertsTop[currentLengthPosition] = new PositionTransformVertex(verts[currentLengthPosition]);
         vertsBottom[this.coords.size() - currentLengthPosition - 1] = new PositionTransformVertex(verts[currentLengthPosition + this.coords.size()]);
         if(faceLengths != null) {
            totalLength += faceLengths[currentLengthPosition];
         } else {
            totalLength = (float)((double)totalLength + Math.sqrt(Math.pow(idx.xCoord - curCoord.xCoord, 2.0D) + Math.pow(idx.yCoord - curCoord.yCoord, 2.0D)));
         }
      }

      poly[this.coords.size()] = new TexturedPolygon(vertsTop);
      poly[this.coords.size() + 1] = new TexturedPolygon(vertsBottom);
      float var35 = totalLength;

      for(int var36 = 0; var36 < this.coords.size(); ++var36) {
         curCoord = (Coord2D)this.coords.get(var36);
         Coord2D var37 = (Coord2D)this.coords.get((var36 + 1) % this.coords.size());
         currentLength = (float)Math.sqrt(Math.pow(curCoord.xCoord - var37.xCoord, 2.0D) + Math.pow(curCoord.yCoord - var37.yCoord, 2.0D));
         if(faceLengths != null) {
            currentLength = faceLengths[faceLengths.length - var36 - 1];
         }

         ratioPosition = var35 / totalLength;
         float var38 = (var35 - currentLength) / totalLength;
         float texU1 = (var38 * (float)sideTextureWidth + (float)u) / textureWidth;
         float texU2 = (ratioPosition * (float)sideTextureWidth + (float)u) / textureWidth;
         float texV1 = ((float)v + (float)shapeTextureHeight) / textureHeight;
         float texV2 = ((float)v + (float)shapeTextureHeight + (float)sideTextureHeight) / textureHeight;
         PositionTransformVertex[] polySide = new PositionTransformVertex[]{new PositionTransformVertex(verts[var36], texU2, texV1), new PositionTransformVertex(verts[this.coords.size() + var36], texU2, texV2), new PositionTransformVertex(verts[this.coords.size() + (var36 + 1) % this.coords.size()], texU1, texV2), new PositionTransformVertex(verts[(var36 + 1) % this.coords.size()], texU1, texV1)};
         poly[var36] = new TexturedPolygon(polySide);
         var35 -= currentLength;
      }

      return new Shape3D(verts, poly);
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
