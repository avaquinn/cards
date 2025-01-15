import java.util.ArrayList;
import java.util.Scanner;

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

    public void updateHand(Card card)
    {
        playerHand.add(card);
    }

    public void updateHand(ArrayList<Card> pile)
    {
        while (!pile.isEmpty()) playerHand.add(pile.removeFirst());
    }

    public Card playFromHand(int playedCardIndex)
    {
        if (playerHand.getLast() != null)
        {
            return playerHand.remove(playedCardIndex);
        }
        Card myCard = new Card("foo", "Ace");
        return myCard;
    }

    public void printHands()
    {
        System.out.println("Player Hand: "+ playerHand);
        System.out.println("Player Top Cards: "+ playerTopCards);
        System.out.println("Player [[HIDDEN]] Cards: "+ playerHiddenCards);
    }

    public ArrayList<Card> currentHand()
    {
        return playerHand;
    }

    public static Card playTurn(Scanner typeIn, Player player, ArrayList<Card> playedStack)
    {
        System.out.println("Your turn");
        System.out.println("You're playing on a: " + "\n" + playedStack.getLast());
        System.out.println("\nYour hand: " + player.currentHand());
        printTurnOptions(player.currentHand());

        System.out.println("\n Can play?"+ RuleBook.checkHand(playedStack, player.currentHand())+ "\n");
        String input = typeIn.nextLine();
        System.out.println(RuleBook.checkHand(playedStack, player.currentHand()));

        int playedCardIndex = Integer.parseInt(input) - 1;
        if (!RuleBook.checkHand(playedStack, player.currentHand()))
        {
            System.out.println("Draw");
            player.updateHand(playedStack);
        }
        else playedStack.add(player.playFromHand(playedCardIndex));

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

}
