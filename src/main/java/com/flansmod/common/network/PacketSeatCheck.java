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
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 */
package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.network.PacketBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.io.PrintStream;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class PacketSeatCheck
extends PacketBase {
    public int entityId;
    public int checkCount;

    public PacketSeatCheck() {
    }

    public PacketSeatCheck(EntitySeat seat) {
        this.entityId = seat.getEntityId();
        this.checkCount = 3;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeInt(this.entityId);
        data.writeInt(this.checkCount);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.entityId = data.readInt();
        this.checkCount = data.readInt();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        this.log("handleServerSide", (EntityPlayer)playerEntity);
        if (this.checkCount <= 0) {
            ChatComponentText cct1 = new ChatComponentText("[FlansMod] " + playerEntity.getDisplayName() + " was recovering from a fall. id=" + this.entityId);
            cct1.getChatStyle().setColor(EnumChatFormatting.YELLOW);
            ChatComponentText cct2 = new ChatComponentText("[FlansMod]================================================");
            cct2.getChatStyle().setColor(EnumChatFormatting.RED);
        } else {
            this.entityId = playerEntity.ridingEntity instanceof EntitySeat ? playerEntity.ridingEntity.getEntityId() : -1;
            FlansMod.getPacketHandler().sendTo(this, playerEntity);
        }
    }

    private void log(String s, EntityPlayer player) {
        Object re;
        System.out.println(s + " :" + player.getDisplayName() + " : rideEntity=" + ((re = player.ridingEntity) != null ? re.getClass().getName() : re) + " : seatEntityId=" + this.entityId + " : check=" + this.checkCount);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        this.log("handleClientSide", clientPlayer);
        if (clientPlayer.ridingEntity == null && this.entityId != -1) {
            if (this.checkCount > 1) {
                --this.checkCount;
            } else {
                --this.checkCount;
                Entity entity = clientPlayer.worldObj.getEntityByID(this.entityId);
                if (entity instanceof EntitySeat) {
                    System.out.println("mount seat :" + clientPlayer.getDisplayName() + " : seatEntityId=" + this.entityId + " : check=" + this.checkCount);
                    clientPlayer.mountEntity(entity);
                }
            }
            FlansMod.getPacketHandler().sendToServer(this);
        }
    }
}

