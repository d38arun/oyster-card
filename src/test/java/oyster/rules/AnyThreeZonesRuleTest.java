package oyster.rules;

import org.junit.Test;
import oyster.enums.Mode;
import oyster.enums.Station;
import oyster.models.Card;
import oyster.models.Journey;

import static org.junit.Assert.*;

public class AnyThreeZonesRuleTest {

    @Test
    public void shouldReturnFare() {
        assertEquals(3.2, new AnyThreeZonesRule().getFare(), 0.0);
    }

    @Test
    public void shouldReturnTrueWhenRuleApplies() {
        Journey journey = new Journey(Station.EARLS_COURT, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.WIMBLEDON);

        assertTrue(new AnyThreeZonesRule().applies(journey));
    }

    @Test
    public void shouldReturnFalseWhenRuleDoesNotApply() {
        Journey journey = new Journey(Station.HAMMERSMITH, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.WIMBLEDON);

        assertFalse(new AnyThreeZonesRule().applies(journey));
    }

}