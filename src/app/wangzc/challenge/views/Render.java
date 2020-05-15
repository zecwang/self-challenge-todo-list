package app.wangzc.challenge.views;

/**
 * 定义渲染方式
 *
 * @author WangZC
 */
public class Render {
	public static void defStyle() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Render.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}
}
