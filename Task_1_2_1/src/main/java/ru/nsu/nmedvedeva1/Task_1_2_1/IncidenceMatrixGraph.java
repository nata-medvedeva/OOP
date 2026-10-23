package ru.nsu.nmedvedeva1.Task_1_2_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Граф, который хранится как матрица инцидентности.
 * Строки — вершины, столбцы — рёбра.
 * -1 значит «ребро выходит из вершины», 1 значит «ребро входит в вершину».
 *
 * @param <T> тип вершины
 */
public class IncidenceMatrixGraph<T> extends AbstractGraph<T> {
    private final List<T> vertices;
    private final List<Edge<T>> edges;
    private int[][] matrix;

    /**
     * Создаёт пустой граф.
     */
    public IncidenceMatrixGraph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        matrix = new int[0][0];
    }

    /**
     * Добавляет вершину в граф.
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

        int oldVertexSize = vertices.size();
        int edgesSize = edges.size();

        int[][] newMatrix = new int[oldVertexSize + 1][edgesSize];
        for (int i = 0; i < oldVertexSize; i++) {
            for (int j = 0; j < edgesSize; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }

        vertices.add(vertex);
        matrix = newMatrix;
    }

    /**
     * Удаляет вершину и все рёбра, связанные с ней.
     *
     * @param vertex вершина
     */
    @Override
    public void removeVertex(T vertex) {
        if (!containsVertex(vertex)) {
            return;
        }

        List<Edge<T>> removable = new ArrayList<>();
        for (Edge<T> edge : edges) {
            if (Objects.equals(edge.getFrom(), vertex) || Objects.equals(edge.getTo(), vertex)) {
                removable.add(edge);
            }
        }

        for (Edge<T> edge : removable) {
            removeEdge(edge.getFrom(), edge.getTo());
        }

        int row = vertices.indexOf(vertex);
        int oldVertexSize = vertices.size();
        int[][] newMatrix = new int[oldVertexSize - 1][edges.size()];
        int newRow = 0;

        for (int oldRow = 0; oldRow < oldVertexSize; oldRow++) {
            if (oldRow == row) {
                continue;
            }

            for (int column = 0; column < edges.size(); column++) {
                newMatrix[newRow][column] = matrix[oldRow][column];
            }
            newRow++;
        }

        vertices.remove(row);
        matrix = newMatrix;
    }

    /**
     * Добавляет ребро из одной вершины в другую.
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

        int vertexCount = vertices.size();
        int oldEdgeCount = edges.size();
        int[][] newMatrix = new int[vertexCount][oldEdgeCount + 1];

        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < oldEdgeCount; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }

        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);
        newMatrix[fromIndex][oldEdgeCount] = -1;
        newMatrix[toIndex][oldEdgeCount] = 1;
        edges.add(new Edge<>(from, to));
        matrix = newMatrix;
    }

    /**
     * Удаляет ребро между вершинами.
     *
     * @param from начало ребра
     * @param to конец ребра
     */
    @Override
    public void removeEdge(T from, T to) {
        int column = -1;

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).equals(new Edge<>(from, to))) {
                column = i;
                break;
            }
        }

        if (column == -1) {
            return;
        }

        int vertexCount = vertices.size();
        int oldEdgeCount = edges.size();
        int[][] newMatrix = new int[vertexCount][oldEdgeCount - 1];

        for (int i = 0; i < vertexCount; i++) {
            int newColumn = 0;
            for (int j = 0; j < oldEdgeCount; j++) {
                if (j == column) {
                    continue;
                }
                newMatrix[i][newColumn] = matrix[i][j];
                newColumn++;
            }
        }
        edges.remove(column);
        matrix = newMatrix;
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
        return edges.contains(new Edge<>(from, to));
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

        for (int column = 0; column < edges.size(); column++) {
            if (matrix[row][column] != -1) {
                continue;
            }
            for (int orow = 0; orow < vertices.size(); orow++) {
                if (matrix[orow][column] == 1) {
                    result.add(vertices.get(orow));
                    break;
                }
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
        return new ArrayList<>(edges);
    }

    /**
     * Возвращает количество рёбер.
     *
     * @return число рёбер
     */
    @Override
    public int edgeCount() {
        return edges.size();
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
     * Первая строка — рёбра, дальше строки матрицы из -1, 0 и 1.
     *
     * @return строковое описание графа
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("   ");

        for (int i = 0; i < edges.size(); i++) {
            result.append("e");
            result.append(i + 1);
            result.append(" ");
        }
        result.append("\n");

        for (int row = 0; row < vertices.size(); row++) {
            result.append(vertices.get(row));
            result.append(": ");

            for (int column = 0; column < edges.size(); column++) {
                result.append(matrix[row][column]);
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
