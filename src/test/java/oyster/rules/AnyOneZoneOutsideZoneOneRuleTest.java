package oyster.rules;

import org.junit.Test;
import oyster.enums.Mode;
import oyster.enums.Station;
import oyster.models.Card;
import oyster.models.Journey;

import static org.junit.Assert.*;

public class AnyOneZoneOutsideZoneOneRuleTest {

    @Test
    public void getFare() {
        assertEquals(2.0, new AnyOneZoneOutsideZoneOneRule().getFare(), 0.0);
    }

    @Test
    public void shouldReturnTrueWhenRuleApplies() {
        Journey journey = new Journey(Station.HAMMERSMITH, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.HAMMERSMITH);

        assertTrue(new AnyOneZoneOutsideZoneOneRule().applies(journey));
    }

    @Test
    public void shouldReturnFalseWhenRuleDoesNotApply() {
        Journey journey = new Journey(Station.HOLBORN, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.EARLS_COURT);

        assertFalse(new AnyOneZoneOutsideZoneOneRule().applies(journey));
    }

    @Test
    public void shouldReturnFalseWhenMoreThanOneZone() {
        Journey journey = new Journey(Station.HOLBORN, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.WIMBLEDON);

        assertFalse(new AnyOneZoneOutsideZoneOneRule().applies(journey));
    }


}