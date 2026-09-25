package ru.nsu.nmedvedeva1.blackjack;

/**
 * Класс игры, где описываются раунд игры со стороны дилера и игрока
 * и общая логика проведения раунда в целом.
 * */
public class Game {
    private final Gamer gamer = new Gamer("Игрок");
    private final Dealer dealer = new Dealer();
    private Deck deck = new Deck(3);

    private int dealerScore = 0;
    private int gamerScore = 0;
    private int round = 0;

    /**
     * Метод, где мы начинаем проигрывать раунд,
     * печатаем вводные слова с добро пожаловать и тп
     * и спрашиваем игрока, продолжать ли игру.
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
     * Метод, в котором проигрывается весь раунд в общем,
     * но не обновляется счет игры.
     *
     * @return результат раунда
     * */
    public RoundResult playRound() {
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
                Prints.printRoundResult(gamer.getHand().getScore(),
                        dealer.getHand().getScore(), gamerScore, dealerScore);
                return RoundResult.draw;
            }
            if (dealer.hasBlackjack()) {
                Prints.dealerHasBlackjack();
                updateScore(RoundResult.dealerWin);
                Prints.printRoundResult(gamer.getHand().getScore(),
                        dealer.getHand().getScore(), gamerScore, dealerScore);
                return RoundResult.dealerWin;
            }
            if (gamer.getHand().isBlackjack()) {
                Prints.gamerHasBlackjack();
                updateScore(RoundResult.gamerWin);
                Prints.printRoundResult(gamer.getHand().getScore(),
                        dealer.getHand().getScore(), gamerScore, dealerScore);
                return RoundResult.gamerWin;
            }
        }

        if (gamerRound()) {
            updateScore(RoundResult.dealerWin);
            Prints.printRoundResult(gamer.getHand().getScore(),
                    dealer.getHand().getScore(), gamerScore, dealerScore);
            return RoundResult.dealerWin;
        }

        dealerRound();

        if (dealer.getHand().isBust()) {
            updateScore(RoundResult.gamerWin);
            Prints.printRoundResult(gamer.getHand().getScore(),
                    dealer.getHand().getScore(), gamerScore, dealerScore);
            return RoundResult.gamerWin;
        }

        int gamerHandScore = gamer.getHand().getScore();
        int dealerHandScore = dealer.getHand().getScore();

        RoundResult result;
        if (gamerHandScore > dealerHandScore) {
            result = RoundResult.gamerWin;
        } else if (dealerHandScore > gamerHandScore) {
            result = RoundResult.dealerWin;
        } else {
            result = RoundResult.draw;
        }

        updateScore(result);
        Prints.printRoundResult(gamerHandScore, dealerHandScore, gamerScore, dealerScore);

        return result;
    }

    /**
     * Метод, в котором мы возвращаем 1, если у игрока перебор,
     * пока игрок нажимает 1, раунд продолжается и он набирает карты,
     * при 21 ему больше не даем набирать карты, ему уже хватит.
     *
     * @return правда, если у игрока + очко и надо прервать раунд,
     * ложь в ином случае.
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
                return true;
            }

            if (gamer.getHand().getScore() == 21) {
                Prints.gamerGot21();
                break;
            }

            answer = Prints.printCommonInfo();
        }
        return false;
    }

    /**
     * Метод, где дилер играет раунд, раскрываем закрытую карту,
     * дилер набирает, в конце проверяем, что нет перебора.
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
        }
    }

    /**
     * Метод возвращает счёт дилера по всей игре.
     *
     * @return количество раундов, выигранных дилером
     */
    public int getDealerScore() {
        return dealerScore;
    }

    /**
     * Метод возвращает счёт игрока по всей игре.
     *
     * @return количество раундов, выигранных игроком
     */
    public int getGamerScore() {
        return gamerScore;
    }

    /**
     * Возвращает номер текущего раунда.
     *
     * @return номер раунда
     */
    public int getRound() {
        return round;
    }

    /**
     * Возвращает игрока.
     *
     * @return объект игрока
     */
    public Gamer getGamer() {
        return gamer;
    }

    /**
     * Возвращает дилера.
     *
     * @return объект дилера
     */
    public Dealer getDealer() {
        return dealer;
    }

    /**
     * Обновляет общий счёт игры на основе результата раунда.
     *
     * @param result результат завершённого раунда
     */
    private void updateScore(RoundResult result) {
        if (result == RoundResult.gamerWin) {
            gamerScore++;
        } else if (result == RoundResult.dealerWin) {
            dealerScore++;
        }
    }
}
