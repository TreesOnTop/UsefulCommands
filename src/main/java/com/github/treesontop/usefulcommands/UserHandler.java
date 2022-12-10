package com.github.treesontop.usefulcommands;

import com.github.treesontop.usefulcommands.classes.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

public class UserHandler {
    static final HashMap<UUID, User> players = new HashMap<>();

    public static User getPlayerData(UUID uuid) {
        Gson gson = new Gson();
        File path = new File(UsefulCommands.getMainClass().getDataFolder() + File.separator + "PlayerData");
        if(!path.exists()) {
            path.mkdir();
        }
        File file = new File(path, uuid + ".json");
        User user;
        if (file.exists()) {
            try {
                Reader reader = Files.newBufferedReader(Paths.get(file.getPath()));
                user = gson.fromJson(reader, User.class);
            } catch (IOException e) {
                user = new User();
            }
        } else {
            user = new User();
        }

        return user;
    }

    public static void addUser(Player p) {
        UUID uuid = p.getUniqueId();
        players.put(uuid, getPlayerData(uuid));
    }

    public static void removeUser(Player p) {
        final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeHierarchyAdapter(ConfigurationSerializable.class, new ConfigurationSerializableAdapter())
            .create();
        UUID uuid = p.getUniqueId();
        File path = new File(UsefulCommands.getMainClass().getDataFolder() + File.separator + "PlayerData" + File.separator + uuid + ".json");
        User playerData = players.get(uuid);
        String json = gson.toJson(playerData);
        try {
            Files.write(path.toPath(), json.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(Player p) {
        return players.get(p.getUniqueId());
    }

    public static void setUser(Player p, User u) {
        players.replace(p.getUniqueId(), u);
    }
}
