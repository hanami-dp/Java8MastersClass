package com.exercise.GroupExercise1;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Options {
    Option[] value() default {};
}
