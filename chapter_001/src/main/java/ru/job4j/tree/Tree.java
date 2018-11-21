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
     * Конструктор, создает дерево с корнем, содержащим переданное значение.
     *
     * @param element Значение элемента.
     */
    public Tree(E element) {
        this.root = new Node<>(element);
    }

    /**
     * Метод определяет бинарность дерева.
     *
     * @return Результат проверки.
     */
    public boolean isBinary() {
        boolean isBinary = true;
        Iterator<E> newIterator = iterator();
        while (newIterator.hasNext()) {
            Optional<Node<E>> optional = this.findBy(newIterator.next());
            if (optional.isPresent() && optional.get().leaves().size() > 2) {
                isBinary = false;
                break;
            }
        }
        return isBinary;
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
             * Счетчик значений.
             */
            private int currentCount;
            /**
             * Текущий проверяемый корень дерева.
             */
            private Node<E> currentRoot = root;
            /**
             * Список проверенных узлов.
             */
            private List<Node<E>> lookedNodes = new ArrayList<>();
            /**
             * Счетчик проверенных узлов.
             */
            private int treeCount;
            /**
             * Счетчик подчиненных элементов проверяемого узла.
             */
            private int treeChildrenCount;
            /**
             * Текущий проверяемый элемент.
             */
            private Node<E> treeNode;

            /**
             * Метод проверяет наличие следующего элемента дерева.
             *
             * @return Результат проверки.
             */
            @Override
            public boolean hasNext() {
                return this.currentCount <= size;
            }

            /**
             * Метод возвращает следующий элемент дерева.
             *
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
                return getElement(this.currentCount++);
            }

            /**
             * Метод получает следующий элемент дерева.
             *
             * @param count Текущий номер элемента.
             * @return Значение элемента или null.
             */
            private E getElement(int count) {
                E value = null;
                if (count == 0) {
                    value = this.currentRoot.getValue();
                    this.lookedNodes.add(this.currentRoot);
                } else {
                    this.treeNode = null;
                    while (this.treeCount < count) {
                        this.treeCount++;
                        if (this.treeChildrenCount < this.currentRoot.leaves().size()) {
                            this.treeNode = this.currentRoot.leaves().get(this.treeChildrenCount++);
                        } else {
                            this.lookedNodes.add(this.currentRoot);
                            lookForNewCurrentNode(this.currentRoot);
                        }
                        if (this.treeNode == null) {
                            this.lookedNodes.add(this.currentRoot);
                            lookForNewCurrentNode(root);
                        }
                        value = this.treeNode.getValue();
                    }
                }
                return value;
            }

            /**
             * Метод устанавливает новый корень дерева из еще не проверенных узлов.
             *
             * @param rootNode Новый корень.
             */
            private void lookForNewCurrentNode(Node<E> rootNode) {
                for (Node<E> node : this.lookedNodes) {
                    for (Node<E> childNode : node.leaves()) {
                        if (!this.lookedNodes.contains(childNode) && childNode.leaves().size() > 0) {
                            this.currentRoot = childNode;
                            this.treeChildrenCount = 0;
                            this.treeNode = this.currentRoot.leaves().get(this.treeChildrenCount++);
                            break;
                        }
                    }
                    if (!rootNode.equals(this.currentRoot)) {
                        break;
                    }
                }
            }
        };
    }
}