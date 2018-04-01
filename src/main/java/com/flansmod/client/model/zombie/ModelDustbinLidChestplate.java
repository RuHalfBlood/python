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

public class ModelDustbinLidChestplate
extends ModelCustomArmour {
    public ModelDustbinLidChestplate() {
        int textureX = 32;
        int textureY = 64;
        this.bodyModel = new ModelRendererTurbo[9];
        this.bodyModel[0] = new ModelRendererTurbo((ModelBase)this, 0, 0, textureX, textureY);
        this.bodyModel[0].addBox(-4.0f, 0.0f, -3.0f, 8, 12, 1);
        this.bodyModel[1] = new ModelRendererTurbo((ModelBase)this, 0, 13, textureX, textureY);
        this.bodyModel[1].addBox(-6.0f, 2.0f, -3.0f, 2, 8, 1);
        this.bodyModel[2] = new ModelRendererTurbo((ModelBase)this, 6, 13, textureX, textureY);
        this.bodyModel[2].addBox(4.0f, 2.0f, -3.0f, 2, 8, 1);
        this.bodyModel[3] = new ModelRendererTurbo((ModelBase)this, 0, 22, textureX, textureY);
        this.bodyModel[3].addBox(-4.0f, 2.0f, -4.0f, 8, 8, 1);
        this.bodyModel[4] = new ModelRendererTurbo((ModelBase)this, 0, 31, textureX, textureY);
        this.bodyModel[4].addBox(-2.0f, 5.5f, -6.0f, 4, 1, 1);
        this.bodyModel[5] = new ModelRendererTurbo((ModelBase)this, 10, 31, textureX, textureY);
        this.bodyModel[5].addBox(-2.0f, 5.5f, -5.0f, 1, 1, 1);
        this.bodyModel[6] = new ModelRendererTurbo((ModelBase)this, 14, 31, textureX, textureY);
        this.bodyModel[6].addBox(1.0f, 5.5f, -5.0f, 1, 1, 1);
        this.bodyModel[7] = new ModelRendererTurbo((ModelBase)this, 0, 33, textureX, textureY);
        this.bodyModel[7].addBox(-4.0f, -0.5f, -2.5f, 1, 13, 5);
        this.bodyModel[8] = new ModelRendererTurbo((ModelBase)this, 0, 33, textureX, textureY);
        this.bodyModel[8].addBox(3.0f, -0.5f, -2.5f, 1, 13, 5);
    }
}

