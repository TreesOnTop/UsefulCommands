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
        if (args.length > 0) {
            if (!sender.hasPermission("usefulcommands.fly.others")) {
                sender.sendMessage(command.getPermissionMessage());
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                target.setAllowFlight(!(target.getAllowFlight()));
                target.sendMessage(prefix + "Your flight has been set to " + target.getAllowFlight());
                sender.sendMessage(prefix + target.getName() + "'s flight has been set to" + target.getAllowFlight() + ".");
            } else {
                sender.sendMessage(prefix + "That player is not online.");
            }
        } else if (sender instanceof Player) {
            Player p = (Player) sender;
            p.setAllowFlight(!(p.getAllowFlight()));
            p.sendMessage(prefix + "Your flight has been set to " + p.getAllowFlight());
        } else {
            sender.sendMessage(prefix + "You must be a player to use this command");
        }
        return true;
    }
}
