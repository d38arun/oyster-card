package oyster.services;

import oyster.models.Journey;
import oyster.rules.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PricingService {
    private final List<PricingRules> rules;

    public PricingService() {
        this.rules = getPricingRules();
    }

    private List<PricingRules> getPricingRules() {
        return List.of(new OnlyZoneOneRule(),
                new AnyOneZoneOutsideZoneOneRule(),
                new AnyTwoZoneIncZoneOneRule(),
                new AnyTwoZonesExcZoneOne(),
                new AnyThreeZonesRule(),
                new BusFareRule());
    }

    public double getMaxDeductableAmount() {
        return calculateMaxDeductableAmount();
    }

    private double calculateMaxDeductableAmount() {
       return this.rules.stream().max(Comparator.comparing(PricingRules::getFare)).get().getFare();
    }

    public List<PricingRules> getApplicableRules(Journey journey) {
        List<PricingRules> applicableRules = this.rules.stream()
                .filter(rule -> rule.applies(journey))
                .collect(Collectors.toList());

        return applicableRules;
    }
}
