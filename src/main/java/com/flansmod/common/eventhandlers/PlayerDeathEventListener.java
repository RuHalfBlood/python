package com.flansmod.common.eventhandlers;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.EntityGrenade;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketKillMessage;
import com.flansmod.common.teams.Team;
import com.flansmod.common.types.InfoType;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class PlayerDeathEventListener {

   public PlayerDeathEventListener() {
      MinecraftForge.EVENT_BUS.register(this);
   }

   @EventHandler
   @SubscribeEvent
   public void PlayerDied(LivingDeathEvent DamageEvent) {
      Entity souceEntity = DamageEvent.source.getSourceOfDamage();
      if(DamageEvent.source.getDamageType().equalsIgnoreCase("explosion") && (souceEntity instanceof EntityGrenade || souceEntity instanceof EntityBullet) && DamageEvent.entityLiving instanceof EntityPlayer) {
         EntityPlayer killer = null;
         EntityPlayer killed = (EntityPlayer)DamageEvent.entityLiving;
         Object info;
         if(souceEntity instanceof EntityGrenade) {
            killer = (EntityPlayer)((EntityGrenade)souceEntity).thrower;
            info = ((EntityGrenade)souceEntity).type;
         } else {
            killer = (EntityPlayer)((EntityBullet)souceEntity).owner;
            info = ((EntityBullet)souceEntity).type;
         }

         Team killerTeam = null;
         Team killedTeam = null;
         killerTeam = PlayerHandler.getPlayerData(killer).team;
         killedTeam = PlayerHandler.getPlayerData(killed).team;
         FlansMod.getPacketHandler().sendToDimension((PacketBase)(new PacketKillMessage(false, (InfoType)info, (killedTeam == null?"f":Character.valueOf(killedTeam.textColour)) + killed.getDisplayName(), (killerTeam == null?"f":Character.valueOf(killedTeam.textColour)) + killer.getDisplayName())), DamageEvent.entityLiving.dimension);
         System.out.println(killer.getDisplayName() + " has killed " + killed.getDisplayName() + " with " + ((InfoType)info).name + ". TickExisted:" + killed.ticksExisted / 20 + " KilledPos(X:" + (int)killed.posX + " Y:" + (int)killed.posY + " Z:" + (int)killed.posZ + ") KillerPos(X:" + (int)killer.posX + " Y:" + (int)killer.posY + " Z:" + (int)killer.posZ + ")");
         if(killed.getCurrentArmor(2) != null) {
            System.out.println("KilledPlayer:" + killed + " wear a " + killed.getCurrentArmor(2).getUnlocalizedName());
         } else {
            System.out.println("KilledPlayer:" + killed + " wear nothing.");
         }

         if(killer.getCurrentArmor(2) != null) {
            System.out.println("Killer:" + killer + " wear a " + killer.getCurrentArmor(2).getUnlocalizedName());
         } else {
            System.out.println("Killer:" + killer + " wear nothing.");
         }

         if(killed.ticksExisted / 20 < FlansMod.noticeSpawnKillTime) {
            System.out.println("Warning! PlayerName:" + killer.getCommandSenderName() + " may do SPAWN KILL. Time:" + killed.ticksExisted / 20 + " " + killed.getCommandSenderName() + " was killed.");
         }
      }

   }
}
