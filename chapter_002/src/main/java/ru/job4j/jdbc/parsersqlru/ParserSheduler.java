package ru.job4j.jdbc.parsersqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.CronTrigger;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author Valeriy Gyrievskikh
 * @since 08.09.2019
 */
public class ParserSheduler {
    /**
     * Логгер планировщика.
     */
    private static final Logger LOG = LogManager.getLogger(ParserSheduler.class.getName());

    /**
     * Параметры подключения.
     */
    private static final Properties PROPERTIES = new Properties();

    /**
     * Метод возвращает информацию логгера.
     *
     * @return Логгер.
     */
    public Logger getLog() {
        return LOG;
    }

    /**
     * Метод возвращает параметры подключения.
     *
     * @return Параметры подключения.
     */
    public static Properties getProperties() {
        return PROPERTIES;
    }

    /**
     * Метод выполняет запуск планировщика.
     *
     * @param args Список параметров запуска.
     *             Первый параметр - название конфигурационного файла,
     *             следующие параметры - названия классов-парсеров.
     *             Если список пустой, выполняется запуск парсера VacancyParser,
     *             настройки читаются из файла app.properties.
     */
    public static void main(String[] args) {
        ParserSheduler parserSheduler = new ParserSheduler();
        if (args.length > 0) {
            for (int i = 1; i < args.length; i++) {
                parserSheduler.initScheduler(args[0], args[i]);
            }
        } else {
            parserSheduler.initScheduler("app.properties");
        }
    }

    /**
     * Метод выполняет запуск планировщика для класса VacancyParser.
     *
     * @param arg Название конфигурационного файла.
     */
    public void initScheduler(String arg) {
        initScheduler(arg, "VacancyParser");
    }

    /**
     * Метод инициализирует настройки подключения и выполняет настройку и запуск планировщика.
     *
     * @param arg Название файла настроек.
     */
    public void initScheduler(String arg, String parserName) {
        try (InputStream in = ParserSheduler.class.getClassLoader().getResourceAsStream(arg);) {
            PROPERTIES.load(in);
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail job = newJob(ParserJob.class)
                    .withIdentity(parserName, Scheduler.DEFAULT_GROUP)
                    .build();
            CronTrigger trigger = newTrigger()
                    .withIdentity("trigger1", Scheduler.DEFAULT_GROUP)
                    .withSchedule(cronSchedule(PROPERTIES.getProperty("cron.time")))
                    .build();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (IOException | SchedulerException e) {
            LOG.error(e);
        }
    }
}
