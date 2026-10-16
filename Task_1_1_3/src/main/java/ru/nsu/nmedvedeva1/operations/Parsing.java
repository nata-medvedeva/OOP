package ru.nsu.nmedvedeva1.operations;

/**
 * Парсер строк.
 */
public class Parsing {
    /**
     * Разбирает строку в выражении по символам, рекурсивно, по частям.
     *
     * @param string строка вида в кавычках
     * @return объект Expression выражение
     */
    public static Expression toParse(String string) {
        String str = hasWhitespace(string);

        if (Character.isDigit(str.charAt(0))) {
            int i = 0;
            while (i < str.length() && Character.isDigit(str.charAt(i))) {
                i++;
            }
            int value = Integer.parseInt(str.substring(0, i));
            return new Number(value);
        }

        if (Character.isLetter(str.charAt(0))) {
            int i = 0;
            while (i < str.length() && Character.isLetter(str.charAt(i))) {
                i++;
            }
            String name = str.substring(0, i);
            return new Variable(name);
        }

        if (str.charAt(0) == '(') {
            int counterOfBrackets = 0;
            int mainOperatorPosition = -1;
            char mainOperator = ' ';

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    counterOfBrackets++;
                } else if (str.charAt(i) == ')') {
                    counterOfBrackets--;
                } else if (counterOfBrackets == 1 && isOperator(str.charAt(i))) {
                    mainOperatorPosition = i;
                    mainOperator = str.charAt(i);
                    break;
                }
            }

            String leftPart = str.substring(1, mainOperatorPosition);
            String rightPart = str.substring(mainOperatorPosition + 1, str.length() - 1);

            Expression left = toParse(leftPart);
            Expression right = toParse(rightPart);

            if (mainOperator == '+') {
                return new Add(left, right);
            } else if (mainOperator == '-') {
                return new Sub(left, right);
            } else if (mainOperator == '*') {
                return new Mul(left, right);
            } else if (mainOperator == '/') {
                return new Div(left, right);
            } else {
                throw new IllegalArgumentException("Unknown sign: " + mainOperator);
            }
        }
        throw new IllegalArgumentException("Can not to parse the string: " + str);
    }

    /**
     * Удаляет все пробелы из строки.
     *
     * @param str исходная строка
     * @return строка без пробелов
     */
    public static String hasWhitespace(String str) {
        return str.replace(" ", "");
    }

    /**
     * Проверяет, является ли символ оператором (+, -, *, /).
     *
     * @param c проверяемый символ
     * @return true, если символ — оператор
     */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
