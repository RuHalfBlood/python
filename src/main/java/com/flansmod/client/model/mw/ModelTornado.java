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
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTornado
extends ModelPlane {
    int textureX = 512;
    int textureY = 512;

    public ModelTornado() {
        this.noseModel = new ModelRendererTurbo[2];
        this.noseModel[0] = new ModelRendererTurbo(this, 0, 45, this.textureX, this.textureY);
        this.noseModel[1] = new ModelRendererTurbo(this, 0, 85, this.textureX, this.textureY);
        this.noseModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 35, 18, 18, 0.0f, 0.0f, -8.5f, -8.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.5f, -8.5f, 0.0f, -8.5f, -8.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -8.5f, -8.5f);
        this.noseModel[0].setRotationPoint(-81.0f, -28.0f, -9.0f);
        this.noseModel[1].addBox(0.0f, 0.0f, 0.0f, 10, 1, 1, 0.0f);
        this.noseModel[1].setRotationPoint(-91.0f, -19.5f, -0.5f);
        this.bodyModel = new ModelRendererTurbo[41];
        this.bodyModel[0] = new ModelRendererTurbo(this, 0, 90, this.textureX, this.textureY);
        this.bodyModel[1] = new ModelRendererTurbo(this, 0, 135, this.textureX, this.textureY);
        this.bodyModel[2] = new ModelRendererTurbo(this, 0, 165, this.textureX, this.textureY);
        this.bodyModel[3] = new ModelRendererTurbo(this, 0, 180, this.textureX, this.textureY);
        this.bodyModel[4] = new ModelRendererTurbo(this, 0, 205, this.textureX, this.textureY);
        this.bodyModel[5] = new ModelRendererTurbo(this, 0, 205, this.textureX, this.textureY);
        this.bodyModel[6] = new ModelRendererTurbo(this, 0, 220, this.textureX, this.textureY);
        this.bodyModel[7] = new ModelRendererTurbo(this, 0, 250, this.textureX, this.textureY);
        this.bodyModel[8] = new ModelRendererTurbo(this, 0, 220, this.textureX, this.textureY);
        this.bodyModel[9] = new ModelRendererTurbo(this, 0, 275, this.textureX, this.textureY);
        this.bodyModel[10] = new ModelRendererTurbo(this, 0, 300, this.textureX, this.textureY);
        this.bodyModel[11] = new ModelRendererTurbo(this, 0, 335, this.textureX, this.textureY);
        this.bodyModel[12] = new ModelRendererTurbo(this, 0, 350, this.textureX, this.textureY);
        this.bodyModel[13] = new ModelRendererTurbo(this, 20, 350, this.textureX, this.textureY);
        this.bodyModel[14] = new ModelRendererTurbo(this, 20, 350, this.textureX, this.textureY);
        this.bodyModel[15] = new ModelRendererTurbo(this, 35, 350, this.textureX, this.textureY);
        this.bodyModel[16] = new ModelRendererTurbo(this, 50, 350, this.textureX, this.textureY);
        this.bodyModel[17] = new ModelRendererTurbo(this, 0, 365, this.textureX, this.textureY);
        this.bodyModel[18] = new ModelRendererTurbo(this, 0, 380, this.textureX, this.textureY);
        this.bodyModel[19] = new ModelRendererTurbo(this, 0, 410, this.textureX, this.textureY);
        this.bodyModel[20] = new ModelRendererTurbo(this, 30, 410, this.textureX, this.textureY);
        this.bodyModel[21] = new ModelRendererTurbo(this, 30, 410, this.textureX, this.textureY);
        this.bodyModel[22] = new ModelRendererTurbo(this, 0, 425, this.textureX, this.textureY);
        this.bodyModel[23] = new ModelRendererTurbo(this, 0, 450, this.textureX, this.textureY);
        this.bodyModel[24] = new ModelRendererTurbo(this, 120, 0, this.textureX, this.textureY);
        this.bodyModel[25] = new ModelRendererTurbo(this, 120, 30, this.textureX, this.textureY);
        this.bodyModel[26] = new ModelRendererTurbo(this, 120, 50, this.textureX, this.textureY);
        this.bodyModel[27] = new ModelRendererTurbo(this, 120, 65, this.textureX, this.textureY);
        this.bodyModel[28] = new ModelRendererTurbo(this, 120, 50, this.textureX, this.textureY);
        this.bodyModel[29] = new ModelRendererTurbo(this, 120, 30, this.textureX, this.textureY);
        this.bodyModel[30] = new ModelRendererTurbo(this, 120, 50, this.textureX, this.textureY);
        this.bodyModel[31] = new ModelRendererTurbo(this, 120, 50, this.textureX, this.textureY);
        this.bodyModel[32] = new ModelRendererTurbo(this, 120, 65, this.textureX, this.textureY);
        this.bodyModel[33] = new ModelRendererTurbo(this, 120, 160, this.textureX, this.textureY);
        this.bodyModel[34] = new ModelRendererTurbo(this, 120, 210, this.textureX, this.textureY);
        this.bodyModel[35] = new ModelRendererTurbo(this, 120, 270, this.textureX, this.textureY);
        this.bodyModel[36] = new ModelRendererTurbo(this, 120, 300, this.textureX, this.textureY);
        this.bodyModel[37] = new ModelRendererTurbo(this, 120, 120, this.textureX, this.textureY);
        this.bodyModel[38] = new ModelRendererTurbo(this, 120, 140, this.textureX, this.textureY);
        this.bodyModel[39] = new ModelRendererTurbo(this, 120, 120, this.textureX, this.textureY);
        this.bodyModel[40] = new ModelRendererTurbo(this, 120, 140, this.textureX, this.textureY);
        this.bodyModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 24, 20, 22, 0.0f, 0.0f, -2.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f);
        this.bodyModel[0].setRotationPoint(-46.0f, -30.0f, -11.0f);
        this.bodyModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 12, 10, 12, 0.0f, 0.0f, -8.0f, -3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -3.0f, 0.0f, -8.0f, -3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -3.0f);
        this.bodyModel[1].setRotationPoint(-36.0f, -38.0f, -6.0f);
        this.bodyModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 8, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[2].setRotationPoint(-24.0f, -39.0f, -4.0f);
        this.bodyModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 2, 10, 12, 0.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[3].setRotationPoint(-24.0f, -38.0f, -6.0f);
        this.bodyModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 2, 10, 1, 0.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[4].setRotationPoint(-24.0f, -38.0f, 6.0f);
        this.bodyModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 2, 10, 1, 0.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, -3.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, 3.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[5].setRotationPoint(-24.0f, -38.0f, -7.0f);
        this.bodyModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 34, 20, 4, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        this.bodyModel[6].setRotationPoint(-22.0f, -30.0f, -11.0f);
        this.bodyModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 19, 10, 14, 0.0f, 0.0f, -2.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, -2.0f, -4.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f);
        this.bodyModel[7].setRotationPoint(-22.0f, -40.0f, -7.0f);
        this.bodyModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 34, 20, 4, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[8].setRotationPoint(-22.0f, -30.0f, 7.0f);
        this.bodyModel[9].addBox(0.0f, 0.0f, 0.0f, 34, 4, 14, 0.0f);
        this.bodyModel[9].setRotationPoint(-22.0f, -14.0f, -7.0f);
        this.bodyModel[10].addBox(-4.0f, 0.0f, 0.0f, 4, 20, 14, 0.0f);
        this.bodyModel[10].setRotationPoint(-21.0f, -30.0f, -7.0f);
        this.bodyModel[10].rotateAngleZ = 0.5235988f;
        this.bodyModel[11].addBox(0.0f, -3.0f, -3.0f, 0, 6, 6, 0.0f);
        this.bodyModel[11].setRotationPoint(-15.0f, -34.0f, 0.0f);
        this.bodyModel[12].addBox(0.0f, 2.0f, 0.0f, 2, 6, 6, 0.0f);
        this.bodyModel[12].setRotationPoint(-21.0f, -30.0f, -3.0f);
        this.bodyModel[12].rotateAngleZ = 0.5235988f;
        this.bodyModel[13].addBox(0.0f, 8.0f, 5.0f, 1, 4, 4, 0.0f);
        this.bodyModel[13].setRotationPoint(-21.0f, -30.0f, -3.0f);
        this.bodyModel[13].rotateAngleZ = 0.5235988f;
        this.bodyModel[14].addBox(0.0f, 8.0f, -3.0f, 1, 4, 4, 0.0f);
        this.bodyModel[14].setRotationPoint(-21.0f, -30.0f, -3.0f);
        this.bodyModel[14].rotateAngleZ = 0.5235988f;
        this.bodyModel[15].addBox(0.0f, 0.0f, 0.0f, 4, 6, 3, 0.0f);
        this.bodyModel[15].setRotationPoint(-15.0f, -21.0f, -1.5f);
        this.bodyModel[16].addBox(0.0f, 0.0f, 0.0f, 3, 5, 1, 0.0f);
        this.bodyModel[16].setRotationPoint(-14.5f, -26.0f, -0.5f);
        this.bodyModel[17].addBox(0.0f, 0.0f, 0.0f, 10, 2, 10, 0.0f);
        this.bodyModel[17].setRotationPoint(-4.0f, -16.0f, -5.0f);
        this.bodyModel[18].addBox(0.0f, 0.0f, 0.0f, 2, 18, 10, 0.0f);
        this.bodyModel[18].setRotationPoint(6.0f, -32.0f, -5.0f);
        this.bodyModel[19].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 8, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[19].setRotationPoint(-5.0f, -41.0f, -4.0f);
        this.bodyModel[20].addShapeBox(0.0f, 0.0f, 0.0f, 2, 10, 1, 0.0f, 0.0f, 0.0f, 4.0f, 0.0f, 0.0f, 4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[20].setRotationPoint(-5.0f, -40.0f, 7.0f);
        this.bodyModel[21].addShapeBox(0.0f, 0.0f, 0.0f, 2, 10, 1, 0.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 4.0f, 0.0f, 0.0f, 4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[21].setRotationPoint(-5.0f, -40.0f, -8.0f);
        this.bodyModel[22].addShapeBox(0.0f, 0.0f, 0.0f, 15, 9, 14, 0.0f, 0.0f, 1.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 1.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[22].setRotationPoint(-3.0f, -39.0f, -7.0f);
        this.bodyModel[23].addShapeBox(0.0f, 0.0f, 0.0f, 25, 14, 46, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -5.0f, 0.0f, 0.0f, -5.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -5.0f, 0.0f, 0.0f, -5.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[23].setRotationPoint(24.0f, -30.0f, -23.0f);
        this.bodyModel[24].addShapeBox(0.0f, 0.0f, 0.0f, 12, 10, 15, 0.0f, 0.0f, 0.0f, -4.5f, 0.0f, -0.5f, -4.5f, 0.0f, -0.5f, -4.5f, 0.0f, 0.0f, -4.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[24].setRotationPoint(12.0f, -40.0f, -7.5f);
        this.bodyModel[25].addBox(0.0f, 0.0f, 0.0f, 22, 4, 12, 0.0f);
        this.bodyModel[25].setRotationPoint(2.0f, -30.0f, -23.0f);
        this.bodyModel[26].addShapeBox(0.0f, 0.0f, 0.0f, 22, 8, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -12.0f, 0.0f, 0.0f);
        this.bodyModel[26].setRotationPoint(2.0f, -26.0f, -13.0f);
        this.bodyModel[27].addBox(0.0f, 0.0f, 0.0f, 10, 2, 12, 0.0f);
        this.bodyModel[27].setRotationPoint(14.0f, -18.0f, -23.0f);
        this.bodyModel[28].addShapeBox(0.0f, 0.0f, 0.0f, 22, 8, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -12.0f, 0.0f, 0.0f);
        this.bodyModel[28].setRotationPoint(2.0f, -26.0f, -23.0f);
        this.bodyModel[29].addBox(0.0f, 0.0f, 0.0f, 22, 4, 12, 0.0f);
        this.bodyModel[29].setRotationPoint(2.0f, -30.0f, 11.0f);
        this.bodyModel[30].addShapeBox(0.0f, 0.0f, 0.0f, 22, 8, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -12.0f, 0.0f, 0.0f);
        this.bodyModel[30].setRotationPoint(2.0f, -26.0f, 11.0f);
        this.bodyModel[31].addShapeBox(0.0f, 0.0f, 0.0f, 22, 8, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -12.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -12.0f, 0.0f, 0.0f);
        this.bodyModel[31].setRotationPoint(2.0f, -26.0f, 21.0f);
        this.bodyModel[32].addBox(0.0f, 0.0f, 0.0f, 10, 2, 12, 0.0f);
        this.bodyModel[32].setRotationPoint(14.0f, -18.0f, 11.0f);
        this.bodyModel[33].addBox(0.0f, 0.0f, 0.0f, 12, 20, 22, 0.0f);
        this.bodyModel[33].setRotationPoint(12.0f, -30.0f, -11.0f);
        this.bodyModel[34].addBox(0.0f, 0.0f, 0.0f, 44, 14, 36, 0.0f);
        this.bodyModel[34].setRotationPoint(49.0f, -30.0f, -18.0f);
        this.bodyModel[35].addShapeBox(0.0f, 0.0f, 0.0f, 69, 9, 14, 0.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[35].setRotationPoint(24.0f, -39.0f, -7.0f);
        this.bodyModel[36].addBox(0.0f, 0.0f, 0.0f, 69, 6, 22, 0.0f);
        this.bodyModel[36].setRotationPoint(24.0f, -16.0f, -11.0f);
        this.bodyModel[37].addShapeBox(0.0f, 0.0f, 0.0f, 28, 6, 11, 0.0f, 0.0f, 0.0f, 0.0f, -6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[37].setRotationPoint(39.0f, -29.0f, -29.0f);
        this.bodyModel[38].addShapeBox(0.0f, 0.0f, 0.0f, 20, 6, 11, 0.0f, 0.0f, 0.0f, -6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[38].setRotationPoint(19.0f, -29.0f, -29.0f);
        this.bodyModel[39].addShapeBox(0.0f, 0.0f, 0.0f, 28, 6, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[39].setRotationPoint(39.0f, -29.0f, 18.0f);
        this.bodyModel[40].addShapeBox(0.0f, 0.0f, 0.0f, 20, 6, 11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -6.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -6.0f);
        this.bodyModel[40].setRotationPoint(19.0f, -29.0f, 18.0f);
        this.tailModel = new ModelRendererTurbo[10];
        this.tailModel[0] = new ModelRendererTurbo(this, 120, 330, this.textureX, this.textureY);
        this.tailModel[1] = new ModelRendererTurbo(this, 120, 380, this.textureX, this.textureY);
        this.tailModel[2] = new ModelRendererTurbo(this, 120, 400, this.textureX, this.textureY);
        this.tailModel[3] = new ModelRendererTurbo(this, 200, 450, this.textureX, this.textureY);
        this.tailModel[4] = new ModelRendererTurbo(this, 200, 450, this.textureX, this.textureY);
        this.tailModel[5] = new ModelRendererTurbo(this, 240, 0, this.textureX, this.textureY);
        this.tailModel[6] = new ModelRendererTurbo(this, 240, 20, this.textureX, this.textureY);
        this.tailModel[7] = new ModelRendererTurbo(this, 240, 40, this.textureX, this.textureY);
        this.tailModel[8] = new ModelRendererTurbo(this, 240, 140, this.textureX, this.textureY);
        this.tailModel[9] = new ModelRendererTurbo(this, 240, 140, this.textureX, this.textureY);
        this.tailModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 41, 14, 32, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f);
        this.tailModel[0].setRotationPoint(93.0f, -30.0f, -16.0f);
        this.tailModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 41, 6, 10, 0.0f, 0.0f, 3.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 3.0f, -2.0f, 0.0f, 0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f);
        this.tailModel[1].setRotationPoint(93.0f, -36.0f, -5.0f);
        this.tailModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 41, 6, 22, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -5.0f, 0.0f, 0.0f, -5.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.tailModel[2].setRotationPoint(93.0f, -16.0f, -11.0f);
        this.tailModel[3].addBox(0.0f, 0.0f, 0.0f, 14, 12, 12, 0.0f);
        this.tailModel[3].setRotationPoint(134.0f, -29.0f, -15.0f);
        this.tailModel[4].addBox(0.0f, 0.0f, 0.0f, 14, 12, 12, 0.0f);
        this.tailModel[4].setRotationPoint(134.0f, -29.0f, 3.0f);
        this.tailModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 52, 8, 6, 0.0f, -18.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -18.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.tailModel[5].setRotationPoint(61.0f, -44.0f, -3.0f);
        this.tailModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 19, 6, 6, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f);
        this.tailModel[6].setRotationPoint(79.0f, -50.0f, -3.0f);
        this.tailModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 47, 43, 2, 0.0f, -45.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -45.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.tailModel[7].setRotationPoint(87.0f, -79.0f, -1.0f);
        this.tailModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 47, 2, 34, 0.0f, -45.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -45.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.tailModel[8].setRotationPoint(93.0f, -22.0f, -50.0f);
        this.tailModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 47, 2, 34, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -45.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -45.0f, 0.0f, 0.0f);
        this.tailModel[9].setRotationPoint(93.0f, -22.0f, 16.0f);
        this.yawFlapModel = new ModelRendererTurbo[1];
        this.yawFlapModel[0] = new ModelRendererTurbo(this, 240, 90, this.textureX, this.textureY);
        this.yawFlapModel[0].addShapeBox(0.0f, 0.0f, -1.0f, 27, 43, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -26.0f, 0.0f, 0.0f, -26.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.yawFlapModel[0].setRotationPoint(134.0f, -79.0f, 0.0f);
        this.pitchFlapLeftModel = new ModelRendererTurbo[1];
        this.pitchFlapLeftModel[0] = new ModelRendererTurbo(this, 300, 200, this.textureX, this.textureY);
        this.pitchFlapLeftModel[0].addShapeBox(0.0f, -1.0f, 0.0f, 16, 2, 34, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -15.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -15.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.pitchFlapLeftModel[0].setRotationPoint(140.0f, -21.0f, -50.0f);
        this.pitchFlapRightModel = new ModelRendererTurbo[1];
        this.pitchFlapRightModel[0] = new ModelRendererTurbo(this, 300, 200, this.textureX, this.textureY);
        this.pitchFlapRightModel[0].addShapeBox(0.0f, -1.0f, 0.0f, 16, 2, 34, 0.0f, 0.0f, 0.0f, 0.0f, -15.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -15.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.pitchFlapRightModel[0].setRotationPoint(140.0f, -21.0f, 16.0f);
        this.rightWingPos2Model = new ModelRendererTurbo[1];
        this.rightWingPos2Model[0] = new ModelRendererTurbo(this, 240, 300, this.textureX, this.textureY);
        this.rightWingPos2Model[0].addShapeBox(0.0f, 0.0f, -83.0f, 40, 3, 83, 0.0f, -27.0f, 0.0f, -4.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -27.0f, 0.0f, -4.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.rightWingPos2Model[0].setRotationPoint(38.0f, -28.0f, -18.0f);
        this.leftWingPos1Model = new ModelRendererTurbo[1];
        this.leftWingPos1Model[0] = new ModelRendererTurbo(this, 240, 300, this.textureX, this.textureY);
        this.leftWingPos1Model[0].addShapeBox(0.0f, 0.0f, 0.0f, 40, 3, 83, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 10.0f, 0.0f, 0.0f, -27.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 10.0f, 0.0f, 0.0f, -27.0f, 0.0f, -4.0f);
        this.leftWingPos1Model[0].setRotationPoint(34.0f, -28.0f, 20.0f);
        this.leftWingPos1Model[0].rotateAngleY = -0.7679449f;
        this.rightWingPos1Model = new ModelRendererTurbo[1];
        this.rightWingPos1Model[0] = new ModelRendererTurbo(this, 240, 300, this.textureX, this.textureY);
        this.rightWingPos1Model[0].addShapeBox(0.0f, 0.0f, -83.0f, 40, 3, 83, 0.0f, -27.0f, 0.0f, -4.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -27.0f, 0.0f, -4.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.rightWingPos1Model[0].setRotationPoint(34.0f, -28.0f, -20.0f);
        this.rightWingPos1Model[0].rotateAngleY = 0.7679449f;
        this.bodyWheelModel = new ModelRendererTurbo[8];
        this.bodyWheelModel[0] = new ModelRendererTurbo(this, 400, 0, this.textureX, this.textureY);
        this.bodyWheelModel[1] = new ModelRendererTurbo(this, 400, 20, this.textureX, this.textureY);
        this.bodyWheelModel[2] = new ModelRendererTurbo(this, 400, 45, this.textureX, this.textureY);
        this.bodyWheelModel[3] = new ModelRendererTurbo(this, 400, 60, this.textureX, this.textureY);
        this.bodyWheelModel[4] = new ModelRendererTurbo(this, 400, 60, this.textureX, this.textureY);
        this.bodyWheelModel[5] = new ModelRendererTurbo(this, 400, 45, this.textureX, this.textureY);
        this.bodyWheelModel[6] = new ModelRendererTurbo(this, 400, 60, this.textureX, this.textureY);
        this.bodyWheelModel[7] = new ModelRendererTurbo(this, 400, 60, this.textureX, this.textureY);
        this.bodyWheelModel[0].addBox(0.0f, 0.0f, 0.0f, 10, 6, 6, 0.0f);
        this.bodyWheelModel[0].setRotationPoint(-17.0f, -10.0f, -3.0f);
        this.bodyWheelModel[1].addBox(0.0f, 0.0f, 0.0f, 6, 10, 2, 0.0f);
        this.bodyWheelModel[1].setRotationPoint(-15.0f, -4.0f, -1.0f);
        this.bodyWheelModel[2].addBox(0.0f, 0.0f, 0.0f, 10, 6, 2, 0.0f);
        this.bodyWheelModel[2].setRotationPoint(-17.0f, 2.0f, -3.0f);
        this.bodyWheelModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 10, 2, 2, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyWheelModel[3].setRotationPoint(-17.0f, 0.0f, -3.0f);
        this.bodyWheelModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 10, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f);
        this.bodyWheelModel[4].setRotationPoint(-17.0f, 8.0f, -3.0f);
        this.bodyWheelModel[5].addBox(0.0f, 0.0f, 0.0f, 10, 6, 2, 0.0f);
        this.bodyWheelModel[5].setRotationPoint(-17.0f, 2.0f, 1.0f);
        this.bodyWheelModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 10, 2, 2, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyWheelModel[6].setRotationPoint(-17.0f, 0.0f, 1.0f);
        this.bodyWheelModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 10, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f);
        this.bodyWheelModel[7].setRotationPoint(-17.0f, 8.0f, 1.0f);
        this.leftWingWheelModel = new ModelRendererTurbo[5];
        this.leftWingWheelModel[0] = new ModelRendererTurbo(this, 400, 80, this.textureX, this.textureY);
        this.leftWingWheelModel[1] = new ModelRendererTurbo(this, 400, 20, this.textureX, this.textureY);
        this.leftWingWheelModel[2] = new ModelRendererTurbo(this, 400, 45, this.textureX, this.textureY);
        this.leftWingWheelModel[3] = new ModelRendererTurbo(this, 400, 60, this.textureX, this.textureY);
        this.leftWingWheelModel[4] = new ModelRendererTurbo(this, 400, 60, this.textureX, this.textureY);
        this.leftWingWheelModel[0].addBox(0.0f, 0.0f, 0.0f, 10, 12, 6, 0.0f);
        this.leftWingWheelModel[0].setRotationPoint(73.0f, -16.0f, -18.0f);
        this.leftWingWheelModel[1].addBox(0.0f, 0.0f, 0.0f, 6, 10, 2, 0.0f);
        this.leftWingWheelModel[1].setRotationPoint(75.0f, -4.0f, -16.0f);
        this.leftWingWheelModel[2].addBox(0.0f, 0.0f, 0.0f, 10, 6, 2, 0.0f);
        this.leftWingWheelModel[2].setRotationPoint(73.0f, 2.0f, -18.0f);
        this.leftWingWheelModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 10, 2, 2, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.leftWingWheelModel[3].setRotationPoint(73.0f, 0.0f, -18.0f);
        this.leftWingWheelModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 10, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f);
        this.leftWingWheelModel[4].setRotationPoint(73.0f, 8.0f, -18.0f);
        this.rightWingWheelModel = new ModelRendererTurbo[5];
        this.rightWingWheelModel[0] = new ModelRendererTurbo(this, 400, 80, this.textureX, this.textureY);
        this.rightWingWheelModel[1] = new ModelRendererTurbo(this, 400, 20, this.textureX, this.textureY);
        this.rightWingWheelModel[2] = new ModelRendererTurbo(this, 400, 45, this.textureX, this.textureY);
        this.rightWingWheelModel[3] = new ModelRendererTurbo(this, 400, 60, this.textureX, this.textureY);
        this.rightWingWheelModel[4] = new ModelRendererTurbo(this, 400, 60, this.textureX, this.textureY);
        this.rightWingWheelModel[0].addBox(0.0f, 0.0f, 0.0f, 10, 12, 6, 0.0f);
        this.rightWingWheelModel[0].setRotationPoint(73.0f, -16.0f, 12.0f);
        this.rightWingWheelModel[1].addBox(0.0f, 0.0f, 0.0f, 6, 10, 2, 0.0f);
        this.rightWingWheelModel[1].setRotationPoint(75.0f, -4.0f, 14.0f);
        this.rightWingWheelModel[2].addBox(0.0f, 0.0f, 0.0f, 10, 6, 2, 0.0f);
        this.rightWingWheelModel[2].setRotationPoint(73.0f, 2.0f, 16.0f);
        this.rightWingWheelModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 10, 2, 2, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.rightWingWheelModel[3].setRotationPoint(73.0f, 0.0f, 16.0f);
        this.rightWingWheelModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 10, 2, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f, -2.0f, 0.0f, 0.0f);
        this.rightWingWheelModel[4].setRotationPoint(73.0f, 8.0f, 16.0f);
        this.leftWingPos2Model = new ModelRendererTurbo[1];
        this.leftWingPos2Model[0] = new ModelRendererTurbo(this, 240, 300, this.textureX, this.textureY);
        this.leftWingPos2Model[0].addShapeBox(0.0f, 0.0f, 0.0f, 40, 3, 83, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 10.0f, 0.0f, 0.0f, -27.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 10.0f, 0.0f, 0.0f, -27.0f, 0.0f, -4.0f);
        this.leftWingPos2Model[0].setRotationPoint(38.0f, -28.0f, 18.0f);
        this.translateAll(0, 0, 0);
        this.propellerModels = new ModelRendererTurbo[1][1];
        this.propellerModels[0][0] = new ModelRendererTurbo(this, 0, 0, this.textureX, this.textureY);
        this.propellerModels[0][0].addBox(0.0f, 0.0f, 0.0f, 10, 2, 2, 0.0f);
        this.propellerModels[0][0].setRotationPoint(65.0f, -23.0f, -1.0f);
        this.flipAll();
    }

    private ModelRendererTurbo[] makeProp(int i, int j, int k) {
        ModelRendererTurbo[] prop = new ModelRendererTurbo[]{};
        return prop;
    }
}

