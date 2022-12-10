package com.github.treesontop.usefulcommands.events;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UserHandler;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = config.getString("Prefix");
        if (UserHandler.getUser(event.getPlayer()).isFrozen()) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(prefix + "You are frozen!");
        }
    }
}
