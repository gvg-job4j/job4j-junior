package ru.job4j.statistic;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 21.11.2018
 */
public class StoreTest {
    List<Store.User> prev = new ArrayList<>();
    List<Store.User> current = new ArrayList<>();

    @Before
    public void fillList() {
        prev.add(new Store.User(1, "Vova"));
        prev.add(new Store.User(2, "Dima"));
        prev.add(new Store.User(3, "Vasya"));
        prev.add(new Store.User(4, "Misha"));
        prev.add(new Store.User(5, "Sergey"));
        prev.add(new Store.User(6, "Pavel"));
        current.add(new Store.User(1, "Vova"));
        current.add(new Store.User(2, "Dima"));
        current.add(new Store.User(3, "Vasya"));
        current.add(new Store.User(4, "Misha"));
        current.add(new Store.User(5, "Sergey"));
        current.add(new Store.User(6, "Pavel"));
    }

    @Test
    public void whenAddTwoThenAddedTwo() {
        current.add(new Store.User(7, "user7"));
        current.add(new Store.User(8, "user8"));
        Info info = new Store().diff(prev, current);
        assertThat(info.getAdded(), is(2));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenAddTwoDeleteOneThenAddedTwoDeletedOne() {
        current.add(new Store.User(7, "user7"));
        current.add(new Store.User(8, "user8"));
        current.remove(1);
        Info info = new Store().diff(prev, current);
        assertThat(info.getAdded(), is(2));
        assertThat(info.getChanged(), is(0));
        assertThat(info.getDeleted(), is(1));
    }

    @Test
    public void whenChangeOneThenChangedOne() {
        Store.User user = current.get(1);
        user.setName("changed name");
        Info info = new Store().diff(prev, current);
        assertThat(info.getAdded(), is(0));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenAddChangeDeleteThenTrue() {
        current.add(new Store.User(7, "user7"));
        current.add(new Store.User(8, "user8"));
        current.remove(1);
        Store.User user = current.get(1);
        user.setName("changed name");
        Info info = new Store().diff(prev, current);
        assertThat(info.getAdded(), is(2));
        assertThat(info.getChanged(), is(1));
        assertThat(info.getDeleted(), is(1));
    }
}
