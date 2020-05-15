package app.wangzc.challenge.models;

import app.wangzc.challenge.controllers.conf.Constant;
import app.wangzc.challenge.controllers.conf.DatabaseConf;
import app.wangzc.challenge.controllers.timerTask.TimerManager;
import app.wangzc.challenge.views.Loading;
import app.wangzc.challenge.views.Render;

import javax.swing.*;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 数据库初始化检查
 *
 * @author WangZC
 */
public class DatabaseHandle {

	private static int count = 0;
	private static int loseScore = 0;
	private static int overTask = 0;
	private static int punishScore = 0;
	private static int longTimeNoLogin = 0;
	private static int noLoginDays = 0;
	private static String lastLoginTime;
	private static boolean cleanUp = false;
	private static boolean doneCleanUp = false;

	/**
	 * 昨天是否进行CleanUp
	 **/

	public static void main(String[] args) {
		Render.defStyle();
		check();
	}

	public static void cleanUp() {

		/** 清零 **/
		count = 0;
		loseScore = 0;
		overTask = 0;
		punishScore = 0;
		longTimeNoLogin = 0;
		noLoginDays = 0;
		cleanUp = true; /** 开启cleanUp模式 **/

		doClean();
		doCleanDaily();
		doCleanAward();
		doChange();
		punish();
		if (count != 0 || overTask != 0) {
			JOptionPane.showMessageDialog(null, new JLabel("<html>23: 30 --> task list checking & updating ... <br>" +
					" 共 " + count + " 项任务逾时, 扣去 " + loseScore + " 点积分 <br>" +
					" 有 " + overTask + " 项任务超最终期限, 将被强制删除, 惩罚 " + punishScore + " 点积分</html>"), "警告", JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, new JLabel("<html>23: 30 --> task list checking & updating ... <br>" +
					" ## done. no change. ##</html>"), "CleanUp处理", JOptionPane.INFORMATION_MESSAGE);
		}
		ScoreModel.updateTodayAndHistory(-loseScore - punishScore - longTimeNoLogin);
		TimeModel.ensureIdy();
		TimeModel.calcLastWeekScore();
		TimeModel.calcThisWeekScore();

		updateCleanUpStatus("true");
		doneCleanUp = true;
		cleanUp = false;
	}

