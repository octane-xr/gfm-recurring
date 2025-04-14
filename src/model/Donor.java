package model;

/**
 * Represents a donor who can contribute to campaigns with a specified monthly limit.
 */
public class Donor {
    private final String name;
    private int monthlyLimit;
    private int totalDonated;
    private int donationCount;

    /**
     * Constructs a new Donor with the
     * specified name and monthly limit.
     *
     * @param name         the name of the donor
     * @param monthlyLimit the monthly donation limit
     */
    public Donor(final String name, final int monthlyLimit) {
        this.name = name;
        this.monthlyLimit = monthlyLimit;
        this.totalDonated = 0;
        this.donationCount = 0;
    }

    /**
     * Checks if the donor can donate a specific
     * amount without exceeding the monthly limit.
     *
     * @param amount the amount to check
     * @return true if the donation is allowed, false otherwise
     */
    public boolean canDonate(final int amount) {
        return totalDonated + amount <= monthlyLimit;
    }

    /**
     * Registers a new donation, updating
     * total donated and donation count.
     *
     * @param amount the amount donated
     */
    public void addDonation(final int amount) {
        totalDonated += amount;
        donationCount++;
    }

    /**
     * Calculates and returns the average donation amount.
     *
     * @return the average donation amount,
     * or 0 if no donations have been made
     */
    public int getAverageDonation() {
        return donationCount == 0 ? 0 : totalDonated / donationCount;
    }

    public String getName() {
        return name;
    }

    public int getMonthlyLimit() {
        return monthlyLimit;
    }

    public int getTotalDonated() {
        return totalDonated;
    }

    public int getDonationCount() {
        return donationCount;
    }
}
