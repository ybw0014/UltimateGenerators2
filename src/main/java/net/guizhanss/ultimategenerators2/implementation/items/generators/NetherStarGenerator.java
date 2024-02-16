package net.guizhanss.ultimategenerators2.implementation.items.generators;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import net.guizhanss.ultimategenerators2.implementation.items.abstracts.AGenerator;

public class NetherStarGenerator extends AGenerator {
    @ParametersAreNonnullByDefault
    public NetherStarGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    @Nonnull
    public ItemStack getProgressBar() {
        return new ItemStack(Material.WITHER_SKELETON_SKULL);
    }

    @Override
    protected void registerDefaultFuelTypes() {
        registerFuel(240, new ItemStack(Material.NETHER_STAR));
    }
}
