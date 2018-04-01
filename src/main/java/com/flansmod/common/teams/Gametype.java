package com.flansmod.common.teams;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.teams.ITeamBase;
import com.flansmod.common.teams.ITeamObject;
import com.flansmod.common.teams.PlayerClass;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.teams.TeamsRound;
import com.flansmod.common.types.InfoType;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.Vec3;

public abstract class Gametype {

   public static HashMap gametypes = new HashMap();
   public static TeamsManager teamsManager = TeamsManager.getInstance();
   public static Random rand = new Random();
   public String name;
   public String shortName;
   public int numTeamsRequired;


   public static Gametype getGametype(String type) {
      return (Gametype)gametypes.get(type);
   }

   public Gametype(String s, String s1, int numTeams) {
      this.name = s;
      this.shortName = s1;
      this.numTeamsRequired = numTeams;
      gametypes.put(this.shortName, this);
   }

   public abstract void roundStart();

   public abstract void roundEnd();

   public abstract void roundCleanup();

   public abstract boolean teamHasWon(Team var1);

   public void tick() {}

   public Team[] getTeamsCanSpawnAs(TeamsRound currentRound, EntityPlayer player) {
      return currentRound.teams;
   }

   public void playerJoined(EntityPlayerMP player) {}

   public void playerRespawned(EntityPlayerMP player) {}

   public void playerQuit(EntityPlayerMP player) {}

   public boolean playerAttacked(EntityPlayerMP player, DamageSource source) {
      return true;
   }

   public void playerKilled(EntityPlayerMP player, DamageSource source) {}

   public void baseAttacked(ITeamBase base, DamageSource source) {}

   public void objectAttacked(ITeamObject object, DamageSource source) {}

   public void baseClickedByPlayer(ITeamBase base, EntityPlayerMP player) {}

   public void objectClickedByPlayer(ITeamObject object, EntityPlayerMP player) {}

   public boolean playerCanLoot(ItemStack stack, InfoType infoType, EntityPlayer player, Team playerTeam) {
      return true;
   }

   public abstract Vec3 getSpawnPoint(EntityPlayerMP var1);

   public boolean setVariable(String variable, String value) {
      return false;
   }

   public abstract void readFromNBT(NBTTagCompound var1);

   public abstract void saveToNBT(NBTTagCompound var1);

   public boolean sortScoreboardByTeam() {
      return true;
   }

   public boolean showZombieScore() {
      return false;
   }

   public boolean playerCanAttack(EntityPlayerMP attacker, Team attackerTeam, EntityPlayerMP victim, Team victimTeam) {
      return true;
   }

   public void entityKilled(Entity entity, DamageSource source) {}

   public void playerChoseTeam(EntityPlayerMP player, Team team, Team newTeam) {}

   public void playerChoseNewClass(EntityPlayerMP player, PlayerClass playerClass) {}

   public void playerDefected(EntityPlayerMP player, Team team, Team newTeam) {}

   public void playerEnteredTheGame(EntityPlayerMP player, Team team, PlayerClass playerClass) {}

   public EntityPlayerMP getPlayer(String username) {
      return MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
   }

   public static PlayerData getPlayerData(EntityPlayerMP player) {
      return PlayerHandler.getPlayerData((EntityPlayer)player);
   }

   public static void sendPacketToPlayer(PacketBase packet, EntityPlayerMP player) {
      FlansMod.getPacketHandler().sendTo(packet, player);
   }

   public static String[] getPlayerNames() {
      return MinecraftServer.getServer().getAllUsernames();
   }

   public static List getPlayers() {
      return MinecraftServer.getServer().getConfigurationManager().playerEntityList;
   }

   public static void givePoints(EntityPlayerMP player, int points) {
      PlayerData data = getPlayerData(player);
      data.score += points;
      if(data.team != null) {
         data.team.score += points;
      }

   }

   public static EntityPlayerMP getPlayerFromDamageSource(DamageSource source) {
      EntityPlayerMP attacker = null;
      if(source instanceof EntityDamageSource && source.getEntity() instanceof EntityPlayerMP) {
         attacker = (EntityPlayerMP)source.getEntity();
      }

      if(source instanceof EntityDamageSourceIndirect && source.getSourceOfDamage() instanceof EntityPlayerMP) {
         attacker = (EntityPlayerMP)source.getSourceOfDamage();
      }

      return attacker;
   }

   public boolean shouldAutobalance() {
      return true;
   }

}
