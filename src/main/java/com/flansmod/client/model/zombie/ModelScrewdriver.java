/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package com.flansmod.client.model.zombie;

import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.client.model.ModelBase;

public class ModelScrewdriver
extends ModelGun {
    public ModelScrewdriver() {
        int textureX = 16;
        int textureY = 8;
        this.gunModel = new ModelRendererTurbo[2];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addBox(-4.0f, -1.0f, -1.0f, 4, 2, 2);
        this.gunModel[1] = new ModelRendererTurbo(this, 0, 6, textureX, textureY);
        this.gunModel[1].addBox(0.0f, -0.5f, -0.5f, 4, 1, 1);
        for (int i = 0; i < 2; ++i) {
            this.gunModel[i].setRotationPoint(0.0f, 12.0f, 0.0f);
        }
        this.thirdPersonOffset = new Vector3f(0.25f, -0.75f, 0.0f);
    }
}

