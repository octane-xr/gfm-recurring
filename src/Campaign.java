package src;

public class Campaign {
    String name;
    int total_raised;

    public Campaign(String name) {
        this.name = name;
    }

    public void addDonation(int amount) {
        total_raised += amount;
    }

    public int getTotalRaised() {
        return total_raised;
    }

    public String getName() {
        return name;
    }
}