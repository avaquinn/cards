import java.util.ArrayList;

public class Player {
    private ArrayList<Card> playerHand = new ArrayList<>(52);
    private ArrayList<Card> playerTopCards = new ArrayList<>(3);
    private ArrayList<Card> playerHiddenCards = new ArrayList<>(3);

    public void dealCards(Deck deck)
    {
        for (int i = 0;  i < 3; i++) {
            playerHand.add(deck.draw());
            playerTopCards.add(deck.draw());
            playerHiddenCards.add(deck.draw());
        }
    }

    public void printHands()
    {
        System.out.println("Player Hand: "+ playerHand);
        System.out.println("Player Top Cards: "+ playerTopCards);
        System.out.println("Player [[HIDDEN]] Cards: "+ playerHiddenCards);
    }
}
