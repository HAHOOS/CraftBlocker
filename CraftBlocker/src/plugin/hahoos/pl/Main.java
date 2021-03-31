package plugin.hahoos.pl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.command.ConsoleCommandSender;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;


import plugin.hahoos.pl.API.CraftCancel;

import plugin.hahoos.pl.Commands.MainCommand;

import org.bukkit.plugin.java.JavaPlugin;





public class Main extends JavaPlugin implements Listener {
  public void onEnable() {
	  getServer().getPluginManager().registerEvents(this, this);
	  ConsoleCommandSender console = getServer().getConsoleSender();
	  getCommand("craftblocker").setExecutor(new MainCommand());
	  saveDefaultConfig();
	  console.sendMessage("");
	  console.sendMessage(ChatColor.BLUE + "###########  ############");                                       
	  console.sendMessage(ChatColor.BLUE + "#            #          #");
	  console.sendMessage(ChatColor.BLUE + "#            #          #");
	  console.sendMessage(ChatColor.BLUE + "#            ###########");
	  console.sendMessage(ChatColor.BLUE + "#            #          #");
	  console.sendMessage(ChatColor.BLUE + "#            #          #");
	  console.sendMessage(ChatColor.BLUE + "###########  ############");                                    
	  console.sendMessage("");
	  console.sendMessage(ChatColor.BLUE + "-------------");
	  console.sendMessage(ChatColor.BLUE + "Name: CraftBlocker");
	  console.sendMessage(ChatColor.BLUE + "Author: HAHOOS");
	  console.sendMessage(ChatColor.BLUE + "Version: 1.0.beta");
	  console.sendMessage(ChatColor.BLUE + "-------------");
  }
  
  public void onDisable() {
	  
  }
 
  @EventHandler
  public void onCraftEvent(CraftItemEvent event) {
	if(getConfig().getBoolean("enabled") == true) {  
      Player p = (Player)event.getWhoClicked();
      if (!(p.hasPermission("craftblocker.bypass"))) {
       event.setCancelled(true);
	   Bukkit.getPluginManager().callEvent(new CraftCancel((Player)event.getWhoClicked(), event.getCurrentItem()));

       if(getConfig().getBoolean("prefix-enabled") == true) {p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")) + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));}else{p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));}
      }
	}
  }
 
}
