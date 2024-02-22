package net.guizhanss.ultimategenerators2.core.recipes;

import org.bukkit.inventory.ItemStack;

/**
 * A {@link MachineRecipe} but only has one input and one output.
 */
public class SingleMachineRecipe extends SingleOutputMachineRecipe {
    public SingleMachineRecipe(int ticks, ItemStack input, ItemStack output) {
        super(ticks, new ItemStack[] {input}, output);
    }
}
