/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;

public class ModelFireAxe
extends ModelGun {
    int textureX = 128;
    int textureY = 128;

    public ModelFireAxe() {
        this.gunModel = new ModelRendererTurbo[19];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 17, 1, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 33, 1, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 49, 1, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 65, 1, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 81, 1, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 97, 1, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 113, 1, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 33, 17, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 49, 33, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 1, 49, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 49, 25, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 97, 25, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 1, 33, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 49, 17, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 97, 17, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 89, 33, this.textureX, this.textureY);
        this.gunModel[17] = new ModelRendererTurbo(this, 105, 33, this.textureX, this.textureY);
        this.gunModel[18] = new ModelRendererTurbo(this, 89, 41, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 4, 30, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[0].setRotationPoint(-2.0f, -19.0f, 0.0f);
        this.gunModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 4, 23, 1, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[1].setRotationPoint(-2.0f, -42.0f, 0.0f);
        this.gunModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 5, 9, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[2].setRotationPoint(-3.0f, -51.0f, 0.0f);
        this.gunModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 5, 9, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[3].setRotationPoint(-3.0f, -51.0f, 1.0f);
        this.gunModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 4, 23, 1, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[4].setRotationPoint(-2.0f, -42.0f, 1.0f);
        this.gunModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 4, 30, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[5].setRotationPoint(-2.0f, -19.0f, 1.0f);
        this.gunModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 5, 9, 1, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[6].setRotationPoint(-3.0f, -51.0f, -1.0f);
        this.gunModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 4, 23, 1, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[7].setRotationPoint(-2.0f, -42.0f, -1.0f);
        this.gunModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 4, 30, 1, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[8].setRotationPoint(-2.0f, -19.0f, -1.0f);
        this.gunModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 14, 9, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[9].setRotationPoint(-4.0f, -60.0f, -1.0f);
        this.gunModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 18, 2, 3, 0.0f, -1.0f, 0.5f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, -1.0f, 0.5f, -0.2f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f);
        this.gunModel[10].setRotationPoint(-8.0f, -62.0f, -1.0f);
        this.gunModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 6, 4, 3, 0.0f, 0.0f, -2.0f, -0.2f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, -2.0f, -0.2f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[11].setRotationPoint(10.0f, -64.0f, -1.0f);
        this.gunModel[12].addShapeBox(0.0f, 0.0f, 0.0f, 6, 4, 3, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, -2.0f, 0.0f);
        this.gunModel[12].setRotationPoint(10.0f, -53.0f, -1.0f);
        this.gunModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 7, 7, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[13].setRotationPoint(10.0f, -60.0f, -1.0f);
        this.gunModel[14].addShapeBox(0.0f, 0.0f, 0.0f, 4, 4, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[14].setRotationPoint(-2.0f, 11.0f, 1.0f);
        this.gunModel[15].addShapeBox(0.0f, 0.0f, 0.0f, 4, 4, 1, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[15].setRotationPoint(-2.0f, 11.0f, -1.0f);
        this.gunModel[16].addShapeBox(0.0f, 0.0f, 0.0f, 4, 4, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[16].setRotationPoint(-2.0f, 11.0f, 0.0f);
        this.gunModel[17].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[17].setRotationPoint(-3.0f, -63.0f, 0.5f);
        this.gunModel[18].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 1, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[18].setRotationPoint(-3.0f, -63.0f, -0.5f);
        this.translateAll(0.0f, 0.0f, 0.0f);
        this.flipAll();
    }
}

