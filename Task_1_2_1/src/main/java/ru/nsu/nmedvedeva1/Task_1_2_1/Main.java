package ru.nsu.nmedvedeva1.Task_1_2_1;

import java.io.FileNotFoundException;

/**
 * Точка входа в программу.
 * Читает графы из файлов и запускает топологическую сортировку.
 */
public class Main {
    /**
     * Запускает программу.
     *
     * @param args аргументы командной строки
     * @throws FileNotFoundException если файл с графом не найден
     */
    public static void main(String[] args) throws FileNotFoundException {
        Graph<Integer> listGraph = new AdjacencyListGraph<>();

        Graph<Integer> matrixGraph = new AdjacencyMatrixGraph<>();

        Graph<Integer> incidenceGraph = new IncidenceMatrixGraph<>();

        GraphReader.read(
            listGraph,
            "integer_graph.txt",
            Integer::parseInt
        );

        GraphReader.read(
            matrixGraph,
            "integer_graph.txt",
            Integer::parseInt
        );

        GraphReader.read(
            incidenceGraph,
            "integer_graph.txt",
            Integer::parseInt
        );

        TopologicalSort<Integer> dfs = new Dfs<>();
        System.out.println("DFS:");
        System.out.println(dfs.sort(listGraph));

        Graph<String> stringGraph = new AdjacencyListGraph<>();

        GraphReader.read(
            stringGraph,
            "string_graph.txt",
            s -> s
        );

        TopologicalSort<String> stringDfs = new Dfs<>();
        System.out.println("DFS:");
        System.out.println(stringDfs.sort(stringGraph));
    }
}

