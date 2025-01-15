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
            Player.playTurn(typeIn, myPerson, playedStack);
            System.out.println(".........");
            playedStack.addLast(cardDrew);
            if(RuleBook.calculateCardTier(cardDrew) == 10) playedStack.clear();
            // 10 THROWS AN ERROR RIGHT NOW
            cardDrew = deck.draw();
        } while (cardDrew != null);

        //System.out.println(playedStack);

    }

}
