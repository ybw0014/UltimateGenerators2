package net.guizhanss.ultimategenerators2.utils;

import org.bukkit.NamespacedKey;

import net.guizhanss.ultimategenerators2.UltimateGenerators2;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Keys {
    public static final NamespacedKey ITEMGROUP_MAIN = createKey("main");
    public static final NamespacedKey ITEMGROUP_MISC = createKey("misc");
    public static final NamespacedKey ITEMGROUP_MACHINES = createKey("machines");
    public static final NamespacedKey ITEMGROUP_ELECTRICITY_STORAGE = createKey("electricity_storage");
    public static final NamespacedKey ITEMGROUP_SIMPLE_GENERATORS = createKey("simple_generators");
    public static final NamespacedKey ITEMGROUP_MODULAR_GENERATORS = createKey("modular_generators");
    public static final NamespacedKey CANNOT_CRAFT = createKey("cannot_craft");
    public static final NamespacedKey DIESEL_REFINERY = createKey("diesel_refinery");
    public static final NamespacedKey BIOMASS_EXTRACTION_MACHINE = createKey("biomass_extraction_machine");
    public static final NamespacedKey BIOFUEL_REFINERY = createKey("biofuel_refinery");
    public static final NamespacedKey HEAVY_WATER_REFINING_MACHINE = createKey("heavy_water_refining_machine");

    public static final String ENHANCED_ENDER_CRYSTAL = "UG2_ENHANCED_ENDER_CRYSTAL";

    private static NamespacedKey createKey(String key) {
        return new NamespacedKey(UltimateGenerators2.getInstance(), key);
    }
}
