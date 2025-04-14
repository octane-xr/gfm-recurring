package service;

import interfaces.DonationRepositoryInterface;
import model.Donation;
import model.Donor;
import model.Campaign;

import java.util.List;

/**
 * Service class responsible for handling donation-related operations.
 */
public final class DonationService {
    private final DonationRepositoryInterface donationRepo;
    private final DonorService donorService;
    private final CampaignService campaignService;

    /**
     * Constructs a new DonationService with dependencies injected.
     *
     * @param donationRepo     the donation repository
     * @param donorService     the donor service
     * @param campaignService  the campaign service
     */
    public DonationService(final DonationRepositoryInterface donationRepo,
                           final DonorService donorService,
                           final CampaignService campaignService) {
        this.donationRepo = donationRepo;
        this.donorService = donorService;
        this.campaignService = campaignService;

    }

    public List<Donation> getDonations() {
        return donationRepo.loadAllDonations();
    }

    /**
     * Attempts to make a donation from a donor to a campaign.
     *
     * @param donorName    the name of the donor
     * @param campaignName the name of the campaign
     * @param amount       the amount to be donated
     * @return true if the donation was successful, false otherwise
     */
    public boolean makeDonation(final String donorName,
                                final String campaignName,
                                final int amount) {
        Donor donor = donorService.get(donorName);
        Campaign campaign = campaignService.get(campaignName);

        if (!donor.canDonate(amount)) {
            System.out.println("The ammount desired to donate surpassed the monthly limit. Total donated: $"
                    + donor.getTotalDonated()
                    + ". Limit: $"
                    + donor.getMonthlyLimit());
            return false;
        }

        donor.addDonation(amount);
        campaign.addDonation(amount);
        Donation donation = new Donation(donorName, campaignName, amount);
        donationRepo.saveDonation(donation);
        return true;
    }

}
