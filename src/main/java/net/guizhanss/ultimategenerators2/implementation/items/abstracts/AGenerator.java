package net.guizhanss.ultimategenerators2.implementation.items.abstracts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemState;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.operations.FuelOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.NumberUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

import net.guizhanss.guizhanlib.minecraft.utils.ChatUtil;
import net.guizhanss.ultimategenerators2.api.recipes.MachineFuel;
import net.guizhanss.ultimategenerators2.implementation.UGItems;

import lombok.Getter;

public abstract class AGenerator extends AHopper implements EnergyNetProvider, MachineProcessHolder<FuelOperation> {

    protected final Set<MachineFuel> fuelTypes = new HashSet<>();
    private final MachineProcessor<FuelOperation> processor = new MachineProcessor<>(this);

    @Getter
    private int energyCapacity = -1;
    @Getter
    private int energyProduction = -1;

    @ParametersAreNonnullByDefault
    protected AGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    public abstract ItemStack getProgressBar();

    @Override
    @Nonnull
    public MachineProcessor<FuelOperation> getMachineProcessor() {
        return processor;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onBreak(BlockBreakEvent e, BlockMenu menu) {
        super.onBreak(e, menu);
        processor.endOperation(menu.getLocation());
    }

    protected abstract void registerDefaultFuelTypes();


    protected void registerFuel(int ticks, @Nonnull ItemStack fuel) {
        registerFuel(ticks, fuel, null);
    }

    protected void registerFuel(int ticks, @Nonnull ItemStack fuel, @Nullable ItemStack output) {
        fuelTypes.add(new MachineFuel(ticks, fuel, output));
    }

    @Override
    @Nonnull
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> list = new ArrayList<>();
        for (MachineFuel fuel : fuelTypes) {
            ItemStack item = new CustomItemStack(
                fuel.getInput().clone(),
                im -> {
                    List<String> lore = new ArrayList<>(List.of(
                        "&8\u21E8 &7\u23F2 " + NumberUtils.getTimeLeft(fuel.getTicks() / 2),
                        "&8\u21E8 &e\u26A1 &7" + getEnergyProduction() + " J/t",
                        "&8\u21E8 &e\u26A1 &7" + NumberUtils.getCompactDouble((double) fuel.getTicks() * getEnergyProduction()) + " J in total"
                    ));
                    im.setLore(ChatUtil.color(lore));
                }
            );
            list.add(item);
        }
        return list;
    }

    @Override
    public int getCapacity() {
        return energyCapacity;
    }

    public final AGenerator setCapacity(int capacity) {
        Preconditions.checkArgument(capacity > 0, "Capacity must be greater than 0");

        if (getState() == ItemState.UNREGISTERED) {
            this.energyCapacity = capacity;
            return this;
        } else {
            throw new IllegalStateException("You cannot modify the capacity after the Item was registered.");
        }
    }

    public final AGenerator setEnergyProduction(int energyProduction) {
        Preconditions.checkArgument(energyProduction > 0, "Energy production must be greater than 0");

        if (getState() == ItemState.UNREGISTERED) {
            this.energyProduction = energyProduction;
            return this;
        } else {
            throw new IllegalStateException("You cannot modify the energy production after the Item was registered.");
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public int getGeneratedOutput(Location l, Config data) {
        BlockMenu inv = BlockStorage.getInventory(l);
        FuelOperation operation = processor.getOperation(l);

        if (operation != null) {
            if (!operation.isFinished()) {
                processor.updateProgressBar(inv, STATUS_SLOT, operation);

                if (isChargeable()) {
                    int charge = getCharge(l, data);

                    if (getCapacity() - charge >= getEnergyProduction()) {
                        operation.addProgress(1);
                        return getEnergyProduction();
                    }

                    return 0;
                } else {
                    operation.addProgress(1);
                    return getEnergyProduction();
                }
            } else {
                ItemStack fuel = operation.getIngredient();

                if (isBucket(fuel)) {
                    inv.pushItem(new ItemStack(Material.BUCKET), getOutputSlots());
                }

                inv.replaceExistingItem(STATUS_SLOT, NOT_WORKING_ITEM);

                processor.endOperation(l);
                return 0;
            }
        } else {
            Map<Integer, Integer> found = new HashMap<>();
            MachineFuel fuel = findRecipe(inv, found);

            if (fuel != null) {
                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    inv.consumeItem(entry.getKey(), entry.getValue());
                }

                processor.startOperation(l, new FuelOperation(fuel.toSf()));
            }

            return 0;
        }
    }

    private boolean isBucket(@Nullable ItemStack item) {
        if (item == null) {
            return false;
        }

        ItemStackWrapper wrapper = ItemStackWrapper.wrap(item);
        return item.getType() == Material.LAVA_BUCKET ||
            SlimefunUtils.isItemSimilar(wrapper, SlimefunItems.FUEL_BUCKET, false)
            || SlimefunUtils.isItemSimilar(wrapper, SlimefunItems.OIL_BUCKET, false)
            || SlimefunUtils.isItemSimilar(wrapper, UGItems.BIOFUEL_BUCKET, false)
            || SlimefunUtils.isItemSimilar(wrapper, UGItems.BIOMASS_BUCKET, false)
            || SlimefunUtils.isItemSimilar(wrapper, UGItems.DIESEL_BUCKET, false);
    }

    @Nullable
    @ParametersAreNonnullByDefault
    private MachineFuel findRecipe(BlockMenu menu, Map<Integer, Integer> found) {
        for (MachineFuel fuel : fuelTypes) {
            for (int slot : getInputSlots()) {
                if (fuel.test(menu.getItemInSlot(slot))) {
                    found.put(slot, fuel.getInput().getAmount());
                    return fuel;
                }
            }
        }

        return null;
    }

    @Override
    public void register(@Nonnull SlimefunAddon addon) {
        Preconditions.checkArgument(energyCapacity > 0, "Energy capacity must be greater than 0");
        Preconditions.checkArgument(energyProduction > 0, "Energy production must be greater than 0");
        Preconditions.checkArgument(energyCapacity >= energyProduction, "Energy capacity must be greater than or equal to energy production");
        super.register(addon);
        registerDefaultFuelTypes();
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block block, BlockMenu menu) {
        // generator ticker is handled in EnergyNet, no need to do anything here.
    }
}
