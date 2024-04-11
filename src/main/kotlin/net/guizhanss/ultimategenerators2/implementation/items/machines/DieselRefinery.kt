package net.guizhanss.ultimategenerators2.implementation.items.machines

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import net.guizhanss.ultimategenerators2.implementation.UGItems
import net.guizhanss.ultimategenerators2.implementation.items.abstracts.AMachine
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class DieselRefinery(
    itemGroup: ItemGroup,
    item: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<ItemStack?>
) : AMachine(itemGroup, item, recipeType, recipe) {
    override val progressBar = ItemStack(Material.FLINT_AND_STEEL)

    override fun registerDefaultRecipes() {
        registerRecipe(80, SlimefunItems.OIL_BUCKET, UGItems.DIESEL_BUCKET)
    }
}
