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

public class ModelGL1
extends ModelGun {
    public ModelGL1() {
        int textureX = 64;
        int textureY = 16;
        this.gunModel = new ModelRendererTurbo[4];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addShapeBox(-8.0f, 2.0f, -1.0f, 4, 3, 2, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[1] = new ModelRendererTurbo(this, 12, 0, textureX, textureY);
        this.gunModel[1].addShapeBox(-4.0f, 2.0f, -1.0f, 4, 2, 2, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f);
        this.gunModel[2] = new ModelRendererTurbo(this, 24, 0, textureX, textureY);
        this.gunModel[2].addShapeBox(0.0f, 2.0f, -1.0f, 3, 3, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f);
        this.gunModel[3] = new ModelRendererTurbo(this, 0, 5, textureX, textureY);
        this.gunModel[3].addBox(3.0f, 2.0f, -1.0f, 5, 1, 2);
        this.breakActionModel = new ModelRendererTurbo[5];
        this.breakActionModel[0] = new ModelRendererTurbo(this, 0, 8, textureX, textureY);
        this.breakActionModel[0].addBox(3.0f, 3.0f, -1.0f, 13, 2, 2);
        this.breakActionModel[1] = new ModelRendererTurbo(this, 14, 5, textureX, textureY);
        this.breakActionModel[1].addBox(15.0f, 5.0f, -0.5f, 1, 1, 1);
        this.breakActionModel[2] = new ModelRendererTurbo(this, 0, 13, textureX, textureY);
        this.breakActionModel[2].addBox(8.0f, 2.0f, -1.0f, 4, 1, 2);
        this.breakActionModel[3] = new ModelRendererTurbo(this, 12, 14, textureX, textureY);
        this.breakActionModel[3].addBox(3.0f, 4.5f, -0.5f, 3, 1, 1);
        this.breakActionModel[4] = new ModelRendererTurbo(this, 34, -1, textureX, textureY);
        this.breakActionModel[4].addShapeBox(11.0f, 5.0001f, -3.5f, 1, 10, 7, 0.0f, 0.0f, 0.0f, -2.5f, 0.0f, 0.0f, -2.5f, 0.0f, 0.0f, -2.5f, 0.0f, 0.0f, -2.5f, 0.0f, -6.0f, -2.5f, 0.0f, -6.0f, -2.5f, 0.0f, -6.0f, -2.5f, 0.0f, -6.0f, -2.5f);
        this.breakAngle = 60.0f;
        this.barrelBreakPoint = new Vector3f(0.25f, 0.125f, 0.0f);
        this.ammoModel = new ModelRendererTurbo[1];
        this.ammoModel[0] = new ModelRendererTurbo(this, 20, 12, textureX, textureY);
        this.ammoModel[0].addBox(2.9f, 3.25f, -0.75f, 3.0f, 1.5f, 1.5f);
        this.barrelAttachPoint = new Vector3f(0.46875f, 0.25f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.1875f, 0.3125f, 0.0f);
        this.scopeIsOnSlide = true;
        this.translateAll(-4.0f, 0.0f, 0.0f);
        this.gunSlideDistance = 0.25f;
        this.animationType = EnumAnimationType.BREAK_ACTION;
        this.tiltGunTime = 0.2f;
        this.unloadClipTime = 0.2f;
        this.loadClipTime = 0.2f;
        this.untiltGunTime = 0.4f;
    }
}

