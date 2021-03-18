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

        Hotbar hotbar = Hotbar.getHotbars().stream()
                .filter(it -> it.getFilter().test(player))
                .findFirst().orElse(null);

        if(hotbar == null) return;

        HotbarItem item = hotbar.getItem(player.getItemInHand());

        if(item == null || !item.getActions().contains(event.getAction())) return;

        item.onInteract(player);
    }

}
