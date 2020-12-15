package services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import oyster.models.Card;
import oyster.repository.CardRepository;
import oyster.services.CardService;
import oyster.services.PricingService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
public class CardServiceTest {
    @Mock
    private CardRepository repository;

    @Mock
    private PricingService pricingService;

    private CardService cardService;
    @Before
    public void setup() {
        initMocks(this);
        cardService = new CardService(repository, pricingService);
    }

    @Test
    public void shouldNotCallPricingServiceWhenCardIsNull() {
        String cardNumber = "1";
        when(repository.getCard(cardNumber)).thenReturn(null);

        cardService.deductMaxTripAmount(cardNumber);
        verify(pricingService, times(0)).getMaxDeductableAmount();

    }

    @Test
    public void shouldDeductMaxAmountFromCard() {
        String cardNumber = "1";
        Card card = new Card(cardNumber, 30);

        when(repository.getCard(cardNumber)).thenReturn(card);
        when(pricingService.getMaxDeductableAmount()).thenReturn(2.5);

        cardService.deductMaxTripAmount(cardNumber);
        assertEquals(27.5, card.getBalance(), 0.0);
    }

    @Test
    public void shouldReturnTrueWhenCardHasMaxDeductableBalance() {
        Card card = new Card("1", 30);
        assertTrue(cardService.cardHasRequiredBalance(card, 2.5));
    }

    @Test
    public void shouldReturnFalseWhenCardDoesNotHaveMaxDeductableBalance() {
        Card card = new Card("1", 1);
        assertFalse(cardService.cardHasRequiredBalance(card, 2.5));
    }

    @Test
    public void shouldCallCardRepositoryToGetCard() {
        when(repository.getCard("1")).thenReturn(new Card("1", 30));

        Card expectedCard = new Card("1", 30);

        assertEquals(expectedCard, cardService.getCard("1"));
        verify(repository, times(1)).getCard("1");
    }

    @Test
    public void shouldCreateCardAndReturnCardNumber() {
        when(repository.save(30.0)).thenReturn(new Card("1", 30));
        assertEquals("1", cardService.createCard(30));
    }
}