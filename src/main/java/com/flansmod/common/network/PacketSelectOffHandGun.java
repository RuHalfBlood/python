/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketOffHandGunInfo;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketSelectOffHandGun
extends PacketBase {
    public int slot;
    public int entityID;

    public PacketSelectOffHandGun() {
    }

    public PacketSelectOffHandGun(int i) {
        this.slot = i;
    }

    public PacketSelectOffHandGun(EntityPlayer player, int i) {
        this.entityID = player.getEntityId();
        this.slot = i;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeByte(this.slot);
        data.writeInt(this.entityID);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.slot = data.readByte();
        this.entityID = data.readInt();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)playerEntity, Side.SERVER);
        data.offHandGunSlot = this.slot;
        FlansMod.getPacketHandler().sendToAllAround(new PacketOffHandGunInfo(playerEntity, this.slot), playerEntity.posX, playerEntity.posY, playerEntity.posZ, 50.0f, playerEntity.dimension);
    }

    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        FlansMod.log("Received off hand gun select packet on client. Skipping. Did you mean to send a PacketOffHandGunInfo?");
    }
}

