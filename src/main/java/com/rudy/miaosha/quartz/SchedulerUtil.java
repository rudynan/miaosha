package com.rudy.miaosha.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchedulerUtil {
    private static Logger logger = LoggerFactory.getLogger(SchedulerUtil.class);

    public static void handleSimpleTrigger(String jobName, String jobGroupName, String triggerName, String tiggerGroupName, Class jobClass, int time, int count) {
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            // 通过schedulerfactory获取一个调度器
            scheduler = schedulerFactory.getScheduler();
            // 创建jobdetail实例 绑定job类
            JobDetail job = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();

            // 定义触发规则
            // 使用simpletrigger规则
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, tiggerGroupName)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(time).withRepeatCount(count))
                    .startNow().build();

            scheduler.scheduleJob(job,trigger);
            scheduler.start();
        }catch (Exception e){
            logger.warn("执行"+jobName+"组"+jobName+"任务出现异常E:["+ e.getMessage() + "]");
        }
    }

    public static void handleCronTigger(String jobName, String jobGroupName,
                                        String triggerName, String triggerGroupName, Class jobClass,String cron) {
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try{
             scheduler = schedulerFactory.getScheduler();
            JobDetail job = JobBuilder.newJob(jobClass)
                    .withIdentity(jobName, jobGroupName).build();

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .startNow().build();

            scheduler.scheduleJob(job,trigger);
            scheduler.start();

        }catch (Exception e){
            logger.warn("执行"+jobName+"组"+jobName+"任务出现异常E:["+ e.getMessage() + "]");
        }
    }
}
