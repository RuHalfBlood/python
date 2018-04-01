/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.flansmod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMuzzle
extends ModelBase {
    ModelRenderer Box_0;
    ModelRenderer Box_1;
    ModelRenderer Box_3;

    public ModelMuzzle() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Box_0 = new ModelRenderer((ModelBase)this, 1, 1);
        this.Box_0.addBox(0.0f, 0.0f, 0.0f, 8, 8, 0);
        this.Box_0.setRotationPoint(-4.0f, -4.0f, 0.0f);
        this.Box_0.setTextureSize(64, 32);
        this.Box_0.mirror = true;
        this.setRotation(this.Box_0, 0.0f, 0.0f, 0.0f);
        this.Box_1 = new ModelRenderer((ModelBase)this, 9, 1);
        this.Box_1.addBox(-4.0f, 0.0f, 0.0f, 8, 0, 15);
        this.Box_1.setRotationPoint(0.0f, 0.0f, -15.0f);
        this.Box_1.setTextureSize(64, 32);
        this.Box_1.mirror = true;
        this.setRotation(this.Box_1, 0.0f, 0.0f, -0.7853982f);
        this.Box_3 = new ModelRenderer((ModelBase)this, 1, 17);
        this.Box_3.addBox(-4.0f, 0.0f, 0.0f, 8, 0, 15);
        this.Box_3.setRotationPoint(0.0f, 0.0f, -15.0f);
        this.Box_3.setTextureSize(64, 32);
        this.Box_3.mirror = true;
        this.setRotation(this.Box_3, 0.0f, 0.0f, -2.373648f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Box_0.render(f5);
        this.Box_1.render(f5);
        this.Box_3.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
        super.setRotationAngles(f5, f, f1, f2, f3, f4, e);
    }
}

