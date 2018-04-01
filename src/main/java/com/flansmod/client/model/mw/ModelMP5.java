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

public class ModelMP5
extends ModelGun {
    int textureX = 64;
    int textureY = 64;

    public ModelMP5() {
        this.gunModel = new ModelRendererTurbo[35];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 17, 1, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 33, 1, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 1, 9, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 33, 9, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 1, 17, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 33, 17, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 49, 1, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 1, 25, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 17, 25, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 25, 25, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 33, 25, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 41, 25, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 9, 33, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 17, 33, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 25, 33, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 33, 33, this.textureX, this.textureY);
        this.gunModel[17] = new ModelRendererTurbo(this, 41, 33, this.textureX, this.textureY);
        this.gunModel[18] = new ModelRendererTurbo(this, 1, 41, this.textureX, this.textureY);
        this.gunModel[19] = new ModelRendererTurbo(this, 49, 33, this.textureX, this.textureY);
        this.gunModel[20] = new ModelRendererTurbo(this, 25, 41, this.textureX, this.textureY);
        this.gunModel[21] = new ModelRendererTurbo(this, 41, 41, this.textureX, this.textureY);
        this.gunModel[22] = new ModelRendererTurbo(this, 1, 49, this.textureX, this.textureY);
        this.gunModel[23] = new ModelRendererTurbo(this, 57, 33, this.textureX, this.textureY);
        this.gunModel[24] = new ModelRendererTurbo(this, 57, 41, this.textureX, this.textureY);
        this.gunModel[25] = new ModelRendererTurbo(this, 17, 49, this.textureX, this.textureY);
        this.gunModel[26] = new ModelRendererTurbo(this, 25, 49, this.textureX, this.textureY);
        this.gunModel[27] = new ModelRendererTurbo(this, 33, 49, this.textureX, this.textureY);
        this.gunModel[28] = new ModelRendererTurbo(this, 41, 49, this.textureX, this.textureY);
        this.gunModel[29] = new ModelRendererTurbo(this, 49, 49, this.textureX, this.textureY);
        this.gunModel[30] = new ModelRendererTurbo(this, 57, 49, this.textureX, this.textureY);
        this.gunModel[31] = new ModelRendererTurbo(this, 1, 57, this.textureX, this.textureY);
        this.gunModel[32] = new ModelRendererTurbo(this, 9, 57, this.textureX, this.textureY);
        this.gunModel[33] = new ModelRendererTurbo(this, 17, 57, this.textureX, this.textureY);
        this.gunModel[34] = new ModelRendererTurbo(this, 25, 57, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f);
        this.gunModel[0].setRotationPoint(6.2f, -3.7f, -0.5f);
        this.gunModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f);
        this.gunModel[1].setRotationPoint(6.2f, -4.5f, -0.5f);
        this.gunModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.1f, 0.2f, 0.0f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f);
        this.gunModel[2].setRotationPoint(6.2f, -2.9f, -0.5f);
        this.gunModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 13, 1, 1, 0.0f, -0.4f, -0.45f, -0.25f, 0.0f, -0.45f, -0.25f, 0.0f, -0.45f, -0.25f, -0.4f, -0.45f, -0.25f, -0.4f, -0.1f, -0.2f, 0.0f, -0.1f, -0.2f, 0.0f, -0.1f, -0.2f, -0.4f, -0.1f, -0.2f);
        this.gunModel[3].setRotationPoint(-0.8f, -4.7f, -0.5f);
        this.gunModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 13, 1, 1, 0.0f, -0.4f, -0.45f, -0.35f, 0.0f, -0.45f, -0.35f, 0.0f, -0.45f, -0.35f, -0.4f, -0.45f, -0.35f, -0.4f, -0.45f, -0.25f, 0.0f, -0.45f, -0.25f, 0.0f, -0.45f, -0.25f, -0.4f, -0.45f, -0.25f);
        this.gunModel[4].setRotationPoint(-0.8f, -4.8f, -0.5f);
        this.gunModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 13, 1, 1, 0.0f, -0.4f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f, -0.4f, -0.45f, -0.2f, -0.4f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, -0.4f, 0.0f, 0.1f);
        this.gunModel[5].setRotationPoint(-0.8f, -4.25f, -0.5f);
        this.gunModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 13, 1, 1, 0.0f, -0.4f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, 0.0f, 0.0f, 0.1f, -0.4f, 0.0f, 0.1f, -0.4f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f, -0.4f, -0.45f, -0.2f);
        this.gunModel[6].setRotationPoint(-0.8f, -3.25f, -0.5f);
        this.gunModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 6, 2, 1, 0.0f, -0.4f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, -0.4f, 0.0f, -0.2f, -0.4f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f, -0.4f, -0.45f, -0.2f);
        this.gunModel[7].setRotationPoint(-0.8f, -3.4f, -0.5f);
        this.gunModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 4, 1, 1, 0.0f, 0.4f, -0.15f, -0.2f, 0.1f, -0.15f, -0.2f, 0.1f, -0.15f, -0.2f, 0.4f, -0.15f, -0.2f, 0.1f, -0.1f, -0.2f, -0.1f, -0.1f, -0.2f, -0.1f, -0.1f, -0.2f, 0.1f, -0.1f, -0.2f);
        this.gunModel[8].setRotationPoint(0.5f, -2.0f, -0.5f);
        this.gunModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, 0.4f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.4f, 0.0f, 0.0f, 0.2f, 0.1f, 0.0f, 0.2f, -0.45f, 0.0f, 0.2f, -0.45f, 0.0f, 0.2f, 0.1f, 0.0f);
        this.gunModel[9].setRotationPoint(4.6f, -3.2f, -0.5f);
        this.gunModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 1, 3, 1, 0.0f, 0.4f, -0.15f, -0.2f, 0.1f, -0.15f, -0.2f, 0.1f, -0.15f, -0.2f, 0.4f, -0.15f, -0.2f, 1.8f, -0.7f, -0.2f, -1.1f, 0.0f, -0.2f, -1.1f, 0.0f, -0.2f, 1.8f, -0.7f, -0.2f);
        this.gunModel[10].setRotationPoint(0.8f, -1.25f, -0.5f);
        this.gunModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.4f, -0.3f, 0.0f, -0.4f, -0.3f, 0.0f, -0.4f, -0.3f, 0.0f, -0.4f, -0.3f, 0.0f, -0.4f, -0.3f, 0.0f, -0.4f, -0.3f, 0.0f, -0.4f, -0.3f, 0.0f, -0.4f, -0.3f);
        this.gunModel[11].setRotationPoint(2.2f, -0.5f, -0.5f);
        this.gunModel[12].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.2f, -0.6f, -0.3f, 0.0f, 1.6f, -0.3f, 0.0f, 1.6f, -0.3f, 0.2f, -0.6f, -0.3f, 0.0f, -0.4f, -0.3f, 0.0f, -2.2f, -0.3f, 0.0f, -2.2f, -0.3f, 0.0f, -0.4f, -0.3f);
        this.gunModel[12].setRotationPoint(3.2f, -0.5f, -0.5f);
        this.gunModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.45f, -0.2f, 0.2f, -0.45f, -0.2f, 0.2f, -0.45f, -0.2f, 0.0f, -0.45f, -0.2f, 0.8f, -0.1f, -0.15f, 0.2f, -0.1f, -0.15f, 0.2f, -0.1f, -0.15f, 0.8f, -0.1f, -0.15f);
        this.gunModel[13].setRotationPoint(-1.4f, -4.7f, -0.5f);
        this.gunModel[14].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.1f, -0.4f, -0.35f, -0.1f, -0.4f, -0.35f, -0.1f, -0.4f, -0.35f, -0.1f, -0.4f, -0.35f, 0.2f, -0.4f, -0.2f, 0.0f, -0.4f, -0.2f, 0.0f, -0.4f, -0.2f, 0.2f, -0.4f, -0.2f);
        this.gunModel[14].setRotationPoint(-1.2f, -4.85f, -0.5f);
        this.gunModel[15].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.2f, -0.45f, -0.15f, 0.2f, -0.45f, -0.15f, 0.2f, -0.45f, -0.15f, -0.2f, -0.45f, -0.15f, -0.2f, 0.0f, -0.15f, 0.2f, 0.0f, 0.15f, 0.2f, 0.0f, 0.15f, -0.2f, 0.0f, -0.15f);
        this.gunModel[15].setRotationPoint(-2.4f, -4.25f, -0.5f);
        this.gunModel[16].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.2f, 0.0f, -0.15f, 0.2f, 0.0f, 0.15f, 0.2f, 0.0f, 0.15f, -0.2f, 0.0f, -0.15f, -0.2f, -0.45f, -0.15f, 0.2f, -0.45f, -0.15f, 0.2f, -0.45f, -0.15f, -0.2f, -0.45f, -0.15f);
        this.gunModel[16].setRotationPoint(-2.4f, -3.25f, -0.5f);
        this.gunModel[17].addShapeBox(0.0f, 0.0f, 0.0f, 2, 2, 1, 0.0f, -0.2f, 0.0f, -0.15f, 0.2f, 0.0f, -0.15f, 0.2f, 0.0f, -0.15f, -0.2f, 0.0f, -0.15f, -0.2f, -0.45f, -0.15f, 0.2f, -0.45f, -0.15f, 0.2f, -0.45f, -0.15f, -0.2f, -0.45f, -0.15f);
        this.gunModel[17].setRotationPoint(-2.4f, -3.4f, -0.5f);
        this.gunModel[18].addShapeBox(0.0f, 0.0f, 0.0f, 7, 2, 1, 0.0f, -0.2f, -0.2f, -0.15f, 0.2f, 0.0f, -0.15f, 0.2f, 0.0f, -0.15f, -0.2f, -0.2f, -0.15f, -0.6f, 1.8f, -0.15f, 0.2f, -0.45f, -0.15f, 0.2f, -0.45f, -0.15f, -0.6f, 1.8f, -0.15f);
        this.gunModel[18].setRotationPoint(-9.4f, -3.4f, -0.5f);
        this.gunModel[19].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -1.05f, -0.15f, 0.0f, -0.6f, -0.15f, 0.0f, -0.6f, -0.15f, 0.0f, -1.05f, -0.15f, 0.0f, 0.05f, -0.15f, 0.0f, 0.0f, -0.15f, 0.0f, 0.0f, -0.15f, 0.0f, 0.05f, -0.15f);
        this.gunModel[19].setRotationPoint(-3.2f, -4.4f, -0.5f);
        this.gunModel[20].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f);
        this.gunModel[20].setRotationPoint(11.0f, -3.75f, -0.5f);
        this.gunModel[21].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.55f, -0.35f, 0.0f, -0.55f, -0.35f, 0.0f, -0.55f, -0.35f, 0.0f, -0.55f, -0.35f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f);
        this.gunModel[21].setRotationPoint(11.0f, -4.35f, -0.5f);
        this.gunModel[22].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.55f, -0.35f, 0.0f, -0.55f, -0.35f, 0.0f, -0.55f, -0.35f, 0.0f, -0.55f, -0.35f);
        this.gunModel[22].setRotationPoint(11.0f, -3.15f, -0.5f);
        this.gunModel[23].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f);
        this.gunModel[23].setRotationPoint(11.3f, -4.6f, -0.5f);
        this.gunModel[24].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f);
        this.gunModel[24].setRotationPoint(11.3f, -5.1f, -0.5f);
        this.gunModel[25].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f, -0.25f, -0.45f, -0.25f);
        this.gunModel[25].setRotationPoint(11.3f, -5.55f, -0.5f);
        this.gunModel[26].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f);
        this.gunModel[26].setRotationPoint(11.3f, -5.35f, -0.75f);
        this.gunModel[27].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f, -0.25f, -0.3f, -0.45f);
        this.gunModel[27].setRotationPoint(11.3f, -5.35f, -0.25f);
        this.gunModel[28].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f);
        this.gunModel[28].setRotationPoint(11.3f, -5.2f, -0.5f);
        this.gunModel[29].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.25f, -0.25f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, -0.25f, -0.25f, -0.25f, -0.3f, -0.25f, -0.25f, -0.3f, -0.25f, -0.25f, -0.3f, -0.25f, -0.25f, -0.3f, -0.25f);
        this.gunModel[29].setRotationPoint(0.6f, -5.2f, -0.5f);
        this.gunModel[29].rotateAngleZ = -0.5061455f;
        this.gunModel[30].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.35f, -0.4f, -0.4f, -0.25f, -0.6f, -0.4f, -0.25f, -0.6f, -0.45f, -0.35f, -0.4f, -0.45f, -0.2f, -0.4f, -0.4f, -0.25f, -0.4f, -0.4f, -0.25f, -0.4f, -0.45f, -0.2f, -0.4f, -0.45f);
        this.gunModel[30].setRotationPoint(0.5f, -5.45f, -0.65f);
        this.gunModel[31].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.35f, -0.4f, -0.4f, -0.25f, -0.6f, -0.4f, -0.25f, -0.6f, -0.45f, -0.35f, -0.4f, -0.45f, -0.2f, -0.4f, -0.4f, -0.25f, -0.4f, -0.4f, -0.25f, -0.4f, -0.45f, -0.2f, -0.4f, -0.45f);
        this.gunModel[31].setRotationPoint(0.5f, -5.45f, -0.3f);
        this.gunModel[32].addShapeBox(0.0f, 0.0f, 0.0f, 2, 2, 1, 0.0f, -0.25f, -0.25f, -0.2f, -0.25f, -0.25f, -0.2f, -0.25f, -0.25f, -0.2f, -0.25f, -0.25f, -0.2f, -0.25f, -0.25f, -0.2f, -0.25f, -0.25f, -0.2f, -0.25f, -0.25f, -0.2f, -0.25f, -0.25f, -0.2f);
        this.gunModel[32].setRotationPoint(-0.4f, -4.8f, -0.5f);
        this.gunModel[33].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, 1.6f, -0.35f, 0.2f, -0.6f, -0.35f, 0.2f, -0.6f, -0.35f, 0.0f, 1.6f, -0.35f, 0.0f, -2.2f, -0.35f, 0.0f, -0.4f, -0.35f, 0.0f, -0.4f, -0.35f, 0.0f, -2.2f, -0.35f);
        this.gunModel[33].setRotationPoint(1.4f, -1.0f, -0.5f);
        this.gunModel[33].rotateAngleZ = -0.10471976f;
        this.gunModel[34].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, 0.6f, -0.3f, 0.0f, -0.4f, -0.3f, 0.0f, -0.4f, -0.3f, 0.0f, 0.6f, -0.3f, 0.0f, -1.4f, -0.3f, 0.0f, -0.4f, -0.3f, 0.0f, -0.4f, -0.3f, 0.0f, -1.4f, -0.3f);
        this.gunModel[34].setRotationPoint(1.2f, -0.5f, -0.5f);
        this.ammoModel = new ModelRendererTurbo[3];
        this.ammoModel[0] = new ModelRendererTurbo(this, 49, 25, this.textureX, this.textureY);
        this.ammoModel[1] = new ModelRendererTurbo(this, 57, 25, this.textureX, this.textureY);
        this.ammoModel[2] = new ModelRendererTurbo(this, 1, 33, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, 0.3f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.3f, 0.0f, -0.2f, -0.1f, 0.0f, -0.2f, 0.4f, 0.05f, -0.2f, 0.4f, 0.05f, -0.2f, -0.1f, 0.0f, -0.2f);
        this.ammoModel[0].setRotationPoint(4.6f, -2.0f, -0.5f);
        this.ammoModel[0].rotateAngleZ = 0.03490659f;
        this.ammoModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, 0.3f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.3f, 0.0f, -0.2f, -0.6f, 0.0f, -0.2f, 0.9f, 0.0f, -0.2f, 0.9f, 0.0f, -0.2f, -0.6f, 0.0f, -0.2f);
        this.ammoModel[1].setRotationPoint(5.07f, 0.0f, -0.5f);
        this.ammoModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.3f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.3f, 0.0f, -0.2f, -0.35f, 0.0f, -0.2f, 0.4f, -0.4f, -0.2f, 0.4f, -0.4f, -0.2f, -0.35f, 0.0f, -0.2f);
        this.ammoModel[2].setRotationPoint(5.97f, 2.0f, -0.5f);
        this.barrelAttachPoint = new Vector3f(0.78125f, 0.2625f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.25f, 0.3125f, 0.0f);
        this.gunSlideDistance = 0.25f;
        this.animationType = EnumAnimationType.PISTOL_CLIP;
        this.translateAll(0.0f, -1.4f, 0.0f);
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

