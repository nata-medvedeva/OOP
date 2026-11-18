package ru.nsu.nmedvedeva1.strings;

import java.util.List;

/**
 * Вывод результатов поиска.
 */
public class ResultPrinter {
    /**
     * Запрещает создание экземпляров класса извне.
     */
    private ResultPrinter() {
    }

    /**
     * Выводит список индексов найденных вхождений.
     *
     * @param res список индексов начала вхождений
     */
    public static void printResult(List<Long> res) {
        System.out.println(res);
    }
}
