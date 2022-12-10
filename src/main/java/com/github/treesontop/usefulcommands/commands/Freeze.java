package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UserHandler;
import com.github.treesontop.usefulcommands.classes.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Freeze implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (args.length == 0) {
            sender.sendMessage(prefix + "You must specify a player to freeze.");
            return true;
        }
        Player p = Bukkit.getPlayer(args[0]);
        if (p == null) {
            sender.sendMessage(prefix + "That player is not online.");
            return true;
        }
        if (p.hasPermission("usefulcommands.freeze.bypass")) {
            sender.sendMessage(prefix + "You cannot freeze that player.");
            return true;
        }
        User u = UserHandler.getUser(p);
        u.setFrozen(!u.isFrozen());
        p.sendMessage(prefix + "You have been " + (u.isFrozen() ? "frozen" : "unfrozen") + ".");
        return true;
    }
}
