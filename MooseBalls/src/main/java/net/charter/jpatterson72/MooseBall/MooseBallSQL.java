package net.charter.jpatterson72.MooseBall;


	

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.text.SimpleDateFormat;
	import java.util.Calendar;
	import java.util.logging.Level;
	import java.util.logging.Logger;

	import org.bukkit.OfflinePlayer;
	import org.bukkit.entity.Player;
	import org.bukkit.metadata.FixedMetadataValue;
	import org.bukkit.plugin.Plugin;

	public class MooseBallSQL  {
		public static String shorturl = MooseBallConfig.mySQL_Host;
		public static String user = MooseBallConfig.mySQL_User;
		public static String password = MooseBallConfig.mySQL_Pass;
		public static String url = MooseBallConfig.mySQL_Host + "pvpplayers";
		public static MooseBall plugin;
		
		
		public static void SavePlayer(Player player) {
		
			
			Connection con = null;
			PreparedStatement pst = null;
			
			String MooseBallPlayer = player.getName();



			int MooseBalls = player.getMetadata("MooseBalls").get(0).asInt();
			String  armor = player.getMetadata("MooseBallArmor").get(0).asString();

			try {
				

				con = DriverManager.getConnection(url, user, password);
				pst = con.prepareStatement("DELETE FROM mooseballers WHERE MooseBallPlayer=?");
				pst.setString(1, MooseBallPlayer);
				pst.executeUpdate();

				pst = con.prepareStatement("INSERT INTO mooseballers(MooseBallPlayer, MooseBalls, armor) VALUES(?,?,?)");
				pst.setString(1, MooseBallPlayer);
				pst.setInt(2, MooseBalls);
				pst.setString(3, armor);
				
				pst.executeUpdate();

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(MooseBallSQL.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);

			} finally {

				try {
					if (pst != null) {
						pst.close();
					}
					if (con != null) {
						con.close();
					}

				} catch (SQLException ex) {
					Logger lgr = Logger.getLogger(MooseBallSQL.class.getName());
					lgr.log(Level.SEVERE, ex.getMessage(), ex);
				}
			}
		}

		public static void LoadPlayer(Player newPlayer, Plugin plugin) {

			Connection con = null;
			PreparedStatement pst = null;
			String OSIname = newPlayer.getName();

			try {
				con = DriverManager.getConnection(url, user, password);
				String query = "SELECT * FROM mooseballers Where MooseBallPlayer = ?";
				pst = con.prepareStatement(query);
				pst.setString(1, OSIname);
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					newPlayer.setMetadata("MooseBalls", new FixedMetadataValue(
							plugin, rs.getInt("MooseBalls")));

					newPlayer.setMetadata("MooseBallArmor", new FixedMetadataValue(
							plugin, rs.getString("armor")));
}



			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(MooseBallSQL.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);

			} finally {

				try {
					if (pst != null) {
						pst.close();
					}
					if (con != null) {
						con.close();
					}

				} catch (SQLException ex) {
					Logger lgr = Logger.getLogger(MooseBallSQL.class.getName());
					lgr.log(Level.SEVERE, ex.getMessage(), ex);
				}
			}
		}

		public static int playerInSQL(Player newPlayer) {
			Connection con = null;
			PreparedStatement pst = null;
			String OSIname = newPlayer.getName();

			Statement stmt = null;
			ResultSet rs = null;
			int rowCount = -1;

			try {
				con = DriverManager.getConnection(url, user, password);
				String query = "SELECT COUNT(*) FROM mooseballers WHERE MooseBallPlayer=?";
				pst = con.prepareStatement(query);
				pst.setString(1, OSIname);
				rs = pst.executeQuery();
				// get the number of rows from the result set
				rs.next();
				rowCount = rs.getInt(1);
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(MooseBallSQL.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);

			} finally {

				try {
					if (stmt != null) {
						stmt.close();
					}
					if (con != null) {
						con.close();
					}

				} catch (SQLException ex) {
					Logger lgr = Logger.getLogger(MooseBallSQL.class.getName());
					lgr.log(Level.SEVERE, ex.getMessage(), ex);
				}
			}

			return rowCount;
		}

		public static void dbExists() {
			Connection con = null;
			PreparedStatement pst = null;

			try {
				con = DriverManager.getConnection(shorturl, user, password);
				pst = con
						.prepareStatement("CREATE DATABASE IF NOT EXISTS pvpplayers");
				pst.executeUpdate();
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(MooseBallSQL.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);

			} finally {

				try {
					if (pst != null) {
						pst.close();
					}
					if (con != null) {
						con.close();
					}

				} catch (SQLException ex) {
					Logger lgr = Logger.getLogger(MooseBallSQL.class.getName());
					lgr.log(Level.SEVERE, ex.getMessage(), ex);
				}
			}
			tableExists();
		
		}

		public static void tableExists() {
			Connection con = null;
			PreparedStatement pst = null;

			try {
				con = DriverManager.getConnection(url, user, password);
				pst = con
						.prepareStatement("CREATE TABLE IF NOT EXISTS mooseballers (MooseBallPlayer VARCHAR(20), MooseBalls VARCHAR(10), armor VARCHAR(10))");
				pst.executeUpdate();
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(MooseBallSQL.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);

			} finally {

				try {
					if (pst != null) {
						pst.close();
					}
					if (con != null) {
						con.close();
					}

				} catch (SQLException ex) {
					Logger lgr = Logger.getLogger(MooseBallSQL.class.getName());
					lgr.log(Level.SEVERE, ex.getMessage(), ex);
				}
			}

			
		}

		

}
