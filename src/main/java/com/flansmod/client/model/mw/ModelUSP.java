/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.client.model.ModelBase;

public class ModelUSP
extends ModelGun {
    int textureX = 32;
    int textureY = 32;

    public ModelUSP() {
        this.gunModel = new ModelRendererTurbo[6];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 8, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 0, 2, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 0, 17, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 0, 20, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 8, 11, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 10, 17, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.15f, -2.75f, -1.0f, 2, 4, 2, 0.0f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.75f, -0.45f, -0.2f, -0.75f, -0.45f, -0.2f, -0.75f, -0.45f, -0.2f, 0.75f, -0.45f, -0.2f);
        this.gunModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[1].addShapeBox(-1.1f, -3.75f, -1.0f, 6, 1, 2, 0.0f, -0.5f, -0.25f, -0.2f, 0.5f, -0.25f, -0.2f, 0.5f, -0.25f, -0.2f, -0.5f, -0.25f, -0.2f, -0.25f, 0.0f, -0.2f, 0.5f, 0.0f, -0.2f, 0.5f, 0.0f, -0.2f, -0.25f, 0.0f, -0.2f);
        this.gunModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[2].addShapeBox(0.9f, -4.75f, -0.5f, 4, 2, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f);
        this.gunModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[3].addShapeBox(4.9f, -4.75f, -0.5f, 2, 1, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[4].addShapeBox(5.4f, -3.75f, -1.0f, 1, 1, 2, 0.0f, 0.0f, -0.25f, -0.4f, 0.0f, -0.25f, -0.4f, 0.0f, -0.25f, -0.4f, 0.0f, -0.25f, -0.4f, 0.0f, 0.0f, -0.4f, 0.0f, 0.0f, -0.4f, 0.0f, 0.0f, -0.4f, 0.0f, 0.0f, -0.4f);
        this.gunModel[4].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[5].addShapeBox(1.8f, -3.25f, -1.0f, 2, 2, 2, 0.0f, 0.0f, 0.0f, -0.35f, 0.0f, 0.0f, -0.35f, 0.0f, 0.0f, -0.35f, 0.0f, 0.0f, -0.35f, 0.0f, -0.125f, -0.35f, 0.0f, -0.125f, -0.35f, 0.0f, -0.125f, -0.35f, 0.0f, -0.125f, -0.35f);
        this.gunModel[5].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.defaultScopeModel = new ModelRendererTurbo[2];
        this.defaultScopeModel[0] = new ModelRendererTurbo(this, 0, 6, this.textureX, this.textureY);
        this.defaultScopeModel[1] = new ModelRendererTurbo(this, 4, 6, this.textureX, this.textureY);
        this.defaultScopeModel[0].addShapeBox(5.6f, -5.35f, -0.5f, 1, 1, 1, 0.0f, -0.35f, 0.1f, -0.3f, -0.35f, 0.1f, -0.3f, -0.35f, 0.1f, -0.3f, -0.35f, 0.1f, -0.3f, -0.35f, 0.0f, -0.3f, -0.35f, 0.0f, -0.3f, -0.35f, 0.0f, -0.3f, -0.35f, 0.0f, -0.3f);
        this.defaultScopeModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.defaultScopeModel[1].addShapeBox(-0.4f, -5.35f, -0.5f, 1, 1, 1, 0.0f, -0.35f, 0.0f, -0.1f, -0.35f, 0.0f, -0.1f, -0.35f, 0.0f, -0.1f, -0.35f, 0.0f, -0.1f, -0.35f, 0.0f, -0.1f, -0.35f, 0.0f, -0.1f, -0.35f, 0.0f, -0.1f, -0.35f, 0.0f, -0.1f);
        this.defaultScopeModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel = new ModelRendererTurbo[2];
        this.ammoModel[0] = new ModelRendererTurbo(this, 14, 11, this.textureX, this.textureY);
        this.ammoModel[1] = new ModelRendererTurbo(this, 14, 8, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(0.15f, -2.75f, -1.0f, 2, 4, 2, 0.0f, -0.125f, 0.0f, -0.35f, -0.125f, 0.0f, -0.35f, -0.125f, 0.0f, -0.35f, -0.125f, 0.0f, -0.35f, 0.625f, -0.45f, -0.35f, -0.875f, -0.45f, -0.35f, -0.875f, -0.45f, -0.35f, 0.625f, -0.45f, -0.35f);
        this.ammoModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[1].addShapeBox(-0.6f, 0.55f, -1.0f, 2, 1, 2, 0.0f, 0.1f, -0.25f, -0.1f, 0.1f, -0.25f, -0.1f, 0.1f, -0.25f, -0.1f, 0.1f, -0.25f, -0.1f, 0.1f, -0.2f, -0.1f, 0.2f, -0.2f, -0.1f, 0.2f, -0.2f, -0.1f, 0.1f, -0.2f, -0.1f);
        this.ammoModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.slideModel = new ModelRendererTurbo[2];
        this.slideModel[0] = new ModelRendererTurbo(this, 11, 22, this.textureX, this.textureY);
        this.slideModel[1] = new ModelRendererTurbo(this, 11, 27, this.textureX, this.textureY);
        this.slideModel[0].addShapeBox(-1.1f, -5.25f, -1.0f, 8, 2, 2, 0.0f, -0.85f, -0.6f, -0.2f, -0.5f, -0.6f, -0.2f, -0.5f, -0.6f, -0.2f, -0.85f, -0.6f, -0.2f, -0.5f, -0.25f, -0.2f, -0.5f, -0.25f, -0.2f, -0.5f, -0.25f, -0.2f, -0.5f, -0.25f, -0.2f);
        this.slideModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.slideModel[1].addShapeBox(-1.1f, -5.65f, -1.0f, 8, 1, 2, 0.0f, -1.0f, -0.6f, -0.45f, -0.5f, -0.6f, -0.45f, -0.5f, -0.6f, -0.45f, -1.0f, -0.6f, -0.45f, -0.85f, 0.0f, -0.2f, -0.5f, 0.0f, -0.2f, -0.5f, 0.0f, -0.2f, -0.85f, 0.0f, -0.2f);
        this.slideModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.barrelAttachPoint = new Vector3f(0.43125f, 0.265625f, 0.0f);
        this.stockAttachPoint = new Vector3f(0.0f, 0.0f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.125f, 0.3125f, 0.0f);
        this.gripAttachPoint = new Vector3f(0.0f, 0.25f, 0.0f);
        this.gunSlideDistance = 0.0f;
        this.animationType = EnumAnimationType.PISTOL_CLIP;
        this.translateAll(2.0f, -1.5f, 0.0f);
        this.hasFlash = false;
        this.hasArms = true;
        this.leftArmPos = new Vector3f(-0.15f, -0.35f, 0.0f);
        this.leftArmRot = new Vector3f(90.0f, 45.0f, 0.0f);
        this.leftArmReloadPos = new Vector3f(-0.15, -0.3499999940395355, 0.0);
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

