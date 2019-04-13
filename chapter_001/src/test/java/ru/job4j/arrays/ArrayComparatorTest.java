package ru.job4j.arrays;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 13.04.2019
 */
public class ArrayComparatorTest {

    @Test
    public void whenContentOfTwoStringArraysEqualsThenTrue() {
        ArrayComparator comparator = new ArrayComparator();
        assertTrue(comparator.compare(new String[]{"m", "a", "m", "a"}, new String[]{"a", "m", "a", "m"}));
    }

    @Test
    public void whenContentOfTwoStringArraysNotEqualsThenFalse() {
        ArrayComparator comparator = new ArrayComparator();
        assertFalse(comparator.compare(new String[]{"m", "a", "m", "s"}, new String[]{"a", "m", "a", "m"}));
    }

    @Test
    public void whenContentOfTwoIntegerArraysEqualsThenTrue() {
        ArrayComparator comparator = new ArrayComparator();
        assertTrue(comparator.compare(new Integer[]{1, 2, 3, 3}, new Integer[]{3, 2, 3, 1}));
    }
}
