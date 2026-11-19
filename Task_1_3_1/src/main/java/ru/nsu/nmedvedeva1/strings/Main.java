package ru.nsu.nmedvedeva1.strings;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * Обрабатывает пользовательский ввод, обрабатывает исключения и вызывает поиск подстроки.
 */
public class Main {

    /**
     * Запрещает создание экземпляров класса извне (чтобы тесты не на 0% закрывались).
     */
    private Main() {
    }

    /**
     * Считывает команду пользователя и выводит результат поиска.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            String info = scanner.nextLine();

            info = info.trim();

            if (info.endsWith(";")) {
                info = info.substring(0, info.length() - 1).trim();
            }

            if (info.startsWith("find(") && info.endsWith(")")) {
                info = info.substring(5, info.length() - 1);
            }
            String[] parts = info.split(",", 2);

            if (parts.length < 2) {
                System.err.println("Ожидается формат: find(\"файл\", \"строка\");");
                return;
            }

            String fileName = miniParser(parts[0]);
            String str = miniParser(parts[1]);

            List<Long> res = new ReadingString().reading(fileName, str);
            ResultPrinter.printResult(res);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Удаляет пробельные символы и двойные кавычки по краям строки.
     *
     * @param s исходная строка
     * @return обработанная строка
     */
    private static String miniParser(String s) {
        s = s.trim();
        if (s.length() >= 2 && s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"') {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
}