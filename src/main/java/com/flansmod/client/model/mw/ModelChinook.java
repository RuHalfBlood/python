/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.ModelDriveable;
import com.flansmod.client.model.ModelPlane;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelChinook
extends ModelPlane {
    public ModelChinook() {
        int i;
        int textureX = 1024;
        int textureY = 512;
        this.bodyModel = new ModelRendererTurbo[9];
        this.bodyModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.bodyModel[0].addBox(-96.0f, 8.0f, -32.0f, 192, 2, 64);
        this.bodyModel[1] = new ModelRendererTurbo(this, 0, 132, textureX, textureY);
        this.bodyModel[1].addBox(-96.0f, 10.0f, -32.0f, 192, 64, 2);
        this.bodyModel[2] = new ModelRendererTurbo(this, 0, 198, textureX, textureY);
        this.bodyModel[2].addBox(-96.0f, 10.0f, 30.0f, 192, 64, 2);
        this.bodyModel[3] = new ModelRendererTurbo(this, 0, 66, textureX, textureY);
        this.bodyModel[3].addBox(-96.0f, 74.0f, -32.0f, 192, 2, 64);
        this.bodyModel[4] = new ModelRendererTurbo(this, 0, 264, textureX, textureY);
        this.bodyModel[4].addShapeBox(-96.0f, 10.0f, -48.0f, 192, 32, 16, 0.0f, -8.0f, -8.0f, 0.0f, -8.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f, -8.0f, 0.0f, -8.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[5] = new ModelRendererTurbo(this, 0, 312, textureX, textureY);
        this.bodyModel[5].addShapeBox(-96.0f, 10.0f, 32.0f, 192, 32, 16, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f, -8.0f, 0.0f, -8.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f, -8.0f, 0.0f, -8.0f, -8.0f, 0.0f);
        this.bodyModel[6] = new ModelRendererTurbo(this, 512, 314, textureX, textureY);
        this.bodyModel[6].addBox(-92.0f, 32.0f, -30.0f, 184, 2, 16);
        this.bodyModel[7] = new ModelRendererTurbo(this, 512, 332, textureX, textureY);
        this.bodyModel[7].addBox(-92.0f, 32.0f, 14.0f, 184, 2, 16);
        this.bodyModel[8] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.bodyModel[8].addBox(-96.0f, 24.0f, -32.0f, 192, 2, 64);
        this.bodyWheelModel = new ModelRendererTurbo[12];
        for (i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                this.bodyWheelModel[3 * i + 6 * j] = new ModelRendererTurbo(this, 624, 32, textureX, textureY);
                this.bodyWheelModel[3 * i + 6 * j].addBox(0.0f, -8.0f, -2.0f, 4, 16, 4);
                this.bodyWheelModel[3 * i + 6 * j].setRotationPoint(-80.0f * (float)(i == 0 ? 1 : -1), 0.0f, -24.0f * (float)(j == 0 ? 1 : -1));
                this.bodyWheelModel[1 + 3 * i + 6 * j] = new ModelRendererTurbo(this, 640, 12, textureX, textureY);
                this.bodyWheelModel[1 + 3 * i + 6 * j].addBox(-6.0f, -14.0f, -4.0f, 16, 16, 3);
                this.bodyWheelModel[1 + 3 * i + 6 * j].setRotationPoint(-80.0f * (float)(i == 0 ? 1 : -1), 0.0f, -24.0f * (float)(j == 0 ? 1 : -1));
                this.bodyWheelModel[2 + 3 * i + 6 * j] = new ModelRendererTurbo(this, 640, 12, textureX, textureY);
                this.bodyWheelModel[2 + 3 * i + 6 * j].addBox(-6.0f, -14.0f, 1.0f, 16, 16, 3);
                this.bodyWheelModel[2 + 3 * i + 6 * j].setRotationPoint(-80.0f * (float)(i == 0 ? 1 : -1), 0.0f, -24.0f * (float)(j == 0 ? 1 : -1));
            }
        }
        this.noseModel = new ModelRendererTurbo[17];
        this.noseModel[0] = new ModelRendererTurbo(this, 0, 360, textureX, textureY);
        this.noseModel[0].addShapeBox(-128.0f, 8.0f, -32.0f, 32, 2, 64, 0.0f, 0.0f, -4.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, -8.0f, 0.0f, 4.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 4.0f, -8.0f);
        this.noseModel[1] = new ModelRendererTurbo(this, 0, 426, textureX, textureY);
        this.noseModel[1].addShapeBox(-128.0f, 10.0f, -32.0f, 32, 64, 2, 0.0f, 0.0f, -4.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, 8.0f, -8.0f, -4.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f, -4.0f, 8.0f);
        this.noseModel[2] = new ModelRendererTurbo(this, 68, 426, textureX, textureY);
        this.noseModel[2].addShapeBox(-128.0f, 10.0f, 30.0f, 32, 64, 2, 0.0f, 0.0f, -4.0f, 8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, -8.0f, -8.0f, -4.0f, 8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f, -4.0f, -8.0f);
        this.noseModel[3] = new ModelRendererTurbo(this, 192, 360, textureX, textureY);
        this.noseModel[3].addShapeBox(-128.0f, 74.0f, -32.0f, 32, 2, 64, 0.0f, -8.0f, 4.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f, 4.0f, -8.0f, -8.0f, -4.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f, -4.0f, -8.0f);
        this.noseModel[4] = new ModelRendererTurbo(this, 136, 426, textureX, textureY);
        this.noseModel[4].addShapeBox(-125.75f, 44.0f, -24.0f, 2, 28, 48, 0.0f, 0.0f, 0.25f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.25f, -1.0f, -4.0f, 0.0f, 0.0f, 4.0f, 0.0f, 0.0f, 4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f);
        this.noseModel[5] = new ModelRendererTurbo(this, 236, 426, textureX, textureY);
        this.noseModel[5].addShapeBox(-152.0f, 12.0f, -24.0f, 24, 2, 48, 0.0f, 0.0f, -4.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, -8.0f, 0.0f, 4.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 4.0f, -8.0f);
        this.noseModel[6] = new ModelRendererTurbo(this, 128, 360, textureX, textureY);
        this.noseModel[6].addShapeBox(-152.0f, 14.0f, -24.0f, 24, 28, 2, 0.0f, 0.0f, -4.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, 8.0f, 0.0f, -4.0f, -8.0f, 4.0f, 0.0f, 0.0f, 4.0f, 0.0f, 0.0f, 0.0f, -4.0f, 8.0f);
        this.noseModel[7] = new ModelRendererTurbo(this, 128, 390, textureX, textureY);
        this.noseModel[7].addShapeBox(-152.0f, 14.0f, 22.0f, 24, 28, 2, 0.0f, 0.0f, -4.0f, 8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, -8.0f, 0.0f, -4.0f, 8.0f, 4.0f, 0.0f, 0.0f, 4.0f, 0.0f, 0.0f, 0.0f, -4.0f, -8.0f);
        this.noseModel[8] = new ModelRendererTurbo(this, 320, 360, textureX, textureY);
        this.noseModel[8].addShapeBox(-152.0f, 42.0f, -24.0f, 24, 2, 48, 0.0f, 0.0f, 4.0f, -8.0f, 4.0f, 0.0f, 0.0f, 4.0f, 0.0f, 0.0f, 0.0f, 4.0f, -8.0f, 0.0f, -4.0f, -8.0f, 4.25f, 0.0f, 0.0f, 4.25f, 0.0f, 0.0f, 0.0f, -4.0f, -8.0f);
        this.noseModel[9] = new ModelRendererTurbo(this, 448, 0, textureX, textureY);
        this.noseModel[9].addShapeBox(-152.0f, 18.0f, -14.0f, 2, 20, 28, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.noseModel[10] = new ModelRendererTurbo(this, 512, 0, textureX, textureY);
        this.noseModel[10].addShapeBox(-96.0f, 76.0f, -4.0f, 48, 24, 8, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -16.0f, 0.0f, 0.0f, -16.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.noseModel[11] = new ModelRendererTurbo(this, 512, 32, textureX, textureY);
        this.noseModel[11].addShapeBox(-120.0f, 76.0f, -4.0f, 24, 24, 8, 0.0f, 0.0f, 4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 4.0f, 0.0f, -16.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -16.0f, 0.0f, 0.0f);
        this.noseModel[12] = new ModelRendererTurbo(this, 512, 252, textureX, textureY);
        this.noseModel[12].addShapeBox(-124.0f, 24.0f, -30.0f, 28, 2, 60, 0.0f, 0.0f, 0.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f, 0.0f, 0.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f);
        this.noseModel[13] = new ModelRendererTurbo(this, 688, 196, textureX, textureY);
        this.noseModel[13].addBox(-112.0f, 26.0f, 8.0f, 16, 2, 16);
        this.noseModel[14] = new ModelRendererTurbo(this, 688, 196, textureX, textureY);
        this.noseModel[14].addBox(-112.0f, 26.0f, -24.0f, 16, 2, 16);
        this.noseModel[15] = new ModelRendererTurbo(this, 688, 214, textureX, textureY);
        this.noseModel[15].addBox(-96.0f, 26.0f, 8.0f, 2, 24, 16);
        this.noseModel[16] = new ModelRendererTurbo(this, 688, 214, textureX, textureY);
        this.noseModel[16].addBox(-96.0f, 26.0f, -24.0f, 2, 24, 16);
        this.tailModel = new ModelRendererTurbo[2];
        this.tailModel[0] = new ModelRendererTurbo(this, 512, 64, textureX, textureY);
        this.tailModel[0].addShapeBox(96.0f, 8.0f, -32.0f, 64, 68, 64, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -48.0f, -24.0f, 0.0f, -48.0f, -24.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -24.0f, 0.0f, 0.0f, -24.0f, 0.0f, 0.0f, 0.0f);
        this.tailModel[1] = new ModelRendererTurbo(this, 512, 196, textureX, textureY);
        this.tailModel[1].addShapeBox(80.0f, 76.0f, -4.0f, 80, 48, 8, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -16.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -16.0f, 0.0f, 0.0f);
        this.heliMainRotorModels = new ModelRendererTurbo[2][7];
        for (int j = 0; j < 2; ++j) {
            this.heliMainRotorModels[j][0] = new ModelRendererTurbo(this, 624, 0, textureX, textureY);
            this.heliMainRotorModels[j][0].addBox(-2.0f, 124.0f, -2.0f, 4, 8, 4);
            for (int i2 = 0; i2 < 3; ++i2) {
                this.heliMainRotorModels[j][i2 * 2 + 1] = new ModelRendererTurbo(this, 640, 0, textureX, textureY);
                this.heliMainRotorModels[j][i2 * 2 + 1].addBox(0.0f, 132.0f, -2.0f, 8, 1, 4);
                this.heliMainRotorModels[j][i2 * 2 + 1].rotateAngleY = 1.0471976f * (float)i2 * 2.0f;
                this.heliMainRotorModels[j][i2 * 2 + 2] = new ModelRendererTurbo(this, 664, 0, textureX, textureY);
                this.heliMainRotorModels[j][i2 * 2 + 2].addBox(8.0f, 132.0f, -2.0f, 128, 1, 8);
                this.heliMainRotorModels[j][i2 * 2 + 2].rotateAngleY = 1.0471976f * (float)i2 * 2.0f;
            }
        }
        for (i = 0; i < 7; ++i) {
            this.heliMainRotorModels[0][i].setRotationPoint(-84.0f, -24.0f, 0.0f);
        }
        for (i = 0; i < 7; ++i) {
            this.heliMainRotorModels[1][i].setRotationPoint(128.0f, 0.0f, 0.0f);
        }
        this.heliMainRotorOrigins = new Vector3f[]{new Vector3f(-5.25f, 3.0f, 0.0f), new Vector3f(8.0f, 3.0f, 0.0f)};
        this.heliRotorSpeeds = new float[]{1.0f, 1.0f};
        this.translateAll(0.0f, -15.0f, 0.0f);
    }
}

