package com.flansmod.common.guns;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.guns.EntityGrenade;
import com.flansmod.common.guns.EntityShootable;
import com.flansmod.common.guns.GrenadeType;
import com.flansmod.common.guns.ItemShootable;
import com.flansmod.common.types.IFlanItem;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3f;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemGrenade extends ItemShootable implements IFlanItem {

   public GrenadeType type;


   public ItemGrenade(GrenadeType t) {
      super(t);
      this.type = t;
      this.type.item = this;
      this.setCreativeTab(FlansMod.tabFlanGuns);
   }

   public Multimap getAttributeModifiers(ItemStack stack) {
      Multimap multimap = super.getAttributeModifiers(stack);
      multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(Item.field_111210_e, "Weapon modifier", (double)this.type.meleeDamage, 0));
      return multimap;
   }

   public boolean isFull3D() {
      return true;
   }

   public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
      return this.type.meleeDamage == 0;
   }

   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
      PlayerData data = PlayerHandler.getPlayerData(player, world.isRemote?Side.CLIENT:Side.SERVER);
      if(this.type.canThrow && data != null && data.shootTimeRight <= 0 && data.shootTimeLeft <= 0) {
         data.shootTimeRight = this.type.throwDelay;
         EntityGrenade grenade = new EntityGrenade(world, this.type, player);
         if(!world.isRemote) {
            world.spawnEntityInWorld(grenade);
         }

         if(this.type.remote) {
            data.remoteExplosives.add(grenade);
         }

         if(!player.capabilities.isCreativeMode) {
            --stack.stackSize;
         }

         if(this.type.dropItemOnThrow != null) {
            String itemName = this.type.dropItemOnDetonate;
            int damage = 0;
            if(itemName.contains(".")) {
               damage = Integer.parseInt(itemName.split("\\.")[1]);
               itemName = itemName.split("\\.")[0];
            }

            ItemStack dropStack = InfoType.getRecipeElement(itemName, damage);
            world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, dropStack));
         }
      }

      return stack;
   }

   @SideOnly(Side.CLIENT)
   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
      return this.type.colour;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister icon) {
      super.itemIcon = icon.registerIcon("FlansMod:" + this.type.iconPath);
   }

   public InfoType getInfoType() {
      return this.type;
   }

   public EntityShootable getEntity(World worldObj, Vec3 origin, float yaw, float pitch, double motionX, double motionY, double motionZ, EntityLivingBase shooter, float gunDamage, int itemDamage, InfoType shotFrom) {
      return null;
   }

   public EntityShootable getEntity(World worldObj, Vector3f origin, Vector3f direction, EntityLivingBase thrower, float spread, float damage, float speed, int itemDamage, InfoType shotFrom) {
      return this.getGrenade(worldObj, thrower);
   }

   public EntityShootable getEntity(World worldObj, Vec3 origin, float yaw, float pitch, EntityLivingBase shooter, float spread, float damage, int itemDamage, InfoType shotFrom) {
      return null;
   }

   public EntityShootable getEntity(World worldObj, EntityLivingBase player, float bulletSpread, float damage, float bulletSpeed, boolean b, int itemDamage, InfoType shotFrom) {
      return this.getGrenade(worldObj, player);
   }

   public EntityGrenade getGrenade(World world, EntityLivingBase thrower) {
      EntityGrenade grenade = new EntityGrenade(world, this.type, thrower);
      if(this.type.remote && thrower instanceof EntityPlayer) {
         PlayerHandler.getPlayerData((EntityPlayer)thrower).remoteExplosives.add(grenade);
      }

      return grenade;
   }
}
