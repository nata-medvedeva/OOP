package ru.nsu.nmedvedeva1.Task_1_2_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdjacencyMatrixGraphTest {
    @Test
    void addVertexTest() {
        Graph graph = new AdjacencyMatrixGraph();

        graph.addVertex(10);
        graph.addVertex(20);

        assertTrue(graph.containsVertex(10));
        assertTrue(graph.containsVertex(20));
        assertEquals(2, graph.vertexCount());
    }

    @Test
    void duplicateVertexTest() {
        Graph graph = new AdjacencyMatrixGraph();

        graph.addVertex(10);
        graph.addVertex(10);

        assertEquals(1, graph.vertexCount());
    }

    @Test
    void addEdgeTest() {
        Graph graph = new AdjacencyMatrixGraph();

        graph.addVertex(10);
        graph.addVertex(20);

        graph.addEdge(10, 20);

        assertTrue(graph.containsEdge(10, 20));
        assertFalse(graph.containsEdge(20, 10));
        assertEquals(1, graph.edgeCount());
    }

    @Test
    void duplicateEdgeTest() {
        Graph graph = new AdjacencyMatrixGraph();

        graph.addVertex(10);
        graph.addVertex(20);

        graph.addEdge(10, 20);
        graph.addEdge(10, 20);

        assertEquals(1, graph.edgeCount());
    }

    @Test
    void removeEdgeTest() {
        Graph graph = new AdjacencyMatrixGraph();

        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2);
        graph.removeEdge(1, 2);

        assertFalse(graph.containsEdge(1, 2));
    }
}
