
package com.mdtt.client.hud;

import com.mdtt.client.config.PlayerData;
import com.mdtt.client.config.PlayerManager;
import com.mdtt.client.gamemode.Gamemode;
import com.mdtt.client.tier.Tier;
import net.minecraft.network.chat.Component;

public class NameTagRenderer {

    public static Component getTierLabel(String username) {
        PlayerData player = PlayerManager.getPlayer(username);

        if (player == null) {
            return Component.empty();
        }

        Tier tier = player.getHighestTier();

        if (tier == null) {
            return Component.empty();
        }

        return Component.literal(" " + tier.getDisplayName())
                .withStyle(style -> style.withBold(true));
    }

    public static boolean hasPlayerData(String username) {
        return PlayerManager.getPlayer(username) != null;
    }

    public static Tier getTier(String username, Gamemode gamemode) {
        PlayerData player = PlayerManager.getPlayer(username);

        if (player == null) {
            return null;
        }

        return player.getTier(gamemode);
    }
}
