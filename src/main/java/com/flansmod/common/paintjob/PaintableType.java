package com.flansmod.common.paintjob;

import com.flansmod.common.FlansMod;
import com.flansmod.common.paintjob.Paintjob;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.types.TypeFile;
import java.util.ArrayList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public abstract class PaintableType extends InfoType {

   public ArrayList paintjobs = new ArrayList();
   public Paintjob defaultPaintjob;
   private int nextPaintjobID = 1;


   public PaintableType(TypeFile file) {
      super(file);
   }

   public void preRead(TypeFile file) {}

   public void postRead(TypeFile file) {
      this.defaultPaintjob = new Paintjob(0, super.iconPath, super.texture, new ItemStack[0]);
      ArrayList newPaintjobList = new ArrayList();
      newPaintjobList.add(this.defaultPaintjob);
      newPaintjobList.addAll(this.paintjobs);
      this.paintjobs = newPaintjobList;
   }

   protected void read(String[] split, TypeFile file) {
      super.read(split, file);

      try {
         if(split[0].toLowerCase().equals("paintjob")) {
            ItemStack[] e = new ItemStack[(split.length - 3) / 2];

            for(int i = 0; i < (split.length - 3) / 2; ++i) {
               e[i] = new ItemStack(Items.dye, Integer.parseInt(split[i * 2 + 4]), this.getDyeDamageValue(split[i * 2 + 3]));
            }

            this.paintjobs.add(new Paintjob(this.nextPaintjobID++, split[1], split[2], e));
         }
      } catch (Exception var5) {
         FlansMod.log("Reading file failed : " + super.shortName);
         var5.printStackTrace();
      }

   }

   public Paintjob getPaintjob(int i) {
      return (Paintjob)this.paintjobs.get(i);
   }

   public float GetRecommendedScale() {
      return 50.0F;
   }
}
