package com.flansmod.common.guns;

import com.flansmod.common.FlansMod;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.EntityShootable;
import com.flansmod.common.guns.ItemShootable;
import com.flansmod.common.types.IFlanItem;
import com.flansmod.common.types.InfoType;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemBullet extends ItemShootable implements IFlanItem {

   public BulletType type;




   public ItemBullet(BulletType infoType) {
       super(infoType);
       this.type = infoType;
       this.setMaxStackSize(this.type.maxStackSize);
       this.setHasSubtypes(true);
       this.type.item = this;
       switch (this.type.weaponType) {
           case SHELL: 
           case BOMB: 
           case MINE: 
           case MISSILE: {
               this.setCreativeTab((CreativeTabs)FlansMod.tabFlanDriveables);
               break;
           }
           default: {
               this.setCreativeTab((CreativeTabs)FlansMod.tabFlanGuns);
           }
       }
   }


   public boolean isRepairable() {
      return super.canRepair;
   }

   @SideOnly(Side.CLIENT)
   public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
      return this.type.colour;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister icon) {
      super.itemIcon = icon.registerIcon("FlansMod:" + this.type.iconPath);
   }

   public void addInformation(ItemStack stack, EntityPlayer player, List lines, boolean b) {
      if(!this.type.packName.isEmpty()) {
         lines.add(this.type.packName);
      }

      if(this.type.description != null) {
         Collections.addAll(lines, this.type.description.split("_"));
      }

   }

   public EntityShootable getEntity(World worldObj, Vec3 origin, float yaw, float pitch, double motionX, double motionY, double motionZ, EntityLivingBase shooter, float gunDamage, int itemDamage, InfoType shotFrom) {
      return new EntityBullet(worldObj, origin, yaw, pitch, motionX, motionY, motionZ, shooter, gunDamage, this.type, shotFrom);
   }

   public EntityShootable getEntity(World worldObj, Vector3f origin, Vector3f direction, EntityLivingBase shooter, float spread, float damage, float speed, int itemDamage, InfoType shotFrom) {
      return new EntityBullet(worldObj, origin, direction, shooter, spread, damage, this.type, speed, shotFrom);
   }

   public EntityShootable getEntity(World worldObj, Vec3 origin, float yaw, float pitch, EntityLivingBase shooter, float spread, float damage, int itemDamage, InfoType shotFrom) {
      return new EntityBullet(worldObj, origin, yaw, pitch, shooter, spread, damage, this.type, shotFrom);
   }

   public EntityShootable getEntity(World worldObj, EntityLivingBase player, float bulletSpread, float damage, float bulletSpeed, boolean b, int itemDamage, InfoType shotFrom) {
      return new EntityBullet(worldObj, player, bulletSpread, damage, this.type, bulletSpeed, b, shotFrom);
   }

   public InfoType getInfoType() {
      return this.type;
   }
}
