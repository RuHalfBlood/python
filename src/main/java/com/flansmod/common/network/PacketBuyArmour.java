/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 */
package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.teams.ArmourBoxType;
import com.flansmod.common.teams.BlockArmourBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;

public class PacketBuyArmour
extends PacketBase {
    public String boxShortName;
    public String armourShortName;
    public int piece;

    public PacketBuyArmour() {
    }

    public PacketBuyArmour(String box, String armour, int i) {
        this.boxShortName = box;
        this.armourShortName = armour;
        this.piece = i;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.writeUTF(data, this.boxShortName);
        this.writeUTF(data, this.armourShortName);
        data.writeByte((int)((byte)this.piece));
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.boxShortName = this.readUTF(data);
        this.armourShortName = this.readUTF(data);
        this.piece = data.readByte();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        ArmourBoxType box = ArmourBoxType.getBox(this.boxShortName);
        box.block.buyArmour(this.armourShortName, this.piece, playerEntity.inventory);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        FlansMod.log("Received armour box purchase packet on client. Skipping.");
    }
}

