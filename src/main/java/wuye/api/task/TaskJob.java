package wuye.api.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import wuye.logic.AssessLogic;

@Component("taskJob")  
public class TaskJob {  
	
	private AssessLogic assessLogic;

	public AssessLogic getAssessLogic() {
		return assessLogic;
	}

	public void setAssessLogic(AssessLogic assessLogic) {
		this.assessLogic = assessLogic;
	}
	
	
    @Scheduled(cron = "0 20 1 ? * MON")
    public void jobWeek() {   // 每周一的定时任务， 1点10分
        System.out.println("week任务进行中。。。");
        assessLogic.doSumWeek();
        
        System.out.println("week任务（文件）开始");
        SimpleDateFormat dfday = new SimpleDateFormat("yyyyMMdd");
        
        long end;
		try {
			end = dfday.parse(dfday.format(new Date())).getTime();
		} catch (ParseException e1) {
			return;
		}
		long begin = end - 24*3600*1000;
		Date day = new Date(begin);
		
		int date = Integer.parseInt(dfday.format(day));
		// 文件逻辑
        assessLogic.doneWordData(date);
        assessLogic.getPianquWeekData(date);
        System.out.println("week任务结束");
    }
    
    @Scheduled(cron = "0 10 3 1 * ?")
    public void jobMonth() {   // 每月第一天的定时任务 3点10分
        System.out.println("month任务进行中。。。");
        assessLogic.doSumMonth();
        System.out.println("month任务结束");
    }
}
