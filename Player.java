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
        // A crude insturment to ensure that played index will never be out of bounds
        playedCardIndex = playedCardIndex % playerHand.size();

        if (playerHand.getLast() != null)
        {
            return playerHand.remove(playedCardIndex);
        }
        return null;
    }

    public void printHands()
    {
        System.out.println("Player Hand: "+ playerHand);
        System.out.println("Player Top Cards: "+ playerTopCards);
        System.out.println("Player [[HIDDEN]] Cards: "+ playerHiddenCards);
    }

    public ArrayList<Card> getPlayerHand()
    {
        return playerHand;
    }

    public ArrayList<Card> getPlayerTopCards()
    {
        return playerHand;
    }
    public ArrayList<Card> getPlayerHiddenCards()
    {
        return playerHand;
    }


    public static Card playTurn(Scanner typeIn, Player player, ArrayList<Card> playedStack)
    {
        System.out.println("Your turn");
        if (!playedStack.isEmpty())System.out.println("You're playing on a: " + "\n" + playedStack.getLast());
        else System.out.println("You're playing on an empty stack");

        System.out.println("\nYour hand: " + player.getPlayerHand());
        printTurnOptions(player.getPlayerHand());

        System.out.println("\n Can play?"+ RuleBook.checkHand(playedStack, player.getPlayerHand())+ "\n");
        String input = typeIn.nextLine();
        System.out.println(RuleBook.checkHand(playedStack, player.getPlayerHand()));

        int playedCardIndex = Integer.parseInt(input) - 1;
        if (!RuleBook.checkHand(playedStack, player.getPlayerHand()))
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
