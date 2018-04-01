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

public class GametypeDM extends Gametype {

   public int scoreLimit = 25;
   public int newRoundTimer = 0;
   public int time;


   public GametypeDM() {
      super("Free For All", "DM", 2);
   }

   public void roundStart() {}

   public void roundEnd() {}

   public void roundCleanup() {}

   public void tick() {}

   public void playerQuit(EntityPlayerMP player) {}

   public boolean playerAttacked(EntityPlayerMP player, DamageSource source) {
      if(getPlayerData(player) != null && getPlayerData(player).team != null) {
         EntityPlayerMP attacker = getPlayerFromDamageSource(source);
         if(attacker != null) {
            if(getPlayerData(attacker) == null || getPlayerData(attacker).team == null) {
               return false;
            }

            if(getPlayerData(attacker).team == Team.spectators) {
               return false;
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
            if(data.newTeam == Team.spectators) {
               ArrayList spawnPoint = Gametype.teamsManager.currentRound.map.getBasesPerTeam(Gametype.teamsManager.currentRound.getTeamID(data.newTeam));
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

   public void playerRespawned(EntityPlayerMP player) {}

   public boolean setVariable(String variable, String value) {
      if(variable.toLowerCase().equals("scorelimit")) {
         this.scoreLimit = Integer.parseInt(value);
         return true;
      } else {
         return false;
      }
   }

   public void readFromNBT(NBTTagCompound tags) {
      this.scoreLimit = tags.getInteger("DMScoreLimit");
   }

   public void saveToNBT(NBTTagCompound tags) {
      tags.setInteger("DMScoreLimit", this.scoreLimit);
   }

   public boolean sortScoreboardByTeam() {
      return false;
   }

   public boolean shouldAutobalance() {
      return false;
   }

   public boolean teamHasWon(Team team) {
      return false;
   }
}
