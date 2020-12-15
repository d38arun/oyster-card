package oyster.services;


import oyster.enums.Mode;
import oyster.enums.Station;
import oyster.models.Card;
import oyster.models.Journey;
import oyster.rules.PricingRules;

import java.util.Comparator;
import java.util.List;

public class JourneyService {
    private CardService cardService;
    private PricingService pricingService;

    public JourneyService(CardService cardService, PricingService pricingService) {
        this.cardService = cardService;
        this.pricingService = pricingService;
    }

    public Journey startJourney(Station start, Card card, Mode mode) {
        double maxDeductableAmount = pricingService.getMaxDeductableAmount();
        if (!cardService.cardHasRequiredBalance(card, maxDeductableAmount)) {
            System.out.println("Card does not have minimum balance");
            return null;
        }
        return new Journey(start, card, mode);
    }

    public void endJourney(Journey journey, Station end) {
        journey.setEndStation(end);
        List<PricingRules> applicateRules = pricingService.getApplicableRules(journey);
        double deductableFare = getMinFare(applicateRules);
        Card journeyPaymentCard = journey.getPaymentCard();
        journeyPaymentCard.setBalance(journeyPaymentCard.getBalance() - deductableFare);
    }

    private double getMinFare(List<PricingRules> applicateRules) {
        return applicateRules.stream().min(Comparator.comparing(PricingRules::getFare)).get().getFare();
    }
}
