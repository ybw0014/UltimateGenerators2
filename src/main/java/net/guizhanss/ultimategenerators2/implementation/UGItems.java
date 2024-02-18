package net.guizhanss.ultimategenerators2.implementation;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import net.guizhanss.ultimategenerators2.UltimateGenerators2;
import net.guizhanss.ultimategenerators2.utils.Heads;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class UGItems {
    public static final SlimefunItemStack ENDLESS_GENERATOR = UltimateGenerators2.getLocalization().getItem(
        "ENDLESS_GENERATOR",
        Heads.ENDLESS_GENERATOR.getTexture()
    );

    public static final SlimefunItemStack DIESEL_REFINERY = UltimateGenerators2.getLocalization().getItem(
        "DIESEL_REFINERY",
        Material.PISTON
    );

    public static final SlimefunItemStack DIESEL_BUCKET = UltimateGenerators2.getLocalization().getItem(
        "DIESEL_BUCKET",
        Heads.DIESEL_BUCKET.getTexture()
    );

    public static final SlimefunItemStack DIESEL_GENERATOR = UltimateGenerators2.getLocalization().getItem(
        "DIESEL_GENERATOR",
        Heads.DIESEL_GENERATOR.getTexture()
    );

    public static final SlimefunItemStack BIOMASS_EXTRACTION_MACHINE = UltimateGenerators2.getLocalization().getItem(
        "BIOMASS_EXTRACTION_MACHINE",
        Material.LIME_STAINED_GLASS
    );

    public static final SlimefunItemStack BIOMASS_BUCKET = UltimateGenerators2.getLocalization().getItem(
        "BIOMASS_BUCKET",
        Heads.BIOMASS_BUCKET.getTexture()
    );

    public static final SlimefunItemStack BIOFUEL_REFINERY = UltimateGenerators2.getLocalization().getItem(
        "BIOFUEL_REFINERY",
        Material.PISTON
    );

    public static final SlimefunItemStack BIOFUEL_BUCKET = UltimateGenerators2.getLocalization().getItem(
        "BIOFUEL_BUCKET",
        Heads.BIOFUEL_BUCKET.getTexture()
    );

    public static final SlimefunItemStack BIOFUEL_GENERATOR = UltimateGenerators2.getLocalization().getItem(
        "BIOFUEL_GENERATOR",
        Material.YELLOW_STAINED_GLASS
    );

    public static final SlimefunItemStack DRAGON_BREATH_GENERATOR = UltimateGenerators2.getLocalization().getItem(
        "DRAGON_BREATH_GENERATOR",
        Material.MAGENTA_STAINED_GLASS
    );

    public static final SlimefunItemStack REACTION_GENERATOR = UltimateGenerators2.getLocalization().getItem(
        "REACTION_GENERATOR",
        Material.GREEN_TERRACOTTA
    );

    public static final SlimefunItemStack ENDER_CRYSTAL_GENERATOR = UltimateGenerators2.getLocalization().getItem(
        "ENDER_CRYSTAL_GENERATOR",
        Material.PURPLE_STAINED_GLASS
    );

    public static final SlimefunItemStack ENDER_CRYSTAL_GENERATOR_BASE = UltimateGenerators2.getLocalization().getItem(
        "ENDER_CRYSTAL_GENERATOR_BASE",
        Material.END_STONE_BRICKS
    );

    public static final SlimefunItemStack ENDER_CRYSTAL_GENERATOR_STABILIZER = UltimateGenerators2.getLocalization().getItem(
        "ENDER_CRYSTAL_GENERATOR_STABILIZER",
        Material.OBSIDIAN
    );

    public static final SlimefunItemStack ENDER_CRYSTAL_ENHANCER = UltimateGenerators2.getLocalization().getItem(
        "ENDER_CRYSTAL_ENHANCER",
        new CustomItemStack(Material.POTION, im -> {
            var meta = (PotionMeta) im;
            meta.clearCustomEffects();
            meta.setColor(Color.fromRGB(85, 0, 255));
        })
    );

    public static final SlimefunItemStack MODULAR_GENERATOR_REGULATOR = UltimateGenerators2.getLocalization().getItem(
        "MODULAR_GENERATOR_REGULATOR",
        Heads.MODULAR_GENERATOR_REGULATOR.getTexture()
    );

    public static final SlimefunItemStack SOLID_STORAGE_EXPANSION = UltimateGenerators2.getLocalization().getItem(
        "SOLID_STORAGE_EXPANSION",
        Heads.SOLID_STORAGE_EXPANSION.getTexture()
    );

    public static final SlimefunItemStack LIQUID_STORAGE_EXPANSION = UltimateGenerators2.getLocalization().getItem(
        "LIQUID_STORAGE_EXPANSION",
        Heads.LIQUID_STORAGE_EXPANSION.getTexture()
    );

    public static final SlimefunItemStack ELECTRICITY_STORAGE_UNIT = UltimateGenerators2.getLocalization().getItem(
        "ELECTRICITY_STORAGE_UNIT",
        Heads.ELECTRICITY_STORAGE_UNIT.getTexture()
    );

    public static final SlimefunItemStack ADVANCED_BATTERY = UltimateGenerators2.getLocalization().getItem(
        "ADVANCED_BATTERY",
        Heads.ADVANCED_BATTERY.getTexture()
    );

    public static final SlimefunItemStack ALPHA_BATTERY = UltimateGenerators2.getLocalization().getItem(
        "ALPHA_BATTERY",
        Heads.ALPHA_BATTERY.getTexture()
    );

    public static final SlimefunItemStack BETA_BATTERY = UltimateGenerators2.getLocalization().getItem(
        "BETA_BATTERY",
        Heads.BETA_BATTERY.getTexture()
    );

    public static final SlimefunItemStack GAMMA_BATTERY = UltimateGenerators2.getLocalization().getItem(
        "GAMMA_BATTERY",
        Heads.GAMMA_BATTERY.getTexture()
    );

    public static final SlimefunItemStack BASIC_ELECTRICITY_STORAGE = UltimateGenerators2.getLocalization().getItem(
        "BASIC_ELECTRICITY_STORAGE",
        Material.YELLOW_STAINED_GLASS
    );

    public static final SlimefunItemStack ADVANCED_ELECTRICITY_STORAGE = UltimateGenerators2.getLocalization().getItem(
        "ADVANCED_ELECTRICITY_STORAGE",
        Material.ORANGE_STAINED_GLASS
    );

    public static final SlimefunItemStack ALPHA_ELECTRICITY_STORAGE = UltimateGenerators2.getLocalization().getItem(
        "ALPHA_ELECTRICITY_STORAGE",
        Material.LIME_STAINED_GLASS
    );

    public static final SlimefunItemStack BETA_ELECTRICITY_STORAGE = UltimateGenerators2.getLocalization().getItem(
        "BETA_ELECTRICITY_STORAGE",
        Material.BLUE_STAINED_GLASS
    );

    public static final SlimefunItemStack GAMMA_ELECTRICITY_STORAGE = UltimateGenerators2.getLocalization().getItem(
        "GAMMA_ELECTRICITY_STORAGE",
        Material.MAGENTA_STAINED_GLASS
    );

    public static final SlimefunItemStack LAMBDA_ELECTRICITY_STORAGE = UltimateGenerators2.getLocalization().getItem(
        "LAMBDA_ELECTRICITY_STORAGE",
        Material.PINK_STAINED_GLASS
    );

    public static final SlimefunItemStack KAPA_ELECTRICITY_STORAGE = UltimateGenerators2.getLocalization().getItem(
        "KAPA_ELECTRICITY_STORAGE",
        Material.BLACK_STAINED_GLASS
    );

    public static final SlimefunItemStack PHI_ELECTRICITY_STORAGE = UltimateGenerators2.getLocalization().getItem(
        "PHI_ELECTRICITY_STORAGE",
        Material.WHITE_STAINED_GLASS
    );

    public static final SlimefunItemStack QUANTUM_SOLAR_GENERATOR = UltimateGenerators2.getLocalization().getItem(
        "QUANTUM_SOLAR_GENERATOR",
        Material.DAYLIGHT_DETECTOR
    );

    public static final SlimefunItemStack RUNE_COMPLEX_ENDER = UltimateGenerators2.getLocalization().getItem(
        "RUNE_COMPLEX_ENDER",
        new CustomItemStack(Material.FIREWORK_STAR, im -> {
            var meta = (FireworkEffectMeta) im;
            meta.setEffect(FireworkEffect.builder().with(FireworkEffect.Type.BURST)
                .withColor(Color.fromRGB(85, 0, 255)).build());
        })
    );

    public static final SlimefunItemStack ENDER_LUMP_4 = UltimateGenerators2.getLocalization().getItem(
        "ENDER_LUMP_4",
        Material.GOLD_NUGGET
    );

    public static final SlimefunItemStack HEAVY_WATER_BUCKET = UltimateGenerators2.getLocalization().getItem(
        "HEAVY_WATER_BUCKET",
        Material.WATER_BUCKET
    );

    public static final SlimefunItemStack HEAVY_WATER_REFINING_MACHINE = UltimateGenerators2.getLocalization().getItem(
        "HEAVY_WATER_REFINING_MACHINE",
        Material.GRAY_STAINED_GLASS
    );

    public static final SlimefunItemStack NEUTRON_MODERATOR = UltimateGenerators2.getLocalization().getItem(
        "NEUTRON_MODERATOR",
        new CustomItemStack(Material.POTION, im -> {
            PotionMeta meta = (PotionMeta) im;
            meta.clearCustomEffects();
            meta.setColor(Color.BLACK);
        })
    );

    public static final SlimefunItemStack THERMAL_NEUTRON_REACTOR = UltimateGenerators2.getLocalization().getItem(
        "THERMAL_NEUTRON_REACTOR",
        Heads.THERMAL_NEUTRON_REACTOR.getTexture()
    );

    public static final SlimefunItemStack THERMAL_NEUTRON_REACTOR_COOLANT_CELL = UltimateGenerators2.getLocalization().getItem(
        "THERMAL_NEUTRON_REACTOR_COOLANT_CELL",
        Heads.THERMAL_NEUTRON_REACTOR_COOLANT_CELL.getTexture()
    );

    public static final SlimefunItemStack RAINBOW_ALLOY = UltimateGenerators2.getLocalization().getItem(
        "RAINBOW_ALLOY",
        Heads.RAINBOW_ALLOY.getTexture()
    );

    public static final SlimefunItemStack REINFORCED_RAINBOW_GLASS = glow(UltimateGenerators2.getLocalization().getItem(
        "REINFORCED_RAINBOW_GLASS",
        Material.MAGENTA_STAINED_GLASS
    ));

    public static final SlimefunItemStack RAINBOW_REACTOR = UltimateGenerators2.getLocalization().getItem(
        "RAINBOW_REACTOR",
        Material.PINK_STAINED_GLASS
    );

    public static final SlimefunItemStack GLASS_ELECTRICITY_TRANSMITTER = UltimateGenerators2.getLocalization().getItem(
        "GLASS_ELECTRICITY_TRANSMITTER",
        Material.CYAN_STAINED_GLASS
    );

    private static SlimefunItemStack glow(SlimefunItemStack sfItemStack) {
        sfItemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta im = sfItemStack.getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        sfItemStack.setItemMeta(im);
        return sfItemStack;
    }
}
