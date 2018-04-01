package com.flansmod.hand.client.dev.flans;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import com.flansmod.hand.client.dev.BasicRenderConfig;
import com.flansmod.hand.client.dev.GuiDev;

@SideOnly(Side.CLIENT)
public class GuiFlansGunDev extends GuiDev {

   public GuiFlansGunDev(ItemStack is, BasicRenderConfig anim) {
      super(is, 20, anim);
   }

   public void initGui() {
	      super.initGui();
	      this.putCategory("Правая рука");
	      this.registryAsBool(this.putControlButtons("Рендерить:", 1.0F, 0.0F));
	      this.putBasicControls();
	      this.putControlButtons("Длина:", 0.1F, 0.05F);
	      this.putControlButtons("Размер:", 0.1F, 0.05F);
	      this.putCategory("> В прицеливании");
	      this.putBasicControls();
	      this.putCategory("Левая рука");
	      this.registryAsBool(this.putControlButtons("Рендерить:", 1.0F, 0.0F));
	      this.putBasicControls();
	      this.putControlButtons("Длина:", 0.1F, 0.05F);
	      this.putControlButtons("Размер:", 0.1F, 0.05F);
	      this.putCategory("> В прицеливании");
	      this.putBasicControls();
	      this.putCategory("Анимация перезарядки");
	      this.registryAsBool(this.putControlButtons("Анимировать:", 1.0F, 0.0F));
	      this.registryAsBool(this.putControlButtons("Левой рукой:", 1.0F, 0.0F));
	      this.putCategory("> Начальные положения");
	      this.putBasicControls();
	      this.putCategory("Эффект выстрела (Первое лицо)");
	      this.putTranslations();
	      this.putCategory("Эффект выстрела (Третье лицо)");
	      this.putTranslations();
	      this.create();
	   }
	}
