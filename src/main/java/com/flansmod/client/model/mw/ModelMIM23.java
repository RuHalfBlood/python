/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.ModelDriveable;
import com.flansmod.client.model.ModelVehicle;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelMIM23
extends ModelVehicle {
    public ModelMIM23() {
        int textureX = 256;
        int textureY = 256;
        this.bodyModel = new ModelRendererTurbo[4];
        this.bodyModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.bodyModel[0].addBox(-24.0f, -4.0f, -8.0f, 48, 9, 16);
        this.bodyModel[1] = new ModelRendererTurbo(this, 0, 25, textureX, textureY);
        this.bodyModel[1].addBox(-24.0f, 5.0f, -16.0f, 48, 1, 32);
        this.bodyModel[2] = new ModelRendererTurbo(this, 0, 58, textureX, textureY);
        this.bodyModel[2].addBox(0.0f, -1.0f, -16.0f, 6, 1, 32);
        this.bodyModel[2].setRotationPoint(24.0f, 6.0f, 0.0f);
        this.bodyModel[2].rotateAngleZ = -0.7853982f;
        this.bodyModel[3] = new ModelRendererTurbo(this, 0, 58, textureX, textureY);
        this.bodyModel[3].addBox(0.0f, -1.0f, -16.0f, 6, 1, 32);
        this.bodyModel[3].setRotationPoint(-24.0f, 6.0f, 0.0f);
        this.bodyModel[3].rotateAngleZ = -0.7853982f;
        this.bodyModel[3].rotateAngleY = 3.1415927f;
        this.leftTrackModel = new ModelRendererTurbo[8];
        this.leftTrackModel[0] = new ModelRendererTurbo(this, 0, 94, textureX, textureY);
        this.leftTrackModel[0].addBox(-23.0f, -8.0f, -16.0f, 46, 1, 8);
        this.leftTrackModel[1] = new ModelRendererTurbo(this, 0, 104, textureX, textureY);
        this.leftTrackModel[1].addBox(-23.0f, 3.0f, -16.0f, 46, 1, 8);
        this.leftTrackModel[2] = new ModelRendererTurbo(this, 0, 114, textureX, textureY);
        this.leftTrackModel[2].addBox(-23.0f, -7.0f, -16.0f, 1, 10, 8);
        this.leftTrackModel[3] = new ModelRendererTurbo(this, 20, 114, textureX, textureY);
        this.leftTrackModel[3].addBox(22.0f, -7.0f, -16.0f, 1, 10, 8);
        this.leftTrackModel[4] = new ModelRendererTurbo(this, 40, 114, textureX, textureY);
        this.leftTrackModel[4].addBox(-21.5f, -6.5f, -16.0f, 9, 9, 4);
        this.leftTrackModel[5] = new ModelRendererTurbo(this, 40, 114, textureX, textureY);
        this.leftTrackModel[5].addBox(-10.5f, -6.5f, -16.0f, 9, 9, 4);
        this.leftTrackModel[6] = new ModelRendererTurbo(this, 40, 114, textureX, textureY);
        this.leftTrackModel[6].addBox(1.5f, -6.5f, -16.0f, 9, 9, 4);
        this.leftTrackModel[7] = new ModelRendererTurbo(this, 40, 114, textureX, textureY);
        this.leftTrackModel[7].addBox(12.5f, -6.5f, -16.0f, 9, 9, 4);
        this.rightTrackModel = new ModelRendererTurbo[8];
        this.rightTrackModel[0] = new ModelRendererTurbo(this, 0, 94, textureX, textureY);
        this.rightTrackModel[0].addBox(-23.0f, -8.0f, -16.0f, 46, 1, 8);
        this.rightTrackModel[0].rotateAngleY = 3.1415927f;
        this.rightTrackModel[1] = new ModelRendererTurbo(this, 0, 104, textureX, textureY);
        this.rightTrackModel[1].addBox(-23.0f, 3.0f, -16.0f, 46, 1, 8);
        this.rightTrackModel[1].rotateAngleY = 3.1415927f;
        this.rightTrackModel[2] = new ModelRendererTurbo(this, 0, 114, textureX, textureY);
        this.rightTrackModel[2].addBox(-23.0f, -7.0f, -16.0f, 1, 10, 8);
        this.rightTrackModel[2].rotateAngleY = 3.1415927f;
        this.rightTrackModel[3] = new ModelRendererTurbo(this, 20, 114, textureX, textureY);
        this.rightTrackModel[3].addBox(22.0f, -7.0f, -16.0f, 1, 10, 8);
        this.rightTrackModel[3].rotateAngleY = 3.1415927f;
        this.rightTrackModel[4] = new ModelRendererTurbo(this, 40, 114, textureX, textureY);
        this.rightTrackModel[4].addBox(-21.5f, -6.5f, -16.0f, 9, 9, 4);
        this.rightTrackModel[4].rotateAngleY = 3.1415927f;
        this.rightTrackModel[5] = new ModelRendererTurbo(this, 40, 114, textureX, textureY);
        this.rightTrackModel[5].addBox(-10.5f, -6.5f, -16.0f, 9, 9, 4);
        this.rightTrackModel[5].rotateAngleY = 3.1415927f;
        this.rightTrackModel[6] = new ModelRendererTurbo(this, 40, 114, textureX, textureY);
        this.rightTrackModel[6].addBox(1.5f, -6.5f, -16.0f, 9, 9, 4);
        this.rightTrackModel[6].rotateAngleY = 3.1415927f;
        this.rightTrackModel[7] = new ModelRendererTurbo(this, 40, 114, textureX, textureY);
        this.rightTrackModel[7].addBox(12.5f, -6.5f, -16.0f, 9, 9, 4);
        this.rightTrackModel[7].rotateAngleY = 3.1415927f;
        this.turretModel = new ModelRendererTurbo[7];
        this.turretModel[0] = new ModelRendererTurbo(this, 0, 134, textureX, textureY);
        this.turretModel[0].addBox(-12.0f, 6.0f, -12.0f, 24, 2, 24);
        this.turretModel[1] = new ModelRendererTurbo(this, 0, 162, textureX, textureY);
        this.turretModel[1].addBox(4.0f, 8.0f, -6.0f, 1, 16, 12);
        this.turretModel[2] = new ModelRendererTurbo(this, 28, 162, textureX, textureY);
        this.turretModel[2].addBox(12.0f, 6.0f, -6.0f, 4, 2, 12);
        this.turretModel[3] = new ModelRendererTurbo(this, 0, 192, textureX, textureY);
        this.turretModel[3].addShapeBox(-12.0f, 8.0f, -6.0f, 16, 16, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f, 0.0f, 0.0f);
        this.turretModel[4] = new ModelRendererTurbo(this, 0, 212, textureX, textureY);
        this.turretModel[4].addBox(-4.0f, 24.0f, -6.0f, 8, 8, 2);
        this.turretModel[5] = new ModelRendererTurbo(this, 38, 192, textureX, textureY);
        this.turretModel[5].addShapeBox(-12.0f, 8.0f, 4.0f, 16, 16, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.0f, 0.0f, 0.0f);
        this.turretModel[6] = new ModelRendererTurbo(this, 38, 212, textureX, textureY);
        this.turretModel[6].addBox(-4.0f, 24.0f, 4.0f, 8, 8, 2);
        this.barrelModel = new ModelRendererTurbo[4];
        this.barrelModel[0] = new ModelRendererTurbo(this, 0, 224, textureX, textureY);
        this.barrelModel[0].addBox(-12.0f, -4.0f, -4.0f, 24, 8, 8);
        this.barrelModel[0].setRotationPoint(0.0f, 32.0f, 0.0f);
        this.barrelModel[1] = new ModelRendererTurbo(this, 0, 242, textureX, textureY);
        this.barrelModel[1].addBox(-6.0f, 4.0f, -1.0f, 12, 8, 2);
        this.barrelModel[1].setRotationPoint(0.0f, 32.0f, 0.0f);
        this.barrelModel[2] = new ModelRendererTurbo(this, 30, 242, textureX, textureY);
        this.barrelModel[2].addBox(-6.0f, -1.0f, -12.0f, 12, 2, 8);
        this.barrelModel[2].setRotationPoint(0.0f, 32.0f, 0.0f);
        this.barrelModel[3] = new ModelRendererTurbo(this, 30, 242, textureX, textureY);
        this.barrelModel[3].addBox(-6.0f, -1.0f, 4.0f, 12, 2, 8);
        this.barrelModel[3].setRotationPoint(0.0f, 32.0f, 0.0f);
        this.ammoModel = new ModelRendererTurbo[3][4];
        for (int i = 0; i < 3; ++i) {
            float yOffset = i == 1 ? 12.0f : 0.0f;
            this.ammoModel[i] = new ModelRendererTurbo[4];
            this.ammoModel[i][0] = new ModelRendererTurbo(this, 72, 212, textureX, textureY);
            this.ammoModel[i][0].addBox(-20.0f, -3.0f + yOffset, -3.0f + (float)i * 12.0f - 12.0f, 40, 6, 6);
            this.ammoModel[i][1] = new ModelRendererTurbo(this, 72, 226, textureX, textureY);
            this.ammoModel[i][1].addTrapezoid(20.0f, -3.0f + yOffset, -3.0f + (float)i * 12.0f - 12.0f, 8, 6, 6, 0.0f, -3.0f, 2);
            this.ammoModel[i][2] = new ModelRendererTurbo(this, 102, 226, textureX, textureY);
            this.ammoModel[i][2].addShapeBox(-20.0f, -7.0f + yOffset, 0.0f + (float)i * 12.0f - 12.0f, 20, 14, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f);
            this.ammoModel[i][3] = new ModelRendererTurbo(this, 88, 242, textureX, textureY);
            this.ammoModel[i][3].addShapeBox(-20.0f, 0.0f + yOffset, -7.0f + (float)i * 12.0f - 12.0f, 20, 0, 14, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f);
            for (int j = 0; j < 4; ++j) {
                this.ammoModel[i][j].setRotationPoint(0.0f, 32.0f, 0.0f);
            }
        }
    }
}

