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

public class ModelMTAR
extends ModelGun {
    int textureX = 32;
    int textureY = 32;

    public ModelMTAR() {
        this.gunModel = new ModelRendererTurbo[8];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 6, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 0, 10, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 7, 7, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 0, 14, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 7, 4, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 3, 3, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 8, 1, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 0, 19, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(-0.25f, -1.8f, -0.5f, 2, 3, 1, 0.0f, -0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, -0.25f, 0.0f, 0.0f);
        this.gunModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[0].rotateAngleZ = -0.13962634f;
        this.gunModel[1].addBox(-1.5f, -4.5f, -1.0f, 6, 2, 2, 0.0f);
        this.gunModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[2].addShapeBox(4.5f, -3.25f, -1.0f, 2, 1, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f);
        this.gunModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[3].addShapeBox(-5.5f, -4.5f, -1.0f, 4, 3, 2, 0.0f, 0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[4].addShapeBox(4.5f, -4.25f, -1.0f, 2, 1, 2, 0.0f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[4].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[5].addBox(6.0f, -3.75f, -0.5f, 2, 1, 1, 0.0f);
        this.gunModel[5].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[6].addBox(0.2f, -2.5f, -1.0f, 3, 1, 2, 0.0f);
        this.gunModel[6].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[7].addBox(0.0f, -5.0f, -0.5f, 6, 1, 1, 0.0f);
        this.gunModel[7].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel = new ModelRendererTurbo[1];
        this.ammoModel[0] = new ModelRendererTurbo(this, 0, 21, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(-3.8f, -3.0f, -0.5f, 2, 5, 1, 0.0f, 0.25f, 0.0f, 0.2f, -0.25f, 0.0f, 0.2f, -0.25f, 0.0f, 0.2f, 0.25f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, -0.5f, 0.2f, 0.0f, -0.5f, 0.2f, 0.0f, 0.0f, 0.2f);
        this.ammoModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.slideModel = new ModelRendererTurbo[1];
        this.slideModel[0] = new ModelRendererTurbo(this, 6, 21, this.textureX, this.textureY);
        this.slideModel[0].addShapeBox(2.5f, -4.0f, -1.0f, 1, 1, 2, 0.0f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.25f);
        this.slideModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.barrelAttachPoint = new Vector3f(0.5625f, 0.203125f, 0.0f);
        this.stockAttachPoint = new Vector3f(0.0f, 0.0f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.25f, 0.3125f, 0.0f);
        this.gripAttachPoint = new Vector3f(0.0f, 0.0f, 0.0f);
        this.gunSlideDistance = 0.0f;
        this.animationType = EnumAnimationType.PISTOL_CLIP;
        this.translateAll(1.0f, 0.0f, 0.0f);
        this.flipAll();
    }
}

