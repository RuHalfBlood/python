/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class ModelDeployableBag
extends ModelBase {
    public ModelRendererTurbo[] bagModel = new ModelRendererTurbo[2];

    public ModelDeployableBag() {
        this.bagModel[0] = new ModelRendererTurbo(this, 0, 0, 32, 32);
        this.bagModel[0].addShapeBox(-3.0f, 0.0f, -6.0f, 6, 6, 6, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, -1.0f, 0.0f, -0.1f, -1.0f, 0.0f);
        this.bagModel[1] = new ModelRendererTurbo(this, 0, 12, 32, 32);
        this.bagModel[1].addShapeBox(-3.0f, 0.0f, 0.0f, 6, 6, 6, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.1f, -1.0f, 0.0f, -0.5f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        for (ModelRendererTurbo bagModelBit : this.bagModel) {
            bagModelBit.render(f5);
        }
    }
}

