package src.repository;

import src.model.Campaign;
import src.interfaces.CampaignRepositoryInterface;

import java.io.*;
import java.util.*;

public class CampaignRepository implements CampaignRepositoryInterface {
    private final String file_path;

    public CampaignRepository(String file_path) {
        this.file_path = file_path;
        ensureFileExists();
    }

    private void ensureFileExists() {
        File file = new File(file_path);
        if(!file.exists()) {
            try{
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Error creating campaign file: " + e.getMessage());
            }
        }
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
            System.err.println("Failed to read campaign file: " + e.getMessage());
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
