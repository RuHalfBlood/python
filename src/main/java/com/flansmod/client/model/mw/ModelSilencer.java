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

public class ModelSilencer
extends ModelAttachment {
    int textureX = 32;
    int textureY = 32;

    public ModelSilencer() {
        this.attachmentModel = new ModelRendererTurbo[2];
        this.attachmentModel[0] = new ModelRendererTurbo(this, 0, 0, this.textureX, this.textureY);
        this.attachmentModel[1] = new ModelRendererTurbo(this, 0, 3, this.textureX, this.textureY);
        this.attachmentModel[0].addShapeBox(0.0f, -1.0f, -1.0f, 6, 1, 2, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.attachmentModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[1].addShapeBox(0.0f, 0.0f, -1.0f, 6, 1, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f);
        this.attachmentModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.flipAll();
    }
}

