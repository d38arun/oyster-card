package oyster.rules;

import oyster.constants.PricingConstants;
import oyster.enums.Mode;
import oyster.models.Journey;

import java.util.Set;

public class OnlyZoneOneRule implements PricingRules{
    @Override
    public double getFare() {
        return PricingConstants.ONLY_ZONE_ONE_FARE;
    }

    @Override
    public boolean applies(Journey journey) {
        if (!journey.getMode().getMode().equals(Mode.TUBE.getMode()))
            return false;
        return journey.getStartStation().getZone().contains("1")
                && journey.getEndStation().getZone().contains("1");
    }
}
