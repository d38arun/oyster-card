package oyster.rules;

import oyster.constants.PricingConstants;
import oyster.enums.Mode;
import oyster.models.Journey;
import oyster.utils.PricingUtils;

import java.util.Set;

public class AnyThreeZonesRule implements PricingRules {

    @Override
    public double getFare() {
        return PricingConstants.ANY_THREE_ZONES_FARE;
    }

    @Override
    public boolean applies(Journey journey) {
        if (!journey.getMode().getMode().equals(Mode.TUBE.getMode()))
            return false;
        Set<String> zones = PricingUtils.getJourneyStations(journey);
        return zones.size() == 3;
    }
}
