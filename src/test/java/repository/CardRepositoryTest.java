package repository;

import org.junit.Before;
import org.junit.Test;
import oyster.models.Card;
import oyster.repository.CardRepository;

import static org.junit.Assert.*;

public class CardRepositoryTest {

    private CardRepository cardRepository;

    @Before
    public void setup() {
        this.cardRepository = new CardRepository();
    }

    @Test
    public void shouldSaveCardWithBalance() {
        Card card = cardRepository.save(30);
        Card expectedCard = new Card("1", 30.0);
        assertEquals(expectedCard, card);

        Card anotherCard = cardRepository.save(20);
        Card antoherExpectedCard = new Card("2", 20.0);
        assertEquals(antoherExpectedCard, anotherCard);
    }

    @Test
    public void shouldGetBalanceGivenCardNumber() {
        cardRepository.save(30);
        Card expectedCard = new Card("1", 30.0);
        assertEquals(expectedCard, cardRepository.getCard("1"));
    }

    @Test
    public void shouldReturnNullWhenGivenInvalidNumber() {
        assertNull(cardRepository.getCard("abc"));
    }

}