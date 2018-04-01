/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 */
package com.flansmod.client.model.yeolde;

import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class ModelRock
extends ModelBase {
    public ModelRendererTurbo rockModel;

    public ModelRock() {
        this.rockModel = new ModelRendererTurbo(this, 0, 0, 8, 8);
        this.rockModel.addBox(-1.0f, -1.0f, -1.0f, 2, 2, 2);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.rockModel.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    }
}

