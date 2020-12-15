# Oyster Card Problem   

To run the server:
`./gradlew run`

To perform health check: <br/>
```curl -X GET http://localhost:4567/ping```

To create a card:<br/>
```curl --location --request POST 'localhost:4567/cards' \
 --header 'Content-Type: application/json' \
 --data-raw '{
     "balance": 30.0
 }'
 ```

To create a journey:<br/>

```
curl --location --request POST 'localhost:4567/journeys' \
   --header 'Content-Type: application/json' \
   --data-raw '{
       "station": "WIMBLEDON",
       "card_number": "1",
       "mode": "TUBE",
       "end_station": "HAMMERSMITH"
   }'
```

To get balance:<br/>
```
curl --location --request GET 'localhost:4567/cards/1/balance'
```

Note:
* The controllers and contracts do not have tests as they were added to communicate with the application.
* No validation logic has been added to the endpoints. Please test with valid data.
* All the core logic has tests.