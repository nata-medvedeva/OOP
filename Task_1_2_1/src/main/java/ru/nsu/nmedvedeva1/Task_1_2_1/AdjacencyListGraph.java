package ru.nsu.nmedvedeva1.Task_1_2_1;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyListGraph extends AbstractGraph{
    private final List<Integer> vertices;
    private final List<List<Integer>> adjacency;

    public AdjacencyListGraph() {
        vertices = new ArrayList<>();
        adjacency = new ArrayList<>();
    }

    @Override
    public void addVertex(int vertex) {
        if (containsVertex(vertex)) {
            return;
        }

        vertices.add(vertex);
        adjacency.add(new ArrayList<>());
    }

    @Override
    public void removeVertex(int vertex) {
        int index = vertices.indexOf(vertex);
        if (index == -1) {
            return;
        }

        vertices.remove(index);
        adjacency.remove(index);

        for (List<Integer> edges : adjacency) {
            edges.remove(Integer.valueOf(vertex));
        }
    }

    @Override
    public void addEdge(int from, int to) {
        int fromIndex = vertices.indexOf(from);
        int toIndex = vertices.indexOf(to);
        if (fromIndex == -1 || toIndex == -1) {
            return;
        }

        if (!adjacency.get(fromIndex).contains(to)) {
            adjacency.get(fromIndex).add(to);
        }
    }

    @Override
    public void removeEdge(int from, int to) {
        int fromIndex = vertices.indexOf(from);
        if (fromIndex == -1) {
            return;
        }

        if (adjacency.get(fromIndex).contains(to)) {
            adjacency.get(fromIndex).remove(Integer.valueOf(to));
        }
    }

    @Override
    public boolean containsEdge(int from, int to) {
        int fromIndex = vertices.indexOf(from);
        if (fromIndex == -1) {
            return false;
        }

        return adjacency.get(fromIndex).contains(to);
    }

    @Override
    public boolean containsVertex(int vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public List<Integer> getVertices() {
        return new ArrayList<>(vertices);
    }

    @Override
    public List<Integer> getNeighbours(int vertex) {
        int index = vertices.indexOf(vertex);
        if (index == -1) {
            return new ArrayList<>();
        }

        return new ArrayList<>(adjacency.get(index));
    }

    @Override
    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            int from = vertices.get(i);
            for (int to : adjacency.get(i)) {
                edges.add(new Edge(from, to));
            }
        }

        return edges;
    }

    @Override
    public int edgeCount() {
        int counter = 0;
        for (List<Integer> neighbours : adjacency) {
            counter += neighbours.size();
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

        for (int i = 0; i < vertices.size(); i++) {
            result.append(vertices.get(i));
            result.append(": ");

            for (int neighbor : adjacency.get(i)) {
                result.append(neighbor);
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
