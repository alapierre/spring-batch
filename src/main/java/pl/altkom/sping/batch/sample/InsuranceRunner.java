/*
 * Copyright 2014-09-16 the original author or authors.
 */

package pl.altkom.sping.batch.sample;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Adrian Lapierre <adrian@softproject.com.pl>
 */
public class InsuranceRunner {
    
    public static void main(String[] args) {
        //"batch-task_customer_to_db.xml",
        String[] springConfig  = 
		{	                        
			"batch-task_FL_insurance_sample.xml",
                        
		};
 
	ApplicationContext context = 
			new ClassPathXmlApplicationContext(springConfig);
        
        long statTime = System.currentTimeMillis();
        
        runTask(context);
        
        long endTime = System.currentTimeMillis();
 
        long exTime = endTime - statTime;
        
	System.out.println("time = " + exTime);
        
        
    }

    private static void runTask(ApplicationContext context) throws BeansException {
        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("importInsurance");
        
        try {
            
            JobExecution execution = jobLauncher.run(job, new JobParameters());
            System.out.println("Exit Status : " + execution.getStatus());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
