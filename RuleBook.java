import java.util.ArrayList;

public class RuleBook {

    public static int calculateCardTier(Card card){
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
        int pileTier = calculateCardTier(topOfPile);

        // Calculates the integer tier of the played card
        int playedTier = calculateCardTier(playedCard);

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

    public static boolean checkHand(ArrayList<Card> pile, ArrayList<Card> hand)
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
