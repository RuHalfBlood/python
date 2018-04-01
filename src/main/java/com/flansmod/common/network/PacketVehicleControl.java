/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 */
package com.flansmod.common.network;

import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.network.PacketDriveableControl;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class PacketVehicleControl
extends PacketDriveableControl {
    public boolean doors;

    public PacketVehicleControl() {
    }

    public PacketVehicleControl(EntityDriveable driveable) {
        super(driveable);
        EntityVehicle vehicle = (EntityVehicle)driveable;
        this.doors = vehicle.varDoor;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        super.encodeInto(ctx, data);
        data.writeBoolean(this.doors);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        super.decodeInto(ctx, data);
        this.doors = data.readBoolean();
    }

    @Override
    protected void updateDriveable(EntityDriveable driveable, boolean clientSide) {
        super.updateDriveable(driveable, clientSide);
        EntityVehicle vehicle = (EntityVehicle)driveable;
        vehicle.varDoor = this.doors;
    }
}

