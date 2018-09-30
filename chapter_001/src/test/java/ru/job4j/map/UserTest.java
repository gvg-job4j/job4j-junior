package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Valeriy Gyrievskikh
 * @since 25.09.2018
 */
public class UserTest {

    @Test
    public void createTwoUsersMap() {
        Calendar birthday = new GregorianCalendar(2000, 01, 01);
        Map<User, Object> userMap = new HashMap<>();
        User user1 = new User("Vova", 1, birthday);
        User user2 = new User("Vova", 1, birthday);
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        userMap.put(user1, new Object());
        userMap.put(user2, new Object());
        System.out.println(userMap.toString());
    }
}
