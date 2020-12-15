package oyster.repository;


import oyster.models.Card;

import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private List<Card> cards;

    public CardRepository() {
        this.cards = new ArrayList<>();
    }

    public Card save(double balance) {
        Card card = new Card(String.valueOf(cards.size() + 1), balance);
        cards.add(card);
        return card;
    }

    public Card getCard(String number) {
        try {
            int cardNumber = Integer.parseInt(number);
            return cards.get(cardNumber - 1);
        } catch (NumberFormatException e) {
            System.out.println("Illegal Number exception: "+e);
            return null;
        }
    }
}
