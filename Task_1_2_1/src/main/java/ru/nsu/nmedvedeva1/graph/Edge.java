package ru.nsu.nmedvedeva1.graph;

import java.util.Objects;

/**
 * Ребро графа.
 * Хранит две вершины: откуда и куда идёт ребро.
 *
 * @param <T> тип вершины
 */
public class Edge<T> {
    private final T from;
    private final T to;

    /**
     * Создаёт ребро.
     *
     * @param from начало ребра
     * @param to конец ребра
     */
    public Edge(T from, T to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Возвращает конец ребра.
     *
     * @return вершина, куда идёт ребро
     */
    public T getTo() {
        return to;
    }

    /**
     * Возвращает начало ребра.
     *
     * @return вершина, откуда идёт ребро
     */
    public T getFrom() {
        return from;
    }

    /**
     * Сравнивает два ребра.
     * Рёбра равны, если совпадают начало и конец.
     *
     * @param obj объект для сравнения
     * @return true, если рёбра равны
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge<?>)) {
            return false;
        }
        Edge<?> other = (Edge<?>) obj;
        return Objects.equals(from, other.from) && Objects.equals(to, other.to);
    }

    /**
     * Возвращает ребро в виде строки, например "1->2".
     *
     * @return строковое описание ребра
     */
    @Override
    public String toString() {
        return from + "->" + to;
    }

    /**
     * Считает хеш-код ребра.
     *
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }
}
