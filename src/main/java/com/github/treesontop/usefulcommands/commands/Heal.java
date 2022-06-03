package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (args.length > 0 && sender.hasPermission("usefulcommands.heal.others")) {
            try {
                heal(Bukkit.getPlayer(args[0]));
                sender.sendMessage(prefix + Bukkit.getPlayer(args[0]).getName() + " has been healed");
            } catch (Exception e) {
                sender.sendMessage(prefix + args[0] + " is not a valid player");
            }
        } else if (sender instanceof Player && args.length == 0) {
            heal((Player) sender);
        } else if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + "You must be a player to use this command");
        } else {
            sender.sendMessage(prefix + "§cI'm sorry but you do not have permission to perform this command. Please contact the server administrator if you believe that this is in error.");
        }
        return true;
    }

    private void heal(Player target) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        target.setHealth(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        target.sendMessage(prefix + "You have been healed");
    }
}
