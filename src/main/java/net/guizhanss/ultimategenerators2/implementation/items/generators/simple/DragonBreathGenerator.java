package net.guizhanss.ultimategenerators2.implementation.items.generators.simple;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import net.guizhanss.ultimategenerators2.implementation.items.abstracts.AGenerator;

public class DragonBreathGenerator extends AGenerator {
    @ParametersAreNonnullByDefault
    public DragonBreathGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    @Nonnull
    public ItemStack getProgressBar() {
        return new ItemStack(Material.END_CRYSTAL);
    }

    @Override
    protected void registerDefaultFuelTypes() {
        registerFuel(60, new ItemStack(Material.DRAGON_BREATH));
    }
}
