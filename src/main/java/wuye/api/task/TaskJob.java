package wuye.api.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import wuye.logic.AssessLogic;
import wuye.manager.utils.DateUtil;
import wuye.manager.utils.TimeUtil;

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
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format(new Date());
        String realTime = TimeUtil.getDayAddStr(time, -1);

        int dateId = Integer.parseInt(realTime);

        System.out.println("week任务进行中。。。");
        assessLogic.doSumWeek(dateId);
        
        System.out.println("week任务（文件）开始");

		// 文件逻辑
        assessLogic.doneWordData(dateId);
        assessLogic.getPianquWeekData(dateId);
        System.out.println("week任务结束");
    }
    
    @Scheduled(cron = "0 10 3 1 * ?")
    public void jobMonth() {   // 每月第一天的定时任务 3点10分
        System.out.println("month任务进行中。。。");
        assessLogic.doSumMonth(1);  // todo
        System.out.println("month任务结束");
    }
}
