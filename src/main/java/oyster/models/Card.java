package oyster.models;

import java.util.Objects;

public class Card {
    private String number;
    private double balance;

    public Card(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Double.compare(card.balance, balance) == 0 &&
                Objects.equals(number, card.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, balance);
    }
}
