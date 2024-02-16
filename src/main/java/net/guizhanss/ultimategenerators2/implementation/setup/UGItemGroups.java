package net.guizhanss.ultimategenerators2.implementation.setup;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;

import net.guizhanss.ultimategenerators2.UltimateGenerators2;
import net.guizhanss.ultimategenerators2.utils.Heads;
import net.guizhanss.ultimategenerators2.utils.Keys;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class UGItemGroups {
    public static final NestedItemGroup MAIN = new NestedItemGroup(
        Keys.ITEMGROUP_MAIN,
        UltimateGenerators2.getLocalization().getItem(
            "_UG2_MAIN",
            Heads.ENDLESS_GENERATOR.getTexture()
        )
    );

    public static final SubItemGroup MISC = new SubItemGroup(
        Keys.ITEMGROUP_MISC,
        MAIN,
        UltimateGenerators2.getLocalization().getItem(
            "_UG2_MISC",
            Material.COMPARATOR
        ),
        4
    );


    public static final SubItemGroup MACHINES = new SubItemGroup(
        Keys.ITEMGROUP_MACHINES,
        MAIN,
        UltimateGenerators2.getLocalization().getItem(
            "_UG2_MACHINES",
            Material.PAPER
        ),
        4
    );

    public static final SubItemGroup ELECTRICITY_STORAGE = new SubItemGroup(
        Keys.ITEMGROUP_ELECTRICITY_STORAGE,
        MAIN,
        UltimateGenerators2.getLocalization().getItem(
            "_UG2_ELECTRICITY_STORAGE",
            Material.LIGHT_GRAY_STAINED_GLASS
        ),
        4
    );

    public static final SubItemGroup SIMPLE_GENERATORS = new SubItemGroup(
        Keys.ITEMGROUP_SIMPLE_GENERATORS,
        MAIN,
        UltimateGenerators2.getLocalization().getItem(
            "_UG2_SIMPLE_GENERATORS",
            Material.TRIPWIRE_HOOK
        ),
        4
    );
}
