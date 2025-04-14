package controller;

import service.CampaignService;
import service.DonationService;
import service.DonorService;

import java.util.*;
import java.io.*;
import view.ConsoleView;


public class CommandController {
    private final DonationService donationService;
    private final CampaignService campaignService;
    private final DonorService donorService;
    private final ConsoleView cv;

    public CommandController(DonationService donationService, CampaignService campaignService, DonorService donorService) {
        this.donationService = donationService;
        this.campaignService = campaignService;
        this.donorService = donorService;
        this.cv = new ConsoleView(this);
    }



    public void processCommands(String line){
        String[] parts = line.trim().split(" ");
        try{
            switch (parts[0]){
                case "Add":
                    if("Donor".equals(parts[1])){
                        if(parts.length != 4){
                            System.out.println("Missing arguments");
                            break;
                        }
                        String donorName = parts[2];

                        if(donorService.get(donorName) != null){
                            System.out.println("Donor " + donorName + " already exists");
                            break;
                        }

                        if(!parts[3].startsWith("$")){
                            System.out.println("Invalid format. Use: Add Donor [name] $[amount]");
                            break;
                        }

                        int monthly_limit = Integer.parseInt(parts[3].replace("$",""));

                        if(monthly_limit < 1){
                            System.out.println("Limit must be greater than 0");
                            break;
                        }

                        donorService.addDonor(donorName, monthly_limit);
                    }else if("Campaign".equals(parts[1])){
                        if(parts.length != 3){
                            System.out.println("Missing arguments");
                            break;
                        }
                        String campaignName = parts[2];

                        if(campaignService.get(campaignName) != null){
                            System.out.println("Campaign " + campaignName + " already exists");
                            break;
                        }

                        campaignService.addCampaign(campaignName);
                    }else{
                        System.out.println("Unknown command: " + parts[1]);
                    }
                    break;

                case "Donate":
                    if(parts.length != 4){
                        System.out.println("Missing arguments");
                        break;
                    }
                    String donor_name = parts[1];
                    if(donorService.get(donor_name) == null){
                        System.out.println("Donor " + donor_name + " does not exist");
                        break;
                    }

                    String campaign_name = parts[2];

                    if(campaignService.get(campaign_name) == null){
                        System.out.println("Campaign " + campaign_name + " doest not exist");
                        break;
                    }

                    if(!parts[3].startsWith("$")){
                        System.out.println("Invalid formate. Use: Donate [donorName] [campaignName] $[amount]");
                        break;
                    }

                    int amount = Integer.parseInt(parts[3].replace("$",""));
                    if(amount < 1){
                        System.out.println("Amount donated must be greater than 0");
                        break;
                    }

                    donationService.makeDonation(donor_name,campaign_name,amount);
                    break;

                case "Delete":
                    if(parts.length != 3){
                        System.out.println("Missing arguments");
                        break;
                    }

                    if("Donor".equals(parts[1])){
                        String donorName = parts[2];

                        if(donorService.get(donorName) == null){
                            System.out.println("Donor " + donorName + " doest not exist");
                            break;
                        }
                        donorService.deleteDonor(donorName);
                        break;

                    }
                    else if("Campaign".equals(parts[1])){
                        String campaignName = parts[2];

                        if(campaignService.get(campaignName) == null){
                            System.out.println("Campaign " + campaignName + " doest not exist");
                            break;
                        }

                        campaignService.deleteCampaign(campaignName);
                        break;

                    }

                case "Print_summary":
                    cv.printSummary();
                    break;

                case "Print_donations":
                    cv.printDonations();
                    break;


                case "Exit":
                    persist();
                    System.exit(0);

                default:
                    System.err.println(parts[0] + ": command not found");


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

    public DonationService getDonationService() {
        return donationService;
    }
}
