package model;

public class Donation {
    private final String donor_name;
    private final String campaign_name;
    private final int amount_donated;

    public Donation(String donor_name, String campaign_name, int amount_donated) {
        this.donor_name = donor_name;
        this.campaign_name = campaign_name;
        this.amount_donated = amount_donated;
    }

    public String getDonor_name() {
        return donor_name;
    }

    public String getCampaign_name() {
        return campaign_name;
    }

    public int getAmount_donated() {
        return amount_donated;
    }

}
