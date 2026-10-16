package ru.nsu.nmedvedeva1.Task_1_2_1;

import java.util.List;

public interface Graph {

    void addVertex(int vertex);

    void removeVertex(int vertex);

    void addEdge(int from, int to);

    void removeEdge(int from, int to);

    boolean containsVertex(int vertex);

    boolean containsEdge(int from, int to);

    List<Integer> getVertices();

    List<Integer> getNeighbours(int vertex);

    List<Edge> getEdges();

    int edgeCount();

    int vertexCount();
}
