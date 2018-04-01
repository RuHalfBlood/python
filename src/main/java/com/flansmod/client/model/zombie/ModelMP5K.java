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

public class ModelMP5K
extends ModelGun {
    public ModelMP5K() {
        int textureX = 64;
        int textureY = 16;
        this.gunModel = new ModelRendererTurbo[6];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addBox(-0.5f, 4.0f, -0.75f, 9.0f, 1.0f, 1.5f);
        this.gunModel[1] = new ModelRendererTurbo(this, 0, 3, textureX, textureY);
        this.gunModel[1].addBox(-0.5f, 3.0f, -1.0f, 9, 1, 2);
        this.gunModel[2] = new ModelRendererTurbo(this, 0, 6, textureX, textureY);
        this.gunModel[2].addBox(-0.5f, 2.0f, -1.0f, 6, 1, 2);
        this.gunModel[3] = new ModelRendererTurbo(this, 26, 0, textureX, textureY);
        this.gunModel[3].addBox(8.5f, 3.0f, -0.5f, 1, 1, 1);
        this.gunModel[5] = new ModelRendererTurbo(this, 26, 0, textureX, textureY);
        this.gunModel[5].addBox(7.0f, 5.0f, -0.5f, 1, 1, 1);
        this.gunModel[4] = new ModelRendererTurbo(this, 0, 9, textureX, textureY);
        this.gunModel[4].addBox(-0.75f, -3.0f, -0.75f, 1.5f, 4.0f, 1.5f);
        this.gunModel[4].setRotationPoint(1.0f, 2.0f, 0.0f);
        this.gunModel[4].rotateAngleZ = -0.5f;
        this.ammoModel = new ModelRendererTurbo[1];
        this.ammoModel[0] = new ModelRendererTurbo(this, 8, 9, textureX, textureY);
        this.ammoModel[0].addShapeBox(3.0f, -1.0f, -0.5f, 2, 4, 1, 0.0f, -1.0f, 0.0f, 0.0f, 0.8f, -0.2f, 0.0f, 0.8f, -0.2f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.stockAttachPoint = new Vector3f(-0.0625f, 0.21875f, 0.0f);
        this.defaultGripModel = new ModelRendererTurbo[1];
        this.defaultGripModel[0] = new ModelRendererTurbo(this, 21, 9, textureX, textureY);
        this.defaultGripModel[0].addBox(7.0f, 1.0f, -0.5f, 1, 2, 1);
        this.barrelAttachPoint = new Vector3f(0.5625f, 0.21875f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.125f, 0.3125f, 0.0f);
        this.gunSlideDistance = 0.25f;
        this.animationType = EnumAnimationType.PISTOL_CLIP;
    }
}

