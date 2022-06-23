package com.gestankbraturst.killborder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class BorderManager {

  private final Map<UUID, Border> worldBorderMap = new HashMap<>();
  private final NamespacedKey borderKey;
  private final Gson gson = new GsonBuilder()
          .disableHtmlEscaping()
          .registerTypeHierarchyAdapter(Border.class, new TrivialInterfaceAdapter())
          .create();

  public BorderManager(JavaPlugin plugin) {
    this.borderKey = new NamespacedKey(plugin, "border");
  }

  public Optional<Border> getBorder(World world) {
    return Optional.ofNullable(worldBorderMap.get(world.getUID()));
  }

  public void setBorder(World world, Border border) {
    worldBorderMap.put(world.getUID(), border);
  }

  public boolean removeBorder(World world) {
    return worldBorderMap.remove(world.getUID()) != null;
  }

  public void initialize() {
    for (World world : Bukkit.getWorlds()) {
      loadBorder(world);
    }
  }

  public void terminate() {
    for (World world : Bukkit.getWorlds()) {
      saveBorder(world);
    }
  }

  public void loadBorder(World world) {
    PersistentDataContainer container = world.getPersistentDataContainer();
    String borderData = container.get(borderKey, PersistentDataType.STRING);
    if (borderData == null) {
      return;
    }
    Border border = gson.fromJson(borderData, Border.class);
    setBorder(world, border);
  }

  public void saveBorder(World world) {
    PersistentDataContainer container = world.getPersistentDataContainer();
    getBorder(world).ifPresent(border -> container.set(borderKey, PersistentDataType.STRING, gson.toJson(border)));
  }

}
