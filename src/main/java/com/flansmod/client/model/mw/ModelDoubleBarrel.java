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

public class ModelDoubleBarrel
extends ModelGun {
    int textureX = 128;
    int textureY = 128;

    public ModelDoubleBarrel() {
        this.gunModel = new ModelRendererTurbo[23];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 25, 1, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 97, 1, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 1, 17, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 17, 1, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 81, 17, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 105, 17, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 1, 25, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 41, 9, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 97, 17, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 17, 25, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 81, 25, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 97, 25, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 113, 25, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 105, 33, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 1, 81, this.textureX, this.textureY);
        this.gunModel[17] = new ModelRendererTurbo(this, 33, 81, this.textureX, this.textureY);
        this.gunModel[18] = new ModelRendererTurbo(this, 65, 81, this.textureX, this.textureY);
        this.gunModel[19] = new ModelRendererTurbo(this, 105, 41, this.textureX, this.textureY);
        this.gunModel[20] = new ModelRendererTurbo(this, 41, 1, this.textureX, this.textureY);
        this.gunModel[21] = new ModelRendererTurbo(this, 121, 1, this.textureX, this.textureY);
        this.gunModel[22] = new ModelRendererTurbo(this, 121, 9, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 4, 8, 5, 0.0f, 0.0f, 0.0f, 0.0f, -0.6f, -2.0f, 0.0f, -0.6f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, -1.0f);
        this.gunModel[0].setRotationPoint(3.0f, -10.0f, -2.5f);
        this.gunModel[0].rotateAngleZ = -1.0471976f;
        this.gunModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 4, 3, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[1].setRotationPoint(3.0f, -10.0f, -2.5f);
        this.gunModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 6, 3, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[2].setRotationPoint(7.0f, -10.0f, -2.5f);
        this.gunModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 10, 1, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[3].setRotationPoint(3.0f, -7.0f, -2.5f);
        this.gunModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 1, 0.0f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.1f, -0.1f, 0.0f, 0.1f, -0.1f, 0.0f, 0.1f, -0.1f, 0.0f, 0.1f, -0.1f);
        this.gunModel[4].setRotationPoint(1.0f, -3.5f, -0.5f);
        this.gunModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 1, 3, 1, 0.0f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f);
        this.gunModel[5].setRotationPoint(2.0f, -7.0f, -0.5f);
        this.gunModel[5].rotateAngleZ = 0.2268928f;
        this.gunModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[6].setRotationPoint(13.0f, -9.0f, -2.5f);
        this.gunModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f);
        this.gunModel[7].setRotationPoint(13.0f, -7.0f, -2.5f);
        this.gunModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[8].setRotationPoint(13.0f, -8.0f, -2.5f);
        this.gunModel[9].addShapeBox(0.0f, -0.5f, 0.0f, 2, 1, 3, 0.0f, -0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, -0.5f);
        this.gunModel[9].setRotationPoint(11.5f, -9.5f, 0.0f);
        this.gunModel[10].addShapeBox(0.0f, -1.5f, 0.0f, 2, 1, 3, 0.0f, -1.0f, -0.2f, -1.2f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, -1.0f, -0.2f, -1.2f, -0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, -0.5f);
        this.gunModel[10].setRotationPoint(11.5f, -9.5f, 0.0f);
        this.gunModel[11].addShapeBox(0.0f, 0.5f, 0.0f, 2, 1, 3, 0.0f, -0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, -0.5f, -1.0f, -0.2f, -1.2f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, -1.0f, -0.2f, -1.2f);
        this.gunModel[11].setRotationPoint(11.5f, -9.5f, 0.0f);
        this.gunModel[12].addShapeBox(0.0f, -0.5f, 0.0f, 2, 1, 3, 0.0f, -0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, -0.5f);
        this.gunModel[12].setRotationPoint(11.5f, -9.5f, -3.0f);
        this.gunModel[13].addShapeBox(0.0f, -1.5f, 0.0f, 2, 1, 3, 0.0f, -1.0f, -0.2f, -1.2f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, -1.0f, -0.2f, -1.2f, -0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, -0.5f);
        this.gunModel[13].setRotationPoint(11.5f, -9.5f, -3.0f);
        this.gunModel[14].addShapeBox(0.0f, 0.5f, 0.0f, 2, 1, 3, 0.0f, -0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, -0.5f, -1.0f, -0.2f, -1.2f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, -1.0f, -0.2f, -1.2f);
        this.gunModel[14].setRotationPoint(11.5f, -9.5f, -3.0f);
        this.gunModel[15].addShapeBox(0.0f, 0.0f, 0.0f, 6, 1, 5, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[15].setRotationPoint(7.0f, -11.0f, -2.5f);
        this.gunModel[16].addShapeBox(0.0f, 0.0f, 0.0f, 8, 1, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 0.0f, -1.0f);
        this.gunModel[16].setRotationPoint(18.0f, -7.0f, -2.5f);
        this.gunModel[17].addShapeBox(0.0f, 0.0f, 0.0f, 8, 1, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[17].setRotationPoint(18.0f, -8.0f, -2.5f);
        this.gunModel[18].addShapeBox(0.0f, 0.0f, 0.0f, 8, 1, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[18].setRotationPoint(18.0f, -9.0f, -2.5f);
        this.gunModel[19].addShapeBox(0.0f, 0.0f, 0.0f, 4, 1, 5, 0.0f, -1.5f, -0.2f, -1.5f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, -1.5f, -0.2f, -1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[19].setRotationPoint(3.0f, -11.0f, -2.5f);
        this.gunModel[20].addShapeBox(0.0f, -0.4f, 0.0f, 1, 2, 1, 0.0f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f);
        this.gunModel[20].setRotationPoint(0.5f, -9.0f, -0.5f);
        this.gunModel[20].rotateAngleZ = 1.2042772f;
        this.gunModel[21].addShapeBox(0.0f, 0.0f, 0.0f, 1, 4, 1, 0.0f, -2.0f, 0.0f, -0.1f, 2.0f, 0.0f, -0.1f, 2.0f, 0.0f, -0.1f, -2.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f);
        this.gunModel[21].setRotationPoint(5.0f, -6.5f, -0.5f);
        this.gunModel[22].addShapeBox(0.0f, 0.0f, 0.0f, 1, 4, 1, 0.0f, 2.0f, 0.0f, -0.1f, -2.0f, 0.0f, -0.1f, -2.0f, 0.0f, -0.1f, 2.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f);
        this.gunModel[22].setRotationPoint(1.0f, -6.5f, -0.5f);
        this.defaultStockModel = new ModelRendererTurbo[4];
        this.defaultStockModel[0] = new ModelRendererTurbo(this, 49, 1, this.textureX, this.textureY);
        this.defaultStockModel[1] = new ModelRendererTurbo(this, 33, 17, this.textureX, this.textureY);
        this.defaultStockModel[2] = new ModelRendererTurbo(this, 113, 49, this.textureX, this.textureY);
        this.defaultStockModel[3] = new ModelRendererTurbo(this, 113, 65, this.textureX, this.textureY);
        this.defaultStockModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 18, 4, 4, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, -12.0f, 0.0f, 0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f);
        this.defaultStockModel[0].setRotationPoint(-20.0f, -2.0f, -2.0f);
        this.defaultStockModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 18, 4, 4, 0.0f, 1.0f, 0.0f, -0.5f, -1.0f, 0.5f, -0.5f, -1.0f, 0.5f, -0.5f, 1.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.1f, -0.5f, -0.5f, 0.1f, -0.5f, -0.5f, 0.0f, 0.0f, 0.0f);
        this.defaultStockModel[1].setRotationPoint(-20.0f, -6.0f, -2.0f);
        this.defaultStockModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 1, 6, 3, 0.0f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, -2.0f, -0.1f, 0.0f, -2.0f, -0.1f, 0.0f, 0.0f, -0.1f);
        this.defaultStockModel[2].setRotationPoint(-1.5f, -4.5f, -1.5f);
        this.defaultStockModel[2].rotateAngleZ = -0.5235988f;
        this.defaultStockModel[3].addShapeBox(-2.0f, 0.0f, 0.0f, 2, 6, 3, 0.0f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, -1.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, -1.0f, -0.1f);
        this.defaultStockModel[3].setRotationPoint(-1.5f, -4.5f, -1.5f);
        this.defaultStockModel[3].rotateAngleZ = -0.5235988f;
        this.ammoModel = new ModelRendererTurbo[2];
        this.ammoModel[0] = new ModelRendererTurbo(this, 1, 54, this.textureX, this.textureY);
        this.ammoModel[1] = new ModelRendererTurbo(this, 1, 54, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(13.45f, -10.0f, 1.0f, 4, 1, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[1].addShapeBox(13.45f, -10.0f, -2.0f, 4, 1, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.breakActionModel = new ModelRendererTurbo[7];
        this.breakActionModel[0] = new ModelRendererTurbo(this, 1, 33, this.textureX, this.textureY);
        this.breakActionModel[1] = new ModelRendererTurbo(this, 1, 41, this.textureX, this.textureY);
        this.breakActionModel[2] = new ModelRendererTurbo(this, 1, 49, this.textureX, this.textureY);
        this.breakActionModel[3] = new ModelRendererTurbo(this, 1, 57, this.textureX, this.textureY);
        this.breakActionModel[4] = new ModelRendererTurbo(this, 1, 65, this.textureX, this.textureY);
        this.breakActionModel[5] = new ModelRendererTurbo(this, 1, 73, this.textureX, this.textureY);
        this.breakActionModel[6] = new ModelRendererTurbo(this, 97, 1, this.textureX, this.textureY);
        this.breakActionModel[0].addShapeBox(0.0f, -0.5f, -3.0f, 50, 1, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.breakActionModel[0].setRotationPoint(13.5f, -9.5f, 0.0f);
        this.breakActionModel[1].addShapeBox(0.0f, 0.5f, -3.0f, 50, 1, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f);
        this.breakActionModel[1].setRotationPoint(13.5f, -9.5f, 0.0f);
        this.breakActionModel[2].addShapeBox(0.0f, -1.5f, -3.0f, 50, 1, 3, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.breakActionModel[2].setRotationPoint(13.5f, -9.5f, 0.0f);
        this.breakActionModel[3].addShapeBox(0.0f, -0.5f, 0.0f, 50, 1, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.breakActionModel[3].setRotationPoint(13.5f, -9.5f, 0.0f);
        this.breakActionModel[4].addShapeBox(0.0f, 0.5f, 0.0f, 50, 1, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f);
        this.breakActionModel[4].setRotationPoint(13.5f, -9.5f, 0.0f);
        this.breakActionModel[5].addShapeBox(0.0f, -1.5f, 0.0f, 50, 1, 3, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.breakActionModel[5].setRotationPoint(13.5f, -9.5f, 0.0f);
        this.breakActionModel[6].addShapeBox(0.0f, -0.4f, 0.0f, 1, 2, 1, 0.0f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f);
        this.breakActionModel[6].setRotationPoint(61.5f, -11.5f, -0.5f);
        this.translateAll(0.0f, -7.0f, 0.0f);
        this.breakAngle = 60.0f;
        this.barrelBreakPoint = new Vector3f(1.4f, 0.0f, 0.0f);
        this.gunSlideDistance = 0.25f;
        this.animationType = EnumAnimationType.BREAK_ACTION;
        this.flipAll();
    }
}

