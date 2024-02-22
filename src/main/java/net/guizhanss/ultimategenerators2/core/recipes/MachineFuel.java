package net.guizhanss.ultimategenerators2.core.recipes;

import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper;

import lombok.Getter;

@Getter
public class MachineFuel implements Predicate<ItemStack> {
    private final int ticks;
    private final ItemStack input;
    private final ItemStack output;
    private final ItemStackWrapper wrapper;

    public MachineFuel(int ticks, @Nonnull ItemStack input, @Nullable ItemStack output) {
        this.ticks = ticks;
        this.input = input;
        this.output = output;
        this.wrapper = ItemStackWrapper.wrap(input);
    }

    @Override
    public boolean test(@Nullable ItemStack item) {
        return SlimefunUtils.isItemSimilar(item, wrapper, true);
    }

    @Nonnull
    public me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel toSf() {
        // it is very annoying the constructor accepts seconds and the tick rate is hard-coded.
        return new me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel(ticks / 2, input, output);
    }
}
