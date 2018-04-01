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

public class ModelBat
extends ModelGun {
    int textureX = 128;
    int textureY = 128;

    public ModelBat() {
        this.gunModel = new ModelRendererTurbo[12];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 17, 1, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 33, 1, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 57, 1, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 81, 1, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 105, 1, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 1, 49, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 97, 49, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 17, 57, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 73, 49, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 41, 57, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 57, 57, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(-1.0f, -51.0f, -2.0f, 3, 36, 4, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f);
        this.gunModel[0].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[0].rotateAngleZ = -0.08726646f;
        this.gunModel[1].addShapeBox(-2.0f, -51.0f, -2.0f, 1, 36, 4, 0.0f, 0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -1.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, -0.5f, 0.0f, -1.0f);
        this.gunModel[1].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[1].rotateAngleZ = -0.08726646f;
        this.gunModel[2].addShapeBox(-1.0f, -95.0f, -3.5f, 3, 44, 7, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f);
        this.gunModel[2].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[2].rotateAngleZ = -0.08726646f;
        this.gunModel[3].addShapeBox(2.0f, -95.0f, -3.5f, 1, 44, 7, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, -1.0f, 0.5f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.5f, 0.0f, -2.0f, 0.5f, 0.0f, -2.0f, 0.0f, 0.0f, -1.0f);
        this.gunModel[3].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[3].rotateAngleZ = -0.08726646f;
        this.gunModel[4].addShapeBox(-2.0f, -95.0f, -3.5f, 1, 44, 7, 0.0f, 0.5f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, -1.0f, 0.5f, 0.0f, -2.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.5f, 0.0f, -2.0f);
        this.gunModel[4].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[4].rotateAngleZ = -0.08726646f;
        this.gunModel[5].addShapeBox(2.0f, -51.0f, -2.0f, 1, 36, 4, 0.0f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f, -0.5f, 0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f, 0.0f, -0.5f, 0.0f, -1.0f, -0.5f, 0.0f, -1.0f, 0.5f, 0.0f, 0.0f);
        this.gunModel[5].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[5].rotateAngleZ = -0.08726646f;
        this.gunModel[6].addShapeBox(-1.0f, -97.0f, -3.5f, 3, 2, 7, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[6].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[6].rotateAngleZ = -0.08726646f;
        this.gunModel[7].addShapeBox(2.0f, -97.0f, -3.5f, 1, 2, 7, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, -1.0f, 0.5f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[7].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[7].rotateAngleZ = -0.08726646f;
        this.gunModel[8].addShapeBox(-2.0f, -97.0f, -3.5f, 1, 2, 7, 0.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.5f, 0.5f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, -1.0f);
        this.gunModel[8].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[8].rotateAngleZ = -0.08726646f;
        this.gunModel[9].addShapeBox(-2.0f, -15.0f, -2.0f, 1, 29, 4, 0.0f, -0.5f, 0.0f, -1.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, -0.5f, 0.0f, -1.0f, 0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f, -0.5f);
        this.gunModel[9].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[9].rotateAngleZ = -0.08726646f;
        this.gunModel[10].addShapeBox(2.0f, -15.0f, -2.0f, 1, 29, 4, 0.0f, 0.5f, 0.0f, 0.0f, -0.5f, 0.0f, -1.0f, -0.5f, 0.0f, -1.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.5f, 0.0f, -0.5f, 0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.5f);
        this.gunModel[10].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[10].rotateAngleZ = -0.08726646f;
        this.gunModel[11].addShapeBox(-1.0f, -15.0f, -2.0f, 3, 29, 4, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f);
        this.gunModel[11].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[11].rotateAngleZ = -0.08726646f;
        this.translateAll(0.0f, 0.0f, 0.0f);
        this.flipAll();
    }
}

