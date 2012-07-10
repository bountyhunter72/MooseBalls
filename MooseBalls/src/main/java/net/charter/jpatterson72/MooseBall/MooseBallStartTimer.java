package net.charter.jpatterson72.MooseBall;

import org.bukkit.ChatColor;
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
						}
					}
				}, 600);
		

		return timerID;
	}
}