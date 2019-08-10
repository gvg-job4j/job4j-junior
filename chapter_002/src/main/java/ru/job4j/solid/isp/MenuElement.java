package ru.job4j.solid.isp;

/**
 * @author Valeriy Gyrievskikh
 * @since 07.08.2019
 */
public class MenuElement implements Comparable<MenuElement> {
    /**
     * Родительский элемент меню.
     */
    private MenuElement parent;
    /**
     * Признак родительского элемента.
     */
    private boolean isParent;
    /**
     * Признак подчиненного элемента.
     */
    private boolean isChild;
    /**
     * Значение элемента.
     */
    private String value;
    /**
     * Индекс меню.
     */
    private int menuIndex;
    /**
     * Текущий используемый индекс.
     */
    private int usedIndex;
    /**
     * Выполняемое действие.
     */
    private MenuAction action;

    /**
     * Конструктор, добавляет новый элемент меню с указанным индексом.
     *
     * @param menuIndex Индекс элемента.
     * @param value     Значение элемента.
     */
    public MenuElement(int menuIndex, String value) {
        this.menuIndex = menuIndex;
        this.value = value;
    }

    /**
     * Конструктор, создает новый подчиненный элемент меню.
     *
     * @param parent Родительский элемент меню.
     */
    public MenuElement(MenuElement parent) {
        this.parent = parent;
        this.isChild = true;
        parent.setParentSign();
        this.value = "----" + parent.getValue();
        this.menuIndex = parent.getUsedIndex() + 1;
        parent.setUsedIndex(this.menuIndex);
    }

    /**
     * Метод возвращает родительский элемент.
     *
     * @return Родительский элемент.
     */
    public MenuElement getParent() {
        return parent;
    }

    /**
     * Метод устанавливает родительский элемент.
     *
     * @param parent Родительский элемент.
     */
    private void setParent(MenuElement parent) {
        this.parent = parent;
    }

    /**
     * Метод возвращает признак родительского элемента.
     *
     * @return Признак родительского элемента.
     */
    public boolean isParent() {
        return isParent;
    }

    /**
     * Метод устанавливает признак родительского элемента.
     */
    private void setParentSign() {
        isParent = true;
    }

    /**
     * Метод возвращает признак подчиненного элемента.
     *
     * @return Признак подчиненного элемента.
     */
    public boolean isChild() {
        return isChild;
    }

    /**
     * Метод устанавливает признак подчиненного элемента.
     */
    private void setChildSign() {
        isChild = true;
    }

    /**
     * Метод возвращает значение элемента.
     *
     * @return Значение элемента.
     */
    public String getValue() {
        return value;
    }

    /**
     * Метод устанавливает значение элемента.
     *
     * @param value Значение элемента.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Метод возвращает строковое представление индекса элемента.
     *
     * @return Строковое представление индекса элемента.
     */
    public String getStringMenuIndex() {
        String index = Integer.toString(menuIndex) + ".";
        if (parent != null) {
            index = parent.getStringMenuIndex() + menuIndex + ".";
        }
        return index;
    }

    /**
     * Метод возвращает индекс элемента.
     *
     * @return Индекс элемента.
     */
    public int getMenuIndex() {
        return menuIndex;
    }

    /**
     * Метод устанавливает индекс элемента.
     *
     * @param menuIndex Индекс элемента.
     */
    public void setMenuIndex(int menuIndex) {
        this.menuIndex = menuIndex;
    }

    /**
     * Метод возвращает используемый индекс.
     *
     * @return Используемый индекс.
     */
    public int getUsedIndex() {
        return usedIndex;
    }

    /**
     * Метод устанавливает используемый индекс.
     *
     * @param usedIndex Используемый индекс.
     */
    public void setUsedIndex(int usedIndex) {
        this.usedIndex = usedIndex;
    }

    /**
     * Метод возвращает действие элемента.
     *
     * @return Действие элемента.
     */
    public MenuAction getAction() {
        return action;
    }

    /**
     * Метод устанавливает действие элемента.
     *
     * @param action Действие элемента.
     */
    public void setAction(MenuAction action) {
        this.action = action;
    }

    /**
     * Метод возвращает представление значения элемента.
     *
     * @return Представление значения элемента.
     */
    public String showValue() {
        String value = "";
        if (parent == null) {
            value = this.value + " " + menuIndex + ".";
        } else {
            value = this.value + " " + parent.getStringMenuIndex() + menuIndex + ".";
        }
        return value;
    }

    /**
     * Метод выполняет сравнение с переданным элементом меню.
     *
     * @param element Переданный элемент меню.
     * @return Результат сравнения (1 - текущий элемент больше, -1 - меньше.)
     */
    @Override
    public int compareTo(MenuElement element) {
        int value = 0;
        if (menuIndex >= element.getMenuIndex()) {
            value = 1;
        } else {
            value = -1;
        }
        return value;
    }
}
