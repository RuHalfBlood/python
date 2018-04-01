/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package com.flansmod.client.model.zombie;

import com.flansmod.client.model.ModelCustomArmour;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;

public class ModelPanHelmet
extends ModelCustomArmour {
    public ModelPanHelmet() {
        int textureX = 64;
        int textureY = 16;
        this.headModel = new ModelRendererTurbo[3];
        this.headModel[0] = new ModelRendererTurbo((ModelBase)this, 0, 0, textureX, textureY);
        this.headModel[0].addBox(-4.5f, -9.0f, -4.5f, 9, 4, 9);
        this.headModel[1] = new ModelRendererTurbo((ModelBase)this, 2, 2, textureX, textureY);
        this.headModel[1].addBox(-3.5f, -10.0f, -3.5f, 7, 1, 7);
        this.headModel[2] = new ModelRendererTurbo((ModelBase)this, 27, 0, textureX, textureY);
        this.headModel[2].addBox(-9.5f, -7.0f, -1.0f, 5, 2, 2);
    }
}

