package com.flansmod.common.teams;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.driveables.ItemPlane;
import com.flansmod.common.driveables.ItemVehicle;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemAAGun;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.ItemShootable;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketRoundFinished;
import com.flansmod.common.network.PacketTeamInfo;
import com.flansmod.common.network.PacketTeamSelect;
import com.flansmod.common.network.PacketVoting;
import com.flansmod.common.teams.EntityGunItem;
import com.flansmod.common.teams.Gametype;
import com.flansmod.common.teams.GametypeCTF;
import com.flansmod.common.teams.GametypeDM;
import com.flansmod.common.teams.GametypeTDM;
import com.flansmod.common.teams.GametypeZombies;
import com.flansmod.common.teams.ITeamBase;
import com.flansmod.common.teams.ITeamObject;
import com.flansmod.common.teams.ItemOpStick;
import com.flansmod.common.teams.ItemTeamArmour;
import com.flansmod.common.teams.PlayerClass;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsMap;
import com.flansmod.common.teams.TeamsRound;
import com.flansmod.common.types.InfoType;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;

public class TeamsManager {

   public static boolean enabled = true;
   public static TeamsManager instance;
   public static boolean voting = false;
   public static boolean explosions = true;
   public static boolean driveablesBreakBlocks = true;
   public static boolean bombsEnabled = true;
   public static boolean shellsEnabled = true;
   public static boolean missilesEnabled = true;
   public static boolean bulletsEnabled = true;
   public static boolean forceAdventureMode = true;
   public static boolean canBreakGuns = true;
   public static boolean canBreakGlass = true;
   public static boolean armourDrops = true;
   public static boolean vehiclesNeedFuel = true;
   public static boolean overrideHunger = true;
   public static boolean allowVehicleZoom = false;
   public static int weaponDrops = 1;
   public static int mgLife = 0;
   public static int planeLife = 0;
   public static int vehicleLife = 0;
   public static int mechaLove = 0;
   public static int aaLife = 0;
   public static int scoreDisplayTime = 200;
   public static int votingTime = 200;
   public TeamsRound currentRound;
   public ArrayList rounds;
   public HashMap maps;
   private int nextBaseID = 1;
   public ArrayList bases;
   public ArrayList objects;
   private long time;
   public int roundTimeLeft;
   public int interRoundTimeLeft;
   public TeamsRound[] voteOptions;
   public TeamsRound nextRound;
   public static boolean autoBalance;
   public static int autoBalanceInterval;
   public static int bulletSnapshotMin = 0;
   public static int bulletSnapshotDivisor = 50;


   public TeamsManager() {
      instance = this;
      MinecraftForge.EVENT_BUS.register(this);
      FMLCommonHandler.instance().bus().register(this);
      this.bases = new ArrayList();
      this.objects = new ArrayList();
      this.maps = new HashMap();
      this.rounds = new ArrayList();
      new GametypeTDM();
      new GametypeZombies();
      new GametypeDM();
      new GametypeCTF();
   }

   public void reset() {
      this.currentRound = null;
      this.bases = new ArrayList();
      this.objects = new ArrayList();
      this.maps = new HashMap();
      this.rounds = new ArrayList();
   }

   public static TeamsManager getInstance() {
      return instance;
   }

   public void tick() {
      if(this.time % 40L == 0L) {
         FlansMod var10000 = FlansMod.INSTANCE;
         FlansMod.getPacketHandler().sendToAll((PacketBase)(new PacketTeamInfo()));
         this.showTeamsMenuToAll(true);
      }

      if(enabled) {
         if(this.currentRound != null) {
            this.currentRound.gametype.tick();
         }

         ++this.time;
         Iterator roundEnded = this.bases.iterator();

         while(roundEnded.hasNext()) {
            ITeamBase object = (ITeamBase)roundEnded.next();
            object.tick();
         }

         roundEnded = this.objects.iterator();

         while(roundEnded.hasNext()) {
            ITeamObject var9 = (ITeamObject)roundEnded.next();
            var9.tick();
         }

         int var3;
         if(overrideHunger && this.currentRound != null) {
            WorldServer[] var7 = MinecraftServer.getServer().worldServers;
            int var10 = var7.length;

            for(var3 = 0; var3 < var10; ++var3) {
               WorldServer world = var7[var3];
               Iterator team = world.playerEntities.iterator();

               while(team.hasNext()) {
                  Object player = team.next();
                  ((EntityPlayer)player).getFoodStats().addStats(20, 10.0F);
               }
            }
         }

         if(this.interRoundTimeLeft > 0) {
            --this.interRoundTimeLeft;
            if(voting) {
               if(this.nextRound != null) {
                  this.startNextRound();
                  this.interRoundTimeLeft = 0;
                  return;
               }

               if(this.interRoundTimeLeft == votingTime) {
                  this.pickVoteOptions();
               }

               if(this.interRoundTimeLeft <= votingTime) {
                  if(this.voteOptions == null) {
                     this.pickVoteOptions();
                  }

                  this.displayVotingGUI();
               }
            }

            if(this.interRoundTimeLeft == 0 && this.currentRound.isNextRoundOn) {
               this.startNextRound();
            }
         }

         if(this.currentRound != null && this.roundTimeLeft > 0) {
            if(this.autoBalance() && this.time % (long)autoBalanceInterval == (long)(autoBalanceInterval - 200) && this.needAutobalance()) {
               messageAll("§fAutobalancing teams...");
            }

            if(this.autoBalance() && this.time % (long)autoBalanceInterval == 0L && this.needAutobalance()) {
               this.autobalance();
            }

            --this.roundTimeLeft;
            boolean var8 = this.roundTimeLeft == 0;
            if(var8) {
               messageAll(this.randomTimeOutString());
            }

            Team[] var11 = this.currentRound.teams;
            var3 = var11.length;

            for(int var12 = 0; var12 < var3; ++var12) {
               Team var13 = var11[var12];
               if(this.currentRound.gametype.teamHasWon(var13)) {
                  var8 = true;
                  messageAll(var13.name + " won the round!");
               }
            }

            if(var8) {
               this.roundTimeLeft = 0;
               this.interRoundTimeLeft = voting?votingTime + scoreDisplayTime:scoreDisplayTime;
               this.displayScoreboardGUI();
               this.currentRound.gametype.roundEnd();
               PlayerHandler.roundEnded();
            }
         }

      }
   }

