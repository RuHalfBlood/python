package com.flansmod.common;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.EntityDamageSourceGun;
import com.flansmod.common.guns.EntityGrenade;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketRequestDebug;
import com.flansmod.common.teams.TeamsManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PlayerHandler {

   private static final Random rand = new Random();
   public static Map serverSideData = new HashMap();
   public static Map clientSideData = new HashMap();
   public static ArrayList clientsToRemoveAfterThisRound = new ArrayList();


   public PlayerHandler() {
      MinecraftForge.EVENT_BUS.register(this);
      FMLCommonHandler.instance().bus().register(this);
   }

   @SubscribeEvent
   public void onEntityHurt(LivingAttackEvent event) {
      EntityLivingBase entity = event.entityLiving;
     //Дамаг нету если в машине
     /* if(event instanceof LivingAttackEvent && (entity.ridingEntity instanceof EntityDriveable || entity.ridingEntity instanceof EntitySeat)) {
         event.setCanceled(true);
      }*/

   }

   @SubscribeEvent
   public void onLivingHurtEvent(LivingHurtEvent event) {
      float damage = event.ammount;
      if(damage > 0.0F && event.source instanceof EntityDamageSourceGun) {
         EntityDamageSourceGun source = (EntityDamageSourceGun)event.source;
         Object shootableType = null;
         Entity damageSouceEntity = source.getDamageSourceEntity();
         if(damageSouceEntity instanceof EntityBullet) {
            shootableType = ((EntityBullet)damageSouceEntity).type;
         }

         if(damageSouceEntity instanceof EntityGrenade) {
            shootableType = ((EntityGrenade)damageSouceEntity).type;
         }

         if(shootableType != null && ((ShootableType)shootableType).ignoreArmorProbability > 0.0F && rand.nextFloat() < ((ShootableType)shootableType).ignoreArmorProbability) {
            EntityLivingBase entity = event.entityLiving;
            float f1 = damage;
            damage = Math.max(damage - entity.getAbsorptionAmount(), 0.0F);
            entity.setAbsorptionAmount(entity.getAbsorptionAmount() - (f1 - damage));
            damage *= ((ShootableType)shootableType).ignoreArmorDamageFactor;
            if(damage != 0.0F) {
               float health = entity.getHealth();
               entity.setHealth(health - damage);
               entity.func_110142_aN().func_94547_a(source, health, damage);
               entity.setAbsorptionAmount(entity.getAbsorptionAmount() - damage);
            }

            event.setCanceled(true);
         }
      }

   }

   @SubscribeEvent
   public void onEntityKilled(LivingDeathEvent event) {
      EntityLivingBase entity = event.entityLiving;
      if(entity instanceof EntityPlayer) {
         getPlayerData((EntityPlayer)entity).playerKilled();
      }

   }

   public void serverTick() {
      WorldServer[] var1 = MinecraftServer.getServer().worldServers;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         WorldServer world = var1[var3];
         Iterator var5 = world.playerEntities.iterator();

         while(var5.hasNext()) {
            Object player = var5.next();
            getPlayerData((EntityPlayer)player).tick((EntityPlayer)player);
         }
      }

   }

   public void clientTick() {
      if(Minecraft.getMinecraft().theWorld != null) {
         Iterator var1 = Minecraft.getMinecraft().theWorld.playerEntities.iterator();

         while(var1.hasNext()) {
            Object player = var1.next();
            getPlayerData((EntityPlayer)player).tick((EntityPlayer)player);
         }
      }

   }

   public static PlayerData getPlayerData(EntityPlayer player) {
      return player == null?null:getPlayerData(player.getCommandSenderName(), player.worldObj.isRemote?Side.CLIENT:Side.SERVER);
   }

   public static PlayerData getPlayerData(String username) {
      return getPlayerData(username, Side.SERVER);
   }

   public static PlayerData getPlayerData(EntityPlayer player, Side side) {
      return player == null?null:getPlayerData(player.getCommandSenderName(), side);
   }

   public static PlayerData getPlayerData(String username, Side side) {
      if(side.isClient()) {
         if(!clientSideData.containsKey(username)) {
            clientSideData.put(username, new PlayerData(username));
         }
      } else if(!serverSideData.containsKey(username)) {
         serverSideData.put(username, new PlayerData(username));
      }

      return side.isClient()?(PlayerData)clientSideData.get(username):(PlayerData)serverSideData.get(username);
   }

   @SubscribeEvent
   public void onPlayerEvent(PlayerEvent event) {
      EntityPlayer player;
      String username;
      if(event instanceof PlayerLoggedInEvent) {
         if(event.player instanceof EntityPlayerMP) {
            FlansMod.packetHandler.sendTo((PacketBase)(new PacketRequestDebug(false)), (EntityPlayerMP)event.player);
         }

         player = event.player;
         username = player.getCommandSenderName();
         if(!serverSideData.containsKey(username)) {
            serverSideData.put(username, new PlayerData(username));
         }

         if(clientsToRemoveAfterThisRound.contains(username)) {
            clientsToRemoveAfterThisRound.remove(username);
         }
      } else if(event instanceof PlayerLoggedOutEvent) {
         player = event.player;
         username = player.getCommandSenderName();
         if(TeamsManager.getInstance().currentRound == null) {
            serverSideData.remove(username);
         } else {
            clientsToRemoveAfterThisRound.add(username);
         }
      } else if(event instanceof PlayerRespawnEvent) {
         player = event.player;
         username = player.getCommandSenderName();
         if(!serverSideData.containsKey(username)) {
            serverSideData.put(username, new PlayerData(username));
         }
      }

   }

   public static void roundEnded() {
      Iterator var0 = clientsToRemoveAfterThisRound.iterator();

      while(var0.hasNext()) {
         String username = (String)var0.next();
         serverSideData.remove(username);
      }

   }

}
