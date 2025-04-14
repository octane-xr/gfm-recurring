package service;

import interfaces.DonationRepositoryInterface;
import model.Donation;
import model.Donor;
import model.Campaign;

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

    public List<Donation> getDonations() {return donationRepo.loadAllDonations();}

    public boolean makeDonation(String donorName, String campaignName, int amount) {
        Donor donor = donorService.get(donorName);
        Campaign campaign = campaignService.get(campaignName);

        if(!donor.canDonate(amount)) {
            System.out.println("The ammount desired to donate surpassed the monthly limit. Total donated: $" + donor.getTotal_donated() + ". Limit: $" + donor.getMonthly_limit());
            return false;
        }

        donor.addDonation(amount);
        campaign.add_donation(amount);
        Donation donation = new Donation(donorName, campaignName, amount);
        donationRepo.saveDonation(donation);
        return true;
    }

}
