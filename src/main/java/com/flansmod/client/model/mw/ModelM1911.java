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

public class ModelM1911
extends ModelGun {
    int textureX = 64;
    int textureY = 64;

    public ModelM1911() {
        this.gunModel = new ModelRendererTurbo[40];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 9, 1, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 17, 1, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 25, 1, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 33, 1, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 41, 1, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 49, 1, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 57, 1, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 1, 9, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 9, 9, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 17, 9, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 25, 9, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 33, 9, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 41, 9, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 49, 9, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 57, 9, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 1, 17, this.textureX, this.textureY);
        this.gunModel[17] = new ModelRendererTurbo(this, 9, 17, this.textureX, this.textureY);
        this.gunModel[18] = new ModelRendererTurbo(this, 17, 17, this.textureX, this.textureY);
        this.gunModel[19] = new ModelRendererTurbo(this, 25, 17, this.textureX, this.textureY);
        this.gunModel[20] = new ModelRendererTurbo(this, 33, 17, this.textureX, this.textureY);
        this.gunModel[21] = new ModelRendererTurbo(this, 41, 17, this.textureX, this.textureY);
        this.gunModel[22] = new ModelRendererTurbo(this, 9, 41, this.textureX, this.textureY);
        this.gunModel[23] = new ModelRendererTurbo(this, 17, 41, this.textureX, this.textureY);
        this.gunModel[24] = new ModelRendererTurbo(this, 25, 41, this.textureX, this.textureY);
        this.gunModel[25] = new ModelRendererTurbo(this, 33, 41, this.textureX, this.textureY);
        this.gunModel[26] = new ModelRendererTurbo(this, 41, 41, this.textureX, this.textureY);
        this.gunModel[27] = new ModelRendererTurbo(this, 49, 41, this.textureX, this.textureY);
        this.gunModel[28] = new ModelRendererTurbo(this, 57, 41, this.textureX, this.textureY);
        this.gunModel[29] = new ModelRendererTurbo(this, 1, 49, this.textureX, this.textureY);
        this.gunModel[30] = new ModelRendererTurbo(this, 9, 49, this.textureX, this.textureY);
        this.gunModel[31] = new ModelRendererTurbo(this, 17, 49, this.textureX, this.textureY);
        this.gunModel[32] = new ModelRendererTurbo(this, 25, 49, this.textureX, this.textureY);
        this.gunModel[33] = new ModelRendererTurbo(this, 33, 49, this.textureX, this.textureY);
        this.gunModel[34] = new ModelRendererTurbo(this, 41, 49, this.textureX, this.textureY);
        this.gunModel[35] = new ModelRendererTurbo(this, 49, 49, this.textureX, this.textureY);
        this.gunModel[36] = new ModelRendererTurbo(this, 57, 49, this.textureX, this.textureY);
        this.gunModel[37] = new ModelRendererTurbo(this, 1, 57, this.textureX, this.textureY);
        this.gunModel[38] = new ModelRendererTurbo(this, 25, 57, this.textureX, this.textureY);
        this.gunModel[39] = new ModelRendererTurbo(this, 33, 57, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 1, 3, 1, 0.0f, -0.1f, 0.0f, -0.15f, 1.0f, 0.0f, -0.15f, 1.0f, 0.0f, -0.15f, -0.1f, 0.0f, -0.15f, 1.1f, -0.2f, -0.15f, 0.0f, 0.1f, -0.15f, 0.0f, 0.1f, -0.15f, 1.1f, -0.2f, -0.15f);
        this.gunModel[0].setRotationPoint(0.0f, -1.7f, -0.5f);
        this.gunModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.1f, 0.25f, -0.15f, 0.0f, 0.25f, -0.15f, 0.0f, 0.25f, -0.15f, -0.1f, 0.25f, -0.15f, -0.1f, 0.0f, -0.15f, 0.0f, 0.0f, -0.15f, 0.0f, 0.0f, -0.15f, -0.1f, 0.0f, -0.15f);
        this.gunModel[1].setRotationPoint(0.0f, -2.7f, -0.5f);
        this.gunModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.2f, -0.25f, -0.15f, 0.7f, -0.25f, -0.15f, 0.7f, -0.25f, -0.15f, -0.2f, -0.25f, -0.15f, -0.2f, -0.35f, -0.15f, 0.0f, -0.35f, -0.15f, 0.0f, -0.35f, -0.15f, -0.2f, -0.35f, -0.15f);
        this.gunModel[2].setRotationPoint(1.8f, -3.2f, -0.5f);
        this.gunModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.25f, -0.15f, -0.2f, -0.25f, -0.15f, -0.2f, -0.25f, -0.15f, -0.4f, -0.25f, -0.15f, -0.4f, -0.35f, -0.15f, -0.2f, -0.75f, -0.15f, -0.2f, -0.75f, -0.15f, -0.4f, -0.35f, -0.15f);
        this.gunModel[3].setRotationPoint(1.6f, -2.8f, -0.5f);
        this.gunModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.4f, -0.4f, -0.15f, 0.0f, -0.4f, -0.15f, 0.0f, -0.4f, -0.15f, -0.4f, -0.4f, -0.15f, -0.15f, -0.4f, -0.15f, 0.0f, -0.4f, -0.15f, 0.0f, -0.4f, -0.15f, -0.15f, -0.4f, -0.15f);
        this.gunModel[4].setRotationPoint(3.9f, -3.35f, -0.5f);
        this.gunModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.25f, -0.5f, -0.15f, 0.0f, -0.5f, -0.15f, 0.0f, -0.5f, -0.15f, -0.25f, -0.5f, -0.15f, -0.25f, -0.1f, -0.3f, 0.0f, -0.1f, -0.3f, 0.0f, -0.1f, -0.3f, -0.25f, -0.1f, -0.3f);
        this.gunModel[5].setRotationPoint(3.9f, -3.25f, -0.5f);
        this.gunModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.35f, -0.25f, -0.15f, -0.25f, -0.25f, -0.15f, -0.25f, -0.25f, -0.15f, -0.35f, -0.25f, -0.15f, -0.2f, -0.35f, -0.15f, -0.25f, -0.75f, -0.15f, -0.25f, -0.75f, -0.15f, -0.2f, -0.35f, -0.15f);
        this.gunModel[6].setRotationPoint(1.6f, -1.75f, -0.5f);
        this.gunModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.2f, -0.15f, -0.25f, -0.7f, -0.15f, -0.25f, -0.7f, -0.15f, -0.4f, -0.2f, -0.15f, -0.4f, -0.3f, -0.15f, -0.25f, -0.3f, -0.15f, -0.25f, -0.3f, -0.15f, -0.4f, -0.3f, -0.15f);
        this.gunModel[7].setRotationPoint(1.6f, -2.35f, -0.5f);
        this.gunModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.42f, -0.15f, -0.3f, -0.42f, -0.15f, -0.3f, -0.42f, -0.15f, -0.3f, -0.42f, -0.15f, -0.3f, -0.42f, -0.15f, -0.3f, -0.42f, -0.15f, -0.3f, -0.42f, -0.15f, -0.3f, -0.42f, -0.15f);
        this.gunModel[8].setRotationPoint(1.65f, -2.07f, -0.5f);
        this.gunModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.43f, -0.45f, -0.15f, -0.35f, -0.25f, -0.15f, -0.35f, -0.25f, -0.15f, -0.43f, -0.45f, -0.15f, -0.43f, -0.55f, -0.15f, -0.35f, -0.25f, -0.15f, -0.35f, -0.25f, -0.15f, -0.43f, -0.55f, -0.15f);
        this.gunModel[9].setRotationPoint(1.57f, -2.6f, -0.5f);
        this.gunModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.7f, -0.15f, -0.35f, -0.5f, -0.15f, -0.35f, -0.5f, -0.15f, -0.3f, -0.7f, -0.15f, -0.3f, 0.0f, -0.3f, -0.35f, -0.1f, -0.3f, -0.35f, -0.1f, -0.3f, -0.3f, 0.0f, -0.3f);
        this.gunModel[10].setRotationPoint(3.5f, -3.25f, -0.5f);
        this.gunModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.4f, -0.35f, -0.15f, 0.0f, -0.35f, -0.15f, 0.0f, -0.35f, -0.15f, 0.4f, -0.35f, -0.15f, 0.2f, -0.35f, -0.3f, 0.0f, -0.35f, -0.3f, 0.0f, -0.35f, -0.3f, 0.2f, -0.35f, -0.3f);
        this.gunModel[11].setRotationPoint(2.8f, -2.9f, -0.5f);
        this.gunModel[12].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.52f, -0.55f, -0.15f, -0.3f, -0.35f, -0.15f, -0.3f, -0.35f, -0.15f, -0.52f, -0.55f, -0.15f, -0.52f, 0.0f, -0.15f, -0.1f, -0.35f, -0.3f, -0.1f, -0.35f, -0.3f, -0.52f, 0.0f, -0.15f);
        this.gunModel[12].setRotationPoint(1.7f, -2.9f, -0.5f);
        this.gunModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.3f, -0.3f, -0.45f, -0.3f, -0.3f, -0.45f, -0.3f, -0.3f, -0.45f, -0.3f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f);
        this.gunModel[13].setRotationPoint(3.3f, -2.4f, -0.5f);
        this.gunModel[14].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, -0.4f, -0.3f, -0.3f, -0.4f, -0.3f, -0.3f, -0.4f, -0.3f, -0.2f, -0.4f, -0.3f, -0.45f, -0.4f, -0.3f, -0.45f, -0.4f, -0.3f, -0.45f, -0.4f, -0.3f, -0.45f, -0.4f, -0.3f);
        this.gunModel[14].setRotationPoint(3.3f, -2.7f, -0.5f);
        this.gunModel[15].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.45f, -0.3f, 0.0f, -0.45f, -0.3f, 0.0f, -0.45f, -0.3f, 0.0f, -0.45f, -0.3f, 0.0f, -0.45f, -0.3f, 0.0f, -0.45f, -0.3f, 0.0f, -0.45f, -0.3f, 0.0f, -0.45f, -0.3f);
        this.gunModel[15].setRotationPoint(1.5f, -1.8f, -0.5f);
        this.gunModel[16].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.3f, -0.3f, -0.43f, -0.3f, -0.3f, -0.43f, -0.3f, -0.3f, -0.45f, -0.3f, -0.3f, -0.05f, -0.3f, -0.3f, -0.8f, -0.3f, -0.3f, -0.8f, -0.3f, -0.3f, -0.05f, -0.3f, -0.3f);
        this.gunModel[16].setRotationPoint(3.3f, -1.95f, -0.5f);
        this.gunModel[17].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, 0.2f, -0.3f, 0.0f, -0.45f, -0.3f, 0.0f, -0.45f, -0.3f, 0.0f, 0.2f, -0.3f, 0.0f, -0.45f, -0.3f, 0.0f, -0.45f, -0.3f, 0.0f, -0.45f, -0.3f, 0.0f, -0.45f, -0.3f);
        this.gunModel[17].setRotationPoint(1.6f, -1.8f, -0.5f);
        this.gunModel[18].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.35f, 0.0f, -0.15f, -0.35f, 0.0f, -0.15f, -0.35f, 0.0f, -0.15f, -0.35f, 0.0f, -0.15f, -0.35f, -0.3f, -0.15f, -0.35f, 0.0f, -0.15f, -0.35f, 0.0f, -0.15f, -0.35f, -0.3f, -0.15f);
        this.gunModel[18].setRotationPoint(-0.55f, -2.95f, -0.5f);
        this.gunModel[19].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.25f, -0.15f, -0.4f, 0.0f, -0.15f, -0.4f, 0.0f, -0.15f, -0.4f, -0.25f, -0.15f, -0.4f, -0.4f, -0.15f, -0.4f, -0.3f, -0.15f, -0.4f, -0.3f, -0.15f, -0.4f, -0.4f, -0.15f);
        this.gunModel[19].setRotationPoint(-0.8f, -2.95f, -0.5f);
        this.gunModel[20].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.35f, -0.15f, -0.4f, -0.3f, -0.15f, -0.4f, -0.3f, -0.15f, -0.4f, -0.35f, -0.15f, -0.4f, -0.35f, -0.15f, -0.4f, -0.35f, -0.15f, -0.4f, -0.35f, -0.15f, -0.4f, -0.35f, -0.15f);
        this.gunModel[20].setRotationPoint(-1.0f, -3.0f, -0.5f);
        this.gunModel[21].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.45f, -0.15f, -0.45f, -0.35f, -0.15f, -0.45f, -0.35f, -0.15f, -0.45f, -0.45f, -0.15f, -0.45f, -0.45f, -0.15f, -0.45f, -0.35f, -0.15f, -0.45f, -0.35f, -0.15f, -0.45f, -0.45f, -0.15f);
        this.gunModel[21].setRotationPoint(-1.15f, -3.0f, -0.5f);
        this.gunModel[22].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.3f, -0.15f, 0.1f, -0.3f, -0.15f, 0.1f, -0.3f, -0.15f, 0.0f, -0.3f, -0.15f, 0.0f, -0.3f, -0.2f, 0.1f, -0.3f, -0.2f, 0.1f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f);
        this.gunModel[22].setRotationPoint(5.9f, -3.45f, -0.5f);
        this.gunModel[23].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.35f, -0.2f, 0.1f, -0.35f, -0.2f, 0.1f, -0.35f, -0.2f, 0.0f, -0.35f, -0.2f, 0.0f, -0.4f, -0.35f, 0.1f, -0.4f, -0.35f, 0.1f, -0.4f, -0.35f, 0.0f, -0.4f, -0.35f);
        this.gunModel[23].setRotationPoint(5.9f, -3.1f, -0.5f);
        this.gunModel[24].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.4f, -0.35f, -0.4f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.4f, -0.4f, -0.35f, -0.4f, -0.5f, -0.35f, -0.4f, -0.4f, -0.35f, -0.4f, -0.4f, -0.35f, -0.4f, -0.5f, -0.35f);
        this.gunModel[24].setRotationPoint(-1.4f, -2.9f, -0.5f);
        this.gunModel[25].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.4f, -0.3f, -0.35f, -0.4f, -0.2f, -0.35f, -0.4f, -0.2f, -0.35f, -0.4f, -0.3f, -0.35f, -0.4f, -0.4f, -0.35f, -0.4f, -0.4f, -0.35f, -0.4f, -0.4f, -0.35f, -0.4f, -0.4f, -0.35f);
        this.gunModel[25].setRotationPoint(-1.2f, -2.9f, -0.5f);
        this.gunModel[26].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.35f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.35f, -0.2f, -0.25f);
        this.gunModel[26].setRotationPoint(-0.5f, -2.3f, -0.5f);
        this.gunModel[27].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.2f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, 0.2f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f);
        this.gunModel[27].setRotationPoint(-0.5f, -2.7f, -0.5f);
        this.gunModel[28].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.35f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.35f, -0.2f, -0.25f, -0.1f, -0.3f, -0.25f, -0.4f, -0.3f, -0.25f, -0.4f, -0.3f, -0.25f, -0.1f, -0.3f, -0.25f);
        this.gunModel[28].setRotationPoint(-0.5f, -1.7f, -0.5f);
        this.gunModel[29].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, -0.9f, 0.2f, -0.25f, 0.1f, 0.2f, -0.25f, 0.1f, 0.2f, -0.25f, -0.9f, 0.2f, -0.25f, -0.1f, -0.5f, -0.25f, -0.4f, -0.15f, -0.5f, -0.4f, -0.15f, -0.5f, -0.1f, -0.5f, -0.25f);
        this.gunModel[29].setRotationPoint(-1.3f, -0.7f, -0.5f);
        this.gunModel[30].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.25f, -0.35f, -0.3f, -0.25f, -0.35f, -0.3f, -0.25f, -0.35f, -0.3f, -0.25f, -0.35f);
        this.gunModel[30].setRotationPoint(1.9f, -2.4f, -0.5f);
        this.gunModel[31].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.4f, -0.35f, 0.0f, -0.4f, -0.35f, 0.0f, -0.4f, -0.35f, -0.3f, -0.4f, -0.35f, -0.3f, -0.4f, -0.35f, -0.3f, -0.4f, -0.35f, -0.3f, -0.4f, -0.35f, -0.3f, -0.4f, -0.35f);
        this.gunModel[31].setRotationPoint(1.9f, -2.7f, -0.5f);
        this.gunModel[32].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.4f, -0.35f, -0.1f, -0.4f, -0.35f, -0.1f, -0.4f, -0.35f, -0.3f, -0.4f, -0.35f);
        this.gunModel[32].setRotationPoint(1.9f, -1.95f, -0.5f);
        this.gunModel[33].addShapeBox(0.0f, 0.0f, 0.0f, 1, 4, 1, 0.0f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.9f, -0.4f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.9f, -0.4f, 0.0f);
        this.gunModel[33].setRotationPoint(0.1f, -2.7f, -0.5f);
        this.gunModel[34].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, -0.2f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, -0.2f, -0.2f, -0.3f, -0.4f, -0.35f, -0.3f, 0.0f, -0.85f, -0.3f, 0.0f, -0.85f, -0.3f, -0.4f, -0.35f, -0.3f);
        this.gunModel[34].setRotationPoint(-0.4f, -2.9f, -0.25f);
        this.gunModel[35].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, -0.4f, -0.3f, -0.2f, -0.2f, -0.3f, -0.2f, -0.2f, -0.3f, -0.2f, -0.4f, -0.3f, -0.2f, -0.5f, -0.3f, -0.2f, -0.7f, -0.3f, -0.2f, -0.7f, -0.3f, -0.2f, -0.5f, -0.3f);
        this.gunModel[35].setRotationPoint(-0.3f, -3.05f, -0.15f);
        this.gunModel[36].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.2f, -0.42f, -0.3f, -0.4f, -0.42f, -0.3f, -0.4f, -0.42f, -0.3f, -0.2f, -0.42f, -0.3f, -0.2f, -0.42f, -0.3f, -0.4f, -0.42f, -0.3f, -0.4f, -0.42f, -0.3f, -0.2f, -0.42f, -0.3f);
        this.gunModel[36].setRotationPoint(0.4f, -3.35f, -0.25f);
        this.gunModel[37].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, -0.42f, -0.3f, 0.0f, -0.42f, -0.3f, 0.0f, -0.42f, -0.3f, -0.2f, -0.42f, -0.3f, -0.2f, -0.42f, -0.3f, 0.0f, -0.35f, -0.3f, 0.0f, -0.35f, -0.3f, -0.2f, -0.42f, -0.3f);
        this.gunModel[37].setRotationPoint(1.9f, -3.3f, -0.3f);
        this.gunModel[38].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.3f, -0.5f, -0.35f, -0.4f, -0.1f, -0.35f, -0.4f, -0.1f, -0.35f, -0.3f, -0.5f, -0.35f);
        this.gunModel[38].setRotationPoint(-0.6f, -3.9f, -0.5f);
        this.gunModel[39].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.3f, -0.35f, -0.3f, -0.4f, -0.35f, -0.3f, -0.4f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.6f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.6f, -0.35f);
        this.gunModel[39].setRotationPoint(-0.9f, -4.0f, -0.5f);
        this.ammoModel = new ModelRendererTurbo[1];
        this.ammoModel[0] = new ModelRendererTurbo(this, 5, 41, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(0.1f, -2.35f, -0.5f, 1, 4, 1, 0.0f, -0.5f, 0.0f, -0.2f, 1.0f, 0.0f, -0.2f, 1.0f, 0.0f, -0.2f, -0.5f, 0.0f, -0.2f, 0.9f, -0.4f, -0.2f, -0.2f, -0.15f, -0.2f, -0.2f, -0.15f, -0.2f, 0.9f, -0.4f, -0.2f);
        this.ammoModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.slideModel = new ModelRendererTurbo[26];
        this.slideModel[0] = new ModelRendererTurbo(this, 49, 17, this.textureX, this.textureY);
        this.slideModel[1] = new ModelRendererTurbo(this, 57, 17, this.textureX, this.textureY);
        this.slideModel[2] = new ModelRendererTurbo(this, 1, 25, this.textureX, this.textureY);
        this.slideModel[3] = new ModelRendererTurbo(this, 9, 25, this.textureX, this.textureY);
        this.slideModel[4] = new ModelRendererTurbo(this, 17, 25, this.textureX, this.textureY);
        this.slideModel[5] = new ModelRendererTurbo(this, 25, 25, this.textureX, this.textureY);
        this.slideModel[6] = new ModelRendererTurbo(this, 33, 25, this.textureX, this.textureY);
        this.slideModel[7] = new ModelRendererTurbo(this, 49, 25, this.textureX, this.textureY);
        this.slideModel[8] = new ModelRendererTurbo(this, 57, 25, this.textureX, this.textureY);
        this.slideModel[9] = new ModelRendererTurbo(this, 1, 33, this.textureX, this.textureY);
        this.slideModel[10] = new ModelRendererTurbo(this, 9, 33, this.textureX, this.textureY);
        this.slideModel[11] = new ModelRendererTurbo(this, 17, 33, this.textureX, this.textureY);
        this.slideModel[12] = new ModelRendererTurbo(this, 25, 33, this.textureX, this.textureY);
        this.slideModel[13] = new ModelRendererTurbo(this, 33, 33, this.textureX, this.textureY);
        this.slideModel[14] = new ModelRendererTurbo(this, 41, 33, this.textureX, this.textureY);
        this.slideModel[15] = new ModelRendererTurbo(this, 41, 33, this.textureX, this.textureY);
        this.slideModel[16] = new ModelRendererTurbo(this, 57, 33, this.textureX, this.textureY);
        this.slideModel[17] = new ModelRendererTurbo(this, 1, 41, this.textureX, this.textureY);
        this.slideModel[18] = new ModelRendererTurbo(this, 9, 57, this.textureX, this.textureY);
        this.slideModel[19] = new ModelRendererTurbo(this, 17, 57, this.textureX, this.textureY);
        this.slideModel[20] = new ModelRendererTurbo(this, 41, 57, this.textureX, this.textureY);
        this.slideModel[21] = new ModelRendererTurbo(this, 49, 57, this.textureX, this.textureY);
        this.slideModel[22] = new ModelRendererTurbo(this, 57, 57, this.textureX, this.textureY);
        this.slideModel[23] = new ModelRendererTurbo(this, 41, 33, this.textureX, this.textureY);
        this.slideModel[24] = new ModelRendererTurbo(this, 49, 33, this.textureX, this.textureY);
        this.slideModel[25] = new ModelRendererTurbo(this, 57, 33, this.textureX, this.textureY);
        this.slideModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.15f, -0.1f, 0.0f, -0.15f, -0.1f, 0.0f, -0.15f, -0.1f, 0.0f, -0.15f, -0.1f, 0.0f, -0.15f, -0.1f, 0.0f, -0.15f, -0.1f, 0.0f, -0.15f, -0.1f, 0.0f, -0.15f, -0.1f);
        this.slideModel[0].setRotationPoint(3.9f, -3.8f, -0.5f);
        this.slideModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.3f, -0.1f, 0.0f, -0.3f, -0.1f, 0.0f, -0.3f, -0.1f, 0.0f, -0.3f, -0.1f, 0.0f, -0.3f, -0.1f, 0.0f, -0.3f, -0.1f, 0.0f, -0.3f, -0.1f, 0.0f, -0.3f, -0.1f);
        this.slideModel[1].setRotationPoint(1.9f, -3.65f, -0.5f);
        this.slideModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, 0.0f, -0.3f, -0.1f, 0.0f, -0.4f, -0.1f, 0.0f, -0.4f, -0.1f, 0.0f, -0.4f, -0.1f, 0.0f, -0.4f, -0.1f);
        this.slideModel[2].setRotationPoint(1.9f, -3.95f, -0.5f);
        this.slideModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.1f, -0.3f, -0.4f, 0.0f, -0.3f, -0.4f, 0.0f, -0.3f, -0.45f, 0.1f, -0.3f, -0.45f, 0.0f, -0.4f, -0.4f, 0.0f, -0.4f, -0.4f, 0.0f, -0.4f, -0.45f, 0.0f, -0.4f, -0.45f);
        this.slideModel[3].setRotationPoint(2.9f, -3.95f, -0.15f);
        this.slideModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.25f, -0.1f, 0.1f, -0.25f, -0.1f, 0.1f, -0.25f, -0.1f, 0.0f, -0.25f, -0.1f, 0.0f, -0.25f, -0.1f, 0.1f, -0.25f, -0.1f, 0.1f, -0.25f, -0.1f, 0.0f, -0.25f, -0.1f);
        this.slideModel[4].setRotationPoint(5.9f, -3.9f, -0.5f);
        this.slideModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.4f, -0.1f, -0.3f, -0.4f, -0.1f, -0.3f, -0.4f, -0.1f, -0.3f, -0.4f, -0.1f, -0.3f, -0.4f, -0.1f, -0.3f, -0.6f, -0.1f, -0.3f, -0.6f, -0.1f, -0.3f, -0.4f, -0.1f);
        this.slideModel[5].setRotationPoint(5.6f, -3.55f, -0.5f);
        this.slideModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 1, 0.0f, 0.0f, -0.4f, -0.3f, 0.1f, -0.4f, -0.3f, 0.1f, -0.4f, -0.3f, 0.0f, -0.4f, -0.3f, 0.0f, -0.4f, -0.1f, 0.1f, -0.4f, -0.1f, 0.1f, -0.4f, -0.1f, 0.0f, -0.4f, -0.1f);
        this.slideModel[6].setRotationPoint(3.9f, -4.25f, -0.5f);
        this.slideModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.4f, -0.3f, 0.65f, -0.4f, -0.3f, 0.65f, -0.4f, -0.3f, 0.0f, -0.4f, -0.3f, 0.15f, -0.4f, -0.1f, 0.65f, -0.4f, -0.1f, 0.65f, -0.4f, -0.1f, 0.15f, -0.4f, -0.1f);
        this.slideModel[7].setRotationPoint(0.15f, -4.25f, -0.5f);
        this.slideModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.1f, -0.4f, -0.5f, 0.0f, -0.4f, -0.5f, 0.0f, -0.4f, -0.3f, 0.1f, -0.4f, -0.3f, 0.1f, -0.4f, -0.5f, 0.0f, -0.4f, -0.5f, 0.0f, -0.4f, -0.1f, 0.1f, -0.4f, -0.1f);
        this.slideModel[8].setRotationPoint(2.9f, -4.25f, -0.5f);
        this.slideModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.1f, 0.0f, -0.1f, -0.3f, 0.0f, -0.1f, -0.3f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, 0.1f, -0.3f, -0.1f, -0.3f, -0.3f, -0.1f, -0.3f, -0.3f, -0.1f, 0.1f, -0.3f, -0.1f);
        this.slideModel[9].setRotationPoint(-0.1f, -3.65f, -0.5f);
        this.slideModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.1f, 0.0f, -0.12f, -0.1f, 0.0f, -0.12f, -0.1f, 0.0f, -0.12f, -0.1f, 0.0f, -0.12f, -0.1f, -0.3f, -0.12f, -0.1f, -0.3f, -0.12f, -0.1f, -0.3f, -0.12f, -0.1f, -0.3f, -0.12f);
        this.slideModel[10].setRotationPoint(0.1f, -3.65f, -0.5f);
        this.slideModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f);
        this.slideModel[11].setRotationPoint(0.2f, -3.65f, -0.5f);
        this.slideModel[12].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f);
        this.slideModel[12].setRotationPoint(0.4f, -3.65f, -0.5f);
        this.slideModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f);
        this.slideModel[13].setRotationPoint(0.6f, -3.65f, -0.5f);
        this.slideModel[14].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f);
        this.slideModel[14].setRotationPoint(0.8f, -3.65f, -0.5f);
        this.slideModel[15].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f);
        this.slideModel[15].setRotationPoint(1.0f, -3.65f, -0.5f);
        this.slideModel[16].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, 0.0f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f, -0.45f, -0.3f, -0.1f);
        this.slideModel[16].setRotationPoint(1.2f, -3.65f, -0.5f);
        this.slideModel[17].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.05f, -0.4f, -0.4f, 0.0f, -0.4f, -0.4f, 0.0f, -0.4f, -0.45f, 0.05f, -0.4f, -0.45f, 0.0f, -0.4f, -0.4f, 0.0f, -0.4f, -0.4f, 0.0f, -0.4f, -0.45f, 0.0f, -0.4f, -0.45f);
        this.slideModel[17].setRotationPoint(2.9f, -3.95f, -0.8f);
        this.slideModel[18].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.4f, -0.35f, 0.1f, -0.4f, -0.35f, 0.1f, -0.4f, -0.35f, 0.0f, -0.4f, -0.35f, 0.0f, -0.4f, -0.2f, 0.1f, -0.4f, -0.2f, 0.1f, -0.4f, -0.2f, 0.0f, -0.4f, -0.2f);
        this.slideModel[18].setRotationPoint(2.8f, -4.2f, -0.55f);
        this.slideModel[19].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.4f, -0.2f, 0.1f, -0.4f, -0.2f, 0.1f, -0.4f, -0.2f, 0.0f, -0.4f, -0.2f, 0.0f, -0.4f, -0.2f, 0.1f, -0.4f, -0.2f, 0.1f, -0.4f, -0.2f, 0.0f, -0.4f, -0.2f);
        this.slideModel[19].setRotationPoint(2.8f, -4.0f, -0.55f);
        this.slideModel[20].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.45f, -0.45f, -0.4f, -0.44f, -0.45f, -0.4f, -0.44f, -0.45f, -0.4f, -0.45f, -0.45f, -0.2f, -0.4f, -0.45f, -0.4f, -0.4f, -0.45f, -0.4f, -0.4f, -0.45f, -0.2f, -0.4f, -0.45f);
        this.slideModel[20].setRotationPoint(6.1f, -4.45f, -0.5f);
        this.slideModel[21].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.45f, -0.4f, -0.45f, -0.44f, -0.4f, -0.45f, -0.44f, -0.4f, -0.45f, -0.45f, -0.4f, -0.45f, 0.0f, -0.4f, -0.45f, 0.0f, -0.4f, -0.45f, 0.0f, -0.4f, -0.45f, 0.0f, -0.4f);
        this.slideModel[21].setRotationPoint(0.1f, -4.5f, -0.3f);
        this.slideModel[22].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.45f, -0.4f, -0.45f, -0.44f, -0.4f, -0.45f, -0.44f, -0.4f, -0.45f, -0.45f, -0.4f, -0.45f, 0.0f, -0.4f, -0.45f, 0.0f, -0.4f, -0.45f, 0.0f, -0.4f, -0.45f, 0.0f, -0.4f);
        this.slideModel[22].setRotationPoint(0.1f, -4.5f, -0.67f);
        this.slideModel[23].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.4f, -0.25f, -0.45f, -0.4f, -0.22f, -0.45f, -0.4f, -0.22f, -0.45f, -0.4f, -0.22f, -0.4f, -0.4f, -0.22f, -0.4f, -0.4f, -0.22f, -0.4f, -0.4f, -0.22f, -0.4f, -0.4f, -0.22f);
        this.slideModel[23].setRotationPoint(0.1f, -4.3f, -0.5f);
        this.slideModel[24].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.1f, -0.2f, -0.2f, -0.1f, -0.2f, -0.2f, -0.1f, -0.2f, -0.2f, -0.1f, -0.2f, -0.2f, -0.1f, -0.2f, -0.2f, -0.1f, -0.2f, -0.2f, -0.1f, -0.2f, -0.2f, -0.1f, -0.2f, -0.2f);
        this.slideModel[24].setRotationPoint(6.2f, -3.9f, -0.5f);
        this.slideModel[25].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.1f, -0.25f, -0.25f, -0.1f, -0.25f, -0.25f, -0.1f, -0.25f, -0.25f, -0.1f, -0.25f, -0.25f, -0.1f, -0.25f, -0.4f, -0.1f, -0.25f, -0.4f, -0.1f, -0.25f, -0.4f, -0.1f, -0.25f, -0.4f);
        this.slideModel[25].setRotationPoint(6.15f, -3.4f, -0.5f);
        this.gunSlideDistance = 0.08f;
        this.animationType = EnumAnimationType.PISTOL_CLIP;
        this.scopeIsOnSlide = true;
        this.barrelAttachPoint = new Vector3f(0.4375f, 0.3f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.28125f, 0.3125f, 0.0f);
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
        this.translateAll(2.0f, -1.5f, 0.0f);
        this.flipAll();
    }
}

