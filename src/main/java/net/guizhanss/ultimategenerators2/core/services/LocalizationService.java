package net.guizhanss.ultimategenerators2.core.services;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.guizhanss.guizhanlib.slimefun.addon.SlimefunLocalization;
import net.guizhanss.ultimategenerators2.UltimateGenerators2;
import net.guizhanss.ultimategenerators2.utils.FileUtils;

@SuppressWarnings("ConstantConditions")
public final class LocalizationService extends SlimefunLocalization {
    private static final String FOLDER_NAME = "lang";
    private final UltimateGenerators2 plugin;
    private final File jarFile;

    @ParametersAreNonnullByDefault
    public LocalizationService(UltimateGenerators2 plugin, File jarFile) {
        super(plugin);

        this.plugin = plugin;
        this.jarFile = jarFile;
        extractTranslations();
    }

    private void extractTranslations() {
        final File translationsFolder = new File(plugin.getDataFolder(), FOLDER_NAME);
        if (!translationsFolder.exists()) {
            translationsFolder.mkdirs();
        }
        List<String> translationFiles = FileUtils.listYmlFilesInJar(jarFile, FOLDER_NAME + "/");
        for (String translationFile : translationFiles) {
            String filePath = FOLDER_NAME + File.separator + translationFile;
            File file = new File(plugin.getDataFolder(), filePath);
            if (file.exists()) {
                continue;
            }
            plugin.saveResource(filePath, true);
        }
    }

    @ParametersAreNonnullByDefault
    @Nonnull
    public String getString(String key, Object... args) {
        return MessageFormat.format(getString(key), args);
    }

}
