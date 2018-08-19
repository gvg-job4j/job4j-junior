package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
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
        roleStore.delete(1);
        Role roleFromStore = (Role) roleStore.get(0);
        assertThat(roleFromStore.getId(), is(role.getId()));
    }

    @Test
    public void whenSetUser() {
        UserStore<User> userStore = new UserStore<>(2);
        User user = new User("2");
        userStore.add(new User("1"));
        userStore.set(0, user);
        User userFromStore = (User) userStore.get(0);
        assertThat(userFromStore.getId(), is(user.getId()));
    }
}
