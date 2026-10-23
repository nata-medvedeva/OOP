package ru.nsu.nmedvedeva1.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для проверки работы матрицы инцидентности.
 */
class IncidenceMatrixGraphTests {
    @Test
    void addVertexTest() {
        Graph graph = new IncidenceMatrixGraph();

        graph.addVertex(1);
        graph.addVertex(2);

        assertTrue(graph.containsVertex(1));
        assertTrue(graph.containsVertex(2));

        assertEquals(2, graph.vertexCount());
    }

    @Test
    void duplicateVertexTest() {
        Graph graph = new IncidenceMatrixGraph();

        graph.addVertex(1);
        graph.addVertex(1);

        assertEquals(1, graph.vertexCount());
    }

    @Test
    void addEdgeTest() {
        Graph graph = new IncidenceMatrixGraph();

        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);

        assertTrue(graph.containsEdge(1, 2));
        assertFalse(graph.containsEdge(2, 1));

        assertEquals(1, graph.edgeCount());
    }

    @Test
    void duplicateEdgeTest() {
        Graph graph = new IncidenceMatrixGraph();

        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);
        graph.addEdge(1, 2);

        assertEquals(1, graph.edgeCount());
    }

    @Test
    void removeEdgeTest() {
        Graph graph = new IncidenceMatrixGraph();

        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);
        graph.removeEdge(1, 2);

        assertFalse(graph.containsEdge(1, 2));
        assertEquals(0, graph.edgeCount());
    }

    @Test
    void removeVertexTest() {
        Graph<Integer> g = new IncidenceMatrixGraph();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        g.removeVertex(2);

        assertEquals(2, g.vertexCount());
        assertEquals(0, g.edgeCount());
        assertFalse(g.containsVertex(2));
        assertFalse(g.containsEdge(1, 2));
        assertFalse(g.containsEdge(2, 3));
    }

    @Test
    void removeUnknownVertexTest() {
        Graph<Integer> g = new IncidenceMatrixGraph();
        g.addVertex(1);
        g.removeVertex(99);
        assertEquals(1, g.vertexCount());
    }

    @Test
    void noLoopsTest() {
        Graph<Integer> g = new IncidenceMatrixGraph();
        g.addVertex(1);
        g.addEdge(1, 1);
        assertEquals(0, g.edgeCount());
    }

    @Test
    void toStringTest() {
        Graph<Integer> g = new IncidenceMatrixGraph();
        g.addVertex(1);
        g.addVertex(2);
        g.addEdge(1, 2);
        assertTrue(g.toString().contains("1"));
        assertTrue(g.toString().contains("2"));
    }
}
