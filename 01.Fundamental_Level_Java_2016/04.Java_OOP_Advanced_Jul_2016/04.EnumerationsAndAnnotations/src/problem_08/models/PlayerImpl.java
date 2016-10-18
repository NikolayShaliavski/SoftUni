package problem_08.models;

import problem_08.contracts.Card;
import problem_08.contracts.Player;

import java.util.Set;
import java.util.TreeSet;

public class PlayerImpl implements Player {

    private static final int DEFAULT_HAND_SIZE = 5;

    private String name;
    private Set<Card> hand;

    public PlayerImpl(String name) {
        this.setName(name);
        this.hand = new TreeSet<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public void receiveCard(Card card) {
        this.hand.add(card);
    }

    @Override
    public Card showStrongestCard() {
        Card strongest = null;
        for (Card card : this.hand) {
            strongest = card;
        }
        return strongest;
    }

    @Override
    public boolean canReceiveCard() {
        return this.hand.size() < DEFAULT_HAND_SIZE;
    }
}
