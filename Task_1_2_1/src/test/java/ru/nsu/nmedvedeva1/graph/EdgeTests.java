package ru.nsu.nmedvedeva1.Task_1_2_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Тесты для проверки работы ребра.
 */
class EdgeTests {
    @Test
    void gettersTest() {
        Edge edge = new Edge(1, 2);

        assertEquals(1, edge.getFrom());
        assertEquals(2, edge.getTo());
    }

    @Test
    void equalEdgesTest() {
        Edge edge1 = new Edge(1, 2);
        Edge edge2 = new Edge(1, 2);

        assertEquals(edge1, edge2);
    }

    @Test
    void differentEdgesTest() {
        Edge edge1 = new Edge(1, 2);
        Edge edge2 = new Edge(1, 3);

        assertNotEquals(edge1, edge2);
    }

    @Test
    void differentEdges2Test() {
        Edge edge1 = new Edge(1, 2);
        Edge edge2 = new Edge(2, 1);

        assertNotEquals(edge1, edge2);
    }

    @Test
    void toStringTest() {
        Edge edge = new Edge(1, 2);

        assertEquals("1->2", edge.toString());
    }

    @Test
    void equalsNullTest() {
        Edge<Integer> e = new Edge<>(1, 2);
        assertNotEquals(e, null);
    }

    @Test
    void hashCodeEquals() {
        assertEquals(new Edge<>(1, 2).hashCode(), new Edge<>(1, 2).hashCode());
    }
}
