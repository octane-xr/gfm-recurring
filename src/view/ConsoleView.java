package src.view;

import src.controller.CommandController;
import src.model.Donor;
import src.model.Campaign;

import java.io.*;
import java.util.*;

public class ConsoleView {
    private final CommandController commandController;

    public ConsoleView(CommandController commandController) {
        this.commandController = commandController;
    }

    private void printSummary(){
        List<Donor> donors = new ArrayList<>(commandController.getDonorService().getAllDonors());
        List<Campaign> campaigns = new ArrayList<>(commandController.getCampaignService().getAllCampaigns());

        System.out.println("Donors: ");
        for(Donor d : donors){
            System.out.println(d.getName() + ": $" + d.getTotal_donated() + " Average: $" + d.getAverageDonation() );
        }
        System.out.println("\nCampaigns: ");
        for(Campaign c: campaigns){
            System.out.println(c.getName() +": Total: $" + c.getTotal_raised() );
        }

    }

    public void run(String[] args){
        Scanner sc = null;

        try{
            if(args.length == 1){
                sc = new Scanner(new File(args[0]));
            }else{
                if(System.in.available() == 0){
                    System.err.println("No imput file provided.");
                    return;
                }
                sc = new Scanner(System.in);
            }

            while(sc.hasNextLine()) {
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
        }
        System.out.println("\n");
        printSummary();
        commandController.persist();


    }




}
