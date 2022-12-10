package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Date;

public class Tempban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (args.length > 0) {
            Player p = Bukkit.getPlayer(args[0]);
            String reason;
            if (args.length > 2) {
                reason = args[2];
            } else {
                reason = "Not Provided";
            }
            long time = Duration.parse("PT" + args[1]).toMillis();
            Bukkit.getBanList(Type.NAME).addBan(p.getName(), reason, new Date(System.currentTimeMillis() + time), sender.getName());
            sender.sendMessage(prefix + args[0] + "was banned for" + reason + "for" + Duration.parse("PT" + args[1] + "."));
        }
        return true;
    }
}