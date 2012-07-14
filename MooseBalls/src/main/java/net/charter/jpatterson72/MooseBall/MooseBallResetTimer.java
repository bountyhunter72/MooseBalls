package net.charter.jpatterson72.MooseBall;

import org.bukkit.plugin.Plugin;

public class MooseBallResetTimer {
	public static void MooseBallResetTimer(final Plugin myPlugin) {
		final int timerID = myPlugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(myPlugin, new Runnable() {

					public void run() {


						MooseBallHandler.redTeamMatch.clear();
						MooseBallHandler.blueTeamMatch.clear();
						MooseBallHandler.blueTeamEleminated.clear();
						MooseBallHandler.redTeamEleminated.clear();	
						MooseBallHandler.checkTeams();
						
					}
				}, 60);
		

		return;
	}

}
