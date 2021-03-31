package plugin.hahoos.pl.API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerTryCraft extends Event implements Cancellable {
    Player p;
    ItemStack i;
    boolean cancelled;
    
	
	public PlayerTryCraft(Player p, ItemStack i) {
		this.p = p;
		this.i = i;
	}
	
	public Player getPlayer() {
		return p;
		
	}
	
	public ItemStack getItem() {
		return i;
	}
	public void setCancelled(boolean bool) {
		this.cancelled = bool;
	}
	
	
	
	private static final HandlerList handlers = new HandlerList();
	
	public HandlerList getHandlers() {
		
		return handlers;
	}

	
	public static HandlerList getHandlerList() {
		
		return handlers;
	}
    
	public void onCraftEvent(CraftItemEvent event) {
	      Bukkit.getPluginManager().callEvent(new PlayerTryCraft((Player)event.getWhoClicked(), event.getCurrentItem()));
	      while(cancelled == false) {
	    	  
	      }
	  }

	@Override
	public boolean isCancelled() {
		
		return this.cancelled;
	}
}
