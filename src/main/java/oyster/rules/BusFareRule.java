package oyster.rules;

import oyster.constants.PricingConstants;
import oyster.enums.Mode;
import oyster.models.Journey;

public class BusFareRule implements PricingRules {
    @Override
    public double getFare() {
        return PricingConstants.BUS_FARE;
    }

    @Override
    public boolean applies(Journey journey) {
        return journey.getMode() == Mode.BUS;
    }
}
