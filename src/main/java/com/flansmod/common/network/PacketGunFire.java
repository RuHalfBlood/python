/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.network.PacketBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PacketGunFire
extends PacketBase {
    public boolean held;
    public boolean left;
    public float yaw;
    public float pitch;

    public PacketGunFire() {
    }

    public PacketGunFire(boolean l, boolean h, float y, float p) {
        this.left = l;
        this.held = h;
        this.yaw = y;
        this.pitch = p;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeBoolean(this.held);
        data.writeBoolean(this.left);
        data.writeFloat(this.yaw);
        data.writeFloat(this.pitch);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.held = data.readBoolean();
        this.left = data.readBoolean();
        this.yaw = data.readFloat();
        this.pitch = data.readFloat();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        ItemStack currentItem = playerEntity.inventory.getCurrentItem();
        if (currentItem != null && currentItem.getItem() != null && currentItem.getItem() instanceof ItemGun) {
            float bkYaw = playerEntity.rotationYaw;
            float bkPitch = playerEntity.rotationPitch;
            playerEntity.rotationYaw = this.yaw;
            playerEntity.rotationPitch = this.pitch;
            ((ItemGun)currentItem.getItem()).onMouseHeld(currentItem, playerEntity.worldObj, playerEntity, this.left, this.held);
            playerEntity.rotationYaw = bkYaw;
            playerEntity.rotationPitch = bkPitch;
        }
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        FlansMod.log("Received gun button packet on client. Skipping.");
        ItemStack currentItem = clientPlayer.inventory.getCurrentItem();
    }
}

