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

public class ModelApron
extends ModelCustomArmour {
    public ModelApron() {
        int textureX = 64;
        int textureY = 32;
        this.bodyModel = new ModelRendererTurbo[1];
        this.bodyModel[0] = new ModelRendererTurbo((ModelBase)this, 0, 0, textureX, textureY);
        this.bodyModel[0].addBox(-5.0f, 0.0f, -3.0f, 10, 12, 6);
        this.skirtFrontModel = new ModelRendererTurbo[1];
        this.skirtFrontModel[0] = new ModelRendererTurbo((ModelBase)this, 32, 0, textureX, textureY);
        this.skirtFrontModel[0].addBox(-5.0f, 0.0f, -3.0f, 10, 12, 6);
    }
}

