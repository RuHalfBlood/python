package com.flansmod.common.guns;


public enum EnumFireMode {

   SEMIAUTO("SEMIAUTO", 0),
   FULLAUTO("FULLAUTO", 1),
   MINIGUN("MINIGUN", 2),
   BURST("BURST", 3);
   // $FF: synthetic field
   private static final EnumFireMode[] $VALUES = new EnumFireMode[]{SEMIAUTO, FULLAUTO, MINIGUN, BURST};


   private EnumFireMode(String var1, int var2) {}

   public static EnumFireMode getFireMode(String s) {
      s = s.toLowerCase();
      return s.equals("fullauto")?FULLAUTO:(s.equals("minigun")?MINIGUN:(s.equals("burst")?BURST:SEMIAUTO));
   }

}
