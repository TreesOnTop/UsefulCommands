package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class Broadcast implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        List<String> broadcast = config.getStringList("Broadcast");
        if (args.length == 0) {
            sender.sendMessage(prefix + "You must specify a message to broadcast.");
        } else {
            for (int x = 0; x < broadcast.size(); x++) {
                int value = x;
                Bukkit.getOnlinePlayers().forEach(player ->
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', broadcast.get(value)
                                .replace("<broadcast-message>", String.join(" ", args))
                                .replace("<broadcast-sender>", sender.getName())))
                    );
            }
        }
        return true;
    }
}
