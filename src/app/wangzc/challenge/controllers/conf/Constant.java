package app.wangzc.challenge.controllers.conf;

/**
 * 全局常量
 *
 * @author WangZC
 */
public class Constant {

	private static String username = "WangZC";
	private static String motto = "优于别人并不高贵，真正的高贵应该是优于过去的自己";

	public static final String lib_dir = "/app/wangzc/challenge/lib/";  // lib location
	public static final String Version = "1.0.2";                       // Version number
	public static final String AboutAuthor = "<html>\n" +               // author info
			"<font face='华文行楷' size='5'>\n" +
			"<br>\n" +
			"版本号: " + Constant.Version + "<br><br>\n" +
			"开发人员: WangZC<br><br>\n" +
			"更新日期: 2016/7/10<br><br>\n" +
			"</font>\n" +
			"</html>";

	public static void setUsername(String username) {
		Constant.username = username;
	}

	public static String getUsername() {
		return username;
	}

	public static void setMotto(String motto) {
		Constant.motto = motto;
	}

	public static String getMotto() {
		return motto;
	}
}
