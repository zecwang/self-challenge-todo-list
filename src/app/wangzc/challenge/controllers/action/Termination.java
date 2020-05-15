package app.wangzc.challenge.controllers.action;

import javax.swing.*;

public class Termination {

	public static void loseDBConnection() {
		JOptionPane.showMessageDialog(null, " 失去数据库连接，程序即将终止，请检查网络状况", "终止", JOptionPane.WARNING_MESSAGE);
		System.exit(-1);
	}

}
