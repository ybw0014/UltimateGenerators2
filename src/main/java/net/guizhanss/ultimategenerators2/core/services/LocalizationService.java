package net.guizhanss.ultimategenerators2.core.services;

import java.text.MessageFormat;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.guizhanss.guizhanlib.slimefun.addon.SlimefunLocalization;
import net.guizhanss.ultimategenerators2.UltimateGenerators2;

@SuppressWarnings("ConstantConditions")
public final class LocalizationService extends SlimefunLocalization {
    public LocalizationService(UltimateGenerators2 plugin) {
        super(plugin);
    }

    @ParametersAreNonnullByDefault
    @Nonnull
    public String getString(String key, Object... args) {
        return MessageFormat.format(getString(key), args);
    }


}