   public boolean needAutobalance() {
      if(this.autoBalance() && this.currentRound != null && this.currentRound.teams.length == 2) {
         int membersTeamA = this.currentRound.teams[0].members.size();
         int membersTeamB = this.currentRound.teams[1].members.size();
         return Math.abs(membersTeamA - membersTeamB) > 1;
      } else {
         return false;
      }
   }

   public void autobalance() {
      if(this.autoBalance() && this.currentRound != null && this.currentRound.teams.length == 2) {
         int membersTeamA = this.currentRound.teams[0].members.size();
         int membersTeamB = this.currentRound.teams[1].members.size();
         int i;
         EntityPlayerMP playerToKick;
         if(membersTeamA - membersTeamB > 1) {
            for(i = 0; i < (membersTeamA - membersTeamB) / 2; ++i) {
               playerToKick = this.getPlayer(this.currentRound.teams[1].addPlayer(this.currentRound.teams[0].removeWorstPlayer()));
               messagePlayer(playerToKick, "You were moved to the other team by the autobalancer.");
               this.sendClassMenuToPlayer(playerToKick);
            }
         }

         if(membersTeamB - membersTeamA > 1) {
            for(i = 0; i < (membersTeamB - membersTeamA) / 2; ++i) {
               playerToKick = this.getPlayer(this.currentRound.teams[0].addPlayer(this.currentRound.teams[1].removeWorstPlayer()));
               messagePlayer(playerToKick, "You were moved to the other team by the autobalancer.");
               this.sendClassMenuToPlayer(playerToKick);
            }
         }

      }
   }

   public String randomTimeOutString() {
      switch(Gametype.rand.nextInt(4)) {
      case 0:
         return "That\'s time!";
      case 1:
         return "How dull; a tie...";
      case 2:
         return "Everybody\'s a loser but the clock.";
      default:
         return "Time up.";
      }
   }

   public void displayScoreboardGUI() {
      Iterator var1 = getPlayers().iterator();

      while(var1.hasNext()) {
         EntityPlayer player = (EntityPlayer)var1.next();
         PlayerData data = PlayerHandler.getPlayerData(player);
         if(!data.builder) {
            sendPacketToPlayer(new PacketRoundFinished(scoreDisplayTime), (EntityPlayerMP)player);
         }
      }

   }

   public void displayVotingGUI() {
      Iterator var1 = getPlayers().iterator();

      while(var1.hasNext()) {
         EntityPlayer player = (EntityPlayer)var1.next();
         PlayerData data = PlayerHandler.getPlayerData(player);
         if(!data.builder) {
            sendPacketToPlayer(new PacketVoting(this), (EntityPlayerMP)player);
         }
      }

   }

   public void pickVoteOptions() {
      Collections.sort(this.rounds);
      this.voteOptions = new TeamsRound[Math.min(5, this.rounds.size())];

      for(int i = 0; i < this.voteOptions.length; ++i) {
         this.voteOptions[i] = (TeamsRound)this.rounds.get(i);
      }

      this.voteOptions[Gametype.rand.nextInt(this.voteOptions.length)] = (TeamsRound)this.rounds.get(Gametype.rand.nextInt(this.rounds.size()));
   }

   public void start() {
      if(enabled && this.rounds.size() != 0) {
         if(this.currentRound != null) {
            this.currentRound.gametype.roundCleanup();
            this.resetScores();
         }

         this.currentRound = (TeamsRound)this.rounds.get(0);
         this.startRound();
      }
   }

