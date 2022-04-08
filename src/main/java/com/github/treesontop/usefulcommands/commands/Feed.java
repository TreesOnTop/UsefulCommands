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
        if (args.length > 0) {
            try {
                if (sender.hasPermission("usefulcommands.feed.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setFoodLevel(20);
                    target.sendMessage(prefix + "You have been fed");
                    sender.sendMessage(prefix + target.getName() + " has been fed");
                } else {
                    sender.sendMessage(permissionMessage);
                }
            } catch (Exception e) {
                sender.sendMessage(prefix + args[0] + " is not a valid player");
            }
        } else {
            if (sender.hasPermission("usefulcommands.feed")) {
                if (sender instanceof Player) {
                    Player target = (Player) sender;
                    target.setFoodLevel(20);
                    target.sendMessage(prefix + "You have been fed");
                } else {
                    sender.sendMessage(prefix + "You must be a player to use this command");
                }
            } else {
                sender.sendMessage(permissionMessage);
            }
        }
        return true;
    }
}
