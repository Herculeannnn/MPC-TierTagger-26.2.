
package com.mdtt.client;

import net.fabricmc.api.ClientModInitializer;

public class MDTTClient implements ClientModInitializer {

    public static final String MOD_ID = "mdtt";
    public static final String VERSION = "1.0.0";

    @Override
    public void onInitializeClient() {
        System.out.println("[MDTT] Maldives TierTagger initialized!");

        System.out.println("[MDTT] Minecraft: 1.21.11");
        System.out.println("[MDTT] Fabric client loaded.");
        System.out.println("[MDTT] TierTagger engine starting...");
    }
}
