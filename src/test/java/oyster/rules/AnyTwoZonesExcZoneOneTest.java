package oyster.rules;

import org.junit.Test;
import oyster.enums.Mode;
import oyster.enums.Station;
import oyster.models.Card;
import oyster.models.Journey;


import static org.junit.Assert.*;

public class AnyTwoZonesExcZoneOneTest {

    @Test
    public void shouldReturnFare() {
        assertEquals(2.25, new AnyTwoZonesExcZoneOne().getFare(), 0.0);
    }

    @Test
    public void shouldReturnTrueWhenRuleApplies() {
        Journey journey = new Journey(Station.HAMMERSMITH, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.WIMBLEDON);

        assertTrue(new AnyTwoZonesExcZoneOne().applies(journey));
    }

    @Test
    public void shouldReturnFalseWhenRuleDoesNotApply() {
        Journey journey = new Journey(Station.HAMMERSMITH, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.EARLS_COURT);

        assertFalse(new AnyTwoZonesExcZoneOne().applies(journey));
    }

}