package com.flansmod.common.teams;

import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.teams.TeamsMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

public class ChunkLoadingHandler implements LoadingCallback {

   public void ticketsLoaded(List tickets, World world) {
      Iterator var3 = tickets.iterator();

      while(var3.hasNext()) {
         Ticket ticket = (Ticket)var3.next();
         String s = ticket.getModData().getString("ShortName");
         TeamsMap map = (TeamsMap)TeamsManager.getInstance().maps.get(s);
         if(ticket != null && map != null) {
            map.forceChunkLoading(ticket);
         }
      }

   }
}
