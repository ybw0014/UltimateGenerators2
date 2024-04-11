package net.guizhanss.ultimategenerators2.implementation.items.generators.simple

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems
import net.guizhanss.ultimategenerators2.implementation.items.abstracts.AGenerator
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ReactionGenerator(
    itemGroup: ItemGroup,
    item: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<ItemStack?>
) : AGenerator(itemGroup, item, recipeType, recipe) {
    override val progressBar = ItemStack(Material.FLINT_AND_STEEL)

    override fun registerDefaultFuelTypes() {
        registerFuel(6, SlimefunItems.TINY_URANIUM)
        registerFuel(54, SlimefunItems.SMALL_URANIUM)
        registerFuel(216, SlimefunItems.URANIUM)
    }
}
