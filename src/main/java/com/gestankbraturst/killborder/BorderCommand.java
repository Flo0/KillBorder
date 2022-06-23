package com.gestankbraturst.killborder;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Values;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

@CommandAlias("kborder")
@CommandPermission("kborder.admin")
public class BorderCommand extends BaseCommand {

  private final BorderManager borderManager;

  public BorderCommand(BorderManager borderManager) {
    this.borderManager = borderManager;
  }

  @Subcommand("create")
  @CommandCompletion("@BorderType @Nothing")
  public void onCreateBorder(Player player, @Values("@BorderType") BorderType borderType, double size) {
    Vector center = player.getLocation().toVector();
    Border border = borderType.creator().apply(center, size);
    borderManager.setBorder(player.getWorld(), border);
    player.sendMessage("§aBorder for this world was set.");
  }

  @Subcommand("remove")
  public void onRemoveBorder(Player player) {
    if (borderManager.removeBorder(player.getWorld())) {
      player.sendMessage("§aBorder for this world was removed.");
    } else {
      player.sendMessage("§cNo border was present in the first place.");
    }
  }

}
