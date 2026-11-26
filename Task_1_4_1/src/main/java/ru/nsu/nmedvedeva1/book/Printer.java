package ru.nsu.nmedvedeva1.book;

import java.util.OptionalDouble;

/**
 * Вывод итогового текста в консоль для пользователя, вычисление 4 функций.
 * */
public class Printer {
    /**
     * Выводит в консоль.
     *
     * @param book зачетная книга
     * */
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
