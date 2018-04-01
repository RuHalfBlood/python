package com.flansmod.common.teams;

import com.flansmod.common.PlayerHandler;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.ItemShootable;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.teams.TeamsManager;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class EntityGunItem extends EntityItem {

   public List ammoStacks;


   public EntityGunItem(World w) {
      super(w);
   }

   public EntityGunItem(EntityItem entity) {
      super(entity.worldObj, entity.posX, entity.posY, entity.posZ, entity.getEntityItem().copy());
      this.setSize(1.0F, 1.0F);
      this.ammoStacks = new ArrayList();
   }

   public EntityGunItem(World w, double x, double y, double z, ItemStack stack, List stacks) {
      super(w, x, y, z, stack);
      this.setSize(1.0F, 1.0F);
      this.ammoStacks = new ArrayList();
      Iterator var10 = stacks.iterator();

      while(var10.hasNext()) {
         ItemStack ammoStack = (ItemStack)var10.next();
         if(ammoStack != null && ammoStack.getItem() != null && ammoStack.getItem() instanceof ItemBullet) {
            this.ammoStacks.add(ammoStack);
         }
      }

   }

   public EntityGunItem(World w, double x, double y, double z) {
      super(w, x, y, z);
   }

   public boolean canBeCollidedWith() {
      return true;
   }

   protected boolean canTriggerWalking() {
      return true;
   }

   public AxisAlignedBB getBoundingBox() {
      return null;
   }

   public void onUpdate() {
      this.onEntityUpdate();
      if(this.getEntityItem() == null || this.getEntityItem().getItem() == null || !(this.getEntityItem().getItem() instanceof ItemGun)) {
         this.setDead();
      }

      if(!super.worldObj.isRemote && this.ammoStacks == null) {
         this.setDead();
      }

      super.prevPosX = super.posX;
      super.prevPosY = super.posY;
      super.prevPosZ = super.posZ;
      super.motionY -= 0.03999999910593033D;
      this.func_145771_j(super.posX, (super.boundingBox.minY + super.boundingBox.maxY) / 2.0D, super.posZ);
      this.moveEntity(super.motionX, super.motionY, super.motionZ);
      float var2 = 0.98F;
      if(super.onGround) {
         var2 = 0.58800006F;
         Block item = super.worldObj.getBlock(MathHelper.floor_double(super.posX), MathHelper.floor_double(super.boundingBox.minY) - 1, MathHelper.floor_double(super.posZ));
         if(item != null) {
            var2 = item.slipperiness * 0.98F;
         }
      }

      super.motionX *= (double)var2;
      super.motionY *= 0.9800000190734863D;
      super.motionZ *= (double)var2;
      if(super.onGround) {
         super.motionY *= -0.5D;
      }

      ++super.age;
      ItemStack item1 = this.getDataWatcher().getWatchableObjectItemStack(10);
      if(!super.worldObj.isRemote && super.age >= super.lifespan) {
         if(item1 != null) {
            ItemExpireEvent event = new ItemExpireEvent(this, item1.getItem() == null?6000:item1.getItem().getEntityLifespan(item1, super.worldObj));
            if(MinecraftForge.EVENT_BUS.post(event)) {
               super.lifespan += event.extraLife;
            } else {
               this.setDead();
            }
         } else {
            this.setDead();
         }
      }

      if(item1 != null && item1.stackSize <= 0) {
         this.setDead();
      }

      if(super.worldObj.isRemote) {
         this.extinguish();
      }

   }

   public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
      return false;
   }

   public void onCollideWithPlayer(EntityPlayer player) {
      if(!super.worldObj.isRemote && this.ammoStacks != null && this.ammoStacks.size() > 0) {
         for(int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if(stack != null && stack.getItem() != null && stack.getItem() instanceof ItemGun) {
               GunType type = ((ItemGun)stack.getItem()).type;

               for(int j = this.ammoStacks.size() - 1; j >= 0; --j) {
                  ItemStack ammoStack = (ItemStack)this.ammoStacks.get(j);
                  if(type.isAmmo(((ItemShootable)ammoStack.getItem()).type) && player.inventory.addItemStackToInventory(ammoStack)) {
                     FMLCommonHandler.instance().firePlayerItemPickupEvent(player, this);
                     this.playSound("random.pop", 0.2F, ((super.rand.nextFloat() - super.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                     this.ammoStacks.remove(j);
                  }
               }

               if(this.ammoStacks.size() == 0) {
                  this.setDead();
               }
            }
         }
      }

   }

   public boolean interactFirst(EntityPlayer player) {
      if(super.worldObj.isRemote) {
         return true;
      } else {
         EntityItemPickupEvent event = new EntityItemPickupEvent(player, this);
         TeamsManager.getInstance().playerLoot(event);
         if(!event.isCanceled()) {
            ItemStack currentItem = player.getCurrentEquippedItem();
            if(currentItem != null && currentItem.getItem() instanceof ItemGun) {
               GunType gunType = ((ItemGun)currentItem.getItem()).type;
               ArrayList newAmmoStacks = new ArrayList();

               for(int newGunItem = 0; newGunItem < player.inventory.getSizeInventory(); ++newGunItem) {
                  ItemStack stack = player.inventory.getStackInSlot(newGunItem);
                  if(stack != null && stack.getItem() instanceof ItemShootable) {
                     ShootableType stack1 = ((ItemShootable)stack.getItem()).type;
                     if(gunType.isAmmo(stack1)) {
                        newAmmoStacks.add(stack.copy());
                        player.inventory.setInventorySlotContents(newGunItem, (ItemStack)null);
                     }
                  }
               }

               EntityGunItem var9 = new EntityGunItem(super.worldObj, super.posX, super.posY, super.posZ, currentItem.copy(), newAmmoStacks);
               super.worldObj.spawnEntityInWorld(var9);
               player.inventory.setInventorySlotContents(player.inventory.currentItem, this.getEntityItem());
               Iterator var10 = this.ammoStacks.iterator();

               while(var10.hasNext()) {
                  ItemStack var11 = (ItemStack)var10.next();
                  player.inventory.addItemStackToInventory(var11);
               }

               this.setDead();
               PlayerHandler.getPlayerData(player).shootClickDelay = 10;
               PlayerHandler.getPlayerData(player).isShootingRight = false;
               return true;
            }
         }

         return false;
      }
   }

   public boolean canAttackWithItem() {
      return false;
   }

   public boolean isBurning() {
      return false;
   }
}
