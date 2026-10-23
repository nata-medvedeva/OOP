package ru.nsu.nmedvedeva1.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тесты для проверки работы чтения из файла.
 */
class GraphReaderTests {

    @Test
    void readIntegerGraph() throws Exception {
        Graph<Integer> g = new AdjacencyListGraph<>();
        GraphReader.read(g, "integer_graph.txt", Integer::parseInt);

        assertEquals(5, g.vertexCount());
        assertEquals(5, g.edgeCount());
        assertTrue(g.containsVertex(1));
        assertTrue(g.containsVertex(5));
        assertTrue(g.containsEdge(1, 2));
        assertTrue(g.containsEdge(1, 3));
        assertTrue(g.containsEdge(2, 4));
        assertTrue(g.containsEdge(3, 4));
        assertTrue(g.containsEdge(4, 5));
    }

    @Test
    void readStringGraph() throws Exception {
        Graph<String> g = new AdjacencyListGraph<>();
        GraphReader.read(g, "string_graph.txt", s -> s);

        assertEquals(4, g.vertexCount());
        assertEquals(4, g.edgeCount());
        assertTrue(g.containsVertex("A"));
        assertTrue(g.containsVertex("D"));
        assertTrue(g.containsEdge("A", "B"));
        assertTrue(g.containsEdge("A", "C"));
        assertTrue(g.containsEdge("B", "D"));
        assertTrue(g.containsEdge("C", "D"));
    }

    @Test
    void readIntoMatrixGraph() throws Exception {
        Graph<Integer> g = new AdjacencyMatrixGraph<>();
        GraphReader.read(g, "integer_graph.txt", Integer::parseInt);

        assertEquals(5, g.vertexCount());
        assertEquals(5, g.edgeCount());
        assertTrue(g.containsEdge(4, 5));
    }

    @Test
    void readIntoIncidenceGraph() throws Exception {
        Graph<Integer> g = new IncidenceMatrixGraph<>();
        GraphReader.read(g, "integer_graph.txt", Integer::parseInt);

        assertEquals(5, g.vertexCount());
        assertEquals(5, g.edgeCount());
        assertTrue(g.containsEdge(2, 4));
    }
}