package com.sample.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ParseException;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.sample.project.quartz.LogsReaderTask;
import com.sample.project.util.StaticConstants;

/**
 * Initializes view resolver and beans for the web application context
 * 
 */
@Configuration
@ComponentScan("com.sample.project")
@EnableWebMvc
public class Config {

    /**
     * @return
     */
    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix(StaticConstants.RESOLVER_PREFIX);
        resolver.setSuffix(StaticConstants.RESOLVER_SUFFIX);
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean(name = "logsReaderTask")
    public LogsReaderTask logsReaderTask() {
        return new LogsReaderTask();
    }

    @Bean(name = "readerJobBean")
    public MethodInvokingJobDetailFactoryBean readerJobBean() throws ClassNotFoundException, NoSuchMethodException {
        MethodInvokingJobDetailFactoryBean jobDetailFactory = new MethodInvokingJobDetailFactoryBean();
        jobDetailFactory.setBeanName(StaticConstants.READER_JOB_BEAN);
        jobDetailFactory.setTargetObject(logsReaderTask());
        jobDetailFactory.setTargetMethod(StaticConstants.READER_JOB_METHOD);
        jobDetailFactory.setConcurrent(false);
        jobDetailFactory.afterPropertiesSet();
        return jobDetailFactory;
    }

    @Bean(name = "readerJobTrigger")
    public CronTriggerFactoryBean readerJobTrigger() throws ParseException, ClassNotFoundException, NoSuchMethodException {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setBeanName(StaticConstants.READER_JOB_TRIGGER);
        cronTriggerFactoryBean.setJobDetail(readerJobBean().getObject());
        cronTriggerFactoryBean.setCronExpression(StaticConstants.READER_JOB_CRON_EXRESSION);
        return cronTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean setupscheSchedulerFactoryBean() throws ClassNotFoundException, NoSuchMethodException {
        SchedulerFactoryBean schedulerfaFactoryBean = new SchedulerFactoryBean();
        schedulerfaFactoryBean.setJobDetails(readerJobBean().getObject());
        schedulerfaFactoryBean.setTriggers(readerJobTrigger().getObject());
        return schedulerfaFactoryBean;
    }
}