package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.network.PacketBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketDriveableDamage extends PacketBase {

   public int entityId;
   public short[] health;
   public boolean[] onFire;


   public PacketDriveableDamage() {
      this.health = new short[EnumDriveablePart.values().length];
      this.onFire = new boolean[EnumDriveablePart.values().length];
   }

   public PacketDriveableDamage(EntityDriveable driveable) {
      this.entityId = driveable.getEntityId();
      this.health = new short[EnumDriveablePart.values().length];
      this.onFire = new boolean[EnumDriveablePart.values().length];

      for(int i = 0; i < EnumDriveablePart.values().length; ++i) {
         EnumDriveablePart ep = EnumDriveablePart.values()[i];
         DriveablePart part = (DriveablePart)driveable.getDriveableData().parts.get(ep);
         this.health[i] = (short)part.health;
         this.onFire[i] = part.onFire;
      }

   }

   public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
      data.writeInt(this.entityId);

      for(int i = 0; i < EnumDriveablePart.values().length; ++i) {
         data.writeShort(this.health[i]);
         data.writeBoolean(this.onFire[i]);
      }

   }

   public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
      this.entityId = data.readInt();

      for(int i = 0; i < EnumDriveablePart.values().length; ++i) {
         this.health[i] = data.readShort();
         this.onFire[i] = data.readBoolean();
      }

   }

   public void handleServerSide(EntityPlayerMP playerEntity) {
      FlansMod.log("Driveable damage packet received on server. Skipping.");
   }

   @SideOnly(Side.CLIENT)
   public void handleClientSide(EntityPlayer clientPlayer) {
      EntityDriveable driveable = null;
      Iterator i = clientPlayer.worldObj.loadedEntityList.iterator();

      while(i.hasNext()) {
         Object ep = i.next();
         if(ep instanceof EntityDriveable && ((Entity)ep).getEntityId() == this.entityId) {
            driveable = (EntityDriveable)ep;
            break;
         }
      }

      if(driveable != null) {
         for(int var6 = 0; var6 < EnumDriveablePart.values().length; ++var6) {
            EnumDriveablePart var7 = EnumDriveablePart.values()[var6];
            DriveablePart part = (DriveablePart)driveable.getDriveableData().parts.get(var7);
            part.health = this.health[var6];
            part.onFire = this.onFire[var6];
         }
      }

   }
}
