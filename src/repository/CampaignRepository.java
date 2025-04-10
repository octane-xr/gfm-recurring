package src.repository;

import src.model.Campaign;
import src.interfaces.CampaignRepositoryInterface;

import java.io.*;
import java.util.*;

public class CampaignRepository implements CampaignRepositoryInterface {
    private final String file_path;

    public CampaignRepository(String file_path) {
        this.file_path = file_path;
    }

    @Override
    public List<Campaign> loadAllCampaigns() {
        List<Campaign> campaigns = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(file_path))){
            while(sc.hasNextLine()){
                String[] parts = sc.nextLine().split(",");
                if(parts.length<2) continue;
                Campaign c = new Campaign(parts[0]);
                c.add_donation(Integer.parseInt(parts[1]));
                campaigns.add(c);
            }
        }catch (IOException e) {
            System.out.println("No campaign file found, creating new one...");
        }
        return campaigns;
    }

    @Override
    public void saveAllCampaigns(List<Campaign> campaigns) {
        try(PrintWriter pw = new PrintWriter(new File(file_path))){
            for(Campaign c : campaigns){
                pw.println(c.getName()+","+c.getTotal_raised());
            }

        }catch (IOException e) {
            throw new RuntimeException("Failed to save campaigns. Error: " + e.getMessage());
        }

    }
}
