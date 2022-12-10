package com.github.treesontop.usefulcommands.events;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UsefulCommands;
import com.github.treesontop.usefulcommands.UserHandler;
import com.github.treesontop.usefulcommands.classes.User;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.util.Date;

public class AsyncPlayerChat implements Listener {
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        User u = UserHandler.getUser(event.getPlayer());
        if (u.getMuted() > new Date().getTime()) {
            event.setCancelled(true);
            player.sendMessage(prefix + "You are muted.");
        }
    }
}
