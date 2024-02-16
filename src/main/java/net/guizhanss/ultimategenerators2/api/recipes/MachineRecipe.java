package net.guizhanss.ultimategenerators2.api.recipes;

import javax.annotation.Nonnull;

import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents a recipe for a machine.
 */
@RequiredArgsConstructor
@Getter
public class MachineRecipe {
    private final int ticks;
    private final ItemStack[] input;
    private final ItemStack[] output;

    @Nonnull
    public me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe toSf() {
        // it is very annoying the constructor accepts seconds and the tick rate is hard-coded.
        var recipe = new me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe(ticks, input, output);
        recipe.setTicks(ticks);
        return recipe;
    }
}
