package oyster.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import oyster.enums.Mode;
import oyster.enums.Station;
import oyster.models.Card;
import oyster.models.Journey;
import oyster.rules.AnyTwoZoneIncZoneOneRule;
import oyster.rules.OnlyZoneOneRule;
import oyster.rules.PricingRules;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
public class JourneyServiceTest {
    @Mock
    private PricingService pricingService;

    @Mock
    private CardService cardService;

    private JourneyService journeyService;

    @Before
    public void setup() {
        initMocks(this);
        journeyService = new JourneyService(cardService, pricingService);
    }

    @Test
    public void shouldReturnNullWhenCardDoesNotHaveMinimumBalance() {
        Card card = new Card("1", 1);
        when(pricingService.getMaxDeductableAmount()).thenReturn(2.5);
        when(cardService.cardHasRequiredBalance(eq(card), anyDouble())).thenReturn(false);

        assertNull(journeyService.startJourney(Station.HOLBORN, card, Mode.TUBE));
    }

    @Test
    public void shouldReturnJourneyWhenCardHasMinimumBalance() {
        Card card = new Card("1", 2);
        when(pricingService.getMaxDeductableAmount()).thenReturn(1.0);
        when(cardService.cardHasRequiredBalance(eq(card), anyDouble())).thenReturn(true);

        Journey expectedJourney = new Journey(Station.HOLBORN, card, Mode.TUBE);

        assertEquals(expectedJourney, journeyService.startJourney(Station.HOLBORN, card, Mode.TUBE));
    }

    @Test
    public void shouldEndJourneyAndDeductBalance() {
        Card card = new Card("1", 30);
        List<PricingRules> applicableRules = List.of(new OnlyZoneOneRule(), new AnyTwoZoneIncZoneOneRule());
        Journey journey = new Journey(Station.HOLBORN, card, Mode.TUBE);

        when(pricingService.getApplicableRules(journey)).thenReturn(applicableRules);

        journeyService.endJourney(journey, Station.EARLS_COURT);

        assertEquals(Station.EARLS_COURT, journey.getEndStation());
        assertEquals(27.5, card.getBalance(), 0.0);
    }

}