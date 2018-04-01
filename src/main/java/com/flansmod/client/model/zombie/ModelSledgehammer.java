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

public class ModelSledgehammer
extends ModelGun {
    public ModelSledgehammer() {
        int textureX = 64;
        int textureY = 32;
        this.gunModel = new ModelRendererTurbo[2];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addBox(-1.0f, -1.0f, -12.0f, 2, 2, 24);
        this.gunModel[1] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[1].addBox(-4.0f, -2.0f, 12.0f, 8, 4, 4);
        for (int i = 0; i < 2; ++i) {
            this.gunModel[i].setRotationPoint(4.0f, 0.0f, 0.0f);
        }
        this.thirdPersonOffset = new Vector3f(-0.125f, 0.0f, 0.0f);
    }
}

