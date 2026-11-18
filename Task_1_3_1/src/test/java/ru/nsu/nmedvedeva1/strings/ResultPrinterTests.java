package ru.nsu.nmedvedeva1.strings;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Проверяет корректность вывода результатов поиска.
 */
public class ResultPrinterTests {

    /**
     * Перехватывает вывод списка результатов поиска.
     *
     * @param res список индексов
     * @return строковое представление результата
     */
    private String print(List<Long> res) {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try (PrintStream newOut = new PrintStream(output, true, StandardCharsets.UTF_8)) {
            System.setOut(newOut);
            ResultPrinter.printResult(res);
        } finally {
            System.setOut(oldOut);
        }
        return output.toString(StandardCharsets.UTF_8).trim();
    }

    @Test
    void testEmptyList() {
        assertEquals("[]", print(List.of()));
    }

    @Test
    void testSingleElement() {
        assertEquals("[0]", print(List.of(0L)));
    }

    @Test
    void testSeveralElements() {
        assertEquals("[1, 8, 15]", print(List.of(1L, 8L, 15L)));
    }

    @Test
    void testLargeIndexes() {
        assertEquals("[2147483648, 5000000000]", print(List.of(2147483648L, 5000000000L)));
    }
}