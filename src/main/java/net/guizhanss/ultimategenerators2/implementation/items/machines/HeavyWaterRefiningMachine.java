package net.guizhanss.ultimategenerators2.implementation.items.machines;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import net.guizhanss.ultimategenerators2.implementation.UGItems;
import net.guizhanss.ultimategenerators2.implementation.items.abstracts.AMachine;

public class HeavyWaterRefiningMachine extends AMachine {
    @ParametersAreNonnullByDefault
    public HeavyWaterRefiningMachine(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    @Nonnull
    public ItemStack getProgressBar() {
        return new ItemStack(Material.WATER_BUCKET);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(240, new ItemStack(Material.WATER_BUCKET), UGItems.HEAVY_WATER_BUCKET);
    }
}
