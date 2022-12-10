package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UsefulCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Vanish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (args.length > 0) {
            if (!sender.hasPermission("usefulcommands.vanish.others")) {
                sender.sendMessage(command.getPermissionMessage());
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                vanish(target);
                sender.sendMessage(prefix + target.getName() + "'s vanish has been toggled");
            } else {
                sender.sendMessage(prefix + "That player is not online.");
            }
        } else if (sender instanceof Player) {
            vanish((Player) sender);
        } else {
            sender.sendMessage(prefix + "You must be a player to use this command");
        }
        return true;
    }

    private void vanish(Player player) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        File playerDataFile = UsefulCommands.Players.get(player.getUniqueId().toString());
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        playerData.set("vanished", !playerData.getBoolean("vanished"));
        if (playerData.getBoolean("vanished")) {
            player.sendMessage(prefix + "You are now vanished.");
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.hidePlayer(UsefulCommands.getMainClass(), player);
                if (config.getBoolean("LeaveOnVanish")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e") + player.getName() + " left the game.");
                }
            }
        } else {
            player.sendMessage(prefix + "You are no longer vanished.");
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.showPlayer(UsefulCommands.getMainClass(), player);
                if (config.getBoolean("LeaveOnVanish")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e") + player.getName() + " joined the game.");
                }
            }
        }
    }
}
