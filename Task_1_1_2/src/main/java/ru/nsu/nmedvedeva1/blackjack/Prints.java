package ru.nsu.nmedvedeva1.blackjack;

import java.util.Scanner;

public class Prints {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printWelcome() {
        System.out.println("Добро пожаловать в Блэкджек!");
        System.out.println();
    }

    public static void printRound(int roundCounter) {
        System.out.println("Раунд " + roundCounter);
        System.out.println();
        System.out.println("Дилер раздал карты");
    }

    public static void printInfo(Gamer gamer, Dealer dealer, boolean hiddenCardShown) {
        System.out.println("Ваши карты: " + gamer.getHand().describe());
        if (hiddenCardShown) {
            System.out.println("Карты дилера: " + dealer.getHand().describe());
        } else {
            System.out.println("Карты дилера: " + dealer.describeOneCard());
        }
    }

    public static void printGamerTurn() {
        System.out.println("Ваш ход");
        System.out.println("-------");
        System.out.println();
    }

    public static void printDealerTurn() {
        System.out.println("Ход дилера");
        System.out.println("-------");
        System.out.println();
    }

    public static boolean printCommonInfo() {
        System.out.println("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...");
        System.out.println();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("1")) return true;
            if (input.equals("0")) return false;
            System.out.println("Некорректный ввод");
        }
    }

    public static boolean printPlayAgain() {
        System.out.println("Хотите сыграть еще раз? Введите “1”, чтобы продолжить, и “0”, чтобы остановиться...");
        System.out.println();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("1")) return true;
            if (input.equals("0")) return false;
            System.out.println("Некорректный ввод");
        }
    }

    public static void printRoundResult(int gamerRoundScore, int dealerRoundScore, int totalGamerScore, int totalDealerScore) {
        if (gamerRoundScore > dealerRoundScore) {
            System.out.println("Вы выиграли раунд! Общий счет " + totalGamerScore + ":" + totalDealerScore + " в вашу пользу.");
        } else if (dealerRoundScore > gamerRoundScore) {
            System.out.println("Вы проиграли раунд! Общий счет " + totalGamerScore + ":" + totalDealerScore + " в пользу Дилера.");
        } else {
            System.out.println("Ничья! Общий счет " + totalGamerScore + ":" + totalDealerScore + ".");
        }
    }

    public static void gamerHasBlackjack() {
        System.out.println("Блэкджек! Вы выиграли раунд!");
    }

    public static void dealerHasBlackjack() {
        System.out.println("Блэкджек! Дилер выиграл раунд!");
    }

    public static void bothHaveBlackjack() {
        System.out.println("Ничья! У обоих игроков блекджек!");
    }

    public static void gamerHasBust() {
        System.out.println("Перебор! Дилер выиграл раунд!");
    }

    public static void dealerHasBust() {
        System.out.println("Перебор у дилера! Вы выиграли раунд!");
    }

    public static void printGamerDrawnCard(Card card) {
        System.out.println("Вы открыли карту " + card.toStringWithValue(card.getBaseValue()));
    }

    public static void printDealerDrawnCard(Card card) {
        System.out.println("Дилер открывает карту " + card.toStringWithValue(card.getBaseValue()));
    }

    public static void printDealerRevealHiddenCard(Card card) {
        System.out.println("Дилер открывает закрытую карту " + card.toStringWithValue(card.getBaseValue()));
    }
}
