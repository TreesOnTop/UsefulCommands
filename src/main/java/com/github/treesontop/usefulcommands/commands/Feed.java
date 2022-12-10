package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (args.length > 0) {
            if (!sender.hasPermission("usefulcommands.feed.others")) {
                sender.sendMessage(command.getPermissionMessage());
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                feed(target);
                sender.sendMessage(prefix + "You have fed " + target.getName() + ".");
            } else {
                sender.sendMessage(prefix + "That player is not online.");
            }
        } else if (sender instanceof Player) {
            feed((Player) sender);
        } else {
            sender.sendMessage(prefix + "You must be a player to use this command");
        }
        return true;
    }

    private void feed(Player target) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        target.setFoodLevel(20);
        target.setSaturation(20);
        target.sendMessage(prefix + "You have been fed");
    }
}
