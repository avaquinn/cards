import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RuleBookTest {

    private ArrayList<Card> exampleDeck;

    @BeforeEach
    void setUp() {
        exampleDeck = new ArrayList<>(); // Initialize the deck

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
    void testTwos() {
        ArrayList<Card> twoPile = new ArrayList<>();
        twoPile.add(exampleDeck.get(1));

        // Test twos can be played on any card
        for(int i = 0; i < 12; i++) assertTrue(RuleBook.checkCard(twoPile, exampleDeck.get(i)));
    }

    @Test
    void testThrees() {
        ArrayList<Card> threePile = new ArrayList<>();
        threePile.add(exampleDeck.get(2));

        // Test threes can be played on any card
        for(int i = 0; i < 12; i++) assertTrue(RuleBook.checkCard(threePile, exampleDeck.get(i)));
    }

    @Test
    void testSevens() {
        ArrayList<Card> sevenPile = new ArrayList<>();
        sevenPile.add(exampleDeck.get(6));

        // Test a lower valued cards can be played on a seven
        for(int i = 3; i <= 5; i++) assertTrue(RuleBook.checkCard(sevenPile, exampleDeck.get(i)));

        // Test a 7 can be played on a seven
        assertTrue(RuleBook.checkCard(sevenPile, exampleDeck.get(6)));

        // Test special cards can be played on a seven
        // Test 10
        assertTrue(RuleBook.checkCard(sevenPile, exampleDeck.get(0)));
        // Test 2
        assertTrue(RuleBook.checkCard(sevenPile, exampleDeck.get(1)));
        // Test 3
        assertTrue(RuleBook.checkCard(sevenPile, exampleDeck.get(2)));

        // Test higher value cards cannot be played on a seven
        for(int i = 7; i <= 12; i++) assertFalse(RuleBook.checkCard(sevenPile, exampleDeck.get(i)));
    }

    @Test
    void testOtherCards() {
        ArrayList<Card> pile = new ArrayList<>();

        // Iterates through all possible pile cards 4 through ace
        for (int pile_card = 3; pile_card <= 12; pile_card++)
        {
            // Iterates through all possible played cards 4 through ace
            for (int played_card = 3; played_card <= 12; played_card++)
            {
                pile.add(exampleDeck.get(pile_card));

                // For pile cards that not 7, check that next card played is greater or equal in value returns true
                if (played_card < pile_card && pile_card != 6) assertFalse(RuleBook.checkCard(pile, exampleDeck.get(played_card)));
                if (played_card >= pile_card && pile_card != 6) assertTrue(RuleBook.checkCard(pile, exampleDeck.get(played_card)));

                pile.removeLast();
            }
        }
    }

    @Test
    void test1() {

        assertEquals(13, exampleDeck.size()); // Ensure the deck has 13 cards
    }

    @Test
    void test2() {
        assertNotNull(exampleDeck); // Ensure the deck is not null
    }
}