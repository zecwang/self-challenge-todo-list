package app.wangzc.challenge.models;

import app.wangzc.challenge.controllers.action.Termination;
import app.wangzc.challenge.controllers.conf.DatabaseConf;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 积分兑换模型
 *
 * @author WangZC
 */
public class AwardModel {

	private static int storage = 0;

	public static int getStorage() {
		return storage;
	}

	public static Object[][] getAward() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Object[][] objects = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM exchange");
			rset = stmt.executeQuery();
			rset.next();
			int count = rset.getInt(1);

			stmt = conn.prepareStatement("SELECT * FROM exchange ORDER BY value");
			rset = stmt.executeQuery();
			ResultSetMetaData data = rset.getMetaData();
			int rowCount = data.getColumnCount();

			objects = new Object[count][rowCount];
			int i = 0;

			while (rset.next()) {
				for (int j = 0; j < rowCount; j++) {
					objects[i][j] = rset.getObject(j + 1);
				}
				i++;
			}
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			Termination.loseDBConnection();
		} finally {
			try {
				DatabaseConf.release(rset, stmt, conn);
			} catch (NoClassDefFoundError e) {
				e.printStackTrace();
			}
		}

		return objects;
	}

	public static Object[][] queryAward() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Object[][] objects = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM award");
			rset = stmt.executeQuery();
			rset.next();
			int count = rset.getInt(1);

			stmt = conn.prepareStatement("SELECT * FROM award ORDER BY value");
			rset = stmt.executeQuery();
			ResultSetMetaData data = rset.getMetaData();
			int rowCount = data.getColumnCount();

			objects = new Object[count][rowCount];
			int i = 0;

			while (rset.next()) {
				for (int j = 0; j < rowCount; j++) {
					objects[i][j] = rset.getObject(j + 1);
				}
				i++;
			}
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			Termination.loseDBConnection();
		} finally {
			try {
				DatabaseConf.release(rset, stmt, conn);
			} catch (NoClassDefFoundError e) {
				e.printStackTrace();
			}
		}

		return objects;
	}

	public static Object[][] queryHaveAward() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Object[][] objects = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM award WHERE have>0");
			rset = stmt.executeQuery();
			rset.next();
			int count = rset.getInt(1);

			stmt = conn.prepareStatement("SELECT * FROM award WHERE have>0");
			rset = stmt.executeQuery();
			ResultSetMetaData data = rset.getMetaData();
			int rowCount = data.getColumnCount();

			objects = new Object[count][rowCount];
			int i = 0;

			storage = 0;
			while (rset.next()) {
				for (int j = 0; j < rowCount; j++) {
					objects[i][j] = rset.getObject(j + 1);
				}
				storage += rset.getInt("have");
				i++;
			}
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			Termination.loseDBConnection();
		} finally {
			try {
				DatabaseConf.release(rset, stmt, conn);
			} catch (NoClassDefFoundError e) {
				e.printStackTrace();
			}
		}

		return objects;
	}

	public static void exchange(int value, String item, int have) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DatabaseConf.getConnection();
			if (ScoreModel.queryHistory() >= value) {
				stmt = conn.prepareStatement("SELECT * FROM exchange WHERE item=?");
				stmt.setString(1, item);
				rset = stmt.executeQuery();
				if (rset.next()) {
					stmt = conn.prepareStatement("UPDATE award SET have=? WHERE item=?");

					stmt.setInt(1, have + 1);
					stmt.setString(2, item);
					if (stmt.executeUpdate() == 1) {
						ScoreModel.updateHistory(-value);
					}
				} else {
					JOptionPane.showMessageDialog(null, " 奖励已下架，无法兑换更多", "下架通知", JOptionPane.PLAIN_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, " 积分不足，无法兑换", "积分不足", JOptionPane.PLAIN_MESSAGE);
			}

		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DatabaseConf.release(rset, stmt, conn);
			} catch (NoClassDefFoundError e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteItem(String item) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("DELETE FROM exchange WHERE item=?");

			stmt.setString(1, item);
			stmt.executeUpdate();

			DatabaseHandle.doCleanAward(item);
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DatabaseConf.release(rset, stmt, conn);
			} catch (NoClassDefFoundError e) {
				e.printStackTrace();
			}
		}
	}

	public static void insertItem(int value, String item) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("INSERT INTO exchange (value, item) VALUES (?, ?)");

			stmt.setInt(1, value);
			stmt.setString(2, item);
			stmt.executeUpdate();

			stmt = conn.prepareStatement("INSERT INTO award (value, item) VALUES (?, ?)");

			stmt.setInt(1, value);
			stmt.setString(2, item);
			stmt.executeUpdate();
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DatabaseConf.release(rset, stmt, conn);
			} catch (NoClassDefFoundError e) {
				e.printStackTrace();
			}
		}
	}

	public static Object[][] queryUsing() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Object[][] objects = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM occupied");
			rset = stmt.executeQuery();
			rset.next();
			int count = rset.getInt(1);

			stmt = conn.prepareStatement("SELECT * FROM occupied");
			rset = stmt.executeQuery();
			ResultSetMetaData data = rset.getMetaData();
			int rowCount = data.getColumnCount();

			objects = new Object[count][rowCount];
			int i = 0;

			while (rset.next()) {
				for (int j = 0; j < rowCount; j++) {
					objects[i][j] = rset.getObject(j + 1);
				}
				i++;
			}
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DatabaseConf.release(rset, stmt, conn);
			} catch (NoClassDefFoundError e) {
				e.printStackTrace();
			}
		}

		return objects;
	}

	public static void insertUsing(String item, int have) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("INSERT INTO occupied (time, item) VALUES (?, ?)");

			stmt.setString(1, getCurrentTime());
			stmt.setString(2, item);

			if (stmt.executeUpdate() == 1)
				cost(item, have);
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (MySQLIntegrityConstraintViolationException e) {
		} catch (Exception e) {
			e.printStackTrace();
			Termination.loseDBConnection();
		} finally {
			try {
				DatabaseConf.release(rset, stmt, conn);
			} catch (NoClassDefFoundError e) {
				e.printStackTrace();
			}
		}
	}

	public static void cost(String item, int have) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("UPDATE award SET have=? WHERE item=?");

			stmt.setInt(1, have - 1);
			stmt.setString(2, item);
			stmt.executeUpdate();
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DatabaseConf.release(rset, stmt, conn);
			} catch (NoClassDefFoundError e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteUsing(String time) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("DELETE FROM occupied WHERE time=?");

			stmt.setString(1, time);
			stmt.executeUpdate();
		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DatabaseConf.release(rset, stmt, conn);
			} catch (NoClassDefFoundError e) {
				e.printStackTrace();
			}
		}
	}

	private static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

}
