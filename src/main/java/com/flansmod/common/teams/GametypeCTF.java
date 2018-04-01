package com.flansmod.common.teams;

import com.flansmod.common.PlayerData;
import com.flansmod.common.teams.EntityFlag;
import com.flansmod.common.teams.EntityFlagpole;
import com.flansmod.common.teams.Gametype;
import com.flansmod.common.teams.ITeamBase;
import com.flansmod.common.teams.ITeamObject;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;

public class GametypeCTF extends Gametype {

   public boolean friendlyFire = false;
   public boolean autoBalance = true;
   public int time;
   public int autoBalanceInterval = 1200;
   public int flagReturnTime = 60;


   public GametypeCTF() {
      super("Capture the Flag", "CTF", 2);
   }

   public void roundStart() {}

   public void roundEnd() {}

   public void roundCleanup() {}

   public void tick() {}

   public void playerQuit(EntityPlayerMP player) {}

   public boolean playerCanAttack(EntityPlayerMP attacker, Team attackerTeam, EntityPlayerMP victim, Team victimTeam) {
      return attackerTeam != victimTeam || this.friendlyFire;
   }

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

   public void playerKilled(EntityPlayerMP player, DamageSource source) {
      EntityPlayerMP attacker = getPlayerFromDamageSource(source);
      if(attacker != null) {
         if(attacker == player) {
            --getPlayerData(player).score;
         } else {
            ++getPlayerData(attacker).score;
            ++getPlayerData(attacker).kills;
         }
      } else {
         --getPlayerData(player).score;
      }

      ++getPlayerData(player).deaths;
      if(player.riddenByEntity instanceof EntityFlag) {
         Team flagTeam = Gametype.teamsManager.getTeam(((EntityFlag)player.riddenByEntity).getBase().getOwnerID());
         player.riddenByEntity.mountEntity((Entity)null);
         TeamsManager.messageAll("§f" + player.getCommandSenderName() + " dropped the §" + flagTeam.textColour + flagTeam.name + "§f flag");
      }

   }

   public void baseAttacked(ITeamBase base, DamageSource source) {}

   public void objectAttacked(ITeamObject object, DamageSource source) {}

   public void baseClickedByPlayer(ITeamBase base, EntityPlayerMP player) {
      if(base instanceof EntityFlagpole && ((EntityFlag)base.getFlag()).isHome) {
         this.objectClickedByPlayer(base.getFlag(), player);
      }

   }

   public void objectClickedByPlayer(ITeamObject object, EntityPlayerMP player) {
      if(Gametype.teamsManager.currentRound != null) {
         if(object instanceof EntityFlag) {
            EntityFlag flag = (EntityFlag)object;
            if(flag.getBase().getOwnerID() > 1) {
               Team playerTeam = getPlayerData(player).team;
               PlayerData playerData = getPlayerData(player);
               Team flagTeam = Gametype.teamsManager.getTeam(flag.getBase().getOwnerID());
               if(playerTeam != null && playerTeam != Team.spectators && flag.getBase().getMap() == Gametype.teamsManager.currentRound.map) {
                  if(playerTeam == flagTeam) {
                     if(flag.ridingEntity == null && !flag.isHome) {
                        flag.reset();
                        playerData.score += 2;
                        TeamsManager.messageAll("§f" + player.getCommandSenderName() + " returned the §" + flagTeam.textColour + flagTeam.name + "§f flag");
                     } else if(player.riddenByEntity instanceof EntityFlag) {
                        EntityFlag otherFlag = (EntityFlag)player.riddenByEntity;
                        Team otherFlagTeam = Gametype.teamsManager.getTeam(otherFlag.getBase().getOwnerID());
                        if(otherFlagTeam != null && otherFlagTeam != Team.spectators && otherFlagTeam != flagTeam && flag.isHome) {
                           ++playerTeam.score;
                           playerData.score += 10;
                           otherFlag.reset();
                           TeamsManager.messageAll("§f" + player.getCommandSenderName() + " captured the §" + otherFlagTeam.textColour + otherFlagTeam.name + "§f flag");
                        }
                     }
                  } else if(flag.ridingEntity == player) {
                     flag.mountEntity((Entity)null);
                     TeamsManager.messageAll("§f" + player.getCommandSenderName() + " dropped the §" + flagTeam.textColour + flagTeam.name + "§f flag");
                  } else if(flag.ridingEntity == null) {
                     if(flag.isHome) {
                        playerData.score += 3;
                     }

                     flag.mountEntity(player);
                     TeamsManager.messageAll("§f" + player.getCommandSenderName() + " picked up the §" + flagTeam.textColour + flagTeam.name + "§f flag");
                     flag.isHome = false;
                  }
               }
            }
         }

      }
   }

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
      if(variable.toLowerCase().equals("friendlyfire")) {
         this.friendlyFire = Boolean.parseBoolean(value);
         return true;
      } else if(variable.toLowerCase().equals("autobalance")) {
         this.autoBalance = Boolean.parseBoolean(value);
         return true;
      } else if(variable.toLowerCase().equals("flagtime")) {
         this.flagReturnTime = Integer.parseInt(value);
         return true;
      } else {
         return false;
      }
   }

   public void readFromNBT(NBTTagCompound tags) {
      this.friendlyFire = tags.getBoolean("CTFFriendlyFire");
      this.autoBalance = tags.getBoolean("CTFAutoBalance");
      this.flagReturnTime = tags.getInteger("CTFFlagTime");
   }

   public void saveToNBT(NBTTagCompound tags) {
      tags.setBoolean("CTFFriendlyFire", this.friendlyFire);
      tags.setBoolean("CTFAutoBalance", this.autoBalance);
      tags.setInteger("CTFFlagTime", this.flagReturnTime);
   }

   public boolean sortScoreboardByTeam() {
      return true;
   }

   public boolean teamHasWon(Team team) {
      return Gametype.teamsManager.currentRound != null && team.score == Gametype.teamsManager.currentRound.scoreLimit;
   }
}
