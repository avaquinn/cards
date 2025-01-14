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
            if(RuleBook.calculateCardTier(cardDrew) == 10) playedStack.clear();
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

        System.out.println("\n Can play?"+ RuleBook.checkHand(playedStack, player.currentHand())+ "\n");
        String input = typeIn.nextLine();
        System.out.println(RuleBook.checkHand(playedStack, player.currentHand()));

        int playedCardIndex = Integer.parseInt(input) - 1;
        if (!RuleBook.checkHand(playedStack, player.currentHand()))
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

}
