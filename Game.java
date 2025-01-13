public class Game {
    public static void main(String[] args)
    {
        System.out.println("foo");
        Deck deck = new Deck();
        deck.shuffle();

        for (int i = 0; i < 5; i++) {
            Card card = deck.draw();
            System.out.println("Drew card: " + card);
        }

    }
}
