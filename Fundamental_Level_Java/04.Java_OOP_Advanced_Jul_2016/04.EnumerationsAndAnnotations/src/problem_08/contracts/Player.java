package problem_08.contracts;

public interface Player {

    String getName();

    void receiveCard(Card card);

    Card showStrongestCard();

    boolean canReceiveCard();
}
