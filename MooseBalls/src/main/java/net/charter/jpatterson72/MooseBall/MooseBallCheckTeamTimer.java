package net.charter.jpatterson72.MooseBall;

import org.bukkit.plugin.Plugin;

public class MooseBallCheckTeamTimer {


	public static void MooseBallCheckTeamTimer(final Plugin myPlugin) {
		final int timerID = myPlugin.getServer().getScheduler()
				.scheduleSyncRepeatingTask(myPlugin, new Runnable() {

					public void run() {

MooseBallHandler.checkTeams();
						
						
					}
				}, 0, 20);
		

		return;
	}
}
