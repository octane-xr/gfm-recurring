package model;

/**
 * Represents a fundraising campaign that tracks the total amount raised.
 */
public class Campaign {
    private String name;
    private int totalRaised;

    /**
     * Constructs a new Campaign with the given name
     * and initializes totalRaised to 0.
     *
     * @param name the name of the campaign
     */
    public Campaign(final String name) {
        this.name = name;
        this.totalRaised = 0;
    }

    /**
     * Adds a donation amount to the campaign's total raised value.
     *
     * @param amount the amount to add
     */
    public void addDonation(final int amount) {
        totalRaised += amount;
    }

    public String getName() {
        return name;
    }

    public int getTotalRaised() {
        return totalRaised;
    }
}
