import org.junit.Test;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class RuleBookTest {
    @BeforeEach
    void setUp() {
        RuleBook ruleBook = new RuleBook();
        // Initialize or configure the object as needed

        ArrayList<Card> exampleDeck = new ArrayList<>(13);
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        for (String rank : ranks) {
            exampleDeck.add(new Card("test", rank));
        }

    }

    @Test
    public void test() {
        // Arrange


        // Act & Assert
        assertTrue(true);
    }

}