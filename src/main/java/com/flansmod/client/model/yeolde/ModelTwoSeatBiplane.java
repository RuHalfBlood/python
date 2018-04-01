/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.flansmod.client.model.yeolde;

import com.flansmod.client.model.ModelDriveable;
import com.flansmod.client.model.ModelPlane;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTwoSeatBiplane
extends ModelPlane {
    public ModelTwoSeatBiplane() {
        int textureX = 128;
        int textureY = 64;
        this.propellerModels = new ModelRendererTurbo[1][3];
        this.propellerModels[0][0] = new ModelRendererTurbo(this, 56, 8, textureX, textureY);
        this.propellerModels[0][1] = new ModelRendererTurbo(this, 56, 8, textureX, textureY);
        this.propellerModels[0][2] = new ModelRendererTurbo(this, 56, 8, textureX, textureY);
        this.propellerModels[0][0].addBox(-1.0f, 2.0f, -1.0f, 2, 5, 2, 0.0f);
        this.propellerModels[0][1].addBox(-1.0f, 2.0f, -1.0f, 2, 5, 2, 0.0f);
        this.propellerModels[0][2].addBox(-1.0f, 2.0f, -1.0f, 2, 5, 2, 0.0f);
        this.propellerModels[0][0].setRotationPoint(-14.0f, 0.0f, 0.0f);
        this.propellerModels[0][1].setRotationPoint(-14.0f, 0.0f, 0.0f);
        this.propellerModels[0][2].setRotationPoint(-14.0f, 0.0f, 0.0f);
        this.yawFlapModel = new ModelRendererTurbo[1];
        this.yawFlapModel[0] = new ModelRendererTurbo(this, 104, 0, textureX, textureY);
        this.yawFlapModel[0].addBox(0.0f, 0.0f, 0.0f, 10, 10, 2, 0.0f);
        this.yawFlapModel[0].setPosition(44.0f, -12.0f, -1.0f);
        this.pitchFlapLeftModel = new ModelRendererTurbo[1];
        this.pitchFlapLeftModel[0] = new ModelRendererTurbo(this, 104, 0, textureX, textureY);
        this.pitchFlapLeftModel[0].addBox(0.0f, 0.0f, 0.0f, 10, 15, 2, 0.0f);
        this.pitchFlapLeftModel[0].rotateAngleX = 1.570796f;
        this.pitchFlapLeftModel[0].setPosition(44.0f, 3.0f, -15.0f);
        this.pitchFlapRightModel = new ModelRendererTurbo[1];
        this.pitchFlapRightModel[0] = new ModelRendererTurbo(this, 104, 0, textureX, textureY);
        this.pitchFlapRightModel[0].addBox(0.0f, 0.0f, 0.0f, 10, 15, 2, 0.0f);
        this.pitchFlapRightModel[0].rotateAngleX = 1.570796f;
        this.pitchFlapRightModel[0].setPosition(44.0f, 3.0f, 0.0f);
        this.bodyModel = new ModelRendererTurbo[6];
        this.bodyModel[0] = new ModelRendererTurbo(this, 0, 8, textureX, textureY);
        this.bodyModel[1] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.bodyModel[2] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.bodyModel[3] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.bodyModel[4] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.bodyModel[5] = new ModelRendererTurbo(this, 44, 0, textureX, textureY);
        int length = 24;
        int sidePieceHeight = 6;
        int width = 20;
        int boatDepth = 4;
        this.bodyModel[0].addBox((- length) / 2, (- width) / 2 + 2, -3.0f, length, width - 4, 4, 0.0f);
        this.bodyModel[0].setPosition(0.0f, boatDepth, 0.0f);
        this.bodyModel[1].addBox((- length) / 2 + 2, - sidePieceHeight - 1, -1.0f, length - 4, sidePieceHeight, 2, 0.0f);
        this.bodyModel[1].setPosition((- length) / 2 + 1, boatDepth, 0.0f);
        this.bodyModel[2].addBox((- length) / 2 + 2, - sidePieceHeight - 1, -1.0f, length - 4, sidePieceHeight, 2, 0.0f);
        this.bodyModel[2].setPosition(length / 2 - 1, boatDepth, 0.0f);
        this.bodyModel[3].addBox((- length) / 2 + 2, - sidePieceHeight - 1, -1.0f, length - 4, sidePieceHeight, 2, 0.0f);
        this.bodyModel[3].setPosition(0.0f, boatDepth, (- width) / 2 + 1);
        this.bodyModel[4].addBox((- length) / 2 + 2, - sidePieceHeight - 1, -1.0f, length - 4, sidePieceHeight, 2, 0.0f);
        this.bodyModel[4].setPosition(0.0f, boatDepth, width / 2 - 1);
        this.bodyModel[5].addBox(0.0f, 0.0f, 0.0f, 4, 4, 4, 0.0f);
        this.bodyModel[5].setPosition(-15.0f, -2.0f, -2.0f);
        this.bodyModel[0].rotateAngleX = 1.570796f;
        this.bodyModel[1].rotateAngleY = 4.712389f;
        this.bodyModel[2].rotateAngleY = 1.570796f;
        this.bodyModel[3].rotateAngleY = 3.141593f;
        this.bayModel = new ModelRendererTurbo[5];
        this.bayModel[0] = new ModelRendererTurbo(this, 0, 8, textureX, textureY);
        this.bayModel[1] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.bayModel[2] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.bayModel[3] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.bayModel[4] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.bayModel[0].addBox((- length) / 2, (- width) / 2 + 2, -3.0f, length, width - 4, 4, 0.0f);
        this.bayModel[0].setPosition(length, boatDepth, 0.0f);
        this.bayModel[1].addBox((- length) / 2 + 2, - sidePieceHeight - 1, -1.0f, length - 4, sidePieceHeight, 2, 0.0f);
        this.bayModel[1].setPosition(length - length / 2 + 1, boatDepth, 0.0f);
        this.bayModel[2].addBox((- length) / 2 + 2, - sidePieceHeight - 1, -1.0f, length - 4, sidePieceHeight, 2, 0.0f);
        this.bayModel[2].setPosition(length + length / 2 - 1, boatDepth, 0.0f);
        this.bayModel[3].addBox((- length) / 2 + 2, - sidePieceHeight - 1, -1.0f, length - 4, sidePieceHeight, 2, 0.0f);
        this.bayModel[3].setPosition(length, boatDepth, (- width) / 2 + 1);
        this.bayModel[4].addBox((- length) / 2 + 2, - sidePieceHeight - 1, -1.0f, length - 4, sidePieceHeight, 2, 0.0f);
        this.bayModel[4].setPosition(length, boatDepth, width / 2 - 1);
        this.bayModel[0].rotateAngleX = 1.570796f;
        this.bayModel[1].rotateAngleY = 4.712389f;
        this.bayModel[2].rotateAngleY = 1.570796f;
        this.bayModel[3].rotateAngleY = 3.141593f;
        this.bodyWheelModel = new ModelRendererTurbo[4];
        this.bodyWheelModel[0] = new ModelRendererTurbo(this, 0, 32, textureX, textureY);
        this.bodyWheelModel[1] = new ModelRendererTurbo(this, 0, 32, textureX, textureY);
        this.bodyWheelModel[2] = new ModelRendererTurbo(this, 56, 15, textureX, textureY);
        this.bodyWheelModel[3] = new ModelRendererTurbo(this, 56, 15, textureX, textureY);
        this.bodyWheelModel[0].addBox(-4.0f, 11.0f, 5.0f, 4, 4, 2, 0.0f);
        this.bodyWheelModel[1].addBox(-4.0f, 11.0f, -7.0f, 4, 4, 2, 0.0f);
        this.bodyWheelModel[2].addBox(-3.0f, 6.0f, 5.0f, 2, 5, 2, 0.0f);
        this.bodyWheelModel[3].addBox(-3.0f, 6.0f, -7.0f, 2, 5, 2, 0.0f);
        this.rightWingModel = new ModelRendererTurbo[2];
        this.rightWingModel[0] = new ModelRendererTurbo(this, 64, 0, textureX, textureY);
        this.rightWingModel[1] = new ModelRendererTurbo(this, 56, 15, textureX, textureY);
        this.rightWingModel[0].addBox(0.0f, 0.0f, 0.0f, 18, 41, 2, 0.0f);
        this.rightWingModel[0].rotateAngleX = -1.570796f;
        this.rightWingModel[0].setPosition(-9.0f, 0.0f, -9.0f);
        this.rightWingModel[1].addBox(-1.0f, -18.0f, -40.0f, 2, 18, 2, 0.0f);
        this.leftWingModel = new ModelRendererTurbo[2];
        this.leftWingModel[0] = new ModelRendererTurbo(this, 64, 0, textureX, textureY);
        this.leftWingModel[1] = new ModelRendererTurbo(this, 56, 15, textureX, textureY);
        this.leftWingModel[0].addBox(0.0f, 0.0f, 0.0f, 18, 41, 2, 0.0f);
        this.leftWingModel[0].rotateAngleX = -1.570796f;
        this.leftWingModel[0].setPosition(-9.0f, 0.0f, 50.0f);
        this.leftWingModel[1].addBox(-1.0f, -18.0f, 38.0f, 2, 18, 2, 0.0f);
        this.topWingModel = new ModelRendererTurbo[3];
        this.topWingModel[0] = new ModelRendererTurbo(this, 64, 0, textureX, textureY);
        this.topWingModel[1] = new ModelRendererTurbo(this, 64, 0, textureX, textureY);
        this.topWingModel[2] = new ModelRendererTurbo(this, 64, 0, textureX, textureY);
        this.topWingModel[0].addBox(0.0f, 0.0f, 0.0f, 18, 18, 2, 0.0f);
        this.topWingModel[0].rotateAngleX = -1.570796f;
        this.topWingModel[0].setPosition(-9.0f, -20.0f, 9.0f);
        this.topWingModel[1].addBox(0.0f, 0.0f, 0.0f, 18, 41, 2, 0.0f);
        this.topWingModel[1].rotateAngleX = -1.570796f;
        this.topWingModel[1].setPosition(-9.0f, -20.0f, 50.0f);
        this.topWingModel[2].addBox(0.0f, 0.0f, 0.0f, 18, 41, 2, 0.0f);
        this.topWingModel[2].rotateAngleX = -1.570796f;
        this.topWingModel[2].setPosition(-9.0f, -20.0f, -9.0f);
        this.tailModel = new ModelRendererTurbo[1];
        this.tailModel[0] = new ModelRendererTurbo(this, 0, 43, textureX, textureY);
        this.tailModel[0].addBox(35.0f, -2.0f, -5.0f, 20, 8, 10, 0.0f);
        this.tailWheelModel = new ModelRendererTurbo[1];
        this.tailWheelModel[0] = new ModelRendererTurbo(this, 0, 32, textureX, textureY);
        this.tailWheelModel[0].addBox(44.0f, 6.0f, -1.0f, 4, 4, 2, 0.0f);
        this.flipAll();
    }
}

