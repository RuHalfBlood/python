/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelRPGRocket
extends ModelBase {
    public ModelRendererTurbo[] bulletModel;

    public ModelRPGRocket() {
        int textureX = 64;
        int textureY = 32;
        this.bulletModel = new ModelRendererTurbo[3];
        this.bulletModel[0] = new ModelRendererTurbo(this, 14, 12, textureX, textureY);
        this.bulletModel[0].addBox(-1.5f, 0.0f, -1.5f, 3, 1, 3);
        this.bulletModel[1] = new ModelRendererTurbo(this, 26, 9, textureX, textureY);
        this.bulletModel[1].addTrapezoid(-2.0f, 1.0f, -2.0f, 4, 3, 4, 0.0f, -1.0f, 4);
        this.bulletModel[2] = new ModelRendererTurbo(this, 38, 6, textureX, textureY);
        this.bulletModel[2].addTrapezoid(-2.0f, 4.0f, -2.0f, 4, 3, 4, 0.0f, -1.0f, 5);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        for (ModelRendererTurbo mrt : this.bulletModel) {
            mrt.render(f5);
        }
    }
}

