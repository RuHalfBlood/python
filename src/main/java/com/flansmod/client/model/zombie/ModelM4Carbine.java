/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package com.flansmod.client.model.zombie;

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.client.model.ModelBase;

public class ModelM4Carbine
extends ModelGun {
    public ModelM4Carbine() {
        int textureX = 64;
        int textureY = 16;
        this.gunModel = new ModelRendererTurbo[6];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addBox(-1.0f, 2.0f, -1.0f, 7, 3, 2);
        this.gunModel[1] = new ModelRendererTurbo(this, 32, 4, textureX, textureY);
        this.gunModel[1].addBox(0.0f, -1.0f, -1.0f, 1, 3, 2);
        this.gunModel[2] = new ModelRendererTurbo(this, 20, 0, textureX, textureY);
        this.gunModel[2].addBox(1.0f, 1.0f, -1.0f, 1, 1, 2);
        this.gunModel[3] = new ModelRendererTurbo(this, 0, 5, textureX, textureY);
        this.gunModel[3].addBox(6.0f, 3.0f, -1.0f, 5, 2, 2);
        this.gunModel[4] = new ModelRendererTurbo(this, 12, 14, textureX, textureY);
        this.gunModel[4].addBox(12.0f, 4.5f, -0.5f, 1, 1, 1);
        this.gunModel[5] = new ModelRendererTurbo(this, 0, 9, textureX, textureY);
        this.gunModel[5].addBox(6.0f, 3.5f, -0.5f, 10, 1, 1);
        this.scopeAttachPoint = new Vector3f(0.21875f, 0.3125f, 0.0f);
        this.defaultStockModel = new ModelRendererTurbo[3];
        this.defaultStockModel[0] = new ModelRendererTurbo(this, 20, 3, textureX, textureY);
        this.defaultStockModel[0].addBox(-8.0f, 1.0f, -1.0f, 2, 4, 2);
        this.defaultStockModel[1] = new ModelRendererTurbo(this, 0, 11, textureX, textureY);
        this.defaultStockModel[1].addBox(-6.0f, 2.0f, -1.0f, 2, 3, 2);
        this.defaultStockModel[2] = new ModelRendererTurbo(this, 10, 11, textureX, textureY);
        this.defaultStockModel[2].addBox(-4.0f, 3.5f, -0.5f, 3, 1, 1);
        this.stockAttachPoint = new Vector3f(0.0625f, 0.25f, 0.0f);
        this.barrelAttachPoint = new Vector3f(1.0f, 0.25f, 0.0f);
        this.gripAttachPoint = new Vector3f(0.625f, 0.1875f, 0.0f);
        this.defaultGripModel = new ModelRendererTurbo[2];
        this.defaultGripModel[0] = new ModelRendererTurbo(this, 30, 0, textureX, textureY);
        this.defaultGripModel[0].addBox(7.5f, 2.0f, -1.0f, 2, 1, 2);
        this.defaultGripModel[1] = new ModelRendererTurbo(this, 38, 0, textureX, textureY);
        this.defaultGripModel[1].addBox(8.0f, 0.0f, -0.5f, 1, 2, 1);
        this.ammoModel = new ModelRendererTurbo[2];
        this.ammoModel[0] = new ModelRendererTurbo(this, 20, 11, textureX, textureY);
        this.ammoModel[0].addBox(3.0f, 0.0f, -1.0f, 3, 2, 2);
        this.ammoModel[1] = new ModelRendererTurbo(this, 30, 11, textureX, textureY);
        this.ammoModel[1].addBox(4.0f, -2.0f, -1.0f, 3, 2, 2);
        this.gunSlideDistance = 0.5f;
        this.animationType = EnumAnimationType.BOTTOM_CLIP;
    }
}

