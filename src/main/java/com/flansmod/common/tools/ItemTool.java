package com.flansmod.common.tools;

import com.flansmod.client.debug.EntityDebugVector;
import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.guns.EntityGrenade;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketFlak;
import com.flansmod.common.tools.EntityParachute;
import com.flansmod.common.tools.ToolType;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemTool extends ItemFood {

   public ToolType type;
   private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
   private static final int RANDOM_STRING_LENGTH = 10;


   public ItemTool(ToolType t) {
      super(t.foodness, false);
      super.maxStackSize = 1;
      this.type = t;
      this.type.item = this;
      this.setMaxDamage(this.type.toolLife);
      if(this.type.foodness == 0) {
         this.setCreativeTab(FlansMod.tabFlanParts);
         if(this.type.remote) {
            this.setCreativeTab(FlansMod.tabFlanGuns);
         }

         if(this.type.healDriveables) {
            this.setCreativeTab(FlansMod.tabFlanDriveables);
         }
      }

      GameRegistry.registerItem(this, this.type.shortName, "flansmod");
   }

   public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean b) {
      if(!this.type.packName.isEmpty()) {
         lines.add(this.type.packName);
      }

      if(this.type.description != null) {
         Collections.addAll(lines, this.type.description.split("_"));
      }

      if(stack.stackTagCompound != null) {
         lines.add(stack.stackTagCompound.getString("key"));
      }

   }

   public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
      itemStack.stackTagCompound = new NBTTagCompound();
      itemStack.stackTagCompound.setString("key", this.generateRandomString());
   }

   @SideOnly(Side.CLIENT)
   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
      return this.type.colour;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister icon) {
      super.itemIcon = icon.registerIcon("FlansMod:" + this.type.iconPath);
   }

   public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
      if(this.type.foodness > 0) {
         super.onItemRightClick(itemstack, world, entityplayer);
      } else {
         if(this.type.parachute) {
            if(EntityParachute.canUseParachute(entityplayer)) {
               if(!world.isRemote) {
                  EntityParachute var19 = new EntityParachute(world, this.type, entityplayer);
                  if(!var19.isDead) {
                     world.spawnEntityInWorld(var19);
                     entityplayer.mountEntity(var19);
                  }
               }

               if(!entityplayer.capabilities.isCreativeMode && this.type.toolLife > 0) {
                  itemstack.setItemDamage(itemstack.getItemDamage() + 1);
               }

               if(this.type.toolLife > 0 && this.type.destroyOnEmpty && itemstack.getItemDamage() == itemstack.getMaxDamage()) {
                  --itemstack.stackSize;
               }
            }

            return itemstack;
         }

         if(this.type.remote) {
            PlayerData cosYaw = PlayerHandler.getPlayerData(entityplayer, world.isRemote?Side.CLIENT:Side.SERVER);
            Iterator sinYaw = cosYaw.remoteExplosives.iterator();

            while(sinYaw.hasNext()) {
               EntityGrenade cosPitch = (EntityGrenade)sinYaw.next();
               if(cosPitch.isDead) {
                  sinYaw.remove();
               }
            }

            if(cosYaw.remoteExplosives.size() > 0) {
               ((EntityGrenade)cosYaw.remoteExplosives.get(0)).detonate();
               if(((EntityGrenade)cosYaw.remoteExplosives.get(0)).detonated) {
                  cosYaw.remoteExplosives.remove(0);
               }

               if(!entityplayer.capabilities.isCreativeMode && this.type.toolLife > 0) {
                  itemstack.setItemDamage(itemstack.getItemDamage() + 1);
               }

               if(this.type.toolLife > 0 && this.type.destroyOnEmpty && itemstack.getItemDamage() == itemstack.getMaxDamage()) {
                  --itemstack.stackSize;
               }

               return itemstack;
            }
         } else {
            float var18 = MathHelper.cos(-entityplayer.rotationYaw * 0.01745329F);
            float var20 = MathHelper.sin(-entityplayer.rotationYaw * 0.01745329F);
            float var21 = -MathHelper.cos(entityplayer.rotationPitch * 0.01745329F);
            float sinPitch = MathHelper.sin(entityplayer.rotationPitch * 0.01745329F);
            double length = -5.0D;
            Vec3 posVec = Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY + 1.62D - (double)entityplayer.yOffset, entityplayer.posZ);
            Vec3 lookVec = posVec.addVector((double)(var20 * var21) * length, (double)sinPitch * length, (double)(var18 * var21) * length);
            if(world.isRemote && FlansMod.DEBUG) {
               world.spawnEntityInWorld(new EntityDebugVector(world, new Vector3f(posVec), new Vector3f(posVec.subtract(lookVec)), 100));
            }

            int i;
            Object obj;
            EntityDriveable driveable;
            DriveablePart part;
            if(this.type.healDriveables) {
               for(i = 0; i < world.loadedEntityList.size(); ++i) {
                  obj = world.loadedEntityList.get(i);
                  if(obj instanceof EntityDriveable) {
                     driveable = (EntityDriveable)obj;
                     part = driveable.raytraceParts(new Vector3f(posVec), Vector3f.sub(new Vector3f(lookVec), new Vector3f(posVec), (Vector3f)null));
                     if(part != null && part.maxHealth > 0 && part.health < part.maxHealth && (this.type.toolLife == 0 || itemstack.getItemDamage() < itemstack.getMaxDamage())) {
                        part.health += this.type.healAmount;
                        if(part.health > part.maxHealth) {
                           part.health = part.maxHealth;
                        }

                        if(!entityplayer.capabilities.isCreativeMode && this.type.toolLife > 0) {
                           itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                        }

                        if(this.type.toolLife > 0 && this.type.destroyOnEmpty && itemstack.getItemDamage() == itemstack.getMaxDamage()) {
                           --itemstack.stackSize;
                        }

                        return itemstack;
                     }
                  }
               }
            }

            if(!world.isRemote && this.type.healPlayers) {
               Object var22 = entityplayer;
               List var23 = world.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(Math.min(posVec.xCoord, lookVec.xCoord), Math.min(posVec.yCoord, lookVec.yCoord), Math.min(posVec.zCoord, lookVec.zCoord), Math.max(posVec.xCoord, lookVec.xCoord), Math.max(posVec.yCoord, lookVec.yCoord), Math.max(posVec.zCoord, lookVec.zCoord)));
               Iterator var24 = var23.iterator();

               while(var24.hasNext()) {
                  Object var25 = var24.next();
                  if(var25 instanceof EntityLivingBase) {
                     EntityLivingBase checkEntity = (EntityLivingBase)var25;
                     if(checkEntity != entityplayer) {
                        MovingObjectPosition hit = checkEntity.boundingBox.calculateIntercept(posVec, lookVec);
                        if(hit != null) {
                           var22 = checkEntity;
                        }
                     }
                  }
               }

               if(var22 != null) {
                  if(itemstack.getItemDamage() >= itemstack.getMaxDamage() && this.type.toolLife > 0) {
                     return itemstack;
                  }

                  ((EntityLivingBase)var22).heal((float)this.type.healAmount);
                  FlansMod.getPacketHandler().sendToAllAround((PacketBase)(new PacketFlak(((Entity)var22).posX, ((Entity)var22).posY, ((Entity)var22).posZ, 5, "heart")), new TargetPoint(((Entity)var22).dimension, ((Entity)var22).posX, ((Entity)var22).posY, ((Entity)var22).posZ, 50.0D));
                  if(!entityplayer.capabilities.isCreativeMode && this.type.toolLife > 0) {
                     itemstack.setItemDamage(itemstack.getItemDamage() + 1);
                  }

                  if(this.type.toolLife > 0 && this.type.destroyOnEmpty && itemstack.getItemDamage() >= itemstack.getMaxDamage()) {
                     --itemstack.stackSize;
                  }
               }
            }

            if(!world.isRemote && this.type.key) {
               for(i = 0; i < world.loadedEntityList.size(); ++i) {
                  obj = world.loadedEntityList.get(i);
                  if(obj instanceof EntityDriveable) {
                     driveable = (EntityDriveable)obj;
                     part = driveable.raytraceParts(new Vector3f(posVec), Vector3f.sub(new Vector3f(lookVec), new Vector3f(posVec), (Vector3f)null));
                     if(part != null && part.maxHealth > 0) {
                        if(part.owner.locked) {
                           if(itemstack.stackTagCompound == null) {
                              itemstack.stackTagCompound = new NBTTagCompound();
                              itemstack.stackTagCompound.setString("key", this.generateRandomString());
                           }

                           part.owner.unlock(itemstack.stackTagCompound.getString("key"), entityplayer);
                        } else if(!part.owner.locked) {
                           if(itemstack.stackTagCompound == null) {
                              itemstack.stackTagCompound = new NBTTagCompound();
                              itemstack.stackTagCompound.setString("key", this.generateRandomString());
                           }

                           part.owner.lock(itemstack.stackTagCompound.getString("key"), entityplayer);
                        }
                     }
                  }
               }
            }
         }
      }

      return itemstack;
   }

   public String generateRandomString() {
      StringBuffer randStr = new StringBuffer();

      for(int i = 0; i < 10; ++i) {
         int number = this.getRandomNumber();
         char ch = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".charAt(number);
         randStr.append(ch);
      }

      return randStr.toString();
   }

   private int getRandomNumber() {
      boolean randomInt = false;
      Random randomGenerator = new Random();
      int randomInt1 = randomGenerator.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".length());
      return randomInt1 - 1 == -1?randomInt1:randomInt1 - 1;
   }

   public String toString() {
      return this.type == null?this.getUnlocalizedName():this.type.name;
   }
}
