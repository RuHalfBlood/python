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
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.network.PacketBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class PacketDriveableGUI
extends PacketBase {
    public int guiID;

    public PacketDriveableGUI() {
    }

    public PacketDriveableGUI(int i) {
        this.guiID = i;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeInt(this.guiID);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.guiID = data.readInt();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        if (playerEntity.ridingEntity != null && playerEntity.ridingEntity instanceof EntitySeat) {
            EntityDriveable d = ((EntitySeat)playerEntity.ridingEntity).driveable;
            switch (this.guiID) {
                case 0: {
                    playerEntity.openGui((Object)FlansMod.INSTANCE, 6, playerEntity.worldObj, d.chunkCoordX, d.chunkCoordY, d.chunkCoordZ);
                    break;
                }
                case 1: {
                    playerEntity.openGui((Object)FlansMod.INSTANCE, 7, playerEntity.worldObj, d.chunkCoordX, d.chunkCoordY, d.chunkCoordZ);
                    break;
                }
                case 2: {
                    playerEntity.openGui((Object)FlansMod.INSTANCE, 8, playerEntity.worldObj, d.chunkCoordX, d.chunkCoordY, d.chunkCoordZ);
                    break;
                }
                case 3: {
                    playerEntity.openGui((Object)FlansMod.INSTANCE, 9, playerEntity.worldObj, d.chunkCoordX, d.chunkCoordY, d.chunkCoordZ);
                    break;
                }
                case 4: {
                    playerEntity.openGui((Object)FlansMod.INSTANCE, 10, playerEntity.worldObj, d.chunkCoordX, d.chunkCoordY, d.chunkCoordZ);
                    break;
                }
                case 5: {
                    playerEntity.openGui((Object)FlansMod.INSTANCE, 12, playerEntity.worldObj, d.chunkCoordX, d.chunkCoordY, d.chunkCoordZ);
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        FlansMod.log("Received GUI open packet on client. Skipping.");
    }
}

