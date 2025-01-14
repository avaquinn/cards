import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args)
    {
        //Display.runDisplay();
        initializeGame();

    }
    public static void initializeGame()
    {
        Scanner typeIn = new Scanner(System.in);

        Deck deck = new Deck();
        deck.shuffle();

        ArrayList<Card> playedStack = new ArrayList<>(52);
        playedStack.addLast(deck.draw());

        ArrayList<Card> playerHand = new ArrayList<>(52);
        ArrayList<Card> playerTopCards = new ArrayList<>(3);
        ArrayList<Card> playerHiddenCards = new ArrayList<>(3);

        ArrayList<Card> computerHand = new ArrayList<>(52);
        ArrayList<Card> computerTopCards = new ArrayList<>(3);
        ArrayList<Card> computerHiddenCards = new ArrayList<>(3);
        for (int i = 0;  i < 3; i++)
        {
            playerHand.add(deck.draw());
            playerTopCards.add(deck.draw());
            playerHiddenCards.add(deck.draw());

            computerHand.add(deck.draw());
            computerTopCards.add(deck.draw());
            computerHiddenCards.add(deck.draw());
        }
        System.out.println("Player hand: " + playerHand);
        System.out.println("Computer hand: " + computerHand);


        Card cardDrew = deck.draw();

        do{
            System.out.println(checkCard(playedStack, cardDrew));
            System.out.println(".........");
            playedStack.addLast(cardDrew);
            if(cardTier(cardDrew) == 10) playedStack.clear();
            cardDrew = deck.draw();
        } while (cardDrew != null);

        System.out.println(playedStack);
        playTurn();
    }
    public static void playTurn()
    {
        System.out.println("Your turn");
    }


    public static int cardTier(Card card){
        String[] ranks = {"10", "2", "3", "4", "5", "6", "7", "8", "9", "Jack", "Queen", "King", "Ace"};
        int tier = 0;
        for(String rank : ranks)
        {
            tier++;
            if (card.getCardRank().equals(rank)) return tier;
        }
        return 0;
    }

    public static boolean checkCard(ArrayList<Card> Pile, Card playedCard)
    {
        System.out.print("Drew card: " + playedCard);
        System.out.println("    Rank: " + cardTier(playedCard));
        if (Pile.isEmpty()) return true;

        Card topOfPile = Pile.removeLast();
        System.out.print("Pile card: " + topOfPile);
        System.out.println("    Rank: " + cardTier(topOfPile));

        int pileTier = cardTier(topOfPile);
        int playedTier = cardTier(playedCard);


        // REFACTOR THIS AS A STACK
        if(pileTier == 3)
        {
            // pileTier = cardTier(topOfPile);
            return true;
        }
        // Cards played on a 7 must be 7 or lower
        else if (pileTier == 7 && playedTier <= 7) return true;
        else if (playedTier == 2) return true; //RESET CUE HERE
        else if (playedTier == 1) return true; //RESET CUE HERE
        else if (playedTier >= pileTier) return true;

        return false;
    }
}
