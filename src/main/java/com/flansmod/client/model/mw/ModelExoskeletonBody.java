/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.ModelCustomArmour;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelExoskeletonBody
extends ModelCustomArmour {
    int textureX = 512;
    int textureY = 512;

    public ModelExoskeletonBody() {
        this.bodyModel = new ModelRendererTurbo[33];
        this.bodyModel[0] = new ModelRendererTurbo((ModelBase)this, 145, 41, this.textureX, this.textureY);
        this.bodyModel[1] = new ModelRendererTurbo((ModelBase)this, 233, 1, this.textureX, this.textureY);
        this.bodyModel[2] = new ModelRendererTurbo((ModelBase)this, 249, 1, this.textureX, this.textureY);
        this.bodyModel[3] = new ModelRendererTurbo((ModelBase)this, 217, 49, this.textureX, this.textureY);
        this.bodyModel[4] = new ModelRendererTurbo((ModelBase)this, 457, 1, this.textureX, this.textureY);
        this.bodyModel[5] = new ModelRendererTurbo((ModelBase)this, 481, 1, this.textureX, this.textureY);
        this.bodyModel[6] = new ModelRendererTurbo((ModelBase)this, 345, 9, this.textureX, this.textureY);
        this.bodyModel[7] = new ModelRendererTurbo((ModelBase)this, 97, 17, this.textureX, this.textureY);
        this.bodyModel[8] = new ModelRendererTurbo((ModelBase)this, 289, 49, this.textureX, this.textureY);
        this.bodyModel[9] = new ModelRendererTurbo((ModelBase)this, 433, 49, this.textureX, this.textureY);
        this.bodyModel[10] = new ModelRendererTurbo((ModelBase)this, 497, 41, this.textureX, this.textureY);
        this.bodyModel[11] = new ModelRendererTurbo((ModelBase)this, 481, 49, this.textureX, this.textureY);
        this.bodyModel[12] = new ModelRendererTurbo((ModelBase)this, 289, 65, this.textureX, this.textureY);
        this.bodyModel[13] = new ModelRendererTurbo((ModelBase)this, 305, 65, this.textureX, this.textureY);
        this.bodyModel[14] = new ModelRendererTurbo((ModelBase)this, 321, 65, this.textureX, this.textureY);
        this.bodyModel[15] = new ModelRendererTurbo((ModelBase)this, 121, 17, this.textureX, this.textureY);
        this.bodyModel[16] = new ModelRendererTurbo((ModelBase)this, 369, 9, this.textureX, this.textureY);
        this.bodyModel[17] = new ModelRendererTurbo((ModelBase)this, 145, 17, this.textureX, this.textureY);
        this.bodyModel[18] = new ModelRendererTurbo((ModelBase)this, 233, 17, this.textureX, this.textureY);
        this.bodyModel[19] = new ModelRendererTurbo((ModelBase)this, 433, 65, this.textureX, this.textureY);
        this.bodyModel[20] = new ModelRendererTurbo((ModelBase)this, 249, 17, this.textureX, this.textureY);
        this.bodyModel[21] = new ModelRendererTurbo((ModelBase)this, 337, 73, this.textureX, this.textureY);
        this.bodyModel[22] = new ModelRendererTurbo((ModelBase)this, 1, 81, this.textureX, this.textureY);
        this.bodyModel[23] = new ModelRendererTurbo((ModelBase)this, 457, 17, this.textureX, this.textureY);
        this.bodyModel[24] = new ModelRendererTurbo((ModelBase)this, 345, 25, this.textureX, this.textureY);
        this.bodyModel[25] = new ModelRendererTurbo((ModelBase)this, 73, 81, this.textureX, this.textureY);
        this.bodyModel[26] = new ModelRendererTurbo((ModelBase)this, 121, 81, this.textureX, this.textureY);
        this.bodyModel[27] = new ModelRendererTurbo((ModelBase)this, 257, 73, this.textureX, this.textureY);
        this.bodyModel[28] = new ModelRendererTurbo((ModelBase)this, 73, 81, this.textureX, this.textureY);
        this.bodyModel[29] = new ModelRendererTurbo((ModelBase)this, 105, 81, this.textureX, this.textureY);
        this.bodyModel[30] = new ModelRendererTurbo((ModelBase)this, 433, 81, this.textureX, this.textureY);
        this.bodyModel[31] = new ModelRendererTurbo((ModelBase)this, 273, 121, this.textureX, this.textureY);
        this.bodyModel[32] = new ModelRendererTurbo((ModelBase)this, 193, 129, this.textureX, this.textureY);
        this.bodyModel[0].addBox(-16.0f, 6.0f, -10.0f, 32, 36, 2, 0.0f);
        this.bodyModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[1].addBox(-16.0f, 0.0f, -10.0f, 3, 6, 2, 0.0f);
        this.bodyModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[2].addBox(13.0f, 0.0f, -10.0f, 3, 6, 2, 0.0f);
        this.bodyModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[3].addBox(-16.0f, 20.0f, -12.0f, 32, 14, 2, 0.0f);
        this.bodyModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[4].addBox(-15.0f, 21.0f, -14.0f, 6, 12, 2, 0.0f);
        this.bodyModel[4].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[5].addBox(-7.0f, 21.0f, -14.0f, 6, 12, 2, 0.0f);
        this.bodyModel[5].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[6].addBox(1.0f, 21.0f, -14.0f, 6, 12, 2, 0.0f);
        this.bodyModel[6].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[7].addBox(9.0f, 21.0f, -14.0f, 6, 12, 2, 0.0f);
        this.bodyModel[7].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[8].addBox(-15.0f, 6.0f, -10.0f, 30, 10, 1, 0.0f);
        this.bodyModel[8].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.bodyModel[9].addShapeBox(-41.0f, -7.0f, 11.25f, 19, 4, 4, 0.0f, -6.0f, 0.0f, 9.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, -13.0f, -6.0f, 0.0f, 9.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, -13.0f);
        this.bodyModel[9].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[10].addBox(-5.0f, 1.0f, 9.0f, 3, 35, 1, 0.0f);
        this.bodyModel[10].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[11].addBox(3.0f, 1.0f, 9.0f, 3, 35, 1, 0.0f);
        this.bodyModel[11].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[12].addBox(2.0f, 2.0f, 9.0f, 2, 33, 3, 0.0f);
        this.bodyModel[12].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[13].addBox(-3.0f, 2.0f, 9.0f, 2, 33, 3, 0.0f);
        this.bodyModel[13].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[14].addBox(-1.0f, 1.0f, 8.0f, 3, 35, 2, 0.0f);
        this.bodyModel[14].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[15].addBox(-22.0f, -7.5f, 10.8f, 5, 5, 5, 0.0f);
        this.bodyModel[15].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[16].addBox(-1.0f, 16.0f, 11.0f, 3, 6, 2, 0.0f);
        this.bodyModel[16].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[17].addBox(-1.0f, 7.0f, 11.0f, 3, 6, 2, 0.0f);
        this.bodyModel[17].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[18].addBox(-1.0f, 25.0f, 11.0f, 3, 6, 2, 0.0f);
        this.bodyModel[18].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[19].addBox(-6.0f, -1.0f, 9.0f, 13, 8, 6, 0.0f);
        this.bodyModel[19].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[20].addBox(35.5f, -7.5f, -2.2f, 5, 5, 5, 0.0f);
        this.bodyModel[20].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[21].addShapeBox(22.0f, -7.0f, 11.25f, 19, 4, 4, 0.0f, 0.0f, 0.0f, 0.0f, -6.0f, 0.0f, 10.0f, -1.0f, 0.0f, -14.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -6.0f, 0.0f, 10.0f, -1.0f, 0.0f, -14.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[21].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[22].addBox(-16.0f, 6.0f, 7.0f, 32, 36, 2, 0.0f);
        this.bodyModel[22].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[23].addBox(-16.0f, 0.0f, 7.0f, 3, 6, 2, 0.0f);
        this.bodyModel[23].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[24].addBox(13.0f, 0.0f, 7.0f, 3, 6, 2, 0.0f);
        this.bodyModel[24].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[25].addBox(13.0f, -2.0f, -10.0f, 3, 2, 19, 0.0f);
        this.bodyModel[25].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[26].addBox(-16.0f, -2.0f, -10.0f, 3, 2, 19, 0.0f);
        this.bodyModel[26].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[27].addBox(18.0f, -4.0f, 11.0f, 4, 12, 4, 0.0f);
        this.bodyModel[27].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[28].addBox(-23.0f, -4.0f, 11.0f, 4, 12, 4, 0.0f);
        this.bodyModel[28].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[28].rotateAngleZ = -0.01745329f;
        this.bodyModel[29].addBox(7.0f, 4.0f, 11.0f, 11, 4, 4, 0.0f);
        this.bodyModel[29].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[30].addBox(-18.0f, 4.0f, 11.0f, 12, 4, 4, 0.0f);
        this.bodyModel[30].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[31].addBox(17.5f, -7.5f, 10.8f, 5, 5, 5, 0.0f);
        this.bodyModel[31].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[32].addBox(-40.5f, -7.5f, -2.2f, 5, 5, 5, 0.0f);
        this.bodyModel[32].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightArmModel = new ModelRendererTurbo[13];
        this.rightArmModel[0] = new ModelRendererTurbo((ModelBase)this, 457, 17, this.textureX, this.textureY);
        this.rightArmModel[1] = new ModelRendererTurbo((ModelBase)this, 505, 1, this.textureX, this.textureY);
        this.rightArmModel[2] = new ModelRendererTurbo((ModelBase)this, 97, 1, this.textureX, this.textureY);
        this.rightArmModel[3] = new ModelRendererTurbo((ModelBase)this, 409, 49, this.textureX, this.textureY);
        this.rightArmModel[4] = new ModelRendererTurbo((ModelBase)this, 489, 17, this.textureX, this.textureY);
        this.rightArmModel[5] = new ModelRendererTurbo((ModelBase)this, 201, 73, this.textureX, this.textureY);
        this.rightArmModel[6] = new ModelRendererTurbo((ModelBase)this, 113, 41, this.textureX, this.textureY);
        this.rightArmModel[7] = new ModelRendererTurbo((ModelBase)this, 137, 129, this.textureX, this.textureY);
        this.rightArmModel[8] = new ModelRendererTurbo((ModelBase)this, 1, 121, this.textureX, this.textureY);
        this.rightArmModel[9] = new ModelRendererTurbo((ModelBase)this, 481, 17, this.textureX, this.textureY);
        this.rightArmModel[10] = new ModelRendererTurbo((ModelBase)this, 153, 1, this.textureX, this.textureY);
        this.rightArmModel[11] = new ModelRendererTurbo((ModelBase)this, 137, 49, this.textureX, this.textureY);
        this.rightArmModel[12] = new ModelRendererTurbo((ModelBase)this, 25, 25, this.textureX, this.textureY);
        float armOffsetX = 20.0f;
        float armOffsetY = 60.0f;
        float armOffsetZ = -1.0f;
        this.rightArmModel[0].addBox(-33.0f + armOffsetX, -39.0f + armOffsetY, -6.0f + armOffsetZ, 1, 10, 14, 0.0f);
        this.rightArmModel[0].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.rightArmModel[1].addBox(-34.0f + armOffsetX, -36.0f + armOffsetY, -4.0f + armOffsetZ, 1, 10, 2, 0.0f);
        this.rightArmModel[1].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.rightArmModel[2].addBox(-33.0f + armOffsetX, -43.0f + armOffsetY, -4.0f + armOffsetZ, 1, 4, 2, 0.0f);
        this.rightArmModel[2].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.rightArmModel[3].addBox(-40.0f + armOffsetX, -74.0f + armOffsetY, -2.0f + armOffsetZ, 4, 32, 4, 0.0f);
        this.rightArmModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightArmModel[4].addShapeBox(-40.0f + armOffsetX, -42.0f + armOffsetY, -2.0f + armOffsetZ, 4, 16, 4, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -6.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, -6.0f, 0.0f, 0.0f);
        this.rightArmModel[4].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightArmModel[5].addBox(-33.5f + armOffsetX, -28.0f + armOffsetY, -8.0f + armOffsetZ, 16, 3, 18, 0.0f);
        this.rightArmModel[5].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.rightArmModel[6].addBox(-34.0f + armOffsetX, -67.0f + armOffsetY, -4.0f + armOffsetZ, 2, 20, 8, 0.0f);
        this.rightArmModel[6].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightArmModel[7].addBox(-33.0f + armOffsetX, -72.2f + armOffsetY, -8.0f + armOffsetZ, 17, 5, 18, 0.0f);
        this.rightArmModel[7].setRotationPoint(0.0f, 0.0f, -5.0f);
        this.rightArmModel[8].addBox(-36.0f + armOffsetX, -62.0f + armOffsetY, -3.0f + armOffsetZ, 2, 10, 6, 0.0f);
        this.rightArmModel[8].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightArmModel[9].addBox(-34.0f + armOffsetX, -36.0f + armOffsetY, 0.0f + armOffsetZ, 1, 10, 2, 0.0f);
        this.rightArmModel[9].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.rightArmModel[10].addBox(-33.0f + armOffsetX, -43.0f + armOffsetY, 0.0f + armOffsetZ, 1, 4, 2, 0.0f);
        this.rightArmModel[10].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.rightArmModel[11].addBox(-34.0f + armOffsetX, -36.0f + armOffsetY, 4.0f + armOffsetZ, 1, 10, 2, 0.0f);
        this.rightArmModel[11].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.rightArmModel[12].addBox(-33.0f + armOffsetX, -43.0f + armOffsetY, 4.0f + armOffsetZ, 1, 4, 2, 0.0f);
        this.rightArmModel[12].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.leftArmModel = new ModelRendererTurbo[13];
        this.leftArmModel[0] = new ModelRendererTurbo((ModelBase)this, 337, 49, this.textureX, this.textureY);
        this.leftArmModel[1] = new ModelRendererTurbo((ModelBase)this, 385, 73, this.textureX, this.textureY);
        this.leftArmModel[2] = new ModelRendererTurbo((ModelBase)this, 1, 41, this.textureX, this.textureY);
        this.leftArmModel[3] = new ModelRendererTurbo((ModelBase)this, 169, 81, this.textureX, this.textureY);
        this.leftArmModel[4] = new ModelRendererTurbo((ModelBase)this, 57, 137, this.textureX, this.textureY);
        this.leftArmModel[5] = new ModelRendererTurbo((ModelBase)this, 113, 137, this.textureX, this.textureY);
        this.leftArmModel[6] = new ModelRendererTurbo((ModelBase)this, 113, 41, this.textureX, this.textureY);
        this.leftArmModel[7] = new ModelRendererTurbo((ModelBase)this, 473, 65, this.textureX, this.textureY);
        this.leftArmModel[8] = new ModelRendererTurbo((ModelBase)this, 281, 73, this.textureX, this.textureY);
        this.leftArmModel[9] = new ModelRendererTurbo((ModelBase)this, 473, 137, this.textureX, this.textureY);
        this.leftArmModel[10] = new ModelRendererTurbo((ModelBase)this, 473, 73, this.textureX, this.textureY);
        this.leftArmModel[11] = new ModelRendererTurbo((ModelBase)this, 489, 89, this.textureX, this.textureY);
        this.leftArmModel[12] = new ModelRendererTurbo((ModelBase)this, 401, 113, this.textureX, this.textureY);
        this.leftArmModel[0].addBox(16.5f - armOffsetX, -28.0f + armOffsetY, -8.0f + armOffsetZ, 16, 3, 18, 0.0f);
        this.leftArmModel[0].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.leftArmModel[1].addBox(36.0f - armOffsetX, -74.0f + armOffsetY, -2.0f + armOffsetZ, 4, 32, 4, 0.0f);
        this.leftArmModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftArmModel[2].addShapeBox(36.0f - armOffsetX, -42.0f + armOffsetY, -2.0f + armOffsetZ, 4, 16, 4, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, -6.0f, 0.0f, 0.0f, -6.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f);
        this.leftArmModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftArmModel[3].addBox(32.0f - armOffsetX, -67.0f + armOffsetY, -4.0f + armOffsetZ, 2, 20, 8, 0.0f);
        this.leftArmModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftArmModel[4].addBox(16.0f - armOffsetX, -72.2f + armOffsetY, -8.0f + armOffsetZ, 17, 5, 18, 0.0f);
        this.leftArmModel[4].setRotationPoint(0.0f, 0.0f, -5.0f);
        this.leftArmModel[5].addBox(34.0f - armOffsetX, -62.0f + armOffsetY, -3.0f + armOffsetZ, 2, 10, 6, 0.0f);
        this.leftArmModel[5].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftArmModel[6].addBox(32.0f - armOffsetX, -43.0f + armOffsetY, 4.0f + armOffsetZ, 1, 4, 2, 0.0f);
        this.leftArmModel[6].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.leftArmModel[7].addBox(32.0f - armOffsetX, -43.0f + armOffsetY, 0.0f + armOffsetZ, 1, 4, 2, 0.0f);
        this.leftArmModel[7].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.leftArmModel[8].addBox(32.0f - armOffsetX, -43.0f + armOffsetY, -4.0f + armOffsetZ, 1, 4, 2, 0.0f);
        this.leftArmModel[8].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.leftArmModel[9].addBox(32.0f - armOffsetX, -39.0f + armOffsetY, -6.0f + armOffsetZ, 1, 10, 14, 0.0f);
        this.leftArmModel[9].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.leftArmModel[10].addBox(33.0f - armOffsetX, -36.0f + armOffsetY, 4.0f + armOffsetZ, 1, 10, 2, 0.0f);
        this.leftArmModel[10].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.leftArmModel[11].addBox(33.0f - armOffsetX, -36.0f + armOffsetY, 0.0f + armOffsetZ, 1, 10, 2, 0.0f);
        this.leftArmModel[11].setRotationPoint(0.0f, 0.0f, -1.0f);
        this.leftArmModel[12].addBox(33.0f - armOffsetX, -36.0f + armOffsetY, -4.0f + armOffsetZ, 1, 10, 2, 0.0f);
        this.leftArmModel[12].setRotationPoint(0.0f, 0.0f, -1.0f);
    }
}

