package ru.job4j.jdbc.parsersqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;

import java.util.Date;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 08.09.2019
 */
public class ParserJob implements Job {
    /**
     * Логгер.
     */
    private static final Logger LOG = LogManager.getLogger(ParserJob.class.getName());

    /**
     * Метод выполняет запуск парсинга сайта по расписанию.
     *
     * @param jobExecutionContext Параметры задачи.
     * @throws JobExecutionException Возможное исключение.
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        String jobName = jobDetail.getDescription();
        LOG.info(jobName + " " + new Date());
        DatabaseManager databaseManager = new DatabaseManager();
        if (databaseManager.connect()) {
            VacancyParser parser = new VacancyParser(databaseManager);
            List<String[]> vacancyList = parser.connect("http://www.sql.ru/forum/job-offers");
            if (vacancyList.isEmpty()) {
                LOG.warn(new Date() + " .No Java vacancies was found...");
            } else {
                List<String[]> vacancyNoDulicatesList = databaseManager.checkDoublicates(vacancyList);
                if (vacancyNoDulicatesList.size() > 0) {
                    if (databaseManager.writeAll(vacancyNoDulicatesList)) {
                        LOG.info(new Date() + " .New vacancies writed to database.");
                    }
                } else {
                    LOG.warn(new Date() + " .New vacancies found, but not writed database.");
                }
            }
        } else {
            LOG.warn(new Date() + " .No connection to database. Parser not started");
        }
    }
}
