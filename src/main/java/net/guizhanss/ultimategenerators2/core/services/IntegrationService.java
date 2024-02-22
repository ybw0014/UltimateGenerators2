package net.guizhanss.ultimategenerators2.core.services;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import net.guizhanss.slimefuntranslation.api.config.TranslationConfiguration;
import net.guizhanss.slimefuntranslation.api.config.TranslationConfigurationFields;
import net.guizhanss.slimefuntranslation.utils.FileUtils;
import net.guizhanss.ultimategenerators2.UltimateGenerators2;

import lombok.Getter;

public final class IntegrationService {
    private final UltimateGenerators2 plugin;

    @Getter
    private boolean slimefunTranslationEnabled = false;

    public IntegrationService(UltimateGenerators2 plugin) {
        this.plugin = plugin;

        if (isPluginEnabled("SlimefunTranslation")) {
            slimefunTranslationEnabled = true;
        }
    }

    private boolean isPluginEnabled(String pluginName) {
        return plugin.getServer().getPluginManager().isPluginEnabled(pluginName);
    }

    public void loadTranslations() {
        var fields = TranslationConfigurationFields.builder().items("items").build();
        List<String> languages = FileUtils.listYamlFiles(new File(plugin.getDataFolder(), "lang"));
        for (String langFile : languages) {
            var file = new File(plugin.getDataFolder(), "lang" + File.separator + langFile);
            String lang = langFile.replace(".yml", "");
            var fileConfig = YamlConfiguration.loadConfiguration(file);
            var cfg = TranslationConfiguration.fromFileConfiguration(lang, fileConfig, fields);
            cfg.ifPresent(translationConfiguration -> translationConfiguration.register(plugin));
        }
    }
}
