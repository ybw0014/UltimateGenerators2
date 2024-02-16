package net.guizhanss.ultimategenerators2.implementation.items.machines;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

import net.guizhanss.ultimategenerators2.implementation.UGItems;
import net.guizhanss.ultimategenerators2.implementation.items.abstracts.AMachine;

public class BiomassExtractionMachine extends AMachine {
    private static final Set<Material> LEAVES = Tag.LEAVES.getValues();

    @ParametersAreNonnullByDefault
    public BiomassExtractionMachine(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    @Nonnull
    public ItemStack getProgressBar() {
        return new ItemStack(Material.SLIME_BALL);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(8, new ItemStack(Material.SLIME_BALL, 2));
        registerRecipe(8, new ItemStack(Material.NETHER_WART_BLOCK, 2));
        LEAVES.forEach(material -> registerRecipe(16, new ItemStack(material, 32)));
        registerRecipe(16, new ItemStack(Material.WHEAT_SEEDS, 16));
        registerRecipe(16, new ItemStack(Material.BEETROOT_SEEDS, 16));
        registerRecipe(16, new ItemStack(Material.MELON_SEEDS, 16));
        registerRecipe(16, new ItemStack(Material.PUMPKIN_SEEDS, 16));
    }

    private void registerRecipe(int ticks, @Nonnull ItemStack input) {
        registerRecipe(ticks, new ItemStack[] {input, new ItemStack(Material.BUCKET)}, UGItems.BIOMASS_BUCKET);
    }
}
