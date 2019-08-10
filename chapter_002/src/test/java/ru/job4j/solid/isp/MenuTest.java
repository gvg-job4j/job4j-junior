package ru.job4j.solid.isp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Valeriy Gyrievskikh
 * @since 10.08.2019
 */
public class MenuTest {

    @Test
    public void whenCreateSimpleMenuFromThreeParentsOnlyElementsThenHaveThree() {

        List<MenuElement> list = new ArrayList<>();
        MenuElement parent1 = new MenuElement(1, "Задача");
        MenuElement parent2 = new MenuElement(2, "Задача");
        MenuElement parent3 = new MenuElement(3, "Задача");
        list.add(parent1);
        list.add(parent2);
        list.add(parent3);
        MenuOutput output = new MenuOutput(list);
        assertThat(output.getElements().size(), is(3));
    }

    @Test
    public void whenCreateMenuFromFiveParentsElementsThenHaveFive() {

        List<MenuElement> list = new ArrayList<>();
        MenuElement parent1 = new MenuElement(1, "Задача");
        MenuElement parent2 = new MenuElement(2, "Задача");
        MenuElement parent3 = new MenuElement(3, "Задача");
        MenuElement parent11 = new MenuElement(parent1);
        MenuElement parent111 = new MenuElement(parent11);
        list.add(parent1);
        list.add(parent2);
        list.add(parent3);
        list.add(parent111);
        list.add(parent11);
        MenuOutput output = new MenuOutput(list);
        assertThat(output.getElements().size(), is(5));
    }

    @Test
    public void whenCreateComplexMenuFromElevenElementsThenHaveEleven() {

        List<MenuElement> list = new ArrayList<>();
        MenuElement parent1 = new MenuElement(1, "Задача");
        MenuElement parent2 = new MenuElement(2, "Задача");
        MenuElement parent3 = new MenuElement(3, "Задача");
        MenuElement parent11 = new MenuElement(parent1);
        MenuElement parent111 = new MenuElement(parent11);
        list.add(parent1);
        list.add(parent2);
        list.add(parent3);
        list.add(new MenuElement(parent2));
        list.add(new MenuElement(parent2));
        list.add(new MenuElement(parent11));
        list.add(new MenuElement(parent111));
        list.add(new MenuElement(parent1));
        list.add(new MenuElement(parent1));
        list.add(parent111);
        list.add(parent11);
        MenuOutput output = new MenuOutput(list);
        assertThat(output.getElements().size(), is(11));
    }
}
