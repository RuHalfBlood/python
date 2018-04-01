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
 *  net.minecraft.world.World
 */
package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.EntityMG;
import com.flansmod.common.network.PacketBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class PacketMGMount
extends PacketBase {
    public int playerEntityId;
    public int mgEntityId;
    public boolean mounting;

    public PacketMGMount() {
    }

    public PacketMGMount(EntityPlayer player, EntityMG mg, boolean mounting) {
        this.playerEntityId = player.getEntityId();
        this.mgEntityId = mg.getEntityId();
        this.mounting = mounting;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeInt(this.playerEntityId);
        data.writeInt(this.mgEntityId);
        data.writeBoolean(this.mounting);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.playerEntityId = data.readInt();
        this.mgEntityId = data.readInt();
        this.mounting = data.readBoolean();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        FlansMod.log("Received MG mount packet on server. Skipping.");
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        EntityPlayer player = (EntityPlayer)clientPlayer.worldObj.getEntityByID(this.playerEntityId);
        EntityMG mg = (EntityMG)clientPlayer.worldObj.getEntityByID(this.mgEntityId);
        if (mg != null && player != null) {
            mg.mountGun(player, this.mounting);
        }
    }
}

