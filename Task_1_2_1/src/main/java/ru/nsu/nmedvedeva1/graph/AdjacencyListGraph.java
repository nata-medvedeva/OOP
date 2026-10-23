package ru.nsu.nmedvedeva1.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Граф, который хранится как список смежности.
 * Для каждой вершины хранится список её соседей.
 *
 * @param <T> тип вершины
 */
public class AdjacencyListGraph<T> extends AbstractGraph<T> {
    private final List<T> vertices;
    private final List<List<T>> adjacency;

    /**
     * Создаём пустой граф.
     */
    public AdjacencyListGraph() {
        vertices = new ArrayList<>();
        adjacency = new ArrayList<>();
    }

    /**
     * Добавляем вершину в граф.
     *
     * @param vertex вершина
     */
    @Override
    public void addVertex(T vertex) {
        if (containsVertex(vertex)) {
            return;
        }

        if (vertex == null) {
            throw new IllegalArgumentException("Vertex cannot be null");
        }

        vertices.add(vertex);
        adjacency.add(new ArrayList<>());
    }

    /**
     * Удаляем вершину и все рёбра, связанные с ней.
     *
     * @param vertex вершина
     */
    @Override
    public void removeVertex(T vertex) {
        int index = vertices.indexOf(vertex);
        if (index == -1) {
            return;
        }

        vertices.remove(index);
        adjacency.remove(index);

        for (List<T> edges : adjacency) {
            edges.remove(vertex);
        }
    }

    /**
     * Добавляем ребро из одной вершины в другую.
     *
     * @param from начало ребра
     * @param to конец ребра
     */
    @Override
    public void addEdge(T from, T to) {
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);
        if (fromIndex == -1 || toIndex == -1) {
            return;
        }

        if (Objects.equals(from, to)) {
            return;
        }

        if (!adjacency.get(fromIndex).contains(to)) {
            adjacency.get(fromIndex).add(to);
        }
    }

    /**
     * Удаляем ребро между вершинами.
     *
     * @param from начало ребра
     * @param to конец ребра
     */
    @Override
    public void removeEdge(T from, T to) {
        int fromIndex = vertices.indexOf(from);
        if (fromIndex == -1) {
            return;
        }

        if (adjacency.get(fromIndex).contains(to)) {
            adjacency.get(fromIndex).remove(to);
        }
    }

    /**
     * Проверяет, есть ли ребро между вершинами.
     *
     * @param from начало ребра
     * @param to конец ребра
     * @return true, если ребро есть
     */
    @Override
    public boolean containsEdge(T from, T to) {
        int fromIndex = vertices.indexOf(from);
        if (fromIndex == -1) {
            return false;
        }

        return adjacency.get(fromIndex).contains(to);
    }

    /**
     * Проверяет, есть ли вершина в графе.
     *
     * @param vertex вершина
     * @return true, если вершина есть
     */
    @Override
    public boolean containsVertex(T vertex) {
        return vertices.contains(vertex);
    }

    /**
     * Возвращает список всех вершин.
     *
     * @return список вершин
     */
    @Override
    public List<T> getVertices() {
        return new ArrayList<>(vertices);
    }

    /**
     * Возвращает соседей вершины.
     *
     * @param vertex вершина
     * @return список соседей
     */
    @Override
    public List<T> getNeighbours(T vertex) {
        int index = vertices.indexOf(vertex);
        if (index == -1) {
            return new ArrayList<>();
        }

        return new ArrayList<>(adjacency.get(index));
    }

    /**
     * Возвращает список всех рёбер.
     *
     * @return список рёбер
     */
    @Override
    public List<Edge<T>> getEdges() {
        List<Edge<T>> edges = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            T from = vertices.get(i);
            for (T to : adjacency.get(i)) {
                edges.add(new Edge<>(from, to));
            }
        }

        return edges;
    }

    /**
     * Возвращает количество рёбер.
     *
     * @return число рёбер
     */
    @Override
    public int edgeCount() {
        int counter = 0;
        for (List<T> neighbours : adjacency) {
            counter += neighbours.size();
        }

        return counter;
    }

    /**
     * Возвращает количество вершин.
     *
     * @return число вершин
     */
    @Override
    public int vertexCount() {
        return vertices.size();
    }

    /**
     * Возвращает граф в виде строки.
     * Каждая строка — вершина и её соседи.
     *
     * @return строковое описание графа
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < vertices.size(); i++) {
            result.append(vertices.get(i));
            result.append(": ");

            for (T neighbor : adjacency.get(i)) {
                result.append(neighbor);
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
