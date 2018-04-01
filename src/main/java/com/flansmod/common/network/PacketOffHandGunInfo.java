/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.relauncher.Side
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.network.PacketBase;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PacketOffHandGunInfo
extends PacketBase {
    public int entityID;
    public ItemStack gunStack;

    public PacketOffHandGunInfo() {
    }

    public PacketOffHandGunInfo(EntityPlayerMP playerEntity, int slot) {
        this.entityID = playerEntity.getEntityId();
        this.gunStack = slot == 0 ? null : playerEntity.inventory.getStackInSlot(slot - 1);
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeInt(this.entityID);
        ByteBufUtils.writeItemStack((ByteBuf)data, (ItemStack)this.gunStack);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.entityID = data.readInt();
        this.gunStack = ByteBufUtils.readItemStack((ByteBuf)data);
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        FlansMod.log("Received off hand gun info packet on server. Skipping. Did you mean to send a PacketSelectOffHandGun?");
    }

    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        Entity entity = clientPlayer.worldObj.getEntityByID(this.entityID);
        if (entity instanceof EntityPlayer && entity != clientPlayer) {
            PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)entity, Side.CLIENT);
            data.offHandGunStack = this.gunStack;
        }
    }
}

