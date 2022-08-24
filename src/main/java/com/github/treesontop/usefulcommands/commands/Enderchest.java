package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
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
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.openInventory(player.getEnderChest());
            } else if (sender.hasPermission("usefulcommands.enderchest.others")) {
                try {
                    Player target = sender.getServer().getPlayer(args[0]);
                    player.openInventory(target.getEnderChest());
                } catch (Exception e) {
                    sender.sendMessage(prefix + args[0] + " is not a valid player");
                }
            } else {
                sender.sendMessage("§cI'm sorry but you do not have permission to perform this command. Please contact the server administrator if you believe that this is in error.");
            }
        } else {
            sender.sendMessage(prefix + "You must be a player to use this command");
        }
        return true;
    }
}
