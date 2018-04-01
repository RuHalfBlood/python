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
 */
package com.flansmod.common.network;

import com.flansmod.common.CommonProxy;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.network.PacketBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketCraftDriveable
extends PacketBase {
    public String shortName;

    public PacketCraftDriveable() {
    }

    public PacketCraftDriveable(String s) {
        this.shortName = s;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.writeUTF(data, this.shortName);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.shortName = this.readUTF(data);
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        DriveableType type = DriveableType.getDriveable(this.shortName);
        FlansMod.proxy.craftDriveable((EntityPlayer)playerEntity, type);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        FlansMod.log("Received driveable repair packet on client side. Skipping.");
    }
}

