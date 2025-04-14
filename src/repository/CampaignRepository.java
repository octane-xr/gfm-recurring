package repository;

import model.Campaign;
import interfaces.CampaignRepositoryInterface;
import util.CsvUtil;

import java.io.*;
import java.util.*;

public class CampaignRepository implements CampaignRepositoryInterface {
    private final String file_path;
    private final CsvUtil csv_util;

    public CampaignRepository(String file_path) throws IOException {
        this.file_path = file_path;
        csv_util = new CsvUtil();
        csv_util.ensureFileExists(file_path);
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

    @Override
    public void deleteCampaign(String campaignName) {
        List<Campaign> campaigns = loadAllCampaigns();
        campaigns.removeIf(c -> c.getName().equalsIgnoreCase(campaignName));
        saveAllCampaigns(campaigns);
    }
}
