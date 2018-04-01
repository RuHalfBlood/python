/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package com.flansmod.common.network;

import com.flansmod.common.CommonProxy;
import com.flansmod.common.FlansMod;
import com.flansmod.common.network.PacketBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketFlashBang
extends PacketBase {
    public int time = 10;

    public PacketFlashBang() {
    }

    public PacketFlashBang(int flashTime) {
        this.time = flashTime;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeInt(this.time);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.time = data.readInt();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        FlansMod.log("Received flashBang packet on server. Disregarding.");
    }

    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        if (FlansMod.proxy.isThePlayer(clientPlayer)) {
            FlansMod.isInFlash = true;
        }
        FlansMod.flashTime = this.time;
    }
}

