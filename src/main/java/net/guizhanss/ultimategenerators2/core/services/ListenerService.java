package net.guizhanss.ultimategenerators2.core.services;

import javax.annotation.Nonnull;

import net.guizhanss.ultimategenerators2.UltimateGenerators2;
import net.guizhanss.ultimategenerators2.implementation.listeners.ClassifiedItemListener;
import net.guizhanss.ultimategenerators2.implementation.listeners.EnderCrystalEnhancerListener;
import net.guizhanss.ultimategenerators2.implementation.listeners.TranslationsLoadListener;

public final class ListenerService {
    public ListenerService(@Nonnull UltimateGenerators2 plugin) {
        new ClassifiedItemListener(plugin);
        new EnderCrystalEnhancerListener(plugin);
        new TranslationsLoadListener(plugin);
    }
}

