/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.ResourceLocation
 */
package com.flansmod.common.network;

import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.common.FlansMod;
import com.flansmod.common.network.PacketBase;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class PacketPlaySound
extends PacketBase {
    public static Random rand = new Random();
    public float posX;
    public float posY;
    public float posZ;
    public String sound;
    public boolean distort;
    public boolean silenced;

    public PacketPlaySound() {
    }

    public static void sendSoundPacket(double x, double y, double z, double range, int dimension, String s, boolean distort) {
        PacketPlaySound.sendSoundPacket(x, y, z, range, dimension, s, distort, false);
    }

    public static void sendSoundPacket(double x, double y, double z, double range, int dimension, String s, boolean distort, boolean silenced) {
        if (s != null && !s.isEmpty()) {
            FlansMod.getPacketHandler().sendToAllAround(new PacketPlaySound(x, y, z, s, distort, silenced), x, y, z, (float)range, dimension);
        }
    }

    public PacketPlaySound(double x, double y, double z, String s) {
        this(x, y, z, s, false);
    }

    public PacketPlaySound(double x, double y, double z, String s, boolean distort) {
        this(x, y, z, s, distort, false);
    }

    public PacketPlaySound(double x, double y, double z, String s, boolean distort, boolean silenced) {
        this.posX = (float)x;
        this.posY = (float)y;
        this.posZ = (float)z;
        this.sound = s;
        this.distort = distort;
        this.silenced = silenced;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeFloat(this.posX);
        data.writeFloat(this.posY);
        data.writeFloat(this.posZ);
        this.writeUTF(data, this.sound);
        data.writeBoolean(this.distort);
        data.writeBoolean(this.silenced);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.posX = data.readFloat();
        this.posY = data.readFloat();
        this.posZ = data.readFloat();
        this.sound = this.readUTF(data);
        this.distort = data.readBoolean();
        this.silenced = data.readBoolean();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        FlansMod.log("Received play sound packet on server. Skipping.");
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        FMLClientHandler.instance().getClient().getSoundHandler().playSound((ISound)new PositionedSoundRecord(FlansModResourceHandler.getSound(this.sound), this.silenced ? 5.0f : 10.0f, (this.distort ? 1.0f / (rand.nextFloat() * 0.4f + 0.8f) : 1.0f) * (this.silenced ? 2.0f : 1.0f), this.posX, this.posY, this.posZ));
    }
}

