package ru.nsu.nmedvedeva1.Task_1_2_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFS implements TopologicalSort {
    @Override
    public List<Integer> sort(Graph graph) {
        List<Integer> vertices = graph.getVertices();
        int[] colour = new int[vertices.size()];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            if (colour[i] == 0) {
                boolean cycle = dfs(vertices.get(i), graph, vertices, colour, result);
                if (cycle) {
                    throw new RuntimeException("Graph have a cycle");
                }
            }
        }
        Collections.reverse(result);
        return result;
    }

    private boolean dfs(int vertex, Graph graph, List<Integer> vertices, int[] colour, List<Integer> result) {
        int vertexIndex = vertices.indexOf(vertex);
        colour[vertexIndex] = 1;
        for (int neighbour : graph.getNeighbours(vertex)) {
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