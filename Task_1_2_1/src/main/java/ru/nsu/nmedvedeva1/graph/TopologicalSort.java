package ru.nsu.nmedvedeva1.Task_1_2_1;

import java.util.List;

/**
 * Интерфейс топологической сортировки графа.
 *
 * @param <T> тип вершины
 */
public interface TopologicalSort<T> {

    /**
     * Сортирует вершины графа так, чтобы для каждого ребра начало шло раньше конца.
     *
     * @param graph граф
     */
    List<T> sort(Graph<T> graph);
}
