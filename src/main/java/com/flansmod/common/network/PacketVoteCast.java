/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.teams.TeamsRound;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketVoteCast
extends PacketBase {
    public int vote;

    public PacketVoteCast() {
    }

    public PacketVoteCast(int vote) {
        this.vote = vote;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeByte(this.vote);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.vote = data.readByte();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        if (this.vote < 0 || this.vote > TeamsManager.getInstance().voteOptions.length) {
            FlansMod.log("Invalid vote " + this.vote + " from " + playerEntity.getCommandSenderName());
            return;
        }
        PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)playerEntity, Side.SERVER);
        data.vote = this.vote;
    }

    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        FlansMod.log("Received vote cast packet on client. Skipping.");
    }
}

