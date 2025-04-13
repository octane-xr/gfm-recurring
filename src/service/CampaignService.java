package src.service;

import src.interfaces.CampaignRepositoryInterface;
import src.model.Campaign;

import java.util.*;

public class CampaignService {
    private final CampaignRepositoryInterface campaignRepo;
    private final Map<String, Campaign> campaigns ;

    public CampaignService(CampaignRepositoryInterface campaignRepo) {
        this.campaignRepo = campaignRepo;
        this.campaigns = new HashMap<>();
        for(Campaign c : campaignRepo.loadAllCampaigns()){
            campaigns.put(c.getName(), c);
        }
    }

    public void addCampaign(String name){
        if(!campaigns.containsKey(name)){
            Campaign c = new Campaign(name);
            campaigns.put(name, c);
        }
    }

    public Campaign get(String name){
        return campaigns.get(name);
    }

    public Collection<Campaign> getAllCampaigns(){return campaigns.values();}

    public void saveAllCampaigns(){
        campaignRepo.saveAllCampaigns(new ArrayList<>(campaigns.values()));
    }
}
