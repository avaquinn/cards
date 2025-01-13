public class Game {
    public static void main(String[] args)
    {
        Display.runDisplay();

        System.out.println("foo");
        Deck deck = new Deck();
        deck.shuffle();

        for (int i = 0; i < 5; i++) {
            Card card = deck.draw();
            // System.out.println("Drew card: " + card);
        }


        System.out.println(checkCard(deck.draw(), deck.draw(), deck.draw()));


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

    public static boolean checkCard(Card priorTop, Card topOfPile, Card playedCard)
    {
        System.out.println("Drew card: " + topOfPile);
        System.out.println("Rank: " + cardTier(topOfPile));
        System.out.println("Drew card: " + playedCard);
        System.out.println("Rank: " + cardTier(playedCard));

        int pileTier = cardTier(topOfPile);
        int playedTier = cardTier(playedCard);


        // REFACTOR THIS AS A STACK
        if(pileTier == 3)
        {
            pileTier = cardTier(priorTop);
        }
        // Cards played on a 7 must be 7 or lower
        if (pileTier == 1 && playedTier <= 7) return true;

        //else if ()


        return false;
    }
}
