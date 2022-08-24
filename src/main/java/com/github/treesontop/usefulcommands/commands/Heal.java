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
        if (args.length > 0) {
            if (!sender.hasPermission("usefulcommands.heal.others")) {
                sender.sendMessage(prefix + "§cI'm sorry but you do not have permission to perform this command. Please contact the server administrator if you believe that this is in error.");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                heal(target);
                sender.sendMessage(prefix + "You have healed " + target.getName() + ".");
            } else {
                sender.sendMessage(prefix + "That player is not online.");
            }
        } else if (sender instanceof Player player) {
            heal(player);
        } else {
            sender.sendMessage(prefix + "You must be a player to use this command");
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
