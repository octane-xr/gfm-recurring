package src;
import src.model.Campaign;
import src.model.Donor;

import java.util.*;

public class DonorCampaignManager {
    Map<String, Donor> donors = new HashMap<>();
    Map<String, Campaign> campaigns = new HashMap<>();

    public void createDonor(String name, int monthly_limit){
        Donor donor = new Donor(name,monthly_limit);
        donors.put(name, donor);
    }

    public void createCampaign(String name){
        Campaign campaign = new Campaign(name);
        campaigns.put(name, campaign);
    }

    public void makeDonation(String donor_name, String campaign_name, int amount){
        Donor donor = donors.get(donor_name);
        Campaign campaign = campaigns.get(campaign_name);
        if(donor != null && campaign != null){
            if(donor.makeDonation(amount)){
                campaign.addDonation(amount);
            }
        }

    }

    public void printSummary(){
        List<Donor> donors_list = new ArrayList<>(donors.values());
        List<Campaign> campaigns_list = new ArrayList<>(campaigns.values());

        donors_list.sort(Comparator.comparing(Donor::getName));
        campaigns_list.sort(Comparator.comparing(Campaign:: getName));
        System.out.println("\nDonors: ");
        for(Donor d : donors_list){
            System.out.println(d.name + ": Total: $" + d.totalDonated() + " Average: $" + d.averageDonation());
        }

        System.out.println("\nCampaigns: ");

        for(Campaign c : campaigns_list){
            System.out.println(c.name + ": Total: $" + c.getTotalRaised());
        }
    }
}