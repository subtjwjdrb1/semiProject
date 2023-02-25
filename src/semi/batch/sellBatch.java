/*package semi.batch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import semi.dao.SellDao;
import semi.vo.SellVo;

public class sellBatch {
	public sellBatch() {
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				System.out.println("task");
				
				Calendar cal1=Calendar.getInstance();
				
				int y=cal1.get(Calendar.YEAR);
				int m=cal1.get(Calendar.MONTH) +1;
				int d=cal1.get(Calendar.DATE);
				
				String date="";
				date += y;
				if(m < 10) date += "0";
				date += m;
				date += d;
				
				try {
					PrintWriter pw = new PrintWriter("c:\\sell\\" + date + ".txt");
					SellDao dao = SellDao.getInstance();
					ArrayList<SellVo> list = dao.sellBatch();
					for (SellVo vo : list) {
						pw.println(vo);
					}
					pw.close();

				} catch (IOException ie) {
					System.out.println(ie.getMessage());
				}
			}
		};

		Timer timer = new Timer(true);
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 1, 27, 0, 0, 0);
		timer.scheduleAtFixedRate(task, new Date(cal.getTimeInMillis()), 1000 * 60 * 60 * 24);
	}
}
*/