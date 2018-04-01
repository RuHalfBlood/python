
package com.flansmod.client.model;

import com.flansmod.client.FlansModClient;
import com.flansmod.client.FlansModResourceHandler;
import com.flansmod.client.TickHandlerClient;
import com.flansmod.client.model.EnumAnimationType;
import com.flansmod.client.model.GunAnimations;
import com.flansmod.client.model.ModelAttachment;
import com.flansmod.client.model.ModelGun;
import com.flansmod.common.FlansMod;
import com.flansmod.common.PlayerData;
import com.flansmod.common.PlayerHandler;
import com.flansmod.common.guns.AttachmentType;
import com.flansmod.common.guns.EnumFireMode;
import com.flansmod.common.guns.GunType;
import com.flansmod.common.guns.IScope;
import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.guns.ItemGun;
import com.flansmod.common.paintjob.Paintjob;
import com.flansmod.common.vector.Vector3f;
import com.flansmod.hand.client.ModelHand;
import com.flansmod.hand.client.dev.flans.RenderConfigFlans;

import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderGun
implements IItemRenderer {
    private static TextureManager renderEngine;
    public static float smoothing;
	   private static final ModelHand HAND_MODEL = new ModelHand();
	   float sprint;
	   public ModelBase muzzleModel;
	    public static double muzzlePosX;
	    public static double muzzlePosY;
	    public static double muzzlePosZ;
	    final double max = 1;
	    Random rnd;
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        switch (type) {
            case ENTITY: {
                if (!Minecraft.getMinecraft().gameSettings.fancyGraphics) {
                    return false;
                }
            }
            case EQUIPPED: 
            case EQUIPPED_FIRST_PERSON: {
                return item != null && item.getItem() instanceof ItemGun && ((ItemGun)item.getItem()).type.model != null;
            }
        }
        return false;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public /* varargs */ void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
    	this.muzzleModel = new ModelMuzzle();
        EntityLivingBase entity;
        if (!(item.getItem() instanceof ItemGun)) {
            return;
        }
        RenderBlocks renderBlocks = (RenderBlocks)data[0];
        GunType gunType = ((ItemGun)item.getItem()).type;
        if (gunType == null) {
            return;
        }
        ModelGun model = gunType.model;
        if (model == null) {
            return;
        }
        GunAnimations animations = type == IItemRenderer.ItemRenderType.ENTITY ? new GunAnimations() : FlansModClient.getGunAnimations((EntityLivingBase)data[1], false);
        this.renderGun(type, item, gunType, animations, false, data);
        if (gunType.oneHanded && type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON && (entity = (EntityLivingBase)data[1]) instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            PlayerData playerData = PlayerHandler.getPlayerData(player, Side.CLIENT);
            if (playerData.offHandGunSlot != 0) {
                ItemStack offHandItem;
                GunAnimations animations2 = (GunAnimations) FlansMod.gunAnimationsLeft.get(data[1]);
                if (animations2 == null) {
                    animations2 = new GunAnimations();
                    FlansMod.gunAnimationsLeft.put((EntityLivingBase)data[1], animations2);
                }
                if ((offHandItem = player.inventory.getStackInSlot(playerData.offHandGunSlot - 1)) == null || !(offHandItem.getItem() instanceof ItemGun)) {
                    return;
                }
                GunType offHandGunType = ((ItemGun)offHandItem.getItem()).type;
                if (!offHandGunType.oneHanded) {
                    return;
                }
                this.renderGun(type, offHandItem, offHandGunType, animations2, true, data);
            }
        }
    }

    public void renderOffHandGun(EntityPlayer player, ItemStack offHandItemStack) {
        GunAnimations animations = (GunAnimations) FlansMod.gunAnimationsLeft.get((Object)player);
        if (animations == null) {
            animations = new GunAnimations();
            FlansMod.gunAnimationsLeft.put((EntityLivingBase)player, animations);
        }
        GunType offHandGunType = ((ItemGun)offHandItemStack.getItem()).type;
        if (!offHandGunType.oneHanded) {
            return;
        }
        
        this.renderGun(IItemRenderer.ItemRenderType.EQUIPPED, offHandItemStack, offHandGunType, animations, true, new Object[]{player});
    }

    private /* varargs */ void renderGun(IItemRenderer.ItemRenderType type, ItemStack item, GunType gunType, GunAnimations animations, boolean offHand, Object ... data) {
        float f = 0.0625f;
        
        ModelGun model = gunType.model;
        int flip = offHand ? -1 : 1;
        GL11.glPushMatrix();
        float reloadRotate = 0.0f;
        block0 : switch (type) {
            case ENTITY: {
                EntityItem entity = (EntityItem)data[1];
                GL11.glRotatef((float)((float)entity.age + (entity.age == 0 ? 0.0f : smoothing)), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)(-0.2f + model.itemFrameOffset.x), (float)(0.2f + model.itemFrameOffset.y), (float)(0.1f + model.itemFrameOffset.z));
                break;
            }
            case EQUIPPED: {
                if (offHand) {
                    GL11.glRotatef((float)-70.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)48.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glRotatef((float)105.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glTranslatef((float)-0.1f, (float)-0.22f, (float)-0.15f);
                } else {
                    GL11.glRotatef((float)35.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glRotatef((float)-5.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glTranslatef((float)0.75f, (float)-0.22f, (float)-0.08f);
                    GL11.glScalef((float)1.0f, (float)1.0f, (float)-1.0f);
                }
                GL11.glTranslatef((float)model.thirdPersonOffset.x, (float)model.thirdPersonOffset.y, (float)model.thirdPersonOffset.z);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                IScope scope = gunType.getCurrentScope(item);
                if (FlansModClient.zoomProgress > 0.9f && scope.hasZoomOverlay()) {
                    GL11.glPopMatrix();
                    return;
                }
                float adsSwitch = FlansModClient.lastZoomProgress + (FlansModClient.zoomProgress - FlansModClient.lastZoomProgress) * smoothing;
                if (offHand) {
                    GL11.glTranslatef((float)0.0f, (float)0.03f, (float)-0.76f);
                    GL11.glRotatef((float)23.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glRotatef((float)-4.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glTranslatef((float)0.15f, (float)0.2f, (float)-0.6f);
                } else {
                    GL11.glRotatef((float)(25.0f - 5.0f * adsSwitch), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glRotatef((float)-5.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glTranslatef((float)0.15f, (float)(0.2f + 0.175f * adsSwitch), (float)(-0.6f - 0.405f * adsSwitch));
                    if (gunType.hasScopeOverlay) {
                        GL11.glTranslatef((float)(-0.3f * adsSwitch), (float)0.0f, (float)0.0f);
                    }
                    GL11.glRotatef((float)(4.5f * adsSwitch), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)(-0.03f * adsSwitch), (float)0.0f);
                }
                if (animations.meleeAnimationProgress > 0 && animations.meleeAnimationProgress < gunType.meleePath.size()) {
                    Vector3f meleePos = (Vector3f) gunType.meleePath.get(animations.meleeAnimationProgress);
                    Vector3f nextMeleePos = (Vector3f) (animations.meleeAnimationProgress + 1 < gunType.meleePath.size() ? gunType.meleePath.get(animations.meleeAnimationProgress + 1) : new Vector3f());
                    GL11.glTranslatef((float)(meleePos.x + (nextMeleePos.x - meleePos.x) * smoothing), (float)(meleePos.y + (nextMeleePos.y - meleePos.y) * smoothing), (float)(meleePos.z + (nextMeleePos.z - meleePos.z) * smoothing));
                    Vector3f meleeAngles = (Vector3f) gunType.meleePathAngles.get(animations.meleeAnimationProgress);
                    Vector3f nextMeleeAngles = (Vector3f) (animations.meleeAnimationProgress + 1 < gunType.meleePathAngles.size() ? gunType.meleePathAngles.get(animations.meleeAnimationProgress + 1) : new Vector3f());
                    GL11.glRotatef((float)(meleeAngles.y + (nextMeleeAngles.y - meleeAngles.y) * smoothing), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(meleeAngles.z + (nextMeleeAngles.z - meleeAngles.z) * smoothing), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glRotatef((float)(meleeAngles.x + (nextMeleeAngles.x - meleeAngles.x) * smoothing), (float)1.0f, (float)0.0f, (float)0.0f);
                }
                if (model.spinningCocking) {
                    GL11.glTranslatef((float)model.spinPoint.x, (float)model.spinPoint.y, (float)model.spinPoint.z);
                    float pumped = animations.lastPumped + (animations.pumped - animations.lastPumped) * smoothing;
                    GL11.glRotatef((float)(pumped * 180.0f + 180.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)(- model.spinPoint.x), (float)(- model.spinPoint.y), (float)(- model.spinPoint.z));
                }
                if (!animations.reloading) break;
                float effectiveReloadAnimationProgress = animations.lastReloadAnimationProgress + (animations.reloadAnimationProgress - animations.lastReloadAnimationProgress) * smoothing;
                reloadRotate = 1.0f;
                if (effectiveReloadAnimationProgress < model.tiltGunTime) {
                    reloadRotate = effectiveReloadAnimationProgress / model.tiltGunTime;
                }
                if (effectiveReloadAnimationProgress > model.tiltGunTime + model.unloadClipTime + model.loadClipTime) {
                    reloadRotate = 1.0f - (effectiveReloadAnimationProgress - (model.tiltGunTime + model.unloadClipTime + model.loadClipTime)) / model.untiltGunTime;
                }
                switch (model.animationType) {
                    case BOTTOM_CLIP: 
                    case PISTOL_CLIP: 
                    case SHOTGUN: 
                    case END_LOADED: {
                        GL11.glRotatef((float)(60.0f * reloadRotate), (float)0.0f, (float)0.0f, (float)1.0f);
                        GL11.glRotatef((float)(30.0f * reloadRotate * (float)flip), (float)1.0f, (float)0.0f, (float)0.0f);
                        GL11.glTranslatef((float)(0.25f * reloadRotate), (float)0.0f, (float)0.0f);
                        break block0;
                    }
                    case BACK_LOADED: {
                        GL11.glRotatef((float)(-75.0f * reloadRotate), (float)0.0f, (float)0.0f, (float)1.0f);
                        GL11.glRotatef((float)(-30.0f * reloadRotate * (float)flip), (float)1.0f, (float)0.0f, (float)0.0f);
                        GL11.glTranslatef((float)(0.5f * reloadRotate), (float)0.0f, (float)0.0f);
                        break block0;
                    }
                    case BULLPUP: {
                        GL11.glRotatef((float)(70.0f * reloadRotate), (float)0.0f, (float)0.0f, (float)1.0f);
                        GL11.glRotatef((float)(10.0f * reloadRotate * (float)flip), (float)1.0f, (float)0.0f, (float)0.0f);
                        GL11.glTranslatef((float)(0.5f * reloadRotate), (float)(-0.2f * reloadRotate), (float)0.0f);
                        break block0;
                    }
                    case RIFLE: {
                        GL11.glRotatef((float)(30.0f * reloadRotate), (float)0.0f, (float)0.0f, (float)1.0f);
                        GL11.glRotatef((float)(-30.0f * reloadRotate * (float)flip), (float)1.0f, (float)0.0f, (float)0.0f);
                        GL11.glTranslatef((float)(0.5f * reloadRotate), (float)0.0f, (float)(-0.5f * reloadRotate));
                        break block0;
                    }
                    case RIFLE_TOP: 
                    case REVOLVER: {
                        GL11.glRotatef((float)(30.0f * reloadRotate), (float)0.0f, (float)0.0f, (float)1.0f);
                        GL11.glRotatef((float)(10.0f * reloadRotate), (float)0.0f, (float)1.0f, (float)0.0f);
                        GL11.glRotatef((float)(-10.0f * reloadRotate * (float)flip), (float)1.0f, (float)0.0f, (float)0.0f);
                        GL11.glTranslatef((float)(0.1f * reloadRotate), (float)(-0.2f * reloadRotate), (float)(-0.1f * reloadRotate));
                        break block0;
                    }
                    case REVOLVER2: {
                        GL11.glRotatef((float)(20.0f * reloadRotate), (float)0.0f, (float)0.0f, (float)1.0f);
                        GL11.glRotatef((float)(-10.0f * reloadRotate * (float)flip), (float)1.0f, (float)0.0f, (float)0.0f);
                        break block0;
                    }
                    case ALT_PISTOL_CLIP: {
                        GL11.glRotatef((float)(60.0f * reloadRotate * (float)flip), (float)0.0f, (float)1.0f, (float)0.0f);
                        GL11.glTranslatef((float)(0.15f * reloadRotate), (float)(0.25f * reloadRotate), (float)0.0f);
                        break block0;
                    }
                    case STRIKER: {
                        GL11.glRotatef((float)(-35.0f * reloadRotate * (float)flip), (float)1.0f, (float)0.0f, (float)0.0f);
                        GL11.glTranslatef((float)(0.2f * reloadRotate), (float)0.0f, (float)(-0.1f * reloadRotate));
                        break block0;
                    }
                    case GENERIC: {
                        GL11.glRotatef((float)(45.0f * reloadRotate), (float)0.0f, (float)0.0f, (float)1.0f);
                        GL11.glTranslatef((float)(-0.2f * reloadRotate), (float)(-0.5f * reloadRotate), (float)0.0f);
                        break block0;
                    }
                    case CUSTOM: {
                        GL11.glRotatef((float)(model.rotateGunVertical * reloadRotate), (float)0.0f, (float)0.0f, (float)1.0f);
                        GL11.glRotatef((float)(model.rotateGunHorizontal * reloadRotate), (float)0.0f, (float)1.0f, (float)0.0f);
                        GL11.glRotatef((float)(model.tiltGun * reloadRotate), (float)1.0f, (float)0.0f, (float)0.0f);
                        GL11.glTranslatef((float)(model.translateGun.x * reloadRotate), (float)(model.translateGun.y * reloadRotate), (float)(model.translateGun.z * reloadRotate));
                        break block0;
                    }
                }
                break;
            }
        }
        RenderConfigFlans anim2 = ((ItemGun)item.getItem()).getRenderConfig();
        if(this.floatAsBool(anim2.anim_by_left_hand_bool) || !this.floatAsBool(anim2.anim_bool)) {
           this.renderRightHand(anim2, 1.0F, false, 0.0F, animations);
        }

        if(!this.floatAsBool(anim2.anim_by_left_hand_bool) || !this.floatAsBool(anim2.anim_bool)) {
           this.renderLeftHand(anim2, 1.0F, false, 0.0F, animations);
        }
     

        this.renderGun(item, gunType, f, model, animations, reloadRotate, type);
        GL11.glPopMatrix();
    }
	private void renderLeftHand(RenderConfigFlans anim, float size, boolean animate, float progress, GunAnimations animations) {
	      if(this.floatAsBool(anim.h_l_render_bool)) {
	  
	         GL11.glPushMatrix();
	         GL11.glScaled((double)(1.0F / size), (double)(1.0F / size), (double)(1.0F / size));
	         GL11.glTranslated(-0.4D + (double)anim.h_l_off_z, 0.2D + (double)anim.h_l_off_y, -0.4D + (double)anim.h_l_off_x);
	         GL11.glTranslated((double)(FlansModClient.zoomProgress * anim.aim_h_l_off_z), (double)(FlansModClient.zoomProgress * anim.aim_h_l_off_y), (double)(FlansModClient.zoomProgress * anim.aim_h_l_off_x));
	         if(animate) {
	            float animtime = progress;
	            if(progress > 0.5F) {
	               animtime = Math.abs(progress - 1.0F);
	            }

	            if(animtime > 0.25F) {
	               animtime = 0.25F;
	            }

	            animtime *= 4.0F;
	            GL11.glTranslated((double)(animtime * anim.anim_h_off_z), (double)(animtime * anim.anim_h_off_y), (double)(animtime * anim.anim_h_off_x));
	            GL11.glRotated((double)(animtime * anim.anim_h_rot_y), 0.0D, 0.0D, 1.0D);
	            GL11.glRotated((double)(animtime * anim.anim_h_rot_z), 1.0D, 0.0D, 0.0D);
	            GL11.glRotated((double)(animtime * anim.anim_h_rot_x), 0.0D, 1.0D, 0.0D);
	         }
	        GL11.glTranslatef((float)((- animations.lastGunRecoil + (animations.gunRecoil - animations.lastGunRecoil) * smoothing) *  0.05), (float)0.0f, (float)0.0f);
	          GL11.glRotatef((float)((- animations.lastGunRecoil + (animations.gunRecoil - animations.lastGunRecoil) * smoothing) *  0.05), (float)0.0f, (float)0.0f, (float)1.0f);
	         GL11.glRotated((double)(-90.0F + anim.h_l_rot_y), 0.0D, 0.0D, 1.0D);
	         GL11.glRotated((double)(FlansModClient.zoomProgress * anim.aim_h_l_rot_y), 0.0D, 0.0D, 1.0D);
	         GL11.glRotated((double)(30.0F + anim.h_l_rot_x), 1.0D, 0.0D, 0.0D);
	         GL11.glRotated((double)(FlansModClient.zoomProgress * anim.aim_h_l_rot_x), 1.0D, 0.0D, 0.0D);
	         GL11.glRotated((double)anim.h_l_rot_z, 0.0D, 1.0D, 0.0D);
	         GL11.glRotated((double)(FlansModClient.zoomProgress * anim.aim_h_l_rot_z), 0.0D, 1.0D, 0.0D);
	         GL11.glScaled(0.75D + (double)anim.h_l_size, 0.75D + (double)anim.h_l_l + (double)anim.h_l_size, 0.75D + (double)anim.h_l_size);
	         HAND_MODEL.render(Minecraft.getMinecraft().thePlayer, 2);
	         GL11.glPopMatrix();
	      }

	   }

	   private void renderRightHand(RenderConfigFlans anim, float size, boolean animate, float progress, GunAnimations animations) {
	      if(this.floatAsBool(anim.h_r_render_bool)) {
	  
	         GL11.glPushMatrix();
	         GL11.glScaled((double)(1.0F / size), (double)(1.0F / size), (double)(1.0F / size));
	         GL11.glTranslated(-0.4D + (double)anim.h_r_off_z, 0.2D + (double)anim.h_r_off_y, -0.1D + (double)anim.h_r_off_x);
	         GL11.glTranslated((double)(FlansModClient.zoomProgress * anim.aim_h_r_off_z), (double)(FlansModClient.zoomProgress * anim.aim_h_r_off_y), (double)(FlansModClient.zoomProgress * anim.aim_h_r_off_x));
	         if(animate) {
	            float animtime = progress;
	            if(progress > 0.5F) {
	               animtime = Math.abs(progress - 1.0F);
	            }

	            if(animtime > 0.25F) {
	               animtime = 0.25F;
	            }

	            animtime *= 4.0F;
	            GL11.glTranslated((double)(animtime * anim.anim_h_off_z), (double)(animtime * anim.anim_h_off_y), (double)(animtime * anim.anim_h_off_x));
	            GL11.glRotated((double)(animtime * anim.anim_h_rot_y), 0.0D, 0.0D, 1.0D);
	            GL11.glRotated((double)(animtime * anim.anim_h_rot_z), 1.0D, 0.0D, 0.0D);
	            GL11.glRotated((double)(animtime * anim.anim_h_rot_x), 0.0D, 1.0D, 0.0D);
	         }
	         Minecraft mc = Minecraft.getMinecraft();
	         EntityClientPlayerMP player = mc.thePlayer;
	         if (player.isSprinting()) {
	                sprint = MathHelper.clamp_float((float)(sprint + 0.05f), (float)0.0f, (float)1.0f);
	                GL11.glRotatef((float)(0.0f - sprint * 35f), (float)0.0f, (float)0.0f, (float)1.0f);
	            } else {
	            	 sprint = MathHelper.clamp_float((float)(sprint - 0.05f), (float)0.0f, (float)1.0f);
	                GL11.glRotatef((float)(0.0f - sprint * 35f), (float)0.0f, (float)0.0f, (float)1.0f);
	            }
	          GL11.glTranslatef((float)((- animations.lastGunRecoil + (animations.gunRecoil - animations.lastGunRecoil) * smoothing) * 0.05F), (float)0.0f, (float)0.0f);
	          GL11.glRotatef((float)((- animations.lastGunRecoil + (animations.gunRecoil - animations.lastGunRecoil) * smoothing) *  0.05F), (float)0.0f, (float)0.0f, (float)1.0f);
	         GL11.glRotated((double)(-90.0F + anim.h_r_rot_y), 0.0D, 0.0D, 1.0D);
	         GL11.glRotated((double)(FlansModClient.zoomProgress * anim.aim_h_r_rot_y), 0.0D, 0.0D, 1.0D);
	         GL11.glRotated((double)anim.h_r_rot_x, 1.0D, 0.0D, 0.0D);
	         GL11.glRotated((double)(FlansModClient.zoomProgress * anim.aim_h_r_rot_x), 1.0D, 0.0D, 0.0D);
	         GL11.glRotated((double)anim.h_r_rot_z, 0.0D, 1.0D, 0.0D);
	         GL11.glRotated((double)(FlansModClient.zoomProgress * anim.aim_h_r_rot_z), 0.0D, 1.0D, 0.0D);
	         GL11.glScaled(0.75D + (double)anim.h_r_size, 0.75D + (double)anim.h_r_l + (double)anim.h_r_size, 0.75D + (double)anim.h_r_size);
	         HAND_MODEL.render(Minecraft.getMinecraft().thePlayer, 2);
	         GL11.glPopMatrix();
	      }

	   }

	   private boolean floatAsBool(float f) {
	      return f % 2.0F == 0.0F;
	   }

    public void renderGun(ItemStack item, GunType type, float f, ModelGun model, GunAnimations animations, float reloadRotate, IItemRenderer.ItemRenderType rtype) {

       

        GL11.glPushMatrix();
        
        Minecraft mc = Minecraft.getMinecraft();
        EntityClientPlayerMP player = mc.thePlayer;

       

        if (rtype == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.0f);
            if (player.isSprinting()) {
                sprint = MathHelper.clamp_float((float)(sprint + 0.05f), (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)(0.0f - sprint * 35f), (float)0.0f, (float)0.0f, (float)1.0f);
            } else {
           
            	 sprint = MathHelper.clamp_float((float)(sprint - 0.05f), (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)(0.0f - sprint * 35f), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glTranslatef((float)((- animations.lastGunRecoil + (animations.gunRecoil - animations.lastGunRecoil) * smoothing) *  0.05), (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)((- animations.lastGunRecoil + (animations.gunRecoil - animations.lastGunRecoil) * smoothing) *  0.05), (float)0.0f, (float)0.0f, (float)1.0f);

        
            if (model.animationType == EnumAnimationType.SHOTGUN && !animations.reloading) {
                GL11.glRotatef((float)((- 1.0f - Math.abs(animations.lastPumped + (animations.pumped - animations.lastPumped) * smoothing)) * -5.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)((- 1.0f - Math.abs(animations.lastPumped + (animations.pumped - animations.lastPumped) * smoothing)) * 5.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            if (model.isSingleAction) {
                GL11.glRotatef((float)((- 1.0f - Math.abs(animations.lastGunPullback + (animations.gunPullback - animations.lastGunPullback) * smoothing)) * -5.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)((- 1.0f - Math.abs(animations.lastGunPullback + (animations.gunPullback - animations.lastGunPullback) * smoothing)) * 2.5f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
        }
        if (renderEngine == null) {
            renderEngine = Minecraft.getMinecraft().renderEngine;
        }
        if (animations == null) {
            animations = GunAnimations.defaults;
        }
        AttachmentType scopeAttachment = type.getScope(item);
        AttachmentType barrelAttachment = type.getBarrel(item);
        AttachmentType stockAttachment = type.getStock(item);
        AttachmentType gripAttachment = type.getGrip(item);
        AttachmentType gadgetAttachment = type.getGadget(item);
        AttachmentType slideAttachment = type.getSlide(item);
        AttachmentType pumpAttachment = type.getPump(item);
        AttachmentType accessoryAttachment = type.getAccessory(item);
        ItemStack scopeItemStack = type.getScopeItemStack(item);
        ItemStack barrelItemStack = type.getBarrelItemStack(item);
        ItemStack stockItemStack = type.getStockItemStack(item);
        ItemStack gripItemStack = type.getGripItemStack(item);
        ItemStack gadgetItemStack = type.getGadgetItemStack(item);
        ItemStack slideItemStack = type.getSlideItemStack(item);
        ItemStack pumpItemStack = type.getPumpItemStack(item);
        ItemStack accessoryItemStack = type.getAccessoryItemStack(item);
        ItemStack[] bulletStacks = new ItemStack[type.numAmmoItemsInGun];
        boolean empty = true;
        for (int i = 0; i < type.numAmmoItemsInGun; ++i) {
            bulletStacks[i] = ((ItemGun)item.getItem()).getBulletItemStack(item, i);
            if (bulletStacks[i] == null || !(bulletStacks[i].getItem() instanceof ItemBullet) || bulletStacks[i].getItemDamage() >= bulletStacks[i].getMaxDamage()) continue;
            empty = false;
        }


        EntityClientPlayerMP entityPlayer = mc.thePlayer;
        int textureIndex = (int)(Math.random() * 3.0);
        PlayerData data = PlayerHandler.getPlayerData((EntityPlayer)entityPlayer, Side.CLIENT);
        muzzlePosX = -0.025;
        muzzlePosY = 0.08;
        muzzlePosZ = -0.8 + model.muzzleDistance;
        if (rtype == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {

            if (TickHandlerClient.muzzleTick >= 1.0 && data.offHandGunSlot == 0) {
                GL11.glPushMatrix();
                GL11.glPushMatrix();
                GL11.glRotated((double)-90.0, (double)-0.08, (double)1.0, (double)0.0);
                GL11.glRotated((double)0.0, (double)1.0, (double)0.0, (double)0.0);
                GL11.glScaled((double)2.0, (double)2.0, (double)2.0);
                GL11.glTranslated((double)muzzlePosX, (double)muzzlePosY, (double)muzzlePosZ);
                GL11.glEnable((int)32826);
                GL11.glDisable((int)3008);
                GL11.glDisable((int)2896);
                GL11.glEnable((int)3042);
                GL11.glDepthMask((boolean)false);
                mc.getTextureManager().bindTexture(new ResourceLocation("flansmod:textures/models/muzzleflash" + textureIndex + ".png"));
                this.muzzleModel.render((Entity)entityPlayer, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0325f);
                GL11.glDepthMask((boolean)true);
                GL11.glDisable((int)32826);
                GL11.glEnable((int)3008);
                GL11.glEnable((int)2896);
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                GL11.glPopMatrix();
            }
        }
        
        if (model.slideLockOnEmpty) {
            if (empty) {
                animations.onGunEmpty(true);
            } else if (!empty && !animations.reloading) {
                animations.onGunEmpty(false);
            }
        }

        renderEngine.bindTexture(FlansModResourceHandler.getPaintjobTexture(type.getPaintjob(item.getItemDamage())));
        if (scopeAttachment != null) {
            GL11.glTranslatef((float)0.0f, (float)((- scopeAttachment.model.renderOffset) / 16.0f), (float)0.0f);
        }
        GL11.glPushMatrix();
        GL11.glScalef((float)type.modelScale, (float)type.modelScale, (float)type.modelScale);
        model.renderGun(f);
        if (scopeAttachment == null && !model.scopeIsOnSlide && !model.scopeIsOnBreakAction) {
            model.renderDefaultScope(f);
        }
        if (barrelAttachment == null) {
            model.renderDefaultBarrel(f);
        }
        if (stockAttachment == null) {
            model.renderDefaultStock(f);
        }
        if (gripAttachment == null && !model.gripIsOnPump) {
            model.renderDefaultGrip(f);
        }
        if (gadgetAttachment == null && !model.gadgetIsOnPump) {
            model.renderDefaultGadget(f);
        }

        if (slideAttachment == null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((- animations.lastGunSlide + (animations.gunSlide - animations.lastGunSlide) * smoothing) * model.gunSlideDistance), (float)0.0f, (float)0.0f);
            model.renderSlide(f);
            if (scopeAttachment == null && model.scopeIsOnSlide) {
                model.renderDefaultScope(f);
            }
            GL11.glPopMatrix();
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)model.barrelBreakPoint.x, (float)model.barrelBreakPoint.y, (float)model.barrelBreakPoint.z);
        GL11.glRotatef((float)(reloadRotate * (- model.breakAngle)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)(- model.barrelBreakPoint.x), (float)(- model.barrelBreakPoint.y), (float)(- model.barrelBreakPoint.z));
        model.renderBreakAction(f);
        if (scopeAttachment == null && model.scopeIsOnBreakAction) {
            model.renderDefaultScope(f);
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)model.hammerSpinPoint.x, (float)model.hammerSpinPoint.y, (float)model.hammerSpinPoint.z);
        GL11.glRotatef((float)(- animations.hammerRotation), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)(- model.hammerSpinPoint.x), (float)(- model.hammerSpinPoint.y), (float)(- model.hammerSpinPoint.z));
        model.renderHammer(f);
        GL11.glPopMatrix();
        if (pumpAttachment == null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((- 1.0f - Math.abs(animations.lastPumped + (animations.pumped - animations.lastPumped) * smoothing)) * model.pumpHandleDistance), (float)0.0f, (float)0.0f);
            model.renderPump(f);
            if (gripAttachment == null && model.gripIsOnPump) {
                model.renderDefaultGrip(f);
            }
            if (gadgetAttachment == null && model.gadgetIsOnPump) {
                model.renderDefaultGadget(f);
            }
            GL11.glPopMatrix();
        }
        if (type.mode == EnumFireMode.MINIGUN) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)model.minigunBarrelOrigin.x, (float)model.minigunBarrelOrigin.y, (float)model.minigunBarrelOrigin.z);
            GL11.glRotatef((float)animations.minigunBarrelRotation, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)(- model.minigunBarrelOrigin.x), (float)(- model.minigunBarrelOrigin.y), (float)(- model.minigunBarrelOrigin.z));
            model.renderMinigunBarrel(f);
            GL11.glPopMatrix();
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)model.revolverFlipPoint.x, (float)model.revolverFlipPoint.y, (float)model.revolverFlipPoint.z);
        GL11.glRotatef((float)(reloadRotate * model.revolverFlipAngle), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)(- model.revolverFlipPoint.x), (float)(- model.revolverFlipPoint.y), (float)(- model.revolverFlipPoint.z));
        model.renderRevolverBarrel(f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)model.revolverFlipPoint.x, (float)model.revolverFlipPoint.y, (float)model.revolverFlipPoint.z);
        GL11.glRotatef((float)(reloadRotate * model.revolverFlipAngle), (float)-1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)(- model.revolverFlipPoint.x), (float)(- model.revolverFlipPoint.y), (float)(- model.revolverFlipPoint.z));
        model.renderRevolver2Barrel(f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        boolean shouldRender = true;
        switch (model.animationType) {
            case END_LOADED: 
            case BACK_LOADED: {
                if (!empty) break;
                shouldRender = false;
                break;
            }
        }
        if (shouldRender && animations.reloading && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
            float effectiveReloadAnimationProgress = animations.lastReloadAnimationProgress + (animations.reloadAnimationProgress - animations.lastReloadAnimationProgress) * smoothing;
            float clipPosition = 0.0f;
            if (effectiveReloadAnimationProgress > model.tiltGunTime && effectiveReloadAnimationProgress < model.tiltGunTime + model.unloadClipTime) {
                clipPosition = (effectiveReloadAnimationProgress - model.tiltGunTime) / model.unloadClipTime;
            }
            if (effectiveReloadAnimationProgress >= model.tiltGunTime + model.unloadClipTime && effectiveReloadAnimationProgress < model.tiltGunTime + model.unloadClipTime + model.loadClipTime) {
                clipPosition = 1.0f - (effectiveReloadAnimationProgress - (model.tiltGunTime + model.unloadClipTime)) / model.loadClipTime;
            }
            float loadOnlyClipPosition = Math.max(0.0f, Math.min(1.0f, 1.0f - (effectiveReloadAnimationProgress - model.tiltGunTime) / (model.unloadClipTime + model.loadClipTime)));
            switch (model.animationType) {
                case BREAK_ACTION: {
                    GL11.glTranslatef((float)model.barrelBreakPoint.x, (float)model.barrelBreakPoint.y, (float)model.barrelBreakPoint.z);
                    GL11.glRotatef((float)(reloadRotate * (- model.breakAngle)), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)(- model.barrelBreakPoint.x), (float)(- model.barrelBreakPoint.y), (float)(- model.barrelBreakPoint.z));
                    GL11.glTranslatef((float)(-1.0f * clipPosition * 1.0f / type.modelScale), (float)0.0f, (float)0.0f);
                    break;
                }
                case REVOLVER: {
                    GL11.glTranslatef((float)model.revolverFlipPoint.x, (float)model.revolverFlipPoint.y, (float)model.revolverFlipPoint.z);
                    GL11.glRotatef((float)(reloadRotate * model.revolverFlipAngle), (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glTranslatef((float)(- model.revolverFlipPoint.x), (float)(- model.revolverFlipPoint.y), (float)(- model.revolverFlipPoint.z));
                    GL11.glTranslatef((float)(-1.0f * clipPosition * 1.0f / type.modelScale), (float)0.0f, (float)0.0f);
                    break;
                }
                case REVOLVER2: {
                    GL11.glTranslatef((float)model.revolver2FlipPoint.x, (float)model.revolver2FlipPoint.y, (float)model.revolver2FlipPoint.z);
                    GL11.glRotatef((float)(reloadRotate * model.revolver2FlipAngle), (float)-1.0f, (float)0.0f, (float)0.0f);
                    GL11.glTranslatef((float)(- model.revolver2FlipPoint.x), (float)(- model.revolver2FlipPoint.y), (float)(- model.revolver2FlipPoint.z));
                    GL11.glTranslatef((float)(-1.0f * clipPosition * 1.0f / type.modelScale), (float)0.0f, (float)0.0f);
                    break;
                }
                case BOTTOM_CLIP: {
                	 GL11.glRotatef((float)(clipPosition * clipPosition), (float)0.0f, (float)0.0f, (float)1.0f);
                     GL11.glTranslatef((float)0.0f, (float)(-0.25f * clipPosition * 1.0f / type.modelScale), (float)0.0f);
                     break;
                }
                case PISTOL_CLIP: {
                    GL11.glRotatef((float)(-90.0f * clipPosition * clipPosition), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)(-1.0f * clipPosition * 1.0f / type.modelScale), (float)0.0f);
                    break;
                }
                case ALT_PISTOL_CLIP: {
                    GL11.glRotatef((float)(5.0f * clipPosition), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)(-3.0f * clipPosition * 1.0f / type.modelScale), (float)0.0f);
                    break;
                }
                case SIDE_CLIP: {
                    GL11.glRotatef((float)(180.0f * clipPosition), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(60.0f * clipPosition), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glTranslatef((float)(0.5f * clipPosition * 1.0f / type.modelScale), (float)0.0f, (float)0.0f);
                    break;
                }
                case BULLPUP: {
                    GL11.glRotatef((float)(-150.0f * clipPosition), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glRotatef((float)(60.0f * clipPosition), (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glTranslatef((float)(1.0f * clipPosition * 1.0f / type.modelScale), (float)(-0.5f * clipPosition * 1.0f / type.modelScale), (float)0.0f);
                    break;
                }
                case P90: {
                    GL11.glRotatef((float)(-15.0f * reloadRotate * reloadRotate), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)(0.075f * reloadRotate), (float)0.0f);
                    GL11.glTranslatef((float)(-2.0f * clipPosition * 1.0f / type.modelScale), (float)(-0.3f * clipPosition * 1.0f / type.modelScale), (float)(0.5f * clipPosition * 1.0f / type.modelScale));
                    break;
                }
                case RIFLE: {
                    float thing = clipPosition * model.numBulletsInReloadAnimation;
                    int bulletNum = MathHelper.floor_float((float)thing);
                    float bulletProgress = thing - (float)bulletNum;
                    GL11.glRotatef((float)(bulletProgress * 15.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(bulletProgress * 15.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)(bulletProgress * -1.0f * 1.0f / type.modelScale), (float)0.0f, (float)(bulletProgress * 0.5f * 1.0f / type.modelScale));
                    break;
                }
                case RIFLE_TOP: {
                    float thing = clipPosition * model.numBulletsInReloadAnimation;
                    int bulletNum = MathHelper.floor_float((float)thing);
                    float bulletProgress = thing - (float)bulletNum;
                    GL11.glRotatef((float)(bulletProgress * 55.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(bulletProgress * 95.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)(bulletProgress * -0.1f * 1.0f / type.modelScale), (float)(bulletProgress * 1.0f * 1.0f / type.modelScale), (float)(bulletProgress * 0.5f * 1.0f / type.modelScale));
                    break;
                }
                case SHOTGUN: 
                case STRIKER: {
                    float thing = clipPosition * model.numBulletsInReloadAnimation;
                    int bulletNum = MathHelper.floor_float((float)thing);
                    float bulletProgress = thing - (float)bulletNum;
                    GL11.glRotatef((float)(bulletProgress * -30.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)(bulletProgress * -0.5f * 1.0f / type.modelScale), (float)(bulletProgress * -1.0f * 1.0f / type.modelScale), (float)0.0f);
                    break;
                }
                case CUSTOM: {
                    GL11.glRotatef((float)(model.rotateClipVertical * clipPosition), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glRotatef((float)(model.rotateClipHorizontal * clipPosition), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(model.tiltClip * clipPosition), (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glTranslatef((float)(model.translateClip.x * clipPosition * 1.0f / type.modelScale), (float)(model.translateClip.y * clipPosition * 1.0f / type.modelScale), (float)(model.translateClip.z * clipPosition * 1.0f / type.modelScale));
                    break;
                }
                case END_LOADED: {
                    float dYaw = loadOnlyClipPosition > 0.5f ? loadOnlyClipPosition * 2.0f - 1.0f : 0.0f;
                    GL11.glRotatef((float)(-45.0f * dYaw), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)((- model.endLoadedAmmoDistance) * dYaw), (float)(-0.5f * dYaw), (float)0.0f);
                    float xDisplacement = loadOnlyClipPosition < 0.5f ? loadOnlyClipPosition * 2.0f : 1.0f;
                    GL11.glTranslatef((float)(model.endLoadedAmmoDistance * xDisplacement), (float)0.0f, (float)0.0f);
                    break;
                }
                case BACK_LOADED: {
                    float dYaw = loadOnlyClipPosition > 0.5f ? loadOnlyClipPosition * 2.0f - 1.0f : 0.0f;
                    GL11.glTranslatef((float)(model.endLoadedAmmoDistance * dYaw), (float)(-0.5f * dYaw), (float)0.0f);
                    float xDisplacement = loadOnlyClipPosition < 0.5f ? loadOnlyClipPosition * 2.0f : 1.0f;
                    GL11.glTranslatef((float)((- model.endLoadedAmmoDistance) * xDisplacement), (float)0.0f, (float)0.0f);
                }
            }
        }

        renderEngine.bindTexture(FlansModResourceHandler.getPaintjobTexture(type.getPaintjob(item.getItemDamage())));
        if (shouldRender) {
            model.renderAmmo(f);
        }
		if(rtype == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            RenderConfigFlans var22 = ((ItemGun)item.getItem()).getRenderConfig();
            if(this.floatAsBool(var22.anim_bool)) {
               if(this.floatAsBool(var22.anim_by_left_hand_bool)) {
                  this.renderLeftHand(var22, type.modelScale, animations.reloading, animations.reloadAnimationProgress, animations);
               } else {
                  this.renderRightHand(var22, type.modelScale, animations.reloading, animations.reloadAnimationProgress, animations);
               }
            }
		}

        GL11.glPopMatrix();
        GL11.glPopMatrix();
        if (scopeAttachment != null) {
            ModelAttachment scopeModel;
            GL11.glPushMatrix();
            this.initRenderAttachment(scopeAttachment, scopeItemStack, model.scopeAttachPoint, type);
            if (model.scopeIsOnBreakAction) {
                GL11.glTranslatef((float)model.barrelBreakPoint.x, (float)model.barrelBreakPoint.y, (float)model.barrelBreakPoint.z);
                GL11.glRotatef((float)(reloadRotate * (- model.breakAngle)), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)(- model.barrelBreakPoint.x), (float)(- model.barrelBreakPoint.y), (float)(- model.barrelBreakPoint.z));
            }
            if (model.scopeIsOnSlide) {
                GL11.glTranslatef((float)((- animations.lastGunSlide + (animations.gunSlide - animations.lastGunSlide) * smoothing) * model.gunSlideDistance), (float)0.0f, (float)0.0f);
            }
            if ((scopeModel = scopeAttachment.model) != null) {
                scopeModel.renderAttachment(f);
            }
            renderEngine.bindTexture(FlansModResourceHandler.getTexture(type));
            GL11.glPopMatrix();
        }
        if (gripAttachment != null) {
            ModelAttachment gripModel;
            GL11.glPushMatrix();
            this.initRenderAttachment(gripAttachment, gripItemStack, model.gripAttachPoint, type);
            if (model.gripIsOnPump) {
                GL11.glTranslatef((float)((- 1.0f - Math.abs(animations.lastPumped + (animations.pumped - animations.lastPumped) * smoothing)) * model.pumpHandleDistance), (float)0.0f, (float)0.0f);
            }
            if ((gripModel = gripAttachment.model) != null) {
                gripModel.renderAttachment(f);
            }
            renderEngine.bindTexture(FlansModResourceHandler.getTexture(type));
            GL11.glPopMatrix();
        }
        if (barrelAttachment != null) {
            GL11.glPushMatrix();
            this.initRenderAttachment(barrelAttachment, barrelItemStack, model.barrelAttachPoint, type);
            ModelAttachment barrelModel = barrelAttachment.model;
            if (barrelModel != null) {
                barrelModel.renderAttachment(f);
            }
            renderEngine.bindTexture(FlansModResourceHandler.getTexture(type));
            GL11.glPopMatrix();
        }
        if (stockAttachment != null) {
            GL11.glPushMatrix();
            this.initRenderAttachment(stockAttachment, stockItemStack, model.stockAttachPoint, type);
            ModelAttachment stockModel = stockAttachment.model;
            if (stockModel != null) {
                stockModel.renderAttachment(f);
            }
            renderEngine.bindTexture(FlansModResourceHandler.getTexture(type));
            GL11.glPopMatrix();
        }
        if (slideAttachment != null) {
            GL11.glPushMatrix();
            this.initRenderAttachment(slideAttachment, slideItemStack, model.slideAttachPoint, type);
            GL11.glTranslatef((float)((- animations.lastGunSlide + (animations.gunSlide - animations.lastGunSlide) * smoothing) * model.gunSlideDistance), (float)0.0f, (float)0.0f);
            ModelAttachment slideModel = slideAttachment.model;
            if (slideModel != null) {
                slideModel.renderAttachment(f);
            }
            renderEngine.bindTexture(FlansModResourceHandler.getTexture(type));
            GL11.glPopMatrix();
        }
        if (gadgetAttachment != null) {
            ModelAttachment gadgetModel;
            GL11.glPushMatrix();
            this.initRenderAttachment(gadgetAttachment, gadgetItemStack, model.gadgetAttachPoint, type);
            if (model.gadgetIsOnPump) {
                GL11.glTranslatef((float)((- 1.0f - Math.abs(animations.lastPumped + (animations.pumped - animations.lastPumped) * smoothing)) * model.pumpHandleDistance), (float)0.0f, (float)0.0f);
            }
            if ((gadgetModel = gadgetAttachment.model) != null) {
                gadgetModel.renderAttachment(f);
            }
            renderEngine.bindTexture(FlansModResourceHandler.getTexture(type));
            GL11.glPopMatrix();
        }
        if (accessoryAttachment != null) {
            GL11.glPushMatrix();
            this.initRenderAttachment(accessoryAttachment, accessoryItemStack, model.accessoryAttachPoint, type);
            ModelAttachment AccessoryModel = accessoryAttachment.model;
            if (AccessoryModel != null) {
                AccessoryModel.renderAttachment(f);
            }
            renderEngine.bindTexture(FlansModResourceHandler.getTexture(type));
            GL11.glPopMatrix();
        }
        if (pumpAttachment != null) {
            GL11.glPushMatrix();
            this.initRenderAttachment(pumpAttachment, pumpItemStack, model.pumpAttachPoint, type);
            GL11.glTranslatef((float)((- 1.0f - Math.abs(animations.lastPumped + (animations.pumped - animations.lastPumped) * smoothing)) * model.pumpHandleDistance), (float)0.0f, (float)0.0f);
            ModelAttachment pumpModel = pumpAttachment.model;
            if (pumpModel != null) {
                pumpModel.renderAttachment(f);
            }
            renderEngine.bindTexture(FlansModResourceHandler.getTexture(type));
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    private void initRenderAttachment(AttachmentType attachment, ItemStack stack, Vector3f model, GunType type) {
        Paintjob paintjob = attachment.getPaintjob(stack.getItemDamage());
        renderEngine.bindTexture(FlansModResourceHandler.getPaintjobTexture(paintjob));
        GL11.glTranslatef((float)(model.x * type.modelScale), (float)(model.y * type.modelScale), (float)(model.z * type.modelScale));
        GL11.glScalef((float)attachment.modelScale, (float)attachment.modelScale, (float)attachment.modelScale);
    }
    static {
        muzzlePosX = -0.3;
        muzzlePosY = 0.1;
        muzzlePosZ = -1.4;
    }


}

