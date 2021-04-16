package craftblocker.hahoos.pl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import craftblocker.hahoos.pl.API.CraftBlocker;
import craftblocker.hahoos.pl.API.CraftCancel;





public class Main extends JavaPlugin implements Listener {
  public void onEnable() {
	  getServer().getPluginManager().registerEvents(this, this);
	  ConsoleCommandSender console = getServer().getConsoleSender();
	  //getCommand("craftblocker").setExecutor(new MainCommand());
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
	  console.sendMessage(ChatColor.BLUE + "Version: 1.1");
	  console.sendMessage(ChatColor.BLUE + "-------------");
	  
	  
  }
  
  public void onDisable() {
	  saveConfig(); 
	 ConsoleCommandSender console = getServer().getConsoleSender();
	 try {
		 saveConfig();
		 console.sendMessage(ChatColor.GREEN + "Successfully saved config");
	 }catch(Exception e) {
		console.sendMessage(ChatColor.RED + "Error occured while saving config: " + ChatColor.BOLD + e.toString()); 
	 }
  }
 
  @SuppressWarnings("unchecked")
@EventHandler
  public void onCraftEvent(CraftItemEvent event) {
	  List<String> list = new ArrayList<>();
	  list.addAll((Collection<? extends String>) getConfig().getList("crafting.certain-item.items"));
	  String item = event.getRecipe().getResult().getType().toString().toUpperCase();
 
	ConfigurationSection sec = getConfig().getConfigurationSection("crafting.certain-item.items");
    getLogger().info(event.getRecipe().getResult().getType().toString());
	if(getConfig().getBoolean("crafting.everything.enabled") == true) {  
      Player p = (Player)event.getWhoClicked();
      if (!(p.hasPermission("craftblocker.bypass.*") || p.hasPermission("craftblocker.bypass." + event.getRecipe().getResult().getType()))) {
       event.setCancelled(true);
	   Bukkit.getPluginManager().callEvent(new CraftCancel((Player)event.getWhoClicked(), event.getCurrentItem()));

       if(getConfig().getBoolean("prefix-enabled") == true) {p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")) + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));}else{p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));}
      }
	}else if(getConfig().getBoolean("crafting.certain-item.enabled")) {
	  Player p = (Player)event.getWhoClicked();
	  if(!(p.hasPermission("craftblocker.bypass.*") || p.hasPermission("craftblocker.bypass." + event.getRecipe().getResult().getType()))) {
	    //if(getConfig().getList("crafting.certain-item.items").contains(event.getRecipe().getResult().getType().toString().toUpperCase())){
	      if(getConfig().getList("crafting.certain-item.items").contains(item)) {	
	    
		  //if(event.getRecipe().getResult().getType() == Material.valueOf(material.toUpperCase())) {
			   event.setCancelled(true);
			   Bukkit.getPluginManager().callEvent(new CraftCancel((Player)event.getWhoClicked(), event.getCurrentItem()));
			   
			   
		       if(getConfig().getBoolean("prefix-enabled") == true) {p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")) + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));}else{p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));}  
		  //}
	    }
	  }
	}
   }
  
  
	  
  }
 

