package wuye.api.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskJob")  
public class TaskJob {  
    @Scheduled(cron = "30 3 * * 6 ?")
    public void job1() {   // 每周六的定时任务
        System.out.println("任务进行中。。。");
        
        // 进行本周的数据统计，这里是物业排名
        
        // 进行片区排名
    }
}
