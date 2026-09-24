package ru.nsu.nmedvedeva1.blackjack;

public class Game {
    public final Gamer gamer = new Gamer("Игрок");
    public final Dealer dealer = new Dealer();
    Deck deck = new Deck(3);

    int dealerScore = 0;
    int gamerScore = 0;
    int round = 0;

    /**
     * Метод, где мы начинаем проигрывать раунд,
     * печатаем вводные слова с добро пожаловать и тп
     * и спрашиваем игрока, продолжать ли игру
     * */
    public void start() {
        Prints.printWelcome();
        boolean continuing = true;
        while (continuing) {
            playRound();
            continuing = Prints.printPlayAgain();
        }
    }

    /**
     * Метод, в котором проигрывается весь раунд в общем:
     * скидываем руки (то что с предыдущих раундов осталось),
     * раздаем по 2 карты, сразу проверяем на блэкджек,
     * запускаем отдельно раунд со стороны игрока и дилера,
     * и выводим в конце счет раунда
     * */
    public void playRound() {
        round++;
        gamer.resetHand();
        dealer.resetHand();

        Prints.printRound(round);

        gamer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
        gamer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());

        Prints.printInfo(gamer, dealer, false);

        if (gamer.getHand().isBlackjack() || dealer.getHand().isBlackjack()) {
            if (dealer.getHand().isBlackjack() && gamer.getHand().isBlackjack()) {
                Prints.bothHaveBlackjack();
                return;
            }
            if (dealer.getHand().isBlackjack()) {
                Prints.dealerHasBlackjack();
                dealerScore++;
                return;
            }
            if (gamer.getHand().isBlackjack()) {
                Prints.gamerHasBlackjack();
                gamerScore++;
                return;
            }
        }

        if (gamerRound()) {
            Prints.printRoundResult(gamer.getHand().getScore(), dealer.getHand().getScore(), dealerScore, gamerScore);
            return;
        }

        dealerRound();

        int gamerHandScore = gamer.getHand().getScore();
        int dealerHandScore = dealer.getHand().getScore();

        if (gamerHandScore > dealerHandScore) {
            gamerScore++;
        } else if (dealerHandScore > gamerHandScore) {
            dealerScore++;
        }
        Prints.printRoundResult(gamerHandScore, dealerHandScore, dealerScore, gamerScore);
    }

    /**
     * Метод, в котором мы возвращаем 1, если у игрока перебор,
     * пока игрок нажимает 1, раунд продолжается и он набирает карты,
     * при 21 ему больше не даем набирать карты, ему уже хватит
     * */
    private boolean gamerRound() {
        Prints.printGamerTurn();
        boolean answer = Prints.printCommonInfo();
        while (answer) {
            Card drawnCard = deck.drawCard();
            gamer.receiveCard(drawnCard);

            Prints.printGamerDrawnCard(drawnCard);
            Prints.printInfo(gamer, dealer, false);

            if (gamer.getHand().isBust()) {
                Prints.gamerHasBust();
                dealerScore++;
                return true;
            }

            if (gamer.getHand().getScore() == 21) {
                System.out.println("Вы набрали 21! Ход автоматически передается дилеру.");
                System.out.println();
                break;
            }

            answer = Prints.printCommonInfo();
        }
        return false;
    }

    /**
     * Метод, где дилер играет раунд, раскрываем закрытую карту,
     * дилер набирает, в конце проверяем, что нет перебора
     * */
    private void dealerRound() {
        Prints.printDealerTurn();
        Card hiddenCard = dealer.getHiddenCard();
        if (hiddenCard != null) {
            Prints.printDealerRevealHiddenCard(hiddenCard);
        }
        dealer.revealHiddenCard();
        Prints.printInfo(gamer, dealer, true);

        while (dealer.toDrawCard()) {
            Card drawnCard = deck.drawCard();
            dealer.receiveCard(drawnCard);

            Prints.printDealerDrawnCard(drawnCard);
            Prints.printInfo(gamer, dealer, true);
        }

        if (dealer.getHand().isBust()) {
            Prints.dealerHasBust();
            gamerScore++;
        }
    }

    /**
     * Метод для тестов, описано какие флаги в каких случаях вернем
     *
     * @param gamerScore счет игрока
     * @param dealerScore счет дилера
     * @param gamerBust перебор у игрока
     * @param dealerBust перебор у дилера
     * */
    public static String winnerForTests(int gamerScore, int dealerScore, boolean gamerBust, boolean dealerBust) {
        if (gamerBust) {
            return "DEALER";
        }
        if (dealerBust) {
            return "GAMER";
        }
        if (gamerScore > dealerScore) {
            return "GAMER";
        }
        if (dealerScore > gamerScore) {
            return "DEALER";
        }
        return "DRAW";
    }
}
