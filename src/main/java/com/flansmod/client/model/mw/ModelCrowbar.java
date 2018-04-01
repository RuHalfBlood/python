/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCrowbar
extends ModelGun {
    int textureX = 64;
    int textureY = 64;

    public ModelCrowbar() {
        this.gunModel = new ModelRendererTurbo[14];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 17, 1, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 33, 1, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 49, 1, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 17, 9, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 33, 9, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 49, 9, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 17, 17, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 33, 17, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 17, 25, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 33, 25, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 17, 33, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 33, 33, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 17, 41, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(-2.0f, -35.0f, 0.0f, 3, 45, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[0].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[0].rotateAngleZ = -0.08726646f;
        this.gunModel[1].addShapeBox(-2.0f, -40.0f, 0.0f, 3, 5, 1, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[1].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[1].rotateAngleZ = -0.08726646f;
        this.gunModel[2].addShapeBox(7.0f, -43.0f, 0.0f, 3, 3, 1, 0.0f, 3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[2].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[2].rotateAngleZ = -0.08726646f;
        this.gunModel[3].addShapeBox(-1.0f, -43.0f, 0.0f, 3, 3, 1, 0.0f, -3.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[3].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[3].rotateAngleZ = -0.08726646f;
        this.gunModel[4].addShapeBox(10.0f, -40.0f, 0.0f, 3, 6, 1, 0.0f, 3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, -1.0f, 0.0f, -0.5f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[4].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[4].rotateAngleZ = -0.08726646f;
        this.gunModel[5].addShapeBox(2.0f, -44.0f, 0.0f, 5, 1, 1, 0.0f, -2.0f, -0.5f, 0.0f, -2.0f, -0.5f, 0.0f, -2.0f, -0.5f, -1.0f, -2.0f, -0.5f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[5].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[5].rotateAngleZ = -0.08726646f;
        this.gunModel[6].addShapeBox(-2.0f, -35.0f, -1.0f, 3, 45, 1, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[6].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[6].rotateAngleZ = -0.08726646f;
        this.gunModel[7].addShapeBox(-2.0f, -40.0f, -1.0f, 3, 5, 1, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[7].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[7].rotateAngleZ = -0.08726646f;
        this.gunModel[8].addShapeBox(7.0f, -43.0f, -1.0f, 3, 3, 1, 0.0f, 2.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[8].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[8].rotateAngleZ = -0.08726646f;
        this.gunModel[9].addShapeBox(-1.0f, -43.0f, -1.0f, 3, 3, 1, 0.0f, -4.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[9].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[9].rotateAngleZ = -0.08726646f;
        this.gunModel[10].addShapeBox(10.0f, -40.0f, -1.0f, 3, 6, 1, 0.0f, 2.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f);
        this.gunModel[10].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[10].rotateAngleZ = -0.08726646f;
        this.gunModel[11].addShapeBox(2.0f, -44.0f, -1.0f, 5, 1, 1, 0.0f, -2.0f, -0.5f, -1.0f, -2.0f, -0.5f, -1.0f, -2.0f, -0.5f, 0.0f, -2.0f, -0.5f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[11].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[11].rotateAngleZ = -0.08726646f;
        this.gunModel[12].addShapeBox(-2.0f, 10.0f, 0.0f, 3, 7, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 2.0f, 0.0f, -0.5f, -2.0f, 0.0f, -0.5f, -3.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
        this.gunModel[12].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[12].rotateAngleZ = -0.08726646f;
        this.gunModel[13].addShapeBox(-2.0f, 10.0f, -1.0f, 3, 7, 1, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -2.0f, 0.0f, -0.5f, 2.0f, 0.0f, -0.5f);
        this.gunModel[13].setRotationPoint(-1.0f, 0.0f, 0.0f);
        this.gunModel[13].rotateAngleZ = -0.08726646f;
        this.translateAll(0.0f, 0.0f, 0.0f);
        this.flipAll();
    }
}

