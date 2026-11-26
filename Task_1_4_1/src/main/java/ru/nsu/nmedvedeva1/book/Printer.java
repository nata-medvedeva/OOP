package ru.nsu.nmedvedeva1.book;

import java.util.OptionalDouble;

public class Printer {
    public void printBook(RecordBook book) {
        if (book.possibilityToMove()) {
            System.out.println("Вы можете перевестись на бесплатную форму обучения.");
        } else {
            System.out.println("Вы не можете перевестись на бесплатную форму обучения.");
        }

        if (book.possibilityToGetRedDiploma()) {
            System.out.println("Вы можете получить красный диплом.");
        } else {
            System.out.println("Вы не можете получить красный диплом.");
        }

        if (book.possibilityToHaveBigScholarship()) {
            System.out.println("Вы можете получить повышенную стипендию.");
        } else {
            System.out.println("Вы не можете получить повышенную стипендию.");
        }

        OptionalDouble average = book.averageGrade();

        if (average.isPresent()) {
            System.out.println("Ваш средний балл: " + average.getAsDouble());
        } else {
            System.out.println("Средний балл не определен.");
        }

    }
}
