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

public class ModelMachete
extends ModelGun {
    public ModelMachete() {
        int textureX = 16;
        int textureY = 16;
        this.gunModel = new ModelRendererTurbo[2];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addShapeBox(-1.0f, -2.0f, 0.0f, 2, 16, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, -2.0f, 0.0f, 1.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[1] = new ModelRendererTurbo(this, 4, 0, textureX, textureY);
        this.gunModel[1].addBox(-1.0f, -8.0f, -0.5f, 2, 6, 1);
        for (int i = 0; i < 2; ++i) {
            this.gunModel[i].setRotationPoint(0.0f, 8.0f, 0.0f);
        }
        this.thirdPersonOffset = new Vector3f(0.0f, 0.0f, 0.0f);
    }
}

