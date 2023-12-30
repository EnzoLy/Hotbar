package me.enzol.hotbar;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.Predicate;

@Getter
public class Hotbar {

    private static final Hotbar SPAWN = new Hotbar(player -> player.hasMetadata("spawn"),
        new HotbarItem(0, new ItemStack(Material.COMPASS)).addInteract(player -> player.sendMessage("test")),
        new HotbarItem(1, new ItemStack(Material.WATCH)).addInteract(player -> player.sendMessage("Hi"))
    );

    private static final Hotbar SPECTATOR = new Hotbar(player -> player.hasMetadata("spectator"),
        new HotbarItem(0, new ItemStack(Material.COMPASS)).addInteract(player -> player.sendMessage("test"))
    );

    private static final Map<Integer, Hotbar> HOTBAR_TYPES = Maps.newHashMap();

    private Predicate<Player> filter;
    private List<HotbarItem> items;

    /**
     * Constructor for hotbar
     * @param filter you can change Player for another object (I recommend using states, like: Profile#getState)
     * @param items Item for give to player
     */
    Hotbar(Predicate<Player> filter, HotbarItem... items) {
        this.filter = filter;
        this.items = Arrays.asList(items);

        this.items.forEach(hotbarItem -> {
            ItemStack itemStack = hotbarItem.getItem();
            HOTBAR_TYPES.put(Objects.hash(itemStack.getItemMeta(), itemStack.getItemMeta().getLore()), this);
        });
    }

    public void giveItems(Player player) {

        player.getInventory().setArmorContents(new ItemStack[4]);
        player.getInventory().setContents(new ItemStack[36]);
        player.getInventory().setHeldItemSlot(0);

        items.forEach(hotbarItem -> {
            ItemStack itemStack = hotbarItem.getItem();
            Integer slot = hotbarItem.getSlot();
            if(hotbarItem.getFilter() != null){
                if(hotbarItem.getFilter().test(player)) player.getInventory().setItem(slot, itemStack);
            }else player.getInventory().setItem(slot, itemStack);
        });

        player.updateInventory();
    }

    /**
     * Get all hotbars
     * @return values of enum converted in ArrayList
     */
    public static List<Hotbar> getHotbars(){
        return Arrays.asList(SPAWN, SPECTATOR);
    }

    public HotbarItem getHotbarItem(ItemStack itemStack) {
        return HOTBAR_TYPES.get(Objects.hash(itemStack.getItemMeta(), itemStack.getItemMeta().getLore())).getItems().stream().filter(hotbarItem -> hotbarItem.getItem().equals(itemStack)).findFirst().orElse(null);
    }

    public static Hotbar getHotbar(ItemStack itemStack) {
        return HOTBAR_TYPES.get(Objects.hash(itemStack.getItemMeta(), itemStack.getItemMeta().getLore()));
    }

}