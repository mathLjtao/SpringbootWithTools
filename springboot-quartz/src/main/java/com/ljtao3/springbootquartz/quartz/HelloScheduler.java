package com.ljtao3.springbootquartz.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author ljtao3 on 2020/3/9
 */
public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // jobdetail 和 job实例绑定
        JobDetail helloJobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("helloJob", "helloGroup").build();

        // 触发器 simpleTrigger(SimpleScheduleBuilder)  CronTrigger
        // 每5秒运行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("helloTrigger", "helloGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("/5 * * * * ? *"))
                .build();

        // 调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        //执行任务
        scheduler.scheduleJob(helloJobDetail, trigger);


        //Thread.sleep(20000);
        //scheduler.shutdown();
    }
}
