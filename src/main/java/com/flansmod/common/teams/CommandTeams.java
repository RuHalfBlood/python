package com.flansmod.common.teams;

import com.flansmod.common.FlansMod;
import com.flansmod.common.teams.Gametype;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.teams.TeamsMap;
import com.flansmod.common.teams.TeamsRound;
import java.util.Iterator;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandTeams extends CommandBase {

   public static TeamsManager teamsManager = TeamsManager.getInstance();


   public String getCommandName() {
      return "teams";
   }

   public void processCommand(ICommandSender sender, String[] split) {
      if(teamsManager == null) {
         sender.addChatMessage(new ChatComponentText("Teams mod is broken. You will need to look at the server side logs to see what\'s wrong"));
      } else if(split != null && split.length != 0 && !split[0].equals("help") && !split[0].equals("?")) {
         TeamsManager var10000;
         if(split[0].equals("off")) {
            teamsManager.currentRound = null;
            var10000 = teamsManager;
            TeamsManager.enabled = false;
            TeamsManager.messageAll("Flan\'s Teams Mod disabled");
         } else if(split[0].equals("on")) {
            var10000 = teamsManager;
            TeamsManager.enabled = true;
            TeamsManager.messageAll("Flan\'s Teams Mod enabled");
         } else {
            var10000 = teamsManager;
            if(!TeamsManager.enabled) {
               sender.addChatMessage(new ChatComponentText("Teams mod is disabled. Try /teams on"));
            } else if(split[0].equals("survival")) {
               var10000 = teamsManager;
               TeamsManager.explosions = true;
               var10000 = teamsManager;
               TeamsManager.driveablesBreakBlocks = true;
               var10000 = teamsManager;
               TeamsManager.bombsEnabled = true;
               var10000 = teamsManager;
               TeamsManager.bulletsEnabled = true;
               var10000 = teamsManager;
               TeamsManager.forceAdventureMode = false;
               var10000 = teamsManager;
               TeamsManager.overrideHunger = false;
               var10000 = teamsManager;
               TeamsManager.canBreakGuns = true;
               var10000 = teamsManager;
               TeamsManager.canBreakGlass = true;
               var10000 = teamsManager;
               TeamsManager.armourDrops = true;
               var10000 = teamsManager;
               TeamsManager.weaponDrops = 1;
               var10000 = teamsManager;
               TeamsManager.vehiclesNeedFuel = true;
               var10000 = teamsManager;
               var10000 = teamsManager;
               var10000 = teamsManager;
               var10000 = teamsManager;
               var10000 = teamsManager;
               TeamsManager.mechaLove = 0;
               TeamsManager.aaLife = 0;
               TeamsManager.vehicleLife = 0;
               TeamsManager.planeLife = 0;
               TeamsManager.mgLife = 0;
               var10000 = teamsManager;
               TeamsManager.messageAll("Flan\'s Mod switching to survival presets");
            } else if(split[0].equals("arena")) {
               var10000 = teamsManager;
               TeamsManager.explosions = false;
               var10000 = teamsManager;
               TeamsManager.driveablesBreakBlocks = false;
               var10000 = teamsManager;
               TeamsManager.bombsEnabled = true;
               var10000 = teamsManager;
               TeamsManager.bulletsEnabled = true;
               var10000 = teamsManager;
               TeamsManager.forceAdventureMode = true;
               var10000 = teamsManager;
               TeamsManager.overrideHunger = true;
               var10000 = teamsManager;
               TeamsManager.canBreakGuns = true;
               var10000 = teamsManager;
               TeamsManager.canBreakGlass = false;
               var10000 = teamsManager;
               TeamsManager.armourDrops = false;
               var10000 = teamsManager;
               TeamsManager.weaponDrops = 2;
               var10000 = teamsManager;
               TeamsManager.vehiclesNeedFuel = false;
               var10000 = teamsManager;
               var10000 = teamsManager;
               var10000 = teamsManager;
               var10000 = teamsManager;
               var10000 = teamsManager;
               TeamsManager.mechaLove = 120;
               TeamsManager.aaLife = 120;
               TeamsManager.vehicleLife = 120;
               TeamsManager.planeLife = 120;
               TeamsManager.mgLife = 120;
               TeamsManager.messageAll("Flan\'s Mod switching to arena mode presets");
            } else {
               Iterator bmn;
               Gametype var13;
               if(split[0].equals("listGametypes")) {
                  sender.addChatMessage(new ChatComponentText("§2Showing all avaliable gametypes"));
                  sender.addChatMessage(new ChatComponentText("§2To pick a gametype, use \"/teams setGametype <gametype>\" with the name in brackets"));
                  bmn = Gametype.gametypes.values().iterator();

                  while(bmn.hasNext()) {
                     var13 = (Gametype)bmn.next();
                     sender.addChatMessage(new ChatComponentText("§f" + var13.name + " (" + var13.shortName + ")"));
                  }

               } else if(split[0].equals("listMaps")) {
                  if(teamsManager.maps == null) {
                     sender.addChatMessage(new ChatComponentText("The map list is null"));
                  } else {
                     sender.addChatMessage(new ChatComponentText("§2Listing maps"));
                     bmn = teamsManager.maps.values().iterator();

                     while(bmn.hasNext()) {
                        TeamsMap var22 = (TeamsMap)bmn.next();
                        sender.addChatMessage(new ChatComponentText((teamsManager.currentRound != null && var22 == teamsManager.currentRound.map?"§4":"") + var22.name + " (" + var22.shortName + ")"));
                     }

                  }
               } else if(split[0].equals("addMap")) {
                  if(split.length < 3) {
                     sender.addChatMessage(new ChatComponentText("You need to specify a map name"));
                  } else {
                     String var17 = split[1];
                     String var18 = split[2];

                     for(int var21 = 3; var21 < split.length; ++var21) {
                        var18 = var18 + " " + split[var21];
                     }

                     teamsManager.maps.put(var17, new TeamsMap(sender.getEntityWorld(), var17, var18));
                     sender.addChatMessage(new ChatComponentText("Added new map : " + var18 + " (" + var17 + ")"));
                  }
               } else if(split[0].equals("removeMap")) {
                  if(split.length != 2) {
                     sender.addChatMessage(new ChatComponentText("You need to specify a map\'s short name"));
                  } else {
                     if(teamsManager.maps.containsKey(split[1])) {
                        teamsManager.maps.remove(split[1]);
                        sender.addChatMessage(new ChatComponentText("Removed map " + split[1]));
                     } else {
                        sender.addChatMessage(new ChatComponentText("Map (" + split[1] + ") not found"));
                     }

                  }
               } else if(split[0].equals("setRound")) {
                  if(split.length != 2) {
                     sender.addChatMessage(new ChatComponentText("You need to specify the round index (see /teams listRounds)"));
                  } else {
                     TeamsRound var15 = (TeamsRound)teamsManager.rounds.get(Integer.parseInt(split[1]));
                     if(var15 != null) {
                        teamsManager.nextRound = var15;
                        TeamsManager.messageAll("§2Next round will be " + var15.gametype.shortName + " in " + var15.map.name);
                     }

                  }
               } else if(!split[0].equals("listTeams") && !split[0].equals("listAllTeams")) {
                  if(!split[0].equals("getSticks") && !split[0].equals("getOpSticks") && !split[0].equals("getOpKit")) {
                     if(split[0].toLowerCase().equals("autobalance")) {
                        if(split.length != 2) {
                           sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                        } else {
                           TeamsManager.autoBalance = Boolean.parseBoolean(split[1]);
                           sender.addChatMessage(new ChatComponentText("Autobalance is now " + (TeamsManager.autoBalance?"enabled":"disabled")));
                        }
                     } else if(split[0].equals("useRotation")) {
                        if(split.length != 2) {
                           sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                        } else {
                           TeamsManager.voting = !Boolean.parseBoolean(split[1]);
                           sender.addChatMessage(new ChatComponentText("Voting is now " + (TeamsManager.voting?"enabled":"disabled")));
                        }
                     } else if(split[0].equals("voting")) {
                        if(split.length != 2) {
                           sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                        } else {
                           TeamsManager.voting = Boolean.parseBoolean(split[1]);
                           sender.addChatMessage(new ChatComponentText("Voting is now " + (TeamsManager.voting?"enabled":"disabled")));
                        }
                     } else {
                        int i;
                        int var10;
                        if(!split[0].equals("listRounds") && !split[0].equals("listRotation")) {
                           if(!split[0].equals("removeRound") && !split[0].equals("removeMapFromRotation") && !split[0].equals("removeFromRotation") && !split[0].equals("removeRotation")) {
                              if(!split[0].equals("addMapToRotation") && !split[0].equals("addToRotation") && !split[0].equals("addRotation") && !split[0].equals("addRound")) {
                                 if(!split[0].equals("start") && !split[0].equals("begin")) {
                                    if(!split[0].equals("nextMap") && !split[0].equals("next") && !split[0].equals("nextRound")) {
                                       if(!split[0].equals("forceAdventure") && !split[0].equals("forceAdventureMode")) {
                                          if(!split[0].equals("overrideHunger") && !split[0].equals("noHunger")) {
                                             if(split[0].equals("explosions")) {
                                                if(split.length != 2) {
                                                   sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                                                } else {
                                                   TeamsManager.explosions = Boolean.parseBoolean(split[1]);
                                                   sender.addChatMessage(new ChatComponentText("Expolsions are now " + (TeamsManager.explosions?"enabled":"disabled")));
                                                }
                                             } else if(!split[0].equals("bombs") && !split[0].equals("allowBombs")) {
                                                if(!split[0].equals("bullets") && !split[0].equals("bulletsEnabled")) {
                                                   if(split[0].equals("canBreakGuns")) {
                                                      if(split.length != 2) {
                                                         sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                                                      } else {
                                                         TeamsManager.canBreakGuns = Boolean.parseBoolean(split[1]);
                                                         sender.addChatMessage(new ChatComponentText("AAGuns and MGs can " + (TeamsManager.canBreakGuns?"now":"no longer") + " be broken"));
                                                      }
                                                   } else if(split[0].equals("canBreakGlass")) {
                                                      if(split.length != 2) {
                                                         sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                                                      } else {
                                                         TeamsManager.canBreakGlass = Boolean.parseBoolean(split[1]);
                                                         sender.addChatMessage(new ChatComponentText("Glass and glowstone can " + (TeamsManager.canBreakGlass?"now":"no longer") + " be broken"));
                                                      }
                                                   } else if(!split[0].equals("armourDrops") && !split[0].equals("armorDrops")) {
                                                      if(split[0].equals("weaponDrops")) {
                                                         if(split.length != 2) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <on/off/smart>"));
                                                         } else {
                                                            if(split[1].toLowerCase().equals("on")) {
                                                               TeamsManager.weaponDrops = 1;
                                                               sender.addChatMessage(new ChatComponentText("Weapons will be dropped normally"));
                                                            } else if(split[1].toLowerCase().equals("off")) {
                                                               TeamsManager.weaponDrops = 0;
                                                               sender.addChatMessage(new ChatComponentText("Weapons will be not be dropped"));
                                                            } else if(split[1].toLowerCase().equals("smart")) {
                                                               TeamsManager.weaponDrops = 2;
                                                               sender.addChatMessage(new ChatComponentText("Smart drops enabled"));
                                                            }

                                                         }
                                                      } else if(split[0].equals("fuelNeeded")) {
                                                         if(split.length != 2) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                                                         } else {
                                                            TeamsManager.vehiclesNeedFuel = Boolean.parseBoolean(split[1]);
                                                            sender.addChatMessage(new ChatComponentText("Vehicles will " + (TeamsManager.vehiclesNeedFuel?"now":"no longer") + " require fuel"));
                                                         }
                                                      } else if(split[0].equals("mgLife")) {
                                                         if(split.length != 2) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <time>"));
                                                         } else {
                                                            TeamsManager.mgLife = Integer.parseInt(split[1]);
                                                            if(TeamsManager.mgLife > 0) {
                                                               sender.addChatMessage(new ChatComponentText("MGs will despawn after " + TeamsManager.mgLife + " seconds"));
                                                            } else {
                                                               sender.addChatMessage(new ChatComponentText("MGs will not despawn"));
                                                            }

                                                         }
                                                      } else if(split[0].equals("planeLife")) {
                                                         if(split.length != 2) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <time>"));
                                                         } else {
                                                            TeamsManager.planeLife = Integer.parseInt(split[1]);
                                                            if(TeamsManager.planeLife > 0) {
                                                               sender.addChatMessage(new ChatComponentText("Planes will despawn after " + TeamsManager.planeLife + " seconds"));
                                                            } else {
                                                               sender.addChatMessage(new ChatComponentText("Planes will not despawn"));
                                                            }

                                                         }
                                                      } else if(split[0].equals("vehicleLife")) {
                                                         if(split.length != 2) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <time>"));
                                                         } else {
                                                            TeamsManager.vehicleLife = Integer.parseInt(split[1]);
                                                            if(TeamsManager.vehicleLife > 0) {
                                                               sender.addChatMessage(new ChatComponentText("Vehicles will despawn after " + TeamsManager.vehicleLife + " seconds"));
                                                            } else {
                                                               sender.addChatMessage(new ChatComponentText("Vehicles will not despawn"));
                                                            }

                                                         }
                                                      } else if(split[0].equals("mechaLife")) {
                                                         if(split.length != 2) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <time>"));
                                                         } else {
                                                            TeamsManager.mechaLove = Integer.parseInt(split[1]);
                                                            if(TeamsManager.mechaLove > 0) {
                                                               sender.addChatMessage(new ChatComponentText("Mechas will despawn after " + TeamsManager.mechaLove + " seconds"));
                                                            } else {
                                                               sender.addChatMessage(new ChatComponentText("Mechas will not despawn"));
                                                            }

                                                         }
                                                      } else if(split[0].equals("aaLife")) {
                                                         if(split.length != 2) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <time>"));
                                                         } else {
                                                            TeamsManager.aaLife = Integer.parseInt(split[1]);
                                                            if(TeamsManager.aaLife > 0) {
                                                               sender.addChatMessage(new ChatComponentText("AA Guns will despawn after " + TeamsManager.aaLife + " seconds"));
                                                            } else {
                                                               sender.addChatMessage(new ChatComponentText("AA Guns will not despawn"));
                                                            }

                                                         }
                                                      } else if(split[0].equals("vehiclesBreakBlocks")) {
                                                         if(split.length != 2) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                                                         } else {
                                                            TeamsManager.driveablesBreakBlocks = Boolean.parseBoolean(split[1]);
                                                            sender.addChatMessage(new ChatComponentText("Vehicles will " + (TeamsManager.driveablesBreakBlocks?"now":"no longer") + " break blocks"));
                                                         }
                                                      } else if(split[0].equals("vehiclesCanZoom")) {
                                                         if(split.length != 2) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                                                         } else {
                                                            TeamsManager.allowVehicleZoom = Boolean.parseBoolean(split[1]);
                                                            sender.addChatMessage(new ChatComponentText("Vehicles will " + (TeamsManager.allowVehicleZoom?"now":"no longer") + " be able to zoom"));
                                                         }
                                                      } else if(split[0].equals("scoreDisplayTime")) {
                                                         if(split.length != 2) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <time>"));
                                                         } else {
                                                            TeamsManager.scoreDisplayTime = Integer.parseInt(split[1]) * 20;
                                                            sender.addChatMessage(new ChatComponentText("Score summary menu will appear for " + TeamsManager.scoreDisplayTime / 20 + " seconds"));
                                                         }
                                                      } else if(split[0].equals("votingTime")) {
                                                         if(split.length != 2) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <time>"));
                                                         } else {
                                                            TeamsManager.votingTime = Integer.parseInt(split[1]) * 20;
                                                            sender.addChatMessage(new ChatComponentText("Voting menu will appear for " + TeamsManager.votingTime / 20 + " seconds"));
                                                         }
                                                      } else if(split[0].toLowerCase().equals("autobalancetime")) {
                                                         if(split.length != 2) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <time>"));
                                                         } else {
                                                            TeamsManager.autoBalanceInterval = Integer.parseInt(split[1]) * 20;
                                                            sender.addChatMessage(new ChatComponentText("Autobalance will now occur every " + TeamsManager.autoBalanceInterval / 20 + " seconds"));
                                                         }
                                                      } else if(split[0].equals("setVariable")) {
                                                         if(TeamsManager.getInstance().currentRound == null) {
                                                            sender.addChatMessage(new ChatComponentText("There is no gametype to set variables for"));
                                                         } else if(split.length != 3) {
                                                            sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams setVariable <variable> <value>"));
                                                         } else {
                                                            if(TeamsManager.getInstance().currentRound.gametype.setVariable(split[1], split[2])) {
                                                               sender.addChatMessage(new ChatComponentText("Set variable " + split[1] + " in gametype " + TeamsManager.getInstance().currentRound.gametype.shortName + " to " + split[2]));
                                                            } else {
                                                               sender.addChatMessage(new ChatComponentText("Variable " + split[1] + " did not exist in gametype " + TeamsManager.getInstance().currentRound.gametype.shortName));
                                                            }

                                                         }
                                                      } else {
                                                         int var14;
                                                         if(split[0].equals("ping")) {
                                                            var10 = 0;
                                                            var14 = 0;
                                                            List var20 = TeamsManager.getPlayers();
                                                            Iterator var16 = var20.iterator();

                                                            while(var16.hasNext()) {
                                                               EntityPlayer player = (EntityPlayer)var16.next();
                                                               if(player instanceof EntityPlayerMP) {
                                                                  EntityPlayerMP pm = (EntityPlayerMP)player;
                                                                  sender.addChatMessage(new ChatComponentText("[Ping] " + pm.ping + " : " + pm.getDisplayName()));
                                                                  if(pm.ping > 0) {
                                                                     var10 += pm.ping;
                                                                     ++var14;
                                                                  }
                                                               }
                                                            }

                                                            if(var14 > 0) {
                                                               sender.addChatMessage(new ChatComponentText("[PingAverage] " + String.format("%.1f", new Object[]{Double.valueOf((double)var10 / (double)var14)})));
                                                            }

                                                         } else if(split[0].equals("bltss")) {
                                                            if(split.length != 3) {
                                                               sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams bltss <0 ... 100> <0 ... 1000>"));
                                                               sender.addChatMessage(new ChatComponentText("Bullet use player snapshot = Min[default=0] + (Ping / Divisor[default=50])"));
                                                            } else {
                                                               var10 = Integer.parseInt(split[1]);
                                                               var14 = Integer.parseInt(split[2]);
                                                               if(var10 < 0) {
                                                                  var10 = 0;
                                                               }

                                                               if(var10 > 100) {
                                                                  var10 = 100;
                                                               }

                                                               if(var14 < 0) {
                                                                  var14 = 0;
                                                               }

                                                               if(var14 > 1000) {
                                                                  var14 = 1000;
                                                               }

                                                               if(TeamsManager.bulletSnapshotMin != var10 || TeamsManager.bulletSnapshotDivisor != var14) {
                                                                  TeamsManager.bulletSnapshotMin = var10;
                                                                  TeamsManager.bulletSnapshotDivisor = var14;
                                                                  FlansMod.updateBltssConfig(var10, var14);
                                                               }

                                                               sender.addChatMessage(new ChatComponentText("[BulletDelay] Min=" + var10 + " : Divisor=" + var14));
                                                            }
                                                         } else if(split[0].equals("showbltss")) {
                                                            sender.addChatMessage(new ChatComponentText("[BulletDelay] Min=" + TeamsManager.bulletSnapshotMin + " : Divisor=" + TeamsManager.bulletSnapshotDivisor));
                                                         } else {
                                                            sender.addChatMessage(new ChatComponentText(split[0] + " is not a valid teams command. Try /teams help"));
                                                         }
                                                      }
                                                   } else if(split.length != 2) {
                                                      sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                                                   } else {
                                                      TeamsManager.armourDrops = Boolean.parseBoolean(split[1]);
                                                      sender.addChatMessage(new ChatComponentText("Armour will " + (TeamsManager.armourDrops?"now":"no longer") + " be dropped"));
                                                   }
                                                } else if(split.length != 2) {
                                                   sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                                                } else {
                                                   TeamsManager.bulletsEnabled = Boolean.parseBoolean(split[1]);
                                                   sender.addChatMessage(new ChatComponentText("Bullets are now " + (TeamsManager.bulletsEnabled?"enabled":"disabled")));
                                                }
                                             } else if(split.length != 2) {
                                                sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                                             } else {
                                                TeamsManager.bombsEnabled = Boolean.parseBoolean(split[1]);
                                                sender.addChatMessage(new ChatComponentText("Bombs are now " + (TeamsManager.bombsEnabled?"enabled":"disabled")));
                                             }
                                          } else if(split.length != 2) {
                                             sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                                          } else {
                                             TeamsManager.overrideHunger = Boolean.parseBoolean(split[1]);
                                             sender.addChatMessage(new ChatComponentText("Players will " + (TeamsManager.overrideHunger?"no longer":"now") + " get hungry during rounds"));
                                          }
                                       } else if(split.length != 2) {
                                          sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <true/false>"));
                                       } else {
                                          TeamsManager.forceAdventureMode = Boolean.parseBoolean(split[1]);
                                          sender.addChatMessage(new ChatComponentText("Adventure mode will " + (TeamsManager.forceAdventureMode?"now":"no longer") + " be forced"));
                                       }
                                    } else {
                                       teamsManager.roundTimeLeft = 1;
                                    }
                                 } else {
                                    teamsManager.start();
                                    sender.addChatMessage(new ChatComponentText("Started teams map rotation"));
                                 }
                              } else if(split.length < 8) {
                                 sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <Map> <Gametype> <Team1> <Team2> ... <TimeLimit> <ScoreLimit> <isNextRoundOn true/false>"));
                              } else {
                                 TeamsMap var12 = (TeamsMap)TeamsManager.getInstance().maps.get(split[1]);
                                 if(var12 == null) {
                                    sender.addChatMessage(new ChatComponentText("Could not find map : " + split[1]));
                                 } else {
                                    var13 = Gametype.getGametype(split[2]);
                                    if(var13 == null) {
                                       sender.addChatMessage(new ChatComponentText("Could not find gametype : " + split[2]));
                                    } else if(split.length != 6 + var13.numTeamsRequired) {
                                       sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <Map> <Gametype> <Team1> <Team2> ... <ScoreLimit> <TimeLimit> <isNextRoundOn true/false>"));
                                    } else {
                                       Team[] var19 = new Team[var13.numTeamsRequired];

                                       for(i = 0; i < var19.length; ++i) {
                                          var19[i] = Team.getTeam(split[3 + i]);
                                       }

                                       sender.addChatMessage(new ChatComponentText("Added map (" + var12.shortName + ") to rotation"));
                                       TeamsManager.getInstance().rounds.add(new TeamsRound(var12, var13, var19, Integer.parseInt(split[3 + var13.numTeamsRequired]), Integer.parseInt(split[4 + var13.numTeamsRequired]), Boolean.getBoolean(split[5 + var13.numTeamsRequired])));
                                    }
                                 }
                              }
                           } else if(split.length != 2) {
                              sender.addChatMessage(new ChatComponentText("Incorrect Usage : Should be /teams " + split[0] + " <ID>"));
                           } else {
                              var10 = Integer.parseInt(split[1]);
                              sender.addChatMessage(new ChatComponentText("Removed map " + var10 + " (" + ((TeamsRound)TeamsManager.getInstance().rounds.get(var10)).map.shortName + ") from rotation"));
                              TeamsManager.getInstance().rounds.remove(var10);
                           }
                        } else {
                           sender.addChatMessage(new ChatComponentText("§2Current Round List"));

                           for(var10 = 0; var10 < TeamsManager.getInstance().rounds.size(); ++var10) {
                              TeamsRound var11 = (TeamsRound)TeamsManager.getInstance().rounds.get(var10);
                              if(var11.map == null) {
                                 sender.addChatMessage(new ChatComponentText("Round had null map"));
                                 return;
                              }

                              if(var11.gametype == null) {
                                 sender.addChatMessage(new ChatComponentText("Round had null gametype"));
                                 return;
                              }

                              String list = var10 + ". " + var11.map.shortName + ", " + var11.gametype.shortName;
                              if(var11 == TeamsManager.getInstance().currentRound) {
                                 list = "§4" + list;
                              }

                              for(i = 0; i < var11.teams.length; ++i) {
                                 list = list + ", " + var11.teams[i].shortName;
                              }

                              list = list + ", " + var11.timeLimit;
                              list = list + ", " + var11.scoreLimit;
                              list = list + ", Pop : " + (int)(var11.popularity * 100.0F) + "%";
                              sender.addChatMessage(new ChatComponentText(list));
                           }

                        }
                     }
                  } else {
                     EntityPlayerMP var9 = this.getPlayer(sender.getCommandSenderName());
                     if(var9 != null) {
                        var9.inventory.addItemStackToInventory(new ItemStack(FlansMod.opStick, 1, 0));
                        var9.inventory.addItemStackToInventory(new ItemStack(FlansMod.opStick, 1, 1));
                        var9.inventory.addItemStackToInventory(new ItemStack(FlansMod.opStick, 1, 2));
                        var9.inventory.addItemStackToInventory(new ItemStack(FlansMod.opStick, 1, 3));
                        sender.addChatMessage(new ChatComponentText("§2Enjoy your op sticks."));
                        sender.addChatMessage(new ChatComponentText("§7The Stick of Connecting connects objects (spawners, banners etc) to bases (flagpoles etc)"));
                        sender.addChatMessage(new ChatComponentText("§7The Stick of Ownership sets the team that currently owns a base"));
                        sender.addChatMessage(new ChatComponentText("§7The Stick of Mapping sets the map that a base is currently associated with"));
                        sender.addChatMessage(new ChatComponentText("§7The Stick of Destruction deletes bases and team objects"));
                     }

                  }
               } else if(Team.teams.size() == 0) {
                  sender.addChatMessage(new ChatComponentText("§4No teams available. You need a content pack that has some teams with it"));
               } else {
                  sender.addChatMessage(new ChatComponentText("§2Showing all avaliable teams"));
                  sender.addChatMessage(new ChatComponentText("§2To pick these teams, use /teams setTeams <team1> <team2> with the names in brackets"));
                  bmn = Team.teams.iterator();

                  while(bmn.hasNext()) {
                     Team bdv = (Team)bmn.next();
                     sender.addChatMessage(new ChatComponentText("§" + bdv.textColour + bdv.name + " (" + bdv.shortName + ")"));
                  }

               }
            }
         }
      } else {
         if(split.length == 2) {
            this.sendHelpInformation(sender, Integer.parseInt(split[1]));
         } else {
            this.sendHelpInformation(sender, 1);
         }

      }
   }

   public List addTabCompletionOptions(ICommandSender sender, String[] prm) {
      return prm.length <= 1?getListOfStringsMatchingLastWord(prm, new String[]{"help", "off", "arena", "survival", "getSticks", "listGametypes", "setGametype", "listAllTeams", "listTeams", "setTeams", "addMap", "listMaps", "removeMap", "setMap", "useRotation", "voting", "addRound", "listRounds", "removeRound", "nextMap", "goToMap", "votingTime", "scoreDisplayTime", "setVariable", "forceAdventure", "overrideHunger", "explosions", "canBreakGuns", "canBreakGlass", "armourDrops", "weaponDrops", "fuelNeeded", "mgLife", "planeLife", "vehicleLife", "aaLife", "vehiclesBreakBlocks", "ping", "bltss", "showbltss", "vehiclesCanZoom"}):null;
   }

   public void sendHelpInformation(ICommandSender sender, int page) {
      if(page <= 4 && page >= 1) {
         sender.addChatMessage(new ChatComponentText("§2Listing teams commands §f[Page " + page + " of 4]"));
         switch(page) {
         case 1:
            sender.addChatMessage(new ChatComponentText("/teams help [page]"));
            sender.addChatMessage(new ChatComponentText("/teams off"));
            sender.addChatMessage(new ChatComponentText("/teams arena"));
            sender.addChatMessage(new ChatComponentText("/teams survival"));
            sender.addChatMessage(new ChatComponentText("/teams getSticks"));
            sender.addChatMessage(new ChatComponentText("/teams listGametypes"));
            sender.addChatMessage(new ChatComponentText("/teams listTeams"));
            sender.addChatMessage(new ChatComponentText("/teams addMap <shortName> <longName>"));
            sender.addChatMessage(new ChatComponentText("/teams listMaps"));
            sender.addChatMessage(new ChatComponentText("/teams removeMap <shortName>"));
            break;
         case 2:
            sender.addChatMessage(new ChatComponentText("/teams useRotation <true / false>"));
            sender.addChatMessage(new ChatComponentText("/teams voting <true / false>"));
            sender.addChatMessage(new ChatComponentText("/teams addRound <map> <gametype> <team1> <team2> <TimeLimit> <ScoreLimit> <isNextRoundOn true/false>"));
            sender.addChatMessage(new ChatComponentText("/teams listRounds"));
            sender.addChatMessage(new ChatComponentText("/teams removeRound <ID>"));
            sender.addChatMessage(new ChatComponentText("/teams nextMap"));
            sender.addChatMessage(new ChatComponentText("/teams votingTime <time>"));
            sender.addChatMessage(new ChatComponentText("/teams scoreDisplayTime <time>"));
            break;
         case 3:
            sender.addChatMessage(new ChatComponentText("/teams setVariable <variable> <value>"));
            sender.addChatMessage(new ChatComponentText("/teams forceAdventure <true / false>"));
            sender.addChatMessage(new ChatComponentText("/teams overrideHunger <true / false>"));
            sender.addChatMessage(new ChatComponentText("/teams explosions <true / false>"));
            sender.addChatMessage(new ChatComponentText("/teams canBreakGuns <true / false>"));
            sender.addChatMessage(new ChatComponentText("/teams canBreakGlass <true / false>"));
            sender.addChatMessage(new ChatComponentText("/teams armourDrops <true / false>"));
            sender.addChatMessage(new ChatComponentText("/teams weaponDrops <off / on / smart>"));
            sender.addChatMessage(new ChatComponentText("/teams fuelNeeded <true / false>"));
            sender.addChatMessage(new ChatComponentText("/teams mgLife <time>"));
            sender.addChatMessage(new ChatComponentText("/teams planeLife <time>"));
            sender.addChatMessage(new ChatComponentText("/teams vehicleLife <time>"));
            sender.addChatMessage(new ChatComponentText("/teams aaLife <time>"));
            sender.addChatMessage(new ChatComponentText("/teams vehiclesBreakBlocks <true / false>"));
            break;
         case 4:
            sender.addChatMessage(new ChatComponentText("/teams ping <PlayerName>"));
            sender.addChatMessage(new ChatComponentText("/teams bltss <0 ... 100> <0 ... 1000>"));
            sender.addChatMessage(new ChatComponentText("/teams showbltss"));
            sender.addChatMessage(new ChatComponentText("/teams vehiclesCanZoom <true / false>"));
         }

      } else {
         ChatComponentText text = new ChatComponentText("Invalid help page, should be in the range (1-4)");
         text.getChatStyle().setColor(EnumChatFormatting.RED);
         sender.addChatMessage(text);
      }
   }

   public EntityPlayerMP getPlayer(String name) {
      return MinecraftServer.getServer().getConfigurationManager().func_152612_a(name);
   }

   public String getCommandUsage(ICommandSender icommandsender) {
      return "Try \"/teams help\"";
   }

}
