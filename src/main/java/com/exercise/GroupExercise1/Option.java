package com.exercise.GroupExercise1;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Repeatable(value= Options.class)
public @interface Option {
    int optionId();
    String label();
}
