package org.example;

/**
 * Класс-контейнер для хранения произвольного количества объектов.
 * Реализован на основе массива с динамическим расширением.
 * Не использует встроенные коллекции Java.
 *
 * @author Oleg Yastreb
 * @version 1.0
 */
public class ObjectContainer {
    private Object[] elements;
    private int size;

    /**
     * Создает новый контейнер с начальной емкостью 5 элементов.
     */
    public ObjectContainer() {
        this.elements = new Object[5];
        this.size = 0;
    }

    /**
     * Добавляет элемент в контейнер.
     * Если контейнер заполнен, его емкость автоматически увеличивается.
     *
     * @param element элемент для добавления в контейнер
     */
    public void add(Object element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    /**
     * Удаляет элемент по указанному индексу и возвращает его.
     * Все последующие элементы сдвигаются влево.
     *
     * @param index индекс удаляемого элемента (от 0 до size-1)
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за границы контейнера
     */
    public Object remove(int index) {
        checkIndex(index);
        Object removedElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return removedElement;
    }

    /**
     * Возвращает текущее количество элементов в контейнере.
     *
     * @return количество элементов в контейнере
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, есть ли такой индекс.
     *
     * @param index проверяемый индекс
     * @throws IndexOutOfBoundsException если индекс выходит за границы контейнера
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param индекс запрашиваемого элемента (от 0 до size-1)
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы контейнера
     */
    public Object get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
     * Увеличивает емкость внутреннего массива на 1 элемент.
     * Используется автоматически при добавлении элементов, когда контейнер заполнен.
     */
    private void resize() {
        Object[] newArray = new Object[elements.length + 1];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    /**
     * Возвращает строковое представление контейнера в формате [element1, element2, ...].
     *
     * @return строковое представление контейнера
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}