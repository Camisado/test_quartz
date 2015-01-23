package org.test_quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by Yuri on 23.01.2015.
 */
public class SimpleTriggerExample {

    public static void main(String args[]) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("jobName", "group1").build();
        JobDetail job2 = JobBuilder.newJob(SecondJob.class).withIdentity("jobName2", "group2").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("triggerName", "group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();
        Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("triggerName2", "group2")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
        scheduler.scheduleJob(job2, trigger2);
    }
}
