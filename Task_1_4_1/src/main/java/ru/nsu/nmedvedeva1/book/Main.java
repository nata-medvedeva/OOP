package ru.nsu.nmedvedeva1.book;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();

        System.out.println("Введите форму обучения (1 - Бюджетная, 2 - Платная): ");
        int t = scanner.nextInt();
        StudyForm form = (t == 1) ? StudyForm.Бюджетная : StudyForm.Платная;

        System.out.println("Введите текущий семестр: ");
        int semester = scanner.nextInt();
        scanner.nextLine();

        RecordBook book = new RecordBook(semester, form);

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

            System.out.println("Введите номер типа контроля: ");
            int typeChoice = scanner.nextInt();
            ControlType type = types[typeChoice - 1];

            System.out.println("Введите оценку (2-5, 0 для зачёта): ");
            int value = scanner.nextInt();

            System.out.println("Введите номер семестра, когда оценка была получена: ");
            int gradeSemester = scanner.nextInt();
            scanner.nextLine();

            book.addGrade(new Grade(type, value, gradeSemester));
        }

        printer.printBook(book);
        scanner.close();
    }
}
