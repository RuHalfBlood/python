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

public class ModelAT4
extends ModelGun {
    int textureX = 128;
    int textureY = 128;

    public ModelAT4() {
        this.gunModel = new ModelRendererTurbo[25];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 1, 9, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 1, 17, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 73, 1, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 89, 1, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 105, 1, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 73, 9, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 89, 9, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 105, 9, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 73, 17, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 89, 17, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 105, 17, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 1, 25, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 17, 25, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 33, 25, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 65, 25, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 121, 1, this.textureX, this.textureY);
        this.gunModel[17] = new ModelRendererTurbo(this, 81, 1, this.textureX, this.textureY);
        this.gunModel[18] = new ModelRendererTurbo(this, 121, 9, this.textureX, this.textureY);
        this.gunModel[19] = new ModelRendererTurbo(this, 97, 1, this.textureX, this.textureY);
        this.gunModel[20] = new ModelRendererTurbo(this, 113, 1, this.textureX, this.textureY);
        this.gunModel[21] = new ModelRendererTurbo(this, 81, 9, this.textureX, this.textureY);
        this.gunModel[22] = new ModelRendererTurbo(this, 121, 17, this.textureX, this.textureY);
        this.gunModel[23] = new ModelRendererTurbo(this, 97, 9, this.textureX, this.textureY);
        this.gunModel[24] = new ModelRendererTurbo(this, 113, 9, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 32, 1, 2, 0.0f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f, 0.0f, 0.0f, 0.3f);
        this.gunModel[0].setRotationPoint(-19.0f, -3.5f, -1.0f);
        this.gunModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 32, 1, 2, 0.0f, 0.0f, -0.1f, -0.4f, 0.0f, -0.1f, -0.4f, 0.0f, -0.1f, -0.4f, 0.0f, -0.1f, -0.4f, 0.0f, -0.1f, 0.3f, 0.0f, -0.1f, 0.3f, 0.0f, -0.1f, 0.3f, 0.0f, -0.1f, 0.3f);
        this.gunModel[1].setRotationPoint(-19.0f, -4.4f, -1.0f);
        this.gunModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 32, 1, 2, 0.0f, 0.0f, -0.1f, 0.3f, 0.0f, -0.1f, 0.3f, 0.0f, -0.1f, 0.3f, 0.0f, -0.1f, 0.3f, 0.0f, -0.1f, -0.4f, 0.0f, -0.1f, -0.4f, 0.0f, -0.1f, -0.4f, 0.0f, -0.1f, -0.4f);
        this.gunModel[2].setRotationPoint(-19.0f, -2.6f, -1.0f);
        this.gunModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 3, 0.0f, 0.0f, 0.4f, -0.6f, 0.0f, 0.4f, -0.6f, 0.0f, 0.4f, -0.6f, 0.0f, 0.4f, -0.56f, 0.0f, -0.1f, 0.4f, 0.0f, -0.1f, 0.4f, 0.0f, -0.1f, 0.4f, 0.0f, -0.1f, 0.4f);
        this.gunModel[3].setRotationPoint(13.0f, -4.3f, -1.5f);
        this.gunModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 3, 0.0f, 0.0f, 0.0f, 0.4f, 0.0f, 0.0f, 0.4f, 0.0f, 0.0f, 0.4f, 0.0f, 0.0f, 0.4f, 0.0f, 0.0f, 0.4f, 0.0f, 0.0f, 0.4f, 0.0f, 0.0f, 0.4f, 0.0f, 0.0f, 0.4f);
        this.gunModel[4].setRotationPoint(13.0f, -3.4f, -1.5f);
        this.gunModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 3, 0.0f, 0.0f, -0.1f, 0.4f, 0.0f, -0.1f, 0.4f, 0.0f, -0.1f, 0.4f, 0.0f, -0.1f, 0.4f, 0.0f, 0.4f, -0.6f, 0.0f, 0.4f, -0.6f, 0.0f, 0.4f, -0.6f, 0.0f, 0.4f, -0.56f);
        this.gunModel[5].setRotationPoint(13.0f, -2.5f, -1.5f);
        this.gunModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 3, 0.0f, 0.0f, -0.1f, 1.0f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 1.0f, 0.0f, 1.0f, -0.5f, 0.0f, 0.4f, -1.0f, 0.0f, 0.4f, -1.0f, 0.0f, 1.0f, -0.5f);
        this.gunModel[6].setRotationPoint(-19.5f, -2.5f, -1.5f);
        this.gunModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 3, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 1.0f);
        this.gunModel[7].setRotationPoint(-19.5f, -3.4f, -1.5f);
        this.gunModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 3, 0.0f, 0.0f, 1.0f, -0.5f, 0.0f, 0.4f, -1.0f, 0.0f, 0.4f, -1.0f, 0.0f, 1.0f, -0.5f, 0.0f, -0.1f, 1.0f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 1.0f);
        this.gunModel[8].setRotationPoint(-19.5f, -4.3f, -1.5f);
        this.gunModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 3, 0.0f, 0.0f, 1.0f, -0.5f, 0.0f, 1.0f, -0.5f, 0.0f, 1.0f, -0.5f, 0.0f, 1.0f, -0.5f, 0.0f, -0.1f, 1.0f, 0.0f, -0.1f, 1.0f, 0.0f, -0.1f, 1.0f, 0.0f, -0.1f, 1.0f);
        this.gunModel[9].setRotationPoint(-20.5f, -4.3f, -1.5f);
        this.gunModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 3, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f);
        this.gunModel[10].setRotationPoint(-20.5f, -3.4f, -1.5f);
        this.gunModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 3, 0.0f, 0.0f, -0.1f, 1.0f, 0.0f, -0.1f, 1.0f, 0.0f, -0.1f, 1.0f, 0.0f, -0.1f, 1.0f, 0.0f, 1.0f, -0.5f, 0.0f, 1.0f, -0.5f, 0.0f, 1.0f, -0.5f, 0.0f, 1.0f, -0.5f);
        this.gunModel[11].setRotationPoint(-20.5f, -2.5f, -1.5f);
        this.gunModel[12].addShapeBox(0.0f, 0.0f, 0.0f, 4, 1, 2, 0.0f, 0.0f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, 0.0f, -0.1f, -0.3f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f);
        this.gunModel[12].setRotationPoint(0.0f, -4.8f, -1.0f);
        this.gunModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 2, 0.0f, -0.4f, -0.1f, -0.3f, 0.0f, -0.1f, -0.3f, 0.0f, -0.1f, -0.3f, -0.4f, -0.1f, -0.3f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f);
        this.gunModel[13].setRotationPoint(9.0f, -4.6f, -1.0f);
        this.gunModel[14].addShapeBox(0.0f, 0.0f, 0.0f, 13, 1, 1, 0.0f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f);
        this.gunModel[14].setRotationPoint(-17.0f, -3.5f, -2.0f);
        this.gunModel[15].addShapeBox(0.0f, 0.0f, 0.0f, 6, 2, 1, 0.0f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, 0.8f, 0.0f, -0.2f, 0.8f, 0.0f, -0.2f, -1.0f, 0.0f, -0.2f, -1.0f);
        this.gunModel[15].setRotationPoint(-12.0f, -3.3f, 1.0f);
        this.gunModel[16].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.2f, 0.8f, 0.0f, -0.2f, 0.8f);
        this.gunModel[16].setRotationPoint(-5.0f, -3.5f, -2.0f);
        this.gunModel[17].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, -0.1f, -0.3f, -0.2f, -0.1f, -0.3f, -0.2f, -0.1f, -0.3f, -0.2f, -0.1f, -0.3f, -0.2f, -0.1f, -0.3f, -0.2f, -0.1f, -0.3f, -0.2f, -0.1f, -0.3f, -0.2f, -0.1f, -0.3f);
        this.gunModel[17].setRotationPoint(-4.2f, -3.1f, -1.8f);
        this.gunModel[18].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f);
        this.gunModel[18].setRotationPoint(7.4f, -5.45f, -0.15f);
        this.gunModel[19].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.45f, -0.5f, -0.45f, -0.45f, -0.5f, -0.45f, -0.45f, -0.5f, -0.45f, -0.45f, -0.5f, -0.45f, -0.35f, -0.25f, -0.45f, -0.35f, -0.25f, -0.45f, -0.35f, -0.25f, -0.45f, -0.35f, -0.25f);
        this.gunModel[19].setRotationPoint(7.4f, -6.3f, -0.15f);
        this.gunModel[20].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f);
        this.gunModel[20].setRotationPoint(7.4f, -6.0f, -0.35f);
        this.gunModel[21].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f);
        this.gunModel[21].setRotationPoint(7.4f, -6.0f, 0.05f);
        this.gunModel[22].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f);
        this.gunModel[22].setRotationPoint(12.4f, -5.45f, -0.15f);
        this.gunModel[23].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.4f, -0.45f, -0.45f, -0.4f, -0.45f, -0.45f, -0.4f, -0.45f, -0.45f, -0.4f, -0.45f, -0.45f, -0.4f, -0.3f, -0.45f, -0.4f, -0.3f, -0.45f, -0.4f, -0.3f, -0.45f, -0.4f, -0.3f);
        this.gunModel[23].setRotationPoint(12.4f, -5.95f, -0.15f);
        this.gunModel[24].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.3f, -0.15f, -0.4f, -0.3f, -0.15f, -0.4f, -0.3f, -0.15f, -0.4f, -0.3f, -0.15f, -0.4f, -0.3f, -0.15f, -0.4f, -0.3f, -0.15f, -0.4f, -0.3f, -0.15f, -0.4f, -0.3f, -0.15f);
        this.gunModel[24].setRotationPoint(7.3f, -5.45f, -0.15f);
        this.ammoModel = new ModelRendererTurbo[1];
        this.ammoModel[0] = new ModelRendererTurbo(this, 81, 17, this.textureX, this.textureY);
        this.ammoModel[0].addBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.ammoModel[0].setRotationPoint(0.0f, -3.5f, -0.5f);
        this.translateAll(0.0f, -1.0f, -0.4f);
        this.flipAll();
        this.gunSlideDistance = 0.0f;
        this.animationType = EnumAnimationType.NONE;
        this.itemFrameOffset = new Vector3f(0.6875f, 0.0f, 0.0f);
    }
}

