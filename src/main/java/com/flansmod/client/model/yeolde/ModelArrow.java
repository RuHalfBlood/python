/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package com.flansmod.client.model.yeolde;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelArrow
extends ModelBase {
    public ModelRenderer bulletModel;

    public ModelArrow() {
        this.bulletModel = new ModelRenderer((ModelBase)this, 0, 0);
        this.bulletModel.addBox(-0.5f, -1.0f, -0.5f, 1, 2, 1);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        Tessellator var10 = Tessellator.instance;
        int var11 = 0;
        float var12 = 0.0f;
        float var13 = 0.5f;
        float var14 = (float)(var11 * 10) / 32.0f;
        float var15 = (float)(5 + var11 * 10) / 32.0f;
        float var16 = 0.0f;
        float var17 = 0.15625f;
        float var18 = (float)(5 + var11 * 10) / 32.0f;
        float var19 = (float)(10 + var11 * 10) / 32.0f;
        float var20 = 0.05625f;
        GL11.glEnable((int)32826);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)45.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)var20, (float)var20, (float)var20);
        GL11.glTranslatef((float)-4.0f, (float)0.0f, (float)0.0f);
        GL11.glNormal3f((float)var20, (float)0.0f, (float)0.0f);
        var10.startDrawingQuads();
        var10.addVertexWithUV(-7.0, -2.0, -2.0, (double)var16, (double)var18);
        var10.addVertexWithUV(-7.0, -2.0, 2.0, (double)var17, (double)var18);
        var10.addVertexWithUV(-7.0, 2.0, 2.0, (double)var17, (double)var19);
        var10.addVertexWithUV(-7.0, 2.0, -2.0, (double)var16, (double)var19);
        var10.draw();
        GL11.glNormal3f((float)(- var20), (float)0.0f, (float)0.0f);
        var10.startDrawingQuads();
        var10.addVertexWithUV(-7.0, 2.0, -2.0, (double)var16, (double)var18);
        var10.addVertexWithUV(-7.0, 2.0, 2.0, (double)var17, (double)var18);
        var10.addVertexWithUV(-7.0, -2.0, 2.0, (double)var17, (double)var19);
        var10.addVertexWithUV(-7.0, -2.0, -2.0, (double)var16, (double)var19);
        var10.draw();
        for (int var23 = 0; var23 < 4; ++var23) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glNormal3f((float)0.0f, (float)0.0f, (float)var20);
            var10.startDrawingQuads();
            var10.addVertexWithUV(-8.0, -2.0, 0.0, (double)var12, (double)var14);
            var10.addVertexWithUV(8.0, -2.0, 0.0, (double)var13, (double)var14);
            var10.addVertexWithUV(8.0, 2.0, 0.0, (double)var13, (double)var15);
            var10.addVertexWithUV(-8.0, 2.0, 0.0, (double)var12, (double)var15);
            var10.draw();
        }
        GL11.glDisable((int)32826);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    }
}

