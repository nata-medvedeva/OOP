package ru.nsu.nmedvedeva1.blackjack;

import java.util.Scanner;

/**
 * Класс, состоящий из выводов строк,
 * все оформления по типу ваш ход, ход диллера и тп.
 * */
public class Prints {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Метод для тестов: пересоздает Scanner, чтобы он подхватил
     * новый System.in после System.setIn().
     */
    public static void resetScanner() {
        scanner = new Scanner(System.in);
    }

    /**
     * Вывод приветствия.
     * */
    public static void printWelcome() {
        System.out.println("Добро пожаловать в Блэкджек!");
        System.out.println();
    }

    /**
     * Вывод номера раунда.
     * */
    public static void printRound(int roundCounter) {
        System.out.println("Раунд " + roundCounter);
        System.out.println();
        System.out.println("Дилер раздал карты");
    }

    /**
     * Описание, какие карты находятся у диела, а какие у игрока.
     *
     * @param gamer игрок
     * @param dealer дилер
     * @param hiddenCardShown показывать скрытую карту или нет
     * */
    public static void printInfo(Gamer gamer, Dealer dealer, boolean hiddenCardShown) {
        System.out.println("Ваши карты: " + gamer.getHand().describe());
        if (hiddenCardShown) {
            System.out.println("Карты дилера: " + dealer.getHand().describe());
        } else {
            System.out.println("Карты дилера: " + dealer.describeOneCard());
        }
    }

    /**
     * Вывод фразы ваш ход.
     * */
    public static void printGamerTurn() {
        System.out.println("Ваш ход");
        System.out.println("-------");
        System.out.println();
    }

    /**
     * Вывод фразы ход дилера.
     * */
    public static void printDealerTurn() {
        System.out.println("Ход дилера");
        System.out.println("-------");
        System.out.println();
    }

    /**
     * Спрашиваем о продолжении игры в раунде.
     * */
    public static boolean printCommonInfo() {
        System.out.println("Введите “1”, чтобы взять карту, и “0”, "
                + "чтобы остановиться...");
        System.out.println();
        while (true) {
            if (!scanner.hasNextLine()) {
                return false;
            }
            String input = scanner.nextLine();
            if (input.equals("1")) {
                return true;
            }
            if (input.equals("0")) {
                return false;
            }
            System.out.println("Некорректный ввод");
        }
    }

    /**
     * Спрашиваем, играть ли следущий раунд.
     * */
    public static boolean printPlayAgain() {
        System.out.println("Хотите сыграть еще раз? Введите “1”, "
                + "чтобы продолжить, и “0”, чтобы остановиться...");
        System.out.println();
        while (true) {
            if (!scanner.hasNextLine()) {
                return false;
            }
            String input = scanner.nextLine();
            if (input.equals("1")) {
                return true;
            }
            if (input.equals("0")) {
                return false;
            }
            System.out.println("Некорректный ввод");
        }
    }

    /**
     * Вывод результатов раунда (счет).
     *
     * @param gamerRoundScore счет игрока в раунде
     * @param dealerRoundScore счет дилера в раунде
     * @param totalGamerScore счет игрока в общем по игре
     * @param totalDealerScore счет дилера в обшем по игре
     * */
    public static void printRoundResult(int gamerRoundScore, int dealerRoundScore,
                                        int totalGamerScore, int totalDealerScore) {
        String roundResult;
        if (gamerRoundScore > 21) {
            roundResult = "Вы проиграли раунд!";
        } else if (dealerRoundScore > 21) {
            roundResult = "Вы выиграли раунд!";
        } else if (gamerRoundScore > dealerRoundScore) {
            roundResult = "Вы выиграли раунд!";
        } else if (dealerRoundScore > gamerRoundScore) {
            roundResult = "Вы проиграли раунд!";
        } else {
            roundResult = "Ничья в раунде!";
        }

        String s;
        if (totalGamerScore > totalDealerScore) {
            s = " в вашу пользу.";
        } else if (totalDealerScore > totalGamerScore) {
            s = " в пользу Дилера.";
        } else {
            s = ".";
        }

        System.out.println(roundResult + " Общий счёт "
                + totalGamerScore + ":" + totalDealerScore + s);

    }

    /**
     * Вывод победы игрока с блэкджеком.
     * */
    public static void gamerHasBlackjack() {
        System.out.println("Блэкджек! Вы выиграли раунд!");
    }

    /**
     * Вывод победы диле а с блэкджеком.
     * */
    public static void dealerHasBlackjack() {
        System.out.println("Блэкджек! Дилер выиграл раунд!");
    }

    /**
     * Вывод ничью, если у двоих блэкджек.
     * */
    public static void bothHaveBlackjack() {
        System.out.println("Ничья! У обоих игроков блекджек!");
    }

    /**
     * Вывод перебора у игрока.
     * */
    public static void gamerHasBust() {
        System.out.println("Перебор! Дилер выиграл раунд!");
    }

    /**
     * Вывод перебора у дилера.
     * */
    public static void dealerHasBust() {
        System.out.println("Перебор у дилера! Вы выиграли раунд!");
    }

    /**
     * Вывод того, какую карту открыл игрок.
     *
     * @param card карта, которую нужно вывести
     * */
    public static void printGamerDrawnCard(Card card) {
        System.out.println("Вы открыли карту " + card.toStringWithValue(card.getBaseValue()));
    }

    /**
     * Вывод того, какую карту открыл дилер.
     *
     * @param card карта, которую нужно вывести
     * */
    public static void printDealerDrawnCard(Card card) {
        System.out.println("Дилер открывает карту " + card.toStringWithValue(card.getBaseValue()));
    }

    /**
     * Вывод того, какую закрытую карту открыл дилер.
     *
     * @param card карта, которую нужно вывести
     * */
    public static void printDealerRevealHiddenCard(Card card) {
        System.out.println("Дилер открывает закрытую карту "
                + card.toStringWithValue(card.getBaseValue()));
    }

    public static void gamerGot21() {
        System.out.println("Вы набрали 21! Ход автоматически передается дилеру.");
        System.out.println();
    }
}
