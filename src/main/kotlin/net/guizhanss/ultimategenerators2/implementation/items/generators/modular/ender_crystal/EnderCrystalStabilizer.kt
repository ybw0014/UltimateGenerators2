@file:Suppress("DEPRECATION")

package net.guizhanss.ultimategenerators2.implementation.items.generators.modular.ender_crystal

import com.google.common.base.Preconditions
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup
import io.github.thebusybiscuit.slimefun4.api.items.ItemState
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker
import me.mrCookieSlime.Slimefun.api.BlockStorage
import net.guizhanss.ultimategenerators2.UltimateGenerators2
import net.guizhanss.ultimategenerators2.utils.BlockUtils
import net.guizhanss.ultimategenerators2.utils.RandomUtils
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack

class EnderCrystalStabilizer(
    itemGroup: ItemGroup,
    item: SlimefunItemStack,
    recipeType: RecipeType,
    recipe: Array<ItemStack?>
) : SimpleSlimefunItem<BlockTicker>(itemGroup, item, recipeType, recipe), EnergyNetComponent {
    companion object {
        private val FAILED_TIMES: MutableMap<BlockPosition, Int> = HashMap()
    }

    private var energyCapacity: Int = -1

    var energyConsumption = -1
        private set

    override fun getCapacity() = energyCapacity

    override fun getEnergyComponentType() = EnergyNetComponentType.CONSUMER

    fun setCapacity(capacity: Int): EnderCrystalStabilizer {
        Preconditions.checkArgument(capacity > 0, "Capacity must be greater than 0")

        if (state == ItemState.UNREGISTERED) {
            this.energyCapacity = capacity
            return this
        } else {
            error("You cannot modify the capacity after the Item was registered.")
        }
    }

    fun setEnergyConsumption(energyConsumption: Int): EnderCrystalStabilizer {
        Preconditions.checkArgument(energyConsumption > 0, "Energy consumption must be greater than 0")

        if (state == ItemState.UNREGISTERED) {
            this.energyConsumption = energyConsumption
            return this
        } else {
            error("You cannot modify the energy consumption after the Item was registered.")
        }
    }

    override fun getItemHandler() = object : BlockTicker() {
        override fun tick(b: Block, sf: SlimefunItem, data: Config) {
            this@EnderCrystalStabilizer.tick(b)
        }

        override fun isSynchronized() = false
    }

    override fun register(addon: SlimefunAddon) {
        Preconditions.checkArgument(capacity > 0, "Energy capacity must be greater than 0")
        Preconditions.checkArgument(energyConsumption > 0, "Energy consumption must be greater than 0")
        Preconditions.checkArgument(
            capacity >= energyConsumption,
            "Energy capacity must be greater than or equal to energy consumption"
        )
        super.register(addon)
    }

    private fun tick(b: Block) {
        val pos = BlockPosition(b)
        if (!BlockUtils.takeCharge(this, b.location, energyConsumption)) {
            FAILED_TIMES[pos] = FAILED_TIMES.getOrPut(pos) { 0 } + 1

            if (FAILED_TIMES.getValue(pos) >= 5) {
                UltimateGenerators2.scheduler().run {
                    FAILED_TIMES.remove(pos)
                    BlockStorage.clearBlockInfo(b)
                    b.type = Material.AIR
                    b.world.createExplosion(BlockUtils.getBlockCenter(b), 0f, false)
                    if (RandomUtils.nextBoolean()) {
                        b.type = Material.LAVA
                    }
                    if (RandomUtils.nextBoolean()) {
                        b.world.createExplosion(
                            BlockUtils.getBlockCenter(b),
                            RandomUtils.nextFloat(1f, 8f),
                            RandomUtils.nextBoolean()
                        )
                    }
                }
            }
        }
    }
}
