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

public class ModelRocketPack
extends ModelMechaTool {
    public ModelRocketPack() {
        int textureX = 64;
        int textureY = 64;
        this.baseModel = new ModelRendererTurbo[5];
        this.baseModel[0] = new ModelRendererTurbo(this, 0, 27, textureX, textureY);
        this.baseModel[0].addBox(-1.0f, -10.0f, -8.0f, 1, 20, 16);
        this.baseModel[1] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.baseModel[1].addBox(-7.0f, -11.0f, -7.0f, 6, 21, 6);
        this.baseModel[2] = new ModelRendererTurbo(this, 24, 6, textureX, textureY);
        this.baseModel[2].addBox(-7.0f, -11.0f, 1.0f, 6, 21, 6);
        this.baseModel[3] = new ModelRendererTurbo(this, 0, 27, textureX, textureY);
        this.baseModel[3].addBox(-6.0f, -15.0f, -6.0f, 4, 4, 4);
        this.baseModel[4] = new ModelRendererTurbo(this, 0, 35, textureX, textureY);
        this.baseModel[4].addBox(-6.0f, -15.0f, 2.0f, 4, 4, 4);
    }
}

