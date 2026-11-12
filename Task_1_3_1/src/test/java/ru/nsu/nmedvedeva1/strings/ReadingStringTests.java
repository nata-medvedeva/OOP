package ru.nsu.nmedvedeva1.strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReadingStringTests {

    @TempDir
    Path tempDir;

    private Path createFile(String content) throws IOException {
        Path file = tempDir.resolve("input.txt");
        Files.writeString(file, content, StandardCharsets.UTF_8);
        return file;
    }

    private String find(Path file, String str) throws IOException {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try (PrintStream newOut = new PrintStream(output, true, StandardCharsets.UTF_8)) {
            System.setOut(newOut);
            new ReadingString().reading(file.toString(), str);

        } finally {
            System.setOut(oldOut);
        }

        return output.toString(StandardCharsets.UTF_8).trim();
    }

    @Test
    void testExample() throws IOException {
        Path file = createFile("абракадабра");

        assertEquals("[1, 8]", find(file, "бра"));
    }

    @Test
    void testNoMatches() throws IOException {
        Path file = createFile("абракадабра");

        assertEquals("[]", find(file, "кот"));
    }

    @Test
    void testOverlappingMatches() throws IOException {
        Path file = createFile("aaaaa");

        assertEquals("[0, 1, 2, 3]", find(file, "aa"));
    }

    @Test
    void testWholeFileMatch() throws IOException {
        Path file = createFile("привет");

        assertEquals("[0]", find(file, "привет"));
    }

    @Test
    void testSubstringLongerThanFile() throws IOException {
        Path file = createFile("кот");

        assertEquals("[]", find(file, "котёнок"));
    }

    @Test
    void testEmptyFile() throws IOException {
        Path file = createFile("");

        assertEquals("[]", find(file, "abc"));
    }

    @Test
    void testMatchBetweenBlocks() throws IOException {

        String content = "а".repeat(65535) + "бра";
        Path file = createFile(content);

        assertEquals("[65535]", find(file, "бра"));
    }

    @Test
    void testMatchesInSeveralBlocks() throws IOException {
        String content = "бра" + "а".repeat(65533) + "бра" + "а".repeat(65533) + "бра";

        Path file = createFile(content);

        assertEquals("[0, 65536, 131072]", find(file, "бра"));
    }

    @Test
    void testLargeGeneratedFile() throws IOException {
        Path file = tempDir.resolve("large.txt");

        String block = "а".repeat(8192);

        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {

            for (int i = 0; i < 1024; i++) {
                writer.write(block);
            }

            writer.write("бра");
        }

        assertTrue(Files.size(file) > 16_000_000);

        assertEquals("[8388608]", find(file, "бра"));
    }

    @Test
    void testNewLines() throws IOException {
        Path file = createFile("кот\nсобака\nкот");

        assertEquals("[0, 11]", find(file, "кот"));
    }
}
