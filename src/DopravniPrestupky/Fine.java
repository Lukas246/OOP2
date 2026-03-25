package DopravniPrestupky;

public class Fine {
    private double amount;
    private String description;

    public Fine(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
