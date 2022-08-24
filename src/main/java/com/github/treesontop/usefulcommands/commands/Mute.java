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
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

public class Mute implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (args.length > 0) {
            Player target;
            try {
                target = Bukkit.getPlayer(args[0]);
            } catch (NullPointerException e) {
                sender.sendMessage(prefix + "Player not found.");
                return true;
            }
            File playerDataFile = UsefulCommands.Players.get(target.getUniqueId().toString());
            YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);
            if (target == null) {
                sender.sendMessage(prefix + "Player not found.");
                return true;
            }
            if (args.length > 1) {
                try {
                    long time = Duration.parse("PT" + args[1]).toMillis();
                    playerData.set("muted", new Date().getTime() + time);
                } catch (Exception e) {
                    sender.sendMessage(prefix + "Invalid time.");
                    return true;
                }
            } else {
                playerData.set("muted", 4102444800000L);
            }
            try {
                playerData.save(playerDataFile);
                UsefulCommands.Players.replace(target.getUniqueId().toString(), playerDataFile);
                sender.sendMessage(prefix + target.getName() + " has been muted.");
            } catch (IOException ignored) {
                sender.sendMessage(prefix + "Error saving player data.");
            }
        } else {
            sender.sendMessage(prefix + "You need a player to mute");
            return true;
        }
        return true;
    }
}
