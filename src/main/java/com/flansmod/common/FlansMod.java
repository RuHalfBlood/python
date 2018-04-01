package com.flansmod.common;

import com.flansmod.common.BlockFlansWorkbench;
import com.flansmod.common.CommonGuiHandler;
import com.flansmod.common.CommonProxy;
import com.flansmod.common.CommonTickHandler;
import com.flansmod.common.CreativeTabFlan;
import com.flansmod.common.FlansHooks;
import com.flansmod.common.ItemBlockManyNames;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.driveables.EntityPlane;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.driveables.EntityWheel;
import com.flansmod.common.driveables.ItemPlane;
import com.flansmod.common.driveables.ItemVehicle;
import com.flansmod.common.driveables.PlaneType;
import com.flansmod.common.driveables.VehicleType;
import com.flansmod.common.driveables.mechas.EntityMecha;
import com.flansmod.common.driveables.mechas.ItemMecha;
import com.flansmod.common.driveables.mechas.ItemMechaAddon;
import com.flansmod.common.driveables.mechas.MechaItemType;
import com.flansmod.common.driveables.mechas.MechaType;
import com.flansmod.common.eventhandlers.PlayerDeathEventListener;
import com.flansmod.common.guns.AAGunType;
import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.EntityAAGun;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.EntityGrenade;
import com.flansmod.common.guns.EntityMG;
import com.flansmod.common.guns.GrenadeType;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemAAGun;
import com.flansmod.common.guns.ItemAttachment;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.guns.ItemGrenade;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.boxes.BlockGunBox;
import com.flansmod.common.guns.boxes.GunBoxType;
import com.flansmod.common.network.PacketHandler;
import com.flansmod.common.paintjob.BlockPaintjobTable;
import com.flansmod.common.paintjob.TileEntityPaintjobTable;
import com.flansmod.common.parts.ItemPart;
import com.flansmod.common.parts.PartType;
import com.flansmod.common.teams.ArmourBoxType;
import com.flansmod.common.teams.ArmourType;
import com.flansmod.common.teams.BlockArmourBox;
import com.flansmod.common.teams.BlockSpawner;
import com.flansmod.common.teams.ChunkLoadingHandler;
import com.flansmod.common.teams.CommandTeams;
import com.flansmod.common.teams.EntityFlag;
import com.flansmod.common.teams.EntityFlagpole;
import com.flansmod.common.teams.EntityGunItem;
import com.flansmod.common.teams.EntityTeamItem;
import com.flansmod.common.teams.ItemFlagpole;
import com.flansmod.common.teams.ItemOpStick;
import com.flansmod.common.teams.ItemTeamArmour;
import com.flansmod.common.teams.Team;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.teams.TileEntitySpawner;
import com.flansmod.common.tools.EntityParachute;
import com.flansmod.common.tools.ItemTool;
import com.flansmod.common.tools.ToolType;
import com.flansmod.common.types.EnumType;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.types.TypeFile;
import cpw.mods.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import net.minecraft.block.material.Material;
import net.minecraft.command.CommandHandler;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

@Mod(
   modid = "flansmod",
   name = "Flan\'s Mod",
   version = "@VERSION@",
   acceptableRemoteVersions = "@ALLOWEDVERSIONS@",
   guiFactory = "com.flansmod.client.gui.config.ModGuiFactory"
)
public class FlansMod {

