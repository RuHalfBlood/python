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

public class ModelGlock
extends ModelGun {
    int textureX = 64;
    int textureY = 64;

    public ModelGlock() {
        this.gunModel = new ModelRendererTurbo[23];
        this.gunModel[0] = new ModelRendererTurbo(this, 25, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 41, 1, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 49, 1, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 57, 1, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 1, 9, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 9, 9, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 17, 9, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 25, 9, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 33, 9, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 41, 9, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 49, 9, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 57, 9, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 1, 17, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 9, 17, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 17, 17, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 25, 17, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 33, 17, this.textureX, this.textureY);
        this.gunModel[17] = new ModelRendererTurbo(this, 41, 17, this.textureX, this.textureY);
        this.gunModel[18] = new ModelRendererTurbo(this, 49, 17, this.textureX, this.textureY);
        this.gunModel[19] = new ModelRendererTurbo(this, 57, 17, this.textureX, this.textureY);
        this.gunModel[20] = new ModelRendererTurbo(this, 1, 25, this.textureX, this.textureY);
        this.gunModel[21] = new ModelRendererTurbo(this, 9, 25, this.textureX, this.textureY);
        this.gunModel[22] = new ModelRendererTurbo(this, 41, 33, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 1, 0.0f, -0.4f, -0.05f, -0.05f, 0.1f, -0.05f, -0.05f, 0.1f, -0.05f, -0.05f, -0.4f, -0.05f, -0.05f, -0.4f, -0.05f, -0.05f, -0.1f, -0.2f, -0.05f, -0.1f, -0.2f, -0.05f, -0.4f, -0.05f, -0.05f);
        this.gunModel[0].setRotationPoint(-0.6f, -2.95f, -0.5f);
        this.gunModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.2f, -0.1f, 0.0f, -0.2f, -0.1f, 0.0f, -0.2f, -0.1f, 0.0f, -0.2f, -0.1f);
        this.gunModel[1].setRotationPoint(2.2f, -3.0f, -0.5f);
        this.gunModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.1f, -0.12f, 0.0f, -0.1f, -0.12f, 0.0f, -0.1f, -0.12f, 0.0f, -0.1f, -0.12f, 0.0f, -0.2f, -0.12f, 0.0f, -0.3f, -0.12f, 0.0f, -0.3f, -0.12f, 0.0f, -0.2f, -0.12f);
        this.gunModel[2].setRotationPoint(4.2f, -3.0f, -0.5f);
        this.gunModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, -0.1f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, -0.1f, 0.0f, -0.05f, 0.0f, -0.25f, -0.05f, 0.0f, -0.25f, -0.05f, 0.0f, -0.25f, -0.05f, 0.0f, -0.25f, -0.05f);
        this.gunModel[3].setRotationPoint(-1.2f, -2.95f, -0.5f);
        this.gunModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, 1.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, 1.0f, 0.0f, -0.05f);
        this.gunModel[4].setRotationPoint(-1.2f, -1.2f, -0.5f);
        this.gunModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, 0.0f, 0.05f, -0.05f, 0.5f, 0.15f, -0.05f, 0.5f, 0.15f, -0.05f, 0.0f, 0.05f, -0.05f, 0.0f, -0.2f, -0.05f, -0.2f, -0.2f, -0.05f, -0.2f, -0.2f, -0.05f, 0.0f, -0.2f, -0.05f);
        this.gunModel[5].setRotationPoint(-0.2f, -1.95f, -0.5f);
        this.gunModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, -0.3f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, -0.3f, 0.0f, -0.05f);
        this.gunModel[6].setRotationPoint(-2.2f, 0.8f, -0.5f);
        this.gunModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, 0.0f, 0.2f, -0.05f, -0.2f, 0.2f, -0.05f, -0.2f, 0.2f, -0.05f, 0.0f, 0.2f, -0.05f, 0.0f, -0.25f, -0.05f, -1.0f, -0.25f, -0.05f, -1.0f, -0.25f, -0.05f, 0.0f, -0.25f, -0.05f);
        this.gunModel[7].setRotationPoint(-0.2f, 0.05f, -0.5f);
        this.gunModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.7f, 0.0f, -0.05f, -0.3f, 0.0f, -0.05f, -0.3f, 0.0f, -0.05f, -0.7f, 0.0f, -0.05f, -0.5f, -0.4f, -0.05f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, -0.5f, -0.4f, -0.05f);
        this.gunModel[8].setRotationPoint(-2.9f, 0.8f, -0.5f);
        this.gunModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.15f, -0.05f, 0.0f, -0.05f, -0.05f, 0.0f, -0.05f, -0.05f, -0.4f, -0.15f, -0.05f, -0.3f, -0.6f, -0.05f, -0.05f, -0.25f, -0.05f, -0.05f, -0.25f, -0.05f, -0.3f, -0.6f, -0.05f);
        this.gunModel[9].setRotationPoint(-2.1f, -2.95f, -0.5f);
        this.gunModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.25f, -0.05f, -0.4f, -0.45f, -0.05f, -0.4f, -0.45f, -0.05f, -0.4f, -0.25f, -0.05f, -0.4f, -0.25f, -0.05f, -0.4f, -0.45f, -0.05f, -0.4f, -0.45f, -0.05f, -0.4f, -0.25f, -0.05f);
        this.gunModel[10].setRotationPoint(0.4f, -0.95f, -0.5f);
        this.gunModel[10].rotateAngleZ = -0.29670596f;
        this.gunModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.25f, -0.05f, -0.4f, -0.45f, -0.05f, -0.4f, -0.45f, -0.05f, -0.4f, -0.25f, -0.05f, -0.4f, -0.25f, -0.05f, -0.4f, -0.45f, -0.05f, -0.4f, -0.45f, -0.05f, -0.4f, -0.25f, -0.05f);
        this.gunModel[11].setRotationPoint(0.05f, -0.1f, -0.5f);
        this.gunModel[11].rotateAngleZ = -0.27925268f;
        this.gunModel[12].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, 0.0f, -0.05f, -0.4f, -0.45f, -0.05f, -0.4f, -0.45f, -0.05f, -0.4f, 0.0f, -0.05f, -0.4f, -0.25f, -0.05f, -0.4f, -0.45f, -0.05f, -0.4f, -0.45f, -0.05f, -0.4f, -0.25f, -0.05f);
        this.gunModel[12].setRotationPoint(-0.3f, 0.8f, -0.5f);
        this.gunModel[12].rotateAngleZ = -0.27925268f;
        this.gunModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.38f, 0.2f, -0.15f, -0.38f, 0.2f, -0.15f, -0.38f, 0.2f, -0.15f, -0.38f, 0.2f, -0.15f, -0.38f, 0.2f, -0.15f, -0.38f, 0.2f, -0.15f, -0.38f, 0.2f, -0.15f, -0.38f, 0.2f, -0.15f);
        this.gunModel[13].setRotationPoint(2.65f, -2.1f, -0.5f);
        this.gunModel[14].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.38f, -0.15f, -0.4f, -0.38f, -0.15f, -0.4f, -0.38f, -0.15f, 0.0f, -0.38f, -0.15f, 0.0f, -0.38f, -0.15f, -0.4f, -0.38f, -0.15f, -0.4f, -0.38f, -0.15f, 0.0f, -0.38f, -0.15f);
        this.gunModel[14].setRotationPoint(1.65f, -1.5f, -0.5f);
        this.gunModel[15].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.38f, -0.15f, -0.4f, -0.38f, -0.15f, -0.4f, -0.38f, -0.15f, 0.0f, -0.38f, -0.15f, 0.0f, -0.35f, -0.15f, -0.4f, -0.38f, -0.15f, -0.4f, -0.38f, -0.15f, 0.0f, -0.35f, -0.15f);
        this.gunModel[15].setRotationPoint(1.5f, -1.73f, -0.5f);
        this.gunModel[15].rotateAngleZ = -0.61086524f;
        this.gunModel[16].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, 0.0f, -0.15f, -0.3f, -0.4f, -0.15f, -0.3f, -0.4f, -0.15f, -0.4f, 0.0f, -0.15f, -0.4f, 0.0f, -0.15f, -0.3f, -0.4f, -0.15f, -0.3f, -0.4f, -0.15f, -0.4f, 0.0f, -0.15f);
        this.gunModel[16].setRotationPoint(0.75f, -1.95f, -0.5f);
        this.gunModel[16].rotateAngleZ = -0.27925268f;
        this.gunModel[17].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, 0.0f, -0.055f, -0.3f, -0.4f, -0.055f, -0.3f, -0.4f, -0.055f, 0.0f, 0.0f, -0.055f, -0.4f, 0.0f, -0.055f, -0.3f, -0.4f, -0.055f, -0.3f, -0.4f, -0.055f, -0.4f, 0.0f, -0.055f);
        this.gunModel[17].setRotationPoint(1.1f, -2.9f, -0.5f);
        this.gunModel[17].rotateAngleZ = -0.27925268f;
        this.gunModel[18].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.6f, -0.15f, -0.3f, -0.38f, -0.15f, -0.3f, -0.38f, -0.15f, -0.3f, -0.6f, -0.15f, -0.3f, -0.38f, -0.15f, -0.3f, -0.38f, -0.15f, -0.3f, -0.38f, -0.15f, -0.3f, -0.38f, -0.15f);
        this.gunModel[18].setRotationPoint(2.35f, -1.73f, -0.5f);
        this.gunModel[19].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.38f, -0.3f, -0.15f, -0.6f, -0.3f, -0.15f, -0.6f, -0.3f, -0.15f, -0.38f, -0.3f, -0.15f, -0.38f, -0.3f, -0.15f, -0.45f, -0.4f, -0.15f, -0.45f, -0.4f, -0.15f, -0.38f, -0.3f, -0.15f);
        this.gunModel[19].setRotationPoint(2.9f, -1.59f, -0.5f);
        this.gunModel[20].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.38f, -0.3f, -0.15f, -0.4f, -0.3f, -0.15f, -0.4f, -0.3f, -0.15f, -0.38f, -0.3f, -0.15f, -0.38f, -0.3f, -0.15f, -0.6f, -0.3f, -0.15f, -0.6f, -0.3f, -0.15f, -0.38f, -0.3f, -0.15f);
        this.gunModel[20].setRotationPoint(2.89f, -2.58f, -0.5f);
        this.gunModel[21].addShapeBox(0.0f, 0.2f, 0.0f, 1, 1, 1, 0.0f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f);
        this.gunModel[21].setRotationPoint(1.2f, -2.4f, -0.5f);
        this.gunModel[21].rotateAngleZ = 0.2443461f;
        this.gunModel[22].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 4.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 4.0f, -0.15f, -0.15f, 4.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 4.0f, -0.15f, -0.15f);
        this.gunModel[22].setRotationPoint(4.5f, -3.9f, -0.5f);
        this.ammoModel = new ModelRendererTurbo[2];
        this.ammoModel[0] = new ModelRendererTurbo(this, 49, 33, this.textureX, this.textureY);
        this.ammoModel[1] = new ModelRendererTurbo(this, 57, 33, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 2, 8, 1, 0.0f, -0.2f, -0.05f, -0.15f, -0.2f, -0.05f, -0.15f, -0.2f, -0.05f, -0.15f, -0.2f, -0.05f, -0.15f, 2.6f, -0.2f, -0.15f, -3.2f, 0.1f, -0.15f, -3.2f, 0.1f, -0.15f, 2.6f, -0.2f, -0.15f);
        this.ammoModel[0].setRotationPoint(-0.8f, -1.9f, -0.5f);
        this.ammoModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 2, 2, 1, 0.0f, -0.1f, -0.2f, -0.05f, -0.1f, -0.2f, -0.05f, -0.1f, -0.2f, -0.05f, -0.1f, -0.2f, -0.05f, 0.2f, -1.0f, -0.05f, -0.5f, -0.7f, -0.05f, -0.5f, -0.7f, -0.05f, 0.2f, -1.0f, -0.05f);
        this.ammoModel[1].setRotationPoint(-3.4f, 5.0f, -0.5f);
        this.slideModel = new ModelRendererTurbo[11];
        this.slideModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.slideModel[1] = new ModelRendererTurbo(this, 25, 25, this.textureX, this.textureY);
        this.slideModel[2] = new ModelRendererTurbo(this, 33, 25, this.textureX, this.textureY);
        this.slideModel[3] = new ModelRendererTurbo(this, 41, 25, this.textureX, this.textureY);
        this.slideModel[4] = new ModelRendererTurbo(this, 49, 25, this.textureX, this.textureY);
        this.slideModel[5] = new ModelRendererTurbo(this, 57, 25, this.textureX, this.textureY);
        this.slideModel[6] = new ModelRendererTurbo(this, 1, 33, this.textureX, this.textureY);
        this.slideModel[7] = new ModelRendererTurbo(this, 9, 33, this.textureX, this.textureY);
        this.slideModel[8] = new ModelRendererTurbo(this, 17, 33, this.textureX, this.textureY);
        this.slideModel[9] = new ModelRendererTurbo(this, 25, 33, this.textureX, this.textureY);
        this.slideModel[10] = new ModelRendererTurbo(this, 33, 33, this.textureX, this.textureY);
        this.slideModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 8, 1, 1, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, -0.05f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, 0.0f);
        this.slideModel[0].setRotationPoint(-1.6f, -3.9f, -0.5f);
        this.slideModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.3f, -0.3f, -0.4f, -0.3f, -0.3f, -0.4f, -0.3f, -0.45f, -0.4f, -0.3f, -0.45f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.35f, -0.35f, -0.3f, -0.35f);
        this.slideModel[1].setRotationPoint(-1.8f, -4.4f, -0.2f);
        this.slideModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.3f, -0.4f, -0.4f, -0.3f, -0.4f, -0.4f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.35f, -0.35f, -0.3f, -0.35f);
        this.slideModel[2].setRotationPoint(-1.8f, -4.4f, -0.75f);
        this.slideModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.4f, -0.42f, -0.4f, -0.4f, -0.42f, -0.4f, -0.4f, -0.42f, -0.45f, -0.4f, -0.42f, -0.45f, -0.4f, -0.42f, -0.3f, -0.4f, -0.42f, -0.3f, -0.4f, -0.42f, -0.45f, -0.4f, -0.42f);
        this.slideModel[3].setRotationPoint(5.5f, -4.4f, -0.5f);
        this.slideModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.35f, -0.3f, -0.4f, -0.35f, -0.3f, -0.4f, -0.35f, -0.3f, -0.4f, -0.35f, -0.3f, -0.35f, -0.35f, -0.3f, -0.35f, -0.35f, -0.3f, -0.35f, -0.35f, -0.3f, -0.35f, -0.35f, -0.3f);
        this.slideModel[4].setRotationPoint(-1.8f, -4.3f, -0.5f);
        this.slideModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f);
        this.slideModel[5].setRotationPoint(-1.8f, -3.8f, -0.5f);
        this.slideModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f);
        this.slideModel[6].setRotationPoint(-1.4f, -3.8f, -0.5f);
        this.slideModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f);
        this.slideModel[7].setRotationPoint(-1.0f, -3.8f, -0.5f);
        this.slideModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f);
        this.slideModel[8].setRotationPoint(-0.6f, -3.8f, -0.5f);
        this.slideModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f, -0.4f, -0.01f, 0.02f);
        this.slideModel[9].setRotationPoint(-0.2f, -3.8f, -0.5f);
        this.slideModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.3f, -0.1f, -0.2f, -0.3f, -0.1f, -0.2f, -0.3f, -0.1f, -0.2f, -0.3f, -0.1f, -0.2f, -0.3f, -0.3f, -0.2f, -0.3f, -0.3f, -0.2f, -0.3f, -0.3f, -0.2f, -0.3f, -0.3f, -0.2f);
        this.slideModel[10].setRotationPoint(1.4f, -3.97f, -0.75f);
        this.barrelAttachPoint = new Vector3f(0.4125f, 0.26875f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.125f, 0.30625f, 0.0f);
        this.scopeIsOnSlide = true;
        this.translateAll(2.0f, -1.5f, 0.0f);
        this.gunSlideDistance = 0.2f;
        this.animationType = EnumAnimationType.PISTOL_CLIP;
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

