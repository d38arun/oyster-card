package oyster;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import oyster.controllers.CardController;
import oyster.controllers.JourneyController;
import oyster.repository.CardRepository;
import oyster.services.CardService;
import oyster.services.JourneyService;
import oyster.services.PricingService;
import static spark.Spark.*;

public class OysterApp {
    private static CardController cardController;
    private static JourneyController journeyController;

    static class ShutDown extends Thread {
        public void run() {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutDown());
        CardRepository cardRepository = new CardRepository();
        PricingService pricingService = new PricingService();
        CardService cardService = new CardService(cardRepository, pricingService);
        JourneyService journeyService = new JourneyService(cardService, pricingService);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .serializeNulls()
                .create();

        cardController = new CardController(cardService, gson);
        journeyController = new JourneyController(journeyService, cardService, gson);

        initServer();
    }

    private static void initServer() {
        get("/ping", (req, res) -> "Hello World");
        post("/cards", (req, res) -> cardController.handleCreate(req));
        post("/journeys", (req, res) -> journeyController.handle(req));
        get("/cards/:cardNumber/balance", (req, res) -> cardController.getBalance(req));
    }

}
