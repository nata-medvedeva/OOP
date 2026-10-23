package ru.nsu.nmedvedeva1.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Граф, который хранится как матрица смежности.
 * Если между вершинами есть ребро, в матрице стоит true.
 *
 * @param <T> тип вершины
 */
public class AdjacencyMatrixGraph<T> extends AbstractGraph<T> {
    private final List<T> vertices;
    private boolean[][] matrix;

    /**
     * Создаём пустой граф.
     */
    public AdjacencyMatrixGraph() {
        vertices = new ArrayList<>();
        matrix = new boolean[0][0];
    }

    /**
     * Добавляем вершину в граф.
     *
     * @param vertex вершина
     */
    @Override
    public void addVertex(T vertex) {
        if (vertices.contains(vertex)) {
            return;
        }

        if (vertex == null) {
            throw new IllegalArgumentException("Vertex cannot be null");
        }

        int oldSize = vertices.size();

        boolean[][] newMatrix = new boolean[oldSize + 1][oldSize + 1];
        for (int i = 0; i < oldSize; i++) {
            for (int j = 0; j < oldSize; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }

        vertices.add(vertex);
        matrix = newMatrix;
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

        int oldSize = vertices.size();
        boolean[][] newMatrix = new boolean[oldSize - 1][oldSize - 1];

        int row = 0;
        for (int i = 0; i < oldSize; i++) {
            int column = 0;
            if (i == index) {
                continue;
            }
            for (int j = 0; j < oldSize; j++) {
                if (j == index) {
                    continue;
                }
                newMatrix[row][column] = matrix[i][j];
                column++;
            }
            row++;
        }
        vertices.remove(index);
        matrix = newMatrix;
    }

    /**
     * Добавляем ребро из одной вершины в другую.
     *
     * @param from начало ребра
     * @param to конец ребра
     */
    @Override
    public void addEdge(T from, T to) {
        if (!containsVertex(from) || !containsVertex(to)) {
            return;
        }

        if (Objects.equals(from, to)) {
            return;
        }

        if (containsEdge(from, to)) {
            return;
        }
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);

        matrix[fromIndex][toIndex] = true;
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
        int toIndex = vertices.indexOf(to);
        if (fromIndex == -1 || toIndex == -1) {
            return;
        }

        matrix[fromIndex][toIndex] = false;
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
     * Проверяет, есть ли ребро между вершинами.
     *
     * @param from начало ребра
     * @param to конец ребра
     * @return true, если ребро есть
     */
    @Override
    public boolean containsEdge(T from, T to) {
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);
        if (fromIndex == -1 || toIndex == -1) {
            return false;
        }

        return matrix[fromIndex][toIndex];
    }

    /**
     * Возвращает список всех вершин.
     *
     * @return копия списка вершин
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
        List<T> result = new ArrayList<>();
        int row = vertices.indexOf(vertex);

        if (row == -1) {
            return result;
        }

        for (int column = 0; column < vertices.size(); column++) {
            if (matrix[row][column]) {
                 result.add(vertices.get(column));
            }
        }
        return result;
    }

    /**
     * Возвращает список всех рёбер.
     *
     * @return список рёбер
     */
    @Override
    public List<Edge<T>> getEdges() {
        List<Edge<T>> result = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (matrix[i][j]) {
                    result.add(new Edge<>(vertices.get(i), vertices.get(j)));
                }
            }
        }
        return result;
    }

    /**
     * Возвращает количество рёбер.
     *
     * @return число рёбер
     */
    @Override
    public int edgeCount() {
        int counter = 0;
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (matrix[i][j]) {
                    counter++;
                }
            }
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
     * Первая строка — вершины, дальше строки матрицы из 0 и 1.
     *
     * @return строковое описание графа
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("   ");

        for (T vertex : vertices) {
            result.append(vertex);
            result.append(" ");
        }
        result.append("\n");

        for (int row = 0; row < vertices.size(); row++) {
            result.append(vertices.get(row));
            result.append(": ");

            for (int column = 0; column < vertices.size(); column++) {
                if (matrix[row][column]) {
                    result.append("1 ");
                } else {
                    result.append("0 ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}
