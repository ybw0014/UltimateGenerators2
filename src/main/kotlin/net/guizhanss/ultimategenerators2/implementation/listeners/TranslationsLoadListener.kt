package net.guizhanss.ultimategenerators2.implementation.listeners

import net.guizhanss.slimefuntranslation.api.events.TranslationsLoadEvent
import net.guizhanss.ultimategenerators2.UltimateGenerators2
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class TranslationsLoadListener(val plugin: UltimateGenerators2) : Listener {
    init {
        if (UltimateGenerators2.integrationService().slimefunTranslationEnabled) {
            plugin.server.pluginManager.registerEvents(this, plugin)
        }
    }

    @EventHandler
    fun onLoad(e: TranslationsLoadEvent) {
        UltimateGenerators2.integrationService().loadTranslations()
    }
}
