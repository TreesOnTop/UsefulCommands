package com.github.treesontop.usefulcommands.events;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UsefulCommands;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.Date;

public class AsyncChat implements Listener {
    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        File playerDataFile = UsefulCommands.Players.get(player.getUniqueId().toString());
        YamlConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);
        if (playerData.getLong("muted") > new Date().getTime()) {
            event.setCancelled(true);
            player.sendMessage(prefix + "You are muted.");
        }
    }
}
