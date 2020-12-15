package oyster.rules;

import oyster.models.Journey;

import java.util.Set;

public interface PricingRules {
    double getFare();
    boolean applies(Journey j);
}
