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

public class ModelAUG
extends ModelGun {
    int textureX = 32;
    int textureY = 32;

    public ModelAUG() {
        this.gunModel = new ModelRendererTurbo[11];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 7, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 0, 0, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 13, 2, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 21, 7, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 8, 12, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 24, 1, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 0, 18, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 27, 4, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 23, 13, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 0, 22, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 5, 22, this.textureX, this.textureY);
        this.gunModel[0].addBox(-6.0f, -3.25f, -3.25f, 8, 2, 2, 0.0f);
        this.gunModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[0].rotateAngleX = -0.7853982f;
        this.gunModel[1].addShapeBox(-7.0f, -3.0f, -1.0f, 4, 4, 2, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.5f, 0.0f, 0.0f, -2.5f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[2].addShapeBox(-3.0f, -3.5f, -1.0f, 4, 2, 2, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.3f, 0.0f, 0.0f, -1.3f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[3].addShapeBox(-0.4f, -3.0f, -0.5f, 2, 4, 1, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f);
        this.gunModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[3].rotateAngleZ = -0.13962634f;
        this.gunModel[4].addShapeBox(2.0f, -3.0f, -3.0f, 5, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f);
        this.gunModel[4].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[4].rotateAngleX = -0.7853982f;
        this.gunModel[5].addShapeBox(7.0f, -3.4f, -0.5f, 2, 1, 1, 0.0f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f);
        this.gunModel[5].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[6].addBox(-1.5f, -5.0f, -0.5f, 8, 2, 1, 0.0f);
        this.gunModel[6].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[7].addShapeBox(6.5f, -5.0f, -0.5f, 1, 2, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[7].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[8].addBox(4.5f, -2.6f, -0.5f, 3, 1, 1, 0.0f);
        this.gunModel[8].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[9].addShapeBox(-2.5f, -5.0f, -0.5f, 1, 1, 1, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[9].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[10].addShapeBox(-7.0f, -3.25f, -3.25f, 1, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f);
        this.gunModel[10].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.gunModel[10].rotateAngleX = -0.7853982f;
        this.ammoModel = new ModelRendererTurbo[1];
        this.ammoModel[0] = new ModelRendererTurbo(this, 1, 12, this.textureX, this.textureY);
        this.ammoModel[0].addBox(-4.0f, -2.7f, -0.5f, 2, 4, 1, 0.0f);
        this.ammoModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[0].rotateAngleZ = 0.15707964f;
        this.slideModel = new ModelRendererTurbo[2];
        this.slideModel[0] = new ModelRendererTurbo(this, 19, 17, this.textureX, this.textureY);
        this.slideModel[1] = new ModelRendererTurbo(this, 19, 17, this.textureX, this.textureY);
        this.slideModel[0].addShapeBox(3.0f, -4.1f, 0.4f, 1, 1, 1, 0.0f, -0.5f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, -0.5f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f);
        this.slideModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.slideModel[1].addShapeBox(3.0f, -4.1f, -1.4f, 1, 1, 1, 0.0f, -0.5f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, -0.5f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f);
        this.slideModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.barrelAttachPoint = new Vector3f(0.5625f, 0.171875f, 0.0f);
        this.stockAttachPoint = new Vector3f(0.0f, 0.0f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.25f, 0.3125f, 0.0f);
        this.gripAttachPoint = new Vector3f(0.0f, 0.0f, 0.0f);
        this.gunSlideDistance = 0.0f;
        this.animationType = EnumAnimationType.PISTOL_CLIP;
        this.translateAll(1.0f, 0.0f, 0.0f);
        this.flipAll();
    }
}

