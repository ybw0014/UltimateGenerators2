@file:Suppress("DEPRECATION")

package net.guizhanss.ultimategenerators2.implementation.items.generators.simple

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
import org.bukkit.Location
import org.bukkit.inventory.ItemStack

class EndlessGenerator(
    itemGroup: ItemGroup,
    item: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<ItemStack?>
) : SlimefunItem(itemGroup, item, recipeType, recipe), EnergyNetProvider {
    override fun getGeneratedOutput(location: Location, config: Config) = location.block.blockPower * 256

    override fun getCapacity() = 256 * 15
}
