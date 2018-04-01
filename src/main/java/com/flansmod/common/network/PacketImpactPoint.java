/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.network.PacketBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PacketImpactPoint
extends PacketBase {
    public int x;
    public int y;
    public int z;
    public int entityId;

    public PacketImpactPoint() {
    }

    public PacketImpactPoint(int x, int y, int z, int entityId) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.entityId = entityId;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeInt(this.x);
        data.writeInt(this.y);
        data.writeInt(this.z);
        data.writeInt(this.entityId);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.x = data.readInt();
        this.y = data.readInt();
        this.z = data.readInt();
        this.entityId = data.readInt();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        if (playerEntity.getCurrentEquippedItem() != null && playerEntity.getCurrentEquippedItem().getItem() instanceof ItemGun) {
            ItemGun itemGun = (ItemGun)playerEntity.getCurrentEquippedItem().getItem();
            itemGun.impactX = this.x;
            itemGun.impactY = this.y;
            itemGun.impactZ = this.z;
        }
    }

    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        FlansMod.log("Received impactpoint packet on client. Disregarding.");
    }
}

