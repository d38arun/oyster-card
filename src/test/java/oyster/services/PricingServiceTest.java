package oyster.services;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import oyster.constants.PricingConstants;
import oyster.enums.Mode;
import oyster.enums.Station;
import oyster.models.Card;
import oyster.models.Journey;
import oyster.rules.AnyTwoZoneIncZoneOneRule;
import oyster.rules.OnlyZoneOneRule;
import oyster.rules.PricingRules;

import java.util.List;

import static org.junit.Assert.*;

public class PricingServiceTest {

    @Test
    public void shouldReturnMaxDeductableDeposit() {
        assertEquals(PricingConstants.ANY_THREE_ZONES_FARE, new PricingService().getMaxDeductableAmount(), 0.0);
    }

    @Test
    public void shouldReturnApplicableRules() {
        Journey journey = new Journey(Station.HOLBORN, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.EARLS_COURT);

        List<PricingRules> actualRules = new PricingService().getApplicableRules(journey);
        assertEquals(2, actualRules.size());
        assertThat(actualRules.get(0), instanceOf(OnlyZoneOneRule.class));
        assertThat(actualRules.get(1), instanceOf(AnyTwoZoneIncZoneOneRule.class));
    }
}