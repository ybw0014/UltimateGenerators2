package net.guizhanss.ultimategenerators2.implementation.items.misc

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack

class FakePotionItem(
    itemGroup: ItemGroup,
    item: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<ItemStack?>
) : SimpleSlimefunItem<ItemConsumptionHandler>(itemGroup, item, recipeType, recipe) {
    override fun getItemHandler(): ItemConsumptionHandler {
        return ItemConsumptionHandler { e: PlayerItemConsumeEvent, _: Player, _: ItemStack ->
            e.isCancelled = true
        }
    }
}
