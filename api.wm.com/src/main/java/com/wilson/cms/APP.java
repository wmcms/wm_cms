package com.wilson.cms;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.mybatis.spring.annotation.MapperScan;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wilson.cms.service.NewsService;
import com.wilson.cms.utils.SpidertUtils;


@SpringBootApplication
@MapperScan(value = "com.wilson.cms.mapper")
public class APP {
	@Autowired
	 NewsService newsService;
	@PostConstruct
	public void init() {
		SpidertUtils.setService(newsService);
	}
	// 维护一个本类的静态变量
	public static NewsService service;
	public static void main(String[] args) throws SchedulerException, InterruptedException {
	
		SpringApplication.run(APP.class, args);
		System.out.println(System.currentTimeMillis());
		Timestamp ts = new Timestamp(1573932957000L);  
        Date date = new Date();  
    
            date = ts;  
            System.out.println(date);  
		
		//获取Calendar对象对应的时间戳
		System.out.println("============================");
		System.out.println("==========启动成功==========");
		System.out.println("============================");
		
		//startJob();
	}
	
	static void startJob() throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.start();
		System.out.println("scheduler.start");
		// 具体任务.
		JobDetail jobDetail = JobBuilder.newJob(SpiderJob.class).withIdentity("job1", "group1").build();
		// 触发时间点. (每5秒执行1次.)
		SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(30)
				.repeatForever();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow()
				.withSchedule(simpleScheduleBuilder).build();
		// 交由Scheduler安排触发
		scheduler.scheduleJob(jobDetail, trigger);
		// 睡眠20秒.
		//TimeUnit.SECONDS.sleep(20);
	//	scheduler.shutdown();// 关闭定时任务调度器.
		//System.out.println("scheduler.shutdown");
	}

}
