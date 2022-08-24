package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UsefulCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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
        if (args.length > 0) {
            if (!sender.hasPermission("usefulcommands.gamemode.others")) {
                sender.sendMessage(UsefulCommands.getPermissionMessage());
                return true;
            }
            Player p = Bukkit.getPlayer(args[0]);
            if (p != null) {
                p.setGameMode(GameMode.CREATIVE);
                sender.sendMessage(prefix + p.getName() + "'s gamemode was set to creative.");
                p.sendMessage(prefix + "Your gamemode was set to creative.");
            } else {
                sender.sendMessage(prefix + "That player is not online.");
            }
        } else if (sender instanceof Player player) {
            player.setGameMode(GameMode.CREATIVE);
            sender.sendMessage(prefix + "Your gamemode was set to creative.");
        } else {
            sender.sendMessage(prefix + "You must be a player to use this command.");
        }
        return true;
    }
}
