package ru.nsu.nmedvedeva1.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Топологическая сортировка через поиск в глубину.
 * Обходит граф и складывает вершины в обратном порядке.
 *
 * @param <T> тип вершины
 */
public class Dfs<T> implements TopologicalSort<T> {

    /**
     * Сортирует вершины графа.
     * Если в графе есть цикл, бросает исключение.
     *
     * @param graph граф
     * @return список вершин в топологическом порядке
     */
    @Override
    public List<T> sort(Graph<T> graph) {
        List<T> vertices = graph.getVertices();
        int[] colour = new int[vertices.size()];
        List<T> result = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            if (colour[i] == 0) {
                boolean cycle = dfs(vertices.get(i), graph, vertices, colour, result);
                if (cycle) {
                    throw new IllegalStateException("Graph have a cycle");
                }
            }
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * Обходит граф в глубину из одной вершины.
     *
     * @param vertex текущая вершина
     * @param graph граф
     * @param vertices список всех вершин
     * @param colour состояния вершин: 0 — не посещена, 1 — в процессе, 2 — обработана
     * @param result список для результата
     * @return true, если найден цикл
     */
    private boolean dfs(T vertex, Graph<T> graph, List<T> vertices, int[] colour, List<T> result) {
        int vertexIndex = vertices.indexOf(vertex);
        colour[vertexIndex] = 1;
        for (T neighbour : graph.getNeighbours(vertex)) {
            int neighbourIndex = vertices.indexOf(neighbour);
            if (colour[neighbourIndex] == 1) {
                return true;
            }

            if (colour[neighbourIndex] == 0) {
                if (dfs(neighbour, graph, vertices, colour, result)) {
                    return true;
                }
            }
        }
        colour[vertexIndex] = 2;
        result.add(vertex);
        return false;
    }
}