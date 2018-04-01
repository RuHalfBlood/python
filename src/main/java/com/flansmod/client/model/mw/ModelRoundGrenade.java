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

public class ModelRoundGrenade
extends ModelBase {
    public ModelRendererTurbo headModel;
    public ModelRendererTurbo bodyModel;

    public ModelRoundGrenade() {
        this.bodyModel = new ModelRendererTurbo(this, 0, 0, 16, 8);
        this.bodyModel.addBox(-1.0f, -1.0f, -1.0f, 2, 2, 2);
        this.headModel = new ModelRendererTurbo(this, 8, 0, 16, 8);
        this.headModel.addBox(-0.5f, -0.5f, 0.5f, 1, 1, 1);
        this.bodyModel.rotateAngleX = -1.5707964f;
        this.headModel.rotateAngleX = -1.5707964f;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.headModel.render(f5);
        this.bodyModel.render(f5);
    }
}

