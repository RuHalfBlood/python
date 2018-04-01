package com.flansmod.common.teams;

import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.paintjob.Paintjob;
import com.flansmod.common.teams.ArmourType;
import com.flansmod.common.teams.ItemTeamArmour;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.types.TypeFile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PlayerClass extends InfoType {

   public static List classes = new ArrayList();
   public List startingItemStrings = new ArrayList();
   public List startingItems = new ArrayList();
   public boolean horse = false;
   public ItemStack hat;
   public ItemStack chest;
   public ItemStack legs;
   public ItemStack shoes;


   public PlayerClass(TypeFile file) {
      super(file);
      classes.add(this);
   }

   protected void read(String[] split, TypeFile file) {
      super.read(split, file);
      if(split[0].equals("AddItem")) {
         this.startingItemStrings.add(split);
      }

      if(split[0].equals("SkinOverride")) {
         super.texture = split[1];
      }

      Iterator var3;
      Item item;
      ArmourType armour;
      if(split[0].equals("Hat") || split[0].equals("Helmet")) {
         if(split[1].equals("None")) {
            return;
         }

         var3 = FlansMod.armourItems.iterator();

         while(var3.hasNext()) {
            item = (Item)var3.next();
            armour = ((ItemTeamArmour)item).type;
            if(armour != null && armour.shortName.equals(split[1])) {
               this.hat = new ItemStack(item);
            }
         }
      }

      if(split[0].equals("Chest") || split[0].equals("Top")) {
         if(split[1].equals("None")) {
            return;
         }

         var3 = FlansMod.armourItems.iterator();

         while(var3.hasNext()) {
            item = (Item)var3.next();
            armour = ((ItemTeamArmour)item).type;
            if(armour != null && armour.shortName.equals(split[1])) {
               this.chest = new ItemStack(item);
            }
         }
      }

      if(split[0].equals("Legs") || split[0].equals("Bottom")) {
         if(split[1].equals("None")) {
            return;
         }

         var3 = FlansMod.armourItems.iterator();

         while(var3.hasNext()) {
            item = (Item)var3.next();
            armour = ((ItemTeamArmour)item).type;
            if(armour != null && armour.shortName.equals(split[1])) {
               this.legs = new ItemStack(item);
            }
         }
      }

      if(split[0].equals("Shoes") || split[0].equals("Boots")) {
         if(split[1].equals("None")) {
            return;
         }

         var3 = FlansMod.armourItems.iterator();

         while(var3.hasNext()) {
            item = (Item)var3.next();
            armour = ((ItemTeamArmour)item).type;
            if(armour != null && armour.shortName.equals(split[1])) {
               this.shoes = new ItemStack(item);
            }
         }
      }

   }

   protected void preRead(TypeFile file) {}

   protected void postRead(TypeFile file) {
      this.onWorldLoad((World)null);
   }

   public void onWorldLoad(World world) {
      if(world == null || !world.isRemote) {
         try {
            this.startingItems.clear();

            ItemStack var18;
            for(Iterator e = this.startingItemStrings.iterator(); e.hasNext(); this.startingItems.add(var18)) {
               String[] split = (String[])e.next();
               Item matchingItem = null;
               int amount = 1;
               int damage = 0;
               String[] itemNames = split[1].split("\\+");
               Iterator stack = Item.itemRegistry.iterator();

               while(stack.hasNext()) {
                  Object gunType = stack.next();
                  Item tags = (Item)gunType;
                  if(tags != null && tags.getUnlocalizedName() != null && (tags.getUnlocalizedName().equals(itemNames[0]) || tags.getUnlocalizedName().split("\\.").length > 1 && tags.getUnlocalizedName().split("\\.")[1].equals(itemNames[0]))) {
                     matchingItem = tags;
                  }
               }

               stack = InfoType.infoTypes.iterator();

               while(stack.hasNext()) {
                  InfoType var19 = (InfoType)stack.next();
                  if(var19.shortName.equals(itemNames[0]) && var19.item != null) {
                     matchingItem = var19.item;
                  }
               }

               if(matchingItem == null) {
                  FlansMod.log("Tried to add " + split[1] + " to player class " + super.shortName + " but the item did not exist");
                  return;
               }

               if(split.length > 2) {
                  amount = Integer.parseInt(split[2]);
               }

               if(split.length > 3) {
                  damage = Integer.parseInt(split[3]);
               }

               var18 = new ItemStack(matchingItem, amount, damage);
               if(itemNames.length > 1 && matchingItem instanceof ItemGun) {
                  GunType var20 = ((ItemGun)matchingItem).type;
                  NBTTagCompound var21 = new NBTTagCompound();
                  NBTTagCompound attachmentTags = new NBTTagCompound();
                  int genericID = 0;

                  for(int i = 0; i < itemNames.length - 1; ++i) {
                     AttachmentType attachment = AttachmentType.getAttachment(itemNames[i + 1]);
                     if(attachment != null) {
                        String tagName = null;

                        switch (attachment.type) {
                            case sights: {
                                tagName = "scope";
                                break;
                            }
                            case barrel: {
                                tagName = "barrel";
                                break;
                            }
                            case stock: {
                                tagName = "stock";
                                break;
                            }
                            case grip: {
                                tagName = "grip";
                                break;
                            }
                            case generic: {
                                tagName = "generic_" + genericID++;
                            }
                        }

                        NBTTagCompound specificAttachmentTags = new NBTTagCompound();
                        (new ItemStack(attachment.item)).writeToNBT(specificAttachmentTags);
                        attachmentTags.setTag(tagName, specificAttachmentTags);
                     } else {
                        Paintjob var22 = var20.getPaintjob(itemNames[i + 1]);
                        if(var22 != null) {
                           var21.setString("Paint", var22.iconName);
                        }
                     }
                  }

                  var21.setTag("attachments", attachmentTags);
                  var18.stackTagCompound = var21;
               }
            }
         } catch (Exception var17) {
            System.out.println("Interpreting player class file failed.");
            var17.printStackTrace();
         }

      }
   }

   public static PlayerClass getClass(String s) {
      Iterator var1 = classes.iterator();

      PlayerClass playerClass;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         playerClass = (PlayerClass)var1.next();
      } while(!playerClass.shortName.equals(s));

      return playerClass;
   }

   public float GetRecommendedScale() {
      return 50.0F;
   }

   @SideOnly(Side.CLIENT)
   public ModelBase GetModel() {
      return null;
   }

}
