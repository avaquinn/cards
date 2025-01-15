import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    private ArrayList<Card> exampleDeck;
    private Player examplePlayer;

    @BeforeEach
    void setUp() {
        exampleDeck = new ArrayList<>(); // Initialize the deck
        examplePlayer = new Player();

        String[] ranks = {"10", "2", "3", "4", "5", "6", "7", "8", "9", "Jack", "Queen", "King", "Ace"};
        for (String rank : ranks) {
            exampleDeck.add(new Card("testSuit", rank)); // Add cards to the deck
        }
    }

    @Test
    void testExampleTens() {
        ArrayList<Card> tenPile = new ArrayList<>();
        tenPile.add(exampleDeck.get(0));

        for(int i = 0; i < 12; i++) assertTrue(RuleBook.checkCard(tenPile, exampleDeck.get(i)));
    }

    @Test
    public void testUpdateHand() {

        // Test updating one card at a time for all cards
        for (int i = 0; i <= 12; i++)
        {
            examplePlayer.updateHand(exampleDeck.get(i));
            assertEquals(i + 1, examplePlayer.getPlayerHand().size());
        }

        examplePlayer.getPlayerHand().clear();

        // Test updating with a deck of all cards
        examplePlayer.updateHand(exampleDeck);
        assertEquals(13, examplePlayer.getPlayerHand().size());
    }

    @Test
    void testDealCards() {
        Deck drawDeck = new Deck();
        drawDeck.shuffle();
        examplePlayer.dealCards(drawDeck);

        // Test player hand has three cards
        assertEquals(3, examplePlayer.getPlayerHand().size());

        // Test player top cards has three cards
        assertEquals(3, examplePlayer.getPlayerTopCards().size());

        // Test player hidden cards has three cards
        assertEquals(3, examplePlayer.getPlayerHiddenCards().size());

        // Test deck size has reduced by 9
        int cardsInDeck = 0; while (drawDeck.draw() != null) cardsInDeck++;
        assertEquals(43, cardsInDeck);
    }

    @Test
    void testPlayFromHand() {
        // Copies example deck into player hand
        for (int i = 0; i < 13; i++) examplePlayer.updateHand(exampleDeck.get(i));

        for (int i = 12; i >= 0; i--){

            // Tests that the correct card is being pulled from the hand
            assertEquals(exampleDeck.get(i), examplePlayer.playFromHand(i));

            // Check that player hand decreases by 1 card each time
            assertEquals(i, examplePlayer.getPlayerHand().size());
        }
    }

}