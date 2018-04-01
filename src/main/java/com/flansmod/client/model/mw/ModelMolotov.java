/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMolotov
extends ModelBase {
    public ModelRendererTurbo neckModel;
    public ModelRendererTurbo bodyModel;
    public ModelRendererTurbo clothModel;

    public ModelMolotov() {
        this.bodyModel = new ModelRendererTurbo(this, 0, 0, 32, 16);
        this.bodyModel.addBox(-1.0f, -2.0f, -1.0f, 2, 4, 2);
        this.neckModel = new ModelRendererTurbo(this, 8, 0, 32, 16);
        this.neckModel.addBox(-0.5f, 2.0f, -0.5f, 1, 2, 1);
        this.clothModel = new ModelRendererTurbo(this, 0, 6, 32, 16);
        this.clothModel.addShapeBox(-1.0f, -0.5f, -0.5f, 2, 1, 5, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.clothModel.setRotationPoint(0.0f, 4.0f, 0.0f);
        this.clothModel.rotateAngleX = 1.0f;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.neckModel.render(f5);
        this.bodyModel.render(f5);
        this.clothModel.render(f5);
    }
}

