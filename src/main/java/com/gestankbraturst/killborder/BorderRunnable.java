package com.gestankbraturst.killborder;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class BorderRunnable implements Runnable {

  private final BorderManager borderManager;

  public BorderRunnable(BorderManager borderManager) {
    this.borderManager = borderManager;
  }

  @Override
  public void run() {
    Bukkit.getWorlds().forEach(this::checkWorld);
  }

  private void checkWorld(World world) {
    borderManager.getBorder(world).ifPresent(border -> world.getPlayers().stream().filter(player -> border.isOutside(player.getLocation().toVector())).forEach(this::punish));
  }

  private void punish(Player player) {
    player.setHealth(0);
    player.damage(0);
  }

}
