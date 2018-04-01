/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.EnumFireMode;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.network.PacketBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PacketGunMode
extends PacketBase {
    public EnumFireMode mode = EnumFireMode.SEMIAUTO;

    public PacketGunMode() {
    }

    public PacketGunMode(EnumFireMode md) {
        this.mode = md;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeByte(this.mode.ordinal());
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        byte i = data.readByte();
        if (i < EnumFireMode.values().length) {
            this.mode = EnumFireMode.values()[i];
        }
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        ItemStack itemStack = playerEntity.inventory.getCurrentItem();
        if (itemStack != null && itemStack.getItem() instanceof ItemGun) {
            EnumFireMode currentMode;
            ItemGun gun = (ItemGun)itemStack.getItem();
            EnumFireMode nextMode = currentMode = gun.type.getFireMode(itemStack);
            EnumFireMode[] submode = gun.type.submode;
            for (int i = 0; i < submode.length; ++i) {
                if (currentMode != submode[i]) continue;
                nextMode = submode[(i + 1) % submode.length];
                break;
            }
            if (currentMode != nextMode) {
                gun.type.setFireMode(itemStack, nextMode.ordinal());
                FlansMod.getPacketHandler().sendTo(new PacketGunMode(nextMode), playerEntity);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        ItemStack itemStack = clientPlayer.inventory.getCurrentItem();
        if (itemStack != null && itemStack.getItem() instanceof ItemGun) {
            ((ItemGun)itemStack.getItem()).type.setFireMode(itemStack, this.mode.ordinal());
        }
    }
}

