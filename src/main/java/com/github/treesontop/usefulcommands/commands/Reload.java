package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("prefix"));
        String permissionMessage = ChatColor.translateAlternateColorCodes('&', config.getString("PermissionMessage"));
        if (sender instanceof Player) {
            if (sender.hasPermission("usefulcommands.reload")) {
                ConfigHandler.reloadConfig();
                sender.sendMessage(prefix + "Config reloaded");
            } else {
                sender.sendMessage(permissionMessage);
            }
        } else {
            ConfigHandler.reloadConfig();
            sender.sendMessage(prefix + "Config reloaded");
        }
        return true;
    }
}
