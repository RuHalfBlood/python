/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.flansmod.client.model.titan;

import com.flansmod.client.model.ModelMechaTool;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelDrill
extends ModelMechaTool {
    public ModelDrill() {
        int textureX = 32;
        int textureY = 32;
        this.baseModel = new ModelRendererTurbo[1];
        this.baseModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.baseModel[0].addBox(0.0f, -3.5f, -3.5f, 3, 7, 7);
        this.drillModel = new ModelRendererTurbo[4];
        this.drillModel[0] = new ModelRendererTurbo(this, 0, 14, textureX, textureY);
        this.drillModel[0].addBox(3.0f, -2.5f, -2.5f, 2, 5, 5);
        this.drillModel[0].rotateAngleX = -0.34906587f;
        this.drillModel[1] = new ModelRendererTurbo(this, 13, 0, textureX, textureY);
        this.drillModel[1].addBox(5.0f, -1.5f, -1.5f, 2, 3, 3);
        this.drillModel[1].rotateAngleX = -0.69813174f;
        this.drillModel[2] = new ModelRendererTurbo(this, 9, 14, textureX, textureY);
        this.drillModel[2].addBox(7.0f, -0.5f, -0.5f, 2, 1, 1);
        this.drillModel[2].rotateAngleX = -1.0471976f;
        this.drillModel[3] = new ModelRendererTurbo(this, 9, 14, textureX, textureY);
    }
}

