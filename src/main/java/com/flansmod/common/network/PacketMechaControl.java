/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.item.ItemStack
 */
package com.flansmod.common.network;

import com.flansmod.common.RotatedAxes;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.driveables.mechas.EnumMechaSlotType;
import com.flansmod.common.driveables.mechas.MechaInventory;
import com.flansmod.common.network.PacketDriveableControl;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.item.ItemStack;

public class PacketMechaControl
extends PacketDriveableControl {
    public float legYaw;
    public float legSwing;
    public ItemStack leftStack;
    public ItemStack rightStack;
    public float leftLegUpperAngle;
    public float leftLegLowerAngle;
    public float leftFootAngle;
    public float rightLegUpperAngle;
    public float rightLegLowerAngle;
    public float rightFootAngle;
    public float prevLeftLegUpperAngle;
    public float prevLeftLegLowerAngle;
    public float prevLeftFootAngle;
    public float prevRightLegUpperAngle;
    public float prevRightLegLowerAngle;
    public float prevRightFootAngle;

    public PacketMechaControl() {
    }

    public PacketMechaControl(EntityDriveable driveable) {
        super(driveable);
        EntityMecha mecha = (EntityMecha)driveable;
        this.legYaw = mecha.legAxes.getYaw();
        this.legSwing = mecha.legSwing;
        this.leftStack = mecha.inventory.getStackInSlot(EnumMechaSlotType.leftTool);
        this.rightStack = mecha.inventory.getStackInSlot(EnumMechaSlotType.rightTool);
        this.leftLegUpperAngle = mecha.leftLegUpperAngle;
        this.leftLegLowerAngle = mecha.leftLegLowerAngle;
        this.leftFootAngle = mecha.leftFootAngle;
        this.rightLegUpperAngle = mecha.rightLegUpperAngle;
        this.rightLegLowerAngle = mecha.rightLegLowerAngle;
        this.rightFootAngle = mecha.rightFootAngle;
        this.prevLeftLegUpperAngle = mecha.prevLeftLegUpperAngle;
        this.prevLeftLegLowerAngle = mecha.prevLeftLegLowerAngle;
        this.prevLeftFootAngle = mecha.prevLeftFootAngle;
        this.prevRightLegUpperAngle = mecha.prevRightLegUpperAngle;
        this.prevRightLegLowerAngle = mecha.prevRightLegLowerAngle;
        this.prevRightFootAngle = mecha.prevRightFootAngle;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        super.encodeInto(ctx, data);
        data.writeFloat(this.legYaw);
        data.writeFloat(this.legSwing);
        data.writeFloat(this.leftLegUpperAngle);
        data.writeFloat(this.leftLegLowerAngle);
        data.writeFloat(this.leftFootAngle);
        data.writeFloat(this.rightLegLowerAngle);
        data.writeFloat(this.rightLegUpperAngle);
        data.writeFloat(this.rightFootAngle);
        data.writeFloat(this.prevLeftLegUpperAngle);
        data.writeFloat(this.prevLeftLegLowerAngle);
        data.writeFloat(this.prevLeftFootAngle);
        data.writeFloat(this.prevRightLegLowerAngle);
        data.writeFloat(this.prevRightLegUpperAngle);
        data.writeFloat(this.prevRightFootAngle);
        ByteBufUtils.writeItemStack((ByteBuf)data, (ItemStack)this.leftStack);
        ByteBufUtils.writeItemStack((ByteBuf)data, (ItemStack)this.rightStack);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        super.decodeInto(ctx, data);
        this.legYaw = data.readFloat();
        this.legSwing = data.readFloat();
        this.leftLegUpperAngle = data.readFloat();
        this.leftLegLowerAngle = data.readFloat();
        this.leftFootAngle = data.readFloat();
        this.rightLegUpperAngle = data.readFloat();
        this.rightLegLowerAngle = data.readFloat();
        this.rightFootAngle = data.readFloat();
        this.prevLeftLegUpperAngle = data.readFloat();
        this.prevLeftLegLowerAngle = data.readFloat();
        this.prevLeftFootAngle = data.readFloat();
        this.prevRightLegUpperAngle = data.readFloat();
        this.prevRightLegLowerAngle = data.readFloat();
        this.prevRightFootAngle = data.readFloat();
        this.leftStack = ByteBufUtils.readItemStack((ByteBuf)data);
        this.rightStack = ByteBufUtils.readItemStack((ByteBuf)data);
    }

    @Override
    protected void updateDriveable(EntityDriveable driveable, boolean clientSide) {
        super.updateDriveable(driveable, clientSide);
        EntityMecha mecha = (EntityMecha)driveable;
        mecha.legAxes.setAngles(this.legYaw, 0.0f, 0.0f);
        mecha.legSwing = this.legSwing / 2.0f;
        mecha.setLegAngles(this.leftLegUpperAngle, this.prevLeftLegUpperAngle, this.rightLegUpperAngle, this.prevRightLegUpperAngle, this.leftLegLowerAngle, this.prevLeftLegLowerAngle, this.rightLegLowerAngle, this.prevRightLegLowerAngle, this.leftFootAngle, this.prevLeftFootAngle, this.rightFootAngle, this.prevRightFootAngle);
        if (clientSide) {
            mecha.inventory.setInventorySlotContents(EnumMechaSlotType.leftTool, this.leftStack);
            mecha.inventory.setInventorySlotContents(EnumMechaSlotType.rightTool, this.rightStack);
        }
    }
}

