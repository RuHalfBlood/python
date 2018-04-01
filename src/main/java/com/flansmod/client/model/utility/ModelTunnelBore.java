/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.flansmod.client.model.utility;

import com.flansmod.client.model.ModelDriveable;
import com.flansmod.client.model.ModelVehicle;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTunnelBore
extends ModelVehicle {
    int textureX = 256;
    int textureY = 512;

    public ModelTunnelBore() {
        this.bodyModel = new ModelRendererTurbo[6];
        this.bodyModel[0] = new ModelRendererTurbo(this, 0, 0, this.textureX, this.textureY);
        this.bodyModel[1] = new ModelRendererTurbo(this, 0, 56, this.textureX, this.textureY);
        this.bodyModel[2] = new ModelRendererTurbo(this, 0, 104, this.textureX, this.textureY);
        this.bodyModel[3] = new ModelRendererTurbo(this, 0, 200, this.textureX, this.textureY);
        this.bodyModel[4] = new ModelRendererTurbo(this, 0, 216, this.textureX, this.textureY);
        this.bodyModel[5] = new ModelRendererTurbo(this, 0, 232, this.textureX, this.textureY);
        this.bodyModel[0].addBox(0.0f, 0.0f, 0.0f, 64, 8, 48, 0.0f);
        this.bodyModel[0].setRotationPoint(-32.0f, -16.0f, -24.0f);
        this.bodyModel[1].addBox(0.0f, 0.0f, 0.0f, 62, 16, 32, 0.0f);
        this.bodyModel[1].setRotationPoint(-30.0f, -8.0f, -16.0f);
        this.bodyModel[2].addBox(0.0f, 0.0f, 0.0f, 16, 48, 48, 0.0f);
        this.bodyModel[2].setRotationPoint(32.0f, -40.0f, -24.0f);
        this.bodyModel[3].addBox(0.0f, 0.0f, 0.0f, 32, 8, 8, 0.0f);
        this.bodyModel[3].setRotationPoint(0.0f, -24.0f, -24.0f);
        this.bodyModel[4].addBox(0.0f, 0.0f, 0.0f, 32, 8, 8, 0.0f);
        this.bodyModel[4].setRotationPoint(0.0f, -24.0f, 16.0f);
        this.bodyModel[5].addBox(0.0f, 0.0f, 0.0f, 32, 8, 48, 0.0f);
        this.bodyModel[5].setRotationPoint(-32.0f, -24.0f, -24.0f);
        this.drillHeadModel = new ModelRendererTurbo[9];
        this.drillHeadModel[0] = new ModelRendererTurbo(this, 0, 288, this.textureX, this.textureY);
        this.drillHeadModel[1] = new ModelRendererTurbo(this, 0, 360, this.textureX, this.textureY);
        this.drillHeadModel[2] = new ModelRendererTurbo(this, 0, 424, this.textureX, this.textureY);
        this.drillHeadModel[3] = new ModelRendererTurbo(this, 80, 288, this.textureX, this.textureY);
        this.drillHeadModel[4] = new ModelRendererTurbo(this, 80, 336, this.textureX, this.textureY);
        this.drillHeadModel[5] = new ModelRendererTurbo(this, 72, 376, this.textureX, this.textureY);
        this.drillHeadModel[6] = new ModelRendererTurbo(this, 72, 408, this.textureX, this.textureY);
        this.drillHeadModel[7] = new ModelRendererTurbo(this, 64, 432, this.textureX, this.textureY);
        this.drillHeadModel[8] = new ModelRendererTurbo(this, 64, 448, this.textureX, this.textureY);
        this.drillHeadModel[0].addBox(0.0f, -18.0f, -18.0f, 4, 36, 36, 0.0f);
        this.drillHeadModel[0].setRotationPoint(48.0f, -16.0f, 0.0f);
        this.drillHeadModel[1].addBox(0.0f, -16.0f, -16.0f, 4, 32, 32, 0.0f);
        this.drillHeadModel[1].setRotationPoint(52.0f, -16.0f, 0.0f);
        this.drillHeadModel[1].rotateAngleX = 0.41887903f;
        this.drillHeadModel[2].addBox(0.0f, -14.0f, -14.0f, 4, 28, 28, 0.0f);
        this.drillHeadModel[2].setRotationPoint(56.0f, -16.0f, 0.0f);
        this.drillHeadModel[2].rotateAngleX = 0.83775806f;
        this.drillHeadModel[3].addBox(0.0f, -12.0f, -12.0f, 4, 24, 24, 0.0f);
        this.drillHeadModel[3].setRotationPoint(60.0f, -16.0f, 0.0f);
        this.drillHeadModel[3].rotateAngleX = 1.2566371f;
        this.drillHeadModel[4].addBox(0.0f, -10.0f, -10.0f, 4, 20, 20, 0.0f);
        this.drillHeadModel[4].setRotationPoint(64.0f, -16.0f, 0.0f);
        this.drillHeadModel[4].rotateAngleX = 1.6755161f;
        this.drillHeadModel[5].addBox(0.0f, -8.0f, -8.0f, 4, 16, 16, 0.0f);
        this.drillHeadModel[5].setRotationPoint(68.0f, -16.0f, 0.0f);
        this.drillHeadModel[5].rotateAngleX = 2.0943952f;
        this.drillHeadModel[6].addBox(0.0f, -6.0f, -6.0f, 4, 12, 12, 0.0f);
        this.drillHeadModel[6].setRotationPoint(72.0f, -16.0f, 0.0f);
        this.drillHeadModel[6].rotateAngleX = 2.5132742f;
        this.drillHeadModel[7].addBox(0.0f, -4.0f, -4.0f, 4, 8, 8, 0.0f);
        this.drillHeadModel[7].setRotationPoint(76.0f, -16.0f, 0.0f);
        this.drillHeadModel[7].rotateAngleX = 2.9321532f;
        this.drillHeadModel[8].addBox(0.0f, -2.0f, -2.0f, 4, 4, 4, 0.0f);
        this.drillHeadModel[8].setRotationPoint(80.0f, -16.0f, 0.0f);
        this.drillHeadModel[8].rotateAngleX = 3.3510323f;
        this.drillHeadOrigin = new Vector3f(3.0f, 1.0f, 0.0f);
        this.leftTrackModel = new ModelRendererTurbo[10];
        this.leftTrackModel[0] = new ModelRendererTurbo(this, 0, 489, this.textureX, this.textureY);
        this.leftTrackModel[1] = new ModelRendererTurbo(this, 0, 489, this.textureX, this.textureY);
        this.leftTrackModel[2] = new ModelRendererTurbo(this, 0, 489, this.textureX, this.textureY);
        this.leftTrackModel[3] = new ModelRendererTurbo(this, 0, 489, this.textureX, this.textureY);
        this.leftTrackModel[4] = new ModelRendererTurbo(this, 0, 480, this.textureX, this.textureY);
        this.leftTrackModel[5] = new ModelRendererTurbo(this, 0, 489, this.textureX, this.textureY);
        this.leftTrackModel[6] = new ModelRendererTurbo(this, 32, 490, this.textureX, this.textureY);
        this.leftTrackModel[7] = new ModelRendererTurbo(this, 0, 480, this.textureX, this.textureY);
        this.leftTrackModel[8] = new ModelRendererTurbo(this, 32, 490, this.textureX, this.textureY);
        this.leftTrackModel[9] = new ModelRendererTurbo(this, 54, 490, this.textureX, this.textureY);
        this.leftTrackModel[0].addBox(0.0f, 0.0f, 0.0f, 8, 8, 6, 0.0f);
        this.leftTrackModel[0].setRotationPoint(2.0f, 0.0f, 17.0f);
        this.leftTrackModel[1].addBox(0.0f, 0.0f, 0.0f, 8, 8, 6, 0.0f);
        this.leftTrackModel[1].setRotationPoint(13.0f, 0.0f, 17.0f);
        this.leftTrackModel[2].addBox(0.0f, 0.0f, 0.0f, 8, 8, 6, 0.0f);
        this.leftTrackModel[2].setRotationPoint(-20.0f, 0.0f, 17.0f);
        this.leftTrackModel[3].addBox(0.0f, 0.0f, 0.0f, 8, 8, 6, 0.0f);
        this.leftTrackModel[3].setRotationPoint(-9.0f, 0.0f, 17.0f);
        this.leftTrackModel[4].addBox(0.0f, 0.0f, 0.0f, 60, 1, 8, 0.0f);
        this.leftTrackModel[4].setRotationPoint(-30.0f, 8.0f, 16.0f);
        this.leftTrackModel[5].addBox(0.0f, 0.0f, 0.0f, 8, 8, 6, 0.0f);
        this.leftTrackModel[5].setRotationPoint(-30.0f, -6.0f, 17.0f);
        this.leftTrackModel[6].addBox(0.0f, 0.0f, 0.0f, 1, 14, 8, 0.0f);
        this.leftTrackModel[6].setRotationPoint(-31.0f, -6.0f, 16.0f);
        this.leftTrackModel[7].addBox(0.0f, 0.0f, 0.0f, 60, 1, 8, 0.0f);
        this.leftTrackModel[7].setRotationPoint(-30.0f, -7.0f, 16.0f);
        this.leftTrackModel[8].addBox(0.0f, 0.0f, 0.0f, 1, 14, 8, 0.0f);
        this.leftTrackModel[8].setRotationPoint(30.0f, -6.0f, 16.0f);
        this.leftTrackModel[9].addBox(0.0f, 0.0f, 0.0f, 6, 6, 6, 0.0f);
        this.leftTrackModel[9].setRotationPoint(24.0f, -6.0f, 17.0f);
        this.rightTrackModel = new ModelRendererTurbo[10];
        this.rightTrackModel[0] = new ModelRendererTurbo(this, 0, 480, this.textureX, this.textureY);
        this.rightTrackModel[1] = new ModelRendererTurbo(this, 0, 480, this.textureX, this.textureY);
        this.rightTrackModel[2] = new ModelRendererTurbo(this, 32, 490, this.textureX, this.textureY);
        this.rightTrackModel[3] = new ModelRendererTurbo(this, 32, 490, this.textureX, this.textureY);
        this.rightTrackModel[4] = new ModelRendererTurbo(this, 0, 489, this.textureX, this.textureY);
        this.rightTrackModel[5] = new ModelRendererTurbo(this, 54, 490, this.textureX, this.textureY);
        this.rightTrackModel[6] = new ModelRendererTurbo(this, 0, 489, this.textureX, this.textureY);
        this.rightTrackModel[7] = new ModelRendererTurbo(this, 0, 489, this.textureX, this.textureY);
        this.rightTrackModel[8] = new ModelRendererTurbo(this, 0, 489, this.textureX, this.textureY);
        this.rightTrackModel[9] = new ModelRendererTurbo(this, 0, 489, this.textureX, this.textureY);
        this.rightTrackModel[0].addBox(0.0f, 0.0f, 0.0f, 60, 1, 8, 0.0f);
        this.rightTrackModel[0].setRotationPoint(-30.0f, 8.0f, -24.0f);
        this.rightTrackModel[1].addBox(0.0f, 0.0f, 0.0f, 60, 1, 8, 0.0f);
        this.rightTrackModel[1].setRotationPoint(-30.0f, -7.0f, -24.0f);
        this.rightTrackModel[2].addBox(0.0f, 0.0f, 0.0f, 1, 14, 8, 0.0f);
        this.rightTrackModel[2].setRotationPoint(-31.0f, -6.0f, -24.0f);
        this.rightTrackModel[3].addBox(0.0f, 0.0f, 0.0f, 1, 14, 8, 0.0f);
        this.rightTrackModel[3].setRotationPoint(30.0f, -6.0f, -24.0f);
        this.rightTrackModel[4].addBox(0.0f, 0.0f, 0.0f, 8, 8, 6, 0.0f);
        this.rightTrackModel[4].setRotationPoint(-30.0f, -6.0f, -23.0f);
        this.rightTrackModel[5].addBox(0.0f, 0.0f, 0.0f, 6, 6, 6, 0.0f);
        this.rightTrackModel[5].setRotationPoint(24.0f, -6.0f, -23.0f);
        this.rightTrackModel[6].addBox(0.0f, 0.0f, 0.0f, 8, 8, 6, 0.0f);
        this.rightTrackModel[6].setRotationPoint(13.0f, 0.0f, -23.0f);
        this.rightTrackModel[7].addBox(0.0f, 0.0f, 0.0f, 8, 8, 6, 0.0f);
        this.rightTrackModel[7].setRotationPoint(2.0f, 0.0f, -23.0f);
        this.rightTrackModel[8].addBox(0.0f, 0.0f, 0.0f, 8, 8, 6, 0.0f);
        this.rightTrackModel[8].setRotationPoint(-9.0f, 0.0f, -23.0f);
        this.rightTrackModel[9].addBox(0.0f, 0.0f, 0.0f, 8, 8, 6, 0.0f);
        this.rightTrackModel[9].setRotationPoint(-20.0f, 0.0f, -23.0f);
        this.translateAll(0.0f, 0.0f, 0.0f);
        this.flipAll();
    }
}

