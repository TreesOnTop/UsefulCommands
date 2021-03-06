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
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        String permissionMessage = ChatColor.translateAlternateColorCodes('&', config.getString("PermissionMessage"));
        if (args.length > 0 && sender.hasPermission("usefulcommands.fly.others")) {
            try {
                fly(Bukkit.getPlayer(args[0]));
                sender.sendMessage(prefix + Bukkit.getPlayer(args[0]).getName() + "'s flight has been set to " + Bukkit.getPlayer(args[0]).getAllowFlight());
            } catch (Exception e) {
                sender.sendMessage(prefix + args[0] + " is not a valid player");
            }
        } else if (sender instanceof Player && args.length == 0 && sender.hasPermission("usefulcommands.fly")) {
            fly((Player) sender);
        } else if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "You must be a player to use this command");
        } else {
            sender.sendMessage(permissionMessage);
        }
        return true;
    }
    private void fly(Player target) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        target.setAllowFlight(!(target.getAllowFlight()));
        target.sendMessage(prefix + "Your flight has been set to " + target.getAllowFlight());
    }
}