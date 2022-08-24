package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UsefulCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Enderchest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (!(sender instanceof Player player)) {
            sender.sendMessage(prefix + "You must be a player to use this command");
            return true;
        }
        if (args.length == 0) {
            player.openInventory(player.getEnderChest());
        } else if (sender.hasPermission("usefulcommands.enderchest.others")) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                target.openInventory(target.getEnderChest());
            } else {
                sender.sendMessage(prefix + "That player is not online.");
            }
        } else {
            sender.sendMessage(UsefulCommands.getPermissionMessage());
        }
        return true;
    }
}
