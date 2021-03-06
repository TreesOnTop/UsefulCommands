package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UsefulCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class ClearChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        String permissionMessage = ChatColor.translateAlternateColorCodes('&', config.getString("PermissionMessage"));
        if (sender.hasPermission("usefulcommands.clearchat")) {
            for (int x = 0; x < 100; x++) {
                Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(""));
            }
            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(prefix + "Chat cleared by " + sender.getName()));
        } else {
            sender.sendMessage(permissionMessage);
        }
        return true;
    }
}
