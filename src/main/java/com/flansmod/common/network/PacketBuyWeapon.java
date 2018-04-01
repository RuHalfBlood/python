/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 */
package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.boxes.BlockGunBox;
import com.flansmod.common.guns.boxes.GunBoxType;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.types.InfoType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;

public class PacketBuyWeapon
extends PacketBase {
    public String boxShortName;
    public int purchaseType;
    public int weaponID;

    public PacketBuyWeapon() {
    }

    public PacketBuyWeapon(GunBoxType box, int type, int wepID) {
        this.boxShortName = box.shortName;
        this.purchaseType = type;
        this.weaponID = wepID;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.writeUTF(data, this.boxShortName);
        data.writeInt(this.purchaseType);
        data.writeInt(this.weaponID);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.boxShortName = this.readUTF(data);
        this.purchaseType = data.readInt();
        this.weaponID = data.readInt();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        GunBoxType box = GunBoxType.getBox(this.boxShortName);
        box.block.purchaseItem(this.purchaseType, this.weaponID, playerEntity.inventory, box);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        FlansMod.log("Received gun box purchase packet on client. Skipping.");
    }
}

