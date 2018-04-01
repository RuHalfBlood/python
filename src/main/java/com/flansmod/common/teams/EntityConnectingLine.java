package com.flansmod.common.teams;

import com.flansmod.common.teams.ITeamBase;
import com.flansmod.common.teams.ITeamObject;
import com.flansmod.common.teams.ItemOpStick;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityConnectingLine extends EntityFishHook {

   public Object connectedTo;


   public EntityConnectingLine(World world) {
      super(world);
   }

   public EntityConnectingLine(World world, EntityPlayer player, ITeamBase base) {
      super(world);
      super.field_146043_c = this;
      super.ignoreFrustumCheck = true;
      super.field_146042_b = player;
      super.field_146042_b.fishEntity = this;
      this.setSize(0.25F, 0.25F);
      this.setPosition(base.getPosX(), base.getPosY(), base.getPosZ());
      super.yOffset = 0.0F;
      super.motionX = 0.0D;
      super.motionZ = 0.0D;
      super.motionY = 0.0D;
      this.connectedTo = base;
   }

   public EntityConnectingLine(World world, EntityPlayer player, ITeamObject object) {
      super(world);
      super.field_146043_c = this;
      super.ignoreFrustumCheck = true;
      super.field_146042_b = player;
      super.field_146042_b.fishEntity = this;
      this.setSize(0.25F, 0.25F);
      this.setPosition(object.getPosX(), object.getPosY(), object.getPosZ());
      super.yOffset = 0.0F;
      super.motionX = 0.0D;
      super.motionZ = 0.0D;
      super.motionY = 0.0D;
      this.connectedTo = object;
   }

   public void onUpdate() {
      ItemStack currentItemstack = super.field_146042_b.inventory.getCurrentItem();
      if(currentItemstack == null || !(currentItemstack.getItem() instanceof ItemOpStick) || currentItemstack.getItemDamage() != 1) {
         this.setDead();
         super.field_146042_b.fishEntity = null;
      }

   }
}
