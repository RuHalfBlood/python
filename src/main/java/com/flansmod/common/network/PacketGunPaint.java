/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 */
package com.flansmod.common.network;

import com.flansmod.common.guns.ContainerGunModTable;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.paintjob.ContainerPaintjobTable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;

public class PacketGunPaint
extends PacketBase {
    private int paintjobID;

    public PacketGunPaint() {
    }

    public PacketGunPaint(int i) {
        this.paintjobID = i;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeInt(this.paintjobID);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.paintjobID = data.readInt();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        if (playerEntity.openContainer instanceof ContainerGunModTable) {
            ContainerGunModTable gunModTable = (ContainerGunModTable)playerEntity.openContainer;
            gunModTable.clickPaintjob(this.paintjobID);
        } else if (playerEntity.openContainer instanceof ContainerPaintjobTable) {
            ContainerPaintjobTable paintjobTable = (ContainerPaintjobTable)playerEntity.openContainer;
            paintjobTable.clickPaintjob(this.paintjobID);
        }
    }

    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
    }
}

