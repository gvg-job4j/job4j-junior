package ru.job4j.jdbc.parsersqlru;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Valeriy Gyrievskikh
 * @since 01.09.2019
 */
public class VacancyParser {

    /**
     * Менеджер базы данных.
     */
    private DatabaseManager databaseManager;
    /**
     * Таблица конвертации месяцев.
     */
    private Map<String, Month> monthEquals = new HashMap<>();
    /**
     * Первые две цифры текущего года.
     */
    private String firstNumbersOfYear = Integer.toString(LocalDate.now().getYear()).substring(0, 2);

    /**
     * Конструктор, создает новый менеджер базы данных.
     */
    public VacancyParser() {
        this(new DatabaseManager());
    }

    /**
     * Конструктор, использует существующий менеджер базы данных.
     *
     * @param databaseManager Существующий менеджер базы данных.
     */
    public VacancyParser(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        monthEquals.put("янв", Month.JANUARY);
        monthEquals.put("фев", Month.FEBRUARY);
        monthEquals.put("мар", Month.MARCH);
        monthEquals.put("апр", Month.APRIL);
        monthEquals.put("май", Month.MAY);
        monthEquals.put("июн", Month.JUNE);
        monthEquals.put("июл", Month.JULY);
        monthEquals.put("авг", Month.AUGUST);
        monthEquals.put("сен", Month.SEPTEMBER);
        monthEquals.put("окт", Month.OCTOBER);
        monthEquals.put("ноя", Month.NOVEMBER);
        monthEquals.put("дек", Month.DECEMBER);
    }

    /**
     * Метод получает данные по указанному адресу.
     *
     * @param url Адрес сайта для парсинга.
     * @return Список полученных данных.
     */
    public List<String[]> connect(String url) {
        List<String[]> vacancyList = new ArrayList<>();
        if (databaseManager.connect()) {
            vacancyList = getData(url);
        }
        return vacancyList;
    }

    /**
     * Метод выполняет парсинг сайта по указанному адресу.
     *
     * @param url Адрес сайта.
     * @return Список полученных данных.
     */
    private List<String[]> getData(String url) {
        List<String[]> vacancyList = new ArrayList<>();
        try {
            String currentNumber = "1";
            Document doc = Jsoup.connect(url).get();
            Document page = getPageFromDoc(doc, vacancyList);
            String pageNumber = page.getElementById("af_form").childNodes().get(3).attr("value");
            while (Integer.parseInt(currentNumber) < Integer.parseInt(pageNumber)) {
                currentNumber = pageNumber;
                page = getPageFromDoc(doc, vacancyList);
                pageNumber = page.getElementById("af_form").childNodes().get(3).attr("value");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vacancyList;
    }

    /**
     * Метод получает текущую страницу документа.
     *
     * @param doc         Разбираемый документ.
     * @param vacancyList Список вакансий.
     * @return Текущая страница документа.
     * @throws IOException Возможное прерывание.
     */
    private Document getPageFromDoc(Document doc, List<String[]> vacancyList) throws IOException {
        Elements data = doc.select("table.forumTable");
        Elements rows = data.select("tr");
        if (rows.size() > 0) {
            parseRows(rows, vacancyList);
        }
        Elements pages = doc.select("table.sort_options");
        Elements pagesNext = pages.get(1).select("[href]");
        return Jsoup.connect(pagesNext.attr("href")).get();
    }

    /**
     * Методы формирует список данных.
     *
     * @param rows        Данные для обработки.
     * @param vacancyList Существующий список данных.
     * @throws IOException Возможное исключение.
     */
    private void parseRows(Elements rows, List<String[]> vacancyList) throws IOException {
        for (Element row : rows) {
            Node descrNode = row.childNodes().get(3);
            Node dataNode = row.childNodes().get(11);
            if (descrNode.childNodes().size() > 1) {
                if (!correctDate(dataNode.childNode(0).attr("text"))) {
                    continue;
                }
                String href = descrNode.childNodes().get(1).attributes().get("href");
                Document post = Jsoup.connect(href).get();
                Elements postData = post.select("table.msgTable");
                Elements postHeader = postData.get(0).select("td.messageHeader");
                Elements postText = postData.select("td.msgBody");
                String id = postHeader.get(0).attr("id").trim();
                String name = postHeader.get(0).childNodes().get(1).attr("text").trim();
                String text = postText.get(1).text().trim();
                if (name.contains("Java") && !(name.contains("JavaScript") || name.contains("Java Script"))) {
                    vacancyList.add(new String[]{id, name, text, href});
                }
            }
        }
    }

    /**
     * Метод выполняет проверку записи на попадание в период отбора.
     *
     * @param dataString Проверяемая запись.
     * @return Результат проверки.
     */
    private boolean correctDate(String dataString) {
        boolean isCorrect = false;
        if (!this.databaseManager.isFirst() && dataString.contains("сегодня")) {
            isCorrect = true;
        } else if (this.databaseManager.isFirst()
                && (dataString.contains("сегодня") || dataString.contains("вчера"))) {
            isCorrect = true;
        } else if (this.databaseManager.isFirst()) {
            Month dataMonth = null;
            for (Map.Entry<String, Month> entry : monthEquals.entrySet()) {
                if (dataString.contains(entry.getKey())) {
                    dataMonth = entry.getValue();
                    break;
                }
            }
            int afterYear = dataString.indexOf(",");
            int afterDays = dataString.indexOf(" ");
            int afterMonth = dataString.indexOf(" ", afterDays + 1);
            LocalDate date = LocalDate.of(
                    Integer.parseInt(firstNumbersOfYear + dataString.substring(afterMonth + 1, afterYear)),
                    (dataMonth == null ? Month.JANUARY : dataMonth),
                    Integer.parseInt(dataString.substring(0, afterDays)));
            LocalDate currentDate = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1);
            isCorrect = currentDate.compareTo(date) < 0;
        }
        return isCorrect;
    }
}
