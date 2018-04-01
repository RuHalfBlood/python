/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.flansmod.client.model.titan;

import com.flansmod.client.model.ModelDriveable;
import com.flansmod.client.model.ModelMecha;
import com.flansmod.client.tmt.Coord2D;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.client.tmt.Shape2D;
import com.flansmod.common.vector.Vector3f;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelProtoTitan
extends ModelMecha {
    public ModelProtoTitan() {
        int textureX = 256;
        int textureY = 256;
        this.hipsAttachmentPoint = new Vector3f(-0.75625f, 2.25f, 0.0f);
        this.bodyModel = new ModelRendererTurbo[8];
        this.bodyModel[0] = new ModelRendererTurbo(this, 0, 0, textureX, textureY);
        this.bodyModel[0].addBox(-12.0f, 29.0f, -12.0f, 16, 19, 24);
        this.bodyModel[1] = new ModelRendererTurbo(this, 88, 0, textureX, textureY);
        this.bodyModel[1].addShape3D(0.0f, 21.0f, 12.0f, new Shape2D(new Coord2D[]{new Coord2D(4.0, 8.0, 0, 0), new Coord2D(12.0, 8.0, 8, 0), new Coord2D(12.0, 18.0, 8, 10), new Coord2D(4.0, 27.0, 0, 19)}), 24.0f, 8, 19, 49, 24, 1, new float[]{19.0f, 12.0f, 10.0f, 8.0f});
        this.bodyModel[2] = new ModelRendererTurbo(this, 139, 0, textureX, textureY);
        this.bodyModel[2].addShape3D(0.0f, 21.0f, 12.0f, new Shape2D(new Coord2D[]{new Coord2D(-12.0, 0.0, 0, 0), new Coord2D(8.0, 0.0, 20, 0), new Coord2D(12.0, 8.0, 24, 8), new Coord2D(-12.0, 8.0, 0, 8)}), 24.0f, 24, 8, 61, 24, 1, new float[]{8.0f, 24.0f, 9.0f, 20.0f});
        this.bodyModel[3] = new ModelRendererTurbo(this, 118, 34, textureX, textureY);
        this.bodyModel[3].flip = true;
        this.bodyModel[3].addBox(-12.0f, 29.0f, -11.5f, 16, 19, 23);
        this.bodyModel[4] = new ModelRendererTurbo(this, 203, 0, textureX, textureY);
        this.bodyModel[4].addShape3D(0.0f, 21.0f, 11.5f, new Shape2D(new Coord2D[]{new Coord2D(4.0, 8.0, 0, 0), new Coord2D(4.0, 27.0, 0, 19), new Coord2D(12.0, 18.0, 8, 10), new Coord2D(12.0, 8.0, 8, 0)}), 23.0f, 8, 19, 49, 24, 1, new float[]{8.0f, 10.0f, 12.0f, 19.0f});
        this.bodyModel[5] = new ModelRendererTurbo(this, 0, 52, textureX, textureY);
        this.bodyModel[5].addShape3D(0.0f, 21.0f, 11.5f, new Shape2D(new Coord2D[]{new Coord2D(-12.0, 2.0, 0, 0), new Coord2D(-12.0, 8.0, 0, 6), new Coord2D(12.0, 8.0, 24, 6), new Coord2D(9.0, 2.0, 21, 0)}), 23.0f, 24, 6, 61, 24, 1, new float[]{20.0f, 9.0f, 24.0f, 8.0f});
        this.bodyModel[6] = new ModelRendererTurbo(this, 85, 82, textureX, textureY);
        this.bodyModel[6].addBox(6.0f, 21.0f, -5.0f, 2, 8, 2);
        this.bodyModel[7] = new ModelRendererTurbo(this, 85, 82, textureX, textureY);
        this.bodyModel[7].addBox(6.0f, 21.0f, 3.0f, 2, 8, 2);
        this.leftArmModel = new ModelRendererTurbo[6];
        this.leftArmModel[0] = new ModelRendererTurbo(this, 63, 50, textureX, textureY);
        this.leftArmModel[0].addBox(-2.5f, -2.5f, -2.5f, 5, 5, 5);
        this.leftArmModel[0].setPosition(0.0f, 0.0f, 0.0f);
        this.leftArmModel[1] = new ModelRendererTurbo(this, 85, 50, textureX, textureY);
        this.leftArmModel[1].addBox(-3.0f, -9.0f, -3.0f, 6, 9, 6);
        this.leftArmModel[1].setPosition(0.25f, 0.0f, 0.0f);
        this.leftArmModel[1].rotateAngleZ = -0.34906587f;
        this.leftArmModel[2] = new ModelRendererTurbo(this, 63, 60, textureX, textureY);
        this.leftArmModel[2].addBox(-2.5f, -2.5f, -2.5f, 5, 5, 5);
        this.leftArmModel[2].setPosition(-2.8f, -10.0f, 0.0f);
        this.leftArmModel[3] = new ModelRendererTurbo(this, 85, 65, textureX, textureY);
        this.leftArmModel[3].addBox(-3.0f, 0.0f, -3.0f, 6, 9, 6);
        this.leftArmModel[3].setPosition(0.25f, -19.0f, 0.0f);
        this.leftArmModel[3].rotateAngleZ = 0.34906587f;
        this.leftArmModel[4] = new ModelRendererTurbo(this, 63, 70, textureX, textureY);
        this.leftArmModel[4].addBox(-2.5f, -2.5f, -2.5f, 5, 5, 5);
        this.leftArmModel[4].setPosition(0.0f, -20.0f, 0.0f);
        this.leftArmModel[5] = new ModelRendererTurbo(this, 63, 82, textureX, textureY);
        this.leftArmModel[5].addBox(-1.5f, -1.5f, 2.0f, 3, 3, 2);
        this.leftArmModel[5].setPosition(0.0f, 0.0f, 0.0f);
        this.rightArmModel = (ModelRendererTurbo[])this.leftArmModel.clone();
        this.rightArmModel[5] = new ModelRendererTurbo(this, 63, 82, textureX, textureY);
        this.rightArmModel[5].addBox(-1.5f, -1.5f, -4.0f, 3, 3, 2);
        this.rightArmModel[5].setPosition(0.0f, 0.0f, 0.0f);
        this.hipsModel = new ModelRendererTurbo[1];
        this.hipsModel[0] = new ModelRendererTurbo(this, 0, 84, textureX, textureY);
        this.hipsModel[0].addBox(-10.0f, 15.0f, -10.0f, 20, 6, 20);
        this.leftLegModel = new ModelRendererTurbo[1];
        this.leftLegModel[0] = new ModelRendererTurbo(this, 0, 112, textureX, textureY);
        this.leftLegModel[0].addBox(-4.0f, -16.0f, -4.0f, 8, 20, 8);
        this.leftLegModel[0].setPosition(0.0f, 16.0f, -5.0f);
        this.rightLegModel = new ModelRendererTurbo[1];
        this.rightLegModel[0] = new ModelRendererTurbo(this, 32, 112, textureX, textureY);
        this.rightLegModel[0].addBox(-4.0f, -16.0f, -4.0f, 8, 20, 8);
        this.rightLegModel[0].setPosition(0.0f, 16.0f, 5.0f);
    }
}

