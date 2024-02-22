package net.guizhanss.ultimategenerators2.implementation.items.generators.modular.ender_crystal;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.operations.FuelOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

import net.guizhanss.ultimategenerators2.core.recipes.MachineFuel;
import net.guizhanss.ultimategenerators2.implementation.UGItems;
import net.guizhanss.ultimategenerators2.implementation.items.abstracts.AGenerator;
import net.guizhanss.ultimategenerators2.utils.Keys;
import net.guizhanss.ultimategenerators2.utils.LocationUtils;

public class EnderCrystalGenerator extends AGenerator {
    private static final Map<BlockPosition, Integer> CRYSTAL_COUNT = new HashMap<>();

    private static final int INFO_SLOT = 18;

    @ParametersAreNonnullByDefault
    protected EnderCrystalGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void setup(@Nonnull BlockMenuPreset preset) {
        super.setup(preset);
        preset.addItem(INFO_SLOT, NOT_WORKING_ITEM, ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.END_CRYSTAL);
    }

    @Override
    protected void registerDefaultFuelTypes() {
        registerFuel(7200, UGItems.RUNE_COMPLEX_ENDER, SlimefunItems.STONE_CHUNK);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onPlace(BlockPlaceEvent e, Block b) {
        super.onPlace(e, b);
        var loc = new BlockPosition(b);
        CRYSTAL_COUNT.put(loc, 0);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onBreak(BlockBreakEvent e, BlockMenu menu) {
        super.onBreak(e, menu);
        Location loc = menu.getLocation();
        CRYSTAL_COUNT.remove(new BlockPosition(loc));
        loc.getWorld().getNearbyEntities(loc, 4D, 4D, 4D, entity -> entity.getType() == EntityType.ENDER_CRYSTAL)
            .stream()
            .map(EnderCrystal.class::cast)
            .forEachOrdered(crystal -> {
                try {
                    if (!crystal.isShowingBottom()
                        && crystal.hasMetadata(Keys.ENHANCED_ENDER_CRYSTAL)
                        && (crystal.getBeamTarget().getBlock().getLocation().distance(LocationUtils.getBlockCenter(loc.add(0, -1, 0))) == 0D)) {
                        crystal.setBeamTarget(null);

                    }
                } catch (Exception ex) {
                    crystal.setBeamTarget(null);
                }
            });
    }

    public int getEnergyProduction(BlockPosition pos) {
        return super.getEnergyProduction() * CRYSTAL_COUNT.computeIfAbsent(pos, k -> 0);
    }

    @Override
    @ParametersAreNonnullByDefault
    public int getGeneratedOutput(Location l, Config data) {
        BlockMenu inv = BlockStorage.getInventory(l);
        BlockPosition pos = new BlockPosition(l);
        FuelOperation operation = processor.getOperation(l);

        // check for ender crystals
//        BlockPosition basePos = new BlockPosition(l.add(0, -1, 0));
//        AtomicInteger count = new AtomicInteger(0);
//        l.getWorld().getNearbyEntities(l, 4D, 4D, 4D, entity -> entity.getType() == EntityType.ENDER_CRYSTAL)
//            .stream()
//            .map(EnderCrystal.class::cast)
//            .forEachOrdered(crystal -> {
//                if (crystal.isShowingBottom() && crystal.getBeamTarget().getBlock().getLocation().distance(LocationUtils.getBlockCenter(basePos)) == 0D) {
//                    count.incrementAndGet();
//                    crystal.setBeamTarget(basePos.toLocation());
//                }
//            });
//        CRYSTAL_COUNT.put(pos, count.get());

        // check for fuel
        if (operation != null) {
            if (!operation.isFinished()) {
                processor.updateProgressBar(inv, STATUS_SLOT, operation);

                if (isChargeable()) {
                    int charge = getCharge(l, data);

                    if (getCapacity() - charge >= getEnergyProduction(pos)) {
                        operation.addProgress(1);
                        return getEnergyProduction(pos);
                    }

                    return 0;
                } else {
                    operation.addProgress(1);
                    return getEnergyProduction(pos);
                }
            } else {
                inv.replaceExistingItem(STATUS_SLOT, NOT_WORKING_ITEM);

                processor.endOperation(l);
                return 0;
            }
        } else if (CRYSTAL_COUNT.get(pos) > 0) {
            Map<Integer, Integer> found = new HashMap<>();
            MachineFuel fuel = findRecipe(inv, found);

            if (fuel != null) {
                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    inv.consumeItem(entry.getKey(), entry.getValue());
                }

                processor.startOperation(l, new FuelOperation(fuel.toSf()));
            }

            return 0;
        } else {
            return 0;
        }
    }
}
