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

public class ModelM9
extends ModelGun {
    int textureX = 64;
    int textureY = 64;

    public ModelM9() {
        this.gunModel = new ModelRendererTurbo[30];
        this.gunModel[0] = new ModelRendererTurbo(this, 25, 9, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 41, 9, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 1, 17, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 17, 17, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 41, 17, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 57, 9, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 1, 25, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 17, 25, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 33, 25, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 57, 17, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 49, 25, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 57, 25, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 1, 33, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 9, 33, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 17, 33, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 25, 33, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 33, 33, this.textureX, this.textureY);
        this.gunModel[17] = new ModelRendererTurbo(this, 41, 33, this.textureX, this.textureY);
        this.gunModel[18] = new ModelRendererTurbo(this, 57, 33, this.textureX, this.textureY);
        this.gunModel[19] = new ModelRendererTurbo(this, 1, 41, this.textureX, this.textureY);
        this.gunModel[20] = new ModelRendererTurbo(this, 9, 41, this.textureX, this.textureY);
        this.gunModel[21] = new ModelRendererTurbo(this, 17, 41, this.textureX, this.textureY);
        this.gunModel[22] = new ModelRendererTurbo(this, 25, 41, this.textureX, this.textureY);
        this.gunModel[23] = new ModelRendererTurbo(this, 33, 41, this.textureX, this.textureY);
        this.gunModel[24] = new ModelRendererTurbo(this, 41, 41, this.textureX, this.textureY);
        this.gunModel[25] = new ModelRendererTurbo(this, 49, 41, this.textureX, this.textureY);
        this.gunModel[26] = new ModelRendererTurbo(this, 57, 41, this.textureX, this.textureY);
        this.gunModel[27] = new ModelRendererTurbo(this, 49, 49, this.textureX, this.textureY);
        this.gunModel[28] = new ModelRendererTurbo(this, 57, 49, this.textureX, this.textureY);
        this.gunModel[29] = new ModelRendererTurbo(this, 25, 57, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.45f, -0.38f, -0.5f, -0.45f, -0.38f, -0.5f, -0.45f, -0.38f, 0.0f, -0.45f, -0.38f, 0.0f, -0.4f, -0.25f, -0.5f, -0.4f, -0.25f, -0.5f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f);
        this.gunModel[0].setRotationPoint(1.3f, -4.1f, -0.5f);
        this.gunModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.4f, -0.25f, -0.5f, -0.4f, -0.25f, -0.5f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f, 0.0f, -0.45f, -0.38f, -0.5f, -0.45f, -0.38f, -0.5f, -0.45f, -0.38f, 0.0f, -0.45f, -0.38f);
        this.gunModel[1].setRotationPoint(1.3f, -3.75f, -0.5f);
        this.gunModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.45f, -0.25f, -0.5f, -0.45f, -0.25f, -0.5f, -0.45f, -0.25f, 0.0f, -0.45f, -0.25f, 0.0f, -0.4f, -0.25f, -0.5f, -0.4f, -0.25f, -0.5f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f);
        this.gunModel[2].setRotationPoint(1.3f, -3.95f, -0.5f);
        this.gunModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 7, 1, 1, 0.0f, 0.3f, -0.3f, -0.1f, 0.2f, -0.3f, -0.1f, 0.2f, -0.3f, -0.1f, 0.3f, -0.3f, -0.1f, 0.6f, -0.3f, -0.1f, 0.2f, -0.3f, -0.1f, 0.2f, -0.3f, -0.1f, 0.6f, -0.3f, -0.1f);
        this.gunModel[3].setRotationPoint(-0.65f, -3.0f, -0.5f);
        this.gunModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 1, 0.0f, 0.6f, -0.4f, -0.1f, 0.2f, -0.4f, -0.1f, 0.2f, -0.4f, -0.1f, 0.6f, -0.4f, -0.1f, 0.2f, -0.35f, -0.1f, 0.0f, -0.35f, -0.1f, 0.0f, -0.35f, -0.1f, 0.2f, -0.35f, -0.1f);
        this.gunModel[4].setRotationPoint(-0.65f, -2.7f, -0.5f);
        this.gunModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.8f, -0.45f, -0.1f, 0.2f, -0.45f, -0.1f, 0.2f, -0.45f, -0.1f, 0.8f, -0.45f, -0.1f, 0.95f, -0.4f, -0.3f, 0.2f, -0.4f, -0.3f, 0.2f, -0.4f, -0.3f, 0.95f, -0.4f, -0.3f);
        this.gunModel[5].setRotationPoint(5.35f, -2.75f, -0.5f);
        this.gunModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.4f, -0.25f, 0.3f, -0.4f, -0.25f, 0.3f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f, 0.3f, -0.4f, -0.25f, 0.3f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f);
        this.gunModel[6].setRotationPoint(0.9f, -3.25f, -0.5f);
        this.gunModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.45f, -0.25f, 0.3f, -0.45f, -0.25f, 0.3f, -0.45f, -0.25f, 0.0f, -0.45f, -0.25f, 0.0f, -0.4f, -0.25f, 0.3f, -0.4f, -0.25f, 0.3f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f);
        this.gunModel[7].setRotationPoint(0.9f, -3.1f, -0.5f);
        this.gunModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f, 0.0f, -0.4f, -0.25f, 0.3f, -0.4f, -0.25f, 0.3f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f, 0.0f, -0.4f, -0.35f, 0.3f, -0.4f, -0.35f, 0.3f, -0.4f, -0.35f, 0.0f, -0.4f, -0.35f);
        this.gunModel[8].setRotationPoint(0.9f, -2.9f, -0.5f);
        this.gunModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.1f, -0.4f, -0.25f, -0.1f, -0.4f, -0.25f, -0.1f, -0.4f, -0.25f, -0.1f, -0.4f, -0.25f, -0.3f, -0.35f, -0.25f, -0.3f, -0.35f, -0.25f, -0.3f, -0.35f, -0.25f, -0.3f, -0.35f, -0.25f);
        this.gunModel[9].setRotationPoint(3.1f, -2.45f, -0.5f);
        this.gunModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.4f, -0.25f, -0.3f, -0.4f, -0.25f, -0.3f, -0.4f, -0.25f, -0.3f, -0.4f, -0.25f, -0.4f, -0.35f, -0.25f, -0.4f, -0.35f, -0.25f, -0.4f, -0.35f, -0.25f, -0.4f, -0.35f, -0.25f);
        this.gunModel[10].setRotationPoint(3.1f, -2.2f, -0.5f);
        this.gunModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.4f, -0.2f, -0.25f, -0.45f, -0.2f, -0.25f, -0.45f, -0.3f, -0.25f, -0.45f, -0.3f, -0.25f, -0.45f, -0.2f, -0.25f);
        this.gunModel[11].setRotationPoint(3.1f, -1.75f, -0.5f);
        this.gunModel[12].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -1.0f, -0.7f, -0.25f, -0.4f, -0.4f, -0.25f, -0.4f, -0.4f, -0.25f, -1.0f, -0.7f, -0.25f, -1.0f, -0.2f, -0.25f, -0.4f, -0.45f, -0.25f, -0.4f, -0.45f, -0.25f, -1.0f, -0.2f, -0.25f);
        this.gunModel[12].setRotationPoint(1.95f, -1.5f, -0.5f);
        this.gunModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.5f, -0.45f, -0.25f, -0.4f, -0.45f, -0.25f, -0.4f, -0.45f, -0.25f, -0.5f, -0.45f, -0.25f, -0.5f, -0.45f, -0.25f, -0.4f, -0.45f, -0.25f, -0.4f, -0.45f, -0.25f, -0.5f, -0.45f, -0.25f);
        this.gunModel[13].setRotationPoint(1.35f, -1.25f, -0.5f);
        this.gunModel[14].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.9f, 0.75f, -0.25f, -0.5f, -0.45f, -0.25f, -0.5f, -0.45f, -0.25f, 0.9f, 0.75f, -0.25f, 0.9f, -0.65f, -0.25f, -0.5f, -0.45f, -0.25f, -0.5f, -0.45f, -0.25f, 0.9f, -0.65f, -0.25f);
        this.gunModel[14].setRotationPoint(1.45f, -1.25f, -0.5f);
        this.gunModel[15].addShapeBox(0.0f, 0.0f, 0.0f, 2, 4, 1, 0.0f, -0.1f, -0.6f, -0.1f, 0.1f, -0.6f, -0.1f, 0.1f, -0.6f, -0.1f, -0.1f, -0.6f, -0.1f, 0.9f, -0.5f, -0.1f, -0.7f, -0.5f, -0.1f, -0.7f, -0.5f, -0.1f, 0.9f, -0.5f, -0.1f);
        this.gunModel[15].setRotationPoint(-0.6f, -2.65f, -0.5f);
        this.gunModel[16].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.2f, -0.1f, 0.2f, -0.2f, -0.1f, 0.2f, -0.2f, -0.1f, 0.0f, -0.2f, -0.1f, 0.0f, -0.2f, -0.1f, 0.2f, -0.2f, -0.1f, 0.2f, -0.2f, -0.1f, 0.0f, -0.2f, -0.1f);
        this.gunModel[16].setRotationPoint(-1.5f, 0.65f, -0.5f);
        this.gunModel[17].addShapeBox(0.0f, 0.0f, 0.0f, 0, 1, 1, 0.0f, 0.2f, -0.4f, -0.1f, 0.15f, -0.4f, -0.1f, 0.15f, -0.4f, -0.1f, 0.2f, -0.4f, -0.1f, -0.05f, -0.25f, -0.1f, 0.05f, -0.25f, -0.1f, 0.05f, -0.25f, -0.1f, -0.05f, -0.25f, -0.1f);
        this.gunModel[17].setRotationPoint(-0.65f, -2.45f, -0.5f);
        this.gunModel[18].addShapeBox(0.0f, 0.0f, 0.0f, 0, 1, 1, 0.0f, -0.15f, -0.4f, -0.1f, 0.35f, -0.4f, -0.1f, 0.35f, -0.4f, -0.1f, -0.15f, -0.4f, -0.1f, -0.05f, -0.3f, -0.1f, 0.05f, -0.3f, -0.1f, 0.05f, -0.3f, -0.1f, -0.05f, -0.3f, -0.1f);
        this.gunModel[18].setRotationPoint(1.35f, -2.45f, -0.5f);
        this.gunModel[19].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.1f, -0.45f, -0.11f, -0.4f, -0.3f, -0.11f, -0.4f, -0.3f, -0.11f, -0.1f, -0.45f, -0.11f, -0.1f, -0.45f, -0.11f, -0.45f, -0.4f, -0.11f, -0.45f, -0.4f, -0.11f, -0.1f, -0.45f, -0.11f);
        this.gunModel[19].setRotationPoint(-1.69f, -2.8f, -0.5f);
        this.gunModel[20].addShapeBox(0.0f, 0.0f, 0.0f, 2, 4, 1, 0.0f, -0.2f, -0.6f, 0.05f, 0.0f, -0.6f, 0.05f, 0.0f, -0.6f, 0.05f, -0.2f, -0.6f, 0.05f, 0.8f, -0.5f, 0.05f, -0.8f, -0.5f, 0.05f, -0.8f, -0.5f, 0.05f, 0.8f, -0.5f, 0.05f);
        this.gunModel[20].setRotationPoint(-0.6f, -2.65f, -0.5f);
        this.gunModel[21].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.1f, -0.2f, 0.05f, 0.1f, -0.2f, 0.05f, 0.1f, -0.2f, 0.05f, -0.1f, -0.2f, 0.05f, -0.2f, -0.3f, 0.05f, 0.1f, -0.4f, 0.05f, 0.1f, -0.4f, 0.05f, -0.2f, -0.4f, 0.05f);
        this.gunModel[21].setRotationPoint(-1.5f, 0.65f, -0.5f);
        this.gunModel[22].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.2f, -0.3f, 0.05f, 0.0f, -0.4f, 0.05f, 0.0f, -0.4f, 0.05f, -0.2f, -0.4f, 0.05f, -0.1f, -0.2f, 0.05f, -0.1f, -0.2f, 0.05f, -0.1f, -0.2f, 0.05f, -0.1f, -0.2f, 0.05f);
        this.gunModel[22].setRotationPoint(-0.5f, -2.85f, -0.5f);
        this.gunModel[23].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.2f, -0.3f, 0.0f, -0.2f, -0.3f, 0.0f, -0.2f, -0.3f, 0.0f, -0.2f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, -0.2f, -0.3f, 0.0f, -0.2f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f);
        this.gunModel[23].setRotationPoint(-1.0f, -3.0f, -0.5f);
        this.gunModel[24].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, 0.0f, -0.35f, 0.0f, -0.2f, -0.35f, 0.0f, -0.2f, -0.35f, 0.0f, 0.0f, -0.35f, 0.0f, -0.5f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.5f, -0.2f, 0.0f);
        this.gunModel[24].setRotationPoint(-1.0f, -2.65f, -0.5f);
        this.gunModel[25].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, -0.35f, 0.0f, -0.2f, -0.35f, 0.0f, -0.2f, -0.35f, 0.0f, -0.2f, -0.35f, 0.0f, -0.2f, -0.35f, 0.0f, -0.2f, -0.35f, 0.0f, -0.2f, -0.35f, 0.0f, -0.2f, -0.35f, 0.0f);
        this.gunModel[25].setRotationPoint(1.5f, -3.0f, -0.5f);
        this.gunModel[26].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f);
        this.gunModel[26].setRotationPoint(1.75f, -2.25f, -0.5f);
        this.gunModel[26].rotateAngleZ = 0.20943952f;
        this.gunModel[27].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.35f, -0.35f, -0.35f, -0.25f, -0.4f, -0.35f, -0.25f, -0.4f, -0.35f, -0.35f, -0.35f, -0.35f, -0.3f, -0.4f, -0.35f, -0.3f, 0.0f, -0.35f, -0.3f, 0.0f, -0.35f, -0.3f, -0.4f, -0.35f);
        this.gunModel[27].setRotationPoint(-1.25f, -3.9f, -0.5f);
        this.gunModel[28].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.2f, -0.35f, -0.25f, -0.2f, -0.35f, -0.25f, -0.2f, -0.35f, -0.3f, -0.2f, -0.35f, 0.0f, 0.0f, -0.35f, -0.3f, 0.0f, -0.35f, -0.3f, 0.0f, -0.35f, 0.0f, 0.0f, -0.35f);
        this.gunModel[28].setRotationPoint(-1.1f, -3.45f, -0.5f);
        this.gunModel[29].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f);
        this.gunModel[29].setRotationPoint(6.7f, -3.9f, -0.5f);
        this.ammoModel = new ModelRendererTurbo[1];
        this.ammoModel[0] = new ModelRendererTurbo(this, 1, 57, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 2, 5, 1, 0.0f, -0.25f, -0.6f, -0.15f, -0.1f, -0.6f, -0.15f, -0.1f, -0.6f, -0.15f, -0.25f, -0.6f, -0.15f, 0.9f, -0.5f, -0.15f, -1.1f, -0.4f, -0.15f, -1.1f, -0.4f, -0.15f, 0.9f, -0.5f, -0.15f);
        this.ammoModel[0].setRotationPoint(-0.5f, -2.85f, -0.5f);
        this.slideModel = new ModelRendererTurbo[15];
        this.slideModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.slideModel[1] = new ModelRendererTurbo(this, 25, 1, this.textureX, this.textureY);
        this.slideModel[2] = new ModelRendererTurbo(this, 33, 1, this.textureX, this.textureY);
        this.slideModel[3] = new ModelRendererTurbo(this, 49, 1, this.textureX, this.textureY);
        this.slideModel[4] = new ModelRendererTurbo(this, 57, 1, this.textureX, this.textureY);
        this.slideModel[5] = new ModelRendererTurbo(this, 1, 9, this.textureX, this.textureY);
        this.slideModel[6] = new ModelRendererTurbo(this, 9, 9, this.textureX, this.textureY);
        this.slideModel[7] = new ModelRendererTurbo(this, 1, 49, this.textureX, this.textureY);
        this.slideModel[8] = new ModelRendererTurbo(this, 9, 49, this.textureX, this.textureY);
        this.slideModel[9] = new ModelRendererTurbo(this, 17, 49, this.textureX, this.textureY);
        this.slideModel[10] = new ModelRendererTurbo(this, 25, 49, this.textureX, this.textureY);
        this.slideModel[11] = new ModelRendererTurbo(this, 33, 49, this.textureX, this.textureY);
        this.slideModel[12] = new ModelRendererTurbo(this, 41, 49, this.textureX, this.textureY);
        this.slideModel[13] = new ModelRendererTurbo(this, 9, 57, this.textureX, this.textureY);
        this.slideModel[14] = new ModelRendererTurbo(this, 17, 57, this.textureX, this.textureY);
        this.slideModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 7, 1, 1, 0.0f, 0.1f, -0.2f, -0.05f, 0.2f, -0.2f, -0.05f, 0.2f, -0.2f, -0.05f, 0.1f, -0.2f, -0.05f, 0.3f, -0.2f, -0.05f, 0.2f, -0.2f, -0.05f, 0.2f, -0.2f, -0.05f, 0.3f, -0.2f, -0.05f);
        this.slideModel[0].setRotationPoint(-0.65f, -3.5f, -0.5f);
        this.slideModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, -0.2f, -0.05f, -0.5f, 0.0f, -0.2f, -0.5f, 0.0f, -0.2f, -0.2f, -0.2f, -0.05f, -0.2f, -0.2f, -0.05f, -0.5f, -0.2f, -0.2f, -0.5f, -0.2f, -0.2f, -0.2f, -0.2f, -0.05f);
        this.slideModel[1].setRotationPoint(6.35f, -3.5f, -0.5f);
        this.slideModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 1, 0.0f, 0.0f, -0.45f, -0.05f, 0.2f, -0.45f, -0.05f, 0.2f, -0.45f, -0.05f, 0.0f, -0.45f, -0.05f, 0.1f, -0.45f, -0.05f, 0.2f, -0.45f, -0.05f, 0.2f, -0.45f, -0.05f, 0.1f, -0.45f, -0.05f);
        this.slideModel[2].setRotationPoint(3.35f, -3.85f, -0.5f);
        this.slideModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, 0.0f, -0.2f, -0.3f, 0.0f, -0.2f, -0.3f, 0.0f, -0.2f, -0.3f, 0.0f, -0.2f, -0.3f, -0.2f, -0.2f, -0.3f, -0.3f, -0.2f, -0.3f, -0.3f, -0.2f, -0.3f, -0.2f, -0.2f);
        this.slideModel[3].setRotationPoint(6.55f, -3.5f, -0.5f);
        this.slideModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.45f, -0.35f, -0.3f, -0.45f, -0.35f, -0.3f, -0.45f, -0.35f, -0.3f, -0.45f, -0.35f, -0.1f, -0.35f, -0.2f, -0.3f, -0.35f, -0.2f, -0.3f, -0.35f, -0.2f, -0.1f, -0.35f, -0.2f);
        this.slideModel[4].setRotationPoint(6.55f, -4.15f, -0.5f);
        this.slideModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, -0.2f, -0.05f, -0.7f, -0.1f, -0.2f, -0.7f, -0.1f, -0.2f, -0.2f, -0.2f, -0.05f, -0.2f, -0.7f, -0.05f, -0.5f, -0.9f, -0.2f, -0.5f, -0.9f, -0.2f, -0.2f, -0.7f, -0.05f);
        this.slideModel[5].setRotationPoint(6.35f, -3.6f, -0.5f);
        this.slideModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 1, 0.0f, -0.3f, -0.25f, -0.35f, 0.0f, -0.25f, -0.35f, 0.0f, -0.25f, -0.35f, -0.3f, -0.25f, -0.35f, 0.1f, -0.3f, -0.05f, 0.0f, -0.3f, -0.05f, 0.0f, -0.3f, -0.05f, 0.1f, -0.3f, -0.05f);
        this.slideModel[6].setRotationPoint(-0.65f, -4.0f, -0.5f);
        this.slideModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.35f, -0.3f, -0.45f, -0.35f, -0.3f, -0.45f, -0.35f, -0.3f, -0.45f, -0.35f, -0.3f, -0.45f);
        this.slideModel[7].setRotationPoint(-0.4f, -4.25f, -0.65f);
        this.slideModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.35f, -0.3f, -0.45f, -0.35f, -0.3f, -0.45f, -0.35f, -0.3f, -0.45f, -0.35f, -0.3f, -0.45f);
        this.slideModel[8].setRotationPoint(-0.4f, -4.25f, -0.35f);
        this.slideModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.35f, -0.4f, -0.45f, -0.35f, -0.4f, -0.45f, -0.35f, -0.4f, -0.45f, -0.35f, -0.4f, -0.45f, -0.3f, -0.4f, -0.45f, -0.3f, -0.4f, -0.45f, -0.3f, -0.4f, -0.45f, -0.3f, -0.4f);
        this.slideModel[9].setRotationPoint(-0.4f, -4.17f, -0.5f);
        this.slideModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f);
        this.slideModel[10].setRotationPoint(-0.4f, -4.23f, -0.61f);
        this.slideModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f, -0.45f, -0.3f, -0.45f);
        this.slideModel[11].setRotationPoint(-0.4f, -4.23f, -0.39f);
        this.slideModel[12].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.35f, -0.47f, -0.4f, -0.35f, -0.47f, -0.4f, -0.35f, -0.47f, -0.4f, -0.35f, -0.47f, -0.2f, -0.3f, -0.47f, -0.4f, -0.3f, -0.47f, -0.4f, -0.3f, -0.47f, -0.2f, -0.3f, -0.47f);
        this.slideModel[12].setRotationPoint(6.6f, -4.2f, -0.5f);
        this.slideModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, -0.35f, -0.1f, -0.2f, -0.35f, -0.1f, -0.2f, -0.35f, -0.1f, -0.2f, -0.35f, -0.1f, -0.2f, -0.35f, 0.0f, -0.2f, -0.35f, 0.0f, -0.2f, -0.35f, 0.0f, -0.2f, -0.35f, 0.0f);
        this.slideModel[13].setRotationPoint(-0.6f, -3.9f, -0.5f);
        this.slideModel[14].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, -0.42f, 0.05f, -0.2f, -0.42f, 0.05f, -0.2f, -0.42f, -0.05f, -0.2f, -0.42f, -0.05f, -0.2f, -0.42f, 0.1f, -0.2f, -0.42f, 0.1f, -0.2f, -0.42f, 0.0f, -0.2f, -0.42f, 0.0f);
        this.slideModel[14].setRotationPoint(-0.25f, -3.9f, -0.45f);
        this.translateAll(0.0f, -2.5f, 0.0f);
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
   
        this.barrelAttachPoint = new Vector3f(0.45625f, 0.3f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.1875f, 0.3125f, 0.0f);
        this.scopeIsOnSlide = true;
        this.gunSlideDistance = 0.25f;
        this.animationType = EnumAnimationType.PISTOL_CLIP;
    }
}
