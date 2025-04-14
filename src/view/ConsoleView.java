package view;

import controller.CommandController;
import model.Donation;
import model.Donor;
import model.Campaign;

import java.io.*;
import java.util.*;

public class ConsoleView {
    private final CommandController commandController;

    public ConsoleView(CommandController commandController) {
        this.commandController = commandController;
    }

    public void printSummary(){
        List<Donor> donors = new ArrayList<>(commandController.getDonorService().getAllDonors());
        List<Campaign> campaigns = new ArrayList<>(commandController.getCampaignService().getAllCampaigns());

        donors.sort(Comparator.comparing(Donor::getName));
        campaigns.sort(Comparator.comparing(Campaign::getName));

        System.out.println("Donors: ");
        for(Donor d : donors){
            System.out.println(d.getName() + ": $" + d.getTotal_donated() + " Average: $" + d.getAverageDonation() );
        }
        System.out.println("\nCampaigns: ");
        for(Campaign c: campaigns){
            System.out.println(c.getName() +": Total: $" + c.getTotal_raised() );
        }

    }

    public void printDonations() {
        List<Donation> donations = new ArrayList<>(commandController.getDonationService().getDonations());
        donations.sort(Comparator.comparing(Donation::getDonor_name));
        System.out.println("Donations:");
        for (Donation d : donations) {
            System.out.println(d.getDonor_name() + " donated $" + d.getAmount_donated() + " to "  + d.getCampaign_name());
        }
    }



    public void run(String[] args){
        Scanner sc = null;

        if(args.length == 1){
            try {
                sc = new Scanner(new File(args[0]));
                while(sc.hasNextLine()){
                    String line = sc.nextLine().trim();
                    if (line.isEmpty()) continue;
                    System.out.println(line);
                    commandController.processCommands(line);
                }

            } catch(FileNotFoundException e){
                System.err.println("File: " + args[0] + " not found." );
            } catch(Exception e){
                System.err.println(e);
            } finally{
                if(sc != null) sc.close();
                printSummary();
            }
        }


        System.out.println();


        sc = new Scanner(System.in);
        while(true){
            System.out.print("> ");
            String input = sc.nextLine().trim();
            commandController.processCommands(input);
        }

    }




}
