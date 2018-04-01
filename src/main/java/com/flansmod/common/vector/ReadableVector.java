package com.flansmod.common.vector;

import com.flansmod.common.vector.Vector;
import java.nio.FloatBuffer;

public interface ReadableVector {

   float length();

   float lengthSquared();

   Vector store(FloatBuffer var1);
}
