package com.mdtt.client.utils;

import com.mdtt.client.gamemode.Gamemode;
import net.minecraft.resources.Identifier;

public class GamemodeIcons {

    public static Identifier getIcon(Gamemode mode) {
        if (mode == null) return null;

        String file = switch (mode) {
            case MACE -> "mace";
            case SWORD -> "sword";
            case AXE -> "axe";
            case SMP -> "smp";
            case POT -> "pot";
            case NETHPOT -> "nethop";
            case UHC -> "uhc";
            case VANILLA -> "vanilla";
            case DIA_SMP -> "dia_smp";
            case CRYSTAL -> null;
        };

        if (file == null) return null;

        return Identifier.fromNamespaceAndPath(
                "mdtt",
                "textures/gamemodes/" + file + ".png"
        );
    }
}