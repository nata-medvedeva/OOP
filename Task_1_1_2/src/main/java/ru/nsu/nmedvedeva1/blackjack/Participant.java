package ru.nsu.nmedvedeva1.blackjack;

/**
 * Абстрактный класс учатник, где описываются получение карты,
 * присвоение имени, получение руки и сброс руки.
 * Может быть использован игроком, либо дилером.
 * */
public abstract class Participant {
    protected final Hand hand = new Hand();
    protected String name;

    /**
     * Метод, где мы можем задать имя игроку или дилеру.
     *
     * @param name то имя, которое мы задаем
     */
    public Participant(String name) {
        this.name = name;
    }

    /**
     * Метод, для получения карты, добавляем эту карту в руку/набор.
     *
     * @param card карта, которую добавляем
     * */
    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    /**
     * Метод, когда возвращаем (создаем) руку.
     *
     * @return hand та рука, которую создаем
     * */
    public Hand getHand() {
        return hand;
    }

    /**
     * Метод, когда возварщаем имя участника.
     *
     * @return name имя
     * */
    public String getName() {
        return name;
    }

    /**
     * Метод, где сбрасываем, отчищаем руку.
     * */
    public void resetHand() {
        hand.clear();
    }
}