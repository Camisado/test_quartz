package org.test_quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * Created by Yuri on 23.01.2015.
 */
public class SimpleTriggerExample {

    public static void main(String args[]) throws SchedulerException {
        JobKey jobKey = new JobKey("jobName", "group1");
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity(jobKey).build();
        JobDetail job2 = JobBuilder.newJob(SecondJob.class).withIdentity("jobName2", "group1").build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("triggerName", "group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();
        Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("triggerName2", "group1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getListenerManager().addJobListener(new HelloJobListener(), KeyMatcher.keyEquals(jobKey));

        scheduler.start();
        scheduler.scheduleJob(job, trigger);
        scheduler.scheduleJob(job2, trigger2);
    }
}
