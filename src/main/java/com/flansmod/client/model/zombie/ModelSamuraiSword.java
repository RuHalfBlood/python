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

public class ModelSamuraiSword
extends ModelGun {
    public ModelSamuraiSword() {
        int textureX = 8;
        int textureY = 32;
        this.gunModel = new ModelRendererTurbo[4];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addShapeBox(-1.0f, -2.0f, 0.0f, 2, 12, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[1] = new ModelRendererTurbo(this, 0, 12, textureX, textureY);
        this.gunModel[1].addShapeBox(-1.0f, 10.0f, 0.0f, 2, 12, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.5f, -2.0f, 0.0f, -1.5f, -2.0f, 0.0f, 1.0f, 0.0f, 0.0f);
        this.gunModel[2] = new ModelRendererTurbo(this, 0, 29, textureX, textureY);
        this.gunModel[2].addBox(-1.0f, -3.0f, -1.0f, 2, 1, 2);
        this.gunModel[3] = new ModelRendererTurbo(this, 4, 0, textureX, textureY);
        this.gunModel[3].addBox(-0.5f, -11.0f, -0.5f, 1, 8, 1);
        for (int i = 0; i < 4; ++i) {
            this.gunModel[i].setRotationPoint(0.0f, 8.0f, 0.0f);
        }
        this.thirdPersonOffset = new Vector3f(0.0f, -0.125f, 0.0f);
    }
}

