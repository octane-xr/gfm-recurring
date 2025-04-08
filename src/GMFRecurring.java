package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GMFRecurring {
    public static void main(String[] args) {
        DonorCampaignManager manager = new DonorCampaignManager();
        Scanner sc;

        try {
            if(args.length == 1){
                sc = new Scanner(new File(args[0]));
            }else{
                sc = new Scanner(System.in);
            }

            while(sc.hasNextLine()){
                String line = sc.nextLine().trim();
                if (line.isEmpty()){continue;}
                String[] parts = line.split(" ");
                switch(parts[0]){
                    case "Add":
                        if(parts[1].equals("Donor")){
                            String donor_name = parts[2];
                            int monthly_limit = Integer.parseInt(parts[3].replace("$", ""));
                            manager.createDonor(donor_name,monthly_limit);
                        }
                        else if(parts[1].equals("Campaign")){
                            String campaign_name = parts[2];
                            manager.createCampaign(campaign_name);
                        }
                        break;

                    case "Donate":
                        String donor_name = parts[1];
                        String campaign_name = parts[2];
                        int donation_amount = Integer.parseInt(parts[3].replace("$", ""));
                        manager.makeDonation(donor_name,campaign_name,donation_amount);
                        break;
                }
            }

            sc.close();
            manager.printSummary();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
