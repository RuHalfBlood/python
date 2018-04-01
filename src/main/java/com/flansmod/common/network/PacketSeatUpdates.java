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
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.Seat;
import com.flansmod.common.network.PacketBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class PacketSeatUpdates
extends PacketBase {
    public int entityId;
    public int seatId;
    public float yaw;
    public float pitch;
    public float playerYaw;
    public float playerPitch;
    public boolean playYawSound;
    public boolean playPitchSound;
    public int yawSoundDelay;
    public int pitchSoundDelay;

    public PacketSeatUpdates() {
    }

    public PacketSeatUpdates(EntitySeat seat) {
        this.entityId = seat.driveable.getEntityId();
        this.seatId = seat.seatInfo.id;
        this.yaw = seat.looking.getYaw();
        this.pitch = seat.looking.getPitch();
        this.playerYaw = seat.playerLooking.getYaw();
        this.playerPitch = seat.playerLooking.getPitch();
        this.playYawSound = seat.playYawSound;
        this.playPitchSound = seat.playPitchSound;
        this.yawSoundDelay = seat.yawSoundDelay;
        this.pitchSoundDelay = seat.pitchSoundDelay;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeInt(this.entityId);
        data.writeInt(this.seatId);
        data.writeFloat(this.yaw);
        data.writeFloat(this.pitch);
        data.writeFloat(this.playerYaw);
        data.writeFloat(this.playerPitch);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.entityId = data.readInt();
        this.seatId = data.readInt();
        this.yaw = data.readFloat();
        this.pitch = data.readFloat();
        this.playerYaw = data.readFloat();
        this.playerPitch = data.readFloat();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        EntityDriveable driveable = null;
        for (Object obj : playerEntity.worldObj.loadedEntityList) {
            if (!(obj instanceof EntityDriveable) || ((Entity)obj).getEntityId() != this.entityId) continue;
            driveable = (EntityDriveable)obj;
            break;
        }
        if (driveable != null) {
            driveable.seats[this.seatId].prevLooking = driveable.seats[this.seatId].looking.clone();
            driveable.seats[this.seatId].looking.setAngles(this.yaw, this.pitch, 0.0f);
            driveable.seats[this.seatId].prevPlayerLooking = driveable.seats[this.seatId].playerLooking.clone();
            driveable.seats[this.seatId].playerLooking.setAngles(this.playerYaw, this.playerPitch, 0.0f);
            FlansMod.getPacketHandler().sendToAllAround(this, driveable.posX, driveable.posY, driveable.posZ, 50.0f, driveable.dimension);
        }
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        EntityDriveable driveable = null;
        Object seat = null;
        for (Object obj : clientPlayer.worldObj.loadedEntityList) {
            if (!(obj instanceof EntityDriveable) || ((Entity)obj).getEntityId() != this.entityId) continue;
            driveable = (EntityDriveable)obj;
            break;
        }
        if (driveable != null) {
            if (driveable.seats[this.seatId] == null || driveable.seats[this.seatId].riddenByEntity == clientPlayer) {
                return;
            }
            driveable.seats[this.seatId].prevLooking = driveable.seats[this.seatId].looking.clone();
            driveable.seats[this.seatId].looking.setAngles(this.yaw, this.pitch, 0.0f);
            driveable.seats[this.seatId].prevPlayerLooking = driveable.seats[this.seatId].playerLooking.clone();
            driveable.seats[this.seatId].playerLooking.setAngles(this.playerYaw, this.playerPitch, 0.0f);
        }
    }
}

