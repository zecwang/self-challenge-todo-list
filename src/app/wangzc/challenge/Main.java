package app.wangzc.challenge;

import app.wangzc.challenge.models.DatabaseHandle;
import app.wangzc.challenge.views.Loading;
import app.wangzc.challenge.views.Render;
import app.wangzc.challenge.views.SelfChallengeJFrame;

/**
 * program entry
 *
 * @author WangZC
 */
public class Main {
	public static void main(String[] args) {
		/** 定义渲染样式 */
		Render.defStyle();

		/** 加载界面logo */
		Loading.getInstance().setVisible(true);

		/** 对数据库例行检查 */
		DatabaseHandle.check();

		/** 启动程序界面 */
		SelfChallengeJFrame.getInstance().setVisible(true);
	}
}
