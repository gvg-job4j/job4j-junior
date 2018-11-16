package ru.job4j.tree;

import java.util.*;

/**
 * @author Valeriy Gyrievskikh
 * @since 11.11.2018
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Корневой элемент дерева.
     */
    private Node<E> root;
    /**
     * Текущий размер дерева.
     */
    private int size;
    /**
     * Счетчик изменений дерева.
     */
    private int modCount;
    /**
     * Список элементов дерева.
     */
    private List<E> elements = new ArrayList<>();

    /**
     * Конструктор, создает дерево с корнем, содержащим переданное значение.
     *
     * @param element Значение элемента.
     */
    public Tree(E element) {
        this.root = new Node<>(element);
        this.elements.add(element);
    }

    /**
     * Метод создает элемент дерева и добавляет его список
     * подчиненных в элемент, содержащий указанное значение.
     *
     * @param parent Значение главного элемента.
     * @param child  Значение подчиненного элемента.
     * @return Результат добавления.
     */
    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> optional = this.findBy(parent);
        return (optional.isPresent() && addChildrenNode(optional.get(), child));
    }

    /**
     * Метод создает элемент дерева и добавляет его список
     * подчиненных в элемент, содержащий указанное значение.
     *
     * @param parent Значение главного элемента.
     * @param child  Значение подчиненного элемента.
     * @return Результат добавления.
     */
    private boolean addChildrenNode(Node<E> parent, E child) {
        boolean added = false;
        Node<E> childNode = new Node<>(child);
        if (!parent.leaves().contains(childNode)) {
            parent.add(childNode);
            this.elements.add(child);
            added = true;
            this.size++;
            this.modCount++;
        }
        return added;
    }

    /**
     * Метод выполняет поиск элемента дерева по значению.
     *
     * @param value Значение элемента.
     * @return Найденный элемент или пустой элемент.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Метод создает новый итератор для дерева.
     *
     * @return Новый итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Счетчик изменений на момент создания итератора.
             */
            private final int currentModCount = modCount;
            /**
             * Счетчик элементов.
             */
            private int currentCount;

            /**
             * Метод проверяет наличие следующего элемента дерева.
             * @return Результат проверки.
             */
            @Override
            public boolean hasNext() {
                return this.currentCount <= size;
            }

            /**
             * Метод ищет следующий элемент дерева.
             * @return Следующий элемент.
             */
            @Override
            public E next() {
                if (this.currentModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements.get(currentCount++);
            }
        };
    }
}
