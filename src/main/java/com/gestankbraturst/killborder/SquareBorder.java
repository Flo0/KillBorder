package com.gestankbraturst.killborder;

import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

public class SquareBorder implements Border {

  private final Vector center;
  private final BoundingBox box;

  protected SquareBorder() {
    this.center = new Vector();
    this.box = BoundingBox.of(center, 0, 0, 0);
  }

  public SquareBorder(Vector center, double width) {
    this.center = center;
    this.box = BoundingBox.of(center, width, 1024, width);
  }

  @Override
  public BorderType getBorderType() {
    return BorderType.SQUARE;
  }

  @Override
  public Vector getCenter() {
    return center;
  }

  @Override
  public boolean isOutside(Vector location) {
    return !box.contains(location);
  }
}
