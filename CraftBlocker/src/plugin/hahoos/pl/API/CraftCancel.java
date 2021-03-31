package plugin.hahoos.pl.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class CraftCancel extends Event {

private static final HandlerList handlers = new HandlerList();
    Player p;
    ItemStack i;

    public CraftCancel(Player p, ItemStack i) {
    	this.p = p;
    	this.i = i;
    }
    
    public Player getPlayer() {
		return p;
		
	}
    
    public ItemStack getItem() {
		return i;
		
	}
    
	public HandlerList getHandlers() {
		
		return handlers;
	}

	
	public static HandlerList getHandlerList() {
		
		return handlers;
	}

}
