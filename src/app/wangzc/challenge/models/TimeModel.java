package app.wangzc.challenge.models;

import app.wangzc.challenge.controllers.conf.DatabaseConf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间计算模型
 *
 * @author WangZC
 */
public class TimeModel {

	public static void main(String[] args) {
//		ensureIdy();
//		System.out.println(getToday());
//		System.out.println(getLastMonday());
//		System.out.println(getLastSunday());
//		System.out.println(getThisMonday());
//		System.out.println(getThisSunday());
	}

	private static int lastWeekScore = 0;
	private static int thisWeekScore = 0;

	public static void calcLastWeekScore() {
		lastWeekScore = 0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM panel WHERE time >= ? AND time <= ?");
			stmt.setString(1, getLastMonday());
			stmt.setString(2, getLastSunday());
			rset = stmt.executeQuery();

			while (rset.next()) {
				lastWeekScore += rset.getInt("score");
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

	public static void calcThisWeekScore() {
		thisWeekScore = 0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM panel WHERE time >= ? AND time <= ?");
			stmt.setString(1, getThisMonday());
			stmt.setString(2, getThisSunday());
			rset = stmt.executeQuery();

			while (rset.next()) {
				thisWeekScore += rset.getInt("score");
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

	public static int getLastWeekScore() {
		return lastWeekScore;
	}

	public static int getThisWeekScore() {
		return thisWeekScore;
	}

	private static void example() {
		/*
		Calendar cal = Calendar.getInstance();
		//n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		int n = -1;
		String monday;
		cal.add(Calendar.DATE, n*7);
		//想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		System.out.println(monday);
		*/
	}

	public static String getLastMonday() {
		Calendar cal = Calendar.getInstance();
		int n = idy - 1;
		String monday;
		cal.add(Calendar.DATE, n * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return monday;
	}

	public static String getLastSunday() {
		Calendar cal = Calendar.getInstance();
		int n = idy;
		String sunday;
		cal.add(Calendar.DATE, n * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		sunday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return sunday;
	}

	public static String getThisMonday() {
		Calendar cal = Calendar.getInstance();
		int n = idy;
		String monday;
		cal.add(Calendar.DATE, n * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return monday;
	}

	public static String getThisSunday() {
		Calendar cal = Calendar.getInstance();
		int n = idy + 1;
		String sunday;
		cal.add(Calendar.DATE, n * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		sunday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return sunday;
	}

	public static void ensureIdy() {
		int ct = getThisMonday().compareTo(getToday());
		if (ct > 0) {
			idy = -1;
		}
	}

	private static int idy = 0;

	private static String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

}
