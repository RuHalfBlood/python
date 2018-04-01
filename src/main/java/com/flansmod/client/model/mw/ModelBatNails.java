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

public class ModelBatNails
extends ModelGun {
    int textureX = 128;
    int textureY = 128;

    public ModelBatNails() {
        this.gunModel = new ModelRendererTurbo[26];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 17, 1, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 33, 1, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 57, 1, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 81, 1, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 105, 1, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 1, 49, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 97, 49, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 17, 57, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 41, 57, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 73, 49, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 57, 57, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[17] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[18] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[19] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[20] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[21] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[22] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[23] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[24] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
        this.gunModel[25] = new ModelRendererTurbo(this, 57, 92, this.textureX, this.textureY);
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
        this.gunModel[12].addShapeBox(1.0f, -95.0f, -3.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[12].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[12].rotateAngleY = -0.36651915f;
        this.gunModel[12].rotateAngleZ = -0.08726646f;
        this.gunModel[13].addShapeBox(0.0f, -91.0f, -9.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[13].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[13].rotateAngleY = 0.80285144f;
        this.gunModel[13].rotateAngleZ = -0.08726646f;
        this.gunModel[14].addShapeBox(0.0f, -87.0f, -9.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[14].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[14].rotateAngleY = 0.34906584f;
        this.gunModel[14].rotateAngleZ = -0.08726646f;
        this.gunModel[15].addShapeBox(-9.0f, -88.0f, 1.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[15].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[15].rotateAngleY = -1.7976891f;
        this.gunModel[15].rotateAngleZ = -0.08726646f;
        this.gunModel[16].addShapeBox(-14.0f, -92.0f, 1.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[16].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[16].rotateAngleY = -2.1991148f;
        this.gunModel[16].rotateAngleZ = -0.08726646f;
        this.gunModel[17].addShapeBox(-7.0f, -87.0f, 5.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[17].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[17].rotateAngleY = -1.2391838f;
        this.gunModel[17].rotateAngleZ = -0.08726646f;
        this.gunModel[18].addShapeBox(-5.0f, -92.0f, -0.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[18].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[18].rotateAngleY = -1.2391838f;
        this.gunModel[18].rotateAngleZ = -0.08726646f;
        this.gunModel[19].addShapeBox(-8.5f, -96.0f, 4.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[19].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[19].rotateAngleY = -1.6057029f;
        this.gunModel[19].rotateAngleZ = -0.08726646f;
        this.gunModel[20].addShapeBox(0.0f, -76.0f, -9.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[20].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[20].rotateAngleY = 0.34906584f;
        this.gunModel[20].rotateAngleZ = -0.08726646f;
        this.gunModel[21].addShapeBox(-6.0f, -75.0f, 2.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[21].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[21].rotateAngleY = -1.2391838f;
        this.gunModel[21].rotateAngleZ = -0.08726646f;
        this.gunModel[22].addShapeBox(1.0f, -84.0f, -3.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[22].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[22].rotateAngleY = -0.36651915f;
        this.gunModel[22].rotateAngleZ = -0.08726646f;
        this.gunModel[23].addShapeBox(0.0f, -81.0f, -9.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[23].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[23].rotateAngleY = 0.80285144f;
        this.gunModel[23].rotateAngleZ = -0.08726646f;
        this.gunModel[24].addShapeBox(-14.0f, -82.0f, 1.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[24].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[24].rotateAngleY = -2.1991148f;
        this.gunModel[24].rotateAngleZ = -0.08726646f;
        this.gunModel[25].addShapeBox(-9.0f, -80.0f, 1.5f, 1, 1, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[25].setRotationPoint(-2.0f, 0.0f, 0.0f);
        this.gunModel[25].rotateAngleY = -1.7976891f;
        this.gunModel[25].rotateAngleZ = -0.08726646f;
        this.translateAll(0.0f, 0.0f, 0.0f);
        this.flipAll();
    }
}

