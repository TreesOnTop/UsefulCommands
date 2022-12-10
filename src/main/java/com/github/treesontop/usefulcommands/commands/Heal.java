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
                sender.sendMessage(command.getPermissionMessage());
                return true;
            }
            Player p = Bukkit.getPlayer(args[0]);
            if (p != null) {
                p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                p.sendMessage(prefix + "You have been healed");
                sender.sendMessage(prefix + "You have healed " + p.getName() + ".");
            } else {
                sender.sendMessage(prefix + "That player is not online.");
            }
        } else if (sender instanceof Player) {
            Player p = (Player) sender;
            p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            p.sendMessage(prefix + "You have been healed");
        } else {
            sender.sendMessage(prefix + "You must be a player to use this command");
        }
        return true;
    }
}
