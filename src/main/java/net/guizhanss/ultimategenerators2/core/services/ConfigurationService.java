package net.guizhanss.ultimategenerators2.core.services;

import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig;
import net.guizhanss.ultimategenerators2.UltimateGenerators2;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public final class ConfigurationService {
    @Getter(AccessLevel.NONE)
    private final AddonConfig config;

    private boolean autoUpdate;
    private boolean debug;
    private String lang;
    private int glassElectricityTransmitterCraftAmount;

    public ConfigurationService(UltimateGenerators2 plugin) {
        config = new AddonConfig(plugin, "config.yml");
        reload();
    }

    public void reload() {
        config.reload();
        config.addMissingKeys();

        autoUpdate = config.getBoolean("auto-update", true);
        debug = config.getBoolean("debug", false);
        lang = config.getString("lang", "en");
        glassElectricityTransmitterCraftAmount = config.getInt("glass-electricity-transmitter.craft-amount", 12);

        config.save();
    }
}
