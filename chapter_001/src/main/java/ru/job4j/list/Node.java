package ru.job4j.list;

/**
 * Определение цикличности заданного связанного списка.
 *
 * @author Valeriy Gyrievskikh
 * @since 05.09.2018
 */
public class Node<T> {
    /**
     * Значение элемента списка.
     */
    T value;
    /**
     * Следующий элемент списка.
     */
    Node<T> next;

    /**
     * Метод устанавливает значение элемента списка.
     *
     * @param value Утанавливаемое значение.
     */
    Node(T value) {
        this.value = value;
    }

    /**
     * Метод проверяет наличие зацикленности в связанном списке.
     *
     * @param first Первый элемент списка.
     * @return true, если есть зацикленность, false, если нет.
     */
    static boolean hasCycle(Node first) {
        boolean isCycle = false;
        if (first.next != null) {
            Node slow = first;
            Node fast = first;
            while (true) {
                if (slow.next == null || fast == null || fast.next == null) {
                    break;
                }
                slow = slow.next;
                fast = fast.next.next;
                if (slow.equals(fast)) {
                    isCycle = true;
                    break;
                }
            }
        }
        return isCycle;
    }
}
