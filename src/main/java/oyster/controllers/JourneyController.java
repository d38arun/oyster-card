package oyster.controllers;

import com.google.gson.Gson;
import oyster.contracts.JourneyStartRequest;
import oyster.enums.Mode;
import oyster.enums.Station;
import oyster.models.Journey;
import oyster.services.CardService;
import oyster.services.JourneyService;
import spark.Request;

public class JourneyController {
    private JourneyService journeyService;
    private CardService cardService;
    private Gson gson;

    public JourneyController(JourneyService journeyService, CardService cardService, Gson gson) {
        this.journeyService = journeyService;
        this.cardService = cardService;
        this.gson = gson;
    }

    public String handle(Request req) {
        JourneyStartRequest request = gson.fromJson(req.body(), JourneyStartRequest.class);
        Journey journey = journeyService.startJourney(Station.valueOf(request.getStation()), cardService.getCard(request.getCardNumber()),
                Mode.valueOf(request.getMode()));
        journeyService.endJourney(journey, Station.valueOf(request.getEndStation()));
        return "Journey ended";
    }
}
