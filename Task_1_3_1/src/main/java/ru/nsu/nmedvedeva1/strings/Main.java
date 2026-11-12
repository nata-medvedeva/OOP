package ru.nsu.nmedvedeva1.strings;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    static String fileName;
    static String str;

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

            fileName = miniParser(parts[0]);
            str = miniParser(parts[1]);

            new ReadingString().reading(fileName, str);
        }
    }

    private static String miniParser(String s) {
        s = s.trim();
        if (s.length() >= 2 && s.charAt(0) == '"' && s.charAt(s.length() - 1) == '"') {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
}