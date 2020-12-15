package oyster.utils;

import org.junit.Test;
import oyster.enums.Mode;
import oyster.enums.Station;
import oyster.models.Card;
import oyster.models.Journey;

import java.util.Set;

import static org.junit.Assert.*;

public class PricingUtilsTest {

    @Test
    public void shouldAddAllJourneyZones() {
        Journey journey = new Journey(Station.HOLBORN, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.HAMMERSMITH);

        Set<String> expectedZones = Set.of("1","2");
        assertEquals(expectedZones, PricingUtils.getJourneyStations(journey));
    }

    @Test
    public void shouldAddAllUniqueJourneyZones() {
        Journey journey = new Journey(Station.HOLBORN, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.EARLS_COURT);

        Set<String> expectedZones = Set.of("1","2");
        assertEquals(expectedZones, PricingUtils.getJourneyStations(journey));
    }

    @Test
    public void shouldAddAllAvailableJourneyZones() {
        Journey journey = new Journey(Station.EARLS_COURT, new Card("1", 30), Mode.TUBE);
        journey.setEndStation(Station.WIMBLEDON);

        Set<String> expectedZones = Set.of("1","2","3");
        assertEquals(expectedZones, PricingUtils.getJourneyStations(journey));
    }

}