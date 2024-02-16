package net.guizhanss.ultimategenerators2.implementation.items.machines;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

import net.guizhanss.ultimategenerators2.implementation.UGItems;
import net.guizhanss.ultimategenerators2.implementation.items.abstracts.AMachine;

public class DieselRefinery extends AMachine {
    @ParametersAreNonnullByDefault
    public DieselRefinery(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    @Nonnull
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(80, SlimefunItems.OIL_BUCKET, UGItems.DIESEL_BUCKET);
    }
}
