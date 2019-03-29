package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Valeriy Gyrievskikh
 * @since 21.06.2018.
 */
public class StartUITest {

    /**
     * Экземпляр трекера для теста.
     */
    private final Tracker tracker = new Tracker();

    /**
     * Тестовая заявка.
     */
    private Item item;

    /**
     * Метод создаеет новую заявку и добавляет ее в трекер
     */
    @Before
    public void beforeTest() {
        item = new Item("test name", "desc");
        tracker.add(item);
    }

    /**
     * Метод очищает трекер и стирает заявку.
     */
    @After
    public void afterTest() {
        tracker.delete(item.getId());
        item = null;
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteThenTrackerHasNoValue() {
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        Input expect = null;
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()), is(expect));
    }

    @Test
    public void whenFindByIdThenTrackerHasValue() {
        String itemId = item.getId();
        Input input = new StubInput(new String[]{"4", itemId, "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getId(), is(itemId));
    }

    @Test
    public void whenFindAllThenTrackerHasValue() {
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().size(), is(1));
    }

    @Test
    public void whenFindByNameThenTrackerHasValue() {
        Input input = new StubInput(new String[]{"5", "test name", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName("test name").size(), is(1));
    }
}
