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
	public static double redX1;
	public static double redZ1;
	public static double redY1;
	public static float redYaw1 ;
	public static double blueX1 ;
	public static double blueZ1 ;
	public static double blueY1 ;
	public static float blueYaw1 ;
	public static double redX2 ;
	public static double redZ2 ;
	public static double redY2;
	public static float redYaw2 ;
	public static double blueX2 ;
	public static double blueZ2 ;
	public static double blueY2 ;
	public static float blueYaw2 ;	
	public static double redX3 ;
	public static double redZ3 ;
	public static double redY3 ;
	public static float redYaw3 ;
	public static double blueX3 = 0.0;
	public static double blueZ3 = 0.0;
	public static double blueY3 = 0.0;
	public static float blueYaw3 = 0.0f;
	public static double redX4 = 0.;
	public static double redZ4 = 0.0;
	public static double redY4 = 0.0;
	public static float redYaw4 = 0.0f;
	public static double blueX4 = 0.0;
	public static double blueZ4 = 0.0;
	public static double blueY4 = 0.0;
	public static float blueYaw4 = 0.0f;;
	public static boolean arena1 = true;
	public static boolean arena2 = false;
	public static boolean arena3 = false;
	public static boolean arena4 = false;
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
			
			redX1 = Config.getDouble("redX1");
			redY1 = Config.getDouble("redY1");
			redZ1 = Config.getDouble("redZ1");
			redYaw1 = (float) Config.getDouble ("redYaw1");
			
			blueX1 = Config.getDouble("blueX1");
			blueY1 = Config.getDouble("blueY1");
			blueZ1 = Config.getDouble("blueZ1");
			blueYaw1 = (float) Config.getDouble ("blueYaw1");
			
			redX2 = Config.getDouble("redX2");
			redY2 = Config.getDouble("redY2");
			redZ2 = Config.getDouble("redZ2");
			redYaw2 = (float) Config.getDouble ("redYaw2");
			
			blueX2 = Config.getDouble("blueX2");
			blueY2 = Config.getDouble("blueY2");
			blueZ2 = Config.getDouble("blueZ2");
			blueYaw2 = (float) Config.getDouble ("blueYaw2");
		
			redX3 = Config.getDouble("redX3");
			redY3 = Config.getDouble("redY3");
			redZ3 = Config.getDouble("redZ3");
			redYaw3 = (float) Config.getDouble ("redYaw3");
			
			blueX3 = Config.getDouble("blueX3");
			blueY3 = Config.getDouble("blueY3");
			blueZ3 = Config.getDouble("blueZ3");
			blueYaw3 = (float) Config.getDouble ("blueYaw3");
			
			redX4 = Config.getDouble("redX4");
			redY4 = Config.getDouble("redY4");
			redZ4 = Config.getDouble("redZ4");
			redYaw4 = (float) Config.getDouble ("redYaw4");
			
			blueX4 = Config.getDouble("blueX4");
			blueY4 = Config.getDouble("blueY4");
			blueZ4 = Config.getDouble("blueZ4");
			blueYaw4 = (float) Config.getDouble ("blueYaw4");
			
			arena1 = Config.getBoolean("arena1");
			arena2 = Config.getBoolean("arena2");
			arena3 = Config.getBoolean("arena3");
			arena4 = Config.getBoolean("arena4");
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
		
		Config.set("redX1", Double.valueOf(redX1));
		Config.set("redY1", Double.valueOf(redY1));
		Config.set("redZ1", Double.valueOf(redZ1));
		Config.set("redYaw1", Float.valueOf(redYaw1));
		
		Config.set("blueX1", Double.valueOf(blueX1));
		Config.set("blueY1", Double.valueOf(blueY1));
		Config.set("blueZ1", Double.valueOf(blueZ1));
		Config.set("blueYaw1", Float.valueOf(blueYaw1));
		
		Config.set("redX2", Double.valueOf(redX2));
		Config.set("redY2", Double.valueOf(redY2));
		Config.set("redZ2", Double.valueOf(redZ2));
		Config.set("redYaw2", Float.valueOf(redYaw2));
		
		Config.set("blueX2", Double.valueOf(blueX2));
		Config.set("blueY2", Double.valueOf(blueY2));
		Config.set("blueZ2", Double.valueOf(blueZ2));
		Config.set("blueYaw2", Float.valueOf(blueYaw2));
		
		Config.set("redX3", Double.valueOf(redX3));
		Config.set("redY3", Double.valueOf(redY3));
		Config.set("redZ3", Double.valueOf(redZ3));
		Config.set("redYaw3", Float.valueOf(redYaw3));
		
		Config.set("blueX3", Double.valueOf(blueX3));
		Config.set("blueY4", Double.valueOf(blueY3));
		Config.set("blueZ3", Double.valueOf(blueZ3));
		Config.set("blueYaw4", Float.valueOf(blueYaw3));
		
		Config.set("redX4", Double.valueOf(redX4));
		Config.set("redY4", Double.valueOf(redY4));
		Config.set("redZ4", Double.valueOf(redZ4));
		Config.set("redYaw4", Float.valueOf(redYaw4));
		
		Config.set("blueX4", Double.valueOf(blueX4));
		Config.set("blueY4", Double.valueOf(blueY4));
		Config.set("blueZ4", Double.valueOf(blueZ4));
		Config.set("blueYaw4", Float.valueOf(blueYaw4));
		
		Config.set("arena1", Boolean.valueOf(arena1));
		Config.set("arena2", Boolean.valueOf(arena2));
		Config.set("arena3", Boolean.valueOf(arena3));
		Config.set("arena4", Boolean.valueOf(arena4));
		
		try {
			Config.save(plugin._dataFolder + "spawn.yml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
