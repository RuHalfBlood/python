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

public class ModelPoliceHat
extends ModelCustomArmour {
    int textureX = 64;
    int textureY = 32;

    public ModelPoliceHat() {
        this.headModel = new ModelRendererTurbo[9];
        this.headModel[0] = new ModelRendererTurbo((ModelBase)this, 0, 0, this.textureX, this.textureY);
        this.headModel[1] = new ModelRendererTurbo((ModelBase)this, 0, 12, this.textureX, this.textureY);
        this.headModel[2] = new ModelRendererTurbo((ModelBase)this, 0, 19, this.textureX, this.textureY);
        this.headModel[3] = new ModelRendererTurbo((ModelBase)this, 0, 23, this.textureX, this.textureY);
        this.headModel[4] = new ModelRendererTurbo((ModelBase)this, 28, 0, this.textureX, this.textureY);
        this.headModel[5] = new ModelRendererTurbo((ModelBase)this, 32, 7, this.textureX, this.textureY);
        this.headModel[6] = new ModelRendererTurbo((ModelBase)this, 31, 3, this.textureX, this.textureY);
        this.headModel[7] = new ModelRendererTurbo((ModelBase)this, 40, 3, this.textureX, this.textureY);
        this.headModel[8] = new ModelRendererTurbo((ModelBase)this, 30, 17, this.textureX, this.textureY);
        this.headModel[0].addBox(-4.5f, -8.0f, -4.5f, 9, 2, 9, 0.0f);
        this.headModel[1].addShapeBox(-5.5f, -9.0f, -0.5f, 11, 1, 5, 0.0f, 0.0f, 0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f);
        this.headModel[2].addShapeBox(-4.5f, -7.0f, -6.5f, 9, 1, 2, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.0f, 0.0f, 0.0f, -0.0f, 0.0f, 0.0f, -0.0f, 0.0f, 0.0f, -0.0f, 0.0f);
        this.headModel[3].addShapeBox(-4.5f, -7.0f, -7.5f, 9, 1, 1, 0.0f, -1.0f, -0.4f, 0.0f, -1.0f, -0.4f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.headModel[4].addShapeBox(-5.5f, -9.0f, 4.5f, 11, 1, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -1.5f, 0.0f, -0.5f, -1.5f, 0.0f, -0.5f);
        this.headModel[5].addShapeBox(-5.5f, -11.0f, -5.0f, 11, 3, 5, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f);
        this.headModel[6].addShapeBox(-1.0f, -10.0f, -5.5f, 2, 2, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.headModel[7].addShapeBox(-1.0f, -8.0f, -5.5f, 2, 1, 1, 0.0f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.25f, 0.0f, -0.4f, -0.25f, 0.0f, -0.4f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f);
        this.headModel[8].addShapeBox(-5.0f, -8.0f, -5.0f, 10, 1, 5, 0.0f, -0.4f, -0.6f, -0.4f, -0.4f, -0.6f, -0.4f, -0.4f, -0.6f, -0.4f, -0.4f, -0.6f, -0.4f, -0.4f, 0.0f, -0.4f, -0.4f, 0.0f, -0.4f, -0.4f, 0.0f, -0.4f, -0.4f, 0.0f, -0.4f);
    }
}

