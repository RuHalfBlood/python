/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.ModelBullet;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class ModelStingerMissile
extends ModelBullet {
    public ModelRendererTurbo[] bulletModel;

    public ModelStingerMissile() {
        int textureX = 64;
        int textureY = 32;
        this.bulletModel = new ModelRendererTurbo[3];
        this.bulletModel[0] = new ModelRendererTurbo(this, 60, 0, textureX, textureY);
        this.bulletModel[0].addBox(-0.5f, -10.0f, -0.5f, 1, 20, 1);
        this.bulletModel[1] = new ModelRendererTurbo(this, 55, 0, textureX, textureY);
        this.bulletModel[1].addBox(-1.0f, -10.0f, 0.0f, 2, 1, 0);
        this.bulletModel[2] = new ModelRendererTurbo(this, 55, 2, textureX, textureY);
        this.bulletModel[2].addBox(0.0f, -10.0f, -1.0f, 0, 1, 2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        for (ModelRendererTurbo mrt : this.bulletModel) {
            mrt.render(f5);
        }
    }
}

