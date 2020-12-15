package oyster.utils;

import oyster.models.Journey;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PricingUtils {
    public static Set<String> getJourneyStations(Journey journey) {
        String[] startStationZones = journey.getStartStation().getZone().split(",");
        String[] endStationZones = journey.getEndStation().getZone().split(",");
        Set<String> journeyStations = new HashSet<>();

        addToSet(journeyStations, startStationZones);
        addToSet(journeyStations, endStationZones);

        return  journeyStations;
    }

    private static void addToSet(Set<String> elements, String[] zones) {
        elements.addAll(Arrays.asList(zones));
    }
}
