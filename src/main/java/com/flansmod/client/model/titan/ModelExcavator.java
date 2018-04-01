/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package com.flansmod.client.model.titan;

import com.flansmod.client.model.ModelMechaTool;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;

public class ModelExcavator
extends ModelMechaTool {
    public ModelExcavator() {
        int textureX = 32;
        int textureY = 32;
        this.baseModel = new ModelRendererTurbo[1];
        this.baseModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.baseModel[0].addBox(0.0f, -3.5f, -3.5f, 3, 7, 7);
        this.drillModel = new ModelRendererTurbo[9];
        this.drillModel[0] = new ModelRendererTurbo(this, 13, 0, textureX, textureY);
        this.drillModel[0].addBox(3.0f, -0.5f, -0.5f, 1, 1, 1);
        this.drillModel[1] = new ModelRendererTurbo(this, 13, 2, textureX, textureY);
        this.drillModel[1].addBox(4.0f, -1.0f, -0.5f, 1, 1, 1);
        this.drillModel[2] = new ModelRendererTurbo(this, 13, 4, textureX, textureY);
        this.drillModel[2].addBox(5.0f, -1.5f, -0.5f, 1, 1, 1);
        this.drillModel[3] = new ModelRendererTurbo(this, 17, 0, textureX, textureY);
        this.drillModel[3].addBox(6.0f, -2.0f, -0.5f, 2, 1, 1);
        this.drillModel[4] = new ModelRendererTurbo(this, 0, 14, textureX, textureY);
        this.drillModel[4].addBox(8.0f, -2.5f, -1.0f, 7, 1, 2);
        this.drillModel[5] = new ModelRendererTurbo(this, 17, 2, textureX, textureY);
        this.drillModel[5].addBox(8.0f, -1.5f, -4.0f, 5, 1, 1);
        this.drillModel[6] = new ModelRendererTurbo(this, 17, 4, textureX, textureY);
        this.drillModel[6].addBox(8.0f, -1.5f, 3.0f, 5, 1, 1);
        this.drillModel[7] = new ModelRendererTurbo(this, 0, 17, textureX, textureY);
        this.drillModel[7].addBox(8.0f, -2.0f, -3.0f, 6, 1, 2);
        this.drillModel[8] = new ModelRendererTurbo(this, 0, 20, textureX, textureY);
        this.drillModel[8].addBox(8.0f, -2.0f, 1.0f, 6, 1, 2);
    }
}

