package ru.nsu.nmedvedeva1.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий основные методы дилера (наследует поведение участника),
 * такие как получение карты,
 * обнажение скрытой карты, сброс руки, описание карт,
 * когда одна закрыта, получение скрытой карты и логика,
 * что дилер берет карты, пока у него сумма меньше 17.
 * */
public class Dealer extends Participant {
    private Card hiddenCard;

    /**
     * Метод в котором создаем дилера с именем Дилер.
     * */
    public Dealer() {
        super("Дилер");
    }

    /**
     * Метод, для получения карты,
     * если карт еще не было то добавляем дилеру,
     * если карты хиден не было то добавляем нашу карту как скрытую
     * иначе просто добавляем карту к дилеру.
     *
     * @param card карта
     * */
    public void receiveCard(Card card) {
        if (hand.getCards().isEmpty()) {
            super.receiveCard(card);
        } else if (hiddenCard == null) {
            hiddenCard = card;
        } else {
            super.receiveCard(card);
        }
    }

    /**
     * Метод, когда добавляем скрытую карту в руку.
     * */
    public void revealHiddenCard() {
        if (hiddenCard != null) {
            hand.addCard(hiddenCard);
            hiddenCard = null;
        }
    }

    /**
     * Метод, который показывает,
     * что дилер будет брать карты пока у него меньше 17 счет.
     *
     * @return правда или ложь
     * */
    public boolean toDrawCard() {
        return hand.getScore() < 17;
    }

    /**
     * Метод, когда сбрасываем руку у дилера
     * и сбрасываем скрытую карту.
     * */
    public void resetHand() {
        super.resetHand();
        hiddenCard = null;
    }

    /**
     * Метод, когда описываем одну карту у дилера,
     * закрытую не трогаем.
     *
     * @return [масть и ранг карты, ее стоимость и закрытая карта]
     * */
    public String describeOneCard() {
        Card openCard = hand.getCards().get(0);
        return "[" + openCard.toStringWithValue(openCard.getBaseValue())
                + ", <закрытая карта>]";
    }

    /**
     * Метод, для получения скрытой карты.
     *
     * @return скрытой карты
     * */
    public Card getHiddenCard() {
        return hiddenCard;
    }
}