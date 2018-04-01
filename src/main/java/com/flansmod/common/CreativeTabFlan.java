package com.flansmod.common;

import com.flansmod.common.FlansMod;
import com.flansmod.common.ItemBlockManyNames;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EnumPlaneMode;
import com.flansmod.common.driveables.ItemPlane;
import com.flansmod.common.driveables.ItemVehicle;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemAAGun;
import com.flansmod.common.guns.ItemAttachment;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.guns.ItemGrenade;
import com.flansmod.common.types.IFlanItem;
import com.flansmod.common.types.InfoType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class CreativeTabFlan extends CreativeTabs {

   public int type;
   public int icon;
   public int time = 0;


   public CreativeTabFlan(int i) {
      super("tabFlan" + i);
      this.type = i;
   }

   public Item getTabIconItem() {
      return null;
   }

   public ItemStack getIconItemStack() {
      this.icon = FlansMod.ticker / 20;
      switch(this.type) {
      case 0:
         return GunType.gunList.size() == 0?new ItemStack(Blocks.wool, 1, 4):new ItemStack(((GunType)GunType.gunList.get(this.icon % GunType.gunList.size())).item);
      case 1:
         return DriveableType.types.size() == 0?new ItemStack(Blocks.wool, 1, 14):new ItemStack(((DriveableType)DriveableType.types.get(this.icon % DriveableType.types.size())).item);
      case 2:
         return FlansMod.partItems.size() == 0?new ItemStack(Blocks.wool, 1, 5):new ItemStack((Item)FlansMod.partItems.get(this.icon % FlansMod.partItems.size()));
      case 3:
         return FlansMod.armourItems.size() == 0?new ItemStack(Blocks.wool, 1, 11):new ItemStack((Item)FlansMod.armourItems.get(this.icon % FlansMod.armourItems.size()));
      case 4:
         return FlansMod.mechaItems.size() == 0?new ItemStack(Blocks.wool, 1, 10):new ItemStack((Item)FlansMod.mechaItems.get(this.icon % FlansMod.mechaItems.size()));
      default:
         return new ItemStack(FlansMod.workbench);
      }
   }



   @SideOnly(value=Side.CLIENT)
   public void displayAllReleventItems(List list) {
       super.displayAllReleventItems(list);
       Comparator<ItemStack> cmp = new Comparator<ItemStack>(){

@Override
         public int compare(ItemStack is1, ItemStack is2) {
            String s1 = is1.getUnlocalizedName();
            String s2 = is2.getUnlocalizedName();
            Item i1 = is1.getItem();
            Item i2 = is2.getItem();
            if(i1 instanceof IFlanItem && i2 instanceof IFlanItem) {
               String dt1 = ((IFlanItem)i1).getInfoType().packName;
               String dt2 = ((IFlanItem)i2).getInfoType().packName;
               int epm1 = dt1.compareTo(dt2);
               if(epm1 != 0) {
                  return epm1;
               }
            }

            if(i1 instanceof ItemBlockManyNames && !(i2 instanceof ItemBlockManyNames)) {
               return -1;
            } else if(!(i1 instanceof ItemBlockManyNames) && i2 instanceof ItemBlockManyNames) {
               return 1;
            } else if(i1 instanceof ItemBlock && !(i2 instanceof ItemBlock)) {
               return -1;
            } else if(!(i1 instanceof ItemBlock) && i2 instanceof ItemBlock) {
               return 1;
            } else if(i1 instanceof ItemAttachment && !(i2 instanceof ItemAttachment)) {
               return -1;
            } else if(!(i1 instanceof ItemAttachment) && i2 instanceof ItemAttachment) {
               return 1;
            } else if(i1 instanceof ItemAAGun && !(i2 instanceof ItemAAGun)) {
               return -1;
            } else if(!(i1 instanceof ItemAAGun) && i2 instanceof ItemAAGun) {
               return 1;
            } else if(i1 instanceof ItemVehicle && i2 instanceof ItemBullet) {
               return 1;
            } else if(i1 instanceof ItemBullet && i2 instanceof ItemVehicle) {
               return -1;
            } else if(i1 instanceof ItemPlane && i2 instanceof ItemBullet) {
               return 1;
            } else if(i1 instanceof ItemBullet && i2 instanceof ItemPlane) {
               return -1;
            } else {
               Object dt11 = null;
               Object dt21 = null;
               if(i1 instanceof ItemVehicle) {
                  dt11 = ((ItemVehicle)i1).type;
               }

               if(i2 instanceof ItemVehicle) {
                  dt21 = ((ItemVehicle)i2).type;
               }

               if(i1 instanceof ItemPlane) {
                  dt11 = ((ItemPlane)i1).type;
               }

               if(i2 instanceof ItemPlane) {
                  dt21 = ((ItemPlane)i2).type;
               }

               if(dt11 != null && dt21 != null) {
                  String epm11 = "";
                  String epm2 = "";
                  if(((InfoType)dt11).modelString.indexOf(".") >= 1) {
                     epm11 = ((InfoType)dt11).modelString.substring(0, ((InfoType)dt11).modelString.indexOf(".") - 1);
                  }

                  if(((InfoType)dt21).modelString.indexOf(".") >= 1) {
                     epm11 = ((InfoType)dt21).modelString.substring(0, ((InfoType)dt21).modelString.indexOf(".") - 1);
                  }

                  if(!epm11.equals(epm2)) {
                     return epm11.compareTo(epm2);
                  }
               }

               if(i1 instanceof ItemVehicle && !(i2 instanceof ItemVehicle)) {
                  return -1;
               } else if(!(i1 instanceof ItemVehicle) && i2 instanceof ItemVehicle) {
                  return 1;
               } else if(i1 instanceof ItemPlane && !(i2 instanceof ItemPlane)) {
                  return -1;
               } else if(!(i1 instanceof ItemPlane) && i2 instanceof ItemPlane) {
                  return 1;
               } else if(i1 instanceof ItemPlane && i2 instanceof ItemPlane) {
                  EnumPlaneMode epm12 = ((ItemPlane)i1).type.mode;
                  EnumPlaneMode epm21 = ((ItemPlane)i2).type.mode;
                  return epm12.compareTo(epm21);
               } else {
                  return !(i1 instanceof ItemGrenade) && i2 instanceof ItemGrenade?-1:(i1 instanceof ItemGrenade && !(i2 instanceof ItemGrenade)?1:s1.compareTo(s2));
               }
            }
         }
      };

      try {
         Collections.sort(list, cmp);
      } catch (Exception var4) {
         ;
      }

   }
}
