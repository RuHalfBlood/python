/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.ModelCustomArmour;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;

public class ModelBandana
extends ModelCustomArmour {
    int textureX = 256;
    int textureY = 256;

    public ModelBandana() {
        this.headModel = new ModelRendererTurbo[1];
        this.headModel[0] = new ModelRendererTurbo((ModelBase)this, 1, 1, this.textureX, this.textureY);
        this.headModel[0].addShapeBox(-4.25f, -7.5f, -4.25f, 40, 20, 40, 0.0f, 0.0f, 0.0f, 0.0f, -31.5f, 0.0f, 0.0f, -31.5f, -0.5f, -31.5f, 0.0f, -0.5f, -31.5f, 0.0f, -17.5f, 0.0f, -31.5f, -17.5f, 0.0f, -31.5f, -17.0f, -31.5f, 0.0f, -17.0f, -31.5f);
        this.headModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
    }
}

