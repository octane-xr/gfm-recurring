package src.service;

import src.interfaces.DonationRepositoryInterface;
import src.model.Donation;
import src.model.Donor;
import src.model.Campaign;

import java.util.*;

public class DonationService {
    private final DonationRepositoryInterface donationRepo;
    private final DonorService donorService;
    private final CampaignService campaignService;

    public DonationService(DonationRepositoryInterface donationRepo, DonorService donorService, CampaignService campaignService) {
        this.donationRepo = donationRepo;
        this.donorService = donorService;
        this.campaignService = campaignService;
    }

    public boolean makeDonation(String donorName, String campaignName, int amount) {
        Donor donor = donorService.get(donorName);
        Campaign campaign = campaignService.get(campaignName);

        if(donor==null || campaign==null || !donor.canDonate(amount)) {
            return false;
        }

        donor.addDonation(amount);
        campaign.add_donation(amount);
        Donation donation = new Donation(donorName, campaignName, amount);
        donationRepo.saveDonation(donation);
        return true;
    }

}
