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

public class ModelOlympia
extends ModelGun {
    public ModelOlympia() {
        int textureX = 64;
        int textureY = 16;
        this.gunModel = new ModelRendererTurbo[6];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addShapeBox(-5.0f, 2.0f, -1.0f, 2, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.8f, 0.0f, 0.0f, 0.8f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[1] = new ModelRendererTurbo(this, 8, 0, textureX, textureY);
        this.gunModel[1].addShapeBox(-3.0f, 2.0f, -1.0f, 4, 3, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, -0.5f, 0.0f);
        this.gunModel[2] = new ModelRendererTurbo(this, 0, 5, textureX, textureY);
        this.gunModel[2].addBox(1.0f, 2.0f, -1.0f, 2, 1, 2);
        this.gunModel[3] = new ModelRendererTurbo(this, 10, 5, textureX, textureY);
        this.gunModel[3].addBox(1.0f, 3.0f, -1.0f, 1, 2, 2);
        this.gunModel[4] = new ModelRendererTurbo(this, 30, 0, textureX, textureY);
        this.gunModel[4].addBox(-1.5f, 4.0f, -0.5f, 1, 1, 1);
        this.gunModel[5] = new ModelRendererTurbo(this, 20, 2, textureX, textureY);
        this.gunModel[5].addShapeBox(-9.0f, 1.0f, -1.0f, 4, 3, 2, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.breakActionModel = new ModelRendererTurbo[4];
        this.breakActionModel[0] = new ModelRendererTurbo(this, 0, 12, textureX, textureY);
        this.breakActionModel[0].addBox(3.0f, 2.0f, -1.0f, 8, 2, 2);
        this.breakActionModel[1] = new ModelRendererTurbo(this, 0, 8, textureX, textureY);
        this.breakActionModel[1].addBox(2.0f, 4.0f, -0.5f, 15, 1, 1);
        this.breakActionModel[2] = new ModelRendererTurbo(this, 0, 10, textureX, textureY);
        this.breakActionModel[2].addBox(2.0f, 2.9f, -0.5f, 15, 1, 1);
        this.breakActionModel[3] = new ModelRendererTurbo(this, 20, 13, textureX, textureY);
        this.breakActionModel[3].addBox(2.25f, 3.5f, -0.25f, 14.5f, 2.0f, 0.5f);
        this.breakAngle = 60.0f;
        this.barrelBreakPoint = new Vector3f(0.1875f, 0.125f, 0.0f);
        this.ammoModel = new ModelRendererTurbo[2];
        this.ammoModel[0] = new ModelRendererTurbo(this, 20, 0, textureX, textureY);
        this.ammoModel[0].addBox(1.9f, 3.0f, -0.4f, 3.0f, 0.8f, 0.8f);
        this.ammoModel[1] = new ModelRendererTurbo(this, 20, 0, textureX, textureY);
        this.ammoModel[1].addBox(1.9f, 4.1f, -0.4f, 3.0f, 0.8f, 0.8f);
        this.barrelAttachPoint = new Vector3f(0.46875f, 0.25f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.1875f, 0.3125f, 0.0f);
        this.scopeIsOnSlide = true;
        this.gunSlideDistance = 0.25f;
        this.animationType = EnumAnimationType.BREAK_ACTION;
    }
}

