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

public class ModelSuperHeavyHelmet
extends ModelCustomArmour {
    public ModelSuperHeavyHelmet() {
        int textureX = 128;
        int textureY = 64;
        this.headModel = new ModelRendererTurbo[10];
        this.headModel[0] = new ModelRendererTurbo((ModelBase)this, 28, 20, textureX, textureY);
        this.headModel[0].addBox(-4.5f, -9.0f, -4.5f, 9, 9, 9);
        this.headModel[1] = new ModelRendererTurbo((ModelBase)this, 20, 38, textureX, textureY);
        this.headModel[1].addBox(-3.0f, -10.0f, -3.0f, 6, 1, 6);
        this.headModel[2] = new ModelRendererTurbo((ModelBase)this, 38, 38, textureX, textureY);
        this.headModel[2].addBox(-2.0f, -11.0f, -2.0f, 4, 1, 4);
        this.headModel[3] = new ModelRendererTurbo((ModelBase)this, 20, 45, textureX, textureY);
        this.headModel[3].addShapeBox(-5.5f, -7.0f, -4.5f, 1, 7, 9, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.headModel[4] = new ModelRendererTurbo((ModelBase)this, 40, 45, textureX, textureY);
        this.headModel[4].addShapeBox(4.5f, -7.0f, -4.5f, 1, 7, 9, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 2.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f);
        this.headModel[5] = new ModelRendererTurbo((ModelBase)this, 64, 0, textureX, textureY);
        this.headModel[5].addShapeBox(-4.5f, -7.0f, 4.5f, 9, 7, 1, 0.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, -1.0f, 2.0f, 0.0f, 1.0f, 2.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
        this.headModel[6] = new ModelRendererTurbo((ModelBase)this, 64, 8, textureX, textureY);
        this.headModel[6].addShapeBox(-4.0f, -7.0f, -5.5f, 8, 7, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
        this.headModel[7] = new ModelRendererTurbo((ModelBase)this, 65, 17, textureX, textureY);
        this.headModel[7].addBox(-3.0f, 0.0f, -5.5f, 6, 1, 1);
        this.headModel[8] = new ModelRendererTurbo((ModelBase)this, 66, 19, textureX, textureY);
        this.headModel[8].addBox(-2.0f, 1.0f, -5.5f, 4, 1, 1);
        this.headModel[9] = new ModelRendererTurbo((ModelBase)this, 65, 17, textureX, textureY);
        this.headModel[9].addBox(-3.0f, -8.0f, -5.5f, 6, 1, 1);
    }
}

