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

public class ModelWinchesterModel70
extends ModelGun {
    public ModelWinchesterModel70() {
        int textureX = 64;
        int textureY = 16;
        this.gunModel = new ModelRendererTurbo[2];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addBox(1.0f, 1.5f, -0.5f, 16, 1, 1);
        this.gunModel[1] = new ModelRendererTurbo(this, 0, 2, textureX, textureY);
        this.gunModel[1].addShapeBox(-1.0f, 0.0f, -1.0f, 13, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.defaultScopeModel = new ModelRendererTurbo[5];
        this.defaultScopeModel[0] = new ModelRendererTurbo(this, 0, 10, textureX, textureY);
        this.defaultScopeModel[0].addBox(-1.0f, 3.0f, -1.0f, 2, 2, 2);
        this.defaultScopeModel[1] = new ModelRendererTurbo(this, 0, 14, textureX, textureY);
        this.defaultScopeModel[1].addBox(1.0f, 3.5f, -0.5f, 5, 1, 1);
        this.defaultScopeModel[2] = new ModelRendererTurbo(this, 8, 10, textureX, textureY);
        this.defaultScopeModel[2].addBox(6.0f, 3.0f, -1.0f, 3, 2, 2);
        this.defaultScopeModel[3] = new ModelRendererTurbo(this, 12, 14, textureX, textureY);
        this.defaultScopeModel[3].addBox(1.5f, 2.5f, -0.5f, 1, 1, 1);
        this.defaultScopeModel[4] = new ModelRendererTurbo(this, 16, 14, textureX, textureY);
        this.defaultScopeModel[4].addBox(4.5f, 2.5f, -0.5f, 1, 1, 1);
        this.scopeAttachPoint = new Vector3f(0.1875f, 0.3125f, 0.0f);
        this.barrelAttachPoint = new Vector3f(1.0625f, 0.28125f, 0.0f);
        this.defaultStockModel = new ModelRendererTurbo[2];
        this.defaultStockModel[0] = new ModelRendererTurbo(this, 0, 6, textureX, textureY);
        this.defaultStockModel[0].addBox(-4.0f, -0.5f, -1.0f, 3, 2, 2);
        this.defaultStockModel[1] = new ModelRendererTurbo(this, 16, 6, textureX, textureY);
        this.defaultStockModel[1].addShapeBox(-9.0f, -2.0f, -1.0f, 5, 4, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.stockAttachPoint = new Vector3f(-0.0625f, 0.0625f, 0.0f);
        this.ammoModel = new ModelRendererTurbo[1];
        this.ammoModel[0] = new ModelRendererTurbo(this, 34, 0, textureX, textureY);
        this.ammoModel[0].addBox(1.0f, 0.5f, -0.5f, 3, 1, 1);
        this.translateAll(0.0f, 2.5f, 0.0f);
        this.gunSlideDistance = 0.5f;
        this.animationType = EnumAnimationType.RIFLE;
        this.numBulletsInReloadAnimation = 5.0f;
        this.tiltGunTime = 0.279f;
        this.unloadClipTime = 0.0f;
        this.loadClipTime = 0.558f;
        this.untiltGunTime = 0.163f;
    }
}

