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

public class ModelExoskeletonBoots
extends ModelCustomArmour {
    public ModelExoskeletonBoots() {
        int textureX = 512;
        int textureY = 512;
        this.rightLegModel = new ModelRendererTurbo[3];
        this.rightLegModel[0] = new ModelRendererTurbo((ModelBase)this, 313, 113, textureX, textureY);
        this.rightLegModel[1] = new ModelRendererTurbo((ModelBase)this, 57, 121, textureX, textureY);
        this.rightLegModel[2] = new ModelRendererTurbo((ModelBase)this, 217, 121, textureX, textureY);
        float x = 8.5f;
        float y = 24.0f;
        float z = 1.0f;
        this.rightLegModel[0].addBox(-17.0f + x, 16.0f + y, -9.0f, 17, 8, 18, 0.0f);
        this.rightLegModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightLegModel[1].addBox(-17.0f + x, 19.0f + y, -10.0f, 16, 5, 6, 0.0f);
        this.rightLegModel[1].setRotationPoint(0.0f, 0.0f, -5.0f);
        this.rightLegModel[2].addBox(-17.0f + x, 11.0f + y, -10.0f, 16, 5, 19, 0.0f);
        this.rightLegModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftLegModel = new ModelRendererTurbo[3];
        this.leftLegModel[0] = new ModelRendererTurbo((ModelBase)this, 1, 121, textureX, textureY);
        this.leftLegModel[1] = new ModelRendererTurbo((ModelBase)this, 105, 121, textureX, textureY);
        this.leftLegModel[2] = new ModelRendererTurbo((ModelBase)this, 409, 121, textureX, textureY);
        this.leftLegModel[0].addBox(0.0f - x, 16.0f + y, -9.0f, 17, 8, 18, 0.0f);
        this.leftLegModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftLegModel[1].addBox(1.0f - x, 19.0f + y, -10.0f, 16, 5, 6, 0.0f);
        this.leftLegModel[1].setRotationPoint(0.0f, 0.0f, -5.0f);
        this.leftLegModel[2].addBox(1.0f - x, 11.0f + y, -10.0f, 16, 5, 19, 0.0f);
        this.leftLegModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
    }
}