	public static void check() {
		doCheckHistory();
		doUpdateLoginfo();
		try {
			if (dailyBetween(lastLoginTime, getCurrentTime()) != 0) {
				doClean();
				doCleanDaily();
				doCleanAward();
				doChange();
				punish();
				if (count != 0 || overTask != 0 || longTimeNoLogin != 0)
					JOptionPane.showMessageDialog(null, new JLabel("<html>共 " + count + " 项任务逾时, 扣去 " + loseScore + " 点积分 <br>" +
							" 有 " + overTask + " 项任务超最终期限, 将被强制删除, 惩罚 " + punishScore + " 点积分 <br>" +
							" 连续 " + noLoginDays + " 天没有登录, 扣去 " + longTimeNoLogin + " 点积分</html>"), "警告", JOptionPane.WARNING_MESSAGE);
				ScoreModel.updateSpecialDayAndHistory(-loseScore - punishScore - longTimeNoLogin, lastLoginTime.split(" ")[0]);
				updateCleanUpStatus("false");
				doneCleanUp = false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			TimeModel.ensureIdy();
			TimeModel.calcLastWeekScore();
			TimeModel.calcThisWeekScore();

			startTimerTask(); /** 启动定时任务 **/
		}
	}

	public static void startTimerTask() {
		TimerManager.cleanUpTimerTask();
	}

	public static void doCheckHistory() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM panel WHERE time='history'");
			rset = stmt.executeQuery();

			if (!rset.next()) {
				stmt = conn.prepareStatement("INSERT INTO panel (time, score) VALUES ('history', 0)");
				stmt.executeUpdate();
			}

		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, " 数据库连接失败。", "连接失败", JOptionPane.WARNING_MESSAGE);
			Loading.getInstance().dispose();
			System.exit(-1);
		} finally {
			try {
				DatabaseConf.release(rset, stmt, conn);
			} catch (NoClassDefFoundError e) {
				e.printStackTrace();
			}
		}
	}

	public static void doUpdateLoginfo() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM loginfo");
			rset = stmt.executeQuery();

			if (!rset.next()) {
				stmt = conn.prepareStatement("INSERT INTO loginfo (username, ip, login_time, doCleanUp) VALUES (?, ?, ?, ?)");
				stmt.setString(1, Constant.getUsername());
				stmt.setString(2, InetAddress.getLocalHost().getHostAddress());
				stmt.setString(3, getCurrentTime());
				stmt.setString(4, "false");
				stmt.executeUpdate();
				lastLoginTime = getCurrentTime();
				doneCleanUp = false;
			} else {

				doneCleanUp = rset.getString("doCleanUp").equals("true");
				lastLoginTime = rset.getString("login_time");
				noLoginDays = daysBetween(rset.getString("login_time"), getCurrentTime());
				if (noLoginDays > 2) {
					longTimeNoLogin = (noLoginDays - 1) * 5;
				}

				stmt = conn.prepareStatement("UPDATE loginfo SET login_time=?, ip=? WHERE username=?");
				stmt.setString(1, getCurrentTime());
				stmt.setString(2, InetAddress.getLocalHost().getHostAddress());
				stmt.setString(3, Constant.getUsername());
				stmt.executeUpdate();
			}

		} catch (ExceptionInInitializerError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, " 数据库连接失败。", "连接失败", JOptionPane.WARNING_MESSAGE);
			Loading.getInstance().dispose();
			System.exit(-1);
		} finally {
			try {
				DatabaseConf.release(rset, stmt, conn);
			} catch (NoClassDefFoundError e) {
				e.printStackTrace();
			}
		}
	}

	public static void updateCleanUpStatus(String status) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("UPDATE loginfo SET doCleanUp=? WHERE username=?");

			stmt.setString(1, status);
			stmt.setString(2, Constant.getUsername());
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

	public static void doCleanAward() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM award WHERE have=?");

			stmt.setInt(1, 0);
			rset = stmt.executeQuery();

			while (rset.next()) {
				doCleanAward(rset.getString("item"));
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

	public static void doCleanAward(String item) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM exchange WHERE item=?");

			stmt.setString(1, item);
			rset = stmt.executeQuery();

			if (!rset.next()) {
				stmt = conn.prepareStatement("DELETE FROM award WHERE item=?");

				stmt.setString(1, item);
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

	public static void doClean() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM task WHERE type<>'日常任务' AND (status='已完成' OR status='放弃')");
			rset = stmt.executeQuery();

			while (rset.next()) {
				doClean(rset.getString("setup_time"));
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

	private static void doClean(String time) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			if (cleanUp || daysBetween(time, getCurrentTime()) > 0) {
				stmt = conn.prepareStatement("DELETE FROM task WHERE setup_time=?");
				stmt.setString(1, time);
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

	public static void doCleanDaily() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM task WHERE type='日常任务'");
			rset = stmt.executeQuery();

			while (rset.next()) {
				if (!rset.getString("status").equals("已完成")) {
					if (cleanUp || dailyBetween(rset.getString("time"), getCurrentTime()) > 0 || doneCleanUp) {
						if (!rset.getString("status").equals("放弃") && !doneCleanUp) {
							count++;
							loseScore += rset.getInt("score");
						}
						doCleanDaily(rset.getString("setup_time"));
					}
				} else if (rset.getString("status").equals("已完成")) {
					if (cleanUp || dailyBetween(rset.getString("time"), getCurrentTime()) > 0) {
						doCleanDaily(rset.getString("setup_time"));
					}
				}
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

	private static void doCleanDaily(String time) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("UPDATE task SET status='', time=? WHERE setup_time=?");
			stmt.setString(1, getCurrentTime());
			stmt.setString(2, time);
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

	public static void doChange() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM task WHERE status='' AND type<>'长期任务' AND type<>'日常任务'");
			rset = stmt.executeQuery();

			while (rset.next()) {
				doChange(rset.getString("setup_time"), rset.getInt("score"));
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

	private static void doChange(String time, int score) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			if (daysBetween(time, getCurrentTime()) > 0) {
				count++;
				loseScore += score;
				stmt = conn.prepareStatement("UPDATE task SET status=?, type=? WHERE setup_time=?");
				stmt.setString(1, "未完成");
				stmt.setString(2, "超时任务");
				stmt.setString(3, time);
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

	public static void punish() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM task WHERE type='超时任务'");
			rset = stmt.executeQuery();

			while (rset.next()) {
				punish(rset.getString("setup_time"), rset.getInt("score"));
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

	private static void punish(String time, int score) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			if (daysBetween(time, getCurrentTime()) > 2) {
				overTask++;
				punishScore += score;
				stmt = conn.prepareStatement("DELETE FROM task WHERE setup_time=?");
				stmt.setString(1, time);
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

	private static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	private static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	private static int dailyBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

}
