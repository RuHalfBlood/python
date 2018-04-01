package com.flansmod.client.tmt;

import com.flansmod.client.tmt.PositionTextureVertex;
import com.flansmod.client.tmt.PositionTransformVertex;
import com.flansmod.client.tmt.TmtTessellator;
import java.util.ArrayList;
import net.minecraft.util.Vec3;

public class TexturedPolygon {

   public PositionTextureVertex[] vertexPositions;
   public int nVertices;
   private boolean invertNormal;
   private float[] normals;
   private ArrayList iNormals;


   public TexturedPolygon(PositionTextureVertex[] apositionTexturevertex) {
      this.invertNormal = false;
      this.vertexPositions = apositionTexturevertex;
      this.nVertices = apositionTexturevertex.length;
      this.iNormals = new ArrayList();
      this.normals = new float[0];
   }

   public TexturedPolygon(PositionTextureVertex[] apositionTexturevertex, int par2, int par3, int par4, int par5, float par6, float par7) {
      this(apositionTexturevertex);
      float var8 = 0.0F / par6;
      float var9 = 0.0F / par7;
      apositionTexturevertex[0] = apositionTexturevertex[0].setTexturePosition((float)par4 / par6 - var8, (float)par3 / par7 + var9);
      apositionTexturevertex[1] = apositionTexturevertex[1].setTexturePosition((float)par2 / par6 + var8, (float)par3 / par7 + var9);
      apositionTexturevertex[2] = apositionTexturevertex[2].setTexturePosition((float)par2 / par6 + var8, (float)par5 / par7 - var9);
      apositionTexturevertex[3] = apositionTexturevertex[3].setTexturePosition((float)par4 / par6 - var8, (float)par5 / par7 - var9);
   }

   public void setInvertNormal(boolean isSet) {
      this.invertNormal = isSet;
   }

   public void setNormals(float x, float y, float z) {
      this.normals = new float[]{x, y, z};
   }

   public void flipFace() {
      PositionTextureVertex[] var1 = new PositionTextureVertex[this.vertexPositions.length];

      for(int var2 = 0; var2 < this.vertexPositions.length; ++var2) {
         var1[var2] = this.vertexPositions[this.vertexPositions.length - var2 - 1];
      }

      this.vertexPositions = var1;
   }

   public void setNormals(ArrayList vec) {
      this.iNormals = vec;
   }

   public void draw(TmtTessellator tessellator, float f) {
      if(this.nVertices == 3) {
         tessellator.startDrawing(4);
      } else if(this.nVertices == 4) {
         tessellator.startDrawingQuads();
      } else {
         tessellator.startDrawing(9);
      }

      if(this.iNormals.size() == 0) {
         if(this.normals.length == 3) {
            if(this.invertNormal) {
               tessellator.setNormal(-this.normals[0], -this.normals[1], -this.normals[2]);
            } else {
               tessellator.setNormal(this.normals[0], this.normals[1], this.normals[2]);
            }
         } else {
            if(this.vertexPositions.length < 3) {
               return;
            }

            Vec3 i = this.vertexPositions[1].vector3D.subtract(this.vertexPositions[0].vector3D);
            Vec3 positionTexturevertex = this.vertexPositions[1].vector3D.subtract(this.vertexPositions[2].vector3D);
            Vec3 Vec32 = positionTexturevertex.crossProduct(i).normalize();
            if(this.invertNormal) {
               tessellator.setNormal(-((float)Vec32.xCoord), -((float)Vec32.yCoord), -((float)Vec32.zCoord));
            } else {
               tessellator.setNormal((float)Vec32.xCoord, (float)Vec32.yCoord, (float)Vec32.zCoord);
            }
         }
      }

      for(int var6 = 0; var6 < this.nVertices; ++var6) {
         PositionTextureVertex var7 = this.vertexPositions[var6];
         if(var7 instanceof PositionTransformVertex) {
            ((PositionTransformVertex)var7).setTransformation();
         }

         if(var6 < this.iNormals.size()) {
            if(this.invertNormal) {
               tessellator.setNormal(-((float)((Vec3)this.iNormals.get(var6)).xCoord), -((float)((Vec3)this.iNormals.get(var6)).yCoord), -((float)((Vec3)this.iNormals.get(var6)).zCoord));
            } else {
               tessellator.setNormal((float)((Vec3)this.iNormals.get(var6)).xCoord, (float)((Vec3)this.iNormals.get(var6)).yCoord, (float)((Vec3)this.iNormals.get(var6)).zCoord);
            }
         }

         tessellator.addVertexWithUVW((double)((float)var7.vector3D.xCoord * f), (double)((float)var7.vector3D.yCoord * f), (double)((float)var7.vector3D.zCoord * f), (double)var7.texturePositionX, (double)var7.texturePositionY, (double)var7.texturePositionW);
      }

      tessellator.draw();
   }
}
