package com.flansmod.common.network;

import com.flansmod.client.FlansModClient;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.teams.PlayerClass;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketTeamInfo extends PacketBase {

   public static String mapShortName;
   public static String map;
   public static String gametype;
   public static boolean showZombieScore;
   public static int numTeams;
   public static PacketTeamInfo.TeamData[] teamData;
   public static boolean sortedByTeam;
   public static int timeLeft;
   public static int scoreLimit;
   public static int numLines;


   public static PacketTeamInfo.PlayerScoreData getPlayerScoreData(String username) {
      PacketTeamInfo var10000 = FlansModClient.teamInfo;
      if(teamData == null) {
         return null;
      } else {
         var10000 = FlansModClient.teamInfo;
         PacketTeamInfo.TeamData[] var1 = teamData;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            PacketTeamInfo.TeamData team = var1[var3];
            if(team == null || team.playerData == null) {
               return null;
            }

            PacketTeamInfo.PlayerScoreData[] var5 = team.playerData;
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               PacketTeamInfo.PlayerScoreData player = var5[var7];
               if(player != null && player.username != null && player.username.equals(username)) {
                  return player;
               }
            }
         }

         return null;
      }
   }

   public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) {
      data.writeBoolean(TeamsManager.canBreakGlass);
      data.writeBoolean(TeamsManager.vehiclesNeedFuel);
      data.writeBoolean(TeamsManager.driveablesBreakBlocks);
      data.writeBoolean(TeamsManager.allowVehicleZoom);
      if(TeamsManager.getInstance().currentRound == null) {
         this.writeUTF(data, "No Gametype");
         data.writeInt(0);
      } else {
         this.writeUTF(data, TeamsManager.getInstance().currentRound.gametype.name);
         data.writeBoolean(TeamsManager.getInstance().currentRound.gametype.showZombieScore());
         this.writeUTF(data, TeamsManager.getInstance().currentRound.map.name);
         this.writeUTF(data, TeamsManager.getInstance().currentRound.map.shortName);
         data.writeInt(TeamsManager.getInstance().roundTimeLeft);
         data.writeInt(TeamsManager.getInstance().currentRound.scoreLimit);
         if(TeamsManager.getInstance().currentRound.gametype.sortScoreboardByTeam()) {
            data.writeBoolean(true);
            if(TeamsManager.getInstance().currentRound.teams == null) {
               data.writeInt(0);
            } else {
               data.writeInt(TeamsManager.getInstance().currentRound.teams.length);

               for(int playerNames = 0; playerNames < TeamsManager.getInstance().currentRound.teams.length; ++playerNames) {
                  Team i = TeamsManager.getInstance().currentRound.teams[playerNames];
                  if(i == null) {
                     this.writeUTF(data, "none");
                  } else {
                     this.writeUTF(data, i.shortName);
                     data.writeInt(i.score);
                     data.writeBoolean(TeamsManager.getInstance().currentRound.gametype.teamHasWon(i));
                     i.sortPlayers();
                     data.writeInt(i.members.size());

                     for(int username = 0; username < i.members.size(); ++username) {
                        String playerData = (String)i.members.get(username);
                        PlayerData playerData1 = PlayerHandler.getPlayerData(playerData, Side.SERVER);
                        this.writeUTF(data, playerData);
                        if(playerData1 == null) {
                           data.writeInt(0);
                           data.writeInt(0);
                           data.writeInt(0);
                           this.writeUTF(data, "");
                        } else {
                           data.writeInt(playerData1.score);
                           data.writeInt(playerData1.zombieScore);
                           data.writeInt(playerData1.kills);
                           data.writeInt(playerData1.deaths);
                           this.writeUTF(data, playerData1.playerClass.shortName);
                        }
                     }
                  }
               }
            }
         } else {
            data.writeBoolean(false);
            ArrayList var8 = new ArrayList();

            for(int var9 = 0; var9 < TeamsManager.getInstance().currentRound.teams.length; ++var9) {
               Team var11 = TeamsManager.getInstance().currentRound.teams[var9];
               if(var11 != null && var11.members != null) {
                  var8.addAll(var11.members);
               }
            }

            Collections.sort(var8, new Team.ComparatorScore());
            data.writeInt(var8.size());
            Iterator var10 = var8.iterator();

            while(var10.hasNext()) {
               String var12 = (String)var10.next();
               PlayerData var13 = PlayerHandler.getPlayerData(var12, Side.SERVER);
               this.writeUTF(data, var12);
               if(var13 == null) {
                  data.writeInt(0);
                  data.writeInt(0);
                  data.writeInt(0);
                  this.writeUTF(data, "");
               } else {
                  data.writeInt(var13.score);
                  data.writeInt(var13.kills);
                  data.writeInt(var13.deaths);
                  this.writeUTF(data, var13.playerClass.shortName);
               }
            }
         }
      }

   }

   public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) {
      TeamsManager.canBreakGlass = data.readBoolean();
      TeamsManager.vehiclesNeedFuel = data.readBoolean();
      TeamsManager.driveablesBreakBlocks = data.readBoolean();
      TeamsManager.allowVehicleZoom = data.readBoolean();
      gametype = this.readUTF(data);
      if(gametype.equals("No Gametype")) {
         numTeams = 0;
         teamData = new PacketTeamInfo.TeamData[0];
      } else {
         showZombieScore = data.readBoolean();
         map = this.readUTF(data);
         mapShortName = this.readUTF(data);
         timeLeft = data.readInt();
         scoreLimit = data.readInt();
         sortedByTeam = data.readBoolean();
         int j;
         if(sortedByTeam) {
            numLines = numTeams = data.readInt();
            if(numTeams == 0) {
               return;
            }

            teamData = new PacketTeamInfo.TeamData[numTeams];

            for(j = 0; j < numTeams; ++j) {
               teamData[j] = new PacketTeamInfo.TeamData();
               String teamName = this.readUTF(data);
               if(!teamName.equals("none")) {
                  teamData[j].team = Team.getTeam(teamName);
                  teamData[j].score = data.readInt();
                  teamData[j].winner = data.readBoolean();
                  teamData[j].numPlayers = data.readInt();
                  teamData[j].playerData = new PacketTeamInfo.PlayerScoreData[teamData[j].numPlayers];
                  if(teamData[j].numPlayers > numLines) {
                     numLines = teamData[j].numPlayers;
                  }

                  for(int j1 = 0; j1 < teamData[j].numPlayers; ++j1) {
                     teamData[j].playerData[j1] = new PacketTeamInfo.PlayerScoreData();
                     teamData[j].playerData[j1].team = teamData[j];
                     teamData[j].playerData[j1].username = this.readUTF(data);
                     teamData[j].playerData[j1].score = data.readInt();
                     teamData[j].playerData[j1].zombieScore = data.readInt();
                     teamData[j].playerData[j1].kills = data.readInt();
                     teamData[j].playerData[j1].deaths = data.readInt();
                     teamData[j].playerData[j1].playerClass = PlayerClass.getClass(this.readUTF(data));
                  }
               }
            }
         } else {
            numLines = 0;
            teamData = new PacketTeamInfo.TeamData[]{new PacketTeamInfo.TeamData()};
            teamData[0].team = null;
            teamData[0].score = 0;
            teamData[0].numPlayers = data.readInt();
            teamData[0].playerData = new PacketTeamInfo.PlayerScoreData[teamData[0].numPlayers];
            numLines += teamData[0].numPlayers;

            for(j = 0; j < teamData[0].numPlayers; ++j) {
               teamData[0].playerData[j] = new PacketTeamInfo.PlayerScoreData();
               teamData[0].playerData[j].team = teamData[0];
               teamData[0].playerData[j].username = this.readUTF(data);
               teamData[0].playerData[j].score = data.readInt();
               teamData[0].playerData[j].kills = data.readInt();
               teamData[0].playerData[j].deaths = data.readInt();
               teamData[0].playerData[j].playerClass = PlayerClass.getClass(this.readUTF(data));
            }
         }
      }

   }

   public void handleServerSide(EntityPlayerMP playerEntity) {}

   @SideOnly(Side.CLIENT)
   public void handleClientSide(EntityPlayer clientPlayer) {
      FlansModClient.teamInfo = this;
   }

   public Team getTeam(int spawnerTeamID) {
      switch(spawnerTeamID) {
      case 0:
         return null;
      case 1:
         return Team.spectators;
      default:
         return teamData.length > spawnerTeamID - 2?teamData[spawnerTeamID - 2].team:null;
      }
   }

   public boolean roundOver() {
      if(timeLeft == 0) {
         return true;
      } else {
         PacketTeamInfo.TeamData[] var1 = teamData;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            PacketTeamInfo.TeamData aTeamData = var1[var3];
            if(aTeamData.score == scoreLimit) {
               return true;
            }
         }

         return false;
      }
   }

   public Team getWinner() {
      PacketTeamInfo.TeamData[] var1 = teamData;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         PacketTeamInfo.TeamData aTeamData = var1[var3];
         if(aTeamData.winner) {
            return aTeamData.team;
         }
      }

      return null;
   }

   public static class PlayerScoreData {

      public String username;
      public int score;
      public int kills;
      public int deaths;
      public PacketTeamInfo.TeamData team;
      public PlayerClass playerClass;
      public int zombieScore;


   }

   public static class TeamData {

      public Team team;
      public int score;
      public int numPlayers;
      public PacketTeamInfo.PlayerScoreData[] playerData;
      public boolean winner;


   }
}
