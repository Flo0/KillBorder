package com.gestankbraturst.killborder;

import org.bukkit.util.Vector;

public interface Border {

  BorderType getBorderType();

  Vector getCenter();

  boolean isOutside(Vector location);

}
