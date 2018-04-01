/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 */
package com.flansmod.client.model.zombie;

import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class ModelFleshLump
extends ModelBase {
    public ModelRendererTurbo[] fleshModel = new ModelRendererTurbo[2];

    public ModelFleshLump() {
        this.fleshModel[0] = new ModelRendererTurbo(this, 0, 0, 16, 16);
        this.fleshModel[0].addShapeBox(-2.0f, -2.0f, -3.0f, 5, 5, 4, 0.0f, 0.5f, 0.5f, -0.5f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.5f, 0.0f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -0.5f, 0.5f, 1.0f, 0.5f, 0.5f, 1.0f);
        this.fleshModel[1] = new ModelRendererTurbo(this, 8, 0, 16, 16);
        this.fleshModel[1].addShapeBox(-2.0f, -2.0f, 0.0f, 4, 5, 5, 0.0f, 0.0f, -1.0f, 0.5f, -0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 0.5f, 0.5f, 0.5f, -1.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.5f, 1.0f, 0.0f, 0.0f, 0.5f, 0.0f, -1.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        for (ModelRendererTurbo m : this.fleshModel) {
            m.render(f5);
        }
    }
}

