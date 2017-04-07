package wuye.api.task;

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
	
	
    @Scheduled(cron = "0 10 1 ? * MON")
    public void jobWeek() {   // 每周一的定时任务， 1点10分
        System.out.println("week任务进行中。。。");
        assessLogic.doSumWeek();
        System.out.println("week任务结束");
    }
    
    @Scheduled(cron = "0 10 3 1 * ?")
    public void jobMonth() {   // 每月第一天的定时任务 3点10分
        System.out.println("month任务进行中。。。");
        assessLogic.doSumMonth();
        System.out.println("month任务结束");
    }
}
