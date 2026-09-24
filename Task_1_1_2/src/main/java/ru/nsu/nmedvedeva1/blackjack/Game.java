package ru.nsu.nmedvedeva1.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final Gamer gamer = new Gamer("Игрок");
    public final Dealer dealer = new Dealer();
    Deck deck = new Deck(3);

    int dealerScore = 0;
    int gamerScore = 0;
    int round = 0;

    public void start() {
        Prints.printWelcome();
        boolean continuing = true;
        while (continuing) {
            playRound();
            continuing = Prints.printPlayAgain();
        }
    }

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
                return;
            }

            if (gamer.getHand().getScore() == 21) {
                System.out.println("Вы набрали 21! Ход автоматически передается дилеру.");
                System.out.println();
                break;
            }

            answer = Prints.printCommonInfo();
        }

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
            return;
        }

        int gamerHandScore = gamer.getHand().getScore();
        int dealerHandScore = dealer.getHand().getScore();

        if (gamerHandScore > dealerHandScore) {
            gamerScore++;
        } else if (dealerHandScore > gamerHandScore) {
            dealerScore++;
        }
        Prints.printRoundResult(gamerHandScore, dealerHandScore, dealerScore, gamerScore);
    }

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
