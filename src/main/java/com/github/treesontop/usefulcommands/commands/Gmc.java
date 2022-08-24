package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Gmc implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setGameMode(GameMode.CREATIVE);
                sender.sendMessage(prefix + "Your gamemode was set to §ecreative§r.");
            } else {
                sender.sendMessage(prefix + "You must be a player to use this command");
            }
        } else {
            Player p = Bukkit.getPlayer(args[0]);
            if (p != null) {
                sender.sendMessage(prefix + p.getName() + "'s gamemode was set to §ecreative§r.");
            } else {
                sender.sendMessage(prefix + args[0] + "was not found.");
            }
        }
        return true;
    }
}
