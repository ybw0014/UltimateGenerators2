package net.guizhanss.ultimategenerators2.implementation.items.generators.modular.ender_crystal;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemState;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

import net.guizhanss.ultimategenerators2.UltimateGenerators2;
import net.guizhanss.ultimategenerators2.utils.EnergyNetUtils;
import net.guizhanss.ultimategenerators2.utils.LocationUtils;
import net.guizhanss.ultimategenerators2.utils.RandomUtils;

import lombok.Getter;

public class EnderCrystalStabilizer extends SimpleSlimefunItem<BlockTicker> implements EnergyNetComponent {

    private static final Map<BlockPosition, Integer> FAILED_TIMES = new HashMap<>();

    private int energyCapacity = -1;
    @Getter
    private int energyConsumption = -1;

    @ParametersAreNonnullByDefault
    public EnderCrystalStabilizer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return energyCapacity;
    }

    @Nonnull
    public final EnderCrystalStabilizer setCapacity(int capacity) {
        Preconditions.checkArgument(capacity > 0, "Capacity must be greater than 0");

        if (getState() == ItemState.UNREGISTERED) {
            this.energyCapacity = capacity;
            return this;
        } else {
            throw new IllegalStateException("You cannot modify the capacity after the Item was registered.");
        }
    }

    @Nonnull
    public final EnderCrystalStabilizer setEnergyConsumption(int energyConsumption) {
        Preconditions.checkArgument(energyConsumption > 0, "Energy consumption must be greater than 0");

        if (getState() == ItemState.UNREGISTERED) {
            this.energyConsumption = energyConsumption;
            return this;
        } else {
            throw new IllegalStateException("You cannot modify the energy consumption after the Item was registered.");
        }
    }

    @Nonnull
    @Override
    public BlockTicker getItemHandler() {
        return new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                EnderCrystalStabilizer.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        };
    }

    @Override
    public void register(@Nonnull SlimefunAddon addon) {
        Preconditions.checkArgument(energyCapacity > 0, "Energy capacity must be greater than 0");
        Preconditions.checkArgument(energyConsumption > 0, "Energy consumption must be greater than 0");
        Preconditions.checkArgument(energyCapacity >= energyConsumption, "Energy capacity must be greater than or equal to energy consumption");
        super.register(addon);
    }

    private void tick(@Nonnull Block b) {
        var pos = new BlockPosition(b);
        if (!EnergyNetUtils.takeCharge(this, b.getLocation(), getEnergyConsumption())) {
            FAILED_TIMES.putIfAbsent(pos, 0);
            FAILED_TIMES.put(pos, FAILED_TIMES.get(pos) + 1);

            if (FAILED_TIMES.get(pos) >= 5) {
                UltimateGenerators2.getScheduler().run(() -> {
                    FAILED_TIMES.remove(pos);
                    BlockStorage.clearBlockInfo(b);
                    b.setType(Material.AIR);
                    b.getWorld().createExplosion(LocationUtils.getBlockCenter(b.getLocation()), 0F, false);
                    if (RandomUtils.nextBoolean()) {
                        b.setType(Material.LAVA);
                    }
                    if (RandomUtils.nextBoolean()) {
                        b.getWorld().createExplosion(LocationUtils.getBlockCenter(b.getLocation()), RandomUtils.nextFloat(1F, 8F), RandomUtils.nextBoolean());
                    }
                });
            }
        }
    }
}
