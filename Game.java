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

        Player myPerson = new Player();
        myPerson.dealCards(deck);
        //myPerson.printHands();

        Player myBot = new Player();
        myBot.dealCards(deck);
        //myBot.printHands();

        ArrayList<Card> playedStack = new ArrayList<>(52);
        playedStack.addLast(deck.draw());

        Card cardDrew = deck.draw();

        do{
            //System.out.println(checkCard(playedStack, cardDrew));
            playTurn(typeIn, myPerson, playedStack);
            System.out.println(".........");
            playedStack.addLast(cardDrew);
            if(cardTier(cardDrew) == 10) playedStack.clear();
            cardDrew = deck.draw();
        } while (cardDrew != null);

        //System.out.println(playedStack);

    }
    public static Card playTurn(Scanner typeIn, Player player, ArrayList<Card> playedStack)
    {
        System.out.println("Your turn");
        System.out.println("You're playing on a: " + "\n" + playedStack.getLast());
        System.out.println("\nYour hand: " + player.currentHand());
        printTurnOptions(player.currentHand());

        System.out.println("\n Can play?"+ checkAllCards(playedStack, player.currentHand())+ "\n");
        String input = typeIn.nextLine();
        System.out.println(checkAllCards(playedStack, player.currentHand()));

        int playedCardIndex = Integer.parseInt(input) - 1;
        if (!checkAllCards(playedStack, player.currentHand()))
        {
            System.out.println("Draw");
            player.updateHand(playedStack);
        }
        else
        {
            playedStack.add(player.playFromHand(playedCardIndex));
        }
        System.out.println(playedStack);
        //Card myCard = playedStack.removeLast(); // PLACEHOLDER
        Card myCard = new Card("foo", "foo");

        return myCard;
    }

    public static void printTurnOptions(ArrayList<Card> currentHand)
    {
        // Prints available turn options
        for (int i = 0; i < currentHand.size(); i++) {
            System.out.println(i + 1 + ". " + currentHand.get(i));
        }
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

    public static boolean checkCard(ArrayList<Card> pile, Card playedCard)
    {
        // Can always play on an empty pile
        if (pile.isEmpty()) return true;

        // Views the last card from the pile
        Card topOfPile = pile.getLast();

        // Calculates the integer tier of the pile card
        int pileTier = cardTier(topOfPile);

        // Calculates the integer tier of the played card
        int playedTier = cardTier(playedCard);

        // REFACTOR THIS AS A STACK
        if(pileTier == 3)
        {
            return true;
            // pile.getLast();
            // checkCard(pile, playedCard);
        }

        // Cards played on a 7 must be 7 or lower
        else if (pileTier == 7 && playedTier <= 7) return true;

        // Any card can be played on a two
        else if (playedTier == 2) return true; //RESET CUE HERE

        // Any card can be played on a ten
        else if (playedTier == 1) return true; //RESET CUE HERE

        // Other cards played must be higher in value than the card they were played on
        else if (playedTier >= pileTier) return true;

        return false;
    }

    public static boolean checkAllCards(ArrayList<Card> pile, ArrayList<Card> hand)
    {
        // Iterates through all cards in hand, and confirms if you can play on the pile.
        System.out.print("Pile card: " + pile.getLast());
        for (int i = 0; i < hand.size(); i++)
        {
            if(checkCard(pile, hand.get(i))) return true;
        }
        return false;

    }
}
