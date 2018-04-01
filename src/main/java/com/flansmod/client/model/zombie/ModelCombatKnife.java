/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package com.flansmod.client.model.zombie;

import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;

public class ModelCombatKnife
extends ModelGun {
    public ModelCombatKnife() {
        int textureX = 16;
        int textureY = 8;
        this.gunModel = new ModelRendererTurbo[3];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addBox(-4.0f, -1.0f, -0.7f, 3.0f, 2.0f, 1.4f);
        this.gunModel[1] = new ModelRendererTurbo(this, 10, 0, textureX, textureY);
        this.gunModel[1].addBox(-1.0f, -1.5f, -1.0f, 1, 3, 2);
        this.gunModel[2] = new ModelRendererTurbo(this, 0, 5, textureX, textureY);
        this.gunModel[2].addShapeBox(0.0f, -1.0f, -0.5f, 5, 2, 1, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        for (int i = 0; i < 3; ++i) {
            this.gunModel[i].setRotationPoint(4.0f, 0.0f, 0.0f);
        }
    }
}

