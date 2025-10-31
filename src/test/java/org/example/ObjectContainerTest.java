package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тесты для класса ObjectContainer
 */
class ObjectContainerTest {

    private ObjectContainer container;

    @BeforeEach
    void setUp() {
        container = new ObjectContainer();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(0, container.size());
    }

    @Test
    void testAddSingleElement() {
        String testElement = "Test Element";
        container.add(testElement);

        assertEquals(1, container.size());
        assertEquals(testElement, container.get(0));
    }

    @Test
    void testAddMultipleElements() {
        container.add("First");
        container.add("Second");
        container.add("Third");

        assertEquals(3, container.size());
        assertEquals("First", container.get(0));
        assertEquals("Second", container.get(1));
        assertEquals("Third", container.get(2));
    }

    @Test
    void testAddDifferentTypes() {
        container.add("String");
        container.add(123);
        container.add(45.67);
        container.add(null);

        assertEquals(4, container.size());
        assertEquals("String", container.get(0));
        assertEquals(123, container.get(1));
        assertEquals(45.67, container.get(2));
        assertNull(container.get(3));
    }

    @Test
    void testGetValidIndex() {
        container.add("A");
        container.add("B");

        assertEquals("A", container.get(0));
        assertEquals("B", container.get(1));
    }

    @Test
    void testGetInvalidIndex() {
        container.add("Element");

        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(100));
    }

    @Test
    void testRemoveByIndex() {
        container.add("A");
        container.add("B");
        container.add("C");

        Object removed = container.remove(1);

        assertEquals("B", removed);
        assertEquals(2, container.size());
        assertEquals("A", container.get(0));
        assertEquals("C", container.get(1));
    }

    @Test
    void testRemoveFirstElement() {
        container.add("First");
        container.add("Second");

        Object removed = container.remove(0);

        assertEquals("First", removed);
        assertEquals(1, container.size());
        assertEquals("Second", container.get(0));
    }

    @Test
    void testRemoveLastElement() {
        container.add("First");
        container.add("Second");

        Object removed = container.remove(1);

        assertEquals("Second", removed);
        assertEquals(1, container.size());
        assertEquals("First", container.get(0));
    }

    @Test
    void testRemoveInvalidIndex() {
        container.add("Element");

        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(5));
    }

    @Test
    void testRemoveFromEmptyContainer() {
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(0));
    }

    @Test
    void testSize() {
        assertEquals(0, container.size());

        container.add("One");
        assertEquals(1, container.size());

        container.add("Two");
        assertEquals(2, container.size());

        container.remove(0);
        assertEquals(1, container.size());

        container.remove(0);
        assertEquals(0, container.size());
    }

    @Test
    void testResizeAutomatically() {
        // Изначальная емкость 5 элементов
        for (int i = 0; i < 5; i++) {
            container.add("Element " + i);
        }

        assertEquals(5, container.size());

        // Добавляем 6-й элемент - должен произойти resize
        container.add("Element 5");

        assertEquals(6, container.size());

        // Проверяем, что все элементы на месте
        for (int i = 0; i < 6; i++) {
            assertEquals("Element " + i, container.get(i));
        }
    }

    @Test
    void testMultipleResizes() {
        // Добавляем много элементов для проверки нескольких увеличений емкости
        for (int i = 0; i < 10; i++) {
            container.add(i);
        }

        assertEquals(10, container.size());

        // Проверяем, что все элементы сохранились
        for (int i = 0; i < 10; i++) {
            assertEquals(i, container.get(i));
        }
    }

    @Test
    void testToStringEmptyContainer() {
        assertEquals("[]", container.toString());
    }

    @Test
    void testToStringWithElements() {
        container.add("A");
        container.add("B");
        container.add("C");

        assertEquals("[A, B, C]", container.toString());
    }

    @Test
    void testToStringWithNullElements() {
        container.add("A");
        container.add(null);
        container.add("C");

        assertEquals("[A, null, C]", container.toString());
    }

    @Test
    void testComplexScenario() {
        // Комплексный тест: добавление, удаление, проверка порядка
        container.add("Start");
        container.add("Middle");
        container.add("End");

        assertEquals(3, container.size());
        assertEquals("[Start, Middle, End]", container.toString());

        // Удаляем средний элемент
        container.remove(1);

        assertEquals(2, container.size());
        assertEquals("[Start, End]", container.toString());

        // Добавляем новые элементы
        container.add("New End");

        assertEquals(3, container.size());
        assertEquals("[Start, End, New End]", container.toString());

        // Удаляем первый элемент
        container.remove(0);

        assertEquals(2, container.size());
        assertEquals("[End, New End]", container.toString());
    }

    @Test
    void testRemoveAllElements() {
        container.add("A");
        container.add("B");
        container.add("C");

        container.remove(0);
        container.remove(0);
        container.remove(0);

        assertEquals(0, container.size());
        assertEquals("[]", container.toString());

        // После удаления всех элементов можно снова добавлять
        container.add("New Element");
        assertEquals(1, container.size());
        assertEquals("New Element", container.get(0));
    }
}