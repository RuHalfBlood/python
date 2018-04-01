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
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBagPipe
extends ModelGun {
    int textureX = 64;
    int textureY = 64;

    public ModelBagPipe() {
        this.ammoModel = new ModelRendererTurbo[25];
        this.ammoModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.ammoModel[1] = new ModelRendererTurbo(this, 17, 1, this.textureX, this.textureY);
        this.ammoModel[2] = new ModelRendererTurbo(this, 33, 1, this.textureX, this.textureY);
        this.ammoModel[3] = new ModelRendererTurbo(this, 33, 1, this.textureX, this.textureY);
        this.ammoModel[4] = new ModelRendererTurbo(this, 49, 1, this.textureX, this.textureY);
        this.ammoModel[5] = new ModelRendererTurbo(this, 1, 9, this.textureX, this.textureY);
        this.ammoModel[6] = new ModelRendererTurbo(this, 9, 9, this.textureX, this.textureY);
        this.ammoModel[7] = new ModelRendererTurbo(this, 25, 9, this.textureX, this.textureY);
        this.ammoModel[8] = new ModelRendererTurbo(this, 41, 17, this.textureX, this.textureY);
        this.ammoModel[9] = new ModelRendererTurbo(this, 57, 1, this.textureX, this.textureY);
        this.ammoModel[10] = new ModelRendererTurbo(this, 1, 9, this.textureX, this.textureY);
        this.ammoModel[11] = new ModelRendererTurbo(this, 25, 9, this.textureX, this.textureY);
        this.ammoModel[12] = new ModelRendererTurbo(this, 41, 9, this.textureX, this.textureY);
        this.ammoModel[13] = new ModelRendererTurbo(this, 1, 25, this.textureX, this.textureY);
        this.ammoModel[14] = new ModelRendererTurbo(this, 49, 9, this.textureX, this.textureY);
        this.ammoModel[15] = new ModelRendererTurbo(this, 57, 9, this.textureX, this.textureY);
        this.ammoModel[16] = new ModelRendererTurbo(this, 1, 17, this.textureX, this.textureY);
        this.ammoModel[17] = new ModelRendererTurbo(this, 17, 25, this.textureX, this.textureY);
        this.ammoModel[18] = new ModelRendererTurbo(this, 25, 25, this.textureX, this.textureY);
        this.ammoModel[19] = new ModelRendererTurbo(this, 41, 33, this.textureX, this.textureY);
        this.ammoModel[20] = new ModelRendererTurbo(this, 9, 17, this.textureX, this.textureY);
        this.ammoModel[21] = new ModelRendererTurbo(this, 1, 41, this.textureX, this.textureY);
        this.ammoModel[22] = new ModelRendererTurbo(this, 17, 41, this.textureX, this.textureY);
        this.ammoModel[23] = new ModelRendererTurbo(this, 33, 41, this.textureX, this.textureY);
        this.ammoModel[24] = new ModelRendererTurbo(this, 49, 41, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(0.0f, 5.0f, 0.0f, 3, 2, 4, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[0].rotateAngleX = 0.7853982f;
        this.ammoModel[0].rotateAngleY = 0.2617994f;
        this.ammoModel[1].addShapeBox(0.0f, -2.0f, 2.0f, 3, 2, 3, 0.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f);
        this.ammoModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[1].rotateAngleX = 0.7853982f;
        this.ammoModel[1].rotateAngleY = 0.2617994f;
        this.ammoModel[2].addShapeBox(-3.0f, 14.0f, 0.0f, 3, 1, 5, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, -1.0f);
        this.ammoModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[2].rotateAngleX = 0.7853982f;
        this.ammoModel[2].rotateAngleY = 0.2617994f;
        this.ammoModel[3].addShapeBox(3.0f, -4.0f, 1.5f, 1, 3, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f, -0.1f, 0.0f, -0.1f);
        this.ammoModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[3].rotateAngleX = -0.55850536f;
        this.ammoModel[3].rotateAngleY = 0.2617994f;
        this.ammoModel[3].rotateAngleZ = 1.1170107f;
        this.ammoModel[4].addShapeBox(3.0f, -5.0f, 1.5f, 1, 1, 1, 0.0f, 0.2f, 0.0f, 0.2f, 0.2f, 0.0f, 0.2f, 0.2f, 0.0f, 0.2f, 0.2f, 0.0f, 0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[4].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[4].rotateAngleX = -0.55850536f;
        this.ammoModel[4].rotateAngleY = 0.2617994f;
        this.ammoModel[4].rotateAngleZ = 1.1170107f;
        this.ammoModel[5].addShapeBox(0.5f, 2.0f, 4.5f, 1, 1, 6, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[5].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[5].rotateAngleX = 0.7853982f;
        this.ammoModel[5].rotateAngleY = 0.2617994f;
        this.ammoModel[5].rotateAngleZ = 0.20943952f;
        this.ammoModel[6].addShapeBox(0.0f, 5.0f, 3.5f, 1, 1, 12, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[6].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[6].rotateAngleX = 0.7853982f;
        this.ammoModel[6].rotateAngleY = 0.2617994f;
        this.ammoModel[6].rotateAngleZ = 0.34906584f;
        this.ammoModel[7].addShapeBox(-0.25f, 8.0f, 3.5f, 1, 1, 8, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[7].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[7].rotateAngleX = 0.7330383f;
        this.ammoModel[7].rotateAngleY = 0.33161256f;
        this.ammoModel[7].rotateAngleZ = 0.01745329f;
        this.ammoModel[8].addShapeBox(0.0f, 10.0f, 3.5f, 1, 1, 8, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[8].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[8].rotateAngleX = 0.62831855f;
        this.ammoModel[8].rotateAngleY = 0.33161256f;
        this.ammoModel[8].rotateAngleZ = -0.12217305f;
        this.ammoModel[9].addShapeBox(0.5f, 2.0f, 10.5f, 1, 1, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f);
        this.ammoModel[9].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[9].rotateAngleX = 0.7853982f;
        this.ammoModel[9].rotateAngleY = 0.2617994f;
        this.ammoModel[9].rotateAngleZ = 0.20943952f;
        this.ammoModel[10].addShapeBox(0.0f, 5.0f, 15.5f, 1, 1, 1, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[10].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[10].rotateAngleX = 0.7853982f;
        this.ammoModel[10].rotateAngleY = 0.2617994f;
        this.ammoModel[10].rotateAngleZ = 0.34906584f;
        this.ammoModel[11].addShapeBox(-0.25f, 8.0f, 11.5f, 1, 1, 1, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[11].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[11].rotateAngleX = 0.7330383f;
        this.ammoModel[11].rotateAngleY = 0.33161256f;
        this.ammoModel[11].rotateAngleZ = 0.01745329f;
        this.ammoModel[12].addShapeBox(0.0f, 10.0f, 11.5f, 1, 1, 1, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[12].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[12].rotateAngleX = 0.62831855f;
        this.ammoModel[12].rotateAngleY = 0.33161256f;
        this.ammoModel[12].rotateAngleZ = -0.12217305f;
        this.ammoModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 3, 5, 4, 0.0f, -0.5f, 0.0f, -2.5f, -0.5f, 0.0f, -2.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[13].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[13].rotateAngleX = 0.7853982f;
        this.ammoModel[13].rotateAngleY = 0.2617994f;
        this.ammoModel[14].addShapeBox(-0.25f, 8.0f, 11.0f, 1, 1, 1, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[14].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[14].rotateAngleX = 0.7330383f;
        this.ammoModel[14].rotateAngleY = 0.33161256f;
        this.ammoModel[14].rotateAngleZ = 0.01745329f;
        this.ammoModel[15].addShapeBox(0.0f, 5.0f, 15.0f, 1, 1, 1, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[15].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[15].rotateAngleX = 0.7853982f;
        this.ammoModel[15].rotateAngleY = 0.2617994f;
        this.ammoModel[15].rotateAngleZ = 0.34906584f;
        this.ammoModel[16].addShapeBox(0.0f, 10.0f, 11.0f, 1, 1, 1, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[16].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[16].rotateAngleX = 0.62831855f;
        this.ammoModel[16].rotateAngleY = 0.33161256f;
        this.ammoModel[16].rotateAngleZ = -0.12217305f;
        this.ammoModel[17].addShapeBox(-0.2f, 5.0f, 9.2f, 1, 7, 1, 0.0f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, -0.2f);
        this.ammoModel[17].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[17].rotateAngleX = 0.7330383f;
        this.ammoModel[17].rotateAngleY = 0.33161256f;
        this.ammoModel[17].rotateAngleZ = 0.03490659f;
        this.ammoModel[18].addShapeBox(0.0f, 7.0f, 0.0f, 3, 4, 4, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
        this.ammoModel[18].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[18].rotateAngleX = 0.7853982f;
        this.ammoModel[18].rotateAngleY = 0.2617994f;
        this.ammoModel[19].addShapeBox(0.0f, 11.0f, 0.0f, 3, 3, 4, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f);
        this.ammoModel[19].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[19].rotateAngleX = 0.7853982f;
        this.ammoModel[19].rotateAngleY = 0.2617994f;
        this.ammoModel[20].addShapeBox(0.5f, 2.0f, 10.0f, 1, 1, 1, 0.0f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, -0.3f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.ammoModel[20].setRotationPoint(1.0f, 0.0f, 0.0f);
        this.ammoModel[20].rotateAngleX = 0.7853982f;
        this.ammoModel[20].rotateAngleY = 0.2617994f;
        this.ammoModel[20].rotateAngleZ = 0.20943952f;
        this.ammoModel[21].addShapeBox(0.0f, 0.0f, 4.0f, 3, 5, 1, 0.0f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, -0.5f, -0.5f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f);
        this.ammoModel[21].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[21].rotateAngleX = 0.7853982f;
        this.ammoModel[21].rotateAngleY = 0.2617994f;
        this.ammoModel[22].addShapeBox(0.0f, 5.0f, 4.0f, 3, 2, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f);
        this.ammoModel[22].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[22].rotateAngleX = 0.7853982f;
        this.ammoModel[22].rotateAngleY = 0.2617994f;
        this.ammoModel[23].addShapeBox(0.0f, 7.0f, 4.0f, 3, 4, 1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f);
        this.ammoModel[23].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[23].rotateAngleX = 0.7853982f;
        this.ammoModel[23].rotateAngleY = 0.2617994f;
        this.ammoModel[24].addShapeBox(0.0f, 11.0f, 4.0f, 3, 3, 1, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.5f, 0.0f, 0.0f, 0.54f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f);
        this.ammoModel[24].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.ammoModel[24].rotateAngleX = 0.7853982f;
        this.ammoModel[24].rotateAngleY = 0.2617994f;
        this.translateAll(0.0f, 0.0f, 0.0f);
        this.animationType = EnumAnimationType.BOTTOM_CLIP;
        this.flipAll();
    }
}

