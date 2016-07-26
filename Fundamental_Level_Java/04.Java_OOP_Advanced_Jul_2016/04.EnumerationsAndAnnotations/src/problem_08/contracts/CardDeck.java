package problem_08.contracts;

public interface CardDeck {

    void addCard(Card card);

    Card tryPullCard(Card card);
}
