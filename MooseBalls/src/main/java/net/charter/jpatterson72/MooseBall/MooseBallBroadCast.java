package net.charter.jpatterson72.MooseBall;

import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MooseBallBroadCast {
	
	public static MooseBall plugin;
	public MooseBallBroadCast (MooseBall instance) {

		plugin = instance;
	}

	Logger log;


	public static void MooseBallBraodCast(String victim, String killer) {
		String killString = "";
		Random rand = new Random();
		int randMessage = rand.nextInt(6);
		int redTeamSize = MooseBallHandler.redTeamMatch.size();
		int blueTeamSize = MooseBallHandler.blueTeamMatch.size();

		if ((randMessage == 0) || (randMessage == 5)) {
			killString = (ChatColor.GOLD + victim + ChatColor.AQUA
					+ " was eliminated by " + ChatColor.GOLD + killer
					+ ChatColor.GREEN + " One Point Awarded!");
		}
		if ((randMessage == 1) || (randMessage == 4)) {
			killString = (ChatColor.GOLD + victim + ChatColor.AQUA
					+ " got MooseBalled by " + ChatColor.GOLD + killer
					+ ChatColor.GREEN + " One Point Awarded!");
		}
		if ((randMessage == 2) || (randMessage == 3)) {
			killString = (ChatColor.GOLD + killer + ChatColor.AQUA + " sent "
					+ ChatColor.GOLD + victim + ChatColor.AQUA
					+ " back to the Lobby" + ChatColor.GREEN + " One Point Awarded!");
		}

		String currentmatch = (ChatColor.RED + "Red Team: " + redTeamSize
				+ " Players Remaining  " + ChatColor.BLUE + "Blue Team: "
				+ blueTeamSize + " Players Remaining  ");
		for (int i = 0; i < MooseBallHandler.redTeamcue.size(); i++) {
			String playerName = (MooseBallHandler.redTeamcue.get(i));
			if ((plugin.getServer().getPlayer(playerName)) != null)

			{

				Player messagePlayer = plugin.getServer().getPlayer(playerName);

				messagePlayer.sendMessage(killString);
				messagePlayer.sendMessage(currentmatch);
			}
		}
		for (int i = 0; i < MooseBallHandler.blueTeamcue.size(); i++) {
			String playerName = (MooseBallHandler.blueTeamcue.get(i));
			if ((plugin.getServer().getPlayer(playerName)) != null)

			{

				Player messagePlayer = plugin.getServer().getPlayer(playerName);

				messagePlayer.sendMessage(killString);
				messagePlayer.sendMessage(currentmatch);
			}
		}
		for (int i = 0; i < MooseBallHandler.blueTeamMatch.size(); i++) {
			String playerName = (MooseBallHandler.blueTeamMatch.get(i));
			if ((plugin.getServer().getPlayer(playerName)) != null)

			{

				Player messagePlayer = plugin.getServer().getPlayer(playerName);

				messagePlayer.sendMessage(killString);
				messagePlayer.sendMessage(currentmatch);
			}
		}
		for (int i = 0; i < MooseBallHandler.redTeamMatch.size(); i++) {
			String playerName = (MooseBallHandler.redTeamMatch.get(i));
			if ((plugin.getServer().getPlayer(playerName)) != null)

			{

				Player messagePlayer = plugin.getServer().getPlayer(playerName);

				messagePlayer.sendMessage(killString);
				messagePlayer.sendMessage(currentmatch);
			}
		}
		for (int i = 0; i < MooseBallHandler.redTeamEleminated.size(); i++) {
			String playerName = (MooseBallHandler.redTeamEleminated.get(i));
			if ((plugin.getServer().getPlayer(playerName)) != null)

			{

				Player messagePlayer = plugin.getServer().getPlayer(playerName);

				messagePlayer.sendMessage(killString);
				messagePlayer.sendMessage(currentmatch);
			}
		}
		for (int i = 0; i < MooseBallHandler.blueTeamEleminated.size(); i++) {
			String playerName = (MooseBallHandler.blueTeamEleminated.get(i));
			if ((plugin.getServer().getPlayer(playerName)) != null)

			{

				Player messagePlayer = plugin.getServer().getPlayer(playerName);

				messagePlayer.sendMessage(killString);
				messagePlayer.sendMessage(currentmatch);
			}
		}
		
		
	}
	
}
