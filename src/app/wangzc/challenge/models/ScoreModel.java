package app.wangzc.challenge.models;

import app.wangzc.challenge.controllers.conf.DatabaseConf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 得分操作模型
 *
 * @author WangZC
 */
public class ScoreModel {

	public static void updateHistory(int score) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM panel WHERE time='history'");
			rset = stmt.executeQuery();
			if (rset.next()) {
				int historyScore = rset.getInt("score");
				historyScore += score;
				stmt = conn.prepareStatement("UPDATE panel SET score=? WHERE time='history'");
				stmt.setInt(1, historyScore);
				stmt.executeUpdate();
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

	public static int queryHistory() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM panel WHERE time='history'");
			rset = stmt.executeQuery();
			if (rset.next()) {
				return rset.getInt("score");
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

		return 0;
	}

	public static void updateToday(int score) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM panel WHERE time=?");
			stmt.setString(1, getCurrentTime());
			rset = stmt.executeQuery();
			if (rset.next()) {
				int todayScore = rset.getInt("score");
				todayScore += score;
				stmt = conn.prepareStatement("UPDATE panel SET score=? WHERE time=?");
				stmt.setInt(1, todayScore);
				stmt.setString(2, getCurrentTime());
				stmt.executeUpdate();
			} else {
				stmt = conn.prepareStatement("INSERT INTO panel (time, score) VALUES (?,?)");
				stmt.setString(1, getCurrentTime());
				stmt.setInt(2, score);
				stmt.executeUpdate();
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

	public static int queryToday() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM panel WHERE time=?");
			stmt.setString(1, getCurrentTime());
			rset = stmt.executeQuery();
			if (rset.next()) {
				return rset.getInt("score");
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
		return 0;
	}

	public static void updateTodayAndHistory(int score) {
		updateToday(score);
		updateHistory(score);
	}

	public static void updateSpecialDayAndHistory(int score, String time) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM panel WHERE time=?");
			stmt.setString(1, time);
			rset = stmt.executeQuery();
			if (rset.next()) {
				int lastDayScore = rset.getInt("score");
				lastDayScore += score;
				stmt = conn.prepareStatement("UPDATE panel SET score=? WHERE time=?");
				stmt.setInt(1, lastDayScore);
				stmt.setString(2, time);
				stmt.executeUpdate();
			}
			updateHistory(score);

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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	public static void main(String[] args) {
		updateToday(1);
	}

}
