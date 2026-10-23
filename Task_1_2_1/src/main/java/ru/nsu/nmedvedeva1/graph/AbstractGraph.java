package ru.nsu.nmedvedeva1.graph;

import java.util.Objects;

/**
 * Абстрактный класс для всех реализаций графа.
 * Здесь описаны сравнение графов и хеш-кодов.
 *
 * @param <T> тип вершины
 */
public abstract class AbstractGraph<T> implements Graph<T> {

    /**
     * Сравнивает два графа.
     * Должно быть одинаковое число вершин и рёбер и совпадать сами вершины и рёбра.
     *
     * @param obj объект для сравнения
     * @return true, если графы равны
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Graph<?>)) {
            return false;
        }

        Graph<?> other = (Graph<?>) obj;
        if (vertexCount() != other.vertexCount()) {
            return false;
        }

        if (edgeCount() != other.edgeCount()) {
            return false;
        }

        if (!other.getVertices().containsAll(getVertices())) {
            return false;
        }

        if (!other.getEdges().containsAll(getEdges())) {
            return false;
        }

        return true;
    }

    /**
     * Считает хеш-код графа.
     * Суммирует хеши всех вершин и рёбер.
     *
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        int result = 0;

        for (T vertex : getVertices()) {
            result += Objects.hashCode(vertex);
        }

        for (Edge<T> edge : getEdges()) {
            result += edge.hashCode();
        }

        return result;
    }
}
