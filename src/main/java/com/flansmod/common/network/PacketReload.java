package com.flansmod.common.network;

import com.flansmod.client.FlansModClient;
import com.flansmod.client.model.GunAnimations;
import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.ItemShootable;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketPlaySound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class PacketReload extends PacketBase {

   public boolean left;


   public PacketReload() {}

   public PacketReload(boolean l) {
      this.left = l;
   }

   public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
      data.writeBoolean(this.left);
   }

   public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
      this.left = data.readBoolean();
   }

   public void handleServerSide(EntityPlayerMP playerEntity) {
      PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)playerEntity);
      ItemStack stack = playerEntity.getCurrentEquippedItem();
      if(this.left && data.offHandGunSlot != 0) {
         stack = playerEntity.inventory.getStackInSlot(data.offHandGunSlot - 1);
      }

      if(data != null && stack != null && stack.getItem() instanceof ItemGun) {
         GunType type = ((ItemGun)stack.getItem()).type;
         boolean empty = true;

         for(int i = 0; i < type.numAmmoItemsInGun; ++i) {
            ItemStack bulletStack = ((ItemGun)stack.getItem()).getBulletItemStack(stack, i);
            if(bulletStack != null && bulletStack.getItem() != null && bulletStack.getItemDamage() < bulletStack.getMaxDamage()) {
               empty = false;
               break;
            }
         }

         if(((ItemGun)stack.getItem()).reload(stack, type, playerEntity.worldObj, playerEntity, true, this.left)) {
            data.shootTimeRight = data.shootTimeLeft = type.reloadTime;
            if(this.left) {
               data.reloadingLeft = true;
            } else {
               data.reloadingRight = true;
            }

            FlansMod.getPacketHandler().sendTo((PacketBase)(new PacketReload(this.left)), playerEntity);
            if(empty && type.reloadSoundOnEmpty != null) {
               PacketPlaySound.sendSoundPacket(playerEntity.posX, playerEntity.posY, playerEntity.posZ, (double)type.reloadSoundRange, playerEntity.dimension, type.reloadSoundOnEmpty, true);
            } else if(type.reloadSound != null) {
               PacketPlaySound.sendSoundPacket(playerEntity.posX, playerEntity.posY, playerEntity.posZ, 50.0D, playerEntity.dimension, type.reloadSound, false);
            }
         }
      }

   }

   @SideOnly(Side.CLIENT)
   public void handleClientSide(EntityPlayer clientPlayer) {
      ItemStack stack = clientPlayer.getCurrentEquippedItem();
      PlayerData data = PlayerHandler.getPlayerData(clientPlayer, Side.CLIENT);
      if(this.left) {
         stack = clientPlayer.inventory.getStackInSlot(data.offHandGunSlot - 1);
      }

      if(stack != null && stack.getItem() instanceof ItemGun) {
         GunType type = ((ItemGun)stack.getItem()).type;
         if(this.left) {
            FlansModClient.shootTimeLeft = type.reloadTime;
         } else {
            FlansModClient.shootTimeRight = type.reloadTime;
         }

         GunAnimations animations = null;
         if(this.left) {
            if(FlansMod.gunAnimationsLeft.containsKey(clientPlayer)) {
               animations = (GunAnimations)FlansMod.gunAnimationsLeft.get(clientPlayer);
            } else {
               animations = new GunAnimations();
               FlansMod.gunAnimationsLeft.put(clientPlayer, animations);
            }
         } else if(FlansMod.gunAnimationsRight.containsKey(clientPlayer)) {
            animations = (GunAnimations)FlansMod.gunAnimationsRight.get(clientPlayer);
         } else {
            animations = new GunAnimations();
            FlansMod.gunAnimationsRight.put(clientPlayer, animations);
         }

         int pumpDelay = type.model == null?0:type.model.pumpDelayAfterReload;
         int pumpTime = type.model == null?1:type.model.pumpTime;
         animations.doReload(type.reloadTime, pumpDelay, pumpTime);
         int bestSlot = -1;
         int bulletsInBestSlot = 0;

         for(int newBulletStack = 0; newBulletStack < clientPlayer.inventory.getSizeInventory(); ++newBulletStack) {
            ItemStack newBulletType = clientPlayer.inventory.getStackInSlot(newBulletStack);
            if(newBulletType != null && newBulletType.getItem() instanceof ItemShootable && type.isAmmo(((ItemShootable)((ItemShootable)newBulletType.getItem())).type)) {
               int bulletsInThisSlot = newBulletType.getMaxDamage() - newBulletType.getItemDamage();
               if(bulletsInThisSlot > bulletsInBestSlot) {
                  bestSlot = newBulletStack;
                  bulletsInBestSlot = bulletsInThisSlot;
               }
            }
         }

         if(bestSlot != -1) {
            ItemStack var13 = clientPlayer.inventory.getStackInSlot(bestSlot);
            ShootableType var14 = ((ItemShootable)var13.getItem()).type;
            if(!clientPlayer.capabilities.isCreativeMode) {
               --var13.stackSize;
            }

            if(var13.stackSize <= 0) {
               var13 = null;
            }

            clientPlayer.inventory.setInventorySlotContents(bestSlot, var13);
         }
      }

   }
}
