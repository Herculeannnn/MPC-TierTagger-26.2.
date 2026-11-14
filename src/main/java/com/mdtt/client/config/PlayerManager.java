
package com.mdtt.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.mdtt.client.gamemode.Gamemode;
import com.mdtt.client.tier.Tier;

import net.fabricmc.loader.api.FabricLoader;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerManager {

    private static final Gson GSON =
            new GsonBuilder().setPrettyPrinting().create();

    private static final Path FILE =
            FabricLoader.getInstance()
                    .getConfigDir()
                    .resolve("mdtt/players.json");

    private static final Map<String, PlayerData> PLAYERS =
            new LinkedHashMap<>();

    public static void load() {
        try {
            Files.createDirectories(FILE.getParent());

            if (!Files.exists(FILE)) {
                save();
                return;
            }

            try (Reader reader = Files.newBufferedReader(FILE)) {
                JsonObject root = JsonParser.parseReader(reader)
                        .getAsJsonObject();

                JsonObject players = root.getAsJsonObject("players");

                PLAYERS.clear();

                if (players == null) return;

                for (String username : players.keySet()) {
                    JsonObject data = players.getAsJsonObject(username);

                    PlayerData player = new PlayerData(username);

                    for (String modeName : data.keySet()) {
                        Gamemode mode = Gamemode.fromString(modeName);
                        Tier tier = Tier.fromString(
                                data.get(modeName).getAsString()
                        );

                        if (mode != null && tier != null) {
                            player.setTier(mode, tier);
                        }
                    }

                    PLAYERS.put(username, player);
                }
            }

            System.out.println("[MDTT] Loaded "
                    + PLAYERS.size() + " players.");

        } catch (Exception e) {
            System.err.println("[MDTT] Failed to load players!");
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());

            JsonObject root = new JsonObject();
            JsonObject players = new JsonObject();

            for (PlayerData player : PLAYERS.values()) {
                JsonObject tiers = new JsonObject();

                for (Map.Entry<Gamemode, Tier> entry :
                        player.getTiers().entrySet()) {

                    tiers.addProperty(
                            entry.getKey().getDisplayName(),
                            entry.getValue().getDisplayName()
                    );
                }

                players.add(player.getUsername(), tiers);
            }

            root.add("players", players);

            try (Writer writer = Files.newBufferedWriter(FILE)) {
                GSON.toJson(root, writer);
            }

        } catch (Exception e) {
            System.err.println("[MDTT] Failed to save players!");
            e.printStackTrace();
        }
    }

    public static PlayerData getPlayer(String username) {
        return PLAYERS.get(username);
    }

    public static Collection<PlayerData> getPlayers() {
        return Collections.unmodifiableCollection(
                PLAYERS.values()
        );
    }

    public static void setTier(
            String username,
            Gamemode gamemode,
            Tier tier
    ) {
        if (username == null || username.isBlank()
                || gamemode == null || tier == null) {
            return;
        }

        PlayerData player = PLAYERS.computeIfAbsent(
                username,
                PlayerData::new
        );

        player.setTier(gamemode, tier);
        save();
    }

    public static void removePlayer(String username) {
        if (PLAYERS.remove(username) != null) {
            save();
        }
    }
}
