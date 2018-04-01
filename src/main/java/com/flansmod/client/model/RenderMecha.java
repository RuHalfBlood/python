package com.flansmod.client.model;

import com.flansmod.client.ClientProxy;
import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.model.ModelDriveable;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.model.ModelMecha;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.ShootPoint;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.driveables.mechas.EnumMechaSlotType;
import com.flansmod.common.driveables.mechas.ItemMecha;
import com.flansmod.common.driveables.mechas.ItemMechaAddon;
import com.flansmod.common.driveables.mechas.MechaItemType;
import com.flansmod.common.driveables.mechas.MechaType;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.paintjob.Paintjob;
import com.flansmod.common.vector.Vector3f;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderMecha extends Render implements IItemRenderer {

   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
   private static final ItemRenderer renderer = new ItemRenderer(Minecraft.getMinecraft());


   public RenderMecha() {
      super.shadowSize = 1.5F;
   }

   public void render(EntityMecha mecha, double d, double d1, double d2, float f, float f1) {
      this.bindEntityTexture(mecha);
      float scale = 0.0625F;
      MechaType type = mecha.getMechaType();
      GL11.glPushMatrix();
      GL11.glTranslatef((float)d, (float)d1, (float)d2);

      float dYaw;
      for(dYaw = mecha.axes.getYaw() - mecha.prevRotationYaw; dYaw > 180.0F; dYaw -= 360.0F) {
         ;
      }

      while(dYaw <= -180.0F) {
         dYaw += 360.0F;
      }

      float dPitch;
      for(dPitch = mecha.axes.getPitch() - mecha.prevRotationPitch; dPitch > 180.0F; dPitch -= 360.0F) {
         ;
      }

      while(dPitch <= -180.0F) {
         dPitch += 360.0F;
      }

      float dRoll;
      for(dRoll = mecha.axes.getRoll() - mecha.prevRotationRoll; dRoll > 180.0F; dRoll -= 360.0F) {
         ;
      }

      while(dRoll <= -180.0F) {
         dRoll += 360.0F;
      }

      GL11.glRotatef(-mecha.prevRotationYaw - dYaw * f1, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(mecha.prevRotationPitch + dPitch * f1, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(mecha.prevRotationRoll + dRoll * f1, 1.0F, 0.0F, 0.0F);
      float modelScale = mecha.getMechaType().modelScale;
      ModelMecha model = (ModelMecha)type.model;
      GL11.glPushMatrix();
      GL11.glScalef(modelScale, modelScale, modelScale);
      if(model != null) {
         model.render(mecha, f1);
      }

      ItemStack legLength = mecha.inventory.getStackInSlot(EnumMechaSlotType.hips);
      if(legLength != null && legLength.getItem() instanceof ItemMechaAddon) {
         MechaItemType dLLUR = ((ItemMechaAddon)legLength.getItem()).type;
         if(dLLUR.model != null) {
            GL11.glTranslatef(model.hipsAttachmentPoint.x, model.hipsAttachmentPoint.y, model.hipsAttachmentPoint.z);
            GL11.glScalef(type.heldItemScale, type.heldItemScale, type.heldItemScale);
            if(dLLUR.texture != null) {
               this.bindTexture(FlansModResourceHandler.getTexture(dLLUR));
            }

            dLLUR.model.render(mecha, f1);
         }
      }

      GL11.glPopMatrix();
      float legLength1;
      ItemStack dLLUR1;
      if(mecha.isPartIntact(EnumDriveablePart.leftArm)) {
         this.bindEntityTexture(mecha);
         GL11.glPushMatrix();
         legLength1 = 0.0F;
         if(mecha.seats[0] != null) {
            legLength1 = mecha.seats[0].prevLooking.getPitch() + (mecha.seats[0].looking.getPitch() - mecha.seats[0].prevLooking.getPitch()) * f1;
         }

         if(legLength1 > type.lowerArmLimit) {
            legLength1 = type.lowerArmLimit;
         }

         if(legLength1 < -type.upperArmLimit) {
            legLength1 = -type.upperArmLimit;
         }

         GL11.glTranslatef(type.leftArmOrigin.x, mecha.getMechaType().leftArmOrigin.y, mecha.getMechaType().leftArmOrigin.z);
         GL11.glRotatef(90.0F - legLength1, 0.0F, 0.0F, 1.0F);
         GL11.glPushMatrix();
         GL11.glScalef(modelScale, modelScale, modelScale);
         model.renderLeftArm(scale, mecha, f1);
         GL11.glPopMatrix();
         GL11.glTranslatef(0.0F + type.leftHandModifierY, -type.armLength - type.leftHandModifierX, 0.0F + type.leftHandModifierZ);
         dLLUR1 = mecha.inventory.getStackInSlot(EnumMechaSlotType.leftTool);
         GL11.glScalef(modelScale, modelScale, modelScale);
         if(dLLUR1 == null) {
            model.renderLeftHand(scale, mecha, f1);
         } else {
            GL11.glScalef(type.heldItemScale, type.heldItemScale, type.heldItemScale);
            this.renderItem(mecha, dLLUR1, 0, true, f1);
         }

         GL11.glPopMatrix();
      }

      if(mecha.isPartIntact(EnumDriveablePart.rightArm)) {
         this.bindEntityTexture(mecha);
         GL11.glPushMatrix();
         legLength1 = 0.0F;
         if(mecha.seats[0] != null) {
            legLength1 = mecha.seats[0].prevLooking.getPitch() + (mecha.seats[0].looking.getPitch() - mecha.seats[0].prevLooking.getPitch()) * f1;
         }

         if(legLength1 > type.lowerArmLimit) {
            legLength1 = type.lowerArmLimit;
         }

         if(legLength1 < -type.upperArmLimit) {
            legLength1 = -type.upperArmLimit;
         }

         GL11.glTranslatef(type.rightArmOrigin.x, mecha.getMechaType().rightArmOrigin.y, mecha.getMechaType().rightArmOrigin.z);
         GL11.glRotatef(90.0F - legLength1, 0.0F, 0.0F, 1.0F);
         GL11.glPushMatrix();
         GL11.glScalef(modelScale, modelScale, modelScale);
         model.renderRightArm(scale, mecha, f1);
         GL11.glPopMatrix();
         GL11.glTranslatef(0.0F + type.rightHandModifierY, -type.armLength - type.rightHandModifierX, 0.0F + type.rightHandModifierZ);
         GL11.glScalef(modelScale, modelScale, modelScale);
         dLLUR1 = mecha.inventory.getStackInSlot(EnumMechaSlotType.rightTool);
         if(dLLUR1 == null) {
            model.renderRightHand(scale, mecha, f1);
         } else {
            GL11.glScalef(type.heldItemScale, type.heldItemScale, type.heldItemScale);
            this.renderItem(mecha, dLLUR1, 0, false, f1);
         }

         GL11.glPopMatrix();
      }

      if(FlansMod.DEBUG) {
         GL11.glDisable(3553);
         GL11.glEnable(3042);
         GL11.glDisable(2929);
         GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.3F);
         Iterator legLength2 = mecha.getDriveableData().parts.values().iterator();

         while(legLength2.hasNext()) {
            DriveablePart dLLUR2 = (DriveablePart)legLength2.next();
            if(dLLUR2.box != null) {
               renderAABB(AxisAlignedBB.getBoundingBox((double)(dLLUR2.box.x / 16.0F), (double)(dLLUR2.box.y / 16.0F), (double)(dLLUR2.box.z / 16.0F), (double)((dLLUR2.box.x + dLLUR2.box.w) / 16.0F), (double)((dLLUR2.box.y + dLLUR2.box.h) / 16.0F), (double)((dLLUR2.box.z + dLLUR2.box.d) / 16.0F)));
            }
         }

         GL11.glColor4f(0.0F, 0.0F, 1.0F, 0.3F);
         legLength2 = type.shootPointsPrimary.iterator();

         ShootPoint dLLUR3;
         while(legLength2.hasNext()) {
            dLLUR3 = (ShootPoint)legLength2.next();
            renderAABB(AxisAlignedBB.getBoundingBox((double)(dLLUR3.rootPos.position.x - 0.25F), (double)(dLLUR3.rootPos.position.y - 0.25F), (double)(dLLUR3.rootPos.position.z - 0.25F), (double)(dLLUR3.rootPos.position.x + 0.25F), (double)(dLLUR3.rootPos.position.y + 0.25F), (double)(dLLUR3.rootPos.position.z + 0.25F)));
         }

         GL11.glColor4f(0.0F, 1.0F, 0.0F, 0.3F);
         legLength2 = type.shootPointsSecondary.iterator();

         while(legLength2.hasNext()) {
            dLLUR3 = (ShootPoint)legLength2.next();
            renderAABB(AxisAlignedBB.getBoundingBox((double)(dLLUR3.rootPos.position.x - 0.25F), (double)(dLLUR3.rootPos.position.y - 0.25F), (double)(dLLUR3.rootPos.position.z - 0.25F), (double)(dLLUR3.rootPos.position.x + 0.25F), (double)(dLLUR3.rootPos.position.y + 0.25F), (double)(dLLUR3.rootPos.position.z + 0.25F)));
         }

         GL11.glEnable(3553);
         GL11.glEnable(2929);
         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      }

      GL11.glPopMatrix();
      if(mecha.isPartIntact(EnumDriveablePart.hips)) {
         this.bindEntityTexture(mecha);
         GL11.glPushMatrix();
         GL11.glTranslatef((float)d, (float)d1, (float)d2);

         for(dYaw = mecha.legAxes.getYaw() - mecha.prevLegsYaw; dYaw > 180.0F; dYaw -= 360.0F) {
            ;
         }

         while(dYaw <= -180.0F) {
            dYaw += 360.0F;
         }

         GL11.glRotatef(-dYaw * f1 - mecha.prevLegsYaw, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(mecha.prevRotationPitch + dPitch * f1, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(mecha.prevRotationRoll + dRoll * f1, 1.0F, 0.0F, 0.0F);
         GL11.glScalef(modelScale, modelScale, modelScale);
         if(model != null) {
            legLength1 = type.legLength;
            float dLLUR4 = mecha.leftLegUpperAngle - mecha.prevLeftLegUpperAngle;
            float dLLLR = mecha.leftLegLowerAngle - mecha.prevLeftLegLowerAngle;
            float dLFR = mecha.leftFootAngle - mecha.prevLeftFootAngle;
            float dRLUR = mecha.rightLegUpperAngle - mecha.prevRightLegUpperAngle;
            float dRLLR = mecha.rightLegLowerAngle - mecha.prevRightLegLowerAngle;
            float dRFR = mecha.rightFootAngle - mecha.prevRightFootAngle;
            float leftLegUpperRot = (float)Math.toRadians((double)(mecha.prevLeftLegUpperAngle + dLLUR4 * f1));
            float rightLegUpperRot = (float)Math.toRadians((double)(mecha.prevRightLegUpperAngle + dRLUR * f1));
            float leftLegLowerRot = (float)Math.toRadians((double)(mecha.prevLeftLegLowerAngle + dLLLR * f1));
            float rightLegLowerRot = (float)Math.toRadians((double)(mecha.prevRightLegLowerAngle + dRLLR * f1));
            float leftFootRot = (float)Math.toRadians((double)(mecha.prevLeftFootAngle + dLFR * f1));
            float rightFootRot = (float)Math.toRadians((double)(mecha.rightFootAngle + dRFR * f1));
            float legsYaw = (float)Math.sin((double)(((float)mecha.ticksExisted + f1) / type.legSwingTime)) * mecha.legSwing;
            float footH = (float)Math.sin((double)legsYaw) * legLength1;
            float footV = (float)Math.cos((double)legsYaw) * legLength1;
            model.renderHips(scale, mecha, f1);
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, legLength1, 0.0F);
            GL11.glPushMatrix();
            GL11.glTranslatef(footH, -footV, 0.0F);
            model.renderLeftFoot(scale, mecha, f1);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef(-footH, -footV, 0.0F);
            model.renderRightFoot(scale, mecha, f1);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glRotatef(legsYaw * 180.0F / 3.1415927F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -legLength1, 0.0F);
            model.renderLeftLeg(scale, mecha, f1);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glRotatef(-legsYaw * 180.0F / 3.1415927F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -legLength1, 0.0F);
            model.renderRightLeg(scale, mecha, f1);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glRotatef(leftLegUpperRot * 180.0F / 3.1415927F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -legLength1, 0.0F);
            model.renderLeftAnimLegUpper(scale, mecha, f1);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glRotatef(rightLegUpperRot * 180.0F / 3.1415927F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -legLength1, 0.0F);
            model.renderRightAnimLegUpper(scale, mecha, f1);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glPushMatrix();
            Vector3f leftLegLowerPos = this.rotatedChildPosition(model.leftLegUpperOrigin, model.leftLegLowerOrigin, leftLegUpperRot);
            GL11.glTranslatef(model.leftLegUpperOrigin.x, model.leftLegUpperOrigin.y, model.leftLegUpperOrigin.z);
            GL11.glTranslatef(leftLegLowerPos.x, -leftLegLowerPos.y, 0.0F);
            GL11.glRotatef(leftLegLowerRot * 180.0F / 3.1415927F, 0.0F, 0.0F, 1.0F);
            model.renderLeftAnimLegLower(scale, mecha, f1);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            Vector3f rightLegLowerPos = this.rotatedChildPosition(model.rightLegUpperOrigin, model.rightLegLowerOrigin, rightLegUpperRot);
            GL11.glTranslatef(model.rightLegUpperOrigin.x, model.rightLegUpperOrigin.y, model.rightLegUpperOrigin.z);
            GL11.glTranslatef(rightLegLowerPos.x, -rightLegLowerPos.y, 0.0F);
            GL11.glRotatef(rightLegLowerRot * 180.0F / 3.1415927F, 0.0F, 0.0F, 1.0F);
            model.renderRightAnimLegLower(scale, mecha, f1);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            Vector3f leftFootPos = this.rotatedChildPosition(model.leftLegLowerOrigin, model.leftFootOrigin, leftLegLowerRot);
            GL11.glTranslatef(-model.leftFootOrigin.x, legLength1, -model.leftFootOrigin.z);
            GL11.glTranslatef(leftFootPos.x + leftLegLowerPos.x, -leftFootPos.y - leftLegLowerPos.y, 0.0F);
            GL11.glRotatef(leftFootRot * 180.0F / 3.1415927F, 0.0F, 0.0F, 1.0F);
            model.renderLeftAnimFoot(scale, mecha, f1);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            Vector3f rightFootPos = this.rotatedChildPosition(model.rightLegLowerOrigin, model.rightFootOrigin, rightLegLowerRot);
            GL11.glTranslatef(-model.rightFootOrigin.x, legLength1, -model.rightFootOrigin.z);
            GL11.glTranslatef(rightFootPos.x + rightLegLowerPos.x, -rightFootPos.y - rightLegLowerPos.y, 0.0F);
            GL11.glRotatef(rightFootRot * 180.0F / 3.1415927F, 0.0F, 0.0F, 1.0F);
            model.renderRightAnimFoot(scale, mecha, f1);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
         }

         GL11.glPopMatrix();
      }

   }

   public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
      this.render((EntityMecha)entity, d0, d1, d2, f, f1);
   }

   public Vector3f rotatedChildPosition(Vector3f parentJoint, Vector3f childJoint, float rotation) {
      float yDiff = parentJoint.y - childJoint.y;
      float xDiff = parentJoint.x - childJoint.x;
      float length = (float)Math.sqrt((double)(yDiff * yDiff + xDiff * xDiff));
      float initialRot = (float)Math.atan((double)(xDiff / yDiff));
      float xPos = (float)Math.sin((double)(rotation - initialRot)) * length;
      float yPos = (float)Math.cos((double)(rotation - initialRot)) * length;
      Vector3f position = new Vector3f(xPos, yPos, 0.0F);
      return position;
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      DriveableType type = ((EntityDriveable)entity).getDriveableType();
      Paintjob paintjob = type.getPaintjob(((EntityDriveable)entity).getDriveableData().paintjobID);
      return FlansModResourceHandler.getPaintjobTexture(paintjob);
   }

   private void renderItem(EntityMecha mecha, ItemStack stack, int par3, boolean leftHand, float dT) {
      GL11.glPushMatrix();
      TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
      Item item = stack.getItem();
      if(item instanceof ItemMechaAddon) {
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         ItemMechaAddon icon = (ItemMechaAddon)item;
         MechaItemType tessellator = icon.type;
         this.bindTexture(FlansModResourceHandler.getTexture(tessellator));
         if(tessellator.model != null) {
            tessellator.model.render(mecha, dT);
            GL11.glPushMatrix();
            if(leftHand && mecha.leftMouseHeld || !leftHand && mecha.rightMouseHeld) {
               GL11.glRotatef(25.0F * (float)mecha.ticksExisted, 1.0F, 0.0F, 0.0F);
            }

            tessellator.model.renderDrill(mecha, dT);
            GL11.glPopMatrix();
            tessellator.model.renderSaw(mecha, dT, leftHand && mecha.leftMouseHeld || !leftHand && mecha.rightMouseHeld);
         }
      } else if(item instanceof ItemGun && ((ItemGun)item).type.model != null) {
         GunType icon2 = ((ItemGun)item).type;
         ModelGun tessellator2 = icon2.model;
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
         texturemanager.bindTexture(FlansModResourceHandler.getTexture(icon2));
         ItemRenderType f1 = ItemRenderType.ENTITY;
         ClientProxy.gunRenderer.renderGun(stack, icon2, 0.0625F, tessellator2, leftHand?mecha.leftAnimations:mecha.rightAnimations, 0.0F, f1);
      } else {
         IIcon icon1 = stack.getIconIndex();
         if(icon1 == null) {
            GL11.glPopMatrix();
            return;
         }

         texturemanager.bindTexture(texturemanager.getResourceLocation(stack.getItemSpriteNumber()));
         Tessellator tessellator1 = Tessellator.instance;
         float f = icon1.getMinU();
         float f1 = icon1.getMaxU();
         float f2 = icon1.getMinV();
         float f3 = icon1.getMaxV();
         float f4 = 0.0F;
         float f5 = 0.3F;
         GL11.glEnable('\u803a');
         GL11.glTranslatef(-f4, -f5, 0.0F);
         float f6 = 1.5F;
         GL11.glScalef(f6, f6, f6);
         GL11.glTranslatef(0.2F, 0.7F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-133.0F, 0.0F, 0.0F, 1.0F);
         ItemRenderer var10000 = renderer;
         ItemRenderer.renderItemIn2D(tessellator1, f1, f2, f, f3, icon1.getIconWidth(), icon1.getIconHeight(), 0.0625F);
         if(stack.hasEffect(par3)) {
            GL11.glDepthFunc(514);
            GL11.glDisable(2896);
            texturemanager.bindTexture(RES_ITEM_GLINT);
            GL11.glEnable(3042);
            GL11.glBlendFunc(768, 1);
            float f7 = 0.76F;
            GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
            GL11.glMatrixMode(5890);
            GL11.glPushMatrix();
            float f8 = 0.125F;
            GL11.glScalef(f8, f8, f8);
            float f9 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
            GL11.glTranslatef(f9, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
            var10000 = renderer;
            ItemRenderer.renderItemIn2D(tessellator1, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(f8, f8, f8);
            f9 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
            GL11.glTranslatef(-f9, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            var10000 = renderer;
            ItemRenderer.renderItemIn2D(tessellator1, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
            GL11.glPopMatrix();
            GL11.glMatrixMode(5888);
            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glDepthFunc(515);
         }

         GL11.glDisable('\u803a');
      }

      GL11.glPopMatrix();
   }

   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
       switch (type) {
           case EQUIPPED: 
           case EQUIPPED_FIRST_PERSON: 
           case ENTITY: {
               return Minecraft.getMinecraft().gameSettings.fancyGraphics && item != null && item.getItem() instanceof ItemMecha && ((ItemMecha)item.getItem()).type.model != null;
           }
       }
       return false;
   }

   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
       return false;
   }

   public /* varargs */ void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
       GL11.glPushMatrix();
       if (item != null && item.getItem() instanceof ItemMecha) {
           MechaType mechaType = ((ItemMecha)item.getItem()).type;
           if (mechaType.model != null) {
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
                       GL11.glTranslatef((float)0.0f, (float)0.1f, (float)-0.4f);
                       scale = 1.0f;
                       break;
                   }
                   case EQUIPPED_FIRST_PERSON: {
                       GL11.glRotatef((float)25.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                       GL11.glRotatef((float)-5.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                       GL11.glTranslatef((float)0.15f, (float)0.35f, (float)-0.6f);
                       GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                       break;
                   }
               }
               GL11.glScalef((float)(scale / mechaType.cameraDistance), (float)(scale / mechaType.cameraDistance), (float)(scale / mechaType.cameraDistance));
               Minecraft.getMinecraft().renderEngine.bindTexture(FlansModResourceHandler.getTexture(mechaType));
               ModelDriveable model = mechaType.model;
               model.render(mechaType);
           }
       }
       GL11.glPopMatrix();
   }

}
