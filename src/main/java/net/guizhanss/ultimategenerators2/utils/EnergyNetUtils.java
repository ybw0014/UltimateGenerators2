package net.guizhanss.ultimategenerators2.utils;

import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.Location;

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class EnergyNetUtils {
    @ParametersAreNonnullByDefault
    public static boolean takeCharge(EnergyNetComponent block, Location location, int consumption) {
        Preconditions.checkArgument(block != null, "Block cannot be null");
        Preconditions.checkArgument(location != null, "Location cannot be null");

        if (!block.isChargeable()) {
            return false;
        }

        int charge = block.getCharge(location);
        if (charge < consumption) {
            return false;
        }

        block.setCharge(location, charge - consumption);
        return true;
    }
}
