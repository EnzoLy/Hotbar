package me.enzol.hotbar;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class HotbarListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        Hotbar hotbar = Hotbar.getHotbar(player.getItemInHand());

        if(hotbar == null) return;

        if (hotbar.getFilter() != null && !hotbar.getFilter().test(player)) return;

        HotbarItem item = hotbar.getHotbarItem(player.getItemInHand());

        if(item == null || !item.getActions().contains(event.getAction())) return;

        item.onInteract(player);
    }

}
