package app.wangzc.challenge.models;

import app.wangzc.challenge.controllers.action.Termination;
import app.wangzc.challenge.controllers.conf.DatabaseConf;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 任务操作模型
 *
 * @author WangZC
 */
public class TaskModel {

	public static Object[][] getTask() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Object[][] objects = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM task WHERE status<>'已完成' AND status<>'放弃'");
			rset = stmt.executeQuery();
			rset.next();
			int count = rset.getInt(1);

			stmt = conn.prepareStatement("SELECT * FROM task WHERE status<>'已完成' AND status<>'放弃' " +
					"ORDER BY CASE type WHEN '超时任务' THEN 1 WHEN '新任务' THEN 2 WHEN '日常任务' THEN 3 WHEN '长期任务' THEN 4 END, score, setup_time ASC ");
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

	public static Object[][] getDailyTask() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		Object[][] objects = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM task WHERE type='日常任务'");
			rset = stmt.executeQuery();
			rset.next();
			int count = rset.getInt(1);

			stmt = conn.prepareStatement("SELECT * FROM task WHERE type='日常任务'");
			rset = stmt.executeQuery();

			objects = new Object[count][2];
			int i = 0;

			while (rset.next()) {
				objects[i][0] = rset.getInt("score");
				objects[i][1] = rset.getString("content");
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

	public static void deleteDailyTask(String content) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("DELETE FROM task WHERE type='日常任务' AND content=?");

			stmt.setString(1, content);
			stmt.executeUpdate();
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
	}

	public static void insertTask(int score, String type, String content) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("INSERT INTO task (score, type, setup_time ,time, content) VALUES (?,?,?,?,?)");

			stmt.setInt(1, score);
			stmt.setString(2, type);
			stmt.setString(3, getCurrentTime());
			stmt.setString(4, getCurrentTime());
			stmt.setString(5, content);
			if (stmt.executeUpdate() == 1)
				JOptionPane.showMessageDialog(null, " 添加成功", "操作成功", JOptionPane.PLAIN_MESSAGE);

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
	}
	
	public static void updateTaskContent(String setup_time, String content, String score) {
		if ("".equals(content.trim())) {
			JOptionPane.showMessageDialog(null, " 任务内容不可为`NULL`", "操作失败", JOptionPane.WARNING_MESSAGE);
			return;
		}
		try {
			Integer.parseInt(score);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, " 分值格式错误", "操作失败", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("UPDATE task SET content=?, score=? WHERE setup_time=?");
			
			stmt.setString(1, content);
			stmt.setString(2, score);
			stmt.setString(3, setup_time);
			
			stmt.executeUpdate();
//			if (stmt.executeUpdate() == 1) {
//				JOptionPane.showMessageDialog(null, " 修改成功", "", JOptionPane.PLAIN_MESSAGE);
//			}
			
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

	public static void updateTaskStatus(String setup_time, String status, String score) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DatabaseConf.getConnection();
			stmt = conn.prepareStatement("UPDATE task SET status=? WHERE setup_time=?");

			stmt.setString(1, status);
			stmt.setString(2, setup_time);

			if (stmt.executeUpdate() == 1) {
				if (status.equals("已完成")) {
					ScoreModel.updateTodayAndHistory(Integer.parseInt(score));
					JOptionPane.showMessageDialog(null, " 已完成任务+1, 获得 " + score + " 积分", "", JOptionPane.PLAIN_MESSAGE);
				} else {
					ScoreModel.updateTodayAndHistory(-Integer.parseInt(score));
					JOptionPane.showMessageDialog(null, " 放弃任务, 扣去 " + score + " 积分", "", JOptionPane.PLAIN_MESSAGE);
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

	private static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	public static void main(String[] args) {
		System.out.println(getCurrentTime());
	}

}
