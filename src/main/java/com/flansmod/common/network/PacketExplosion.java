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
import com.flansmod.common.guns.FlansModExplosion;
import com.flansmod.common.network.PacketBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class PacketExplosion
extends PacketBase {
    public double explosionX;
    public double explosionY;
    public double explosionZ;
    public float explosionSize;

    public PacketExplosion() {
    }

    public PacketExplosion(double x1, double y1, double z1, float s) {
        this.explosionX = x1;
        this.explosionY = y1;
        this.explosionZ = z1;
        this.explosionSize = s;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeDouble(this.explosionX);
        data.writeDouble(this.explosionY);
        data.writeDouble(this.explosionZ);
        data.writeFloat(this.explosionSize);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.explosionX = data.readDouble();
        this.explosionY = data.readDouble();
        this.explosionZ = data.readDouble();
        this.explosionSize = data.readFloat();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        FlansMod.log("Received Explosion packet on server. Disregarding.");
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        FlansModExplosion.clientExplosion(clientPlayer.worldObj, this.explosionSize, this.explosionX, this.explosionY, this.explosionZ);
    }
}

