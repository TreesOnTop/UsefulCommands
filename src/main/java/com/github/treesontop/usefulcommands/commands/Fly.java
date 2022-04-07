package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("OutputPrefix"));
        String permissionMessage = ChatColor.translateAlternateColorCodes('&', config.getString("PermissionMessage"));
        if (args.length > 0) {
            try {
                if (sender.hasPermission("usefulcommands.fly.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.setAllowFlight(!(target.getAllowFlight()));
                    target.sendMessage(prefix + "Your flight has been set to " + target.getAllowFlight());
                    sender.sendMessage(prefix + target.getName() + "'s flight has been set to " + target.getAllowFlight());
                } else {
                    sender.sendMessage(permissionMessage);
                }
            } catch (Exception e) {
                sender.sendMessage(prefix + args[0] + " is not a valid player");
            }
        } else if (sender instanceof Player) {
            if (sender.hasPermission("usefulcommands.fly")) {
                Player target = (Player) sender;
                target.setAllowFlight(!(target.getAllowFlight()));
                target.sendMessage(prefix + "Your flight has been set to " + target.getAllowFlight());
            } else {
                sender.sendMessage(permissionMessage);
            }
        } else {
            sender.sendMessage(prefix + "You must be a player to use this command");
        }
        return true;
    }
}
