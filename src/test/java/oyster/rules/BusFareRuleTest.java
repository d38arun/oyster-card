package oyster.rules;

import org.junit.Test;
import oyster.enums.Mode;
import oyster.enums.Station;
import oyster.models.Card;
import oyster.models.Journey;

import static org.junit.Assert.*;

public class BusFareRuleTest {

    @Test
    public void shouldReturnFare() {
        assertEquals(1.8, new BusFareRule().getFare(), 0.0);
    }

    @Test
    public void shouldReturnFalseWhenRuleApplies() {
        Journey journey = new Journey(Station.EARLS_COURT, new Card("1", 30), Mode.BUS);
        journey.setEndStation(Station.WIMBLEDON);

        assertTrue(new BusFareRule().applies(journey));
    }

    @Test
    public void shouldReturnFalseWhenRuleDoesNotApply() {
        Journey journey = new Journey(Station.EARLS_COURT, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.WIMBLEDON);

        assertFalse(new BusFareRule().applies(journey));
    }

}