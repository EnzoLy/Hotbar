package example;

import me.enzol.hotbar.HotbarListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ExampleMain extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new HotbarListener(), this);
        Bukkit.getPluginManager().registerEvents(new ExampleListeners(), this);
    }
}