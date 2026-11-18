package ru.nsu.nmedvedeva1.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Проверяет корректность поиска подстрок, обработку границ блоков, больших файлов и исключений.
 */
class ReadingStringTests {

    @TempDir
    Path tempDir;

    /**
     * Создаёт временный файл с заданным содержимым.
     *
     * @param content содержимое файла
     * @return путь к созданному файлу
     * @throws IOException при ошибке записи файла
     */
    private Path createFile(String content) throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, content, StandardCharsets.UTF_8);
        return file;
    }

    /**
     * Выполняет поиск подстроки в тестовом файле.
     *
     * @param file путь к файлу
     * @param str искомая подстрока
     * @return список индексов найденных вхождений
     * @throws IOException при ошибке чтения файла
     */
    private List<Long> find(Path file, String str) throws IOException {
        return new ReadingString().reading(file.toString(), str);
    }

    @Test
    void testExample() throws IOException {
        Path file = createFile("абракадабра");

        assertEquals(List.of(1L, 8L), find(file, "бра"));
    }

    @Test
    void testNoMatches() throws IOException {
        Path file = createFile("абракадабра");

        assertEquals(List.of(), find(file, "кот"));
    }

    @Test
    void testOverlappingMatches() throws IOException {
        Path file = createFile("aaaaa");

        assertEquals(List.of(0L, 1L, 2L, 3L), find(file, "aa"));
    }

    @Test
    void testWholeFileMatch() throws IOException {
        Path file = createFile("привет");

        assertEquals(List.of(0L), find(file, "привет"));
    }

    @Test
    void testSubstringLongerThanFile() throws IOException {
        Path file = createFile("кот");

        assertEquals(List.of(), find(file, "котёнок"));
    }

    @Test
    void testEmptyFile() throws IOException {
        Path file = createFile("");

        assertEquals(List.of(), find(file, "abc"));
    }

    @Test
    void testMatchBetweenBlocks() throws IOException {

        String content = "а".repeat(8 * 1024 - 1) + "бра";
        Path file = createFile(content);

        assertEquals(List.of(8191L), find(file, "бра"));
    }

    @Test
    void testMatchesInSeveralBlocks() throws IOException {
        int blockSize = 8 * 1024;
        String content = "бра" + "а".repeat(blockSize - 3) + "бра"
                + "а".repeat(blockSize - 3) + "бра";
        Path file = createFile(content);

        assertEquals(List.of(0L, 8192L, 16384L), find(file, "бра"));
    }

    @Test
    void testLargeGeneratedFile() throws IOException {
        Path file = tempDir.resolve("large.txt");

        String block = "а".repeat(8192);

        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {

            for (int i = 0; i < 8192; i++) {
                writer.write(block);
            }

            writer.write("бра");
        }

        long expectedIndex = 8192 * 8192;
        long expectedSize = (long) block.getBytes(StandardCharsets.UTF_8).length * 8192
                + "бра".getBytes(StandardCharsets.UTF_8).length;

        assertEquals(expectedSize, Files.size(file));
        assertTrue(Files.size(file) > Runtime.getRuntime().maxMemory());
        assertEquals(List.of(expectedIndex), find(file, "бра"));
    }

    @Test
    void testNewLines() throws IOException {
        Path file = createFile("кот\nсобака\nкот");

        assertEquals(List.of(0L, 11L), find(file, "кот"));
    }

    @Test
    void testEmptySubstring() throws IOException {
        Path file = createFile("абракадабра");

        assertThrows(IllegalArgumentException.class, () -> find(file, ""));
    }

    @Test
    void testNullSubstring() throws IOException {
        Path file = createFile("абракадабра");

        assertThrows(IllegalArgumentException.class, () -> find(file, null));
    }

    @Test
    void testNonExistentFile() {
        Path file = tempDir.resolve("missing.txt");

        assertThrows(IOException.class, () -> find(file, "бра"));
    }

    @Test
    void testRepeatedSearch() throws IOException {
        Path file = createFile("абракадабра");

        ReadingString reader = new ReadingString();

        List<Long> first = reader.reading(file.toString(), "бра");
        List<Long> second = reader.reading(file.toString(), "кот");

        assertEquals(List.of(1L, 8L), first);
        assertEquals(List.of(), second);
    }
}
