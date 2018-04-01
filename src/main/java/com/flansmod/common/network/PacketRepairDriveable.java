package com.flansmod.common.network;

import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.DriveablePart;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EnumDriveablePart;
import com.flansmod.common.network.PacketBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketRepairDriveable extends PacketBase {

   public String shortName;


   public PacketRepairDriveable() {}

   public PacketRepairDriveable(EnumDriveablePart part) {
      this.shortName = part.getShortName();
   }

   public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
      this.writeUTF(data, this.shortName);
   }

   public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
      this.shortName = this.readUTF(data);
   }

   public void handleServerSide(EntityPlayerMP playerEntity) {
      EnumDriveablePart part = EnumDriveablePart.getPart(this.shortName);
      FlansMod.proxy.repairDriveable(playerEntity, ((EntitySeat)playerEntity.ridingEntity).driveable, (DriveablePart)((EntitySeat)playerEntity.ridingEntity).driveable.getDriveableData().parts.get(part));
   }

   @SideOnly(Side.CLIENT)
   public void handleClientSide(EntityPlayer clientPlayer) {
      FlansMod.log("Received driveable repair packet on client side. Skipping.");
   }
}
