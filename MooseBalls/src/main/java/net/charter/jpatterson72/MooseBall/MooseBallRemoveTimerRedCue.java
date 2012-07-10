package net.charter.jpatterson72.MooseBall;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MooseBallRemoveTimerRedCue {

	public static void MooseBallRemoveTimerRedCue(final String playerName, Plugin myPlugin) {
		final int timerID = myPlugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(myPlugin, new Runnable() {

					public void run() {
						
						MooseBallHandler.redTeamcue.remove(playerName);
					}
				}, 5);
		

		return;
	}
}
