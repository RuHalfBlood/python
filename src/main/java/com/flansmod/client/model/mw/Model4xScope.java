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

public class Model4xScope
extends ModelAttachment {
    int textureX = 32;
    int textureY = 32;

    public Model4xScope() {
        this.attachmentModel = new ModelRendererTurbo[7];
        this.attachmentModel[0] = new ModelRendererTurbo(this, 0, 0, this.textureX, this.textureY);
        this.attachmentModel[1] = new ModelRendererTurbo(this, 0, 10, this.textureX, this.textureY);
        this.attachmentModel[2] = new ModelRendererTurbo(this, 0, 10, this.textureX, this.textureY);
        this.attachmentModel[3] = new ModelRendererTurbo(this, 0, 15, this.textureX, this.textureY);
        this.attachmentModel[4] = new ModelRendererTurbo(this, 9, 15, this.textureX, this.textureY);
        this.attachmentModel[5] = new ModelRendererTurbo(this, 0, 20, this.textureX, this.textureY);
        this.attachmentModel[6] = new ModelRendererTurbo(this, 0, 20, this.textureX, this.textureY);
        this.attachmentModel[0].addBox(0.0f, 0.0f, 0.0f, 9, 1, 1, 0.0f);
        this.attachmentModel[0].setRotationPoint(-4.0f, -2.0f, -0.5f);
        this.attachmentModel[1].addBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f);
        this.attachmentModel[1].setRotationPoint(-2.0f, -1.0f, -0.5f);
        this.attachmentModel[2].addBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f);
        this.attachmentModel[2].setRotationPoint(2.0f, -1.0f, -0.5f);
        this.attachmentModel[3].addBox(0.0f, 0.0f, 0.0f, 2, 2, 2, 0.0f);
        this.attachmentModel[3].setRotationPoint(-5.0f, -2.5f, -1.0f);
        this.attachmentModel[4].addBox(0.0f, 0.0f, 0.0f, 2, 2, 2, 0.0f);
        this.attachmentModel[4].setRotationPoint(4.0f, -2.5f, -1.0f);
        this.attachmentModel[5].addBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.attachmentModel[5].setRotationPoint(-1.0f, -2.5f, -0.5f);
        this.attachmentModel[6].addBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.attachmentModel[6].setRotationPoint(-1.0f, -2.0f, -1.0f);
        this.renderOffset = 0.0f;
        this.flipAll();
    }
}

