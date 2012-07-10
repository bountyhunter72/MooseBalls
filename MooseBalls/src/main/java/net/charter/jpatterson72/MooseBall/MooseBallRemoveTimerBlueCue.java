package net.charter.jpatterson72.MooseBall;

import org.bukkit.plugin.Plugin;

public class MooseBallRemoveTimerBlueCue {

	public static void MooseBallRemoveTimerBlueCue(final String playerName, Plugin myPlugin) {
		final int timerID = myPlugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(myPlugin, new Runnable() {

					public void run() {
						
						MooseBallHandler.blueTeamcue.remove(playerName);
					}
				}, 1);
		

		return;
	}
}
