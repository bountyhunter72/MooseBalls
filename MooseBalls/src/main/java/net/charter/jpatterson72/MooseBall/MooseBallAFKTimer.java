package net.charter.jpatterson72.MooseBall;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;



public class MooseBallAFKTimer {
	public static void MooseBallAFKTimer(final Plugin myPlugin) {
		final int timerID = myPlugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(myPlugin, new Runnable() {

					public void run() {

						for (int i = 0; i < MooseBallHandler.redTeamMatch.size(); i++) {
							String playerName = (MooseBallHandler.redTeamMatch.get(i));

							if ((myPlugin.getServer().getPlayer(playerName)) != null)

							{
								Player player = myPlugin.getServer().getPlayer(playerName);
				                if(player.hasMetadata("MooseBallAFK"))
				                
				                {
				                	MooseBallHandler.teamLeave(player); 

				                player.sendMessage(ChatColor.RED + "Eliminated From Match: No Movement");
				                
				                player.removeMetadata("MooseBallAFK", myPlugin);

				        		}
				                

				            }
				         

						}
						for (int i = 0; i < MooseBallHandler.blueTeamMatch.size(); i++) {
							String playerName = (MooseBallHandler.blueTeamMatch.get(i));

							if ((myPlugin.getServer().getPlayer(playerName)) != null)

							{
								Player player = myPlugin.getServer().getPlayer(playerName);
				                if(player.hasMetadata("MooseBallAFK"))
				                {
				                	MooseBallHandler.teamLeave(player); 

				                player.sendMessage(ChatColor.RED + "Eliminated From Match: No Movement");
				                
				                player.removeMetadata("MooseBallAFK", myPlugin);

				        		}
				                

				            }
				         

						}
					}
					
					
					
				}, 300);
		

		return;
	}
}