   public static boolean DEBUG = false;
   public static Configuration configFile;
   public static final String MODID = "flansmod";
   public static final String VERSION = "@VERSION@";
   @Instance("flansmod")
   public static FlansMod INSTANCE;
   public static int generalConfigInteger = 32;
   public static String generalConfigString = "Hello!";
   public static boolean printDebugLog = true;
   public static boolean printStackTrace = false;
   public static int noticeSpawnKillTime = 10;
   public static float[] hitCrossHairColor = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
   public static boolean addGunpowderRecipe = true;
   public static boolean addAllPaintjobsToCreative = true;
   public static int teamsConfigInteger = 32;
   public static String teamsConfigString = "Hello!";
   public static boolean teamsConfigBoolean = false;
   @SidedProxy(
      clientSide = "com.flansmod.client.ClientProxy",
      serverSide = "com.flansmod.common.CommonProxy"
   )
   public static CommonProxy proxy;
   public static int ticker = 0;
   public static long lastTime;
   public static File flanDir;
   public static final float soundRange = 50.0F;
   public static final float driveableUpdateRange = 400.0F;
   public static final int numPlayerSnapshots = 20;
   public static int armourSpawnRate = 20;
   public static Team spectators = new Team("spectators", "Spectators", 4210752, '7');
   public static final PacketHandler packetHandler = new PacketHandler();
   public static final PlayerHandler playerHandler = new PlayerHandler();
   public static final TeamsManager teamsManager = new TeamsManager();
   public static final CommonTickHandler tickHandler = new CommonTickHandler();
   public static FlansHooks hooks = new FlansHooks();
   public static boolean isInFlash = false;
   public static int flashTime = 10;
   public static BlockFlansWorkbench workbench;
   public static BlockPaintjobTable paintjobTable;
   public static BlockSpawner spawner;
   public static ItemOpStick opStick;
   public static ItemFlagpole flag;
   public static ArrayList gunBoxBlocks = new ArrayList();
   public static ArrayList bulletItems = new ArrayList();
   public static ArrayList gunItems = new ArrayList();
   public static ArrayList attachmentItems = new ArrayList();
   public static ArrayList partItems = new ArrayList();
   public static ArrayList planeItems = new ArrayList();
   public static ArrayList vehicleItems = new ArrayList();
   public static ArrayList mechaToolItems = new ArrayList();
   public static ArrayList mechaItems = new ArrayList();
   public static ArrayList aaGunItems = new ArrayList();
   public static ArrayList grenadeItems = new ArrayList();
   public static ArrayList toolItems = new ArrayList();
   public static ArrayList armourItems = new ArrayList();
   public static ArrayList armourBoxBlocks = new ArrayList();
   public static CreativeTabFlan tabFlanGuns = new CreativeTabFlan(0);
   public static CreativeTabFlan tabFlanDriveables = new CreativeTabFlan(1);
   public static CreativeTabFlan tabFlanParts = new CreativeTabFlan(2);
   public static CreativeTabFlan tabFlanTeams = new CreativeTabFlan(3);
   public static CreativeTabFlan tabFlanMechas = new CreativeTabFlan(4);
   public static HashMap gunAnimationsRight = new HashMap();
   public static HashMap gunAnimationsLeft = new HashMap();


   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      log("Preinitialising Flan\'s mod.");
      configFile = new Configuration(event.getSuggestedConfigurationFile());
      syncConfig();
      flanDir = new File(event.getModConfigurationDirectory().getParentFile(), "/Flan/");
      if(!flanDir.exists()) {
         log("Flan folder not found. Creating empty folder.");
         log("You should get some content packs and put them in the Flan folder.");
         flanDir.mkdirs();
         flanDir.mkdir();
      }

      workbench = (BlockFlansWorkbench)((BlockFlansWorkbench)(new BlockFlansWorkbench(1, 0)).setBlockName("flansWorkbench").setBlockTextureName("flansWorkbench"));
      GameRegistry.registerBlock(workbench, ItemBlockManyNames.class, "flansWorkbench");
      GameRegistry.addRecipe(new ItemStack(workbench, 1, 0), new Object[]{"BBB", "III", "III", Character.valueOf('B'), Items.bowl, Character.valueOf('I'), Items.iron_ingot});
      GameRegistry.addRecipe(new ItemStack(workbench, 1, 1), new Object[]{"ICI", "III", Character.valueOf('C'), Items.cauldron, Character.valueOf('I'), Items.iron_ingot});
      opStick = new ItemOpStick();
      GameRegistry.registerItem(opStick, "opStick", "flansmod");
      flag = (ItemFlagpole)((ItemFlagpole)(new ItemFlagpole()).setUnlocalizedName("flagpole"));
      GameRegistry.registerItem(flag, "flagpole", "flansmod");
      spawner = (BlockSpawner)((BlockSpawner)(new BlockSpawner(Material.iron)).setBlockName("teamsSpawner").setBlockUnbreakable().setResistance(1000000.0F));
      GameRegistry.registerBlock(spawner, ItemBlockManyNames.class, "teamsSpawner");
      GameRegistry.registerTileEntity(TileEntitySpawner.class, "teamsSpawner");
      paintjobTable = new BlockPaintjobTable();
      GameRegistry.registerBlock(paintjobTable, "paintjobTable");
      GameRegistry.registerTileEntity(TileEntityPaintjobTable.class, "flansmod");
      proxy.registerRenderers();
      this.readContentPacks(event);
      if(gunItems.size() >= 1) {
         MinecraftForge.EVENT_BUS.register(gunItems.get(0));
      }

