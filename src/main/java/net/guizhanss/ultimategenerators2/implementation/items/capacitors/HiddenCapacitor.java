package net.guizhanss.ultimategenerators2.implementation.items.capacitors;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class HiddenCapacitor extends UGCapacitor {
    @ParametersAreNonnullByDefault
    public HiddenCapacitor(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int capacity) {
        super(itemGroup, item, recipeType, recipe, capacity);
    }

    @ParametersAreNonnullByDefault
    public HiddenCapacitor(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int capacity, int craftAmount) {
        super(itemGroup, item, recipeType, recipe, capacity, craftAmount);
    }

    @Override
    public void postRegister() {
        setHidden(true);
    }
}
