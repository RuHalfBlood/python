package com.flansmod.common.teams;

import com.flansmod.common.FlansMod;
import com.flansmod.common.teams.ITeamBase;
import com.flansmod.common.teams.ITeamObject;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class TeamsMap {

   public String shortName;
   public String name;
   public Ticket chunkLoadingTicket;
   public ArrayList bases = new ArrayList();
   public int minPlayers = 0;
   public int maxPlayers = 1000000;


   public TeamsMap(World world, String sn, String n) {
      this.shortName = sn;
      this.name = n;
      this.chunkLoadingTicket = ForgeChunkManager.requestTicket(FlansMod.INSTANCE, world, Type.NORMAL);
      if(this.chunkLoadingTicket == null) {
         FlansMod.log("Failed to add chunk loading ticket as Flan\'s Mod has run out");
      } else {
         this.chunkLoadingTicket.getModData().setString("ShortName", this.shortName);
      }
   }

   public ArrayList getBasesPerTeam(int teamID) {
      ArrayList basesForThisTeam = new ArrayList();
      Iterator var3 = this.bases.iterator();

      while(var3.hasNext()) {
         ITeamBase base = (ITeamBase)var3.next();
         if(base.getOwnerID() == teamID) {
            basesForThisTeam.add(base);
         }
      }

      return basesForThisTeam;
   }

   public void addBase(ITeamBase base) {
      this.bases.add(base);
   }

   public void addBaseFirstTime(ITeamBase base) {
      this.addBase(base);
      ForgeChunkManager.forceChunk(this.chunkLoadingTicket, new ChunkCoordIntPair((int)base.getPosX() >> 4, (int)base.getPosZ() >> 4));
      FlansMod.log("Added chunk at " + ((int)base.getPosX() >> 4) + ",  " + ((int)base.getPosZ() >> 4) + " to chunk loading ticket for base " + this.name);
   }

   public void removeBase(ITeamBase base) {
      if(this.bases == null) {
         FlansMod.log("Base array for map " + this.name + " null");
      } else {
         this.bases.remove(base);
         ForgeChunkManager.unforceChunk(this.chunkLoadingTicket, new ChunkCoordIntPair((int)base.getPosX() >> 4, (int)base.getPosZ() >> 4));
         FlansMod.log("Removed chunk at " + ((int)base.getPosX() >> 4) + ",  " + ((int)base.getPosZ() >> 4) + " from chunk loading ticket for base " + this.name);
      }
   }

   public void addObject(ITeamObject object) {}

   public void addObjectFirstTime(ITeamObject object) {
      if(object.forceChunkLoading()) {
         ForgeChunkManager.forceChunk(this.chunkLoadingTicket, new ChunkCoordIntPair((int)object.getPosX() >> 4, (int)object.getPosZ() >> 4));
      }

   }

   public TeamsMap(World world, NBTTagCompound tags) {
      this.shortName = tags.getString("ShortName");
      this.name = tags.getString("Name");
      this.minPlayers = tags.getInteger("MinPlayers");
      this.maxPlayers = tags.getInteger("MaxPlayers");
   }

   public void writeToNBT(NBTTagCompound tags) {
      tags.setString("ShortName", this.shortName);
      tags.setString("Name", this.name);
      tags.setInteger("MinPlayers", this.minPlayers);
      tags.setInteger("MaxPlayers", this.maxPlayers);
   }

   public void forceChunkLoading(Ticket ticket) {
      UnmodifiableIterator var2 = ticket.getChunkList().iterator();

      while(var2.hasNext()) {
         ChunkCoordIntPair coord = (ChunkCoordIntPair)var2.next();
         FlansMod.log("Loading chunk at " + coord.chunkXPos + ", " + coord.chunkZPos + " for map : " + this.name);
         ForgeChunkManager.forceChunk(ticket, coord);
      }

   }

   public void deleteMap() {
      ForgeChunkManager.releaseTicket(this.chunkLoadingTicket);
   }
}
