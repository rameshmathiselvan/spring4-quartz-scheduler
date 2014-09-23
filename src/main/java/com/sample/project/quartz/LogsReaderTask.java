package com.sample.project.quartz;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Quartz Bean which is triggered periodically by a cron trigger
 */
@Component
public class LogsReaderTask {

    private static final Logger LOGGER = Logger.getLogger(LogsReaderTask.class);

    /**
     * Triggers task for reading logs periodically based on a cron trigger
     * 
     * @return null
     * 
     */
    public String executeLogsReaderTask() {
        LOGGER.info("LogsReaderTask triggered for reading logs");
        try {

        }
        catch (Exception e) {
            LOGGER.error("Exception occured in executeLogsReaderTask method!" + e);
        }
        return null;
    }

}
