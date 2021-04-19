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
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import org.bukkit.plugin.java.JavaPlugin;

import craftblocker.hahoos.pl.API.CraftCancel;
import craftblocker.hahoos.pl.PlaceholderAPI.Attempts_Placeholder;
import me.clip.placeholderapi.PlaceholderAPI;








public class Main extends JavaPlugin implements Listener {
  	
  File logs;
  File data = new File(getDataFolder(), "data.yml");
  File logsDirectory = new File(getDataFolder() + "/logs");

  
  
  FileConfiguration dataConfig = YamlConfiguration.loadConfiguration(data);
  String log_date;
  DateTimeFormatter dtf_logs_content = DateTimeFormatter.ofPattern(getConfig().getString("logs.content.format")); 
  DateTimeFormatter dtf_logs_name = DateTimeFormatter.ofPattern(getConfig().getString("logs.date-format"));
  LocalDateTime now = LocalDateTime.now();  
  
  @SuppressWarnings("deprecation")
public void onEnable() {
      dataConfig.options().copyDefaults(true);

	  ConsoleCommandSender console = getServer().getConsoleSender();
	  if(!data.exists()) {
		  console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aCreating file &a&ldata.yml"));  
			try {
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully created file data.yml!"));  
				data.createNewFile();
			} catch (IOException e) {
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cError occured while creating file data.yml"));
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "Error: " + e.toString()));  
				
			}
	  }

	  getServer().getPluginManager().registerEvents(this, this);
	  if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
	    if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
          
          PlaceholderAPI.registerPlaceholderHook(this, new Attempts_Placeholder());
        }
	  }
	 
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
	  logs = new File(getDataFolder(), getConfig().getString("logs.name") + "-" + now.format(dtf_logs_name) + ".txt");
	  logsDirectory.mkdir();
	 
	  
	  Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	       public void run() {
	    	now = LocalDateTime.now();   
	       if(!logs.exists()) {	    
	 		  //if(logs.getName().replace("log-", "").replace(".txt", "") != now.format(dtf_logs_name)) {
	    	  if(log_date != dtf_logs_name.format(now)) { 
	 			 log_date = dtf_logs_name.format(now);
	     		  logs = new File(getDataFolder() + "/logs/" + getConfig().getString("logs.name") + "-" + now.format(dtf_logs_name) + ".txt");
	               
	     	      try {
	 				logs.createNewFile();
	 			} catch (IOException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	        }else if(logs.exists()) {
	     	   
	        }
	       }else {
	    	   log_date = dtf_logs_name.format(now);
	    	   logs = new File(getDataFolder() + "/logs/" + getConfig().getString("logs.name") + "-" + now.format(dtf_logs_name) + ".txt");
               
	     	      try {
	 				logs.createNewFile();
	 			} catch (IOException e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace(); 
	       }
	       }
	       }
	  }, 10L, 10L); // Always multiply by twenty because that's the amount of ticks in Minecraft
	  
	   
	  
	  
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
       /*
       if(!(dataConfig.contains("attempts." + p.toString()))) {
		   dataConfig.addDefault("attempts." + p.toString(), (0 + 1));
	   }else {
		   dataConfig.set("attempts." + p.toString(), (dataConfig.getInt("attempts." + p.toString()) + 1));
	   }
       dataConfig.save(data);
       */
	   if(getConfig().getBoolean("logs.enabled") == true) {
		 LogToLogs(event.getWhoClicked().getName() + " attempted to craft " + item, true);
	   }
       if(getConfig().getBoolean("prefix-enabled") == true) {p.sendMessage(PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")) + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message"))));}else{p.sendMessage(PlaceholderAPI.setPlaceholders(p, ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message"))));}
      }
	}else if(getConfig().getBoolean("crafting.certain-item.enabled")) {
	  Player p = (Player)event.getWhoClicked();
	  if(!(p.hasPermission("craftblocker.bypass.*") || p.hasPermission("craftblocker.bypass." + event.getRecipe().getResult().getType()))) {
	      if(getConfig().getList("crafting.certain-item.items").contains(item)) {	
			   event.setCancelled(true);
			   /*
			   if(!(dataConfig.contains("attempts." + p.toString()))) {
				   dataConfig.addDefault("attempts." + p.toString(), (0 + 1));
			   }else {
				   dataConfig.set("attempts." + p.toString(), (dataConfig.getInt("attempts." + p.toString()) + 1));
			   }
			   
		        dataConfig.save(data);
		        */
               Bukkit.getPluginManager().callEvent(new CraftCancel(p, event.getRecipe().getResult()));
			   
			   if(getConfig().getBoolean("logs.enabled") == true) {
				   LogToLogs(event.getWhoClicked().getName() + " attempted to craft " + item, true);
			   }
			   
		       if(getConfig().getBoolean("prefix-enabled") == true) {p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("prefix")) + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));}else{p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message")));}  
		  
	    }
	  }
	}
   }

   public void LogToLogs(String msg, boolean date) throws IOException {
	   now = LocalDateTime.now();
	   PrintWriter pw = new PrintWriter(new FileWriter(logs, true));
	   if(date == true) {
	       pw.println("[" + dtf_logs_content.format(now) + "] " + msg);
 
	   }else {
		   pw.println(msg); 
	   }
       pw.flush();
       pw.close();
   }
   
   
  
	  
  }
 

