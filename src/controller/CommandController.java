package src.controller;

import src.service.CampaignService;
import src.service.DonationService;
import src.service.DonorService;


public class CommandController {
    private final DonationService donationService;
    private final CampaignService campaignService;
    private final DonorService donorService;

    public CommandController(DonationService donationService, CampaignService campaignService, DonorService donorService) {
        this.donationService = donationService;
        this.campaignService = campaignService;
        this.donorService = donorService;
    }

    public void processCommands(String line){
        if(line==null || line.trim().isEmpty()){return;}

        String[] parts = line.trim().split(" ");
        try{
            switch (parts[0]){
                case "Add":
                    if("Donor".equals(parts[1])){
                        String donorName = parts[2];
                        int monthly_limit = Integer.parseInt(parts[3].replace("$",""));
                        donorService.addDonor(donorName, monthly_limit);
                    }else if("Campaign".equals(parts[1])){
                        String campaignName = parts[2];
                        campaignService.addCampaign(campaignName);
                    }
                    break;

                case "Donate":
                    String donor_name = parts[1];
                    String campaign_name = parts[2];
                    int amount = Integer.parseInt(parts[3].replace("$",""));
                    donationService.makeDonation(donor_name,campaign_name,amount);
                    break;
            }
        }catch(Exception e){
            System.err.println("Error processing line: " + line);
            System.err.println("    -->" + e.getClass().getSimpleName() + ": " + e.getMessage());

        }
    }

    public void persist(){
        donorService.saveAllDonors();
        campaignService.saveAllCampaigns();
    }

    public CampaignService getCampaignService() {
        return campaignService;
    }

    public DonorService getDonorService() {
        return donorService;
    }
}
