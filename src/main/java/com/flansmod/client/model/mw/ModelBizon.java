/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.client.model.ModelBase;

public class ModelBizon
extends ModelGun {
    public ModelBizon() {
        int textureX = 64;
        int textureY = 16;
        this.gunModel = new ModelRendererTurbo[5];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addShapeBox(-2.0f, 3.0f, -1.0f, 15, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[1] = new ModelRendererTurbo(this, 0, 4, textureX, textureY);
        this.gunModel[1].addBox(-2.0f, 2.0f, -1.0f, 5, 1, 2);
        this.gunModel[2] = new ModelRendererTurbo(this, 0, 7, textureX, textureY);
        this.gunModel[2].addShapeBox(-2.0f, -1.0f, -1.0f, 2, 3, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[3] = new ModelRendererTurbo(this, 8, 7, textureX, textureY);
        this.gunModel[3].addBox(13.0f, 3.5f, -0.5f, 2, 1, 1);
        this.gunModel[4] = new ModelRendererTurbo(this, 8, 9, textureX, textureY);
        this.gunModel[4].addBox(13.0f, 4.5f, -0.5f, 1, 1, 1);
        this.scopeAttachPoint = new Vector3f(0.46875f, 0.3125f, 0.0f);
        this.barrelAttachPoint = new Vector3f(0.625f, 0.125f, 0.0f);
        this.ammoModel = new ModelRendererTurbo[1];
        this.ammoModel[0] = new ModelRendererTurbo(this, 0, 12, textureX, textureY);
        this.ammoModel[0].addBox(3.0f, 1.2f, -0.9f, 10.0f, 1.8f, 1.8f);
        this.gunSlideDistance = 0.5f;
        this.animationType = EnumAnimationType.BOTTOM_CLIP;
    }
}

