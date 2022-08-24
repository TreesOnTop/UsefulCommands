package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class Repair implements CommandExecutor {

    public static void fix(ItemStack item) {
        if (item.getItemMeta() instanceof Damageable meta) {
            meta.setDamage(0);
            item.setItemMeta((ItemMeta) meta);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (label.equalsIgnoreCase("fixall") || label.equalsIgnoreCase("repairall")) {
            for (ItemStack item : ((Player) sender).getInventory().getContents()) {
                if (item != null) {
                    fix(item);
                }
            }
            sender.sendMessage(prefix + "All of your items have been repaired.");
        } else {
            fix(((Player) sender).getInventory().getItemInMainHand());
            sender.sendMessage(prefix + "Your held item has been repaired.");
        }
        return true;
    }
}
