package com.flansmod.common.teams;

import com.flansmod.common.FlansMod;
import com.flansmod.common.teams.ArmourType;
import com.flansmod.common.types.IFlanItem;
import com.flansmod.common.types.InfoType;
import com.google.common.collect.Multimap;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class ItemTeamArmour extends ItemArmor implements ISpecialArmor, IFlanItem {

   public ArmourType type;
   protected static final UUID[] uuid = new UUID[]{UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()};


   public ItemTeamArmour(ArmourType t) {
      super(ArmorMaterial.CLOTH, 0, t.type);
      this.type = t;
      this.type.item = this;
      this.setCreativeTab(FlansMod.tabFlanTeams);
      if(t.durability > 0) {
         this.setMaxDamage(t.durability);
      }

      GameRegistry.registerItem(this, this.type.shortName, "flansmod");
   }

   public ItemTeamArmour(ArmorMaterial armorMaterial, int renderIndex, int armourType) {
      super(armorMaterial, renderIndex, armourType);
   }

   public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
      return new ArmorProperties(1, this.type.defence, Integer.MAX_VALUE);
   }

   public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
      return (int)(this.type.defence * 20.0D);
   }

   public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {}

   public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String s) {
      return "flansmod:armor/" + this.type.armourTextureName + "_" + (this.type.type == 2?"2":"1") + ".png";
   }

   public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean b) {
      if(!this.type.packName.isEmpty()) {
         lines.add(this.type.packName);
      }

      if(this.type.description != null) {
         Collections.addAll(lines, this.type.description.split("_"));
      }

      if(Math.abs(this.type.jumpModifier - 1.0F) > 0.01F) {
         lines.add("§3+" + (int)((this.type.jumpModifier - 1.0F) * 100.0F) + "% Jump Height");
      }

      if(this.type.smokeProtection) {
         lines.add("§2+Smoke Protection");
      }

      if(this.type.nightVision) {
         lines.add("§2+Night Vision");
      }

      if(this.type.invisible) {
         lines.add("§2+Invisiblity");
      }

      if(this.type.negateFallDamage) {
         lines.add("§2+Negates Fall Damage");
      }

   }

   @SideOnly(Side.CLIENT)
   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
      return this.type.colour;
   }

   @SideOnly(Side.CLIENT)
   public boolean requiresMultipleRenderPasses() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister icon) {
      super.itemIcon = icon.registerIcon("FlansMod:" + this.type.iconPath);
   }

   public Multimap getAttributeModifiers(ItemStack stack) {
      Multimap map = super.getAttributeModifiers(stack);
      map.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), new AttributeModifier(uuid[this.type.type], "KnockbackResist", (double)this.type.knockbackModifier, 0));
      map.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(uuid[this.type.type], "MovementSpeed", (double)(this.type.moveSpeedModifier - 1.0F), 2));
      return map;
   }

   @SideOnly(Side.CLIENT)
   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
      return this.type.model;
   }

   public InfoType getInfoType() {
      return this.type;
   }

   public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
      if(this.type.nightVision && FlansMod.ticker % 25 == 0) {
         player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 250));
      }

      if(this.type.invisible && FlansMod.ticker % 25 == 0) {
         player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 250));
      }

      if(this.type.jumpModifier > 1.01F && FlansMod.ticker % 25 == 0) {
         player.addPotionEffect(new PotionEffect(Potion.jump.id, 250, (int)((this.type.jumpModifier - 1.0F) * 2.0F), true));
      }

      if(this.type.negateFallDamage) {
         player.fallDistance = 0.0F;
      }

      if(this.type.onWaterWalking) {
         if(player.isInWater()) {
            player.capabilities.allowFlying = true;
         } else {
            player.capabilities.isFlying = false;
         }
      }

   }

}
