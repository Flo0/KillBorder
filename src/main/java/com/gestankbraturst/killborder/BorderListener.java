package com.gestankbraturst.killborder;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

public class BorderListener implements Listener {

  private final BorderManager borderManager;

  public BorderListener(BorderManager borderManager) {
    this.borderManager = borderManager;
  }

  @EventHandler
  public void onLoad(WorldLoadEvent event) {
    borderManager.loadBorder(event.getWorld());
  }

  @EventHandler
  public void onUnload(WorldUnloadEvent event) {
    borderManager.saveBorder(event.getWorld());
    borderManager.removeBorder(event.getWorld());
  }

}
