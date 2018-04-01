/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.network.FMLEmbeddedChannel
 *  cpw.mods.fml.common.network.FMLOutboundHandler
 *  cpw.mods.fml.common.network.FMLOutboundHandler$OutboundTarget
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.internal.FMLProxyPacket
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelHandler$Sharable
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.handler.codec.MessageToMessageCodec
 *  io.netty.util.Attribute
 *  io.netty.util.AttributeKey
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.INetHandler
 *  net.minecraft.network.NetHandlerPlayServer
 *  net.minecraft.network.Packet
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 */
package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.network.PacketAAGunAngles;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketBaseEdit;
import com.flansmod.common.network.PacketBreakSound;
import com.flansmod.common.network.PacketBuyArmour;
import com.flansmod.common.network.PacketBuyWeapon;
import com.flansmod.common.network.PacketCraftDriveable;
import com.flansmod.common.network.PacketDriveableControl;
import com.flansmod.common.network.PacketDriveableDamage;
import com.flansmod.common.network.PacketDriveableGUI;
import com.flansmod.common.network.PacketDriveableKey;
import com.flansmod.common.network.PacketDriveableKeyHeld;
import com.flansmod.common.network.PacketExplosion;
import com.flansmod.common.network.PacketFlak;
import com.flansmod.common.network.PacketFlashBang;
import com.flansmod.common.network.PacketGunFire;
import com.flansmod.common.network.PacketGunMode;
import com.flansmod.common.network.PacketGunPaint;
import com.flansmod.common.network.PacketGunSpread;
import com.flansmod.common.network.PacketImpactPoint;
import com.flansmod.common.network.PacketKillMessage;
import com.flansmod.common.network.PacketMGFire;
import com.flansmod.common.network.PacketMGMount;
import com.flansmod.common.network.PacketMechaControl;
import com.flansmod.common.network.PacketOffHandGunInfo;
import com.flansmod.common.network.PacketParticle;
import com.flansmod.common.network.PacketPlaneControl;
import com.flansmod.common.network.PacketPlaySound;
import com.flansmod.common.network.PacketReload;
import com.flansmod.common.network.PacketRepairDriveable;
import com.flansmod.common.network.PacketRequestDebug;
import com.flansmod.common.network.PacketRoundFinished;
import com.flansmod.common.network.PacketSeatCheck;
import com.flansmod.common.network.PacketSeatUpdates;
import com.flansmod.common.network.PacketSelectOffHandGun;
import com.flansmod.common.network.PacketTeamInfo;
import com.flansmod.common.network.PacketTeamSelect;
import com.flansmod.common.network.PacketVehicleControl;
import com.flansmod.common.network.PacketVoteCast;
import com.flansmod.common.network.PacketVoting;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;

