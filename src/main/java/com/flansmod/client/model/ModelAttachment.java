package com.flansmod.client.model;

import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;

public class ModelAttachment extends ModelBase {

   public ModelRendererTurbo[] attachmentModel = new ModelRendererTurbo[0];
   public float renderOffset = 0.0F;


   public void renderAttachment(float f) {
      ModelRendererTurbo[] var2 = this.attachmentModel;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ModelRendererTurbo model = var2[var4];
         if(model != null) {
            model.render(f);
         }
      }

   }

   public void flipAll() {
      ModelRendererTurbo[] var1 = this.attachmentModel;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         ModelRendererTurbo anAttachmentModel = var1[var3];
         anAttachmentModel.doMirror(false, true, true);
         anAttachmentModel.setRotationPoint(anAttachmentModel.rotationPointX, -anAttachmentModel.rotationPointY, -anAttachmentModel.rotationPointZ);
      }

   }
}
