/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package com.flansmod.client.model.mw;

import com.flansmod.client.model.ModelCustomArmour;
import com.flansmod.client.tmt.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;

public class ModelRecruitChest
extends ModelCustomArmour {
    int textureX = 128;
    int textureY = 128;

    public ModelRecruitChest() {
        this.bodyModel = new ModelRendererTurbo[13];
        this.bodyModel[0] = new ModelRendererTurbo((ModelBase)this, 1, 1, this.textureX, this.textureY);
        this.bodyModel[1] = new ModelRendererTurbo((ModelBase)this, 1, 73, this.textureX, this.textureY);
        this.bodyModel[2] = new ModelRendererTurbo((ModelBase)this, 41, 1, this.textureX, this.textureY);
        this.bodyModel[3] = new ModelRendererTurbo((ModelBase)this, 73, 1, this.textureX, this.textureY);
        this.bodyModel[4] = new ModelRendererTurbo((ModelBase)this, 113, 9, this.textureX, this.textureY);
        this.bodyModel[5] = new ModelRendererTurbo((ModelBase)this, 121, 9, this.textureX, this.textureY);
        this.bodyModel[6] = new ModelRendererTurbo((ModelBase)this, 113, 17, this.textureX, this.textureY);
        this.bodyModel[7] = new ModelRendererTurbo((ModelBase)this, 121, 17, this.textureX, this.textureY);
        this.bodyModel[8] = new ModelRendererTurbo((ModelBase)this, 25, 33, this.textureX, this.textureY);
        this.bodyModel[9] = new ModelRendererTurbo((ModelBase)this, 33, 33, this.textureX, this.textureY);
        this.bodyModel[10] = new ModelRendererTurbo((ModelBase)this, 41, 33, this.textureX, this.textureY);
        this.bodyModel[11] = new ModelRendererTurbo((ModelBase)this, 113, 41, this.textureX, this.textureY);
        this.bodyModel[12] = new ModelRendererTurbo((ModelBase)this, 121, 41, this.textureX, this.textureY);
        this.bodyModel[0].addShapeBox(-4.5f, 0.0f, -2.0f, 16, 18, 4, 0.0f, -0.2f, 0.15f, 0.1f, -7.0f, 0.15f, 0.1f, -7.0f, 0.15f, 0.1f, -0.2f, 0.15f, 0.1f, -0.2f, -6.0f, 0.1f, -7.0f, -6.0f, 0.1f, -7.0f, -6.0f, 0.1f, -0.2f, -6.0f, 0.1f);
        this.bodyModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[1].addShapeBox(-4.5f, 8.6f, -2.2f, 16, 8, 5, 0.0f, 0.1f, 0.0f, 0.1f, -6.9f, 0.0f, 0.1f, -6.9f, 0.0f, -0.51f, 0.1f, 0.0f, -0.5f, 0.1f, -6.7f, 0.1f, -6.9f, -6.7f, 0.1f, -6.9f, -6.7f, -0.5f, 0.1f, -6.7f, -0.5f);
        this.bodyModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[2].addShapeBox(0.0f, 0.0f, -2.35f, 3, 2, 1, 0.0f, -0.5f, -1.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -0.5f, -1.5f, 0.0f, 0.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.5f, 0.0f);
        this.bodyModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[3].addShapeBox(-3.0f, 0.0f, -2.35f, 3, 2, 1, 0.0f, 1.0f, 0.0f, 0.0f, -0.5f, -1.5f, 0.0f, -0.5f, -1.5f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.5f, 0.0f, 0.0f, 1.5f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.bodyModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[4].addShapeBox(-0.3f, 3.7f, -1.9f, 1, 1, 1, 0.0f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f);
        this.bodyModel[4].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[5].addShapeBox(-0.3f, 5.7f, -1.9f, 1, 1, 1, 0.0f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f);
        this.bodyModel[5].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[6].addShapeBox(-0.3f, 7.7f, -1.9f, 1, 1, 1, 0.0f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f);
        this.bodyModel[6].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[7].addShapeBox(-0.8f, 2.7f, -1.9f, 1, 7, 1, 0.0f, -0.4f, -0.2f, 0.35f, -0.4f, -0.2f, 0.35f, -0.4f, -0.2f, 0.35f, -0.4f, -0.2f, 0.35f, -0.4f, -0.2f, 0.35f, -0.4f, -0.2f, 0.35f, -0.4f, -0.2f, 0.35f, -0.4f, -0.2f, 0.35f);
        this.bodyModel[7].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[8].addShapeBox(0.1f, 2.3f, -2.15f, 1, 1, 1, 0.0f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f);
        this.bodyModel[8].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[9].addShapeBox(-1.1f, 2.3f, -2.15f, 1, 1, 1, 0.0f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f, -0.3f, -0.3f, 0.35f);
        this.bodyModel[9].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[10].addShapeBox(-0.3f, 3.7f, -1.9f, 1, 1, 1, 0.0f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f);
        this.bodyModel[10].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[11].addShapeBox(-0.3f, 5.7f, -1.9f, 1, 1, 1, 0.0f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f);
        this.bodyModel[11].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bodyModel[12].addShapeBox(-0.3f, 7.7f, -1.9f, 1, 1, 1, 0.0f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f, -0.3f, -0.3f, 0.4f);
        this.bodyModel[12].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftArmModel = new ModelRendererTurbo[5];
        this.leftArmModel[0] = new ModelRendererTurbo((ModelBase)this, 49, 1, this.textureX, this.textureY);
        this.leftArmModel[1] = new ModelRendererTurbo((ModelBase)this, 25, 25, this.textureX, this.textureY);
        this.leftArmModel[2] = new ModelRendererTurbo((ModelBase)this, 89, 25, this.textureX, this.textureY);
        this.leftArmModel[3] = new ModelRendererTurbo((ModelBase)this, 17, 33, this.textureX, this.textureY);
        this.leftArmModel[4] = new ModelRendererTurbo((ModelBase)this, 97, 57, this.textureX, this.textureY);
        this.leftArmModel[0].addShapeBox(-0.8f, -2.1f, -2.0f, 8, 16, 4, 0.0f, 0.3f, 0.0f, 0.15f, -3.85f, 0.0f, 0.15f, -3.85f, 0.0f, 0.15f, 0.3f, 0.0f, 0.15f, 0.3f, -6.0f, 0.15f, -3.85f, -6.0f, 0.15f, -3.85f, -6.0f, 0.15f, 0.3f, -6.0f, 0.15f);
        this.leftArmModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftArmModel[1].addShapeBox(-0.8f, 7.8f, -2.0f, 4, 1, 4, 0.0f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f);
        this.leftArmModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftArmModel[2].addShapeBox(2.3f, 6.7f, -1.5f, 1, 1, 2, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f);
        this.leftArmModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftArmModel[3].addShapeBox(2.3f, 6.7f, -0.8f, 1, 1, 1, 0.0f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f);
        this.leftArmModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftArmModel[4].addShapeBox(2.45f, 1.0f, -1.5f, 1, 2, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.leftArmModel[4].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightArmModel = new ModelRendererTurbo[5];
        this.rightArmModel[0] = new ModelRendererTurbo((ModelBase)this, 81, 1, this.textureX, this.textureY);
        this.rightArmModel[1] = new ModelRendererTurbo((ModelBase)this, 1, 25, this.textureX, this.textureY);
        this.rightArmModel[2] = new ModelRendererTurbo((ModelBase)this, 17, 25, this.textureX, this.textureY);
        this.rightArmModel[3] = new ModelRendererTurbo((ModelBase)this, 41, 25, this.textureX, this.textureY);
        this.rightArmModel[4] = new ModelRendererTurbo((ModelBase)this, 17, 57, this.textureX, this.textureY);
        this.rightArmModel[0].addShapeBox(-3.2f, -2.1f, -2.0f, 8, 16, 4, 0.0f, 0.2f, 0.0f, 0.15f, -3.7f, 0.0f, 0.15f, -3.7f, 0.0f, 0.15f, 0.2f, 0.0f, 0.15f, 0.2f, -6.0f, 0.15f, -3.7f, -6.0f, 0.15f, -3.7f, -6.0f, 0.15f, 0.2f, -6.0f, 0.15f);
        this.rightArmModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightArmModel[1].addShapeBox(-3.2f, 7.8f, -2.0f, 4, 1, 4, 0.0f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f, 0.3f, 0.0f, 0.3f);
        this.rightArmModel[1].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightArmModel[2].addShapeBox(-3.5f, 6.7f, -1.5f, 1, 1, 2, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f);
        this.rightArmModel[2].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightArmModel[3].addShapeBox(-3.5f, 6.7f, -0.8f, 1, 1, 1, 0.0f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f, 0.35f, -0.2f, -0.2f);
        this.rightArmModel[3].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightArmModel[4].addShapeBox(-3.55f, 1.0f, -1.5f, 1, 2, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.rightArmModel[4].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.leftLegModel = new ModelRendererTurbo[1];
        this.leftLegModel[0] = new ModelRendererTurbo((ModelBase)this, 1, 49, this.textureX, this.textureY);
        this.leftLegModel[0].addShapeBox(-1.45f, -2.1f, -2.4f, 4, 4, 5, 0.0f, 0.4f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.4f, 0.4f, 0.0f, -0.4f, 0.2f, 0.0f, 0.2f, 0.2f, 0.0f, 0.2f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f);
        this.leftLegModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
        this.rightLegModel = new ModelRendererTurbo[1];
        this.rightLegModel[0] = new ModelRendererTurbo((ModelBase)this, 17, 41, this.textureX, this.textureY);
        this.rightLegModel[0].addShapeBox(-2.35f, -2.1f, -2.4f, 4, 4, 5, 0.0f, 0.0f, 0.0f, -0.1f, 0.4f, 0.0f, -0.1f, 0.4f, 0.0f, -0.4f, 0.0f, 0.0f, -0.4f, 0.2f, 0.0f, 0.2f, 0.2f, 0.0f, 0.2f, 0.2f, 0.0f, 0.0f, 0.2f, 0.0f, 0.0f);
        this.rightLegModel[0].setRotationPoint(0.0f, 0.0f, 0.0f);
    }
}

