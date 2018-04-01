package com.flansmod.common.guns.raytracing;

import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.raytracing.EnumHitboxType;
import com.flansmod.common.guns.raytracing.PlayerBulletHit;
import com.flansmod.common.guns.raytracing.PlayerHitbox;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class PlayerSnapshot {

   public EntityPlayer player;
   public Vector3f pos;
   public ArrayList hitboxes;
   public long time;
   PlayerHitbox hitbox;

   public PlayerSnapshot(EntityPlayer p) {
      this.player = p;
      this.pos = new Vector3f(p.posX, p.posY, p.posZ);
      if(FlansMod.proxy.isThePlayer(p)) {
         this.pos = new Vector3f(p.posX, p.posY - 1.600000023841858D, p.posZ);
      }

      this.hitboxes = new ArrayList();
      RotatedAxes bodyAxes = new RotatedAxes(p.renderYawOffset, 0.0F, 0.0F);
      RotatedAxes headAxes = new RotatedAxes(p.rotationYawHead - p.renderYawOffset, 0.0F, -p.rotationPitch);
      this.hitboxes.add(new PlayerHitbox(this.player, bodyAxes, new Vector3f(0.0F, 0.0F, 0.0F), new Vector3f(-0.25F, 0.0F, -0.15F), new Vector3f(0.5F, 1.4F, 0.3F), EnumHitboxType.BODY));
      this.hitboxes.add(new PlayerHitbox(this.player, bodyAxes.findLocalAxesGlobally(headAxes), new Vector3f(0.0F, 1.4F, 0.0F), new Vector3f(-0.25F, 0.0F, -0.25F), new Vector3f(0.5F, 0.5F, 0.5F), EnumHitboxType.HEAD));
      float yHead = (p.rotationYawHead - p.renderYawOffset) / 57.295776F;
      float xHead = p.rotationPitch / 57.295776F;
      float zRight = 0.0F;
      float zLeft = 0.0F;
      float yRight = -0.1F + yHead - 1.5707964F;
      float yLeft = 0.1F + yHead + 0.4F - 1.5707964F;
      float xRight = -1.5707964F + xHead;
      float xLeft = -1.5707964F + xHead;
      zRight += MathHelper.cos((float)p.ticksExisted * 0.09F) * 0.05F + 0.05F;
      zLeft -= MathHelper.cos((float)p.ticksExisted * 0.09F) * 0.05F + 0.05F;
      xRight += MathHelper.sin((float)p.ticksExisted * 0.067F) * 0.05F;
      xLeft -= MathHelper.sin((float)p.ticksExisted * 0.067F) * 0.05F;
      RotatedAxes leftArmAxes = (new RotatedAxes()).rotateGlobalPitchInRads(xLeft).rotateGlobalYawInRads(3.1415927F + yLeft).rotateGlobalRollInRads(-zLeft);
      RotatedAxes rightArmAxes = (new RotatedAxes()).rotateGlobalPitchInRads(xRight).rotateGlobalYawInRads(3.1415927F + yRight).rotateGlobalRollInRads(-zRight);
      float originZRight = MathHelper.sin(-p.renderYawOffset * 3.1415927F / 180.0F) * 5.0F / 16.0F;
      float originXRight = -MathHelper.cos(-p.renderYawOffset * 3.1415927F / 180.0F) * 5.0F / 16.0F;
      float originZLeft = -MathHelper.sin(-p.renderYawOffset * 3.1415927F / 180.0F) * 5.0F / 16.0F;
      float originXLeft = MathHelper.cos(-p.renderYawOffset * 3.1415927F / 180.0F) * 5.0F / 16.0F;
      this.hitboxes.add(new PlayerHitbox(this.player, bodyAxes.findLocalAxesGlobally(leftArmAxes), new Vector3f(originXLeft, 1.3F, originZLeft), new Vector3f(-0.125F, -0.6F, -0.125F), new Vector3f(0.25F, 0.7F, 0.25F), EnumHitboxType.LEFTARM));
      this.hitboxes.add(new PlayerHitbox(this.player, bodyAxes.findLocalAxesGlobally(rightArmAxes), new Vector3f(originXRight, 1.3F, originZRight), new Vector3f(-0.125F, -0.6F, -0.125F), new Vector3f(0.25F, 0.7F, 0.25F), EnumHitboxType.RIGHTARM));
      ItemStack playerRightHandStack = this.player.getCurrentEquippedItem();
      if(playerRightHandStack != null && playerRightHandStack.getItem() instanceof ItemGun) {
         GunType gunType = ((ItemGun)playerRightHandStack.getItem()).type;
         if(gunType.shield) {
            this.hitboxes.add(new PlayerHitbox(this.player, bodyAxes.findLocalAxesGlobally(rightArmAxes), new Vector3f(originXRight, 1.3F, originZRight), new Vector3f(gunType.shieldOrigin.y, -1.05F + gunType.shieldOrigin.x, -0.0625F + gunType.shieldOrigin.z), new Vector3f(gunType.shieldDimensions.y, gunType.shieldDimensions.x, gunType.shieldDimensions.z), EnumHitboxType.RIGHTITEM));
         }

         PlayerData data = PlayerHandler.getPlayerData(this.player);
         if(gunType.oneHanded && data.offHandGunSlot != 0) {
            ItemStack leftHandStack = null;
            if(this.player.worldObj.isRemote && !FlansMod.proxy.isThePlayer(this.player)) {
               leftHandStack = data.offHandGunStack;
            } else {
               leftHandStack = this.player.inventory.getStackInSlot(data.offHandGunSlot - 1);
            }

            if(leftHandStack != null && leftHandStack.getItem() instanceof ItemGun) {
               GunType leftGunType = ((ItemGun)leftHandStack.getItem()).type;
               if(leftGunType.shield) {
                  this.hitboxes.add(new PlayerHitbox(this.player, bodyAxes.findLocalAxesGlobally(leftArmAxes), new Vector3f(originXLeft, 1.3F, originZLeft), new Vector3f(leftGunType.shieldOrigin.y, -1.05F + leftGunType.shieldOrigin.x, -0.0625F + leftGunType.shieldOrigin.z), new Vector3f(leftGunType.shieldDimensions.y, leftGunType.shieldDimensions.x, leftGunType.shieldDimensions.z), EnumHitboxType.LEFTITEM));
               }
            }
         }
      }

   }

   public ArrayList raytrace(Vector3f origin, Vector3f motion) {
      Vector3f localOrigin = Vector3f.sub(origin, this.pos, (Vector3f)null);
      ArrayList hits = new ArrayList();
      Iterator var5 = this.hitboxes.iterator();

      while(var5.hasNext()) {
         PlayerHitbox hitbox = (PlayerHitbox)var5.next();
         PlayerBulletHit hit = hitbox.raytrace(localOrigin, motion);
         if(hit != null && hit.intersectTime >= 0.0F && hit.intersectTime <= 1.0F) {
            hits.add(hit);
         }
      }

      return hits;
   }

   @SideOnly(Side.CLIENT)
   public void renderSnapshot() {
      Iterator var1 = this.hitboxes.iterator();

      while(var1.hasNext()) {
         PlayerHitbox hitbox = (PlayerHitbox)var1.next();
         hitbox.renderHitbox(this.player.worldObj, this.pos);
      }

   }


   public PlayerHitbox GetHitbox(EnumHitboxType type) {
	   Iterator var1 = this.hitboxes.iterator();
	
       while(var1.hasNext()) {
           if (hitbox.type != type) continue;
           return hitbox;
       }
       return null;
   }


   public Vector3f GetMuzzleLocation(GunType gunType, AttachmentType barrelAttachment, boolean isOffHand) {
       PlayerHitbox hitbox = this.GetHitbox(isOffHand ? EnumHitboxType.LEFTARM : EnumHitboxType.RIGHTARM);
       Vector3f muzzlePos = new Vector3f(hitbox.o.x, hitbox.o.y + hitbox.d.y * 0.5f, hitbox.o.z + hitbox.d.z * 0.5f);
       if (gunType != null && gunType.model != null) {
           Vector3f barrelAttach = new Vector3f(gunType.model.barrelAttachPoint.z, - gunType.model.barrelAttachPoint.x, gunType.model.barrelAttachPoint.y);
           Vector3f.add(muzzlePos, barrelAttach, muzzlePos);
       }
       muzzlePos = hitbox.axes.findLocalVectorGlobally(muzzlePos);
       Vector3f.add(muzzlePos, hitbox.rP, muzzlePos);
       return muzzlePos;
   }

}
