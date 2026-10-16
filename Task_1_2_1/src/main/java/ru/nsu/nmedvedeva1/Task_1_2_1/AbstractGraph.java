package ru.nsu.nmedvedeva1.Task_1_2_1;

public abstract class AbstractGraph implements Graph{
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Graph)) {
            return false;
        }

        Graph other = (Graph) obj;
        if (vertexCount()!= other.vertexCount()) {
            return false;
        }

        if (edgeCount() != other.edgeCount()) {
            return false;
        }

        for (int vertex : getVertices()) {
            if (!other.containsVertex(vertex)) {
                return false;
            }
        }

        for (Edge edge : getEdges()) {
            if (!other.containsEdge(edge.getFrom(), edge.getTo())) {
                return false;
            }
        }
        return true;
    }
}
