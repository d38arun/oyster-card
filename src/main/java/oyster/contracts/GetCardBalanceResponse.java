package oyster.contracts;

public class GetCardBalanceResponse {
    private double balance;

    public GetCardBalanceResponse(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
