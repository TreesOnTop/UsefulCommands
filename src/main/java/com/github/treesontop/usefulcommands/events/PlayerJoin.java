package com.github.treesontop.usefulcommands.events;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UsefulCommands;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UsefulCommands.Players.put(event.getPlayer().getUniqueId().toString(),
            ConfigHandler.getPlayerData(event.getPlayer().getUniqueId().toString()));
    }
}
