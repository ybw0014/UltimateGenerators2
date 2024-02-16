package net.guizhanss.ultimategenerators2.implementation.items.abstracts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Preconditions;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemState;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

import net.guizhanss.ultimategenerators2.api.recipes.MachineRecipe;
import net.guizhanss.ultimategenerators2.api.recipes.SingleMachineRecipe;
import net.guizhanss.ultimategenerators2.api.recipes.SingleOutputMachineRecipe;

import lombok.Getter;

public abstract class AMachine extends AHopper implements EnergyNetComponent, MachineProcessHolder<CraftingOperation> {
    protected final List<MachineRecipe> recipes = new ArrayList<>();
    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);

    @Getter
    private int energyCapacity = -1;
    @Getter
    private int energyConsumption = -1;

    @ParametersAreNonnullByDefault
    protected AMachine(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        processor.setProgressBar(getProgressBar());
    }

    public abstract ItemStack getProgressBar();

    @Override
    @Nonnull
    public final MachineProcessor<CraftingOperation> getMachineProcessor() {
        return processor;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onBreak(BlockBreakEvent e, BlockMenu menu) {
        super.onBreak(e, menu);
        processor.endOperation(menu.getLocation());
    }

    @Nonnull
    @Override
    public final EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return energyCapacity;
    }

    @Nonnull
    public final AMachine setCapacity(int capacity) {
        Preconditions.checkArgument(capacity > 0, "Capacity must be greater than 0");

        if (getState() == ItemState.UNREGISTERED) {
            this.energyCapacity = capacity;
            return this;
        } else {
            throw new IllegalStateException("You cannot modify the capacity after the Item was registered.");
        }
    }

    @Nonnull
    public final AMachine setEnergyConsumption(int energyConsumption) {
        Preconditions.checkArgument(energyConsumption > 0, "Energy consumption must be greater than 0");

        if (getState() == ItemState.UNREGISTERED) {
            this.energyConsumption = energyConsumption;
            return this;
        } else {
            throw new IllegalStateException("You cannot modify the energy consumption after the Item was registered.");
        }
    }

    protected abstract void registerDefaultRecipes();

    @Nonnull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> list = new ArrayList<>();
        for (MachineRecipe recipe : recipes) {
            int length = Math.max(recipe.getInput().length, recipe.getOutput().length);
            ItemStack[] inputs = Arrays.copyOf(recipe.getInput(), length);
            ItemStack[] outputs = Arrays.copyOf(recipe.getOutput(), length);
            for (int i = 0; i < length; i++) {
                list.add(inputs[i]);
                list.add(outputs[i]);
            }
        }
        return list;
    }

    @ParametersAreNonnullByDefault
    protected void registerRecipe(int ticks, ItemStack[] inputs, ItemStack[] outputs) {
        recipes.add(new MachineRecipe(ticks, inputs, outputs));
    }

    @ParametersAreNonnullByDefault
    protected void registerRecipe(int ticks, ItemStack[] inputs, ItemStack outputs) {
        recipes.add(new SingleOutputMachineRecipe(ticks, inputs, outputs));
    }

    @ParametersAreNonnullByDefault
    protected void registerRecipe(int ticks, ItemStack input, ItemStack output) {
        recipes.add(new SingleMachineRecipe(ticks, input, output));
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void tick(Block b, BlockMenu menu) {
        BlockMenu inv = BlockStorage.getInventory(b);
        CraftingOperation currentOperation = processor.getOperation(b);

        if (currentOperation != null) {
            if (takeCharge(b.getLocation())) {
                if (!currentOperation.isFinished()) {
                    processor.updateProgressBar(inv, STATUS_SLOT, currentOperation);
                    currentOperation.addProgress(1);
                } else {
                    inv.replaceExistingItem(STATUS_SLOT, NOT_WORKING_ITEM);

                    for (ItemStack output : currentOperation.getResults()) {
                        inv.pushItem(output.clone(), getOutputSlots());
                    }

                    processor.endOperation(b);
                }
            }
        } else {
            MachineRecipe next = findNextRecipe(inv);

            if (next != null) {
                processor.startOperation(b, new CraftingOperation(next.toSf()));
            }
        }
    }

    protected boolean takeCharge(@Nonnull Location l) {
        Preconditions.checkNotNull(l, "Location cannot be null");

        if (isChargeable()) {
            int charge = getCharge(l);

            if (charge < getEnergyConsumption()) {
                return false;
            }

            setCharge(l, charge - getEnergyConsumption());
        }
        return true;
    }

    @Nullable
    protected MachineRecipe findNextRecipe(@Nonnull BlockMenu inv) {
        Map<Integer, ItemStack> inventory = new HashMap<>();

        for (int slot : getInputSlots()) {
            ItemStack item = inv.getItemInSlot(slot);

            if (item != null) {
                inventory.put(slot, ItemStackWrapper.wrap(item));
            }
        }

        Map<Integer, Integer> found = new HashMap<>();

        for (MachineRecipe recipe : recipes) {
            for (ItemStack input : recipe.getInput()) {
                for (int slot : getInputSlots()) {
                    if (SlimefunUtils.isItemSimilar(inventory.get(slot), input, false)) {
                        found.put(slot, input.getAmount());
                        break;
                    }
                }
            }

            if (found.size() == recipe.getInput().length) {
                if (!InvUtils.fitAll(inv.toInventory(), recipe.getOutput(), getOutputSlots())) {
                    return null;
                }

                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    inv.consumeItem(entry.getKey(), entry.getValue());
                }

                return recipe;
            } else {
                found.clear();
            }
        }

        return null;
    }

    @Override
    public void register(@Nonnull SlimefunAddon addon) {
        Preconditions.checkArgument(energyCapacity > 0, "Energy capacity must be greater than 0");
        Preconditions.checkArgument(energyConsumption > 0, "Energy consumption must be greater than 0");
        Preconditions.checkArgument(energyConsumption <= energyCapacity, "Energy consumption must be less than or equal to the energy capacity");
        super.register(addon);
        registerDefaultRecipes();
    }
}
