package net.charter.jpatterson72.MooseBall;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MooseBallRemoveTimerBlue {

	public static void MooseBallRemoveTimerBlue(final Player player, Plugin myPlugin) {
		final int timerID = myPlugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(myPlugin, new Runnable() {

					public void run() {
						String playerName = player.getName();
						MooseBallHandler.blueTeamcue.remove(playerName);
					}
				}, 3);
		

		return;
	}
}
