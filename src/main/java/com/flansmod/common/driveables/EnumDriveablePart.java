package com.flansmod.common.driveables;

import java.util.ArrayList;

public enum EnumDriveablePart {

   tailWheel("tailWheel", 0, new EnumDriveablePart[0], "tailWheel", "Wheel (Tail)"),
   tail("tail", 1, new EnumDriveablePart[]{tailWheel}, "tail", "Tail"),
   bay("bay", 2, new EnumDriveablePart[]{tail}, "bay", "Bay"),
   topWing("topWing", 3, new EnumDriveablePart[0], "topWing", "Top Wing"),
   leftWingWheel("leftWingWheel", 4, new EnumDriveablePart[0], "leftWingWheel", "Wheel (Left Wing)"),
   leftWing("leftWing", 5, new EnumDriveablePart[]{topWing, leftWingWheel}, "leftWing", "Left Wing"),
   rightWingWheel("rightWingWheel", 6, new EnumDriveablePart[0], "rightWingWheel", "Wheel (Right Wing)"),
   rightWing("rightWing", 7, new EnumDriveablePart[]{topWing, rightWingWheel}, "rightWing", "Right Wing"),
   nose("nose", 8, new EnumDriveablePart[0], "nose", "Nose"),
   coreWheel("coreWheel", 9, new EnumDriveablePart[0], "coreWheel", "Wheel (Core)"),
   skids("skids", 10, new EnumDriveablePart[0], "skids", "Skids"),
   blades("blades", 11, new EnumDriveablePart[0], "blades", "Blades"),
   turret("turret", 12, new EnumDriveablePart[0], "turret", "Turret"),
   backWheel("backWheel", 13, new EnumDriveablePart[0], "backWheel", "Back Wheel"),
   frontWheel("frontWheel", 14, new EnumDriveablePart[0], "frontWheel", "Front Wheel"),
   backLeftWheel("backLeftWheel", 15, new EnumDriveablePart[0], "backLeftWheel", "Back Left Wheel"),
   frontLeftWheel("frontLeftWheel", 16, new EnumDriveablePart[0], "frontLeftWheel", "Front Left Wheel"),
   backRightWheel("backRightWheel", 17, new EnumDriveablePart[0], "backRightWheel", "Back Right Wheel"),
   frontRightWheel("frontRightWheel", 18, new EnumDriveablePart[0], "frontRightWheel", "Front Right Wheel"),
   leftTrack("leftTrack", 19, new EnumDriveablePart[0], "leftTrack", "Left Track"),
   rightTrack("rightTrack", 20, new EnumDriveablePart[0], "rightTrack", "Right Track"),
   trailer("trailer", 21, new EnumDriveablePart[0], "trailer", "AdditionArmor"),
   harvester("harvester", 22, new EnumDriveablePart[0], "harvester", "Harvester"),
   frontalArmor("frontalArmor", 23, new EnumDriveablePart[0], "frontalArmor", "Frontal Armor"),
   leftsideArmor("leftsideArmor", 24, new EnumDriveablePart[0], "leftsideArmor", "Left Side Armor"),
   rightsideArmor("rightsideArmor", 25, new EnumDriveablePart[0], "rightsideArmor", "Right Side Armor"),
   additionalArmor("additionalArmor", 26, new EnumDriveablePart[0], "additionalArmor", "Additional Armor"),
   ERA("ERA", 27, new EnumDriveablePart[0], "ERA", "ERA"),
   APS("APS", 28, new EnumDriveablePart[0], "APS", "APS"),
   ADS("ADS", 29, new EnumDriveablePart[0], "ADS", "ADS"),
   leftArm("leftArm", 30, new EnumDriveablePart[0], "leftArm", "Left Arm"),
   rightArm("rightArm", 31, new EnumDriveablePart[0], "rightArm", "Right Arm"),
   head("head", 32, new EnumDriveablePart[0], "head", "Head"),
   hips("hips", 33, new EnumDriveablePart[0], "hips", "Hips"),
   barrel("barrel", 34, new EnumDriveablePart[0], "barrel", "Barrel"),
   core("core", 35, new EnumDriveablePart[]{bay, leftWing, rightWing, nose, turret, coreWheel, leftArm, rightArm, head, hips, blades, skids, backWheel, frontWheel, backLeftWheel, frontLeftWheel, backRightWheel, frontRightWheel, leftTrack, rightTrack, trailer, harvester}, "core", "Core");
   private String shortName;
   private String name;
   private EnumDriveablePart[] children;
   // $FF: synthetic field
   private static final EnumDriveablePart[] $VALUES = new EnumDriveablePart[]{tailWheel, tail, bay, topWing, leftWingWheel, leftWing, rightWingWheel, rightWing, nose, coreWheel, skids, blades, turret, backWheel, frontWheel, backLeftWheel, frontLeftWheel, backRightWheel, frontRightWheel, leftTrack, rightTrack, trailer, harvester, frontalArmor, leftsideArmor, rightsideArmor, additionalArmor, ERA, APS, ADS, leftArm, rightArm, head, hips, barrel, core};


   private EnumDriveablePart(String var1, int var2, EnumDriveablePart[] parts, String s, String s2) {
      this.children = parts;
      this.shortName = s;
      this.name = s2;
   }

   public EnumDriveablePart[] getChildren() {
      return this.children;
   }

   public EnumDriveablePart[] getParents() {
      ArrayList parents = new ArrayList();
      EnumDriveablePart[] var2 = values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         EnumDriveablePart part = var2[var4];
         EnumDriveablePart[] var6 = part.getChildren();
         int var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            EnumDriveablePart childPart = var6[var8];
            if(childPart == this) {
               parents.add(part);
            }
         }
      }

      return (EnumDriveablePart[])parents.toArray(new EnumDriveablePart[parents.size()]);
   }

   public String getShortName() {
      return this.shortName;
   }

   public String getName() {
      return this.name;
   }

   public static EnumDriveablePart getPart(String s) {
      EnumDriveablePart[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         EnumDriveablePart part = var1[var3];
         if(part.getShortName().equals(s)) {
            return part;
         }
      }

      return null;
   }

   public static boolean isWheel(EnumDriveablePart part) {
      return part == coreWheel || part == tailWheel || part == leftWingWheel || part == rightWingWheel;
   }

}
