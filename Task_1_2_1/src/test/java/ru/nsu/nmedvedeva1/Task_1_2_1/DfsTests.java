package ru.nsu.nmedvedeva1.Task_1_2_1;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Тесты для проверки работы dfs.
 */
class DfsTest {
    private Graph createChain(Graph graph) {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        return graph;
    }

    @Test
    void adjacencyListGraphSortTest() {
        Graph graph = createChain(new AdjacencyListGraph());
        TopologicalSort sort = new Dfs();
        assertEquals(List.of(1, 2, 3, 4), sort.sort(graph));
    }

    @Test
    void adjacencyMatrixGraphSortTest() {
        Graph graph = createChain(new AdjacencyMatrixGraph());
        TopologicalSort sort = new Dfs();
        assertEquals(List.of(1, 2, 3, 4), sort.sort(graph));
    }

    @Test
    void incidenceMatrixGraphSortTest() {
        Graph graph = createChain(new IncidenceMatrixGraph());
        TopologicalSort sort = new Dfs();
        assertEquals(List.of(1, 2, 3, 4), sort.sort(graph));
    }

    @Test
    void cycleTest() {
        Graph graph = new AdjacencyListGraph();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        TopologicalSort sort = new Dfs();

        assertThrows(RuntimeException.class, () -> sort.sort(graph));
    }
}
