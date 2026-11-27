package ru.nsu.nmedvedeva1.book;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для вывода.
 * */
class PrinterTests {

    private PrintStream originalOut;
    private ByteArrayOutputStream output;
    private Printer printer;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));

        printer = new Printer();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void positiveOutput() {
        RecordBook book = new RecordBook(3, StudyForm.Contract_based, 35);

        book.addGrade(new Grade(ControlType.Exam, 5, 1));
        book.addGrade(new Grade(ControlType.Exam, 5, 2));

        printer.printBook(book);

        String result = output.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains("Вы можете перевестись на бесплатную форму обучения."));
        assertTrue(result.contains("Вы можете получить красный диплом."));
        assertTrue(result.contains("Вы можете получить повышенную стипендию."));
        assertTrue(result.contains("Ваш средний балл: 5.0"));
    }

    @Test
    void negativeOutput() {
        RecordBook book = new RecordBook(3, StudyForm.Contract_based, 35);

        book.addGrade(new Grade(ControlType.Exam, 3, 1));
        book.addGrade(new Grade(ControlType.Exam, 4, 2));

        printer.printBook(book);

        String result = output.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains("Вы не можете перевестись на бесплатную форму обучения."));
        assertTrue(result.contains("Вы не можете получить красный диплом."));
        assertTrue(result.contains("Вы не можете получить повышенную стипендию."));
        assertTrue(result.contains("Ваш средний балл: 3.5"));
    }

    @Test
    void emptyOutput() {
        RecordBook book = new RecordBook(1, StudyForm.Contract_based, 35);

        printer.printBook(book);

        String result = output.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains("Вы не можете перевестись на бесплатную форму обучения."));
        assertTrue(result.contains("Вы можете получить красный диплом."));
        assertTrue(result.contains("Вы не можете получить повышенную стипендию."));
        assertTrue(result.contains("Средний балл не определен."));
    }
}