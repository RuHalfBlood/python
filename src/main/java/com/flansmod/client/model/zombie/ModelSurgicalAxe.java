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

public class ModelSurgicalAxe
extends ModelGun {
    public ModelSurgicalAxe() {
        int textureX = 16;
        int textureY = 16;
        this.gunModel = new ModelRendererTurbo[4];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addBox(-1.0f, -6.0f, -1.0f, 2, 6, 2);
        this.gunModel[1] = new ModelRendererTurbo(this, 8, 0, textureX, textureY);
        this.gunModel[1].addBox(-1.0f, 0.0f, -0.5f, 2, 6, 1);
        this.gunModel[2] = new ModelRendererTurbo(this, 0, 8, textureX, textureY);
        this.gunModel[2].addShapeBox(1.0f, 2.0f, -0.5f, 4, 4, 1, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, -3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f);
        this.gunModel[3] = new ModelRendererTurbo(this, 0, 13, textureX, textureY);
        this.gunModel[3].addShapeBox(-4.0f, 4.0f, -0.5f, 3, 2, 1, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f);
        for (int i = 0; i < 4; ++i) {
            this.gunModel[i].setRotationPoint(0.0f, 6.0f, 0.0f);
        }
        this.thirdPersonOffset = new Vector3f(0.0f, -0.0f, 0.0f);
    }
}

