/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 */
package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketHandler;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;

public class PacketRequestDebug
extends PacketBase {
    private boolean debug;

    public PacketRequestDebug() {
    }

    public PacketRequestDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeByte(this.debug ? 1 : 0);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.debug = data.readByte() != 0;
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        if (MinecraftServer.getServer().getConfigurationManager().func_152596_g(playerEntity.getGameProfile())) {
            FlansMod.packetHandler.sendTo(new PacketRequestDebug(true), playerEntity);
        }
    }

    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        FlansMod.DEBUG = this.debug;
    }
}

