package ru.nsu.nmedvedeva1.graph;

import java.util.List;

/**
 * Общий интерфейс графа.
 * Граф хранит вершины и направленные рёбра между ними.
 *
 * @param <T> тип вершины
 */
public interface Graph<T> {

    /**
     * Добавляет вершину в граф.
     * Если такая вершина уже есть, ничего не делает.
     *
     * @param vertex вершина
     */
    void addVertex(T vertex);

    /**
     * Удаляет вершину и все рёбра, связанные с ней.
     *
     * @param vertex вершина
     */
    void removeVertex(T vertex);

    /**
     * Добавляет ребро из одной вершины в другую.
     * Если вершины не существуют или ребро уже есть, ничего не делает.
     *
     * @param from начало ребра
     * @param to конец ребра
     */
    void addEdge(T from, T to);

    /**
     * Удаляет ребро между вершинами.
     *
     * @param from начало ребра
     * @param to конец ребра
     */
    void removeEdge(T from, T to);

    /**
     * Проверяет, есть ли вершина в графе.
     *
     * @param vertex вершина
     * @return true, если вершина есть
     */
    boolean containsVertex(T vertex);

    /**
     * Проверяет, есть ли ребро между вершинами.
     *
     * @param from начало ребра
     * @param to конец ребра
     * @return true, если ребро есть
     */
    boolean containsEdge(T from, T to);

    /**
     * Возвращает список всех вершин.
     *
     * @return копия списка вершин
     */
    List<T> getVertices();

    /**
     * Возвращает соседей вершины, т.е те, в которые идут рёбра из данной.
     *
     * @param vertex вершина
     * @return список соседей
     */
    List<T> getNeighbours(T vertex);

    /**
     * Возвращает список всех рёбер.
     *
     * @return список рёбер
     */
    List<Edge<T>> getEdges();

    /**
     * Возвращает количество рёбер.
     *
     * @return число рёбер
     */
    int edgeCount();

    /**
     * Возвращает количество вершин.
     *
     * @return число вершин
     */
    int vertexCount();
}
