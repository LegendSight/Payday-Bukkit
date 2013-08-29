package delta.pd.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PD implements CommandExecutor {

	ChatColor g = ChatColor.DARK_GRAY;
	ChatColor b = ChatColor.BLUE;
	ChatColor r = ChatColor.RED;
	ChatColor u = ChatColor.UNDERLINE;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		Player p = (Player) sender;
		
		String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "" + ChatColor.DARK_GRAY + "] ";
		
		if(label.equalsIgnoreCase("pd") || label.equalsIgnoreCase("payday")) {

			if(args.length == 0) {
				
			p.sendMessage(g + "" + u + "-------------------- " + prefix + "--------------------");
			p.sendMessage(g + "Payday for MinecraftUniverse developed by " + r + "BenCS_ " + g + "and" );
			p.sendMessage(r + "LegendSight. " + g + "**CURRENTLY IN BETA!!!!**");
				
			}
			
			
		}
		
		return false;
	}

}
