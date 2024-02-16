package net.guizhanss.ultimategenerators2.implementation.setup;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import net.guizhanss.ultimategenerators2.UltimateGenerators2;
import net.guizhanss.ultimategenerators2.implementation.UGItems;
import net.guizhanss.ultimategenerators2.utils.Keys;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class UGRecipeTypes {
    public static final RecipeType CANNOT_CRAFT = new RecipeType(
        Keys.CANNOT_CRAFT,
        UltimateGenerators2.getLocalization().getItem("_UG2_CANNOT_CRAFT", Material.BARRIER)
    );

    public static final RecipeType DIESEL_REFINERY = new RecipeType(
        Keys.DIESEL_REFINERY,
        UltimateGenerators2.getLocalization().getItem("_UG2_DIESEL_REFINERY", UGItems.DIESEL_REFINERY)
    );

    public static final RecipeType BIOMASS_EXTRACTION_MACHINE = new RecipeType(
        Keys.BIOMASS_EXTRACTION_MACHINE,
        UltimateGenerators2.getLocalization().getItem("_UG2_BIOMASS_EXTRACTION_MACHINE", UGItems.BIOMASS_EXTRACTION_MACHINE)
    );

    public static final RecipeType BIOFUEL_REFINERY = new RecipeType(
        Keys.BIOFUEL_REFINERY,
        UltimateGenerators2.getLocalization().getItem("_UG2_BIOFUEL_REFINERY", UGItems.BIOFUEL_REFINERY)
    );

    public static final RecipeType HEAVY_WATER_REFINING_MACHINE = new RecipeType(
        Keys.HEAVY_WATER_REFINING_MACHINE,
        UltimateGenerators2.getLocalization().getItem("_UG2_HEAVY_WATER_REFINING_MACHINE", UGItems.HEAVY_WATER_REFINING_MACHINE)
    );
}
