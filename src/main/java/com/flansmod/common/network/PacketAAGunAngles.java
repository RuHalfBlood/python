/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.World
 */
package com.flansmod.common.network;

import com.flansmod.common.guns.EntityAAGun;
import com.flansmod.common.network.PacketBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class PacketAAGunAngles
extends PacketBase {
    public int entityID;
    public float gunYaw;
    public float gunPitch;

    public PacketAAGunAngles() {
    }

    public PacketAAGunAngles(EntityAAGun entity) {
        this.entityID = entity.getEntityId();
        this.gunYaw = entity.gunYaw;
        this.gunPitch = entity.gunPitch;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeInt(this.entityID);
        data.writeFloat(this.gunYaw);
        data.writeFloat(this.gunPitch);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.entityID = data.readInt();
        this.gunYaw = data.readFloat();
        this.gunPitch = data.readFloat();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
    }

    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        Entity entity = clientPlayer.worldObj.getEntityByID(this.entityID);
        if (entity instanceof EntityAAGun) {
            EntityAAGun aa = (EntityAAGun)entity;
            aa.prevGunYaw = aa.gunYaw;
            aa.prevGunPitch = aa.gunPitch;
            aa.gunYaw = this.gunYaw;
            aa.gunPitch = this.gunPitch;
        }
    }
}

