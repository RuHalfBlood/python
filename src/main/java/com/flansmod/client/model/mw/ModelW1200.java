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

public class ModelW1200
extends ModelGun {
    int textureX = 64;
    int textureY = 64;

    public ModelW1200() {
        this.gunModel = new ModelRendererTurbo[25];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 25, 1, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 41, 1, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 1, 9, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 1, 17, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 1, 25, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 49, 1, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 41, 17, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 49, 17, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 57, 17, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 33, 25, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 41, 25, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 49, 25, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 57, 25, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 1, 33, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 9, 33, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 25, 33, this.textureX, this.textureY);
        this.gunModel[17] = new ModelRendererTurbo(this, 33, 33, this.textureX, this.textureY);
        this.gunModel[18] = new ModelRendererTurbo(this, 41, 33, this.textureX, this.textureY);
        this.gunModel[19] = new ModelRendererTurbo(this, 49, 33, this.textureX, this.textureY);
        this.gunModel[20] = new ModelRendererTurbo(this, 17, 41, this.textureX, this.textureY);
        this.gunModel[21] = new ModelRendererTurbo(this, 25, 41, this.textureX, this.textureY);
        this.gunModel[22] = new ModelRendererTurbo(this, 33, 41, this.textureX, this.textureY);
        this.gunModel[23] = new ModelRendererTurbo(this, 41, 41, this.textureX, this.textureY);
        this.gunModel[24] = new ModelRendererTurbo(this, 49, 41, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 10, 2, 1, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 1.6f, 0.0f, 0.0f, 1.6f, 0.0f, 0.0f, -0.1f, 0.0f, -0.2f, 2.0f, 0.0f, 0.3f, -2.5f, 0.0f, 0.3f, -2.5f, 0.0f, -0.2f, 2.0f, 0.0f);
        this.gunModel[0].setRotationPoint(-9.8f, -2.0f, -0.5f);
        this.gunModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 5, 2, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.7f, 0.0f, 0.0f, 0.7f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.7f, 0.0f, 0.0f, 0.7f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[1].setRotationPoint(1.6f, -4.05f, -0.5f);
        this.gunModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, 0.4f, -0.45f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.4f, -0.45f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[2].setRotationPoint(0.6f, -4.05f, -0.5f);
        this.gunModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 17, 1, 1, 0.0f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f);
        this.gunModel[3].setRotationPoint(6.6f, -4.05f, -0.5f);
        this.gunModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 16, 1, 1, 0.0f, 0.0f, -0.15f, -0.15f, 0.5f, -0.15f, -0.15f, 0.5f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f, 0.5f, -0.15f, -0.15f, 0.5f, -0.15f, -0.15f, 0.0f, -0.15f, -0.15f);
        this.gunModel[4].setRotationPoint(6.6f, -3.05f, -0.5f);
        this.gunModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 12, 2, 1, 0.0f, -0.1f, -0.6f, -0.1f, -0.1f, -0.6f, -0.1f, -0.1f, -0.6f, -0.1f, -0.1f, -0.6f, -0.1f, -0.1f, -0.6f, -0.1f, -0.1f, -0.6f, -0.1f, -0.1f, -0.6f, -0.1f, -0.1f, -0.6f, -0.1f);
        this.gunModel[5].setRotationPoint(8.0f, -4.6f, -0.5f);
        this.gunModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 1, 0.0f, 0.0f, -0.15f, -0.35f, 0.0f, -0.15f, -0.35f, 0.0f, -0.15f, -0.35f, 0.0f, -0.15f, -0.35f, 0.0f, -0.15f, -0.35f, 0.0f, -0.15f, -0.35f, 0.0f, -0.15f, -0.35f, 0.0f, -0.15f, -0.35f);
        this.gunModel[6].setRotationPoint(6.6f, -3.5f, -0.5f);
        this.gunModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f);
        this.gunModel[7].setRotationPoint(22.5f, -3.05f, -0.5f);
        this.gunModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.1f, -0.1f, -0.35f, -0.3f, -0.1f, -0.35f, -0.3f, -0.1f, -0.35f, -0.1f, -0.1f, -0.35f, -0.4f, -0.08f, -0.35f, -0.4f, -0.1f, -0.35f, -0.4f, -0.1f, -0.35f, -0.4f, -0.08f, -0.35f);
        this.gunModel[8].setRotationPoint(0.2f, -2.2f, -0.5f);
        this.gunModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.1f, -0.1f, -0.35f, 0.7f, -0.1f, -0.35f, 0.7f, -0.1f, -0.35f, -0.1f, -0.1f, -0.35f, -0.4f, -0.1f, -0.35f, -0.4f, -0.1f, -0.35f, -0.4f, -0.1f, -0.35f, -0.4f, -0.1f, -0.35f);
        this.gunModel[9].setRotationPoint(0.2f, -2.6f, -0.5f);
        this.gunModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.45f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.45f, -0.3f, -0.35f);
        this.gunModel[10].setRotationPoint(0.12f, -1.2f, -0.5f);
        this.gunModel[10].rotateAngleZ = 0.7853982f;
        this.gunModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.25f, -0.35f, -0.4f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.4f, -0.25f, -0.35f, -0.45f, -0.3f, -0.35f, -0.43f, -0.28f, -0.35f, -0.43f, -0.28f, -0.35f, -0.45f, -0.3f, -0.35f);
        this.gunModel[11].setRotationPoint(2.3f, -2.0f, -0.5f);
        this.gunModel[11].rotateAngleZ = -0.9250245f;
        this.gunModel[12].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.43f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.43f, -0.3f, -0.35f, -0.42f, -0.3f, -0.35f, -0.45f, -0.3f, -0.35f, -0.45f, -0.3f, -0.35f, -0.42f, -0.3f, -0.35f);
        this.gunModel[12].setRotationPoint(0.65f, -0.6f, -0.5f);
        this.gunModel[12].rotateAngleZ = 1.553343f;
        this.gunModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.4f, -0.3f, -0.35f, -0.45f, -0.3f, -0.35f, -0.45f, -0.3f, -0.35f, -0.4f, -0.3f, -0.35f, -0.43f, -0.3f, -0.35f, -0.45f, -0.3f, -0.35f, -0.45f, -0.3f, -0.35f, -0.43f, -0.3f, -0.35f);
        this.gunModel[13].setRotationPoint(1.05f, -0.6f, -0.5f);
        this.gunModel[13].rotateAngleZ = 1.553343f;
        this.gunModel[14].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.42f, -0.35f, -0.35f, -0.45f, -0.35f, -0.35f, -0.45f, -0.35f, -0.35f, -0.42f, -0.35f, -0.35f, -0.47f, -0.35f, -0.35f, -0.42f, -0.35f, -0.35f, -0.42f, -0.35f, -0.35f, -0.47f, -0.35f, -0.35f);
        this.gunModel[14].setRotationPoint(1.48f, -0.55f, -0.5f);
        this.gunModel[14].rotateAngleZ = 1.727876f;
        this.gunModel[15].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f, -0.35f, 0.0f, -0.35f, -0.35f, 0.1f, -0.35f, -0.35f, 0.1f, -0.35f, -0.35f, 0.1f, -0.35f, -0.35f, 0.1f, -0.35f);
        this.gunModel[15].setRotationPoint(0.8f, -2.4f, -0.5f);
        this.gunModel[15].rotateAngleZ = 0.17453294f;
        this.gunModel[16].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.38f, -0.38f, -0.25f, -0.38f, -0.38f, -0.25f, -0.38f, -0.38f, -0.25f, -0.38f, -0.38f, -0.25f, -0.38f, -0.38f, -0.25f, -0.38f, -0.38f, -0.25f, -0.38f, -0.38f, -0.25f, -0.38f, -0.38f, -0.25f);
        this.gunModel[16].setRotationPoint(0.15f, -2.35f, -0.5f);
        this.gunModel[17].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f, -0.1f, -0.1f, -0.3f);
        this.gunModel[17].setRotationPoint(4.6f, -4.1f, -0.87f);
        this.gunModel[18].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.35f, -0.3f, -0.3f, -0.35f, -0.1f, -0.3f, -0.35f, -0.1f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.1f, -0.3f, -0.35f, -0.1f, -0.3f, -0.35f, -0.3f, -0.3f);
        this.gunModel[18].setRotationPoint(4.05f, -4.1f, -0.87f);
        this.gunModel[19].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.35f, -0.1f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.1f, -0.3f, -0.35f, -0.1f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.3f, -0.3f, -0.35f, -0.1f, -0.3f);
        this.gunModel[19].setRotationPoint(6.15f, -4.1f, -0.87f);
        this.gunModel[20].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.4f, -0.45f, -0.45f, -0.4f, -0.45f, -0.45f, -0.4f, -0.45f, -0.45f, -0.4f, -0.45f, -0.45f, 0.0f, -0.45f, -0.45f, 0.0f, -0.45f, -0.45f, 0.0f, -0.45f, -0.45f, 0.0f, -0.45f);
        this.gunModel[20].setRotationPoint(22.35f, -4.6f, -0.5f);
        this.gunModel[21].addShapeBox(0.0f, 0.0f, 0.0f, 1, 4, 1, 0.0f, 0.2f, -0.65f, 0.0f, 0.2f, -0.05f, 0.0f, 0.2f, -0.05f, 0.0f, 0.2f, -0.65f, 0.0f, 1.5f, -0.5f, 0.0f, -0.6f, -0.2f, 0.0f, -0.6f, -0.2f, 0.0f, 1.5f, -0.5f, 0.0f);
        this.gunModel[21].setRotationPoint(-0.9f, -2.45f, -0.5f);
        this.gunModel[22].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, 0.0f, -0.01f, 0.0f, 0.0f, -0.01f, 0.0f, 0.0f, -0.01f, 0.0f, 0.0f, -0.01f, 0.0f, 0.0f, -0.01f, 0.1f, -0.3f, -0.01f, 0.1f, -0.3f, -0.01f, 0.0f, 0.0f, -0.01f);
        this.gunModel[22].setRotationPoint(-0.5f, -2.6f, -0.5f);
        this.gunModel[23].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, 0.0f, -0.01f, 0.0f, 0.0f, -0.01f, 0.0f, 0.0f, -0.01f, 0.0f, 0.0f, -0.01f, 0.1f, -0.3f, -0.01f, 0.0f, 0.0f, -0.01f, 0.0f, 0.0f, -0.01f, 0.1f, -0.3f, -0.01f);
        this.gunModel[23].setRotationPoint(-1.5f, -2.3f, -0.5f);
        this.gunModel[24].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.1f, -0.35f, -0.1f, -0.1f, -0.35f, -0.1f, -0.1f, -0.35f, -0.3f, -0.1f, -0.35f, -0.4f, -0.1f, -0.35f, -0.4f, -0.08f, -0.35f, -0.4f, -0.08f, -0.35f, -0.4f, -0.1f, -0.35f);
        this.gunModel[24].setRotationPoint(1.81f, -2.25f, -0.5f);
        this.ammoModel = new ModelRendererTurbo[3];
        this.ammoModel[0] = new ModelRendererTurbo(this, 57, 33, this.textureX, this.textureY);
        this.ammoModel[1] = new ModelRendererTurbo(this, 1, 41, this.textureX, this.textureY);
        this.ammoModel[2] = new ModelRendererTurbo(this, 9, 41, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(5.0f, -3.05f, -0.5f, 2, 1, 1, 0.0f, 0.0f, -0.14f, -0.14f, 0.0f, -0.14f, -0.14f, 0.0f, -0.14f, -0.14f, 0.0f, -0.14f, -0.14f, 0.0f, -0.14f, -0.14f, 0.0f, -0.14f, -0.14f, 0.0f, -0.14f, -0.14f, 0.0f, -0.14f, -0.14f);
        this.ammoModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[1].addShapeBox(4.2f, -3.05f, -0.5f, 1, 1, 1, 0.0f, -0.2f, -0.12f, -0.12f, -0.2f, -0.12f, -0.12f, -0.2f, -0.12f, -0.12f, -0.2f, -0.12f, -0.12f, -0.2f, -0.12f, -0.12f, -0.2f, -0.12f, -0.12f, -0.2f, -0.12f, -0.12f, -0.2f, -0.12f, -0.12f);
        this.ammoModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[2].addShapeBox(3.9f, -3.05f, -0.5f, 1, 1, 1, 0.0f, -0.45f, -0.11f, -0.11f, -0.45f, -0.11f, -0.11f, -0.45f, -0.11f, -0.11f, -0.45f, -0.11f, -0.11f, -0.45f, -0.11f, -0.11f, -0.45f, -0.11f, -0.11f, -0.45f, -0.11f, -0.11f, -0.45f, -0.11f, -0.11f);
        this.ammoModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.pumpModel = new ModelRendererTurbo[3];
        this.pumpModel[0] = new ModelRendererTurbo(this, 41, 9, this.textureX, this.textureY);
        this.pumpModel[1] = new ModelRendererTurbo(this, 57, 9, this.textureX, this.textureY);
        this.pumpModel[2] = new ModelRendererTurbo(this, 57, 41, this.textureX, this.textureY);
        this.pumpModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 4, 2, 1, 0.0f, 0.5f, -0.15f, 0.1f, 0.5f, -0.15f, 0.1f, 0.5f, -0.15f, 0.1f, 0.5f, -0.15f, 0.1f, 0.5f, -0.15f, 0.1f, 0.5f, -0.15f, 0.1f, 0.5f, -0.15f, 0.1f, 0.5f, -0.15f, 0.1f);
        this.pumpModel[0].setRotationPoint(11.4f, -3.7f, -0.5f);
        this.pumpModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, -0.2f, -0.15f, 0.1f, -0.2f, -0.5f, 0.1f, -0.2f, -0.5f, 0.1f, -0.2f, -0.15f, 0.1f, -0.2f, -0.15f, 0.1f, -0.2f, -0.5f, 0.1f, -0.2f, -0.5f, 0.1f, -0.2f, -0.15f, 0.1f);
        this.pumpModel[1].setRotationPoint(15.7f, -3.7f, -0.5f);
        this.pumpModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, -0.2f, -0.5f, 0.1f, -0.2f, -0.15f, 0.1f, -0.2f, -0.15f, 0.1f, -0.2f, -0.5f, 0.1f, -0.2f, -0.5f, 0.1f, -0.2f, -0.15f, 0.1f, -0.2f, -0.15f, 0.1f, -0.2f, -0.5f, 0.1f);
        this.pumpModel[2].setRotationPoint(10.1f, -3.7f, -0.5f);
        this.gunSlideDistance = 0.4f;
        this.animationType = EnumAnimationType.SHOTGUN;
        this.pumpDelayAfterReload = 115;
        this.pumpDelay = 6;
        this.pumpTime = 9;
        this.gripIsOnPump = true;
        this.numBulletsInReloadAnimation = 7.0f;
        this.tiltGunTime = 0.159f;
        this.unloadClipTime = 0.0f;
        this.loadClipTime = 0.708f;
        this.untiltGunTime = 0.133f;
        this.scopeAttachPoint = new Vector3f(0.1875f, 0.30625f, 0.0f);
        this.gripAttachPoint = new Vector3f(0.84375, 0.17499999701976776, 0.0);
        this.translateAll(0.0f, -2.2f, 0.0f);
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

