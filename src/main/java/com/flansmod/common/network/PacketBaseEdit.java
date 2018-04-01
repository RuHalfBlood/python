/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ServerConfigurationManager
 */
package com.flansmod.common.network;

import com.flansmod.client.gui.GuiBaseEditor;
import com.flansmod.common.FlansMod;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.teams.ITeamBase;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.teams.TeamsMap;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;

public class PacketBaseEdit
extends PacketBase {
    public int baseID;
    public String baseName;
    public String[] maps;
    public int mapID;
    public int teamID;
    public boolean destroy;

    public PacketBaseEdit() {
    }

    public PacketBaseEdit(int baseID, String baseName, String[] maps, int mapID, int teamID) {
        this(baseID, baseName, maps, mapID, teamID, false);
    }

    public PacketBaseEdit(int baseID, String baseName, String[] maps, int mapID, int teamID, boolean destroy) {
        this.baseID = baseID;
        this.baseName = baseName;
        this.maps = maps;
        this.mapID = mapID;
        this.teamID = teamID;
        this.destroy = destroy;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        data.writeInt(this.baseID);
        this.writeUTF(data, this.baseName);
        data.writeInt(this.maps.length);
        for (String map : this.maps) {
            this.writeUTF(data, map);
        }
        data.writeInt(this.mapID);
        data.writeByte((int)((byte)this.teamID));
        data.writeBoolean(this.destroy);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
        this.baseID = data.readInt();
        this.baseName = this.readUTF(data);
        int mapsLength = data.readInt();
        this.maps = new String[mapsLength];
        for (int i = 0; i < mapsLength; ++i) {
            this.maps[i] = this.readUTF(data);
        }
        this.mapID = data.readInt();
        this.teamID = data.readByte();
        this.destroy = data.readBoolean();
    }

    @Override
    public void handleServerSide(EntityPlayerMP playerEntity) {
        if (!MinecraftServer.getServer().getConfigurationManager().func_152596_g(playerEntity.getGameProfile())) {
            return;
        }
        ITeamBase base = TeamsManager.getInstance().getBase(this.baseID);
        if (this.destroy) {
            base.destroy();
            return;
        }
        base.setDefaultOwnerID(this.teamID);
        base.setOwnerID(this.teamID);
        if (this.mapID != -1) {
            base.setMapFirstTime(TeamsManager.getInstance().getMapFromFullName(this.maps[this.mapID]));
        }
        base.setName(this.baseName);
        FlansMod.log(playerEntity.getCommandSenderName() + " modified attributes of base " + this.baseID);
    }

    @SideOnly(value=Side.CLIENT)
    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiBaseEditor(this));
    }
}

