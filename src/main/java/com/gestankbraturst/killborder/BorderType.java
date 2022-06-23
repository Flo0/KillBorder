package com.gestankbraturst.killborder;

import org.bukkit.util.Vector;

import java.util.function.BiFunction;

public enum BorderType {

  CIRCLE(CircleBorder::new),
  SQUARE(SquareBorder::new);

  private final BiFunction<Vector, Double, Border> creator;

  BorderType(BiFunction<Vector, Double, Border> creator) {
    this.creator = creator;
  }

  public BiFunction<Vector, Double, Border> creator() {
    return creator;
  }

}
