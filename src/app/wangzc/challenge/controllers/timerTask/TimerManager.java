package app.wangzc.challenge.controllers.timerTask;

import app.wangzc.challenge.models.DatabaseHandle;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

	private static Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	public static void cleanUpTimerTask() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 30);
		calendar.set(Calendar.SECOND, 0);
		/** 23:30:00 执行 **/
		Date date = calendar.getTime();
		if (date.before(new Date())) {
			date = addDay(date, 1);
		}

		Timer timer = new Timer();
		CleanUpTask task = new CleanUpTask();
		timer.schedule(task, date, PERIOD_DAY);
	}
}

class CleanUpTask extends TimerTask {

	@Override
	public void run() {
		DatabaseHandle.cleanUp();
	}

}