package net.charter.jpatterson72.MooseBall;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MooseBallStartTimer {

	public static int matchStartTimer(final Plugin myPlugin) {
		final int timerID = myPlugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(myPlugin, new Runnable() {

					public void run() {
						if((MooseBallHandler.blueTeamcue.size() + MooseBallHandler.blueTeamcue.size()) > 1)
						{
						MooseBallHandler.loadBlueTeam();
						MooseBallHandler.loadRedTeam();
						MooseBallAFKTimer.MooseBallAFKTimer(myPlugin);
						}
						else{
							myPlugin.getServer().broadcastMessage(ChatColor.AQUA + "[MooseBall] " + ChatColor.RED + "Match Cancelled: Not Enough Players!");
							MooseBallHandler.matchInit = false;
							Player[] playerlist = myPlugin.getServer().getOnlinePlayers();
					        int listsize = myPlugin.getServer().getOnlinePlayers().length;
					            for(int i = 0; i < listsize; i++) {
					                Player replayer = playerlist[i];
					                if(replayer.hasMetadata("MooseBall"))
					                {	
										
										MooseBallHandler.clearMooseBall(replayer);
										MooseBallHandler.lobbySpawn(replayer);
										MooseBallHandler.deColorize(replayer);
										MooseBallHandler.eleminatePlayer(replayer);
										MooseBallHandler.checkWin();		
					                }

					            }
						}
					}
				}, 600);
		

		return timerID;
	}
}