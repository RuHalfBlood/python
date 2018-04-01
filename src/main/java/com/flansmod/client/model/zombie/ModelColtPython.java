/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.flansmod.client.model.zombie;

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelColtPython
extends ModelGun {
    public ModelColtPython() {
        int textureX = 64;
        int textureY = 16;
        this.gunModel = new ModelRendererTurbo[6];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addShapeBox(-1.0f, -1.0f, -0.5f, 2, 3, 1, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, -1.0f, 0.0f);
        this.gunModel[1] = new ModelRendererTurbo(this, 6, 0, textureX, textureY);
        this.gunModel[1].addShapeBox(1.0f, 1.0f, -0.5f, 1, 2, 1, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, -1.0f, 0.0f);
        this.gunModel[2] = new ModelRendererTurbo(this, 10, 0, textureX, textureY);
        this.gunModel[2].addBox(2.0f, 1.0f, -0.5f, 3.0f, 0.25f, 1.0f);
        this.gunModel[3] = new ModelRendererTurbo(this, 10, 2, textureX, textureY);
        this.gunModel[3].addBox(2.0f, 3.25f, -0.5f, 3.0f, 0.25f, 1.0f);
        this.gunModel[4] = new ModelRendererTurbo(this, 18, 0, textureX, textureY);
        this.gunModel[4].addBox(5.0f, 1.0f, -0.5f, 0.5f, 2.5f, 1.0f);
        this.gunModel[5] = new ModelRendererTurbo(this, 22, 0, textureX, textureY);
        this.gunModel[5].addBox(5.5f, 2.0f, -0.5f, 6.0f, 1.5f, 1.0f);
        this.revolverBarrelModel = new ModelRendererTurbo[1];
        this.revolverBarrelModel[0] = new ModelRendererTurbo(this, 0, 4, textureX, textureY);
        this.revolverBarrelModel[0].addBox(2.0f, 1.25f, -1.0f, 3, 2, 2);
        this.revolverFlipAngle = 30.0f;
        this.ammoModel = new ModelRendererTurbo[6];
        for (int i = 0; i < 6; ++i) {
            this.ammoModel[i] = new ModelRendererTurbo(this, 10, 4, textureX, textureY);
            this.ammoModel[i].addBox(0.0f, 0.35f, -0.5f, 2.0f, 0.5f, 0.5f);
            this.ammoModel[i].setRotationPoint(2.5f, 2.25f, 0.0f);
            this.ammoModel[i].rotateAngleX = 1.0471976f * (float)i;
        }
        this.barrelAttachPoint = new Vector3f(0.6875f, 0.28125f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.25f, 0.3125f, 0.0f);
        this.translateAll(0.0f, 1.5f, 0.0f);
        this.gunSlideDistance = 0.25f;
        this.animationType = EnumAnimationType.REVOLVER;
    }
}

