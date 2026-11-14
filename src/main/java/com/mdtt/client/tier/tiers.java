
package com.mdtt.client.tier;

public enum Tier {

    LT5("LT5", 1),
    LT4("LT4", 2),
    LT3("LT3", 3),
    LT2("LT2", 4),
    LT1("LT1", 5),

    HT5("HT5", 6),
    HT4("HT4", 7),
    HT3("HT3", 8),
    HT2("HT2", 9),
    HT1("HT1", 10);

    private final String displayName;
    private final int level;

    Tier(String displayName, int level) {
        this.displayName = displayName;
        this.level = level;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getLevel() {
        return level;
    }

    public boolean isHigherThan(Tier other) {
        return this.level > other.level;
    }

    public static Tier fromString(String name) {
        if (name == null) return null;

        try {
            return Tier.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException exception) {
            return null;
        }
    }
}
