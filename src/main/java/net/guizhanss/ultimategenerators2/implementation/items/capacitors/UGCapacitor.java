package net.guizhanss.ultimategenerators2.implementation.items.capacitors;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.Capacitor;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public class UGCapacitor extends Capacitor {

    @ParametersAreNonnullByDefault
    public UGCapacitor(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int capacity) {
        super(itemGroup, capacity, item, recipeType, recipe);
    }

    @ParametersAreNonnullByDefault
    public UGCapacitor(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int capacity, int craftAmount) {
        super(itemGroup, capacity, item, recipeType, recipe);
        setRecipeOutput(new CustomItemStack(item, craftAmount));
    }
}
