package com.flansmod.hand.client.dev;

import com.flansmod.client.FlansModClient;
import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.network.PacketReload;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import com.flansmod.hand.client.dev.BasicRenderConfig;
import com.flansmod.hand.client.dev.GuiDevButton;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class GuiDev extends GuiScreen {

   protected String guiName;
   protected int x;
   protected int y;
   protected int w;
   protected int h;
   private BasicRenderConfig anim;
   private float[] configFieldsReserve;
   private Field[] configFields;
   private List steps = Lists.newArrayList();
   private List stepsRes = Lists.newArrayList();
   private List stepChanges = Lists.newArrayList();
   protected boolean useItem;
   private GuiButton selectedCategory;
   private Map customStrings = Maps.newHashMap();
   private Map buttonsForCategories = Maps.newLinkedHashMap();
   private GuiButton currentCategory;
   private int yOffsetIndex = 0;
   private int registryIndex = 0;
   private GuiButton selectedButton;


   public GuiDev(ItemStack is, int hOffset, BasicRenderConfig anim) {
      this.guiName = is.getDisplayName();
      this.w = 200;
      this.h = 300 + hOffset;
      this.anim = anim;
      this.configFields = anim.getClass().getFields();
      this.configFieldsReserve = new float[this.configFields.length];

      for(int i = 0; i < this.configFieldsReserve.length; ++i) {
         Field field = this.configFields[i];
         field.setAccessible(true);

         try {
            this.configFieldsReserve[i] = field.getFloat(anim);
         } catch (IllegalAccessException var7) {
            var7.printStackTrace();
         }
      }

   }

   public void initGui() {
      super.initGui();
      this.yOffsetIndex = 0;
      this.registryIndex = 0;
      this.currentCategory = null;
      this.buttonsForCategories.clear();
      this.customStrings.clear();
      this.stepChanges.clear();
      this.stepsRes.clear();
      this.steps.clear();
      super.buttonList.clear();
      super.buttonList.add((new GuiDevButton(9999, this.x + 5, this.y + this.h - 30, this.w - 10, 20, "РЎРѕС…СЂР°РЅРёС‚СЊ")).drawBlackOutline());
   }

   protected GuiButton putControlButtons(String text, float step, float stepChange) {
      if(this.yOffsetIndex == 0) {
         throw new IllegalStateException("");
      } else {
         List buttons = (List)this.buttonsForCategories.get(this.currentCategory);
         int y = this.y + 29 + this.buttonsForCategories.size() * 16 + buttons.size() / 5 * 16;
         this.stepsRes.add(Float.valueOf(step));
         this.steps.add(Float.valueOf(step));
         this.stepChanges.add(Float.valueOf(stepChange));
         buttons.add((new GuiDevButton(this.registryIndex++, this.x + this.w - 80, y + 3, 10, 10, "<")).drawBlackOutline().drawUnderline(false));
         buttons.add((new GuiDevButton(this.registryIndex++, this.x + this.w - 68, y + 3, 10, 10, ">")).drawBlackOutline().drawUnderline(false));
         buttons.add((new GuiDevButton(this.registryIndex++, this.x + this.w - 56, y + 3, 10, 10, "C")).drawBlackOutline().setColor(-5636096, -65536).drawUnderline(false));
         buttons.add((new GuiDevButton(1000 + (this.registryIndex - 2), this.x + this.w - 40, y, 35, 16, "В±" + step)).drawOnlyText().setTextColor(-11513776, -5592406));
         GuiDevButton b;
         buttons.add(b = (new GuiDevButton(2000 + (this.registryIndex - 2), this.x, y, text)).drawOnlyText().setTextLeftOriented().setCantBePressed());
         this.buttonsForCategories.put(this.currentCategory, buttons);
         ++this.yOffsetIndex;
         return b;
      }
   }

   protected void registryAsBool(GuiButton b) {
      this.customStrings.put(b, new GuiDev.ICustomStringListener() {
         @SideOnly(Side.CLIENT)
         public String getString(GuiButton b, float value) {
            return value % 2.0F == 0.0F?"Рендерить":"Нерендерить";
         }
      });
   }

   protected void registryCustomString(GuiButton b, GuiDev.ICustomStringListener listener) {
      this.customStrings.put(b, listener);
   }

   protected void putCategory(String category) {
      int y = this.y + 30 + this.buttonsForCategories.size() * 16 - 1;
      super.buttonList.add(this.currentCategory = (new GuiDevButton(5000 + this.yOffsetIndex, this.x + 1, y, this.w - 2, 16, EnumChatFormatting.GOLD + category)).drawOnlyText().setTextLeftOriented());
      if(this.yOffsetIndex == 0 && this.selectedCategory == null || this.currentCategory.id == this.selectedCategory.id) {
         this.selectedCategory = this.currentCategory;
      }

      this.buttonsForCategories.put(this.currentCategory, Lists.newArrayList());
      ++this.yOffsetIndex;
   }

   protected void putTranslations() {
	      this.putControlButtons("Расположение X:", 0.01F, 0.002F);
	      this.putControlButtons("Расположение Y:", 0.01F, 0.002F);
	      this.putControlButtons("Расположение Z:", 0.01F, 0.002F);
	   }

	   protected void putBasicControls() {
	      this.putControlButtons("Поворот X:", 1.0F, 0.2F);
	      this.putControlButtons("Поворот Y:", 1.0F, 0.2F);
	      this.putControlButtons("Поворот Z:", 1.0F, 0.2F);
	      this.putControlButtons("Расположение X:", 0.01F, 0.002F);
	      this.putControlButtons("Расположение Y:", 0.01F, 0.002F);
	      this.putControlButtons("Расположение Z:", 0.01F, 0.002F);
	   }

   protected void create() {
      if(this.selectedCategory != null && this.buttonsForCategories.containsKey(this.selectedCategory)) {
         this.moveCategories(this.selectedCategory, 16 * (((List)this.buttonsForCategories.get(this.selectedCategory)).size() / 5));
      }

   }

   protected void actionPerformed(GuiButton b) {
      super.actionPerformed(b);
      int id = b.id;
      if(id == 9999) {
         this.anim.saveConfig();
      } else if(id < 1000) {
         this.controlButtonClicked(id / 3, id % 3);
      } else if(id >= 5000) {
         if(this.selectedCategory != null) {
            this.moveCategories(this.selectedCategory, -16 * (((List)this.buttonsForCategories.get(this.selectedCategory)).size() / 5));
         }

         this.selectedCategory = b;
         this.moveCategories(this.selectedCategory, 16 * (((List)this.buttonsForCategories.get(this.selectedCategory)).size() / 5));
      }

   }

   private void moveCategories(GuiButton after, int value) {
      boolean found = false;
      Iterator var4 = this.buttonsForCategories.keySet().iterator();

      while(var4.hasNext()) {
         GuiButton category = (GuiButton)var4.next();
         if(found) {
            category.yPosition += value;
         }

         if(category == after) {
            found = true;
         }
      }

   }

   protected void controlButtonClicked(int id, int bIndex) {
      Field field = this.configFields[id];
      field.setAccessible(true);
      float step = ((Float)this.steps.get(id)).floatValue();

      try {
         float e = field.getFloat(this.anim);
         if(bIndex == 0) {
            field.setFloat(this.anim, e - step);
         }

         if(bIndex == 1) {
            field.setFloat(this.anim, e + step);
         }

         if(bIndex == 2) {
            field.setFloat(this.anim, this.configFieldsReserve[id]);
         }
      } catch (IllegalAccessException var6) {
         var6.printStackTrace();
      }

   }

   public void drawScreen(int mx, int my, float f) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if(this.useItem) {
         if(super.mc.thePlayer.getCurrentEquippedItem() != null && super.mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemGun) {
            FlansModClient.currentScope = ((ItemGun)super.mc.thePlayer.getCurrentEquippedItem().getItem()).type.getCurrentScope(super.mc.thePlayer.getCurrentEquippedItem());
         }
      } else {
         FlansModClient.currentScope = null;
      }

      drawRect(this.x, this.y, this.x + this.w, this.y + this.h, -16777216);
      drawRect(this.x + 1, this.y + 1, this.x + this.w - 1, this.y + this.h - 1, -15458776);
      drawRect(this.x + this.w - 7, this.y + 7, this.x + this.w - 1, this.y + 13, -16777216);
      drawRect(this.x + this.w - 6, this.y + 7, this.x + this.w - 1, this.y + 12, -1);
      if(this.mouseOver(mx, my, this.x + this.w - 6, this.y + 7, this.x + this.w - 1, this.y + 12)) {
          this.drawDesc(mx, my, new String[]{"Перезарядить"});
      }

      drawRect(this.x + this.w - 7, this.y + 13, this.x + this.w - 1, this.y + 19, -16777216);
      if(this.useItem) {
         drawRect(this.x + this.w - 6, this.y + 13, this.x + this.w - 1, this.y + 18, -256);
      } else {
         drawRect(this.x + this.w - 6, this.y + 13, this.x + this.w - 1, this.y + 18, -1);
      }

      if(this.mouseOver(mx, my, this.x + this.w - 6, this.y + 13, this.x + this.w - 1, this.y + 18)) {
          this.drawDesc(mx, my, new String[]{"Прицелиться"});
      }

      super.drawCenteredString(super.fontRendererObj, EnumChatFormatting.GOLD + this.guiName, this.x + this.w / 2, this.y + 10, 0);
      GuiButton categoryOver = this.getCategoryButtonMouseOver(mx, my);
      if(categoryOver != null && categoryOver != this.selectedCategory) {
         drawRect(this.x + 1, categoryOver.yPosition, this.x + this.w - 1, categoryOver.yPosition + categoryOver.height, -15326419);
      }

      if(this.selectedCategory != null) {
         drawRect(this.x + 1, this.selectedCategory.yPosition, this.x + this.w - 1, this.selectedCategory.yPosition + this.selectedCategory.height, -15985121);
         GuiButton button;
         String prevText;
         if(this.buttonsForCategories.containsKey(this.selectedCategory)) {
            for(Iterator var5 = ((List)this.buttonsForCategories.get(this.selectedCategory)).iterator(); var5.hasNext(); button.displayString = prevText) {
               button = (GuiButton)var5.next();
               prevText = button.displayString;
               if(button.id >= 2000 && button.id < 3000) {
                  int index = (button.id - 2000) / 3;
                  if(index >= 0 && index < this.configFields.length) {
                     Field field = this.configFields[index];
                     field.setAccessible(true);
                     float value = -1.0F;

                     try {
                        value = field.getFloat(this.anim);
                     } catch (IllegalAccessException var12) {
                        var12.printStackTrace();
                     }

                     value = BigDecimal.valueOf((double)value).setScale(4, 4).floatValue();
                     if(this.customStrings.containsKey(button)) {
                        button.displayString = button.displayString + " " + ((GuiDev.ICustomStringListener)this.customStrings.get(button)).getString(button, value);
                     } else {
                        button.displayString = button.displayString + " " + value;
                     }
                  }
               }

               button.drawButton(super.mc, mx, my);
            }
         }
      }

      super.drawScreen(mx, my, f);
   }

   private GuiButton getCategoryButtonMouseOver(int mx, int my) {
      for(int i = 0; i < super.buttonList.size(); ++i) {
         GuiButton b = (GuiButton)super.buttonList.get(i);
         if(b.id >= 5000 && b.id < 9999 && b.mousePressed(super.mc, mx, my)) {
            return b;
         }
      }

      return null;
   }

   private void drawDesc(int mx, int my, String ... strokes) {
      GL11.glPushMatrix();
      GL11.glTranslated(0.0D, 0.0D, 800.0D);
      int widest = 0;
      String[] i = strokes;
      int str = strokes.length;

      for(int var7 = 0; var7 < str; ++var7) {
         String str1 = i[var7];
         int w = super.mc.fontRenderer.getStringWidth(str1);
         if(w > widest) {
            widest = w;
         }
      }

      drawRect(mx, my, mx + widest + 10, my + strokes.length * super.fontRendererObj.FONT_HEIGHT + 10, -16777216);
      drawRect(mx + 1, my + 1, mx + widest + 9, my + strokes.length * super.fontRendererObj.FONT_HEIGHT + 9, -15458776);

      for(int var10 = 0; var10 < strokes.length; ++var10) {
         String var11 = strokes[var10];
         super.mc.fontRenderer.drawStringWithShadow(var11, mx + 5, my + 5 + var10 * super.fontRendererObj.FONT_HEIGHT, -1);
      }

      GL11.glPopMatrix();
   }

   protected void mouseClicked(int mx, int my, int b) {
      GuiButton guibutton;
      if(b == 0) {
         for(int l = 0; l < super.buttonList.size(); ++l) {
            guibutton = (GuiButton)super.buttonList.get(l);
            if(guibutton.mousePressed(super.mc, mx, my)) {
               this.selectedButton = guibutton;
               super.mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
               this.actionPerformed(guibutton);
               if(guibutton.id >= 5000 && guibutton.id < 6000) {
                  break;
               }
            }
         }
      }

      Iterator var8 = ((List)this.buttonsForCategories.get(this.selectedCategory)).iterator();

      while(var8.hasNext()) {
         guibutton = (GuiButton)var8.next();
         if(guibutton.mousePressed(super.mc, mx, my)) {
            this.selectedButton = guibutton;
            super.mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            if(guibutton.id < 2000 && guibutton.mousePressed(super.mc, mx, my)) {
               this.selectedButton = guibutton;
               super.mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
               if(guibutton.id >= 1000) {
                  int id = (guibutton.id - 1000) / 3;
                  if(b == 0) {
                     this.steps.set(id, Float.valueOf(((Float)this.steps.get(id)).floatValue() + ((Float)this.stepChanges.get(id)).floatValue()));
                  }

                  if(b == 1) {
                     float value = ((Float)this.steps.get(id)).floatValue() - ((Float)this.stepChanges.get(id)).floatValue();
                     if(value > 0.0F) {
                        this.steps.set(id, Float.valueOf(value));
                     }
                  }

                  if(b == 2) {
                     this.steps.clear();
                     this.steps.addAll(this.stepsRes);
                  }

                  guibutton.displayString = "В±" + BigDecimal.valueOf((double)((Float)this.steps.get(id)).floatValue()).setScale(4, 4).floatValue();
               } else if(b == 0) {
                  this.actionPerformed(guibutton);
               }
            }
         }
      }

      if(this.mouseOver(mx, my, this.x + this.w - 6, this.y + 7, this.x + this.w - 1, this.y + 12)) {
         if(b == 0) {
            FlansMod.getPacketHandler().sendToServer(new PacketReload(false));
         }
      } else if(this.mouseOver(mx, my, this.x + this.w - 6, this.y + 13, this.x + this.w - 1, this.y + 18) && b == 0) {
         this.useItem = !this.useItem;
      }

   }

   protected boolean mouseOver(int mx, int my, int x, int y, int x1, int y1) {
      return mx >= x && mx <= x1 && my >= y && my <= y1;
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public interface ICustomStringListener {

      String getString(GuiButton var1, float var2);
   }
}
