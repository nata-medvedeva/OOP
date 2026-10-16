package ru.nsu.nmedvedeva1.Task_1_2_1;

public class Edge {
    private final int from;
    private final int to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getTo() {
        return to;
    }

    public int getFrom() {
        return from;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge)) {
            return false;
        }
        Edge other = (Edge) obj;
        return from == other.from && to == other.to;
    }

    @Override
    public String toString() {
        return from + "->" + to;
    }
}
