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

public class ModelFlareGun
extends ModelGun {
    public ModelFlareGun() {
        int textureX = 64;
        int textureY = 16;
        this.gunModel = new ModelRendererTurbo[3];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addShapeBox(-1.0f, -2.0f, -0.5f, 2, 4, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[1] = new ModelRendererTurbo(this, 6, 0, textureX, textureY);
        this.gunModel[1].addShapeBox(1.0f, 0.0f, -0.5f, 5, 2, 1, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.gunModel[2] = new ModelRendererTurbo(this, 18, 0, textureX, textureY);
        this.gunModel[2].addShapeBox(0.0f, 2.0f, -0.5f, 2, 2, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
        this.breakActionModel = new ModelRendererTurbo[1];
        this.breakActionModel[0] = new ModelRendererTurbo(this, 0, 6, textureX, textureY);
        this.breakActionModel[0].addBox(2.0f, 2.0f, -1.0f, 6, 2, 2);
        this.barrelBreakPoint = new Vector3f(0.375f, 0.125f, 0.0f);
        this.ammoModel = new ModelRendererTurbo[1];
        this.ammoModel[0] = new ModelRendererTurbo(this, 0, 14, textureX, textureY);
        this.ammoModel[0].addBox(2.5f, 2.5f, -0.5f, 5, 1, 1);
        this.barrelAttachPoint = new Vector3f(0.46875f, 0.25f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.25f, 0.3125f, 0.0f);
        this.scopeIsOnBreakAction = true;
        this.translateAll(0.0f, 1.0f, 0.0f);
        this.gunSlideDistance = 0.25f;
        this.animationType = EnumAnimationType.BREAK_ACTION;
    }
}

