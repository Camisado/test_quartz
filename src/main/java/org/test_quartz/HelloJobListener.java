package org.test_quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Created by Yuri on 26.01.2015.
 */
public class HelloJobListener implements JobListener {

    public static final String JOB_LISTENER_NAME = "Job listener name example";

    @Override
    public String getName() {
        return JOB_LISTENER_NAME;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        String jobName = jobExecutionContext.getJobDetail().getKey().toString();
        System.out.println("jobToBeExecuted");
        System.out.println("Job : " + jobName + " is going to start...");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("jobExecutionVetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("jobWasExecuted");

        String jobName = jobExecutionContext.getJobDetail().getKey().toString();
        System.out.println("Job : " + jobName + " is finished...");

        if (!e.getMessage().equals("")) {
            System.out.println("Exception thrown by: " + jobName
                    + " Exception: " + e.getMessage());
        }
    }
}
