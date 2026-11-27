package ru.nsu.nmedvedeva1.book;

import java.util.Scanner;

/**
 * Точка входа в программу.
 * */
public class Main {
    /**
     * Вывод общей информации, формирование зачетной книги и
     * вызов метода, который напечатает результат.
     * */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите форму обучения (1 - Бюджетная, 2 - Платная): ");
        int t = scanner.nextInt();
        final StudyForm form = (t == 1) ? StudyForm.Budget_funded : StudyForm.Contract_based;

        System.out.println("Введите текущий семестр: ");
        final int semester = scanner.nextInt();

        System.out.println("Введите общее количество итоговых оценок по учебному плану: ");
        final int totalGrades = scanner.nextInt();
        scanner.nextLine();

        RecordBook book = new RecordBook(semester, form, totalGrades);

        System.out.println("Доступные типы контроля: ");
        ControlType[] types = ControlType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + " - " + types[i]);
        }

        while (true) {
            System.out.println("\nДобавить оценку? (1 - да, 0 - нет): ");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }

            System.out.println("Введите оценку (2-5, 0 для зачёта): ");
            final int value = scanner.nextInt();

            System.out.println("Введите номер семестра, когда оценка была получена: ");
            final int gradeSemester = scanner.nextInt();

            System.out.println("Введите номер типа контроля: ");
            int typeChoice = scanner.nextInt();
            final ControlType type = types[typeChoice - 1];

            scanner.nextLine();

            book.addGrade(new Grade(type, value, gradeSemester));
        }

        Printer printer = new Printer();

        printer.printBook(book);
        scanner.close();
    }
}
