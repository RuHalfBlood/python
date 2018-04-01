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

public class ModelCloakerGoggles
extends ModelCustomArmour {
    public ModelCloakerGoggles() {
        int textureX = 64;
        int textureY = 32;
        this.headModel = new ModelRendererTurbo[4];
        this.headModel[0] = new ModelRendererTurbo((ModelBase)this, 0, 0, textureX, textureY);
        this.headModel[0].addBox(-4.5f, -9.0f, -4.5f, 9, 9, 9);
        this.headModel[1] = new ModelRendererTurbo((ModelBase)this, 0, 18, textureX, textureY);
        this.headModel[1].addBox(-3.0f, -6.5f, -7.5f, 2, 2, 3);
        this.headModel[2] = new ModelRendererTurbo((ModelBase)this, 0, 18, textureX, textureY);
        this.headModel[2].addBox(1.0f, -6.5f, -7.5f, 2, 2, 3);
        this.headModel[3] = new ModelRendererTurbo((ModelBase)this, 0, 23, textureX, textureY);
        this.headModel[3].addBox(-2.0f, -3.5f, -6.5f, 4, 4, 2);
    }
}

