
package com.mdtt.client.config;

import com.mdtt.client.gamemode.Gamemode;
import com.mdtt.client.tier.Tier;

import java.util.EnumMap;
import java.util.Map;

public class PlayerData {

    private final String username;
    private final Map<Gamemode, Tier> tiers;

    public PlayerData(String username) {
        this.username = username;
        this.tiers = new EnumMap<>(Gamemode.class);
    }

    public String getUsername() {
        return username;
    }

    public void setTier(Gamemode gamemode, Tier tier) {
        if (gamemode == null || tier == null) return;
        tiers.put(gamemode, tier);
    }

    public Tier getTier(Gamemode gamemode) {
        return tiers.get(gamemode);
    }

    public Map<Gamemode, Tier> getTiers() {
        return tiers;
    }

    public Tier getHighestTier() {
        return tiers.values()
                .stream()
                .max((a, b) -> Integer.compare(
                        a.getLevel(), b.getLevel()))
                .orElse(null);
    }
}
