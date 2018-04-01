/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package com.flansmod.common.guns.raytracing;

import com.flansmod.common.CommonProxy;
import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.RotatedAxes;
import com.flansmod.common.guns.BulletType;
import com.flansmod.common.guns.EntityBullet;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.guns.ShootableType;
import com.flansmod.common.guns.raytracing.EnumHitboxType;
import com.flansmod.common.guns.raytracing.PlayerBulletHit;
import com.flansmod.common.teams.Gametype;
import com.flansmod.common.teams.TeamsManager;
import com.flansmod.common.teams.TeamsRound;
import com.flansmod.common.vector.Vector3f;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class PlayerHitbox {
    public EntityPlayer player;
    public RotatedAxes axes;
    public Vector3f rP;
    public Vector3f o;
    public Vector3f d;
    public EnumHitboxType type;

    public PlayerHitbox(EntityPlayer player, RotatedAxes axes, Vector3f rotationPoint, Vector3f origin, Vector3f dimensions, EnumHitboxType type) {
        this.player = player;
        this.axes = axes;
        this.o = origin;
        this.d = dimensions;
        this.type = type;
        this.rP = rotationPoint;
    }

    @SideOnly(value=Side.CLIENT)
    public void renderHitbox(World world, Vector3f pos) {
    }

    public PlayerBulletHit raytrace(Vector3f origin, Vector3f motion) {
        float intersectZ;
        float intersectTime;
        float intersectX;
        origin = Vector3f.sub(origin, this.rP, null);
        origin = this.axes.findGlobalVectorLocally(origin);
        motion = this.axes.findGlobalVectorLocally(motion);
        if (motion.x != 0.0f) {
            float intersectY;
            if (origin.x < this.o.x) {
                intersectTime = (this.o.x - origin.x) / motion.x;
                intersectY = origin.y + motion.y * intersectTime;
                intersectZ = origin.z + motion.z * intersectTime;
                if (intersectY >= this.o.y && intersectY <= this.o.y + this.d.y && intersectZ >= this.o.z && intersectZ <= this.o.z + this.d.z) {
                    return new PlayerBulletHit(this, intersectTime);
                }
            } else if (origin.x > this.o.x + this.d.x) {
                intersectTime = (this.o.x + this.d.x - origin.x) / motion.x;
                intersectY = origin.y + motion.y * intersectTime;
                intersectZ = origin.z + motion.z * intersectTime;
                if (intersectY >= this.o.y && intersectY <= this.o.y + this.d.y && intersectZ >= this.o.z && intersectZ <= this.o.z + this.d.z) {
                    return new PlayerBulletHit(this, intersectTime);
                }
            }
        }
        if (motion.z != 0.0f) {
            float intersectY;
            if (origin.z < this.o.z) {
                intersectTime = (this.o.z - origin.z) / motion.z;
                intersectX = origin.x + motion.x * intersectTime;
                intersectY = origin.y + motion.y * intersectTime;
                if (intersectX >= this.o.x && intersectX <= this.o.x + this.d.x && intersectY >= this.o.y && intersectY <= this.o.y + this.d.y) {
                    return new PlayerBulletHit(this, intersectTime);
                }
            } else if (origin.z > this.o.z + this.d.z) {
                intersectTime = (this.o.z + this.d.z - origin.z) / motion.z;
                intersectX = origin.x + motion.x * intersectTime;
                intersectY = origin.y + motion.y * intersectTime;
                if (intersectX >= this.o.x && intersectX <= this.o.x + this.d.x && intersectY >= this.o.y && intersectY <= this.o.y + this.d.y) {
                    return new PlayerBulletHit(this, intersectTime);
                }
            }
        }
        if (motion.y != 0.0f) {
            if (origin.y < this.o.y) {
                intersectTime = (this.o.y - origin.y) / motion.y;
                intersectX = origin.x + motion.x * intersectTime;
                intersectZ = origin.z + motion.z * intersectTime;
                if (intersectX >= this.o.x && intersectX <= this.o.x + this.d.x && intersectZ >= this.o.z && intersectZ <= this.o.z + this.d.z) {
                    return new PlayerBulletHit(this, intersectTime);
                }
            } else if (origin.y > this.o.y + this.d.y) {
                intersectTime = (this.o.y + this.d.y - origin.y) / motion.y;
                intersectX = origin.x + motion.x * intersectTime;
                intersectZ = origin.z + motion.z * intersectTime;
                if (intersectX >= this.o.x && intersectX <= this.o.x + this.d.x && intersectZ >= this.o.z && intersectZ <= this.o.z + this.d.z) {
                    return new PlayerBulletHit(this, intersectTime);
                }
            }
        }
        return null;
    }

    public float hitByBullet(EntityBullet bullet, float penetratingPower) {
        if(bullet.type.setEntitiesOnFire) {
           this.player.setFire(20);
        }

        Iterator damageModifier = bullet.type.hitEffects.iterator();

        while(damageModifier.hasNext()) {
           PotionEffect data = (PotionEffect)damageModifier.next();
           this.player.addPotionEffect(new PotionEffect(data));
        }

        float damageModifier1 = bullet.type.penetratingPower < 0.1f ? penetratingPower / bullet.type.penetratingPower : 1.0f;
        switch (this.type) {
            case BODY: {
                break;
            }
            case HEAD: {
                damageModifier1 *= 1.25f;
                break;
            }
            case LEFTARM: {
                damageModifier1 *= 0.8f;
                break;
            }
            case RIGHTARM: {
                damageModifier1 *= 0.8f;
                break;
            }
            case LEFTITEM: {
                break;
            }
            case RIGHTITEM: {
                break;
            }
        }
        switch (this.type) {
        case BODY: 
        case HEAD: 
        case LEFTARM: 
        case RIGHTARM: {
            DamageSource damagesource = null;
            float hitDamage = bullet.damage * bullet.type.damageVsPlayer * damageModifier1;
            DamageSource damageSource = bullet.owner == null ? DamageSource.generic : (damagesource = bullet.getBulletDamage(this.type == EnumHitboxType.HEAD));
            if (!this.player.worldObj.isRemote && hitDamage == 0.0f && TeamsManager.getInstance().currentRound != null) {
                TeamsManager.getInstance().currentRound.gametype.playerAttacked((EntityPlayerMP)this.player, damagesource);
            }
            if (this.player.attackEntityFrom(damagesource, hitDamage)) {
                ++this.player.arrowHitTimer;
                this.player.hurtResistantTime = this.player.maxHurtResistantTime / 2;
            }
            return penetratingPower - 1.0f;
        }
        case RIGHTITEM: {
            ItemStack currentStack = this.player.getCurrentEquippedItem();
            if (currentStack != null && currentStack.getItem() instanceof ItemGun) {
                GunType gunType = ((ItemGun)currentStack.getItem()).type;
                return penetratingPower - gunType.shieldDamageAbsorption;
            }
            return penetratingPower;
        }
        case LEFTITEM: {
            PlayerData data = PlayerHandler.getPlayerData(this.player);
            if (data.offHandGunSlot == 0) break;
            ItemStack leftHandStack = null;
            leftHandStack = this.player.worldObj.isRemote && !FlansMod.proxy.isThePlayer(this.player) ? data.offHandGunStack : this.player.inventory.getStackInSlot(data.offHandGunSlot - 1);
            if (leftHandStack == null || !(leftHandStack.getItem() instanceof ItemGun)) break;
            GunType leftGunType = ((ItemGun)leftHandStack.getItem()).type;
            return penetratingPower - leftGunType.shieldDamageAbsorption;
        }
    }
    return penetratingPower;
}

}
