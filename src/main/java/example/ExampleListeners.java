package example;

import me.enzol.hotbar.Hotbar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Examples of how to use Hotbars#class
 */
public class ExampleListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        spawn(player);
    }

    public void spawn(Player player){
        Hotbar.SPAWN.giveItems(player);
        player.teleport(player.getWorld().getSpawnLocation());
    }

    public void spectate(Player player){
        Hotbar.SPECTATOR.giveItems(player);
        player.teleport(player.getWorld().getSpawnLocation());
    }


}