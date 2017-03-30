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
	
	
    @Scheduled(cron = "10 1 * * * ?")
    public void jobWeek() {   // 每周六的定时任务
        System.out.println("week任务进行中。。。");
        assessLogic.doSumWeek();
        System.out.println("week任务结束");
    }
    
    @Scheduled(cron = "*/10 * * * * ?")
    public void jobMonth() {   // 每月第一天的定时任务
        System.out.println("month任务进行中。。。");
        assessLogic.doSumMonth();
        System.out.println("month任务结束");
    }
}
