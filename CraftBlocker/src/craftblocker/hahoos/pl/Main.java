package craftblocker.hahoos.pl;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.command.ConsoleCommandSender;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import org.bukkit.plugin.java.JavaPlugin;


import craftblocker.hahoos.pl.API.CraftCancel;





public class Main extends JavaPlugin implements Listener {
	
  File logs;	
  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
  LocalDateTime now = LocalDateTime.now();  
  
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
	  console.sendMessage(ChatColor.BLUE + "Version: 1.0.beta");
	  console.sendMessage(ChatColor.BLUE + "-------------");
	  logs = new File(getDataFolder(), getConfig().getString("logs.name") + ".txt");
	  if(!logs.exists()) {
		try {
			logs.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  }
	  
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

@EventHandler
  public void onCraftEvent(CraftItemEvent event) throws IOException {
	 
	 
	  String item = event.getRecipe().getResult().getType().toString().toUpperCase();
	 

	
    getLogger().info(event.getRecipe().getResult().getType().toString());
	if(getConfig().getBoolean("crafting.everything.enabled") == true) {  
      Player p = (Player)event.getWhoClicked();
      if (!(p.hasPermission("craftblocker.bypass.*") || p.hasPermission("craftblocker.bypass." + event.getRecipe().getResult().getType()))) {
       event.setCancelled(true);
	   Bukkit.getPluginManager().callEvent(new CraftCancel((Player)event.getWhoClicked(), event.getCurrentItem()));
	   if(getConfig().getBoolean("logs.enabled") == true) {
		 LogToFile(event.getWhoClicked().getName() + " attempted to craft " + item, true);
	   }
       if(getConfig().getBoolean("prefix-enabled") == true) {p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")) + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));}else{p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));}
      }
	}else if(getConfig().getBoolean("crafting.certain-item.enabled")) {
	  Player p = (Player)event.getWhoClicked();
	  if(!(p.hasPermission("craftblocker.bypass.*") || p.hasPermission("craftblocker.bypass." + event.getRecipe().getResult().getType()))) {
	      if(getConfig().getList("crafting.certain-item.items").contains(item)) {	
			   event.setCancelled(true);
			   Bukkit.getPluginManager().callEvent(new CraftCancel((Player)event.getWhoClicked(), event.getCurrentItem()));
			   if(getConfig().getBoolean("logs.enabled") == true) {
				   LogToFile(event.getWhoClicked().getName() + " attempted to craft " + item, true);
			   }
			   
		       if(getConfig().getBoolean("prefix-enabled") == true) {p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")) + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));}else{p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));}  
		  
	    }
	  }
	}
   }

   public void LogToFile(String msg, boolean date) throws IOException {
	   PrintWriter pw = new PrintWriter(new FileWriter(logs, true));
	   if(date == true) {
	       pw.println("[" + dtf.format(now) + "] " + msg);
 
	   }else {
		   pw.println(msg); 
	   }
       pw.flush();
       pw.close();
   }
  
  
	  
  }
 

