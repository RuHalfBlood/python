/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.ModelBullet;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMIM23Rocket
extends ModelBullet {
    public ModelRendererTurbo[] bulletModel;

    public ModelMIM23Rocket() {
        int textureX = 256;
        int textureY = 256;
        this.bulletModel = new ModelRendererTurbo[4];
        this.bulletModel = new ModelRendererTurbo[4];
        this.bulletModel[0] = new ModelRendererTurbo(this, 72, 212, textureX, textureY);
        this.bulletModel[0].addBox(-20.0f, -3.0f, -3.0f, 40, 6, 6);
        this.bulletModel[1] = new ModelRendererTurbo(this, 72, 226, textureX, textureY);
        this.bulletModel[1].addTrapezoid(20.0f, -3.0f, -3.0f, 8, 6, 6, 0.0f, -3.0f, 2);
        this.bulletModel[2] = new ModelRendererTurbo(this, 102, 226, textureX, textureY);
        this.bulletModel[2].addShapeBox(-20.0f, -7.0f, 0.0f, 20, 14, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bulletModel[3] = new ModelRendererTurbo(this, 88, 242, textureX, textureY);
        this.bulletModel[3].addShapeBox(-20.0f, 0.0f, -7.0f, 20, 0, 14, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f);
        for (int i = 0; i < 4; ++i) {
            this.bulletModel[i].rotateAngleZ = 1.5707964f;
        }
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        for (ModelRendererTurbo mrt : this.bulletModel) {
            mrt.render(f5);
        }
    }
}

