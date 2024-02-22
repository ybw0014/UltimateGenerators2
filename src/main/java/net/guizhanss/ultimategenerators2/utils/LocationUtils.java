package net.guizhanss.ultimategenerators2.utils;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import org.bukkit.Location;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class LocationUtils {
    public static Location getBlockCenter(@Nonnull Location l) {
        Preconditions.checkArgument(l != null, "Location cannot be null");

        return new Location(l.getWorld(), l.getBlockX() + 0.5F, l.getBlockY() + 0.5F, l.getBlockZ() + 0.5F);
    }
}