      proxy.load();
      proxy.forceReload();
      log("Preinitializing complete.");
   }

   @EventHandler
   public void init(FMLInitializationEvent event) {
      log("Initialising Flan\'s Mod.");
      packetHandler.initialise();
      NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonGuiHandler());
      Iterator charcoal = InfoType.infoTypes.iterator();

      while(charcoal.hasNext()) {
         InfoType type = (InfoType)charcoal.next();
         type.addRecipe();
      }

      if(addGunpowderRecipe) {
         ItemStack charcoal1 = new ItemStack(Items.coal, 1, 1);
         GameRegistry.addShapelessRecipe(new ItemStack(Items.gunpowder), new Object[]{charcoal1, charcoal1, charcoal1, new ItemStack(Items.glowstone_dust)});
      }

      log("Loaded recipes.");
    
      EntityRegistry.registerGlobalEntityID(EntityFlagpole.class, "Flagpole", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityFlagpole.class, "Flagpole", 93, this, 40, 5, true);
      EntityRegistry.registerGlobalEntityID(EntityFlag.class, "Flag", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityFlag.class, "Flag", 94, this, 40, 5, true);
      EntityRegistry.registerGlobalEntityID(EntityTeamItem.class, "TeamsItem", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityTeamItem.class, "TeamsItem", 97, this, 100, 10000, true);
      EntityRegistry.registerGlobalEntityID(EntityGunItem.class, "GunItem", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityGunItem.class, "GunItem", 98, this, 100, 20, true);
      EntityRegistry.registerGlobalEntityID(EntityPlane.class, "Plane", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityPlane.class, "Plane", 90, this, 200, 3, true);
      EntityRegistry.registerGlobalEntityID(EntityVehicle.class, "Vehicle", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityVehicle.class, "Vehicle", 95, this, 400, 10, true);
      EntityRegistry.registerGlobalEntityID(EntitySeat.class, "Seat", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntitySeat.class, "Seat", 99, this, 250, 10, true);
      EntityRegistry.registerGlobalEntityID(EntityWheel.class, "Wheel", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityWheel.class, "Wheel", 103, this, 200, 20, true);
      EntityRegistry.registerGlobalEntityID(EntityParachute.class, "Parachute", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityParachute.class, "Parachute", 101, this, 40, 20, false);
      EntityRegistry.registerGlobalEntityID(EntityMecha.class, "Mecha", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityMecha.class, "Mecha", 102, this, 250, 20, false);
      EntityRegistry.registerGlobalEntityID(EntityBullet.class, "Bullet", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", 96, this, 200, 20, false);
      EntityRegistry.registerGlobalEntityID(EntityGrenade.class, "Grenade", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityGrenade.class, "Grenade", 100, this, 40, 100, true);
      EntityRegistry.registerGlobalEntityID(EntityMG.class, "MG", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityMG.class, "MG", 91, this, 40, 5, true);
      EntityRegistry.registerGlobalEntityID(EntityAAGun.class, "AAGun", EntityRegistry.findGlobalUniqueEntityId());
      EntityRegistry.registerModEntity(EntityAAGun.class, "AAGun", 92, this, 40, 500, false);
      ForgeChunkManager.setForcedChunkLoadingCallback(this, new ChunkLoadingHandler());
      FMLCommonHandler.instance().bus().register(INSTANCE);
      new PlayerDeathEventListener();
      log("Loading complete.");
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent event) {
      packetHandler.postInitialise();
      hooks.hook();
   }

   @SubscribeEvent
   public void playerDrops(PlayerDropsEvent event) {
      for(int i = event.drops.size() - 1; i >= 0; --i) {
         EntityItem ent = (EntityItem)event.drops.get(i);
         InfoType type = InfoType.getType(ent.getEntityItem());
         if(type != null && !type.canDrop) {
            event.drops.remove(i);
         }
      }

   }

   @SubscribeEvent
   public void playerDrops(ItemTossEvent event) {
      InfoType type = InfoType.getType(event.entityItem.getEntityItem());
      if(type != null && !type.canDrop) {
         event.setCanceled(true);
      }

   }

   @EventHandler
   public void registerCommand(FMLServerStartedEvent e) {
      CommandHandler handler = (CommandHandler)FMLCommonHandler.instance().getSidedDelegate().getServer().getCommandManager();
      handler.registerCommand(new CommandTeams());
   }

   @SubscribeEvent
   public void onConfigChanged(OnConfigChangedEvent eventArgs) {
      if(eventArgs.modID.equals("flansmod")) {
         syncConfig();
      }

   }

   @SubscribeEvent
   public void onLivingSpecialSpawn(CheckSpawn event) {
      int chance = event.world.rand.nextInt(101);
      if(chance < armourSpawnRate && (event.entityLiving instanceof EntityZombie || event.entityLiving instanceof EntitySkeleton)) {
         if(event.world.rand.nextBoolean() && ArmourType.armours.size() > 0) {
            ArmourType team1 = (ArmourType)ArmourType.armours.get(event.world.rand.nextInt(ArmourType.armours.size()));
            if(team1 != null && team1.type != 2) {
               event.entityLiving.setCurrentItemOrArmor(team1.type + 1, new ItemStack(team1.item));
            }
         } else if(Team.teams.size() > 0) {
            Team team = (Team)Team.teams.get(event.world.rand.nextInt(Team.teams.size()));
            if(team.hat != null) {
               event.entityLiving.setCurrentItemOrArmor(1, team.hat.copy());
            }

            if(team.chest != null) {
               event.entityLiving.setCurrentItemOrArmor(2, team.chest.copy());
            }

            if(team.shoes != null) {
               event.entityLiving.setCurrentItemOrArmor(4, team.shoes.copy());
            }
         }
      }

   }

   private void getTypeFiles(List contentPacks) {
      Iterator var2 = contentPacks.iterator();

      while(var2.hasNext()) {
         File contentPack = (File)var2.next();
         int e1;
         int var11;
         if(contentPack.isDirectory()) {
            EnumType[] var23 = EnumType.values();
            int var24 = var23.length;

            for(int var25 = 0; var25 < var24; ++var25) {
               EnumType var26 = var23[var25];
               File var27 = new File(contentPack, "/" + var26.folderName + "/");
               if(var27.exists()) {
                  File[] var29 = var27.listFiles();
                  e1 = var29.length;

                  for(var11 = 0; var11 < e1; ++var11) {
                     File var30 = var29[var11];

                     try {
                        BufferedReader var31 = new BufferedReader(new FileReader(var30));
                        String[] splitName1 = var30.getName().split("/");
                        TypeFile typeFile1 = new TypeFile(var26, splitName1[splitName1.length - 1].split("\\.")[0], contentPack.getName());

                        while(true) {
                           String line1 = null;

                           try {
                              line1 = var31.readLine();
                           } catch (Exception var18) {
                              break;
                           }

                           if(line1 == null) {
                              break;
                           }

                           typeFile1.lines.add(line1);
                        }

                        var31.close();
                     } catch (FileNotFoundException var19) {
                        var19.printStackTrace();
                     } catch (IOException var20) {
                        var20.printStackTrace();
                     }
                  }
               }
            }
         } else {
            try {
               ZipFile e = new ZipFile(contentPack);
               ZipInputStream zipStream = new ZipInputStream(new FileInputStream(contentPack));
               BufferedReader reader = new BufferedReader(new InputStreamReader(zipStream));
               ZipEntry zipEntry = zipStream.getNextEntry();

               do {
                  zipEntry = zipStream.getNextEntry();
                  if(zipEntry != null) {
                     TypeFile typeFile = null;
                     EnumType[] line = EnumType.values();
                     e1 = line.length;

                     for(var11 = 0; var11 < e1; ++var11) {
                        EnumType type = line[var11];
                        if(zipEntry.getName().startsWith(type.folderName + "/") && zipEntry.getName().split(type.folderName + "/").length > 1 && zipEntry.getName().split(type.folderName + "/")[1].length() > 0) {
                           String[] splitName = zipEntry.getName().split("/");
                           typeFile = new TypeFile(type, splitName[splitName.length - 1].split("\\.")[0], contentPack.getName());
                        }
                     }

                     if(typeFile != null) {
                        while(true) {
                           line = null;

                           String var28;
                           try {
                              var28 = reader.readLine();
                           } catch (Exception var21) {
                              break;
                           }

                           if(var28 == null) {
                              break;
                           }

                           typeFile.lines.add(var28);
                        }
                     }
                  }
               } while(zipEntry != null);

               reader.close();
               e.close();
               zipStream.close();
            } catch (IOException var22) {
               var22.printStackTrace();
            }
         }
      }

   }

   private void readContentPacks(FMLPreInitializationEvent event) {
	      ClassLoader classloader = MinecraftServer.class.getClassLoader();
	      Method method = null;

	      try {
	         method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
	         method.setAccessible(true);
	      } catch (Exception var14) {
	         log("Failed to get class loader. All content loading will now fail.");
	         if(printStackTrace) {
	            var14.printStackTrace();
	         }
	      }

	      List contentPacks = proxy.getContentList(method, classloader);
	      if(!event.getSide().equals(Side.CLIENT)) {
	         ;
	      }

	      this.getTypeFiles(contentPacks);
	      EnumType[] var5 = EnumType.values();
	      int var6 = var5.length;

	      for(int var7 = 0; var7 < var6; ++var7) {
	         EnumType type = var5[var7];
	         Class typeClass = type.getTypeClass();
	         Iterator var10 = ((ArrayList)TypeFile.files.get(type)).iterator();

	         while(var10.hasNext()) {
	            TypeFile typeFile = (TypeFile)var10.next();

	            try {
	               InfoType e = (InfoType)typeClass.getConstructor(new Class[]{TypeFile.class}).newInstance(new Object[]{typeFile});
	               e.read(typeFile);
	               switch(type) {

                   case bullet: {
                       bulletItems.add((ItemBullet)new ItemBullet((BulletType)e).setUnlocalizedName(e.shortName));
                       break;
                   }
                   case attachment: {
                       attachmentItems.add((ItemAttachment)new ItemAttachment((AttachmentType)e).setUnlocalizedName(e.shortName));
                       break;
                   }
                   case gun: {
                       gunItems.add((ItemGun)new ItemGun((GunType)e).setUnlocalizedName(e.shortName));
                       break;
                   }
                   case grenade: {
                       grenadeItems.add((ItemGrenade)new ItemGrenade((GrenadeType)e).setUnlocalizedName(e.shortName));
                       break;
                   }
                   case part: {
                       partItems.add((ItemPart)new ItemPart((PartType)e).setUnlocalizedName(e.shortName));
                       break;
                   }
                   case plane: {
                       planeItems.add((ItemPlane)new ItemPlane((PlaneType)e).setUnlocalizedName(e.shortName));
                       break;
                   }
                   case vehicle: {
                       vehicleItems.add((ItemVehicle)new ItemVehicle((VehicleType)e).setUnlocalizedName(e.shortName));
                       break;
                   }
                   case aa: {
                       aaGunItems.add((ItemAAGun)new ItemAAGun((AAGunType)e).setUnlocalizedName(e.shortName));
                       break;
                   }
                   case mechaItem: {
                       mechaToolItems.add((ItemMechaAddon)new ItemMechaAddon((MechaItemType)e).setUnlocalizedName(e.shortName));
                       break;
                   }
                   case mecha: {
                       mechaItems.add((ItemMecha)new ItemMecha((MechaType)e).setUnlocalizedName(e.shortName));
                       break;
                   }
                   case tool: {
                       toolItems.add((ItemTool)new ItemTool((ToolType)e).setUnlocalizedName(e.shortName));
                       break;
                   }
                   case box: {
                       gunBoxBlocks.add((BlockGunBox)new BlockGunBox((GunBoxType)e).setBlockName(e.shortName));
                       break;
                   }
                   case armour: {
                       armourItems.add((ItemTeamArmour)new ItemTeamArmour((ArmourType)e).setUnlocalizedName(e.shortName));
                       break;
                   }
                   case armourBox: {
                       armourBoxBlocks.add((BlockArmourBox)new BlockArmourBox((ArmourBoxType)e).setBlockName(e.shortName));
                       break;
                   }
                   case playerClass: {
                       break;
                   }
                   case team: {
                       break;
                   }

	               
	               default:
	                  log("Unrecognised type for " + e.shortName);
	               }
	            } catch (Exception var13) {
	               log("Failed to add " + type.name() + " : " + typeFile.name);
	               if(printStackTrace) {
	                  var13.printStackTrace();
	               }
	            }
	         }

	         log("Loaded " + type.name() + ".");
	      }

	      Team.spectators = spectators;
	   }




   public static PacketHandler getPacketHandler() {
      FlansMod var10000 = INSTANCE;
      return packetHandler;
   }

   public static void syncConfig() {
      printDebugLog = configFile.getBoolean("Print Debug Log", "general", printDebugLog, "");
      printStackTrace = configFile.getBoolean("Print Stack Trace", "general", printStackTrace, "");
      addGunpowderRecipe = configFile.getBoolean("Gunpowder Recipe", "general", addGunpowderRecipe, "Whether or not to add the extra gunpowder recipe (3 charcoal + 1 lightstone)");
      addAllPaintjobsToCreative = configFile.getBoolean("Add All Paintjobs To Creative", "general", addAllPaintjobsToCreative, "Whether to list all available paintjobs in the Creative menu");
      armourSpawnRate = configFile.getInt("ArmourSpawnRate", "general", 20, 0, 100, "The rate of Zombie or Skeleton to spawn equipped with armor. [0=0%, 100=100%]");
      noticeSpawnKillTime = configFile.getInt("NoticeSpawnKillTime", "general", 10, 0, 600, "Min(default=10)");
      TeamsManager.bulletSnapshotMin = configFile.getInt("BltSS_Min", "general", 0, 0, 1000, "Min(default=0)");
      TeamsManager.bulletSnapshotDivisor = configFile.getInt("BltSS_Divisor", "general", 50, 0, 1000, "Divisor(default=50)");

      for(int i = 0; i < hitCrossHairColor.length; ++i) {
         String[] COLOR = new String[]{"Alpha", "Red", "Green", "Blue"};
         hitCrossHairColor[i] = configFile.getFloat("HitCrossHairColor" + COLOR[i], "general", hitCrossHairColor[i], 0.0F, 1.0F, "Hit cross hair color " + COLOR[i] + "(default=1.0)");
      }

      if(configFile.hasChanged()) {
         configFile.save();
      }

   }

   public static void updateBltssConfig(int min, int divisor) {
      ConfigCategory category = configFile.getCategory("general");
      if(category != null) {
         if(category.containsKey("BltSS_Min")) {
            category.get("BltSS_Min").set(min);
         }

         if(category.containsKey("BltSS_Divisor")) {
            category.get("BltSS_Divisor").set(divisor);
         }

         TeamsManager.bulletSnapshotMin = min;
         TeamsManager.bulletSnapshotDivisor = divisor;
         configFile.save();
      }
   }

   public static void log(String string) {
      if(printDebugLog) {
         System.out.println("[Flan\'s Mod] " + string);
      }

   }

   public static void log(String format, Object ... args) {
      log(String.format(format, args));
   }

}
