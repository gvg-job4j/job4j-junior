package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Valeriy Gyrievskikh
 * @since 15.08.2018
 */
public class StoreTest {

    @Test
    public void whenAddTwoRolesAndDeleteOne() {
        RoleStore<Role> roleStore = new RoleStore<>(2);
        Role role = new Role("123");
        roleStore.add(role);
        roleStore.add(role);
        assertThat(roleStore.get(1), is(role));
        roleStore.delete(0);
        assertNull(roleStore.get(1));
        assertThat(roleStore.get(0), is(role));
    }

    @Test
    public void whenSetUser() {
        UserStore<User> userStore = new UserStore<>(2);
        User user = new User("2");
        userStore.add(new User("1"));
        userStore.set(0, user);
        User result = userStore.get(0);
        assertThat(result.getId(), is(user.getId()));
    }

    @Test
    public void whenReplaceUserAndDeleteUser() {
        UserStore<User> userStore = new UserStore<>(2);
        User user = new User("2");
        userStore.add(new User("1"));
        userStore.replace("1", user);
        User result = userStore.get(0);
        assertThat(result.getId(), is(user.getId()));
        assertThat(userStore.delete("2"), is(true));
    }

    @Test
    public void whenDeleteRole() {
        RoleStore<Role> roleStore = new RoleStore<>(2);
        Role role = new Role("123");
        roleStore.add(role);
        roleStore.add(role);
        roleStore.delete(0);
        assertThat(roleStore.delete("123"), is(true));
        assertThat(roleStore.delete("123"), is(false));
    }

    @Test
    public void whenTryFindById() {
        RoleStore<Role> roleStore = new RoleStore<>(2);
        Role role = new Role("123");
        roleStore.add(role);
        assertThat(roleStore.findById("123"), is(role));
        assertNull(roleStore.findById("1234"));
    }
}
