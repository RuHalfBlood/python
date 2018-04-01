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

public class ModelRecruitHat
extends ModelCustomArmour {
    int textureX = 128;
    int textureY = 128;

    public ModelRecruitHat() {
        this.headModel = new ModelRendererTurbo[4];
        this.headModel[0] = new ModelRendererTurbo((ModelBase)this, 105, 1, this.textureX, this.textureY);
        this.headModel[1] = new ModelRendererTurbo((ModelBase)this, 33, 25, this.textureX, this.textureY);
        this.headModel[2] = new ModelRendererTurbo((ModelBase)this, 89, 25, this.textureX, this.textureY);
        this.headModel[3] = new ModelRendererTurbo((ModelBase)this, 89, 25, this.textureX, this.textureY);
        this.headModel[0].addShapeBox(-4.0f, -6.0f, -6.0f, 8, 1, 2, 0.0f, 0.0f, -0.5f, -2.0f, 0.0f, -0.5f, -2.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.headModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.headModel[1].addShapeBox(-4.0f, -8.0f, -4.0f, 16, 3, 16, 0.0f, 0.1f, 0.0f, 0.1f, -7.9f, 0.0f, 0.1f, -7.9f, 0.0f, -7.9f, 0.1f, 0.0f, -7.9f, 0.1f, -1.0f, 0.1f, -7.9f, -1.0f, 0.1f, -7.9f, -1.0f, -7.9f, 0.1f, -1.0f, -7.9f);
        this.headModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.headModel[2].addShapeBox(-4.0f, -9.0f, -4.0f, 8, 1, 8, 0.0f, -0.9f, -0.5f, -0.9f, -0.9f, -0.5f, -0.9f, -0.9f, -0.5f, -0.9f, -0.9f, -0.5f, -0.9f, 0.1f, 0.0f, 0.1f, 0.1f, 0.0f, 0.1f, 0.1f, 0.0f, 0.1f, 0.1f, 0.0f, 0.1f);
        this.headModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.headModel[3].addShapeBox(-4.0f, -6.0f, -4.0f, 8, 1, 8, 0.0f, 0.1f, 0.0f, 0.1f, 0.1f, 0.0f, 0.1f, 0.1f, 0.0f, 0.1f, 0.1f, 0.0f, 0.1f, 0.1f, 0.0f, 0.1f, 0.1f, 0.0f, 0.1f, 0.1f, 0.0f, 0.1f, 0.1f, 0.0f, 0.1f);
        this.headModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
    }
}

