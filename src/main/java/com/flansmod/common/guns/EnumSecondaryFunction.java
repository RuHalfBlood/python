package com.flansmod.common.guns;


public enum EnumSecondaryFunction {

   ZOOM("ZOOM", 0),
   ADS_ZOOM("ADS_ZOOM", 1),
   MELEE("MELEE", 2),
   CUSTOM_MELEE("CUSTOM_MELEE", 3);
   // $FF: synthetic field
   private static final EnumSecondaryFunction[] $VALUES = new EnumSecondaryFunction[]{ZOOM, ADS_ZOOM, MELEE, CUSTOM_MELEE};


   private EnumSecondaryFunction(String var1, int var2) {}

   public static EnumSecondaryFunction get(String s) {
      s = s.toLowerCase();
      return s.equals("zoom")?ZOOM:(s.equals("melee")?MELEE:(s.equals("custommelee")?CUSTOM_MELEE:ADS_ZOOM));
   }

}
