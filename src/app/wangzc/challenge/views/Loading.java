package app.wangzc.challenge.views;

import app.wangzc.challenge.controllers.conf.Constant;
import com.sun.awt.AWTUtilities;

import javax.swing.*;

/**
 * loading image JFrame
 *
 * @author WangZC
 */

public class Loading extends JFrame {

	private static Loading loading = new Loading();

	public static Loading getInstance() {
		return loading;
	}

	private Loading() {
		this.setTitle("loading");
		JLabel label = new JLabel(new ImageIcon(Constant.class.getResource(Constant.lib_dir + "images/loading/loading.gif")));
		this.add(label);

		this.setUndecorated(true);
		AWTUtilities.setWindowOpaque(this, false);

		this.setSize(455, 265);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
