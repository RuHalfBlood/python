/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.ModelAttachment;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;

public class ModelRedDot
extends ModelAttachment {
    int textureX = 32;
    int textureY = 32;

    public ModelRedDot() {
        this.attachmentModel = new ModelRendererTurbo[17];
        this.attachmentModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.attachmentModel[1] = new ModelRendererTurbo(this, 17, 1, this.textureX, this.textureY);
        this.attachmentModel[2] = new ModelRendererTurbo(this, 25, 1, this.textureX, this.textureY);
        this.attachmentModel[3] = new ModelRendererTurbo(this, 1, 9, this.textureX, this.textureY);
        this.attachmentModel[4] = new ModelRendererTurbo(this, 9, 9, this.textureX, this.textureY);
        this.attachmentModel[5] = new ModelRendererTurbo(this, 17, 9, this.textureX, this.textureY);
        this.attachmentModel[6] = new ModelRendererTurbo(this, 25, 9, this.textureX, this.textureY);
        this.attachmentModel[7] = new ModelRendererTurbo(this, 1, 17, this.textureX, this.textureY);
        this.attachmentModel[8] = new ModelRendererTurbo(this, 9, 17, this.textureX, this.textureY);
        this.attachmentModel[9] = new ModelRendererTurbo(this, 17, 17, this.textureX, this.textureY);
        this.attachmentModel[10] = new ModelRendererTurbo(this, 25, 17, this.textureX, this.textureY);
        this.attachmentModel[11] = new ModelRendererTurbo(this, 1, 25, this.textureX, this.textureY);
        this.attachmentModel[12] = new ModelRendererTurbo(this, 9, 25, this.textureX, this.textureY);
        this.attachmentModel[13] = new ModelRendererTurbo(this, 17, 12, this.textureX, this.textureY);
        this.attachmentModel[14] = new ModelRendererTurbo(this, 17, 12, this.textureX, this.textureY);
        this.attachmentModel[15] = new ModelRendererTurbo(this, 17, 12, this.textureX, this.textureY);
        this.attachmentModel[16] = new ModelRendererTurbo(this, 17, 12, this.textureX, this.textureY);
        this.attachmentModel[0].addShapeBox(-1.5f, -1.0f, -1.0f, 2, 1, 2, 0.0f, 0.0f, -0.5f, -0.5f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.5f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.5f);
        this.attachmentModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[1].addShapeBox(0.25f, -1.0f, -1.0f, 1, 1, 2, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, 0.0f, -0.25f, -0.05f, 0.0f, -0.25f, -0.05f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f);
        this.attachmentModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[2].addShapeBox(-0.1f, -2.75f, 0.0f, 1, 1, 1, 0.0f, -0.3f, -0.5f, -0.8f, -0.2f, -0.5f, -0.8f, -0.2f, -0.5f, 0.0f, -0.3f, -0.5f, 0.0f, -0.4f, 0.0f, -0.9f, -0.1f, 0.0f, -0.9f, -0.1f, 0.0f, 0.1f, -0.4f, 0.0f, 0.1f);
        this.attachmentModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[3].addShapeBox(-0.1f, -2.95f, -1.0f, 1, 1, 2, 0.0f, -0.3f, -0.5f, -0.2f, -0.3f, -0.5f, -0.2f, -0.3f, -0.5f, -0.2f, -0.3f, -0.5f, -0.2f, -0.3f, -0.3f, 0.0f, -0.2f, -0.3f, 0.0f, -0.2f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f);
        this.attachmentModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[4].addShapeBox(-0.5f, -0.75f, -1.0f, 1, 1, 2, 0.0f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f);
        this.attachmentModel[4].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[5].addShapeBox(0.2f, -2.0f, -0.5f, 1, 1, 1, 0.0f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f);
        this.attachmentModel[5].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[6].addShapeBox(-1.5f, -0.75f, -1.0f, 1, 1, 2, 0.0f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f, -0.25f, -0.35f, -0.25f);
        this.attachmentModel[6].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[7].addShapeBox(-1.4f, -0.75f, -0.5f, 1, 1, 1, 0.0f, -0.2f, -0.1f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, -0.1f, -0.2f, -0.2f, -0.4f, -0.2f, -0.2f, -0.4f, -0.2f, -0.2f, -0.4f, -0.2f, -0.2f, -0.4f, -0.2f);
        this.attachmentModel[7].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[8].addShapeBox(0.3f, -1.25f, 0.0f, 1, 1, 1, 0.0f, -0.1f, 0.0f, -0.9f, -0.4f, 0.0f, -0.9f, -0.4f, 0.0f, 0.1f, -0.1f, 0.0f, 0.1f, -0.2f, -0.5f, -0.8f, -0.3f, -0.5f, -0.8f, -0.3f, -0.5f, 0.0f, -0.2f, -0.5f, 0.0f);
        this.attachmentModel[8].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[9].addShapeBox(0.2f, -1.75f, 0.1f, 1, 1, 1, 0.0f, -0.1f, 0.0f, -0.8f, -0.4f, 0.0f, -0.8f, -0.4f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.2f, -0.5f, -0.8f, -0.3f, -0.5f, -0.8f, -0.3f, -0.5f, 0.0f, -0.2f, -0.5f, 0.0f);
        this.attachmentModel[9].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[10].addShapeBox(-0.1f, -2.75f, -1.0f, 1, 1, 1, 0.0f, -0.3f, -0.5f, 0.0f, -0.2f, -0.5f, 0.0f, -0.2f, -0.5f, -0.8f, -0.3f, -0.5f, -0.8f, -0.4f, 0.0f, 0.1f, -0.1f, 0.0f, 0.1f, -0.1f, 0.0f, -0.9f, -0.4f, 0.0f, -0.9f);
        this.attachmentModel[10].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[11].addShapeBox(0.2f, -1.75f, -1.1f, 1, 1, 1, 0.0f, -0.1f, 0.0f, 0.0f, -0.4f, 0.0f, 0.0f, -0.4f, 0.0f, -0.8f, -0.1f, 0.0f, -0.8f, -0.2f, -0.5f, 0.0f, -0.3f, -0.5f, 0.0f, -0.3f, -0.5f, -0.8f, -0.2f, -0.5f, -0.8f);
        this.attachmentModel[11].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[12].addShapeBox(0.3f, -1.25f, -1.0f, 1, 1, 1, 0.0f, -0.1f, 0.0f, 0.1f, -0.4f, 0.0f, 0.1f, -0.4f, 0.0f, -0.9f, -0.1f, 0.0f, -0.9f, -0.2f, -0.5f, 0.0f, -0.3f, -0.5f, 0.0f, -0.3f, -0.5f, -0.8f, -0.2f, -0.5f, -0.8f);
        this.attachmentModel[12].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[13].addShapeBox(0.2f, -2.25f, -0.5f, 1, 1, 1, 0.0f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f);
        this.attachmentModel[13].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[14].addShapeBox(0.2f, -1.85f, -0.5f, 1, 1, 1, 0.0f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f);
        this.attachmentModel[14].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[15].addShapeBox(0.2f, -2.0f, -0.65f, 1, 1, 1, 0.0f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f);
        this.attachmentModel[15].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[16].addShapeBox(0.2f, -2.0f, -0.25f, 1, 1, 1, 0.0f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f, -0.45f);
        this.attachmentModel[16].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.flipAll();
    }
}

