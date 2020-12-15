package oyster.rules;

import org.junit.Test;
import oyster.enums.Mode;
import oyster.enums.Station;
import oyster.models.Card;
import oyster.models.Journey;

import java.util.Set;

import static org.junit.Assert.*;

public class OnlyZoneOneRuleTest {

    @Test
    public void shouldReturnFare() {
        assertEquals(2.5, new OnlyZoneOneRule().getFare(), 0.0);
    }

    @Test
    public void shouldReturnTrueWhenRuleApplies() {
        Journey journey = new Journey(Station.HOLBORN, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.EARLS_COURT);
        assertTrue(new OnlyZoneOneRule().applies(journey));
    }

    @Test
    public void shouldReturnFalseWhenRuleDoesNotApply() {
        Journey journey = new Journey(Station.HOLBORN, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.HAMMERSMITH);
        assertFalse(new OnlyZoneOneRule().applies(journey));
    }

}