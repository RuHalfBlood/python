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

public class ModelForegrip
extends ModelAttachment {
    int textureX = 32;
    int textureY = 32;

    public ModelForegrip() {
        this.attachmentModel = new ModelRendererTurbo[2];
        this.attachmentModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.attachmentModel[1] = new ModelRendererTurbo(this, 17, 1, this.textureX, this.textureY);
        this.attachmentModel[0].addShapeBox(-1.5f, -0.25f, -1.0f, 3, 1, 2, 0.0f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f);
        this.attachmentModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.attachmentModel[1].addShapeBox(-1.0f, 0.75f, -1.0f, 2, 6, 2, 0.0f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, 0.0f, -0.3f, -0.3f, -3.0f, -0.3f, -0.3f, -3.0f, -0.3f, -0.3f, -3.0f, -0.3f, -0.3f, -3.0f, -0.3f);
        this.attachmentModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.flipAll();
    }
}

