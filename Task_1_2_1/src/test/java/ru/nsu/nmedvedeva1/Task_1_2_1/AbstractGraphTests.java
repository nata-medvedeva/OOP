package ru.nsu.nmedvedeva1.Task_1_2_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для проверки работы абстрактного класса граф.
 */
class AbstractGraphTests {

    private void fill(Graph<Integer> g) {
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
    }

    @Test
    void equalsSameObject() {
        Graph<Integer> g = new AdjacencyListGraph<>();
        fill(g);
        assertTrue(g.equals(g));
    }

    @Test
    void equalsNull() {
        Graph<Integer> g = new AdjacencyListGraph<>();
        fill(g);
        assertFalse(g.equals(null));
    }

    @Test
    void equalsDifferentVertexCount() {
        Graph<Integer> a = new AdjacencyListGraph<>();
        Graph<Integer> b = new AdjacencyListGraph<>();
        fill(a);
        b.addVertex(1);
        assertNotEquals(a, b);
    }

    @Test
    void equalsDifferentEdgeCount() {
        Graph<Integer> a = new AdjacencyListGraph<>();
        Graph<Integer> b = new AdjacencyListGraph<>();
        a.addVertex(1);
        a.addVertex(2);
        a.addEdge(1, 2);
        b.addVertex(1);
        b.addVertex(2);
        assertNotEquals(a, b);
    }

    @Test
    void equalsDifferentVertices() {
        Graph<Integer> a = new AdjacencyListGraph<>();
        Graph<Integer> b = new AdjacencyListGraph<>();
        a.addVertex(1);
        a.addVertex(2);
        a.addEdge(1, 2);
        b.addVertex(3);
        b.addVertex(4);
        b.addEdge(3, 4);
        assertNotEquals(a, b);
    }

    @Test
    void equalsDifferentEdges() {
        Graph<Integer> a = new AdjacencyListGraph<>();
        Graph<Integer> b = new AdjacencyListGraph<>();
        a.addVertex(1);
        a.addVertex(2);
        a.addVertex(3);
        a.addEdge(1, 2);
        b.addVertex(1);
        b.addVertex(2);
        b.addVertex(3);
        b.addEdge(2, 3);
        assertNotEquals(a, b);
    }

    @Test
    void equalsDifferentRepresentation() {
        Graph<Integer> list = new AdjacencyListGraph<>();
        Graph<Integer> matrix = new AdjacencyMatrixGraph<>();
        Graph<Integer> incidence = new IncidenceMatrixGraph<>();
        fill(list);
        fill(matrix);
        fill(incidence);

        assertEquals(list, matrix);
        assertEquals(matrix, incidence);
        assertEquals(list, incidence);
    }

    @Test
    void hashCodeEqualForEqualGraphs() {
        Graph<Integer> list = new AdjacencyListGraph<>();
        Graph<Integer> matrix = new AdjacencyMatrixGraph<>();
        fill(list);
        fill(matrix);
        assertEquals(list.hashCode(), matrix.hashCode());
    }
}