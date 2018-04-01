package com.flansmod.client.tmt;

import com.flansmod.client.tmt.PositionTextureVertex;
import com.flansmod.client.tmt.TransformGroup;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.util.Vec3;

public class PositionTransformVertex extends PositionTextureVertex {

   public Vec3 neutralVector;
   public ArrayList transformGroups;


   public PositionTransformVertex(float x, float y, float z, float u, float v) {
      this(Vec3.createVectorHelper((double)x, (double)y, (double)z), u, v);
   }

   public PositionTransformVertex(PositionTextureVertex vertex, float u, float v) {
      super(vertex, u, v);
      this.transformGroups = new ArrayList();
      if(vertex instanceof PositionTransformVertex) {
         this.neutralVector = ((PositionTransformVertex)vertex).neutralVector;
      } else {
         this.neutralVector = Vec3.createVectorHelper(vertex.vector3D.xCoord, vertex.vector3D.yCoord, vertex.vector3D.zCoord);
      }

   }

   public PositionTransformVertex(PositionTextureVertex vertex) {
      this(vertex, vertex.texturePositionX, vertex.texturePositionY);
   }

   public PositionTransformVertex(Vec3 vector, float u, float v) {
      super(vector, u, v);
      this.transformGroups = new ArrayList();
      this.neutralVector = Vec3.createVectorHelper(vector.xCoord, vector.yCoord, vector.zCoord);
   }

   public void setTransformation() {
      if(this.transformGroups.size() == 0) {
         super.vector3D.xCoord = this.neutralVector.xCoord;
         super.vector3D.yCoord = this.neutralVector.yCoord;
         super.vector3D.zCoord = this.neutralVector.zCoord;
      } else {
         double weight = 0.0D;

         Iterator var3;
         TransformGroup group;
         for(var3 = this.transformGroups.iterator(); var3.hasNext(); weight += group.getWeight()) {
            group = (TransformGroup)var3.next();
         }

         super.vector3D.xCoord = 0.0D;
         super.vector3D.yCoord = 0.0D;
         super.vector3D.zCoord = 0.0D;

         double cWeight;
         Vec3 vector;
         for(var3 = this.transformGroups.iterator(); var3.hasNext(); super.vector3D.zCoord += cWeight * vector.zCoord) {
            group = (TransformGroup)var3.next();
            cWeight = group.getWeight() / weight;
            vector = group.doTransformation(this);
            super.vector3D.xCoord += cWeight * vector.xCoord;
            super.vector3D.yCoord += cWeight * vector.yCoord;
         }

      }
   }

   public void addGroup(TransformGroup group) {
      this.transformGroups.add(group);
   }

   public void removeGroup(TransformGroup group) {
      this.transformGroups.remove(group);
   }
}