@ChannelHandler.Sharable
public class PacketHandler
extends MessageToMessageCodec<FMLProxyPacket, PacketBase> {
    private EnumMap<Side, FMLEmbeddedChannel> channels;
    private LinkedList<Class<? extends PacketBase>> packets = new LinkedList();
    private boolean modInitialised = false;

    public boolean registerPacket(Class<? extends PacketBase> cl) {
        if (this.packets.size() > 256) {
            FlansMod.log("Packet limit exceeded in Flan's Mod packet handler by packet " + cl.getCanonicalName() + ".");
            return false;
        }
        if (this.packets.contains(cl)) {
            FlansMod.log("Tried to register " + cl.getCanonicalName() + " packet class twice.");
            return false;
        }
        if (this.modInitialised) {
            FlansMod.log("Tried to register packet " + cl.getCanonicalName() + " after mod initialisation.");
            return false;
        }
        this.packets.add(cl);
        return true;
    }

    protected void encode(ChannelHandlerContext ctx, PacketBase msg, List<Object> out) throws Exception {
        ByteBuf encodedData = Unpooled.buffer();
        Class cl = msg.getClass();
        if (!this.packets.contains(cl)) {
            throw new NullPointerException("Packet not registered : " + cl.getCanonicalName());
        }
        byte discriminator = (byte)this.packets.indexOf(cl);
        encodedData.writeByte((int)discriminator);
        msg.encodeInto(ctx, encodedData);
        FMLProxyPacket proxyPacket = new FMLProxyPacket(encodedData.copy(), (String)ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
        out.add((Object)proxyPacket);
    }

    protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {
        ByteBuf encodedData = msg.payload();
        byte discriminator = encodedData.readByte();
        Class<? extends PacketBase> cl = this.packets.get(discriminator);
        if (cl == null) {
            throw new NullPointerException("Packet not registered for discriminator : " + discriminator);
        }
        PacketBase packet = cl.newInstance();
        packet.decodeInto(ctx, encodedData.slice());
        switch (FMLCommonHandler.instance().getEffectiveSide()) {
            case CLIENT: {
                packet.handleClientSide(this.getClientPlayer());
                break;
            }
            case SERVER: {
                INetHandler netHandler = (INetHandler)ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
                packet.handleServerSide(((NetHandlerPlayServer)netHandler).playerEntity);
                break;
            }
        }
    }

    public void initialise() {
        this.channels = NetworkRegistry.INSTANCE.newChannel("FlansMod", new ChannelHandler[]{this});
        this.registerPacket(PacketAAGunAngles.class);
        this.registerPacket(PacketBaseEdit.class);
        this.registerPacket(PacketBreakSound.class);
        this.registerPacket(PacketBuyArmour.class);
        this.registerPacket(PacketBuyWeapon.class);
        this.registerPacket(PacketCraftDriveable.class);
        this.registerPacket(PacketDriveableControl.class);
        this.registerPacket(PacketDriveableDamage.class);
        this.registerPacket(PacketDriveableGUI.class);
        this.registerPacket(PacketDriveableKey.class);
        this.registerPacket(PacketDriveableKeyHeld.class);
        this.registerPacket(PacketFlak.class);
        this.registerPacket(PacketExplosion.class);
        this.registerPacket(PacketGunFire.class);
        this.registerPacket(PacketGunMode.class);
        this.registerPacket(PacketGunPaint.class);
        this.registerPacket(PacketGunSpread.class);
        this.registerPacket(PacketKillMessage.class);
        this.registerPacket(PacketMechaControl.class);
        this.registerPacket(PacketMGFire.class);
        this.registerPacket(PacketMGMount.class);
        this.registerPacket(PacketOffHandGunInfo.class);
        this.registerPacket(PacketParticle.class);
    
        this.registerPacket(PacketPlaneControl.class);
        this.registerPacket(PacketPlaySound.class);
        this.registerPacket(PacketReload.class);
        this.registerPacket(PacketRepairDriveable.class);
        this.registerPacket(PacketRoundFinished.class);
        this.registerPacket(PacketSeatUpdates.class);
        this.registerPacket(PacketSeatCheck.class);
        this.registerPacket(PacketSelectOffHandGun.class);
        this.registerPacket(PacketTeamInfo.class);
        this.registerPacket(PacketTeamSelect.class);
        this.registerPacket(PacketVehicleControl.class);
        this.registerPacket(PacketVoteCast.class);
        this.registerPacket(PacketVoting.class);
        this.registerPacket(PacketRequestDebug.class);
        this.registerPacket(PacketFlashBang.class);
        this.registerPacket(PacketImpactPoint.class);
 
    }

    public void postInitialise() {
        if (this.modInitialised) {
            return;
        }
        this.modInitialised = true;
        Collections.sort(this.packets, new Comparator<Class<? extends PacketBase>>(){

            @Override
            public int compare(Class<? extends PacketBase> c1, Class<? extends PacketBase> c2) {
                int com = String.CASE_INSENSITIVE_ORDER.compare(c1.getCanonicalName(), c2.getCanonicalName());
                if (com == 0) {
                    com = c1.getCanonicalName().compareTo(c2.getCanonicalName());
                }
                return com;
            }
        });
    }

    @SideOnly(value=Side.CLIENT)
    private EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    public void sendToAll(PacketBase packet) {
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set((OutboundTarget)FMLOutboundHandler.OutboundTarget.ALL);
        this.channels.get((Object)Side.SERVER).writeAndFlush((Object)packet);
    }

    public void sendTo(PacketBase packet, EntityPlayerMP player) {
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set((OutboundTarget)FMLOutboundHandler.OutboundTarget.PLAYER);
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set((Object)player);
        this.channels.get((Object)Side.SERVER).writeAndFlush((Object)packet);
    }

    public void sendToAllAround(PacketBase packet, NetworkRegistry.TargetPoint point) {
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set((OutboundTarget)FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set((Object)point);
        this.channels.get((Object)Side.SERVER).writeAndFlush((Object)packet);
    }

    public void sendToDimension(PacketBase packet, int dimensionID) {
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set((OutboundTarget)FMLOutboundHandler.OutboundTarget.DIMENSION);
        this.channels.get((Object)Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set((Object)dimensionID);
        this.channels.get((Object)Side.SERVER).writeAndFlush((Object)packet);
    }

    public void sendToServer(PacketBase packet) {
        this.channels.get((Object)Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set((OutboundTarget)FMLOutboundHandler.OutboundTarget.TOSERVER);
        this.channels.get((Object)Side.CLIENT).writeAndFlush((Object)packet);
    }

    public void sendToAll(Packet packet) {
        MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);
    }

    public void sendTo(Packet packet, EntityPlayerMP player) {
        player.playerNetServerHandler.sendPacket(packet);
    }

    public void sendToAllAround(Packet packet, NetworkRegistry.TargetPoint point) {
        MinecraftServer.getServer().getConfigurationManager().sendToAllNear(point.x, point.y, point.z, point.range, point.dimension, packet);
    }

    public void sendToDimension(Packet packet, int dimensionID) {
        MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayersInDimension(packet, dimensionID);
    }

    public void sendToServer(Packet packet) {
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(packet);
    }

    public void sendToAllAround(PacketBase packet, double x, double y, double z, float range, int dimension) {
        this.sendToAllAround(packet, new NetworkRegistry.TargetPoint(dimension, x, y, z, (double)range));
    }

}

