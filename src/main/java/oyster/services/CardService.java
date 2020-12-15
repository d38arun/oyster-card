package oyster.services;


import oyster.models.Card;
import oyster.repository.CardRepository;

public class CardService {
    private CardRepository repository;
    private PricingService pricingService;

    public CardService(CardRepository repository, PricingService pricingService) {
        this.repository = repository;
        this.pricingService = pricingService;
    }

    public String createCard(double balance) {
        Card card = repository.save(balance);
        return card.getNumber();
    }

    public void deductMaxTripAmount(String cardNumber) {
        Card card = repository.getCard(cardNumber);
        if (card != null) {
            double maxDeductableAmount = pricingService.getMaxDeductableAmount();

            if (cardHasRequiredBalance(card, maxDeductableAmount)) {
                card.setBalance(card.getBalance() - pricingService.getMaxDeductableAmount());
            }
        }
    }

    public Card getCard(String cardNumber) {
        return repository.getCard(cardNumber);
    }

    public boolean cardHasRequiredBalance(Card card, double maxDeductableAmount) {
        return (card.getBalance() - maxDeductableAmount) >= 0;
    }
}
