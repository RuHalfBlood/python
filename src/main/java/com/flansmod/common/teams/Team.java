package com.flansmod.common.teams;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.teams.ArmourType;
import com.flansmod.common.teams.ItemTeamArmour;
import com.flansmod.common.teams.PlayerClass;
import com.flansmod.common.types.EnumType;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.types.TypeFile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Team extends InfoType {

   public static List teams = new ArrayList();
   public List members = new ArrayList();
   public List classes = new ArrayList();
   public static Team spectators;
   public int score = 0;
   public int teamColour = 16777215;
   public char textColour = 102;
   public ItemStack hat;
   public ItemStack chest;
   public ItemStack legs;
   public ItemStack shoes;


   public Team(String s, String s1, int teamCol, char textCol) {
      super(new TypeFile(EnumType.team, s, "", false));
      super.shortName = s;
      super.name = s1;
      this.teamColour = teamCol;
      this.textColour = textCol;
      teams.add(this);
   }

   public Team(TypeFile file) {
      super(file);
      teams.add(this);
   }

   protected void preRead(TypeFile file) {}

   protected void postRead(TypeFile file) {}

   protected void read(String[] split, TypeFile file) {
      super.read(split, file);

      try {
         if(split[0].equals("TeamColour")) {
            this.teamColour = (Integer.parseInt(split[1]) << 16) + (Integer.parseInt(split[2]) << 8) + Integer.parseInt(split[3]);
         }

         if(split[0].equals("TextColour")) {
            if(split[1].equals("Black")) {
               this.textColour = 48;
            }

            if(split[1].equals("Blue")) {
               this.textColour = 49;
            }

            if(split[1].equals("Green")) {
               this.textColour = 50;
            }

            if(split[1].equals("Aqua")) {
               this.textColour = 51;
            }

            if(split[1].equals("Red")) {
               this.textColour = 52;
            }

            if(split[1].equals("Purple")) {
               this.textColour = 53;
            }

            if(split[1].equals("Orange")) {
               this.textColour = 54;
            }

            if(split[1].equals("LGrey")) {
               this.textColour = 55;
            }

            if(split[1].equals("Grey")) {
               this.textColour = 56;
            }

            if(split[1].equals("LBlue")) {
               this.textColour = 57;
            }

            if(split[1].equals("LGreen")) {
               this.textColour = 97;
            }

            if(split[1].equals("LAqua")) {
               this.textColour = 98;
            }

            if(split[1].equals("Red")) {
               this.textColour = 99;
            }

            if(split[1].equals("Pink")) {
               this.textColour = 100;
            }

            if(split[1].equals("Yellow")) {
               this.textColour = 101;
            }

            if(split[1].equals("White")) {
               this.textColour = 102;
            }
         }

         Iterator e;
         Item item;
         ArmourType armour;
         if(split[0].equals("Hat") || split[0].equals("Helmet")) {
            if(split[1].equals("None")) {
               return;
            }

            e = FlansMod.armourItems.iterator();

            while(e.hasNext()) {
               item = (Item)e.next();
               armour = ((ItemTeamArmour)item).type;
               if(armour != null && armour.shortName.equals(split[1])) {
                  this.hat = new ItemStack(item);
               }
            }
         }

         if(split[0].equals("Chest") || split[0].equals("Top")) {
            if(split[1].equals("None")) {
               return;
            }

            e = FlansMod.armourItems.iterator();

            while(e.hasNext()) {
               item = (Item)e.next();
               armour = ((ItemTeamArmour)item).type;
               if(armour != null && armour.shortName.equals(split[1])) {
                  this.chest = new ItemStack(item);
               }
            }
         }

         if(split[0].equals("Legs") || split[0].equals("Bottom")) {
            if(split[1].equals("None")) {
               return;
            }

            e = FlansMod.armourItems.iterator();

            while(e.hasNext()) {
               item = (Item)e.next();
               armour = ((ItemTeamArmour)item).type;
               if(armour != null && armour.shortName.equals(split[1])) {
                  this.legs = new ItemStack(item);
               }
            }
         }

         if(split[0].equals("Shoes") || split[0].equals("Boots")) {
            if(split[1].equals("None")) {
               return;
            }

            e = FlansMod.armourItems.iterator();

            while(e.hasNext()) {
               item = (Item)e.next();
               armour = ((ItemTeamArmour)item).type;
               if(armour != null && armour.shortName.equals(split[1])) {
                  this.shoes = new ItemStack(item);
               }
            }
         }

         if(split[0].equals("AddDefaultClass") || split[0].equals("AddClass")) {
            this.classes.add(PlayerClass.getClass(split[1]));
         }
      } catch (Exception var6) {
         System.out.println("Reading team file failed.");
         var6.printStackTrace();
      }

   }

   public static Team getTeam(String s) {
      Iterator var1 = teams.iterator();

      Team team;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         team = (Team)var1.next();
      } while(!team.shortName.equals(s));

      return team;
   }

   public void removePlayer(EntityPlayer player) {
      this.removePlayer(player.getCommandSenderName());
   }

   public String removePlayer(String username) {
      this.members.remove(username);
      if(PlayerHandler.getPlayerData(username) != null) {
         PlayerHandler.getPlayerData(username).team = null;
      }

      return username;
   }

   public EntityPlayer addPlayer(EntityPlayer player) {
      this.addPlayer(player.getCommandSenderName());
      return player;
   }

   public String addPlayer(String username) {
      ArrayList list = new ArrayList();
      list.add(username);
      Iterator var3 = teams.iterator();

      while(var3.hasNext()) {
         Team team = (Team)var3.next();
         team.members.removeAll(list);
      }

      this.members.add(username);
      PlayerHandler.getPlayerData(username).newTeam = PlayerHandler.getPlayerData(username).team = this;
      return username;
   }

   public String removeWorstPlayer() {
      this.sortPlayers();
      return this.members.size() == 0?null:this.removePlayer((String)this.members.get(this.members.size() - 1));
   }

   public void sortPlayers() {
      Collections.sort(this.members, new Team.ComparatorScore());
   }

   public float GetRecommendedScale() {
      return 50.0F;
   }

   @SideOnly(Side.CLIENT)
   public ModelBase GetModel() {
      return null;
   }


   public static class ComparatorScore
   implements Comparator<String> {
       @Override
       public int compare(String a, String b) {
           PlayerData dataA = PlayerHandler.getPlayerData(a);
           PlayerData dataB = PlayerHandler.getPlayerData(b);
           if (dataA == null || dataB == null) {
               return 0;
           }
           return dataB.score - dataA.score;
       }
   }

}

