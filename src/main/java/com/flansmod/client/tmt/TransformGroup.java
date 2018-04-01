package com.flansmod.client.tmt;

import com.flansmod.client.tmt.PositionTransformVertex;
import net.minecraft.util.Vec3;

public abstract class TransformGroup {

   public abstract double getWeight();

   public abstract Vec3 doTransformation(PositionTransformVertex var1);
}
