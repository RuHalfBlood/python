/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelDragunov
extends ModelGun {
    int textureX = 64;
    int textureY = 64;

    public ModelDragunov() {
        this.gunModel = new ModelRendererTurbo[18];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 12, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 0, 9, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 0, 6, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 11, 17, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 0, 3, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 11, 22, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 11, 26, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 11, 30, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 16, 0, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 9, 0, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 24, 7, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 0, 0, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 0, 45, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 0, 51, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 8, 51, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 0, 48, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 16, 45, this.textureX, this.textureY);
        this.gunModel[17] = new ModelRendererTurbo(this, 0, 29, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(-0.5f, -4.2f, -1.0f, 7, 2, 2, 0.0f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, -0.2f);
        this.gunModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[1].addShapeBox(0.0f, -5.0f, -1.0f, 6, 1, 2, 0.0f, 0.0f, -0.2f, -0.6f, 0.0f, -0.2f, -0.6f, 0.0f, -0.2f, -0.6f, 0.0f, -0.2f, -0.6f, 0.5f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.5f, 0.0f, -0.2f);
        this.gunModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[2].addShapeBox(7.5f, -3.8f, -0.5f, 11, 1, 1, 0.0f, 0.0f, -0.1f, -0.125f, 0.0f, -0.1f, -0.125f, 0.0f, -0.1f, -0.125f, 0.0f, -0.1f, -0.125f, 0.0f, -0.1f, -0.125f, 0.0f, -0.1f, -0.125f, 0.0f, -0.1f, -0.125f, 0.0f, -0.1f, -0.125f);
        this.gunModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[3].addShapeBox(6.5f, -4.0f, -1.0f, 6, 2, 2, 0.0f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.65f, -0.25f, 0.0f, -0.65f, -0.25f, 0.0f, -0.25f, -0.25f);
        this.gunModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[4].addShapeBox(7.75f, -4.8f, -0.5f, 6, 1, 1, 0.0f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f);
        this.gunModel[4].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[5].addShapeBox(7.5f, -5.0f, -1.0f, 4, 1, 2, 0.0f, -0.5f, -0.15f, -0.55f, 0.0f, -0.15f, -0.55f, 0.0f, -0.15f, -0.55f, -0.5f, -0.15f, -0.55f, -0.5f, -0.1f, -0.25f, 0.0f, -0.1f, -0.25f, 0.0f, -0.1f, -0.25f, -0.5f, -0.1f, -0.25f);
        this.gunModel[5].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[6].addShapeBox(13.0f, -5.0f, -0.5f, 1, 2, 1, 0.0f, -0.75f, -1.05f, -0.15f, -0.25f, -0.35f, -0.15f, -0.25f, -0.35f, -0.15f, -0.75f, -1.05f, -0.15f, -0.9f, -0.65f, -0.15f, 0.4f, -0.65f, -0.15f, 0.4f, -0.65f, -0.15f, -0.9f, -0.65f, -0.15f);
        this.gunModel[6].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[7].addShapeBox(18.5f, -3.8f, -0.5f, 3, 1, 1, 0.0f, 0.0f, -0.05f, -0.05f, 0.0f, -0.05f, -0.05f, 0.0f, -0.05f, -0.05f, 0.0f, -0.05f, -0.05f, 0.0f, -0.05f, -0.05f, 0.0f, -0.05f, -0.05f, 0.0f, -0.05f, -0.05f, 0.0f, -0.05f, -0.05f);
        this.gunModel[7].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[8].addShapeBox(-1.2f, -3.0f, -1.0f, 2, 4, 2, 0.0f, -0.3f, 0.0f, -0.4f, -0.2f, 0.0f, -0.4f, -0.2f, 0.0f, -0.4f, -0.3f, 0.0f, -0.4f, -0.3f, -0.2f, -0.4f, -0.1f, -0.5f, -0.4f, -0.1f, -0.5f, -0.4f, -0.3f, -0.2f, -0.4f);
        this.gunModel[8].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[8].rotateAngleZ = -0.2617994f;
        this.gunModel[9].addBox(0.65f, -2.4f, -0.5f, 2, 1, 1, 0.0f);
        this.gunModel[9].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[10].addShapeBox(18.5f, -5.5f, -0.5f, 1, 2, 1, 0.0f, -0.5f, -0.1f, -0.35f, 0.0f, -0.1f, -0.35f, 0.0f, -0.1f, -0.35f, -0.5f, -0.1f, -0.35f, -0.2f, 0.0f, -0.35f, 0.0f, 0.0f, -0.35f, 0.0f, 0.0f, -0.35f, -0.2f, 0.0f, -0.35f);
        this.gunModel[10].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[11].addShapeBox(1.7f, -3.1f, -0.5f, 1, 2, 1, 0.0f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.4f, -0.2f, -0.2f, -0.2f, -0.2f, -0.2f, -0.2f, -0.2f, -0.2f, -0.4f, -0.2f, -0.2f);
        this.gunModel[11].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[11].rotateAngleZ = -0.19198622f;
        this.gunModel[12].addShapeBox(-6.0f, -3.5f, -1.0f, 6, 1, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, 0.0f);
        this.gunModel[12].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[13].addShapeBox(-8.0f, -3.5f, -1.0f, 2, 5, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.15f, 0.0f, 0.0f, -0.15f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[13].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[14].addShapeBox(-6.5f, -3.7f, -1.0f, 4, 1, 2, 0.0f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f);
        this.gunModel[14].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[15].addShapeBox(-6.0f, -0.5f, -1.0f, 6, 1, 2, 0.0f, 0.0f, -0.85f, 0.0f, 0.0f, 0.0f, -0.45f, 0.0f, 0.0f, -0.45f, 0.0f, -0.85f, 0.0f, 0.0f, 0.85f, 0.0f, 0.0f, 0.0f, -0.45f, 0.0f, 0.0f, -0.45f, 0.0f, 0.85f, 0.0f);
        this.gunModel[15].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[16].addShapeBox(-5.0f, -2.75f, -0.5f, 2, 3, 1, 0.0f, 0.0f, 0.0f, 0.4f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.4f, 0.0f, 0.0f, 0.4f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.4f);
        this.gunModel[16].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[17].addShapeBox(6.0f, -5.0f, -1.0f, 2, 1, 2, 0.0f, 0.0f, -0.1f, -0.35f, 0.0f, -0.1f, -0.35f, 0.0f, -0.1f, -0.35f, 0.0f, -0.1f, -0.35f, 0.0f, 0.0f, -0.35f, 0.0f, 0.0f, -0.35f, 0.0f, 0.0f, -0.35f, 0.0f, 0.0f, -0.35f);
        this.gunModel[17].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.defaultScopeModel = new ModelRendererTurbo[10];
        this.defaultScopeModel[0] = new ModelRendererTurbo(this, 26, 27, this.textureX, this.textureY);
        this.defaultScopeModel[1] = new ModelRendererTurbo(this, 30, 31, this.textureX, this.textureY);
        this.defaultScopeModel[2] = new ModelRendererTurbo(this, 30, 31, this.textureX, this.textureY);
        this.defaultScopeModel[3] = new ModelRendererTurbo(this, 15, 37, this.textureX, this.textureY);
        this.defaultScopeModel[4] = new ModelRendererTurbo(this, 23, 45, this.textureX, this.textureY);
        this.defaultScopeModel[5] = new ModelRendererTurbo(this, 32, 37, this.textureX, this.textureY);
        this.defaultScopeModel[6] = new ModelRendererTurbo(this, 15, 41, this.textureX, this.textureY);
        this.defaultScopeModel[7] = new ModelRendererTurbo(this, 25, 41, this.textureX, this.textureY);
        this.defaultScopeModel[8] = new ModelRendererTurbo(this, 32, 37, this.textureX, this.textureY);
        this.defaultScopeModel[9] = new ModelRendererTurbo(this, 23, 49, this.textureX, this.textureY);
        this.defaultScopeModel[0].addShapeBox(2.0f, -4.4f, 0.0f, 3, 2, 1, 0.0f, 0.0f, -0.4f, 0.0f, 0.0f, -0.4f, 0.0f, 0.0f, -0.4f, 0.0f, 0.0f, -0.4f, 0.0f, -0.2f, -0.4f, 0.0f, -0.2f, -0.4f, 0.0f, -0.2f, -0.4f, 0.0f, -0.2f, -0.4f, 0.0f);
        this.defaultScopeModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.defaultScopeModel[1].addShapeBox(1.8f, -7.0f, 0.0f, 1, 3, 1, 0.0f, -0.2f, -0.5f, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f, -0.5f, 0.0f, -0.2f, -0.5f, 0.0f, -0.2f, 0.0f, -0.5f, -0.2f, 0.0f, -0.5f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f);
        this.defaultScopeModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.defaultScopeModel[2].addShapeBox(4.2f, -7.0f, 0.0f, 1, 3, 1, 0.0f, -0.2f, -0.5f, -0.5f, -0.2f, -0.5f, -0.5f, -0.2f, -0.5f, 0.0f, -0.2f, -0.5f, 0.0f, -0.2f, 0.0f, -0.5f, -0.2f, 0.0f, -0.5f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f);
        this.defaultScopeModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.defaultScopeModel[3].addBox(0.0f, -6.5f, -0.5f, 7, 1, 1, 0.0f);
        this.defaultScopeModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.defaultScopeModel[4].addShapeBox(1.5f, -7.0f, -1.0f, 4, 2, 2, 0.0f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f);
        this.defaultScopeModel[4].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.defaultScopeModel[5].addShapeBox(6.25f, -7.0f, -1.0f, 1, 2, 2, 0.0f, 0.0f, -0.5f, -0.5f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.5f, -0.5f);
        this.defaultScopeModel[5].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.defaultScopeModel[6].addShapeBox(7.25f, -7.0f, -1.0f, 3, 2, 2, 0.0f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f);
        this.defaultScopeModel[6].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.defaultScopeModel[7].addShapeBox(-2.75f, -7.0f, -1.0f, 2, 2, 2, 0.0f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f);
        this.defaultScopeModel[7].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.defaultScopeModel[8].addShapeBox(-0.75f, -7.0f, -1.0f, 1, 2, 2, 0.0f, 0.0f, -0.2f, -0.2f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.2f, -0.2f);
        this.defaultScopeModel[8].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.defaultScopeModel[9].addShapeBox(1.25f, -5.75f, -0.5f, 5, 1, 1, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.45f, 0.0f, 0.0f, -0.45f, 0.0f, 0.0f, -0.45f, 0.0f, 0.0f, -0.45f, 0.0f);
        this.defaultScopeModel[9].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel = new ModelRendererTurbo[1];
        this.ammoModel[0] = new ModelRendererTurbo(this, 0, 39, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(2.5f, -2.5f, -1.0f, 4, 3, 2, 0.0f, -0.4f, 0.0f, -0.3f, -0.4f, 0.0f, -0.3f, -0.4f, 0.0f, -0.3f, -0.4f, 0.0f, -0.3f, -0.6f, -0.3f, -0.3f, -0.2f, -0.5f, -0.3f, -0.2f, -0.5f, -0.3f, -0.6f, -0.3f, -0.3f);
        this.ammoModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.slideModel = new ModelRendererTurbo[1];
        this.slideModel[0] = new ModelRendererTurbo(this, 4, 0, this.textureX, this.textureY);
        this.slideModel[0].addShapeBox(5.3f, -4.25f, -1.6f, 1, 1, 1, 0.0f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f, 0.0f);
        this.slideModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.animationType = EnumAnimationType.BOTTOM_CLIP;
        this.barrelAttachPoint = new Vector3f(1.15625f, 0.2f, 0.0f);
        this.stockAttachPoint = new Vector3f(-0.03125f, 0.1875f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.471875f, 0.2875f, 0.0f);
        this.gripAttachPoint = new Vector3f(0.5625f, 0.171875f, 0.0f);
        this.gunSlideDistance = 0.1f;
        this.animationType = EnumAnimationType.BOTTOM_CLIP;
        this.translateAll(2.0f, -1.5f, 0.0f);
        this.hasFlash = false;
        this.hasArms = true;
        this.leftArmPos = new Vector3f(-0.15f, -0.35f, 0.0f);
        this.leftArmRot = new Vector3f(90.0f, 45.0f, 0.0f);
        this.leftArmReloadPos = new Vector3f(-0.15f, -0.35f, 0.0f);
        this.leftArmReloadRot = new Vector3f(90.0f, 45.0f, 0.0f);
        this.rightArmPos = new Vector3f(0.4f, -0.5f, 0.0f);
        this.rightArmRot = new Vector3f(0.0f, 0.0f, -90.0f);
        this.rightArmReloadPos = new Vector3f(0.4f, -0.5f, 0.0f);
        this.rightArmReloadRot = new Vector3f(0.0f, 0.0f, -90.0f);
        this.rightHandAmmo = false;
        this.leftHandAmmo = true;
        this.flipAll();
    }
}

