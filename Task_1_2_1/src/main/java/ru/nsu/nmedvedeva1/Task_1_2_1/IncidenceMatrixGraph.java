package ru.nsu.nmedvedeva1.Task_1_2_1;

import java.util.ArrayList;
import java.util.List;

public class IncidenceMatrixGraph extends AbstractGraph{
    private final List<Integer> vertices;
    private final List<Edge> edges;
    private int[][] matrix;

    public IncidenceMatrixGraph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        matrix = new int[0][0];
    }

    @Override
    public void addVertex(int vertex) {
        if (vertices.contains(vertex)) {
            return;
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

    @Override
    public void removeVertex(int vertex) {
        if (!containsVertex(vertex)) {
            return;
        }

        List<Edge> removable = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getFrom() == vertex || edge.getTo() == vertex) {
                removable.add(edge);
            }
        }

        for (Edge edge : removable) {
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

    @Override
    public void addEdge(int from, int to) {
        if (!containsVertex(from) || !containsVertex(to)) {
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
        edges.add(new Edge(from, to));
        matrix = newMatrix;
    }

    @Override
    public void removeEdge(int from, int to) {
        int column = -1;

        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).equals(new Edge(from, to))) {
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

    @Override
    public boolean containsVertex(int vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public boolean containsEdge(int from, int to) {
        return edges.contains(new Edge(from, to));
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

    @Override
    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    @Override
    public int edgeCount() {
        return edges.size();
    }

    @Override
    public int vertexCount() {
        return vertices.size();
    }

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
