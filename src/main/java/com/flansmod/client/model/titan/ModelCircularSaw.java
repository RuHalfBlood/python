/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.flansmod.client.model.titan;

import com.flansmod.client.model.ModelMechaTool;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCircularSaw
extends ModelMechaTool {
    public ModelCircularSaw() {
        int textureX = 32;
        int textureY = 32;
        this.baseModel = new ModelRendererTurbo[2];
        this.baseModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.baseModel[0].addBox(0.0f, -3.5f, -3.5f, 3, 7, 7);
        this.baseModel[1] = new ModelRendererTurbo(this, 0, 14, textureX, textureY);
        this.baseModel[1].addBox(3.0f, -1.0f, -1.0f, 7, 2, 2);
        this.sawModel = new ModelRendererTurbo[2];
        this.sawModel[0] = new ModelRendererTurbo(this, 0, 18, textureX, textureY);
        this.sawModel[0].addBox(-4.0f, -0.4f, -4.0f, 8, 1, 8);
        this.sawModel[0].setRotationPoint(9.0f, 0.0f, 0.0f);
        this.sawModel[1] = new ModelRendererTurbo(this, 0, 18, textureX, textureY);
        this.sawModel[1].addBox(-4.0f, -0.6f, -4.0f, 8, 1, 8);
        this.sawModel[1].rotateAngleY = 0.7853982f;
        this.sawModel[1].setRotationPoint(9.0f, 0.0f, 0.0f);
    }
}

