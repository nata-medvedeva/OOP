package ru.nsu.nmedvedeva1.Task_1_2_1;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrixGraph extends AbstractGraph{
    private final List<Integer> vertices;
    private boolean[][] matrix;

    public AdjacencyMatrixGraph() {
        vertices = new ArrayList<>();
        matrix = new boolean[0][0];
    }

    @Override
    public void addVertex(int vertex) {
        if (vertices.contains(vertex)) {
            return;
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

    @Override
    public void removeVertex(int vertex) {
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
            for (int j = 0; j< oldSize; j++) {
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

    @Override
    public void addEdge(int from, int to) {
        if (!containsVertex(from) || !containsVertex(to)) {
            return;
        }

        if (containsEdge(from, to)) {
            return;
        }
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);

        matrix[fromIndex][toIndex] = true;
    }

    @Override
    public void removeEdge(int from, int to) {
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);
        if (fromIndex == -1 || toIndex == -1) {
            return;
        }

        matrix[fromIndex][toIndex] = false;
    }

    @Override
    public boolean containsVertex(int vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public boolean containsEdge(int from, int to) {
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);
        if (fromIndex == -1 || toIndex == -1) {
            return false;
        }

        return matrix[fromIndex][toIndex];
    }

    @Override
    public List<Integer> getVertices() {
        return new ArrayList<>(vertices);
    }

    @Override
    public List<Integer> getNeighbours(int vertex) {
        List<Integer> result = new ArrayList<>();
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

    @Override
    public List<Edge> getEdges() {
        List<Edge> result = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (matrix[i][j]){
                    result.add(new Edge(vertices.get(i), vertices.get(j)));
                }
            }
        }
        return result;
    }

    @Override
    public int edgeCount() {
        int counter = 0;
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (matrix[i][j]){
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public int vertexCount() {
        return vertices.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("   ");

        for (int vertex : vertices) {
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
