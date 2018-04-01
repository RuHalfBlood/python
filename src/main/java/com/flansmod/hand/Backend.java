package com.flansmod.hand;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Backend {

   boolean client = true;
   boolean server = true;

}
