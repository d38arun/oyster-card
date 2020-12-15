package oyster.models;


import oyster.enums.Mode;
import oyster.enums.Station;

import java.util.Objects;

public class Journey {
    private Station startStation;
    private Station endStation;
    private Card paymentCard;
    private Mode mode;

    public Journey(Station startStation, Card paymentCard, Mode mode) {
        this.startStation = startStation;
        this.paymentCard = paymentCard;
        this.mode = mode;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    public Card getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(Card paymentCard) {
        this.paymentCard = paymentCard;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return startStation == journey.startStation &&
                endStation == journey.endStation &&
                Objects.equals(paymentCard, journey.paymentCard) &&
                mode == journey.mode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startStation, endStation, paymentCard, mode);
    }
}
