package com.flansmod.common.driveables;


public enum EnumPlaneMode {

   PLANE("PLANE", 0),
   VTOL("VTOL", 1),
   HELI("HELI", 2),
   SIXDOF("SIXDOF", 3);
   // $FF: synthetic field
   private static final EnumPlaneMode[] $VALUES = new EnumPlaneMode[]{PLANE, VTOL, HELI, SIXDOF};


   private EnumPlaneMode(String var1, int var2) {}

   public static EnumPlaneMode getMode(String s) {
      return s.toLowerCase().equals("vtol")?VTOL:(s.toLowerCase().equals("heli")?HELI:PLANE);
   }

}
