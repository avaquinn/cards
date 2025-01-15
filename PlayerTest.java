import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
    void testTens() {
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
            assertEquals(i + 1, examplePlayer.currentHand().size());
        }

        examplePlayer.currentHand().clear();

        // Test updating with a deck of all cards
        examplePlayer.updateHand(exampleDeck);
        assertEquals(13, examplePlayer.currentHand().size());
    }

}