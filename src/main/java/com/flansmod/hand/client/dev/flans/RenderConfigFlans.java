package com.flansmod.hand.client.dev.flans;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.flansmod.hand.client.dev.BasicRenderConfig;
import com.flansmod.hand.helpers.loader.NIConfiguration;

@SideOnly(Side.CLIENT)
public class RenderConfigFlans extends BasicRenderConfig {

   public float h_r_render_bool;
   public float h_r_rot_x;
   public float h_r_rot_y;
   public float h_r_rot_z;
   public float h_r_off_x;
   public float h_r_off_y;
   public float h_r_off_z;
   public float h_r_l;
   public float h_r_size;
   public float aim_h_r_rot_x;
   public float aim_h_r_rot_y;
   public float aim_h_r_rot_z;
   public float aim_h_r_off_x;
   public float aim_h_r_off_y;
   public float aim_h_r_off_z;
   public float h_l_render_bool;
   public float h_l_rot_x;
   public float h_l_rot_y;
   public float h_l_rot_z;
   public float h_l_off_x;
   public float h_l_off_y;
   public float h_l_off_z;
   public float h_l_l;
   public float h_l_size;
   public float aim_h_l_rot_x;
   public float aim_h_l_rot_y;
   public float aim_h_l_rot_z;
   public float aim_h_l_off_x;
   public float aim_h_l_off_y;
   public float aim_h_l_off_z;
   public float anim_bool;
   public float anim_by_left_hand_bool;
   public float anim_h_rot_x;
   public float anim_h_rot_y;
   public float anim_h_rot_z;
   public float anim_h_off_x;
   public float anim_h_off_y;
   public float anim_h_off_z;
   public float p_fp_off_x;
   public float p_fp_off_y;
   public float p_fp_off_z;
   public float p_tp_off_x;
   public float p_tp_off_y;
   public float p_tp_off_z;


   public RenderConfigFlans(String filename) {
      super("fha/" + filename);
   }

   public void writeConfig(NIConfiguration config) {
      saveByReflection(this, config);
   }

   public void setupConfig(NIConfiguration configuration) {
      readByReflection(this, configuration);
   }
}
