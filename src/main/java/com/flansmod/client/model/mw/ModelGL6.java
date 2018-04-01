/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.ModelGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelGL6
extends ModelGun {
    public ModelGL6() {
        int textureX = 64;
        int textureY = 16;
        this.gunModel = new ModelRendererTurbo[13];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.gunModel[0].addShapeBox(-12.0f, 1.0f, -1.0f, 9, 3, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[1] = new ModelRendererTurbo(this, 22, 0, textureX, textureY);
        this.gunModel[1].addBox(-3.0f, 1.0f, -1.0f, 3, 3, 2);
        this.gunModel[2] = new ModelRendererTurbo(this, 32, 0, textureX, textureY);
        this.gunModel[2].addShapeBox(-3.0f, -2.0f, -1.0f, 2, 3, 2, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[3] = new ModelRendererTurbo(this, 0, 5, textureX, textureY);
        this.gunModel[3].addBox(-2.0f, 4.0f, -1.0f, 10, 1, 2);
        this.gunModel[4] = new ModelRendererTurbo(this, 0, 8, textureX, textureY);
        this.gunModel[4].addBox(0.0f, -2.0f, -1.0f, 1, 6, 2);
        this.gunModel[5] = new ModelRendererTurbo(this, 0, 8, textureX, textureY);
        this.gunModel[5].addShapeBox(0.0f, -2.0f, 1.0f, 1, 6, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f);
        this.gunModel[6] = new ModelRendererTurbo(this, 0, 8, textureX, textureY);
        this.gunModel[6].addShapeBox(0.0f, -2.0f, -3.0f, 1, 6, 2, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[7] = new ModelRendererTurbo(this, 0, 8, textureX, textureY);
        this.gunModel[7].addBox(7.0f, -2.0f, -1.0f, 1, 6, 2);
        this.gunModel[8] = new ModelRendererTurbo(this, 0, 8, textureX, textureY);
        this.gunModel[8].addShapeBox(7.0f, -2.0f, 1.0f, 1, 6, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f);
        this.gunModel[9] = new ModelRendererTurbo(this, 0, 8, textureX, textureY);
        this.gunModel[9].addShapeBox(7.0f, -2.0f, -3.0f, 1, 6, 2, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[10] = new ModelRendererTurbo(this, 22, 13, textureX, textureY);
        this.gunModel[10].addBox(0.0f, -3.0f, -1.0f, 8, 1, 2);
        this.gunModel[11] = new ModelRendererTurbo(this, 22, 9, textureX, textureY);
        this.gunModel[11].addBox(8.0f, 1.5f, -1.0f, 8, 2, 2);
        this.gunModel[12] = new ModelRendererTurbo(this, 40, 0, textureX, textureY);
        this.gunModel[12].addBox(8.0f, 1.0f, -1.5f, 4, 3, 3);
        this.revolverBarrelModel = new ModelRendererTurbo[3];
        this.revolverBarrelModel[0] = new ModelRendererTurbo(this, 6, 8, textureX, textureY);
        this.revolverBarrelModel[0].addBox(1.0f, -2.0f, -1.0f, 6, 6, 2);
        this.revolverBarrelModel[1] = new ModelRendererTurbo(this, 6, 8, textureX, textureY);
        this.revolverBarrelModel[1].addShapeBox(1.0f, -2.0f, 1.0f, 6, 6, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f);
        this.revolverBarrelModel[2] = new ModelRendererTurbo(this, 6, 8, textureX, textureY);
        this.revolverBarrelModel[2].addShapeBox(1.0f, -2.0f, -3.0f, 6, 6, 2, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.revolverFlipAngle = -90.0f;
        this.revolverFlipPoint = new Vector3f(0.0f, -0.15625f, 0.0f);
        this.ammoModel = new ModelRendererTurbo[6];
        for (int i = 0; i < 6; ++i) {
            this.ammoModel[i] = new ModelRendererTurbo(this, 42, 12, textureX, textureY);
            this.ammoModel[i].addBox(0.9f, -2.75f, -0.75f, 4.0f, 1.5f, 1.5f);
            this.ammoModel[i].setRotationPoint(0.0f, 1.0f, 0.0f);
            this.ammoModel[i].rotateAngleX = 1.0471976f * (float)i;
        }
        this.barrelAttachPoint = new Vector3f(0.46875f, 0.25f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.375f, 0.3125f, 0.0f);
        this.translateAll(6.0f, 0.0f, 0.0f);
        this.gunSlideDistance = 0.25f;
        this.animationType = EnumAnimationType.REVOLVER;
        this.tiltGunTime = 0.2f;
        this.unloadClipTime = 0.2f;
        this.loadClipTime = 0.4f;
        this.untiltGunTime = 0.2f;
        this.itemFrameOffset = new Vector3f(-0.125f, 0.0f, 0.0f);
    }
}

