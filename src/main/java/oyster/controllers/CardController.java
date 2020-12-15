package oyster.controllers;

import com.google.gson.Gson;
import oyster.contracts.*;
import oyster.services.CardService;
import spark.Request;

public class CardController {
    private CardService cardService;
    private Gson gson;

    public CardController(CardService cardService, Gson gson) {
        this.cardService = cardService;
        this.gson = gson;
    }

    public String handleCreate(Request request) {
        String body = request.body();
        CardCreateRequest cardCreateRequest = gson.fromJson(body, CardCreateRequest.class);
        String cardNumber = cardService.createCard(cardCreateRequest.getBalance());
        CardCreateResponse cardCreateResponse = new CardCreateResponse(cardNumber);
        return gson.toJson(cardCreateResponse);
    }

    public String getBalance(Request req) {
        double balance = cardService.getCard(req.params(":cardNumber")).getBalance();
        GetCardBalanceResponse response = new GetCardBalanceResponse(balance);
        return gson.toJson(response);
    }
}
