package ru.nsu.nmedvedeva1.Task_1_2_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdjacencyListGraphTest {
    @Test
    void addVertexTest() {
        Graph graph = new AdjacencyListGraph();

        graph.addVertex(1);
        graph.addVertex(2);

        assertTrue(graph.containsVertex(1));
        assertTrue(graph.containsVertex(2));
        assertEquals(2, graph.vertexCount());
    }

    @Test
    void duplicateVertexTest() {
        Graph graph = new AdjacencyListGraph();

        graph.addVertex(1);
        graph.addVertex(1);

        assertEquals(1, graph.vertexCount());
    }

    @Test
    void addEdgeTest() {
        Graph graph = new AdjacencyListGraph();

        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);

        assertTrue(graph.containsEdge(1, 2));
        assertFalse(graph.containsEdge(2, 1));
        assertEquals(1, graph.edgeCount());
    }

    @Test
    void duplicateEdgeTest() {
        Graph graph = new AdjacencyListGraph();

        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);
        graph.addEdge(1, 2);

        assertEquals(1, graph.edgeCount());
    }

    @Test
    void addEdgeUnknownVertexTest() {
        Graph graph = new AdjacencyListGraph();

        graph.addVertex(1);

        graph.addEdge(1, 2);

        assertFalse(graph.containsEdge(1, 2));
        assertEquals(0, graph.edgeCount());
    }

    @Test
    void removeEdgeTest() {
        Graph graph = new AdjacencyListGraph();

        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);
        graph.removeEdge(1, 2);

        assertFalse(graph.containsEdge(1, 2));
        assertEquals(0, graph.edgeCount());
    }
}
