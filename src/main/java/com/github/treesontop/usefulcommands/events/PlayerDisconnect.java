package com.github.treesontop.usefulcommands.events;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UsefulCommands;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerDisconnect implements Listener {
    @EventHandler
    public void onPlayerDisconnect(PlayerJoinEvent event) {
        UsefulCommands.Players.remove(ConfigHandler.getPlayerData(event.getPlayer().getUniqueId().toString()));
    }
}
