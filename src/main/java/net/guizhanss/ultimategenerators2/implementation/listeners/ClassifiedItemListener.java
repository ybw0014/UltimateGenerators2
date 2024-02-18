package net.guizhanss.ultimategenerators2.implementation.listeners;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.FireworkUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

import net.guizhanss.ultimategenerators2.UltimateGenerators2;

import net.guizhanss.ultimategenerators2.implementation.UGItems;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ClassifiedItemListener implements Listener {
    public ClassifiedItemListener(@Nonnull UltimateGenerators2 plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void kapa(@Nonnull EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.LAVA && e.getEntity() instanceof Item itemEntity) {
            ItemStack item = itemEntity.getItemStack();
            if (item.getAmount() == 8 && SlimefunUtils.isItemSimilar(item, UGItems.LAMBDA_ELECTRICITY_STORAGE, false)) {
                e.setCancelled(true);
                Location l = getBlockCenter(itemEntity.getLocation());
                itemEntity.remove();

                generateItem(l, UGItems.KAPA_ELECTRICITY_STORAGE, new Color[] { Color.BLACK, Color.BLACK, Color.GRAY, Color.SILVER });
            }
        }
    }

    @EventHandler
    public void phi(@Nonnull EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING && e.getEntity() instanceof Item itemEntity) {
            ItemStack item = itemEntity.getItemStack();
            if (item.getAmount() == 8 && SlimefunUtils.isItemSimilar(item, UGItems.KAPA_ELECTRICITY_STORAGE, false)) {
                e.setCancelled(true);
                Location l = getBlockCenter(itemEntity.getLocation());
                itemEntity.remove();

                generateItem(l, UGItems.PHI_ELECTRICITY_STORAGE, new Color[] { Color.WHITE, Color.WHITE, Color.GRAY, Color.SILVER });
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void generateItem(Location l, ItemStack item, Color[] fireworkColors) {
        l.getWorld().playSound(l, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0F, 1.0F);
        Item newItem = l.getWorld().dropItemNaturally(l, item);
        l.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, l, 3);
        for (Color color : fireworkColors) {
            FireworkUtils.launchFirework(l, color);
        }
        newItem.setGlowing(true);
        newItem.setInvulnerable(true);
        newItem.setCustomNameVisible(true);
        newItem.setCustomName(item.getItemMeta().getDisplayName());
    }

    @Nonnull
    private Location getBlockCenter(@Nonnull Location loc) {
        return new Location(loc.getWorld(), loc.getBlockX() + 0.5, loc.getBlockY() + 0.5, loc.getBlockZ() + 0.5);
    }
}
