package com.flansmod.common;

import com.flansmod.common.CraftingInstance;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.CollisionBox;
import com.flansmod.common.driveables.ContainerDriveableInventory;
import com.flansmod.common.driveables.ContainerDriveableMenu;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.driveables.mechas.ContainerMechaInventory;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.guns.ContainerGunModTable;
import com.flansmod.common.guns.boxes.GunBoxType;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketBreakSound;
import com.flansmod.common.paintjob.ContainerPaintjobTable;
import com.flansmod.common.paintjob.TileEntityPaintjobTable;
import com.flansmod.common.parts.ItemPart;
import com.flansmod.common.parts.PartType;
import com.flansmod.common.teams.ArmourBoxType;
import com.flansmod.common.types.EnumType;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class CommonProxy {

   protected static Pattern zipJar = Pattern.compile("(.+).(zip|jar)$");


   public List getContentList(Method method, ClassLoader classloader) {
      ArrayList contentPacks = new ArrayList();
      File[] var4 = FlansMod.flanDir.listFiles();
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         File file = var4[var6];
         if(file.isDirectory() || zipJar.matcher(file.getName()).matches()) {
            FlansMod.log("Loaded content pack : " + file.getName());
            contentPacks.add(file);
         }
      }

      FlansMod.log("Loaded content pack list server side.");
      return contentPacks;
   }

   public void load() {}

   public void forceReload() {}

   public void registerRenderers() {}

   public void doTutorialStuff(EntityPlayer player, EntityDriveable entityType) {}

   public void changeControlMode(EntityPlayer player) {}

   public boolean mouseControlEnabled() {
      return false;
   }

   public void openDriveableMenu(EntityPlayer player, World world, EntityDriveable driveable) {}

   public Object loadModel(String s, String shortName, Class typeClass) {
      return null;
   }

   public void loadSound(String contentPack, String type, String sound) {}

   public boolean isThePlayer(EntityPlayer player) {
      return false;
   }

   public EntityPlayer getThePlayer() {
      return null;
   }

   public boolean isOnSameTeamClientPlayer(EntityLivingBase entity) {
      return false;
   }

   public void buyGun(GunBoxType type, int gun) {}

   public void buyAmmo(GunBoxType box, int ammo, int type) {}

   public Object getClientGui(int ID, EntityPlayer player, World world, int x, int y, int z) {
      return null;
   }

   public HashMap getAnimations(boolean left) {
      return left?FlansMod.gunAnimationsLeft:FlansMod.gunAnimationsRight;
   }

   public Container getServerGui(int ID, EntityPlayer player, World world, int x, int y, int z) {
      switch(ID) {
      case 0:
         return null;
      case 1:
         return null;
      case 2:
         return new ContainerGunModTable(player.inventory, world);
      case 3:
         return new ContainerDriveableMenu(player.inventory, world);
      case 4:
         return new ContainerDriveableMenu(player.inventory, world, true, ((EntitySeat)player.ridingEntity).driveable);
      case 5:
         return null;
      case 6:
         return new ContainerDriveableInventory(player.inventory, world, ((EntitySeat)player.ridingEntity).driveable, 0);
      case 7:
         return new ContainerDriveableInventory(player.inventory, world, ((EntitySeat)player.ridingEntity).driveable, 1);
      case 8:
         return new ContainerDriveableMenu(player.inventory, world, true, ((EntitySeat)player.ridingEntity).driveable);
      case 9:
         return new ContainerDriveableInventory(player.inventory, world, ((EntitySeat)player.ridingEntity).driveable, 2);
      case 10:
         return new ContainerMechaInventory(player.inventory, world, (EntityMecha)((EntitySeat)player.ridingEntity).driveable);
      case 11:
         return null;
      case 12:
         return new ContainerDriveableInventory(player.inventory, world, ((EntitySeat)player.ridingEntity).driveable, 3);
      case 13:
         return new ContainerPaintjobTable(player.inventory, world, (TileEntityPaintjobTable)world.getTileEntity(x, y, z));
      default:
         return null;
      }
  
   }

   public void playBlockBreakSound(int x, int y, int z, Block blockHit) {
      FlansMod.packetHandler.sendToAll((PacketBase)(new PacketBreakSound(x, y, z, blockHit)));
   }

   public void addItem(EntityPlayer player, int id) {
      ItemStack item = new ItemStack(Item.getItemById(id), 1, 4);
      player.inventory.addItemStackToInventory(item);
      ArrayList dirts = new ArrayList();
      dirts.add(0, new ItemStack(Item.getItemById(3)));
      CraftingInstance crafting = new CraftingInstance(player.inventory, dirts, new ItemStack(Item.getItemById(id)));
      if(crafting.canCraft()) {
         crafting.craft(player.inventory.player);
      }

   }

   public void craftDriveable(EntityPlayer player, DriveableType type) {
      InventoryPlayer temporaryInventory = new InventoryPlayer((EntityPlayer)null);
      temporaryInventory.copyInventory(player.inventory);
      boolean canCraft = true;
      Iterator engines = type.driveableRecipe.iterator();

      int numEnginesAcquired;
      ItemStack driveableStack;
      while(engines.hasNext()) {
         ItemStack bestEngineSpeed = (ItemStack)engines.next();
         int bestEngineStack = 0;

         for(numEnginesAcquired = 0; numEnginesAcquired < player.inventory.getSizeInventory(); ++numEnginesAcquired) {
            driveableStack = player.inventory.getStackInSlot(numEnginesAcquired);
            if(driveableStack != null && driveableStack.getItem() == bestEngineSpeed.getItem() && driveableStack.getItemDamage() == bestEngineSpeed.getItemDamage()) {
               int tags = Math.min(driveableStack.stackSize, bestEngineSpeed.stackSize - bestEngineStack);
               driveableStack.stackSize -= tags;
               if(driveableStack.stackSize <= 0) {
                  driveableStack = null;
               }

               player.inventory.setInventorySlotContents(numEnginesAcquired, driveableStack);
               bestEngineStack += tags;
               if(bestEngineStack == bestEngineSpeed.stackSize) {
                  break;
               }
            }
         }

         if(bestEngineStack < bestEngineSpeed.stackSize) {
            canCraft = false;
            break;
         }
      }

      if(!canCraft) {
         player.inventory.copyInventory(temporaryInventory);
      } else {
         HashMap var15 = new HashMap();

         ItemStack var18;
         for(int var16 = 0; var16 < temporaryInventory.getSizeInventory(); ++var16) {
            var18 = temporaryInventory.getStackInSlot(var16);
            if(var18 != null && var18.getItem() instanceof ItemPart) {
               PartType var19 = ((ItemPart)var18.getItem()).type;
               if(var19.category == 2 && var19.worksWith.contains(EnumType.getFromObject(type))) {
                  if(var15.containsKey(var19)) {
                     ItemStack var10000 = (ItemStack)var15.get(var19);
                     var10000.stackSize += var18.stackSize;
                  } else {
                     var15.put(var19, var18);
                  }
               }
            }
         }

         float var17 = -1.0F;
         var18 = null;
         Iterator var20 = var15.keySet().iterator();

         while(var20.hasNext()) {
            PartType var21 = (PartType)var20.next();
            if(var21.engineSpeed > var17 && ((ItemStack)var15.get(var21)).stackSize >= type.numEngines()) {
               var17 = var21.engineSpeed;
               var18 = (ItemStack)var15.get(var21);
            }
         }

         if(var18 == null) {
            player.inventory.copyInventory(temporaryInventory);
         } else {
            numEnginesAcquired = 0;

            for(int var22 = 0; var22 < player.inventory.getSizeInventory(); ++var22) {
               ItemStack var23 = player.inventory.getStackInSlot(var22);
               if(var23 != null && var23.getItem() == var18.getItem()) {
                  int amountFound = Math.min(var23.stackSize, type.numEngines() - numEnginesAcquired);
                  var23.stackSize -= amountFound;
                  if(var23.stackSize <= 0) {
                     var23 = null;
                  }

                  player.inventory.setInventorySlotContents(var22, var23);
                  numEnginesAcquired += amountFound;
                  if(numEnginesAcquired == type.numEngines()) {
                     break;
                  }
               }
            }

            driveableStack = new ItemStack(type.item);
            NBTTagCompound var24 = new NBTTagCompound();
            var24.setString("Engine", ((ItemPart)var18.getItem()).type.shortName);
            var24.setString("Type", type.shortName);
            EnumDriveablePart[] var25 = EnumDriveablePart.values();
            int var12 = var25.length;

            for(int var13 = 0; var13 < var12; ++var13) {
               EnumDriveablePart part = var25[var13];
               var24.setInteger(part.getShortName() + "_Health", type.health.get(part) == null?0:((CollisionBox)type.health.get(part)).health);
               var24.setBoolean(part.getShortName() + "_Fire", false);
            }

            driveableStack.stackTagCompound = var24;
            if(!player.inventory.addItemStackToInventory(driveableStack)) {
               player.dropPlayerItemWithRandomChoice(driveableStack, false);
            }

         }
      }
   }

   public void repairDriveable(EntityPlayer driver, EntityDriveable driving, DriveablePart part) {
      EnumDriveablePart[] temporaryInventory = part.type.getParents();
      int canRepair = temporaryInventory.length;

      for(int stacksNeeded = 0; stacksNeeded < canRepair; ++stacksNeeded) {
         EnumDriveablePart parent = temporaryInventory[stacksNeeded];
         if(!driving.isPartIntact(parent)) {
            return;
         }
      }

      InventoryPlayer var13 = new InventoryPlayer((EntityPlayer)null);
      var13.copyInventory(driver.inventory);
      boolean var14 = true;
      ArrayList var15 = driving.getDriveableType().getItemsRequired(part, driving.getDriveableData().engine);
      Iterator var16 = var15.iterator();

      while(var16.hasNext()) {
         ItemStack stackNeeded = (ItemStack)var16.next();
         int totalAmountFound = 0;
         int m = 0;

         while(true) {
            if(m < var13.getSizeInventory()) {
               label42: {
                  ItemStack stackInSlot = var13.getStackInSlot(m);
                  if(stackInSlot != null && stackInSlot.getItem() == stackNeeded.getItem() && stackInSlot.getItemDamage() == stackNeeded.getItemDamage()) {
                     int amountFound = Math.min(stackInSlot.stackSize, stackNeeded.stackSize - totalAmountFound);
                     stackInSlot.stackSize -= amountFound;
                     if(stackInSlot.stackSize <= 0) {
                        stackInSlot = null;
                     }

                     var13.setInventorySlotContents(m, stackInSlot);
                     totalAmountFound += amountFound;
                     if(totalAmountFound == stackNeeded.stackSize) {
                        break label42;
                     }
                  }

                  ++m;
                  continue;
               }
            }

            if(totalAmountFound < stackNeeded.stackSize) {
               var14 = false;
            }
            break;
         }
      }

      if(var14) {
         driver.inventory.copyInventory(var13);
         part.health = Math.max(1, part.maxHealth / 10);
         part.onFire = false;
         part.dead = false;
         driving.checkParts();
      }

   }

   public boolean isScreenOpen() {
      return false;
   }

   public boolean isKeyDown(int key) {
      return false;
   }

   public boolean keyDown(int keycode) {
      return false;
   }

   public void buyArmour(String shortName, int piece, ArmourBoxType type) {}

   public void spawnParticle(String p_72869_1_, double p_72869_2_, double p_72869_4_, double p_72869_6_, double p_72869_8_, double p_72869_10_, double p_72869_12_) {}

   public float getMouseSensitivity() {
      return 0.5F;
   }

}
