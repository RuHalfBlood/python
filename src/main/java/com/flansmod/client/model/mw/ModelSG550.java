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

public class ModelSG550
extends ModelGun {
    int textureX = 64;
    int textureY = 64;

    public ModelSG550() {
        this.gunModel = new ModelRendererTurbo[54];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 9, 1, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 25, 1, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 33, 1, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 41, 1, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 49, 1, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 1, 9, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 9, 9, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 25, 9, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 49, 9, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 57, 9, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 1, 17, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 25, 17, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 1, 25, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 25, 25, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 49, 17, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 57, 17, this.textureX, this.textureY);
        this.gunModel[17] = new ModelRendererTurbo(this, 49, 25, this.textureX, this.textureY);
        this.gunModel[18] = new ModelRendererTurbo(this, 57, 25, this.textureX, this.textureY);
        this.gunModel[19] = new ModelRendererTurbo(this, 9, 33, this.textureX, this.textureY);
        this.gunModel[20] = new ModelRendererTurbo(this, 9, 33, this.textureX, this.textureY);
        this.gunModel[21] = new ModelRendererTurbo(this, 9, 33, this.textureX, this.textureY);
        this.gunModel[22] = new ModelRendererTurbo(this, 25, 33, this.textureX, this.textureY);
        this.gunModel[23] = new ModelRendererTurbo(this, 41, 33, this.textureX, this.textureY);
        this.gunModel[24] = new ModelRendererTurbo(this, 1, 41, this.textureX, this.textureY);
        this.gunModel[25] = new ModelRendererTurbo(this, 57, 33, this.textureX, this.textureY);
        this.gunModel[26] = new ModelRendererTurbo(this, 17, 41, this.textureX, this.textureY);
        this.gunModel[27] = new ModelRendererTurbo(this, 25, 41, this.textureX, this.textureY);
        this.gunModel[28] = new ModelRendererTurbo(this, 41, 41, this.textureX, this.textureY);
        this.gunModel[29] = new ModelRendererTurbo(this, 49, 41, this.textureX, this.textureY);
        this.gunModel[30] = new ModelRendererTurbo(this, 57, 41, this.textureX, this.textureY);
        this.gunModel[31] = new ModelRendererTurbo(this, 17, 49, this.textureX, this.textureY);
        this.gunModel[32] = new ModelRendererTurbo(this, 25, 49, this.textureX, this.textureY);
        this.gunModel[33] = new ModelRendererTurbo(this, 33, 49, this.textureX, this.textureY);
        this.gunModel[34] = new ModelRendererTurbo(this, 41, 49, this.textureX, this.textureY);
        this.gunModel[35] = new ModelRendererTurbo(this, 49, 49, this.textureX, this.textureY);
        this.gunModel[36] = new ModelRendererTurbo(this, 57, 49, this.textureX, this.textureY);
        this.gunModel[37] = new ModelRendererTurbo(this, 1, 57, this.textureX, this.textureY);
        this.gunModel[38] = new ModelRendererTurbo(this, 9, 57, this.textureX, this.textureY);
        this.gunModel[39] = new ModelRendererTurbo(this, 17, 57, this.textureX, this.textureY);
        this.gunModel[40] = new ModelRendererTurbo(this, 25, 57, this.textureX, this.textureY);
        this.gunModel[41] = new ModelRendererTurbo(this, 33, 57, this.textureX, this.textureY);
        this.gunModel[42] = new ModelRendererTurbo(this, 49, 57, this.textureX, this.textureY);
        this.gunModel[43] = new ModelRendererTurbo(this, 57, 57, this.textureX, this.textureY);
        this.gunModel[44] = new ModelRendererTurbo(this, 1, 33, this.textureX, this.textureY);
        this.gunModel[45] = new ModelRendererTurbo(this, 18, 36, this.textureX, this.textureY);
        this.gunModel[46] = new ModelRendererTurbo(this, 17, 33, this.textureX, this.textureY);
        this.gunModel[47] = new ModelRendererTurbo(this, 24, 36, this.textureX, this.textureY);
        this.gunModel[48] = new ModelRendererTurbo(this, 30, 36, this.textureX, this.textureY);
        this.gunModel[49] = new ModelRendererTurbo(this, 37, 36, this.textureX, this.textureY);
        this.gunModel[50] = new ModelRendererTurbo(this, 45, 36, this.textureX, this.textureY);
        this.gunModel[51] = new ModelRendererTurbo(this, 57, 17, this.textureX, this.textureY);
        this.gunModel[52] = new ModelRendererTurbo(this, 57, 61, this.textureX, this.textureY);
        this.gunModel[53] = new ModelRendererTurbo(this, 43, 57, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.6f, 0.0f, -0.12f, 0.0f, 0.0f, -0.12f, 0.0f, 0.0f, -0.12f, 0.6f, 0.0f, -0.12f, 0.6f, 0.0f, -0.12f, 0.0f, 0.0f, -0.12f, 0.0f, 0.0f, -0.12f, 0.6f, 0.0f, -0.12f);
        this.gunModel[0].setRotationPoint(0.8f, -2.0f, -0.5f);
        this.gunModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 1, 0.0f, 0.0f, 0.1f, -0.1f, 0.1f, 0.1f, -0.1f, 0.1f, 0.1f, -0.1f, 0.0f, 0.1f, -0.1f, 0.0f, 0.0f, -0.1f, 0.1f, 0.0f, -0.1f, 0.1f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f);
        this.gunModel[1].setRotationPoint(1.0f, -3.0f, -0.5f);
        this.gunModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, 0.1f, -0.1f, -0.2f, 0.1f, -0.1f, -0.2f, 0.1f, -0.1f, -0.2f, 0.1f, -0.1f, -0.2f, -0.2f, -0.1f, -0.2f, 0.0f, -0.1f, -0.2f, 0.0f, -0.1f, -0.2f, -0.2f, -0.1f);
        this.gunModel[2].setRotationPoint(0.2f, -3.0f, -0.5f);
        this.gunModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, 0.1f, -0.1f, 0.0f, 0.1f, -0.1f, 0.0f, 0.1f, -0.1f, 0.0f, 0.1f, -0.1f, 0.0f, -0.2f, -0.1f, 0.0f, -0.2f, -0.1f, 0.0f, -0.2f, -0.1f, 0.0f, -0.2f, -0.1f);
        this.gunModel[3].setRotationPoint(-0.6f, -3.0f, -0.5f);
        this.gunModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, 0.1f, -0.1f, 0.3f, 0.1f, -0.1f, 0.3f, 0.1f, -0.1f, 0.0f, 0.1f, -0.1f, 0.0f, 0.0f, -0.1f, 0.3f, -0.4f, -0.1f, 0.3f, -0.4f, -0.1f, 0.0f, 0.0f, -0.1f);
        this.gunModel[4].setRotationPoint(6.1f, -3.0f, -0.5f);
        this.gunModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.25f, -0.05f, 1.0f, -0.25f, -0.05f, 1.0f, -0.25f, -0.05f, 0.0f, -0.25f, -0.05f, 0.0f, -0.25f, -0.05f, 1.0f, -0.25f, -0.05f, 1.0f, -0.25f, -0.05f, 0.0f, -0.25f, -0.05f);
        this.gunModel[5].setRotationPoint(0.4f, -3.85f, -0.5f);
        this.gunModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.25f, -0.08f, 0.8f, -0.25f, -0.08f, 0.8f, -0.25f, -0.08f, 0.0f, -0.25f, -0.08f, 0.0f, -0.25f, -0.08f, 0.8f, -0.25f, -0.08f, 0.8f, -0.25f, -0.08f, 0.0f, -0.25f, -0.08f);
        this.gunModel[6].setRotationPoint(-0.6f, -3.85f, -0.5f);
        this.gunModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.25f, -0.25f, 1.0f, -0.25f, -0.25f, 1.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.2f, -0.1f, 1.0f, -0.2f, -0.1f, 1.0f, -0.2f, -0.1f, 0.0f, -0.2f, -0.1f);
        this.gunModel[7].setRotationPoint(0.4f, -4.4f, -0.5f);
        this.gunModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 7, 1, 1, 0.0f, 0.0f, -0.4f, -0.45f, 1.0f, -0.4f, -0.45f, 1.0f, -0.4f, -0.45f, 0.0f, -0.4f, -0.45f, 0.0f, -0.35f, -0.25f, 1.0f, -0.35f, -0.25f, 1.0f, -0.35f, -0.25f, 0.0f, -0.35f, -0.25f);
        this.gunModel[8].setRotationPoint(0.4f, -4.8f, -0.5f);
        this.gunModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.25f, -0.27f, 0.8f, -0.25f, -0.27f, 0.8f, -0.25f, -0.27f, 0.0f, -0.25f, -0.27f, 0.0f, -0.2f, -0.13f, 0.8f, -0.2f, -0.13f, 0.8f, -0.2f, -0.13f, 0.0f, -0.2f, -0.13f);
        this.gunModel[9].setRotationPoint(-0.6f, -4.4f, -0.5f);
        this.gunModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.45f, -0.47f, 0.8f, -0.45f, -0.47f, 0.8f, -0.45f, -0.47f, 0.0f, -0.45f, -0.47f, 0.0f, -0.35f, -0.27f, 0.8f, -0.35f, -0.27f, 0.8f, -0.35f, -0.27f, 0.0f, -0.35f, -0.27f);
        this.gunModel[10].setRotationPoint(-0.6f, -4.8f, -0.5f);
        this.gunModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 10, 1, 1, 0.0f, 0.0f, -0.1f, -0.2f, 1.0f, -0.1f, -0.2f, 1.0f, -0.1f, -0.2f, 0.0f, -0.1f, -0.2f, 0.2f, -0.1f, 0.05f, 0.5f, -0.1f, 0.05f, 0.5f, -0.1f, 0.05f, 0.2f, -0.1f, 0.05f);
        this.gunModel[11].setRotationPoint(7.4f, -4.4f, -0.5f);
        this.gunModel[12].addShapeBox(0.0f, 0.0f, 0.0f, 10, 1, 1, 0.0f, 0.2f, -0.1f, 0.05f, 0.5f, -0.1f, 0.05f, 0.5f, -0.1f, 0.05f, 0.2f, -0.1f, 0.05f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.2f, 0.0f, -0.1f, -0.2f, 0.0f, -0.1f, -0.1f);
        this.gunModel[12].setRotationPoint(7.4f, -3.6f, -0.5f);
        this.gunModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 10, 1, 1, 0.0f, -0.4f, -0.3f, -0.35f, 1.2f, -0.3f, -0.35f, 1.2f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, 0.0f, -0.4f, -0.2f, 1.0f, -0.4f, -0.2f, 1.0f, -0.4f, -0.2f, 0.0f, -0.4f, -0.2f);
        this.gunModel[13].setRotationPoint(7.4f, -4.9f, -0.5f);
        this.gunModel[14].addShapeBox(0.0f, 0.0f, 0.0f, 9, 1, 1, 0.0f, 0.0f, -0.3f, -0.1f, 1.0f, -0.3f, -0.2f, 1.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.1f, -0.4f, -0.3f, -0.35f, 1.0f, -0.3f, -0.35f, 1.0f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f);
        this.gunModel[14].setRotationPoint(7.4f, -3.0f, -0.5f);
        this.gunModel[15].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.1f, -0.3f, 1.0f, -0.1f, -0.3f, 1.0f, -0.1f, -0.3f, 0.0f, -0.1f, -0.3f, 0.2f, -0.1f, -0.05f, 1.0f, -0.1f, -0.05f, 1.0f, -0.1f, -0.05f, 0.2f, -0.1f, -0.05f);
        this.gunModel[15].setRotationPoint(15.6f, -4.4f, -0.5f);
        this.gunModel[16].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.2f, -0.1f, -0.05f, 1.0f, -0.1f, -0.05f, 1.0f, -0.1f, -0.05f, 0.2f, -0.1f, -0.05f, 0.0f, 0.0f, -0.3f, 1.0f, -0.3f, -0.3f, 1.0f, -0.3f, -0.3f, 0.0f, 0.0f, -0.3f);
        this.gunModel[16].setRotationPoint(16.6f, -3.6f, -0.5f);
        this.gunModel[17].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, 0.1f, -0.12f, 0.0f, 0.1f, -0.12f, 0.0f, 0.1f, -0.12f, 0.0f, 0.1f, -0.12f, 0.0f, -0.2f, -0.12f, 0.0f, -0.2f, -0.12f, 0.0f, -0.2f, -0.12f, 0.0f, -0.2f, -0.12f);
        this.gunModel[17].setRotationPoint(-2.6f, -3.0f, -0.5f);
        this.gunModel[18].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.25f, -0.1f, 0.8f, -0.25f, -0.1f, 0.8f, -0.25f, -0.1f, 0.0f, -0.25f, -0.1f, 0.0f, -0.25f, -0.1f, 0.8f, -0.25f, -0.1f, 0.8f, -0.25f, -0.1f, 0.0f, -0.25f, -0.1f);
        this.gunModel[18].setRotationPoint(-2.6f, -3.85f, -0.5f);
        this.gunModel[19].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.75f, -0.27f, 1.0f, -0.25f, -0.27f, 1.0f, -0.25f, -0.27f, 0.0f, -0.75f, -0.27f, 0.0f, -0.2f, -0.1f, 1.0f, -0.2f, -0.1f, 1.0f, -0.2f, -0.1f, 0.0f, -0.2f, -0.1f);
        this.gunModel[19].setRotationPoint(-2.6f, -4.4f, -0.5f);
        this.gunModel[20].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -1.1f, -0.27f, 0.0f, -0.45f, -0.47f, 0.0f, -0.45f, -0.47f, 0.0f, -1.1f, -0.27f, 0.0f, 0.15f, -0.27f, 0.0f, -0.35f, -0.27f, 0.0f, -0.35f, -0.27f, 0.0f, 0.15f, -0.27f);
        this.gunModel[20].setRotationPoint(-2.6f, -4.8f, -0.5f);
        this.gunModel[21].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.4f, 0.1f, -0.12f, 0.0f, 0.1f, -0.12f, 0.0f, 0.1f, -0.12f, 0.4f, 0.1f, -0.12f, 0.4f, 0.6f, -0.12f, 0.0f, -0.2f, -0.12f, 0.0f, -0.2f, -0.12f, 0.4f, 0.6f, -0.12f);
        this.gunModel[21].setRotationPoint(-3.6f, -3.0f, -0.5f);
        this.gunModel[22].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 1, 0.0f, 0.0f, -0.25f, -0.1f, 0.8f, -0.25f, -0.1f, 0.8f, -0.25f, -0.1f, 0.0f, -0.25f, -0.1f, 0.0f, -0.25f, -0.1f, 0.8f, -0.25f, -0.1f, 0.8f, -0.25f, -0.1f, 0.0f, -0.25f, -0.1f);
        this.gunModel[22].setRotationPoint(-8.4f, -3.85f, -0.5f);
        this.gunModel[23].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 1, 0.0f, 0.0f, -0.7f, -0.27f, 1.0f, -0.7f, -0.27f, 1.0f, -0.7f, -0.27f, 0.0f, -0.7f, -0.27f, 0.0f, -0.2f, -0.1f, 1.0f, -0.2f, -0.1f, 1.0f, -0.2f, -0.1f, 0.0f, -0.2f, -0.1f);
        this.gunModel[23].setRotationPoint(-8.4f, -4.4f, -0.5f);
        this.gunModel[24].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 1, 0.0f, 0.0f, -0.25f, -0.1f, 0.8f, -0.25f, -0.1f, 0.8f, -0.25f, -0.1f, 0.0f, -0.25f, -0.1f, 0.0f, -0.6f, -0.1f, 0.8f, -0.25f, -0.1f, 0.8f, -0.25f, -0.1f, 0.0f, -0.6f, -0.1f);
        this.gunModel[24].setRotationPoint(-7.8f, -3.35f, -0.5f);
        this.gunModel[25].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.2f, -0.25f, -0.1f, 0.2f, -0.25f, -0.1f, 0.2f, -0.25f, -0.1f, 0.2f, -0.25f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, -0.6f, -0.1f, 0.2f, -0.6f, -0.1f, 0.2f, 0.0f, -0.1f);
        this.gunModel[25].setRotationPoint(-8.0f, -3.35f, -0.5f);
        this.gunModel[26].addShapeBox(0.0f, 0.0f, 0.0f, 1, 4, 1, 0.0f, 0.0f, -0.25f, -0.1f, -0.2f, -0.25f, -0.1f, -0.2f, -0.25f, -0.1f, 0.0f, -0.25f, -0.1f, 0.0f, -0.1f, -0.1f, -0.2f, -0.05f, -0.1f, -0.2f, -0.05f, -0.1f, 0.0f, -0.05f, -0.1f);
        this.gunModel[26].setRotationPoint(-8.4f, -3.35f, -0.5f);
        this.gunModel[27].addShapeBox(0.0f, 0.0f, 0.0f, 4, 1, 1, 0.0f, -0.2f, -0.25f, -0.1f, -0.2f, 1.75f, -0.1f, -0.2f, 1.75f, -0.1f, -0.2f, -0.25f, -0.1f, -0.2f, -0.05f, -0.1f, -0.2f, -2.05f, -0.1f, -0.2f, -2.05f, -0.1f, -0.2f, -0.05f, -0.1f);
        this.gunModel[27].setRotationPoint(-7.8f, -0.35f, -0.5f);
        this.gunModel[28].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.1f, 0.1f, -0.15f, 0.1f, 0.1f, -0.15f, 0.1f, 0.1f, -0.15f, 0.1f, 0.1f, -0.15f, 0.1f, 0.0f, -0.15f, 0.1f, 0.0f, -0.15f, 0.1f, 0.0f, -0.15f, 0.1f, 0.0f, -0.15f);
        this.gunModel[28].setRotationPoint(1.0f, -3.0f, -0.5f);
        this.gunModel[29].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 1.4f, 0.0f, -0.12f, 0.0f, 0.0f, -0.12f, 0.0f, 0.0f, -0.12f, 1.4f, 0.0f, -0.12f, 0.6f, 0.0f, -0.12f, 0.0f, 0.0f, -0.12f, 0.0f, 0.0f, -0.12f, 0.6f, 0.0f, -0.12f);
        this.gunModel[29].setRotationPoint(0.8f, -3.0f, -0.5f);
        this.gunModel[30].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, 0.6f, 0.0f, -0.12f, 0.0f, 0.0f, -0.12f, 0.0f, 0.0f, -0.12f, 0.6f, 0.0f, -0.12f, 1.8f, -0.5f, -0.12f, -1.2f, 0.0f, -0.12f, -1.2f, 0.0f, -0.12f, 1.8f, -0.5f, -0.12f);
        this.gunModel[30].setRotationPoint(0.8f, -1.0f, -0.5f);
        this.gunModel[31].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f);
        this.gunModel[31].setRotationPoint(3.2f, -2.2f, -0.5f);
        this.gunModel[32].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f);
        this.gunModel[32].setRotationPoint(1.4f, -2.2f, -0.5f);
        this.gunModel[33].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.2f, -0.45f, -0.2f, 0.2f, -0.45f, -0.2f, 0.2f, -0.45f, -0.2f, 0.2f, -0.45f, -0.2f, 0.2f, -0.45f, -0.2f, 0.2f, -0.45f, -0.2f, 0.2f, -0.45f, -0.2f, 0.2f, -0.45f, -0.2f);
        this.gunModel[33].setRotationPoint(2.2f, -1.45f, -0.5f);
        this.gunModel[34].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.35f, -0.2f, -0.45f, -0.35f, -0.2f, -0.45f, -0.35f, -0.2f, -0.45f, -0.35f, -0.2f, -0.55f, -0.35f, -0.2f, -0.35f, -0.35f, -0.2f, -0.35f, -0.35f, -0.2f, -0.55f, -0.35f, -0.2f);
        this.gunModel[34].setRotationPoint(1.4f, -1.55f, -0.5f);
        this.gunModel[35].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.35f, -0.2f, -0.45f, -0.35f, -0.2f, -0.45f, -0.35f, -0.2f, -0.45f, -0.35f, -0.2f, -0.1f, -0.35f, -0.2f, -0.7f, -0.35f, -0.2f, -0.7f, -0.35f, -0.2f, -0.1f, -0.35f, -0.2f);
        this.gunModel[35].setRotationPoint(3.2f, -1.55f, -0.5f);
        this.gunModel[36].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.43f, 0.0f, -0.3f, -0.43f, 0.0f, -0.3f, -0.43f, 0.0f, -0.3f, -0.43f, 0.0f, -0.3f, -0.43f, 0.0f, -0.3f, -0.43f, 0.0f, -0.3f, -0.43f, 0.0f, -0.3f, -0.43f, 0.0f, -0.3f);
        this.gunModel[36].setRotationPoint(3.4f, -2.2f, -0.5f);
        this.gunModel[37].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f, -0.35f, 0.3f, -0.35f, -0.35f, 0.3f, -0.35f, -0.35f, 0.3f, -0.35f, -0.35f, 0.3f, -0.35f);
        this.gunModel[37].setRotationPoint(1.95f, -2.5f, -0.5f);
        this.gunModel[37].rotateAngleZ = 0.17453294f;
        this.gunModel[38].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, 0.0f, -0.35f, 0.3f, 0.0f, -0.35f, 0.3f, 0.0f, -0.35f, 0.0f, 0.0f, -0.35f, 0.0f, -0.4f, -0.35f, 0.3f, -0.4f, -0.35f, 0.3f, -0.4f, -0.35f, 0.0f, -0.4f, -0.35f);
        this.gunModel[38].setRotationPoint(7.1f, -3.0f, -0.5f);
        this.gunModel[39].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, 0.05f);
        this.gunModel[39].setRotationPoint(18.6f, -4.5f, -0.5f);
        this.gunModel[40].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, 0.05f, 0.0f, 0.0f, 0.05f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f);
        this.gunModel[40].setRotationPoint(18.6f, -3.5f, -0.5f);
        this.gunModel[41].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 1, 0.0f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f);
        this.gunModel[41].setRotationPoint(19.6f, -3.8f, -0.5f);
        this.gunModel[42].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f);
        this.gunModel[42].setRotationPoint(22.6f, -3.8f, -0.5f);
        this.gunModel[43].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.2f, -0.2f, -0.3f, -0.2f, -0.2f, -0.3f, -0.2f, -0.2f, -0.3f, -0.2f, -0.2f, -0.3f, -0.2f, -0.2f, -0.3f, -0.2f, -0.2f, -0.3f, -0.2f, -0.2f, -0.3f, -0.2f, -0.2f);
        this.gunModel[43].setRotationPoint(20.9f, -3.8f, -0.5f);
        this.gunModel[44].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f);
        this.gunModel[44].setRotationPoint(18.5f, -5.0f, -0.5f);
        this.gunModel[45].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f);
        this.gunModel[45].setRotationPoint(18.5f, -5.5f, -0.5f);
        this.gunModel[46].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f);
        this.gunModel[46].setRotationPoint(18.5f, -5.95f, -0.5f);
        this.gunModel[47].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f);
        this.gunModel[47].setRotationPoint(18.5f, -5.75f, -0.75f);
        this.gunModel[48].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f);
        this.gunModel[48].setRotationPoint(18.5f, -5.75f, -0.25f);
        this.gunModel[49].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, -0.3f, -0.2f, -0.2f, -0.3f, -0.2f, -0.2f, -0.3f, -0.2f, -0.2f, -0.3f, -0.2f);
        this.gunModel[49].setRotationPoint(0.4f, -4.9f, -0.5f);
        this.gunModel[50].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f);
        this.gunModel[50].setRotationPoint(18.5f, -5.6f, -0.5f);
        this.gunModel[51].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.25f, -0.25f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, -0.25f, -0.25f, -0.25f, -0.3f, -0.25f, -0.25f, -0.3f, -0.25f, -0.25f, -0.3f, -0.25f, -0.25f, -0.3f, -0.25f);
        this.gunModel[51].setRotationPoint(1.0f, -5.55f, -0.5f);
        this.gunModel[51].rotateAngleZ = -0.5061455f;
        this.gunModel[52].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.35f, -0.4f, -0.4f, -0.25f, -0.6f, -0.4f, -0.25f, -0.6f, -0.45f, -0.35f, -0.4f, -0.45f, -0.2f, -0.4f, -0.4f, -0.25f, -0.4f, -0.4f, -0.25f, -0.4f, -0.45f, -0.2f, -0.4f, -0.45f);
        this.gunModel[52].setRotationPoint(0.9f, -5.8f, -0.65f);
        this.gunModel[53].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.35f, -0.4f, -0.4f, -0.25f, -0.6f, -0.4f, -0.25f, -0.6f, -0.45f, -0.35f, -0.4f, -0.45f, -0.2f, -0.4f, -0.4f, -0.25f, -0.4f, -0.4f, -0.25f, -0.4f, -0.45f, -0.2f, -0.4f, -0.45f);
        this.gunModel[53].setRotationPoint(0.9f, -5.8f, -0.3f);
        this.ammoModel = new ModelRendererTurbo[2];
        this.ammoModel[0] = new ModelRendererTurbo(this, 30, 52, this.textureX, this.textureY);
        this.ammoModel[1] = new ModelRendererTurbo(this, 13, 52, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(1.52f, 2.98f, -0.5f, 6, 2, 1, 0.0f, -0.45f, -0.38f, -0.2f, -3.47f, -0.69f, -0.2f, -3.47f, -0.69f, -0.2f, -0.45f, -0.38f, -0.2f, -0.45f, 0.0f, -0.2f, -3.45f, 0.0f, -0.2f, -3.45f, 0.0f, -0.2f, -0.4f, 0.0f, -0.2f);
        this.ammoModel[0].setRotationPoint(1.45f, -3.0f, 0.0f);
        this.ammoModel[0].rotateAngleZ = 0.31415927f;
        this.ammoModel[1].addShapeBox(2.26f, 0.32f, -0.5f, 6, 2, 1, 0.0f, -0.45f, 0.0f, -0.2f, -3.45f, 0.0f, -0.2f, -3.45f, 0.0f, -0.2f, -0.45f, 0.0f, -0.2f, -0.45f, -0.25f, -0.2f, -3.45f, -0.3f, -0.2f, -3.45f, -0.3f, -0.2f, -0.45f, -0.25f, -0.2f);
        this.ammoModel[1].setRotationPoint(1.36f, -2.0f, 0.0f);
        this.ammoModel[1].rotateAngleZ = 0.15707964f;
        this.barrelAttachPoint = new Vector3f(1.5f, 0.26875f, 0.0f);
        this.stockAttachPoint = new Vector3f(-0.0625f, 0.203125f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.625f, 0.34375f, 0.0f);
        this.gripAttachPoint = new Vector3f(0.75f, 0.25f, 0.0f);
        this.gunSlideDistance = 0.0f;
        this.animationType = EnumAnimationType.BOTTOM_CLIP;
        this.translateAll(0.0f, -1.8f, 0.0f);
        this.hasFlash = false;
        this.hasArms = true;
        this.leftArmPos = new Vector3f(-0.15f, -0.35f, 0.0f);
        this.leftArmRot = new Vector3f(90.0f, 45.0f, 0.0f);
        this.leftArmReloadPos = new Vector3f(-0.15f, -0.35f, 0.0f);
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

