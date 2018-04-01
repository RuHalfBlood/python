package com.flansmod.common.teams;

import com.flansmod.common.PlayerData;
import com.flansmod.common.teams.Gametype;
import com.flansmod.common.teams.ITeamBase;
import com.flansmod.common.teams.ITeamObject;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.teams.TeamsRound;
import com.flansmod.common.types.InfoType;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;

public class GametypeZombies extends Gametype {

   public boolean friendlyFire = false;
   public int humanPrepTime = 600;


   public GametypeZombies() {
      super("Zombies", "ZOM", 2);
   }

   public void roundStart() {}

   public void roundEnd() {}

   public void roundCleanup() {}

   public void tick() {
      TeamsManager var10000;
      if(Gametype.teamsManager.roundTimeLeft + this.humanPrepTime - 200 == Gametype.teamsManager.currentRound.timeLimit * 20 * 60) {
         var10000 = Gametype.teamsManager;
         TeamsManager.messageAll("§cThe §4zombies §care upon us! 10 seconds to §4infection§c!");
      }

      if(Gametype.teamsManager.roundTimeLeft + this.humanPrepTime == Gametype.teamsManager.currentRound.timeLimit * 20 * 60) {
         var10000 = Gametype.teamsManager;
         TeamsManager.messageAll("§cThe §4zombie plague §cis here!");
         this.zombifySomeone();
      }

      if(Gametype.teamsManager.roundTimeLeft + this.humanPrepTime < Gametype.teamsManager.currentRound.timeLimit * 20 * 60 && Gametype.teamsManager.roundTimeLeft % 200 == 0 && Gametype.teamsManager.currentRound.teams[1].members.size() == 0) {
         this.zombifySomeone();
      }

   }

   public void zombifySomeone() {
      if(Gametype.teamsManager.currentRound.teams[0].members.size() > 0) {
         EntityPlayerMP zombifiedPlayer = Gametype.teamsManager.getPlayer((String)Gametype.teamsManager.currentRound.teams[0].members.get(Gametype.rand.nextInt(Gametype.teamsManager.currentRound.teams[0].members.size())));
         TeamsManager var10000 = Gametype.teamsManager;
         TeamsManager.messageAll("§4" + zombifiedPlayer.getCommandSenderName() + "§c was infected with the §4zombie plague§c!");
         zombifiedPlayer.attackEntityFrom(DamageSource.generic, 1.0E9F);
      }

   }

   public Team[] getTeamsCanSpawnAs(TeamsRound currentRound, EntityPlayer player) {
      return Gametype.teamsManager.roundTimeLeft + this.humanPrepTime > Gametype.teamsManager.currentRound.timeLimit * 20 * 60?new Team[]{currentRound.teams[0]}:new Team[]{currentRound.teams[1]};
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

   public boolean playerCanAttack(EntityPlayerMP attacker, Team attackerTeam, EntityPlayerMP victim, Team victimTeam) {
      return attackerTeam != victimTeam || this.friendlyFire;
   }

   public void playerKilled(EntityPlayerMP player, DamageSource source) {
      PlayerData playerData = getPlayerData(player);
      EntityPlayerMP attacker = getPlayerFromDamageSource(source);
      if(attacker != null) {
         PlayerData attackerData = getPlayerData(attacker);
         if(attacker != player) {
            if(attackerData.team == playerData.team) {
               if(this.isHuman(attackerData.team)) {
                  ++attackerData.score;
               } else {
                  --attackerData.zombieScore;
               }
            } else {
               if(this.isHuman(attackerData.team)) {
                  ++attackerData.score;
               } else if(this.isZombie(attackerData.team)) {
                  ++attackerData.zombieScore;
               }

               ++attackerData.kills;
            }
         }
      }

      ++playerData.deaths;
      if(Gametype.teamsManager.roundTimeLeft + this.humanPrepTime <= Gametype.teamsManager.currentRound.timeLimit * 20 * 60) {
         if(playerData.team != null) {
            playerData.team.removePlayer((EntityPlayer)player);
         }

         playerData.team = playerData.newTeam = Gametype.teamsManager.currentRound.teams[1];
         playerData.team.addPlayer((EntityPlayer)player);
         Gametype.teamsManager.sendClassMenuToPlayer(player);
      }

   }

   public boolean teamHasWon(Team team) {
      return this.isHuman(team)?Gametype.teamsManager.roundTimeLeft == 1 && team.members.size() > 0:(!this.isZombie(team)?false:Gametype.teamsManager.roundTimeLeft + this.humanPrepTime <= Gametype.teamsManager.currentRound.timeLimit * 20 * 60 && Gametype.teamsManager.currentRound.teams[0].members.size() == 0);
   }

   public boolean isHuman(Team team) {
      return team == Gametype.teamsManager.currentRound.teams[0];
   }

   public boolean isZombie(Team team) {
      return team == Gametype.teamsManager.currentRound.teams[1];
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
            if(data.newTeam == Team.spectators) {
               ArrayList spawnPoint = Gametype.teamsManager.currentRound.map.getBasesPerTeam(1);
               Iterator bases = spawnPoint.iterator();

               while(bases.hasNext()) {
                  ITeamBase base = (ITeamBase)bases.next();
                  if(base.getMap() == Gametype.teamsManager.currentRound.map) {
                     for(int base1 = 0; base1 < base.getObjects().size(); ++base1) {
                        if(((ITeamObject)base.getObjects().get(base1)).isSpawnPoint()) {
                           validSpawnPoints.add(base.getObjects().get(base1));
                        }
                     }
                  }
               }
            } else {
               for(int var9 = 2; var9 < 4; ++var9) {
                  ArrayList var11 = Gametype.teamsManager.currentRound.map.getBasesPerTeam(var9);
                  Iterator var12 = var11.iterator();

                  while(var12.hasNext()) {
                     ITeamBase var13 = (ITeamBase)var12.next();
                     if(var13.getMap() == Gametype.teamsManager.currentRound.map) {
                        for(int i = 0; i < var13.getObjects().size(); ++i) {
                           if(((ITeamObject)var13.getObjects().get(i)).isSpawnPoint()) {
                              validSpawnPoints.add(var13.getObjects().get(i));
                           }
                        }
                     }
                  }
               }
            }

            if(validSpawnPoints.size() > 0) {
               ITeamObject var10 = (ITeamObject)validSpawnPoints.get(Gametype.rand.nextInt(validSpawnPoints.size()));
               return Vec3.createVectorHelper(var10.getPosX(), var10.getPosY(), var10.getPosZ());
            } else {
               return null;
            }
         }
      }
   }

   public boolean showZombieScore() {
      return true;
   }

   public boolean playerCanLoot(ItemStack stack, InfoType infoType, EntityPlayer player, Team playerTeam) {
      return playerTeam != Gametype.teamsManager.currentRound.teams[1];
   }

   public void readFromNBT(NBTTagCompound tags) {
      this.humanPrepTime = tags.getInteger("ZOMPrepTime");
   }

   public void saveToNBT(NBTTagCompound tags) {
      tags.setInteger("ZOMPrepTime", this.humanPrepTime);
   }

   public boolean setVariable(String variable, String value) {
      if(variable.toLowerCase().equals("humanpreptime")) {
         this.humanPrepTime = Integer.parseInt(value) * 20;
         return true;
      } else {
         return false;
      }
   }
}
