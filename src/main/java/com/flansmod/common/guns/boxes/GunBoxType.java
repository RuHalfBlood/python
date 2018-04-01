package com.flansmod.common.guns.boxes;

import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.guns.boxes.BlockGunBox;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.types.TypeFile;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class GunBoxType extends InfoType {

   public BlockGunBox block;
   public String topTexturePath;
   public String sideTexturePath;
   public String bottomTexturePath;
   @SideOnly(Side.CLIENT)
   public IIcon top;
   @SideOnly(Side.CLIENT)
   public IIcon side;
   @SideOnly(Side.CLIENT)
   public IIcon bottom;
   public int numGuns;
   public int nextGun = -1;
   public InfoType[] guns;
   public ShootableType[] bullets;
   public ShootableType[] altBullets;
   public List[] gunParts;
   public List[] bulletParts;
   public List[] altBulletParts;
   private static int lastIconIndex = 2;
   public static HashMap gunBoxMap = new HashMap();


   public GunBoxType(TypeFile file) {
      super(file);
   }

   public void preRead(TypeFile file) {
      Iterator var2 = file.lines.iterator();

      while(var2.hasNext()) {
         String line = (String)var2.next();
         if(line == null) {
            break;
         }

         if(!line.startsWith("//")) {
            String[] split = line.split(" ");
            if(split.length >= 2 && split[0].equals("NumGuns")) {
               this.numGuns = Integer.parseInt(split[1]);
               this.guns = new InfoType[this.numGuns];
               this.bullets = new ShootableType[this.numGuns];
               this.altBullets = new ShootableType[this.numGuns];
               this.gunParts = new List[this.numGuns];
               this.bulletParts = new List[this.numGuns];
               this.altBulletParts = new List[this.numGuns];

               for(int i = 0; i < this.numGuns; ++i) {
                  this.gunParts[i] = new ArrayList();
                  this.bulletParts[i] = new ArrayList();
                  this.altBulletParts[i] = new ArrayList();
               }
            }
         }
      }

   }

   public void postRead(TypeFile file) {
      gunBoxMap.put(super.shortName, this);
   }

   protected void read(String[] split, TypeFile file) {
      super.read(split, file);

      try {
         if(split[0].equals("TopTexture")) {
            this.topTexturePath = split[1];
         }

         if(split[0].equals("BottomTexture")) {
            this.bottomTexturePath = split[1];
         }

         if(split[0].equals("SideTexture")) {
            this.sideTexturePath = split[1];
         }

         int e;
         if(split[0].equals("AddGun")) {
            ++this.nextGun;
            if(this.gunParts[this.nextGun] == null) {
               FlansMod.log("NumGuns was not found or was incorrect");
            }

            this.guns[this.nextGun] = InfoType.getType(split[1]);

            for(e = 0; e < (split.length - 2) / 2; ++e) {
               if(split[e * 2 + 3].contains(".")) {
                  this.gunParts[this.nextGun].add(getRecipeElement(split[e * 2 + 3].split("\\.")[0], Integer.parseInt(split[e * 2 + 2]), Integer.valueOf(split[e * 2 + 3].split("\\.")[1]).intValue(), super.shortName));
               } else {
                  this.gunParts[this.nextGun].add(getRecipeElement(split[e * 2 + 3], Integer.parseInt(split[e * 2 + 2]), 0, super.shortName));
               }
            }
         }

         if(split[0].equals("AddAmmo")) {
            if(this.bulletParts[this.nextGun] == null) {
               FlansMod.log("NumGuns was not found or was incorrect");
            }

            this.bullets[this.nextGun] = ShootableType.getShootableType(split[1]);

            for(e = 0; e < (split.length - 2) / 2; ++e) {
               if(split[e * 2 + 3].contains(".")) {
                  this.bulletParts[this.nextGun].add(getRecipeElement(split[e * 2 + 3].split("\\.")[0], Integer.parseInt(split[e * 2 + 2]), Integer.valueOf(split[e * 2 + 3].split("\\.")[1]).intValue(), super.shortName));
               } else {
                  this.bulletParts[this.nextGun].add(getRecipeElement(split[e * 2 + 3], Integer.parseInt(split[e * 2 + 2]), 0, super.shortName));
               }
            }
         }

         if(split[0].equals("AddAltAmmo") || split[0].equals("AddAlternateAmmo")) {
            if(this.altBulletParts[this.nextGun] == null) {
               FlansMod.log("NumGuns was not found or was incorrect");
            }

            this.altBullets[this.nextGun] = ShootableType.getShootableType(split[1]);

            for(e = 0; e < (split.length - 2) / 2; ++e) {
               if(split[e * 2 + 3].contains(".")) {
                  this.altBulletParts[this.nextGun].add(getRecipeElement(split[e * 2 + 3].split("\\.")[0], Integer.parseInt(split[e * 2 + 2]), Integer.valueOf(split[e * 2 + 3].split("\\.")[1]).intValue(), super.shortName));
               } else {
                  this.altBulletParts[this.nextGun].add(getRecipeElement(split[e * 2 + 3], Integer.parseInt(split[e * 2 + 2]), 0, super.shortName));
               }
            }
         }
      } catch (Exception var4) {
         FlansMod.log("Reading gun box file failed : " + super.shortName);
         var4.printStackTrace();
      }

   }

   public static GunBoxType getBox(String s) {
      return (GunBoxType)gunBoxMap.get(s);
   }

   public static GunBoxType getBox(Block block) {
      Iterator var1 = gunBoxMap.values().iterator();

      GunBoxType type;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         type = (GunBoxType)var1.next();
      } while(type.block != block);

      return type;
   }

   public void addRecipe(Item par1Item) {
      if(super.smeltableFrom != null) {
         GameRegistry.addSmelting(getRecipeElement(super.smeltableFrom, 0), new ItemStack(super.item), 0.0F);
      }

      if(super.recipeLine != null) {
         Object[] i;
         try {
            int e;
            if(!super.shapeless) {
               e = 3;
               int var9;
               if(((String)super.recipe[0]).charAt(0) == 32 && ((String)super.recipe[1]).charAt(0) == 32 && ((String)super.recipe[2]).charAt(0) == 32) {
                  for(var9 = 0; var9 < 3; ++var9) {
                     super.recipe[var9] = ((String)super.recipe[var9]).substring(1);
                  }

                  if(((String)super.recipe[0]).charAt(0) == 32 && ((String)super.recipe[1]).charAt(0) == 32 && ((String)super.recipe[2]).charAt(0) == 32) {
                     for(var9 = 0; var9 < 3; ++var9) {
                        super.recipe[var9] = ((String)super.recipe[var9]).substring(1);
                     }
                  }
               }

               var9 = ((String)super.recipe[0]).length() - 1;
               int var10;
               if(((String)super.recipe[0]).charAt(var9) == 32 && ((String)super.recipe[1]).charAt(var9) == 32 && ((String)super.recipe[2]).charAt(var9) == 32) {
                  for(var10 = 0; var10 < 3; ++var10) {
                     super.recipe[var10] = ((String)super.recipe[var10]).substring(0, var9);
                  }

                  --var9;
                  if(((String)super.recipe[0]).charAt(var9) == 32 && ((String)super.recipe[1]).charAt(var9) == 32 && ((String)super.recipe[2]).charAt(var9) == 32) {
                     for(var10 = 0; var10 < 3; ++var10) {
                        super.recipe[var10] = ((String)super.recipe[var10]).substring(0, 0);
                     }
                  }
               }

               Object[] var11;
               if(super.recipe[0].equals(" ") || super.recipe[0].equals("  ") || super.recipe[0].equals("   ")) {
                  i = new Object[super.recipe.length - 1];
                  i[0] = super.recipe[1];
                  i[1] = super.recipe[2];
                  super.recipe = i;
                  --e;
                  if(super.recipe[0].equals(" ") || super.recipe[0].equals("  ") || super.recipe[0].equals("   ")) {
                     var11 = new Object[super.recipe.length - 1];
                     var11[0] = super.recipe[1];
                     super.recipe = var11;
                     --e;
                  }
               }

               if(super.recipe[e - 1].equals(" ") || super.recipe[e - 1].equals("  ") || super.recipe[e - 1].equals("   ")) {
                  i = new Object[super.recipe.length - 1];
                  i[0] = super.recipe[0];
                  i[1] = super.recipe[1];
                  super.recipe = i;
                  --e;
                  if(super.recipe[e - 1].equals(" ") || super.recipe[e - 1].equals("  ") || super.recipe[e - 1].equals("   ")) {
                     var11 = new Object[super.recipe.length - 1];
                     var11[0] = super.recipe[0];
                     super.recipe = var11;
                     --e;
                  }
               }

               for(var10 = 0; var10 < (super.recipeLine.length - 1) / 2; ++var10) {
                  super.recipe[var10 * 2 + e] = Character.valueOf(super.recipeLine[var10 * 2 + 1].charAt(0));
                  if(super.recipeLine[var10 * 2 + 2].contains(".")) {
                     super.recipe[var10 * 2 + e + 1] = getRecipeElement(super.recipeLine[var10 * 2 + 2].split("\\.")[0], Integer.valueOf(super.recipeLine[var10 * 2 + 2].split("\\.")[1]).intValue());
                  } else {
                     super.recipe[var10 * 2 + e + 1] = getRecipeElement(super.recipeLine[var10 * 2 + 2], 0);
                  }
               }

               GameRegistry.addRecipe(new ItemStack(this.block, super.recipeOutput, 0), super.recipe);
            } else {
               super.recipe = new Object[super.recipeLine.length - 1];

               for(e = 0; e < super.recipeLine.length - 1; ++e) {
                  if(super.recipeLine[e + 1].contains(".")) {
                     super.recipe[e] = getRecipeElement(super.recipeLine[e + 1].split("\\.")[0], Integer.valueOf(super.recipeLine[e + 1].split("\\.")[1]).intValue());
                  } else {
                     super.recipe[e] = getRecipeElement(super.recipeLine[e + 1], 0);
                  }
               }

               GameRegistry.addShapelessRecipe(new ItemStack(this.block, super.recipeOutput, 0), super.recipe);
            }
         } catch (Exception var8) {
            if(super.recipe != null) {
               String msg = " : ";
               i = super.recipe;
               int newRecipe1 = i.length;

               for(int var6 = 0; var6 < newRecipe1; ++var6) {
                  Object o = i[var6];
                  msg = msg + " " + o;
               }

               FlansMod.log("Failed to add recipe for : " + super.shortName + msg);
            } else {
               FlansMod.log("Failed to add recipe for : " + super.shortName);
            }

            if(FlansMod.printStackTrace) {
               var8.printStackTrace();
            }
         }

      }
   }

   public float GetRecommendedScale() {
      return 50.0F;
   }

   @SideOnly(Side.CLIENT)
   public ModelBase GetModel() {
      return null;
   }

}
