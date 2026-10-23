package ru.nsu.nmedvedeva1.Task_1_2_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Читает граф из файла.
 * Формат: сначала число вершин и рёбер, потом вершины, потом рёбра.
 */
public class GraphReader {

    /**
     * Читает граф из файла и заполняет его.
     *
     * @param graph граф, который нужно заполнить
     * @param fileName имя файла
     * @param converter функция, которая превращает строку в вершину
     * @param <T> тип вершины
     * @throws FileNotFoundException если файл не найден
     */
    public static <T> void read(
        Graph<T> graph,
        String fileName,
        Function<String, T> converter
    ) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            int vertexCounter = scanner.nextInt();
            int edgeCounter = scanner.nextInt();

            for (int i = 0; i < vertexCounter; i++) {
                T vertex = converter.apply(scanner.next());
                graph.addVertex(vertex);
            }

            for (int i = 0; i < edgeCounter; i++) {
                T from = converter.apply(scanner.next());
                T to = converter.apply(scanner.next());

                graph.addEdge(from, to);
            }
        }
    }
}