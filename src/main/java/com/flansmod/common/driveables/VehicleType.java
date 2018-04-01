package com.flansmod.common.driveables;

import com.flansmod.client.model.ModelDriveable;
import com.flansmod.client.model.ModelVehicle;
import com.flansmod.common.FlansMod;
import com.flansmod.common.driveables.DriveablePosition;
import com.flansmod.common.driveables.DriveableType;
import com.flansmod.common.types.TypeFile;
import com.flansmod.common.vector.Vector3f;
import java.util.ArrayList;
import java.util.Iterator;

public class VehicleType extends DriveableType {

   public float turnLeftModifier = 1.0F;
   public float turnRightModifier = 1.0F;
   public boolean squashMobs = false;
   public boolean fourWheelDrive = false;
   public boolean rotateWheels = false;
   public boolean tank = false;
   public int vehicleShootDelay;
   public int vehicleShellDelay;
   public boolean hasDoor = false;
   public Vector3f doorPos1 = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f doorPos2 = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f doorRot1 = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f doorRot2 = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f doorRate = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f doorRotRate = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f door2Pos1 = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f door2Pos2 = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f door2Rot1 = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f door2Rot2 = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f door2Rate = new Vector3f(0.0F, 0.0F, 0.0F);
   public Vector3f door2RotRate = new Vector3f(0.0F, 0.0F, 0.0F);
   public boolean shootWithOpenDoor = false;
   public int trackLinkFix = 5;
   public boolean flipLinkFix = false;
   public String driftSound = "";
   public int driftSoundLength;
   public ArrayList smokers = new ArrayList();
   public static ArrayList types = new ArrayList();


   public VehicleType(TypeFile file) {
      super(file);
      types.add(this);
   }

   public void preRead(TypeFile file) {
      super.preRead(file);
      super.wheelPositions = new DriveablePosition[4];
   }

   public void postRead(TypeFile file) {
      super.postRead(file);
   }

   protected void read(String[] split, TypeFile file) {
      super.read(split, file);

      try {
         if(split[0].equals("TurnLeftSpeed")) {
            this.turnLeftModifier = Float.parseFloat(split[1]);
         }

         if(split[0].equals("TurnRightSpeed")) {
            this.turnRightModifier = Float.parseFloat(split[1]);
         }

         if(split[0].equals("SquashMobs")) {
            this.squashMobs = Boolean.parseBoolean(split[1].toLowerCase());
         }

         if(split[0].equals("FourWheelDrive")) {
            this.fourWheelDrive = Boolean.parseBoolean(split[1].toLowerCase());
         }

         if(split[0].equals("Tank") || split[0].equals("TankMode")) {
            this.tank = Boolean.parseBoolean(split[1].toLowerCase());
         }

         if(split[0].equals("HasDoor")) {
            this.hasDoor = Boolean.parseBoolean(split[1].toLowerCase());
         }

         if(split[0].equals("ShootWithOpenDoor")) {
            this.shootWithOpenDoor = Boolean.parseBoolean(split[1].toLowerCase());
         }

         if(split[0].equals("RotateWheels")) {
            this.rotateWheels = Boolean.parseBoolean(split[1].toLowerCase());
         }

         if(split[0].equals("FixTrackLink")) {
            this.trackLinkFix = Integer.parseInt(split[1].toLowerCase());
         }

         if(split[0].equals("FlipLinkFix")) {
            this.flipLinkFix = Boolean.parseBoolean(split[1].toLowerCase());
         }

         if(split[0].equals("DoorPosition1")) {
            this.doorPos1 = new Vector3f(split[1], super.shortName);
         }

         if(split[0].equals("DoorPosition2")) {
            this.doorPos2 = new Vector3f(split[1], super.shortName);
         }

         if(split[0].equals("DoorRotation1")) {
            this.doorRot1 = new Vector3f(split[1], super.shortName);
         }

         if(split[0].equals("DoorRotation2")) {
            this.doorRot2 = new Vector3f(split[1], super.shortName);
         }

         if(split[0].equals("DoorRate")) {
            this.doorRate = new Vector3f(split[1], super.shortName);
         }

         if(split[0].equals("DoorRotRate")) {
            this.doorRotRate = new Vector3f(split[1], super.shortName);
         }

         if(split[0].equals("Door2Position1")) {
            this.door2Pos1 = new Vector3f(split[1], super.shortName);
         }

         if(split[0].equals("Door2Position2")) {
            this.door2Pos2 = new Vector3f(split[1], super.shortName);
         }

         if(split[0].equals("Door2Rotation1")) {
            this.door2Rot1 = new Vector3f(split[1], super.shortName);
         }

         if(split[0].equals("Door2Rotation2")) {
            this.door2Rot2 = new Vector3f(split[1], super.shortName);
         }

         if(split[0].equals("Door2Rate")) {
            this.door2Rate = new Vector3f(split[1], super.shortName);
         }

         if(split[0].equals("Door2RotRate")) {
            this.door2RotRate = new Vector3f(split[1], super.shortName);
         }

         if(split[0].equals("ShootDelay")) {
            this.vehicleShootDelay = Integer.parseInt(split[1]);
         }

         if(split[0].equals("ShellDelay")) {
            this.vehicleShellDelay = Integer.parseInt(split[1]);
         }

         if(split[0].equals("ShootSound")) {
            super.shootSoundPrimary = split[1];
            FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
         }

         if(split[0].equals("ShellSound")) {
            super.shootSoundSecondary = split[1];
            FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
         } else if(split[0].equals("DriftSoundLength")) {
            this.driftSoundLength = Integer.parseInt(split[1]);
         } else if(split[0].equals("DriftSound")) {
            this.driftSound = split[1];
            FlansMod.proxy.loadSound(super.contentPack, "driveables", split[1]);
         }

         if(split[0].equalsIgnoreCase("AddSmokePoint") || split[0].equalsIgnoreCase("AddSmokeDispenser")) {
            VehicleType.SmokePoint smoke = new VehicleType.SmokePoint();
            smoke.position = new Vector3f(split[1], super.shortName);
            smoke.direction = new Vector3f(split[2], super.shortName);
            smoke.detTime = Integer.parseInt(split[3]);
            smoke.part = split[4];
            this.smokers.add(smoke);
         }
      } catch (Exception var4) {
         ;
      }

   }

   public static VehicleType getVehicle(String find) {
      Iterator var1 = types.iterator();

      VehicleType type;
      do {
         if(!var1.hasNext()) {
            return null;
         }

         type = (VehicleType)var1.next();
      } while(!type.shortName.equals(find));

      return type;
   }

   public void reloadModel() {
      super.model = (ModelDriveable)FlansMod.proxy.loadModel(super.modelString, super.shortName, ModelVehicle.class);
   }


   public class SmokePoint {

      public Vector3f position;
      public Vector3f direction;
      public int detTime;
      public String part;


   }
}
