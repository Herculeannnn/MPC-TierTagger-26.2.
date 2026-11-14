
package com.mdtt.client.gamemode;

public enum Gamemode {

    MACE("Mace"),
    SWORD("Sword"),
    CRYSTAL("Crystal"),
    AXE("Axe"),
    SMP("SMP"),
    POT("Pot"),
    NETHPOT("NethPot"),
    UHC("UHC");

    private final String displayName;

    Gamemode(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Gamemode fromString(String name) {
        if (name == null) return null;

        for (Gamemode mode : values()) {
            if (mode.name().equalsIgnoreCase(name)
                    || mode.displayName.equalsIgnoreCase(name)) {
                return mode;
            }
        }

        return null;
    }
}
