package net.guizhanss.ultimategenerators2.implementation.items.generators;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import net.guizhanss.ultimategenerators2.implementation.UGItems;
import net.guizhanss.ultimategenerators2.implementation.items.abstracts.AGenerator;

public class BioFuelGenerator extends AGenerator {
    @ParametersAreNonnullByDefault
    public BioFuelGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    @Nonnull
    public ItemStack getProgressBar() {
        return new ItemStack(Material.MAGMA_CREAM);
    }

    @Override
    protected void registerDefaultFuelTypes() {
        registerFuel(240, UGItems.BIOFUEL_BUCKET);
    }
}