   public void startNextRound() {
      if(enabled && this.rounds.size() != 0) {
         if(this.nextRound == null) {
            int lastRoundID;
            int base;
            if(voting) {
               lastRoundID = 0;
               base = 0;
               int[] numVotes = new int[this.voteOptions.length];
               Iterator i = PlayerHandler.serverSideData.values().iterator();

               PlayerData data;
               while(i.hasNext()) {
                  data = (PlayerData)i.next();
                  if(data.vote > 0) {
                     ++numVotes[data.vote - 1];
                  }
               }

               int var8;
               for(var8 = 0; var8 < this.voteOptions.length; ++var8) {
                  if(numVotes[var8] > base) {
                     base = numVotes[var8];
                     lastRoundID = var8;
                  }
               }

               this.nextRound = this.voteOptions[lastRoundID];

               TeamsRound var9;
               for(i = this.rounds.iterator(); i.hasNext(); ++var9.roundsSincePlayed) {
                  var9 = (TeamsRound)i.next();
               }

               for(var8 = 0; var8 < this.voteOptions.length; ++var8) {
                  if(var8 == lastRoundID) {
                     this.voteOptions[var8].popularity = 1.0F - (1.0F - this.voteOptions[var8].popularity) * 0.8F;
                     this.voteOptions[var8].roundsSincePlayed = 0;
                  } else {
                     this.voteOptions[var8].popularity *= 0.9F;
                     this.voteOptions[var8].popularity += 0.01F;
                  }
               }

               for(i = PlayerHandler.serverSideData.values().iterator(); i.hasNext(); data.vote = 0) {
                  data = (PlayerData)i.next();
               }
            } else {
               lastRoundID = this.rounds.indexOf(this.currentRound);
               ++lastRoundID;
               base = lastRoundID % this.rounds.size();
               this.nextRound = (TeamsRound)this.rounds.get(base);
            }
         }

         if(this.currentRound != null) {
            Iterator var6 = this.currentRound.map.bases.iterator();

            while(var6.hasNext()) {
               ITeamBase var7 = (ITeamBase)var6.next();
               var7.roundCleanup();
            }

            this.currentRound.gametype.roundCleanup();
         }

         this.resetScores();
         if(this.nextRound != null) {
            this.currentRound = this.nextRound;
         }

         this.startRound();
         this.nextRound = null;
      }
   }

   private void startRound() {
      this.currentRound.gametype.roundStart();
      this.roundTimeLeft = this.currentRound.timeLimit * 60 * 20;
      Iterator var1 = this.bases.iterator();

      while(var1.hasNext()) {
         ITeamBase player = (ITeamBase)var1.next();
         player.startRound();
      }

      var1 = getPlayers().iterator();

      while(var1.hasNext()) {
         EntityPlayer player1 = (EntityPlayer)var1.next();
         this.forceRespawn((EntityPlayerMP)player1);
      }

      this.showTeamsMenuToAll();
      messageAll("§fA new round has started!");
   }

   public void showTeamsMenuToAll() {
      this.showTeamsMenuToAll(false);
   }

   public void showTeamsMenuToAll(boolean info) {
      Iterator var2 = getPlayers().iterator();

      while(var2.hasNext()) {
         EntityPlayer player = (EntityPlayer)var2.next();
         PlayerData data = PlayerHandler.getPlayerData(player);
         if(data != null && (!data.builder || !this.playerIsOp(player))) {
            this.sendTeamsMenuToPlayer((EntityPlayerMP)player, info);
         }
      }

   }

   @SubscribeEvent
   public void onPlayerInteract(EntityInteractEvent event) {
      if(event.entityPlayer.inventory.getCurrentItem() != null && event.entityPlayer.inventory.getCurrentItem().getItem() instanceof ItemOpStick) {
         ((ItemOpStick)event.entityPlayer.inventory.getCurrentItem().getItem()).clickedEntity(event.entityPlayer.worldObj, event.entityPlayer, event.target);
      }

   }

   @SubscribeEvent
   public void onEntityHurt(LivingAttackEvent event) {
      if(enabled && this.currentRound != null) {
         if(event.entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)event.entity;
            PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)player);
            DamageSource source = event.source;
            if(data.team == Team.spectators && source != DamageSource.generic) {
               event.setCanceled(true);
               return;
            }

