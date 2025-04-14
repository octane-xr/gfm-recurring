package interfaces;

import model.Campaign;
import java.util.List;

/**
 * Interface for accessing and manipulating campaign data in the data source.
 */
public interface CampaignRepositoryInterface {

    List<Campaign> loadAllCampaigns();
    void saveAllCampaigns(List<Campaign> campaigns);
    void deleteCampaign(String campaignName);
}
