package model;

/**
 * Represents a donation made by a donor to a campaign.
 */
public class Donation {
    private final String donorName;
    private final String campaignName;
    private final int amountDonated;

    /**
     * Constructs a new Donation with the given donor, campaign, and amount.
     *
     * @param donorName     the name of the donor
     * @param campaignName  the name of the campaign
     * @param amountDonated the amount donated
     */
    public Donation(final String donorName,
                    final String campaignName,
                    final int amountDonated) {
        this.donorName = donorName;
        this.campaignName = campaignName;
        this.amountDonated = amountDonated;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public int getAmountDonated() {
        return amountDonated;
    }

}
