package service;

import interfaces.CampaignRepositoryInterface;
import model.Campaign;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Service responsible for managing campaign logic,
 * including in-memory storage and
 * synchronization with the persistent repository.
 */
public final class CampaignService {
    private final CampaignRepositoryInterface campaignRepo;
    private final Map<String, Campaign> campaigns;

    /**
     * Constructs a new CampaignService with the specified repository.
     * Loads existing campaigns from the repository into memory.
     *
     * @param campaignRepo the campaign repository interface
     */
    public CampaignService(final CampaignRepositoryInterface campaignRepo) {
        this.campaignRepo = campaignRepo;
        this.campaigns = new HashMap<>();
        for (Campaign c : campaignRepo.loadAllCampaigns()) {
            campaigns.put(c.getName(), c);
        }
    }

    /**
     * Adds a new campaign if it doesn't already exist.
     *
     * @param name the name of the campaign to add
     */
    public void addCampaign(final String name) {
        if (!campaigns.containsKey(name)) {
            Campaign c = new Campaign(name);
            campaigns.put(name, c);
        }
    }


    public Campaign get(final String name) {
        return campaigns.get(name);
    }

    public Collection<Campaign> getAllCampaigns() {
        return campaigns.values();
    }

    /**
     * Saves all campaigns to the persistent storage via the repository.
     */
    public void saveAllCampaigns() {
        campaignRepo.saveAllCampaigns(new ArrayList<>(campaigns.values()));
    }

    /**
     * Deletes a campaign from memory and persistent storage.
     *
     * @param name the name of the campaign to delete
     */
    public void deleteCampaign(final String name) {
        campaigns.remove(name);
        campaignRepo.deleteCampaign(name);
    }
}
