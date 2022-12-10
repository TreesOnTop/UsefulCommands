package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
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
        for (int x = 0; x < 100; x++) {
            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(""));
        }
        Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(prefix + "Chat cleared by " + sender.getName()));
        return true;
    }
}
