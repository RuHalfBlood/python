/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.ModelAAGun;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;

public class ModelSentryGun
extends ModelAAGun {
    public ModelSentryGun() {
        int textureX = 64;
        int textureY = 64;
        this.baseModel = new ModelRendererTurbo[5];
        this.baseModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.baseModel[0].addBox(-4.0f, 16.0f, -4.0f, 8, 2, 8, 0.0f);
        this.baseModel[1] = new ModelRendererTurbo(this, 32, 0, textureX, textureY);
        this.baseModel[1].addShapeBox(-4.0f, 0.0f, -4.0f, 2, 16, 2, 0.0f, 4.0f, 0.0f, 4.0f, -4.0f, 0.0f, 4.0f, -4.0f, 0.0f, -4.0f, 4.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.baseModel[2] = new ModelRendererTurbo(this, 40, 0, textureX, textureY);
        this.baseModel[2].addShapeBox(2.0f, 0.0f, -4.0f, 2, 16, 2, 0.0f, -4.0f, 0.0f, 4.0f, 4.0f, 0.0f, 4.0f, 4.0f, 0.0f, -4.0f, -4.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.baseModel[3] = new ModelRendererTurbo(this, 48, 0, textureX, textureY);
        this.baseModel[3].addShapeBox(2.0f, 0.0f, 2.0f, 2, 16, 2, 0.0f, -4.0f, 0.0f, -4.0f, 4.0f, 0.0f, -4.0f, 4.0f, 0.0f, 4.0f, -4.0f, 0.0f, 4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.baseModel[4] = new ModelRendererTurbo(this, 56, 0, textureX, textureY);
        this.baseModel[4].addShapeBox(-4.0f, 0.0f, 2.0f, 2, 16, 2, 0.0f, 4.0f, 0.0f, -4.0f, -4.0f, 0.0f, -4.0f, -4.0f, 0.0f, 4.0f, 4.0f, 0.0f, 4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.seatModel = new ModelRendererTurbo[5];
        this.seatModel[0] = new ModelRendererTurbo(this, 0, 10, textureX, textureY);
        this.seatModel[0].addBox(-4.0f, 18.0f, -4.0f, 8, 1, 8, 0.0f);
        this.seatModel[1] = new ModelRendererTurbo(this, 0, 19, textureX, textureY);
        this.seatModel[1].addBox(-2.0f, 19.0f, -4.0f, 4, 7, 1, 0.0f);
        this.seatModel[2] = new ModelRendererTurbo(this, 16, 19, textureX, textureY);
        this.seatModel[2].addBox(-2.0f, 19.0f, 3.0f, 4, 7, 1, 0.0f);
        this.seatModel[3] = new ModelRendererTurbo(this, 0, 27, textureX, textureY);
        this.seatModel[3].addBox(-3.0f, 18.0f, 4.0f, 6, 6, 2, 0.0f);
        this.seatModel[4] = new ModelRendererTurbo(this, 17, 27, textureX, textureY);
        this.seatModel[4].addBox(-3.0f, 19.0f, -9.0f, 6, 5, 5, 0.0f);
        this.gunModel = new ModelRendererTurbo[4];
        this.gunModel[0] = new ModelRendererTurbo(this, 0, 37, textureX, textureY);
        this.gunModel[0].addBox(-4.0f, -3.0f, -3.0f, 8, 6, 6, 0.0f);
        this.gunModel[1] = new ModelRendererTurbo(this, 0, 49, textureX, textureY);
        this.gunModel[1].addBox(4.0f, -1.0f, -2.0f, 2, 4, 4, 0.0f);
        this.gunModel[2] = new ModelRendererTurbo(this, 12, 49, textureX, textureY);
        this.gunModel[2].addBox(8.0f, -1.0f, -2.0f, 2, 4, 4, 0.0f);
        this.gunModel[3] = new ModelRendererTurbo(this, 24, 49, textureX, textureY);
        this.gunModel[3].addBox(14.0f, -1.0f, -2.0f, 4, 4, 4, 0.0f);
        this.barrelModel = new ModelRendererTurbo[4][1];
        this.barrelModel[0][0] = new ModelRendererTurbo(this, 34, 18, textureX, textureY);
        this.barrelModel[0][0].addBox(6.0f, 1.5f, 0.5f, 14, 1, 1, 0.0f);
        this.barrelModel[1][0] = new ModelRendererTurbo(this, 34, 20, textureX, textureY);
        this.barrelModel[1][0].addBox(6.0f, 1.5f, -1.5f, 14, 1, 1, 0.0f);
        this.barrelModel[2][0] = new ModelRendererTurbo(this, 34, 22, textureX, textureY);
        this.barrelModel[2][0].addBox(6.0f, -0.5f, -1.5f, 14, 1, 1, 0.0f);
        this.barrelModel[3][0] = new ModelRendererTurbo(this, 34, 24, textureX, textureY);
        this.barrelModel[3][0].addBox(6.0f, -0.5f, 0.5f, 14, 1, 1, 0.0f);
        this.ammoModel = new ModelRendererTurbo[0][0];
        this.barrelX = 0;
        this.barrelY = 24;
        this.barrelZ = 0;
    }
}

