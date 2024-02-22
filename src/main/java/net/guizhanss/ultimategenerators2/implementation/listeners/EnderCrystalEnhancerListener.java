package net.guizhanss.ultimategenerators2.implementation.listeners;

import javax.annotation.Nonnull;

import org.bukkit.entity.EnderCrystal;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

import net.guizhanss.ultimategenerators2.UltimateGenerators2;
import net.guizhanss.ultimategenerators2.implementation.UGItems;
import net.guizhanss.ultimategenerators2.utils.Keys;

public final class EnderCrystalEnhancerListener implements Listener {
    public EnderCrystalEnhancerListener(@Nonnull UltimateGenerators2 plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onUse(@Nonnull PlayerInteractEntityEvent e) {
        if (!(e.getRightClicked() instanceof EnderCrystal crystal)) {
            return;
        }

        EquipmentSlot slot = e.getHand();
        ItemStack item = e.getPlayer().getInventory().getItem(slot);
        if (!SlimefunUtils.isItemSimilar(item, UGItems.ENDER_CRYSTAL_ENHANCER, false)) {
            return;
        }

        if (!crystal.isShowingBottom() && !crystal.hasMetadata(Keys.ENHANCED_ENDER_CRYSTAL)) {
            crystal.setMetadata(Keys.ENHANCED_ENDER_CRYSTAL, new FixedMetadataValue(UltimateGenerators2.getInstance(), true));
            crystal.setGlowing(true);
            ItemUtils.consumeItem(item, true);
        }
    }
}
