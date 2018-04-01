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

public class ModelRecruitBoots
extends ModelCustomArmour {
    int textureX = 128;
    int textureY = 128;

    public ModelRecruitBoots() {
        this.leftLegModel = new ModelRendererTurbo[1];
        this.leftLegModel[0] = new ModelRendererTurbo((ModelBase)this, 65, 49, this.textureX, this.textureY);
        this.leftLegModel[0].addShapeBox(-2.2f, 7.0f, -2.0f, 8, 12, 4, 0.0f, 0.1f, -1.0f, 0.35f, -3.5f, -1.0f, 0.35f, -3.5f, -1.0f, 0.35f, 0.1f, -1.0f, 0.35f, 0.15f, -7.0f, 0.35f, -3.5f, -7.0f, 0.35f, -3.59f, -7.0f, 0.35f, 0.15f, -7.0f, 0.35f);
        this.leftLegModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightLegModel = new ModelRendererTurbo[1];
        this.rightLegModel[0] = new ModelRendererTurbo((ModelBase)this, 33, 49, this.textureX, this.textureY);
        this.rightLegModel[0].addShapeBox(-2.2f, 7.0f, -2.0f, 8, 12, 4, 0.0f, 0.1f, -1.0f, 0.35f, -3.5f, -1.0f, 0.35f, -3.5f, -1.0f, 0.35f, 0.1f, -1.0f, 0.35f, 0.15f, -7.0f, 0.35f, -3.5f, -7.0f, 0.35f, -3.59f, -7.0f, 0.35f, 0.15f, -7.0f, 0.35f);
        this.rightLegModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
    }
}

