package net.guizhanss.ultimategenerators2.implementation.listeners;

import javax.annotation.Nonnull;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.guizhanss.slimefuntranslation.api.events.TranslationsLoadEvent;
import net.guizhanss.ultimategenerators2.UltimateGenerators2;

public final class TranslationsLoadListener implements Listener {
    public TranslationsLoadListener(@Nonnull UltimateGenerators2 plugin) {
        if (UltimateGenerators2.getIntegrationService().isSlimefunTranslationEnabled()) {
            plugin.getServer().getPluginManager().registerEvents(this, plugin);
        }
    }

    @EventHandler
    public void onLoad(@Nonnull TranslationsLoadEvent e) {
        UltimateGenerators2.getIntegrationService().loadTranslations();
    }
}
