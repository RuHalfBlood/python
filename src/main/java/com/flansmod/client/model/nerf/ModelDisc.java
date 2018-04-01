/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.flansmod.client.model.nerf;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDisc
extends ModelBase {
    public ModelRenderer bulletModel;

    public ModelDisc() {
        this.bulletModel = new ModelRenderer((ModelBase)this, 0, 0);
        this.bulletModel.addBox(-1.0f, -1.0f, -0.5f, 2, 2, 1);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.bulletModel.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    }
}

