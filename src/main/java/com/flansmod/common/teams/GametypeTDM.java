package com.flansmod.common.teams;

import com.flansmod.common.PlayerData;
import com.flansmod.common.teams.Gametype;
import com.flansmod.common.teams.ITeamBase;
import com.flansmod.common.teams.ITeamObject;
import com.flansmod.common.teams.Team;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;

public class GametypeTDM extends Gametype {

   public boolean friendlyFire = false;
   public boolean autoBalance = true;
   public int scoreLimit = 25;
   public int newRoundTimer = 0;
   public int time;
   public int autoBalanceInterval = 1200;


   public GametypeTDM() {
      super("Team Deathmatch", "TDM", 2);
   }

   public void roundStart() {}

   public void roundEnd() {}

   public void roundCleanup() {}

   public void tick() {}

   public boolean needAutobalance() {
      return false;
   }

   public void autobalance() {}

   public void playerJoined(EntityPlayerMP player) {}

   public void playerQuit(EntityPlayerMP player) {}

   public boolean playerAttacked(EntityPlayerMP player, DamageSource source) {
      if(getPlayerData(player) != null && getPlayerData(player).team != null) {
         EntityPlayerMP attacker = getPlayerFromDamageSource(source);
         if(attacker != null) {
            if(getPlayerData(attacker) == null || getPlayerData(attacker).team == null) {
               return false;
            }

            if(getPlayerData(player).team == getPlayerData(attacker).team) {
               return this.friendlyFire;
            }
         }

         return getPlayerData(player).team != Team.spectators;
      } else {
         return false;
      }
   }

   public boolean playerCanAttack(EntityPlayerMP attacker, Team attackerTeam, EntityPlayerMP victim, Team victimTeam) {
      return attackerTeam != victimTeam || this.friendlyFire;
   }

   public void playerKilled(EntityPlayerMP player, DamageSource source) {
      EntityPlayerMP attacker = getPlayerFromDamageSource(source);
      if(attacker != null) {
         if(attacker == player) {
            --getPlayerData(player).score;
         } else if(getPlayerData(attacker).team == getPlayerData(player).team) {
            --getPlayerData(attacker).score;
         } else {
            givePoints(attacker, 1);
            ++getPlayerData(attacker).kills;
         }
      } else {
         --getPlayerData(player).score;
      }

      ++getPlayerData(player).deaths;
   }

   public void baseAttacked(ITeamBase base, DamageSource source) {}

   public void objectAttacked(ITeamObject object, DamageSource source) {}

   public void baseClickedByPlayer(ITeamBase base, EntityPlayerMP player) {}

   public void objectClickedByPlayer(ITeamObject object, EntityPlayerMP player) {}

   public Vec3 getSpawnPoint(EntityPlayerMP player) {
      if(Gametype.teamsManager.currentRound == null) {
         return null;
      } else {
         PlayerData data = getPlayerData(player);
         ArrayList validSpawnPoints = new ArrayList();
         if(data.newTeam == null) {
            return null;
         } else {
            ArrayList bases = Gametype.teamsManager.currentRound.map.getBasesPerTeam(Gametype.teamsManager.currentRound.getTeamID(data.newTeam));
            Iterator spawnPoint = bases.iterator();

            while(spawnPoint.hasNext()) {
               ITeamBase base = (ITeamBase)spawnPoint.next();
               if(base.getMap() == Gametype.teamsManager.currentRound.map) {
                  for(int i = 0; i < base.getObjects().size(); ++i) {
                     if(((ITeamObject)base.getObjects().get(i)).isSpawnPoint()) {
                        validSpawnPoints.add(base.getObjects().get(i));
                     }
                  }
               }
            }

            if(validSpawnPoints.size() > 0) {
               ITeamObject var8 = (ITeamObject)validSpawnPoints.get(Gametype.rand.nextInt(validSpawnPoints.size()));
               return Vec3.createVectorHelper(var8.getPosX(), var8.getPosY(), var8.getPosZ());
            } else {
               return null;
            }
         }
      }
   }

   public void playerRespawned(EntityPlayerMP player) {}

   public boolean setVariable(String variable, String value) {
      if(variable.toLowerCase().equals("scorelimit")) {
         this.scoreLimit = Integer.parseInt(value);
         return true;
      } else if(variable.toLowerCase().equals("friendlyfire")) {
         this.friendlyFire = Boolean.parseBoolean(value);
         return true;
      } else if(variable.toLowerCase().equals("autobalance")) {
         this.autoBalance = Boolean.parseBoolean(value);
         return true;
      } else {
         return false;
      }
   }

   public void readFromNBT(NBTTagCompound tags) {
      this.scoreLimit = tags.getInteger("TDMScoreLimit");
      this.friendlyFire = tags.getBoolean("TDMFriendlyFire");
      this.autoBalance = tags.getBoolean("TDMAutoBalance");
   }

   public void saveToNBT(NBTTagCompound tags) {
      tags.setInteger("TDMScoreLimit", this.scoreLimit);
      tags.setBoolean("TDMFriendlyFire", this.friendlyFire);
      tags.setBoolean("TDMAutoBalance", this.autoBalance);
   }

   public boolean sortScoreboardByTeam() {
      return true;
   }

   public boolean teamHasWon(Team team) {
      return Gametype.teamsManager.currentRound != null && team.score == Gametype.teamsManager.currentRound.scoreLimit;
   }
}
