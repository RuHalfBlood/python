package com.flansmod.common.guns;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.network.PacketBase;
import com.flansmod.common.network.PacketKillMessage;
import com.flansmod.common.teams.Team;
import com.flansmod.common.types.InfoType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.IChatComponent;

public class EntityDamageSourceGun extends EntityDamageSourceIndirect {

   public InfoType weapon;
   public EntityPlayer shooter;
   public boolean headshot;


   public EntityDamageSourceGun(String s, Entity entity, EntityPlayer player, InfoType wep, boolean head) {
      super(s, entity, player);
      this.weapon = wep;
      this.shooter = player;
      this.headshot = head;
   }

   public Entity getDamageSourceEntity() {
      return super.damageSourceEntity;
   }

   public IChatComponent func_151519_b(EntityLivingBase living) {
      if(living instanceof EntityPlayer && this.shooter != null && PlayerHandler.getPlayerData(this.shooter) != null) {
         EntityPlayer player = (EntityPlayer)living;
         Team killedTeam = PlayerHandler.getPlayerData(player).team;
         Team killerTeam = PlayerHandler.getPlayerData(this.shooter).team;
         FlansMod.getPacketHandler().sendToDimension((PacketBase)(new PacketKillMessage(this.headshot, this.weapon, (killedTeam == null?"f":Character.valueOf(killedTeam.textColour)) + player.getCommandSenderName(), (killerTeam == null?"f":Character.valueOf(killerTeam.textColour)) + this.shooter.getCommandSenderName())), living.dimension);
         System.out.println(this.shooter.getCommandSenderName() + " has killed " + player.getCommandSenderName() + " with " + this.weapon.name + ". TickExisted:" + player.ticksExisted / 20 + " KilledPos(X:" + (int)player.posX + " Y:" + (int)player.posY + " Z:" + (int)player.posZ + ") KillerPos(X:" + (int)this.shooter.posX + " Y:" + (int)this.shooter.posY + " Z:" + (int)this.shooter.posZ + ")");
         if(player.getCurrentArmor(2) != null) {
            System.out.println("KilledPlayer:" + player + " wear a " + player.getCurrentArmor(2).getUnlocalizedName());
         } else {
            System.out.println("KilledPlayer:" + player + " wear nothing.");
         }

         if(this.shooter.getCurrentArmor(2) != null) {
            System.out.println("Killer:" + this.shooter + " wear a " + this.shooter.getCurrentArmor(2).getUnlocalizedName());
         } else {
            System.out.println("Killer:" + this.shooter + " wear nothing.");
         }

         if(player.ticksExisted / 20 < FlansMod.noticeSpawnKillTime) {
            System.out.println("Warning! PlayerName:" + this.shooter.getCommandSenderName() + " may do SPAWN KILL. Time:" + player.ticksExisted / 20 + " " + player.getCommandSenderName() + " was killed.");
         }

         return new ChatComponentText("#flansmod");
      } else {
         return super.func_151519_b(living);
      }
   }
}
