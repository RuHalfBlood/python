package com.flansmod.common.teams;

import com.flansmod.common.teams.Gametype;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.teams.TeamsMap;
import net.minecraft.nbt.NBTTagCompound;

public class TeamsRound
implements Comparable<TeamsRound> {

   public Gametype gametype;
   public TeamsMap map;
   public Team[] teams;
   public int timeLimit;
   public int scoreLimit;
   public float popularity;
   public int roundsSincePlayed;
   public boolean isNextRoundOn = false;


   public TeamsRound(TeamsMap map2, Gametype gametype2, Team[] teams2, int timeLimit, int scoreLimit, boolean isNext) {
      this.map = map2;
      this.gametype = gametype2;
      this.teams = teams2;
      this.timeLimit = timeLimit;
      this.scoreLimit = scoreLimit;
      this.popularity = 0.5F;
      this.isNextRoundOn = isNext;
   }

   public TeamsRound(NBTTagCompound tags) {
      this.map = (TeamsMap)TeamsManager.getInstance().maps.get(tags.getString("Map"));
      this.gametype = Gametype.getGametype(tags.getString("Gametype"));
      this.timeLimit = tags.getInteger("TimeLimit");
      this.scoreLimit = tags.getInteger("ScoreLimit");
      this.teams = new Team[tags.getInteger("NumTeams")];

      for(int i = 0; i < this.teams.length; ++i) {
         this.teams[i] = Team.getTeam(tags.getString("Team_" + i));
      }

      this.popularity = tags.getFloat("Pop");
      this.isNextRoundOn = tags.getBoolean("NextRound");
   }

   public void writeToNBT(NBTTagCompound tags) {
      tags.setString("Map", this.map.shortName);
      tags.setString("Gametype", this.gametype.shortName);
      tags.setInteger("TimeLimit", this.timeLimit);
      tags.setInteger("ScoreLimit", this.scoreLimit);
      tags.setInteger("NumTeams", this.teams.length);

      for(int i = 0; i < this.teams.length; ++i) {
         tags.setString("Team_" + i, this.teams[i].shortName);
      }

      tags.setFloat("Pop", this.popularity);
      tags.setBoolean("NextRound", this.isNextRoundOn);
   }

   public int getTeamID(Team team) {
      return team == Team.spectators?1:(team == this.teams[0]?2:(team == this.teams[1]?3:0));
   }

   public Team getTeam(int id) {
      switch(id) {
      case 0:
         return null;
      case 1:
         return Team.spectators;
      default:
         return this.teams[id - 2];
      }
   }

   public Team getOtherTeam(Team team) {
      return team != Team.spectators && team != null && this.teams.length == 2?(team == this.teams[0]?this.teams[1]:this.teams[0]):team;
   }

   public float getWeight() {
      return this.popularity * 4.0F + (float)this.roundsSincePlayed;
   }

   public int compareTo(TeamsRound o) {
      return this.getWeight() < o.getWeight()?1:(this.getWeight() > o.getWeight()?-1:0);
   }
}
