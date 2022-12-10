package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UsefulCommands;
import com.github.treesontop.usefulcommands.UserHandler;
import com.github.treesontop.usefulcommands.classes.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

public class Mute implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (args.length > 0) {
            Player p;
            try {
                p = Bukkit.getPlayer(args[0]);
            } catch (NullPointerException e) {
                sender.sendMessage(prefix + "Player not found.");
                return true;
            }
            User u = UserHandler.getUser(p);
            if (p == null) {
                sender.sendMessage(prefix + "Player not found.");
                return true;
            }
            if (args.length > 1) {
                try {
                    long time = Duration.parse("PT" + args[1]).toMillis();
                    u.setMuted(new Date().getTime() + time);
                } catch (Exception e) {
                    sender.sendMessage(prefix + "Invalid time.");
                    return true;
                }
            } else {
                u.setMuted(32503698000L);
            }
            sender.sendMessage(prefix + p.getName() + " has been muted.");
        } else {
            sender.sendMessage(prefix + "You need a player to mute");
            return true;
        }
        return true;
    }
}
