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

public class ModelA91
extends ModelGun {
    int textureX = 64;
    int textureY = 16;

    public ModelA91() {
        this.gunModel = new ModelRendererTurbo[16];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 0, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 32, 5, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 0, 5, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 0, 9, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 0, 12, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 10, 3, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 10, 5, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 22, 3, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 14, 5, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 32, 0, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 36, 0, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 12, 13, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 6, 12, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 16, 7, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 0, 5, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 40, 0, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 15, 1, 2, 0.0f, 0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[0].setRotationPoint(-7.0f, -3.0f, -1.0f);
        this.gunModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 4, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, -0.25f, 0.0f, -0.5f, -0.25f, 0.0f, -0.5f, -0.25f, 0.0f, -0.5f, -0.25f);
        this.gunModel[1].setRotationPoint(4.0f, -2.0f, -1.0f);
        this.gunModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 2, 2, 2, 0.0f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, -0.5f, -0.25f, -0.25f, -0.5f, -0.25f, -0.25f, -0.5f, -0.25f, -0.25f, -0.5f, -0.25f);
        this.gunModel[2].setRotationPoint(0.0f, -2.0f, -1.0f);
        this.gunModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 2, 0.0f, 0.5f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.5f, 0.0f, -0.25f, 0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f);
        this.gunModel[3].setRotationPoint(-7.0f, -4.0f, -1.0f);
        this.gunModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 2, 0.0f, 0.0f, 0.0f, -0.25f, 0.0f, 1.0f, -0.5f, 0.0f, 1.0f, -0.5f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[4].setRotationPoint(-2.0f, -4.0f, -1.0f);
        this.gunModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 1, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f);
        this.gunModel[5].setRotationPoint(-1.0f, -5.0f, -0.5f);
        this.gunModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 2, 0.0f, 0.0f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.25f, -0.5f, 0.0f, -0.25f, -0.5f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f);
        this.gunModel[6].setRotationPoint(3.5f, -5.0f, -1.0f);
        this.gunModel[7].addBox(0.0f, 0.0f, 0.0f, 6, 1, 1, 0.0f);
        this.gunModel[7].setRotationPoint(-1.0f, -3.5f, -0.5f);
        this.gunModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 1, 0.0f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f);
        this.gunModel[8].setRotationPoint(8.0f, -3.0f, -0.5f);
        this.gunModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f);
        this.gunModel[9].setRotationPoint(11.0f, -3.0f, -0.5f);
        this.gunModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[10].setRotationPoint(7.0f, -5.0f, -0.5f);
        this.gunModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 2, 0.0f, 0.25f, -0.8f, -0.25f, 0.0f, -0.8f, -0.25f, 0.0f, -0.8f, -0.25f, 0.25f, -0.8f, -0.25f, 0.25f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, -0.25f, 0.25f, 0.0f, -0.25f);
        this.gunModel[11].setRotationPoint(2.0f, -1.5f, -1.0f);
        this.gunModel[12].addShapeBox(-8.0f, -4.0f, -1.0f, 1, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[13].addShapeBox(-6.0f, -2.0f, -1.0f, 2, 1, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f);
        this.gunModel[14].addShapeBox(0.0f, -0.5f, -1.0f, 2, 2, 2, 0.0f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, -0.5f, -0.25f, 0.0f, -0.25f, -0.25f, 0.0f, -0.25f, -0.25f, -0.25f, -0.5f, -0.25f);
        this.gunModel[15].addShapeBox(-8.0f, -2.0f, -1.0f, 1, 1, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel = new ModelRendererTurbo[3];
        this.ammoModel[0] = new ModelRendererTurbo(this, 24, 5, this.textureX, this.textureY);
        this.ammoModel[1] = new ModelRendererTurbo(this, 24, 8, this.textureX, this.textureY);
        this.ammoModel[2] = new ModelRendererTurbo(this, 24, 12, this.textureX, this.textureY);
        this.ammoModel[0].addBox(0.0f, 0.0f, 0.0f, 2, 1, 2, 0.0f);
        this.ammoModel[0].setRotationPoint(-4.0f, -2.0f, -1.0f);
        this.ammoModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 2, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, 0.25f, 0.0f, 0.0f, -0.25f, 0.0f, 0.0f);
        this.ammoModel[1].setRotationPoint(-4.0f, -1.0f, -1.0f);
        this.ammoModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 2, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.25f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, -0.5f, 0.25f, 0.0f);
        this.ammoModel[2].setRotationPoint(-3.75f, 1.0f, -1.0f);
        this.barrelAttachPoint = new Vector3f(0.0f, 0.0f, 0.0f);
        this.stockAttachPoint = new Vector3f(0.0f, 0.0f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.0f, 0.0f, 0.0f);
        this.gripAttachPoint = new Vector3f(0.0f, 0.0f, 0.0f);
        this.gunSlideDistance = 0.0f;
        this.animationType = EnumAnimationType.BULLPUP;
        this.translateAll(0.0f, 0.0f, 0.0f);
        this.flipAll();
    }
}

