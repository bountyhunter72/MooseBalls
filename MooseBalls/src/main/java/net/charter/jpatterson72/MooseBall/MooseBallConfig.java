package net.charter.jpatterson72.MooseBall;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;



public class MooseBallConfig {
	
	public static int restartDelay = 600;
	public static int minimumPlayers = 2;
	public static int maxPlayers = 20;
	public static String mySQL_Host = " ";
	public static String mySQL_DB = " ";
	public static String mySQL_User = " ";
	public static String mySQL_Pass = " ";
	public static Double spawnX;
	public static Double spawnY;
	public static Double spawnZ;
	public static float spawnYaw;
	public static double redX = 0.;
	public static double redZ = 0.0;
	public static double redY = 0.0;
	public static float redYaw = 0.0f;
	public static double blueX = 0.0;
	public static double blueZ = 0.0;
	public static double blueY = 0.0;
	public static float blueYaw = 0.0f;;
	
	public static MooseBall plugin;
	public MooseBallConfig(MooseBall instance) {
		plugin = instance;
	}

	public static void Load() {
MooseBallCheckTeamTimer.MooseBallCheckTeamTimer(plugin);
MooseBallBroadCastTimer.MooseBallBroadCastTimer(plugin);
		File file = new File(plugin._dataFolder + "config.yml");

		if (!file.exists()) {
			try {
				file.createNewFile();

				Save();
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			YamlConfiguration Config = YamlConfiguration.loadConfiguration(new File(plugin._dataFolder + "config.yml"));

			restartDelay = Config.getInt("restartDelay");
			minimumPlayers = Config.getInt("minimumPlayers");
			mySQL_User = Config.getString("mySQL_User");
			mySQL_Pass = Config.getString("mySQL_Pass");
			mySQL_Host = Config.getString("url");

		} catch (Exception e) {
			e.printStackTrace();
		}
		MooseBallCheckTeamTimer.MooseBallCheckTeamTimer(plugin);
	}

	public static void Save() {
		YamlConfiguration Config = new YamlConfiguration();

		Config.set("minimumPlayers", Integer.valueOf(minimumPlayers));
		Config.set("restartDelay", Integer.valueOf(restartDelay));
		Config.set("mySQL_User", String.valueOf(mySQL_User));
		Config.set("mySQL_Pass", String.valueOf(mySQL_Pass));

		try {
			Config.save(plugin._dataFolder + "config.yml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void LoadSpawn() {

		File file = new File(plugin._dataFolder + "spawn.yml");

		if (!file.exists()) {
			try {
			file.createNewFile();

				SaveSpawn();
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			YamlConfiguration Config = YamlConfiguration.loadConfiguration(new File(plugin._dataFolder + "spawn.yml"));

			spawnX = Config.getDouble("spawnX");
			spawnY = Config.getDouble("spawnY");
			spawnZ = Config.getDouble("spawnZ");
			spawnYaw = (float) Config.getDouble ("spawnYaw");
			
			redX = Config.getDouble("redX");
			redY = Config.getDouble("redY");
			redZ = Config.getDouble("redZ");
			redYaw = (float) Config.getDouble ("redYaw");
			
			blueX = Config.getDouble("blueX");
			blueY = Config.getDouble("blueY");
			blueZ = Config.getDouble("blueZ");
			blueYaw = (float) Config.getDouble ("blueYaw");
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void SaveSpawn() {
		YamlConfiguration Config = new YamlConfiguration();

		Config.set("spawnX", Double.valueOf(spawnX));
		Config.set("spawnY", Double.valueOf(spawnY));
		Config.set("spawnZ", Double.valueOf(spawnZ));
		Config.set("spawnYaw", Float.valueOf(spawnYaw));
		
		Config.set("redX", Double.valueOf(redX));
		Config.set("redY", Double.valueOf(redY));
		Config.set("redZ", Double.valueOf(redZ));
		Config.set("redYaw", Float.valueOf(redYaw));
		
		Config.set("blueX", Double.valueOf(blueX));
		Config.set("blueY", Double.valueOf(blueY));
		Config.set("blueZ", Double.valueOf(blueZ));
		Config.set("blueYaw", Float.valueOf(blueYaw));
		

		try {
			Config.save(plugin._dataFolder + "spawn.yml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
