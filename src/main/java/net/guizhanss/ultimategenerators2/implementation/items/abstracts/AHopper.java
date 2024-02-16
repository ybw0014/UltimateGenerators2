package net.guizhanss.ultimategenerators2.implementation.items.abstracts;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

import net.guizhanss.guizhanlib.slimefun.machines.TickingMenuBlock;

/**
 * Just because the menu looks like a hopper.
 */
public abstract class AHopper extends TickingMenuBlock implements InventoryBlock, RecipeDisplayItem {
    // slots
    protected static final int[] BACKGROUND_SLOTS = new int[] {18, 19, 20, 21, 23, 24, 25, 26};
    protected static final int[] INPUT_BORDER = new int[] {0, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    protected static final int[] OUTPUT_BORDER = new int[] {27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 44};
    protected static final int[] INPUT_SLOTS = new int[] {1, 2, 3, 4, 5, 6, 7};
    protected static final int[] OUTPUT_SLOTS = new int[] {37, 38, 39, 40, 41, 42, 43};
    protected static final int STATUS_SLOT = 22;

    // items
    protected static final ItemStack NOT_WORKING_ITEM = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

    @ParametersAreNonnullByDefault
    protected AHopper(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void setup(@Nonnull BlockMenuPreset preset) {
        for (int slot : BACKGROUND_SLOTS) {
            preset.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }
        for (int slot : INPUT_BORDER) {
            preset.addItem(slot, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }
        for (int slot : OUTPUT_BORDER) {
            preset.addItem(slot, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }
        preset.addItem(STATUS_SLOT, NOT_WORKING_ITEM, ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    public int[] getInputSlots() {
        return INPUT_SLOTS;
    }

    @Override
    public int[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }
}
