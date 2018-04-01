package com.flansmod.client;

import com.flansmod.common.FlansMod;
import com.flansmod.common.teams.BlockSpawner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class TileEntitySpawnerRenderer extends TileEntitySpecialRenderer {

   private RenderBlocks blockRenderer;


   public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
      BlockSpawner.colouredPass = true;
      Tessellator var10 = Tessellator.instance;
      this.bindTexture(TextureMap.locationBlocksTexture);
      RenderHelper.disableStandardItemLighting();
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glDisable(2884);
      if(Minecraft.isAmbientOcclusionEnabled()) {
         GL11.glShadeModel(7425);
      } else {
         GL11.glShadeModel(7424);
      }

      var10.startDrawingQuads();
      var10.setTranslation((double)((float)x - (float)te.xCoord), (double)((float)y - (float)te.yCoord), (double)((float)z - (float)te.zCoord));
      var10.setColorOpaque(1, 1, 1);
      GL11.glColor4f(1.0F, 1.0F, 0.0F, 0.5F);
      this.blockRenderer.renderBlockAllFaces(FlansMod.spawner, te.xCoord, te.yCoord, te.zCoord);
      var10.setTranslation(0.0D, 0.0D, 0.0D);
      var10.draw();
      RenderHelper.enableStandardItemLighting();
      BlockSpawner.colouredPass = false;
   }

   public void func_147496_a(World par1World) {
      this.blockRenderer = new RenderBlocks(par1World);
   }
}
