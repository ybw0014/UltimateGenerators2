package net.guizhanss.ultimategenerators2.implementation.items.generators.simple;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

import net.guizhanss.ultimategenerators2.implementation.items.abstracts.AGenerator;

public class ReactionGenerator extends AGenerator {
    @ParametersAreNonnullByDefault
    public ReactionGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    @Nonnull
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    protected void registerDefaultFuelTypes() {
        registerFuel(6, SlimefunItems.TINY_URANIUM);
        registerFuel(54, SlimefunItems.SMALL_URANIUM);
        registerFuel(216, SlimefunItems.URANIUM);
    }
}
