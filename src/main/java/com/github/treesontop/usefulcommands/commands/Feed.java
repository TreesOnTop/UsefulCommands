package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        String permissionMessage = ChatColor.translateAlternateColorCodes('&', config.getString("PermissionMessage"));
        if (args.length > 0 && sender.hasPermission("usefulcommands.feed.others")) {
            try {
                feed(Bukkit.getPlayer(args[0]));
                sender.sendMessage(prefix + Bukkit.getPlayer(args[0]).getName() + " has been fed");
            } catch (Exception e) {
                sender.sendMessage(prefix + args[0] + " is not a valid player");
            }
        } else if (sender instanceof Player && args.length == 0 && sender.hasPermission("usefulcommands.feed")) {
            feed((Player) sender);
        } else if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "You must be a player to use this command");
        } else {
            sender.sendMessage(permissionMessage);
        }
        return true;
    }
    private void feed(Player target) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        target.setFoodLevel(20);
        target.sendMessage(prefix + "You have been fed");
    }
}