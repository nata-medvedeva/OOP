package ru.nsu.nmedvedeva1.strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Проверяет обработку пользовательского ввода и корректность вывода программы.
 */
class MainTests {

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
     * Запускает программу с заданными входными данными и перехватывает её вывод.
     *
     * @param input входная строка
     * @return строка, выведенная программой
     */
    private String runMain(String input) {
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(output));

            Main.main(new String[0]);
            return output.toString().trim();
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
    }

    @Test
    void testCorrectInput() throws IOException {
        Path file = createFile("абракадабра");

        assertEquals("[1, 8]", runMain("find(\"" + file + "\", \"бра\");"));
    }

    @Test
    void testWithoutSemicolon() throws IOException {
        Path file = createFile("абракадабра");

        assertEquals("[1, 8]", runMain("find(\"" + file + "\", \"бра\")"));
    }

    @Test
    void testNoMatches() throws IOException {
        Path file = createFile("абракадабра");

        assertEquals("[]", runMain("find(\"" + file + "\", \"кот\");"));
    }

    @Test
    void testIncorrectInput() {
        assertEquals("", runMain("incorrectInput"));
    }
}