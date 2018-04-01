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

public class ModelM72LAW
extends ModelGun {
    int textureX = 64;
    int textureY = 64;

    public ModelM72LAW() {
        this.gunModel = new ModelRendererTurbo[17];
        this.gunModel[0] = new ModelRendererTurbo(this, 1, 1, this.textureX, this.textureY);
        this.gunModel[1] = new ModelRendererTurbo(this, 1, 9, this.textureX, this.textureY);
        this.gunModel[2] = new ModelRendererTurbo(this, 1, 17, this.textureX, this.textureY);
        this.gunModel[3] = new ModelRendererTurbo(this, 1, 25, this.textureX, this.textureY);
        this.gunModel[4] = new ModelRendererTurbo(this, 17, 25, this.textureX, this.textureY);
        this.gunModel[5] = new ModelRendererTurbo(this, 33, 25, this.textureX, this.textureY);
        this.gunModel[6] = new ModelRendererTurbo(this, 1, 33, this.textureX, this.textureY);
        this.gunModel[7] = new ModelRendererTurbo(this, 9, 33, this.textureX, this.textureY);
        this.gunModel[8] = new ModelRendererTurbo(this, 17, 33, this.textureX, this.textureY);
        this.gunModel[9] = new ModelRendererTurbo(this, 25, 33, this.textureX, this.textureY);
        this.gunModel[10] = new ModelRendererTurbo(this, 33, 33, this.textureX, this.textureY);
        this.gunModel[11] = new ModelRendererTurbo(this, 41, 33, this.textureX, this.textureY);
        this.gunModel[12] = new ModelRendererTurbo(this, 33, 41, this.textureX, this.textureY);
        this.gunModel[13] = new ModelRendererTurbo(this, 41, 41, this.textureX, this.textureY);
        this.gunModel[14] = new ModelRendererTurbo(this, 49, 41, this.textureX, this.textureY);
        this.gunModel[15] = new ModelRendererTurbo(this, 1, 49, this.textureX, this.textureY);
        this.gunModel[16] = new ModelRendererTurbo(this, 17, 49, this.textureX, this.textureY);
        this.gunModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 15, 1, 3, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[0].setRotationPoint(1.0f, -4.0f, -1.5f);
        this.gunModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 15, 1, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f);
        this.gunModel[1].setRotationPoint(1.0f, -2.0f, -1.5f);
        this.gunModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 15, 1, 3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[2].setRotationPoint(1.0f, -3.0f, -1.5f);
        this.gunModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.gunModel[3].setRotationPoint(10.0f, -4.5f, -1.0f);
        this.gunModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 2, 0.0f, 0.0f, 0.0f, -0.3f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3f);
        this.gunModel[4].setRotationPoint(8.5f, -4.5f, -1.0f);
        this.gunModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 4, 1, 2, 0.0f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f);
        this.gunModel[5].setRotationPoint(4.5f, -4.5f, -1.0f);
        this.gunModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f, -0.45f, -0.1f, -0.25f);
        this.gunModel[6].setRotationPoint(4.0f, -5.5f, -0.5f);
        this.gunModel[7].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.45f, -0.5f, -0.45f, -0.45f, -0.5f, -0.45f, -0.45f, -0.5f, -0.45f, -0.45f, -0.5f, -0.45f, -0.35f, -0.25f, -0.45f, -0.35f, -0.25f, -0.45f, -0.35f, -0.25f, -0.45f, -0.35f, -0.25f);
        this.gunModel[7].setRotationPoint(4.0f, -6.35f, -0.5f);
        this.gunModel[8].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f);
        this.gunModel[8].setRotationPoint(4.0f, -6.05f, -0.7f);
        this.gunModel[9].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f, -0.45f, -0.35f, -0.45f);
        this.gunModel[9].setRotationPoint(4.0f, -6.05f, -0.3f);
        this.gunModel[10].addShapeBox(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.0f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f, -0.45f, -0.1f, -0.3f);
        this.gunModel[10].setRotationPoint(14.4f, -5.5f, -0.5f);
        this.gunModel[11].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.45f, -0.4f, -0.45f, -0.45f, -0.4f, -0.45f, -0.45f, -0.4f, -0.45f, -0.45f, -0.4f, -0.45f, -0.45f, -0.4f, -0.3f, -0.45f, -0.4f, -0.3f, -0.45f, -0.4f, -0.3f, -0.45f, -0.4f, -0.3f);
        this.gunModel[11].setRotationPoint(14.4f, -6.0f, -0.5f);
        this.gunModel[12].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.1f, 0.0f, -0.2f, 0.1f, 0.0f, -0.2f, 0.1f, 0.0f, -0.2f, 0.1f, 0.0f, -0.2f, 0.1f, 0.0f, -0.2f, 0.1f, 0.0f, -0.2f, 0.1f, 0.0f, -0.2f, 0.1f, 0.0f, -0.2f);
        this.gunModel[12].setRotationPoint(7.0f, -4.8f, -0.5f);
        this.gunModel[12].rotateAngleZ = -0.13962634f;
        this.gunModel[13].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, 0.0f, -0.2f, -0.2f, -0.5f, 0.0f, -0.2f, -0.5f, 0.0f, -0.2f, 0.0f, -0.2f, -0.2f, 0.0f, 0.0f, -0.2f, -0.5f, 0.0f, -0.2f, -0.5f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f);
        this.gunModel[13].setRotationPoint(4.0f, -4.5f, -0.5f);
        this.gunModel[14].addShapeBox(0.0f, 0.0f, 0.0f, 2, 1, 2, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.5f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.5f, 0.0f, 0.0f);
        this.gunModel[14].setRotationPoint(13.5f, -4.5f, -1.0f);
        this.gunModel[15].addShapeBox(0.0f, 0.0f, 0.0f, 5, 1, 1, 0.0f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, -0.1f);
        this.gunModel[15].setRotationPoint(-1.3f, -4.1f, -0.5f);
        this.gunModel[16].addShapeBox(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f, -0.2f, 0.1f, -0.2f, -0.2f, 0.1f, -0.2f, -0.2f, 0.1f, -0.2f, -0.2f, 0.1f, -0.2f, -0.2f, 0.1f, -0.2f, -0.2f, 0.1f, -0.2f, -0.2f, 0.1f, -0.2f, -0.2f, 0.1f, -0.2f);
        this.gunModel[16].setRotationPoint(2.8f, -4.1f, -0.5f);
        this.ammoModel = new ModelRendererTurbo[7];
        this.ammoModel[0] = new ModelRendererTurbo(this, 41, 1, this.textureX, this.textureY);
        this.ammoModel[1] = new ModelRendererTurbo(this, 41, 9, this.textureX, this.textureY);
        this.ammoModel[2] = new ModelRendererTurbo(this, 41, 17, this.textureX, this.textureY);
        this.ammoModel[3] = new ModelRendererTurbo(this, 49, 25, this.textureX, this.textureY);
        this.ammoModel[4] = new ModelRendererTurbo(this, 49, 33, this.textureX, this.textureY);
        this.ammoModel[5] = new ModelRendererTurbo(this, 1, 41, this.textureX, this.textureY);
        this.ammoModel[6] = new ModelRendererTurbo(this, 17, 41, this.textureX, this.textureY);
        this.ammoModel[0].addShapeBox(0.0f, 0.0f, 0.0f, 7, 1, 3, 0.0f, 0.0f, -0.2f, -1.0f, 0.0f, -0.2f, -1.0f, 0.0f, -0.2f, -1.0f, 0.0f, -0.2f, -1.0f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f);
        this.ammoModel[0].setRotationPoint(-6.0f, -4.0f, -1.5f);
        this.ammoModel[1].addShapeBox(0.0f, 0.0f, 0.0f, 7, 1, 3, 0.0f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f);
        this.ammoModel[1].setRotationPoint(-6.0f, -3.0f, -1.5f);
        this.ammoModel[2].addShapeBox(0.0f, 0.0f, 0.0f, 7, 1, 3, 0.0f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, 0.0f, -0.2f, 0.0f, -0.2f, -1.0f, 0.0f, -0.2f, -1.0f, 0.0f, -0.2f, -1.0f, 0.0f, -0.2f, -1.0f);
        this.ammoModel[2].setRotationPoint(-6.0f, -2.0f, -1.5f);
        this.ammoModel[3].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 2, 0.0f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f);
        this.ammoModel[3].setRotationPoint(-5.5f, -4.0f, -1.0f);
        this.ammoModel[4].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 1, 0.0f, 0.0f, -0.5f, -0.3f, 0.0f, -0.5f, -0.3f, 0.0f, -0.5f, -0.3f, 0.0f, -0.5f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f);
        this.ammoModel[4].setRotationPoint(-5.5f, -5.0f, -1.0f);
        this.ammoModel[5].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 1, 0.0f, 0.0f, -0.5f, -0.3f, 0.0f, -0.5f, -0.3f, 0.0f, -0.5f, -0.3f, 0.0f, -0.5f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f, 0.0f, 0.0f, -0.3f);
        this.ammoModel[5].setRotationPoint(-5.5f, -5.0f, 0.0f);
        this.ammoModel[6].addShapeBox(0.0f, 0.0f, 0.0f, 3, 1, 1, 0.0f, 0.0f, -0.5f, -0.2f, 0.0f, -0.5f, -0.2f, 0.0f, -0.5f, -0.2f, 0.0f, -0.5f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f, 0.0f, -0.3f, -0.2f);
        this.ammoModel[6].setRotationPoint(-5.5f, -5.0f, -0.5f);
        this.translateAll(0.0f, -1.0f, 0.0f);
        this.flipAll();
        this.gunSlideDistance = 0.5f;
        this.animationType = EnumAnimationType.END_LOADED;
        this.tiltGunTime = 0.4f;
        this.unloadClipTime = 0.1f;
        this.loadClipTime = 0.1f;
        this.untiltGunTime = 0.4f;
    }
}

