package net.guizhanss.ultimategenerators2.core.recipes;

import org.bukkit.inventory.ItemStack;

/**
 * A {@link MachineRecipe} but only has one output.
 */
public class SingleOutputMachineRecipe extends MachineRecipe {
    public SingleOutputMachineRecipe(int ticks, ItemStack[] input, ItemStack output) {
        super(ticks, input, new ItemStack[] {output});
    }
}
