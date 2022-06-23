package com.gestankbraturst.killborder;

import co.aikar.commands.BukkitCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public final class KillBorder extends JavaPlugin {

  private BorderManager borderManager;

  @Override
  public void onEnable() {
    borderManager = new BorderManager(this);
    borderManager.initialize();

    Bukkit.getPluginManager().registerEvents(new BorderListener(borderManager), this);

    BukkitCommandManager commandManager = new BukkitCommandManager(this);

    List<String> borderTypes = Arrays.stream(BorderType.values()).map(Enum::name).toList();
    commandManager.getCommandCompletions().registerStaticCompletion("BorderType", borderTypes);
    commandManager.registerCommand(new BorderCommand(borderManager));

    Bukkit.getScheduler().runTaskTimer(this, new BorderRunnable(borderManager), 20, 20);
  }

  @Override
  public void onDisable() {
    borderManager.terminate();
  }

}
