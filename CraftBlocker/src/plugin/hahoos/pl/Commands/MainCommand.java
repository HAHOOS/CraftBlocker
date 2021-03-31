package plugin.hahoos.pl.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_8_R1.CommandExecute;
import plugin.hahoos.pl.Main;

public class MainCommand extends CommandExecute implements CommandExecutor {
    JavaPlugin plugin = Main.getPlugin(Main.class);    

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args[0].equalsIgnoreCase("enable")) {
			if(plugin.getConfig().getBoolean("enabled") == true) {
				sender.sendMessage(ChatColor.RED + "CraftBlocker is already Enabled!");

			}else {
				plugin.getConfig().set("enabled", true);
				sender.sendMessage(ChatColor.GREEN + "Successfully enabled CraftBlocker!");
				plugin.saveConfig();
				ConsoleCommandSender console = plugin.getServer().getConsoleSender();
				console.sendMessage("");
				console.sendMessage(ChatColor.GREEN + "###########  ############");                                       
				console.sendMessage(ChatColor.GREEN + "#            #          #");
				console.sendMessage(ChatColor.GREEN + "#            #          #");
				console.sendMessage(ChatColor.GREEN + "#            ###########");
				console.sendMessage(ChatColor.GREEN + "#            #          #");
				console.sendMessage(ChatColor.GREEN + "#            #          #");
				console.sendMessage(ChatColor.GREEN + "###########  ############");                                    
				console.sendMessage("");
				console.sendMessage(ChatColor.GREEN + "CraftBlocker was enabled");
			}
				
			
			
		}
		if(args[0].equalsIgnoreCase("disable")) {
			if(plugin.getConfig().getBoolean("enabled") == false) {
				sender.sendMessage(ChatColor.RED + "CraftBlocker is already Disabled!");

			}else {
			plugin.getConfig().set("enabled", false);
			sender.sendMessage(ChatColor.GREEN + "Successfully disabled CraftBlocker!");
			plugin.saveConfig();
			ConsoleCommandSender console = plugin.getServer().getConsoleSender();
			console.sendMessage("");
			console.sendMessage(ChatColor.RED + "###########  ############");                                       
			console.sendMessage(ChatColor.RED + "#            #          #");
			console.sendMessage(ChatColor.RED + "#            #          #");
			console.sendMessage(ChatColor.RED + "#            ###########");
			console.sendMessage(ChatColor.RED + "#            #          #");
			console.sendMessage(ChatColor.RED + "#            #          #");
			console.sendMessage(ChatColor.RED + "###########  ############");                                    
			console.sendMessage("");
			console.sendMessage(ChatColor.RED + "CraftBlocker was disabled");
			}
		}
		return false;
	}

}
