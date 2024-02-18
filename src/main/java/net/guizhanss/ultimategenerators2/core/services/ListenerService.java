package net.guizhanss.ultimategenerators2.core.services;

import net.guizhanss.ultimategenerators2.UltimateGenerators2;
import net.guizhanss.ultimategenerators2.implementation.listeners.ClassifiedItemListener;

import javax.annotation.Nonnull;

public final class ListenerService {
    public ListenerService(@Nonnull UltimateGenerators2 plugin) {
        new ClassifiedItemListener(plugin);
    }
}

