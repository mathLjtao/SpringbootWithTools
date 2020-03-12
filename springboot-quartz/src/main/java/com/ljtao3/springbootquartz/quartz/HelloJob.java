package com.ljtao3.springbootquartz.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author ljtao3 on 2020/3/9
 */
@DisallowConcurrentExecution
public class HelloJob implements Job {

    private static Logger log = LogManager.getLogger();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //log.info(" HelloJob ");
        System.out.println("Hello Job");
    }
}