            if(source instanceof EntityDamageSource && source.getEntity() instanceof EntityPlayerMP) {
               EntityPlayerMP attacker = (EntityPlayerMP)source.getEntity();
               PlayerData attackerData = PlayerHandler.getPlayerData((EntityPlayer)attacker);
               if(attackerData == null) {
                  return;
               }

               if(attacker == player) {
                  return;
               }

               if(attackerData.team == Team.spectators) {
                  event.setCanceled(true);
                  return;
               }

               if(attackerData.team == null && data.team != null || attackerData.team != null && data.team == null) {
                  event.setCanceled(true);
                  return;
               }

               if(attackerData.team != null && data.team != null && this.roundTimeLeft > 0 && !this.currentRound.gametype.playerCanAttack(attacker, attackerData.team, player, data.team)) {
                  event.setCanceled(true);
               }
            }
         }

      }
   }

   @SubscribeEvent
   public void onEntityKilled(LivingDeathEvent event) {
      if(enabled) {
         if(this.currentRound != null) {
            this.currentRound.gametype.entityKilled(event.entity, event.source);
            if(event.entity instanceof EntityPlayerMP) {
               this.currentRound.gametype.playerKilled((EntityPlayerMP)event.entity, event.source);
            }
         }

      }
   }

   @SubscribeEvent
   public void entityJoinedWorld(EntityJoinWorldEvent event) {
      if(event.entity instanceof ITeamBase) {
         this.registerBase((ITeamBase)event.entity);
      }

      if(event.entity instanceof ITeamObject) {
         this.objects.add((ITeamObject)event.entity);
      }

   }

   @SubscribeEvent
   public void playerUseEntity(EntityInteractEvent event) {
      if(enabled) {
         if(!event.entityPlayer.worldObj.isRemote) {
            ItemStack currentItem = event.entityPlayer.getCurrentEquippedItem();
            if(currentItem != null && currentItem.getItem() != null && currentItem.getItem() instanceof ItemOpStick) {
               if(event.target instanceof ITeamObject) {
                  ((ItemOpStick)currentItem.getItem()).clickedObject(event.entityPlayer.worldObj, (EntityPlayerMP)event.entityPlayer, (ITeamObject)event.target);
               }

               if(event.target instanceof ITeamBase) {
                  ((ItemOpStick)currentItem.getItem()).clickedBase(event.entityPlayer.worldObj, (EntityPlayerMP)event.entityPlayer, (ITeamBase)event.target);
               }
            } else if(this.currentRound != null) {
               if(event.target instanceof ITeamObject) {
                  this.currentRound.gametype.objectClickedByPlayer((ITeamObject)event.target, (EntityPlayerMP)event.entityPlayer);
               }

               if(event.target instanceof ITeamBase) {
                  this.currentRound.gametype.baseClickedByPlayer((ITeamBase)event.target, (EntityPlayerMP)event.entityPlayer);
               }
            }

         }
      }
   }

   @SubscribeEvent
   public void playerInteracted(PlayerInteractEvent event) {
      if(enabled) {
         if(event.action == Action.LEFT_CLICK_BLOCK && !event.entityPlayer.capabilities.allowEdit) {
            event.setCanceled(true);
         } else if(!event.entityPlayer.worldObj.isRemote) {
            TileEntity te = event.entityPlayer.worldObj.getTileEntity(event.x, event.y, event.z);
            if(te != null) {
               ItemStack currentItem = event.entityPlayer.getCurrentEquippedItem();
               if(currentItem != null && currentItem.getItem() != null && currentItem.getItem() instanceof ItemOpStick) {
                  if(te instanceof ITeamObject) {
                     ((ItemOpStick)currentItem.getItem()).clickedObject(event.entityPlayer.worldObj, (EntityPlayerMP)event.entityPlayer, (ITeamObject)te);
                  }

                  if(te instanceof ITeamBase) {
                     ((ItemOpStick)currentItem.getItem()).clickedBase(event.entityPlayer.worldObj, (EntityPlayerMP)event.entityPlayer, (ITeamBase)te);
                  }
               } else if(this.currentRound != null) {
                  if(te instanceof ITeamObject) {
                     this.currentRound.gametype.objectClickedByPlayer((ITeamObject)te, (EntityPlayerMP)event.entityPlayer);
                  }

                  if(te instanceof ITeamBase) {
                     this.currentRound.gametype.baseClickedByPlayer((ITeamBase)te, (EntityPlayerMP)event.entityPlayer);
                  }
               }
            }

         }
      }
   }

   @SubscribeEvent
   public void playerDrops(PlayerDropsEvent event) {
      ArrayList dropsToThrow = new ArrayList();
      Iterator var3;
      EntityItem entity;
      ItemStack stack;
      if(weaponDrops == 2) {
         var3 = event.drops.iterator();

         while(var3.hasNext()) {
            entity = (EntityItem)var3.next();
            stack = entity.getEntityItem();
            if(stack != null && stack.getItem() != null && stack.getItem() instanceof ItemGun) {
               EntityGunItem gunType = new EntityGunItem(entity);
               stack.stackSize = 0;
               boolean alreadyAdded = false;
               Iterator ammoEntity = dropsToThrow.iterator();

               while(ammoEntity.hasNext()) {
                  EntityItem ammoItemstack = (EntityItem)ammoEntity.next();
                  if(((ItemGun)stack.getItem()).type == ((ItemGun)ammoItemstack.getEntityItem().getItem()).type) {
                     alreadyAdded = true;
                  }
               }

               if(!alreadyAdded) {
                  event.entityPlayer.worldObj.spawnEntityInWorld(gunType);
                  dropsToThrow.add(gunType);
               }
            }
         }
      }

      var3 = dropsToThrow.iterator();

      while(var3.hasNext()) {
         entity = (EntityItem)var3.next();
         EntityGunItem stack1 = (EntityGunItem)entity;
         GunType gunType1 = ((ItemGun)stack1.getEntityItem().getItem()).type;
         Iterator alreadyAdded1 = event.drops.iterator();

         while(alreadyAdded1.hasNext()) {
            EntityItem ammoEntity1 = (EntityItem)alreadyAdded1.next();
            ItemStack ammoItemstack1 = ammoEntity1.getEntityItem();
            if(ammoItemstack1 != null && ammoItemstack1.getItem() instanceof ItemShootable) {
               ShootableType bulletType = ((ItemShootable)ammoItemstack1.getItem()).type;
               if(gunType1.isAmmo(bulletType)) {
                  stack1.ammoStacks.add(ammoItemstack1.copy());
                  ammoItemstack1.stackSize = 0;
               }
            }
         }
      }

      var3 = event.drops.iterator();

      while(var3.hasNext()) {
         entity = (EntityItem)var3.next();
         stack = entity.getEntityItem();
         if(stack != null && stack.getItem() != null && stack.stackSize > 0) {
            if(!(stack.getItem() instanceof ItemGun) && !(stack.getItem() instanceof ItemPlane) && !(stack.getItem() instanceof ItemVehicle) && !(stack.getItem() instanceof ItemAAGun) && !(stack.getItem() instanceof ItemBullet)) {
               if(stack.getItem() instanceof ItemTeamArmour && !armourDrops) {
                  dropsToThrow.add(entity);
               }
            } else if(weaponDrops != 1) {
               dropsToThrow.add(entity);
            }
         }
      }

      event.drops.removeAll(dropsToThrow);
   }

   @SubscribeEvent
   public void playerLoot(EntityItemPickupEvent event) {
      if(event.entity instanceof EntityPlayer) {
         ItemStack itemStack = event.item.getEntityItem();
         PlayerData data = PlayerHandler.getPlayerData(event.entityPlayer);
         if(enabled && this.currentRound != null && data != null && (data.team == Team.spectators || !this.currentRound.gametype.playerCanLoot(itemStack, InfoType.getType(itemStack), event.entityPlayer, data.team))) {
            event.setCanceled(true);
         }
      }

   }

   @SubscribeEvent
   public void onPlayerRespawn(PlayerEvent event) {
      if(event instanceof PlayerRespawnEvent) {
         this.respawnPlayer(event.player, false);
      }

      if(event instanceof PlayerLoggedOutEvent) {
         this.onPlayerLogout(event.player);
      }

      if(event instanceof PlayerLoggedInEvent) {
         this.onPlayerLogin(event.player);
      }

   }

   public void onPlayerLogin(EntityPlayer player) {
      if(enabled && this.currentRound != null) {
         if(player instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP)player;
            this.sendTeamsMenuToPlayer(playerMP);
            this.currentRound.gametype.playerJoined(playerMP);
         }

      }
   }

   public void onPlayerLogout(EntityPlayer player) {
      Iterator var2 = Team.teams.iterator();

      while(var2.hasNext()) {
         Team team = (Team)var2.next();
         team.removePlayer(player);
      }

   }

   public void respawnPlayer(EntityPlayer player, boolean firstSpawn) {
      if(!player.worldObj.isRemote) {
         if(enabled && this.currentRound != null) {
            EntityPlayerMP playerMP = (EntityPlayerMP)player;
            PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)playerMP);
            if(data != null && (!data.builder || !this.playerIsOp(playerMP))) {
               if(firstSpawn) {
                  Vec3 spawnPoint = this.currentRound.gametype.getSpawnPoint(playerMP);
                  if(spawnPoint != null) {
                     player.setPositionAndUpdate(spawnPoint.xCoord, spawnPoint.yCoord, spawnPoint.zCoord);
                  }
               }

               this.setPlayersNextSpawnpoint(playerMP);
               if(forceAdventureMode) {
                  player.setGameType(GameType.ADVENTURE);
               }

               this.resetInventory(player);
               this.currentRound.gametype.playerRespawned((EntityPlayerMP)player);
            }
         }
      }
   }

   private void setPlayersNextSpawnpoint(EntityPlayerMP player, ChunkCoordinates coords) {
      player.setSpawnChunk(coords, true);
   }

   private void setPlayersNextSpawnpoint(EntityPlayerMP player) {
      if(enabled && this.currentRound != null) {
         PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)player);
         Vec3 spawnPoint = this.currentRound.gametype.getSpawnPoint(player);
         if(spawnPoint != null) {
            this.setPlayersNextSpawnpoint(player, new ChunkCoordinates(MathHelper.floor_double(spawnPoint.xCoord), MathHelper.floor_double(spawnPoint.yCoord) + 1, MathHelper.floor_double(spawnPoint.zCoord)));
         } else {
            FlansMod.log("Could not find spawn point for " + player.getDisplayName() + " on team " + (data.newTeam == null?"null":data.newTeam.name));
         }

      }
   }

   public void forceRespawn(EntityPlayerMP player) {
      if(!this.playerIsOp(player) || !PlayerHandler.getPlayerData((EntityPlayer)player).builder) {
         player.inventory.armorInventory = new ItemStack[4];
         player.inventory.mainInventory = new ItemStack[36];
         player.heal(9001.0F);
         if(forceAdventureMode) {
            player.setGameType(GameType.ADVENTURE);
         }

         this.respawnPlayer(player, true);
      }
   }

   public void sendTeamsMenuToPlayer(EntityPlayerMP player) {
      this.sendTeamsMenuToPlayer(player, false);
   }

   public void sendTeamsMenuToPlayer(EntityPlayerMP player, boolean info) {
      if(enabled && this.currentRound != null && this.currentRound.teams != null) {
         Team[] availableTeams = this.currentRound.gametype.getTeamsCanSpawnAs(this.currentRound, player);
         boolean playerIsOp = MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile());
         Team[] allAvailableTeams = new Team[availableTeams.length + (playerIsOp?2:1)];
         System.arraycopy(availableTeams, 0, allAvailableTeams, 0, availableTeams.length);
         allAvailableTeams[availableTeams.length] = Team.spectators;
         sendPacketToPlayer(new PacketTeamSelect(allAvailableTeams, info), player);
      }
   }

   public void sendClassMenuToPlayer(EntityPlayerMP player) {
      Team team = PlayerHandler.getPlayerData((EntityPlayer)player).newTeam;
      if(team == null) {
         this.sendTeamsMenuToPlayer(player);
      } else if(team != Team.spectators && team.classes.size() > 0) {
         sendPacketToPlayer(new PacketTeamSelect((PlayerClass[])team.classes.toArray(new PlayerClass[team.classes.size()])), player);
      }

   }

   public boolean playerIsOp(EntityPlayer player) {
      return MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile());
   }

   public boolean autoBalance() {
      return (this.currentRound == null || this.currentRound.gametype.shouldAutobalance()) && autoBalance;
   }

   public void playerSelectedTeam(EntityPlayerMP player, String teamName) {
      if(enabled && this.currentRound != null) {
         PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)player);
         data.builder = false;
         if(teamName.equals("null")) {
            if(this.playerIsOp(player)) {
               data.team = null;
               data.builder = true;
               return;
            }

            teamName = "spectators";
         }

         Team selectedTeam = Team.getTeam(teamName);
         if(selectedTeam == null) {
            selectedTeam = Team.spectators;
         }

         boolean isValid = selectedTeam == Team.spectators;
         Team[] validTeams = this.currentRound.gametype.getTeamsCanSpawnAs(this.currentRound, player);
         Team[] otherTeam = validTeams;
         int var8 = validTeams.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            Team validTeam = otherTeam[var9];
            if(selectedTeam == validTeam) {
               isValid = true;
            }
         }

         if(!isValid) {
            player.addChatMessage(new ChatComponentText("You may not join " + selectedTeam.name + " for it is invalid. Please try again"));
            FlansMod.log(player.getCommandSenderName() + " tried to spawn on an invalid team : " + selectedTeam.name);
            selectedTeam = Team.spectators;
         }

         if(selectedTeam == Team.spectators) {
            messageAll(player.getCommandSenderName() + " joined §" + selectedTeam.textColour + selectedTeam.name);
            if(data.team != null) {
               data.team.removePlayer((EntityPlayer)player);
            }

            data.newTeam = data.team = Team.spectators;
            player.inventory.armorInventory = new ItemStack[4];
            player.inventory.mainInventory = new ItemStack[36];
            data.team.addPlayer((EntityPlayer)player);
            player.heal(9001.0F);
            this.respawnPlayer(player, true);
         } else {
            Team var11 = this.currentRound.getOtherTeam(selectedTeam);
            if(this.autoBalance() && selectedTeam.members.size() > var11.members.size() + 1) {
               player.addChatMessage(new ChatComponentText("You may not join " + selectedTeam.name + " due to imbalance. Please try again"));
               this.sendTeamsMenuToPlayer(player);
               return;
            }

            data.newTeam = selectedTeam;
            this.sendClassMenuToPlayer(player);
         }

         this.currentRound.gametype.playerChoseTeam(player, data.team, selectedTeam);
      }
   }

   public void playerSelectedClass(EntityPlayerMP player, String className) {
      if(enabled && this.currentRound != null) {
         PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)player);
         PlayerClass playerClass = PlayerClass.getClass(className);
         if(!data.newTeam.classes.contains(playerClass)) {
            player.addChatMessage(new ChatComponentText("You may not select " + playerClass.name + ". Please try again"));
            FlansMod.log(player.getCommandSenderName() + " tried to pick an invalid class : " + playerClass.name);
         } else {
            if(data.team == data.newTeam && data.playerClass != playerClass) {
               this.currentRound.gametype.playerChoseNewClass(player, playerClass);
               data.newPlayerClass = playerClass;
               player.addChatMessage(new ChatComponentText("You will respawn with the " + playerClass.name + " class"));
            } else if(data.team != null && data.team != data.newTeam) {
               messageAll(player.getCommandSenderName() + " switched to §" + data.newTeam.textColour + data.newTeam.name);
               this.currentRound.gametype.playerDefected(player, data.team, data.newTeam);
               this.setPlayersNextSpawnpoint(player);
               player.attackEntityFrom(DamageSource.generic, 10000.0F);
               if(data.team != null) {
                  data.team.removePlayer((EntityPlayer)player);
               }

               data.newTeam.addPlayer((EntityPlayer)player);
               data.team = data.newTeam;
               data.newPlayerClass = playerClass;
            } else if(data.team == null) {
               messageAll(player.getCommandSenderName() + " joined §" + data.newTeam.textColour + data.newTeam.name);
               this.currentRound.gametype.playerEnteredTheGame(player, data.newTeam, playerClass);
               data.newTeam.addPlayer((EntityPlayer)player);
               data.team = data.newTeam;
               data.newPlayerClass = playerClass;
               this.currentRound.gametype.playerChoseNewClass(player, playerClass);
               this.respawnPlayer(player, true);
            }

         }
      }
   }

   public void resetInventory(EntityPlayer player) {
      Team team = PlayerHandler.getPlayerData(player).team;
      PlayerClass playerClass = PlayerHandler.getPlayerData(player).getPlayerClass();
      if(team != null) {
         player.inventory.armorInventory = new ItemStack[4];
         player.inventory.mainInventory = new ItemStack[36];
         if(team.hat != null) {
            player.inventory.armorInventory[3] = team.hat.copy();
         }

         if(team.chest != null) {
            player.inventory.armorInventory[2] = team.chest.copy();
         }

         if(team.legs != null) {
            player.inventory.armorInventory[1] = team.legs.copy();
         }

         if(team.shoes != null) {
            player.inventory.armorInventory[0] = team.shoes.copy();
         }

         if(playerClass != null) {
            if(playerClass.hat != null) {
               player.inventory.armorInventory[3] = playerClass.hat.copy();
            }

            if(playerClass.chest != null) {
               player.inventory.armorInventory[2] = playerClass.chest.copy();
            }

            if(playerClass.legs != null) {
               player.inventory.armorInventory[1] = playerClass.legs.copy();
            }

            if(playerClass.shoes != null) {
               player.inventory.armorInventory[0] = playerClass.shoes.copy();
            }

            Iterator i = playerClass.startingItems.iterator();

            ItemStack stack;
            while(i.hasNext()) {
               stack = (ItemStack)i.next();
               player.inventory.addItemStackToInventory(stack.copy());
            }

            for(int var6 = 0; var6 < player.inventory.getSizeInventory(); ++var6) {
               stack = player.inventory.getStackInSlot(var6);
               if(stack != null && stack.getItem() instanceof ItemGun) {
                  ((ItemGun)stack.getItem()).reload(stack, ((ItemGun)stack.getItem()).type, player.worldObj, player, true, false);
               }
            }

         }
      }
   }

   @SubscribeEvent
   public void chunkLoaded(ChunkDataEvent event) {
      Chunk chunk = event.getChunk();
      List[] var3 = chunk.entityLists;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         List list = var3[var5];
         Iterator var7 = list.iterator();

         while(var7.hasNext()) {
            Entity entity = (Entity)var7.next();
            if(entity instanceof ITeamBase) {
               this.bases.add((ITeamBase)entity);
               if(((ITeamBase)entity).getBaseID() > this.nextBaseID) {
                  FlansMod.log("Loaded base with ID higher than the supposed highest ID. Adjusted highest ID");
                  this.nextBaseID = ((ITeamBase)entity).getBaseID();
               }
            }

            if(entity instanceof ITeamObject) {
               this.objects.add((ITeamObject)entity);
            }
         }
      }

   }

   @SubscribeEvent
   public void worldData(WorldEvent event) {
      if(!event.world.isRemote) {
         if(event instanceof Load) {
            this.loadPerWorldData(event, event.world);
            this.savePerWorldData(event, event.world);
         }

         if(event instanceof Save) {
            this.savePerWorldData(event, event.world);
         }

      }
   }

   private void loadPerWorldData(Event event, World world) {
      this.reset();
      File file = new File(world.getSaveHandler().getWorldDirectory(), "teams_" + world.provider.getDimensionName() + ".dat");
      if(this.checkFileExists(file)) {
         try {
            NBTTagCompound e = CompressedStreamTools.read(new DataInputStream(new FileInputStream(file)));
            this.nextBaseID = e.getInteger("NextBaseID");

            int type;
            for(type = 0; type < e.getInteger("NumberOfMaps"); ++type) {
               TeamsMap round = new TeamsMap(world, e.getCompoundTag("Map_" + type));
               this.maps.put(round.shortName, round);
            }

            if(this.maps.size() == 0) {
               this.maps.put("default" + world.getWorldInfo().getVanillaDimension(), new TeamsMap(world, "default" + world.getWorldInfo().getVanillaDimension(), "Default " + world.getWorldInfo().getWorldName()));
            }

            for(type = 0; type < e.getInteger("RoundsSize"); ++type) {
               TeamsRound var10 = new TeamsRound(e.getCompoundTag("Round_" + type));
               this.rounds.add(var10);
            }

            enabled = e.getBoolean("Enabled");
            voting = e.getBoolean("Voting");
            votingTime = e.getInteger("VotingTime");
            scoreDisplayTime = e.getInteger("ScoreTime");
            bombsEnabled = e.getBoolean("Bombs");
            bulletsEnabled = e.getBoolean("Bullets");
            explosions = e.getBoolean("Explosions");
            forceAdventureMode = e.getBoolean("ForceAdventure");
            canBreakGuns = e.getBoolean("CanBreakGuns");
            canBreakGlass = e.getBoolean("CanBreakGlass");
            armourDrops = e.getBoolean("ArmourDrops");
            weaponDrops = e.getInteger("WeaponDrops");
            vehiclesNeedFuel = e.getBoolean("NeedFuel");
            mgLife = e.getInteger("MGLife");
            aaLife = e.getInteger("AALife");
            vehicleLife = e.getInteger("VehicleLife");
            mechaLove = e.getInteger("MechaLove");
            planeLife = e.getInteger("PlaneLife");
            driveablesBreakBlocks = e.getBoolean("BreakBlocks");
            if(enabled && this.rounds.size() > 0) {
               this.start();
            }
         } catch (Exception var7) {
            FlansMod.log("Failed to load from teams.dat");
            var7.printStackTrace();
         }

         Iterator var8 = InfoType.infoTypes.iterator();

         while(var8.hasNext()) {
            InfoType var9 = (InfoType)var8.next();
            var9.onWorldLoad(world);
         }

      }
   }

   private void savePerWorldData(Event event, World world) {
      File file = new File(world.getSaveHandler().getWorldDirectory(), "teams_" + world.provider.getDimensionName() + ".dat");
      this.checkFileExists(file);

      try {
         NBTTagCompound e = new NBTTagCompound();
         e.setInteger("NextBaseID", this.nextBaseID);
         e.setInteger("NumberOfMaps", this.maps.size());
         int i;
         if(this.maps != null) {
            i = 0;

            for(Iterator gametype = this.maps.values().iterator(); gametype.hasNext(); ++i) {
               TeamsMap roundTags = (TeamsMap)gametype.next();
               NBTTagCompound mapTags = new NBTTagCompound();
               roundTags.writeToNBT(mapTags);
               e.setTag("Map_" + i, mapTags);
            }
         }

         if(this.rounds != null) {
            e.setInteger("RoundsSize", this.rounds.size());

            for(i = 0; i < this.rounds.size(); ++i) {
               TeamsRound var11 = (TeamsRound)this.rounds.get(i);
               if(var11 != null) {
                  NBTTagCompound var13 = new NBTTagCompound();
                  var11.writeToNBT(var13);
                  e.setTag("Round_" + i, var13);
               }
            }
         } else {
            e.setInteger("RoundsSize", 0);
         }

         if(this.currentRound != null) {
            e.setInteger("CurrentRound", this.rounds.indexOf(this.currentRound));
         }

         Iterator var10 = Gametype.gametypes.values().iterator();

         while(var10.hasNext()) {
            Gametype var12 = (Gametype)var10.next();
            var12.saveToNBT(e);
         }

         e.setBoolean("Enabled", enabled);
         e.setBoolean("Voting", voting);
         e.setInteger("VotingTime", votingTime);
         e.setInteger("ScoreTime", scoreDisplayTime);
         e.setBoolean("Bombs", bombsEnabled);
         e.setBoolean("Bullets", bulletsEnabled);
         e.setBoolean("Explosions", explosions);
         e.setBoolean("ForceAdventure", forceAdventureMode);
         e.setBoolean("CanBreakGuns", canBreakGuns);
         e.setBoolean("CanBreakGlass", canBreakGlass);
         e.setBoolean("ArmourDrops", armourDrops);
         e.setInteger("WeaponDrops", weaponDrops);
         e.setBoolean("NeedFuel", vehiclesNeedFuel);
         e.setInteger("MGLife", mgLife);
         e.setInteger("AALife", aaLife);
         e.setInteger("VehicleLife", vehicleLife);
         e.setInteger("MechaLove", mechaLove);
         e.setInteger("PlaneLife", planeLife);
         e.setBoolean("BreakBlocks", driveablesBreakBlocks);
         CompressedStreamTools.write(e, new DataOutputStream(new FileOutputStream(file)));
      } catch (Exception var9) {
         FlansMod.log("Failed to save to teams.dat");
         var9.printStackTrace();
      }

   }

   private boolean checkFileExists(File file) {
      if(!file.exists()) {
         try {
            file.createNewFile();
            FlansMod.log("Created new file");
         } catch (Exception var3) {
            FlansMod.log("Failed to create file");
            FlansMod.log(file.getAbsolutePath());
            var3.printStackTrace();
         }

         return false;
      } else {
         return true;
      }
   }

   public void resetScores() {
      Iterator var1 = Team.teams.iterator();

      while(var1.hasNext()) {
         Team player = (Team)var1.next();
         player.score = 0;
         player.members.clear();
      }

      var1 = getPlayers().iterator();

      while(var1.hasNext()) {
         EntityPlayer player1 = (EntityPlayer)var1.next();
         if(PlayerHandler.getPlayerData(player1) != null) {
            PlayerHandler.getPlayerData(player1).resetScore();
         }
      }

   }

   public ITeamBase getBase(int ID) {
      Iterator var2 = this.bases.iterator();

      ITeamBase base;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         base = (ITeamBase)var2.next();
      } while(base.getBaseID() != ID);

      return base;
   }

   public void registerBase(ITeamBase base) {
      if(base.getBaseID() == 0) {
         base.setBaseID(this.nextBaseID++);
      }

      this.bases.add(base);
   }

   public void registerObject(ITeamObject obj) {
      this.objects.add(obj);
   }

   public EntityPlayerMP getPlayer(String username) {
      return MinecraftServer.getServer().getConfigurationManager().func_152612_a(username);
   }

   public static void log(String s) {
      FlansMod.log("Teams Info : " + s);
   }

   public static void messagePlayer(EntityPlayerMP player, String s) {
      player.addChatComponentMessage(new ChatComponentText(s));
   }

   public static void messageAll(String s) {
      FlansMod.log("Teams Announcement : " + s);
      Iterator var1 = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();

      while(var1.hasNext()) {
         EntityPlayerMP player = (EntityPlayerMP)var1.next();
         player.addChatComponentMessage(new ChatComponentText(s));
      }

   }

   public static void sendPacketToPlayer(PacketBase packet, EntityPlayerMP player) {
      FlansMod.getPacketHandler().sendTo(packet, player);
   }

   public static List getPlayers() {
      return MinecraftServer.getServer().getConfigurationManager().playerEntityList;
   }

   public Team getTeam(int spawnerTeamID) {
      return enabled && this.currentRound != null && spawnerTeamID != 0?(spawnerTeamID == 1?Team.spectators:this.currentRound.teams[spawnerTeamID - 2]):null;
   }

   public TeamsMap getMapFromFullName(String string) {
      Iterator var2 = this.maps.values().iterator();

      TeamsMap map;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         map = (TeamsMap)var2.next();
      } while(!map.name.equals(string));

      return map;
   }

}
