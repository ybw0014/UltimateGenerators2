package net.guizhanss.ultimategenerators2;

import java.io.File;
import java.lang.reflect.Method;
import java.util.logging.Level;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import org.bukkit.plugin.Plugin;

import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.BlobBuildUpdater;

import net.guizhanss.guizhanlib.slimefun.addon.AbstractAddon;
import net.guizhanss.guizhanlib.updater.GuizhanBuildsUpdater;
import net.guizhanss.ultimategenerators2.core.services.ConfigurationService;
import net.guizhanss.ultimategenerators2.core.services.IntegrationService;
import net.guizhanss.ultimategenerators2.core.services.LocalizationService;
import net.guizhanss.ultimategenerators2.implementation.setup.UGItemSetup;

import org.bstats.bukkit.Metrics;

public final class UltimateGenerators2 extends AbstractAddon {
    private static final String DEFAULT_LANG = "en";

    private ConfigurationService configService;
    private LocalizationService localization;
    private IntegrationService integrationService;
    private boolean debugEnabled = false;

    public UltimateGenerators2() {
        super("ybw0014", "UltimateGenerators2", "master", "auto-update");
    }

    private static UltimateGenerators2 inst() {
        return getInstance();
    }

    @Nonnull
    public static ConfigurationService getConfigService() {
        return inst().configService;
    }

    @Nonnull
    public static LocalizationService getLocalization() {
        return inst().localization;
    }

    @Nonnull
    public static IntegrationService getIntegrationService() {
        return inst().integrationService;
    }

    public static void debug(@Nonnull String message, @Nonnull Object... args) {
        Preconditions.checkNotNull(message, "message cannot be null");

        if (inst().debugEnabled) {
            inst().getLogger().log(Level.INFO, "[DEBUG] " + message, args);
        }
    }

    @Override
    public void enable() {
        log(Level.INFO, "=====================");
        log(Level.INFO, " UltimateGenerators2 ");
        log(Level.INFO, "      by ybw0014     ");
        log(Level.INFO, "=====================");

        // config
        configService = new ConfigurationService(this);

        // debug
        debugEnabled = configService.isDebug();

        // localization
        log(Level.INFO, "Loading language...");
        String lang = configService.getLang();
        localization = new LocalizationService(this);
        localization.addLanguage(lang);
        if (!lang.equals(DEFAULT_LANG)) {
            localization.addLanguage(DEFAULT_LANG);
        }
        log(Level.INFO, localization.getString("console.loaded-language"), lang);

        // items
        UGItemSetup.setup(this);

        // integrations
        integrationService = new IntegrationService(this);

        // metrics
        setupMetrics();
    }

    @Override
    public void disable() {
    }

    private void setupMetrics() {
        new Metrics(this, 20496);
    }

    @Override
    protected void autoUpdate() {
        if (getPluginVersion().startsWith("Dev")) {
            new BlobBuildUpdater(this, getFile(), getGithubRepo()).start();
        } else if (getPluginVersion().startsWith("Build")) {
            try {
                // use updater in lib plugin
                Class<?> clazz = Class.forName("net.guizhanss.guizhanlibplugin.updater.GuizhanUpdater");
                Method updaterStart = clazz.getDeclaredMethod("start", Plugin.class, File.class, String.class, String.class, String.class);
                updaterStart.invoke(null, this, getFile(), getGithubUser(), getGithubRepo(), getGithubBranch());
            } catch (Exception ignored) {
                // use updater in lib
                new GuizhanBuildsUpdater(this, getFile(), getGithubUser(), getGithubRepo(), getGithubBranch()).start();
            }
        }
    }
}
