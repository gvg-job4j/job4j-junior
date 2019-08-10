package ru.job4j.solid.isp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Valeriy Gyrievskikh
 * @since 07.08.2019
 */
public class MenuOutput {

    /**
     * Список элементов меню.
     */
    private List<MenuElement> elements;

    /**
     * Сформированное меню.
     */
    private List<MenuElement> menu;

    /**
     * Конструктор, формирует меню из списка элементов.
     *
     * @param elements Список элементов меню.
     */
    public MenuOutput(List<MenuElement> elements) {
        this.elements = elements;
    }

    /**
     * Метод выводит меню на экран.
     */
    public void showMenu() {
        menu = createMenuFromList(elements);
        for (MenuElement element : menu) {
            System.out.println(element.showValue());
        }
    }

    /**
     * Метод возвращает список элементов меню.
     *
     * @return
     */
    public List<MenuElement> getElements() {
        return elements;
    }

    /**
     * Метод создает дерево элементов меню из переданного списка элементов.
     *
     * @param elements Список элементов.
     * @return Дерево элементов меню.
     */
    private List<MenuElement> createMenuFromList(List<MenuElement> elements) {
        List<MenuElement> parentElements = new ArrayList<>();
        List<MenuElement> sortedElements = new ArrayList<>();
        for (MenuElement element : elements) {
            if (element.getParent() == null) {
                parentElements.add(element);
            }
        }
        if (parentElements.size() > 0) {
            addElementsToSortedList(sortedElements, parentElements);
        }
        return sortedElements;
    }

    /**
     * Метод заполняет отсортированный список по списку элементов-родетелей.
     *
     * @param sortedElements Отсортированный список.
     * @param parentElements Список элементов-родителей.
     */
    private void addElementsToSortedList(List<MenuElement> sortedElements, List<MenuElement> parentElements) {
        Collections.sort(parentElements);
        for (MenuElement parentEelement : parentElements) {
            List<MenuElement> childElements = new ArrayList<>();
            for (MenuElement element : elements) {
                if (element.getParent() == parentEelement) {
                    childElements.add(element);
                }
            }
            sortedElements.add(parentEelement);
            if (childElements.size() > 0) {
                addElementsToSortedList(sortedElements, childElements);
            }
        }
    }
}
