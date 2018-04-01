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

public class ModelCoat
extends ModelCustomArmour {
    public ModelCoat() {
        int textureX = 64;
        int textureY = 64;
        this.bodyModel = new ModelRendererTurbo[1];
        this.bodyModel[0] = new ModelRendererTurbo((ModelBase)this, 0, 0, textureX, textureY);
        this.bodyModel[0].addBox(-5.0f, -0.5f, -3.0f, 10, 13, 6);
        this.skirtFrontModel = new ModelRendererTurbo[1];
        this.skirtFrontModel[0] = new ModelRendererTurbo((ModelBase)this, 32, 0, textureX, textureY);
        this.skirtFrontModel[0].addBox(-5.0f, 0.0f, -3.0f, 10, 12, 6);
        this.skirtRearModel = new ModelRendererTurbo[1];
        this.skirtRearModel[0] = new ModelRendererTurbo((ModelBase)this, 0, 19, textureX, textureY);
        this.skirtRearModel[0].addBox(-5.0f, 0.0f, -3.0f, 10, 12, 6);
        this.leftArmModel = new ModelRendererTurbo[1];
        this.leftArmModel[0] = new ModelRendererTurbo((ModelBase)this, 32, 19, textureX, textureY);
        this.leftArmModel[0].addBox(-1.5f, -2.5f, -2.5f, 5, 13, 5);
        this.rightArmModel = new ModelRendererTurbo[1];
        this.rightArmModel[0] = new ModelRendererTurbo((ModelBase)this, 32, 41, textureX, textureY);
        this.rightArmModel[0].addBox(-3.5f, -2.5f, -2.5f, 5, 13, 5);
    }
}

