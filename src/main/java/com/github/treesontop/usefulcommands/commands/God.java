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

public class God implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (args.length > 0) {
            if (!sender.hasPermission("usefulcommands.god.others")) {
                sender.sendMessage(command.getPermissionMessage());
                return true;
            }
            Player p = Bukkit.getPlayer(args[0]);
            if (p != null) {
                User user = UserHandler.getUser(p);
                user.setGod(!user.isGod());
                p.sendMessage(prefix + "Your god mode has been " + (user.isGod() ? "enabled" : "disabled") + ".");
                sender.sendMessage(prefix + "You have " + (user.isGod() ? "enabled" : "disabled") + " god mode for " + p.getName() + ".");
            } else {
                sender.sendMessage(prefix + "That player is not online.");
            }
        }
        else if (sender instanceof Player) {
            Player p = (Player) sender;
            User user = UserHandler.getUser(p);
            user.setGod(!user.isGod());
            p.sendMessage(prefix + "Your god mode has been " + (user.isGod() ? "enabled" : "disabled") + ".");
        } else {
            sender.sendMessage(prefix + "You must be a player to use this command");
        }
        return true;
    }
}
