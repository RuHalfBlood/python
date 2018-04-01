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

public class ModelR870
extends ModelGun {
    int textureX = 128;
    int textureY = 128;

    public ModelR870() {
        this.gunModel = new ModelRendererTurbo[22];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 25, 1, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 1, 17, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 89, 9, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 1, 25, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 97, 1, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 17, 1, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 113, 1, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 25, 1, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 89, 25, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 41, 9, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 73, 17, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 105, 25, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 97, 25, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 1, 33, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 41, 1, this.textureX, this.textureY);
        this.gunModel[17] = new ModelRendererTurbo(this, 1, 41, this.textureX, this.textureY);
        this.gunModel[18] = new ModelRendererTurbo(this, 25, 33, this.textureX, this.textureY);
        this.gunModel[19] = new ModelRendererTurbo(this, 81, 33, this.textureX, this.textureY);
        this.gunModel[20] = new ModelRendererTurbo(this, 105, 41, this.textureX, this.textureY);
        this.gunModel[21] = new ModelRendererTurbo(this, 97, 1, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 4, 8, 5, 0.0f, 0.0f, 0.0f, 0.0f, -0.6f, -2.0f, 0.0f, -0.6f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, -1.0f);
        this.gunModel[0].setRotationPoint(1.0f, -9.0f, -2.5f);
        this.gunModel[0].rotateAngleZ = -1.0471976f;
        this.gunModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 4, 5, 5, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[1].setRotationPoint(1.0f, -10.0f, -2.5f);
        this.gunModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 36, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[2].setRotationPoint(17.0f, -11.0f, -1.0f);
        this.gunModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 11, 3, 5, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[3].setRotationPoint(5.0f, -8.5f, -2.5f);
        this.gunModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 11, 1, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f);
        this.gunModel[4].setRotationPoint(5.0f, -5.5f, -2.5f);
        this.gunModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 5, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[5].setRotationPoint(5.0f, -11.0f, -2.5f);
        this.gunModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[6].setRotationPoint(-1.5f, -4.5f, -0.5f);
        this.gunModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 1, 3, 1, 0.0f, -1.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[7].setRotationPoint(3.5f, -5.5f, -0.5f);
        this.gunModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 1, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[8].setRotationPoint(-1.0f, -2.5f, -0.5f);
        this.gunModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[9].setRotationPoint(1.0f, -5.5f, -0.5f);
        this.gunModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 1, 4, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[10].setRotationPoint(16.0f, -10.0f, -2.5f);
        this.gunModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f);
        this.gunModel[11].setRotationPoint(16.0f, -5.5f, -2.5f);
        this.gunModel[12].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 5, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[12].setRotationPoint(16.0f, -6.5f, -2.5f);
        this.gunModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 5, 2, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[13].setRotationPoint(5.0f, -10.0f, -2.5f);
        this.gunModel[14].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[14].setRotationPoint(10.0f, -10.0f, 1.5f);
        this.gunModel[15].addShapeBox(0.0f, 0.0f, 0.0f, 7, 1, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[15].setRotationPoint(10.0f, -11.0f, -0.5f);
        this.gunModel[16].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 2, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[16].setRotationPoint(15.0f, -11.0f, -2.5f);
        this.gunModel[17].addShapeBox(0.0f, 0.0f, 0.0f, 28, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[17].setRotationPoint(17.0f, -8.0f, -1.0f);
        this.gunModel[18].addShapeBox(0.0f, 0.0f, 0.0f, 2, 3, 2, 0.0f, -0.2f, 0.0f, -0.2f, 25.8f, 0.0f, -0.2f, 25.8f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, 25.8f, 0.0f, -0.2f, 25.8f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f);
        this.gunModel[18].setRotationPoint(17.0f, -9.05f, -1.0f);
        this.gunModel[19].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[19].setRotationPoint(15.0f, -10.0f, -2.5f);
        this.gunModel[20].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[20].setRotationPoint(10.0f, -9.0f, -2.5f);
        this.gunModel[21].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f);
        this.gunModel[21].setRotationPoint(53.0f, -12.2f, -0.5f);
        this.gunModel[21].rotateAngleZ = -0.15707964f;
        this.defaultStockModel = new ModelRendererTurbo[2];
        this.defaultStockModel[0] = new ModelRendererTurbo(this, 49, 1, this.textureX, this.textureY);
        this.defaultStockModel[1] = new ModelRendererTurbo(this, 41, 25, this.textureX, this.textureY);
        this.defaultStockModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 18, 4, 4, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -12.0f, 0.0f, 0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.defaultStockModel[0].setRotationPoint(-22.0f, -1.0f, -2.0f);
        this.defaultStockModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 18, 4, 4, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.5f, -0.5f, -1.0f, 0.5f, -0.5f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.1f, -0.5f, -0.5f, 0.1f, -0.5f, -0.5f, 0.0f, 0.0f, 0.0f);
        this.defaultStockModel[1].setRotationPoint(-22.0f, -5.0f, -2.0f);
        this.ammoModel = new ModelRendererTurbo[1];
        this.ammoModel[0] = new ModelRendererTurbo(this, 41, 49, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(8.0f, -7.0f, -1.0f, 6, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.pumpModel = new ModelRendererTurbo[4];
        this.pumpModel[0] = new ModelRendererTurbo(this, 65, 41, this.textureX, this.textureY);
        this.pumpModel[1] = new ModelRendererTurbo(this, 1, 49, this.textureX, this.textureY);
        this.pumpModel[2] = new ModelRendererTurbo(this, 105, 33, this.textureX, this.textureY);
        this.pumpModel[3] = new ModelRendererTurbo(this, 65, 49, this.textureX, this.textureY);
        this.pumpModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 13, 1, 4, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.pumpModel[0].setRotationPoint(29.0f, -7.0f, -2.0f);
        this.pumpModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 13, 1, 4, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f);
        this.pumpModel[1].setRotationPoint(29.0f, -6.0f, -2.0f);
        this.pumpModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 5, 2, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.pumpModel[2].setRotationPoint(10.0f, -10.5f, -2.0f);
        this.pumpModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 13, 1, 4, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.pumpModel[3].setRotationPoint(29.0f, -8.5f, -2.0f);
        this.translateAll(0.0f, -7.5f, 0.0f);
        this.scopeAttachPoint = new Vector3f(0.75f, 0.875f, 0.0f);
        this.gripAttachPoint = new Vector3f(2.1875f, 0.53125f, 0.0f);
        this.hasFlash = false;
        this.hasArms = true;
        this.leftArmPos = new Vector3f(-0.15f, -0.35f, 0.0f);
        this.leftArmRot = new Vector3f(90.0f, 45.0f, 0.0f);
        this.leftArmReloadPos = new Vector3f(-0.15, -0.3499999940395355, 0.0);
        this.leftArmReloadRot = new Vector3f(90.0f, 45.0f, 0.0f);
        this.rightArmPos = new Vector3f(0.4f, -0.5f, 0.0f);
        this.rightArmRot = new Vector3f(0.0f, 0.0f, -90.0f);
        this.rightArmReloadPos = new Vector3f(0.4f, -0.5f, 0.0f);
        this.rightArmReloadRot = new Vector3f(0.0f, 0.0f, -90.0f);
        this.rightHandAmmo = false;
        this.leftHandAmmo = true;
        this.flipAll();
        this.gunSlideDistance = 0.5f;
        this.animationType = EnumAnimationType.SHOTGUN;
        this.pumpDelayAfterReload = 115;
        this.pumpDelay = 6;
        this.pumpTime = 9;
        this.gripIsOnPump = true;
        this.pumpHandleDistance = -0.25f;
        this.numBulletsInReloadAnimation = 7.0f;
        this.tiltGunTime = 0.159f;
        this.unloadClipTime = 0.0f;
        this.loadClipTime = 0.708f;
        this.untiltGunTime = 0.133f;
    }
}

